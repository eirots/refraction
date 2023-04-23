package generalFrontEnd;

import edu.princeton.cs.algs4.Queue;
import javafx.scene.Scene;
import radioBuilder.RadioStart;
import testSandbox.JFXHelloWorld;

/**
 * Controller class, manipulating a queue of scenes for the application. This class is triggered by class ContinueButton 
 * @see ContinueButton
 * @author astorie
 *
 */
public class Film {
	
	private static Queue<Scene> sceneQueue = new Queue<>();
	
	public Film() {
		
	}
	
	public static void setRadioSelector() {
		
	}
	
	public static void swap() {
		if(sceneQueue.isEmpty()) {
			sceneQueue.enqueue(new RadioStart().getScene());
			JFXHelloWorld.window.setScene(sceneQueue.dequeue());
		}else {
			JFXHelloWorld.window.setScene(sceneQueue.dequeue());
		}
	}
	
	public static void next(Scene scene) {
		sceneQueue.enqueue(scene);
	}
}
