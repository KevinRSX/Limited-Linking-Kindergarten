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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener{
	
	private JLabel back;
	private JButton button01, button02, button03;
	private MainPage f;
	private Dialog dialog;
	
	public MainPage(int i) {
		super("Limited Linking Kindergarten -- version 1.0.0");
		setIconImage(Toolkit.getDefaultToolkit ().getImage(getClass().getResource("/images/icon.png")));
		setSize(650, 500);
		setLayout(null);

		showBackground();
		showButton();
		adapter();
		
		f = this;
		dialog = new Dialog(f);
		dialog.setVisible(false);
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
		
		button01.addActionListener(this);

		button03.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(true);
			}
		});
		
	}
	
	private void showButton() {
		// TODO Auto-generated method stub
		button01 = new JButton("Start");
		button01.setFont(new Font("acefont-family", Font.BOLD, 25));
		button01.setBackground(Color.RED);
		button01.setBounds(260, 200, 150, 40);

		button02 = new JButton("Scores");
		button02.setFont(new Font("acefont-family", Font.BOLD, 25));
		button02.setBackground(Color.GREEN);
		button02.setBounds(260, 250, 150, 40);
		 
		button03 = new JButton("Settings");
     	button03.setFont(new Font("acefont-family", Font.BOLD, 25));
     	button03.setBackground(Color.BLUE);
	    button03.setBounds(260, 300, 150, 40);

		add(button01);
		add(button02);
		add(button03);
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
		Point p = Main.getMainPageLocation();
		Main.disposeMainPage();
		GamePage gamePage = new GamePage(this.dialog.getGame_size(),-1);
		gamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePage.setLayout(null);
		gamePage.setResizable(false);
		gamePage.setLocation(p);
	}

}

