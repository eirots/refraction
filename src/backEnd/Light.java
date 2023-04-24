package backEnd;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * Light mimics the behavior of light passing through a liquid. This light will be passed through a pool of liquid. 
 * its color output will change depending on the resistance needed to pass through the pool.
 * TODO Balance Weight/light intensity to create better color transitions
 * @author Spencer Peck
 *
 */
public class Light {
	private int lightIntensity;
	private Stack<Integer> colorQueue = new Stack<>();
	private Pool pool;
	
	/**
	 * Creates a Light with a specified pool and light intensity.
	 * @param lightIntensity. How strong the light is and how well it can overcome the pool's resistance.
	 * Lower values will lead to more drastic outputs
	 * @param The Pool the light will pass through.
	 */
	public Light(int lightIntensity,Pool pool) {
		this.lightIntensity = lightIntensity;
		this.pool = pool;
	}
	
	/**
	 * Tests the shortest paths through the Light's pool starting from a specified vertex.
	 * Outputs a queue of edges that make up the path and the color change through the pool.
	 * @param sourceNode. The starting Point of the light
	 * @return A Queue of edges that contain RGB values, that make up the shortest paths through the pool
	 */
	public Queue<Iterable<ColoredDirectedEdge>> shine(int sourceNode) {
		
		Queue<Iterable<DirectedEdge>> lightPaths = new Queue<>();
		int currentWeight =Integer.MAX_VALUE;
		int lowestWeight = Integer.MAX_VALUE;
		
		for(Iterable<DirectedEdge> bag : pool.refractPath(sourceNode)) {
			for(DirectedEdge edge : bag ) {
				currentWeight = (int) + edge.weight();
			}
			// DEBUG LINE System.out.println(currentWeight + "current weight ");
			if(currentWeight < lowestWeight) {
                while(!lightPaths.isEmpty()) {
                    lightPaths.dequeue();
                }
                lowestWeight = currentWeight;
                lightPaths.enqueue(bag);
            }
			
			if(currentWeight == lowestWeight) {
				lightPaths.enqueue(bag);				
			}
			 // DEBUG LINE System.out.println("Num Paths in Light Paths: " + lightPaths.size());
	            /*debug chunk
	             for(Iterable<DirectedEdge> tea : lightPaths){
	                System.out.println("Path Start");
	                for(DirectedEdge edge : tea) {
	                    System.out.println(edge);
	                }
	                System.out.println("Path End");
	            }
	             */
		}
		

		Queue<Iterable<ColoredDirectedEdge>> colorPaths = new Queue<>();
		while(!lightPaths.isEmpty()) {
			colorPaths.enqueue(colorPath(lightPaths.dequeue()));
		}
		colorQueue.pop();//Move on to the next RGB set
		colorQueue.pop();
		colorQueue.pop();
		return colorPaths;
	}
	
	/**
	 * Changes any edges it is provided with into edges with RGB values based off of the edge-
	 * weight and the color and intensity of the light
	 * @param Iterable of DirectedEdges with RGB
	 * @return Queue of DirectedEdges with RGB
	 */
	private Iterable<ColoredDirectedEdge> colorPath(Iterable<DirectedEdge> path) {
		Queue<ColoredDirectedEdge> coloredPath = new Queue<>();
		if(colorQueue.isEmpty()) { // Add the color White to the Queue
			colorQueue.push(255);
			colorQueue.push(255);
			colorQueue.push(255);
		}
		int r = colorQueue.peek();
		//DEBUG LINE System.out.println(colorQueue.peek());
		int g = colorQueue.peek();
		int b = colorQueue.peek();

		for(DirectedEdge edge : path ) {
			r = r-(int) ((edge.weight() * 4.2)-lightIntensity);
			g = g-(int) ((edge.weight() * 3.3)-lightIntensity);
			b = b-(int) ((edge.weight() * 2.4)-lightIntensity);
			
			coloredPath.enqueue(new ColoredDirectedEdge(edge.from(),edge.to(),edge.weight(),checkMinColor(r),checkMinColor(g),checkMinColor(b)));
		}
		return coloredPath;
		
		
	}
	
	private int checkMinColor(int c) {
		if(c < 0) {
			return 0;
		}
		else return c;
	}
	/**
	 * Add a new color to the stack
	 * @param red
	 * @param green
	 * @param blue
	 */
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
