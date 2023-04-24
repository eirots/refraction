package generalFrontEnd;

import edu.princeton.cs.algs4.Queue;
import frontEnd.RadioStart;
import javafx.scene.Scene;
import testSandbox.JFXHelloWorld;

/**
 * Controller class, manipulating a queue of scenes for the application. This
 * class is triggered by class ContinueButton
 * 
 * @see ContinueButton
 * @author astorie
 *
 */
public class Film {

	private static Queue<Scene> sceneQueue = new Queue<>();

	public Film() {};
	
	/**
	 * Changes the main scene in the application to the least recently added scene. 
	 */
	public static void swap() {
		if (sceneQueue.isEmpty()) {
			sceneQueue.enqueue(new RadioStart().getScene());
			JFXHelloWorld.window.setScene(sceneQueue.dequeue());
		} else {
			JFXHelloWorld.window.setScene(sceneQueue.dequeue());
		}
	}

	/**
	 * Adds a scene to the end of a Queue of scenes to be displayed. 
	 * @param scene Scene to be displayed later in the application
	 */
	public static void next(Scene scene) {
		sceneQueue.enqueue(scene);
	}
}
