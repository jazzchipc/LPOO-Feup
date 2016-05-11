package maze.logic;

/**
 * 
 * Represents the sword in-game.
 *
 */
public class Sword {
	
	private char letter = 'E';
	private Position pos;
	protected boolean visible = true;
	
	/**
	 * Updates the Position of the Sword.
	 * @param pos New Position.
	 */
	public void updatePosition(Position pos)
	{
		this.pos = pos;
	}
	
	/**
	 * Updates the visibility status of the Sword.
	 * @param visible True if visible. False otherwise.
	 */
	public void updateVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	/**
	 * Updates the letter of the Sword.
	 * @param letter New Letter.
	 */
	public void updateLetter(char letter)
	{
		this.letter = letter;
	}
	
	/**
	 * 
	 * @return The Position of the Sword.
	 */
	public Position getPosition()
	{
		return this.pos;
	}
	
	/**
	 * 
	 * @return The Letter of the Sword.
	 */
	public char getLetter()
	{
		return this.letter;
	}
	
	/**
	 * 
	 * @return True if the Sword is Visible. False otherwise.
	 */
	public boolean getVisible()
	{
		if(visible)
			return true;
		else
			return false;
	}

}
