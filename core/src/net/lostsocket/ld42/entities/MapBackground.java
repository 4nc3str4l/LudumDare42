package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import net.lostsocket.ld42.components.SpriteComponent;

public class MapBackground extends Entity {

	public MapBackground(Texture texture) {
		super(0);
		addComponent(new SpriteComponent(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}
	
	@Override
	public void customUpdate(float delta) {
	}

	@Override
	public void onCollision(Entity other) {
	}
}
