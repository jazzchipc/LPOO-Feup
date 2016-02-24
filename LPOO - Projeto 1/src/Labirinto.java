
public class Labirinto {

	/****ATRIBUTOS****/
	
	// array bidimensional para guardar os símbolos do labirinto
	private char[][] symbols = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H','0','0','0','0','0','0','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','D','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','0','0','0','0','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','E','X','X','0','0','0','0','0','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};

	
	/****MÉTODOS****/
	
	public static void main(String[] args) {
		Labirinto lab = new Labirinto();
		lab.printLab();
	}
	
	// Imprime o labirinto com os símbolos
	private void printLab()
	{
		for (int i = 0; i < this.symbols.length; i++)
		{
			for (int j = 0; j < this.symbols[i].length; j++)
			{
				System.out.print(symbols[i][j]); // imprime a letra do array
				System.out.print("|");
				
			}
			System.out.print("\n");
		}
	}
	
	 

}
