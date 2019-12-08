package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import score.ScoreBoard;
import game.Main;

public class PostGamePage extends AbstractPage {
	private Background background;
	
	private JLabel label;
	private Button home, restart, score;
	private boolean is_finished;
	
	private String username;
	private int gamesize;
	private int points;
	
	public PostGamePage(boolean finished, String username, int gamesize, int points) {
		super("Limited Time");
		setIconImage(Toolkit.getDefaultToolkit().getImage(("images/icon.png")));
		setSize(700, 600);
		 
		this.showBackground();

		this.is_finished=finished;
		this.username = username;
		this.gamesize = gamesize-2;
		this.points = points;
		
		showMenu();
		setVisible(true);
	}
	
	private void showBackground() {
		this.background = new Background("images/background.png", getWidth(), getHeight());
		JPanel j = (JPanel)getContentPane();
		j.setOpaque(false);
		j.setLayout(null);
		getLayeredPane().add(this.background, Integer.valueOf(Integer.MIN_VALUE));
	}
	
	private void showMenu() {
		if(is_finished) {
			this.label= new JLabel("Score: " + points);
		}
		else {
			this.label=new JLabel("You FAILED..");
		}		
		this.label.setFont(new Font("acefont-family", Font.BOLD, 35));
		this.label.setBounds(240,150,400,45);
		this.label.setForeground(Color.RED);
		Dimension d = label.getPreferredSize();
	    label.setPreferredSize(new Dimension(d.width+60,d.height));
		this.add(this.label);
		
//		this.home = new JButton("Home");
//		this.home.setBounds(260,250,150,40);
//		this.add(this.home);
//		this.home.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Point p = postgame.getLocation();
//				postgame.dispose();
//				Main.main(null);
////				PreGamePage.setMainPageLocation(p);
//			}
//		});
			
		this.home = new Button("Home", null, 260,250,150,40);
		MainPage.getInstance().setFather(this);
		this.home.bind(MainPage.getInstance());
		this.add(this.home);
		
//		this.restart = new JButton("Restart");
//		this.restart.setBounds(260,300,150,40);
//		this.add(this.restart);

//		this.restart.addActionListener(new ActionListener() {	
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Point p = postgame.getLocation();
//				postgame.dispose();
//				GamePage GamePage;
//				GamePage = new GamePage(GameSize - 2, username);
//				GamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				GamePage.setLayout(null);
//				GamePage.setResizable(false);
//				GamePage.setLocation(p);
//			}
//		});
		
		this.restart = new Button("Restart", null, 260,300,150,40);
		GamePageController gamepage = GamePageController.getInstance();
		gamepage.setFather(this);
		this.restart.bind(gamepage);
		this.add(this.restart);
		
		
//		this.score=new JButton("Score");
//		this.score.setBounds(260,200,150,40);
//		this.add(this.score);
//		this.score.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				scoreboard = ScoreBoard.getInstance();
//				scoreboard.readScoreBoard("data/1.txt");
//				scorepage = new ScoreDialog(postgame);
//				scorepage.setVisible(true);
//				scorepage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			}
//		});
		this.score = new Button("Scores", null, 260, 200, 150, 40);
		ScoreDialog scoredialog = new ScoreDialog(this);
		this.score.bind(scoredialog);
		this.add(this.score);
		
	}

	@Override
	public HashMap<String, String> getInfo() {
		// TODO Auto-generated method stub
		HashMap<String, String> gameinfo = new HashMap<String, String>();
		gameinfo.put("gamesize", (new Integer(this.gamesize)).toString());
		gameinfo.put("username", this.username);
		return gameinfo;
	}
}
