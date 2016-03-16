package maze.logic;

public class Creature {

	//ATRIBUTES
	private boolean dead;			 
	private char letter;					
	private boolean visible;	
	
	private Position pos = new Position();
	private Position prePos = new Position();
	
	// ---- READ FUNCTIONS BEGIN
	
	/**
	 * 
	 * @return The letter of the creature.
	 */
	public char getLetter()
	{
		return this.letter;
	}
	
	/**
	 * 
	 * @return	The position of the creature.
	 */
	public Position getPosition()
	{
		return this.pos;
	}
	
	/**
	 * 
	 * @return The previous position of the creature.
	 */
	public Position getPrePosition()
	{
		return this.prePos;
	}
	
	/**
	 * 
	 * @return True if the creature is dead. False otherwise.
	 */
	public boolean getDeathStatus()
	{
		return dead;
	}
	
	/**
	 * 
	 * @return	True if the creature is visible on the board. False otherwise.
	 */
	public boolean getVisible()
	{
		if(visible)
			return true;
		else
			return false;
	}
	
	// ---- READ FUNCTIONS END
	
	// ---- UPDATE FUNCTIONS BEGIN
	
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
	
	public void updateVisible(boolean visible)
	{
		this.visible = visible;
	}

	// ---- UPDATE FUNCTIONS END
	
	/**
	 * Kills a creature (hero or dragon) and updates its attributes accordingly.
	 */
	public void killCreature()
	{
		this.dead = true;
		this.visible = false;
	}
}
