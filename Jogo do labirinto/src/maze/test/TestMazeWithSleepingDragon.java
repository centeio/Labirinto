package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Dragon;
import maze.logic.Maze;
import maze.logic.MazeStatus;

public class TestMazeWithSleepingDragon {
	private static final int SLEEP = 3;
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testDragonMoves() {
		Maze maze = new Maze(m1,SLEEP);
		Point dragonInitialPosition = new Point(3,3);
		
		assertEquals(dragonInitialPosition, maze.getDragonPosition());

		for (int i = 0; i < 10 && dragonInitialPosition != maze.getDragonPosition(); i++) {
			maze.update('o');
		}

		assertEquals(false , dragonInitialPosition == maze.getDragonPosition());
	}
	
	@Test
	public void testDragonSleeps() {
		Maze maze = new Maze(m1,SLEEP);
		
		assertEquals(new Point(3,3), maze.getDragonPosition());
		
		int i;
		for (i = 0; i < 20 && Dragon.State.AWAKE == maze.getDragonStatus(); i++) {
			maze.update('n');
		}
		
		if(i != 20){
			assertEquals(Dragon.State.ASLEEP, maze.getDragonStatus());
			assertEquals('d', maze.getDragonSymbol());
		}
	}
}
