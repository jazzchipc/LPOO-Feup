package maze.logic;

import java.util.Random;

public class MazeBuilder implements IMazeBuilder{

	private int size;
	private char[][] m ;
	
	
	public MazeBuilder()
	{
		
	}
	
	public void putExit(char[][] m,int size)
	{
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
	}
	
	public void putHero(char[][] m,int size)
	{
		while(true)
		{
			
		Random in1 = new Random();
		int rand1 = in1.nextInt(size-2);
		Random in2 = new Random();
		int rand2 = in2.nextInt(size-2);
		
			if(m[rand1+1][rand2+1] == ' ')
			{
				m[rand1+1][rand2+1] = 'H';
				break;
			}
		}
	}
	
	public void putDragon(char[][] m,int size)
	{
		while(true)
		{
			
		Random in1 = new Random();
		int rand1 = in1.nextInt(size-2);
		Random in2 = new Random();
		int rand2 = in2.nextInt(size-2);
		
			if(m[rand1+1][rand2+1] == ' ')
			{
				m[rand1+1][rand2+1] = 'D';
				break;
			}
		}
	}
	
	public void putSword(char[][] m,int size)
	{
		while(true)
		{
			
		Random in1 = new Random();
		int rand1 = in1.nextInt(size-2);
		Random in2 = new Random();
		int rand2 = in2.nextInt(size-2);
		
			if(m[rand1+1][rand2+1] == ' ')
			{
				m[rand1+1][rand2+1] = 'E';
				break;
			}
		}
	}
	
	public void correct(char[][] m, int size)
	{
		for (int i = 1; i < size-1; i++)
		{
			for (int j = 1; j < size-1; j++)
			{
				if(m[i][j] == 'X' && m[i+1][j] == 'X' && m[i][j+1] == 'X' && m[i+1][j+1] == 'X' && m[i-1][j] == 'X' && m[i][j-1] == 'X' && m[i-1][j-1] == 'X' && m[i+1][j-1] == 'X' && m[i-1][j+1] == 'X')
					m[i][j] = ' ';
				
				if(m[i][j] == ' ' && m[i+1][j] == ' ' && m[i][j+1] == ' ' && m[i+1][j+1] == ' ')
					m[i][j] = 'X';
	
				if(m[i][j] == 'X' && m[i+1][j] == ' ' && m[i][j+1] == ' ' && m[i+1][j+1] == 'X')
					m[i][j] = ' ';
				
				if(m[i][j] == ' ' && m[i+1][j] == 'X' && m[i][j+1] == 'X' && m[i+1][j+1] == ' ')
					m[i][j] = 'X';

			}
		}
	}
	
	public char[][] buildMaze(int size)
	{
		this.size = size;
		this.m = new char[size][size];
		MazeBuilder mazebuilder = new MazeBuilder();
		
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if(i == 0 || i == size-1 || j == 0 || j == size-1)
					m[i][j] = 'X';
			}
	
		}
		
		for (int i = 1; i < size-1; i++)
		{
			for (int j = 1; j < size-1; j++)
			{
				Random in = new Random();
				int rand = in.nextInt(2);
				
				if(i != 0 && i != size-1  && j != 0  && j != size-1)
				{
					if(rand == 0)
						m[i][j] = 'X';	
					else if(rand == 1)
						m[i][j] = ' ';
				}
			}

		}

		mazebuilder.correct(m,size);
		mazebuilder.putExit(m,size);
		mazebuilder.putHero(m,size);
		mazebuilder.putDragon(m,size);
		mazebuilder.putSword(m,size);

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
