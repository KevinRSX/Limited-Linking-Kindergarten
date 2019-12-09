package gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.HashMap;

import score.ScoreBoard;

@SuppressWarnings("serial")
public class MainPage extends AbstractPage implements ButtonBindable {
	
	private JLabel background;
	private Button start, score, setting; 
	
	private SettingDialog dialog;
	private ScoreDialog scoredialog;
	private JFrame father;
	
	// Singleton
	private static MainPage mainPage = new MainPage();
	public static MainPage getInstance() {
		return mainPage;
	}
	
	private MainPage() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
		this.setSize(650, 500);
		this.setLayout(null);
		
		this.dialog = new SettingDialog(this);
		
		this.showBackground();
		this.showButton();
	}
	
	private void showBackground() {
		this.background = new Background("images/home.jpg",this.getWidth(), this.getHeight());
        
		JPanel contentPanel = (JPanel)this.getContentPane();
        contentPanel.setOpaque(false);
        getLayeredPane().add(this.background, new Integer(Integer.MIN_VALUE));
	}
	
	private void showButton() {
		start = new Button("Start", 230, 150);
		PreGamePage pregamePage = new PreGamePage(this.dialog.getGame_size(), this);
		start.bind(pregamePage);
		
		score = new Button("Scores", 230, 200);
		scoredialog = new ScoreDialog(this);
		score.bind(scoredialog);
		
		setting = new Button("Settings", 230, 250);
		setting.bind(this.dialog);
		
		this.add(start);
		this.add(score);
		this.add(setting);
	}

	public HashMap<String, String> getInfo(){
		HashMap<String, String> gameinfo = new HashMap<String, String>();
		gameinfo.put("gamesize", (new Integer(this.dialog.getGame_size())).toString());
		return gameinfo;
	}

	public void setFather(JFrame f) {this.father = f;}
	
	@Override
	public void display() {
		System.out.println(this.father);
		Point p = this.father.getLocation();
		this.father.dispose();
		this.father = null;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(p);
		this.setVisible(true);
	}

	@Override
	public void refresh() {
		this.scoredialog.refresh();
	}

}

