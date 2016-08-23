package settlers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;

public class Texture {
	
	private int textureID, width, height;
	
	public Texture(ByteBuffer buffer, int width, int height) {
		this.width = width;
		this.height = height;
		textureID = glGenTextures();
		bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL14.GL_GENERATE_MIPMAP, GL_TRUE);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL12.GL_BGRA, GL_UNSIGNED_BYTE, buffer);
		unbind();
	}
	
	public int getTextureWidth() {
		return width;
	}
	
	public int getTextureHeight() {
		return height;
	}
	
	public int getId() {
		return textureID;
	}
	
	public void bind() {
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public void cleanup() {
		glDeleteTextures(textureID);
	}
}
