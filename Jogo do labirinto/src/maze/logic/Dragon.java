package maze.logic;

import java.util.Random;

public class Dragon extends Piece{
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private static final int STAY = 4;
	
	public enum State{
		AWAKE, ASLEEP, DEAD
	}
	
	private State state;
	
	public Dragon(int x, int  y){
		super(x,y,'D');
		state = State.AWAKE;
	}

	public void kill(){
		state = State.DEAD;
		symbol = ' ';
	}

	public void wakeUp(){
		state = State.AWAKE;
		symbol = 'D';
	}
	public void fallAsleep(){
		state = State.ASLEEP;
		symbol = 'd';
	}
	public int update(Mode mode){
		Random r = new Random();
		boolean change = r.nextBoolean();

		if(state != State.DEAD & mode != Mode.STILL){
			if(change & mode == Mode.SLEEP){
				if(state == State.ASLEEP)
					wakeUp();
				else
					fallAsleep();
			}
			else
				if(state == State.AWAKE)
					return move();
		}

		return STAY;
	}
	public int move(){
		Random r = new Random();
		int dir = r.nextInt(4);

		switch(dir){
		case UP:
			posY--;
			break;
		case DOWN:
			posY++;
			break;
		case RIGHT:
			posX++;
			break;
		case LEFT:
			posX--;	
		}

		return dir;
	}

	public void undoMove(int dir){
		switch(dir){
		case DOWN:
			posY--;
			break;
		case UP:
			posY++;
			break;
		case LEFT:
			posX++;
			break;
		case RIGHT:
			posX--;	
		}
	}
	
	public State getStatus(){
		return state;
	}
}
