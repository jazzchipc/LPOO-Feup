package maze.logic;

public class Hero extends Creature{
	
	//---ATTRIBUTES
	private boolean armed;
	
	//---GET ATTRIBUTES FUNCTIONS
	public boolean getArmed()
	{
		return this.armed;
	}

	//---UPDATE ATTRIBUTES FUNCTIONS

	public void updateArmed(boolean bool)
	{
		armed = bool;
		
		if(armed)
			this.updateLetter('A');
		else
			this.updateLetter('H');
	}


	//METHODS
	
	public Hero()
	{
		this.updateLetter('H');
		this.updateDeathStatus(false);
		this.updateVisible(true);
		
		this.armed = false;
	}
	
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
	private boolean checkPosition(Position newPos, Maze maze)
	{
		char letter = maze.getMaze()[newPos.getX()][newPos.getY()];	// letter in the new position
		
		switch(letter)
		{
		case '0':
			return true;
			
		case 'S':
			return true;
			
		default: return false;
		}
	}
	
	/**
	 * Updates a hero's position, if the position is valid.
	 * @param maze Game's maze.
	 * @param command Command given to hero.
	 */
	public void newPosition(Maze maze, char command)
	{
		Position newPos = this.calculateMove(command);
		
		if(this.checkPosition(newPos, maze))
		{
			this.updatePosition(newPos);
		}
	}
	
	public void pickSword()
	{
		this.armed = true;
		this.updateLetter('A');
	}
	
}
