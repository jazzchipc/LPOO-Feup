package maze.logic;
import java.util.Scanner;


public class Hero {
	
	/****ATRIBUTOS****/
	
	public boolean armado = false;
	public char letra;
	public Position pos = new Position();
	public Position posAnt = new Position();
	
	/****M�TODOS****/
	
	public void updateLetra()
	{
		if(this.armado == false)
			this.letra = 'H';
		else
			this.letra = 'A';
	}
	
	public static void main(String[] args) 
	{
		Maze lab = new Maze();
		lab.printLab();
	}
	
	public void getPos()
	{
		this.posAnt = this.pos;
		Scanner in = new Scanner(System.in);
		char move;
		
		System.out.println("Move: ");
		move = in.next().charAt(0);
		
		if (!(move == 'w' || move == 'a'|| move == 's'|| move == 'd'))
			System.out.println("Error!");
		
		switch (move)
		{
		case 'w': this.pos.x = this.pos.x - 1;
		break;
		case 'a': this.pos.y = this.pos.y - 1;
		break;
		case 's': this.pos.x = this.pos.x + 1;
		break;
		case 'd': this.pos.y = this.pos.y + 1;
		break;
		
		}
		
	}
	
	public void newPos(char letra)
	{
		switch(letra)
		{
		case 'X': this.pos = this.posAnt;
		break;
		case ' ':break;
		case 'D':
		}
		
	}
	
	public void moveHeroi(Position pos)
	{
		this.posAnt = this.pos;
		this.pos = pos;
	}
	
	
}
