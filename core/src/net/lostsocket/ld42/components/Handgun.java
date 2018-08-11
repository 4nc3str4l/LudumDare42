package net.lostsocket.ld42.components;

import com.badlogic.gdx.Gdx;

import net.lostsocket.ld42.entities.HandgunBullet;
import net.lostsocket.ld42.entities.Player;

public class Handgun extends AbstractComponent implements IUpdatable{
	
	private boolean isMouseDown = false;
	
	@Override
	public void update(float delta) {
		if(Gdx.input.isButtonPressed(0) && Player.instance.isAlive){
			if(!isMouseDown) {
				shoot();
				isMouseDown = true;
			}
		}else {
			isMouseDown = false;
		}
	}
	
	public void shoot(){
		owner.currentScene.addEntity(new HandgunBullet(owner));
	}
}
