package settlers;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse extends GLFWMouseButtonCallback {
	
	public static final int MOUSE_BUTTON_0 = 0;
	
	private static boolean mouseButton_0_Down = false;
	private static boolean mouseButton_1_Down = false;
	private static boolean mouseButton_2_Down = false;
	private static boolean mouseButton_3_Down = false;
	private static boolean mouseButton_4_Down = false;
	private static boolean mouseButton_5_Down = false;
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		switch(button) {
			
		}
	}

}
