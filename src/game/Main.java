package game;

import java.awt.Point;
import javax.swing.JFrame;
import gui.MainPage;

public class Main {
	private static MainPage mainpage = new MainPage("Limited-Linking-Kindergarten");
	public static void disposeMainPage() {
		mainpage.dispose();
	}
	public static Point getMainPageLocation() {
		return mainpage.getLocation();
	}
	public static void setMainPageLocation(Point p) {
		mainpage.setLocation(p);
	}
	
	public static void main(String agrs[]) {	      
		mainpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpage.setVisible(true);
		mainpage.setResizable(false);
   }
}
