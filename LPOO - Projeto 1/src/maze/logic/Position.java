package maze.logic;

/**
 * 
 * Class created to make it easier to locate and spawn elements in the maze.
 *
 */
public class Position {
	
	//---ATTRIUBTES
	private int x,y;
	
	//---GET ATTRIBUTES FUNCTIONS
	/**
	 * 
	 * @return The x value of the position.
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * 
	 * @return The y value of the position.
	 */
	public int getY()
	{
		return this.y;
	}
	
	//---UPDATE ATTRIBUTES FUNCTIONS
	
	/**
	 * Updates the value of x.
	 * @param x New x value.
	 */
	public void updateX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Updates the value of y.
	 * @param y New y value.
	 */
	public void updateY(int y)
	{
		this.y = y;
	}
	
	//---METHODS
	
	/**
	 * Default constructor. Returns a position (0,0).
	 */
	public Position()
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor that creates a position with given coordinates, being that the (0,0) point is the
	 * top left corner.
	 * @param x The horizontal coordinate.
	 * @param y The vertical coordinate.
	 */
	public Position (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Position (Position toClone)
	{
		this.x = toClone.x;
		this.y = toClone.y;
	}
	
	public double distanceTo(Position pos)
	{
		return (Math.sqrt(Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2)));	// sqrt(x^2 + y^2)
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position))
			return false;
		
		if (obj == this)
			return true; 	// same object
		
		Position rhs = (Position) obj;
		
		if(rhs.x == this.x && rhs.y == this.y)
			return true;
		else
			return false;

	}
	
	//---MOVE FUNCTIONS

	/**
	 * Moves the Position Up.
	 */
	public void moveUp()
	{
		this.y = this.y - 1;
	}
	
	/**
	 * Moves the Position Down.
	 */
	public void moveDown()
	{
		this.y = this.y + 1;
	}
	
	/**
	 * Moves the Position to the Left.
	 */
	public void moveLeft()
	{
		this.x = this.x - 1;
	}
	
	/**
	 * Moves the Position to the Right.
	 */
	public void moveRight()
	{
		this.x = this.x + 1;
	}
}
