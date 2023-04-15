package testSandbox;

import backEnd.Pool;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JFXHelloWorld extends Application {

	public static void main(String[] args) {
		Pool pool = new Pool(new In("src/backEnd/resources/gridGraph.txt"));
		for(Iterable<DirectedEdge> bag : pool.refractPath(2)) {
			for(DirectedEdge edge : bag ) {
				System.out.println(edge);
			}
			System.out.println();
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello world");
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello world");
			}
		});

		StackPane root = new StackPane();

		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

}
