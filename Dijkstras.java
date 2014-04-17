/* 
 * Dijkstras.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
/**
* Class to implement the Dijkstra's shortest path algorithm.
*
* @author Shobhit
*/
public class Dijkstras {
	int e,v, distArray[], adj[][];
	BufferedReader br;
	Vector<Integer> adjListVector[];
	Vector<Integer> visited;
	/**
	 * The main program.
	*/
	public static void main(String[] args) throws IOException {
		Dijkstras k=new Dijkstras();
		k.fillAdjacencyMatrix();
		long startTime=System.nanoTime();
		k.computeShortestPaths();
		long endTime=System.nanoTime();
		double time=(Math.round(((endTime-startTime)/1000000.0)*100.0)/100.0);
		k.display();
		System.out.println("Time to compute "+(time));
	}
	/**
	 * Displays the distance from source to all other vertices.
	 * Prints infinity if the node is not reachable
	*/
	private void display() {
		System.out.println("Distance from source to all other nodes is :");
		for (int j = 0; j < distArray.length; j++) {
			if(distArray[j]==Integer.MAX_VALUE)
				System.out.println(j+" :infinity");
			else
				System.out.println(j+":"+distArray[j]);
		}
		System.out.println("Vertices: "+v);
		System.out.println("Edges: "+e);
	}
	/**
	 * Fills the adjacency matrix and adjacency list of each vertex
	 * from the standard input supplied.
	 * Each input line read is split by spaces.
	 * 
	 * @exception   IOException    Throws an input or output exception if any
	*/
	@SuppressWarnings("unchecked")
	private void fillAdjacencyMatrix() throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));	
		String line[];
		line = br.readLine().split(" ");
		v=Integer.parseInt(line[0]);
		e=Integer.parseInt(line[1]);
		adjListVector=new Vector[v];
		for (int i = 0; i < adjListVector.length; i++) {
			adjListVector[i]=new Vector<Integer>();
		} 	
		adj=new int[v][v];
		int count=0, v1, v2, cost;
		while(count <e) {
			line=br.readLine().split(" ");
			count++;
			v1=Integer.parseInt(line[0]);
			v2=Integer.parseInt(line[1]);
			cost=Integer.parseInt(line[2]);
			adj[v1][v2]=cost;
			adjListVector[v1].add(v2);
		}
	}
	/**
	 * Computes the shortest path from source to all other vertices.
	 * Initializes the distance array from source to all other vertices
	 * For all unvisited vertices, visits the minimum vertex and explore its 
	 * adjacency list to check if current distance from 0th vertex is less 
	*/
	private void computeShortestPaths() {
		int minVertex=initDistanceArray(), min;
		Vector<Integer> visited=new Vector<Integer> ();
		visited.add(0);
		int minofAdjVertex=0;
		while(visited.size()<v) {
			visited.add(minVertex);
			min=Integer.MAX_VALUE;
			for(int adjacentVertex:adjListVector[minVertex]) {
				if(!visited.contains(adjacentVertex)) {
					int distanceToAdjacentVertex=distArray[minVertex]+adj[minVertex][adjacentVertex];
					if(distArray[adjacentVertex]>distanceToAdjacentVertex) 
						distArray[adjacentVertex]=distanceToAdjacentVertex;
					
					if(distArray[adjacentVertex]<min) {
						min=distArray[adjacentVertex];
						minofAdjVertex=adjacentVertex;
					}
				}
			}
			if(minofAdjVertex!=0)
				minVertex=minofAdjVertex;
			else 
				break;
		}
	}
	/**
	 * Initializes the distance array from source to all other vertices
	 * Set maximum Integer value to all unreachable vertices from 0th vertex
	 * 
	 * @return	minimum cost vertex from the source
	*/
	private int initDistanceArray() {
		int min=-1, minVertex=0;
		distArray=new int[v];
		int costOfAdjacentNodeof0;
		for(int AdjacentNodeof0: adjListVector[0]) {
			costOfAdjacentNodeof0=adj[0][AdjacentNodeof0];
			distArray[AdjacentNodeof0]=costOfAdjacentNodeof0;
			if(costOfAdjacentNodeof0<min||min==-1) {
				min=costOfAdjacentNodeof0;
				minVertex=AdjacentNodeof0;
			}
		}
		for(int i=0; i<distArray.length; i++) {
			if(distArray[i]==0 && i!=0)
				distArray[i]=Integer.MAX_VALUE;
		}
		return minVertex;
	}
}