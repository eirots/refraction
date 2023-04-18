package generalFrontEnd;

import javafx.scene.control.Button;

public class ContinueButton extends Button{
	
	private int count;
	
	public ContinueButton() {
		super();
		count = 1;
	}
	
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
