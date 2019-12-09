package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PostGamePage extends AbstractPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			
		this.home = new Button("Home", null, 260,250,150,40);
		MainPage.getInstance().setFather(this);
		this.home.bind(MainPage.getInstance());
		this.add(this.home);

		
		this.restart = new Button("Restart", null, 260,300,150,40);
		GamePageController gamepage = GamePageController.getInstance();
		gamepage.setFather(this);
		this.restart.bind(gamepage);
		this.add(this.restart);

		this.score = new Button("Scores", null, 260, 200, 150, 40);
		ScoreDialog scoredialog = new ScoreDialog(this);
		this.score.bind(scoredialog);
		this.add(this.score);
		
	}

	@Override
	public HashMap<String, String> getInfo() {
		HashMap<String, String> gameinfo = new HashMap<String, String>();
		gameinfo.put("gamesize", (new Integer(this.gamesize)).toString());
		gameinfo.put("username", this.username);
		return gameinfo;
	}
}
