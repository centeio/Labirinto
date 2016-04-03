package maze.logic;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dragon.
 */
public class Dragon extends Piece{
	
	/** The Constant UP. */
	private static final int UP = 0;
	
	/** The Constant DOWN. */
	private static final int DOWN = 1;
	
	/** The Constant LEFT. */
	private static final int LEFT = 2;
	
	/** The Constant RIGHT. */
	private static final int RIGHT = 3;
	
	/** The Constant STAY. */
	private static final int STAY = 4;
	
	/**
	 * The Enum State representing dragon's state.
	 */
	public enum State{
		
		/** Dragon is awake and able to kill */ AWAKE, 
		/** Dragon is asleep and temporarily unable of murder */ ASLEEP, 
		/** Dragon is dead */ DEAD
	}
	
	/** Dragon's state. */
	private State state;
	
	/**
	 * Instantiates a new awake dragon in position (x,y).
	 *
	 * @param x position in horizontal axis
	 * @param y position in vertical axis
	 */
	public Dragon(int x, int  y){
		super(x,y,'D');
		state = State.AWAKE;
	}

	/**
	 * Kills dragon
	 */
	public void kill(){
		state = State.DEAD;
		symbol = ' ';
	}

	/**
	 * Wakes dragon up
	 */
	public void wakeUp(){
		state = State.AWAKE;
		symbol = 'D';
	}
	
	/**
	 * Makes dragon fall asleep.
	 */
	public void fallAsleep(){
		state = State.ASLEEP;
		symbol = 'd';
	}
	
	/**
	 * Updates dragon moving and or making it fall asleep/wake up randomly if necessary.
	 *
	 * @param mode the game's mode
	 * @return an int representing the direction taken by the dragon STAY, UP, DOWN, RIGHT or LEFT
	 */
	public int update(Mode mode){
		Random r = new Random();
		boolean change = r.nextBoolean();

		if(state != State.DEAD && mode != Mode.STILL){
			if(change && mode == Mode.SLEEP){
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
	
	/**
	 * Moves dragon randomly.
	 *
	 * @return an int representing the direction taken by the dragon STAY, UP, DOWN, RIGHT or LEFT
	 */
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

	/**
	 * Undoes move.
	 *
	 * @param dir the direction previously taken by the dragon
	 */
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
	
	/**
	 * Gets the dragon's status.
	 *
	 * @return dragon's status
	 */
	public State getStatus(){
		return state;
	}
}
