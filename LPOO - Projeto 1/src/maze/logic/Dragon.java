package maze.logic;

public class Dragon {

	/****ATRIBUTOS****/
	
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
	}
	
	public void updatePosition(Position pos)
	{
		this.pos = pos ;
	}
	
	public char getLetter()
	{
		return this.letter;
	}
	
	public Position getPosition()
	{
		return this.pos;
	}
	
	
	
	
	
	
	
	
	

}
