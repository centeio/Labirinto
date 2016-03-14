package maze.cli;
import java.util.Scanner;

import maze.logic.*;

public class Game {

	public static void main(String[] args) {
		int mode;
		int gameState = 0;
		int size;
		int numDragons;
		MazeBuilder builder = new MazeBuilder();
		/*char[][] board = {{'X','X','X','X','X','X','X','X','X','X'},
						  {'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
						  {'X',' ','X','X',' ','X',' ','X',' ','X'},
						  {'X','D','X','X',' ','X',' ','X',' ','X'},
						  {'X',' ','X','X',' ','X',' ','X','D','X'},
						  {'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
						  {'X',' ','X','X',' ','X',' ','X',' ','X'},
						  {'X',' ','X','X',' ','X',' ','X',' ','X'},
						  {'X','E','X','X',' ',' ',' ',' ',' ','X'},
						  {'X','X','X','X','X','X','X','X','X','X'}};*/
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Insira o tamanho do labirinto: ");
		size = s.nextInt();
		
		System.out.println("Insira o numero de dragoes: ");
		numDragons = s.nextInt();
		
		System.out.println("Por favor insira o numero do modo:"
				+"\n\t1.Sem movimento"
				+"\n\t2.Com movimento"
				+"\n\t3.Com adormecimento");
		mode = s.nextInt();
		
		Maze maze = new Maze(builder.buildMaze(size,numDragons), mode);
		
		System.out.println(maze);
		
		while(gameState == 0){
			System.out.print("Insira a direcao que o heroi deve tomar (N,S,E,O): ");
			char dir = s.next().charAt(0);
			
			System.out.print("\n");
			gameState = maze.update(dir);
			System.out.println(maze);
		}
		s.close();
		
		if(gameState == 1)
			System.out.print("Parabens!!!!");
		else
			System.out.print("Game Over...");
	}
}
