package maze.gui;

import maze.logic.*;
import java.lang.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class OneWindowGame implements KeyListener{

	private JFrame frame;
	private JTextField numberOfDimensions;
	private JTextField numberOfDragons;
	
	final JTextArea mazeView = new JTextArea();
	
	private Game game;
	private MazeBuilder mb;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneWindowGame window = new OneWindowGame();
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
	public OneWindowGame() {
		
		initialize();	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		numberOfDimensions = new JTextField();
		numberOfDimensions.setToolTipText("");
		numberOfDimensions.setText("11");
		numberOfDimensions.setBounds(205, 29, 30, 26);
		frame.getContentPane().add(numberOfDimensions);
		numberOfDimensions.setColumns(10);
		
		JLabel lblMazeDimension = new JLabel("Maze Dimension");
		lblMazeDimension.setBounds(42, 32, 126, 20);
		frame.getContentPane().add(lblMazeDimension);
		
		numberOfDragons = new JTextField();
		numberOfDragons.setText("1");
		numberOfDragons.setColumns(10);
		numberOfDragons.setBounds(205, 65, 30, 26);
		frame.getContentPane().add(numberOfDragons);
		
		final JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setBounds(42, 68, 148, 20);
		frame.getContentPane().add(lblNumberOfDragons);
		
		String[] dragonTypes = {"Still", "Random", "Sleepy"};
		JComboBox dragonType = new JComboBox(dragonTypes);
		dragonType.setBounds(205, 113, 78, 26);
		
		frame.getContentPane().add(dragonType);
		
		JLabel lblDragonType = new JLabel("Dragon type");
		lblDragonType.setBounds(42, 116, 108, 20);
		frame.getContentPane().add(lblDragonType);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(72, 201, 115, 29);
		frame.getContentPane().add(btnExit);
		
		//New font
		Font font = new Font ("Consolas", Font.BOLD, 18);
		
		
		mazeView.setEnabled(false);
		mazeView.setEditable(false);
		mazeView.setBounds(42, 286, 424, 334);
		mazeView.setFont(font);
		frame.getContentPane().add(mazeView);
		
		final JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updatePositions('w');
				frame.repaint();
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(627, 316, 115, 29);
		frame.getContentPane().add(btnUp);
		
		final JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updatePositions('s');
			}
		});
		btnDown.setEnabled(false);
		btnDown.setBounds(627, 490, 115, 29);
		frame.getContentPane().add(btnDown);
		
		final JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updatePositions('a');
				
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(491, 413, 115, 29);
		frame.getContentPane().add(btnLeft);
		
		final JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updatePositions('d');
				mazeView.setText(game.mazeToString());
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(764, 413, 115, 29);
		frame.getContentPane().add(btnRight);
		
		JButton btnGenerateMaze = new JButton("Generate Maze");
		btnGenerateMaze.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//Enable buttons
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				
				mb = new MazeBuilder(Integer.parseInt(numberOfDragons.getText()));
				
				game = new Game(mb.buildMaze(Integer.parseInt(numberOfDimensions.getText())));
				
				//Print the maze
				mazeView.setText(game.mazeToString());
			}
		});
		btnGenerateMaze.setBounds(237, 201, 157, 29);
		frame.getContentPane().add(btnGenerateMaze);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{	
			game.updatePositions('d');
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			game.updatePositions('w');
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			game.updatePositions('a');
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			game.updatePositions('s');
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
