package frontEnd;

import generalFrontEnd.ContinueButton;
import generalFrontEnd.Film;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * Creates a Scene Graph for the first page in the application.
 * 
 * @author astorie
 *
 */
public class RadioStart implements RefractionScene {
	Scene scene;
	AnchorPane ap;
	Label title, label;
	ContinueButton btn;
	ComboBox<Integer> height, width;
	ObservableList<Integer> heightOptions = FXCollections.observableArrayList(3, 4, 5, 6, 7, 8, 9, 10);
	ObservableList<Integer> widthOptions = FXCollections.observableArrayList(3, 4, 5, 6, 7, 8, 9, 10);

	/**
	 * Constructor for class RadioStart. Creates the first page of the application, showing comboboxes for width and height selection. 
	 */
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
		ap.prefHeight(FormatConstants.SCENE_HEIGHT);
		ap.prefWidth(FormatConstants.SCENE_WIDTH);

		label = new Label();
		label.setLayoutX(89);
		label.setLayoutY(185);
		label.setText("Please select your container size");
		label.setFont(new Font(24));

		title = new Label();
		title.setLayoutX(15);
		title.setLayoutY(32);
		title.setText("Welcome to Refraction!");
		title.setFont(new Font(48));

		btn = new ContinueButton();
		btn.setLayoutX(180);
		btn.setLayoutY(393);
		btn.setText("Please select sizes first!");
		
		btn.setDisable(true);
	
		width = new ComboBox<>(widthOptions);
		width.setLayoutX(155);
		width.setLayoutY(282);
		width.setPromptText("Width");
		width.setOnAction((ActionEvent e) -> moveToRadioSelector());
		width.setPromptText("width");

		height = new ComboBox<>(heightOptions);
		height.setLayoutX(155);
		height.setLayoutY(310);
		height.setPromptText("Height");
		height.setOnAction((ActionEvent e) -> moveToRadioSelector());
		height.setPromptText("height");
	}

	/**
	 * Private method to move to radio selector. Called when button is clicked
	 */
	private void moveToRadioSelector() {
		if (height.getValue() != null && width.getValue() != null) {

			// delay is needed in case both width and height try to grab at the same time,
			// this can cause an exception to fire
			btn.setLayoutX(FormatConstants.BTN_X);
			btn.setLayoutY(FormatConstants.BTN_Y);
			btn.setText("Submit");
			btn.setDisable(false);
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Film.next(new RadioSelector(width.getValue(), height.getValue()).getScene());
					Film.swap();
				}
			});
		}
	}

	/**
	 * Creates a Scene Graph that asks the user what size they would like their
	 * container to be
	 * 
	 * @return Returns the first page of the application
	 */
	public Scene getScene() {
		return scene;
	}

}
