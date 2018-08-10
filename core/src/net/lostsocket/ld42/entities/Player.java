package net.lostsocket.ld42.entities;

import net.lostsocket.ld42.RunningOutOfSpace;
import net.lostsocket.ld42.components.SpriteComponent;

public class Player extends Entity{
	
	public Player() {
		addComponent(new SpriteComponent(RunningOutOfSpace.img, 0, 1));
	}
	
}
