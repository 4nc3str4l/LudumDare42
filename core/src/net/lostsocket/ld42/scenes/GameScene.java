package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.MapBackground;

public class GameScene extends AbstractScene{

	private Texture bgTexture = new Texture("MapBg.png");
	private Texture bgForest = new Texture("Forest.png");
	private GameManager gameManager;
	
	@Override
	public void load() {
		System.out.println("Game Scene Loaded!");
		addEntity(new GameManager());
		addEntity(new MapBackground(bgTexture));
	}

	@Override
	public void dispose() {
		System.out.println("Game Scene Disposed!");
		bgTexture.dispose();
	}

	@Override
	public void customRender(SpriteBatch batch) {
		batch.draw(bgForest, 0, 0);
		gameManager.renderUI(batch);
	}
}
 