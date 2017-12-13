/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

import base.Vector3f;
import base.physics.Collider.ColliderType;

public class AABBCollider extends Collider
{
	public Vector3f minExtents;
	public Vector3f maxExtents;
	
	public AABBCollider(Vector3f minExtents, Vector3f maxExtents)
	{
		super(ColliderType.AABB);
		
		this.minExtents = minExtents;
		this.maxExtents = maxExtents;
	}
	
	public Intersection intersect(Collider c)
	{
		Intersection res = null;
		
		switch(c.colliderType)
		{
//		case PLANE:
//			res = intersectPlane((PlaneCollider)c);
//			break;
//		case SPHERE:
//			res = intersectSphere((SphereCollider)c);
//			break;
		case AABB:
			res = intersectAABB((AABBCollider)c);
			break;
		}
		
		return res;
	}
	
//	public Intersection intersectPlane(PlaneCollider pc)
//	{
//		float distanceToCenter = Math.abs(pc.normal.dot(center) + pc.distance);
//		float distanceToSphere = distanceToCenter - radius;
//		
//		return new Intersection(distanceToSphere);
//	}
//	
//	public Intersection intersectSphere(SphereCollider sc)
//	{
//		float radiusSum = radius + sc.radius;
//		float centerDistance = center.sub(sc.center).length();
//		
//		return new Intersection(centerDistance - radiusSum);
//	}
	
	public Intersection intersectAABB(AABBCollider aabb)
	{
		Vector3f distances0 = aabb.minExtents.sub(maxExtents);
		Vector3f distances1 = minExtents.sub(aabb.maxExtents);
		
		Vector3f distances = distances0.max(distances1);
		
		float maxdistance = distances.maxComponent();
		
		return new Intersection(maxdistance);
	}
	
	public Collider getTransformedCollider(Vector3f position)
	{
		return new AABBCollider(minExtents.add(position), maxExtents.add(position));
	}
}
