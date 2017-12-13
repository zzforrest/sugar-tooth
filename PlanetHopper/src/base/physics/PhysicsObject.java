/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

import base.Vector3f;

public class PhysicsObject
{
	public Vector3f position;
	public Vector3f velocity;
	private Collider collider;
	
	public PhysicsObject(Vector3f position, Vector3f velocity)
	{
		this.position = position;
		this.velocity = velocity;
		this.collider = new SphereCollider(new Vector3f(), 1); //FIXME: temp
	}
	
	public void integrate(float delta)
	{
		position = position.add(velocity.mul(delta));
	}
	
	public Collider getCollider()
	{
		return collider.getTransformedCollider(position);
	}
}
