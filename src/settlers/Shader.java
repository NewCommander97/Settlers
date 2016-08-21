package settlers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;

public class Shader {
	
	private int program = 0;
	
	public Shader(String pathVert, String pathFrag) throws Exception {
		int vertShader = 0, fragShader = 0;
		try {
			vertShader = createShader(pathVert, ARBVertexShader.GL_VERTEX_SHADER_ARB);
			fragShader = createShader(pathFrag, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		} catch(Exception e) {
			e.printStackTrace();
			return;
		} finally {
            if(vertShader == 0 || fragShader == 0)
                return;
        }
		
		program = ARBShaderObjects.glCreateProgramObjectARB();
        
        if(program == 0)
            return;
        
        ARBShaderObjects.glAttachObjectARB(program, vertShader);
        ARBShaderObjects.glAttachObjectARB(program, fragShader);
         
        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL_FALSE) {
            System.err.println(getLogInfo(program));
            return;
        }
         
        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL_FALSE) {
            System.err.println(getLogInfo(program));
            return;
        }
	}
	
	public void use() {
		int sampler01 = ARBShaderObjects.glGetUniformLocationARB(program, "sampler01");
		ARBShaderObjects.glUniform1iARB(sampler01, 0);
		ARBShaderObjects.glUseProgramObjectARB(program);
	}
	
	public int getShaderProgramId() {
		return program;
	}
	
	private int createShader(String path, int shaderType) throws Exception {
		int shader = 0;
		try {
			shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
			ARBShaderObjects.glShaderSourceARB(shader, Utilities.readFileAsString(path));
			ARBShaderObjects.glCompileShaderARB(shader);
			if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL_FALSE)
                throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
			return shader;
		} catch(Exception e) {
			ARBShaderObjects.glDeleteObjectARB(shader);
			throw e;
		}
	}
	
	private static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
	
}
