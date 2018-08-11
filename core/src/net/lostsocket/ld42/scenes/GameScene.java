package net.lostsocket.ld42.scenes;

import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;

public class GameScene extends AbstractScene{

	@Override
	public void load() {
		System.out.println("Game Scene Loaded!");
		addEntity(new Player());
		
		for(int i = 0; i < 50; ++i) {
			addEntity(new Zombie());
		}
	}

	@Override
	public void dispose() {
		System.out.println("Game Scene Disposed!");
	}
}
 