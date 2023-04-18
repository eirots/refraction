package generalFrontEnd;

import javafx.scene.control.Button;

/**
 * Button class specific to this application that moves between scenes when clicked
 * @author astorie
 *
 */
public class ContinueButton extends Button{
	
	private int count;
	
	/**
	 * Creates a ContinueButton for moving through the Refraction application
	 */
	public ContinueButton() {
		super();
		count = 1;
	}
	
	/**
	 * Moves the application forward when buttons are clicked
	 */
	public void click() {
		count++;
		
		switch(count) {
		case 2: //TODO this fires when RadioStart button is clicked
			break;
		case 3: //TODO 
			break;
		}
	}

}
