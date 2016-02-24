package maze.logic;

public class Jogo {
	
	/****ATRIBUTOS****/
	
	Labirinto lab = new Labirinto();
	Heroi heroi = new Heroi();

	public static void main(String[] args) {

	}
	
	public void initJogo()
	{
	heroi.pos = lab.findPos(heroi.letra); // primeira posição do "H"
	
	
	}
}
