package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.GameManager;

public class InGameUI extends UI{
	
	private BitmapFont font = new BitmapFont();
	private Texture bottonRect = new Texture("BottomUIPannel.png");
	private Texture waveRect = new Texture("WaveBG.png");
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveRect, Gdx.graphics.getWidth() / 2 - 40, Gdx.graphics.getHeight()- 32);
		font.draw(batch, "Wave: " + GameManager.instance.getWave(), Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() - 10);
		
		batch.draw(bottonRect, 0, 0);
		font.draw(batch, "Kills: " + GameManager.instance.getTotalNumKills(), 10, 25);
	}


	@Override
	public void dispose() {
		font.dispose();
		bottonRect.dispose();
		waveRect.dispose();
	}
}
