package maze.gui;

import maze.logic.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JScrollBar;

public class BuilderWindow extends JFrame {
	private MazeButton[][] positions;
	private int values[];
	private JPanel panel;
	private JPanel options;
	private boolean saved;
	private Window mother;

	public BuilderWindow(int size, Window win) throws IOException{
		this.mother=win;
		win.getFrame().setEnabled(false);

		setBounds(100, 100, size*42, size*42+70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		saved=false;
		positions = new MazeButton[size][size];
		panel= new JPanel();
		panel.setBounds(0, 0, size*40, size*40);

		panel.setLayout(new GridLayout(size,size));

		options= new JPanel();
		options.setBounds(0, size*40, size*40, size*40);

		options.setLayout(new GridLayout(size,size));

		values=new int[6];
		values[0]=size*size-4*size-4;
		values[1]=2*((size-2)/2)*((size-2)/2);
		values[2]=1;
		values[3]=1;
		values[4]=(size-2)*(size-2)-values[1]-values[2]-values[3]-4;
		values[5]=1;

		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(i==0||j==0 || i==size-1 || j==size-1){
					if((i==0&&j==size-1)||(j==0&&i==size-1)||(i==0&&j==0)||(j==size-1&&i==size-1))
						positions[i][j]=new MazeButton(size,true,true,this);
					else
						positions[i][j]=new MazeButton(size,true,false,this);
				}
				else
					positions[i][j]=new MazeButton(size,false,false,this);
				panel.add(positions[i][j]);

			}
		}
		add(panel);
		add(options);
		setVisible(true);
		setEnabled(true);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				win.getFrame().setEnabled(true);
				if(win.getMode().equals("Est�ticos"))
					win.setMaze(new Maze(getEquiMaze(), 1));
				else if ( win.getMode().equals("Dinamicos"))
					win.setMaze(new Maze(getEquiMaze(), 2));
				else
					win.setMaze(new Maze(getEquiMaze(), 3));
				saved=true;
				win.getFrame().setEnabled(true);
				win.getFrame().requestFocus();
				win.update('p');
				
			}
		});
		btnGuardar.setBounds(size*42-50, size*4-200, 50, 200);
		options.add(btnGuardar);

		requestFocus(); 

	}

	public char[][] getEquiMaze(){
		int size=positions.length;
		char[][] maze= new char[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				switch(positions[i][j].getValue()){
				case 0:
					maze[i][j]=' ';
					break;
				case 1:
					maze[i][j]='X';
					break;
				case 2:
					maze[i][j]='H';
					break;
				case 3:
					maze[i][j]='E';
					break;
				case 4:
					maze[i][j]='D';
					break;
				case 5:
					maze[i][j]='S';
					break;
				}
				System.out.print(maze[i][j]);
			}
			System.out.println("");
		}


		return maze;
	}

	public int[] getValues(){
		return values;
	}

	public void setValue(int pos, int value){
		if(pos>=0 && pos<6)
			values[pos]=value;
	}

	public boolean getSaved(){
		return saved;
	}

}