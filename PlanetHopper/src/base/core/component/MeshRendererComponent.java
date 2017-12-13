/**
 * @author Blake Doeren
 * Nov 27, 2017
 */

package base.core.component;

import base.rendering.Material;
import base.rendering.Mesh;
import base.rendering.RenderingEngine;
import base.rendering.shader.Shader;

public class MeshRendererComponent extends GameComponent
{
	private Mesh mesh;
	private Material material;
	
	public MeshRendererComponent(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		shader.bind();
		shader.updateUniforms(parent.transform, material, renderingEngine);
		mesh.draw();
	}

}
