package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Score.ScoreBoard;


@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener {
	
	private JLabel back;
	private JButton start, score, setting;
	private MainPage f;
	private Dialog dialog;
	private ScorePage s;
	private String username;
	
	public MainPage(String name) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		setSize(650, 500);
		setLayout(null);

		
		showBackground();
		showButton();
		adapter();
		
		username=name;
		f = this;
		dialog = new Dialog(f);
		dialog.setVisible(false);
		ScoreBoard.showScoreBoard("src/data/1.txt");
		Object[][] arr = ScoreBoard.getScores();
		s = new ScorePage(arr, f);
		
	}
	
	private void adapter() {
		// TODO Auto-generated method stub
		
//		button01.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				toLevel();
//			}
//		});
//		
		start.addActionListener(this);
		score.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(true);
				s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
			
		setting.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(true);
			}
		});
		
	}
	
	private void showButton() {
		// TODO Auto-generated method stub
		start = new JButton("Start");
		start.setFont(new Font("acefont-family", Font.BOLD, 25));
		start.setBackground(Color.RED);
		start.setBounds(260, 200, 150, 40);

		score = new JButton("Scores");
		score.setFont(new Font("acefont-family", Font.BOLD, 25));
		score.setBackground(Color.GREEN);
		score.setBounds(260, 250, 150, 40);
		 
		setting = new JButton("Settings");
     	setting.setFont(new Font("acefont-family", Font.BOLD, 25));
     	setting.setBackground(Color.BLUE);
	    setting.setBounds(260, 300, 150, 40);

		add(start);
		add(score);
		add(setting);
	}
	
	private void showBackground() {
		// TODO Auto-generated method stub
	 	ImageIcon background = new ImageIcon(getClass().getResource("/images/background.jpg"));
        back = new JLabel(background);
        back.setBounds(0, 0, getWidth(), getHeight());
        JPanel j = (JPanel)getContentPane();
        j.setOpaque(false);
        getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Point p = PreGamePage.getMainPageLocation();
		PreGamePage.disposeMainPage();
		GamePage gamePage = new GamePage(this.dialog.getGame_size(), username,-1);
		gamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePage.setLayout(null);
		gamePage.setResizable(false);
		gamePage.setLocation(p);
	}

}

