/**
 * @author Blake Doeren
 * Nov 7, 2017
 */

package base.rendering;

import base.Vector3f;

public class Material
{
	public Texture texture;
	public Vector3f color;
	public float specIntensity;
	public float specExponent;
	
	public Material(Texture texture, Vector3f color)
	{
		this.texture = texture;
		this.color = color;
	}
}
