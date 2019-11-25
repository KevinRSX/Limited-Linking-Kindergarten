package GUI;

import java.awt.Point;

import javax.swing.JFrame;

public class Main {
	private static MainPage e1 = new MainPage(0);
	
	public static void setMainPageLocation(Point p) {
		e1.setLocation(p);
	}
	
	public static Point getMainPageLocation() {
		return e1.getLocation();
	}
	
	public static void disposeMainPage() {
		e1.dispose();
	}
//	static PlaySound p;
	
	public static void main(String agrs[]) {	      
		e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		e1.setVisible(true);
		e1.setResizable(false);
	
//		if(p == null) {
//			p = new PlaySound();
//				p.open("sounds/background.wav");	
//				p.play();
//				p.loop();
//				p.start();
//			
//		}

   }
}
