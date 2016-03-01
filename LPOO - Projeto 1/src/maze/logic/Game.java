package maze.logic;

public class Game {
	
	/****ATRIBUTOS****/
	
	private Maze maze = new Maze();
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	

	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.initGame();
		
		int i = 0;
		while (i < 5)
		{
		
		game.maze.printLab();
		
		game.hero.getPos();
		
		}
	}
	
	public void initGame()
	{
		
	hero.updatePosition(maze.findPos(hero.getLetter())); // initial Position of the "H" letter
	dragon.updatePosition(maze.findPos(dragon.getLetter()));
	sword.updatePosition(maze.findPos(sword.getLetter()));
	

	}
}
