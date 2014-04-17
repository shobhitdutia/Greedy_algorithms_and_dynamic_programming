/* 
 * AllPairsShortestPath.java 
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
/**
* Class to implement the Floyd warshall's all pairs shortest path algorithm.
*
* @author Shobhit
*/
public class AllPairsShortestPath {
	int adj[][], v, e;
	BufferedReader br;
	/**
	 * The main program.
	*/
	public static void main(String[] args) throws IOException {
		AllPairsShortestPath k=new AllPairsShortestPath();
		k.fillAdjacencyMatrix();
		long startTime=System.nanoTime();
		k.computeShortestPaths();
		long endTime=System.nanoTime();
		double time=(Math.round(((endTime-startTime)/1000000.0)*100.0)/100.0);
		k.display();
		System.out.println("Time to compute "+(time));
	}
	/**
	 * Displays the shortest path matrix
	*/
	private void display() {
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++) {
				System.out.print(adj[i][j]+"  ");	
			}
			System.out.println();
		}
		System.out.println("Vertices: "+v);
		System.out.println("Edges: "+e);
	}
	/**
	 * First gets the number of vertices and edges
	 * Fills the adjacency matrix from the standard input supplied.
	 * Each input line read is split by spaces.
	 * Also sets maximum integer value to unreachable vertices
	 * 
	 * @exception   IOException    Throws an input or output exception if any
	*/
	private void fillAdjacencyMatrix() throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));	
		String line[];
		line = br.readLine().split(" ");
		v=Integer.parseInt(line[0]);
		e=Integer.parseInt(line[1]);
		adj=new int[v][v];
		int count=0;
		while(count <e) {
			line=br.readLine().split(" ");
			count++;
			int v1=Integer.parseInt(line[0]);
			int v2=Integer.parseInt(line[1]);
			int cost=Integer.parseInt(line[2]);
			adj[v1][v2]=cost;
		}
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++) {
				if (adj[i][j]== 0&& (j!=i)) {
					adj[i][j]=Integer.MAX_VALUE;
				}
			}
		}
	}
	/**
	 * Computes the shortest path from every vertex to all other vertices.
	*/
	private void computeShortestPaths() {
		int indirectDist;
		for (int k = 0; k < adj.length; k++) {
			for (int i = 0; i < adj.length; i++) {
				for (int j = 0; j < adj.length; j++) {
					if((adj[i][k]==Integer.MAX_VALUE)||(adj[k][j]==Integer.MAX_VALUE))
						indirectDist=Integer.MAX_VALUE;
					else
						indirectDist=adj[i][k]+adj[k][j];
					if(indirectDist<adj[i][j])
						adj[i][j]=indirectDist;
				}	
			}
		}
	}
}