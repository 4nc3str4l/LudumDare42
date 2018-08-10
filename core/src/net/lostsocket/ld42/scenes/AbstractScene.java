package net.lostsocket.ld42.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.entities.Entity;

public abstract class AbstractScene {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public abstract void load();
	public abstract void dispose();
	
	public void tick(float delta) {
		for(Entity entity : entities) {
			entity.tick(delta);
		}
	}
	
	public void render(SpriteBatch batch){
		for(Entity entity : entities) {
			entity.render(batch);
		}
	}
}
