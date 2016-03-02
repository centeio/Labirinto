package maze.logic;

import java.awt.Point;

public class Maze {
	private static final int STAY = 4;
	private Mode mode;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private Exit exit;
	private char maze[][] = {{'X','X','X','X','X','X','X','X','X','X'},
							  {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
							  {'X',' ','X','X',' ','X',' ','X',' ','X'},
							  {'X',' ','X','X',' ','X',' ','X',' ','X'},
							  {'X',' ','X','X',' ','X',' ','X',' ','X'},
							  {'X',' ',' ',' ',' ',' ',' ','X',' ',' '},
							  {'X',' ','X','X',' ','X',' ','X',' ','X'},
							  {'X',' ','X','X',' ','X',' ','X',' ','X'},
							  {'X',' ','X','X',' ',' ',' ',' ',' ','X'},
	  						  {'X','X','X','X','X','X','X','X','X','X'}};
	
	public Maze(char[][] maze){
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
					dragon = new Dragon(j,i);
					maze[i][j] = ' ';
					break;
				case 'S':
					exit = new Exit(j,i);
					maze[i][j] = ' ';
					break;					
				}
		
		mode = Mode.STILL;
	}
	public Maze(int mode){
		hero = new Hero();
		dragon = new Dragon();
		sword = new Sword();
		exit = new Exit();
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
		hero.move(dir);
		int dragonDir = STAY;
		
		dragonDir = dragon.update(mode);
		
		//If wall undo move
		if(maze[dragon.getPosY()][dragon.getPosX()] == 'X')
			dragon.undoMove(dragonDir);
		
		if(maze[hero.getPosY()][hero.getPosX()] == 'X')
			hero.undoMove(dir);
		
		//Hero got sword
		if(hero.getPosY() == sword.getPosY() && hero.getPosX() == sword.getPosX()){
			hero.arm();
			sword.disappear();
		}
		
		//Hero died
		if(hero.getSymbol() == 'H' && dragon.getSymbol() == 'D'
								&&  hero.getPosY() >= dragon.getPosY() - 1 
								&&  hero.getPosY() <= dragon.getPosY() + 1 
								&& hero.getPosX() >= dragon.getPosX()- 1
								&& hero.getPosX() <= dragon.getPosX()+ 1)
			return 2;
		
		//Hero killed dragon
		if(hero.getSymbol() == 'A' &&  hero.getPosY() == dragon.getPosY() && hero.getPosX() == dragon.getPosX())
			dragon.kill();
		
		//Hero kills dragon and leaves
		if(dragon.getSymbol() == ' ' && hero.getPosY() == exit.getPosY() && hero.getPosX() == exit.getPosX())
			return 1;
		
		return 0;
	}
	public String toString(){
		char[][] board = new char[maze.length][];
		String str = new String();
		
		//Clone maze
		for(int i = 0; i < maze.length; i++)
			board[i] = (char[])maze[i].clone();
		
		board[dragon.getPosY()][dragon.getPosX()] = dragon.getSymbol();
		if(dragon.getPosX() == sword.getPosX() & dragon.getPosY() == sword.getPosY())
			board[sword.getPosY()][sword.getPosX()] = 'F';
		else
			board[sword.getPosY()][sword.getPosX()] = sword.getSymbol();
		board[hero.getPosY()][hero.getPosX()] = hero.getSymbol();
		board[exit.getPosY()][exit.getPosX()] = exit.getSymbol();
		
		
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
}
