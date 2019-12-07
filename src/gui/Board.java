package gui;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import linkAlgorithm.*;
import timer.TimerChangeException;
import timer.TimerTerminateErrorException;

public class Board extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int W = 50; // Width of a single grid
    private Icon grid_icon[];    

	private ArrayList<Integer> images_t; // helper ArrayList to generate grids
	private Grid[][] grids;
	private boolean[][] cancelled;
    private Grid selected;
    private ArrayList<Point> path;
    private GamePage gamePage;
    
    private int GameSize; // including edge
    private int score;
    
	public Board(int GameSize) {
		this.GameSize = GameSize;
		this.score=(GameSize-2)*(GameSize-2)*10;
		selected = null;
		grids = new Grid[GameSize][GameSize];
		cancelled = new boolean[GameSize][GameSize];
		for (int i = 0; i < GameSize; i++) {
			for (int j = 0; j < GameSize; j++) {
				cancelled[i][j] = false;
			}
		}
		
		setLayout(new GridLayout(GameSize, GameSize));
		setBounds((800 - GameSize * W) / 2, (700 - GameSize * W) / 2, GameSize * W, GameSize * W);
		setOpaque(false);
		initMap();
		showGame();
	}
	
	public Board(int GameSize, GamePage gp) {
		this(GameSize);
		gamePage = gp;		
	}
	
	/*-----------------------------utility-----------------------------*/
	public int getGameSize() { return GameSize; }
	
	// get row and column index from the JLabel how number
	// example: 0 returns 0, 0;
	public Point getIndexFromLabel(int numLabel) {
		int row = numLabel / GameSize;
		int col = numLabel % GameSize;
		return new Point(row, col);
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
				if (!grids[i][j].canPassThrough()) {
					remaining++;
				}
			}
		}
		return remaining;
	}

	public void clearBorders() {
		for (int i = 1; i < GameSize - 1; i++) {
			for (int j = 1; j < GameSize - 1; j++) {
				CommandManager cm = new CommandManager();
				cm.addCommand(new DeselectLinkable(grids[i][j]));
				cm.executeAllCommands();
				grids[i][j].deselect();
			}
		}
	}
		
	/*-----------------------------functional methods-----------------------------*/
	public void showGame() {
		for(int i = 0; i < GameSize * GameSize; i++) {
			// set edges to null
			Point rowColPos = getIndexFromLabel(i);
			int row = rowColPos.x;
			int col = rowColPos.y;
			if(i % GameSize == 0 || i % GameSize == GameSize - 1 || i / GameSize == 0 || i / GameSize == GameSize - 1) {
				JLabel j = new JLabel();
				j.setIcon(null);
				grids[row][col] = new Grid();
				grids[row][col].setIcon(null);
				add(grids[row][col]);
				continue;
			}
			
			// generate a random number to determine which icon to show
			int nIndex = new Random().nextInt(images_t.size());
			grids[row][col] = new Grid(grid_icon[(int)images_t.get(nIndex)]);
			grids[row][col].addMouseListener(this);
			add(grids[row][col]);
			
			images_t.remove(nIndex);
		}
		
		while (!isSolvable()) {
			System.out.println("Not solvable, shuffling");
			shuffle();
		}
	}

	public void initMap() {
		images_t = new ArrayList<Integer>();
		path = new ArrayList<>();
		grid_icon = new Icon[10];
		
		for(int i = 0; i < grid_icon.length; i++) {
			grid_icon[i] = new ImageIcon(("images/" + "pic"+ (i + 1) + ".png"));
		}
		
		for(int i = 0; images_t.size() < (GameSize - 2) * (GameSize - 2); i++) {
			images_t.add(i % 10);
			if (images_t.size() == (GameSize - 2) * (GameSize - 2)) {
				continue;
			}
			images_t.add(i % 10);
		}
	}
	
	public void shuffle() {
		int remaining = remainingNum();
		for (int i = 0; i < GameSize; i++) {
			for (int j = 0; j < GameSize; j++) {
				grids[i][j].setIcon(null);
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
			Point rowColPos = getIndexFromLabel(i);
			int row = rowColPos.x;
			int col = rowColPos.y;
			if(i % GameSize == 0 || i % GameSize == GameSize - 1 || i / GameSize == 0 || i / GameSize == GameSize - 1 || cancelled[row][col]) {
				continue;
			}
			int nIndex = new Random().nextInt(images_t.size());
			grids[row][col].setIcon((ImageIcon)grid_icon[(int)images_t.get(nIndex)]);
			images_t.remove(nIndex);
		}
	}		
	
	// determine if there is at least one pair of grids that can be cancelled
	public boolean isSolvable()
	{
		for(int x1 = 1; x1 < GameSize - 1; x1++)
			for(int y1 = 1; y1 < GameSize - 1; y1++)
				for(int x2 = 1; x2 < GameSize - 1; x2++)
					for(int y2 = 1; y2 < GameSize - 1; y2++)
					{
						if(GridMatcher.isCancellable(grids, new Point(x1, y1), new Point(x2, y2)))
						{
							return true;
						}
					}
		return false;					
	}
	
	public void showHint() {
		clearBorders();
		for(int x1 = 1; x1 < GameSize - 1; x1++)
			for(int y1 = 1; y1 < GameSize - 1; y1++)
				for(int x2 = 1; x2 < GameSize - 1; x2++)
					for(int y2 = 1; y2 < GameSize - 1; y2++)
					{
						if(GridMatcher.isCancellable(grids, new Point(x1, y1), new Point(x2, y2)))
						{
							CommandManager cm = new CommandManager();
							cm.addCommand(new SelectLinkable(grids[x1][y1]));
							cm.addCommand(new SelectLinkable(grids[x2][y2]));
							cm.executeAllCommands();
							return;
						}
					}
	}
	
	public boolean isFinished() {
		return remainingNum() == 0;
	}
	
	public void showPath(Graphics2D g2, Insets insets) {
		if (path != null && path.size() > 0) {
	    	Point pre = path.get(0);
			for (int i = 1; i < path.size(); i++) {
				Point next = path.get(i);
				int sx = (800 - GameSize * W) / 2;
				int sy = (700 - GameSize * W) / 2;
				int x1 = insets.left + sx + pre.y * W + W / 2;
	            int x2 = insets.left + sx + next.y * W + W / 2;
	            int y1 = insets.top + sy + pre.x * W + W / 2;
	            int y2 = insets.top + sy + next.x * W + W / 2;
	            g2.drawLine(x1, y1, x2, y2);
	            pre = next;
	            if (i == 1) {
	            	 g2.draw(new Ellipse2D.Float(x1 - 2, y1 - 2, 4, 4));
	            }
	            g2.draw(new Ellipse2D.Float(x2 - 2, y2 - 2, 4, 4));
			}
		}
	}
	
	/*-----------------------------events-----------------------------*/
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		path = new ArrayList<>();
		Grid j = (Grid)e.getComponent();
		clearBorders();
		CommandManager cm = new CommandManager();
		if (j.getIcon() != null) {
			if (selected == null) { // first selected
				cm.addCommand(new SelectLinkable(j));
				cm.executeAllCommands();
				selected = j;
			}
			else {
				Point rac1 = getIndexFromGrid(selected);
				Point rac2 = getIndexFromGrid(j);
				ArrayList<Point> allTurningPoints = new ArrayList<>();
				if ((allTurningPoints = GridMatcher.matchingPath(grids, rac1, rac2)) != null) {
					// cancel
					try {
						gamePage.incTime();
					} catch (TimerChangeException e1) {
						e1.printStackTrace();
					}
					cm.addCommand(new CancelLinkable(j));
					cm.addCommand(new CancelLinkable(selected));
					cm.addCommand(new DeselectLinkable(j));
					cm.addCommand(new DeselectLinkable(selected));
					cancelled[rac1.x][rac1.y] = true;
					cancelled[rac2.x][rac2.y] = true;
					cm.executeAllCommands();
					this.score+=10;
					
					// draw path
					allTurningPoints.add(0, rac1);
					allTurningPoints.add(rac2);
					path = allTurningPoints;
					gamePage.repaint();
					
					if(isFinished()) {
						try {
							gamePage.stopTimer();
						} catch (TimerTerminateErrorException e1) {
							e1.printStackTrace();
						}
						gamePage.endGame(true,score);
					}
				}
				else { // cannot be cancelled
					if (!rac1.equals(rac2)) {
						try {
							gamePage.decTime();
						} catch (TimerChangeException e1) {
							e1.printStackTrace();
						}
					}
					cm.addCommand(new DeselectLinkable(selected));
					cm.executeAllCommands();
				}
				selected = null;
			}
			
			while (!isSolvable() && remainingNum() > 0) {
				System.out.println("Not solvable, shuffling");
				shuffle();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
