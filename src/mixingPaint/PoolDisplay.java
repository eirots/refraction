package mixingPaint;

import backEnd.Pool;
import generalFrontEnd.AwareRadio;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class PoolDisplay implements RefractionScene {

	private Scene scene;
	private BorderPane bp;
	private GridPane gp;
	private Label label;
	private ContinueButton btn;
	private int width, height;
	private AwareRadio[][] radios;
	private Pool p;

	
	
	public PoolDisplay(Pool p, int width, int height) {
		this.p = p;
		this.width = width;
		this.height = height;
		
		
		setFormatting();
	
	}
	private void setFormatting() {
		bp.prefHeight(FormatConstants.SCENE_HEIGHT);
		bp.prefWidth(FormatConstants.SCENE_WIDTH);
		gp.prefHeight(FormatConstants.GP_HEIGHT);
		gp.prefWidth(FormatConstants.GP_WIDTH);

		label = new Label();
		label.setLayoutX(15);
		label.setLayoutY(15);
		label.setText("BLOB PAGE");
		label.setFont(new Font(18));

		btn = new ContinueButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Submit");

		bp.setTop(label);
		bp.setCenter(gp);
		bp.setBottom(btn);
		BorderPane.setAlignment(label, Pos.CENTER);
		BorderPane.setAlignment(gp, Pos.CENTER);
		BorderPane.setAlignment(btn, Pos.CENTER);
		BorderPane.setMargin(gp, FormatConstants.BP_MARGINS);
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}

}
