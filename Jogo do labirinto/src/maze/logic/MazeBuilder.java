package maze.logic;
import java.util.Random;
import java.util.Stack;


/**
 * MazeBuilder Class
 *
 */

public class MazeBuilder implements IMazeBuilder{
	private char[][] randmaze;
	private int[][] cells;
	private int height;
	private int length;
	private int guideX;
	private int guideY;
	private Stack<Integer> path= new Stack<Integer>();

	public MazeBuilder(){}

	/**
	 * @param size
	 * @param numDragons number of dragons expected in this maze
	 * @return random maze
	 * @throws IllegalArgumentException
	 */
	public char[][] buildMaze(int size, int numDragons) throws IllegalArgumentException{
		if(size%2 == 0)
			throw new IllegalArgumentException();

		height=size;
		length=size;
		randmaze = new char[height][length];
		int visitedCellsDimension=(height-1)/2;
		cells= new int[visitedCellsDimension][visitedCellsDimension];

		for(int i=0;i<height;i++){
			for(int j=0;j<length;j++){
				if(i<visitedCellsDimension && j<visitedCellsDimension)
					cells[i][j]=0;
				if(i==0 || j==0 || i==height-1 || j==length-1 || i%2==0 || j%2==0)
					randmaze[i][j]='X';
				else
					randmaze[i][j]=' ';
			}
		}

		Paths();
		PlacePieces(numDragons);

		return randmaze;
	}

	/* (non-Javadoc)
	 * @see maze.logic.IMazeBuilder#buildMaze(int)
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException{
		return buildMaze(size,1);
	}

	/**
	 * @param x 
	 * @param y
	 * @return d new direction possible to destroy a wall and build path in the maze
	 */
	public int direction(int x, int y){
		Random rand = new Random();
		int d=rand.nextInt(4), xcell=(x-1)/2, ycell=(y-1)/2;


		for(int i=0; i<4;i++){
			switch(d){
			case 0:
				if(xcell-1>=0 && xcell-1<length/2 && cells[xcell-1][ycell]==0)
					return d;
				else
					break;
			case 1:
				if(xcell+1>=0 && xcell+1<length/2 && cells[xcell+1][ycell]==0)
					return d;
				else
					break;
			case 2:
				if(ycell-1>=0 && ycell-1<height/2 && cells[xcell][ycell-1]==0)
					return d;
				else
					break;
			case 3:
				if(ycell+1>=0 && ycell+1<height/2 && cells[xcell][ycell+1]==0)
					return d;
				else
					break;
			}
			d=(d+1)%4;
		}

		return -1;
	}

	/**
	 * Saves the visited position (in case we need to undo the path) 
	 * in path in the maze and removes a wall int the position given
	 * 
	 * @param x horizontal position of the wall
	 * @param y vertical position of the wall
	 */
	public void change(int x, int y){
		randmaze[x][y]=' ';
		cells[(guideX-1)/2][(guideY-1)/2]=1;
		path.push(guideY);
		path.push(guideX);
	}

	/**
	 * Chooses places randomly and places the pieces in the maze
	 * 
	 * @param numDragons number of dragons expected in the maze
	 */
	public void PlacePieces(int numDragons){
		Random rand = new Random();
		int x, y;
		int heroX, heroY;

		//Place Hero
		do{
			heroX = rand.nextInt(length);
			heroY = rand.nextInt(height);
		}while(randmaze[heroX][heroY] != ' ');
		randmaze[heroX][heroY] = 'H';

		//Place Dragons
		for(int i = 0; i < numDragons; i++){
			do{
				x = rand.nextInt(length);
				y = rand.nextInt(height);
			}while(randmaze[x][y] != ' ' || (x >= heroX-1 && x <= heroX+1) || (y >= heroY-1 && y <= heroY+1));
			randmaze[x][y] = 'D';
		}

		//Place Sword
		do{
			x = rand.nextInt(length);
			y = rand.nextInt(height);
		}while(randmaze[x][y] != ' ');
		randmaze[x][y] = 'E';
	}

	/**
	 * After placing an exit, builds the possible paths in the maze randomly
	 */
	public void Paths(){

		Random rand = new Random();
		int x, y;

		switch(rand.nextInt(4)){
		case 0:
			x=1;
			y=2*rand.nextInt((height-2)/2)+1;
			randmaze[0][y]='S';		
			break;
		case 1:
			x=2*rand.nextInt((length-2)/2)+1;
			y=1;
			randmaze[x][0]='S';
			break;			
		case 2:
			x=length-2;
			y=2*rand.nextInt((height-2)/2)+1;
			randmaze[length-1][y]='S';
			break;
		default:
			x = 2* rand.nextInt((length-2)/2)+1;
			y=height-2;
			randmaze[x][height-1]='S';
			break;
		}

		cells[(x-1)/2][(y-1)/2]=1;

		path.push(y);
		path.push(x);

		guideX = x;
		guideY = y;


		do{
			int dir = direction(guideX, guideY);

			if(dir!=-1){

				switch(dir){
				case 0:
					x=guideX-1;
					guideX -= 2;
					change(x, y);
					break;
				case 1:
					x=guideX+1;
					guideX += 2;
					change(x, y);
					break;
				case 2:
					y=guideY-1;
					guideY -= 2;
					change(x, y);
					break;		
				case 3:
					y=guideY+1;
					guideY += 2;
					change(x, y);
					break;
				}
			}
			else{
				guideX = path.pop();
				guideY = path.pop();
			}

			x=guideX;
			y=guideY;
		}while(!path.isEmpty());		
	}
}



