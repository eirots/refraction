package frontEnd;

import generalFrontEnd.Film;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Starts the application with mandatory arguments
 * @author astorie
 *
 */
public class StartApplication extends Application {
	private double stageX = 700;private double stageY = 700;
	public static Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setHeight(stageX); window.setWidth(stageY);
		
		Film.next(new RadioStart().getScene());
		Film.swap();
		window.show();
	}
}

