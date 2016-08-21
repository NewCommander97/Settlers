package settlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

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
}
