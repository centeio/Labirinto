package maze.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;



public class MazeButton extends JButton implements ActionListener{
	private ImageIcon brick, hero, sword, dragon, exit;
	private int value = 0;
	private BuilderWindow win;
	private boolean border,corner;

	public MazeButton(int size, boolean border, boolean corner, BuilderWindow win){

		hero = new ImageIcon("hero.png");
		dragon = new ImageIcon("dragon.png");
		sword = new ImageIcon("sword.jpg");
		brick = new ImageIcon("brick.png");
		exit = new ImageIcon("closed_door.png");
		resizeIcons(40,40);

		this.win=win;
		this.border=border;
		this.corner=corner;

		if(border)
			setValue(1);

		this.addActionListener(this);

	}
	
	void resizeIcons(int height, int width){
		Image img = hero.getImage();
        Image newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        hero = new ImageIcon(newimg);
        
        img = dragon.getImage();
        newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        dragon = new ImageIcon(newimg);
        
        img = sword.getImage();
        newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        sword = new ImageIcon(newimg);
        
        img = brick.getImage();
        newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        brick = new ImageIcon(newimg);
        
        img = exit.getImage();
        newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        exit = new ImageIcon(newimg);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		switch(value){
		case 0:
			setIcon(null);
			break;
		case 1:
			setIcon(brick);
			break;
		case 2:
			setIcon(hero);
			break;
		case 3:
			setIcon(sword);
			break;
		case 4:
			setIcon(dragon);
			break;
		}
	}

	public void actionPerformed(ActionEvent e){
		int initvalue=value;
		value=(value+1)%6;
		boolean redo;

		if(border){
			if(!corner){
				if(value==2)
					value=5;
				
				else
					value=1;
				
				if(value==1){
					if(initvalue==5){
						setIcon(brick);
						win.setValue(5,win.getValues()[5]+1);
					}
				}
				else{
					if(win.getValues()[5]>0){
						win.setValue(5,win.getValues()[5]-1);
						setIcon(exit);
					}
					else
						value=1;
				}
			}
		}
		else{

			do{
				redo=false;
				
				//If value is door change to nothing
				if(value==5)
					value=0;

				//Change button state
				if(win.getValues()[value] > 0){
					win.setValue(initvalue,win.getValues()[initvalue]+1);
					win.setValue(value,win.getValues()[value]-1);

					setValue(value);
				}
				else{
					value=(value+1)%6;
					redo=true;}

			}while(redo);

		}
	}
}
