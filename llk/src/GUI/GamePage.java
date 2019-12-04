package GUI;

import GUI.Timer.Timer;
import GUI.Timer.TimerChangeException;
import GUI.Timer.TimerStartErrorException;
import GUI.Timer.TimerTerminateErrorException;
import GUI.Timer.Stoppable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Timer;
//import java.util.TimerTask;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class GamePage extends JFrame implements Stoppable{
	private JLabel back;
	private GamePage g;
	private JButton home, restart, hint;
	private int GameSize;
	private String username;
	private JProgressBar jpb;
	private Timer timer; 
	private Board jpanel;
	private int hintNum; // refactor here (state pattern)
	
	
	
	public GamePage(int GameSize, String username) {
		super("Link And Cancel!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
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
		int time_to_give, bonus_to_give, pun_to_give;
		if (GameSize - 2 == 2) {
			time_to_give = 15;
			bonus_to_give = pun_to_give = 1;
		} else {
			time_to_give = (GameSize - 2) * 15;
			pun_to_give = (GameSize - 2)/2 + 1;
		}
		if (GameSize - 2 == 4 || GameSize - 2 == 6) {
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
		/*
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
		*/
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
		this.dispose();
		FileWriter fw = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		score+=timer.getRemainingTime()*5;
//		PreGamePage.clearInfoForFile("src/data/1.txt");
        try {
            fw = new FileWriter("src/data/1.txt",true);
            fw.write(username+", " + score + ", " + df.format(new Date()) + "\n");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(null != fw) {
                    try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
            }
        }
        
        Point p = g.getLocation();
		PostGamePage postgame=new PostGamePage(is_finished,GameSize ,username,this);
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
//				PreGamePage.setMainPageLocation(p);
				try {
					timer.cancel();
				} catch (TimerTerminateErrorException e1) {
					e1.printStackTrace();
				}
			}
		});

		this.restart.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timer != null)
					try {
						timer.cancel();
					} catch (TimerTerminateErrorException e1) {
						e1.printStackTrace();
					}
				Point p = g.getLocation();
				g.dispose();
				GamePage GamePage;
				GamePage = new GamePage(GameSize - 2, username);
				GamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GamePage.setLayout(null);
				
				GamePage.setResizable(false);
				GamePage.setLocation(p);
				
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
		int score=((GameSize-2)*(GameSize-2)-jpanel.remainingNum())*10;
		try {
			this.stopTimer();
		} catch (TimerTerminateErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endGame(false, score);
		return true;
	}
}
