package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.CowardAI;
import net.lostsocket.ld42.components.Handgun;
import net.lostsocket.ld42.components.MachineGun;
import net.lostsocket.ld42.components.ProtectorAI;
import net.lostsocket.ld42.components.SelfishAI;
import net.lostsocket.ld42.components.Shotgun;
import net.lostsocket.ld42.components.SniperAI;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.components.Weapon;
import net.lostsocket.ld42.maths.Maths;

public class NPC extends Mortal {

	public static final int PROTECTIVE = 0;
	public static final int SELFISH = 1;
	public static final int COWARD = 2;
	public static final int SNIPER = 3;
	
	public float speed = 100;

	private SpriteComponent aliveSprite;
	private SpriteComponent deadSprite;

	private Weapon weapons[];
	public Weapon currentWeapon;
	public int weaponIndex = 0;
	
	public static String[] behaviours = new String[]{ "Protective", "Selfish", "Coward", "Sniper" };
	public static String currentBehaviourName;
	public int choosenBehaviour = 0;
	
	public NPC() {

		super(100, 7);

		health = 50;
		maxHealth = 50;
		weapons = new Weapon[] { new Handgun(), new Shotgun(), new MachineGun() };

		transform.position.x = Gdx.graphics.getWidth() / 2 - 16;
		transform.position.y = Gdx.graphics.getHeight() / 2 - 16;

		chooseRandomBehaviour();
		equipWeapon(Maths.getRandomBetween(0, 3));
		
		aliveSprite = new SpriteComponent(RunningOutOfSpace.img, choosenBehaviour * 2 + 2, 1);
		addComponent(aliveSprite);

		deadSprite = new SpriteComponent(RunningOutOfSpace.img, choosenBehaviour * 2 + 3, 1);
	}
	
	private void chooseRandomBehaviour() {
		choosenBehaviour = Maths.getRandomBetween(0, behaviours.length);
		currentBehaviourName = behaviours[choosenBehaviour];
		switch (choosenBehaviour) {
		case PROTECTIVE:
			addComponent(new ProtectorAI(this));
			break;
		case SELFISH:
			addComponent(new SelfishAI(this));
			break;
		case COWARD:
			addComponent(new CowardAI(this));
			break;
		case SNIPER:
			addComponent(new SniperAI(this));
			break;
		default:
			break;
		}
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
			return;
			
		if (!isAlive || GameManager.instance.currentState != GameManager.GameState.PLAYING)
			return;
		
		currentWeapon.update(delta);
	}
	
	@Override
	public void onCollision(Entity other) {

		if (GameManager.instance.isWarmingUp())
			return;

		if (!isAlive) {
			health = 0;
			return;
		}
		Vector2 pushDirection = null;
		
		if (other instanceof Zombie) {
			Zombie z = (Zombie) other;
			if(z.isAlive) {
				pushDirection = new Vector2(other.transform.position);
				pushDirection.sub(transform.position);
				pushDirection.nor();
				float ammount = -5;
				transform.position.add(pushDirection.scl(ammount));
				if (z.isAlive) {
					health -= 10;
				}	
			}
		}else if(other instanceof NPC) {
			NPC z = (NPC)other;
			if(z.isAlive && isAlive) {
				pushDirection = new Vector2(other.transform.position);
				pushDirection.sub(transform.position);
				pushDirection.nor();
				transform.position.add(pushDirection.scl(-1));
			}
		}else if(other instanceof Player) {
			Player z = (Player)other;
			if(z.isAlive && isAlive) {
				pushDirection = new Vector2(other.transform.position);
				pushDirection.sub(transform.position);
				pushDirection.nor();
				transform.position.add(pushDirection.scl(-1));
			}
		}
	}

	@Override
	public void onDead() {
		addComponent(deadSprite);
		removeComponent(aliveSprite);
	}
}
