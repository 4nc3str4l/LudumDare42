package net.lostsocket.ld42.entities;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.TimedDestroyComponent;
import net.lostsocket.ld42.maths.Maths;

public class BloodStain extends Entity {
	
	private float targetScaleX;
	private float targetScaleY;
	
	private float moveTime;
	private float initialMoveTime;
	
	private float moveSpeed;
	private Vector2 forward;
	
	public BloodStain(Bullet bullet) {
		super(0);
		addComponent(new TimedDestroyComponent(Maths.getRandomFloat(10, 25)));
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 5));
		
		transform.setRotation(Maths.getRandomFloat(0, 360));
		
		transform.forward = new Vector2(bullet.transform.forward);
		transform.scale.x = 0f;
		transform.scale.y = 0f;
		targetScaleX = Maths.getRandomFloat(0.2f, 1);
		targetScaleY = Maths.getRandomFloat(0.2f, 1);
		moveTime = (bullet.pushBack / 100f) + 0.03f;
		initialMoveTime = moveTime;
		transform.position = new Vector2(bullet.transform.position);
		moveSpeed = bullet.SPEED / 4f;
	}

	@Override
	public void customUpdate(float delta) {
		
		if(moveTime <= 0)
			return;
		
		moveTime -= delta;
		
		float t = 1 - (moveTime / initialMoveTime);
		transform.moveForward(moveSpeed * delta);
		transform.scale.x = targetScaleX * t;
		transform.scale.y = targetScaleY * t;
	}

	@Override
	public void onCollision(Entity other) {
	
	}

}
