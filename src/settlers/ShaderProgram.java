package settlers;

import java.nio.FloatBuffer;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL21;
import org.lwjgl.util.vector.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
	protected static FloatBuffer buf16Pool;

    /**
     * Makes the "default shader" (0) the active program. In GL 3.1+ core profile,
     * you may run into glErrors if you try rendering with the default shader. 
     */
    public static void unbind() {
        glUseProgram(0);
    }

    public final int program;
    public final int vertex;
    public final int fragment;
    protected String log;

    public ShaderProgram(String vertexSource, String fragmentSource) throws LWJGLException {
        this(vertexSource, fragmentSource, null);
    }

    /**
     * Creates a new shader from vertex and fragment source, and with the given 
     * map of <Integer, String> attrib locations
     * @param vertexShader the vertex shader source string
     * @param fragmentShader the fragment shader source string
     * @param attributes a map of attrib locations for GLSL 120
     * @throws LWJGLException if the program could not be compiled and linked
     */
    public ShaderProgram(String vertexShader, String fragmentShader, Map<Integer, String> attributes) throws LWJGLException {
        //compile the String source
        vertex = compileShader(vertexShader, GL_VERTEX_SHADER);
        fragment = compileShader(fragmentShader, GL_FRAGMENT_SHADER);

        //create the program
        program = glCreateProgram();

        //attach the shaders
        glAttachShader(program, vertex);
        glAttachShader(program, fragment);

        //bind the attrib locations for GLSL 120
        if (attributes != null)
            for (Entry<Integer, String> e : attributes.entrySet())
                glBindAttribLocation(program, e.getKey(), e.getValue());

        //link our program
        glLinkProgram(program);
        
        glValidateProgram(program);

        //grab our info log
        String infoLog = glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH));

        //if some log exists, append it 
        if (infoLog!=null && infoLog.trim().length()!=0)
            log += infoLog;

        //if the link failed, throw some sort of exception
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE)
            throw new LWJGLException(
                    "Failure in linking program. Error log:\n" + infoLog);

        //detach and delete the shaders which are no longer needed
        glDetachShader(program, vertex);
        glDetachShader(program, fragment);
        glDeleteShader(vertex);
        glDeleteShader(fragment);
    }

    /** Compile the shader source as the given type and return the shader object ID. */
    protected int compileShader(String source, int type) throws LWJGLException {
        //create a shader object
        int shader = glCreateShader(type);
        //pass the source string
        glShaderSource(shader, source);
        //compile the source
        glCompileShader(shader);

        //if info/warnings are found, append it to our shader log
        String infoLog = glGetShaderInfoLog(shader,
                glGetShaderi(shader, GL_INFO_LOG_LENGTH));
        if (infoLog!=null && infoLog.trim().length()!=0)
            log += getName(type) +": "+infoLog + "\n";

        //if the compiling was unsuccessful, throw an exception
        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE)
            throw new LWJGLException("Failure in compiling " + getName(type)
                    + ". Error log:\n" + infoLog);

        return shader;
    }

    protected String getName(int shaderType) {
        if (shaderType == GL_VERTEX_SHADER)
            return "GL_VERTEX_SHADER";
        if (shaderType == GL_FRAGMENT_SHADER)
            return "GL_FRAGMENT_SHADER";
        else
            return "shader";
    }

    /**
     * Make this shader the active program.
     */
    public void use() {
        glUseProgram(program);
    }

    /**
     * Destroy this shader program.
     */
    public void destroy() {
        glDeleteProgram(program);
    }

    /**
     * Gets the location of the specified uniform name.
     * @param str the name of the uniform
     * @return the location of the uniform in this program
     */
    public int getUniformLocation(String str) {
        return glGetUniformLocation(program, str);
    }

    /* ------ UNIFORM SETTERS/GETTERS ------ */

    /**
     * Sets the uniform data at the specified location (the uniform type may be int, bool or sampler2D). 
     * @param loc the location of the int/bool/sampler2D uniform 
     * @param i the value to set
     */
    public void setUniformi(int loc, int i) {
        if (loc==-1) return;
        glUniform1i(loc, i);
    }
    
    public int getAttributeLocation(String str) {
    	return GL20.glGetAttribLocation(program, str);
    }
    
    public void setAttribute1s(int loc, short i) {
    	GL20.glVertexAttrib1s(loc, i);
    }

    /**
     * Sends a 4x4 matrix to the shader program.
     * @param loc the location of the mat4 uniform
     * @param transposed whether the matrix should be transposed
     * @param mat the matrix to send
     */
    public void setUniformMatrix(int loc, boolean transposed, Matrix4f mat) {
        if (loc==-1) return;
        if (buf16Pool == null)
            buf16Pool = BufferUtils.createFloatBuffer(16);
        buf16Pool.clear();
        mat.store(buf16Pool);
        buf16Pool.flip();
        GL21.glUniformMatrix4x3fv(loc, transposed, buf16Pool);
    }
}
