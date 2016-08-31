package settlers;

public class ValueNoise {
	
	public static float[][] generate()
	{
		float[][] x5 = new float[5][5];
		
		x5[0][0] = Utilities.RandomInt(0, 10);
		x5[4][0] = Utilities.RandomInt(0, 10);
		x5[0][4] = Utilities.RandomInt(0, 10);
		x5[4][4] = Utilities.RandomInt(0, 10);
		
		x5 = make5x5(x5);
		float[][] step1 = arrayToarray(x5);
		step1 = makenxn(step1);
		float[][] step2 = arrayToarray(step1);
		step2 = makenxn(step2);
		float[][] step3 = arrayToarray(step2);
		step3 = makenxn(step3);
		return normalize(step3);
	}
	
	private static float[][] arrayToarray(float[][] array)
	{
		int length = array.length + (3*(array.length - 1));
		float[][] res = new float[length][length];
		for (int i=0; i<array.length; i++)
		{
			for (int n=0; n<array.length; n++)
			{
				if (Utilities.RandomInt(0, 5) > 3)
					res[i*4][n*4] = Utilities.RandomInt(5, 10);
				else
					res[i*4][n*4] = array[i][n];
			}
		}
		return res;
	}
	
	private static float[][] makenxn(float[][] xn)
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
	
	private static float[][] make5x5(float[][] x5)
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
	
	private static float[] interpolate(float[] src)
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
	
	private static float midValue(float[] array)
	{
		float distance = 0;
		if (array[0] > array[array.length-1])
			distance = array[0] - array[array.length-1];
		else distance = array[array.length-1] - array[0];
		
		return distance / (array.length-1);
	}
	
	private static float[][] normalize(float[][] src)
	{
		float min = 0;
		float max = 0;
		for (int i=0; i<src.length; i++)
			for (int n=0; n<src.length; n++)
			{
				if (src[i][n] < min)
					min = src[i][n];
				if (src[i][n] > max)
					max = src[i][n];
			}
		
		for (int i=0; i<src.length; i++)
			for (int n=0; n<src.length; n++)
			{
				src[i][n] -= min;
				src[i][n] = src[i][n] / max;
			}
		
		return src;
	}
}
