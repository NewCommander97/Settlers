package settlers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Color;
import org.lwjgl.util.Point;

public class DrawUtilities {
	public static void drawLine(Color color, Point p1, Point p2) {
		glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		glLineWidth(5.0f);
		glBegin(GL_LINES);
			glVertex2i(p1.getX(), p1.getY());
			glVertex2i(p2.getX(), p2.getY());
		glEnd();
	}
	
	public static void drawQuad(Color color, Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
		glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		glBegin(GL_QUADS);
			glVertex2i(topLeft.getX(), topLeft.getY());
			glVertex2i(bottomLeft.getX(), bottomLeft.getY());
			glVertex2i(bottomRight.getX(), bottomRight.getY());
			glVertex2i(topRight.getX(), topRight.getY());
		glEnd();
	}
	
	public static void drawPlane(Texture texture, float x, float y, float z, float width, float height) {
		glColor3f(1, 1, 1);
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3f(x, y, z);
			glTexCoord2f(1, 0);
			glVertex3f(x, y - height, z);
			glTexCoord2f(1, 1);
			glVertex3f(x + width, y - height, z);
			glTexCoord2f(0, 1);
			glVertex3f(x + width, y, z);
		glEnd();
		texture.unbind();
	}
}
