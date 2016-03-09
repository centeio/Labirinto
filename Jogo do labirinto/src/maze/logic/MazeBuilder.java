package maze.logic;
import java.util.Random;
import java.util.Stack;


public class MazeBuilder implements IMazeBuilder{
	public char[][] randmaze;
	private int[][] cells;
	public int height;
	public int length;
	private int guideX;
	private int guideY;
	public Stack<Integer> path= new Stack<Integer>();

	public char[][] buildMaze(int size){
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
		for(char[] line: randmaze){
			for(char pos: line)
				System.out.print(pos+" ");
			System.out.println();
		}
		System.out.println("------------\n");
		
		Paths();
		
		return randmaze;
		
	}

	public MazeBuilder(){
	}

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

	public void change(int x, int y){
		randmaze[x][y]=' ';
		cells[(guideX-1)/2][(guideY-1)/2]=1;
		path.push(guideX);
		path.push(guideY);
	}


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
				for(char[] line: randmaze){
					for(char pos: line)
						System.out.print(pos);
					System.out.println();
				}
				System.out.println();
				
				for(int[] line: cells){
					for(int pos: line)
						System.out.print(pos);
					System.out.println();
					System.out.println();
				}
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


		for(char[] line: randmaze){
			for(char pos: line)
				System.out.print(pos);
			System.out.println();
		}
		
		for(int[] line: cells){
			for(int pos: line)
				System.out.print(pos);
			System.out.println();
		}
		
	}
}



