/**
 * @author Blake Doeren
 * Oct 24, 2017
 */

package base.rendering.shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashMap;

import base.Matrix4f;
import base.Vector3f;
import base.core.Transform;
import base.core.Util;
import base.rendering.Material;
import base.rendering.RenderingEngine;

public class Shader
{
	public RenderingEngine renderingEngine;
	private int program;
	
	private HashMap<String, Integer> uniforms;
	
	public Shader()
	{
		program = glCreateProgram();
		
		if(program == 0)
		{
			System.err.println("Shader program allocation failed");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniforms = new HashMap<>();
	}
	
	public void bind()
	{
		glUseProgram(program);
	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
	{
		
	}
	
	public void addUniform(String uniform)
	{
		int uniformLoc = glGetUniformLocation(program, uniform);
		
		if(uniformLoc == -1)
		{
			System.err.println("Shader could not find uniform " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLoc);
	}
	
	public void addVertexShader(String text)
	{
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text)
	{
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text)
	{
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader()
	{
		glLinkProgram(program);
		
		if(glGetProgrami(program, GL_LINK_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(program, 1024));
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(program, 1024));
			new Exception().printStackTrace();
			System.exit(1);
		}
	}
	
	private void addProgram(String text, int type)
	{
		int shader = glCreateShader(type);
		
		if(shader == 0)
		{
			System.err.println("Shader allocation failed");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(shader, 1024));
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	public void setUniformi(String uniform, int value)
	{
		glUniform1i(uniforms.get(uniform), value);
	}
	
	public void setUniformf(String uniform, float value)
	{
		glUniform1f(uniforms.get(uniform), value);
	}
	
	public void setUniform(String uniform, Vector3f value)
	{
		glUniform3f(uniforms.get(uniform), value.x, value.y, value.z);
	}
	
	public void setUniform(String uniform, Matrix4f value)
	{
		glUniformMatrix4(uniforms.get(uniform), true, Util.createFlippedBuffer(value));
	}
}
