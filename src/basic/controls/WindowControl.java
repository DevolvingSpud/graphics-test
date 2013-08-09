package basic.controls;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowControl extends ComponentAdapter {
	
	private int _componentWidth;
	private int _componentHeight;

	@Override
	public void componentResized(ComponentEvent e) {
		super.componentResized(e);
		_componentWidth = e.getComponent().getWidth();
		_componentHeight = e.getComponent().getHeight();
	}

	public int getComponentWidth() {
		return _componentWidth;
	}

	public int getComponentHeight() {
		return _componentHeight;
	}
	
	

}
