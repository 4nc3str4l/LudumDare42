package net.lostsocket.ld42.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;

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
	
	public Circle collision;
	
	public Entity(float radius) {
		collision = new Circle(transform.position.x, transform.position.y, radius);
	}
	
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
	
	public void removeComponent(AbstractComponent component) {
		
		if(component instanceof IRenderable) {
			renderables.remove((IRenderable)component);
		}
		
		if(component instanceof IUpdatable) {
			updatables.remove((IUpdatable)component);
		}
	}
	
	public abstract void customUpdate(float delta); 
	
	public void tick(float delta) {
		collision.setPosition(transform.position.x, transform.position.y);
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
	
	public boolean isCollidingWith(Entity other) {
		return collision.overlaps(other.collision);
	}
	
	public void dispose() {
		for(IUpdatable updatable : updatables) 
			updatable.dispose();
	}
	
	public abstract void onCollision(Entity other);
}
