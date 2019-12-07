package gui;

import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PreGamePage extends AbstractPage implements ButtonBindable{
	
	private Background background;
	private JLabel label;
	private JTextField textField;
	private Button submit, cancel;
	
	private int gamesize;
	private AbstractPage father;
	
	public PreGamePage(int gamesize, AbstractPage father) {
		super("Limited Linking Kindergarten -- version 1.0.0");
		this.gamesize = gamesize;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
		setSize(650, 500);
		setLayout(null);
		
		setVisible(false);
		this.father = father;
		
		showBackground();
		showInfo();
//		adapter();
		
	}
	
//	private void adapter() {
//		// TODO Auto-generated method stub
//		submit.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (onButtonOk()) {
//					Point p = pregamepage.getLocation();
//					pregamepage.dispose();
//					GamePage gamepage = new GamePage(pregamepage.gamesize, pregamepage.name, pregamepage);
//					gamepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					gamepage.setLayout(null);
//					gamepage.setLocation(p);
//					gamepage.setVisible(true);
//					gamepage.setResizable(false);
//				}
//			}
//		});
//		cancel.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Point p = pregamepage.getLocation();
//				pregamepage.dispose();
//				Main.main(null);
//			}
//		});
//	}
	
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

//		submit  = new JButton("submit");
//		submit.setFont(new Font("acefont-family", Font.BOLD, 25));
//		submit.setBounds(230,250,150,40);
		this.submit = new Button("submit", new Font("acefont-family", Font.BOLD, 25), 230, 250, 150, 40);
		GamePageController gamepage = GamePageController.getInstance();
		gamepage.setFather(this);
		this.submit.bind(gamepage);
		
//		cancel  = new JButton("cancel");
//		cancel.setFont(new Font("acefont-family", Font.BOLD, 25));
//		cancel.setBounds(230,300,150,40);
		this.cancel = new Button("cancel", new Font("acefont-family", Font.BOLD, 25), 230, 300, 150, 40);
		((MainPage)this.father).setFather(this);
		this.cancel.bind((MainPage)this.father);
		
		
		add(label);
		add(textField);
		add(submit);
		add(cancel);
	}
	
	@SuppressWarnings("deprecation")
	private void showBackground() {
		// TODO Auto-generated method stub
//	 	ImageIcon background = new ImageIcon(getClass().getResource("/images/home.jpg"));
//        back = new JLabel(background);
//        back.setBounds(0, 0, getWidth(), getHeight());
//        JPanel j = (JPanel)getContentPane();
//        j.setOpaque(false);
//        getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));
		
        this.background = new Background("images/home.jpg",this.getWidth(), this.getHeight());
        
		JPanel contentPanel = (JPanel)this.getContentPane();
        contentPanel.setOpaque(false);
        getLayeredPane().add(this.background, new Integer(Integer.MIN_VALUE));
	}

//	private boolean onButtonOk()
//	{
//		this.name = textField.getText();
//		if(name.equals(""))
//		{
//			JOptionPane.showMessageDialog(this,"You need to input user name.");
//			return false;
//		}
//		else {
//			return true;
//		}
//	
//	}

	@SuppressWarnings("deprecation")
	public HashMap<String, String> getInfo() {
		HashMap<String, String> gameinfo = new HashMap<String, String>();
		gameinfo.put("gamesize", (new Integer(this.gamesize)).toString());
		gameinfo.put("username", this.textField.getText());
		return gameinfo;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		Point p = this.father.getLocation();
		this.father.dispose();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(p);
		this.setVisible(true);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		HashMap<String, String> gameinfo = this.father.getInfo();
		this.gamesize = Integer.parseInt(gameinfo.get("gamesize"));
		GamePageController.getInstance().setFather(this);
		((MainPage)this.father).setFather(this);
	}

}

