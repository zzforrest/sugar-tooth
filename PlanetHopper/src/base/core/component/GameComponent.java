/**
 * @author Blake Doeren
 * Nov 27, 2017
 */

package base.core.component;

import base.core.CoreEngine;
import base.core.GameObject;
import base.rendering.RenderingEngine;
import base.rendering.shader.Shader;

public abstract class GameComponent
{
	public GameObject parent;
	
	public GameComponent()
	{
		
	}
	
	public void input(double delta){}
	public void update(double delta){}
	public void render(Shader shader, RenderingEngine renderingEngine){}
	
	public void addToEngine(CoreEngine engine)
	{
		
	}
}
