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
	
	/**
	 * Default constructor with no arguments.
	 */
	public MazeBuilder()
	{
		
	}

	/**
	 * Generates a random maze with both sides measuring size.
	 * @param size Size of the side of the maze to generate.
	 */
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
		addPosToStack();
		
		while(!pathHistory.empty())
		{
			while (!guideCellCanGoSomewhere()) 
			{
				pathHistory.pop();
				
				if (pathHistory.empty())
					break;
				else
					guideCell = pathHistory.peek();
			}
			
			if(pathHistory.empty())
				break;
			
			Random r = new Random();
			int dir = r.nextInt(4);
			
			switch (dir) 
			{
			case 0:  //up
				maze[guideCell.getX() * 2][guideCell.getY() * 2 + 1] = ' ';
				break;
			case 1:  //right
				maze[(guideCell.getX() + 1) * 2][guideCell.getY() * 2 + 1] = ' ';
				break;
			case 2:  //down
				maze[guideCell.getX()* 2 + 1][guideCell.getY() * 2] = ' ';
				break;
			case 3:  //left
				maze[guideCell.getX() * 2][guideCell.getY() * 2 + 1] = ' ';
				break;
			case 4:
				break;
			}
			
			moveGuideCell(dir);
			markAsVisited();
			addPosToStack();
		}
		
		return maze;
	}

	/**
	 * Makes one point in one of the walls of the maze as the Exit.
	 */
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
	
	

	/**
	 * Calculates the position for the GuideCell.
	 */
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

	/**
	 * Verifies if the GuideCell can move in the given direction.
	 * @param dir 0 to move up, 1 to move to the right, 2 to move down, 3 to move to the left.
	 * @return True if the GuideCell can move. False otherwise.
	 */
	private boolean guideCellCanMove(int dir) 
	{
		switch (dir) 
		{
		case 0:
			if (guideCell.getY() - 1 < 0)
				return false;
			break;
		case 1:
			if (guideCell.getX() + 1 >= (maze.length - 1) / 2)
				return false;
			break;
		case 2:
			if (guideCell.getY() + 1 >= (maze.length - 1) / 2)
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
	
	/**
	 * Verifies if the GuideCell can move in at least one direction.
	 * @return True if the GuideCell can go somewhere. False otherwise.
	 */
	private boolean guideCellCanGoSomewhere() 
	{
		boolean ret = false;
		
		for (int i = 0; i < 4; i++)
			if (guideCellCanMove(i))
				ret = true;
		
		return ret;
	}
	
	/**
	 * Verifies if the cell in the given direction has been visited.
	 * @param dir 0 to move up, 1 to move to the right, 2 to move down, 3 to move to the left.
	 * @return True if the cell in question has been visited. False otherwise.
	 */
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
	
	/**
	 * Moves the GuideCell in the given direction.
	 * @param dir 0 to move up, 1 to move to the right, 2 to move down, 3 to move to the left.
	 */
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
			guideCell.updateY(guideCell.getX() + 1);
			break;
		case 2:
			guideCell.updateX(guideCell.getY() + 1);
			break;
		case 3:
			guideCell.updateX(guideCell.getX() - 1);
			break;
		default:
			break;
		}
		}
	}

	/**
	 * Adds the GuideCell's Position to the pathHistory stack.
	 */
	private void addPosToStack() 
	{
		Position temp = new Position(guideCell.getX(),guideCell.getY());
		pathHistory.push(temp);
		
	}


	/**
	 * Marks the GuideCell's Position as visited in the visitedCells array.
	 */
	private void markAsVisited() 
	{
		visitedCells[guideCell.getX()][guideCell.getY()] = true;
	}

	/**
	 * Prints a maze.
	 * @param m Bidimensional array to be printed.
	 */
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
		
		char[][] m = mazebuilder.buildMaze(5);

		
		mazebuilder.printMaze(m);
		
	}
	
}
