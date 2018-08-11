package net.lostsocket.ld42.entities;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;
import net.lostsocket.ld42.maths.Maths;

public class Zombie extends Entity{
	
	public float speed = 40;
	
	public Zombie() {
		transform.position.x = Maths.getRandomBetween(-2000, 2000);
		transform.position.y = Maths.getRandomBetween(-2000, 2000);
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 0));
	}
	
	@Override
	public void customUpdate(float delta) {
		if(Player.instance == null)
			return;
		
		transform.lookAt(
				Player.instance.transform.position.x,
				Player.instance.transform.position.y);
		
		transform.moveForward(speed * delta);
	}
}
