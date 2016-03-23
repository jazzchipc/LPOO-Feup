package maze.logic;

import java.util.Random;

public class MazeBuilder implements IMazeBuilder{

	private int size;
	private char[][] m = new char[size][size];
	private MazeBuilder mazebuilder = new MazeBuilder();
	
	public MazeBuilder()
	{
		
	}
	
	public void buildFrame()
	{
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if(i == 0 || i == size-1 || j == 0 || j == size-1)
					m[i][j] = 'X';
			}
	
		}
		
	}
	
	public char[][] buildMaze(int size)
	{
		this.size = size;

		mazebuilder.buildFrame();
		
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				Random in = new Random();
				int rand = in.nextInt(2);
				
				if(i != 0 && i != size-1  && j != 0  && j != size-1)
				{
					if(rand == 0)
					{
						m[i][j] = 'X';
//						if(m[i-1][j-1] == 'X' && 
//							m[i][j-1] == 'X' && 
//							m[i-1][j] == 'X' && 
//							m[i-2][j-2] == 'X' && 
//							m[i-2][j] == 'X' && 
//							m[i][j-2] == 'X' &&
//							m[i-1][j-2] == 'X' &&
//							m[i-2][j-1] == 'X')
//						m[i][j] = ' ';
					}
					else if(rand == 1)
						m[i][j] = ' ';
				}
			}

		}

		while(true)
		{
		Random in1 = new Random();
		int rand1 = in1.nextInt(size);
		Random in2 = new Random();
		int rand2 = in2.nextInt(4);
		
		switch(rand2)
		{
		case 0: m[0][rand1] = 'S';
		break;
		case 1: m[size-1][rand1] ='S';
		break;
		case 2: m[rand1][0] = 'S';
		break;
		case 3: m[rand1][size-1] = 'S';
		break;
		}
		
		if(!(m[0][0] == 'S' || m[0][size-1] == 'S' || m[size-1][0] == 'S' || m[size-1][size-1] == 'S'))
			break;
		}
		
		return m;
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
