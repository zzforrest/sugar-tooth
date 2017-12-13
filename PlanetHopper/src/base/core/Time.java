/**
 * @author Blake Doeren
 * Oct 19, 2017
 */

package base.core;

public class Time
{
	/**Length of a second in nanoseconds, 1 billion*/
	public static final double SECOND = 1000000000;
	
	private static double delta = 0;
	
	public static double getTime()
	{
		return System.nanoTime() / SECOND;
	}
	
	public static double getDelta()
	{
		return delta;
	}
	
	public static void setDelta(double delta)
	{
		Time.delta = delta;
	}
}