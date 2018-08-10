package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.Entity;

public abstract class AbstractComponent {
	
	protected Entity owner;

	public void setOwner(Entity owner) {
		this.owner = owner;
	}
}
