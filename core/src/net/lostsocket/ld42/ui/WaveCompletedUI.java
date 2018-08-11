package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WaveCompletedUI extends UI {

	private Texture waveScreen = new Texture("WaveSurvived.png");
	
	private UIButton btnUpgrade = new UIButton("BtnUpgrade", 100, 100);
	private UIButton btnFindWeapon = new UIButton("BtnFindWeapon", 100, 100);
	private UIButton btnBodies = new UIButton("BtnBodies", 100, 100);
	private UIButton btnBtnHeal = new UIButton("BtnHeal", 100, 100);
	private UIButton btnSurvivors = new UIButton("BtnSurvivors", 100, 100);
	
	public WaveCompletedUI() {
		int middleX = Gdx.graphics.getWidth() / 2 - 168 / 2;
		btnUpgrade = new UIButton("BtnUpgrade", middleX - 200, 290);
		btnFindWeapon = new UIButton("BtnFindWeapon", middleX, 290);
		btnBodies = new UIButton("BtnBodies", middleX + 200, 290);
		btnBtnHeal = new UIButton("BtnHeal", middleX - 120, 160);
		btnSurvivors = new UIButton("BtnSurvivors", middleX + 120, 160);
	}
	
	@Override
	public void update(float delta) {
		btnUpgrade.update(delta);
		btnFindWeapon.update(delta);
		btnBodies.update(delta);
		btnBtnHeal.update(delta);
		btnSurvivors.update(delta);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(waveScreen, 0, 0);
		btnBodies.render(batch);
		btnFindWeapon.render(batch);
		btnBtnHeal.render(batch);
		btnSurvivors.render(batch);
		btnUpgrade.render(batch);
	}

	@Override
	public void dispose() {
		waveScreen.dispose();
		btnBodies.dispose();
		btnFindWeapon.dispose();
		btnBtnHeal.dispose();
		btnSurvivors.dispose();
		btnUpgrade.dispose();
	}


}
