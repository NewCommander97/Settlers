package settlers;

import java.util.HashMap;

public class TextureManager {
	private static HashMap<String, Texture> textures;
	
	public TextureManager() {
		textures = new HashMap<String, Texture>();
	}
	
	public Texture getTexture(String name) {
		return (Texture) textures.get(name);
	}
	
	public void addTexture(String name, String path) {
		Texture texture = TextureLoader.loadTexture(path);
		textures.put(name, texture);
	}
	
	public void removeTexture(String name) {
		textures.remove(name);
	}
}
