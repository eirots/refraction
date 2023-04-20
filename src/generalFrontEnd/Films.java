package generalFrontEnd;

import javafx.scene.Scene;
import radioBuilder.RadioStart;
import testSandbox.JFXHelloWorld;

public class Films {
	private static Scene scenes[] = {new RadioStart().getScene(), new RadioStart().getScene()};
	
	public Films() {
		
	}
	
	public static void swap(int swapTo) {
		JFXHelloWorld.window.setScene(scenes[swapTo]);
	}
}
