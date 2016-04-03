package maze.logic;

import java.awt.Point;

/**
 * The Class Piece representing a game piece (hero, dragon, sword, exit).
 */
public class Piece {
	
	/** The position in horizontal axis */
	protected int posX;
	
	/** The position in vertical axis */
	protected int posY;
	
	/** The symbolic char representing the piece */
	protected char symbol;
	
	/**
	 * Instantiates a new piece.
	 *
	 * @param x position in horizontal axis
	 * @param y position in vertical axis
	 * @param symbol the symbolic char representing the piece
	 */
	public Piece(int x, int y, char symbol){
		posX = x;
		posY = y;
		this.symbol = symbol;
	}
	
	/**
	 * Gets the position in horizontal axis
	 *
	 * @return the position in horizontal axis
	 */
	public int getPosX(){
		return posX;
	}
	
	/**
	 * Gets the position in vertical axis
	 *
	 * @return the position in vertical axis
	 */
	public int getPosY(){
		return posY;
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Point getPosition(){
		return new Point(posX,posY);
	}
}
