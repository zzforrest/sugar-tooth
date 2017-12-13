/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base.core;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import base.Matrix4f;
import base.rendering.Vertex;

public class Util
{
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
	{
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		
		for(int i = 0; i < vertices.length; i ++)
		{
			buffer.put(vertices[i].getPos().x);
			buffer.put(vertices[i].getPos().y);
			buffer.put(vertices[i].getPos().z);
			
			buffer.put(vertices[i].getTex().x);
			buffer.put(vertices[i].getTex().y);

			buffer.put(vertices[i].getNrm().x);
			buffer.put(vertices[i].getNrm().y);
			buffer.put(vertices[i].getNrm().z);
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f value)
	{
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i ++)
			for(int j = 0; j < 4; j ++)
				buffer.put(value.get(i, j));
		
		buffer.flip();
		
		return buffer;
	}

	public static IntBuffer createFlippedBuffer(int... values)
	{
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}
	
	public static String[] removeEmptyStrings(String[] in)
	{
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 0; i < in.length; i ++)
			if(!in[i].isEmpty())
				list.add(in[i]);
		
		String[] res = new String[list.size()];
		list.toArray(res);
		
		return res;
	}
	
	public static int[] toIntArray(Integer[] in)
	{
		int[] res = new int[in.length];
		
		for(int i = 0; i < res.length; i ++)
			res[i] = in[i].intValue();
		
		return res;
	}
}
