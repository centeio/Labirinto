package maze.logic;

/**
 * The Class Sword.
 */
public class Sword extends Piece{
	
	/**
	 * Instantiates a new sword in position (x,y).
	 *
	 * @param x position in horizontal axis
	 * @param y position in vertical axis
	 */
	public Sword(int x, int y){
		super(x,y,'E');
	}
	
	/**
	 * Make sword disappear.
	 */
	public void disappear(){
		symbol = ' ';
	}
}
