/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base;

public class Vector3f
{
	public float x, y, z;
	
	/**
	 * Zero constructor
	 */
	public Vector3f()
	{
		x = y = z = 0;
	}
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Clone constructor
	 * 
	 * @param v
	 */
	public Vector3f(Vector3f v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public Vector3f max(Vector3f v)
	{
		float nx = Math.max(x, v.x);
		float ny = Math.max(y, v.y);
		float nz = Math.max(z, v.z);
		
		return new Vector3f(nz, ny, nz);
	}
	
	public float maxComponent()
	{
		return Math.max(Math.max(x, y), z);
	}
	
	public Vector3f lerp(Vector3f v, float f)
	{
		return v.sub(this).mul(f).add(this);
	}
	
	public Vector3f set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		return this;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	public float dot(Vector3f v)
	{
		return x * v.x + y * v.y + z * v.z;
	}
	
	public Vector3f cross(Vector3f v)
	{
		return new Vector3f(y * v.z - z * v.y,
							z * v.x - x * v.z,
							x * v.y - y * v.x);
	}
	
	public Vector3f normalized()
	{
		float length = length();
		
		return new Vector3f(x / length, y / length, z / length);
	}
	
	public Vector3f rotated(float angle, Vector3f axis)
	{
		float sinhalf = (float)Math.sin(angle / 2);
		float coshalf = (float)Math.cos(angle / 2);
		
		float rx = axis.x * sinhalf;
		float ry = axis.y * sinhalf;
		float rz = axis.z * sinhalf;
		float rw = coshalf;
		
		Quaternion rot = new Quaternion(rx, ry, rz, rw);
		Quaternion con = rot.conjugate();
		Quaternion res = rot.mul(this).mul(con);
		
		return new Vector3f(res.x, res.y, res.z);
	}
	
	public Vector3f rotated(Quaternion q)
	{
		Quaternion conj = q.conjugate();
		
		Quaternion w = q.mul(this).mul(conj);
		
		return new Vector3f(w.x, w.y, w.z);
	}
	
	public Vector3f add(Vector3f v)
	{
		return new Vector3f(x + v.x, y + v.y, z + v.z);
	}
	public Vector3f add(float f)
	{
		return new Vector3f(x + f, y + f, z + f);
	}
	
	public Vector3f sub(Vector3f v)
	{
		return new Vector3f(x - v.x, y - v.y, z - v.z);
	}
	public Vector3f sub(float f)
	{
		return new Vector3f(x - f, y - f, z - f);
	}
	
	public Vector3f mul(Vector3f v)
	{
		return new Vector3f(x * v.x, y * v.y, z * v.z);
	}
	public Vector3f mul(float f)
	{
		return new Vector3f(x * f, y * f, z * f);
	}
	
	public Vector3f div(Vector3f v)
	{
		return new Vector3f(x / v.x, y / v.y, z / v.z);
	}
	public Vector3f div(float f)
	{
		return new Vector3f(x / f, y / f, z / f);
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public boolean equals(Vector3f v)
	{
		return x == v.x && y == v.y && z == v.z;
	}
}