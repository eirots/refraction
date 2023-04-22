package generalFrontEnd;

import javafx.scene.control.RadioButton;

/**
 * Class that is useful when making a grid of Radio buttons without grouping. Used in tandem with WeightedBlob
 * 
 * @see WeightedBlob
 * @author astorie
 *
 */
public class AwareRadio extends RadioButton{
	private int row, column;
	private boolean active;
	
	/**
	 * 
	 * @param row X position in grid 
	 * @param column Y position in grid
	 * @param active 
	 */
	public AwareRadio(int row, int column) {
		this.row = row;
		this.column = column;
		active = false;
	}
	
	public void setActive() {
		this.active = true;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;	
	}
	
	public boolean isActive() {
		return active;
	}
	
	public String printableToString() {
		return "row: " + row + " " + " column: " + column + " " + active + "\n";
	}
}
