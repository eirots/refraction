package radioBuilder;

import generalFrontEnd.ContinueButton;
import generalFrontEnd.Films;
import generalFrontEnd.RefractionScene;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class RadioStart implements RefractionScene{
	Scene scene;
	AnchorPane ap;
	Label title, label;
	ContinueButton btn;
	ComboBox<Integer> height, width;
	ObservableList<Integer> heightOptions = FXCollections.observableArrayList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	ObservableList<Integer> widthOptions = FXCollections.observableArrayList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	
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
		label.setLayoutX(89); label.setLayoutY(185);
		label.setText("Please select your container size");
		label.setFont(new Font(24));

		title = new Label();
		title.setLayoutX(15); title.setLayoutY(32);
		title.setText("Welcome to Refraction!");
		title.setFont(new Font(48));

		btn = new ContinueButton();
		btn.setLayoutX(180); btn.setLayoutY(393);
		btn.setText("Please select sizes first!");
		//TODO remove below 
		/*
		 * btn.setOnAction((ActionEvent e)->{
			if(height.getValue()!= null && width.getValue()!= null) {
				Films.next(new RadioSelector(width.getValue(), height.getValue()).getScene());
			}
			Films.swap();
		});
		 */
		//TODO uncomment this line when ready to go 
		btn.setDisable(true);

		width = new ComboBox<>(widthOptions);
		width.setLayoutX(155); width.setLayoutY(282);
		width.setPromptText("Width");
		width.setOnAction((ActionEvent e) -> {
			if(height.getValue()!= null && width.getValue() != null) {
				
				int wvalue = width.getValue();
				int hvalue = height.getValue();
				Platform.runLater(()->Films.next(new RadioSelector(wvalue, hvalue).getScene()));
				
				
				btn.setLayoutX(208);
				btn.setLayoutY(393);
				btn.setText("Submit");
				btn.setDisable(false);
				
			}
			
		});
		//width.getSelectionModel().select(5); //TODO remove this line
		
		height = new ComboBox<>(heightOptions);
		height.setLayoutX(155); height.setLayoutY(310);
		height.setPromptText("Height");
		//height.getSelectionModel().select(5); //TODO remove this line
		height.setOnAction((ActionEvent e) -> {
			if(height.getValue()!= null && width.getValue() != null) {
				
				int wvalue = width.getValue();
				int hvalue = height.getValue();
				Platform.runLater(()->Films.next(new RadioSelector(wvalue, hvalue).getScene()));
				
				
				btn.setLayoutX(208);
				btn.setLayoutY(393);
				btn.setText("Submit");
				btn.setDisable(false);
				
			}
			
		});
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
