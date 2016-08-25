package settlers;

import java.util.HashMap;

public class FontManager {

	private HashMap<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	public void add(String path, float size, String name)
	{
		try {
			fonts.put(name, new BitmapFont(path, size));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BitmapFont get(String name)
	{
		return fonts.get(name);
	}
}
