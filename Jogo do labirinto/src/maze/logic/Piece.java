package maze.logic;

import java.awt.Point;

public class Piece {
	protected int posX;
	protected int posY;
	protected char symbol;
	
	public Piece(int x, int y, char symbol){
		posX = x;
		posY = y;
		this.symbol = symbol;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public char getSymbol(){
		return symbol;
	}
	
	public Point getPosition(){
		return new Point(posX,posY);
	}
}
