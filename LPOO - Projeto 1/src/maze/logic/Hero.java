package maze.logic;

public class Hero extends Creature{
	
	//---ATTRIBUTES
	private boolean armed;
	
	//---GET ATTRIBUTES FUNCTIONS
	/**
	 * 
	 * @return True if Hero is armed. False otherwise.
	 */
	public boolean getArmed()
	{
		return this.armed;
	}

	//---UPDATE ATTRIBUTES FUNCTIONS

	/**
	 * 
	 * @param bool True if Hero has picked up the Sword. False otherwise.
	 */
	public void updateArmed(boolean bool)
	{
		armed = bool;
		
		if(armed)
			this.updateLetter('A');
		else
			this.updateLetter('H');
	}


	//METHODS
	/**
	 * Default Constructor in which the Hero is not armed.
	 */
	public Hero()
	{
		this.updateLetter('H');
		this.updateDeathStatus(false);
		this.updateVisible(true);
		
		this.armed = false;
	}
	
	/**
	 * 
	 * @param move w if Hero is to move Up, s for Down, d for Right and a for Left.
	 * @return The new position of the Hero.
	 */
	private Position calculateMove(char move)
	{
		Position temp = new Position (this.getPosition());	// copy of current hero position
		
		switch(move)
		{
		case 'w':
			temp.moveUp();
			break;
			
		case 's':
			temp.moveDown();
			break;
			
		case 'd':
			temp.moveRight();
			break;
			
		case 'a':
			temp.moveLeft();
			break;
		}
		
		return temp;
	}
	
	/**
	 * Checks hero's new position and returns a boolean that says if it's valid.
	 * @param newPos Hero's new position.
	 * @param maze Game's maze.
	 * @return False if hero can't occupy case. True if it can.
	 */
	private boolean checkPosition(Position newPos, Maze maze, boolean exit)
	{
		char letter = maze.getMaze()[newPos.getY()][newPos.getX()];	// letter in the new position
		
		switch(letter)
		{
		case ' ':
			return true;
			
		case 'S':
			if(exit)
				return true;
			else
				return false;
		case 'E':
			this.pickSword();
			return true;
			
		default: return false;
		}
	}
	
	/**
	 * Updates a hero's position, if the position is valid.
	 * @param maze Game's maze.
	 * @param command Command given to hero.
	 */
	public void newPosition(Maze maze, char command, boolean exit)
	{
		this.updatePrePosition(new Position(this.getPosition()));
		
		Position newPos = this.calculateMove(command);
		
		if(this.checkPosition(newPos, maze, exit))
		{
			this.updatePosition(newPos);
		}
	}
	
	/**
	 * The Hero picks up the Sword.
	 */
	public void pickSword()
	{
		this.updateArmed(true);
	}
	
}
