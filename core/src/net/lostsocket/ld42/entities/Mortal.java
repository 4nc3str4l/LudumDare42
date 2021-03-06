package net.lostsocket.ld42.entities;

public abstract class Mortal extends Entity{
	
	public float maxHealth;
	public float health;
	public boolean isAlive = true;
	
	public Mortal(float maxHealth, float radius) {
		super(radius);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
	}
	
	@Override
	public void tick(float delta) {
		super.tick(delta);
		
		if(!isAlive)
			return; 
		
		if(this.health <= 0) {
			onDead();
			isAlive = false;
		}
	}
	
	public abstract void onDead();
}
