package maze.logic;

public class Dragon {

	/****ATRIBUTOS****/
	private boolean dead = false;			// Dragão é inicializado vivo 
	private Position pos = new Position();	// posição do Dragão no labirinto
	private char letter;					// letra que representa o Dragão
	private boolean visible;				// determina se o dragão está visível ou não no labirinto
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Dragon()
	{
		this.letter = 'D';
		this.visible = true;
		this.dead = false;
	}
	
	public void updatePosition(Position pos)
	{
		this.pos = pos ;
	}
	
	public void updateDeathStatus(boolean dead)
	{
		this.dead = dead;
	}
	
	public char getLetter()
	{
		return this.letter;
	}
	
	public Position getPosition()
	{
		return this.pos;
	}
	
	public boolean getDeathStatus()
	{
		if (dead)
			return true;
		else
			return false;
	}
	
	
	
	
	
	
	

}
