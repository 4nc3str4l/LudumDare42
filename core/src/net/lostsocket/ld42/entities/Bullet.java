package net.lostsocket.ld42.entities;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.TimedDestroyComponent;

public class Bullet extends Entity {
	
	private final float SPEED = 1000f;
	private Entity owner;
	
	public Bullet(Entity owner) {
		super(3);
		this.owner = owner;
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 3));
		addComponent(new TimedDestroyComponent(1f));
		transform.forward = new Vector2(owner.transform.forward);
		transform.position = new Vector2(owner.transform.position).add(transform.forward.x * 20, transform.forward.y * 20);
	}
	
	@Override
	public void customUpdate(float delta) {
		transform.moveForward(SPEED * delta);
	}

	@Override
	public void onCollision(Entity other) {
		if(other instanceof Zombie) {
			Zombie z = (Zombie)other;
			if(z.isAlive) {
				isDestroyed = true;
			}
		}
	}
}
