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

	
	public char[][] buildMaze(int sizeX, int sizeY)
	{
		this.maze = new char[sizeY][sizeX];
	
		for(int i = 0; i < sizeY; i++)
		{
			for(int j = 0; j < sizeX; j++)
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
		
		int exitZ;
		do {
			exitZ = r.nextInt(maze.length - 2) + 1;
		} while (exitZ % 2 == 0);

		switch (r.nextInt(4)) 
		{
		case 0:
			exit.updateX(exitZ);
			break;
		case 1:
			exit.updateX(maze[0].length - 1);
			exit.updateY(exitZ);
			break;
		case 2:
			exit.updateX(exitZ);
			exit.updateY(maze.length - 1);
			break;
		case 3:
			exit.updateY(exitZ);
			break;
		default:
			exit.updateX(1);
			break;
		}

		maze[exit.getY()][exit.getX()] = 'S';
	}
	
	private boolean guideCellCanGoSomewhere() 
	{
		// TODO Auto-generated method stub
		return false;
	}


	private void addPosToStack() 
	{
		// TODO Auto-generated method stub
		
	}


	private void markAsVisited() 
	{
		// TODO Auto-generated method stub
		
	}


	private void initializeGuideCell() 
	{
		// TODO Auto-generated method stub
		
	}

	private void moveGuideCell(int dir)
	{
		
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
		
		char[][] m = mazebuilder.buildMaze(10, 10);

		
		mazebuilder.printMaze(m);
		
	}
	
}
