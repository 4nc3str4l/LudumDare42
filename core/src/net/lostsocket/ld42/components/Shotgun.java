package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.Bullet;
import net.lostsocket.ld42.maths.Maths;

public class Shotgun extends Weapon{
	
	public Shotgun() {
		super("sounds/shotgun.wav");
		damage = 20;
		shootRate = 1.0f;
	}
	
	public void shoot(){
		for(int i = 0; i < 5; i++)
			owner.currentScene.addEntity(new Bullet(owner, owner.transform.forward.x + Maths.getRandomFloat(-0.1f, 0.1f), owner.transform.forward.y + Maths.getRandomFloat(-0.1f, 0.1f), damage));
	}

	@Override
	protected void levelUpLogic(int newLevel) {
		switch(newLevel) {
		case 2:
			damage += 5;
			shootRate -= 0.1f;
			break; 
		case 3:
			damage += 5;
			shootRate -= 0.05f;
			break;
		case 4:
			damage += 5;
			shootRate -= 0.05f;
			break; 
		case 5:
			damage += 5;
			shootRate -= 0.05f;
			break;
		}
		
		System.out.println("New Level " + newLevel);
	}
}
