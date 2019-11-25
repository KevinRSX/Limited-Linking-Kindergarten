package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {
	
	private int W = 50;                  
    private Icon icon[];         
    private Icon icon_line[];     
    @SuppressWarnings("rawtypes")
	private ArrayList images_t;        
    @SuppressWarnings("rawtypes")
	private ArrayList label_arr;
    private int[] path_line;		
    
    private int index=-1;			
    @SuppressWarnings("unused")
	private Point p_index;			
    private int k;                  
    
    private int sum;               
    @SuppressWarnings("rawtypes")
	private ArrayList path;      
          
    private int GameSize;
    
	public GamePanel(int GameSize) {
		// TODO Auto-generated constructor stub
		this.GameSize = GameSize;
		
		setLayout(new GridLayout(GameSize, GameSize));
		setBounds((700-GameSize*W)/2,(600-GameSize*W)/2,GameSize*W,GameSize*W);
		setOpaque(false);
		
		initMap();
		showGame();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showGame() {
		// TODO Auto-generated method stub
		label_arr = new ArrayList();
		
		for(int i=0;i<GameSize*GameSize;i++) {
			if(i%GameSize==0 || i%GameSize==GameSize-1 || i/GameSize==0 || i/GameSize==GameSize-1) {
				JLabel j = new JLabel();
				j.setIcon(null);
				label_arr.add(j);
				add(j);
				continue;
			}
			int nIndex = new Random().nextInt(images_t.size());
			
			JLabel j = new JLabel(icon[(int) images_t.get(nIndex)]);
			label_arr.add(j);
			j.addMouseListener(this);
			add(j);
			
			images_t.remove(nIndex);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initMap() {
		// TODO Auto-generated method stub
		images_t = new ArrayList();
		path = new ArrayList();
		icon = new Icon[10];
		icon_line = new Icon[6];
		path_line = new int[GameSize*GameSize];
		
		for(int i=0;i<icon.length;i++) {
			icon[i] = new ImageIcon(getClass().getResource("/images/"+"fruit_"+(i+1)+".jpg"));
		}
		for(int i=0;i<icon_line.length;i++) {
			icon_line[i] = new ImageIcon(getClass().getResource("/images/"+"line_"+(i+1)+".png"));
		}
		for(int i=0;images_t.size()<(GameSize-2)*(GameSize-2);i++) {
			images_t.add(i%10);
			if(images_t.size()==(GameSize-2)*(GameSize-2)) {
				continue;
			}
			images_t.add(i%10);
		}
		sum = images_t.size();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
