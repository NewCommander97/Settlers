package settlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;

public class ModelLoader {
	
	public static Model loadModel(String path, Texture texture) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(reader != null) {
			Model m = new Model(texture);
			String line;
			try {
				while((line = reader.readLine()) != null) {
					if(line.startsWith("v ")) {
						float x = Float.valueOf(line.split(" ")[1]);
						float y = Float.valueOf(line.split(" ")[2]);
						float z = Float.valueOf(line.split(" ")[3]);
						m.vertices.add(new Vector3f(x, y, z));
					} else if(line.startsWith("vt ")) {
						float x = Float.valueOf(line.split(" ")[1]);
						float y = Float.valueOf(line.split(" ")[2]);
						m.texCoods.add(new Vector2f(x, y));
					} else if(line.startsWith("vn ")) {
						float x = Float.valueOf(line.split(" ")[1]);
						float y = Float.valueOf(line.split(" ")[2]);
						float z = Float.valueOf(line.split(" ")[3]);
						m.normals.add(new Vector3f(x, y, z));
					} else if(line.startsWith("f ")) {
						Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]),
								Float.valueOf(line.split(" ")[2].split("/")[0]),
								Float.valueOf(line.split(" ")[3].split("/")[0]));
						Vector3f texCoodIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[1]),
								Float.valueOf(line.split(" ")[2].split("/")[1]),
								Float.valueOf(line.split(" ")[3].split("/")[1]));
						Vector3f normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[2]),
								Float.valueOf(line.split(" ")[2].split("/")[2]),
								Float.valueOf(line.split(" ")[3].split("/")[2]));
						m.faces.add(new Face(vertexIndices, texCoodIndices, normalIndices));
					}
				}
				reader.close();
				return m;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
