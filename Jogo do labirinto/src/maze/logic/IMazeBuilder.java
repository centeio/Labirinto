package maze.logic;

/**
 * IMazeBuilder is an interface for classes that build mazes
 *
 */

public interface IMazeBuilder {
	/**
	 * Builds maze
	 *
	 *@param size height and width of the expected maze
	 *@return new random maze
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}