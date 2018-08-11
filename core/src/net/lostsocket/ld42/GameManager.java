package net.lostsocket.ld42;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import net.lostsocket.ld42.entities.Entity;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;
import net.lostsocket.ld42.scenes.GameScene;
import net.lostsocket.ld42.scenes.SceneManager;

public class GameManager extends Entity {
	
	public static GameManager instance;
	
	private int wave = 1;
	private int nZombiesAlive = 0;
	private int totalNumKills = 0;
	
	private enum GameState { NOT_STARTED, PLAYING, GAME_OVER }
	private GameState currentState = GameState.NOT_STARTED;
	
	public GameManager() {
		super(0);
		instance = this;
	}

	@Override
	public void customUpdate(float delta) {
		if(currentState == GameState.NOT_STARTED) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				restartGame();
			}

		}
		
		if(currentState == GameState.GAME_OVER) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				SceneManager.loadScene(new GameScene());
			}
		}
	}
	
	public void restartGame() {
		wave = 0;
		nZombiesAlive = 0;
		totalNumKills = 0;
		currentScene.addEntity(new Player());
		nextWaveLogic();
		currentState = GameState.PLAYING;
	}
	
	public void onZombieDead() {
		
		--nZombiesAlive;
		++totalNumKills;
		
		if(nZombiesAlive == 0) {
			nextWaveLogic();
		}
	}
	
	public void onPlayerDead() {
		currentState = GameState.GAME_OVER;
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
