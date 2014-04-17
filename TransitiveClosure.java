/* 
 * TransitiveClosure.java 
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
* Class to implement the Transitive closure algorithm.
*
* @author Shobhit
*/
public class TransitiveClosure {
	int adj[][], v, e;
	BufferedReader br;
	/**
	 * The main program.
	*/
	public static void main(String[] args) throws IOException {
		TransitiveClosure k=new TransitiveClosure();
		k.fillAdjacencyMatrix();
		long startTime=System.nanoTime();
		k.computeTransitiveClosure();
		long endTime=System.nanoTime();
		double time=(Math.round(((endTime-startTime)/1000000.0)*100.0)/100.0);
		k.display();
		System.out.println("Time to compute "+(time));
	}
	/**
	 * Displays the transitive closure matrix
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
	 * Fills the adjacency matrix from the standard input supplied.
	 * First gets the number of vertices and edges
	 * Each input line read is split by spaces.
	 * Sets 0 to unreachable vertices and 1 to reachable ones
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
			adj[v1][v2]=1;
		}
		for (int i = 0; i < adj.length; i++) {
				adj[i][i]=1;
		}
	}
	/**
	 * Computes the transitive closure from every vertex to all other vertices.
	*/
	private void computeTransitiveClosure() {
		int indirectDist;
		for (int k = 0; k < adj.length; k++) {
			for (int i = 0; i < adj.length; i++) {
				for (int j = 0; j < adj.length; j++) {
					if(adj[i][k]==1 && adj[k][j]==1)
						indirectDist=1;
					else 
						indirectDist=0;
					if(indirectDist==1||adj[i][j]==1)
						adj[i][j]=1;
				}	
			}
		}
	}
}