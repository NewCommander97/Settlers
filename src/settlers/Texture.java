package settlers;

import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL12;

public class Texture {
	
	private int texture, width, height;
	
	public Texture(ByteBuffer buffer, int width, int height) {
		this.width = width;
		this.height = height;
		texture = glGenTextures();
		bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL12.GL_BGRA, GL_UNSIGNED_BYTE, buffer);
		unbind();
	}
	
	public int getTextureWidth() {
		return width;
	}
	
	public int getTextureHeight() {
		return height;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
