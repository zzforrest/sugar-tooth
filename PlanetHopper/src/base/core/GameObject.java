/**
 * @author Blake Doeren
 * Nov 27, 2017
 */

package base.core;

import java.util.ArrayList;

import base.core.component.GameComponent;
import base.rendering.RenderingEngine;
import base.rendering.shader.Shader;

public class GameObject
{
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	
	public CoreEngine engine;
	public GameObject parent;
	public Transform transform;
	
	public GameObject()
	{
		children   = new ArrayList<>();
		components = new ArrayList<>();
		transform  = new Transform();
	}
	
	public void addChild(GameObject child)
	{
		children.add(child);
		child.parent = this;
		
		if(engine != null)
			child.setEngine(engine);
	}
	
	public void addComponent(GameComponent component)
	{
		components.add(component);
		component.parent = this;
		
		if(engine != null)
			component.addToEngine(engine);
	}
	
	public void input(double delta)
	{
		transform.update();
		
		for(GameComponent component : components)
			component.input(delta);
		
		for(GameObject child : children)
			child.input(delta);
	}
	
	public void update(double delta)
	{
		for(GameComponent component : components)
			component.update(delta);
		
		for(GameObject child : children)
			child.update(delta);
	}
	
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		for(GameComponent component : components)
			component.render(shader, renderingEngine);
		
		for(GameObject child : children)
			child.render(shader, renderingEngine);
	}
	
	public void setEngine(CoreEngine engine)
	{
		this.engine = engine;
		
		for(GameComponent component : components)
			component.addToEngine(engine);
		
		for(GameObject child : children)
			child.setEngine(engine);
	}
}
