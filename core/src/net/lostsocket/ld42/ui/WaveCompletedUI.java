package net.lostsocket.ld42.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WaveCompletedUI extends UI {

	private Texture waveScreen = new Texture("WaveSurvived.png");
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveScreen, 0, 0);
	}

	@Override
	public void dispose() {
		waveScreen.dispose();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
