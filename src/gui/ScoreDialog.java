package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import score.ScoreBoard;

@SuppressWarnings("serial")
public class ScoreDialog extends JDialog implements ButtonBindable{
	/**
	 * 
	 */
	private static final String[] columnNames = {"Name", "Score", "Time"};
	private JFrame father;
	
	public ScoreDialog(JFrame f) {
		super(f, true);

		setTitle("ScoreBoard");
		setSize(400,400);
		
		this.father = f;
		renderTable(this.getContentPane());
		setVisible(false);
	}

	private void renderTable(Container panel) {
		panel = new JPanel();
		ScoreBoard scoreboard = ScoreBoard.getInstance();
		JTable scoretable = new JTable(scoreboard.getScores("data/1.txt"), columnNames);
		String[][] arr = scoreboard.getScores("data/1.txt");
		for(String[] record: arr) {
			System.out.println(record[0] + " " +record[1] + " "+record[2] );
		}
		panel.add(new JScrollPane(scoretable));
		this.setContentPane(panel);
		scoretable.setEnabled(false);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		this.renderTable(this.getContentPane());
		setBounds(this.father.getBounds().x+75, this.father.getBounds().y+75, 500, 350);
	}
	
	
}
