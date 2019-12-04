package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GamePage extends JFrame {
	private JLabel back;
	private GamePage g;
	private JButton home, restart, hint;
	private int LEVEL;
	private int GameSize;
	private String username;
	private JProgressBar jpb;
	private Timer timer; 
	private Board jpanel;
	private int hintNum; // refactor here (state pattern)
	
	
	
	public GamePage(int GameSize, String username, int t) {
		super("Link And Cancel!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		LEVEL = t;
		hintNum = 3;
		this.GameSize = GameSize + 2;
		setSize(800, 700);
		
		ImageIcon background = new ImageIcon(getClass().getResource("/images/background.png"));
		back = new JLabel(background);
		back.setBounds(0, 0, getWidth(), getHeight());

		JPanel j = (JPanel)getContentPane();
		j.setOpaque(false);
		getLayeredPane().add(back, Integer.valueOf(Integer.MIN_VALUE));
		setVisible(true);

		showMenu();
		showTime();

		this.username=username;
		this.jpanel = new Board(this.GameSize, this);
		add(this.jpanel);
		g = this;
	}

	private void showTime() {
		jpb = new JProgressBar();
		jpb.setOrientation(JProgressBar.HORIZONTAL);
		jpb.setMinimum(0);
		jpb.setMaximum(100);
		jpb.setValue(0);
		jpb.setBackground(new Color(238,226,29));
		jpb.setBounds(175, 25, 350, 12);
		add(jpb);
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				jpb.setValue(jpb.getValue()+1);
				if(jpb.getValue() > 80)
					jpb.setForeground(Color.RED);
				if(jpb.getValue() == 100) {
					timer.cancel();
				}
			}
		}, 0, 900);
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
	
	protected void endGame(boolean is_finished,int score) {
		this.dispose();
		FileWriter fw = null;
        try{
            fw = new FileWriter("src/data/1.txt",true);
            fw.write("\n"+username+", "+score+", 1000/3/1"); //need to use timer
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(null != fw){
                    try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
            }
        }
        Point p = g.getLocation();
		PostGamePage postgame=new PostGamePage(is_finished,GameSize,LEVEL,username,this);
		postgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		postgame.setLayout(null);
		postgame.setLocation(p);
		
		postgame.setResizable(false);
	}
	
	private void showMenu() {
		this.home = new JButton("Home");
		this.home.setBounds(10, 10, 90, 40);
		this.add(this.home);
		
		this.restart = new JButton("Restart");
		this.restart.setBounds(10, 60, 90, 40);
		this.add(this.restart);

		this.hint = new JButton("Hint (" + hintNum + ")");
		this.hint.setBounds(10, 110, 90, 40);
		this.add(this.hint);

		this.home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Point p = g.getLocation();
				g.dispose();
				Main.main(null);
				PreGamePage.setMainPageLocation(p);
				timer.cancel();
			}
		});

		this.restart.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Point p = g.getLocation();
				g.dispose();
				GamePage GamePage;
				GamePage = new GamePage(GameSize - 2, username, LEVEL);
				GamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GamePage.setLayout(null);
				
				GamePage.setResizable(false);
				GamePage.setLocation(p);
				if(timer != null)
					timer.cancel();
			}
		});
		
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
	}
}
