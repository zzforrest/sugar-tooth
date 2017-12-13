/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package base.core;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import base.Vector2f;

public class Input
{
	/**Number of keys held in memory for getKeyDown and getKeyUp*/
	public static final int KEYCODES     = 256;
	public static final int MOUSEBUTTONS = 32;
	
	private static final boolean[] lastKeys  = new boolean[KEYCODES];
	private static final boolean[] lastMouse = new boolean[MOUSEBUTTONS];
	
	/**
	 * Updates internal variables.
	 * Must be called after everything that uses it during loops.
	 */
	public static void update()
	{
		for(int i = 0; i < KEYCODES; i ++)
			lastKeys[i] = getKey(i);

		for(int i = 0; i < MOUSEBUTTONS; i ++)
			lastMouse[i] = getMouse(i);
	}
	
	/**
	 * @param key
	 * @return true if the key is currently pressed
	 */
	public static boolean getKey(int key)
	{
		return Keyboard.isKeyDown(key);
	}
	
	/**
	 * @param key
	 * @return true if the key has been pressed since the last update
	 */
	public static boolean getKeyDown(int key)
	{
		//Ternary operations are fun
		return (key >= 0 && key < KEYCODES) ?
				(!lastKeys[key] && getKey(key)) :
					false;
	}
	
	/**
	 * @param key
	 * @return true if the key has been released since the last update
	 */
	public static boolean getKeyUp(int key)
	{
		return (key >= 0 && key < KEYCODES) ?
				(lastKeys[key] && !getKey(key)) :
					false;
	}
	
	/**
	 * @param mbutton
	 * @return true if the mouse button is currently held down
	 */
	public static boolean getMouse(int mbutton)
	{
		return Mouse.isButtonDown(mbutton);
	}
	
	/**
	 * @param mbutton
	 * @return true if the mouse button has been pressed since the last update
	 */
	public static boolean getMouseDown(int mbutton)
	{
		return (mbutton >= 0 && mbutton < MOUSEBUTTONS) ?
				(!lastMouse[mbutton] && getMouse(mbutton)) :
					false;
	}
	
	/**
	 * @param mbutton
	 * @return true if the mouse button has been released since the last update
	 */
	public static boolean getMouseUp(int mbutton)
	{
		return (mbutton >= 0 && mbutton < MOUSEBUTTONS) ?
				(lastMouse[mbutton] && !getMouse(mbutton)) :
					false;
	}
	
	/**
	 * @return the position of the mouse relative to the window
	 */
	public static Vector2f getMousePosition()
	{
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
	
	public static void setMousePosition(Vector2f pos)
	{
		Mouse.setCursorPosition((int)pos.x, (int)pos.y);
	}
	
	public static Vector2f getDeltaMousePosition()
	{
		return new Vector2f(Mouse.getDX(), Mouse.getDY());
	}
	
	public static boolean isCursorVisible()
	{
		return !Mouse.isGrabbed();
	}
	
	public static void setCursorVisible(boolean enabled)
	{
		Mouse.setGrabbed(!enabled);
	}
}
