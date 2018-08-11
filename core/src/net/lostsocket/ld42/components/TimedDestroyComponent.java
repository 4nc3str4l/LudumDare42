package net.lostsocket.ld42.components;

public class TimedDestroyComponent extends AbstractComponent implements IUpdatable{

	public float remainingTime;
	
	public TimedDestroyComponent(float destroyTime) {
		remainingTime = destroyTime;
	}
	
	@Override
	public void update(float delta) {
	
		remainingTime -= delta;
		if(remainingTime <= 0)
			owner.isDestroyed = true;
	}

	@Override
	public void dispose() {
	}
}
