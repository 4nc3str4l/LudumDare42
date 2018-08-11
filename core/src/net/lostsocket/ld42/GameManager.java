package net.lostsocket.ld42;

import net.lostsocket.ld42.entities.Entity;
import net.lostsocket.ld42.entities.Zombie;

public class GameManager extends Entity {
	
	public static GameManager instance;
	
	private int wave = 1;
	private int nZombiesAlive = 0;
	private int totalNumKills = 0;
	
	public GameManager() {
		super(0);
		instance = this;
	}

	@Override
	public void customUpdate(float delta) {
		
	}
	
	public void restartGame() {
		wave = 0;
		nZombiesAlive = 0;
		totalNumKills = 0;
		nextWaveLogic();
	}
	
	public void onZombieDead() {
		
		--nZombiesAlive;
		++totalNumKills;
		
		if(nZombiesAlive == 0) {
			nextWaveLogic();
		}
	}
	
	private void nextWaveLogic() {
		++wave;
		nZombiesAlive = wave * 5;
		spawnZombies(nZombiesAlive);
	}
	
	private void spawnZombies(int amount) {
		for(int i = 0; i < amount; ++i) {
			currentScene.addEntity(new Zombie());
		}
	}
	
	//TODO: Consider another strategy to avoid that
	@Override
	public void onCollision(Entity other) {}
}
