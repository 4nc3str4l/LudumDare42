package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.Handgun;
import net.lostsocket.ld42.components.MachineGun;
import net.lostsocket.ld42.components.Shotgun;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.Weapon;
import net.lostsocket.ld42.maths.Maths;

public class NPC extends Mortal {

	public float speed = 100;

	private SpriteComponent aliveSprite;
	private SpriteComponent deadSprite;

	private Weapon weapons[];
	public Weapon currentWeapon;
	public int weaponIndex = 0;

	public Zombie target = null;
	
	public NPC() {

		super(100, 7);

		health = 300;
		maxHealth = 300;

		aliveSprite = new SpriteComponent(RunningOutOfSpace.img, 0, 1);
		addComponent(aliveSprite);

		deadSprite = new SpriteComponent(RunningOutOfSpace.img, 1, 1);

		weapons = new Weapon[] { new Handgun(), new Shotgun(), new MachineGun() };

		transform.position.x = Gdx.graphics.getWidth() / 2 - 16;
		transform.position.y = Gdx.graphics.getHeight() / 2 - 16;

		equipWeapon(Maths.getRandomBetween(0, 3));
	}

	public void equipWeapon(int index) {
		weaponIndex = index;
		currentWeapon = weapons[index];
		currentWeapon.setOwner(this);
		currentWeapon.isForNPC = true;
	}

	@Override
	public void customUpdate(float delta) {

		if(GameManager.instance.isWarmingUp())
			
		if (!isAlive || GameManager.instance.currentState != GameManager.GameState.PLAYING)
			return;
		
		currentWeapon.update(delta);
		
		if(target == null || !target.isAlive) {
			target = findClosestZombieToPlayer();
		}
		else {
			transform.lookAt(target.transform.position.x, target.transform.position.y);
			
			if(distanceToTarget() < 100) {
				currentWeapon.tryShootNPC();
			}else {
				transform.moveForward(speed * delta);
			}
		}
		
	}
	
	private float distanceToTarget() {
		return Vector2.dst(transform.position.x,
				transform.position.y, 
				target.transform.position.x,
				target.transform.position.y);
	}
	
	public Zombie findClosestZombieToPlayer() {
		float minDistance = 100000;
		Zombie temp = null;
		Zombie choosen = null;
		for(int i = 0; i < GameManager.instance.aliveZombies.size(); ++i) {
			temp = GameManager.instance.aliveZombies.get(i);
			float currentDistance = Vector2.dst2(
					Player.instance.transform.position.x,
					Player.instance.transform.position.y,
					temp.transform.position.x,
					temp.transform.position.y);
			if(currentDistance < minDistance) {
				choosen = temp;
				minDistance = currentDistance;
			}
		}
		return choosen;
	}
	
	@Override
	public void onCollision(Entity other) {

		if (GameManager.instance.isWarmingUp())
			return;

		if (!isAlive) {
			health = 0;
			return;
		}

		if (other instanceof Zombie) {
			Zombie z = (Zombie) other;
			Vector2 pushDirection = new Vector2(other.transform.position);
			pushDirection.sub(transform.position);
			pushDirection.nor();
			float ammount = z.isAlive ? -5 : -2;
			transform.position.add(pushDirection.scl(ammount));
			if (z.isAlive) {
				health -= 1;
			}
		}
	}

	@Override
	public void onDead() {
		addComponent(deadSprite);
		removeComponent(aliveSprite);
	}
}
