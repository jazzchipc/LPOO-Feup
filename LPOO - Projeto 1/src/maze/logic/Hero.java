package maze.logic;

public class Hero extends Creature{
	
	/****ATRIBUTOS****/	
	private boolean armed;
	
	//private boolean deadDragon = false

	/****METHODS****/
	
	public Hero()
	{
		this.letter = 'H';
		this.dead = false;
		this.armed = false;
		this.visible = true;
		
		this.pos = pos;
	}
	
	/**----Those which obtain attributes**/
	
	public boolean getArmed()
	{
		return this.armed;
	}
	
	/**----Those which change attribute values**/
	
	
	public void updateArmed(boolean bool)
	{
		armed = bool;
	}
}
