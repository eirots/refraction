package radioBuilder;

import generalFrontEnd.AwareRadio;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.Film;
import generalFrontEnd.FormatConstants;
import generalFrontEnd.RefractionScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import mixingPaint.WeightSelector;

public class RadioSelector implements RefractionScene {
	private Scene scene;
	private BorderPane bp;
	private GridPane gp;
	private Label label;
	private RadPageButton btn;
	private int width, height;
	private AwareRadio[][] radios;
	

	public RadioSelector(int width, int height) {
		this.gp = new GridPane();   
		this.bp = new BorderPane();
		this.width = width;
		this.height = height;
		radios = new AwareRadio[width][height];
		makeRadios();

		setFormatting();

		this.scene = new Scene(bp);
	}
	
	private class RadPageButton extends ContinueButton {
		
		public RadPageButton() {
			super();
			
			super.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					for (int row = 0; row < width; row++) {
						for (int col = 0; col < height; col++) {
							if(radios[row][col].isSelected()) {
								radios[row][col].setActive();
								System.out.println(radios[row][col].printableToString());
								
							}
						}
					}
					Film.next(new WeightSelector(radios, width, height).getScene());
					Film.swap();
				}
			});
		}
		
	}

	/**
	 * Makes radios from previously selected width and height
	 */
	private void makeRadios() {	
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				radios[row][col] = new AwareRadio(row, col);
				radios[row][col].setLayoutX(FormatConstants.GRID_X + (row * 30));
				radios[row][col].setLayoutY(FormatConstants.GRID_Y + (col * 30));
				radios[row][col].setAlignment(Pos.CENTER);
				gp.add(radios[row][col], row, col);
				GridPane.setHgrow(radios[row][col], Priority.ALWAYS);
				GridPane.setVgrow(radios[row][col], Priority.ALWAYS);
			}
			
		}

	}

	/**
	 * Sets formatting for children
	 */
	private void setFormatting() {
		bp.prefHeight(FormatConstants.SCENE_HEIGHT);
		bp.prefWidth(FormatConstants.SCENE_WIDTH);
		gp.prefHeight(FormatConstants.GP_HEIGHT);
		gp.prefWidth(FormatConstants.GP_WIDTH);

		label = new Label();
		label.setLayoutX(15);
		label.setLayoutY(15);
		label.setText("Please select which locations you would like to add weights to.");
		label.setFont(new Font(18));

		btn = new RadPageButton();
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
		
		// debug line gp.setBorder(new Border(new BorderStroke(Paint.valueOf("BLACK"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	/**
	 * Creates a Scene Graph that asks the user what size they would like their
	 * container to be
	 * 
	 * @return Returns the second page of the application, asking user to select
	 *         radios.
	 */
	public Scene getScene() {
		return scene;
	}

}
