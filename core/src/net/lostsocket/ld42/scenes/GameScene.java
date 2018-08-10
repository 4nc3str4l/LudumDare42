package net.lostsocket.ld42.scenes;

import net.lostsocket.ld42.entities.Player;

public class GameScene extends AbstractScene{

	@Override
	public void load() {
		System.out.println("Game Scene Loaded!");
		addEntity(new Player());
	}

	@Override
	public void dispose() {
		System.out.println("Game Scene Disposed!");
	}
}
 