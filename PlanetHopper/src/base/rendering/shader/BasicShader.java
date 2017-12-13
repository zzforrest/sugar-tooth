/**
 * @author Blake Doeren
 * Nov 4, 2017
 */

package base.rendering.shader;

import base.Matrix4f;
import base.core.Transform;
import base.rendering.Material;
import base.rendering.RenderingEngine;
import base.resources.ResourceLoader;

public class BasicShader extends Shader
{
	public BasicShader()
	{
		super();
		
		addVertexShader(ResourceLoader.loadShader("BasicShader.vs"));
		addFragmentShader(ResourceLoader.loadShader("BasicShader.fs"));
		compileShader();
		
		addUniform("transform");
		addUniform("color");
	}
	
	@Override
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
	{
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f MVPMatrix = renderingEngine.mainCamera.getViewProjected().mul(worldMatrix);
		
		renderingEngine.shader = this;
		
		if(material.texture != null)
			material.texture.bind();
		else
			RenderingEngine.unbindTextures();
		
		setUniform("transform", MVPMatrix);
		setUniform("color", material.color);
	}
}
