package generalFrontEnd;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

/**
 * WeightedBlobs ideally should be used with an array of AwareRadios.
 * 
 * @author astorie
 *
 */
public class WeightedBlob {

	private int row, column, weight, index;
	private boolean selected;

	/**
	 * Creates a weighted "blob" that can be later turned into directed edges.
	 * Converted from AwareRadio.
	 * 
	 * @param radio  AwareRadio must be passed
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

	/**
	 * Lightweight constructor that foregoes AwareRadios. Allows for drawing much
	 * larger graphs.
	 * 
	 * @param row    row this should be painted in
	 * @param column column to be painted in
	 * @param weight weight to apply to node
	 * @param index  1D array index
	 */
	private WeightedBlob(int row, int column, int weight, int index) {
		selected = false;
		this.row = row;
		this.column = column;
		this.weight = weight;
		this.index = index;
	}

	/**
	 * Gives a queue of blobs, used when making a pool.
	 * 
	 * @param arr Array of blobs to be put into a queue
	 * @return Queue (fifo) of blobs
	 */
	public static Queue<WeightedBlob> blobQueue(ArrayList<WeightedBlob> blobList) {
		Queue<WeightedBlob> q = new Queue<>();

		for (WeightedBlob small : blobList) {
			q.enqueue(small);
		}
		return q;
	}

	/**
	 * Creates an array of WeightedBlobs with random weights.
	 * 
	 * @param rows    number of rows
	 * @param columns number of columns
	 * @return Array of WeightedBlobs with random weights [1,20]
	 */
	public static ArrayList<WeightedBlob> randomWeights(int rows, int columns) {
		ArrayList<WeightedBlob> returnList = new ArrayList<>();

		int in = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				returnList.add(new WeightedBlob(i, j, StdRandom.uniformInt(8, 200), in));
			}
		}
		return returnList;
	}

	public int getIndex() {
		return index;
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
