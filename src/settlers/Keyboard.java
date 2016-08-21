package settlers;

import java.awt.event.KeyEvent;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {
	
	public static final int KEY_A = KeyEvent.VK_A;
	public static final int KEY_B = KeyEvent.VK_B;
	public static final int KEY_C = KeyEvent.VK_C;
	public static final int KEY_D = KeyEvent.VK_D;
	public static final int KEY_E = KeyEvent.VK_E;
	public static final int KEY_F = KeyEvent.VK_F;
	public static final int KEY_G = KeyEvent.VK_G;
	public static final int KEY_H = KeyEvent.VK_H;
	public static final int KEY_I = KeyEvent.VK_I;
	public static final int KEY_J = KeyEvent.VK_J;
	public static final int KEY_K = KeyEvent.VK_K;
	public static final int KEY_L = KeyEvent.VK_L;
	public static final int KEY_M = KeyEvent.VK_M;
	public static final int KEY_N = KeyEvent.VK_N;
	public static final int KEY_O = KeyEvent.VK_O;
	public static final int KEY_P = KeyEvent.VK_P;
	public static final int KEY_Q = KeyEvent.VK_Q;
	public static final int KEY_R = KeyEvent.VK_R;
	public static final int KEY_S = KeyEvent.VK_S;
	public static final int KEY_T = KeyEvent.VK_T;
	public static final int KEY_U = KeyEvent.VK_U;
	public static final int KEY_V = KeyEvent.VK_V;
	public static final int KEY_W = KeyEvent.VK_W;
	public static final int KEY_X = KeyEvent.VK_X;
	public static final int KEY_Y = KeyEvent.VK_Y;
	public static final int KEY_Z = KeyEvent.VK_Z;
	public static final int KEY_1 = KeyEvent.VK_1;
	public static final int KEY_2 = KeyEvent.VK_2;
	public static final int KEY_3 = KeyEvent.VK_3;
	public static final int KEY_4 = KeyEvent.VK_4;
	public static final int KEY_5 = KeyEvent.VK_5;
	public static final int KEY_6 = KeyEvent.VK_6;
	public static final int KEY_7 = KeyEvent.VK_7;
	public static final int KEY_8 = KeyEvent.VK_8;
	public static final int KEY_9 = KeyEvent.VK_9;
	public static final int KEY_0 = KeyEvent.VK_0;
	public static final int KEY_NUMPAD_1 = KeyEvent.VK_NUMPAD1;
	public static final int KEY_NUMPAD_2 = KeyEvent.VK_NUMPAD2;
	public static final int KEY_NUMPAD_3 = KeyEvent.VK_NUMPAD3;
	public static final int KEY_NUMPAD_4 = KeyEvent.VK_NUMPAD4;
	public static final int KEY_NUMPAD_5 = KeyEvent.VK_NUMPAD5;
	public static final int KEY_NUMPAD_6 = KeyEvent.VK_NUMPAD6;
	public static final int KEY_NUMPAD_7 = KeyEvent.VK_NUMPAD7;
	public static final int KEY_NUMPAD_8 = KeyEvent.VK_NUMPAD8;
	public static final int KEY_NUMPAD_9 = KeyEvent.VK_NUMPAD9;
	public static final int KEY_NUMPAD_0 = KeyEvent.VK_NUMPAD0;
	public static final int KEY_SPACE = KeyEvent.VK_SPACE;
	public static final int KEY_SHIFT = KeyEvent.VK_SHIFT;
	public static final int KEY_ENTER = KeyEvent.VK_ENTER;
	public static final int KEY_ESCAPE = KeyEvent.VK_ESCAPE;
	
	private static boolean key_A_Down = false;
	private static boolean key_B_Down = false;
	private static boolean key_C_Down = false;
	private static boolean key_D_Down = false;
	private static boolean key_E_Down = false;
	private static boolean key_F_Down = false;
	private static boolean key_G_Down = false;
	private static boolean key_H_Down = false;
	private static boolean key_I_Down = false;
	private static boolean key_J_Down = false;
	private static boolean key_K_Down = false;
	private static boolean key_L_Down = false;
	private static boolean key_M_Down = false;
	private static boolean key_N_Down = false;
	private static boolean key_O_Down = false;
	private static boolean key_P_Down = false;
	private static boolean key_Q_Down = false;
	private static boolean key_R_Down = false;
	private static boolean key_S_Down = false;
	private static boolean key_T_Down = false;
	private static boolean key_U_Down = false;
	private static boolean key_V_Down = false;
	private static boolean key_W_Down = false;
	private static boolean key_X_Down = false;
	private static boolean key_Y_Down = false;
	private static boolean key_Z_Down = false;
	private static boolean key_1_Down = false;
	private static boolean key_2_Down = false;
	private static boolean key_3_Down = false;
	private static boolean key_4_Down = false;
	private static boolean key_5_Down = false;
	private static boolean key_6_Down = false;
	private static boolean key_7_Down = false;
	private static boolean key_8_Down = false;
	private static boolean key_9_Down = false;
	private static boolean key_0_Down = false;
	private static boolean key_NUMPAD_1_Down = false;
	private static boolean key_NUMPAD_2_Down = false;
	private static boolean key_NUMPAD_3_Down = false;
	private static boolean key_NUMPAD_4_Down = false;
	private static boolean key_NUMPAD_5_Down = false;
	private static boolean key_NUMPAD_6_Down = false;
	private static boolean key_NUMPAD_7_Down = false;
	private static boolean key_NUMPAD_8_Down = false;
	private static boolean key_NUMPAD_9_Down = false;
	private static boolean key_NUMPAD_0_Down = false;
	private static boolean key_SPACE_Down = false;
	private static boolean key_SHIFT_Down = false;
	private static boolean key_ENTER_Down = false;
	private static boolean key_ESCAPE_Down = false;
	
	public static boolean isKeyDown(int key) {
		switch(key) {
			case KEY_A:
				return key_A_Down;
			case KEY_B:
				return key_B_Down;
			case KEY_C:
				return key_C_Down;
			case KEY_D:
				return key_D_Down;
			case KEY_E:
				return key_E_Down;
			case KEY_F:
				return key_F_Down;
			case KEY_G:
				return key_G_Down;
			case KEY_H:
				return key_H_Down;
			case KEY_I:
				return key_I_Down;
			case KEY_J:
				return key_J_Down;
			case KEY_K:
				return key_K_Down;
			case KEY_L:
				return key_L_Down;
			case KEY_M:
				return key_M_Down;
			case KEY_N:
				return key_N_Down;
			case KEY_O:
				return key_O_Down;
			case KEY_P:
				return key_P_Down;
			case KEY_Q:
				return key_Q_Down;
			case KEY_R:
				return key_R_Down;
			case KEY_S:
				return key_S_Down;
			case KEY_T:
				return key_T_Down;
			case KEY_U:
				return key_U_Down;
			case KEY_V:
				return key_V_Down;
			case KEY_W:
				return key_W_Down;
			case KEY_X:
				return key_X_Down;
			case KEY_Y:
				return key_Y_Down;
			case KEY_Z:
				return key_Z_Down;
			case KEY_1:
				return key_1_Down;
			case KEY_2:
				return key_2_Down;
			case KEY_3:
				return key_3_Down;
			case KEY_4:
				return key_4_Down;
			case KEY_5:
				return key_5_Down;
			case KEY_6:
				return key_6_Down;
			case KEY_7:
				return key_7_Down;
			case KEY_8:
				return key_8_Down;
			case KEY_9:
				return key_9_Down;
			case KEY_0:
				return key_0_Down;
			case KEY_NUMPAD_1:
				return key_NUMPAD_1_Down;
			case KEY_NUMPAD_2:
				return key_NUMPAD_2_Down;
			case KEY_NUMPAD_3:
				return key_NUMPAD_3_Down;
			case KEY_NUMPAD_4:
				return key_NUMPAD_4_Down;
			case KEY_NUMPAD_5:
				return key_NUMPAD_5_Down;
			case KEY_NUMPAD_6:
				return key_NUMPAD_6_Down;
			case KEY_NUMPAD_7:
				return key_NUMPAD_7_Down;
			case KEY_NUMPAD_8:
				return key_NUMPAD_8_Down;
			case KEY_NUMPAD_9:
				return key_NUMPAD_9_Down;
			case KEY_NUMPAD_0:
				return key_NUMPAD_0_Down;
			case KEY_SHIFT:
				return key_SHIFT_Down;
			case KEY_ENTER:
				return key_ENTER_Down;
			case KEY_SPACE:
				return key_SPACE_Down;
			case KEY_ESCAPE:
				return key_ESCAPE_Down;
			default:
				break;
		}
		return false;
	}
	

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		switch(key) {
			case KEY_A:
				if(action == GLFW.GLFW_PRESS)
					key_A_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_A_Down = false;
				break;
			case KEY_B:
				if(action == GLFW.GLFW_PRESS)
					key_B_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_B_Down = false;
				break;
			case KEY_C:
				if(action == GLFW.GLFW_PRESS)
					key_C_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_C_Down = false;
				break;
			case KEY_D:
				if(action == GLFW.GLFW_PRESS)
					key_D_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_D_Down = false;
				break;
			case KEY_E:
				if(action == GLFW.GLFW_PRESS)
					key_E_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_E_Down = false;
				break;
			case KEY_F:
				if(action == GLFW.GLFW_PRESS)
					key_F_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_F_Down = false;
				break;
			case KEY_G:
				if(action == GLFW.GLFW_PRESS)
					key_G_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_G_Down = false;
				break;
			case KEY_H:
				if(action == GLFW.GLFW_PRESS)
					key_H_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_H_Down = false;
				break;
			case KEY_I:
				if(action == GLFW.GLFW_PRESS)
					key_I_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_I_Down = false;
				break;
			case KEY_J:
				if(action == GLFW.GLFW_PRESS)
					key_J_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_J_Down = false;
				break;
			case KEY_K:
				if(action == GLFW.GLFW_PRESS)
					key_K_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_K_Down = false;
				break;
			case KEY_L:
				if(action == GLFW.GLFW_PRESS)
					key_L_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_L_Down = false;
				break;
			case KEY_M:
				if(action == GLFW.GLFW_PRESS)
					key_M_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_M_Down = false;
				break;
			case KEY_N:
				if(action == GLFW.GLFW_PRESS)
					key_N_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_N_Down = false;
				break;
			case KEY_O:
				if(action == GLFW.GLFW_PRESS)
					key_O_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_O_Down = false;
				break;
			case KEY_P:
				if(action == GLFW.GLFW_PRESS)
					key_P_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_P_Down = false;
				break;
			case KEY_Q:
				if(action == GLFW.GLFW_PRESS)
					key_Q_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_Q_Down = false;
				break;
			case KEY_R:
				if(action == GLFW.GLFW_PRESS)
					key_R_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_R_Down = false;
				break;
			case KEY_S:
				if(action == GLFW.GLFW_PRESS)
					key_S_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_S_Down = false;
				break;
			case KEY_T:
				if(action == GLFW.GLFW_PRESS)
					key_T_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_T_Down = false;
				break;
			case KEY_U:
				if(action == GLFW.GLFW_PRESS)
					key_U_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_U_Down = false;
				break;
			case KEY_V:
				if(action == GLFW.GLFW_PRESS)
					key_V_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_V_Down = false;
				break;
			case KEY_W:
				if(action == GLFW.GLFW_PRESS)
					key_W_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_W_Down = false;
				break;
			case KEY_X:
				if(action == GLFW.GLFW_PRESS)
					key_X_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_X_Down = false;
				break;
			case KEY_Y:
				if(action == GLFW.GLFW_PRESS)
					key_Y_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_Y_Down = false;
				break;
			case KEY_Z:
				if(action == GLFW.GLFW_PRESS)
					key_Z_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_Z_Down = false;
				break;
			case KEY_1:
				if(action == GLFW.GLFW_PRESS)
					key_1_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_1_Down = false;
				break;
			case KEY_2:
				if(action == GLFW.GLFW_PRESS)
					key_2_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_2_Down = false;
				break;
			case KEY_3:
				if(action == GLFW.GLFW_PRESS)
					key_3_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_3_Down = false;
				break;
			case KEY_4:
				if(action == GLFW.GLFW_PRESS)
					key_4_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_4_Down = false;
				break;
			case KEY_5:
				if(action == GLFW.GLFW_PRESS)
					key_5_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_5_Down = false;
				break;
			case KEY_6:
				if(action == GLFW.GLFW_PRESS)
					key_6_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_6_Down = false;
				break;
			case KEY_7:
				if(action == GLFW.GLFW_PRESS)
					key_7_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_7_Down = false;
				break;
			case KEY_8:
				if(action == GLFW.GLFW_PRESS)
					key_8_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_8_Down = false;
				break;
			case KEY_9:
				if(action == GLFW.GLFW_PRESS)
					key_9_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_9_Down = false;
				break;
			case KEY_0:
				if(action == GLFW.GLFW_PRESS)
					key_0_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_0_Down = false;
				break;
			case KEY_NUMPAD_1:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_1_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_1_Down = false;
				break;
			case KEY_NUMPAD_2:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_2_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_2_Down = false;
				break;
			case KEY_NUMPAD_3:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_3_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_3_Down = false;
				break;
			case KEY_NUMPAD_4:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_4_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_4_Down = false;
				break;
			case KEY_NUMPAD_5:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_5_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_5_Down = false;
				break;
			case KEY_NUMPAD_6:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_6_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_6_Down = false;
				break;
			case KEY_NUMPAD_7:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_7_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_7_Down = false;
				break;
			case KEY_NUMPAD_8:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_8_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_8_Down = false;
				break;
			case KEY_NUMPAD_9:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_9_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_9_Down = false;
				break;
			case KEY_NUMPAD_0:
				if(action == GLFW.GLFW_PRESS)
					key_NUMPAD_0_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_NUMPAD_0_Down = false;
				break;
			case KEY_SPACE:
				if(action == GLFW.GLFW_PRESS)
					key_SPACE_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_SPACE_Down = false;
				break;
			case KEY_SHIFT:
				if(action == GLFW.GLFW_PRESS)
					key_SHIFT_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_SHIFT_Down = false;
				break;
			case KEY_ENTER:
				if(action == GLFW.GLFW_PRESS)
					key_ENTER_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_ENTER_Down = false;
				break;
			case KEY_ESCAPE:
				if(action == GLFW.GLFW_PRESS)
					key_ESCAPE_Down = true;
				else if(action == GLFW.GLFW_RELEASE)
					key_ESCAPE_Down = false;
				break;
				
		}
	}
}