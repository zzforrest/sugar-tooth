/**
 * @author Blake Doeren
 * Nov 4, 2017
 */

package base.gui;

public class GUIEvent
{
	public static int E_NONE  = 0;
	public static int E_HOVER = 1;
	public static int E_CLICK = 2;
	
	private GUIElement source;
	private int type;
	
	public GUIEvent(GUIElement source, int type)
	{
		this.source = source;
		this.type   = type;
	}
	
	public GUIElement getSource()
	{
		return source;
	}
	
	public int getType()
	{
		return type;
	}
}
