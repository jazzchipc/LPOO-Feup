package maze.gui;	

import maze.logic.*;
import maze.logic.Game.End;

import java.lang.*;
import java.util.Locale;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class OneWindowGame{

	private JFrame frame;
	private JTextField numberOfDimensions;
	private JTextField numberOfDragons;
	private JComboBox dragonType;
	private JTextArea textHints;
	
	
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
	
	private void resetGame()
	{
		game = new Game();
	}
	
	private void madeAPlay()
	{
		if (game.getEnd() == End.END_LOSS)
		{
			JOptionPane.showMessageDialog(frame,
				    "Oh no! The dragon killed you! Next time try to get the sword first.",
				    "A plain message",
				    JOptionPane.PLAIN_MESSAGE);
		}
		
		if (game.getEnd() == End.END_WIN)
		{
			JOptionPane.showMessageDialog(frame,
				    "Congratulations! You managed not to burn to death!",
				    "A plain message",
				    JOptionPane.PLAIN_MESSAGE);
		}
		
			
		if (game.getEnd() == End.END_NOT)
		{
			//Print the maze
			mazeView.setText(game.mazeToString());

			//Print hints
			textHints.setText(game.printHints());
		}
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
		
		final String[] dragonTypes = {"Still", "Random", "Sleepy"};
		dragonType = new JComboBox(dragonTypes);
		dragonType.setBounds(205, 102, 78, 26);
		
		frame.getContentPane().add(dragonType);
		
		JLabel lblDragonType = new JLabel("Dragon type");
		lblDragonType.setBounds(42, 105, 108, 20);
		frame.getContentPane().add(lblDragonType);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(344, 64, 157, 29);
		frame.getContentPane().add(btnExit);
		
		//New font
		Font font = new Font ("Consolas", Font.BOLD, 18);
		
		
		mazeView.setEnabled(false);
		mazeView.setEditable(false);
		mazeView.setBounds(42, 216, 533, 402);
		mazeView.setFont(font);
		frame.getContentPane().add(mazeView);
		
		final JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('w');
				madeAPlay();
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(627, 101, 115, 29);
		frame.getContentPane().add(btnUp);
		
		final JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('s');
				madeAPlay();
			}
		});
		btnDown.setEnabled(false);
		btnDown.setBounds(627, 181, 115, 29);
		frame.getContentPane().add(btnDown);
		
		final JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.updateGame('a');
				madeAPlay();
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(509, 141, 115, 29);
		frame.getContentPane().add(btnLeft);
		
		final JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('d');
				madeAPlay();
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(740, 141, 115, 29);
		frame.getContentPane().add(btnRight);
		
		JButton btnGenerateMaze = new JButton("Generate Maze");
		btnGenerateMaze.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//Enable buttons
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				
				int nDragons = Integer.parseInt(numberOfDragons.getText());
				int nDimensions = Integer.parseInt(numberOfDimensions.getText());
				
				if (nDragons < 1)
				{
					JOptionPane.showMessageDialog(frame,
						    "The number of dragons must be positive.");
					return;
				}
				
				if (nDragons > (nDimensions-3)*(nDimensions-3))
				{
					JOptionPane.showMessageDialog(frame,
						    "The number of dragons is too high for the number of dimensions.");
					return;
				}
				
				if (nDimensions < 5 || nDimensions%2 == 0)
				{
					JOptionPane.showMessageDialog(frame,
						    "The number of dimensions has to be odd and larger than 5.");
					return;
				}
				
				mb = new MazeBuilder(nDragons);
				
				game = new Game(mb.buildMaze(nDimensions));
				
				char dragonMode = 'i';
				
				if (dragonType.getSelectedItem() == dragonTypes[0])
					dragonMode = 'i';
				if (dragonType.getSelectedItem() == dragonTypes[1])
					dragonMode = 'r';
				if (dragonType.getSelectedItem() == dragonTypes[2])
					dragonMode = 's';
				
				game.initGame(dragonMode);
				
				//Print the maze
				mazeView.setText(game.mazeToString());
					
				//Print hints
				textHints.setText(game.printHints());
				
				
			}
		});
		btnGenerateMaze.setBounds(344, 28, 157, 29);
		frame.getContentPane().add(btnGenerateMaze);
		
		textHints = new JTextArea();
		textHints.setBounds(612, 286, 187, 35);
		frame.getContentPane().add(textHints);
	}
}
