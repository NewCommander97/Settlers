package settlers;

import java.awt.Color;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class ValueNoise {
	
	private float[][] map;
	private float min = 0;
	private float max = 0;
	
	public float[][] generate()
	{
		float[][] x5 = new float[5][5];
		
		x5[0][0] = Utilities.RandomInt(0, 5);
		x5[4][0] = Utilities.RandomInt(0, 5);
		x5[0][4] = Utilities.RandomInt(0, 5);
		x5[4][4] = Utilities.RandomInt(0, 5);
		
		x5 = make5x5(x5);
		float[][] step1 = arrayToarray(x5);
		step1 = makenxn(step1);
		float[][] step2 = arrayToarray(step1);
		step2 = makenxn(step2);
		float[][] step3 = arrayToarray(step2);
		step3 = makenxn(step3);
		float[][] step4 = normalize(step3);
		step4 = makenxn(step4);
		map = normalize(step4);
		return map;
	}
	
	private float[][] arrayToarray(float[][] array)
	{
		int length = array.length + (3*(array.length - 1));
		float[][] res = new float[length][length];
		for (int i=0; i<array.length; i++)
		{
			for (int n=0; n<array.length; n++)
			{
				res[i*4][n*4] = array[i][n] + Utilities.RandomInt(-2, 2);
			}
		}
		return res;
	}
	
	private float[][] makenxn(float[][] xn)
	{
		for (int i=0; i<=xn.length/4; i++)
			for (int n=0; n<xn.length/4; n++)
			{
				float[] row = interpolate(Utilities.ArrayCopy(xn[i*4], n*4, 5));
				for (int x=0; x<5; x++)
					xn[i*4][(n*4)+x] = row[x];
			}
		
		for (int i=0; i<xn.length/4; i++)
			for (int n=0; n<=xn.length/4; n++)
			{
				float[] col = new float[5];
				for (int x=0; x<5; x++)
					col[x] = xn[(i*4)+x][n*4];
				col = interpolate(col);
				for (int x=0; x<5; x++)
					xn[(i*4)+x][n*4] = col[x];
			}
		for (int i=1; i<xn.length-1; i++)
		{
			for (int n=0; n<xn.length/4; n++)
			{
				float[] row = interpolate(Utilities.ArrayCopy(xn[i], n*4, 5));
				for (int x=0; x<5; x++)
					xn[i][(n*4)+x] = row[x];
			}
		}
		
		return xn;
	}
	
	private float[][] make5x5(float[][] x5)
	{		
		x5[0] = interpolate(x5[0]);
		float[] col = new float[5];
		for (int i=0; i<5; i++)
			col[i] = x5[i][0];
		col = interpolate(col);
		for (int i=0; i<5; i++)
			x5[i][0] = col[i];
		x5[4] = interpolate(x5[4]);
		for (int i=0; i<5; i++)
			col[i] = x5[i][4];
		col = interpolate(col);
		for (int i=0; i<5; i++)
			x5[i][4] = col[i];
		for (int i=1; i<4; i++)
			x5[i] = interpolate(x5[i]);
		return x5;
	}
	
	private float[] interpolate(float[] src)
	{
		float[] res = new float[src.length];
		float mid = midValue(src);
		if (src[0] < src[4])
		{
			res[0] = src[0];
			res[1] = src[0] + mid;
			res[2] = src[0] + (mid*2);
			res[3] = src[0] + (mid*3);
			res[4] = src[4];
		}
		else
		{
			res[4] = src[4];
			res[3] = src[4] + mid;
			res[2] = src[4] + (mid*2);
			res[1] = src[4] + (mid*3);
			res[0] = src[0];
		}
		return res;	
	}
	
	private float midValue(float[] array)
	{
		float distance = 0;
		if (array[0] > array[array.length-1])
			distance = array[0] - array[array.length-1];
		else distance = array[array.length-1] - array[0];
		
		return distance / (array.length-1);
	}
	
	private float[][] normalize(float[][] src)
	{
		for (int i=0; i<src.length; i++)
			for (int n=0; n<src.length; n++)
			{
				if (src[i][n] < min)
					min = src[i][n];
				if (src[i][n] > max)
					max = src[i][n];
			}
		max = max / 2;
		for (int i=0; i<src.length; i++)
			for (int n=0; n<src.length; n++)
			{
				src[i][n] -= min;
				src[i][n] = src[i][n] / 2;
			}
		
		return src;
	}
	
	public Texture toTexture()
	{
		ByteBuffer buffer = BufferUtils.createByteBuffer(map.length * map.length * 4);
		float n = 1 / max;
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map.length; x++) {
				Color c = new Color(1 - (n * map[y][x]), 1 - (n * map[y][x]), 1 - (n * map[y][x]));
				buffer.putInt(c.getRGB());
			}
		}
		buffer.flip();
		return new Texture(buffer, map.length, map.length);
	}
}
