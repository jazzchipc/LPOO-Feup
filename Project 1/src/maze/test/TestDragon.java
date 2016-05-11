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

	@Test
	public void testDragonMode()
	{
		Game game1 = new Game();
		Dragon dragon1 = game1.getDragon();
		game1.initGame('i');
		
		Game game2 = new Game();
		Dragon dragon2 = game2.getDragon();
		game2.initGame('r');
		
		Game game3 = new Game();
		Dragon dragon3 = game3.getDragon();
		game3.initGame('s');
		
		assertEquals(dragon1.getMode(),Dragon.Mode.STILL);
		assertEquals(dragon2.getMode(),Dragon.Mode.RANDOM);
		assertEquals(dragon3.getMode(),Dragon.Mode.RANDOM_ASLEEP);
	}
}
