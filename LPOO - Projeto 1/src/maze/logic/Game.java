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
		

		game.maze.printLab();
		
		
		game.hero.getPos();
		game.hero.getNewPosition();
		game.hero.moveHeroi(game.hero.getPos());
		game.maze.printLab();
	}
	
	public void initGame()
	{
		
	hero.updatePosition(maze.findPos(hero.getLetter())); // initial Position of the "H" letter
	dragon.updatePosition(maze.findPos(dragon.getLetter()));
	sword.updatePosition(maze.findPos(sword.getLetter()));
	

	}
}
