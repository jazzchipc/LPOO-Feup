package maze.cli;

import maze.logic.Game;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

public class Interface {

	//ATTRIBUTES
	
	Game game = new Game();
	Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		Interface i = new Interface();
		
		char dragonMode = i.getDragonMode();
		
		char move = i.makeAPlay();
	}	
	
	/**
	 * This function receives the dragon mode which the player desires to play against.
	 * @return It returns a character: 
	 * 'i' if the player chose the dragon to be idle; 
	 * 'r' if the dragon is woken up and moves randomly; 
	 * 's' if the dragon can fall asleep and still moves randomly.
	 */
	public char getDragonMode()
	{
		char ret;
		
		while (true)
		{
			System.out.println("Do you want to fight an [i]dle, [r]andom or [s]leepy dragon?");
			ret = this.in.next().charAt(0);	// just the first char
			
			if(!(ret == 'i' || ret == 'r' || ret == 's' || ret == 'I' || ret == 'R' || ret == 'S'))
			{
				System.out.println("Please, insert a valid option ('i', 'r' or 's').");
			}
			else
				break;
		}
		
		ret = Character.toLowerCase(ret);	// so it only returns lower case characters
		
		return ret;	
	}
	
	/**
	 * Prints the game board on the screen and asks the player for the next move.
	 * @return It returns a character:
	 * 'w' if the player wants to move up;
	 * 's' to move down;
	 * 'a' to go left;
	 * 'd' to go right;
	 */
	public char makeAPlay()
	{
		this.game.getMaze().printMaze();
		
		char move;
		
		while (true)
		{
			System.out.println("Move: ");
			move = this.in.next().charAt(0);	// just the first char
			
			if(!(move == 'w' || move == 'a' || move == 's' || move == 'd' 
					|| move == 'W' || move == 'A' || move == 'S' || move == 'D'))
			{
				System.out.println("Please, insert a valid option ('w', 'a', 's' or 'd').");
			}
			else
				break;
		}
		
		move = Character.toLowerCase(move);
		
		return move;
	}
	
	

}
