package basic;

public class Board {
	private int size;
	private GridType[][] grids;
	
	private static Board board=new Board();	
		
	public static Board getInstance() {
		return board;
	}
	
	public void setSize(int gameSize) {
		size = gameSize + 2;
		grids = new GridType[size][size];
	}
	
	

}
