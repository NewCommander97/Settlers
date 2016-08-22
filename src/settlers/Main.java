package settlers;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Toolkit;
import java.nio.FloatBuffer;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;
import org.lwjgl.util.Point;
import org.lwjgl.util.glu.*;

public class Main {
	
	public static final int WINDOW_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int WINDOW_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	private long window;
	private TextureManager textureManager = new TextureManager();
	private float positionZ = -4.0f;
	private float rotate = -45.0f;
	private float lastFrame = 0;
	private double mouseX = 0, mouseY = 0;
	
	private HeightMapMesh hmm;
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		try {
			initGLFW();
			initOpenGL3D();
			loadTextures();
			loop();
			
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			glfwTerminate();
			glfwSetErrorCallback(null).free();
		}
	}
	
	private void loop() {
		
		try {
			hmm = new HeightMapMesh(0.0f, 2.0f, "res/Heightmap_small.png", textureManager.getTexture("grass"), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ShaderProgram shaderProgram = null;
		ShaderProgram normalShader = null;
        try {
			shaderProgram = new ShaderProgram(Utilities.readFileAsString("res/shaders/shader.vert"), Utilities.readFileAsString("res/shaders/shader.frag"));
			normalShader = new ShaderProgram(Utilities.readFileAsString("res/shaders/normal_shader.vert"), Utilities.readFileAsString("res/shaders/normal_shader.frag"));
		} catch (LWJGLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while ( !glfwWindowShouldClose(window) ) {
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
				glfwSetWindowShouldClose(window, true);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			glLoadIdentity();
			
			Camera.acceptInput(getDelta());
			Camera.apply();
			
			shaderProgram.use();
			shaderProgram.setUniformi(shaderProgram.getUniformLocation("texture_diffuse"), 0);
			hmm.getMesh().render();
			ShaderProgram.unbind();
			
			glPushMatrix();
			if(Keyboard.isKeyDown(Keyboard.KEY_R))
				rotate -= 0.4f;
			if(Keyboard.isKeyDown(Keyboard.KEY_F))
				rotate += 0.4f;
			float x, y, z;
			x = -0.5f;
			y = 0;
			z = -3f;
			glTranslatef(x, y, z);
			glRotatef(rotate, 1, 0, 0);
			glTranslatef(-x, -y, -z);
			DrawUtilities.drawPlane(textureManager.getTexture("sand"), x, y, z, 1, 1);
			glPopMatrix();
			
			Mouse.setX(mouseX);
			Mouse.setY(mouseY);
			
			glfwSwapBuffers(window); // swap the color buffers
			
			glfwPollEvents();
		}
	}
	
	private void loadTextures() {
		textureManager.addTexture("grass", "res/grass.jpg");
		textureManager.addTexture("sand", "res/sand_diffuse.png");
		textureManager.addTexture("sand_normal", "res/sand_normalmap.png");
		textureManager.addTexture("test", "res/test.png");
	}
	
	private void initOpenGL2D() {
		GL.createCapabilities();
		
		glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);
		glEnable(GL_LINE_SMOOTH);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	private FloatBuffer matSpecular;
	private FloatBuffer lightPosition;
	private FloatBuffer whiteLight; 
	private FloatBuffer lModelAmbient;
	
	private void initOpenGL3D() {
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
		float aspect = (float)WINDOW_WIDTH / (float)WINDOW_HEIGHT;
		float zNear = 0.1f;
		float zFar = 100.0f;
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
		
		
		//Wireframe Modus
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	    //glDisable(GL_TEXTURE_2D);
	}
	

	//------- Added for Lighting Test----------//
	private void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();
		
		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
	}
	
	private void initGLFW() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Hello World!", glfwGetPrimaryMonitor(), NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");
		
		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		GLFWKeyCallback keyboard;
		glfwSetKeyCallback(window, keyboard = new Keyboard());
		/*glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
			if ( key == GLFW_KEY_W && action == GLFW_PRESS)
				wdown = true;
			if ( key == GLFW_KEY_W && action == GLFW_RELEASE)
				wdown = false;
			if ( key == GLFW_KEY_S && action == GLFW_PRESS)
				sdown = true;
			if ( key == GLFW_KEY_S && action == GLFW_RELEASE)
				sdown = false;
		});*/
		
		
		GLFWMouseButtonCallback mouse;
		glfwSetMouseButtonCallback(window, mouse = new Mouse());
		/*glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
			if(button == GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE) {
				System.out.println("Linke Maustaste losgelassen");
			}
		});*/
		
		glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
			mouseX = xpos;
			mouseY = ypos;
		});
		
		glfwSetScrollCallback(window, (window, xoffset, yoffset) -> {
			
		});

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(
			window,
			(vidmode.width() - WINDOW_WIDTH) / 2,
			(vidmode.height() - WINDOW_HEIGHT) / 2
		);

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}
	
	public long getTime() {
	    return System.nanoTime() / 10000000;
	}
	
	public float getDelta() {
	    long time = getTime();
	    float delta = time - lastFrame;
	    lastFrame = time;
	         
	    return delta;
	}
}
