package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.Score.ScoreBoard;


@SuppressWarnings("serial")
public class PreGamePage extends JFrame implements ActionListener {
	
	private JLabel back;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private String name;
	private static MainPage mainpage;
	
	public PreGamePage(int i) {
		super("Limited Linking Kindergarten -- version 1.0.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		setSize(900, 894);
		setLayout(null);
		
		showBackground();
		showInfo();
		adapter();
		
	}
	
	public static void setMainPageLocation(Point p) {
		mainpage.setLocation(p);
	}
	
	public static Point getMainPageLocation() {
		return mainpage.getLocation();
	}
	
	public static void disposeMainPage() {
		mainpage.dispose();
	}
	
	private void adapter() {
		// TODO Auto-generated method stub
		button.addActionListener(this);
		
	}
	
	private void showInfo() {
		// TODO Auto-generated method stub
		label = new JLabel("Please Input User Name"); 
		label.setFont(new Font("acefont-family", Font.BOLD, 22));
		label.setBounds(210,200,300,45);
		Dimension d = label.getPreferredSize();
	    label.setPreferredSize(new Dimension(d.width+60,d.height));

		textField = new JTextField(20);
		textField.setFont(new Font("acefont-family", Font.BOLD, 25));
		textField.setBounds(210,250,250,40);

		button  = new JButton("submit");
		button.setFont(new Font("acefont-family", Font.BOLD, 25));
		button.setBounds(260,300,150,40);
		
		add(label);
		add(textField);
		add(button);
	}
	
	private void showBackground() {
		// TODO Auto-generated method stub
	 	ImageIcon background = new ImageIcon(getClass().getResource("/images/home.jpg"));
        back = new JLabel(background);
        back.setBounds(0, 0, getWidth(), getHeight());
        JPanel j = (JPanel)getContentPane();
        j.setOpaque(false);
        getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));
	}

	private boolean onButtonOk()
	{
		this.name = textField.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(this,"You need to input user name.");
			return false;
		}
		else {
			JOptionPane.showMessageDialog(this,"Welcome to Limited-Linking-Kindergarten, " + name + "!");
			return true;
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (onButtonOk()) {
			Point p = this.getLocation();
			Main.disposePreGamePage();
			mainpage = new MainPage(name);
			mainpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainpage.setLayout(null);
			mainpage.setLocation(p);
			mainpage.setVisible(true);
			mainpage.setResizable(false);
		}
	}

}

