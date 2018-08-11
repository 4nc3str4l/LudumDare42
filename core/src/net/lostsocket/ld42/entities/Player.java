package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;

public class Player extends Entity{
	
	public float speed = 100;
	
	public Player() {
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 1));
	}

	@Override
	public void customUpdate(float delta) {
		
		lookAtMouse();
		
		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) ){
			transform.moveForward(speed * delta);
		}
	
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN) ){
			transform.moveBack(speed * delta);	
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT) ){
			transform.moveLeft(speed * delta);	
		}
	
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) ){
			transform.moveRight(speed * delta);	
		}
	}
	
	private void lookAtMouse() {
		transform.lookAt(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
	}
	
}
