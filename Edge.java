/* 
 * Eege.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

/**
* Class to implement the Edge object used in Kruska's algorithm.
*
* @author Shobhit
*/
public class Edge implements Comparable<Edge>{
	int v1, v2, cost;
	Edge(int v1, int v2, int cost) {
		this.v1=v1;
		this.v2=v2;
		this.cost=cost;
	}
	/**
	 * Print's the edge object
	*/
	public String toString() {
		return(""+this.v1+" "+this.v2+" "+this.cost);
	}
	/**
	 * Compares two edge objects according to their costs
	 * 
	 * @return    -1, 1 or 0 depending of whether the edge is 
	 * 			   less than greater than or equal to another edge
	*/
	@Override
	public int compareTo(Edge e) {
		if(this.cost==e.cost)
			return 0;
		else if(this.cost<e.cost)
			return -1;
		else 
			return 1;
	}
}
