package maze.logic;

public class Sword extends Piece{
	public Sword(int x, int y){
		super(x,y,'E');
	}
	
	public void disappear(){
		symbol = ' ';
	}
}
