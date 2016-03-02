package maze.logic;
import java.util.Scanner;


public class Hero {
	
	/****ATRIBUTOS****/
	
	private boolean armed = false;
	private boolean dead = false;
	private char letter;
	private Position pos = new Position();
	private Position prePos = new Position();
	private Dragon dragon = new Dragon();
	
	/****METHODS****/
	
	/**----Those which obtain attributes**/
	
	public boolean getArmed()
	{
		return this.armed;
	}
	
	public char getLetter()
	{
		return this.letter;
	}
	
	public Position getPos()
	{
		return this.pos;
	}
	
	public Position getPrePos()
	{
		return this.prePos;
	}
	
	/**----Those which change attribute values**/
	
	public void updatePosition (Position pos)
	{
		this.pos = pos;
	}
	
	public void updateArmed(boolean bool)
	{
		armed = bool;
	}
	
	public void updateLetra()
	{
		if(this.armed == false)
			this.letter = 'H';
		else
			this.letter = 'A';
	}
	
	public void getNewPosition()
	{
		this.prePos = this.pos;
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
	
	public void analiseNewPos(char letra)
	{
		if(!armed)
		{
			switch(letra)
			{
			case 'X': 
				this.pos = this.prePos;
				break;
			case '0':
				break;
			case 'E': 
				this.armed = true;
				break;
			case 'D':
				this.dead = true;
				break;
			case 'S':
				this.pos = this.prePos;
				break;				
			}
		}
		else
		{
			switch(letra)
			{
			case 'X': 
				this.pos = this.prePos;
				break;
			case ' ':
				break;			
			case 'D':
				dragon.updateDeathStatus(true);
				break;
			case 'S':
				if(dragon.getDeathStatus())
					break;
				else
					{
					this.pos = this.prePos;
					break;
					}
			}
		}
		

	}
	
	public void moveHeroi(Position pos)
	{
		this.prePos = this.pos;
		this.pos = pos;
	}
	
	
}
