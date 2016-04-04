	package maze.logic;

	/**
	 * 
	 * Class that saves the array of chars which represent the game and changes it acorrding to game state.
	 *
	 */
public class Maze {
	//---ATTRIBUTES
	
	private char[][] maze;
	private int sizeX, sizeY;
	private Position exit;
	
	//---GET ATTRIBUTES FUNCTIONS
	
	/**
	 * 
	 * @return The bidimensional array of char's that makes up the maze.
	 */
	public char[][] getMaze()
	{
		return this.maze;
	}
	
	/**
	 * 
	 * @return The Position of the exit.
	 */
	public Position getExit()
	{
		return this.exit;
	}
	
	//---UPDATE ATTRIBUTES FUNCTIONS
	
	/**
	 * Assigns a new bidimensional array as the maze.
	 * @param maze
	 */
	public void updateMaze(char[][] maze)
	{
		this.maze = maze;
	}

	//METHODS
	
	/**
	 * Default constructor with no arguments.
	 */
	public Maze()
	{
		
	}
	
	/**
	 * Constructor that assings a bidimensional array as the Maze.
	 * @param symbols bidimensional array to be assigned.
	 */
	public Maze(char[][] symbols)
	{
		this.maze = symbols;
		this.exit = this.findPos('S');
	}
	
	/**
	 * 
	 * @param obj Element to have it's position updated
	 */
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
	/**
	 * 
	 * @param letra Element to find.
	 * @return The Position of letra in the maze.
	 */
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
	
	/**
	 * 
	 * @param pos Position for letter in the maze.
	 * @param letter Element to be added to the maze in pos.
	 */
	public void updateMaze(Position pos, char letter)
	{
				this.maze[pos.getY()][pos.getX()] = letter;
	}
	
	/**
	 * 
	 * @param pos Position to analyze.
	 * @return The char at the given Position.
	 */
	public char charAt(Position pos)
	{
		return this.maze[pos.getY()][pos.getX()];
		
	}
	
	/**
	 * 
	 * @return The size the side of the maze.
	 */
	public int getSize()
	{
		return this.maze.length;
	}
}