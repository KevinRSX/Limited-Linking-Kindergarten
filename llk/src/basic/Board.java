package basic;
import java.util.Random;

public class Board {
	private int size;
	private int typeNum;
	private GridType[][] grids;
	
		
	public Board(int s) throws WrongGameSizeException
	{
		setSize(s);
		grids = new GridType[size][size];
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				grids[i][j] = GridType.NONE;
			}
		}
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setSize(int gameSize) throws WrongGameSizeException {
		if (gameSize % 2 != 0 || gameSize < 2)
		{
			throw new WrongGameSizeException();
		}
		size = gameSize + 2;
		typeNum = gameSize;
	}
	
	public void setGrid(int row, int col, GridType g)
	{
		grids[row][col] = g; 
	}
	
	public int getExistingNumber()
	{
		int sum = 0;
		for (int i = 1; i < size - 1; i++)
		{
			for (int j = 1; j < size - 1; j++)
			{
				if (grids[i][j] != GridType.CANCELLED) sum++;
			}
		}
		return sum;
	}
	
	public boolean canPassThrough(int r, int c) // utility function to judge if a grid can be gone through
	{
		return (grids[r][c] == GridType.CANCELLED) || (grids[r][c] == GridType.NONE);
	}
	
	public void generateGrid(GridType gt)
	{
		Random rand = new Random();
		int r = rand.nextInt(typeNum) + 1;
		int c = rand.nextInt(typeNum) + 1;
		
		while (grids[r][c] != GridType.NONE && grids[r][c] != GridType.CANCELLED)
		{
			rand = new Random();
			r = rand.nextInt(typeNum) + 1;
			c = rand.nextInt(typeNum) + 1;
		}
		setGrid(r, c, gt);
		
		// generate the other one
		while (grids[r][c] != GridType.NONE && grids[r][c] != GridType.CANCELLED)
		{
			rand = new Random();
			r = rand.nextInt(typeNum) + 1;
			c = rand.nextInt(typeNum) + 1;
		}
		setGrid(r, c, gt);
	}
	
	public void generateAll()
	{
		for (int i = 0; i < typeNum / 2; i++)
		{
			for (int j = 1; j <= typeNum; j++)
			{
				generateGrid(GridType.getType(j));
			}
		}
	}
	
	public void show()
	{
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				System.out.print(grids[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void cancel(int x1, int y1, int x2, int y2) throws OutOfBoundException, CannotCancelException
	{
		if (x1 < 1 || y1 < 1 || x2 < 1 || y2 < 1
				|| x1 > typeNum || y1 > typeNum || x2 > typeNum || y2 > typeNum)
		{
			throw new OutOfBoundException();
		}
		if (!isSolvable(x1, y1, x2, y2))
		{
			throw new CannotCancelException();
		}
		setGrid(x1, y1, GridType.CANCELLED);
		setGrid(x2, y2, GridType.CANCELLED);
		
		show();
		while (!isSolvable())
		{
			System.out.println("No grid can be cancelled, shuffling...");
			shuffle();
		}
	}
	
	public void shuffle()
	{
		int numLeft = getExistingNumber();
		int rounds = numLeft / (typeNum * 2);
		int typeLeft = (numLeft % (typeNum * 2)) / 2;
		for (int i = 0; i < rounds; i++)
		{
			for (int j = 1; j <= typeNum; j++)
			{
				generateGrid(GridType.getType(j));
			}
		}
		for (int j = 1; j <= typeLeft; j++)
		{
			generateGrid(GridType.getType(j));
		}
	}
	
	public boolean lineEliminate(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2)
		{
			int step = y1 > y2 ? -1 : 1;
			for (int i = y1 + step; i != y2; i += step)
			{
				if (!canPassThrough(x1, i))
					return false;
			}
			return true;
		}
		else if (y1 == y2)
		{
			int step = x1 > x2 ? -1 : 1;
			for (int i = x1 + step; i != x2; i += step)
			{
				if (!canPassThrough(i, y1))
					return false;
			}
			return true;
		}
		return false;
	}

	public boolean twoLineEliminate(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2 || y1 == y2)
			return false;
		if ((lineEliminate(x1, y1, x1, y2) && lineEliminate(x1, y2, x2, y2) && canPassThrough(x1, y2))
				|| (lineEliminate(x1, y1, x2, y1) && lineEliminate(x2, y1, x2, y2) && canPassThrough(x2, y1)))
		{
			return true;
		}
		return false;
	}

	public boolean threeLineEliminate(int x1, int y1, int x2, int y2)
	{
		for (int i = 0; i < size; i++)
		{
			if (i != x1)
			{
				if (lineEliminate(x1, y1, i, y1) && twoLineEliminate(i, y1, x2, y2) && (grids[i][y1] == GridType.NONE || grids[i][y1] == GridType.CANCELLED))
					return true;
			}
			if (i != y1)
			{
				if (lineEliminate(x1, y1, x1, i) && twoLineEliminate(x1, i, x2, y2) && (grids[x1][i] == GridType.NONE || grids[x1][i] == GridType.CANCELLED))
					return true;
			}
		}
		return false;
	}

	public boolean isSolvable(int x1, int y1, int x2, int y2) throws OutOfBoundException
	{
		if (x1 < 1 || y1 < 1 || x2 < 1 || y2 < 1
				|| x1 > typeNum || y1 > typeNum || x2 > typeNum || y2 > typeNum)
		{
			throw new OutOfBoundException();
		}
		if (x1 == x2 && y1 == y2) return false;
		if (grids[x1][y1] != grids[x2][y2]) return false;
		if (lineEliminate(x1,y1,x2,y2) || twoLineEliminate(x1, y1, x2, y2) || 
				threeLineEliminate(x1,y1,x2,y2))
			return true;
		return false;
	}

	public boolean isSolvable() throws OutOfBoundException
	{
		for(int x1 = 1;x1 < size - 1; x1++)
			for(int y1 = 1;y1 < size - 1; y1++)
				for(int x2 = 1;x2 < size - 1; x2++)
					for(int y2 = 1; y2 < size - 1; y2++)
					{
						if(isSolvable(x1, y1, x2, y2))
						{
							return true;
						}
					}
		return false;					
	}
}
