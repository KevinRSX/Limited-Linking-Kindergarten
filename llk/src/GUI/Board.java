package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener {
	
	private final int W = 50; // Width of a single grid
    private Icon grid_icon[]; 
    private Icon path_icon[];     

	private ArrayList<Integer> images_t; // helper arraylist to generate grids
	private ArrayList<JLabel> label_arr;
	private JLabel[][] grids;
	private boolean[][] cancelled;
    private int[] all_paths;
    private JLabel selected;
    private ArrayList<RowAndColumn> path;
    private GamePage gamePage;
    
    private int index = -1;			
    @SuppressWarnings("unused")
	private Point p_index;			
    private int k;                  
    
    private int sum;
//    @SuppressWarnings("rawtypes")
//	private ArrayList path;      
          
    private int GameSize; // including edge
    
	public Board(int GameSize) {
		this.GameSize = GameSize;
		selected = null;
		grids = new JLabel[GameSize][GameSize];
		cancelled = new boolean[GameSize][GameSize];
		for (int i = 0; i < GameSize; i++) {
			for (int j = 0; j < GameSize; j++) {
				cancelled[i][j] = false;
			}
		}
		
		setLayout(new GridLayout(GameSize, GameSize));
		setBounds((700 - GameSize * W) / 2,(600 - GameSize * W) / 2, GameSize * W, GameSize * W);
		setOpaque(false);
		initMap();
		showGame();
	}
	
	public Board(int GameSize, GamePage gp) {
		this(GameSize);
		gamePage = gp;
		
	}
	
	/*-----------------------------utility-----------------------------*/
	private boolean sameType(JLabel j1, JLabel j2) {
		return j1.getIcon().equals(j2.getIcon());
	}
	
	public void setGridIcon(int row, int col, ImageIcon image) {
		grids[row][col].setIcon(image);
	}
	
	public void setGridIcon(RowAndColumn rac, ImageIcon image) {
		grids[rac.getRow()][rac.getCol()].setIcon(image);
	}
	
	public int getGameSize() { return GameSize; }
	
	public JLabel getLabel(int i, int j) { return grids[i][j]; } 
	
	// get row and column index from the JLabel how number
	// example: 0 returns 0, 0;
	public RowAndColumn getIndexFromLabel(int numLabel) {
		int row = numLabel / GameSize;
		int col = numLabel % GameSize;
		return new RowAndColumn(row, col);
	}
	
	public boolean canPassThrough(int r, int c)
	{
		return grids[r][c].getIcon() == null;
	}
	
	public Point getIndexFromGrid(JLabel g) {
		for (int i = 1; i < GameSize - 1; i++) {
			for (int j = 1; j < GameSize - 1; j++) {
				if (grids[i][j] == g) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}
	
	// traverse the whole board to see how many blocks are not cancelled
	public int remainingNum() {
		int remaining = 0;
		for (int i = 1; i < GameSize - 1; i++) {
			for (int j = 1; j < GameSize - 1; j++) {
				if (grids[i][j].getIcon() != null) {
					remaining++;
				}
			}
		}
		return remaining;
	}
	
	// three methods for determination of cancellation
	// x----x
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
	
	// x-----
	//      |
	//      x
	public Point twoLineEliminate(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2 || y1 == y2)
			return null;
//		if ((lineEliminate(x1, y1, x1, y2) && lineEliminate(x1, y2, x2, y2) && canPassThrough(x1, y2))
//				|| (lineEliminate(x1, y1, x2, y1) && lineEliminate(x2, y1, x2, y2) && canPassThrough(x2, y1)))
//		{
//			return true;
//		}
		if (lineEliminate(x1, y1, x1, y2) && lineEliminate(x1, y2, x2, y2) && canPassThrough(x1, y2)) {
			return new Point(x1, y2);
		}
		else if (lineEliminate(x1, y1, x2, y1) && lineEliminate(x2, y1, x2, y2) && canPassThrough(x2, y1)) {
			return new Point(x2, y1);
		}
		return null;
	}

	// ------
	// |    |
	// x    x
	public ArrayList<Point> threeLineEliminate(int x1, int y1, int x2, int y2)
	{
		ArrayList<Point> turningPoints = new ArrayList<>();
		for (int i = 0; i < GameSize; i++)
		{
			if (i != x1)
			{
				if (lineEliminate(x1, y1, i, y1) && (twoLineEliminate(i, y1, x2, y2) != null) && canPassThrough(i, y1)) {
					turningPoints.add(new Point(i, y1));
					turningPoints.add(twoLineEliminate(i, y1, x2, y2));
					return turningPoints;
				}
			}
			if (i != y1)
			{
				if (lineEliminate(x1, y1, x1, i) && (twoLineEliminate(x1, i, x2, y2) != null) && canPassThrough(x1, i)) {
					turningPoints.add(new Point(x1, i));
					turningPoints.add(twoLineEliminate(x1, i, x2, y2));
					return turningPoints;
				}
			}
		}
		return null;
	}
	
	
//	public boolean dfs(int sx, int sy, int nx, int ny, int tx, int ty) {
//		if (((nx + 1 == tx) && (ny == ty)) || ((nx - 1 == tx) && (ny == ty))
//				|| ((ny + 1 == ty) && (nx == tx)) || ((ny - 1 == ty) && (nx == tx))) {
//			return true;
//		}
//		if (nx + 1 < GameSize && twoLineEliminate(sx, sy, nx + 1, ny)) {
//			if (dfs(sx, sy, nx + 1, ny, tx, ty)) {
//				path.add(new RowAndColumn(nx + 1, ny));
//				return true;
//			}
//		}
//		if (nx - 1 >= 0 && twoLineEliminate(sx, sy, nx + 1, ny)) {
//			if (dfs(sx, sy, nx - 1, ny, tx, ty)) {
//				path.add(new RowAndColumn(nx - 1, ny));
//				return true;
//			}
//		}
//		if (ny + 1 < GameSize && twoLineEliminate(sx, sy, nx, ny + 1)) {
//			if (dfs(sx, sy, nx, ny + 1, tx, ty)) {
//				path.add(new RowAndColumn(nx, ny + 1));
//				return true;
//			}
//		}
//		if (ny - 1 < GameSize && twoLineEliminate(sx, sy, nx, ny - 1)) {
//			if (dfs(sx, sy, nx, ny - 1, tx, ty)) {
//				path.add(new RowAndColumn(nx, ny - 1));
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	
	/*-----------------------------functional methods-----------------------------*/
	public void showGame() {
		// label_arr = new ArrayList<JLabel>();
		
		for(int i = 0; i < GameSize * GameSize; i++) {
			// set edges to null
			RowAndColumn rowColPos = getIndexFromLabel(i);
			int row = rowColPos.getRow();
			int col = rowColPos.getCol();
			if(i % GameSize == 0 || i % GameSize == GameSize - 1 || i / GameSize == 0 || i / GameSize == GameSize - 1) {
				JLabel j = new JLabel();
				j.setIcon(null);
				// label_arr.add(j);
				grids[row][col] = new JLabel();
				setGridIcon(getIndexFromLabel(i), null);
				add(grids[row][col]);
				continue;
			}
			
			// generate a random number to determine which icon to show
			int nIndex = new Random().nextInt(images_t.size());
			
			// JLabel j = new JLabel(grid_icon[(int)images_t.get(nIndex)]);
			grids[row][col] = new JLabel(grid_icon[(int)images_t.get(nIndex)]);
			// label_arr.add(j);
			grids[row][col].addMouseListener(this);
			add(grids[row][col]);
			
			images_t.remove(nIndex);
		}
		
		while (!isSolvable()) {
			System.out.println("Not solvable, shuffling");
			shuffle();
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public void initMap() {
		images_t = new ArrayList<Integer>();
//		path = new ArrayList();
		grid_icon = new Icon[10];
		path_icon = new Icon[6];
		all_paths = new int[GameSize * GameSize];
		
		for(int i = 0; i < grid_icon.length; i++) {
			grid_icon[i] = new ImageIcon(getClass().getResource("/images/" + "fruit_"+ (i + 1) + ".jpg"));
		}
		for(int i = 0;i < path_icon.length; i++) {
			path_icon[i] = new ImageIcon(getClass().getResource("/images/" + "line_" + (i + 1) + ".png"));
		}
		
		for(int i = 0; images_t.size() < (GameSize - 2) * (GameSize - 2); i++) {
			images_t.add(i % 10);
			if (images_t.size() == (GameSize - 2) * (GameSize - 2)) {
				continue;
			}
			images_t.add(i % 10);
		}
		sum = images_t.size();	
	}
	
	public void shuffle() {
		int remaining = remainingNum();
		for (int i = 0; i < GameSize; i++) {
			for (int j = 0; j < GameSize; j++) {
				setGridIcon(i, j, null);
			}
		}
		for(int i = 0; images_t.size() < remaining; i++) {
			images_t.add(i % 10);
			if (images_t.size() == remaining) {
				continue;
			}
			images_t.add(i % 10);
		}
		
		for (int i = 1; i < GameSize * GameSize; i++) {
			RowAndColumn rowColPos = getIndexFromLabel(i);
			int row = rowColPos.getRow();
			int col = rowColPos.getCol();
			if(i % GameSize == 0 || i % GameSize == GameSize - 1 || i / GameSize == 0 || i / GameSize == GameSize - 1 || cancelled[row][col]) {
				continue;
			}
			int nIndex = new Random().nextInt(images_t.size());
			setGridIcon(rowColPos, (ImageIcon)grid_icon[(int)images_t.get(nIndex)]); 		
			images_t.remove(nIndex);
		}
	}
	
	
	public ArrayList<Point> getTurningPoints(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2 && y1 == y2) return null;
		if (!sameType(grids[x1][y1], grids[x2][y2])) return null;
		if (canPassThrough(x1, y1) || canPassThrough(x2, y2)) return null;

		if (lineEliminate(x1, y1, x2, y2)) return new ArrayList<Point>();
		Point le2;
		if ((le2 = twoLineEliminate(x1, y1, x2, y2)) != null) {
			ArrayList<Point> tp = new ArrayList<>();
			tp.add(le2);
			return tp;
		}
		ArrayList<Point> le3;
		if ((le3 = threeLineEliminate(x1, y1, x2, y2)) != null) {
			return le3;
		}
		return null;
	}
	
	public ArrayList<Point> getTurningPoints(Point p1, Point p2) {
		return getTurningPoints(p1.x, p1.y, p2.x, p2.y);
	}

	// determine if the two given grids can be cancelled
	public boolean isSolvable(int x1, int y1, int x2, int y2)
	{
		if (x1 == x2 && y1 == y2) return false;
		if (!sameType(grids[x1][y1], grids[x2][y2])) return false;
		if (canPassThrough(x1, y1) || canPassThrough(x2, y2)) return false;
		if (lineEliminate(x1,y1,x2,y2) || twoLineEliminate(x1, y1, x2, y2) != null || 
				threeLineEliminate(x1,y1,x2,y2) != null)
			return true;
		return false;
	}
	
	
	// determine if there is at least one pair of grids that can be cancelled
	public boolean isSolvable()
	{
		for(int x1 = 1; x1 < GameSize - 1; x1++)
			for(int y1 = 1; y1 < GameSize - 1; y1++)
				for(int x2 = 1; x2 < GameSize - 1; x2++)
					for(int y2 = 1; y2 < GameSize - 1; y2++)
					{
						if(grids[x1][y1].getIcon() != null && grids[x2][y2].getIcon() != null && isSolvable(x1, y1, x2, y2))
						{
							return true;
						}
					}
		return false;					
	}
	
	/*-----------------------------events-----------------------------*/
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		path = new ArrayList<>();
		JLabel j = (JLabel)e.getComponent();
		if (j.getIcon() != null) {
			if (selected == null) { // first selected
				j.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));
				selected = j;
			}
			else {
				Point rac1 = getIndexFromGrid(selected);
				Point rac2 = getIndexFromGrid(j);
				ArrayList<Point> allTurningPoints = new ArrayList<>();
				if (getTurningPoints(rac1, rac2) != null) {
					// cancel
					j.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
					j.setIcon(null);
					j.setBorder(null);
					selected.setIcon(null);
					selected.setBorder(null);
					cancelled[rac1.x][rac1.y] = true;
					cancelled[rac2.x][rac2.y] = true;
					
					// draw path
					allTurningPoints.add(0, rac1);
					allTurningPoints.add(rac2);
					gamePage.setPath(allTurningPoints);
				}
				else { // cannot be cancelled
					selected.setBorder(null);
				}
				selected = null;
			}
			
			while (!isSolvable() && remainingNum() > 0) { // remaining num is just for testing
				System.out.println("Not solvable, shuffling");
				shuffle();
			}
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
