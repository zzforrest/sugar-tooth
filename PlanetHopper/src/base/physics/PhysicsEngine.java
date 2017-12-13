/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

import java.util.ArrayList;

import base.Vector3f;
import base.core.component.PhysicsObjectComponent;

public class PhysicsEngine
{
	private ArrayList<PhysicsObjectComponent> objects;
	
	public PhysicsEngine()
	{
		objects = new ArrayList<>();
	}
	
	public void addObject(PhysicsObjectComponent objectComponent)
	{
		objects.add(objectComponent);
	}
	
	public void simulate(float delta)
	{
		for(PhysicsObjectComponent objectComponent : objects)
		{
			objectComponent.physicsObject.position = new Vector3f(objectComponent.parent.transform.position);
			objectComponent.physicsObject.integrate(delta);
		}
	}
	
	public void handleCollisions()
	{
		for(PhysicsObjectComponent object0 : objects)
		{
			for(PhysicsObjectComponent object1 : objects)
			{
				if(object1 == object0)
					continue;
				
				Collider c0 = object0.physicsObject.getCollider();
				Collider c1 = object1.physicsObject.getCollider();
				
				Intersection intersection = c0.intersect(c1);
				
				if(intersection == null)	//intersect functions return null when not implemented
					intersection = c1.intersect(c0);
				
				if(intersection == null)
				{
					System.err.println("Collision not implemented between ColliderTypes "
							+ c0.colliderType.toString() + " and "
							+ c1.colliderType.toString() + ".");
					continue;
				}
				
				if(intersection.intersecting)
				{
					//FIXME: temp
					object0.physicsObject.velocity = object0.physicsObject.velocity.mul(-1);
					object1.physicsObject.velocity = object1.physicsObject.velocity.mul(-1);
					object0.physicsObject.integrate(0.1f);
					object1.physicsObject.integrate(0.1f);
					//Oh dear god it's so hacky I'm ashamed
				}
			}
		}
	}
	
	public void updateTransformsOfObjects()
	{
		for(PhysicsObjectComponent object : objects)
		{
			object.parent.transform.position = new Vector3f(object.physicsObject.position);
		}
	}
}
