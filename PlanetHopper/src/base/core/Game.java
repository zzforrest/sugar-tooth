/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package base.core;

public abstract class Game
{
	private CoreEngine engine;
	private GameObject root = new GameObject();
	
	public void init()
	{
		
	}
	
	public void input(double delta)
	{
		root.input(delta);
	}
	
	public void update(double delta)
	{
		root.update(delta);
	}
	
	public GameObject getRoot()
	{
		return root;
	}
	
	public void setEngine(CoreEngine engine)
	{
		this.engine = engine;
		root.setEngine(engine);
	}
}
