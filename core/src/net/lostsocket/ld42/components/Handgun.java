package net.lostsocket.ld42.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.entities.Bullet;

public class Handgun extends Weapon{
	
	public Handgun() {
		super("res/sounds/handgun_shot.wav");
		this.shootRate = 0.5f;
		damage = 20;
		name = "Handgun";
		magSize = 16;
		numBullets = magSize;
		pushBack = 0;
	}

	public void shoot(){
		owner.currentScene.addEntity(new Bullet(owner, owner.transform.forward.x, owner.transform.forward.y, damage, pushBack), false);
	}

	@Override
	protected void levelUpLogic(int newLevel) {
		switch(newLevel) {
		case 2:
			damage += 20;
			shootRate -= 0.1f;
			magSize += 4;
			pushBack += 1;
			break; 
		case 3:
			damage += 10;
			shootRate -= 0.05f;
			magSize += 4;
			pushBack += 1;
			break;
		case 4:
			damage += 10;
			shootRate -= 0.05f;
			magSize += 4;
			pushBack += 1;
			break; 
		case 5:
			damage += 50;
			shootRate -= 0.05f;
			magSize += 4;
			pushBack += 20;
			break;
		}
	}

}
