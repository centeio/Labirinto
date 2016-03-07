package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

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
		assertEquals(dragonInitialPosition, maze.getDragonPosition());

		for (int i = 0; i < 10 && dragonInitialPosition != maze.getDragonPosition(); i++) {
			maze.update('o');
		}

		assertEquals(false , dragonInitialPosition == maze.getDragonPosition());
	}

}
