package net.lostsocket.ld42.maths;

import java.util.Random;

public class Maths {
	
	private static final Random rand = new Random();
	
	public static int getRandomBetween(int min, int max) {
		return rand.nextInt(max - min) + min;
	}
	
	public static float getRandomFloat(float min, float max) {
		return rand.nextFloat() * (max - min) + min;
	}
}
