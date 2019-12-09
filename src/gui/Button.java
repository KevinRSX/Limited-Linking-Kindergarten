package gui;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Font FONT_STYLE = new Font("acefont-family", Font.BOLD, 20);
	private static final int BUTTON_HEIGHT = 180;
	private static final int BUTTON_WIDTH = 40;
	
	public Button(String name, Font font, int x, int y, int height, int width ) {
		super(name);
		this.setFont(font);
		this.setBounds(x, y, height, width);
	}
	
	public Button(String name, int x, int y) {
		super(name);
		this.setFont(FONT_STYLE);
		this.setBounds(x, y, BUTTON_HEIGHT, BUTTON_WIDTH);
	}
	
	public void bind(ButtonBindable binded_obj) {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				binded_obj.refresh();
				binded_obj.display();
			}
		});
	}
	
}
