package basic;
import java.util.Random;

public class Board {
	private int size;
	private int typeNum;
	private GridType[][] grids;
	
	private static Board board = new Board();
		
	public static Board getInstance() {
		return board;
	}
	
	public void setSize(int gameSize) throws Exception {
		if (gameSize % 2 != 0 || gameSize < 2)
		{
			throw new Exception("Game size not correct");
		}
		size = gameSize + 2;
		typeNum = gameSize;
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
	
	public void setGrid(int row, int col, GridType g)
	{
		grids[row][col] = g; 
	}
	
	public void generateGrids()
	{
		for (int i = 0; i < typeNum / 2; i++)
		{
			for (int j = 1; j <= typeNum; j++)
			{
				Random rand = new Random();
				int r = rand.nextInt(typeNum) + 1;
				int c = rand.nextInt(typeNum) + 1;
				
				while (grids[r][c] != GridType.NONE)
				{
					rand = new Random();
					r = rand.nextInt(typeNum) + 1;
					c = rand.nextInt(typeNum) + 1;
				}
				grids[r][c] = GridType.getType(j);
				
				// generate the other one
				while (grids[r][c] != GridType.NONE)
				{
					rand = new Random();
					r = rand.nextInt(typeNum) + 1;
					c = rand.nextInt(typeNum) + 1;
				}
				grids[r][c] = GridType.getType(j);
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
	
	public boolean lineEliminate(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2)
		{
			int step = y1 > y2 ? -1 : 1;
			for (int i = y1 + step; i != y2; i += step)
			{
				if (grids[x1][i] != GridType.NONE)
					return false;
			}
			return true;
		}
		else if (y1 == y2)
		{
			int step = x1 > x2 ? -1 : 1;
			for (int i = x1 + step; i != x2; i += step)
			{
				if (grids[i][y1] != GridType.NONE)
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
		if ((lineEliminate(x1, y1, x1, y2) && lineEliminate(x1, y2, x2, y2) && grids[x1][y2] == GridType.NONE)
				|| (lineEliminate(x1, y1, x2, y1) && lineEliminate(x2, y1, x2, y2) && grids[x2][y1] == GridType.NONE))
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
				if (lineEliminate(x1, y1, i, y1) && twoLineEliminate(i, y1, x2, y2) && grids[i][y1] == GridType.NONE)
					return true;
			}
			if (i != y1)
			{
				if (lineEliminate(x1, y1, x1, i) && twoLineEliminate(x1, i, x2, y2) && grids[x1][i] == GridType.NONE)
					return true;
			}
		}
		return false;
	}

	public boolean isSolvable(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2 && y1 == y2) return false;
		if (grids[x1][y1] != grids[x2][y2]) return false;
		if (lineEliminate(x1,y1,x2,y2) || twoLineEliminate(x1, y1, x2, y2) || 
				threeLineEliminate(x1,y1,x2,y2))
			return true;
		return false;
	}

	public boolean isSolvable()
	{
		for(int x1 = 1;x1 < size - 1; x1++)
			for(int y1 = 1;y1 < size - 1; y1++)
				for(int x2 = 1;x2 < size - 1; x2++)
					for(int y2 = 1; y2 < size - 1; y2++)
					{
						if(isSolvable(x1, y1, x2, y2))
						{
							System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
							return true;
						}
					}
		return false;					
	}
}
