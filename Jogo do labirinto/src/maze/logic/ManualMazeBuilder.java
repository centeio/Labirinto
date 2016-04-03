/**
 * 
 */
package maze.logic;

/**
 * @author Carolina
 *
 */
public class ManualMazeBuilder {
	public char[][] walls;

	public ManualMazeBuilder() {
	}

	public char[][] buildWalls(int size) throws IllegalArgumentException{
		if(size%2 == 0)
			throw new IllegalArgumentException();
		for(int i=0; i<size;i++){
			for(int j=0;j<size;j++){
				if(i==0 || j==0 || i==size-1 || j==size-1)
					walls[i][j]='X';
			}
		}
		return walls;

	}
}
