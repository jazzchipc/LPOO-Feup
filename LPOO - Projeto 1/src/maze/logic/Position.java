package maze.logic;

public class Position {
	
	//---ATTRIUBTES
	private int x,y;
	
	//---GET ATTRIBUTES FUNCTIONS
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	//---UPDATE ATTRIBUTES FUNCTIONS
	
	public void updateX(int x)
	{
		this.x = x;
	}
	
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
	
	
	//---MOVE FUNCTIONS
	
	public void moveUp()
	{
		this.y = this.y - 1;
	}
	
	public void moveDown()
	{
		this.y = this.y + 1;
	}
	
	public void moveLeft()
	{
		this.x = this.x - 1;
	}
	
	public void moveRight()
	{
		this.x = this.x + 1;
	}
}
