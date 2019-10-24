package testBasic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import basic.Board;
import basic.GridType;
import basic.WrongGameSizeException;

class testBoard {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void testShow() throws Exception
	{
		Board b = new Board(8);
		b.generateAll();
		b.show();
	}
	
	@Test
	void boardSolvableAABB() throws Exception // global isSolvable
	{
		class BoardStub extends Board
		{
			public BoardStub(int s) throws WrongGameSizeException
			{
				super(s);
			}

			@Override
			public void generateAll()
			{
				setGrid(1, 1, GridType.A);
				setGrid(1, 2, GridType.A);
				setGrid(2, 1, GridType.B);
				setGrid(2, 2, GridType.B);
			}
		}
		BoardStub bs = new BoardStub(2);
		bs.generateAll();
		assertEquals(true, bs.isSolvable());
	}
	
//	@Test
//	void boardSetSize() // test system response for wrong setting of game size
//	{
//		Board b = new Board();
//		try
//		{
//			b.setSize(1);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//		}
//		try
//		{
//			b.setSize(7);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//		}
//	}
	
	@Test
	void boardSolvableABBA() throws Exception // global isSolvable
	{
		class BoardStub extends Board
		{
			public BoardStub(int s) throws WrongGameSizeException
			{
				super(s);
			}
			
			@Override
			public void generateAll()
			{
				setGrid(1, 1, GridType.A);
				setGrid(1, 2, GridType.B);
				setGrid(2, 1, GridType.B);
				setGrid(2, 2, GridType.A);
			}
		}
		
		BoardStub bs = new BoardStub(2);
		bs.generateAll();
		assertEquals(false, bs.isSolvable());
	}
	
	@Test
	void gridSolvableInTurn() throws Exception // local isSolvable
	{
		/* XXXX
		 * XA X
		 * XXAX
		 */
		class BoardStub extends Board
		{
			public BoardStub(int s) throws WrongGameSizeException
			{
				super(s);
			}
			
			@Override
			public void generateAll()
			{
				
				for (int i = 1; i < getSize() - 1; i++)
				{
					for (int j = 1; j < getSize() - 1; j++)
					{
						setGrid(i, j, GridType.B);
					}
				}
				setGrid(2, 2, GridType.A);
				setGrid(3, 3, GridType.A);
			}
		}
		BoardStub bs = new BoardStub(4);
		bs.generateAll();
		assertEquals(false, bs.isSolvable(2, 2, 3, 3));
		bs.setGrid(2, 3, GridType.NONE);
		assertEquals(true, bs.isSolvable(2, 2, 3, 3));
		
	}
	
	@Test
	void gridSolvableOutTurn1() throws Exception // local isSolvable
	{
		/*  AXA
		 *  XXX
		 *  XXX
		 */
		class BoardStub extends Board
		{
			public BoardStub(int s) throws WrongGameSizeException
			{
				super(s);
			}
			
			@Override
			public void generateAll()
			{
				for (int i = 1; i < getSize() - 1; i++)
				{
					for (int j = 1; j < getSize() - 1; j++)
					{
						setGrid(i, j, GridType.B);
					}
				}
				setGrid(1, 1, GridType.A);
				setGrid(1, 3, GridType.A);
			}
		}
		BoardStub bs = new BoardStub(4);
		bs.generateAll();
		assertEquals(true, bs.isSolvable(1, 1, 1, 3));
	}
	
	@Test
	void gridSolvableOutTurn2() throws Exception // local isSolvable
	{
		/*  XXA
		 *  XXX
		 *  XXA
		 */
		class BoardStub extends Board
		{
			public BoardStub(int s) throws WrongGameSizeException
			{
				super(s);
			}
			
			@Override
			public void generateAll()
			{
				for (int i = 1; i < getSize() - 1; i++)
				{
					for (int j = 1; j < getSize() - 1; j++)
					{
						setGrid(i, j, GridType.B);
					}
				}
				setGrid(1, 4, GridType.A);
				setGrid(3, 4, GridType.A);
			}
		}
		BoardStub bs = new BoardStub(4);
		bs.generateAll();
		assertEquals(true, bs.isSolvable(1, 4, 3, 4));
	}
	
	@Test
	void gridSolvableOutTurn3() throws Exception // local isSolvable
	{
		/*  AX X
		 *  XX X
		 *  XXAX
		 */
		class BoardStub extends Board
		{
			public BoardStub(int s) throws WrongGameSizeException
			{
				super(s);
			}
			
			@Override
			public void generateAll()
			{
				for (int i = 1; i < getSize() - 1; i++)
				{
					for (int j = 1; j < getSize() - 1; j++)
					{
						setGrid(i, j, GridType.B);
					}
				}
				setGrid(1, 1, GridType.A);
				setGrid(3, 3, GridType.A);
			}
		}
		BoardStub bs = new BoardStub(4);
		bs.generateAll();
		bs.setGrid(1, 3, GridType.CANCELLED);
		bs.setGrid(2, 3, GridType.CANCELLED);
		bs.show();
		assertEquals(true, bs.isSolvable(1, 1, 3, 3));
	}
	
	
}
