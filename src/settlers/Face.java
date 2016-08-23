package settlers;

import org.lwjgl.util.vector.Vector4f;

public class Face {
	public Vector4f vertexIndices = new Vector4f();
	public Vector4f texCoodIndices = new Vector4f();
	public Vector4f normalIndices = new Vector4f();
	
	public Face(Vector4f vertex, Vector4f texCood, Vector4f normal) {
		this.vertexIndices = vertex;
		this.texCoodIndices = texCood;
		this.normalIndices = normal;
	}
}
