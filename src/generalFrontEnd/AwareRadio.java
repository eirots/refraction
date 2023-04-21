package generalFrontEnd;

import javafx.scene.control.RadioButton;

/**
 * Useful when making a grid of Radio buttons. 
 * @author astorie
 *
 */
public class AwareRadio extends RadioButton{
	int row, column;
	boolean active = false;
	
	public AwareRadio(int row, int column, boolean active) {
		this.row = row;
		this.column = column;
	}
	
	public void update(boolean update) {
		this.active = update;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;	
	}
	
	public String printableToString() {
		return "row: " + row + " " + " column: " + column + " " + active;
	}
}
