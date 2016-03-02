package maze.logic;

public class Hero extends Piece{
	public Hero(int x, int y){
		super(x,y,'H');
	}
	public Hero(){
		super(1,1,'H');
	}
	
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
	
	public void arm(){
		symbol = 'A';
	}
}
