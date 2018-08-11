package net.lostsocket.ld42.components;

import com.badlogic.gdx.audio.Sound;

import net.lostsocket.ld42.maths.Maths;

public class RandomSound extends AbstractComponent implements IUpdatable {

	private Sound soundToPlay;
	private float minRate;
	private float maxRate;
	private float timeUntilNextSound = 0;
	private boolean disposeSound;
	
	public RandomSound(Sound soundToPlay, int minRate, int maxRate, boolean dispose) {
		this.soundToPlay = soundToPlay;
		this.minRate = minRate;
		this.maxRate = maxRate;
		this.disposeSound = dispose;
		this.timeUntilNextSound = Maths.getRandomFloat(minRate, maxRate);
	}
	
	@Override
	public void update(float delta) {
		timeUntilNextSound -= delta;
		if(timeUntilNextSound <= 0) {
			playSound();
		}
	}

	private void playSound() {
		soundToPlay.play(Maths.getRandomFloat(0,  1),
				Maths.getRandomFloat(0, 1),
				Maths.getRandomFloat(0,  1.5f));
		timeUntilNextSound = Maths.getRandomFloat(minRate, maxRate);
	}
	
	@Override
	public void dispose() {
		if(disposeSound)
			soundToPlay.dispose();
	}
	
}
