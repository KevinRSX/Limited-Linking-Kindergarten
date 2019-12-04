package GUI;

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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import GUI.Score.ScoreBoard;

public class PostGamePage extends JFrame {
	private JLabel back;
	private int GameSize;
	private JLabel label;
	private JButton home, restart,score;
	private boolean is_finished;
	private String username;
	private PostGamePage postgame;
	private ScorePage scorepage;
	private ScoreBoard scoreboard;
	private int points;
	
	public PostGamePage(boolean finished, int gamesize,String username,GamePage gp, int points) {
		super("Limited Time");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		setSize(700, 600);
		
		ImageIcon background = new ImageIcon(getClass().getResource("/images/background.png"));
		back = new JLabel(background);
		back.setBounds(0, 0, getWidth(), getHeight());

		JPanel j = (JPanel)getContentPane();
		j.setOpaque(false);
		j.setLayout(null);
		getLayeredPane().add(back, Integer.valueOf(Integer.MIN_VALUE));

		this.is_finished=finished;
		this.GameSize=gamesize;
		this.username=username;
		this.postgame=this;
		this.points = points;
		
		scoreboard = new ScoreBoard();
		scoreboard.showScoreBoard("src/data/1.txt");
		Object[][] arr = scoreboard.getScores();
		scorepage = new ScorePage(arr, this);
		
		showMenu();
		setVisible(true);
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
		
		this.score=new JButton("Score");
		this.score.setBounds(260,200,150,40);
		this.add(this.score);
		
		this.home = new JButton("Home");
		this.home.setBounds(260,250,150,40);
		this.add(this.home);
		
		this.restart = new JButton("Restart");
		this.restart.setBounds(260,300,150,40);
		this.add(this.restart);

		this.home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Point p = postgame.getLocation();
				postgame.dispose();
				Main.main(null);
//				PreGamePage.setMainPageLocation(p);
			}
		});

		this.restart.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Point p = postgame.getLocation();
				postgame.dispose();
				GamePage GamePage;
				GamePage = new GamePage(GameSize - 2, username);
				GamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GamePage.setLayout(null);
				GamePage.setResizable(false);
				GamePage.setLocation(p);
			}
		});
		this.score.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scorepage.setVisible(true);
				scorepage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	}
}
