package frontEnd;

import java.util.ArrayList;

import backEnd.Pool;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.Film;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import generalFrontEnd.WeightedBlob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class BigNumberSelection implements RefractionScene {

	Scene scene;
	AnchorPane ap;
	Label title, label;
	ContinueButton btn;
	ComboBox<Integer> height, width;

	ObservableList<Integer> bigWidthOptions = FXCollections.observableArrayList(50, 75, 100, 125, 150, 175, 200);
	ObservableList<Integer> bigHeightOptions = FXCollections.observableArrayList(50, 75, 100, 125, 150, 175, 200);
	public BigNumberSelection() {
		ap = new AnchorPane();

		setFormatting();
		ap.getChildren().addAll(label, title, btn, width, height);
		this.scene = new Scene(ap);
	}

	private void setFormatting() {
		ap.prefHeight(FormatConstants.SCENE_HEIGHT);
		ap.prefWidth(FormatConstants.SCENE_WIDTH);

		label = new Label();
		label.setLayoutX(80);
		label.setLayoutY(185);
		label.setText("Please select your larger size container");
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

		width = new ComboBox<>(bigWidthOptions);
		width.setLayoutX(155);
		width.setLayoutY(282);
		width.setPromptText("Big Width");
		width.setOnAction((ActionEvent e) -> moveToPoolView());
		width.setPromptText("width");

		height = new ComboBox<>(bigHeightOptions);
		height.setLayoutX(155);
		height.setLayoutY(310);
		height.setPromptText("Big Height");
		height.setOnAction((ActionEvent e) -> moveToPoolView());
		height.setPromptText("height");
	}

	private void moveToPoolView() {
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
					int heightValue = height.getValue();
					int widthValue = width.getValue();
					ArrayList<WeightedBlob> blobList = WeightedBlob.randomWeights(heightValue, widthValue);
					Queue<WeightedBlob> blobQ = WeightedBlob.blobQueue(blobList);
					
					Film.next(
							new PoolDisplay(new Pool(widthValue, heightValue, blobQ), blobList, heightValue, widthValue, (heightValue/2))
									.getScene());
					Film.swap();
				}
			});
		}
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
