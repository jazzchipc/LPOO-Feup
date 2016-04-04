package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;
import maze.logic.*;

public class TestDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testValidCoords() {
		Game game = new Game(m1);
		game.initGame('i'); 	// idle dragon
		Maze maze = game.getMaze();
		Dragon dragon = game.getDragon();
		
		assertTrue(dragon.getPosition().getX() > 0);
		assertTrue(dragon.getPosition().getY() > 0);
		assertTrue(dragon.getPosition().getX() < maze.getSize()-1);
		assertTrue(dragon.getPosition().getY() < maze.getSize()-1);
		
	}

	@Test
	public void testMoveDragonToFreeCell()
	{
		Game game = new Game(m1);
		game.initGame('r'); 	// random dragon
		Maze maze = game.getMaze();
		Dragon dragon = game.getDragon();
		
		Position p1 = new Position(3,3);
		
		assertEquals(maze.charAt(p1), 'D');
	}
	
	
}
