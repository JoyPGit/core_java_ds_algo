
// A Java program to print topological sorting of a DAG 
import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency 
// list representation 
class Graph {
	private int V; // No. of vertices

	// Adjacency List as ArrayList of ArrayList's
	private ArrayList<ArrayList<Integer>> adj;

	// Constructor
	Graph(int v) {
		V = v;
		adj = new ArrayList<ArrayList<Integer>>(v);
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<Integer>());
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj.get(v).add(w);
	}


    void print1DMatrix(int[] arr) {
		for(int i=0; i<arr.length; i++){
			if(i == arr.length-1) System.out.print(arr[i]);
			System.out.print(arr[i]+", ");
		}
		System.out.println();
	}

	/** Steps
	 * 1 call util for each vertex if not visited
	 * 2 iterator->hasNext-> mark visited
	 * 3 push to stack
	 */
	void topologicalSort() {
		Deque<Integer> stack = new LinkedList<Integer>();

		// Mark all the vertices as not visited
		int visited[] = new int[V];
		for (int i = 0; i < V; i++)
			visited[i] = 0;

		// Call the recursive helper function to store
		// Topological Sort starting from all vertices
		// one by one
		for (int i = 0; i < V; i++){
			if (visited[i] == 0) topologicalSortUtil(i, visited, stack);
		}
			

		while (stack.size() != 0)
			System.out.print(stack.pop() + " ");
		
		System.out.println();
	}

	void topologicalSortUtil(int v, int visited[], Deque<Integer> stack) {
		visited[v] = 1;

		Iterator<Integer> it = adj.get(v).iterator();
		while (it.hasNext()) {
			int i = it.next();
			if (visited[i] != 1)
				topologicalSortUtil(i, visited, stack);
		}

		stack.push(v);
	}

	/** a global variable is used to break as soon as lop os found
	 * here we use 
	 * 1 dfs
	 * 2 backtracking
	 * 
	 * first we run the loop for all vertices, for each vertex, we mark it
	 * as visited (line 86),
	 * and then recur for it's adjacent vertices. If any of the 
	 * adjacent is visited beforehand (visited[i] == 1), a loop exists.
	 * 
	 * As soon as we complete visiting all adjacent of a vertex, we mark the vertex 
	 * as visted false. (line 97)
	 * 
	 * 
	 * IN TOPOLOGICAL SORT WE USE THE SAME CONCEPT BUT INSTEAD OF MARKING
	 * VISITED WE PUSH TO STACK.
	 */

	boolean loop = false;
	boolean detectLoopInGraph(){
		int[] visited = new int[this.V];

		for(int i=0; i<this.V; i++){
			detectLoopUtil(i, visited);
			if(loop) return loop;
		}
		return loop;
	}

	void detectLoopUtil(int vertex, int[] visited) {
		visited[vertex] = 1;
		Iterator<Integer> it = this.adj.get(vertex).iterator();
		while (it.hasNext()) {
			int i = it.next();
			if (visited[i] != 0) {
				this.loop = true;
				System.out.println("loop detected");
				break;
				// if(i == current) loop = true;
			}else detectLoopUtil(i, visited); 
		}
		visited[vertex] = 0;

	}

	/** basically same as backtracking.. a vertex is continually assigned colors
     * from 1 till n, and we check if it's safe, then we recur, else 
     * we go back to assigning it 0.
     * 1 isSafe is tricky, run a for loop for all adjacent vertices
     * check if the color is same as the color assigned to the vertex 
     * if there exists an edge.
     * 
     * 2 a global var foundMinColor is used to break out of the recursive calls
	 * 
	 * ABOVE BACKTRACKING IS ON ADJACENCY LIST, HERE ON A MATRIX.
     * 
     */
    boolean foundMinColor =false; 
    void mColoring(int[][] arr){
        int[] color = new int[arr.length];
        mcolorUtil(arr, 0, color);
    }
    
    void mcolorUtil(int[][] arr,int vertex, int[] color){
        if(vertex == arr.length) {
            print1DMatrix(color);
            this.foundMinColor = true; 
            int max =0;
            for(int i =0; i<color.length; i++){
                max = Math.max(max, color[i]);
            }
            System.out.println("no of colors required is "+(max-1));
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (!foundMinColor) {
                color[vertex] = i;

                if (isSafemColor(arr, vertex, color)) {
                    mcolorUtil(arr, vertex + 1, color);
                }

                color[vertex] = 0;
            }

        }
    }

	boolean isSafemColor(int[][] arr, int vertex, int[] color) {
        for(int i =0; i<arr.length; i++){
            if(arr[vertex][i] == 1 && color[vertex]==color[i]) return false;
        }
        return true;
    }
	
	class BFSHolder{
		int val; int row; int col;
		BFSHolder(int v, int r, int c){
			this.val = v;
			this.row = r;
			this.col = c;
		}
	}
	
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		Graph g = new Graph(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(3, 5);

		System.out.println("Following is a Topological " + "sort of the given graph");
		g.topologicalSort();

		g.detectLoopInGraph();

		int[][] graph = {
			{0,1,1,1},
			{0,0,0,1},
			{1,1,0,0},
			{0,0,0,0}
		};
	}
}

