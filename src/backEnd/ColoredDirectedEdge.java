package backEnd;

import edu.princeton.cs.algs4.DirectedEdge;
/**
 * Functions as a normal DirectedEdge would However this contains variable fields for RGB color values
 * @author Spencer Peck
 *
 */
public class ColoredDirectedEdge extends DirectedEdge {
	private int r,g,b;
	public ColoredDirectedEdge(int v, int w, double weight, int r, int g, int b) {
		super(v, w, weight);
		this.r=r;
		this.g=g;
		this.b=b;
	}
	public int getR() {
		return r;
	}
	public int getG() {
		return g;
	}
	public int getB() {
		return b;
	}

}
