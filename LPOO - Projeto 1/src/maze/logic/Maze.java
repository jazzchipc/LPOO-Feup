	package maze.logic;

public class Maze {
	//---ATTRIBUTES
	
	private char[][] maze;
	private Position exit;
	
	//---GET ATTRIBUTES FUNCTIONS
	
	public char[][] getMaze()
	{
		return this.maze;
	}
	
	public Position getExit()
	{
		return this.exit;
	}
	
	//---UPDATE ATTRIBUTES FUNCTIONS
	
	public void updateMaze(char[][] maze)
	{
		this.maze = maze;
	}

	//METHODS
	
	public Maze()
	{
		
	}
	
	public Maze(char[][] symbols)
	{
		this.maze = symbols;
		this.exit = this.findPos('S');
	}
	
	public void updateElementPosition(Object obj)
	{
	}
	
	// Imprime o labirinto com os símbolos
	public void printMaze()
	{
		for (int i = 0; i < this.maze.length; i++)
		{
			for (int j = 0; j < this.maze[i].length; j++)
			{
				System.out.print(maze[i][j]); // imprime a letra do array
				System.out.print(" ");

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
					pos.updateY(i);
					pos.updateX(j);
				}
			}
		}

		return pos;
	}
	
	public void updateMaze(Position pos, char letter)
	{
				this.maze[pos.getY()][pos.getX()] = letter;
	}
	
	public char charAt(Position pos)
	{
		return this.maze[pos.getY()][pos.getX()];
		
	}
}