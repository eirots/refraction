package backEnd;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class Pool {
	private EdgeWeightedDigraph diagraph;
	private int numOfColumns;
	private int rows;
	
	public Pool(In in) {
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
		String fileName = "src/backEnd/resources/poolBuilder.txt";
		Out out = new Out(fileName);
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
				weight = StdRandom.uniformInt(10);
				out.println(intakePop + " " + intakeQueue.peek() + " " + weight);
				out.println(intakeQueue.peek() + " " + intakePop + " " + weight);
			}
			
			if(intakePop.equals("#")) {
				delim = true;
				while(!secondaryQueue.isEmpty()) {
					secondaryPop = secondaryQueue.dequeue();
					primaryPop = primaryQueue.dequeue();
					if(!secondaryQueue.isEmpty()) {
						weight = StdRandom.uniformInt(10);
						out.println(primaryPop + " " + secondaryQueue.peek() + " " + weight);
						out.println(secondaryQueue.peek() + " " + primaryPop + " " + weight);

						weight = StdRandom.uniformInt(10);
						out.println(secondaryPop + " " + primaryQueue.peek() + " " + weight);
						out.println(primaryQueue.peek() + " " + secondaryPop + " " + weight);

						weight = StdRandom.uniformInt(10);
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
		this.diagraph = new EdgeWeightedDigraph(new In(fileName));
	}
	
	public Bag<Iterable<DirectedEdge>> refractPath(int sourceVertex ) {
		Bag<Iterable<DirectedEdge>> bag = new Bag<>();
		int endColumnOffset = rows * (numOfColumns - 1);
		DijkstraSP dijkstra = new DijkstraSP(diagraph, sourceVertex);
		for(int i  = 0; i < getRowCount(); i++) {
			bag.add(dijkstra.pathTo(i + endColumnOffset));
		}
		return bag;
		
	}
	
	public int getRowCount() {
		return rows;
	}

	public int getNumOfColumns() {
		return numOfColumns;
	}
	
}
