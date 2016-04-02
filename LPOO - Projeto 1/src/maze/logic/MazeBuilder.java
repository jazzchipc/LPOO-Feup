package maze.logic;

import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder{

	private int size;
	private char[][] maze;
	private boolean[][] visitedCells;
	private Stack<Position> pathHistory;
	private Position guideCell;
	private Position exit;
	
	
	public MazeBuilder()
	{
		
	}

	
	public char[][] buildMaze(int size)
	{
		this.maze = new char[size][size];
		visitedCells = new boolean[size - 2][size - 2];
		
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
			while (!guideCellCanGoSomewhere()) {
			
				pathHistory.pop();
				
				if (pathHistory.empty())
					break;
				else
					guideCell = pathHistory.peek();
			}
			
			if(pathHistory.empty())
				break;
			
			Random r = new Random();
			int dir = r.nextInt(3);
			
			switch (dir) 
			{
			case 0:
				maze[guideCell.getY() * 2][guideCell.getX() * 2 + 1] = ' ';
				break;
			case 1:
				maze[guideCell.getY() * 2 + 1][(guideCell.getX() + 1) * 2] = ' ';
				break;
			case 2:
				maze[(guideCell.getY() + 1) * 2][guideCell.getX() * 2 + 1] = ' ';
				break;
			case 3:
				maze[guideCell.getY() * 2 + 1][guideCell.getX() * 2] = ' ';
				break;
			}
			
			moveGuideCell(dir);
			markAsVisited();
			addPosToStack();
		}
		
		return maze;
	}
	
	
//	public void putHero(char[][] m,int size)
//	{
//		while(true)
//		{
//			
//		Random in1 = new Random();
//		int rand1 = in1.nextInt(size-2);
//		Random in2 = new Random();
//		int rand2 = in2.nextInt(size-2);
//		
//			if(m[rand1+1][rand2+1] == ' ')
//			{
//				m[rand1+1][rand2+1] = 'H';
//				break;
//			}
//		}
//	}
//	
//	public void putDragon(char[][] m,int size)
//	{
//		while(true)
//		{
//			
//		Random in1 = new Random();
//		int rand1 = in1.nextInt(size-2);
//		Random in2 = new Random();
//		int rand2 = in2.nextInt(size-2);
//		
//			if(m[rand1+1][rand2+1] == ' ')
//			{
//				m[rand1+1][rand2+1] = 'D';
//				break;
//			}
//		}
//	}
//	
//	public void putSword(char[][] m,int size)
//	{
//		while(true)
//		{
//			
//		Random in1 = new Random();
//		int rand1 = in1.nextInt(size-2);
//		Random in2 = new Random();
//		int rand2 = in2.nextInt(size-2);
//		
//			if(m[rand1+1][rand2+1] == ' ')
//			{
//				m[rand1+1][rand2+1] = 'E';
//				break;
//			}
//		}
//	}
//	
//	public void correct(char[][] m, int size)
//	{
//		for (int i = 1; i < size-1; i++)
//		{
//			for (int j = 1; j < size-2; j++)
//			{
//				if(m[i][j] == 'X' && m[i+1][j] == 'X' && m[i][j+1] == 'X' && m[i+1][j+1] == 'X' && m[i-1][j] == 'X' && m[i][j-1] == 'X' && m[i-1][j-1] == 'X' && m[i+1][j-1] == 'X' && m[i-1][j+1] == 'X')
//					m[i][j] = ' ';
//				
//				if(m[i][j] == ' ' && m[i+1][j] == ' ' && m[i][j+1] == ' ' && m[i+1][j+1] == ' ')
//					m[i][j] = 'X';
//	
//				if(m[i][j] == 'X' && m[i+1][j] == ' ' && m[i][j+1] == ' ' && m[i+1][j+1] == 'X')
//					m[i][j] = ' ';
//				
//				if(m[i][j] == ' ' && m[i+1][j] == 'X' && m[i][j+1] == 'X' && m[i+1][j+1] == ' ')
//					m[i][j] = 'X';
//
//			}
//		}
//	}
//	

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
			if (guideCell.getX() + 1 >= (maze[0].length - 1) / 2)
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
		switch (dir) 
		{
		case 0:
			guideCell.updateY(guideCell.getY() - 1);
			break;
		case 1:
			guideCell.updateY(guideCell.getY() + 1);
			break;
		case 2:
			guideCell.updateX(guideCell.getX() - 1);
			break;
		case 3:
			guideCell.updateX(guideCell.getX() + 1);
			break;
		default:
			break;
		}
	}

	private void addPosToStack() 
	{
		Position temp = new Position(guideCell.getX(),guideCell.getY());
		pathHistory.push(temp);
		
	}


	private void markAsVisited() 
	{
		visitedCells[guideCell.getX()][guideCell.getY()] = true;
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
		
		char[][] m = mazebuilder.buildMaze(10);

		
		mazebuilder.printMaze(m);
		
	}
	
}