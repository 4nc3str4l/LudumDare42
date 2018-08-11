package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LandingScene extends AbstractScene{
	
	@Override
	public void load() {
		System.out.println("Landing Scene Loaded!");
	}

	@Override
	public void dispose() {
		System.out.println("Landing Scene Disposed!");
	}

	@Override
	public void customRender(SpriteBatch batch) {
		
	}

}
