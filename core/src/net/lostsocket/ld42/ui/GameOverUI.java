package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.GameManager;

public class GameOverUI extends UI {

	private Texture gameOver = new Texture("res/GameOver.png");
	private BitmapFont font = new BitmapFont();
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(gameOver, 0, 0);
		font.getData().setScale(1.4f);
		font.setColor(new Color(102, 0, 0, 1));
		font.draw(batch, Integer.toString(GameManager.instance.getWave() - 1), Gdx.graphics.getWidth() / 2 - 15, 285);
		font.draw(batch, Integer.toString(GameManager.instance.getTotalNumKills()), Gdx.graphics.getWidth() / 2 -15, 210);
	}

	@Override
	public void dispose() {
		gameOver.dispose();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
