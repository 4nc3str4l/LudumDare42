package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.BloodStain;

public class TimedDestroyComponent extends AbstractComponent implements IUpdatable{

	public float remainingTime;
	
	public TimedDestroyComponent(float destroyTime) {
		remainingTime = destroyTime;
	}
	
	@Override
	public void update(float delta) {
	
		remainingTime -= delta;
		
		if(owner instanceof BloodStain) {
			System.out.println(remainingTime);
		}
		
		if(remainingTime <= 0)
			owner.isDestroyed = true;
	}

	@Override
	public void dispose() {
	}
}
