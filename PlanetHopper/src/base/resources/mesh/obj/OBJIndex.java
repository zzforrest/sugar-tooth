/**
 * @author Blake Doeren
 * Nov 25, 2017
 */

package base.resources.mesh.obj;

public class OBJIndex
{
	public final int pos;
	public final int tex;
	public final int nrm;
	
	public OBJIndex(int pos)
	{
		this(pos, 0, 0);
	}
	
	public OBJIndex(int pos, int tex)
	{
		this(pos, tex, 0);
	}
	
	public OBJIndex(int pos, int tex, int nrm)
	{
		this.pos = pos;
		this.tex = tex;
		this.nrm = nrm;
	}
	
	@Override
	public int hashCode()
	{
		final int BASE = 17;
		final int MULT = 31;
		
		int res = BASE;
		
		res = res * MULT + pos;
		res = res * MULT + tex;
		res = res * MULT + nrm;
		
		return res;
	}
}
