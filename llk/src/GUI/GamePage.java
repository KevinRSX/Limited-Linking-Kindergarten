package GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GamePage extends JFrame{
	private JLabel back;
	private GamePage g;
	private JButton home, restart, hint;
	private int LEVEL;
	private int GameSize;
	private JProgressBar jpb;
	private Timer timer; 
	private GamePanel jpanel;
	
	public GamePage(int GameSize,int t) {
		super("Limited Time");
		setIconImage(Toolkit.getDefaultToolkit ().getImage(getClass().getResource("/images/icon.png")));
		LEVEL = t;
		this.GameSize = GameSize+2;
		setSize(700, 600);
		
		ImageIcon background = new ImageIcon(getClass().getResource("/images/background.png"));
		back = new JLabel(background);
		back.setBounds(0, 0, getWidth(), getHeight());

		JPanel j = (JPanel)getContentPane();
		j.setOpaque(false);
		getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));
		setVisible(true);

		showMenu();
		showTime();

		this.jpanel = new GamePanel(this.GameSize);
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
				// TODO Auto-generated method stub
				jpb.setValue(jpb.getValue()+1);
				if(jpb.getValue() > 80)
					jpb.setForeground(Color.RED);
				if(jpb.getValue() == 100) {
					timer.cancel();
					// new Dialog(g, 2, LEVEL,"haha");
				}
			}
		}, 0, 900);
		
	}
	
	private void showMenu() {
		this.home = new JButton("Home");
		this.home.setBounds(10, 10, 60, 40);
		this.add(this.home);
		
		this.restart = new JButton("Restart");
		this.restart.setBounds(10, 60, 60, 40);
		this.add(this.restart);

		this.hint = new JButton("Hint");
		this.hint.setBounds(10, 110, 60, 40);
		this.add(this.hint);

		this.home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Point p = g.getLocation();
				g.dispose();
				Main.main(null);
				Main.setMainPageLocation(p);
				timer.cancel();
			}
		});

//		restart.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				Point p = g.getLocation();
//				g.dispose();
//				GamePage GamePage;
//				GamePage = new GamePage(GameSize-2,LEVEL);
//				GamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				GamePage.setLayout(null);
//				
//				GamePage.setResizable(false);
//				GamePage.setLocation(p);
//				if(timer != null)
//					timer.cancel();
//			}
//		});
		
	}
}
