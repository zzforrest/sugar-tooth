/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base;

public class Quaternion
{
	public float x, y, z, w;
	
	/**
	 * Zero constructor
	 */
	public Quaternion()
	{
		x = y = z = w = 0;
	}
	
	public Quaternion(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Quaternion(Vector3f axis, float angle)
	{
		float sin = (float)Math.sin(angle / 2);
		float cos = (float)Math.cos(angle / 2);
		
		x = axis.x * sin;
		y = axis.y * sin;
		z = axis.z * sin;
		w = cos;
	}
	
	public Quaternion(Vector3f forward, Vector3f up)
	{
		this(new Matrix4f().toRotation(forward, up));
	}
	
	public Quaternion(Matrix4f rot)
	{
		float trace = rot.get(0, 0) + rot.get(1, 1) + rot.get(2, 2);
		float s;
		
		if(trace > 0)
		{
			s = 0.5f / (float)Math.sqrt(trace + 1.0f);
			
			w = 0.25f / s;
			x = (rot.get(1, 2) - rot.get(2, 1)) * s;
			y = (rot.get(2, 0) - rot.get(0, 2)) * s;
			z = (rot.get(0, 1) - rot.get(1, 0)) * s;
		}
		else if(rot.get(0, 0) > rot.get(1, 1) && rot.get(0, 0) > rot.get(2, 2))
		{
			s = 2.0f * (float)Math.sqrt(1.0f + rot.get(0, 0) - rot.get(1, 1) - rot.get(2, 2));
			
			w = (rot.get(1, 2) - rot.get(2, 1)) * s;
			x = 0.25f / s;
			y = (rot.get(1, 0) - rot.get(0, 1)) * s;
			z = (rot.get(2, 0) - rot.get(0, 2)) * s;
		}
		else if(rot.get(1, 1) > rot.get(2, 2))
		{
			s = 2.0f * (float)Math.sqrt(1.0f + rot.get(1, 1) - rot.get(0, 0) - rot.get(2, 2));

			w = (rot.get(2, 0) - rot.get(0, 2)) * s;
			x = (rot.get(1, 0) - rot.get(0, 1)) * s;
			y = 0.25f / s;
			z = (rot.get(2, 1) - rot.get(1, 2)) * s;
		}
		else
		{
			s = 2.0f * (float)Math.sqrt(1.0f + rot.get(2, 2) - rot.get(0, 0) - rot.get(1, 1));

			w = (rot.get(0, 1) - rot.get(1, 0)) * s;
			x = (rot.get(2, 0) - rot.get(0, 2)) * s;
			y = (rot.get(1, 2) - rot.get(2, 1)) * s;
			z = 0.25f / s;
		}
		
		float length = length();
		x /= length;
		y /= length;
		z /= length;
		w /= length;
	}
	
	/**
	 * Clone constructor
	 * 
	 * @param v
	 */
	public Quaternion(Quaternion v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
		w = v.w;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public float dot(Quaternion v)
	{
		return x * v.x + y * v.y + z * v.z + w * v.w;
	}
	
	public Quaternion normalized()
	{
		float length = length();
		
		return new Quaternion(x / length, y / length, z / length, w / length);
	}
	
	public Quaternion conjugate()
	{
		return new Quaternion(-x, -y, -z, w);
	}
	
	public Quaternion add(Quaternion v)
	{
		return new Quaternion(x + v.x, y + v.y, z + v.z, w + v.w);
	}
	public Quaternion add(float f)
	{
		return new Quaternion(x + f, y + f, z + f, w + f);
	}
	
	public Quaternion sub(Quaternion v)
	{
		return new Quaternion(x - v.x, y - v.y, z - v.z, w - v.w);
	}
	public Quaternion sub(float f)
	{
		return new Quaternion(x - f, y - f, z - f, w - f);
	}
	
	public Quaternion mul(Quaternion v)
	{
		float dw = w * v.w - x * v.x - y * v.y - z * v.z;
		float dx = x * v.w + w * v.x + y * v.z - z * v.y;
		float dy = y * v.w + w * v.y + z * v.x - x * v.z;
		float dz = z * v.w + w * v.z + x * v.y - y * v.x;
		
		return new Quaternion(dx, dy, dz, dw);
	}
	
	public Quaternion mul(Vector3f v)
	{
		float dw =-x * v.x - y * v.y - z * v.z;
		float dx = w * v.x + y * v.z - z * v.y;
		float dy = w * v.y + z * v.x - x * v.z;
		float dz = w * v.z + x * v.y - y * v.x;
		
		return new Quaternion(dx, dy, dz, dw);
	}
	
	public Matrix4f toRotationMatrix()
	{
		Vector3f forward = new Vector3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y));
		Vector3f up      = new Vector3f(2.0f * (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
		Vector3f right   = new Vector3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));
		
		return new Matrix4f().toRotation(forward, up, right);
	}
	
	/**
	 * @return a +X vector rotated by this
	 */
	public Vector3f getRight()
	{
		return new Vector3f(1, 0, 0).rotated(this);
	}
	
	/**
	 * @return a -X vector rotated by this
	 */
	public Vector3f getLeft()
	{
		return new Vector3f(-1, 0, 0).rotated(this);
	}
	
	/**
	 * @return a +Y vector rotated by this
	 */
	public Vector3f getUp()
	{
		return new Vector3f(0, 1, 0).rotated(this);
	}
	
	/**
	 * @return a -Y vector rotated by this
	 */
	public Vector3f getDown()
	{
		return new Vector3f(0, -1, 0).rotated(this);
	}
	
	/**
	 * @return a +Z vector rotated by this
	 */
	public Vector3f getForward()
	{
		return new Vector3f(0, 0, 1).rotated(this);
	}
	
	/**
	 * @return a -Z vector rotated by this
	 */
	public Vector3f getBack()
	{
		return new Vector3f(0, 0, -1).rotated(this);
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ", " + w + ")";
	}
	
	public boolean equals(Quaternion q)
	{
		return x == q.x && y == q.y && z == q.z && w == q.w;
	}
}
