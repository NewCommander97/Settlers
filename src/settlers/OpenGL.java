package settlers;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

public class OpenGL {
	
	public static FloatBuffer matSpecular;
	public static FloatBuffer lightPosition;
	public static FloatBuffer whiteLight; 
	public static FloatBuffer lModelAmbient;
	
	public static void init() {
		GL.createCapabilities();
		System.out.println(glGetString(GL_VERSION));
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // sets background to grey
		glClearDepth(1.0f); // clear depth buffer
		glEnable(GL_DEPTH_TEST); // Enables depth testing
		glDepthFunc(GL_LEQUAL); // sets the type of test to use for depth testing
		glMatrixMode(GL_PROJECTION); // sets the matrix mode to project
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		
		float fovy = 45.0f;
		float aspect = (float)Main.WINDOW_WIDTH / (float)Main.WINDOW_HEIGHT;
		float zNear = 0.1f;
		float zFar = 200.0f;
		float fH = (float) (Math.tan( fovy / 360.0f * 3.14159f ) * zNear);
		float fW = fH * aspect;
		glFrustum(-fW, fW, -fH, fH, zNear, zFar);
		
		glMatrixMode(GL_MODELVIEW);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		//----------- Variables & method calls added for Lighting Test -----------//
		initLightArrays();
		glShadeModel(GL_SMOOTH);
		glMaterialfv(GL_FRONT, GL_SPECULAR, matSpecular);				// sets specular material color
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);					// sets shininess
		
		glLightfv(GL_LIGHT0, GL_POSITION, lightPosition);				// sets light position
		glLightfv(GL_LIGHT0, GL_SPECULAR, whiteLight);				// sets specular light to white
		glLightfv(GL_LIGHT0, GL_DIFFUSE, whiteLight);					// sets diffuse light to white
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);		// global ambient light
		
		glEnable(GL_LIGHTING);										// enables lighting
		glEnable(GL_LIGHT0);										// enables light0
		
		glEnable(GL_COLOR_MATERIAL);								// enables opengl to use glColor3f to define material color
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			// tell opengl glColor3f effects the ambient and diffuse properties of material
		//----------- END: Variables & method calls added for Lighting Test -----------//
		
		Camera.create();
		
		//glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
	}
	
	private static void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();
		
		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
	}
	
	public static void make2D() {
	    glPopAttrib();
	    glPushAttrib(GL_ENABLE_BIT);

	    glViewport(0,0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT,0,-1,1);
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	}
	
	public static void make3D() {

	    glPopAttrib();
	    glPushAttrib(GL_ENABLE_BIT);

	    glEnable(GL_DEPTH_TEST);
	    glDepthFunc(GL_LEQUAL);

	    glViewport(0,0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();

	    // FOV = Field of View
	    // Aspect ration
	    // zNear and zFar (Viewing range)
	    float fovy = 45.0f;
		float aspect = (float)Main.WINDOW_WIDTH / (float)Main.WINDOW_HEIGHT;
		float zNear = 0.1f;
		float zFar = 200.0f;
		float fH = (float) (Math.tan( fovy / 360.0f * 3.14159f ) * zNear);
		float fW = fH * aspect;
		glFrustum(-fW, fW, -fH, fH, zNear, zFar);

	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();

	}
}
