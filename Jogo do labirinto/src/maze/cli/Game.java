package maze.cli;
import java.util.Scanner;

import maze.logic.Maze;

public class Game {

	public static void main(String[] args) {
		int mode;
		int gameState = 0;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Por favor insira o numero do modo:"
				+"\n\t1.Sem movimento"
				+"\n\t2.Com movimento"
				+"\n\t3.Com adormecimento");
		mode = s.nextInt();
		
		Maze maze = new Maze(mode);
		
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
