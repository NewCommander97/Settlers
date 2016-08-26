package settlers;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Toolkit;

import org.lwjgl.*;
import org.lwjgl.glfw.*;

public class Main {
	
	//public static final int WINDOW_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	//public static final int WINDOW_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	public static final int WINDOW_HEIGHT = 720;
	public static final int WINDOW_WIDTH = 1280;
	
	private long window;
	private TextureManager textureManager = new TextureManager();
	private FontManager fontManager = new FontManager();
	private float rotate = -45.0f;
	private float lastFrame = 0;
	private double mouseX = 0, mouseY = 0;
	private int fps = 0;
	private int lfps = 0;
	private long lastFPS = System.nanoTime() / 1000000000;
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		try {
			initGLFW();
			OpenGL.init();
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
		
		HeightMapMesh hmm = null;
		
		try {
			//hmm = new HeightMapMesh(0.0f, 3.0f, "res/Heightmap_small.png", textureManager.getTexture("sand"), 10);
			hmm = new HeightMapMesh();
			hmm.randomize(32, 32, 0f, 10f, textureManager.getTexture("sand"), 10);
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
        
        Model m = ModelLoader.loadModel("res/Mine_UV.obj", textureManager.getTexture("mine"));
        fontManager.add("res/fonts/OpenSans-Regular.ttf", 12f, "OpenSans");
		
		while ( !glfwWindowShouldClose(window) ) {
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
				glfwSetWindowShouldClose(window, true);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			OpenGL.make3D();
			
			float delta = getDelta();
			Camera.acceptInput(delta);
			Camera.apply();
			
			shaderProgram.use();
			shaderProgram.setUniformi(shaderProgram.getUniformLocation("texture_diffuse"), 0);
			hmm.getMesh().render();
			ShaderProgram.unbind();
			
			m.render(10, -2.1f, 10, 0.5f, getTime());
			
			glPushMatrix();
			if(Keyboard.isKeyDown(Keyboard.KEY_R))
				rotate -= 0.4f;
			if(Keyboard.isKeyDown(Keyboard.KEY_F))
				rotate += 0.4f;
			float x = -0.5f, y = 0, z = -3f;
			glTranslatef(x, y, z);
			glRotatef(rotate, 1, 0, 0);
			glTranslatef(-x, -y, -z);
			DrawUtilities.drawPlane(textureManager.getTexture("grass"), x, y, z, 1, 1);
			glPopMatrix();
			
			Mouse.setX(mouseX);
			Mouse.setY(mouseY);
			
			OpenGL.make2D();
			glColor3f(1f, 0, 0);
			glBegin(GL_QUADS);
				glVertex2f(0, WINDOW_HEIGHT - 23);
				glVertex2f(0, WINDOW_HEIGHT);
				glVertex2f(WINDOW_WIDTH, WINDOW_HEIGHT);
				glVertex2f(WINDOW_WIDTH, WINDOW_HEIGHT - 23);
			glEnd();
			glColor3f(1f, 1f, 1f);
			fontManager.get("OpenSans").drawText(lfps + " FPS", 5, WINDOW_HEIGHT - 20);
			
			//FPS Counter
			if(System.nanoTime() / 1000000000 - lastFPS >= 1) {
				System.out.println(fps);
				lfps = fps;
				lastFPS = System.nanoTime() / 1000000000;
				fps = 0;
			}
			fps++;
			
			glfwSwapBuffers(window); // swap the color buffers
			
			glfwPollEvents();
		}
	}
	
	private void loadTextures() {
		textureManager.addTexture("grass", "res/grass.jpg");
		textureManager.addTexture("sand", "res/sand_diffuse.png");
		textureManager.addTexture("sand_normal", "res/sand_normalmap.png");
		textureManager.addTexture("test", "res/test.png");
		textureManager.addTexture("mine", "res/Mine_diffuse.png");
	}
	
	private void initGLFW() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		//window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Hello World!", glfwGetPrimaryMonitor(), NULL);
		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");
		
		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		GLFWKeyCallback keyboard;
		glfwSetKeyCallback(window, keyboard = new Keyboard());
		
		GLFWMouseButtonCallback mouse;
		glfwSetMouseButtonCallback(window, mouse = new Mouse());
		
		glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
			mouseX = xpos;
			mouseY = ypos;
		});
		
		glfwSetScrollCallback(window, (window, xoffset, yoffset) -> {
			
		});
		
		glfwSetCursorEnterCallback(window, (window, entered) -> {
			if(entered)
				Mouse.setIsInWindow(true);
			else
				Mouse.setIsInWindow(false);
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
