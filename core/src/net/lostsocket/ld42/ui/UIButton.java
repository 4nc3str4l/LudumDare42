package net.lostsocket.ld42.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class UIButton extends UI{
	
	private enum BtnStatus { NORMAL, DISABLED, HOVER, SELECTED }
	private BtnStatus currentStatus = BtnStatus.NORMAL;
	
	private Texture normal; 
	private Texture selected;
	private Texture disabled;
	
	private Texture currentTexture;
	private Rectangle rect;
	
	public UIButton(String name, int posX, int posY) {
		normal = new Texture(name + "/btn.png");
		selected = new Texture(name + "/selected.png");
		disabled = new Texture(name + "/disabled.png");
		rect = new Rectangle(posX, posY, normal.getWidth(), normal.getHeight());
		currentTexture = normal;
	}

	@Override
	public void update(float delta) {
		
		
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		switch (currentStatus) {
		case NORMAL:
			currentTexture = normal;
			break;
		case DISABLED:
			currentTexture = disabled;
			break;
		case HOVER:
			currentTexture = selected;
			break;
		case SELECTED:
			currentTexture = selected;
			break;
		default:
			break;
		}
		batch.draw(currentTexture, rect.x, rect.y);
	}

	@Override
	public void dispose() {
		normal.dispose();
		selected.dispose();
		disabled.dispose();
	}
	
	public void setPosition(int x, int y) {
		rect.x = x;
		rect.y = y;
	}
}
