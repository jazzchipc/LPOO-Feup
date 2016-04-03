package maze.logic;

import java.util.Random;
import java.util.Scanner;

public class Game {
	
	// iteration 1 maze, without elements
	private char[][] symbols1 = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X','D','X','X',' ','X',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X','H','X','X',' ','X',' ','X',' ','X'},
				{'X','E','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
	
	//---ATTRIBUTES
	
	private Maze maze;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	
	//---GAME STATE
	
	public enum End {
		END_NOT,		// means the game is still running
		END_FORCED,		// the user exited
		END_WIN,		// the player won
		END_LOSS		// the player died
	};
	
	private End end;
	private boolean exit;

	//---GET ATTRIBUTES FUNCTIONS
	
	public Maze getMaze()
	{
		return this.maze;
	}
	
	public Hero getHero()
	{
		return this.hero;
	}
	
	public Dragon getDragon()
	{
		return this.dragon;
	}
	
	public End getEnd()
	{
		return this.end;
	}
	
	public boolean getExit()
	{
		return exit;
	}
	/**
	 * Default constructor
	 */
	public Game()
	{
		this.maze = new Maze(symbols1);
		this.hero = new Hero();
		this.dragon = new Dragon();
		this.sword = new Sword();
		
		this.end = End.END_NOT;
		this.exit = false;
	}
	
	public Game(char[][] maze)
	{
		this.maze = new Maze(maze);
		this.hero = new Hero();
		this.dragon = new Dragon();
		this.sword = new Sword();
		
		this.end = End.END_NOT;
		this.exit = false;
	}
	
	//GAME METHODS
	
	public void initGame(char dragonMode)
	{	
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

		if (this.end == End.END_NOT)
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
		// Hero moves first
		hero.newPosition(maze, move, exit);	// generates new position
		maze.updateMaze(hero.getPrePosition(), ' ');	
		maze.updateMaze(hero.getPosition(), hero.getLetter());	
		
		// Dragon moves after hero
		if(!dragon.getDeathStatus())
		{
			dragon.newPosition(maze);
			maze.updateMaze(dragon.getPrePosition(), ' ');
			maze.updateMaze(dragon.getPosition(), dragon.getLetter());	// rewrites dragon in new position
		}
		
		if (this.hero.getPosition().equals(this.maze.getExit()))
			this.end = End.END_WIN;
	}
	
	private void heroVSDragon()
	{
		if (this.hero.getArmed())
		{
			this.dragon.killCreature();	// kill dragon
			maze.updateMaze(dragon.getPosition(), '0'); // make a path where the dragon was killed
			this.exit = true; // open door
			System.out.println("You killed the dragon!");
		}

		else
			if (!this.dragon.getAsleep())	// if the hero is not armed and dragon is awake
			{
				this.hero.killCreature();
				this.end = End.END_LOSS;
			}
	}
	
	private void updateDragonHero()
	{
		if((this.hero.getPosition().distanceTo(this.dragon.getPosition()) <= 1) && (!this.dragon.getDeathStatus()))	
			// if the dragon is adjacent to the hero, and it's still alive
		{
			heroVSDragon();
		}
	}
	
	
	
	public void updateGame(char move)
	{
		this.updatePositions(move);
		this.updateDragonHero();
	}
	
	public void endGame()
	{
		switch(this.end)
		{
		case END_WIN: System.out.println("Well done! You're out of the maze!"); break;
		case END_LOSS: System.out.println("Bad luck. Next time try to get the sword first."); break;
		default: break;
		}
	}

	public String mazeToString()
	{
		String ret = "";
		
		for (int i = 0; i < 10; i++)	// TODO:change to match maze dimension
		{
			for (int j = 0; j < 10; j++)
			{
				char c = this.maze.getMaze()[i][j];
				
				if (c == '0')
					c = ' ';
				ret = ret + c + " ";
			}
			ret = ret + "\n";
		}
		
		return ret;
	}
	
	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.initGame('s');
		game.showGame();
		//game.updateGame('d');
		game.showGame();
	}
	
	public void updateHeroPosition(Position pos)
	{
		hero.updatePosition(pos);
	}
	
	public void moveHeroUp()
	{
		this.updatePositions('w');
	}
	
	public void moveHeroDown()
	{
		this.updatePositions('s');
	}
	
	public void moveHeroLeft()
	{
		this.updatePositions('a');
	}
	
	public void moveHeroRight()
	{
		this.updatePositions('d');
	}
}
