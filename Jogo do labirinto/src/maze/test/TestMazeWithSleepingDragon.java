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
		boolean dragonPositionChanged = false;
		assertEquals(dragonInitialPosition, maze.getDragonPosition());

		for (int i = 0; i < 50; i++) {
			dragonInitialPosition = maze.getDragonPosition();
			maze.update('e');

			assertTrue((maze.getDragonSymbol() == 'D' && Dragon.State.AWAKE == maze.getDragonStatus())
					|| (maze.getDragonSymbol() == 'd' && Dragon.State.ASLEEP == maze.getDragonStatus()));
			assertTrue((maze.getDragonPosition().getX() == dragonInitialPosition.getX() 
					&& maze.getDragonPosition().getY() >= dragonInitialPosition.getY() - 1 
					&& maze.getDragonPosition().getY() <= dragonInitialPosition.getY() + 1) ||
					(maze.getDragonPosition().getY() == dragonInitialPosition.getY() 
					&& maze.getDragonPosition().getX() >= dragonInitialPosition.getX() - 1 
					&& maze.getDragonPosition().getX() <= dragonInitialPosition.getX() + 1));

			if(!dragonPositionChanged)
				dragonPositionChanged = !(maze.getDragonPosition() == dragonInitialPosition);
		}

		assertTrue(dragonPositionChanged);
	}

	@Test
	public void testDragonSleeps() {
		Maze maze = new Maze(m1,SLEEP);
		Point dragonInitialPosition = new Point(3,3);
		boolean dragonSlept = false;

		assertEquals(dragonInitialPosition, maze.getDragonPosition());

		for (int i = 0; i < 50; i++) {
			dragonInitialPosition = maze.getDragonPosition();

			maze.update('n');

			assertTrue((maze.getDragonSymbol() == 'D' && Dragon.State.AWAKE == maze.getDragonStatus())
					|| (maze.getDragonSymbol() == 'd' && Dragon.State.ASLEEP == maze.getDragonStatus()));

			if(Dragon.State.ASLEEP == maze.getDragonStatus()){
				assertEquals(dragonInitialPosition, maze.getDragonPosition());
				dragonSlept = true;
			}

		}

		assertTrue(dragonSlept);
	}
}
