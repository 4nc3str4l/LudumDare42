package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.graphics.Texture;

import net.lostsocket.ld42.entities.MapBackground;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;

public class GameScene extends AbstractScene{

	private Texture bgTexture = new Texture("MapBg.png");
	
	@Override
	public void load() {
		System.out.println("Game Scene Loaded!");
		addEntity(new MapBackground(bgTexture));
		addEntity(new Player());
		
		for(int i = 0; i < 50; ++i) {
			addEntity(new Zombie());
		}
	}

	@Override
	public void dispose() {
		System.out.println("Game Scene Disposed!");
		bgTexture.dispose();
	}
}
 