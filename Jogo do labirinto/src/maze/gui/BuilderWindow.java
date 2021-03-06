package maze.gui;

import maze.logic.*;
import maze.test.TestMazeBuilder.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import java.awt.EventQueue;

import javax.swing.JSlider;
import javax.swing.JScrollBar;

public class BuilderWindow extends JDialog {
	private MazeButton[][] positions;
	private int values[];
	private JPanel panel;
	private JPanel options;
	private int possible;
	char[][] maze;

	public BuilderWindow(){};

	public BuilderWindow(int size, Window win, char[][] maze, int possible) throws IOException{
		super(win.getFrame(),true);
		BuilderWindow frame=this;

		setBounds(100, 100, size*42, size*42+60);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		positions = new MazeButton[size][size];
		panel= new JPanel();
		panel.setBounds(0, 0, size*40, size*40);
		panel.setLayout(new GridLayout(size,size));

		this.maze=maze;
		this.possible=possible;

		options= new JPanel();
		options.setBounds(0, size*40, size*40, 60);

		options.setLayout(new GridLayout(2,1));

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
						positions[i][j]=new MazeButton(size,true,true,frame, i, j);
					else
						positions[i][j]=new MazeButton(size,true,false,frame, i, j);
					this.maze[i][j]='X';
				}
				else{
					positions[i][j]=new MazeButton(size,false,false,frame, i, j);
					this.maze[i][j]=' ';
				}
				panel.add(positions[i][j]);

			}
		}


		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!exitReachable(maze)){
					JOptionPane.showMessageDialog(frame,"Labirinto impossivel");

				}
				else if(values[1]!=0)
					JOptionPane.showMessageDialog(frame,"Falta colocar " + values[1] + " tijolos");
				else if(values[2]!=0)
					JOptionPane.showMessageDialog(frame,"Insira o heroi");
				else if(values[3]!=0)
					JOptionPane.showMessageDialog(frame,"Insira a espada");
				else if(values[4]==(size-2)*(size-2)-(2*((size-2)/2)*((size-2)/2))-6)
					JOptionPane.showMessageDialog(frame,"Insira um dragao");
				else if(values[5]!=0)
					JOptionPane.showMessageDialog(frame,"Insira a saida");
				else{
					frame.possible=1;
					dispose();
				}
			}

		});
		options.add(btnGuardar);

		add(panel);
		add(options);

		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				JOptionPane.showMessageDialog(null,"Clique nos quadrados para alterar. Deve incluir um heroi, uma espada e o max de tijolos. No final, guarde.");
			}
		});

		setVisible(true);
		setEnabled(true);

		requestFocus(); 


	}


	private void visit(char[][] m, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return ;
		if (m[i][j] == 'X' || visited[i][j])
			return ;
		visited[i][j] = true;
		visit(m, i-1, j, visited);
		visit(m, i+1, j, visited);
		visit(m, i, j-1, visited);
		visit(m, i, j+1, visited);
	}

	private Point findPos(char [][] maze, char c) {
		for (int x = 0; x < maze.length; x++)			
			for (int y = 0; y < maze.length; y++)
				if (maze[y][x] == c)
					return new Point(y, x);
		return null;		
	}

	private boolean exitReachable(char [][] maze) {
		Point p = findPos(maze, 'S');
		if(p!=null){
			boolean [][] visited = new boolean[maze.length] [maze.length];

			visit(maze, p.getY(), p.getX(), visited);

			for (int i = 0; i < maze.length; i++)
				for (int j = 0; j < maze.length; j++)
					if (maze[i][j] != 'X' && ! visited[i][j] )
						return false;

			return true; 
		}
		else 
			return false;
	}


	public int[] getValues(){
		return values;
	}

	public int getPossible(){
		return possible;
	}
	
	public void setValue(int pos, int value){
		if(pos>=0 && pos<6)
			values[pos]=value;
	}


	public char[][] getMaze() {
		return maze;
	}


	public void setMaze(char[][] maze) {
		this.maze = maze;
	}


	public MazeButton[][] getPositions() {
		return positions;
	}


	public void setPositions(MazeButton[][] positions) {
		this.positions = positions;
	}




}
