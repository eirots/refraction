package mixingPaint;

import generalFrontEnd.AwareRadio;
import generalFrontEnd.RefractionScene;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class WeightSelector implements RefractionScene{
	private Scene scene;
	private AwareRadio selRads[][];
	
	private BorderPane bp;
	private GridPane gp;
	private Label label;
	private int width, height;
	
	
	public WeightSelector(AwareRadio selRads[][]) {
		
		this.selRads = selRads;
		
		
		bp = new BorderPane();
		// debug line System.out.println("length of selRads is " + this.selRads.length);
		
		scene = new Scene(bp);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
