package testBasic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import basic.Board;
import basic.CannotCancelException;
import basic.GridType;
import basic.OutOfBoundException;
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
		assertEquals(true, bs.isSolvable(1, 1, 3, 3));
	}
	
	@Test
	void testShuffleSmall() throws WrongGameSizeException 
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
		bs.shuffle();
		bs.show();
	}
	
	@Test
	void testCancelAndShuffleBig() throws WrongGameSizeException, OutOfBoundException, CannotCancelException
	{
		Board b = new Board(8);
		b.generateAll();
		b.setGrid(1, 3, GridType.A);
		b.setGrid(1, 8, GridType.A);
		b.setGrid(3, 8, GridType.A);
		try
		{
			b.cancel(1, 3, 3, 8);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			b.cancel(1, 3, 1, 8);
			b.show();
			b.shuffle();
			b.show();
		}
	}
	
	@Test
	void testAutomaticShuffle() throws WrongGameSizeException, OutOfBoundException, CannotCancelException
	{
		Board b = new Board(2);
		b.setGrid(1, 1, GridType.A);
		b.setGrid(1, 2, GridType.B);
		b.setGrid(2, 1, GridType.A);
		b.setGrid(2, 2, GridType.A);
		b.cancel(2, 2, 2, 1);
		b.show();
	}
}
