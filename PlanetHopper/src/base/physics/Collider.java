/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

import base.Vector3f;

public abstract class Collider
{
	public enum ColliderType {
		SPHERE,
		AABB,
		PLANE,
	};
	
	public final ColliderType colliderType;
	
	public Collider(ColliderType type)
	{
		this.colliderType = type;
	}
	
	/**
	 * @param c - an object extending collider
	 * @return an intersection object containing the intersection data between this and c, or null if this object does not have that type of collision implemented.
	 */
	public abstract Intersection intersect(Collider c);
	
	public abstract Collider getTransformedCollider(Vector3f position);
}
