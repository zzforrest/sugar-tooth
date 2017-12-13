/**
 * @author Blake Doeren
 * Nov 27, 2017
 */

package plannethopper;

import base.Matrix4f;
import base.Vector3f;
import base.core.Game;
import base.core.GameObject;
import base.core.Time;
import base.core.Transform;
import base.core.Window;
import base.core.component.CameraComponent;
import base.core.component.FreeLookComponent;
import base.core.component.MeshRendererComponent;
import base.core.component.PhysicsObjectComponent;
import base.core.component.SpinComponent;
import base.rendering.BaseLight;
import base.rendering.DirectionalLight;
import base.rendering.Material;
import base.rendering.Mesh;
import base.rendering.shader.BasicShader;
import base.resources.ResourceLoader;

public class TestGame extends Game
{
	private GameObject camera;
	private GameObject terrain;
	
	public void init()
	{
		camera = new GameObject();
		
		Matrix4f projection = new Matrix4f().toProjection(70.0f, Window.getAspectRatio(), 0.1f, 1000.0f);
		CameraComponent cc = new CameraComponent(projection);
		camera.addComponent(cc);
		
		FreeLookComponent fl = new FreeLookComponent();
		camera.addComponent(fl);
		
		getRoot().addChild(camera);
		
		
		
		terrain = new GameObject();
		terrain.transform.scale.set(10, 10, 10);
		terrain.transform.position.set(0, -10, 0);
		
		Mesh mesh = ResourceLoader.loadMesh("terrain1.obj");
		Material material = new Material(ResourceLoader.loadTexture("grass1.png"), new Vector3f(1, 1, 1));
		MeshRendererComponent mr = new MeshRendererComponent(mesh, material);
		terrain.addComponent(mr);
		
		getRoot().addChild(terrain);
		
		
		
		GameObject monkey0 = new GameObject();
		monkey0.transform.position.set(-2, 0, -2);
		
		monkey0.addComponent(new MeshRendererComponent(
				ResourceLoader.loadMesh("monkey1.obj"),
				new Material(ResourceLoader.loadTexture("grass1.png"), new Vector3f(1, 1, 1))
				));
		
		PhysicsObjectComponent poc0 = new PhysicsObjectComponent();
		poc0.physicsObject.velocity.set(0.25f, 0, 0.25f);
		monkey0.addComponent(poc0);
		
		getRoot().addChild(monkey0);
		
		
		
		GameObject monkey1 = new GameObject();
		monkey1.transform.position.set(2, 0, 2);
		
		monkey1.addComponent(new MeshRendererComponent(
				ResourceLoader.loadMesh("monkey1.obj"),
				new Material(ResourceLoader.loadTexture("grass1.png"), new Vector3f(1, 1, 1))
				));
		
		PhysicsObjectComponent poc1 = new PhysicsObjectComponent();
		poc1.physicsObject.velocity.set(-0.25f, 0, -0.25f);
		monkey1.addComponent(poc1);
		
		getRoot().addChild(monkey1);
	}
}
