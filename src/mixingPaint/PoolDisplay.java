package mixingPaint;

import java.util.ArrayList;

import backEnd.ColoredDirectedEdge;
import backEnd.Light;
import backEnd.Pool;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import generalFrontEnd.AwareRadio;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import generalFrontEnd.WeightedBlob;
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
	ArrayList<WeightedBlob> blobs;

	private int width, height;
	private AwareRadio[][] radios;
	private Pool pool;

	public PoolDisplay(Pool p, Iterable<WeightedBlob> blobs, int width, int height) {
		this.pool = p;
		this.width = width;
		this.height = height;
		this.blobs = (ArrayList<WeightedBlob>) blobs;

		setFormatting();
		drawPool();

		scene = new Scene(bp);
	}

	private void drawPool() {
		gc.setFill(Color.BLACK);
		int scale = 2;
		
		
		
		
		for(WeightedBlob b: blobs) {
			double ovalSize = .8* Math.min(FormatConstants.SCENE_WIDTH, FormatConstants.SCENE_HEIGHT) / (double) blobs.size(); // calculate oval size based on window size
	        double x = (gc.getCanvas().getWidth() / 4) + (b.getColumn() * ovalSize) + (ovalSize / 2); // calculate x coordinate with centered alignment
	        double y = (gc.getCanvas().getHeight() / 4) + (b.getRow() * ovalSize) + (ovalSize / 2); // calculate y coordinate with centered alignment

	        gc.fillOval(x, y, ovalSize, ovalSize); // draw the oval
		}
	}

	private void drawPath() {
		//Bag<Iterable<DirectedEdge>> lightPath = pool.refractPath(0);
		Light light = new Light(1, pool);
		int x1, y1, x2, y2;
		
		//gc.beginPath();
		//use strokeLine instead
		
		for (Iterable<ColoredDirectedEdge> bag : light.shine(0)) {
			for (ColoredDirectedEdge edge : bag) {
				x1 = blobs.get(edge.to()).getColumn();
				y1 = blobs.get(edge.to()).getRow();
				x2 = blobs.get(edge.from()).getColumn();
				y2 = blobs.get(edge.from()).getRow();
				
				gc.setStroke(new Color(edge.getR(), edge.getB(), edge.getG(), 1));
				
				gc.strokeLine(x1, y1, x2, y2); //doublex1 double y1 double x2 double y2 stroke a line using the current stroke paint.  
			}
			System.out.println();
		}
		
		
	}

	private void setFormatting() {
		bp = new BorderPane();
		bp.prefHeight(FormatConstants.SCENE_HEIGHT);
		bp.prefWidth(FormatConstants.SCENE_WIDTH);
		
		pane = new Pane();
		pane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");
		pane.setPrefWidth(FormatConstants.GP_WIDTH);
		pane.setPrefHeight(FormatConstants.GP_HEIGHT);
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
		label.setText("DISPLAY PAGE");
		label.setFont(new Font(18));

		btn = new ContinueButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Submit");

		bp.setTop(label);

		
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
