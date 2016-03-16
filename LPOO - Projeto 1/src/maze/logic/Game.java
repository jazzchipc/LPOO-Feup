package maze.logic;

import java.util.Random;
import java.util.Scanner;

public class Game {
	
	// iteration 1 maze, without elements
	private char[][] symbols1 = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','0','0','0','0','0','0','0','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','0','0','0','0','0','X','0','S'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','0','0','0','0','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
	
	//---ATTRIBUTES
	
	private Maze maze;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	
	//---GAME STATE
	
	private boolean end;

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
	}
	
	//GAME METHODS
	
	public void initGame(char dragonMode)
	{
		//Initializing maze
		maze.updateMaze(symbols1);
		
		//Initial elements' position
		Position heroInitPos = new Position (1,1);
		Position dragonInitPos = new Position (1,4);
		Position swordInitPos = new Position (1,8);
		
		//Setting those positions
		hero.updatePosition(heroInitPos);
		dragon.updatePosition(dragonInitPos);
		sword.updatePosition(swordInitPos);
		
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
	 * According to each of the elements state (dead, visible, asleep, ...)
	 * updates the other dependent states.
	 */
	public void updateElements()
	{
		
	}
	
	public void updateGame(char move)
	{
		hero.newPosition(maze, move);
		dragon.newPosition(maze);
	}


	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.initGame('s');
		game.showGame();
		game.updateGame('d');
		game.showGame();
	}
	
	
	
}
