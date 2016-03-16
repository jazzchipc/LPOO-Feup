//package maze.test;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//import maze.logic.*;
//
//public class TestHero {
//
//	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
//                    {'X', ' ', ' ', 'H', 'S'},
//                    {'X', ' ', 'X', ' ', 'X'},
//                    {'X', 'E', ' ', 'D', 'X'},
//                    {'X', 'X', 'X', 'X', 'X'}};
//@Test
//
//	public void testMoveHeroToFreeCell() {
//		Maze maze = new Maze(m1);
//		Position p1 = new Position();
//		p1.x = 1;
//		p1.y = 3;
//		Position p2 = new Position();
//		p2.x = 1;
//		p2.y = 2;
//		assertEquals(maze.charAt(p1), maze.findPos('H'));
//		maze.moveHeroLeft();
//		assertEquals(maze.charAt(p2), maze.findPos('H'));
//	}
//@Test
//	public void testHeroDies() {
//		Maze maze = new Maze(m1);
//		Game game = new Game();
//		assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
//		maze.moveHeroDown();
//		assertEquals(MazeStatus.HeroDied, maze.getStatus());
//	}
//}
