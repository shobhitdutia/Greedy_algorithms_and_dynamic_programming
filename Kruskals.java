import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* 
 * Kruskals.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.util.Collections;
import java.util.Vector;
/**
* Class to create Minimum cost spanning tree using Kruskal's algorithm.
*
* @author Shobhit
*/
public class Kruskals {
	int e, v, unionFind[], cost=0;
	BufferedReader br;
	Vector<Edge> edgeVector;
	Vector<Edge> treeVector;
	/**
	 * The main program.
	*/
	public static void main(String[] args) throws IOException {
		Kruskals k=new Kruskals();
		k.fillEdges();
		long startTime=System.nanoTime();
		k.computeMCST();
		long endTime=System.nanoTime();
		double time=(Math.round(((endTime-startTime)/1000000.0)*100.0)/100.0);
		k.displayTree();
		System.out.println("Time to compute "+(time));
	}
	/**
	 * Displays the minimum cost spanning tree and its associated cost.
	*/
	private void displayTree() {
		System.out.println("Minum cost spanning tree is:");
		for(Edge e: treeVector) {
			System.out.println("("+e.v1+","+e.v2+") ="+e.cost);
		}
		System.out.println("Cost of minimum spanning tree is:" +cost);
		System.out.println("Vertices: "+v);
		System.out.println("Edges: "+e);
	}
	/**
	 * Fills the edge vector from the standard input supplied.
	 * Each input line is split by spaces
	 * 
	 * @exception   IOException    Throws an input or output exception if any
	*/
	private void fillEdges() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String line[];
		line = br.readLine().split(" ");
		v=Integer.parseInt(line[0]);
		e=Integer.parseInt(line[1]);
		edgeVector=new Vector<Edge> ();
		int count=0;
		while(count <e) {
			line=br.readLine().split(" ");
			count++;
			int v1=Integer.parseInt(line[0]);
			int v2=Integer.parseInt(line[1]);
			int cost=Integer.parseInt(line[2]);
			edgeVector.add(new Edge(v1,v2,cost));
		}
	}
	/**
	 * Computes the minimum cost spanning tree.
	 * First it sorts the edges according to the weight and then uses union
	 * find algorithm to construct the tree.
	*/
	private void computeMCST() {	
		treeVector=new Vector<Edge> ();
		Collections.sort(edgeVector);
		makeSet(v);
		for (Edge e1:edgeVector) {
			int v1=e1.v1;
			int v2=e1.v2;
			int parent_v1=findParent(v1);
			int parent_v2=findParent(v2);
			if(parent_v1!=parent_v2) {
				unionFind[v2]=parent_v1;
				treeVector.add(e1);
				cost+=e1.cost;
			}
		}
	}
	/**
	 * Finds the parent in union find algorithm.
	 * If the parent is the same as the vertex, it returns it otherwise
	 * it recursively calls its parent to return his parent. 
	*/
	private int findParent(int v) { 
		int parent=unionFind[v];
		if(parent==v)
			return v;
		else 
			return findParent(parent);
	}
	/**
	 * Makes the initial set of union find algorithm.
	 * The parent value of a particular vertex is the vertex itself. 
	*/
	private void makeSet(int v) {
		unionFind =new int[v];
		for (int i = 0; i < v; i++) {
			unionFind[i]=i;
		}
	}
}