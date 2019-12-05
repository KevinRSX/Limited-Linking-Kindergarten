package gui;

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
import java.io.FileWriter;
import java.io.IOException;
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

import score.ScoreBoard;
import game.Main;

@SuppressWarnings("serial")
public class PreGamePage extends JFrame{
	
	private JLabel back;
	private JLabel label;
	private JTextField textField;
	private JButton submit, cancel;
	private String name;
	private int gamesize;
	private PreGamePage pregamepage;
	
	public PreGamePage(int gamesize) {
		super("Limited Linking Kindergarten -- version 1.0.0");
		this.gamesize = gamesize;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		setSize(650, 500);
		setLayout(null);
		
		setVisible(true);
		this.pregamepage = this;
		
		showBackground();
		showInfo();
		adapter();
		
	}
	
	private void adapter() {
		// TODO Auto-generated method stub
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onButtonOk()) {
					Point p = pregamepage.getLocation();
					pregamepage.dispose();
					GamePage gamepage = new GamePage(pregamepage.gamesize, pregamepage.name);
					gamepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					gamepage.setLayout(null);
					gamepage.setLocation(p);
					gamepage.setVisible(true);
					gamepage.setResizable(false);
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Point p = pregamepage.getLocation();
				pregamepage.dispose();
				Main.main(null);
			}
		});
	}
	
	private void showInfo() {
		// TODO Auto-generated method stub
		label = new JLabel("Please Input User Name"); 
		label.setFont(new Font("acefont-family", Font.BOLD, 22));
		label.setBounds(180,150,300,45);
		Dimension d = label.getPreferredSize();
	    label.setPreferredSize(new Dimension(d.width+60,d.height));

		textField = new JTextField(20);
		textField.setFont(new Font("acefont-family", Font.BOLD, 25));
		textField.setBounds(180,200,250,40);

		submit  = new JButton("submit");
		submit.setFont(new Font("acefont-family", Font.BOLD, 25));
		submit.setBounds(230,250,150,40);
		
		cancel  = new JButton("cancel");
		cancel.setFont(new Font("acefont-family", Font.BOLD, 25));
		cancel.setBounds(230,300,150,40);
		
		add(label);
		add(textField);
		add(submit);
		add(cancel);
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
			JOptionPane.showMessageDialog(this,"Welcome to Limited-Linking-Kindergarten,	 " + name );
			return true;
		}
	
	}

}

