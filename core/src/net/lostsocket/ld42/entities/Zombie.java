package net.lostsocket.ld42.entities;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.maths.Maths;

public class Zombie extends Mortal{
	
	public float speed = 40;
	
	private SpriteComponent aliveSprite;
	private SpriteComponent deadSprite;
	
	public Zombie() {
		super(100, 7);
		transform.position.x = Maths.getRandomBetween(-2000, 2000);
		transform.position.y = Maths.getRandomBetween(-2000, 2000);
		
		aliveSprite = new SpriteComponent(RunningOutOfSpace.img, 0, 0);
		addComponent(aliveSprite);
		
		deadSprite = new SpriteComponent(RunningOutOfSpace.img, 1, 0);
	}
	
	@Override
	public void customUpdate(float delta) {
		
		if(Player.instance == null || !isAlive)
			return;
		
		transform.lookAt(
				Player.instance.transform.position.x,
				Player.instance.transform.position.y);
		
		transform.moveForward(speed * delta);
	}

	@Override
	public void onCollision(Entity other) {
		if(other instanceof HandgunBullet) {
			health -= 20;
		}
		
		if(other instanceof Zombie) {
			Zombie z = (Zombie)other;
			if(z.isAlive && isAlive) {
				Vector2 pushDirection = new Vector2(other.transform.position);
				pushDirection.sub(transform.position);
				pushDirection.nor();
				transform.position.add(pushDirection.scl(-1));
			}
		}
	}

	@Override
	public void onDead() {
		collision.radius = 10;
		addComponent(deadSprite);
		removeComponent(aliveSprite);
	}
}
