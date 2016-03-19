package maze.logic;

import java.util.Random;
import java.util.Scanner;

public class Game {
	
	// iteration 1 maze, without elements
	private char[][] symbols1 = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H','0','0','0','0','0','0','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','D','X','X','0','X','0','X','0','X'},
				{'X','0','0','0','0','0','0','X','0','S'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','E','X','X','0','0','0','0','0','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
	
	//---ATTRIBUTES
	
	private Maze maze;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	
	//---GAME STATE
	
	private boolean end;
	private boolean exit;

	//---GET ATTRIBUTES FUNCTIONS
	
	public Maze getMaze()
	{
		return this.maze;
	}
	
	public boolean getEnd()
	{
		return this.end;
	}
	
	/**
	 * Deafult constructor
	 */
	public Game()
	{
		this.maze = new Maze();
		this.hero = new Hero();
		this.dragon = new Dragon();
		this.sword = new Sword();
		
		this.end = false;
		this.exit = false;
	}
	
	//GAME METHODS
	
	public void initGame(char dragonMode)
	{
		//Initializing maze
		maze.updateMaze(symbols1);
		
		//Initial elements' position
		/*Position heroInitPos = new Position (1,1);
		Position dragonInitPos = new Position (1,4);
		Position swordInitPos = new Position (1,8);*/
		
		//Setting those positions
		hero.updatePosition(this.maze.findPos(this.hero.getLetter()));
		dragon.updatePosition(this.maze.findPos(this.dragon.getLetter()));
		sword.updatePosition(this.maze.findPos(this.sword.getLetter()));
		
		//Updating dragon's mode
		dragon.setMode(dragonMode);
	}
	
	//Printing the maze, its elements and hints
	public void showGame()
	{
		this.maze.printMaze();

		this.printHints();
	}
	
	/**
	 * Prints some hints to remember the player of his current objective.
	 */
	public void printHints()
	{
		if(!this.hero.getArmed())
			System.out.println("Get The Sword!");
		else
		{
			if(!this.dragon.getDeathStatus())
				System.out.println("Kill The Dragon!");
			else
				System.out.println("Get Out!");
		}
	}
	
	/**
	 * Updates creatures positions and also writes the maze accordingly.
	 * @param move
	 */
	public void updatePositions(char move)
	{
		//New positions
		hero.newPosition(maze, move, exit);
		dragon.newPosition(maze);
		
		//Rewriting elements on maze
		maze.updateMaze(hero.getPrePosition(), '0');
		maze.updateMaze(dragon.getPrePosition(), '0');
		
		maze.updateMaze(hero.getPosition(), hero.getLetter());
		maze.updateMaze(dragon.getPosition(), dragon.getLetter());
	}
	
	public void updateEnd()
	{
	
	}


	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.initGame('s');
		game.showGame();
		//game.updateGame('d');
		game.showGame();
	}
	
	
	
}
