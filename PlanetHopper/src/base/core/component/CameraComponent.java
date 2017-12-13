/**
 * @author Blake Doeren
 * Nov 28, 2017
 */

package base.core.component;

import base.Matrix4f;
import base.Vector3f;
import base.core.CoreEngine;
import base.core.GameObject;

public class CameraComponent extends GameComponent
{
	private Matrix4f projection;
	
	public CameraComponent(Matrix4f projection)
	{
		this.projection = projection;
	}
	
	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.renderingEngine.mainCamera = this;
	}
	
	public Matrix4f getViewProjected()
	{
		Matrix4f cameraRotMat   = parent.transform.getTransformedRot().conjugate().toRotationMatrix();
		Vector3f cameraPos      = parent.transform.getTransformedPos().mul(-1);
		Matrix4f cameraTransMat = new Matrix4f().toTranslation(cameraPos.x, cameraPos.y, cameraPos.z);
		
		return projection.mul(cameraRotMat.mul(cameraTransMat));
	}
}
