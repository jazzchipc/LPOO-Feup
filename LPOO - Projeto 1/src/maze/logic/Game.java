package maze.logic;

import java.util.Scanner;
import java.util.Random;

public class Game {
	
	/****ATRIBUTOS****/
	
	private Maze maze = new Maze();
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	private boolean end = false;

	
	/****METODOS****/ 
	
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

	
	public void getNewHeroPosition()
	{
		
		Position temp = new Position();
		temp.x = hero.pos.x;
		temp.y = hero.pos.y;

		Scanner in = new Scanner(System.in);
		char move;
		System.out.println("Move: ");
		move = in.next().charAt(0);
		
		if (!(move == 'w' || move == 'a'|| move == 's'|| move == 'd'))
			System.out.println("Error!");
		
		
	
			switch (move)
			{
			case 'w':
				temp.y = temp.y - 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.y = hero.pos.y - 1;			
				break;
			case 'a': 
				temp.x = temp.x - 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.x = hero.pos.x - 1;
				break;
			case 's': 
				temp.y = temp.y + 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.y = hero.pos.y + 1;
				break;
			case 'd': 
				temp.x = temp.x + 1;
				if(analiseNewHeroPosition(charAt(temp)))
				hero.pos.x = hero.pos.x + 1;
				break;
			}

	}
		
	public boolean analiseNewHeroPosition(char letra)
	{
		if(!(hero.getArmed()))
		{
			switch(letra)
			{
			case 'X': 
				return false;
			case '0':
				return true;
			case 'E': 
				hero.updateArmed(true);
				hero.updateLetter('A');
				sword.updateVisible(false);
				return true;
			case 'D':
				hero.dead = true;
				hero.visible = false;
				return true;
			case 'S':
				return false;				
			}
		}
		else
		{
			switch(letra)
			{
			case 'X': 
				return false;
			case '0':
				return true;			
			case 'D':
				dragon.updateDeathStatus(true);
				dragon.updateVisible(false);
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
	
	public void getNewDragonPosition()
	{
		Position temp = new Position();
		temp.x = dragon.pos.x;
		temp.y = dragon.pos.y;
		
		Random in = new Random();
		int move = in.nextInt(4);

//		Scanner in = new Scanner(System.in);
//		char move;
//		System.out.println("Move: ");
//		move = in.next().charAt(0);
		
		switch (move)
		{
		case 0: 
			temp.y = temp.y - 1;
			if(analiseNewDragonPosition(charAt(temp)))
			dragon.pos.y = dragon.pos.y - 1;
			break;
		case 1: 
			temp.x = temp.x - 1;
			if(analiseNewDragonPosition(charAt(temp)))
			dragon.pos.x = dragon.pos.x - 1;
			break;
		case 2: 
			temp.y = temp.y + 1;
			if(analiseNewDragonPosition(charAt(temp)))
			dragon.pos.y = dragon.pos.y + 1;
			break;
		case 3: 
			temp.x = temp.x + 1;
			if(analiseNewDragonPosition(charAt(temp)))
			dragon.pos.x = dragon.pos.x + 1;
			break;
		}
		
	
	}
	
	public boolean analiseNewDragonPosition(char letra)
	{
		switch(letra)
			{
			case 'X': 
				return false;
			case 'S':
				return false;
			case '0':
				dragon.updateLetter('D');
				sword.updateLetter('E');
				return true;
			case 'E': 
				dragon.updateLetter('F');
				sword.updateLetter('F');
				return true;
			case 'H':
				hero.updateDeathStatus(true);
				hero.updateVisible(false);
				return true;
			case 'A':
				dragon.updateDeathStatus(true);	
				dragon.updateVisible(false);
				return true;
			}
		return false;
	}
	
	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.initGame();
		
		game.maze.printMaze();
		
		while(!(game.end)){
		game.maze.updateMaze(game.hero.getPosition(), '0');
		game.maze.updateMaze(game.dragon.getPosition(), '0');
			
		game.getNewHeroPosition();

		game.getNewDragonPosition();
		
		if(game.hero.getVisible() == true)
			game.maze.updateMaze(game.hero.getPosition(), game.hero.getLetter());
		else
			game.maze.updateMaze(game.hero.getPosition(),'0');
			
		if(game.dragon.getVisible() == true)
			game.maze.updateMaze(game.dragon.getPosition(), game.dragon.getLetter());
		else
			game.maze.updateMaze(game.dragon.getPosition(),'0');
		
		if(game.sword.getVisible() == true)
			game.maze.updateMaze(game.sword.getPosition(),game.sword.getLetter());
//		else
//			game.maze.updateMaze(game.sword.getPosition(), '0');
		
		game.maze.printMaze();
		
		if(!game.hero.getArmed())
			System.out.println("Get The Sword!");
		else
			{if(!game.dragon.getDeathStatus())
				System.out.println("Kill The Dragon!");
			else
				System.out.println("Get Out!");
			}
		}
	}
	
	public void initGame()
	{
		
	hero.updatePosition(maze.findPos(hero.getLetter())); // initial Position of the "H" letter
	dragon.updatePosition(maze.findPos(dragon.getLetter()));
	sword.updatePosition(maze.findPos(sword.getLetter()));
	

	}
	
	
}
