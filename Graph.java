
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
	/** 
	 * TEMPLATE FOR GRAPH QUES (DFS)
	 * 
	 * List<List<Integer>> doesn't need a multi ArrayList
     * only use new ArrayList when adding to final list
	 * 
	 * public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        temp.add(0);
        dfs(res, temp, graph, 0);
        return res;
     }
	
     * void dfs(List<List<Integer>> res, List<Integer> temp, int[][] graph, int curr){
        if(curr == graph.length-1){
            // temp.add(curr); already added
            res.add(new ArrayList<>(temp));
            return;
        }
        for( int j:graph[curr]){
            temp.add(j);
            dfs(res, temp, graph, j);
            temp.remove(temp.size()-1);
        }
	 }
	 *
	 */

	void dfs(Graph g){
		boolean[] visited= new boolean[g.V];
		for(int i =0; i<g.V; i++){
			dfsHelper(g.adj, i, visited);
		}
	} 

	void dfsHelper(ArrayList<ArrayList<Integer>> list, int parent, boolean[] visited){
		System.out.println("dfs "+parent);
		if(!list.contains(adj.get(parent))) return;
		ArrayList<Integer> curr = adj.get(parent);
		for(int i =0; i<curr.size(); i++){
			if(!visited[i]) {
				visited[i] = true;
				dfsHelper(adj, i, visited);
			}
		}
	}

	// dfsMatrix(int[][] matrix){

	// }
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

	/** points:
	 * 1 use structure of backtracking, add, recur and remove
	 * 2 maintain visited array
	 * 3 procedure visited = true, recur, add to list
	 * 4 Iterator<Integer> it = g.adj.get(i).iterator(); 
	 * 5 Integer vertex = it.next()
	 */
	void topoSort(Graph g){
		Deque<Integer> list = new LinkedList<>();
		int[] visited  =  new int[g.V];
		System.out.println("V "+g.V);
		for(int i =0; i<g.V; i++){
			if(visited[i]!=1){
				visited[i] = 1;
				topoSortHelper(g, i, list, visited);
				list.add(i);
			}
		}
		System.out.println("list "+list.toString());
	}

	void topoSortHelper(Graph g, int i, Deque<Integer> list, int[] visited){
		Iterator<Integer> it = g.adj.get(i).iterator();
		while(it.hasNext()){
			Integer vertex = it.next();
			System.out.println("vertex " + vertex);
			if(visited[vertex]==1) continue;
			visited[vertex] = 1;
			topoSortHelper(g, vertex, list, visited);
			list.add(vertex);
			System.out.println("list help "+list);
		}
	}
	
	// https://leetcode.com/problems/course-schedule/
	// https://leetcode.com/problems/is-graph-bipartite/
	// https://leetcode.com/problems/redundant-connection-ii/ BFS CAN BE USED?
	// https://leetcode.com/problems/critical-connections-in-a-network/
	// https://leetcode.com/problems/minimum-height-trees/
	// https://leetcode.com/problems/time-needed-to-inform-all-employees/
	/**
	 * TECHNIQUE : 
	 * 1 CREATE A GRAPH USING HASHMAP
	 * 2 DFS
	 * 3 FOR TIME THERE ARE 2 WAYS, 
	 * 3.1 ADD TIME BEFORE ENTERING CHILD, IF THE CHILD DOESN'T HAVE ANY FURTHER
	 * 	CHILDREN IT JUST RETURN THE TIME.
	 *  IT WORKS BECAUSE ONLY NODES HAVING CHIDREN ARE IN MAP, SO IF NODE IS 
	 *  IN MAP, THEN IT MUST HAVE CHILD NODE(S), HENCE ADDING INFORM TIME 
	 *  BEFORE HAND IS OK.
	 * 
	 * 3.2 USE F(ROOT) = INFORMTIME[ROOT] + F(CHILD);
	 * SET FLAG TO ZERO IN EACH ITERATION AND RETURN MAX
	 * MAX(MINUTES, INFORMTIME[ROOT]+DFS(CHILD))
	 * 
	 * 4 USE DP TO STORE VALUE IN ANOTHER MAP (TIMEHOLDER)
	 * 
	 */
	// AMAZON
	int minutes = Integer.MIN_VALUE;
	HashMap<Integer, Integer> timeHolder = new HashMap<>();
	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

		for(int i =0; i< manager.length; i++){
			if(manager[i] == -1) continue;
			else if(map.containsKey(manager[i])){
				ArrayList<Integer> c = map.get(manager[i]);
				c.add(i);
				map.put(manager[i], c);
			} else{
				ArrayList<Integer> c= new ArrayList<>();
				c.add(i);
				map.put(manager[i], c);
			}
		}

        // System.out.println(map);
		dfsNumMinutes(map, headID, informTime, 0);
		// return dfsNumMinutes(map, headID, informTime, 0);
		return minutes;
	}
	
	/** 
	 * works but added informtime beforehand
	void dfsNumMinutes(HashMap<Integer, ArrayList<Integer>> map, int start, 
													int[] informTime, int time){
		if(!map.containsKey(start)) {
			minutes = Math.max(minutes, time);
			return;
		}
		time+=informTime[start];
        // System.out.println(time);
		for(int i : map.get(start)){
			dfsNumMinutes(map, i, informTime, time);
            
		}
	}
	*/

	int dfsNumMinutes(HashMap<Integer, ArrayList<Integer>> map, int start, 
													int[] informTime, int time){
		int minutes =0;
		if(!map.containsKey(start)) return 0;
		// System.out.println(time);
		for(int i : map.get(start)){
			if(timeHolder.containsKey(i)) return timeHolder.get(i);
			minutes = Math.max(minutes, informTime[start]+dfsNumMinutes(map, i, informTime, time));
			timeHolder.put(i, minutes);
		}
		return minutes;
	}

	// https://leetcode.com/problems/course-schedule/
	boolean notLoop = true; 
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length==0) return notLoop;
        
        int[] visited = new int[numCourses];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int i=0; i< prerequisites.length; i++){
            if(map.containsKey(prerequisites[i][1])){
                ArrayList<Integer> curr = map.get(prerequisites[i][1]);
                curr.add(prerequisites[i][0]);
                map.put(prerequisites[i][1], curr);
            } else{
                ArrayList<Integer> curr = new ArrayList<>();
                curr.add(prerequisites[i][0]);
                map.put(prerequisites[i][1], curr);
            }
        }
        // System.out.println(map);
        for(int i =0; i<prerequisites.length; i++){
            dfs(map, prerequisites[i][1], visited);    
        }
        return notLoop;
    }
    
    void dfs(HashMap<Integer, ArrayList<Integer>> map, int start, int[] visited){
        if(notLoop){
            if(!map.containsKey(start)) return;
            // System.out.println(start);
            visited[start] = 1;
            for(int i: map.get(start)){
                // System.out.println(i);
                if(visited[i] == 1) {
                    notLoop = false; return;
                }
                dfs(map, i, visited);
            }    
            visited[start] = 0;
        }
	}
	
	// BFS
	// https://leetcode.com/problems/rotting-oranges/
	/** POINTS :
	 * 1 USE A CUSTOM CLASS
	 * 2 USE A QUEUE AND ADD ALL 2s
	 * 3 THE TRICKY THING IS TO KEEP TRACK OF VISITED INDEXES, CAN BE
	 *   HANDLED VIA IS SAFE CHECKER AND MARKING THE GRID INDEX AS 2.
	 * 
	 * WE ADD ONLY 2, WE CHECK FOR SURROUNDING INDEXES, IF SAFE
	 * MARK THEM AS 2 AND INCREMENT TIME;
	 * 
	 */
	class Orange{
		int row, col, val, time; 
		Orange(int r, int c, int v, int t){
			this.row = r; this.col =c; this.val = v; this.time = t;
		}
	}
    public int orangesRotting(int[][] grid) {
		Deque<Orange> q = new LinkedList<>();
		int maxTime =0; 
		for(int i =0; i<grid.length; i++){
			for(int j=0; j<grid[0].length; j++){
				if(grid[i][j] == 2) q.add(new Orange(i, j, 2, 0));
			}
		}
		
		int[] rows = {-1,0,0,1}; int[] cols = {0,-1,1,0};
		while(q.size()!=0){
			Orange curr = q.removeFirst();
          
			for(int i =0;i<rows.length; i++){
				if(isSafeOrange(grid, curr.row+rows[i], curr.col+cols[i])){
                    grid[curr.row+rows[i]][curr.col + cols[i]] =2;
					q.addLast(new Orange(curr.row+rows[i], curr.col + cols[i], 2, curr.time+1));
					maxTime = curr.time+1;
				}
			}
		}
    
        for(int i =0; i<grid.length; i++){
			for(int j=0; j<grid[0].length; j++){
				if(grid[i][j] == 1) return -1;
			}
		}
		return maxTime;
	}

	boolean isSafeOrange(int[][] grid, int row, int col){
		if(row>=0 && row<grid.length
		&& col>=0 && col<grid[0].length
		&& grid[row][col]==1) return true;
		return false;
	}

	// https://leetcode.com/problems/path-with-maximum-probability/
	// https://leetcode.com/problems/rotting-oranges/

	// DFS EXAMPLE

	// https://leetcode.com/problems/all-paths-from-source-to-target/
	/** 
	 * 1 the indexes are the vertices, int j :graph[curr] curr is int
	 *   and references to the particular row index
	 * 
	 * 2 use a helper
	 * 3 MOST IMP : ALWAYS USE 
	 *   List<List<Integer>> res = new ArrayList<>();
		 List<Integer> temp = new ArrayList<>();
		 
	 *	AND PASS THIS temp IN dfs;
	 *  dfs (res, temp, graph, 0);
	 *  
	 * 4 while adding the result clone it into a new ArrayList
	 * 

        
	 */
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        temp.add(0);
        dfs(res, temp, graph, 0);
        return res;
    }
	
    void dfs(List<List<Integer>> res, List<Integer> temp, int[][] graph, int curr){
        if(curr == graph.length-1){
            // temp.add(curr);
            res.add(new ArrayList<>(temp));
            return;
        }
        for( int j:graph[curr]){
            temp.add(j);
            dfs(res, temp, graph, j);
            temp.remove(temp.size()-1);
        }
    }
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		Graph g = new Graph(8);
		g.addEdge(5, 2);
		g.addEdge(5, 6);
		g.addEdge(6, 0);
		g.addEdge(6, 4);
		// g.addEdge(4, 0);
		g.addEdge(0, 2); 
		g.addEdge(0, 1); 
		g.addEdge(4, 1);
		g.addEdge(1, 3); 
		g.addEdge(2, 7);
		// g.addEdge(3, 1);
		// g.addEdge(3, 5);

		// g.dfs(g);
		// System.out.println("Following is a Topological " + "sort of the given graph");
		// g.topologicalSort();

		// g.detectLoopInGraph();

		int[][] graph = {
			{0,1,1,1},
			{0,0,0,1},
			{1,1,0,0},
			{0,0,0,0}
		};

		// g.topoSort(g);

		// int n = 7, headID = 6; int[] manager = {1,2,3,4,5,6,-1}, informTime = {0,6,5,4,3,2,1};
		int n = 6, headID = 2; int[] manager = {2,2,-1,2,2,2}, informTime = {0,0,1,0,0,0};
		// g.numOfMinutes(n, headID, manager, informTime);

		int[][] oranges = {{2,1,1},{1,1,0},{0,1,1}};
		g.orangesRotting(oranges);
	}
}

