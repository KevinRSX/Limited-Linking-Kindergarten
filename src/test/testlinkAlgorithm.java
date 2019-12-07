package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import gui.Grid;
import linkAlgorithm.GridMatcher;

class testlinkAlgorithm {

	@Test
	//ox
	//xo
	void testMatching_01() {
		Grid[][] grids = new Grid[4][4];
		ImageIcon image1 = new ImageIcon(getClass().getResource("/images/pic1.png"));
		ImageIcon image2 = new ImageIcon(getClass().getResource("/images/pic2.png"));
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

}
