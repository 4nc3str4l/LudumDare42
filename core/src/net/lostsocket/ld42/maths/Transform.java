package net.lostsocket.ld42.maths;

import com.badlogic.gdx.math.Vector2;

public class Transform {
	
	public Vector2 position = new Vector2(0, 0);
	public float rotation = 0;
	public Vector2 scale = new Vector2(1, 1);
	private Vector2 forward = new Vector2(0, 0);
	private Vector2 right = new Vector2(0, 0);
	
	public void moveForward(float amount) {
		position.x += forward.x * amount;
		position.y += forward.y * amount;;
	}
	
	public void moveBack(float amount) {
		position.x -= forward.x * amount;
		position.y -= forward.y * amount;;
	}
	
	public void moveRight(float amount) {
		position.x += right.x * amount;
		position.y += right.y * amount;;
	}
	
	public void moveLeft(float amount) {
		position.x -= right.x * amount;
		position.y -= right.y * amount;;
	}
	
	public void lookAt(float x, float y) {
		Vector2 target = new Vector2(x, y);
		Vector2 diff = new Vector2(target.x- position.x,
				target.y - position.y);
		setRotation((float)Math.toDegrees(Math.atan2(diff.y, diff.x)) - 90);
	}
	
	private void calcForward() {
		forward.x = -(float) Math.sin(Math.toRadians(rotation));
		forward.y =  (float) Math.cos(Math.toRadians(rotation));
	}
	
	private void calcRight() {
		right.x = -(float) Math.sin(Math.toRadians(rotation - 90));
		right.y =  (float) Math.cos(Math.toRadians(rotation - 90));
	}
	
	public void setRotation(float newRot) {
		rotation = newRot;
		calcForward();
		calcRight();
	}
}
