package maze.logic;

public class Maze {
	// array bidimensional com o labirinto da iteração 1
	char[][] symbols = 	
		{
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H','0','0','0','0','0','0','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','D','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','0','0','0','0','0','X','0','S'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','0','X','X','0','X','0','X','0','X'},
				{'X','E','X','X','0','0','0','0','0','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};

	/****ATRIBUTOS****/
	
	//maze é o array de símbolos do labirinto
	private char[][] maze = symbols;
	private Position exit = this.findPos('S');
	

	/****MÉTODOS****/
	public Maze()
	{
		
	}
	
	
	public Maze(char[][] m1)
	{
		this.maze = m1;
	}
	
	// Imprime o labirinto com os símbolos
	public void printMaze()
	{
		for (int i = 0; i < this.maze.length; i++)
		{
			for (int j = 0; j < this.maze[i].length; j++)
			{
				if(maze[i][j] == '0')
					System.out.print(' ');
				else
					System.out.print(maze[i][j]); // imprime a letra do array
				System.out.print("|");
				
			}
			System.out.print("\n");
		}
	}
	
	// Procura e retorna a posição de uma determinada letra
	public Position findPos(char letra)
	{
		Position pos = new Position();

		for (int i = 0; i < this.maze.length; i++)
		{
			for (int j = 0; j < this.maze[i].length; j++)
			{
				if(maze[i][j] == letra)
				{
					pos.y = i;
					pos.x = j;
				}
			}
		}

		return pos;
	}
	
	public char charAt(Position pos)
	{
		char aux = '0';
		
		for (int i = 0; i < this.symbols.length; i++)
		{
			for (int j = 0; j < this.symbols[i].length; j++)
			{
				if((i == pos.y )&&( j == pos.x))
				{
					aux = this.symbols[i][j];
				}
			}
		}
		
		return aux;
	}
	
	public void updateMaze(Position pos, char letter)
	{
				this.maze[pos.y][pos.x] = letter;
	}
}