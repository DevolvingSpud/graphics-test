package basic.objects;

public abstract class MovableGameObject extends GameObject {

	private int _maxRotation;  // Maximum rotation speed in degrees per tick

	public int getMaxRotation() {
		return _maxRotation;
	}

	public void setMaxRotation(int maxRotation) {
		this._maxRotation = maxRotation;
	}
	
	@Override
	public void setRotation(float rotation)
	{
		float delta = rotation - _rotation;
		
		if (Math.abs(delta) < 0.5) {
			rotation = _rotation;
		} else if (delta > _maxRotation) {
			rotation = _rotation + _maxRotation;
		} else if (delta < -_maxRotation) {
			rotation = _rotation -_maxRotation;
		}
		super.setRotation(rotation);
	}


}
