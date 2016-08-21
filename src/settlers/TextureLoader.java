package settlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class TextureLoader {
	public static Texture loadTexture(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(img.getWidth() * img.getHeight() * 4);
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				buffer.putInt(img.getRGB(x, y));
			}
		}
		buffer.flip();
		return new Texture(buffer, img.getWidth(), img.getHeight());
	}
}
