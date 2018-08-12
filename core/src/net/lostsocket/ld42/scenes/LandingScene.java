package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LandingScene extends AbstractScene{
	
	private Texture background;
	
	@Override
	public void load() {
		System.out.println("Landing Scene Loaded!");
		background = new Texture("TitleScreen.png");
	}

	@Override
	public void customDispose() {
		System.out.println("Landing Scene Disposed!");
		background.dispose();
	}

	@Override
	public void customRender(SpriteBatch batch) {
		batch.draw(background, 0, 0);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			SceneManager.loadScene(new GameScene());
		}
	}

}
