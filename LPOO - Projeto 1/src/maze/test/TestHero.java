package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestHero {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', '0', '0', 'H', 'S'},
					{'X', '0', 'X', '0', 'X'},
					{'X', 'E', '0', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testValidCoords()
	{
		Game game = new Game(m1);
		game.initGame('i'); 	// idle dragon
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		
		assertTrue(hero.getPosition().getX() > 0);
		assertTrue(hero.getPosition().getY() > 0);
		assertTrue(hero.getPosition().getX() < maze.getSize()-1);
		assertTrue(hero.getPosition().getY() < maze.getSize()-1);
		
	}
	
	@Test
	public void testMoveHeroToFreeCell() {
		Game game = new Game(m1);
		game.initGame('i'); 	// idle dragon
		Maze maze = game.getMaze();
		
		Position p1 = new Position(3,1);
		Position p2 = new Position(2,1);
		
		assertEquals(maze.charAt(p1), 'H');
		game.moveHeroLeft();
		assertEquals(maze.charAt(p2), 'H');
	}
	
	@Test
	
	public void testFailToMoveAgainstWall()
	{
		Game game = new Game(m1);
		game.initGame('i'); 	// idle dragon
		Maze maze = game.getMaze();
		
		Position p1 = new Position(3,1);
	
		assertEquals(maze.charAt(p1), 'H');
		game.moveHeroUp();
		assertEquals(maze.charAt(p1), 'H');
	}
	
	@Test
	public void testHeroGetSword()
	{
		Game game = new Game(m1);
		game.initGame('i');
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		
		Position p1 = new Position(1,2);
		Position p2 = new Position(1,3);
		game.updateHeroPosition(p1);
	
		
		assertEquals(maze.charAt(p1), 'H');
		assertEquals(maze.charAt(p2), 'E');
		assertFalse(hero.getArmed());
		game.moveHeroDown();
		assertEquals(maze.charAt(p2), 'A');
		assertTrue(hero.getArmed());
	
	}
	
	@Test
	public void testUnarmedHeroDies()
	{
		Game game = new Game(m1);
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		Dragon dragon = game.getDragon();
		
		Position p1 = new Position(3,1);
		Position p2 = new Position(3,3);

		
		assertEquals(maze.charAt(p1), 'H');
		assertEquals(maze.charAt(p2), 'D');
		game.moveHeroDown();
		assertTrue(hero.getDeathStatus());
		assertFalse(dragon.getDeathStatus());
	}
	
	@Test
	public void testHeroKillsDragon()
	{
		Game game = new Game(m1);
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		Dragon dragon = game.getDragon();
		
		Position p1 = new Position(1,2);
		Position p2 = new Position(1,3);
		Position p3 = new Position(3,3);
		game.updateHeroPosition(p1);
		
		assertEquals(maze.charAt(p1), 'H');
		assertEquals(maze.charAt(p2), 'E');
		assertEquals(maze.charAt(p3), 'D');
		assertFalse(hero.getArmed());
		game.moveHeroDown();
		assertEquals(maze.charAt(p1), 'A');
		assertTrue(hero.getArmed());
		game.moveHeroRight();
		assertFalse(hero.getDeathStatus());
		assertTrue(dragon.getDeathStatus());
		assertEquals(maze.charAt(p3), ' ');
		
	}
	
	@Test
	public void testVictory()
	{
		Game game = new Game(m1);
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		Dragon dragon = game.getDragon();
		
		Position p1 = new Position(1,2);
		Position p2 = new Position(1,3);
		
		game.updateHeroPosition(p1);
		assertEquals(maze.charAt(p1), 'H');
		assertFalse(hero.getArmed());
		game.moveHeroDown();
		assertEquals(maze.charAt(p2), 'A');
		assertTrue(hero.getArmed());
		game.moveHeroRight();
		assertTrue(dragon.getDeathStatus());
		assertTrue(game.getExit());
		game.moveHeroRight();
		game.moveHeroUp();
		game.moveHeroRight();
		//assertEquals(game.getEnd(),!END_NOT);
		
	}
	
	@Test
	public void testExitUnarmed()
	{
		Game game = new Game(m1);
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		Dragon dragon = game.getDragon();
		
		Position p1 = new Position(3,1);
		
		assertEquals(maze.charAt(p1), 'H');
		assertFalse(hero.getArmed());
		game.moveHeroRight();
		//verificar que nao funcionou;
	}
	
	@Test
	public void testExitAliveDragon()
	{
		Game game = new Game(m1);
		Maze maze = game.getMaze();
		Hero hero = game.getHero();
		Dragon dragon = game.getDragon();
		
		
	}
//	@Test
//	public void testHeroDies() {
//		Maze maze = new Maze(m1);
//		Game game = new Game();
//		assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
//		maze.moveHeroDown();
//		assertEquals(MazeStatus.HeroDied, maze.getStatus());
//	}
}