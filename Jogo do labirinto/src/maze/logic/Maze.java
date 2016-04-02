package maze.logic;

import java.awt.Point;
import java.util.Vector;

public class Maze {
	private static final int STAY = 4;
	private Mode mode;
	private MazeStatus status;
	private Hero hero;
	private Vector<Dragon> dragons = new Vector<Dragon>();;
	private Sword sword;
	private Exit exit;
	private char maze[][];

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

			//If wall undo move
			if(dragons.get(i).getPosY() < 0 || dragons.get(i).getPosX() < 0 || dragons.get(i).getPosY() >= maze.length|| dragons.get(i).getPosX() >= maze.length || maze[dragons.get(i).getPosY()][dragons.get(i).getPosX()] == 'X')
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
				dragons.get(i).kill();
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

	public Point getHeroPosition(){
		return hero.getPosition();
	}

	public Point getDragonPosition(){
		return dragons.get(0).getPosition();
	}

	public char getHeroSymbol(){
		return hero.getSymbol();
	}

	public char getDragonSymbol(){
		Dragon dragon = dragons.get(0);
		return dragon.getSymbol();
	}

	public char getSwordSymbol(){
		return sword.getSymbol();
	}

	public MazeStatus getStatus(){
		return status;
	}

	public Dragon.State getDragonStatus(){
		return dragons.get(0).getStatus();
	}
	
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
