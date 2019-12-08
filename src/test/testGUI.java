package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;
import java.util.HashMap;

import gui.AbstractPage;
import gui.Dialog;
import gui.GamePage;
import gui.GamePageController;
import gui.MainPage;
import gui.PostGamePage;
import gui.PreGamePage;

class testGUI {
	@Test
	void testMainPageInstance() {
		MainPage mainpage=MainPage.getInstance();
		mainpage.getInfo();
		mainpage.display();
		mainpage.refresh();
	}

	@Test
	void testPreGamePage() {
		int gamesize=4;
		PreGamePage pregamepage =new PreGamePage(gamesize,MainPage.getInstance());
		pregamepage.getInfo();
		pregamepage.display();
		pregamepage.refresh();
		
	}
	
	@Test
	void testGamePage02() {
		int gamesize=2;
		String username="LI";
		class PreGamePageStub extends AbstractPage{
			public PreGamePageStub() {
				super();
			}

			@Override
			protected HashMap<String, String> getInfo() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		PreGamePageStub pregamepageStub=new PreGamePageStub();
		GamePage gamepage = new GamePage(gamesize, username, pregamepageStub);
		gamepage.stop();
		gamepage.dispose();
		gamepage.getInfo();
		
	}
	
	@Test
	void testGamePage04() {
		int gamesize=4;
		String username="LI";
		class PreGamePageStub extends AbstractPage{
			public PreGamePageStub() {
				super();
			}

			@Override
			protected HashMap<String, String> getInfo() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		PreGamePageStub pregamepageStub=new PreGamePageStub();
		GamePage gamepage = new GamePage(gamesize, username, pregamepageStub);
	}
	
	@Test
	void testGamePage06() {
		int gamesize=6;
		String username="LI";
		class PreGamePageStub extends AbstractPage{
			public PreGamePageStub() {
				super();
			}

			@Override
			protected HashMap<String, String> getInfo() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		PreGamePageStub pregamepageStub=new PreGamePageStub();
		GamePage gamepage = new GamePage(gamesize, username, pregamepageStub);
	}
	
	@Test 
	void testPostGamePage01() {
		boolean finished=true;
		String username="LI";
		int gamesize=4;
		int point=100;
		PostGamePage postgamepage =new PostGamePage(finished, username, gamesize, point);
	}
	
	@Test
	void testPostGamePage02() {
		boolean finished=false;
		String username="LI";
		int gamesize=4;
		int point=100;
		PostGamePage postgamepage =new PostGamePage(finished, username, gamesize, point);
		postgamepage.getInfo();
	}
	
	@Test
	void testDiaLog() {
		class PreGamePageStub extends AbstractPage{
			public PreGamePageStub() {
				super();
			}

			@Override
			protected HashMap<String, String> getInfo() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		PreGamePageStub pregamepageStub = new PreGamePageStub();
		Dialog dialog = new Dialog(pregamepageStub);
	}

}
