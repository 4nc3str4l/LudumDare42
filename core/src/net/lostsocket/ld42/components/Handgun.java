package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.Bullet;

public class Handgun extends Weapon{
	
	public Handgun() {
		super("res/sounds/handgun_shot.wav");
		this.shootRate = 0.5f;
		damage = 20;
		name = "Handgun";
		magSize = 16;
		numBullets = magSize;
	}

	public void shoot(){
		owner.currentScene.addEntity(new Bullet(owner, owner.transform.forward.x, owner.transform.forward.y, damage));
	}

	@Override
	protected void levelUpLogic(int newLevel) {
		switch(newLevel) {
		case 2:
			damage += 20;
			shootRate -= 0.1f;
			magSize += 4;
			break; 
		case 3:
			damage += 10;
			shootRate -= 0.05f;
			magSize += 4;
			break;
		case 4:
			damage += 10;
			shootRate -= 0.05f;
			magSize += 4;
			break; 
		case 5:
			damage += 20;
			shootRate -= 0.05f;
			magSize += 4;
			break;
		}
		
		System.out.println("New Level " + newLevel);
	}

}
