package net.lostsocket.ld42.entities;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.TimedDestroyComponent;

public class HandgunBullet extends Entity {
	
	private final float SPEED = 1000f;
	
	private Entity owner;
	
	public HandgunBullet(Entity owner) {
		this.owner = this;
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 3));
		addComponent(new TimedDestroyComponent(1f));
		transform.position = new Vector2(owner.transform.position);
		transform.forward = new Vector2(owner.transform.forward);
	}
	
	@Override
	public void customUpdate(float delta) {
		transform.moveForward(SPEED * delta);
	}
}
