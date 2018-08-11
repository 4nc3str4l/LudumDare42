package net.lostsocket.ld42.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import net.lostsocket.ld42.entities.Bullet;
import net.lostsocket.ld42.entities.Player;

public abstract class Weapon extends AbstractComponent implements IUpdatable{
	
	protected boolean isMouseDown = false;
	
	private final int MAX_WEAPON_LEVEL = 5;
	private int currentWeaponLevel = 1;
	
	private Sound sound;
	protected float damage = 20;
	protected float shootRate = 1f;
	private float timeUntilNextShoot = 0;
	
	public Weapon(String soundPath) {
		sound = Gdx.audio.newSound(Gdx.files.internal(soundPath));
	}
	
	@Override
	public void update(float delta) {
		
		if(timeUntilNextShoot > 0) {
			timeUntilNextShoot -= delta;
			return;
		}
		
		if(Gdx.input.isButtonPressed(0) && Player.instance.isAlive){
			if(!isMouseDown) {
				sound.play();
				isMouseDown = true;
				timeUntilNextShoot = shootRate;
				shoot();
			}
		}else {
			isMouseDown = false;
		}
		
	}
	
	public abstract void shoot();

	@Override
	public void dispose() {
		sound.dispose();
	}
	
	public boolean isMaxLevel() {
		return currentWeaponLevel < MAX_WEAPON_LEVEL;
	}
	
	protected abstract void levelUpLogic(int newLevel);
	
	public void levelUP() {
		
		if(currentWeaponLevel < MAX_WEAPON_LEVEL)
			return;
		
		++currentWeaponLevel;
		levelUpLogic(currentWeaponLevel);
	}
}
