package radioBuilder;

import generalFrontEnd.ContinueButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class RadioBuilderNew {

	Scene scene;
	AnchorPane ap;
	Label title, label;
	ContinueButton btn;
	TextField height, width;

	public RadioBuilderNew() {
		this.ap = new AnchorPane();
		setFormatting();
		ap.getChildren().addAll(label, title, btn, width, height);
		this.scene = new Scene(ap);
	}
	
	public Scene getScene() {
		return this.scene;
	}


	/**
	 * Sets the formatting for this view.
	 */
	private void setFormatting() {

		ap.prefHeight(500.0);
		ap.prefWidth(500.0);

		label = new Label();
		label.setLayoutX(89);
		label.setLayoutY(185);
		label.setText("Please enter your container size");
		label.setFont(new Font(24));

		title = new Label();
		title.setLayoutX(15);
		title.setLayoutY(32);
		title.setText("Welcome to Refraction!");
		title.setFont(new Font(48));

		btn = new ContinueButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Submit");

		width = new TextField();
		width.setAlignment(Pos.CENTER);
		width.setLayoutX(155);
		height.setLayoutY(282);
		width.setPromptText("Width");

		height = new TextField();
		height.setAlignment(Pos.CENTER);
		height.setLayoutX(155);
		height.setLayoutY(310);
		height.setPromptText("Height");

	}

}
