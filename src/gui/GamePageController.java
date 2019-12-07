package gui;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GamePageController implements ButtonBindable{
	
	private AbstractPage father;
	private String username;
	private int gamesize;
	private GamePage gamepage;
	
	private static GamePageController theInstance = new GamePageController();
	public static GamePageController getInstance() {return theInstance;}
	private GamePageController() {}
	
	public void setFather(AbstractPage f){
		this.father = f;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		if(!this.username.equals("")) {
			Point p = this.father.getLocation();
			this.father.dispose();
			this.father = null;
			
			this.gamepage = new GamePage(this.gamesize, this.username, this.father);
			this.gamepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.gamepage.setLayout(null);
			this.gamepage.setLocation(p);
			this.gamepage.setVisible(true);
			this.gamepage.setResizable(false);
		} else {
			JOptionPane.showMessageDialog(this.father,"You need to input user name.");
		}
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		HashMap<String, String> gameinfo = this.father.getInfo();
		this.username = gameinfo.get("username");
		this.gamesize = Integer.parseInt(gameinfo.get("gamesize"));
		System.out.println(gamesize);
	}

}
