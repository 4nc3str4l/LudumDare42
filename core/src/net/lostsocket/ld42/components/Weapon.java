package net.lostsocket.ld42.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.Player;

public abstract class Weapon extends AbstractComponent implements IUpdatable{
	
	protected boolean isMouseDown = false;
	
	private final int MAX_WEAPON_LEVEL = 5;
	private int currentWeaponLevel = 1;
	public int magSize = 7;
	public int numBullets;

	private Sound sound;
	private Sound reload;
	private Sound noAmmo;
	
	protected float damage = 20;
	public float shootRate = 1f;
	public float reloadSpeed = 1f;
	
	private float timeUntilNextShoot = 0;
	
	public float pushBack = 0;

	public String name;
	
	public boolean isForNPC = false;
	
	public Weapon(String soundPath) {
		sound = Gdx.audio.newSound(Gdx.files.internal(soundPath));
		reload = Gdx.audio.newSound(Gdx.files.internal("res/sounds/reloading.wav"));
		noAmmo = Gdx.audio.newSound(Gdx.files.internal("res/sounds/noAmmo.wav"));
		
		numBullets = magSize;
	}
	
	@Override
	public void update(float delta) {
		
		if(GameManager.instance.isWarmingUp())
			return;
		
		if(Gdx.input.isKeyJustPressed(Keys.R)) {
			numBullets = magSize;
			reload.play();
			timeUntilNextShoot = reloadSpeed;
		}
		
		if(timeUntilNextShoot > 0) {
			timeUntilNextShoot -= delta;
			return;
		}
		
		if(isForNPC)
			return;

		if(Gdx.input.isButtonPressed(0) && Player.instance.isAlive){
			if(!isMouseDown) {
				if(numBullets > 0){
					sound.play();
					isMouseDown = true;

					--numBullets;
					shoot();
				}else {
					noAmmo.play();
				}
				timeUntilNextShoot = shootRate;
			}
		}else {
			isMouseDown = false;
		}
		
	}
	
	public void tryShootNPC() {
		
		if(timeUntilNextShoot > 0)
			return;

		sound.play();
		timeUntilNextShoot = shootRate;
		shoot();
	}
	
	public abstract void shoot();

	@Override
	public void dispose() {
		sound.dispose();
		reload.dispose();
	}
	
	public boolean isMaxLevel() {
		return currentWeaponLevel == MAX_WEAPON_LEVEL;
	}
	
	protected abstract void levelUpLogic(int newLevel);
	
	public String levelUP() {
		
		if(currentWeaponLevel == MAX_WEAPON_LEVEL)
			return "You have reached the maximum level!";
			
		++currentWeaponLevel;
		levelUpLogic(currentWeaponLevel);
		numBullets = magSize;
		return "Congratulations your " + name + " is now level " + currentWeaponLevel + "!";
	}
	
	public boolean isShootingAvaliable() {
		return timeUntilNextShoot <= 0;
	}
	
	public boolean needsToReload() {
		return numBullets <= 0;
	}
}
