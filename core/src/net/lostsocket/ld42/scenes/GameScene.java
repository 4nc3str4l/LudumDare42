package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.graphics.Texture;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.MapBackground;

public class GameScene extends AbstractScene{

	private Texture bgTexture = new Texture("MapBg.png");
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
}
 