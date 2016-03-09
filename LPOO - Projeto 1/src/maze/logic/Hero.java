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
	
	private int checkPosition(Position newPos, Maze maze)
	{
		char letter = maze.getMaze()[newPos.getX()][newPos.getY()];	// letter in the new position
		
		switch
	}
	
	
}
