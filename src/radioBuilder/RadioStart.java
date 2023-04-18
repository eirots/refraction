package radioBuilder;

import generalFrontEnd.ContinueButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class RadioStart {

	private Scene scene;
	private Pane pn;
	private ContinueButton btn = new ContinueButton(); //TODO change to a larger 'continue' button class
	private Text txt = new Text("How large would you like your glass to be?");
	
	public RadioStart() {
		this.pn = new Pane();
		setFormatting();
		
		pn.getChildren().addAll(txt, btn);
		this.scene = new Scene(pn);

		
	}
	
	private void setFormatting() {
		btn.setText("Test button");
		btn.setLayoutX(200); btn.setLayoutY(400);
		
		txt.setLayoutX(200); txt.setLayoutY(300);
		txt.setTextAlignment(TextAlignment.CENTER);
	}

	public Scene getScene() {
		return scene;
	}

}
