/**
 * @author Blake Doeren
 * Dec 9, 2017
 */

package base.physics;

public class Intersection
{
	public final boolean intersecting;
	public final float   distance;
	
	public Intersection(float distance)
	{
		this.distance = distance;
		
		intersecting = distance < 0;
	}
}
