/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package plannethopper;

import base.core.CoreEngine;
import base.core.Game;
import base.rendering.RenderingEngine;

public class TestMain
{
	public static final int WIDTH  = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "blake rulez da world";
	public static final double FRAMECAP = 50000.0;
	
	public static void main(String[] args)
	{
		System.out.println("Hello World! I'm executing at " + System.currentTimeMillis() + "ms!");
		
		CoreEngine ce = new CoreEngine(WIDTH, HEIGHT, TITLE, FRAMECAP, new TestGame());
		ce.createWindow(true);
		
		ce.start();
	}
}