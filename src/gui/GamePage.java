package gui;

import timer.Timer;
import timer.TimerChangeException;
import timer.TimerStartErrorException;
import timer.TimerTerminateErrorException;
import timer.Stoppable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import score.ScoreBoard;

@SuppressWarnings("serial")
public class GamePage extends AbstractPage implements Stoppable{
	private Background background;
	private GamePage game;
	private Button home, restart;
	private JButton hint;
	private int gamesize;
	private String username;
	private JProgressBar jpb;
	private Timer timer; 
	private Board jpanel;
	private int hintNum; // refactor here (state pattern)
	
	private JFrame father;
	
	
	
	public GamePage(int gamesize, String username, JFrame f) {
		super("Link And Cancel!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(("images/icon.png")));
		setSize(800, 700);
		
		this.father = f;
		this.hintNum = 3;
		this.gamesize = gamesize + 2;
		
		this.username=username;
		this.jpanel = new Board(this.gamesize, this);
		add(this.jpanel);

		showBackground();
		showMenu();
		showTime();
		
		game = this;
		this.setVisible(false);

	}
	
	@SuppressWarnings("deprecation")
	private void showBackground() {
		this.background = new Background("images/background.png",this.getWidth(), this.getHeight());
        
		JPanel contentPanel = (JPanel)this.getContentPane();
        contentPanel.setOpaque(false);
        getLayeredPane().add(this.background, new Integer(Integer.MIN_VALUE));
	}
	
	private void showTime() {
		int time_to_give, bonus_to_give, pun_to_give;
		if (gamesize - 2 == 2) {
			time_to_give = 15;
			bonus_to_give = pun_to_give = 1;
		} else {
			time_to_give = (gamesize - 2) * 15;
			pun_to_give = (gamesize - 2)/2 + 1;
		}
		if (gamesize - 2 == 4 || gamesize - 2 == 6) {
			bonus_to_give = 1;
		} else {
			bonus_to_give = 2;
		}
		jpb = new JProgressBar();
		jpb.setOrientation(JProgressBar.HORIZONTAL);
		jpb.setMinimum(0);
		jpb.setMaximum(time_to_give);
		jpb.setValue(0);
		jpb.setBackground(new Color(238,226,29));
		jpb.setBounds(175, 25, 350, 12);
		add(jpb);
		
		timer = Timer.getInstance();
		timer.setUp(this, jpb, time_to_give, bonus_to_give, pun_to_give);
		try {
			timer.start();
		} catch (TimerStartErrorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
	    g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, 
	             BasicStroke.JOIN_ROUND, 1f));
        g2.setColor(Color.CYAN);
	    jpanel.showPath(g2, getInsets());
	}
	
	protected void endGame(boolean is_finished, int score) {
		super.dispose();
        
		// write the record to score file
		ScoreBoard.getInstance().wirteScoreBoard("data/1.txt", this.username, score);
		
		// Turn to post game page
        Point p = game.getLocation();
		PostGamePage postgame=new PostGamePage(is_finished, username, gamesize, score);
		
		postgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		postgame.setLayout(null);
		postgame.setLocation(p);
		postgame.setResizable(false);

	}
	
	private void showMenu() {
		this.home = new Button("Home", null, 10, 10, 90, 40);
		this.home.bind(MainPage.getInstance());
		MainPage.getInstance().setFather(this);
		this.add(this.home);
		
		this.restart = new Button("Restart", null, 10, 60, 90, 40);
		GamePageController gamepage = GamePageController.getInstance();
		gamepage.setFather(this);
		this.restart.bind(gamepage);
		this.add(this.restart);

		this.hint = new JButton("Hint (" + hintNum + ")");
		this.hint.setBounds(10, 110, 90, 40);
		this.hint.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (hintNum > 0) {
					jpanel.showHint();
					hintNum--;
					hint.setText("Hint (" + hintNum + ")");
				}
			}
		});
		this.add(this.hint);
	}
	
	public void stopTimer() throws TimerTerminateErrorException {
		timer.cancel();
	}
	
	public void incTime() throws TimerChangeException {
		timer.increaseTime();
	}
	
	public void decTime() throws TimerChangeException {
		timer.decreaseTime();
	}
	
	@Override
	public boolean stop() {
		int score=((gamesize-2)*(gamesize-2)-jpanel.remainingNum())*10;
		endGame(false, score);
		return true;
	}
	
	@Override
	public void dispose() {
		if(timer != null)
		try {
			timer.cancel();
		} catch (TimerTerminateErrorException e1) {
			e1.printStackTrace();
		}
		super.dispose();
	}
	

	@Override
	protected HashMap<String, String> getInfo() {
		// TODO Auto-generated method stub
		HashMap<String, String> gameinfo = new HashMap<String, String>();
		gameinfo.put("gamesize", (new Integer(this.gamesize-2)).toString());
		gameinfo.put("username", this.username);
		return gameinfo;
	}
}
