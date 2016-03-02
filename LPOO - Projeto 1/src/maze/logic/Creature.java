package maze.logic;

public class Creature {

	/****ATRIBUTOS****/
	protected boolean dead = false;			 
	protected Position pos = new Position();
	protected Position prePos = new Position();
	protected char letter;					
	protected boolean visible;	
	

	
	/****METODOS****/
	



	public char getLetter()
	{
		return this.letter;
	}
	
	public Position getPosition()
	{
		return this.pos;
	}
	
	public Position getPrePos()
	{
		return this.prePos;
	}
	
	public boolean getDeathStatus()
	{
		if (dead)
			return true;
		else
			return false;
	}
	public void updateLetter(char letter)
	{
		this.letter = letter;
	}
	

	public void updatePosition(Position pos)
	{
		this.pos = pos ;
	}
	
	public void updatePrePosition(Position prePos)
	{
		this.prePos = prePos ;
	}
	
	public void updateDeathStatus(boolean dead)
	{
		this.dead = dead;
	}
	
}
