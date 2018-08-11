package net.lostsocket.ld42.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.lostsocket.ld42.Constants;

public class SpriteComponent extends AbstractComponent implements IRenderable {

	private TextureRegion region;
	
	public SpriteComponent(Texture texture, int rowX, int rowY) {
		region = new TextureRegion(texture,
				rowX * Constants.SPRITE_WIDTH,
				rowY * Constants.SPRITE_HEIGHT,
				Constants.SPRITE_WIDTH,
				Constants.SPRITE_HEIGHT);
	}

	@Override
	public void render(SpriteBatch _batch) {
		_batch.draw(region, owner.transform.position.x,
				owner.transform.position.y,
				Constants.SPRITE_WIDTH / 2,
				Constants.SPRITE_HEIGHT / 2,
				Constants.SPRITE_WIDTH,
				Constants.SPRITE_HEIGHT,
				owner.transform.scale.x,
				owner.transform.scale.y,
				owner.transform.rotation);
	}

}
