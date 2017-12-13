/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

import base.Vector3f;

public class SphereCollider extends Collider
{
	public Vector3f center;
	public float    radius;
	
	public SphereCollider(Vector3f center, float radius)
	{
		super(ColliderType.SPHERE);
		
		this.center = center;
		this.radius = radius;
	}
	
	@Override
	public Intersection intersect(Collider c)
	{
		Intersection res = null;
		
		switch(c.colliderType)
		{
		case PLANE:
			res = intersectPlane((PlaneCollider)c);
			break;
		case SPHERE:
			res = intersectSphere((SphereCollider)c);
			break;
		}
		
		return res;
	}
	
	public Intersection intersectPlane(PlaneCollider pc)
	{
		//TODO: rewrite to make sense
		float distanceToCenter = Math.abs(pc.normal.dot(center) + pc.distance);
		float distanceToSphere = distanceToCenter - radius;
		
		return new Intersection(distanceToSphere);
	}
	
	public Intersection intersectSphere(SphereCollider sc)
	{
		float radiusSum = radius + sc.radius;
		float centerDistance = (center.sub(sc.center)).length();
		
		return new Intersection(centerDistance - radiusSum);
	}
	
	public Collider getTransformedCollider(Vector3f position)
	{
		return new SphereCollider(center.add(position), radius);
	}
}
