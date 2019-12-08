package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import gui.Grid;
import linkAlgorithm.GridMatcher;

class testlinkAlgorithm {

	
	//ox
	//xo
	@Test 
	void testMatching_01() {
		Grid[][] grids = new Grid[4][4];
		ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic1.png"));
		ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic2.png"));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				grids[i][j] = new Grid();
				grids[i][j].setIcon(null);
			}
		}
		grids[1][1].setIcon(image1);
		grids[1][2].setIcon(image2);
		grids[2][1].setIcon(image2);
		grids[2][2].setIcon(image1);
		boolean actual = GridMatcher.isCancellable(grids, new Point(1, 1), new Point(2, 2));
		assertEquals(false, actual);
		actual = GridMatcher.isCancellable(grids, new Point(1, 2), new Point(2, 2));
		assertEquals(false, actual);
	}
	
	//xxx
	//o o
	//xxx
	@Test
	void testMatching_02() {
		Grid[][] grids = new Grid[5][5];
		ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic1.png"));
		ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic2.png"));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grids[i][j] = new Grid();
				grids[i][j].setIcon(null);
			}
		}
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				grids[i][j].setIcon(image1);
			}
		}
		grids[2][1].setIcon(image2);
		grids[2][2].setIcon(null);
		grids[2][3].setIcon(image2);
		boolean actual = GridMatcher.isCancellable(grids, new Point(2, 1), new Point(2, 3));
		assertEquals(true, actual);
	}
	
	
	// ox
	//oxx
	//xxx
	@Test
	void testMatching_03() {
		Grid[][] grids = new Grid[5][5];
		ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic1.png"));
		ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic2.png"));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grids[i][j] = new Grid();
				grids[i][j].setIcon(null);
			}
		}
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				grids[i][j].setIcon(image1);
			}
		}
		grids[1][1].setIcon(null);
		grids[1][2].setIcon(image2);
		grids[2][1].setIcon(image2);
		ArrayList<Point> actual = GridMatcher.matchingPath(grids, new Point(1, 2), new Point(2, 1));
		assertEquals(new Point(1, 1), actual.get(0));

	}
	
	//oxo
	//xxx
	//xxx
	@Test
	void testMatching_04() {
		Grid[][] grids = new Grid[5][5];
		ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic1.png"));
		ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic2.png"));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grids[i][j] = new Grid();
				grids[i][j].setIcon(null);
			}
		}
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				grids[i][j].setIcon(image1);
			}
		}
		grids[1][1].setIcon(image2);
		grids[1][3].setIcon(image2);
		ArrayList<Point> actual = GridMatcher.matchingPath(grids, new Point(1, 1), new Point(1, 3));
		assertEquals(new Point(0, 1), actual.get(0));
		assertEquals(new Point(0, 3), actual.get(1));
	}
	
	//xxo
	//xxx
	//xxo
	@Test
	void testMatching_05() {
		Grid[][] grids = new Grid[5][5];
		ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic1.png"));
		ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic2.png"));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grids[i][j] = new Grid();
				grids[i][j].setIcon(null);
			}
		}
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				grids[i][j].setIcon(image1);
			}
		}
		grids[1][3].setIcon(image2);
		grids[3][3].setIcon(image2);
		ArrayList<Point> actual = GridMatcher.matchingPath(grids, new Point(1, 3), new Point(3, 3));
		assertEquals(new Point(1, 4), actual.get(0));
		assertEquals(new Point(3, 4), actual.get(1));
	}
	
	// special cases
	@Test
	void testMatching_06() {
		Grid[][] grids = new Grid[5][5];
		ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic1.png"));
		ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/pic2.png"));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) { 
				grids[i][j] = new Grid();
				grids[i][j].setIcon(null);
			}
		}
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				grids[i][j].setIcon(image1);
			}
		}
		ArrayList<Point> actual_path = GridMatcher.matchingPath(grids, new Point(1, 4), new Point(1, 4));
		boolean actual_judge = GridMatcher.isCancellable(grids, new Point(1, 4), new Point(1, 4));
		assertEquals(null, actual_path);
		assertEquals(false, actual_judge);
		
		grids[1][3].setIcon(null);
		grids[1][2].setIcon(image2);
		actual_path = GridMatcher.matchingPath(grids, new Point(1, 2), new Point(2, 3));
		actual_judge = GridMatcher.isCancellable(grids, new Point(1, 2), new Point(2, 3));
		assertEquals(null, actual_path);
		assertEquals(false, actual_judge);
		
		actual_path = GridMatcher.matchingPath(grids, new Point(1, 3), new Point(1, 4));
		actual_judge = GridMatcher.isCancellable(grids, new Point(1, 3), new Point(1, 4));
		assertEquals(null, actual_path);
		assertEquals(false, actual_judge);
	}
}
