package net.lostsocket.ld42.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.lostsocket.ld42.RunningOutOfSpace;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 960;
		config.height = 640;
		config.title = "Field of Bodies (LD 42, By C.M)";
		config.resizable = false;
		new LwjglApplication(new RunningOutOfSpace(), config);
	}
}
