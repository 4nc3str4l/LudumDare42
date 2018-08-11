package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.Bullet;

public class Handgun extends Weapon{
	
	public Handgun() {
		super("sounds/handgun_shot.wav");
		this.shootRate = 0.5f;
		damage = 20;
	}

	public void shoot(){
		owner.currentScene.addEntity(new Bullet(owner, owner.transform.forward.x, owner.transform.forward.y, damage));
	}

	@Override
	protected void levelUpLogic(int newLevel) {
	}
}
