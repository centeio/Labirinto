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
		maze.update('n');
		assertEquals(new Point(3,1), maze.getHeroPosition());
	}
	
	@Test
	public void testHeroGetsSword() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		maze.update('o');
		maze.update('s');
		maze.update('s');
		
		assertEquals(MazeStatus.HeroArmed, maze.getStatus());
	}
	
	@Test
	public void testHeroDies() {
		Maze maze = new Maze(m1);
		assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
		maze.update('s');
		assertEquals(MazeStatus.HeroDied, maze.getStatus());
	}
	
	@Test
	public void testHeroSlaysDragon() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		maze.update('o');
		maze.update('s');
		maze.update('s');
		assertEquals(MazeStatus.HeroArmed, maze.getStatus());
		
		maze.update('e');
		maze.update('e');
		assertEquals(MazeStatus.HeroSlayed, maze.getStatus());
		
	}
	
	@Test
	public void testHeroWins() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		maze.update('o');
		maze.update('s');
		maze.update('s');
		assertEquals(MazeStatus.HeroArmed, maze.getStatus());
		
		maze.update('e');
		maze.update('e');
		assertEquals(MazeStatus.HeroSlayed, maze.getStatus());
		
		maze.update('e');
		maze.update('n');
		maze.update('n');
		maze.update('e');
		assertEquals(MazeStatus.HeroWon, maze.getStatus());
		
	}
	
	@Test
	public void testBuildDefaultAndPrint() {
		Maze maze = new Maze(1);

		System.out.println(maze);
	}
	
}


