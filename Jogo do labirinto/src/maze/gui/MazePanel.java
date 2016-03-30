package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazePanel extends JPanel {
	private BufferedImage hero;
	private BufferedImage dragon;
	private BufferedImage sword;
	private BufferedImage brick;
	private BufferedImage armed;
	private char[][] maze = null;
	private int width=40, height=40;

	/**
	 * Create the panel.
	 */
	public MazePanel() {
		try {
			hero =  ImageIO.read(new File("hero.png"));
			dragon = ImageIO.read(new File("dragon.png"));
			sword = ImageIO.read(new File("sword.jpg"));
			brick = ImageIO.read(new File("brick.png"));
			armed = ImageIO.read(new File("armed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMaze(char[][] maze){
		this.maze = maze;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(maze != null){
			for (int i = 0; i < maze.length; i++)
				for (int j = 0; j < maze[i].length; j++) {
					char pos = maze[i][j];
					int x = j*width;
					int y = i*height;

					switch(pos){
					case 'X':
						g.drawImage(brick, x, y, x + width - 1, y + height - 1, 0, 0, brick.getWidth(), brick.getHeight(), null);
						break;
					case 'H':
						g.drawImage(hero, x, y, x + width - 1, y + height - 1, 0, 0, hero.getWidth(), hero.getHeight(), null);
						break;
					case 'A':
						g.drawImage(armed, x, y, x + width - 1, y + height - 1, 0, 0, armed.getWidth(), armed.getHeight(), null);
						break;
					case 'D':
						g.drawImage(dragon, x, y, x + width - 1, y + height - 1, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
						break;
					case 'E':
						g.drawImage(sword, x, y, x + width - 1, y + height - 1, 0, 0, sword.getWidth(), sword.getHeight(), null);
						break;
					}

				}
		}

	}

}
