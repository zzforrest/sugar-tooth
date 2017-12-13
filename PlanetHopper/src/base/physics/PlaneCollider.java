/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

import base.Vector3f;

public class PlaneCollider extends Collider
{
	public Vector3f normal;
	public float distance;
	
	public PlaneCollider(Vector3f normal, float distance)
	{
		super(ColliderType.PLANE);
		
		this.normal = normal;
		this.distance = distance;
	}
	
	/**
	 * Returns null
	 */
	public Intersection intersect(Collider c)
	{
		return null;
	}
	
	public Collider getTransformedCollider(Vector3f position)
	{
		return null;
	}
	
	public PlaneCollider normalized()
	{
		float magnitude = normal.length();
		return new PlaneCollider(normal.div(magnitude), distance * magnitude);
	}
}
