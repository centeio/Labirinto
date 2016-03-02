package maze.logic;

public class Sword extends Piece{
	public Sword(int x, int y){
		super(x,y,'E');
	}
	
	public Sword(){
		super(1,8,'E');
	}
	
	public void disappear(){
		symbol = ' ';
	}
}
