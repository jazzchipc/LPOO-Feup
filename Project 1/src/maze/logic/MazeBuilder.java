package maze.logic;

import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder{

	//private int size;
	private char[][] maze;
	private boolean[][] visitedCells;
	private Stack<Position> pathHistory;
	private Position guideCell;
	private Position exit;
	
	public int numberOfDragons = 1;
	
	Random r = new Random();
	
	public MazeBuilder()
	{
		
	}
	
	public MazeBuilder(int numberOfDragons)
	{
		this.numberOfDragons = numberOfDragons;
	}

	
	public char[][] buildMaze(int size)
	{
		this.maze = new char[size][size];
		visitedCells = new boolean[(size-1)/2][(size-1)/2];
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if (i % 2 != 0 && j % 2 != 0)
					maze[i][j] = ' ';
				else
					maze[i][j] = 'X';
			}
		}
		
		for (int i = 0; i < visitedCells.length; i++)
			for (int j = 0; j < visitedCells[i].length; j++)
				visitedCells[i][j] = false;
		
		putExit();
		initializeGuideCell();
		markAsVisited();
		
		pathHistory = new Stack<Position>();
		
		// While there are unvisited cells
		while (!allCellsVisited())
		{
			//printMaze(maze);
			//System.out.println("");
			
			// Set as visited
			markAsVisited();
			
			// If the cell can "jump" somewhere
			if (guideCellCanGoSomewhere())
			{	
				// Push current position
				addPosToStack();
				
				int dir = r.nextInt(4);
				
				// Select direction to "jump"
				while(!guideCellCanMove(dir))
				{
					dir = r.nextInt(4);
				}
				
				// Delete wall
				switch (dir) 
				{
				case 0:  //up
					maze[guideCell.getY() * 2][guideCell.getX() * 2 + 1] = ' ';
					break;
				case 1:  //right
					maze[guideCell.getY() * 2 + 1][guideCell.getX() * 2 + 2] = ' ';
					break;
				case 2:  //down
					maze[guideCell.getY()* 2 + 2][guideCell.getX() * 2 + 1] = ' ';
					break;
				case 3:  //left	
					maze[guideCell.getY() * 2 + 1][guideCell.getX() * 2] = ' ';
					break;
				default:
					break;
				}
				
				// Move the cell
				moveGuideCell(dir);
				
			}
			
			else
			{
				guideCell = pathHistory.pop();
			}
			
		}
		
		//Add hero
		randomAddLetterToMaze('H');
		
		//Add dragons
		for (int i = 0; i < this.numberOfDragons; i++)
			randomAddLetterToMaze('D');
		
		//Add sword
		randomAddLetterToMaze('E');
		
		return maze;
	}

	private void putExit()
	{
		exit = new Position();
		Random r = new Random();
		
		int temp;
		do {
			temp = r.nextInt(maze.length - 2) + 1;
		} while (temp % 2 == 0);

		switch (r.nextInt(4)) 
		{
		case 0:
			exit.updateX(temp);
			break;
		case 1:
			exit.updateX(maze[0].length - 1);
			exit.updateY(temp);
			break;
		case 2:
			exit.updateX(temp);
			exit.updateY(maze.length - 1);
			break;
		case 3:
			exit.updateY(temp);
			break;
		default:
			exit.updateX(1);
			break;
		}

		maze[exit.getY()][exit.getX()] = 'S';
	}
	
	

	private void initializeGuideCell() 
	{
		Position cellNextToExit = new Position(exit.getX(), exit.getY());
		
		if (exit.getX() == 0)
			cellNextToExit.updateX(cellNextToExit.getX() + 1);
		else if (exit.getX() == maze.length - 1)
			cellNextToExit.updateX(cellNextToExit.getX() - 1);
		else if (exit.getY() == 0)
			cellNextToExit.updateY(cellNextToExit.getY() + 1);
		else
			cellNextToExit.updateY(cellNextToExit.getY() - 1);

		int guideCellX = (cellNextToExit.getX() - 1) / 2;
		int guideCellY = (cellNextToExit.getY() - 1) / 2;

		guideCell = new Position(guideCellX, guideCellY);
	}

	private boolean guideCellCanMove(int dir) 
	{
		switch (dir) 
		{
		case 0:
			if (guideCell.getY() - 1 < 0)
				return false;
			break;
		case 1:
			if ((guideCell.getX() + 1) >= visitedCells.length)
				return false;
			break;
		case 2:
			if ((guideCell.getY() + 1) >= visitedCells.length)
				return false;
			break;
		case 3:
			if (guideCell.getX() - 1 < 0)
				return false;
			break;
		default:
			break;
		}

		return !cellNextToGuideCellHasBeenVisited(dir);
	}
	
	private boolean guideCellCanGoSomewhere() 
	{
		boolean ret = false;
		
		for (int i = 0; i < 4; i++)
			if (guideCellCanMove(i))
				ret = true;
		
		return ret;
	}
	
	private boolean cellNextToGuideCellHasBeenVisited(int dir) 
	{
		switch (dir) 
		{
		case 0:
			return visitedCells[guideCell.getY() - 1][guideCell.getX()];
		case 1:
			return visitedCells[guideCell.getY()][guideCell.getX() + 1];
		case 2:
			return visitedCells[guideCell.getY() + 1][guideCell.getX()];
		case 3:
			return visitedCells[guideCell.getY()][guideCell.getX() - 1];
		default:
			break;
		}

		return false;
	}
	
	private void moveGuideCell(int dir)
	{
		if(guideCellCanMove(dir))
		{
		switch (dir) 
		{
		case 0:
			guideCell.updateY(guideCell.getY() - 1);
			break;
		case 1:
			guideCell.updateX(guideCell.getX() + 1);
			break;
		case 2:
			guideCell.updateY(guideCell.getY() + 1);
			break;
		case 3:
			guideCell.updateX(guideCell.getX() - 1);
			break;
		default:
			break;
		}
		}
	}

	private void addPosToStack() 
	{
		Position temp = new Position(guideCell.getX(),guideCell.getY());
		pathHistory.push(temp);
		
	}


	private void markAsVisited() 
	{
		visitedCells[guideCell.getY()][guideCell.getX()] = true;
	}
	
	private boolean allCellsVisited()
	{
		boolean ret = true;
		
		for (int i = 0; i < this.visitedCells.length; i++)
		{
			for (int j = 0; j < this.visitedCells[i].length; j++)
			{
				if (this.visitedCells[i][j] == false)
					ret = false;
			}
		}
		
		return ret;
	}
	
	private void randomAddLetterToMaze(char letter)
	{
		int i, j;
		
		// Placing dragon non-adjacent to hero
		if (letter == 'D')
		{
			Position hero = new Position();
			for (int a = 0; a < this.maze.length; a++)
			{
				for (int b = 0; b < this.maze[a].length; b++)
				{
					if(maze[a][b] == 'H')
					{
						hero.updateY(a);
						hero.updateX(b);
					}
				}
			}
			
			do
			{
				i = r.nextInt(maze.length);
				j = r.nextInt(maze[0].length);

			} while ((maze[i][j] != ' ') || (new Position (j, i).distanceTo(hero) <= 1));
		}
		else
		{
			// find available spot
			do
			{
				i = r.nextInt(maze.length);
				j = r.nextInt(maze[0].length);
			} while (maze[i][j] != ' ');
		}

		maze[i][j] = letter;

	}

	public void printMaze(char[][] m)
	{
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[i].length; j++)
			{
				
				if(j == m.length-1)
					System.out.println(m[i][j] + " ");
				else
					System.out.print(m[i][j] + " ");
	
			}
			
		}
	}
	
	public static void main(String[] args) 
	{
		MazeBuilder mazebuilder = new MazeBuilder();
		
		char[][] m = mazebuilder.buildMaze(7);

		mazebuilder.printMaze(m);
		
	}
	
}
