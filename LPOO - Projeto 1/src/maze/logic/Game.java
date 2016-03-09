package maze.logic;

import java.util.Random;
import java.util.Scanner;

public class Game {
	
	//ATTRIBUTES
	
	private Maze maze = new Maze();
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	
	private boolean end = false;		// the game has ended

	//GET ATTRIBUTES FUNCTIONS
	
	public Maze getMaze()
	{
		return this.maze;
	}
	
	public boolean getEnd()
	{
		return this.end;
	}
	
	//GAME METHODS
	
	public void initGame()
	{
		hero.updatePosition(maze.findPos(hero.getLetter())); // initial Position of the "H" letter
		dragon.updatePosition(maze.findPos(dragon.getLetter()));
		sword.updatePosition(maze.findPos(sword.getLetter()));
	}
	
	public void showGame()
	{
		this.maze.printMaze();

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
	
	public void updateGame(char command)
	{
		this.moveHero(command);
		
		this.moveDragon();
		
		if(this.end)
		{
			this.endGame();
			return;
		}
	}
	
	public void endGame()
	{
		if (this.hero.dead)
		{
			System.out.println("Next time, try to reach the sword first.");
		}
		else
		{
			System.out.println("Well done! You got away from the maze!");
		}
	}

	//MAZE METHODS
	
	public char charAt(Position pos)
	{
		char aux = '0';
		
		for (int i = 0; i < maze.symbols.length; i++)
		{
			for (int j = 0; j < maze.symbols[i].length; j++)
			{
				if((i == pos.y )&&( j == pos.x))
				{
					aux = maze.symbols[i][j];
				}
			}
		}
		
		return aux;
	}

	//HERO METHODS
	
	public void heroKillsDragon()
	{
		this.dragon.dead = true;
		this.dragon.visible = false;
	}
	
	public void heroPicksSword()
	{
		this.hero.updateArmed(true);
		this.hero.letter = 'A';
	}
	
	public void getNewHeroPosition(char move)
	{
		this.hero.prePos.x = this.hero.pos.x;
		this.hero.prePos.y = this.hero.pos.y;// saving current hero position on his previous position variable	

		switch (move)
		{
		case 'w':
			hero.pos.y = hero.pos.y - 1;			
			break;
		case 'a': 
			hero.pos.x = hero.pos.x - 1;
			break;
		case 's': 
			hero.pos.y = hero.pos.y + 1;
			break;
		case 'd': 
			hero.pos.x = hero.pos.x + 1;
			break;
		}
	}
		
	public boolean analiseNewHeroPosition()
	{
		char letter = this.charAt(this.hero.pos);	// letter in hero's next case
		
		if(!(hero.getArmed()))
		{
			switch(letter)
			{
			case 'X': 
				return false;
			case '0':
				return true;
			case 'E': 
				this.heroPicksSword();
				return true;
			case 'D':
				this.dragonKillsHero();
				return true;
			case 'S':
				return false;				
			}
		}
		else
		{
			switch(letter)
			{
			case 'X': 
				return false;
			case '0':
				return true;			
			case 'D':
				this.heroKillsDragon();
				return true;
			case 'S':
				if(dragon.getDeathStatus())
					{	
						return true;
					}
				else
					{
						return false;
					}
			}
		}
		return false;
	}
	
	public void moveHero(char command)
	{
		this.maze.updateMaze(this.hero.pos, '0');

		this.getNewHeroPosition(command);
		if(!this.analiseNewHeroPosition())
			this.hero.pos = this.hero.prePos;	// reverse move

		this.maze.updateMaze(this.hero.pos, this.hero.letter);

	}
	
	//DRAGON METHODS
	
	public void dragonKillsHero()
	{
		this.hero.dead = true;
		this.hero.visible = false;
		this.end = true;
	}
	
	public void getNewDragonPosition()
	{
		this.dragon.prePos = this.dragon.pos;

		Random in = new Random();
		int move = in.nextInt(4);

		switch (move)
		{
		case 0: 
			dragon.pos.y = dragon.pos.y - 1;
			break;
		case 1:
			dragon.pos.x = dragon.pos.x - 1;
			break;
		case 2: 
			dragon.pos.y = dragon.pos.y + 1;
			break;
		case 3: 
			dragon.pos.x = dragon.pos.x + 1;
			break;
		}
	}

	public boolean analiseNewDragonPosition()
	{
		char letter = this.charAt(this.dragon.pos);
		
		switch(letter)
			{
			case 'X': 
				return false;
			case 'S':
				return false;
			case '0':
				dragon.updateLetter('D');
				return true;
			case 'E': 
				dragon.updateLetter('F');
				return true;
			case 'H':
				this.dragonKillsHero();
				return true;
			case 'A':
				dragon.updateDeathStatus(true);	
				dragon.updateVisible(false);
				return true;
			}
		return false;
	}
	
	public void moveDragon()
	{
		this.maze.updateMaze(this.dragon.pos, '0');

		this.getNewDragonPosition();
		if(!this.analiseNewDragonPosition())
			this.dragon.pos = this.dragon.prePos;

		if(this.dragon.visible)
			this.maze.updateMaze(this.dragon.pos, this.dragon.letter);

	}
	
	
	public static void main(String[] args) 
	{
		
		
	}
	
	
	
}
