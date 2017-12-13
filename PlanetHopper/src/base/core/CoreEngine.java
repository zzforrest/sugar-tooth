/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package base.core;

import base.physics.PhysicsEngine;
import base.rendering.RenderingEngine;

public class CoreEngine
{
	public RenderingEngine renderingEngine;
	public PhysicsEngine   physicsEngine;
	
	private Game game;
	
	private int width;
	private int height;
	private String title;
	private double frametime;
	
	private boolean running;
	
	public CoreEngine(int width, int height, String title, double framecap, Game game)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		frametime = 1.0 / framecap;
		this.game = game;
		
		running = false;
		
		game.setEngine(this);
	}
	
	public void createWindow(boolean outputGLVersion)
	{
		Window.createWindow(width, height, title);
		
		renderingEngine = new RenderingEngine();
		physicsEngine   = new PhysicsEngine();
		
		if(outputGLVersion)
			System.out.println(RenderingEngine.getOpenGLVersion());
	}
	
	public void start()
	{
		if(running)
			return;
		
		run();
	}
	
	public void stop()
	{
		if(!running)
			return;
		
		running = false;
	}
	
	public void run()
	{
		game.init();
		
		running = true;
		
		double lastTime     = Time.getTime();
		double startTime    = 0;
		double passedTime   = 0;
		double frameCounter = 0;
		
		double unprocessedTime = 0;
		
		int frames = 0;
		
		while(running)
		{
			boolean render = false;
			
			startTime  = Time.getTime();
			passedTime = startTime - lastTime;
			lastTime   = startTime;
			
			unprocessedTime += passedTime;
			frameCounter += passedTime;
			
			while(unprocessedTime >= frametime)
			{
				render = true;
				
				unprocessedTime -= frametime;
				
				Time.setDelta(frametime);
				
				game.input(frametime);
				Input.update();
				
				game.update(frametime);
				
				physicsEngine.simulate((float)frametime);
				physicsEngine.handleCollisions();
				physicsEngine.updateTransformsOfObjects();
				
				if(frameCounter >= 1)
				{
					System.out.println(frames + " frames");
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render)
			{
				renderingEngine.render(game.getRoot());
				Window.update();
				frames ++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			if(Window.isCloseRequested())
				stop();
		}
		
		cleanup();
	}
	
	private void cleanup()
	{
		Window.dispose();
	}
	
	public void setGame(Game game)
	{
		this.game = game;
		game.setEngine(this);
	}
}