package frontEnd;

import java.util.ArrayList;
import backEnd.ColoredDirectedEdge;
import backEnd.Light;
import backEnd.Pool;
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


/**
 * Displays the final scene of the application, showing which 
 * @author astorie
 *
 */
public class PoolDisplay implements RefractionScene {

	//UI elements
	private Scene scene;
	private BorderPane bp;
	private Pane pane;
	private Label label;
	private ContinueButton btn;
	private Canvas canvas;
	private GraphicsContext gc;
	
	//objects
	ArrayList<WeightedBlob> blobs;
	private Pool pool;

	//additional calculated fields
	private int width, height;
	private double ovalSize;
	

	public PoolDisplay(Pool p, Iterable<WeightedBlob> blobs, int width, int height) {
		this.pool = p;
		this.width = width;
		this.height = height;
		this.blobs = (ArrayList<WeightedBlob>) blobs;
		
		setOvalSize();
		setFormatting();
		drawPool();

		scene = new Scene(bp);
	}
	
	/**
	 * Calculates the size of nodes, a number that is useful later on when formatting. 
	 */
	private void setOvalSize(){
		ovalSize = .3* Math.min(FormatConstants.SCENE_WIDTH, FormatConstants.SCENE_HEIGHT) / (double) ((blobs.size() *.5)/pool.getNumOfColumns());
	}

	/**
	 * Draws the pool based on width and height carried throughout the application 
	 */
	private void drawPool() {
		gc.setFill(Color.BLACK);
		
		for(WeightedBlob b: blobs) {
	        double x = (pool.getRowCount() / gc.getCanvas().getWidth()) + (b.getColumn() * ovalSize) + (ovalSize / 2); 
	        double y = (pool.getRowCount() / gc.getCanvas().getHeight() ) + (b.getRow() * ovalSize) + (ovalSize / 2); 
	        
	        gc.fillOval(x, y, ovalSize, ovalSize);
		}
	}

	/**
	 * Draws the path found in Light
	 * @see Light
	 * @see Pool
	 */
	private void drawPath() {
	    Light light = new Light(1, pool);
	    double x1, y1, x2, y2;
	    
	    //DEBUG LINE  System.out.println(light.shine(0).size());
	    for (Iterable<ColoredDirectedEdge> bag : light.shine(0)) {
	        for (ColoredDirectedEdge edge : bag) {
	            //DEBUG LINE System.out.println(edge);
	            Color color = Color.rgb(edge.getR(), edge.getG(), edge.getB());
	            double lineThickness = 4; 

	            x1 = (pool.getRowCount()/ gc.getCanvas().getWidth()) + (blobs.get(edge.to()).getColumn() * ovalSize) + (ovalSize);
	            y1 = (pool.getRowCount()/ gc.getCanvas().getHeight())  + (blobs.get(edge.to()).getRow() * ovalSize) + (ovalSize);
	            x2 = (pool.getRowCount()/ gc.getCanvas().getWidth() ) + (blobs.get(edge.from()).getColumn() * ovalSize) + (ovalSize);
	            y2 = (pool.getRowCount()/ gc.getCanvas().getHeight() ) + (blobs.get(edge.from()).getRow() * ovalSize) + (ovalSize);
	            
	            //DEBUG LINE System.out.println("red:  " + edge.getR() + "  blue:  " + edge.getB() + "  green :  " + edge.getG());
	           
	            gc.setStroke(color);
	           
	            gc.setLineWidth(lineThickness);

	            gc.strokeLine(x1, y1, x2, y2);
	        }
	      //DEBUG LINE System.out.println();
	    }
	}



	/**
	 * Sets formatting for this classes' scene's children. 
	 */
	private void setFormatting() {
		bp = new BorderPane();
		bp.prefHeight(FormatConstants.SCENE_HEIGHT);
		bp.prefWidth(FormatConstants.SCENE_WIDTH);
		
		pane = new Pane();
		//pane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");
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
		label.setText("Lines are the path light took through your container");
		label.setFont(new Font(18));

		btn = new ContinueButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Again?");

		bp.setTop(label);
		bp.setBottom(btn);

		
		BorderPane.setAlignment(label, Pos.CENTER);

		BorderPane.setAlignment(btn, Pos.CENTER);
	
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
