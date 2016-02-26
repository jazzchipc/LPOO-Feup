package maze.logic;

public class Dragon {

	/****ATRIBUTOS****/
	
	private Position pos = new Position();	// posição do Dragão no labirinto
	private char letter;					// letra que representa o Dragão
	private boolean visible;				// determina se o dragão está visível ou não no labirinto
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Dragon(Position pos)
	{
		this.pos = pos;
		this.letter = 'D';
		this.visible = true;
	}
	
	public Position getPosition()
	{
		return this.pos;
	}
	
	
	
	
	
	
	
	
	

}
