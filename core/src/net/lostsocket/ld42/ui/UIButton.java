package net.lostsocket.ld42.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class UIButton extends UI{
	
	private enum BtnStatus { NORMAL, DISABLED, HOVER, SELECTED }
	private BtnStatus currentStatus = BtnStatus.NORMAL;
	
	private Texture normal; 
	private Texture selected;
	private Texture disabled;
	
	private Texture currentTexture;
	private Rectangle rect;
	
	private UIButtonClick callBack;
	
	public UIButton(String name, int posX, int posY) {
		normal = new Texture(name + "/btn.png");
		selected = new Texture(name + "/selected.png");
		disabled = new Texture(name + "/disabled.png");
		rect = new Rectangle(posX, posY, normal.getWidth(), normal.getHeight());
		currentTexture = normal;
	}
	
	public void setCallback(UIButtonClick callback) {
		this.callBack = callback;
	}
	
	@Override
	public void update(float delta) {
		
		if(currentStatus == BtnStatus.DISABLED || currentStatus == BtnStatus.SELECTED)
			return;
		
		Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		
		currentStatus = rect.contains(mousePos) ? BtnStatus.HOVER : BtnStatus.NORMAL;
		
		if(currentStatus == currentStatus.HOVER) {
			if(Gdx.input.isButtonPressed(0)) {
				currentStatus = BtnStatus.SELECTED;
				onButtonClick();
			}
		}
		
	}
	
	private void onButtonClick() {
		System.out.println("Button Clicked!");
		callBack.onButtonClick();
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

	public void setEnabled(boolean isEnabled) {
		
		currentStatus = isEnabled ? currentStatus.NORMAL : currentStatus.DISABLED; 
	}
	
	public void setSelected(boolean isSelected) {
		if(currentStatus == currentStatus.DISABLED)
			return;
		
		currentStatus = isSelected ? currentStatus.SELECTED : currentStatus.NORMAL; 
	}

}
