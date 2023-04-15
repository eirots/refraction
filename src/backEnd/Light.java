package backEnd;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Queue;

public class Light {
	private int lightIntensity;
	private Queue<Integer> colorQueue = new Queue<>();
	private Pool pool;
	
	public Light(int lightIntensity,Pool pool) {
		this.lightIntensity = lightIntensity;
		this.pool = pool;
	}
	
	public void shine(int sourceNode) {
		if(colorQueue.isEmpty()) { // Add the color White to the Queue
			colorQueue.enqueue(255);
			colorQueue.enqueue(255);
			colorQueue.enqueue(255);
		}
		int r = colorQueue.dequeue();
		int g = colorQueue.dequeue();
		int b = colorQueue.dequeue();
		for(Iterable<DirectedEdge> bag : pool.refractPath(2)) {
			for(DirectedEdge edge : bag ) {
				System.out.println(edge);
			}
			System.out.println();
		}
	}
	
	public void updateColorQueue(int red, int green, int blue) {
		colorQueue.enqueue(red);
		colorQueue.enqueue(green);
		colorQueue.enqueue(blue);
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
