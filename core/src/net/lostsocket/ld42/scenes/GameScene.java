package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.MapBackground;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;

public class GameScene extends AbstractScene{

	private Texture bgTexture = new Texture("res/MapBg.png");
	private Texture bgForest = new Texture("res/Forest.png");
	private Sprite lightMask = new Sprite(new Texture("res/lightMask.png"));
	
	@Override
	public void load() {
		System.out.println("Game Scene Loaded!");
		addEntity(new GameManager());
		addEntity(new MapBackground(bgTexture));
	}

	@Override
	public void customRender(SpriteBatch batch) {
		batch.draw(bgForest, 0, 0);
		
		batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
		if(Player.instance != null) {
			lightMask.setPosition(Player.instance.transform.position.x - 984, Player.instance.transform.position.y - 984);
		}else {
			lightMask.setPosition(0, 0);
		}
		lightMask.draw(batch, 0.05f + 0.2f * (1 - GameManager.instance.nZombiesAlive / (float)GameManager.instance.initialZombies));
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		GameManager.instance.renderUI(batch);
	}
	
	@Override
	public void customDispose() {
		System.out.println("Game Scene Disposed!");
		bgTexture.dispose();
		bgForest.dispose();
		lightMask.getTexture().dispose();
		Zombie.disposeSounds();
		Player.deadSound.dispose();
		Player.hit.dispose();
	}
}
 