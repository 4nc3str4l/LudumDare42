package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.Bullet;

public class MachineGun extends Weapon{

	public MachineGun() {
		super("sounds/handgun_shot.wav");
		shootRate = 0.05f;
		damage = 20;
		name = "Machine Gun";
	}
	
	public void shoot() {
		owner.currentScene.addEntity(new Bullet(owner, owner.transform.forward.x, owner.transform.forward.y, damage));
		isMouseDown = false;
	}

	@Override
	protected void levelUpLogic(int newLevel) {
		switch(newLevel) {
		case 2:
			damage += 5;
			shootRate -= 0.005f;
			break; 
		case 3:
			damage += 5;
			shootRate -= 0.005f;
			break;
		case 4:
			damage += 5;
			shootRate -= 0.005f;
			break; 
		case 5:
			damage += 5;
			shootRate -= 0.005f;
			break;
		}
	}
}
