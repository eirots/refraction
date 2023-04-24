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
 * Most of the constructors for pool requires a file, formatted in the following way:
 * Line 1) Number of Columns present in the pool.
 * Line 2) Number of Rows present in the pool
 * Line 3) Number of Vertices in the pool.
 * line 4) Number of Edges in the pool.
 * Line 5) The list of vertexes starting from 0. Each new column will be designated by a delimiter ("#" by default) and the line must be capped by a trailing delimiter.
 * Vertexes should be spaced by a consistent separator.
 * 
 * Example for a 3x4(ColumnsXRows) with default delimiter: 
 * "3"
 * "4"
 * "12"
 * "27"
 * "0 1 2 3 # 4 5 6 7 # 8 9 10 11 #"
 * 
 * @author Spencer Peck
 *
 */
public class Pool {
	private EdgeWeightedDigraph diagraph;
	private int numOfColumns;
	private int rows;

	/**
	 * Creates a Pool based off of a number of rows and columns. Assigns random weights to each edge.
	 * @param rows present in the pool
	 * @param columns present in the pool
	 */
	public Pool(int rows, int columns){
		poolBuilderFromDimensions(rows, columns);
		buildGraphRandom(new In("src/backEnd/resources/poolBuilder.txt"), "#", " ");//Uses "#" as the standard, delimiter
	}
	
	/**
	 * Creates a Pool based off of a number of rows and columns. Assigns weights to each edge based off of the provided blobs or 1 if no input is provided.
	 * @param rows present in the pool
	 * @param columns present in the pool
	 * @param A queue of type WeightedBlob to provide functionality for custom weighted edges.
	 */
	public Pool(int rows, int columns,Queue<WeightedBlob>blobs) {
		poolBuilderFromDimensions(rows, columns);
		buildGraphBlobs(new In("src/backEnd/resources/poolBuilder.txt"), blobs,"#", " ");
	}

	/**
	 * Builds a Pool based off the data found in the provided text file. Assigns random weights from 0 to 20 to each edge.
	 * @param in
	 */
	public Pool(In in, String delimiter, String separator) {
		buildGraphRandom(in,delimiter, separator);
	}

	/**
	 * Reads a text file formatted for Pool and creates a structured diagraph based off of the input. 
	 * Edges are weighted randomly between zero and 20
	 * @param in. A text file formatted in the way described by the Pool Class.
	 */
	private void buildGraphRandom(In in, String delimiter, String separator) {
		//Creates the queues needed to structure the graph
		Queue<String> intakeQueue = new Queue<String>();
		Queue<String> primaryQueue = new Queue<String>();
		Queue<String> secondaryQueue = new Queue<String>();
		
		while(!in.isEmpty()) { //Fill intake Queue
			for(String el: in.readLine().split(separator)) {
				intakeQueue.enqueue(el);
			}
		}
		
		numOfColumns = Integer.parseInt(intakeQueue.dequeue());//Record the Number of Columns
		rows = Integer.parseInt(intakeQueue.dequeue());//Record the Number of Rows
		String graphBuilderFile = "src/backEnd/resources/graphBuilder.txt";
		Out out = new Out(graphBuilderFile); //Needed for formatting a file to match the requirements of a Algs4 diagraph.
		out.println(intakeQueue.dequeue());//Output Number Of Vertices
		out.println(intakeQueue.dequeue());//Output Number Of Edges
		
		String intakePop; // The latest Popped value from the specified Queue.
		String primaryPop;
		String secondaryPop;
		boolean delim = false; // Used to mark when we have seen a hashtag
		int weight;
		
		while(!intakeQueue.isEmpty()) {
			intakePop = intakeQueue.dequeue();
			if(!intakeQueue.isEmpty() && !intakeQueue.peek().equals(delimiter) && !intakePop.equals(delimiter)) {
				weight = StdRandom.uniformInt(20);
				out.println(intakePop + " " + intakeQueue.peek() + " " + weight);
				out.println(intakeQueue.peek() + " " + intakePop + " " + weight);
			}
			
			if(intakePop.equals(delimiter)) {
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
		this.diagraph = new EdgeWeightedDigraph(new In(graphBuilderFile));
	}
	
	/**
	 * Reads a text file formatted for Pool and creates a structured diagraph based off of the input. 
	 * Edges are weighted according to the provided blobs. Edges not connected to a specified blob receive a weight of 1.
	 * @param in. A text file formatted in the way described by the Pool Class.
	 * @param blobs. A Queue of WeightedBlob to designate which edges have custom weights
	 */
	private void buildGraphBlobs(In in, Queue<WeightedBlob>blobs, String delimiter, String separator) {
		Queue<String> intakeQueue = new Queue<String>();
		Queue<String> primaryQueue = new Queue<String>();
		Queue<String> secondaryQueue = new Queue<String>();
		
		while(!in.isEmpty()) {
			for(String el: in.readLine().split(separator)) {
				intakeQueue.enqueue(el);
			}
		}
		numOfColumns = Integer.parseInt(intakeQueue.dequeue());
		rows = Integer.parseInt(intakeQueue.dequeue());
		String graphBuilderFile = "src/backEnd/resources/graphBuilder.txt";
		Out out = new Out(graphBuilderFile);
		out.println(intakeQueue.dequeue());//Output Number Of Vertices
		out.println(intakeQueue.dequeue());//Output Number Of Edges
		
		String intakePop;
		String primaryPop;
		String secondaryPop;
		boolean delim = false;
		int weight;
		
		while(!intakeQueue.isEmpty()) {
			intakePop = intakeQueue.dequeue();
			if(!intakeQueue.isEmpty() && !intakeQueue.peek().equals(delimiter) && !intakePop.equals(delimiter)) {
				
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
		this.diagraph = new EdgeWeightedDigraph(new In(graphBuilderFile));
	}

	/**
	 * Processing for custom weighted edges.
	 * Checks a Queue of WeightedBlobs to see if they match either of the specified indices, 
	 * indices that match the Blob index add the blob weight to the return value.
	 * @param blobs. List of WeightedBlobs.
	 * @param vertexOne The first vertex of the edge.
	 * @param vertexTwo The Second vertex of the edge.
	 * @return The custom edge weight
	 */
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
	
	/**
	 * Creates a formatted file needed to create a Pool based off of grid dimensions.
	 * @param rows
	 * @param columns
	 */
	private void poolBuilderFromDimensions(int rows, int columns) {
		Out out = new Out("src/backEnd/resources/poolBuilder.txt");
		out.println(columns);
		out.println(rows);
		out.println(rows*columns);
		//m is rows n is columns
		out.println((((rows-1)*columns) + (((rows-1)*3)*(columns-1)))*2) ;
		out.print("0");
		for( int i = 1; i < rows*columns; i++) {
			if(i % rows == 0 && i != 0){
				out.print(" #"); //Standard delimiter
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
	/**
	 * Calculates the index of a vertex based off of X and Y Cords
	 * @param vertexXCord. X value of the vertex
	 * @param vertexYCord. Y value of the vertex
	 * @param rowsInPool. Y size of the Pool
	 * @param columnsInPool. X size of the Pool
	 * @return Index of the Vertex
	 */
	public static int getIndexFromXY(int vertexXCord, int vertexYCord, int rowsInPool, int columnsInPool) {
		if(vertexXCord > columnsInPool || vertexXCord < 1 || vertexYCord < 1 || vertexYCord > rowsInPool) {
			throw new IllegalArgumentException("X & Y Cords must be within the pool boundries");
		}
		return  (vertexYCord + ((vertexXCord-1)*rowsInPool))-1;
	}
	
	public EdgeWeightedDigraph getDigraph() {
		return diagraph;
	}
	
	public int getRowCount() {
		return rows;
	}

	public int getNumOfColumns() {
		return numOfColumns;
	}
	
}
