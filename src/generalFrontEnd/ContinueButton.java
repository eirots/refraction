package generalFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Button class specific to this application that moves between scenes when clicked
 * @author astorie
 *
 */
public class ContinueButton extends Button{
	
	private int count = 0;
	
	/**
	 * Creates a ContinueButton for moving through the Refraction application
	 */
	public ContinueButton() {
		super();
		super.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			/**
			 * Moves the application forward when the button is clicked. 
			 */
			public void handle(ActionEvent e) {
				Films.swap(count);
				count++;
			}
			
		});
		count = 1;
	}
	
	

}
