package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestHero {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	@Test

	public void testMoveHeroToFreeCell() {
		Game game = new Game(m1);
		
		Position p1 = new Position(1,3);
		Position p2 = new Position(1,2);
		
		assertEquals(game.getMaze().charAt(p1), game.getMaze().findPos('H'));
//		game.moveHeroLeft();
//		assertEquals(maze.charAt(p2), maze.findPos('H'));
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
