/**
 * @author Blake Doeren
 * Nov 25, 2017
 */

package base.resources.mesh.obj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import base.Vector2f;
import base.Vector3f;
import base.core.Util;
import base.rendering.Mesh;
import base.rendering.Vertex;
import base.resources.MeshResource;

public class OBJModel
{
	public ArrayList<Vector3f> positions;
	public ArrayList<Vector2f> texcoords;
	public ArrayList<Vector3f> normals;
	public ArrayList<OBJIndex> indices;
	
	public boolean hasTex = false;
	public boolean hasNrm = false;
	
	public OBJModel(String filename)
	{
		positions = new ArrayList<>();
		texcoords = new ArrayList<>();
		normals   = new ArrayList<>();
		indices   = new ArrayList<>();
		
		BufferedReader meshReader = null;
		
		try
		{
			meshReader = new BufferedReader(new FileReader("./res/models/" + filename));
			
			String line;
			
			while((line = meshReader.readLine()) != null)
			{
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyStrings(tokens);
				
				if(tokens.length == 0 || tokens[0].startsWith("#"))
					continue;
				else if(tokens[0].equals("v"))
				{
					positions.add(new Vector3f(Float.valueOf(tokens[1]),
											 Float.valueOf(tokens[2]),
											 Float.valueOf(tokens[3])));
				}
				else if(tokens[0].equals("vt"))
				{
					texcoords.add(new Vector2f(Float.valueOf(tokens[1]),
											Float.valueOf(tokens[2])));
					hasTex = true;
				}
				else if(tokens[0].equals("vn"))
				{
					normals.add(new Vector3f(Float.valueOf(tokens[1]),
											 Float.valueOf(tokens[2]),
											 Float.valueOf(tokens[3])));
					hasNrm = true;
				}
				else if(tokens[0].equals("f"))
				{
					for(int i = 0; i < 3; i ++)
					{
						String[] values = tokens[1 + i].split("/");
						
						int i0 = Integer.valueOf(values[0]) - 1;
						int i1 = 0;
						int i2 = 0;
						
						if(hasTex)
							i1 = Integer.valueOf(values[1]) - 1;
						
						if(hasNrm)
							i2 = Integer.valueOf(values[2]) - 1;
						
						indices.add(new OBJIndex(i0, i1, i2));
					}
				}
			}
			
			meshReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Mesh toMesh()
	{
		ArrayList<Vertex> resvertices = new ArrayList<>();
		ArrayList<Integer> resindices = new ArrayList<>();
		
		for(int i = 0; i < indices.size(); i ++)
		{
			Vertex vert = new Vertex(positions.get(indices.get(i).pos));
			
			if(hasTex)
				vert.setTex(texcoords.get(indices.get(i).tex));
			else
				vert.setTex(new Vector2f());
			if(hasNrm)
				vert.setNrm(normals.get(indices.get(i).nrm));
			else
				vert.setNrm(new Vector3f());
			
			resindices.add(resvertices.size());
			resvertices.add(vert);
		}
		
		Vertex[] vertArr = new Vertex[resvertices.size()];
		Integer[] indArr = new Integer[resindices.size()];
		
		resvertices.toArray(vertArr);
		resindices.toArray(indArr);
		
		Mesh res = new Mesh();
		res.addVertices(vertArr, Util.toIntArray(indArr), !hasNrm);
		
		return res;
	}
}
