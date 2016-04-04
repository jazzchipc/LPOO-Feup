package maze.logic;

/**
 * 
 * This class represents a creature, that in this game is either a Hero or a Dragon.
 * 
 * It is used to separate the Sword (which is a still object) from living characters.
 *
 */
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
	
	/**
	 * @param letter The new letter of the Creature
	 */
	public void updateLetter(char letter)
	{
		this.letter = letter;
	}
	
	/**
	 * @param pos The new position of the Creature
	 */
	public void updatePosition(Position pos)
	{
		this.prePos = this.pos;
		this.pos = pos ;
	}
	
	/**
	 * @param prePos The new previous position of the Creature
	 */
	public void updatePrePosition(Position prePos)
	{
		this.prePos = prePos ;
	}
	
	/**
	 * @param dead The new death status of the Creature
	 */
	public void updateDeathStatus(boolean dead)
	{
		this.dead = dead;
	}
	
	/**
	 * @param visible The new visibility status of the Creature
	 */
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
