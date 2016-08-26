package settlers;

public class Face {
	public Vector3f vertexIndices = new Vector3f();
	public Vector3f texCoodIndices = new Vector3f();
	public Vector3f normalIndices = new Vector3f();
	
	public Face(Vector3f vertex, Vector3f texCood, Vector3f normal) {
		this.vertexIndices = vertex;
		this.texCoodIndices = texCood;
		this.normalIndices = normal;
	}
}
