package GUI;

import java.awt.Point;

import javax.swing.JFrame;

public class Main {
	private static PreGamePage pregame = new PreGamePage(0);
	public static void disposePreGamePage() {
		pregame.dispose();
	}
//	static PlaySound p;
	
	public static void main(String agrs[]) {	      
		pregame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pregame.setVisible(true);
		pregame.setResizable(false);
	
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
