package generalFrontEnd;

import javafx.scene.control.RadioButton;

/**
 * Useful when making a grid of Radio buttons. 
 * @author astorie
 *
 */
public class AwareRadio extends RadioButton{
	int x, y;
	boolean active = false;
	
	public AwareRadio(int row, int column, boolean active) {
		this.x = row;
		this.y = column;
	}
	
	public void update(boolean update) {
		this.active = update;
	}
	
	public int getRow() {
		return this.x;
	}
	
	public int getColumn() {
		return this.y;	
	}
}
