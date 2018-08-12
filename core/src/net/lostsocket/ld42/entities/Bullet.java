package net.lostsocket.ld42.entities;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.TimedDestroyComponent;

public class Bullet extends Entity {
	
	private final float SPEED = 1000f;
	public Entity owner;
	public float damage;
	public float pushBack;
	
	public Bullet(Entity owner, float forwX, float forwY, float damage, float pushBack) {
		super(3);
		this.owner = owner;
		this.damage = damage;
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 3));
		addComponent(new TimedDestroyComponent(1f));
		transform.forward = new Vector2(forwX, forwY);
		transform.position = new Vector2(owner.transform.position).add(transform.forward.x * 20, transform.forward.y * 20);
		this.pushBack = pushBack;
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
