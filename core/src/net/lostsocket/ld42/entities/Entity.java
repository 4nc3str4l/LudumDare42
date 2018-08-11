package net.lostsocket.ld42.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.components.AbstractComponent;
import net.lostsocket.ld42.components.IRenderable;
import net.lostsocket.ld42.components.IUpdatable;
import net.lostsocket.ld42.maths.Transform;
import net.lostsocket.ld42.scenes.AbstractScene;

public abstract class Entity 
{
	public boolean isDestroyed = false;
	
	public AbstractScene currentScene;
	
	private ArrayList<IRenderable> renderables = new ArrayList<IRenderable>();
	private ArrayList<IUpdatable> updatables = new ArrayList<IUpdatable>();
	
	public Transform transform = new Transform();
	
	public AbstractComponent addComponent(AbstractComponent component) {
		component.setOwner(this);
		
		if(component instanceof IRenderable) {
			renderables.add((IRenderable)component);
		}
		
		if(component instanceof IUpdatable) {
			updatables.add((IUpdatable)component);
		}
		
		return component;
	}
	
	public abstract void customUpdate(float delta); 
	
	public void tick(float delta) {
		customUpdate(delta);
		for(IUpdatable updatable : updatables) {
			updatable.update(delta);
		}
	}
	
	public void render(SpriteBatch batch) {
		for(IRenderable renderable : renderables) {
			renderable.render(batch);
		}
	}
}
