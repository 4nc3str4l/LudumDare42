package net.lostsocket.ld42.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.entities.Entity;

public abstract class AbstractScene {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
	public ArrayList<Entity> entitiesToPrepent = new ArrayList<Entity>();
	
	public abstract void load();
	protected abstract void customDispose();
	
	
	public void tick(float delta) {
		
		processCollisions();
		
		Entity e;
		for(int i = entities.size() -1; i >= 0; --i) {
			e = entities.get(i);
			if(e.isDestroyed) {
				entities.remove(i);
			}else {
				e.tick(delta);
			}
		}
		
		for(Entity toAdd : entitiesToAdd) {
			entities.add(toAdd);
		}
		
		for(Entity toAdd : entitiesToPrepent) {
			entities.add(2, toAdd);
		}
		
		entitiesToPrepent.clear();
		entitiesToAdd.clear();

	}
	
	public void processCollisions() {
		Entity e1, e2;
		for(int i = 0; i < entities.size(); ++i) {
			e1 = entities.get(i);
			for(int j = 0; j < entities.size(); ++j) {
				if(i != j) {
					e2 = entities.get(j);
					if(e1.isCollidingWith(e2)) {
						e1.onCollision(e2);
					}
				}
			}
		}
	}
	
	public abstract void customRender(SpriteBatch batch);
	
	public void render(SpriteBatch batch){
		for(Entity entity : entities) {
			entity.render(batch);
		}
		
		customRender(batch);
	}
	
	public void addEntity(Entity newEntity, boolean atBeginning) {
		newEntity.currentScene = this;
		if(atBeginning) {
			entitiesToPrepent.add(newEntity);
		}else {
			entitiesToAdd.add(newEntity);
		}
	}
	
	public void dispose() {
		customDispose();
		
		for(Entity entity : entities) {
			entity.dispose();
		}
	}
	
}
