/**
 * @author Blake Doeren
 * Nov 25, 2017
 */

package base.rendering;

import base.Vector3f;

public class DirectionalLight
{
	public BaseLight bLight;
	public Vector3f direction;
	
	public DirectionalLight(BaseLight bLight, Vector3f direction)
	{
		this.bLight = bLight;
		this.direction = direction.normalized();
	}
}
