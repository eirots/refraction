package testSandbox;

import backEnd.ColoredDirectedEdge;
import backEnd.Light;
import backEnd.Pool;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import generalFrontEnd.Film;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mixingPaint.RadioStart;

public class JFXHelloWorld extends Application {
	private double stageX = 500;private double stageY = 500;
	public static Stage window;

	public Scene scenes[] = { new RadioStart().getScene() };

	public static void main(String[] args) {
		Pool pool = new Pool(new In("src/backEnd/resources/gridGraph.txt"), 100);
		Light light = new Light(1, pool);
		for (Iterable<ColoredDirectedEdge> bag : light.shine(2)) {
			for (ColoredDirectedEdge edge : bag) {
				System.out.println(edge);
			}
			System.out.println();
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;

		// TODO these must be taken out later, makes it easier for me to test
		window.setX(3200.0); window.setY(300.0);

		window.setHeight(stageX); window.setWidth(stageY);

		Film.next(new RadioStart().getScene());
		Film.swap();

		window.show();
	}

}
