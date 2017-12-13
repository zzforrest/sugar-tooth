/**
 * @author Blake Doeren
 * Oct 26, 2017
 */

package base.core;

import base.Matrix4f;
import base.Quaternion;
import base.Vector3f;

public class Transform
{
	public Vector3f position;
	public Quaternion rotation;
	public Vector3f scale;
	
	private Vector3f oldposition;
	private Quaternion oldrotation;
	private Vector3f oldscale;
	
	private Transform parent;
	private Matrix4f parentMatrix;
	private boolean hasChanged;
	
	public Transform()
	{
		position = new Vector3f();
		rotation = new Quaternion(0, 0, 0, 1);
		scale    = new Vector3f(1, 1, 1);
		
		parentMatrix = new Matrix4f().toIdentity();
		hasChanged = true;
	}
	
	public void update()
	{
		if(oldposition == null)
			hasChanged = true;
		else if(!position.equals(oldposition))
			hasChanged = true;
		else if(!rotation.equals(oldrotation))
			hasChanged = true;
		else if(!scale.equals(oldscale))
			hasChanged = true;
		else
			hasChanged = false;
		
		oldposition = new Vector3f(position);
		oldrotation = new Quaternion(rotation);
		oldscale    = new Vector3f(scale);
	}
	
	public boolean hasChanged()
	{
		return hasChanged;
	}
	
	public Matrix4f getParentMatrix()
	{
		if(parent != null && parent.hasChanged())
			parentMatrix = parent.getTransformation();
		return parentMatrix;
	}
	
	public Matrix4f getTranslationMatrix()
	{
		return new Matrix4f().toTranslation(position.x, position.y, position.z);
	}
	
	public Matrix4f getRotationMatrix()
	{
		return rotation.toRotationMatrix();
	}
	
	public Matrix4f getScaleMatrix()
	{
		return new Matrix4f().toScale(scale.x, scale.y, scale.z);
	}
	
	public Vector3f getTransformedPos()
	{
		return getParentMatrix().transform(position);
	}
	
	public void rotate(Vector3f axis, float angle)
	{
		rotation = new Quaternion(axis, angle).mul(rotation).normalized();
	}
	
	public Quaternion getTransformedRot()
	{
		if(parent != null)
			return parent.getTransformedRot().mul(rotation);
		
		return rotation;
	}
	
	public Matrix4f getTransformation()
	{
		return getParentMatrix().mul(getTranslationMatrix().mul(getRotationMatrix().mul(getScaleMatrix())));
	}
}
