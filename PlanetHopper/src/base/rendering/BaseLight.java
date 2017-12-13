/**
 * @author Blake Doeren
 * Nov 25, 2017
 */

package base.rendering;

import base.Vector3f;

public class BaseLight
{
	public Vector3f color;
	public float intensity;
	
	public BaseLight(Vector3f color, float intensity)
	{
		this.color = color;
		this.intensity = intensity;
	}
}
