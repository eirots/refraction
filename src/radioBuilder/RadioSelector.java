package radioBuilder;

import generalFrontEnd.ContinueButton;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class RadioSelector {
	Scene scene;
	AnchorPane ap;
	Label label;
	ContinueButton btn;

	public RadioSelector(int width, int height) {
		this.ap = new AnchorPane();

		setFormatting();
		ap.getChildren().addAll(label, btn);
		this.scene = new Scene(ap);
	}

	/**
	 * Sets formatting for children
	 */
	private void setFormatting() {
		ap.prefHeight(500.0);
		ap.prefWidth(500.0);

		label = new Label();
		label.setLayoutX(15);
		label.setLayoutY(32);
		label.setText("Please select which locations you would like to add weights to.");
		label.setFont(new Font(18));

		

		btn = new ContinueButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Submit");
		//btn.setDisable(true);

		/*
		 * width = new ComboBox<>(options); width.setLayoutX(155);
		 * width.setLayoutY(282); width.setPromptText("Width");
		 * 
		 * 
		 * height = new ComboBox<>(options); height.setLayoutX(155);
		 * height.setLayoutY(310); height.setPromptText("Height");
		 * height.setOnAction((ActionEvent e)->{ btn.setLayoutX(208);
		 * btn.setLayoutY(393); btn.setText("Submit"); btn.setDisable(false); });
		 */
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
