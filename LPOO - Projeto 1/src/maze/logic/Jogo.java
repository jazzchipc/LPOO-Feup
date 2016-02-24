package maze.logic;

public class Jogo {
	
	/****ATRIBUTOS****/
	
	Labirinto lab = new Labirinto();
	Heroi heroi = new Heroi();

	public static void main(String[] args) 
	{
		Jogo jogo = new Jogo();
		
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
