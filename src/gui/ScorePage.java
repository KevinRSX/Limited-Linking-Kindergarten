package gui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ScorePage extends JDialog{
	/**
	 * 
	 */
	
	private JTable ScoreTable;
	private String[] columnNames = {"Name", "Score", "Time"};

	public ScorePage(Object[][] arr, JFrame f) {
		super(f, true);
		
		setTitle("ScoreBoard");
		setSize(400,400);
		setBounds(f.getBounds().x+75, f.getBounds().y+75, 500, 350);
		Container ScorePanel = this.getContentPane();
		ScoreTable = new JTable(arr, columnNames);
		
		placeComponents(ScorePanel);
		setVisible(false);
	}

	private void placeComponents(Container panel) {
		panel.add(new JScrollPane(ScoreTable));
		ScoreTable.setEnabled(false);
		
	}
	
	
}
