package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private BufferedImage exit;
	private BufferedImage sleep;
	private char[][] maze = null;
	private int width=40, height=40;
	
	private Window win;

	/**
	 * Create the panel.
	 */
	public MazePanel(Window win) {
		this.win = win;
		try {
			hero =  ImageIO.read(new File("hero.png"));
			dragon = ImageIO.read(new File("dragon.png"));
			sword = ImageIO.read(new File("sword.jpg"));
			brick = ImageIO.read(new File("brick.png"));
			armed = ImageIO.read(new File("armed.png"));
			exit = ImageIO.read(new File("closed_door.png"));
			sleep = ImageIO.read(new File("sleep.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					win.update('o');
					break;

				case KeyEvent.VK_RIGHT: 
					win.update('e');
					break;

				case KeyEvent.VK_UP: 
					win.update('n');
					break;

				case KeyEvent.VK_DOWN: 
					win.update('s');
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
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
					case 'F':
						g.drawImage(dragon, x, y, x + width - 1, y + height - 1, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
						break;
					case 'E':
						g.drawImage(sword, x, y, x + width - 1, y + height - 1, 0, 0, sword.getWidth(), sword.getHeight(), null);
						break;
					case 'S':
						g.drawImage(exit, x, y, x + width - 1, y + height - 1, 0, 0, exit.getWidth(), exit.getHeight(), null);
						break;
					case 'd':
						g.drawImage(sleep, x, y, x + width - 1, y + height - 1, 0, 0, sleep.getWidth(), sleep.getHeight(), null);
						break;
					}

				}
			this.requestFocus();
		}

	}

}
