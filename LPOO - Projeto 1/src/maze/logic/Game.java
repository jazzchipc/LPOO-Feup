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
		

		game.maze.printMaze();
		game.hero.getPosition();
		System.out.println(game.hero.getPosition().y );
		game.hero.getNewPosition();
		game.hero.moveHeroi(game.hero.getPosition());
		game.maze.updateMaze(game.hero.getPosition(), game.hero.getLetter());
		game.maze.printMaze();
		System.out.println(game.hero.getPosition().y );
	}
	
	public void initGame()
	{
		
	hero.updatePosition(maze.findPos(hero.getLetter())); // initial Position of the "H" letter
	dragon.updatePosition(maze.findPos(dragon.getLetter()));
	sword.updatePosition(maze.findPos(sword.getLetter()));
	

	}
}
