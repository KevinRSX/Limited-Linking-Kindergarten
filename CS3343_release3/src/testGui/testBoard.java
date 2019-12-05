package testGui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import gui.Board;
import gui.RowAndColumn;

class testBoard {

	@Test
	void testUtilities() {
		// getIndexFromLabel
		Board board = new Board(4);
		assertEquals(2, board.getIndexFromLabel(9).getRow());
		assertEquals(1, board.getIndexFromLabel(9).getCol());
		
		// canPassThrough
		assertEquals(true, board.canPassThrough(0, 3));
		assertEquals(false, board.canPassThrough(1, 2));
	}
	
	@Test
	void globalSolvable1() {
		class BoardStub extends Board {
			public BoardStub(int GameSize) {
				super(GameSize);
			}
			
			public void generateTestCases() {
				ImageIcon image1 = new ImageIcon(getClass().getResource("/images/fruit_1.jpg"));
				setGridIcon(1, 1, image1);
				setGridIcon(1, 2, image1);
				setGridIcon(2, 1, image1);
				setGridIcon(2, 2, image1);
			}
		}
		BoardStub board = new BoardStub(4);
		board.generateTestCases();
		assertEquals(true, board.isSolvable());
	}
	
	@Test
	void globalSolvable2() {
		class BoardStub extends Board {
			public BoardStub(int GameSize) {
				super(GameSize);
			}
			
			public void generateTestCases() {
				ImageIcon image1 = new ImageIcon(getClass().getResource("/images/fruit_1.jpg"));
				ImageIcon image2 = new ImageIcon(getClass().getResource("/images/fruit_2.jpg"));
				setGridIcon(1, 1, image1);
				setGridIcon(1, 2, image2);
				setGridIcon(2, 1, image2);
				setGridIcon(2, 2, image1);
			}
		}
		BoardStub board = new BoardStub(4);
		board.generateTestCases();
		assertEquals(false, board.isSolvable());
	}
	
	@Test
	void GridSolvableInTurn() {
		/* XXXX
		 * XA X
		 * XXAX
		 */
		class BoardStub extends Board {
			public BoardStub(int GameSize) {
				super(GameSize);
			}
			
			public void generateTestCases() {
				ImageIcon image1 = new ImageIcon(getClass().getResource("/images/fruit_1.jpg"));
				ImageIcon image2 = new ImageIcon(getClass().getResource("/images/fruit_2.jpg"));
				for (int i = 1; i <= 3; i++) {
					for (int j = 1; j <= 3; j++) {
						setGridIcon(i, j, image1);
					}
				}
				setGridIcon(1, 2, image2);
				setGridIcon(2, 3, image2);
				setGridIcon(1, 3, null);
			}
		}
		BoardStub board = new BoardStub(9);
		board.generateTestCases();
		assertEquals(true, board.isSolvable());
	}
	
	@Test
	void GridSolvableOutTurn1() {
		/*  AXA
		 *  XXX
		 *  XXX
		 */
		class BoardStub extends Board {
			public BoardStub(int GameSize) {
				super(GameSize);
			}
			
			public void generateTestCases() {
				ImageIcon image1 = new ImageIcon(getClass().getResource("/images/fruit_1.jpg"));
				ImageIcon image2 = new ImageIcon(getClass().getResource("/images/fruit_2.jpg"));
				for (int i = 1; i <= 3; i++) {
					for (int j = 1; j <= 3; j++) {
						setGridIcon(i, j, image1);
					}
				}
				setGridIcon(1, 1, image2);
				setGridIcon(1, 3, image2);
			}
		}
		BoardStub board = new BoardStub(9);
		board.generateTestCases();
		assertEquals(true, board.isSolvable());
	}
	
	@Test
	void GridSolvableOutTurn2() {
		/*  AX X
		 *  XX X
		 *  XXAX
		 */
		class BoardStub extends Board {
			public BoardStub(int GameSize) {
				super(GameSize);
			}
			
			public void generateTestCases() {
				ImageIcon image1 = new ImageIcon(getClass().getResource("/images/fruit_1.jpg"));
				ImageIcon image2 = new ImageIcon(getClass().getResource("/images/fruit_2.jpg"));
				for (int i = 1; i <= 3; i++) {
					for (int j = 1; j <= 3; j++) {
						setGridIcon(i, j, image1);
					}
				}
				setGridIcon(1, 1, image2);
				setGridIcon(3, 3, image2);
				setGridIcon(1, 3, null);
				setGridIcon(2, 3, null);
			}
		}
		BoardStub board = new BoardStub(9);
		board.generateTestCases();
		assertEquals(true, board.isSolvable());
	}

}
