package gui;

import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class SettingDialog extends JDialog implements ItemListener, ButtonBindable{
	private final static int MAX_SIZE = 10;
	
	private JLabel sizeLabel, aboutLabel;
	private JComboBox sizeBox;
	private JScrollPane jta;
	
	private int game_size;
	private ArrayList<Integer> sizeList;
	private JFrame father;

	public SettingDialog(JFrame f) {
		super(f, true);
		this.sizeList = new ArrayList<Integer>();
		this.game_size = 2;
		this.father = f;
		
		setLayout(null);
		setTitle("Settings");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(false);
		showSetting(f);
	}

	@SuppressWarnings("unchecked")
	private void showSetting(JFrame f) {
		sizeLabel = new JLabel("Game Size:");
		sizeLabel.setFont(new Font("acefont-family", Font.BOLD, 15));
		sizeLabel.setBounds(10, 10, 100, 20);
		add(sizeLabel);	
		
		for(int i=2;i<=SettingDialog.MAX_SIZE;i+=2) {
			sizeList.add(i);
		}
		
		this.sizeBox = new JComboBox(sizeList.toArray());
		this.sizeBox.setBounds(120, 10, 100, 20);
		this.sizeBox.addItemListener(this);
		this.sizeBox.setSelectedIndex(sizeList.indexOf(this.game_size));
		add(sizeBox);
		
		// about authors
		aboutLabel = new JLabel("Authors:");
		aboutLabel.setFont(new Font("acefont-family", Font.BOLD, 15));
		aboutLabel.setBounds(10, 75, 100, 20);	
		add(aboutLabel);
		
		JTextArea textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setBounds(20, 110, 300, 150);
		
		textarea.setText("Project Group 6:\n"
				  + "\nGame Logic and Alogorithm:\n"
				  + "Kaiwen Xue\n"
				  + "Qihua Dong\n"
				  + "\nMain game and Timer:\n"
				  + "Chengyu Sun\n"
				  + "Ruikang Li\n"
				  + "\nGUI and Scoreboard Design:\n"
				  + "Zelin Ning\n"
				  + "Deheng Zhang");

		jta = new JScrollPane(textarea);
		jta.setBounds(20, 110, 300, 150);
		add(jta);
		
	}

	public int getGame_size() {
		return game_size;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		switch (e.getStateChange())
		{
			case ItemEvent.SELECTED: 
				System.out.println("Selected " + e.getItem());
				this.game_size = (int) e.getItem();
				break;
			case ItemEvent.DESELECTED:
				System.out.println("Unselected "+e.getItem());
				break;
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		setBounds(this.father.getBounds().x+75, this.father.getBounds().y+75, 500, 350);
	}

}

