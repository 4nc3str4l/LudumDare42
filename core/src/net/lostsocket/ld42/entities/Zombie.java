package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.RandomSound;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.maths.Maths;

public class Zombie extends Mortal{
	
	public float speed = 40;
	
	private SpriteComponent aliveSprite;
	private SpriteComponent deadSprite;
	
	private RandomSound growlSoundManager;
	
	public static Sound growl = null;
	private static Sound squish = null;
	private static Sound hit = null;
	
	public Zombie() {
		super(100, 7);
		
		if(growl == null)
			loadSounds(); 
		
		transform.position.x = Player.instance.transform.position.x + (Maths.getRandomFloat(0, 1) > 0.5 ?  Maths.getRandomFloat(-700, -400) : Maths.getRandomFloat(400, 700));
		transform.position.y = Player.instance.transform.position.y + (Maths.getRandomFloat(0, 1) > 0.5 ?  Maths.getRandomFloat(-700, -400) : Maths.getRandomFloat(400, 700));
		
		aliveSprite = new SpriteComponent(RunningOutOfSpace.img, 0, 0);
		addComponent(aliveSprite);
		
		growlSoundManager = new RandomSound(growl, 5, 20, false);
		addComponent(growlSoundManager);
		
		deadSprite = new SpriteComponent(RunningOutOfSpace.img, 1, 0);
	}
	
	private void loadSounds() {
		growl = Gdx.audio.newSound(Gdx.files.internal("res/sounds/zombie.wav"));
		squish = Gdx.audio.newSound(Gdx.files.internal("res/sounds/squish.wav"));
		hit = Gdx.audio.newSound(Gdx.files.internal("res/sounds/zombie_hit.wav"));
	}
	
	@Override
	public void customUpdate(float delta) {
		
		if(Player.instance == null || !isAlive)
			return;
		
		transform.lookAt(
				Player.instance.transform.position.x,
				Player.instance.transform.position.y);
		
		float distance = Vector2.dst(
				Player.instance.transform.position.x,
				Player.instance.transform.position.y,
				transform.position.x,
				transform.position.y);
		
		float distanceToKeep = Player.instance.isAlive ? 7 : 14;
		if(distance > distanceToKeep)
			transform.moveForward(speed * delta);
	}

	@Override
	public void onCollision(Entity other) {
		if(isAlive && other instanceof Bullet) {
			
			Bullet b = (Bullet) other;
			health -= b.damage;
			
			hit.play(Maths.getRandomFloat(0.5f, 0.8f),
					Maths.getRandomFloat(0.95f, 1.05f),
					0);
			
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
		removeComponent(growlSoundManager);
		GameManager.instance.onZombieDead(this);
		squish.play(Maths.getRandomFloat(0.5f, 0.8f),
				Maths.getRandomFloat(0.95f, 1.05f),
				0);
	}
	
	public static void disposeSounds() {
		if(growl == null)
			return; 
		
		growl.dispose();
		squish.dispose();
		hit.dispose();
	}
}
