/**
 * @author Blake Doeren
 * Dec 12, 2017
 */

package base.core.component;

import base.Vector3f;
import base.core.CoreEngine;
import base.physics.PhysicsObject;

/**
 * @author BlakeMain
 *
 * Primary means of communicating with the physics engine
 */
public class PhysicsObjectComponent extends GameComponent
{
	public PhysicsObject physicsObject;
	
	public PhysicsObjectComponent()
	{
		physicsObject = new PhysicsObject(new Vector3f(), new Vector3f());
	}
	
	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.physicsEngine.addObject(this);
	}
}
