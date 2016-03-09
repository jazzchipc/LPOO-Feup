package maze.logic;

public class Sword {
	
	private char letter = 'E';
	private Position pos;
	protected boolean visible = true;
	
	public void updatePosition(Position pos)
	{
		this.pos = pos;
	}
	
	public void updateVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public void updateLetter(char letter)
	{
		this.letter = letter;
	}
	
	public Position getPosition()
	{
		return this.pos;
	}
	
	public char getLetter()
	{
		return this.letter;
	}
	
	public boolean getVisible()
	{
		if(visible)
			return true;
		else
			return false;
	}

}
