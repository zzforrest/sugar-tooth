/**
 * @author Blake Doeren
 * Dec 5, 2017
 */

package base.core.component;

import base.Vector3f;

public class SpinComponent extends GameComponent
{
	private Vector3f axis;
	private float speed;
	
	public SpinComponent(Vector3f axis, float speed)
	{
		this.axis = axis;
		this.speed = speed;
	}
	
	@Override
	public void update(double delta)
	{
		parent.transform.rotate(axis, (float)(speed * delta));
	}
}
