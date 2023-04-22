package generalFrontEnd;

/**
 * WeightedBlobs ideally should be used with an array of AwareRadios. 
 * @author astorie
 *
 */
public class WeightedBlob {
	
	private int row, column, weight, index;
	private boolean selected;
	
	/**
	 * Creates a weighted "blob" that can be later turned into directed edges. Converted from AwareRadio. 
	 * @param radio AwareRadio must be passed  
	 * @param weight Weight of all edges coming from a node
	 * @see AwareRadio
	 */
	public WeightedBlob(AwareRadio radio, int weight, int index) {
		row = radio.getRow();
		column = radio.getColumn();
		selected = radio.isActive();	
		this.weight = weight;
		this.index = index;
	}
	
	public int getRow() {
		return row; 
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	@Override
	public String toString() {
		return "blob at row:" + row + "||  col: " + column + "|| weight: " + weight;
	}
}
