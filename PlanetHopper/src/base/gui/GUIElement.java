/**
 * @author Blake Doeren
 * Nov 4, 2017
 */

package base.gui;

public class GUIElement
{
	private GUIEventHandler handler;
	
	public GUIElement()
	{
		
	}
	
	public void setEventHandler(GUIEventHandler e)
	{
		handler = e;
	}
	
	public void createEvent(int type)
	{
		if(handler != null)
			handler.handleGUIEvent(new GUIEvent(this, type));
	}
}
