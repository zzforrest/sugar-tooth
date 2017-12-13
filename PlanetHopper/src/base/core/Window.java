/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package base.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import base.Vector2f;

public class Window
{
	/**
	 * Creates a window with the given width, height, and title.
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public static void createWindow(int width, int height, String title)
	{
		Display.setTitle(title);
		
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the window
	 */
	public static void update()
	{
		Display.update();
	}
	
	/**
	 * @return true if the window is being closed
	 */
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static int getWidth()
	{
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight()
	{
		return Display.getDisplayMode().getHeight();
	}
	
	public static float getAspectRatio()
	{
		return (float)getWidth() / (float)getHeight();
	}
	
	public static Vector2f center()
	{
		return new Vector2f(getWidth() / 2.0f, getHeight() / 2.0f);
	}
	
	public static String getTitle()
	{
		return Display.getTitle();
	}
}