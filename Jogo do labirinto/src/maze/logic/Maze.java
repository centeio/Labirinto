package maze.logic;

import java.awt.Point;
import java.util.Vector;

/**
 * The Class Maze.
 */
public class Maze {
	
	/** The Constant STAY. */
	private static final int STAY = 4;
	
	/** The mode. */
	private Mode mode;
	
	/** The status. */
	private MazeStatus status;
	
	/** The hero. */
	private Hero hero;
	
	/** The dragons. */
	private Vector<Dragon> dragons = new Vector<Dragon>();
	
	/** The sword. */
	private Sword sword;
	
	/** The exit. */
	private Exit exit;
	
	/** The maze. */
	private char maze[][];

	/**
	 * Instantiates a new maze.
	 *
	 * @param maze matrix of char representing the maze
	 * @param mode number of mode 1:STILL 2:MOVE 3:SLEEP
	 */
	public Maze(char[][] maze, int mode){
		this.maze = maze;

		for(int i = 0; i < maze.length; i++)
			for(int j = 0; j < (maze[0]).length; j++)
				switch(maze[i][j]){
				case 'H':
					hero = new Hero(j,i);
					maze[i][j] = ' ';
					break;
				case 'E':
					sword = new Sword(j,i);
					maze[i][j] = ' ';
					break;
				case 'D':
					dragons.add(new Dragon(j,i));
					maze[i][j] = ' ';
					break;
				case 'S':
					exit = new Exit(j,i);
					maze[i][j] = ' ';
					break;					
				}

		status = MazeStatus.HeroUnarmed;
		switch(mode){
		case 1:
			this.mode = Mode.STILL;
			break;
		case 2: 
			this.mode = Mode.MOVE;
			break;
		case 3:
			this.mode = Mode.SLEEP;
			break;
		}
	}

	/**
	 * Updates the game moving the hero in the direction he/she chose, changing the status and moving the dragon if necessary.
	 *
	 * @param dir represents the direction taken by the hero north(N or n), south(S or s), east (E or e) or west(o or o)
	 * @return the status of the game 0 if still running, 1 if hero left the maze and 2 if dragon kills the hero
	 */
	public int update(char dir){
		
		int dragonDir = STAY;

		//Move hero
		hero.move(dir);

		//If wall undo move
		if(hero.getPosY() < 0 || hero.getPosX() < 0 || hero.getPosY() >= maze.length|| hero.getPosX() >= maze.length || maze[hero.getPosY()][hero.getPosX()] == 'X')
			hero.undoMove(dir);

		//Move every dragon
		for(int i = 0; i < dragons.size() ; i++){
			dragonDir = dragons.get(i).update(mode);
			boolean dragonThere = false;

			//If wall or other dragon undo move
			for(int j = 0; j < dragons.size() ; j++)
				if(i != j && dragons.get(j).getPosX() == dragons.get(i).getPosX() && dragons.get(j).getPosY() == dragons.get(i).getPosY()){
					dragonThere = true;
				}
			
			if(dragons.get(i).getPosY() < 0 || dragons.get(i).getPosX() < 0 || 
					dragons.get(i).getPosY() >= maze.length|| dragons.get(i).getPosX() >= maze.length ||
					maze[dragons.get(i).getPosY()][dragons.get(i).getPosX()] == 'X' || dragonThere)
				dragons.get(i).undoMove(dragonDir);
		}

		//Hero got sword
		if(hero.getPosition().equals(sword.getPosition())){
			hero.arm();
			sword.disappear();
			status = MazeStatus.HeroArmed;
		}

		//Hero died
		for(int i = 0; i < dragons.size(); i++){
			if(status == MazeStatus.HeroUnarmed	&& ( 
						(hero.getPosY() == dragons.get(i).getPosY() - 1 && hero.getPosX() == dragons.get(i).getPosX())
					||  (hero.getPosY() == dragons.get(i).getPosY() + 1 && hero.getPosX() == dragons.get(i).getPosX())
					||	(hero.getPosX() == dragons.get(i).getPosX() - 1 && hero.getPosY() == dragons.get(i).getPosY())
					||	(hero.getPosX() == dragons.get(i).getPosX() + 1 && hero.getPosY() == dragons.get(i).getPosY()))){
				status = MazeStatus.HeroDied;
				return 2;
			}
		}

		//Hero killed dragon
		int i = 0;
		while( i < dragons.size()){
			if((status == MazeStatus.HeroArmed ||  status == MazeStatus.HeroSlayed)&&  hero.getPosition().equals(dragons.get(i).getPosition())){
				//TODO see if function can be removed: dragons.get(i).kill();
				dragons.remove(dragons.get(i));
				status = MazeStatus.HeroSlayed;
			}
			else
				i++;
		}

		//Hero kills every dragon and leaves
		if(dragons.size() == 0 && hero.getPosition().equals(exit.getPosition())){
			status = MazeStatus.HeroWon;
			return 1;
		}

		return 0;
	}

	/* Turns matrix of chars containing maze with pieces in their respective places (game board) into a string
	 * 
	 * @see java.lang.Object#toString()
	 * @returns string representation of game board
	 */
	public String toString(){
		
		char[][] board = getMaze();
		String str = new String();

		for(char[] line: board){
			for(char pos: line)
				str += pos + " ";
			str += "\n";
		}

		return str;
	}

	/**
	 * Gets the hero's position.
	 *
	 * @return position of the hero
	 */
	public Point getHeroPosition(){
		return hero.getPosition();
	}

	/**
	 * Gets the first dragon's position.
	 *
	 * @return the position of the first dragon in dragons vector
	 */
	public Point getDragonPosition(){
		return dragons.get(0).getPosition();
	}

	/**
	 * The symbolic representation of the hero.
	 *
	 * @return char symbolizing the hero
	 */
	public char getHeroSymbol(){
		return hero.getSymbol();
	}
	
	/**
	 * Gets the number dragons to kill.
	 *
	 * @return number dragons left to kill
	 */
	public int getNumDragonsToKill(){
		return dragons.size();
	}

	/**
	 * The symbolic representation of the first dragon.
	 *
	 * @return char symbolizing the first dragon
	 */
	public char getDragonSymbol(){
		Dragon dragon = dragons.get(0);
		return dragon.getSymbol();
	}

	/**
	 * The symbolic representation of the sword.
	 *
	 * @return char symbolizing the sword
	 */
	 
	public char getSwordSymbol(){
		return sword.getSymbol();
	}

	/**
	 * Gets the status of the maze.
	 *
	 * @return maze's status
	 */
	public MazeStatus getStatus(){
		return status;
	}

	/**
	 * Gets the status of the first dragon.
	 *
	 * @return the first dragon's status
	 */
	public Dragon.State getDragonStatus(){
		return dragons.get(0).getStatus();
	}
	
	/**
	 * Gets the maze with all the pieces in their respective place.
	 *
	 * @return the game board
	 */
	public char[][] getMaze(){
		char[][] board = new char[maze.length][];
		
		//Clone maze
		for(int i = 0; i < maze.length; i++)
			board[i] = (char[])maze[i].clone();

		board[sword.getPosY()][sword.getPosX()] = sword.getSymbol();

		for(int i = 0; i < dragons.size(); i++){
			board[dragons.get(i).getPosY()][dragons.get(i).getPosX()] = dragons.get(i).getSymbol();
			
			if(dragons.get(i).getPosX() == sword.getPosX() & dragons.get(i).getPosY() == sword.getPosY())
				board[sword.getPosY()][sword.getPosX()] = 'F';
		}

		board[exit.getPosY()][exit.getPosX()] = exit.getSymbol();
		board[hero.getPosY()][hero.getPosX()] = hero.getSymbol();
		
		return board;
	}
}
