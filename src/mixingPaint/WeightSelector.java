package mixingPaint;

import generalFrontEnd.AwareRadio;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.Film;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import generalFrontEnd.WeightedBlob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * Creates weight selection page of application.
 * 
 * @author astorie
 *
 */
public class WeightSelector implements RefractionScene {
	private Scene scene;
	private AwareRadio radios[][];
	private WeightedBlob blobs[][];
	private WeightedButton btn;
	private BorderPane bp;
	private GridPane gp;
	private Label label;
	private int width, height;
	

	/**
	 * Creates a weight selection Scene
	 * 
	 * @param selRads
	 */
	public WeightSelector(AwareRadio radios[][], int width, int height) {
		gp = new GridPane();
		bp = new BorderPane();
		this.width = width;
		this.height = height;
		this.radios = radios;
		makeDropDowns();
		setFormatting();

		// debug line System.out.println("length of selRads is " + this.selRads.length);

		scene = new Scene(bp);
	}

	private void makeDropDowns() {
		ObservableList<Integer> weightOptions = FXCollections.observableArrayList(
				2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
		
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				if (radios[row][col].isActive()) {
					
					

				}
			}
		}
		

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

		btn = new WeightedButton();
		btn.setLayoutX(208);
		btn.setLayoutY(393);
		btn.setText("Submit");

		bp.setTop(label);
		bp.setCenter(gp);
		bp.setBottom(btn);
		BorderPane.setAlignment(label, Pos.CENTER);
		BorderPane.setAlignment(gp, Pos.CENTER);
		BorderPane.setAlignment(btn, Pos.CENTER);
		BorderPane.setMargin(gp, new Insets(50, 50, 50, 50));

		// debug line gp.setBorder(new Border(new BorderStroke(Paint.valueOf("BLACK"),
		// BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	/**
	 * Expanding functionality for ContinueButton, this extra functionality is only
	 * needed in this class. In addition to moving the application forward, also
	 * grabs all weight information and creates weightedblobs.
	 * 
	 * @author astorie
	 * 
	 * @see ContinueButton
	 * @see WeightedBlob
	 */
	private class WeightedButton extends ContinueButton {

		public WeightedButton() {
			super();

			super.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					for (int row = 0; row < width; row++) {
						for (int col = 0; col < height; col++) {
							/*
							 * if(radios[row][col].isSelected()) { radios[row][col].setActive();
							 * System.out.println(radios[row][col].printableToString());
							 * 
							 * }
							 */
						}
					}
					// Film.next(new WeightSelector(radios).getScene());
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