package maze.logic;
import java.util.Scanner;


public class Hero extends Creature{
	
	/****ATRIBUTOS****/
	
	
	private boolean armed = false;
	//private boolean deadDragon = false

	/****METHODS****/
	
	public Hero()
	{
		this.letter = 'H';
		this.dead = false;
		this.armed = false;
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
	
	
	public void moveHero(Position pos)
	{
		this.prePos = this.pos;
		this.pos = pos;
	}
	
	
}
