package basic.objects;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public abstract class GameObject {
	
	protected float _x, _y;  // Object's coordinates
	protected float _rotation; // Object's rotation in degrees;

	public float getX() {
		return _x;
	}

	public void setX(float x) {
		_x = x;
	}

	public float getY() {
		return _y;
	}

	public void setY(float y) {
		_y = y;
	}
	
	public float getRotation() {
		return _rotation;
	}

	public void setRotation(float rotation) {
		this._rotation = rotation;
	}
	
	public abstract Shape getShape();

	public void draw(Graphics2D g2d) {
		final AffineTransform saved = g2d.getTransform();
	    final AffineTransform tx = AffineTransform.getTranslateInstance(getX(), getY());
	    g2d.transform(tx);
	    final AffineTransform rot = AffineTransform.getRotateInstance(Math.toRadians(getRotation()));
	    g2d.transform(rot);
	    g2d.draw(getShape());
	    g2d.setTransform(saved);
	}

}
