package radioBuilder;

import generalFrontEnd.ContinueButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Creates a Scene Graph for the first page in the application. 
 * @author astorie
 *
 */
public class RadioStart {
	Scene scene;
	AnchorPane ap;
	Label title, label;
	ContinueButton btn;
	TextField height, width;

	
	public RadioStart() {
		this.ap = new AnchorPane();
		setFormatting();
		ap.getChildren().addAll(label, title, btn, width, height);
		this.scene = new Scene(ap);
	}

	/**
	 * Sets formatting for children
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
		width.setLayoutY(282);
		width.setPromptText("Width");

		height = new TextField();
		height.setAlignment(Pos.CENTER);
		height.setLayoutX(155);
		height.setLayoutY(310);
		height.setPromptText("Height");
	}
	
	/**
	 * Creates a Scene Graph that asks the user what size they would like their container to be
	 * @return Returns the first page of the application
	 */
	public Scene getScene() {
		return scene;
	}

}
