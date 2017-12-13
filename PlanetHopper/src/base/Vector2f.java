/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package base;

public class Vector2f
{
	public float x, y;
	
	/**
	 * Zero constructor
	 */
	public Vector2f()
	{
		x = y = 0;
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Clone constructor
	 * 
	 * @param v
	 */
	public Vector2f(Vector2f v)
	{
		x = v.x;
		y = v.y;
	}
	
	/**
	 * @param v
	 * @param f
	 * @return linear interpolation from this vector to v
	 */
	public Vector2f lerp(Vector2f v, float f)
	{
		return v.sub(this).mul(f).add(this);
	}
	
	/**
	 * Set all components of this vector
	 * 
	 * @param x
	 * @param y
	 * @return this
	 */
	public Vector2f set(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	/**
	 * @return length of this vector
	 */
	public float length()
	{
		return (float)Math.sqrt(x * x + y * y);
	}
	
	/**
	 * @param v
	 * @return dot product of this and v
	 */
	public float dot(Vector2f v)
	{
		return x * v.x + y * v.y;
	}
	
	public Vector2f normalized()
	{
		float length = length();
		
		return new Vector2f(x / length, y / length);
	}
	
	/**
	 * @param theta
	 * @return a copy of this vector, rotated about the origin by theta
	 */
	public Vector2f rotated(float theta)
	{
		float cos = (float)Math.cos(theta);
		float sin = (float)Math.sin(theta);
		
		return new Vector2f(x * cos - y * sin,
							x * sin + y * cos);
	}
	
	/**
	 * @param v
	 * @return a new vector that is the component sum of this and v
	 */
	public Vector2f add(Vector2f v)
	{
		return new Vector2f(x + v.x, y + v.y);
	}
	public Vector2f add(float f)
	{
		return new Vector2f(x + f, y + f);
	}
	
	public Vector2f sub(Vector2f v)
	{
		return new Vector2f(x - v.x, y - v.y);
	}
	public Vector2f sub(float f)
	{
		return new Vector2f(x - f, y - f);
	}
	
	public Vector2f mul(Vector2f v)
	{
		return new Vector2f(x * v.x, y * v.y);
	}
	public Vector2f mul(float f)
	{
		return new Vector2f(x * f, y * f);
	}
	
	public Vector2f div(Vector2f v)
	{
		return new Vector2f(x / v.x, y / v.y);
	}
	public Vector2f div(float f)
	{
		return new Vector2f(x / f, y / f);
	}
	
	public float cross(Vector2f v)
	{
		return x * v.y - y * v.x;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * @param v
	 * @return true if all components of this and v are equal
	 */
	public boolean equals(Vector2f v)
	{
		return x == v.x && y == v.y;
	}
}
