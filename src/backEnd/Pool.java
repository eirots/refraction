package backEnd;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import generalFrontEnd.WeightedBlob;
/**
 * The pool class provides functionality to build edge weighted graphs in a format similar to a grid. 
 * It provides functionality to measure the shortest paths from one side of the graph to the far side, tracking the shortest path to each far vertex.
 *  TODO Explain required file formating
 *  TODO Create automated pool builder
 *  TODO Allow loading of a saved pool
 *  TODO Write better comments
 * @author Spencer Peck
 *
 */
public class Pool {
	private EdgeWeightedDigraph diagraph;
	private int numOfColumns;
	private int rows;

	public Pool(int rows, int columns){
		poolBuilderFromDimensions(rows, columns);
		buildGraphRandom(new In("src/backEnd/resources/poolBuilder.txt"));
	}
	public Pool(int rows, int columns,Queue<WeightedBlob>blobs) {
		poolBuilderFromDimensions(rows, columns);
		buildGraphBlobs(new In("src/backEnd/resources/poolBuilder.txt"), blobs);
	}

	public Pool(In in) {
		buildGraphRandom(in);
	}

	private void buildGraphRandom(In in) {
		Queue<String> intakeQueue = new Queue<String>();
		Queue<String> primaryQueue = new Queue<String>();
		Queue<String> secondaryQueue = new Queue<String>();
		
		while(!in.isEmpty()) {
			for(String el: in.readLine().split(" ")) {
				intakeQueue.enqueue(el);
			}
		}
		numOfColumns = Integer.parseInt(intakeQueue.dequeue());
		rows = Integer.parseInt(intakeQueue.dequeue());
		String poolBuilderFile = "src/backEnd/resources/poolBuilder.txt";
		Out out = new Out(poolBuilderFile);
		out.println(intakeQueue.dequeue());//Output Number Of Vertices
		out.println(intakeQueue.dequeue());//Output Number Of Edges
		
		String intakePop;
		String primaryPop;
		String secondaryPop;
		boolean delim = false;
		int weight;
		
		while(!intakeQueue.isEmpty()) {
			intakePop = intakeQueue.dequeue();
			if(!intakeQueue.isEmpty() && !intakeQueue.peek().equals("#") && !intakePop.equals("#")) {
				weight = StdRandom.uniformInt(20);
				out.println(intakePop + " " + intakeQueue.peek() + " " + weight);
				out.println(intakeQueue.peek() + " " + intakePop + " " + weight);
			}
			
			if(intakePop.equals("#")) {
				delim = true;
				while(!secondaryQueue.isEmpty()) {
					secondaryPop = secondaryQueue.dequeue();
					primaryPop = primaryQueue.dequeue();
					if(!secondaryQueue.isEmpty()) {
						weight = StdRandom.uniformInt(20);
						out.println(primaryPop + " " + secondaryQueue.peek() + " " + weight);
						out.println(secondaryQueue.peek() + " " + primaryPop + " " + weight);

						weight = StdRandom.uniformInt(20);
						out.println(secondaryPop + " " + primaryQueue.peek() + " " + weight);
						out.println(primaryQueue.peek() + " " + secondaryPop + " " + weight);

						weight = StdRandom.uniformInt(20);
						out.println(secondaryQueue.peek() + " " + primaryQueue.peek() + " " + weight);
						out.println(primaryQueue.peek() + " " + secondaryQueue.peek() + " " + weight);
					}
					primaryQueue.enqueue(secondaryPop);
				}
			}
			
			else {
				if(delim == false) {
					primaryQueue.enqueue(intakePop);
				}
				else {
					secondaryQueue.enqueue(intakePop);
				}
			}
		}
		out.close();
		this.diagraph = new EdgeWeightedDigraph(new In(poolBuilderFile));
	}
	
	private void buildGraphBlobs(In in, Queue<WeightedBlob>blobs) {
		Queue<String> intakeQueue = new Queue<String>();
		Queue<String> primaryQueue = new Queue<String>();
		Queue<String> secondaryQueue = new Queue<String>();
		
		while(!in.isEmpty()) {
			for(String el: in.readLine().split(" ")) {
				intakeQueue.enqueue(el);
			}
		}
		numOfColumns = Integer.parseInt(intakeQueue.dequeue());
		rows = Integer.parseInt(intakeQueue.dequeue());
		String poolBuilderFile = "src/backEnd/resources/poolBuilder.txt";
		Out out = new Out(poolBuilderFile);
		out.println(intakeQueue.dequeue());//Output Number Of Vertices
		out.println(intakeQueue.dequeue());//Output Number Of Edges
		
		String intakePop;
		String primaryPop;
		String secondaryPop;
		boolean delim = false;
		int weight;
		
		while(!intakeQueue.isEmpty()) {
			intakePop = intakeQueue.dequeue();
			if(!intakeQueue.isEmpty() && !intakeQueue.peek().equals("#") && !intakePop.equals("#")) {
				
				weight = checkForBlobWeight(blobs, intakeQueue.peek(), intakePop);
				out.println(intakePop + " " + intakeQueue.peek() + " " + weight);
				out.println(intakeQueue.peek() + " " + intakePop + " " + weight);
			}
			
			if(intakePop.equals("#")) {
				delim = true;
				while(!secondaryQueue.isEmpty()) {
					secondaryPop = secondaryQueue.dequeue();
					primaryPop = primaryQueue.dequeue();
					
					if(!secondaryQueue.isEmpty()) {
						
						weight = checkForBlobWeight(blobs, secondaryQueue.peek(), primaryPop);
						out.println(primaryPop + " " + secondaryQueue.peek() + " " + weight);
						out.println(secondaryQueue.peek() + " " + primaryPop + " " + weight);

						weight = checkForBlobWeight(blobs, primaryQueue.peek(), secondaryPop);
						out.println(secondaryPop + " " + primaryQueue.peek() + " " + weight);
						out.println(primaryQueue.peek() + " " + secondaryPop + " " + weight);

						weight = checkForBlobWeight(blobs, secondaryQueue.peek(), primaryQueue.peek());
						out.println(secondaryQueue.peek() + " " + primaryQueue.peek() + " " + weight);
						out.println(primaryQueue.peek() + " " + secondaryQueue.peek() + " " + weight);
					}
					primaryQueue.enqueue(secondaryPop);
				}
			}
			
			else {
				if(delim == false) {
					primaryQueue.enqueue(intakePop);
				}
				else {
					secondaryQueue.enqueue(intakePop);
				}
			}
		}
		out.close();
		this.diagraph = new EdgeWeightedDigraph(new In(poolBuilderFile));
	}

	private int checkForBlobWeight(Queue<WeightedBlob> blobs, String vertexOne, String vertexTwo) {
		int weight;
		weight = 1;
		for(WeightedBlob blob: blobs) {
			if(blob.getIndex() == Integer.parseInt(vertexOne)) {
				weight =+ blob.getWeight();
			}
			else {
				if(blob.getIndex() == Integer.parseInt(vertexTwo)) {
					weight =+ blob.getWeight();
				}
			}
		}
		return weight;
	}
	
	private void poolBuilderFromDimensions(int rows, int columns) {
		Out out = new Out("src/backEnd/resources/poolBuilder.txt");
		out.println(columns);
		out.println(rows);
		out.println(rows*columns);
		out.println(((rows-1)*columns) + ((rows-1)*3));
		for( int i = 0; i < rows*columns; i++) {
			if(i % rows == 0){
				out.print(" #");
			}
			out.print(" " + i);
		}
		out.print(" #");
	}
	
	/**
	 * Refract Path calculates the shortest paths from the provided source to all of the vertexes on the other side of the graph
	 * @param sourceVertex is where all the calculated paths will start from
	 * @return an iterable collection of paths.
	 */
	public Bag<Iterable<DirectedEdge>> refractPath(int sourceVertex ) {
		Bag<Iterable<DirectedEdge>> bag = new Bag<>();
		int endColumnOffset = rows * (numOfColumns - 1);
		DijkstraSP dijkstra = new DijkstraSP(diagraph, sourceVertex);
		for(int i  = 0; i < getRowCount(); i++) {
			bag.add(dijkstra.pathTo(i + endColumnOffset));
		}
		return bag;
		
	}
	public static int getIndexFromXY(int vertexXCord, int vertexYCord, int rowsInPool, int columnsInPool) {
		if(vertexXCord > columnsInPool || vertexXCord < 1 || vertexYCord < 1 || vertexYCord > rowsInPool) {
			throw new IllegalArgumentException("X & Y Cords must be within the pool boundries");
		}
		return  (vertexYCord + ((vertexXCord-1)*rowsInPool))-1;
	}
	
	public int getRowCount() {
		return rows;
	}

	public int getNumOfColumns() {
		return numOfColumns;
	}
	
}
