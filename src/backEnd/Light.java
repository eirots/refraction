package backEnd;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Light {
	private int lightIntensity;
	private Stack<Integer> colorQueue = new Stack<>();
	private Pool pool;
	
	public Light(int lightIntensity,Pool pool) {
		this.lightIntensity = lightIntensity;
		this.pool = pool;
	}
	
	public Queue<Iterable<ColoredDirectedEdge>> shine(int sourceNode) {
		
		Queue<Iterable<DirectedEdge>> lightPaths = new Queue<>();
		int currentWeight =Integer.MAX_VALUE;
		int lowestWeight = Integer.MAX_VALUE;
		
		for(Iterable<DirectedEdge> bag : pool.refractPath(sourceNode)) {
			for(DirectedEdge edge : bag ) {
				currentWeight = (int) + edge.weight();
			}
			
			if(currentWeight < lowestWeight) {
				while(!lightPaths.isEmpty()) {
					lightPaths.dequeue();
				}
				lightPaths.enqueue(bag);
			}
			if(currentWeight == lowestWeight) {
				lightPaths.enqueue(bag);				
			}
		}

		Queue<Iterable<ColoredDirectedEdge>> colorPaths = new Queue<>();
		while(!lightPaths.isEmpty()) {
			colorPaths.enqueue(colorPath(lightPaths.dequeue()));
		}
		return colorPaths;
	}
	
	private Iterable<ColoredDirectedEdge> colorPath(Iterable<DirectedEdge> path) {
		Queue<ColoredDirectedEdge> coloredPath = new Queue<>();
		if(colorQueue.isEmpty()) { // Add the color White to the Queue
			colorQueue.push(255);
			colorQueue.push(255);
			colorQueue.push(255);
		}
		int r = colorQueue.peek();
		int g = colorQueue.peek();
		int b = colorQueue.peek();

		for(DirectedEdge edge : path ) {
			r =-(int) (edge.weight()/lightIntensity);
			r =-(int) ((edge.weight()/lightIntensity) * 1.2);
			r =-(int) ((int) (edge.weight()/lightIntensity) * 1.1);
			coloredPath.enqueue(new ColoredDirectedEdge(edge.from(),edge.to(),edge.weight(),r,g,b));
		}
		return coloredPath;
		
		
	}
	
	public void updateColorQueue(int red, int green, int blue) {
		colorQueue.push(red);
		colorQueue.push(green);
		colorQueue.push(blue);
	}

	public int getLightIntensity() {
		return lightIntensity;
	}

	public void setLightIntensity(int lightIntensity) {
		this.lightIntensity = lightIntensity;
	}

	public Pool getPool() {
		return pool;
	}
	
	
}
