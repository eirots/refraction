package generalFrontEnd;

import javafx.scene.Scene;

public interface RefractionScene {

	@SuppressWarnings("unused") //warning supressed because this interface exists as a layout. 
								//classes that implement it have to have this, but no default behavior.
	private void setFormatting() {};
	
	public Scene getScene();
}
