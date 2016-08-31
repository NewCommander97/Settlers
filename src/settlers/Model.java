package settlers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;

public class Model {
	private List<Vector3f> tangents = new ArrayList<Vector3f>();
	private List<Vector3f> bitangents = new ArrayList<Vector3f>();
	private Texture texture;
	
	private final int vaoId;
	private final int vertexCount;
	private final List<Integer> vboIdList;
	
	public Model(List<Vector3f> vertices, List<Vector2f> texCoords, List<Vector3f> normals, List<Face> faces,Texture texture) {
		this.texture = texture;
		
		float[] verticesBufferArray = new float[faces.size() * 3 * 3];
		float[] texCoordsBufferArray = new float[faces.size() * 2 * 3];
		float[] normalsBufferArray = new float[faces.size() * 3 * 3];
		float[] tangentsBufferArray = new float[faces.size() * 3 * 3];
		float[] bitangentsBufferArray = new float[faces.size() * 3 * 3];
		
		int[] indicesBufferArray = new int[faces.size() * 3];
		
		for(int i = 0; i < faces.size(); i++) {
			verticesBufferArray[(i * 9)] = vertices.get((int) faces.get(i).vertexIndices.x - 1).x;
			verticesBufferArray[(i * 9) + 1] = vertices.get((int) faces.get(i).vertexIndices.x - 1).y;
			verticesBufferArray[(i * 9) + 2] = vertices.get((int) faces.get(i).vertexIndices.x - 1).z;
			
			verticesBufferArray[(i * 9) + 3] = vertices.get((int) faces.get(i).vertexIndices.y - 1).x;
			verticesBufferArray[(i * 9) + 4] = vertices.get((int) faces.get(i).vertexIndices.y - 1).y;
			verticesBufferArray[(i * 9) + 5] = vertices.get((int) faces.get(i).vertexIndices.y - 1).z;
			
			verticesBufferArray[(i * 9) + 6] = vertices.get((int) faces.get(i).vertexIndices.z - 1).x;
			verticesBufferArray[(i * 9) + 7] = vertices.get((int) faces.get(i).vertexIndices.z - 1).y;
			verticesBufferArray[(i * 9) + 8] = vertices.get((int) faces.get(i).vertexIndices.z - 1).z;
			
			
			texCoordsBufferArray[(i * 6)] = texCoords.get((int) (faces.get(i).texCoodIndices.x - 1)).x;
			texCoordsBufferArray[(i * 6) + 1] = texCoords.get((int) (faces.get(i).texCoodIndices.x - 1)).y;
			
			texCoordsBufferArray[(i * 6) + 2] = texCoords.get((int) (faces.get(i).texCoodIndices.y - 1)).x;
			texCoordsBufferArray[(i * 6) + 3] = texCoords.get((int) (faces.get(i).texCoodIndices.y - 1)).y;
			
			texCoordsBufferArray[(i * 6) + 4] = texCoords.get((int) (faces.get(i).texCoodIndices.z - 1)).x;
			texCoordsBufferArray[(i * 6) + 5] = texCoords.get((int) (faces.get(i).texCoodIndices.z - 1)).y;
			
			
			normalsBufferArray[(i * 9)] = normals.get((int) (faces.get(i).normalIndices.x - 1)).x;
			normalsBufferArray[(i * 9) + 1] = normals.get((int) (faces.get(i).normalIndices.x - 1)).y;
			normalsBufferArray[(i * 9) + 2] = normals.get((int) (faces.get(i).normalIndices.x - 1)).z;
			
			normalsBufferArray[(i * 9) + 3] = normals.get((int) (faces.get(i).normalIndices.y - 1)).x;
			normalsBufferArray[(i * 9) + 4] = normals.get((int) (faces.get(i).normalIndices.y - 1)).y;
			normalsBufferArray[(i * 9) + 5] = normals.get((int) (faces.get(i).normalIndices.y - 1)).z;
			
			normalsBufferArray[(i * 9) + 6] = normals.get((int) (faces.get(i).normalIndices.z - 1)).x;
			normalsBufferArray[(i * 9) + 7] = normals.get((int) (faces.get(i).normalIndices.z - 1)).y;
			normalsBufferArray[(i * 9) + 8] = normals.get((int) (faces.get(i).normalIndices.z - 1)).z;
			
			
			Vector3f deltaPos1 = new Vector3f();
			vertices.get((int) faces.get(i).vertexIndices.y - 1).sub(vertices.get((int) faces.get(i).vertexIndices.x - 1), deltaPos1);
			Vector3f deltaPos2 = new Vector3f();
			vertices.get((int) faces.get(i).vertexIndices.z - 1).sub(vertices.get((int) faces.get(i).vertexIndices.x - 1), deltaPos2);
			
			Vector2f deltaTexCood1 = new Vector2f();
			Vector2f.sub(texCoords.get((int) faces.get(i).texCoodIndices.y - 1), texCoords.get((int) faces.get(i).texCoodIndices.x - 1), deltaTexCood1);
			Vector2f deltaTexCood2 = new Vector2f();
			Vector2f.sub(texCoords.get((int) faces.get(i).texCoodIndices.z - 1), texCoords.get((int) faces.get(i).texCoodIndices.x - 1), deltaTexCood2);
			
			float r = 1.0f / ((deltaTexCood1.x * deltaTexCood2.y) - (deltaTexCood1.y * deltaTexCood2.x));
			
			Vector3f buffer3f1 = new Vector3f();
			Vector3f buffer3f2 = new Vector3f();
			Vector3f tangent = new Vector3f();
			deltaPos1.mul(deltaTexCood2.y, buffer3f1);
			deltaPos2.mul(deltaTexCood1.y, buffer3f2);
			buffer3f1.sub(buffer3f2, tangent);
			tangent.mul(r);
			
			tangentsBufferArray[(i * 3)] = tangent.x;
			tangentsBufferArray[(i * 3) + 1] = tangent.y;
			tangentsBufferArray[(i * 3) + 2] = tangent.z;
			
			Vector3f bitangent = new Vector3f();
			deltaPos2.mul(deltaTexCood1.x, buffer3f1);
			deltaPos1.mul(deltaTexCood2.x, buffer3f2);
			buffer3f1.sub(buffer3f2, bitangent);
			bitangent.mul(r);
			
			bitangentsBufferArray[(i * 3)] = bitangent.x;
			bitangentsBufferArray[(i * 3) + 1] = bitangent.y;
			bitangentsBufferArray[(i * 3) + 2] = bitangent.z;
			
			
			indicesBufferArray[(i * 3)] = (i * 3);
			indicesBufferArray[(i * 3) + 1] = (i * 3) + 1;
			indicesBufferArray[(i * 3) + 2] = (i * 3) + 2;
		}
		
		vertexCount = faces.size() * 3;
		vboIdList = new ArrayList();
		
		vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Position VBO
        int vboId = glGenBuffers();
        vboIdList.add(vboId);
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(verticesBufferArray.length);
        verticesBuffer.put(verticesBufferArray).flip();
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // Texture coordinates VBO
        vboId = glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer texCoordsBuffer = BufferUtils.createFloatBuffer(texCoordsBufferArray.length);
        texCoordsBuffer.put(texCoordsBufferArray).flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, texCoordsBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        // Vertex normals VBO
        vboId = glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer normalsBuffer = BufferUtils.createFloatBuffer(normalsBufferArray.length);
        normalsBuffer.put(normalsBufferArray).flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, normalsBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
        
        // Tangent VBO
        vboId = glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer tangentBuffer = BufferUtils.createFloatBuffer(tangentsBufferArray.length);
        tangentBuffer.put(tangentsBufferArray).flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, tangentsBufferArray, GL_STATIC_DRAW);
        glVertexAttribPointer(3, 3, GL_FLOAT, false, 0, 0);
        
        // Bitangent VBO
        vboId = glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer bitangentBuffer = BufferUtils.createFloatBuffer(bitangentsBufferArray.length);
        tangentBuffer.put(bitangentsBufferArray).flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, bitangentsBufferArray, GL_STATIC_DRAW);
        glVertexAttribPointer(4, 3, GL_FLOAT, false, 0, 0);

        // Index VBO
        vboId = glGenBuffers();
        vboIdList.add(vboId);
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferArray.length);
        indicesBuffer.put(indicesBufferArray).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
	}
	
	public void render(float x, float y, float z, float s, float r) {
		glPushMatrix();
		glTranslatef(x, y, z);
		glScalef(s, s, s);
		glRotatef(r, 0, 1, 0);
		
		initRender();
		
		glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
		
		endRender();
		
		glPopMatrix();
	}
	
	public void render() {
		render(0, 0, 0, 1, 0);
	}
	
	private void initRender() {
        if (texture != null) {
        	glColor3f(1.0f, 1.0f, 1.0f);
            glActiveTexture(GL_TEXTURE0);
            texture.bind();
        }

        // Draw the mesh
        glBindVertexArray(getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glEnableVertexAttribArray(4);
    }
	
	private void endRender() {
        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);
        glDisableVertexAttribArray(4);
        glBindVertexArray(0);

        texture.unbind();
    }
	
	public int getVaoId() {
        return vaoId;
    }
	
	public int getVertexCount() {
        return vertexCount;
    }
}
