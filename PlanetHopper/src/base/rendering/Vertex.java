/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base.rendering;

import base.Vector2f;
import base.Vector3f;

public class Vertex
{
	public static final int SIZE = 8;
	
	private Vector3f pos;
	private Vector2f tex;
	private Vector3f nrm;
	
	public Vertex(Vector3f pos)
	{
		this(pos, new Vector2f());
	}
	
	public Vertex(Vector3f pos, Vector2f tex)
	{
		this(pos, tex, new Vector3f());
	}
	
	public Vertex(Vector3f pos, Vector2f tex, Vector3f nrm)
	{
		this.pos = pos;
		this.tex = tex;
		this.nrm = nrm;
	}
	
	public Vector3f getPos()
	{
		return pos;
	}

	public void setPos(Vector3f pos)
	{
		this.pos = pos;
	}

	public Vector2f getTex()
	{
		return tex;
	}

	public void setTex(Vector2f tex)
	{
		this.tex = tex;
	}

	public Vector3f getNrm()
	{
		return nrm;
	}

	public void setNrm(Vector3f nrm)
	{
		this.nrm = nrm;
	}
}
