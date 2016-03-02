package maze.logic;

public class Dragon {

	/****ATRIBUTOS****/
	private boolean dead = false;			// Drag�o � inicializado vivo 
	private Position pos = new Position();	// posi��o do Drag�o no labirinto
	private char letter;					// letra que representa o Drag�o
	private boolean visible;				// determina se o drag�o est� vis�vel ou n�o no labirinto
	
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
