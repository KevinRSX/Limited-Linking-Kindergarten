package test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Board;
import gui.GamePage;
import gui.Grid;
import gui.MainPage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testBoard {
	private Icon icon(int i) {
		return new ImageIcon(("images/" + "pic"+i+".png"));
	}
	private class GamePageStub extends GamePage{
		protected Board jpanel;
		public GamePageStub(int gamesize, String username, JFrame f) {
			super(gamesize, username, f);
		}
		@Override
		public void paint(Graphics g) {
		   	super.paint(g);
		    	Graphics2D g2 = (Graphics2D)g;
		    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
		    	g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, 
		     	BasicStroke.JOIN_ROUND, 1f));
	        	g2.setColor(Color.CYAN);
		    	jpanel.showPath(g2, getInsets());
		}
		@Override
		public boolean stop() {return true;}
		
		
	}
	
	@Test
	void testGetGameSize() {
		Board b=new Board(10);
		assertEquals(10,b.getGameSize());
	}
	@Test
	void testClearBorders() {
		Board b=new Board(10);
		b.clearBorders();		
	}
	@Test
	void testGetIndexFromGrid() {
		Board b=new Board(10);
		b.initMap();
		b.shuffle();
		for(int i=1;i<9;i++) {
			JLabel g=new Grid(icon(i));
			b.getIndexFromGrid(g);
		}	
		b.getIndexFromGrid(b.getGrid(1,1));
	}
	@Test
	void testGetIndexFromLabel() {
		Board b=new Board(10);
		Point p=b.getIndexFromLabel(1);	
		assertEquals(p,new Point(0,1));
	}
	@Test
	void testInitMap() {
		Board b=new Board(9);
		b.initMap();
	}
	
	@Test
	void testIsFinished_1() {
		Board b=new Board(10);
		assertEquals(false,b.isFinished());
	}
	@Test
	void testIsFinished_2() {
		Board b=new Board(10);

		for(int i=1;i<9;i++)
			for(int j=1;j<9;j++)
			b.getGrid(i, j).cancel();
		assertEquals(true,b.isFinished());
	}
	@Test
	void testRemaingNum() {
		Board b=new Board(4);
		assertEquals(4,b.remainingNum());
		b.getGrid(1,1).cancel();
		assertEquals(3,b.remainingNum());
	}
	@Test
	void testIsSolvable_showGame() {
		int GameSize=4;
		Board b=new Board(GameSize);
		for(int i=0;i<40;i++)
			b=new Board(GameSize);
	}
	@Test 
	void testShowHint_1() {
		Board b=new Board(4);
		b.showHint();
		for(int i=1;i<3;i++) 
			for(int j=1;j<3;j++){
				Grid a=b.getGrid(i, j);
				a=new Grid(icon(i+j*3));
		}
	}
	@Test
	void testShuffle_1() {
		int GameSize=4;
		GamePageStub gp=new GamePageStub(GameSize,"1",MainPage.getInstance());
		
		Board b=new Board(GameSize,gp);
		while(!b.getGrid(1, 1).sameType(b.getGrid(1, 2)))
			b=new Board(GameSize,gp);

		MouseEvent e1=null,e2=null;
		Grid a1=null,a2=null;
		
		e1=new MouseEvent(b.getGrid(1, 1), 0, 0, 0, 0, 0, 0, false);
		e2=new MouseEvent(b.getGrid(1, 2), 0, 0, 0, 0, 0, 0, false);
		a1=b.getGrid(1, 1);
		a2=b.getGrid(1, 2);

		b.mousePressed(e2);
		b.mousePressed(e1);
		a1.cancel();
		a2.cancel();
		b.shuffle();		
	}
	@Test
	void testShuffle_2() {
		int GameSize=9;		
		Board b=new Board(GameSize);
		b.shuffle();
		
	}
	@Test
	void testMousePressed() {
		int GameSize=9;
		GamePageStub gp=new GamePageStub(GameSize,"1",MainPage.getInstance());
		for(int i=1;i<=10;i++) {
		Board b=new Board(GameSize,gp);
		MouseEvent e1=null,e2=null;
		Grid a1=null,a2=null;
		for(int x1 = 1; x1 < GameSize - 1; x1++)
			for(int y1 = 1; y1 < GameSize - 1; y1++)
				for(int x2 = 1; x2 < GameSize - 1; x2++)
					for(int y2 = 1; y2 < GameSize - 1; y2++)
			if(b.getGrid(x1, y1).sameType(b.getGrid(x2, y2))) {
				e1=new MouseEvent(b.getGrid(x1, y1), 0, 0, 0, 0, 0, 0, false);
				e2=new MouseEvent(b.getGrid(x2, y2), 0, 0, 0, 0, 0, 0, false);
				a1=b.getGrid(x1, y1);
				a2=b.getGrid(x2, y2);
				break;
			}
		b.mousePressed(e2);
		b.mousePressed(e1);		
		}
	}
	@Test 
	void testMouse(){
		int GameSize=4;
		GamePageStub gp=new GamePageStub(GameSize,"1",MainPage.getInstance());
		
		Board b=new Board(GameSize,gp);
		b.mouseReleased(null);
		b.mouseEntered(null);
		b.mouseExited(null);
		b.mouseClicked(null);
	}
	@Test
	void testShowPath_1() {
		int GameSize=6;
		GamePageStub gp=new GamePageStub(GameSize,"1",MainPage.getInstance());
		Board b=new Board(GameSize,gp);
		while(!b.getGrid(1, 1).sameType(b.getGrid(1, 2)))
			b=new Board(GameSize,gp);
		MouseEvent e1=null,e2=null;
		Grid a1=null,a2=null;		
		e1=new MouseEvent(b.getGrid(1, 1), 0, 0, 0, 0, 0, 0, false);
		e2=new MouseEvent(b.getGrid(1, 2), 0, 0, 0, 0, 0, 0, false);
		a1=b.getGrid(1, 1);
		a2=b.getGrid(1, 2);
		b.mousePressed(e2);
		b.mousePressed(e1);
		a1.cancel();
		a2.cancel();
		
		gp.jpanel=b;
		gp.setSize(400, 400);        
		gp.setLocation(200, 200);
		gp.setVisible(true); 
		Graphics g=gp.getGraphics();
		gp.paint(g);
	}
}
