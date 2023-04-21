package generalFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Button class specific to this application that moves between scenes when
 * clicked
 * 
 * @author astorie
 *
 */
public class ContinueButton extends Button {

	/**
	 * Creates a ContinueButton for moving through the Refraction application
	 */
	public ContinueButton() {
		super();
		super.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			/**
			 * Moves the application forward by dequeuing when the button is clicked.
			 */
			public void handle(ActionEvent e) {
				Film.swap();
			}

		});

	}

}
