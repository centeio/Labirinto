package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import maze.logic.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField DimensionField;
	private JTextField DragonsField;
	private JTextField txtStatus;
	private Maze maze;
	private JButton btnNorte;
	private JButton btnOeste;
	private	JButton btnEste;
	private JButton btnSul;
	JTextArea mazeArea;

	private void updateTxtStatus(int status){
		
		mazeArea.setText(maze.toString());
		if(status != 0){
			if(status == 1)
				txtStatus.setText("Parabens!!!!");
			if(status == 2)
				txtStatus.setText("Game Over...");
			
			btnNorte.setEnabled(false);
			btnOeste.setEnabled(false);
			btnEste.setEnabled(false);	
			btnSul.setEnabled(false);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}
	
	public void testArg(int mazeSize){
		if (mazeSize%2!=1)
			throw new IllegalArgumentException();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNorte = new JButton("Norte");
		btnNorte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTxtStatus( maze.update('n'));	
			}
		});
		btnNorte.setEnabled(false);
		btnNorte.setBounds(396, 130, 89, 23);
		frame.getContentPane().add(btnNorte);

		btnOeste = new JButton("Oeste");
		btnOeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTxtStatus( maze.update('o'));
			}
		});
		btnOeste.setEnabled(false);
		btnOeste.setBounds(338, 164, 89, 23);
		frame.getContentPane().add(btnOeste);

		btnEste = new JButton("Este");
		btnEste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTxtStatus( maze.update('e'));
			}
		});
		btnEste.setEnabled(false);
		btnEste.setBounds(440, 164, 89, 23);
		frame.getContentPane().add(btnEste);

		btnSul = new JButton("Sul");
		btnSul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTxtStatus( maze.update('s'));
			}
		});
		btnSul.setEnabled(false);
		btnSul.setBounds(396, 198, 89, 23);
		frame.getContentPane().add(btnSul);

		mazeArea = new JTextArea();
		mazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeArea.setEditable(false);
		mazeArea.setBounds(10, 92, 312, 186);
		frame.getContentPane().add(mazeArea);

		JLabel lblDimension = new JLabel("Dimens\u00E3o do labirinto");
		lblDimension.setBounds(10, 11, 142, 14);
		frame.getContentPane().add(lblDimension);

		DimensionField = new JTextField();
		DimensionField.setBounds(162, 8, 86, 20);
		frame.getContentPane().add(DimensionField);
		DimensionField.setColumns(10);

		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de drag\u00F5es");
		lblNmeroDeDrages.setBounds(10, 36, 142, 14);
		frame.getContentPane().add(lblNmeroDeDrages);

		DragonsField = new JTextField();
		DragonsField.setBounds(162, 33, 86, 20);
		frame.getContentPane().add(DragonsField);
		DragonsField.setColumns(10);

		JLabel lblTipoDeDrages = new JLabel("Tipo de drag\u00F5es");
		lblTipoDeDrages.setBounds(10, 61, 142, 14);
		frame.getContentPane().add(lblTipoDeDrages);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(162, 58, 150, 20);
		comboBox.addItem("Estáticos");
		comboBox.addItem("Dinamicos");
		comboBox.addItem("Adormecidos");
		frame.getContentPane().add(comboBox);

		JButton btnGerarNovoLabirinto = new JButton("Gerar novo labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mazeSize;
				int numDragons;
				MazeBuilder builder = new MazeBuilder();

				try{
					mazeSize = Integer.parseInt(DimensionField.getText());
					numDragons = Integer.parseInt(DragonsField.getText());
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frame,"Formato não válido");
					return;
				}
				
				try{
					testArg(mazeSize);
				}catch(IllegalArgumentException answer){
					JOptionPane.showMessageDialog(frame,"Inserir número ímpar");
					return;
				}


				String s = (String) comboBox.getSelectedItem();
				if(s.equals("Estáticos"))
					maze = new Maze(builder.buildMaze(mazeSize,numDragons), 1);
				else if ( s.equals("Dinamicos"))
					maze = new Maze(builder.buildMaze(mazeSize,numDragons), 2);
				else
					maze = new Maze(builder.buildMaze(mazeSize,numDragons), 3);

				btnNorte.setEnabled(true);
				btnOeste.setEnabled(true);
				btnEste.setEnabled(true);	
				btnSul.setEnabled(true);

				mazeArea.setText(maze.toString());
				txtStatus.setText("Pode jogar!!!!");
			}
		});
		btnGerarNovoLabirinto.setBounds(333, 7, 134, 34);
		frame.getContentPane().add(btnGerarNovoLabirinto);

		JButton btnTerminarPrograma = new JButton("Terminar programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(333, 51, 134, 34);
		frame.getContentPane().add(btnTerminarPrograma);

		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setText("Pode gerar novo labirinto!");
		txtStatus.setBounds(10, 289, 446, 20);
		frame.getContentPane().add(txtStatus);
		txtStatus.setColumns(10);
	}
}
