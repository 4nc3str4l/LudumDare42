package net.lostsocket.ld42;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.entities.Entity;
import net.lostsocket.ld42.entities.NPC;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;
import net.lostsocket.ld42.maths.Maths;
import net.lostsocket.ld42.scenes.GameScene;
import net.lostsocket.ld42.scenes.SceneManager;
import net.lostsocket.ld42.ui.GameOverUI;
import net.lostsocket.ld42.ui.InGameUI;
import net.lostsocket.ld42.ui.StartGameUI;
import net.lostsocket.ld42.ui.UI;
import net.lostsocket.ld42.ui.WaveCompletedUI;

public class GameManager extends Entity {
	
	public static GameManager instance;
	private final int ZOMBIES_MULT = 2;

	private int wave = 1;
	public int nZombiesAlive = 0;
	public int initialZombies;
	private int totalNumKills = 0;
	
	
	public enum GameState { NOT_STARTED, PLAYING, WAVE_SURVIVED, GAME_OVER }
	public GameState currentState = GameState.NOT_STARTED;
	
	public final float WARMUP_TIME = 0.1f;
	private float remainingTimeWarmingUp = 1.0f;
	
	public ArrayList<Zombie> spawnedZombies = new ArrayList<Zombie>();
	public ArrayList<Zombie> aliveZombies = new ArrayList<Zombie>();
	
	private UI currentUI;
	
	public GameManager() {
		super(0);
		instance = this;
		changeUI(new StartGameUI());
	}

	@Override
	public void customUpdate(float delta) {
		
		if(currentState == GameState.NOT_STARTED) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				restartGame();
			}

		}
		
		if(currentState == GameState.GAME_OVER) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
				SceneManager.loadScene(new GameScene());
			}
		}
		
		if(currentUI != null)
			currentUI.update(delta);
		
		if(remainingTimeWarmingUp > 0) {
			remainingTimeWarmingUp -= delta;
		}
	}
	
	public void restartGame() {
		wave = 0;
		nZombiesAlive = 0;
		totalNumKills = 0;
		currentScene.addEntity(new Player(), false);
		currentState = GameState.PLAYING;
		changeUI(new InGameUI());
		remainingTimeWarmingUp = WARMUP_TIME;
		spawnedZombies.clear();
		aliveZombies.clear();
		nextWaveLogic();
	}
	
	public String tryFindNPC() {
		
		if(Maths.getRandomFloat(0, 1) < 0.5){
			return "No one found, alive... maybe tomorrow...";
		}
		
		NPC npc = new NPC();
		currentScene.addEntity(npc, false);
		switch (npc.choosenBehaviour) {
		case NPC.PROTECTIVE:
			return "You found a Survivor, it looks like a very protective person";
		case NPC.SELFISH:
			return "You found a Survivor, be carefull he looks a bit selfish";
		case NPC.COWARD:
			return "You found a Survivor, be carefull he looks a bit coward";
		case NPC.SNIPER:
			return "You found an Sniper from some random army (you feel safe)";
		default:
			return "";
		}
	}
	
	public void onZombieDead(Zombie z) {
		
		aliveZombies.remove(z);
		--nZombiesAlive;
		++totalNumKills;
		
		if(nZombiesAlive == 0) {
			currentState = GameState.WAVE_SURVIVED;
			changeUI(new WaveCompletedUI());
		}
	}
	
	public void onPlayerDead() {
		currentState = GameState.GAME_OVER;
		changeUI(new GameOverUI());
	}
	
	public void nextWaveLogic() {
		++wave;
		currentState = GameState.PLAYING;
		initialZombies = wave * ZOMBIES_MULT;
		nZombiesAlive = initialZombies;
		
		spawnZombies(nZombiesAlive);
		changeUI(new InGameUI());
		remainingTimeWarmingUp = WARMUP_TIME;
	}
	
	private void spawnZombies(int amount) {
		for(int i = 0; i < amount; ++i) {
			Zombie z = new Zombie();
			if(wave > 5) {
				z.health += wave * Maths.getRandomBetween(0, 10);
				z.maxHealth = z.health;
				z.speed += Maths.getRandomBetween(-20, 30);
			}
			spawnedZombies.add(z);
			aliveZombies.add(z);
			currentScene.addEntity(z, false);
		}
	}
	
	public void renderUI(SpriteBatch batch) {
		if(currentUI == null)
			return;
		
		currentUI.render(batch);
	}
	
	private void changeUI(UI newUI) {
		if(currentUI != null)
			currentUI.dispose();
		currentUI = newUI;
	}
	
	public int getWave() {
		return wave;
	}

	public int getnZombiesAlive() {
		return nZombiesAlive;
	}

	public int getTotalNumKills() {
		return totalNumKills;
	}
	
	public boolean isWarmingUp() {
		return remainingTimeWarmingUp > 0;
	}
	
	public String removeBodies() {
		int bodiesToRemove = Maths.getRandomBetween(1, wave * ZOMBIES_MULT);
		Zombie z = null;
		Collections.shuffle(spawnedZombies);
		for(int i = 0; i < bodiesToRemove; ++i) {
			z = spawnedZombies.get(0);
			spawnedZombies.remove(z);
			z.isDestroyed = true;
		}
		
		return "After a long night "  + bodiesToRemove + " where removed and burned!";
	}
	
	//TODO: Consider another strategy to avoid that
	@Override
	public void onCollision(Entity other) {}
}
