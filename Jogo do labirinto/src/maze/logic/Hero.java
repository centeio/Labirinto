package maze.logic;

/**
 * The Class Hero.
 */
public class Hero extends Piece{
	
	/**
	 * Instantiates a new hero in position (x,y).
	 *
	 * @param x position in horizontal axis
	 * @param y position in vertical axis
	 */
	public Hero(int x, int y){
		super(x,y,'H');
	}
	
	/**
	 * Move hero in direction specified in dir.
	 *
	 * @param dir the direction hero plans to take north, south, east or west
	 */
	public void move(char dir){
		switch(dir){
		case 'N':
		case 'n':
			posY--;
			break;
		case 'S':
		case 's':
			posY++;
			break;
		case 'E':
		case 'e':
			posX++;
			break;
		case 'O':
		case 'o':
			posX--;				
		}
	}
	
	/**
	 * Moves hero in opposite direction relative to the one specified in dir.
	 *
	 * @param dir the direction to undo move north, south, east, west
	 */
	public void undoMove(char dir){
		switch(dir){
		case 'S':
		case 's':
			posY--;
			break;
		case 'N':
		case 'n':
			posY++;
			break;
		case 'O':
		case 'o':
			posX++;
			break;
		case 'E':
		case 'e':
			posX--;				
		}
	}
	
	/**
	 * Arms hero with a sword.
	 */
	public void arm(){
		symbol = 'A';
	}
}
