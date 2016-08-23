package settlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;

import org.lwjgl.BufferUtils;

public class Utilities {
	public static String readFileAsString(String filename) throws Exception {
        StringBuilder source = new StringBuilder();
         
        FileInputStream in = new FileInputStream(filename);
         
        Exception exception = null;
         
        BufferedReader reader;
        try{
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
             
            Exception innerExc= null;
            try {
                String line;
                while((line = reader.readLine()) != null)
                    source.append(line).append('\n');
            }
            catch(Exception exc) {
                exception = exc;
            }
            finally {
                try {
                    reader.close();
                }
                catch(Exception exc) {
                    if(innerExc == null)
                        innerExc = exc;
                    else
                        exc.printStackTrace();
                }
            }
             
            if(innerExc != null)
                throw innerExc;
        }
        catch(Exception exc) {
            exception = exc;
        }
        finally {
            try {
                in.close();
            }
            catch(Exception exc) {
                if(exception == null)
                    exception = exc;
                else
                    exc.printStackTrace();
            }
             
            if(exception != null)
                throw exception;
        }
         
        return source.toString();
    }
	
	public static FloatBuffer floatBuffer(float a, float b, float c, float d) {
		   float[] data = new float[] { a, b, c, d };
		   FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
		   fb.put(data);
		   fb.flip();
		   return fb;
	}
	
	public static boolean isEven(int i) {
		return (i & 1) == 0;
	}
	
	public static float[] listToArray(List<Float> list) {
        int size = list != null ? list.size() : 0;
        float[] floatArr = new float[size];
        for (int i = 0; i < size; i++) {
            floatArr[i] = list.get(i);
        }
        return floatArr;
    }
	
	public static int[] intListToArray(List<Integer> list) {
		int size = list != null ? list.size() : 0;
        int[] intArr = new int[size];
        for (int i = 0; i < size; i++) {
            intArr[i] = list.get(i);
        }
        return intArr;
	}
	
	public static float RandomFloat(float start, float end)
	{
		Random random = new Random();
		float r = Math.abs(random.nextFloat());
		if (r < start)
			r += start;
		if (r > end)
			r = r % end;
		return r;
	}
}
