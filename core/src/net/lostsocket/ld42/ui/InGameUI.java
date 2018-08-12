package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.Player;

public class InGameUI extends UI{
	
	private BitmapFont font = new BitmapFont();
	
	private Texture bottonRect = new Texture("res/BottomUIPannel.png");
	private Texture waveRect = new Texture("res/WaveBG.png");
	private Texture ammo = new Texture("res/ammo.png");
	
	private Texture cold45 = new Texture("res/cold_45.png");
	private Texture shotgun = new Texture("res/shotgun.png");
	private Texture machineGun = new Texture("res/machine_gun.png");
	
	private Texture healthBarForeground = new Texture("res/HealthBar/HealthForeground.png");
	private Texture healthBarBackgroud = new Texture("res/HealthBar/HealthBackground.png");
	
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveRect, Gdx.graphics.getWidth() / 2 - 40, Gdx.graphics.getHeight()- 32);
		font.draw(batch, "Night: " + GameManager.instance.getWave(), Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() - 10);
		
		batch.draw(bottonRect, 0, 0);
		
		font.draw(batch, "Kills: " + GameManager.instance.getTotalNumKills(), 15, 25);
		
		drawHealthBar(batch);

		if(Player.instance.currentWeapon.needsToReload()) {
			font.draw(batch, String.format("R to reload!"), 685, 26);
		}
		
		switch(Player.instance.weaponIndex) {
		case 0:
			batch.draw(cold45, 760, 5);
			break;
		case 1:
			batch.draw(shotgun, 760, 5);
			break;
		case 2:
			batch.draw(machineGun, 760, 5);
			break;
		}

		batch.draw(ammo, 860, 5);

		font.setColor(Color.WHITE);
		font.draw(batch, String.format("%d/OO", Player.instance.currentWeapon.numBullets), 890, 25);
	}
	
	private void drawHealthBar(SpriteBatch batch) {
		batch.draw(healthBarBackgroud, 75, 12);
		float percentage = Player.instance.health / Player.instance.maxHealth;
		batch.draw(healthBarForeground, 76, 13, percentage * 200, 13);
	}

	@Override
	public void dispose() {
		font.dispose();
		bottonRect.dispose();
		waveRect.dispose();
		ammo.dispose();
		cold45.dispose();
		machineGun.dispose();
		shotgun.dispose();
		shapeRenderer.dispose();
		healthBarBackgroud.dispose();
		healthBarForeground.dispose();
	}


	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
