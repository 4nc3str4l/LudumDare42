package net.lostsocket.ld42.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.Handgun;
import net.lostsocket.ld42.components.SpriteComponent;

public class Player extends Mortal{
	
	public static Player instance;
	public float speed = 100;
	
	private Handgun handgun;
	
	public Player() {
		super(100, 7);
		instance = this;
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 1));
		
		handgun = new Handgun();
		addComponent(handgun);
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
		
		ensurePlayerInMap();
	}
	
	private void ensurePlayerInMap() {
		if(transform.position.x < 3) {
			transform.position.x = 3;
		}
		
		if(transform.position.x > Gdx.graphics.getWidth() - 25) {
			transform.position.x = Gdx.graphics.getWidth() - 25;
		}
		
		if(transform.position.y < 3) {
			transform.position.y = 3;
		}
		
		if(transform.position.y > Gdx.graphics.getHeight() - 25) {
			transform.position.y = Gdx.graphics.getHeight() - 25;
		}
	}
	
	private void lookAtMouse() {
		transform.lookAt(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
	}

	@Override
	public void onCollision(Entity other) {
		if(other instanceof Zombie) {
			Zombie z = (Zombie)other;
			Vector2 pushDirection = new Vector2(other.transform.position);
			pushDirection.sub(transform.position);
			pushDirection.nor();
			float ammount = z.isAlive ? -5 : -2;
			transform.position.add(pushDirection.scl(ammount));
		}
	}

	@Override
	public void onDead() {
	}
	
}
