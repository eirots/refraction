package mixingPaint;

import java.util.ArrayList;
import java.util.Arrays;

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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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
	private ArrayList<WeightedBlob> blobs;
	private WeightedButton btn;
	private BorderPane bp;
	private GridPane gp;
	private Label label;
	private int width, height;
	private ArrayList<ComboBox<Integer>> cbList;
	

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
		//Label garbLabel;
		ComboBox<Integer> cb;
		cbList = new ArrayList<ComboBox<Integer>>();
		
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				cb = new ComboBox<Integer>(weightOptions);
				if (radios[row][col].isActive()) {
					cb.setDisable(false);
				}else {
					cb.setDisable(true);
					cb.setValue(5);
					
				}
				if(cbList.add(cb)) {
					System.out.println("added " + " to "+ cbList.size());
				}
				gp.add(cb, row, col);
				GridPane.setHgrow(cb, Priority.ALWAYS);
				GridPane.setVgrow(cb, Priority.ALWAYS);
			}
		}
		
		
	}
	
	private ArrayList<WeightedBlob> getBlobList(){
		if(blobs == null) {
			blobs = new ArrayList<>();
		}
		return blobs;
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
		BorderPane.setMargin(gp, FormatConstants.BP_MARGINS);

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
					ArrayList<WeightedBlob> blobs = getBlobList();
					AwareRadio flatRadios[] = new AwareRadio	[height * width];
					
					//flatten this to make it easier to work with in next part 
					int index = 0;
					for(int i = 0; i<width;i++) {
						for(int j = 0; j<height; j++) {
						 flatRadios[index] = radios[i][j];
						 System.out.println(flatRadios[index].printableToString());
						 index++;
						}
					}
					
					for(int i = 0; i<flatRadios.length; i++) {
						ComboBox<Integer> cb = cbList.get(i);
						blobs.add(new WeightedBlob(flatRadios[i], cb.getValue(), cbList.indexOf(cb)));
						
						//System.out.println(blobs.get(i));
					}
					
					System.out.println(blobs);
					
					
					//for (int row = 0; row < width; row++) {
					//	for (int col = 0; col < height; col++) {
							/**
							 * //flattening 2d array to be parsed by 
							
							ComboBox <Integer> cb = cbList.get((row * width) + row);
							
							//flattening array a different way
							
							
							System.out.println(height);
							System.out.println("adding from index " + ((row * width) + col));
							 
							
							
							
							
							//add blob index 
							//
							
							
							/*
							 * if(radios[row][col].isSelected()) { radios[row][col].setActive();
							 * System.out.println(radios[row][col].printableToString());
							 * 
							 * }
							 */
							 
						//}
					//}
					// Film.next(new WeightSelector(radios).getScene());
					//devline below
					for(WeightedBlob blob: blobs) {
						System.out.println(blob.toString());
					}
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
