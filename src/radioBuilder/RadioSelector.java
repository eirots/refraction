package radioBuilder;

import generalFrontEnd.AwareRadio;
import generalFrontEnd.ContinueButton;
import generalFrontEnd.Films;
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

public class RadioSelector implements RefractionScene {
	Scene scene;
	BorderPane bp;
	GridPane gp;
	Label label;
	RadPageButton btn;
	int width, height;
	AwareRadio[][] radios;
	

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
								radios[row][col].update(true);
								System.out.println(radios[row][col]);
							}
						}
						
					}
					Films.swap();
				}
			});
		}
		
	}

	/**
	 * Makes radios from previously selected width and height
	 */
	private void makeRadios() {
		int layoutX = 10;
		int layoutY = 40;
		
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				radios[row][col] = new AwareRadio(row, col, false);
				radios[row][col].setLayoutX(layoutX + (row * 30));
				radios[row][col].setLayoutY(layoutY + (col * 30));
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

		bp.prefHeight(500.0);
		bp.prefWidth(500.0);
		gp.prefHeight(250);
		gp.prefWidth(250);

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
		BorderPane.setMargin(gp, new Insets(50, 50, 50, 50));
		
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
