package maze.logic;

public class Sword {
	
	private char letter = 'E';
	private Position pos;
	
	public void updatePosition(Position pos)
	{
		this.pos = pos;
	}
	
	public Position getPos()
	{
		return this.pos;
	}
	
	public char getLetter()
	{
		return this.letter;
	}

}
