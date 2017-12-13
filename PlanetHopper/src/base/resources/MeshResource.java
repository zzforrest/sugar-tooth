/**
 * @author Blake Doeren
 * Dec 8, 2017
 */

package base.resources;

import static org.lwjgl.opengl.GL15.*;

public class MeshResource
{
	private String filename;
	private int vbo;
	private int ibo;
	private int size;
	private int refcount;
	
	public MeshResource(String filename, int size)
	{
		this.filename = filename;
		this.size = size;
		
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		refcount = 1;
	}
	
	@Override
	protected void finalize()
	{
		glDeleteBuffers(vbo);
		glDeleteBuffers(ibo);
	}
	
	public void addReference()
	{
		refcount ++;
	}
	
	public void removeReference()
	{
		refcount --;
		
		if(refcount == 0 && filename != "")
		{
			ResourceLoader.removeMeshResource(filename);
		}
	}
	
	public int getVbo()
	{
		return vbo;
	}
	
	public int getIbo()
	{
		return ibo;
	}
	
	public int getSize()
	{
		return size;
	}
}
