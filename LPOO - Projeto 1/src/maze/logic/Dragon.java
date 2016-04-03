package maze.logic;

import java.util.Random;
import java.util.Scanner;

public class Dragon extends Creature{

	//---ATTRIBUTES
	private boolean asleep;

	public enum Mode {
		STILL, RANDOM, RANDOM_ASLEEP	// to be decided in the beggining of the game
	}
	
	private Mode mode;	
	
	private Random in = new Random();
	
	//---GET ATTRIBUTES FUNCTIONS
	
	public boolean getAsleep()
	{
		return this.asleep;
	}
	
	public Mode getMode()
	{
		return this.mode;
	}
	
	//---UPDATE ATTRIBUTES FUNCTIONS
	
	public void updateAsleep(boolean asleep)
	{
		this.asleep = asleep;
	}
	
	/**
	 * Default constructor in which the dragon is still.
	 */
	public Dragon()
	{
		this.updateLetter('D');
		this.updateVisible(true);
		this.updateDeathStatus(false); 
		
		this.mode = Mode.STILL;
		
		this.asleep = false;
	}
	
	/**
	 * Receives a char, given by the player ate the beginning of the game,
	 * to set dragon's mode.
	 * @param dragonMode Char given by player.
	 */
	public void setMode(char dragonMode)
	{
		switch(dragonMode)
		{
		case 'i': this.mode = Mode.STILL; break;			// idle
		case 'r': this.mode = Mode.RANDOM; break; 			// random
		case 's': this.mode = Mode.RANDOM_ASLEEP; break; 	// sleepy
		}
	}
	
	//REDUNTANT CONSTRCUTOR!
	/**
	 * If chosen mode is STILL, the dragon won't move. RANDOM means it'll move 
	 * randomly. RANDOM_ASLEEP means it'll move randomly, and may fall asleep as well.
	 * @param mode Mode that'll choose the dragon's behavior. 
	 */
	public Dragon(Mode mode)
	{
		this.updateLetter('D');
		this.updateVisible(true);
		this.updateDeathStatus(false); 
		
		this.mode = mode;
		
		this.asleep = false;
	}
	
	/**
	 * Updates dragons position according to its mode and its surroundings. 
	 * It does not print the dragon.
	 * @param maze Game's maze.
	 */
	public void newPosition(Maze maze)
	{
		this.updatePrePosition(new Position(this.getPosition()));
		
		switch(this.mode)
		{
		case STILL:
			return;
			
		case RANDOM:
			this.updatePosition(this.randomPosition(maze));
			return;
			
		case RANDOM_ASLEEP:
			this.updatePosition(this.randomPosition(maze));
			this.setRandomAsleep();
			return;
		}
	}
	
	/**
	 * Checks whether or not a new dragon position is valid in the current maze state.
	 * @param newPos Dragon's new position.
	 * @param maze Game's maze.
	 * @return True if the new position is valid. False otherwise.
	 */
	private boolean checkPosition(Position newPos, Maze maze)
	{
		char letter = maze.getMaze()[newPos.getY()][newPos.getX()];	// letter in the new position
		
		if (letter == ' ')	// if it is a path, it may walk there
			return true;
		else
			return false;
	}
	
	/**
	 * Randomly generates a new dragon position that is adjacent or equal to the previous one.
	 * @param maze Game's maze.
	 * @return The generated position.
	 */
	private Position randomPosition(Maze maze)
	{
		Position temp = new Position (this.getPosition());	// copy of current position
		
		int move = this.in.nextInt(5); // random number between 0 and 4
		
		switch(move)
		{
		case(0):
			break;	// don't move
		
		case(1):
			temp.moveUp();
			break;
			
		case(2):
			temp.moveDown();	
			break;
			
		case(3):
			temp.moveLeft();	
			break;
			
		case(4):
			temp.moveRight();	
			break;		
		}
		
		if(this.checkPosition(temp, maze))
			return temp;	// if the position is valid
		else
			return this.getPosition(); 	// won't move
	}
	
	/**
	 * Updates dragon's asleep state randomly, meaning he may wake up or fall asleep.
	 */
	private void setRandomAsleep()
	{
		this.asleep = this.in.nextBoolean();
		
		if(this.asleep)
			this.updateLetter(Character.toLowerCase(this.getLetter()));	// updates the letter to lower case if dragon is sleeping
		else
			this.updateLetter(Character.toUpperCase(this.getLetter()));   
	}

	//**MAIN**

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
