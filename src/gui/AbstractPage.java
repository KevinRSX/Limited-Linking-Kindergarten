package gui;

import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractPage extends JFrame{
//	public AbstractPage() {
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
//		this.setLayout(null);
//        JPanel contentPanel = (JPanel)this.getContentPane();
//        contentPanel.setOpaque(false);
//	}
	
	public AbstractPage() {}
	
	public AbstractPage(String name) {
		super(name);
	}
	
	protected abstract HashMap<String, String> getInfo();
	
	
}
