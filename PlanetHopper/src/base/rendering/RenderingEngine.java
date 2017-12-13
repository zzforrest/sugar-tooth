/**
 * @author Blake Doeren
 * Nov 27, 2017
 */

package base.rendering;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import base.Vector3f;
import base.core.GameObject;
import base.core.Window;
import base.core.component.CameraComponent;
import base.rendering.shader.BasicShader;
import base.rendering.shader.Shader;

public class RenderingEngine
{
	public CameraComponent mainCamera;
	public Shader shader;
	
	public RenderingEngine()
	{
		shader = new BasicShader();
		shader.setUniform("color", new Vector3f(1, 1, 1));
		
		glClearColor(0, 0, 0, 0);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	public void render(GameObject object)
	{
		if(mainCamera == null)
			new Exception("RenderingEngine.mainCamera not defined! Catastrophic error!").printStackTrace();
		
		clearScreen();
		object.render(shader, this);
	}
	
	public static void clearScreen()
	{
		//TODO; stencil buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void setClearColor(Vector3f col)
	{
		glClearColor(col.x, col.y, col.z, 1.0f);
	}
	
	public static void setTextures(boolean enable)
	{
		if(enable)
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
	}
	
	public static void unbindTextures()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static String getOpenGLVersion()
	{
		return glGetString(GL_VERSION);
	}
	}
