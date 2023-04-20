package testSandbox;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXExperimenting extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		
		Scene scene = new Scene(group, 600, 300);
		
		scene.setFill(Color.BROWN);
		
		primaryStage.setTitle("Sample application");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	

}
