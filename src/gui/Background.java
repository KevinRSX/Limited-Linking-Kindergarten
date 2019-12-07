package gui;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Background extends JLabel{
	public Background(String filepath, int width, int height) {
    	super(new ImageIcon(filepath));
        this.setBounds(0, 0, width, height);
	}
}
