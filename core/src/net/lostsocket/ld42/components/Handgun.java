package net.lostsocket.ld42.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import net.lostsocket.ld42.entities.HandgunBullet;
import net.lostsocket.ld42.entities.Player;

public class Handgun extends AbstractComponent implements IUpdatable{
	
	private boolean isMouseDown = false;
	
	private Sound sound;
	
	public Handgun() {
		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/handgun_shot.wav"));
	}
	
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
		sound.play();
		owner.currentScene.addEntity(new HandgunBullet(owner));
	}

	@Override
	public void dispose() {
		sound.dispose();
	}
}
