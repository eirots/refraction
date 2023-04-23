package mixingPaint;

import backEnd.Pool;
import generalFrontEnd.AwareRadio;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;

public class PoolDisplay implements RefractionScene {

	private Scene scene;
	private BorderPane bp;
	private Pane pane;

	// private GridPane gp;
	private Label label;
	private ContinueButton btn;
	private Canvas canvas;
	private GraphicsContext gc;

	private int width, height;
	private AwareRadio[][] radios;
	private Pool pool;

	public PoolDisplay(Pool p, int width, int height) {
		this.pool = p;
		this.width = width;
		this.height = height;

		setFormatting();
		drawPool();

		scene = new Scene(bp);
	}

	private void drawPool() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width * 100, height * 100);
		System.out.println("drawing the rectangle");
		// gc.beginPath();// use pathto and moveto to draw the path
	}

	private void drawPath() {

	}

	private void setFormatting() {
		bp = new BorderPane();
		bp.prefHeight(FormatConstants.SCENE_HEIGHT);
		bp.prefWidth(FormatConstants.SCENE_WIDTH);
		
		 pane = new Pane();
		bp.setCenter(pane);

		canvas = new Canvas();
		pane.getChildren().add(canvas);
		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		canvas.widthProperty().addListener(event -> {
			drawPool();
			drawPath();
		});
		canvas.heightProperty().addListener(event -> {
			drawPool();
			drawPath();
		});

		

		canvas.prefHeight(FormatConstants.GP_HEIGHT);
		canvas.prefWidth(FormatConstants.GP_WIDTH);
		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.BLACK);
		gc.fillRect(height, height, width, height);

		label = new Label();
		label.setLayoutX(15);
		label.setLayoutY(15);
		label.setText("BLOB PAGE");
		label.setFont(new Font(18));

		btn = new ContinueButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Submit");

		// bp.setTop(label);

		
		// bp.setCenter(canvas);
		// bp.setBottom(btn);
		// bp.setBorder(new Border(new BorderStroke(Paint.valueOf("BLACK");
		BorderPane.setAlignment(label, Pos.CENTER);
		// BorderPane.setAlignment(gp, Pos.CENTER);
		BorderPane.setAlignment(btn, Pos.CENTER);
		// BorderPane.setMargin(gp, FormatConstants.BP_MARGINS);
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
