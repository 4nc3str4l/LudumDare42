package net.lostsocket.ld42.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager {
	
	private static AbstractScene currentScene = null;

	public static void loadScene(AbstractScene scene) {
		if(currentScene != null)
			currentScene.dispose();
		currentScene = scene;
		currentScene.load();
	}

	public static void renderScene(SpriteBatch batch) {
		currentScene.render(batch);
	}
	
	public static void updateScene(float delta) {
		currentScene.tick(delta);
	}
	
	public static void dispose() {
		currentScene.dispose();
	}
	
}
