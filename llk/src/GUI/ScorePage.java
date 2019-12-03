package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import GUI.Score.ScoreBoard;

@SuppressWarnings("serial")
public class ScorePage extends JDialog{
	/**
	 * 
	 */
	
	//private JPanel ScorePanel;
	private JTable ScoreTable;
//	private JButton button;
	private String[] columnNames = {"Name", "Score", "Time"};

	public ScorePage(Object[][] arr, JFrame f) {
		super(f, true);
		setTitle("ScoreBoard");
		setSize(400,400);
		setBounds(f.getBounds().x+75, f.getBounds().y+75, 500, 350);
		//ScorePanel = new JPanel();
//		button = new JButton("save as");
		Container ScorePanel = this.getContentPane();
		arr = ScoreBoard.getScores();
		ScoreTable = new JTable(arr, columnNames);
		
		placeComponents(ScorePanel);
		setVisible(false);
	}

	private void placeComponents(Container panel) {
		
		panel.add(new JScrollPane(ScoreTable));
		ScoreTable.setEnabled(false);
		
//		button.addActionListener(new ActionListener() {  
//			@Override
//			public void actionPerformed(ActionEvent e) {			 
//				JFileChooser chooser = new JFileChooser();            
//				//chooser.setMultiSelectionEnabled(true);            
//				int returnVal = chooser.showOpenDialog(button);      
//				System.out.println("returnVal="+returnVal);
//			 
//				if (returnVal == JFileChooser.APPROVE_OPTION) {      
//			 
//					String filepath = chooser.getSelectedFile().getAbsolutePath();     
//					System.out.println(filepath);		 
//	 
//				}
//			}	
//		});

		
	}
	
	
}
