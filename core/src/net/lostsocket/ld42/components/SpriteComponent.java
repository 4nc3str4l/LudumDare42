package net.lostsocket.ld42.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.lostsocket.ld42.Constants;

public class SpriteComponent extends AbstractComponent implements IRenderable {

	private TextureRegion region;
	private int width;
	private int heigth;
	private float originX;
	private float originY;
	
	public SpriteComponent(Texture texture, int rowX, int rowY) {
		region = new TextureRegion(texture,
				rowX * Constants.SPRITE_WIDTH,
				rowY * Constants.SPRITE_HEIGHT,
				Constants.SPRITE_WIDTH,
				Constants.SPRITE_HEIGHT);
		width = Constants.SPRITE_WIDTH; 
		heigth = Constants.SPRITE_HEIGHT; 
		originX = Constants.SPRITE_WIDTH / 2; 
		originY = Constants.SPRITE_HEIGHT / 2;
	}
	
	public SpriteComponent(Texture texture, int x, int y, int w, int h) {
		region = new TextureRegion(texture, x, y, w, h);
		width = w; 
		heigth = h; 
		originX = 0; 
		originY = 0;
	}

	@Override
	public void render(SpriteBatch _batch) {
		_batch.draw(region, owner.transform.position.x,
				owner.transform.position.y,
				originX,
				originY,
				width,
				heigth,
				owner.transform.scale.x,
				owner.transform.scale.y,
				owner.transform.rotation);
	}

}
