/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;

import base.Vector3f;
import base.core.Util;
import base.resources.MeshResource;

public class Mesh
{
	private MeshResource resource;
	
	public Mesh()
	{
		
	}
	
	public Mesh(MeshResource resource)
	{
		this.resource = resource;
	}
	
	public void addVertices(Vertex[] vertices, int[] indices)
	{
		addVertices(vertices, indices, false);
	}
	
	public void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals)
	{
		if(calcNormals)
			calcNormals(vertices, indices);
		
		resource = new MeshResource("", indices.length);
		
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
	public void draw()
	{
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 3 * 4);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 5 * 4);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
		glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}
	
	private void calcNormals(Vertex[] vertices, int[] indices)
	{
		for(int i = 0; i < indices.length; i += 3)
		{
			int idx0 = indices[i];
			int idx1 = indices[i + 1];
			int idx2 = indices[i + 2];
			
			Vector3f v1 = vertices[idx1].getPos().sub(vertices[idx0].getPos());
			Vector3f v2 = vertices[idx2].getPos().sub(vertices[idx0].getPos());
			
			Vector3f normal = v1.cross(v2).normalized();
			
			vertices[idx0].setNrm(vertices[idx0].getNrm().add(normal));
			vertices[idx1].setNrm(vertices[idx1].getNrm().add(normal));
			vertices[idx2].setNrm(vertices[idx2].getNrm().add(normal));
		}
		
		for(int i = 0; i < vertices.length; i ++)
			vertices[i].setNrm(vertices[i].getNrm().normalized());
	}
	
	@Override
	protected void finalize()
	{
		resource.removeReference();
	}
}
