package settlers;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse extends GLFWMouseButtonCallback {
	
	public static final int MOUSE_BUTTON_0 = 0;
	public static final int MOUSE_BUTTON_1 = 1;
	public static final int MOUSE_BUTTON_2 = 2;
	public static final int MOUSE_BUTTON_3 = 3;
	public static final int MOUSE_BUTTON_4 = 4;
	public static final int MOUSE_BUTTON_5 = 5;
	
	private static boolean mouseButton_0_Down = false;
	private static boolean mouseButton_1_Down = false;
	private static boolean mouseButton_2_Down = false;
	private static boolean mouseButton_3_Down = false;
	private static boolean mouseButton_4_Down = false;
	private static boolean mouseButton_5_Down = false;
	
	private static boolean grabbed = false;
	private static boolean isInWindow = true;
	
	private static double posX = 0;
	private static double posY = 0;
	private static double deltaX = 0;
	private static double deltaY = 0;
	
	public static boolean isButtonDown(int button) {
		switch(button) {
			case MOUSE_BUTTON_0:
				return mouseButton_0_Down;
			case MOUSE_BUTTON_1:
				return mouseButton_1_Down;
			case MOUSE_BUTTON_2:
				return mouseButton_2_Down;
			case MOUSE_BUTTON_3:
				return mouseButton_3_Down;
			case MOUSE_BUTTON_4:
				return mouseButton_4_Down;
			case MOUSE_BUTTON_5:
				return mouseButton_5_Down;
		}
		return false;
	}
	
	public static boolean isGrabbed() {
		return grabbed;
	}
	
	public static void setIsInWindow(boolean b) {
		isInWindow = b;
	}
	
	public static boolean isInWindow() {
		return isInWindow;
	}
	
	public static void setGrabbed(boolean b) {
		grabbed = b;
	}
	
	public static void setX(double x) {
		deltaX = x - posX;
		posX = x;
	}
	
	public static void setY(double y) {
		deltaY = y - posY;
		posY = y;
	}
	
	public static double getX() {
		return posX;
	}
	
	public static double getY() {
		return posY;
	}
	
	public static double getDX() {
		return deltaX;
	}
	
	public static double getDY() {
		return deltaY;
	}
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		switch(button) {
			case MOUSE_BUTTON_0:
				if(action == GLFW.GLFW_PRESS)
					mouseButton_0_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					mouseButton_0_Down = false;
				break;
			case MOUSE_BUTTON_1:
				if(action == GLFW.GLFW_PRESS)
					mouseButton_1_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					mouseButton_1_Down = false;
				break;
			case MOUSE_BUTTON_2:
				if(action == GLFW.GLFW_PRESS)
					mouseButton_2_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					mouseButton_2_Down = false;
				break;
			case MOUSE_BUTTON_3:
				if(action == GLFW.GLFW_PRESS)
					mouseButton_3_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					mouseButton_3_Down = false;
				break;
			case MOUSE_BUTTON_4:
				if(action == GLFW.GLFW_PRESS)
					mouseButton_4_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					mouseButton_4_Down = false;
				break;
			case MOUSE_BUTTON_5:
				if(action == GLFW.GLFW_PRESS)
					mouseButton_5_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					mouseButton_5_Down = false;
				break;
		}
	}

}
