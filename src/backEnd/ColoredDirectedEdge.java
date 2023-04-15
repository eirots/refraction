package backEnd;

import edu.princeton.cs.algs4.DirectedEdge;

public class ColoredDirectedEdge extends DirectedEdge {
	int r,g,b;
	public ColoredDirectedEdge(int v, int w, double weight, int r, int g, int b) {
		super(v, w, weight);
		this.r=r;
		this.g=g;
		this.b=b;
	}

}
