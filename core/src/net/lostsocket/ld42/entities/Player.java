package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.Handgun;
import net.lostsocket.ld42.components.MachineGun;
import net.lostsocket.ld42.components.Shotgun;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.Weapon;
import net.lostsocket.ld42.maths.Maths;

public class Player extends Mortal{
	
	public static Player instance;
	public float speed = 100;
	
	private SpriteComponent aliveSprite;
	private SpriteComponent deadSprite;

	private Weapon weapons[];
	public Weapon currentWeapon;
	public int weaponIndex = 0;
	
	public static Sound deadSound;
	public static Sound hit;
	
	public Player() {
		
		super(100, 7);
		instance = this;
		
		health = 100000;
		maxHealth = 100000;
		
		aliveSprite = new SpriteComponent(RunningOutOfSpace.img, 0, 1);
		addComponent(aliveSprite);
		
		deadSprite = new SpriteComponent(RunningOutOfSpace.img, 1, 1);
		
		weapons = new Weapon[] {new Handgun(), new Shotgun(), new MachineGun()};
		
		transform.position.x = Gdx.graphics.getWidth() / 2 - 16;
		transform.position.y = Gdx.graphics.getHeight() / 2 - 16;
		
		if(deadSound == null) {
			deadSound = Gdx.audio.newSound(Gdx.files.internal("res/sounds/human_death.wav"));
			hit = Gdx.audio.newSound(Gdx.files.internal("res/sounds/hit_1.wav"));
		}
		
		equipWeapon(0);
	}
	
	public boolean areThereMoreWeapons() {
		return weaponIndex < weapons.length -1;
	}
	
	public String tryFindWeapon() {
		if(Maths.getRandomFloat(0, 1) > 0.5) {
			if(areThereMoreWeapons()) {
				weaponIndex++;
				equipWeapon(weaponIndex);
			}
			return "You found a " + currentWeapon.name + " hidden on the forest!";
		}else {
			return "You explored the forest but no weapons found...";
		}

	}
	
	public void equipWeapon(int index) {
		weaponIndex = index;
		currentWeapon = weapons[index];
		currentWeapon.setOwner(this);
	}
	
	@Override
	public void customUpdate(float delta) {
		
		if(!isAlive || GameManager.instance.currentState != GameManager.GameState.PLAYING)
			return;
		
		lookAtMouse();
		
		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) ){
			transform.moveForward(speed * delta);
		}
	
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN) ){
			transform.moveBack(speed * delta);	
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT) ){
			transform.moveLeft(speed * delta);	
		}
	
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) ){
			transform.moveRight(speed * delta);	
		}
		
		ensurePlayerInMap();
		
		currentWeapon.update(delta);
	}
	
	private void ensurePlayerInMap() {
		if(transform.position.x < 3) {
			transform.position.x = 3;
		}
		
		if(transform.position.x > Gdx.graphics.getWidth() - 25) {
			transform.position.x = Gdx.graphics.getWidth() - 25;
		}
		
		if(transform.position.y < 3) {
			transform.position.y = 3;
		}
		
		if(transform.position.y > Gdx.graphics.getHeight() - 25) {
			transform.position.y = Gdx.graphics.getHeight() - 25;
		}
	}
	
	private void lookAtMouse() {
		transform.lookAt(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
	}

	@Override
	public void onCollision(Entity other) {
		
		if(GameManager.instance.isWarmingUp())
			return;
		
		if(!isAlive) {
			health = 0;
			return;
		}
		
		if(other instanceof Zombie) {
			Zombie z = (Zombie)other;
			Vector2 pushDirection = new Vector2(other.transform.position);
			pushDirection.sub(transform.position);
			pushDirection.nor();
			float ammount = z.isAlive ? -5 : -2;
			transform.position.add(pushDirection.scl(ammount));
			if(z.isAlive) {
				health -= 10;
				hit.play(Maths.getRandomFloat(0, 0.5f),
						Maths.getRandomFloat(0.85f, 1.15f),
						0);
			}
		}
	}

	@Override
	public void onDead() {
		addComponent(deadSprite);
		removeComponent(aliveSprite);
		GameManager.instance.onPlayerDead();
		deadSound.play();
	}
	
	public String heal() {
		health = maxHealth;
		return "After some rest you are fully recovered!";
	}
}
