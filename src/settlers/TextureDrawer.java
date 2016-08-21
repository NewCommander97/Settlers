package settlers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Point;

public class TextureDrawer {
	
	public static void drawTexture(Texture texture, Point p) {
		texture.bind();
		
		glColor3f(1f, 1f, 1f);
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(p.getX(), p.getY());
			glTexCoord2f(1, 0);
			glVertex2f(p.getX() + texture.getTextureWidth(), p.getY());
			glTexCoord2f(1,1);
			glVertex2f(p.getX() + texture.getTextureWidth(), p.getY() + texture.getTextureHeight());
			glTexCoord2f(0,1);
			glVertex2f(p.getX(), p.getY() + texture.getTextureHeight());
		glEnd();
		
		texture.unbind();
	}
	
	public static void drawTexture(Texture texture, Point p, int width, int height) {
		
		texture.bind();
		
		glColor3f(1f, 1f, 1f);
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(p.getX(), p.getY());
			glTexCoord2f(4, 0);
			glVertex2f(p.getX() + width, p.getY());
			glTexCoord2f(4, 4);
			glVertex2f(p.getX() + width, p.getY() + height);
			glTexCoord2f(0, 4);
			glVertex2f(p.getX(), p.getY() + height);
		glEnd();
		
		texture.unbind();
	}
}
