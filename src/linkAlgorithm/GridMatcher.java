package linkAlgorithm;

import java.awt.Point;
import java.util.ArrayList;

public class GridMatcher {
	
	// x----x
	private static boolean oneLineMatcher(Linkable[][] grids, Point p1, Point p2) {
		int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		if (x1 == x2) {
			int step = y1 > y2 ? -1 : 1;
			for (int i = y1 + step; i != y2; i += step) {
				if (!grids[x1][i].canPassThrough())
					return false;
			}
			return true;
		}
		else if (y1 == y2) {
			int step = x1 > x2 ? -1 : 1;
			for (int i = x1 + step; i != x2; i += step) {
				if (!grids[i][y1].canPassThrough())
					return false;
			}
			return true;
		}
		return false;
	}
	
	// x-----
	//      |
	//      x
	private static Point twoLinesMatcher(Linkable[][] grids, Point p1, Point p2) {
		int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		if (x1 == x2 || y1 == y2)
			return null;
		if (oneLineMatcher(grids, new Point(x1, y1), new Point(x1, y2))
				&& oneLineMatcher(grids, new Point(x1, y2), new Point(x2, y2))
				&& grids[x1][y2].canPassThrough()) {
			return new Point(x1, y2);
		}
		else if (oneLineMatcher(grids, new Point(x1, y1), new Point(x2, y1))
				&& oneLineMatcher(grids, new Point(x2, y1), new Point(x2, y2))
				&& grids[x2][y1].canPassThrough()) {
			return new Point(x2, y1);
		}
		return null;
	}
	
	private static ArrayList<Point> threeLinesMatcher(Linkable[][] grids, Point p1, Point p2) {
		ArrayList<Point> turningPoints = new ArrayList<>();
		int GameSize = grids[0].length;
		int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		for (int i = 0; i < GameSize; i++)
		{
			if (i != x1)
			{
				if (oneLineMatcher(grids, new Point(x1, y1), new Point(i, y1)) 
						&& (twoLinesMatcher(grids, new Point(i, y1), new Point(x2, y2)) != null)
						&& grids[i][y1].canPassThrough()) {
					turningPoints.add(new Point(i, y1));
					turningPoints.add(twoLinesMatcher(grids, new Point(i, y1), new Point(x2, y2)));
					return turningPoints;
				}
			}
			if (i != y1)
			{
				if (oneLineMatcher(grids, new Point(x1, y1), new Point(x1, i)) 
						&& (twoLinesMatcher(grids, new Point(x1, i), new Point(x2, y2)) != null)
						&& grids[x1][i].canPassThrough()) {
					turningPoints.add(new Point(x1, i));
					turningPoints.add(twoLinesMatcher(grids, new Point(x1, i), new Point(x2, y2)));
					return turningPoints;
				}
			}
		}
		return null;
	}
	
	public static ArrayList<Point> matchingPath(Linkable[][] grids, Point p1, Point p2) {
		int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		if (x1 == x2 && y1 == y2) return null;
		if (!grids[x1][y1].sameType(grids[x2][y2])) return null;
		if (grids[x1][y1].canPassThrough() || grids[x2][y2].canPassThrough()) return null;

		if (oneLineMatcher(grids, new Point(x1, y1), new Point(x2, y2)))
			return new ArrayList<Point>();
		Point le2;
		if ((le2 = twoLinesMatcher(grids, new Point(x1, y1), new Point(x2, y2))) != null) {
			ArrayList<Point> tp = new ArrayList<>();
			tp.add(le2);
			return tp;
		}
		ArrayList<Point> le3;
		if ((le3 = threeLinesMatcher(grids, new Point(x1, y1), new Point(x2, y2))) != null) {
			return le3;
		}
		return null;
	}
	
	public static boolean isCancellable(Linkable[][] grids, Point p1, Point p2) {
		int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		if (x1 == x2 && y1 == y2) return false;
		if (grids[x1][y1].canPassThrough() || grids[x2][y2].canPassThrough()) return false;
		if (!grids[x1][y1].sameType(grids[x2][y2])) return false;
		if (matchingPath(grids, p1, p2) == null) return false;
		return true;
	}
}
