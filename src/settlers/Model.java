package settlers;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

public class Model {
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector2f> texCoods = new ArrayList<Vector2f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();
	
	public Model() {
		
	}
	
	public void render(float x, float y, float z, float s, float r) {
		glPushMatrix();
		glTranslatef(x, y, z);
		glScalef(s, s, s);
		glRotatef(r, 0, 1, 0);
		glBegin(GL_QUADS);
			for(Face face : faces) {
				Vector3f n1 = normals.get((int) face.normalIndices.w - 1);
				glNormal3f(n1.x, n1.y, n1.z);
				Vector2f t1 = texCoods.get((int) face.texCoodIndices.w - 1);
				glTexCoord2f(t1.x, t1.y);
				Vector3f v1 = vertices.get((int) face.vertexIndices.w - 1);
				glVertex3f(v1.x, v1.y, v1.z);
				
				Vector3f n2 = normals.get((int) face.normalIndices.x - 1);
				glNormal3f(n2.x, n2.y, n2.z);
				Vector2f t2 = texCoods.get((int) face.texCoodIndices.x - 1);
				glTexCoord2f(t2.x, t2.y);
				Vector3f v2 = vertices.get((int) face.vertexIndices.x - 1);
				glVertex3f(v2.x, v2.y, v2.z);
				
				Vector3f n3 = normals.get((int) face.normalIndices.y - 1);
				glNormal3f(n3.x, n3.y, n3.z);
				Vector2f t3 = texCoods.get((int) face.texCoodIndices.y - 1);
				glTexCoord2f(t3.x, t3.y);
				Vector3f v3 = vertices.get((int) face.vertexIndices.y - 1);
				glVertex3f(v3.x, v3.y, v3.z);
				
				Vector3f n4 = normals.get((int) face.normalIndices.z - 1);
				glNormal3f(n4.x, n4.y, n4.z);
				Vector2f t4 = texCoods.get((int) face.texCoodIndices.z - 1);
				glTexCoord2f(t4.x, t4.y);
				Vector3f v4 = vertices.get((int) face.vertexIndices.z - 1);
				glVertex3f(v4.x, v4.y, v4.z);
			}
		glEnd();
		glPopMatrix();
	}
	
	public void render() {
		render(0, 0, 0, 1, 0);
	}
}
