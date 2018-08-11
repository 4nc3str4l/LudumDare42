package net.lostsocket.ld42.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIButton extends UI{
	
	private Texture normal; 
	private Texture selected;
	private Texture disabled;
	
	public UIButton(String name) {
		normal = new Texture(name + "/btn.png");
		selected = new Texture(name + "/selected.png");
		disabled = new Texture(name + "/disabled.png");
	}

	@Override
	public void update(float delta) {
	}
	
	@Override
	public void render(SpriteBatch _batch) {
	}

	@Override
	public void dispose() {
	}

}
