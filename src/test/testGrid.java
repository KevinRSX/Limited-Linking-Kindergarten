package test;


import javax.swing.Icon;
import javax.swing.ImageIcon;


import gui.Grid;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testGrid {
	private Icon icon1=new ImageIcon(("images/" + "pic1"+ ".png")),
icon2=new ImageIcon(("images/" + "pic2"+ ".png"));
	@Test
	void testCancel() {
		Grid grid=new Grid(icon1);
		grid.cancel();
		assertEquals(grid.getIcon(),null);
	}
	@Test
	void testCanPassThrough_1() {
		Grid grid=new Grid();
		assertEquals(true, grid.canPassThrough());
	}
	@Test
	void testCanPassThrough_2() {
		Grid grid=new Grid(icon1);
		assertEquals(false, grid.canPassThrough());
	}

	@Test
	void testSelect() {
		Grid grid=new Grid();
		assertEquals(false, grid.isSelected());
		grid.select();
		assertEquals(true, grid.isSelected());
	}
	@Test
	void testDeselect() {
		Grid grid=new Grid();
		grid.select();
		grid.deselect();
		assertEquals(false, grid.isSelected());
	}
	
	@Test
	void testSameType_1() {
		Grid grid1=new Grid(icon1),grid2=new Grid(icon2);
		assertEquals(false, grid1.sameType(grid2));
	}
	@Test
	void testSameType_2() {
		Grid grid1=new Grid(icon1),grid2=new Grid(icon1);
		assertEquals(true, grid1.sameType(grid2));
	}
	@Test
	void testSameType_3() {
		Grid grid1=new Grid(null),grid2=new Grid(icon1);
		assertEquals(false, grid1.sameType(grid2));
	}
	@Test
	void testSameType_4() {
		Grid grid1=new Grid(null),grid2=new Grid(null);
		assertEquals(true, grid1.sameType(grid2));
	}
}
