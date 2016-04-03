package maze.gui;

import maze.logic.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JScrollBar;


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

		this.win=win;
		this.border=border;
		this.corner=corner;

		if(border)
			setValue(1);

		this.addActionListener(this);

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
				if(value==2){
					value=5;
					initvalue=1;}
				else{
					value=1;
					initvalue=5;}

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
				if(value==5)
					value=0;
				switch(value){
				case 0:
					setIcon(null);
					win.setValue(initvalue,win.getValues()[initvalue]+1);
					win.setValue(0,win.getValues()[0]-1);

					break;
				case 1:

					if(win.getValues()[1]!=0){
						win.setValue(initvalue,win.getValues()[initvalue]+1);
						win.setValue(1,win.getValues()[1]-1);

						setIcon(brick);
					}
					else{
						value=(value+1)%6;
						redo=true;}
					break;
				case 2:
					System.out.println(win.getValues()[2]);
					if(win.getValues()[2]!=0){
						win.setValue(initvalue,win.getValues()[initvalue]+1);
						win.setValue(2,win.getValues()[2]-1);
						setIcon(hero);}
					else{
						value=(value+1)%6;
						redo=true;}
					break;
				case 3:
					System.out.println(win.getValues()[3]);
					if(win.getValues()[3]!=0){
						win.setValue(initvalue,win.getValues()[initvalue]+1);
						win.setValue(3,win.getValues()[3]-1);
						setIcon(sword);}
					else{
						value=(value+1)%6;
						redo=true;}
					break;
				case 4:
					System.out.println(win.getValues()[4]);
					if(win.getValues()[4]!=0){
						win.setValue(initvalue,win.getValues()[initvalue]+1);
						win.setValue(4,win.getValues()[4]-1);
						setIcon(dragon);}
					else{
						value=(value+1)%6;
						redo=true;}
					break;
				}
			}while(redo);

		}


	}




}
