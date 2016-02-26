package maze.logic;

public class Game {
	
	/****ATRIBUTOS****/
	
	Maze lab = new Maze();
	Hero heroi = new Hero();

	public static void main(String[] args) 
	{
		Game jogo = new Game();
		
		jogo.initJogo();
		
		int i = 0;
		while (i < 5)
		{
		
		jogo.lab.printLab();
		
		jogo.heroi.getPos();
		
		}
	}
	
	public void initJogo()
	{
		
	heroi.pos = lab.findPos(heroi.letra); // primeira posição do "H"

	}
}
