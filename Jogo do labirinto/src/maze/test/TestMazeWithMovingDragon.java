package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Dragon;
import maze.logic.Maze;
import maze.logic.MazeStatus;

public class TestMazeWithMovingDragon {
	private static final int MOVE = 2;
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	@Test
	public void testDragonMoves() {
		Maze maze = new Maze(m1,MOVE);
		Point dragonInitialPosition = new Point(3,3);
		boolean dragonPositionChanged = false;
		assertEquals(dragonInitialPosition, maze.getDragonPosition());

		for (int i = 0; i < 50; i++) {
			dragonInitialPosition = maze.getDragonPosition();
			maze.update('e');

			assertEquals('D',maze.getDragonSymbol());
			assertEquals(Dragon.State.AWAKE, maze.getDragonStatus());
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

}
