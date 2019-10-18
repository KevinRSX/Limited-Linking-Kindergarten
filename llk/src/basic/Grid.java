package basic;

public class Grid {
	int row;
	int col;
	GridType type;
	
	public Grid(int r, int c, int t)
	{
		row = r;
		col = c;
		type = GridType.getType(t);
	}
	
	public void set(GridType t) {
		
	}
}
