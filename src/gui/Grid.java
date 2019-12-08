package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

import linkAlgorithm.Linkable;

public class Grid extends JLabel implements Linkable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean selected;
	
	public Grid() {
		super();
	}

	public Grid(Icon icon) {
		super(icon);
	}

	@Override
	public boolean canPassThrough() {
		return getIcon() == null;
	}

	@Override
	public void select() {
		setBorder(BorderFactory.createLineBorder(Color.PINK, 2));
		selected = true;
	}

	@Override
	public void deselect() {
		setBorder(null);
		selected = false;
	}

	@Override
	public void cancel() {
		setIcon(null);
	}
	

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean sameType(Linkable another) {
		if (this.getIcon() == null && ((Grid)another).getIcon() == null) return true;
		return this.getIcon().equals(((Grid)another).getIcon());
	}
}
