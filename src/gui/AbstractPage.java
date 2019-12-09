package gui;

import java.util.HashMap;

import javax.swing.JFrame;

public abstract class AbstractPage extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractPage() {}
	
	public AbstractPage(String name) {
		super(name);
	}
	
	protected abstract HashMap<String, String> getInfo();
	
	
}
