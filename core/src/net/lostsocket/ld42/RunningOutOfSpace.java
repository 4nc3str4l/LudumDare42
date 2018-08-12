package net.lostsocket.ld42;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.lostsocket.ld42.scenes.LandingScene;
import net.lostsocket.ld42.scenes.SceneManager;

public class RunningOutOfSpace extends ApplicationAdapter {
	
	private SpriteBatch batch;
	public static Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("res/OutOfSpace.png");
		SceneManager.loadScene(new LandingScene());
	}

	@Override
	public void render () {
		SceneManager.updateScene(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		SceneManager.renderScene(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		SceneManager.dispose();
		batch.dispose();
		img.dispose();
	}
}
