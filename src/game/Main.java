package game;

import javax.swing.JFrame;
import gui.MainPage;

public class Main {
	public static void main(String agrs[]) {	     
		MainPage mainpage = MainPage.getInstance();
		mainpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpage.setVisible(true);
		mainpage.setResizable(false);
   }
}
