package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.Player;

public class InGameUI extends UI{
	
	private BitmapFont font = new BitmapFont();
	private Texture bottonRect = new Texture("res/BottomUIPannel.png");
	private Texture waveRect = new Texture("res/WaveBG.png");
	private Texture ammo = new Texture("res/ammo.png");
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveRect, Gdx.graphics.getWidth() / 2 - 40, Gdx.graphics.getHeight()- 32);
		font.draw(batch, "Night: " + GameManager.instance.getWave(), Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() - 10);
		
		batch.draw(bottonRect, 0, 0);
		
		font.draw(batch, "Kills: " + GameManager.instance.getTotalNumKills(), 10, 25);
		
		font.setColor(Color.RED);
		font.draw(batch, "Health: " + Player.instance.health, 120, 25);
	
		font.setColor(Color.WHITE);
		font.draw(batch, "Current Weapon: " + Player.instance.currentWeapon.name, 270, 25);
		
		batch.draw(ammo, 860, 5);
		font.draw(batch, String.format("%d/OO", Player.instance.currentWeapon.numBullets), 890, 25);
	}


	@Override
	public void dispose() {
		font.dispose();
		bottonRect.dispose();
		waveRect.dispose();
		ammo.dispose();
	}


	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
