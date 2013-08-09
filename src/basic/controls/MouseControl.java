package basic.controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseControl extends MouseAdapter {
	
	private int _x;
	private int _y;

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		_x = e.getX();
		_y = e.getY();
		e.consume();
	}
	
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		_x = e.getX();
		_y = e.getY();
		e.consume();		
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}


}
