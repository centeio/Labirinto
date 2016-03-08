package maze.test;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import maze.logic.*;

public class TestMazeWithStaticDragon {
	private static final int STAY = 1;
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				    {'X', ' ', ' ', 'H', 'S'},
				    {'X', ' ', 'X', ' ', 'X'},
				    {'X', 'E', 'D', 'D', 'X'},
				    {'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1,STAY);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		assertEquals(new Point(2,1), maze.getHeroPosition());
	}

	@Test
	public void testMoveHeroToWallCell() {
		Maze maze = new Maze(m1,STAY);
		maze.update('n');
		assertEquals(new Point(3,1), maze.getHeroPosition());
	}
	
	@Test
	public void testHeroGetsSword() {
		Maze maze = new Maze(m1,STAY);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		maze.update('o');
		maze.update('s');
		maze.update('s');
		
		assertEquals(MazeStatus.HeroArmed, maze.getStatus());
		assertEquals('A', maze.getHeroSymbol());
		assertEquals(' ', maze.getSwordSymbol());
	}
	
	@Test
	public void testHeroDies() {
		Maze maze = new Maze(m1,STAY);
		assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
		assertEquals(2,maze.update('s'));
		assertEquals(MazeStatus.HeroDied, maze.getStatus());
	}
	
	@Test
	public void testHeroSlaysDragon() {
		Maze maze = new Maze(m1,STAY);
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
		Maze maze = new Maze(m1,STAY);
		assertEquals(new Point(3,1), maze.getHeroPosition());
		maze.update('o');
		maze.update('o');
		maze.update('s');
		maze.update('s');
		assertEquals(MazeStatus.HeroArmed, maze.getStatus());
		
		maze.update('e');
		maze.update('e');
		assertEquals(MazeStatus.HeroSlayed, maze.getStatus());

		maze.update('n');
		maze.update('n');
		
		assertEquals(1,maze.update('e'));
		assertEquals(MazeStatus.HeroWon, maze.getStatus());
		
		System.out.println(maze);
	}
}


