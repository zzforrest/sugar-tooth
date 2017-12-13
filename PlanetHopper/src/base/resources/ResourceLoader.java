/**
 * @author Blake Doeren
 * Oct 22, 2017
 */

package base.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.opengl.TextureLoader;

import base.rendering.Mesh;
import base.rendering.Texture;
import base.resources.mesh.obj.OBJModel;

public class ResourceLoader
{
	private static HashMap<String, MeshResource> loadedMeshes = new HashMap<>();
	
	public static Mesh loadMesh(String filename)
	{
		MeshResource mr = loadedMeshes.get(filename);
		
		if(mr != null)
		{
			mr.addReference();
			return new Mesh(mr);
		}
		
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		
		if(!ext.equals("obj"))
		{
			System.err.println("Mesh file format not supported: " + ext);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		OBJModel model = new OBJModel(filename);
		
		return model.toMesh();
	}
	
	public static void removeMeshResource(String filename)
	{
		loadedMeshes.remove(filename);
	}
	
	public static Texture loadTexture(String filename)
	{
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		
		try
		{
			int id = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + filename))).getTextureID();
			
			return new Texture(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	public static String loadShader(String filename)
	{
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		
		try
		{
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + filename));
			
			String line;
			
			while((line = shaderReader.readLine()) != null)
				shaderSource.append(line).append("\n");
			
			shaderReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return shaderSource.toString();
	}
}
