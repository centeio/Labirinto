package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.*;

public class TestMazeWithStaticDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				    {'X', ' ', ' ', 'H', 'S'},
				    {'X', ' ', 'X', ' ', 'X'},
				    {'X', 'E', ' ', 'D', 'X'},
				    {'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		assertEquals(new Point(2,1), maze.getHeroPosition());
	}
	@Test
	public void testMoveHeroToWallCell() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('n');
		assertEquals(new Point(3,1), maze.getHeroPosition());
	}
/*	@Test
	public void testHeroDies() {
		Maze maze = new Maze(m1);
		assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
		maze.moveHeroDown();
		assertEquals(MazeStatus.HeroDied, maze.getStatus());
	}*/
}


