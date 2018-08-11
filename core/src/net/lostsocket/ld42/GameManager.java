package net.lostsocket.ld42;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.entities.Entity;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;
import net.lostsocket.ld42.scenes.GameScene;
import net.lostsocket.ld42.scenes.SceneManager;
import net.lostsocket.ld42.ui.GameOverUI;
import net.lostsocket.ld42.ui.InGameUI;
import net.lostsocket.ld42.ui.StartGameUI;
import net.lostsocket.ld42.ui.UI;

public class GameManager extends Entity {
	
	public static GameManager instance;

	private int wave = 1;
	private int nZombiesAlive = 0;
	private int totalNumKills = 0;
	
	private enum GameState { NOT_STARTED, PLAYING, GAME_OVER }
	private GameState currentState = GameState.NOT_STARTED;
	
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
	}
	
	public void restartGame() {
		wave = 0;
		nZombiesAlive = 0;
		totalNumKills = 0;
		currentScene.addEntity(new Player());
		nextWaveLogic();
		currentState = GameState.PLAYING;
		changeUI(new InGameUI());
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
		changeUI(new GameOverUI());
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
	
	//TODO: Consider another strategy to avoid that
	@Override
	public void onCollision(Entity other) {}
}
