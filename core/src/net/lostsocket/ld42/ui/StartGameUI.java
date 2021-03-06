package net.lostsocket.ld42.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartGameUI extends UI {

	private Texture startScreen = new Texture("res/StartScreen.png");
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(startScreen, 0, 0);
	}

	@Override
	public void dispose() {
		startScreen.dispose();
	}

	@Override
	public void update(float delta) {
	}

}
