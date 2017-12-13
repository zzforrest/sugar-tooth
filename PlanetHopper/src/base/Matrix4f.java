/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base;

public class Matrix4f
{
	private float[][] m;
	
	/**
	 * Zero constructor
	 */
	public Matrix4f()
	{
		m = new float[4][4];
	}
	
	/**
	 * Clone constructor
	 * 
	 * @param m4
	 */
	public Matrix4f(Matrix4f m4)
	{
		this();
		
		for(int i = 0; i < 4; i ++)
			for(int j = 0; j < 4; j ++)
				m[i][j] = m4.get()[i][j];
	}
	
	/**
	 * Sets this matrix to the 4x4 identity matrix
	 * 
	 * @return this
	 */
	public Matrix4f toIdentity()
	{
		m = new float[][]{
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}	
		};
		return this;
	}
	
	public Matrix4f toTranslation(float x, float y, float z)
	{
		m = new float[][]{
				{1, 0, 0, x},
				{0, 1, 0, y},
				{0, 0, 1, z},
				{0, 0, 0, 1}	
		};
		
		return this;
	}
	
	public Matrix4f toScale(float x, float y, float z)
	{
		m = new float[][]{
				{x, 0, 0, 0},
				{0, y, 0, 0},
				{0, 0, z, 0},
				{0, 0, 0, 1}	
		};
		return this;
	}
	
	public Matrix4f toProjection(float fov, float aspectRatio, float znear, float zfar)
	{
		float tanhalffov = (float)Math.tan(fov / 2);
		float zRange = znear - zfar;
		
		m = new float[][]{
				{1.0f / (tanhalffov * aspectRatio), 0,		  0,						0},
				{0, 					   1.0f / tanhalffov, 0,						0},
				{0, 					   0,				  (-znear - zfar) / zRange, 2 * zfar * znear / zRange},
				{0, 					   0, 				  1,						0}	
		};
		
		return this;
	}
	
	public Matrix4f toOrthographic(float left, float right, float bottom, float top, float near, float far)
	{
		float width  = right - left;
		float height = top - bottom;
		float depth  = far - near;
		
		m = new float[][]{
				{2/width, 0, 0,  -(right + left) / width},
				{0, 2/height, 0, -(top + bottom) / height},
				{0, 0, -2/depth, -(far + near)   / depth},
				{0, 0, 0,        1}	
		};
		
		return this;
	}
	
	public Matrix4f toRotation(float x, float y, float z)
	{
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		float tcos = (float)Math.cos(z);
		float tsin = (float)Math.sin(z);
		
		rz.set(new float[][]{
			{tcos, -tsin, 0, 0},
			{tsin, tcos,  0, 0},
			{0,    0,     1, 0},
			{0,    0,     0, 1}	
		});
		
		tcos = (float)Math.cos(x);
		tsin = (float)Math.sin(x);
		
		rx.set(new float[][]{
			{1, 0,    0,     0},
			{0, tcos, -tsin, 0},
			{0, tsin, tcos,  0},
			{0, 0,    0,     1}	
		});
		
		tcos = (float)Math.cos(y);
		tsin = (float)Math.sin(y);
		
		ry.set(new float[][]{
			{tcos, 0, -tsin, 0},
			{0,    1, 0,     0},
			{tsin, 0, tcos,  0},
			{0,    0, 0,     1}	
		});
		
		m = rz.mul(ry.mul(rx)).get();
		
		return this;
	}
	
	public Matrix4f toRotation(Vector3f forward, Vector3f up, Vector3f right)
	{
		Vector3f f = forward.normalized();
		Vector3f r = right.normalized();
		Vector3f u = up.normalized();
		
		m = new float[][]{
				{r.x, r.y, r.z, 0},
				{u.x, u.y, u.z, 0},
				{f.x, f.y, f.z, 0},
				{0,   0,   0,   1}	
		};
		
		return this;
	}
	
	public Matrix4f toRotation(Vector3f forward, Vector3f up)
	{
		Vector3f f = forward.normalized();
		Vector3f r = up.normalized().cross(f);
		Vector3f u = f.cross(r);
		
		return toRotation(f, u, r);	//Subtle propaganda
	}
	
	public Matrix4f mul(Matrix4f r)
	{
		Matrix4f res = new Matrix4f();
		
		for(int x = 0; x < 4; x ++)
			for(int y = 0; y < 4; y ++)
				for(int i = 0; i < 4; i ++)
					res.set(x, y, res.get(x, y) + m[x][i] * r.get(i, y));
		
		return res;
	}
	
	public Vector3f transform(Vector3f v)
	{
		return new Vector3f(m[0][0] * v.x + m[0][1] * v.y + m[0][2] * v.z + m[0][3],
							m[1][0] * v.x + m[1][1] * v.y + m[1][2] * v.z + m[1][3],
							m[2][0] * v.x + m[2][1] * v.y + m[2][2] * v.z + m[2][3]);
	}
	
	public float[][] get()
	{
		return m;
	}
	
	public float get(int x, int y)
	{
		return m[x][y];
	}
	
	public Matrix4f set(float[][] m)
	{
		if(m.length != 4 || m[0].length != 4)
			new IllegalArgumentException("Input matrix not 4x4").printStackTrace();
		
		this.m = m;
		
		return this;
	}
	
	public void set(int x, int y, float f)
	{
		m[x][y] = f;
	}
}
