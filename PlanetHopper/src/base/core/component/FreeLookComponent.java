/**
 * @author Blake Doeren
 * Nov 28, 2017
 */

package base.core.component;

import org.lwjgl.input.Keyboard;

import base.Vector2f;
import base.Vector3f;
import base.core.Input;
import base.core.Time;
import base.core.Window;

public class FreeLookComponent extends GameComponent
{
	private static final Vector3f YAXIS = new Vector3f(0, 1, 0);
	
	public float sensitivity = (float)Math.PI;
	public float speed = 10;
	
	public FreeLookComponent()
	{
		
	}

	@Override
	public void input(double delta)
	{
		int impx = 0, impz = 0;
		
		if(Input.getKey(Keyboard.KEY_W))
			impz ++;
		if(Input.getKey(Keyboard.KEY_S))
			impz --;
		if(Input.getKey(Keyboard.KEY_D))
			impx ++;
		if(Input.getKey(Keyboard.KEY_A))
			impx --;

		move(parent.transform.rotation.getForward(), (float)(impz * speed * delta));
		move(parent.transform.rotation.getRight(),   (float)(impx * speed * delta));
		
		if(!Input.isCursorVisible())
		{
			if(Input.getKeyDown(Keyboard.KEY_ESCAPE))
				Input.setCursorVisible(true);
			
			Vector2f dpos = Input.getDeltaMousePosition();
			
			if(dpos.x != 0)
				parent.transform.rotate(YAXIS, dpos.x / Window.getWidth() * sensitivity);
			if(dpos.y != 0)
				parent.transform.rotate(parent.transform.rotation.getRight(), -dpos.y / Window.getHeight() * sensitivity);
			
			Input.setMousePosition(Window.center());
		}
		else
		{
			if(Input.getMouseDown(0))
			{
				Input.setMousePosition(Window.center());
				Input.setCursorVisible(false);
			}
		}
	}
	
	private void move(Vector3f dir, float amt)
	{
		parent.transform.position = parent.transform.position.add(dir.mul(amt));
	}
}
