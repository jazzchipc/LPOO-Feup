package maze.logic;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * This class aggregates all other classes, in order to create and update the game itself. 
 * Most classes interact here.
 *
 */
public class Game {
	
	// iteration 1 maze, without elements
	private char[][] symbols1 = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X','D','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X','E','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
	
	//---ATTRIBUTES
	
	private Maze maze;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private MazeBuilder mazeBuilder;
	
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
	
	/**
	 * 
	 * @return The maze of the Game.
	 */
	public Maze getMaze()
	{
		return this.maze;
	}
	
	/**
	 * 
	 * @return The Hero of the Game.
	 */
	public Hero getHero()
	{
		return this.hero;
	}
	
	/**
	 * 
	 * @return The Dragon of the Game.
	 */
	public Dragon getDragon()
	{
		return this.dragon;
	}
	
	/**
	 * 
	 * @return THe End status of the Game(END_NOT,END_FORCED,END_WIN,END_LOSS).
	 */
	public End getEnd()
	{
		return this.end;
	}
	
	/**
	 * 
	 * @return True if the Dragon is dead. False otherwise.
	 */
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

	/**
	 * Constructor where a maze is assigned to the Game.
	 * @param maze Maze to be assigned to the game.
	 */
	public Game(char[][] maze)
	{

		this.maze = new Maze(maze);
		this.hero = new Hero();
		this.dragon = new Dragon();
		this.sword = new Sword();
	
		this.end = End.END_NOT;
		this.exit = false;
		
	}

	/**
	 * Constructor where a maze is generated and assigned to the Game.
	 * @param mazeSize Size of the maze to be generated.
	 */
//	public Game(int mazeSize)
//	{
//		
//		this.maze = new Maze(mazeBuilder.buildMaze(mazeSize));
//		this.hero = new Hero();
//		this.dragon = new Dragon();
//		this.sword = new Sword();
//		
//		this.end = End.END_NOT;
//		this.exit = false;
//	}

	
	//GAME METHODS
	
	/**
	 * Sets up the positions of the Hero, the Dragon, and the Sword and Updates the Dragon's mode.
	 * @param dragonMode i if Dragon is idle, r if Dragon moves randomly, s if Dragon is sleeping.
	 */
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
	/**
	 * Prints the current maze, and hints for the player.
	 */
	public void showGame()
	{
		this.maze.printMaze();

		if (this.end == End.END_NOT)
			this.printHints();
	}
	
	/**
	 * Prints some hints to remember the player of his current objective.
	 */
	public String printHints()
	{
		if(!this.hero.getArmed())
		{
			System.out.println("Get The Sword!");
			return "Get the sword!";
		}
		
		else
		{
			if(!this.dragon.getDeathStatus())
			{
				System.out.println("Kill The Dragon!");
				return "Kill the dragon!";
			}
			else
			{
				System.out.println("Get Out!");
				return "Get out!";
			}
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
	
	/**
	 * Resolves the confrontation between the Hero and the Dragon.
	 */
	public void heroVSDragon()
	{
		if (this.hero.getArmed())
		{
			this.dragon.killCreature();	// kill dragon
			maze.updateMaze(dragon.getPosition(), ' '); // make a path where the dragon was killed
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
	
	/**
	 * Generates the conflict between the Hero and the Dragon if they are adjacent.
	 */
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
		
		for (int i = 0; i < this.maze.getMaze().length; i++)	// TODO:change to match maze dimension
		{
			for (int j = 0; j < this.maze.getMaze().length; j++)
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
		maze.updateMaze(hero.getPrePosition(), ' ');	
		maze.updateMaze(hero.getPosition(), hero.getLetter());
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
