package basic.objects;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

public class Ship extends MovableGameObject {
	
	private float _size;
	private GeneralPath _shape;
		
	public Ship(float size) {
		this(size, 0, 0, 0);
	}
	
	public Ship(float size, float x, float y) {
		this(size, x, y, 0);
	}
	
	public Ship(float size, float x, float y, int rotation) {
		super();
		_size = size;
		this.setX(x);
		this.setY(y);
		this.setRotation(rotation);
	}

	public float getSize() {
		return _size;
	}
	
	public Shape getShape() {
		if (_shape == null) {
			_shape = new GeneralPath();
			_shape.moveTo(0.0f, -_size);
			_shape.lineTo(_size, _size);
			_shape.lineTo(-_size, _size);
			_shape.closePath();
			_shape.moveTo(0.0f, -_size);
			_shape.lineTo(0.0f, _size);

		}
		return _shape;
	}

}
