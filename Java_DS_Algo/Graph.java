package Java_DS_Algo;
import java.util.*;

/**
 * DFS INFORM TIME,
 * walls and gates
 * ROTTING, BIPARTITE, TIME TO INFORM BFS, LADDER LENGTH
 * FLIGHTS
 *  
 * TOPO SORT, SAFE STATES
 * 
 * PRIM, REMOVE GET MIN INDEX, USE PQ
 * 
 * GRAPH AND TO-DO LIST
 * 
 * 
 * 
 * TEMPLATES :
 * DFS
 * 
 * if(!map.containsKey(node)) return; MAKES ADJ LIST FASTER
 * 
 * 2 THINGS : VISITED ARR, HELPER
 * FILL VISITED
 * CHECK FOR VISITED TWICE ONCE IN DFS ONCE IN HELPER
 * MARK VISITED AT START
 * 
 *  public int findCircleNum(int[][] M) {
 *      int n = M.length;
 *      int[] visited = new int[n];
 *      Arrays.fill(visited, Integer.MAX_VALUE);          // 1
 *         
 *      int count = 0;
 *      for(int i =0; i<n; i++){
 *         if(visited[i] != Integer.MAX_VALUE) continue;  // 2
 *         dfs(i, M, visited);
 *         count++;
 *      }
 *      return count;
 *  }
 *   
 *  void dfs(int start, int[][] g,int[] visited){
 *      visited[start] = 1;                               // 3
 *      for(int i = 0; i<g.length; i++){
 *          if(visited[i] != Integer.MAX_VALUE) continue; // 4
 *          if(i!=start && g[start][i] == 1) dfs(i, g, visited);
 *      }
 *  }
 * 
 * 
 * 
 * ////////////////////////////////////////
 * DFS WITH MARKING NO OF ISLANDS
 * LOOP : COURSE SCHEDULE, TERMINAL STATES
 * 
 * 
 * ////////////////////////////////////////
 * TOPO SORT WITH LOOP TEMPLATE :
 * 
 * 1 ADD TO CURRPARENTS AND REMOVE
 * 2 ADD TO Q AT THE END
 * 
 *  visited.add(curr);
 * 	if(!map.containsKey(curr)) return;
 * 	List<Integer> list = map.get(curr);
 * 	currParents.add(curr);                 // 1
 *  for(int i : list){
 * 		if (currParents.contains(i)) {
 * 			this.loop = true;
 * 			System.out.println("loop detected");
 * 			break;
 * 		}
 * 		detectLoopUtil(i, visited, currParents, map);
 * 	}
 * 	currParents.remove(curr);              // 2
 *  q.addLast(curr);                       // 3
 * 
 * //////////////////////////////////////////////
 * BFS :
 * 
 * ADD ONLY IF UNVISITED
 * 2 THINGS : VISITED, Q
 * 
 * DISCONNECTED : IS BIPARTITE
 * 
 * 
 * 
 *  BFS TEMPLATE:
 * 1 Custom class
 * 2 adj matrix, q, visited arr(set can be used but arr can store dist)
 * 
 * int[][] g = new int[n][n];
 * 3 fill edges with -1
 * fill g from graph
 * EDGES ARE FILLED ONLY IN CASES WHERE EDGE WTS CAN BE 0, ELSE NOT REQD
 * 
 * distance will hold dist
 * distance[]
 * 4 fill distance with Max_Value
 * q.addLast(head);
 * while(q.size()!=0){
 * curr = q.removeFirst();
 * 
 * for(int i =0; i<n; i++){
 * if(
 * 5 g[curr.node][i] != -1
 * 6 distance[i] > distance[curr.node] + g[curr.node][i]
 * for FLIGHTS, USE CURR.DISTANCE, WHY? 
 * 
 * 7 update distance[i]
 * 8 add to q
 * )
 * }
 * }
 * 
 * 
 * ///////////////////////////////////////////////
 * FOR DIJKSTRA
 * SAME AS BFS, BUT USE PQUEUE
 * USE CUSTOM CLASS FOR SORTING
 * DON'T USE VISITED
 * 
 * ///////////////////////////////////////////////////
 * QUES 
 * 1 DFS (FRIEND CIRCLE, ALL PATHS FROM SRC TO TARGET, EVENTUAL SAFE STATES) 
 * 2 BFS (ROTTING ORANGES, N/W DELAY, CHEAPEST FLIGHTS, TIME TO INFORM EMP)
 * 
 * 3 DIJKSTRA (N/W DELAY)
 * 4 DAG -> TOPO SORT (COURSE SCHEDULE 1,2, CHECK LOOP USING BACTRACKING(CURRPARENTS)) 
 * 5 GRAPH COLORING 
 * 6 PRIM'S 
 * 7 BACKTRACKING (WORD LADDER, COURSE SCHEDULE 2)
 * 
 * 
 * ///////////////////////////////////////////////////////
 * GRAPH REPRESENTATION: 
 * 1 HASHSET AND HASHMAP<INTEGER, ARRAYLIST> (ADJ LIST) 
 * 2 HASHSET AND INT[][] G (ADJ MATRIX)
 * 
 * WHENEVER EDGE WEIGHTS NEED TO BE CONSIDERED USE ADJ MATRIX
 * TIME TO INFORM, SHORTEST PATH
 * 
 * FOR BIPARTITE, COLORING, USE ADJ LIST
 * 
 * 2 FOR BFS FINDING TIME OR DIST, USE ADJ MATRIX, AS EDGE WTS CAN BE 
 * ACCESSED EASILY, USE DIJKSTRA IF POSSIBLE.
 * 
 * 3 FOR TOPO SORT PREFER ADJLIST OVER MATRIX AS ENTRY EXISTS ONLY IF NODE HAS
 * OUTGOING EDGES(DAG) AND NO NEED TO ADD AND REMOVE.
 * 
 * 4 FOR FINDING LOOP IN GRAPH :
 * 
 * Cycle in undirected graphs : An undirected graph has a cycle if and only
 * if a depth-first search (DFS) finds an already visited node
 * 
 * Cycle in directed graphs : In addition to visited vertices 
 * we need to keep track of vertices currently in recursion stack 
 * of function for DFS traversal. 
 * 
 * If we reach a vertex that is already in the recursion stack, 
 * (currParents) then there is a cycle in the graph.
 * 
 * https://stackoverflow.com/questions/19113189/
 * detecting-cycles-in-a-graph-using-dfs-2-different-approaches-and-whats-the-dif
 * 
 * 5 TOPO SORT WITH CYCLE
 * TO CHECK FOR LOOP IN TPS, MAINTAIN A CURRPARENT SET, ADD AT THE START OF
 * EVERY CALL, REMOVE AT THE END REFER COURSE-SCHEDULE-ii
 * 
 * 6 DIFFERENCE B/W BFS AND DIJKSTRA, ONE USES Q, THE OTHER PQUEUE
 * 
 */
// This class represents a directed graph using adjacency
// list representation
class Graph {
	private int V; // No. of vertices
	
	int[] rows = new int[]{-1,0,0,1};
	int[] cols = new int[]{0,-1,1,0};

	// Adjacency List as ArrayList of ArrayList's
	private ArrayList<ArrayList<Integer>> adj;
	private HashMap<Integer, ArrayList<Integer>> map;

	// Constructor
	Graph(int v) {
		V = v;
		adj = new ArrayList<ArrayList<Integer>>(v);
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<Integer>());

		map = new HashMap<>();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj.get(v).add(w);
		map.putIfAbsent(v, new ArrayList<>());
		ArrayList<Integer> curr = map.getOrDefault(v, new ArrayList<>());
		curr.add(w);
		map.put(v, curr);
	}

	/////////////////////////////////////////////////////////////
	/* TEMPLATES : DFS, BFS, TOPO, LOOP, COLORING, DIJKSTRA, PRIM
	*/

	/** 
	 * DFS
	 * 1 HASHSET(VISITED)
	 * 2 ONLY ADD IF UNVISITED
	 * 3 SET.ADD AT THE START OF HELPER
	 * 4 IF NO ADJACENT NODES, RETURN
	 * 
	 * 2 CHECKS 
	 * IF(SET.CONTAINS) CONTINUE; DFSHELPER
	 * IF(SET.CONTAINS) CONTINUE;
	 * 
	*/
	void dfs() {
		// boolean[] visited = new boolean[g.V];
		HashSet<Integer> set = new HashSet<>();    // 1 
		HashMap<Integer, ArrayList<Integer>> g = new HashMap<>();

		for (int i = 0; i < V; i++) {
			if(set.contains(i)) continue;          // 2
			dfsAdjListHelper(i, set, g);
		}
	}

	void dfsAdjListHelper(int curr, HashSet<Integer> set, 
	HashMap<Integer, ArrayList<Integer>> g ) {

		set.add(curr);                             // 3
		if (!map.containsKey(curr)) return;        // 4
		ArrayList<Integer> list = g.get(curr);
		for (int i : list) {
			if (set.contains(i)) continue;          // 5
			dfsAdjListHelper(i, set, g);
		}
	}


	// fill graph with -1 to account for 0 wt edges
	List<Integer> dfsAdjMatrix(int[][] graph) {
		int n = graph.length;
		for(int[] i: graph) Arrays.fill(i, -1);

		int[] visited = new int[n];
		Arrays.fill(visited, Integer.MAX_VALUE); // like BFS
		List<Integer> res = new ArrayList<>();
		for(int i =0; i<n; i++) {
			// needed for disconnected graphs
			if(visited[i] == Integer.MAX_VALUE) continue;
			dfsAdjMatHelper(graph, i, visited, res);
		}
		System.out.println("dfs "+res);
		return res;
	}


	// visited check is once in dfs for loop
	// and once in dfs helper for loop
	// not at start
	void dfsAdjMatHelper(int[][] graph, int start, int[] visited, 
	List<Integer> res){
		visited[start] = 1; // 1
		res.add(start);
		for (int i = start; i < graph.length; i++) {
			if (i!=start 
			&& graph[start][i] != -1               // 2  
			&& visited[i] == Integer.MAX_VALUE){   // unvisited
				dfsAdjMatHelper(graph, i, visited, res);
			}
		}
	}
	
	
	/** 
	 * POINTS : 
	 * 1 RUN 2 CHECKS IN FOR BEFORE STARTING DFS AND IN ISSAFE
	 * 2 MARK VISITED IN HELPER AFTER ISSAFE
	 * 3 INCREMENT COUNT
     * 
     * CAN SPEED UP, IF POSSIBLE USING //3 USED IN GRAPH
	 * 
	 * MATRIX -> 1 AND 2
     * GRAPH -> 1 AND 3
	 * */
	// DFS TEMPLATE
	/** 
	 * for(){
	 * 	 if(visited) continue
	 *   dfs();
	 *   count++;
	 * }
	*/
    // https://leetcode.com/problems/friend-circles
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        
        int count = 0;
        for(int i =0; i<n; i++){
            if(visited[i] != Integer.MAX_VALUE) continue; // 1
            dfs(i, M, visited);
            count++;
        }
        return count;
    }
    
    void dfs(int start, int[][] g,int[] visited){
        // if(visited[start] != Integer.MAX_VALUE) return; 
        visited[start] = 1;                                // 2
        for(int i = 0; i<g.length; i++){
            if(visited[i] != Integer.MAX_VALUE) continue;  // 3 
            if(i!=start && g[start][i] == 1) dfs(i, g, visited);
        }
    }
	

	// using adj list
	public int findCircleNum1(int[][] M) {
        int n = M.length;
        int count = 0;
        
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i =0; i<n; i++){
            List<Integer> list = new ArrayList<>();
            for(int j =0; j<n; j++){
                if(M[i][j] == 1) list.add(j);
            }
            map.put(i, list);
        }
        
        for(int i =0; i<n; i++){
            if(set.contains(i)) continue;
            dfs1(i, map, set);
            count++;
        }
        return count;
    }
    
    
    void dfs1(int node, HashMap<Integer, List<Integer>> map, HashSet<Integer> set){
        set.add(node);
        if(!map.containsKey(node)) return;
        List<Integer> list = map.get(node);
        for(int i : list){
            if(set.contains(i)) continue;
            dfs1(i, map, set);
        }
    }
	
	/** 
	 * FIND TOTAL NO OF COMPONENTS AND THEN CHECK MST PROPERTY
	 * edges MUST BE n-1
	 * HERE EACH EDGE IS COUNTED TWICE, DIRECTED (0->1, 1->0)
	 * SO (edges/2).
	 * 
	 * IMP :
	 * return (edges/2)>=n-1?components-1:-1;
	 * 
	 * 
	 * SAME AS COUNTING FRIEND CIRCLES
	 * for(){
	 *   dfs();
	 *   count++;
	 * }
	*/
	// https://leetcode.com/problems/number-of-operations-to-make-network-connected
    public int makeConnected(int n, int[][] graph) {
        int components = 0; int edges = 0;
        
        HashSet<Integer> visited = new HashSet<>();
        // HashSet<Integer> currParents = new HashSet<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i = 0; i<graph.length; i++){
            List<Integer> curr = map.getOrDefault(graph[i][0],new ArrayList<>());
            curr.add(graph[i][1]);
            edges++;
            map.put(graph[i][0], curr);
            
            List<Integer> curr1 = map.getOrDefault(graph[i][1],new ArrayList<>());
            curr1.add(graph[i][0]);
            edges++;
            map.put(graph[i][1], curr1);
        }
    
        for(int i =0; i<n; i++){
            if(visited.contains(i)) continue; // 1 check visited
            dfs(i, map, visited);
            components++;
        }
       
        return (edges/2)>=n-1?components-1:-1;
    }
    
    void dfs(int node, HashMap<Integer, List<Integer>> map, HashSet<Integer> visited){
        visited.add(node);
        if(!map.containsKey(node)) return;
        List<Integer> curr = map.get(node);
        for(int i =0; i<curr.size(); i++){
            if(visited.contains(curr.get(i))) continue; // 3 check visited
            dfs(curr.get(i), map, visited);
        }
	}
	

	/**
	 * POINTS : 
	 * 1 CREATE A GRAPH USING ADJ LIST, NO NEED TO HOLD EDGE WEIGHTS 
	 * AS INFORMTIME CAN FETCH THAT,
	 * HENCE DIDN'T USE ADJ MATRIX
	 * 
	 * 2 DFS 
	 * if (no neighbours) return 0;
	 * 
	 * 3 
	 * 
	 * 4 USE DP TO STORE VALUE IN ANOTHER MAP (TIMEHOLDER)
	 * 
	 */
	// https://leetcode.com/problems/time-needed-to-inform-all-employees
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        HashMap<Integer, List<Integer>> g = new HashMap<>();
        for(int i =0; i<n; i++){
            if(manager[i] == -1) continue;
            List<Integer> list = g.getOrDefault(manager[i], new ArrayList<>());
            list.add(i);
            g.put(manager[i], list);   
        }
        int[] dp = new int[n];
        
        return dfs(headID, g, dp, manager, informTime);
    }
	
	// like palindrome partitioning
    int dfs(int src, HashMap<Integer, List<Integer>> g, int[] dp, int[] manager, int[] informTime){
        
        if(dp[src]!=0) return dp[src];
        if(!g.containsKey(src)) return 0;
        // System.out.println("src "+src);
        int max = 0; // 1
        List<Integer> curr = g.get(src);
		
		// tree structure ,so max gives the time to inform the farthest emp
		// also no conflict so, we don't compare
        for(int i : curr) {
			max = Math.max(max, dfs(i, g, dp, manager, informTime));
		}
        dp[src] = max + informTime[src];
        // System.out.println(src +" "+dp[src]);
        return dp[src];
    }
	
	
	// https://leetcode.com/problems/path-with-maximum-probability/


	////////////////////////////////////// TOPOLOGICAL SORT

	/**
	 * POINTS : 
	 * 1 RUN A FOR LOOP, IF NOT VISITED CALL TPS 
	 * 2 IN TPS ADD TO VISITED
	 * 3 CHECK IF VERTEX HAS ADJACENT NODES 
	 * 4 IF YES, THEN ITERATE 
	 * 5 IF VISITED CONTAINS VERTEX, CONTINUE 
	 * 6 ELSE CALL TPS WITH i 
	 * 7 ADD TO Q (RES)
	 * 
	 * VISITED CHECK IS DONE TWICE, ONCE IN MAIN FN, ONCE IN TPS
	 * 
	 * TO CHECK FOR LOOP IN TPS, MAINTAIN A CURRPARENT SET, 
	 * ADD AT THE START OF EVERY CALL, REMOVE AT THE END;
	 * REFER COURSE-SCHEDULE-ii
	 */
	List<Integer> tpsStart(int N) {
		// iterate over the graph in argument to create hashmap graph
		HashMap<Integer, List<Integer>> g = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		Deque<Integer> q = new LinkedList<>();
		int n = N;
		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (!set.contains(i)) tps(i, g, set, q);
		}

		int s = q.size();
		for (int i = 0; i < s; i++) res.add(q.removeLast());

		return res;
	}

	void tps(int curr, HashMap<Integer, List<Integer>> map, HashSet<Integer> set, 
	Deque<Integer> q) {
		set.add(curr);
		if (!map.containsKey(curr)) return;
		List<Integer> list = map.get(curr);
		for (int i : list) {
			if (set.contains(i)) continue;
			tps(i, map, set, q);
		}
		q.addLast(curr);
	}

	// https://leetcode.com/problems/reconstruct-itinerary/
    Deque<String> q = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        
        for(int i =0; i<tickets.size(); i++){
            PriorityQueue<String> curr = 
                map.getOrDefault(tickets.get(i).get(0), new PriorityQueue<>());
            curr.add(tickets.get(i).get(1));
            map.put(tickets.get(i).get(0), curr);
        }
        
        dfs("JFK", map);
        List<String> res = new ArrayList<>();
        while(q.size()!=0){
            res.add(q.removeLast());
        }
        return res;
    }
    
    
    void dfs(String start, HashMap<String, PriorityQueue<String>> map){
        if(!map.containsKey(start)) {
            q.addLast(start);
            return;
        }
        PriorityQueue<String> curr = map.get(start);
        while(curr.size()!=0){
            dfs(curr.remove(), map);
        }
        q.addLast(start);
	}
	
	/**
	 * LOOP
	 * 1 backtracking
	 * 2 2 HASHSETS
	 * 
	 * 
	 * /** it's almost impossible to detect loop using ADJ MATRIX 
	 * AND WITHOUT USING KAHN'S ALGO (KEEP INDEGREE COUNT). 
	 * FOR LOOP USE BACKTRACKING APPROACH FOR DAG 	 
	 * */

	// LOOP IN UNDIRECTED (DFS)
	boolean loop = false;

	boolean detectLoopUndirected(){
		HashSet<Integer> visited = new HashSet<>();
		HashMap<Integer, List<Integer>> map = new HashMap<>(); 
		for (int i = 0; i < this.V; i++) {
			if (loop) return loop;
			if (!visited.contains(i)) detectLoopUHelper(i, visited, map);
		}
		return loop;
	} 

	void detectLoopUHelper(int curr, HashSet<Integer> visited, HashMap<Integer, List<Integer>> map){
		visited.add(curr);
		if (!map.containsKey(curr)) return;
		List<Integer> list = map.get(curr);
		for(int i : list){
			if(visited.contains(i)) {
				loop = true; 
				break;
			}
			detectLoopUHelper(curr, visited, map);
		}
	}


	/**  
	 * DETECT LOOP IN DAG (BACTRACKING)
	 * 
	 * 1 CREATE GRAPH 
	 * 2 USE 2 HASHSETS ONE FOR VISITED, ONE FOR CURR PARENTS 
	 * 3 ADD TO VISITED AT START,
	 * 
	 * 4 2 CHECKS : 
	 * ONE FOR VISITED(SET.CONTAINS) 
	 * ONE FOR CURRPARENTS (LOOP) 
	 * 6 CURRPARENTS.ADD(), CURRPARENTS.REMOVE()
	 * 7 ADD TO Q AT END 
	 * 
	 * TO DETECT LOOP WE KEEP OF TRACK OF CURRENT PARENTS IN EVERY ITERATION
	 * AND REMOVE AT THE END OF THE ITERATION
	 * 
	 * 2 HASHSETS ARE USED. ONE IS TO DETECT THE VERTEX IS VISITED OR NOT
	 * CURRPARENTS HOLDS THE CURRENTLY VISITED NODES IN THIS ITERATION,
	 * IT IS CLEARED AT THE END.
	 * VISITED SAME AS DFS
	 * 
	 * */
	boolean detectLoopDirected() {
		// int[] visited = new int[this.V];
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		HashSet<Integer> visited = new HashSet<>();
		HashSet<Integer> currParents = new HashSet<>();

		for (int i = 0; i < this.V; i++) {
			if (loop) return loop;
			if (!visited.contains(i)) detectLoopUtil(i, visited, currParents, map);
		}
		System.out.println("loop in list " + loop);
		return loop;
	}

	void detectLoopUtil(int curr, HashSet<Integer> visited, HashSet<Integer> currParents, 
	HashMap<Integer, List<Integer>> map) {
		visited.add(curr);
		if(!map.containsKey(curr)) return;
		List<Integer> list = map.get(curr);
		currParents.add(curr);
		for(int i : list){
			if (currParents.contains(i)) {
				this.loop = true;
				System.out.println("loop detected");
				break;
			}
			detectLoopUtil(i, visited, currParents, map);
		}
		currParents.remove(curr);
	}


	/**
	 * FOR TOPO SORT USE ADJ LIST AS 
	 * 
	 * 1 IT HELPS WHEN ANY NODE DOESN'T HAVE ANY
	 * OUTGOING EDGE, THE MAP.CONTAINS CHECK HELPS REMOVE IT QUICKER 
	 * AND NOT ITERATE AND ADD UNNECESSARILY. 
	 * 
	 * 2 ALSO IT IS FASTER AS IN ADJMATRIX WE ITERATE OVER THE MATRIX LENGTH
	 * FOR EACH NODE
	 * 
	 * currParents IS IMP, visited IS FOR SPEED UP
	 * currParents is like backtracking, 
	 * add at the start and remove at the end.
	 * 
	 * STEPS : 
	 * 1 USE CURRPARENTS, DON'T ADD IN MAIN, ALWAYS ADD TO currParents IN DFS
	 * 2 ADD, LOOP CHECK, DFS AND REMOVE
	 * ALDR
	 * 
	 * same as dfs
	 * for(){
	 *   if(visited) continue;
	 * 	 helper()
	 * }
	 * 
	 * helper(){
	 *   visited.add();
	 *   if(!map.contains()) return;
	 *   currparents.add()
	 *   for(){
	 * 		if(visited.contains(i)) continue;
	 *   }
	 *   currParents.remove();
	 *   q.addLast
	 * 
	 * }
	 * 
	 * HOW VISITED WORKS?
	 * visited keeps track of nodes that have been visited, while
	 * currParents keeps track of nodes that are preceding the ith node
	 * in this dfs.
	 * 
	 */
	// https://leetcode.com/problems/course-schedule/
    boolean isLoop = false;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        HashMap<Integer, List<Integer>> g = new HashMap<>();
        
        for(int i =0; i<n; i++){
            List<Integer> list = g.getOrDefault(prerequisites[i][1], new ArrayList<>());
            list.add(prerequisites[i][0]);
            g.put(prerequisites[i][1], list);
        }
        
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> currParents = new HashSet<>();
        for(HashMap.Entry<Integer, List<Integer>> e : g.entrySet()){
            if(isLoop) return false;
            currParents.clear();
            if(visited.contains(e.getKey())) continue;
            tps(e.getKey(), g, visited, currParents);
                        
        }
        return true;
    }
    
    void tps(int src, HashMap<Integer, List<Integer>> g, HashSet<Integer> visited,
    HashSet<Integer> currParents){
		visited.add(src);
		
        if(!g.containsKey(src)) return;
		List<Integer> list = g.get(src);
		
        currParents.add(src); // 
        for(int i : list){
            if(currParents.contains(i)) {
                isLoop = true;
                return;
            }
            // the node has been visited but has a diff set of children
            if(visited.contains(i)) continue; // 
            tps(i, g, visited, currParents);
        }
        currParents.remove(src); // 
    }


	// https://stackoverflow.com/questions/38578995/how-to-cast-object-to-int-java
	// USING ADJ MATRIX doesn't work for all cases

	/**
	 * IMP ADD TO Q AT END
	 * 
	 * BASIC DAG WITH LOOP TEMPLATE 
	 * 1 CREATE GRAPH 
	 * 2 ADD TO Q
	 */
	// https://leetcode.com/problems/course-schedule-ii/
	boolean isLoop2 = false;
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
	    HashSet<Integer> currParents = new HashSet<>();
		Deque<Integer> q = new LinkedList<>();

        // 3, [[1,0]] chk this case
		if (numCourses == 2 && prerequisites.length == 1)
			return new int[] { prerequisites[0][1], prerequisites[0][0] };

		for (int i = 0; i < prerequisites.length; i++) {
			// create adj list graph using hashmap
			ArrayList<Integer> curr = 
                map.getOrDefault(prerequisites[i][1], new ArrayList<>());
			curr.add(prerequisites[i][0]);
			map.put(prerequisites[i][1], curr);
		}

		for (int i = 0; i < numCourses; i++) {
			if (!set.contains(i)) tps(i, map, set, q, currParents);
		}
        
        if (isLoop2) return new int[] {};
        
        int i = 0;
		int[] res = new int[q.size()];
        while(q.size()!=0) res[i++] = q.removeLast();
		// Utility.print1DMatrix(resCourse1);
		return res;
	}


	void tps(int curr, HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> set, 
	Deque<Integer> q, HashSet<Integer> currParents) {
		set.add(curr); // 1
        //smacps
		if (map.containsKey(curr)) { // 2
            
			currParents.add(curr); // 3 add
			ArrayList<Integer> list = map.get(curr);
			for (int i : list) {
				if (currParents.contains(i)) { // 4
					isLoop2 = true;
					return;
				}
                
				if (set.contains(i)) continue; // 5
				tps(i, map, set, q, currParents); // 6
			}
			currParents.remove(curr); // 7 remove
		}
        q.addLast(curr);
	}

	/** 
	 * POINTS : 
	 * 1 currParents is the most imp set, visited is just for speeding up
	 * 2 currParents CHECKS IF THE NODE HAS BEEN SEN PREVIOUSLY, IF YES
	 * THEN A LOOP EXISTS
	 * 3 IF A NODE HAS LOOP TO ITSELF, IT IS UNSAFE, RMEOVE FROM RES
	 * 4 ALSO IF UNSAFE NODE IS REACHED, ADD ALL THE PARENTS IN THIS DFS
	 * TO UNSAFE LIST.
	 * 
	 */

	// FAILS FOR 
	// [[4,9],[3,5,7],[0,3,4,5,6,8],[7,8,9],[5,6,7,8],[6,7,8,9],[7,9],[8,9],[9],[]]
	// PASSES 80/111
	// https://leetcode.com/problems/find-eventual-safe-states/
    HashSet<Integer> unsafe = new HashSet<>();
    public List<Integer> eventualSafeNodes1(int[][] graph) {
        HashMap<Integer, List<Integer>> g = new HashMap<>();
    	List<Integer> res = new ArrayList<>();
        
        int n = graph.length;
        for(int i=0; i<n; i++){
            if(graph[i].length == 0) {
                res.add(i);
                continue;
            }
            List<Integer> list = g.getOrDefault(i, new ArrayList<>());
            
            for(int j =0; j<graph[i].length; j++){
                if(graph[i] == graph[j]) unsafe.add(i);
                list.add(graph[i][j]);
            }
            g.put(i, list); 
            res.add(i);
        }
        
        System.out.println(res);
        // topo
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> currParents = new HashSet<>();
        for(int i =0; i<n; i++){
            // if(res.contains(i)) continue;
            // if(!visited.contains(i))
            currParents.clear();
                tps(i, g, visited, currParents ,res);
        }
        return res;
    }
    
	void tps(int start, HashMap<Integer, List<Integer>> g, HashSet<Integer> visited, 
	HashSet<Integer> currParents, List<Integer> res){
        // if(visited.contains(start)) return;
        // visited.add(start);
        if(!g.containsKey(start)) return;
        currParents.add(start);
        
        List<Integer> list = g.get(start);
        for(int i :list){
            System.out.println(i+" "+unsafe);
            if(unsafe.contains(i)){
                for(int k : currParents){
                    res.remove(Integer.valueOf(k));
                }
                return;
            }
            if(currParents.contains(i)){
                res.remove(Integer.valueOf(i));
                unsafe.add(i);
                return;
            }
            if(res.size() ==9)System.out.println(res);
            tps(i, g, visited, currParents, res);
        }
        currParents.remove(start);
	}
	
	// https://leetcode.com/problems/redundant-connection-ii/ CAN BFS BE USED?
	// https://leetcode.com/problems/critical-connections-in-a-network/
	// https://leetcode.com/problems/minimum-height-trees/

	
	/////////////////////////////////// BFS

	/**
	 * BFS 
	 * USING Q
	 * 1 CUSTOM CLAS TO HOLD INDEX OR NODE 
	 * 2 CREATE MAP FROM A MATRIX 
	 * 3 FILL WITH EDGE WT -1 IF 0 EDGE WTS ARE PRESENT
	 * 4 CREATE A VISITED ARRAY OF SIZE n+1
	 * 5 VISITED[STARTING INDEX] = 1
	 * 6 ADD STARTING VERTEX, AND RUN LOOP FOR Q EMPTY 
	 * 7 MAINTAIN A GENERIC COUNTER
	 * 8 IF UNVISITED, MARK VISITED
	 * 
	 */

	/** 
	 * IMP FINDINGS :
	 * 
	 * BFS CAN WORK WITH LOOPS, NEED TO MAINTAIN VISITED SET
	 * BUT WON'T PROVIDE OPTIMUM DIST
	 * 
	 * WHEN TO USE BFS TO FIND DISTANCE?
	 * BFS CAN GIVE THE SHORTEST TIME ONLY IF THERE ARE NO LOOPS
	 * AND VISITED SET ISN'T USED, BECAUSE THE DISTANCES WON'T BE 
	 * UPDATED EVEN IF WE FIND A SHORTER DIST (if we don't revisit).
	 * 
	 * 
     */
	/**
	 * BFS TEMPLATE:
	 * 
	 * 1 Custom class to hold indexes
	 * 2 adj matrix, q, visited arr(set can be used)
	 * 
	 * int[][] g = new int[n][n];
	 * 3 fill edges with -1
	 * fill g from graph
	 * EDGES ARE FILLED ONLY IN CASES WHERE EDGE WTS CAN BE 0
	 * 
	 * visited will check
	 * 4 visited[], no distance array
	 * 
	 * q.addLast(head);
	 * while(q.size()!=0){
	 * 5 use q size
	 * int size = q.size();
	 * 
	 * 6 for(i =0; i<size; i++)
	 * curr = q.removeFirst();
	 * 
	 * for(int i =0; i<n; i++){
	 * if(
	 * 7 g[curr.node][i] != -1
	 * 8 visited[i] = 1
	 * for FLIGHTS, USE CURR.DISTANCE, WHY? 
	 * 
	 * 9 add to q
	 * 10 use generic counter time++
	 * )
	 * }
	 * }
	 * 
	 * 
	*/


	// BFS
    // use custom class
    // can store distance in class or visited array
    // but we have to update the matrix itself, so custom class can hold dist
    /** 
     * POINTS :
     * 
     * 1 ALWAYS USE Q SIZE FOR BFS, HELPS KEEP TRACK OF DISTANCE
     * UNIFORMLY, SAME AS IN WORD LADDER
     * 
     * 2 ONLY CHECK IF UNVISITED, NO NEED TO CHECK FOR DISTANCE
     * BFS SO NO CONFLICT OF FINDING A SHORTER PATH
     * 
     * 3 UPDATE AFTER ISSAFE, NOT AFTER REMOVAL
     * 
     */
    class Wall{
        int x; int y; int dist;
        Wall(int x, int y, int d){
            this.x = x; 
            this.y = y;
            this.dist = d;
        }
    }
    void wallsAndGatesBFS(int[][] matrix){
        int m = matrix.length; int n = matrix[0].length;
        Deque<Wall> q = new LinkedList<>();
        int distance = 0;

        for(int i =0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 0) q.addLast(new Wall(i,j,0));
            }
        }

        while(q.size()!=0){
            int size = q.size();
           
            for(int i=0; i<size; i++){
                Wall curr = q.removeFirst();
                System.out.println(curr.x+" "+curr.y+" "+curr.dist);

                for(int k =0; k<rows.length; k++){
                    int newX = curr.x + rows[k];
                    int newY = curr.y + cols[k];
                    if(isSafeWallBFS(matrix, newX, newY)){
                        matrix[newX][newY] = distance+1;
                        q.addLast(new Wall(newX, newY, distance+1));
                    }
                }
            }
            distance++;
        }
        System.out.println("bfs wall : ");
        Utility.printMatrix(matrix);
    }

    boolean isSafeWallBFS(int[][] matrix, int r, int c){
        if(r>=0 && r<matrix.length
        && c>=0 && c<matrix[0].length
        // if unvisited
        && matrix[r][c] == Integer.MAX_VALUE) return true;
        return false;
    }


	/**
	 * NORMAL BFS
	 * 
	 * POINTS : 
	 * 1 ONLY VISITED ARRAY
	 * 2 ISSAFE ONLY CHECKS IF VISITED OR NOT
	 * 3 AFTER ISSAFE, MARK VISITED AND ADD TO Q.
	 * 
	 * WHY DOES THIS WORK? ONLY UPDATING MAX?
	 * WHAT OIF THERE IS A SHORTER PATH?
	 * 
	 * IN BFS THERE CAN NEVER BE A CONFLICT OF SHORTER PATH.
	 * AS ALL NODES ARE ADDED TO Q IN SORTED ORDER.
	 * SO NODES WHICH ARE CLOSER WILL UPDATE THEIR NEIGHBOURS
	 * 
	 * IN DFS THERE CAN BE CONFLICT, BUT HERE ALL EQUIDISTANT
	 * NOES ARE UPDATED BEFORE ANY NODE WITH GREATER DISTANCE.
	 * 
	 * we add alls 2s, so all 2s will update neighbouring 1s at 
	 * teh same time. so there won't be a case where a 1 farther
	 * is updated first.
	 * 
	 */
	// https://leetcode.com/problems/rotting-oranges/
	// NORMAL BFS
	// [[2,2],[1,1],[0,0],[2,0]]
    // what if rotting from 2 sources? isSafe can handle that    
    // in bfs, only visited, a generic counter
    class Orange1{
        int x; int y; 
        Orange1(int x, int y){
            this.x = x; this.y = y;
        }
    }
	public int orangesRotting(int[][] grid) {
		Deque<Orange1> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] visited = new int[m][n];
            
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) q.add(new Orange1(i, j));
			}
		}
        
        int time = -1; // 1

		int[] rows = { -1, 0, 0, 1 };
		int[] cols = { 0, -1, 1, 0 };
        
		while (q.size() != 0) {
            int size = q.size();
            for(int i =0; i < size; i++){
                Orange1 curr = q.removeFirst();
                // System.out.println(curr.x+" "+curr.y);
                for (int k = 0; k < rows.length; k++) {
                    int newX = curr.x + rows[k];
                    int newY = curr.y + cols[k];
                    if (isSafeOrange(grid, newX, newY, visited)) {
                        grid[newX][newY] = 2;
                        visited[newX][newY] = time+1; 
                        q.addLast(new Orange1(newX, newY));
                    }
                }
			}
			// after a complete iteration
            time++; // 2
            // System.out.println("time "+time);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) return -1;
			}
		}
		return time<0?0:time; // 3
	}

	boolean isSafeOrange(int[][] grid, int row, int col, int[][] visited) {
		if (row >= 0 && row < grid.length 
		&& col >= 0 && col < grid[0].length
		// only 1 can be rotted further 
		&& grid[row][col] == 1 
		// only unvisited
        && visited[row][col] == 0) return true;
		return false;
	}

    // [[2,2],[1,1],[0,0],[2,0]]
    // https://leetcode.com/problems/rotting-oranges
	// ONLY USING A COUNTER, NO VISTED ARRAY AND CUSTOM CLASS
	public int orangesRotting1(int[][] grid) {
		Deque<Orange1> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
                    
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) q.add(new Orange1(i, j));
			}
		}
        
        int time = -1; // 1

		int[] rows = { -1, 0, 0, 1 };
		int[] cols = { 0, -1, 1, 0 };
        
		while (q.size() != 0) {
            int size = q.size();
            for(int i =0; i < size; i++){
                Orange1 curr = q.removeFirst();
                // System.out.println(curr.x+" "+curr.y);
                for (int k = 0; k < rows.length; k++) {
                    int newX = curr.x + rows[k];
                    int newY = curr.y + cols[k];
                    if (isSafeOrange1(grid, newX, newY)) {
                        grid[newX][newY] = 2;
                        q.addLast(new Orange1(newX, newY));
                    }
                }
            }
            time++;
            // System.out.println("time "+time);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) return -1;
			}
		}
		return time<0?0:time;
	}

	boolean isSafeOrange1(int[][] grid, int row, int col) {
		if (row >= 0 && row < grid.length 
		&& col >= 0 && col < grid[0].length 
		// only unvisited
		&& grid[row][col] == 1 ) return true;
		return false;
	}

	// always keep track of q size in BFS
	// https://leetcode.com/problems/shortest-path-in-binary-matrix
    class Node{
        int row, col, dist;
        Node(int a, int b, int c){
            this.row = a; this.col = b; this.dist = c;
        }
    }
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        Deque<Node> q = new LinkedList<>();
        int m = grid.length-1, n = grid[0].length-1;
        
        if(grid[0][0] !=0) return -1;
        if(grid[m][n] !=0) return -1;
        if(m == 0 && n==0) return 1;
        
        q.addLast(new Node(0, 0, 1));
    
        int[] rows = {-1,0,1,1,1,0,-1,-1};
        int[] cols = {1,1,1,0,-1,-1,-1,0};
        
        while(q.size()!=0){
            int size = q.size();
            for(int i =0; i<size; i++){
                Node curr = q.removeFirst();
                // System.out.println(curr.row+" "+curr.col);
                for(int j =0; j<8; j++){
                    int x = curr.row + rows[j];
                    int y = curr.col + cols[j];
                    if(x == m && y ==n) return curr.dist+1;
                    
                    if(x>=0 && x<=m && y>=0 && y<= n && grid[x][y] == 0){
                        q.addLast(new Node(x, y, curr.dist+1));
                        grid[x][y] = 2;
                    }                    
                }
            }
        }
        return -1;
    }

	/** 
	 * NORMAL BFS
	 * 1 AS GRAPH IS DISCONNECTED, RUN BFS FOR ALL NODES
	 * 2 MAINTAIN A VISITED ARRAY
	 * 3 START BY ASSIGNING COLOR 1 AND THEN CHECK IF ADJACENT NODES HAVE 
	 * SAME COLOR. 
	 * IF DIFFERENT, CONTINUE.
	 * IF NOT VISITED, ASSIGN visited[list.get(i)] = color==1?2:1;
	*/
	// https://leetcode.com/problems/is-graph-bipartite
    boolean isBipartite = true;
    public boolean isBipartite(int[][] graph) {
        // BFS
        // no edge wt worries, so adj list
        // disconnected graph
        // so run BFS for all nodes
        int n = graph.length;
        HashMap<Integer, List<Integer>> g = new HashMap<>();
        for(int i = 0; i<n; i++){
            List<Integer> list = g.getOrDefault(i, new ArrayList<>());
            for(int j = 0; j<graph[i].length; j++){
                list.add(graph[i][j]);
            }
            g.put(i, list);
        }
        
        int[] visited = new int[n];
         // 1 and 2; 0 unvisited
        Deque<Integer> q = new LinkedList<>();
        
        for(int i =0; i<n; i++) {
            // bfs only for unvisited
            if(visited[i]==0 && graph[i].length>0) {
                // it is unvisited, so disconnected
                // start from color 1 same as 0th node
                visited[i] = 1;
                q.addLast(i);
                bfsHelper(q, g, visited);
            }
        }
        return isBipartite;
    }
    
    void bfsHelper(Deque<Integer> q, HashMap<Integer, List<Integer>> g, int[] visited){
        while(q.size()!=0){
            int curr = q.removeFirst();
            int color = visited[curr];
            // System.out.println(curr+" "+color);
            if(!g.containsKey(curr)) continue;
            List<Integer> list = g.get(curr);
            for(int i = 0; i<list.size(); i++){
				// is safe check
                if(visited[list.get(i)] == color) {
                    isBipartite = false;
                    return;
                }
				if(visited[list.get(i)]!=0) continue;
				// update
                visited[list.get(i)] = color==1?2:1;
                q.addLast(list.get(i));
            }
        }
	}
	

	/** 
	 * POINTS : 
	 * 1 HERE BFS IS USED, BUT FOR ALL STRINGS IN THE QUEUE AT A TIME, 
	 * THE NEXT STRING IS FOUND AND STORED. SO MIN DIST CAN BE FOUND
	 * WHENEVER THE END WORD COMES AS WE INCREMENTING BY UNIT DIST FOR ALL 
	 * TRANSFORMATIONS.
	 * 
	 * 2 for(char c ='a'; c<='z'; c++) curr[i] == c
	 * create a new string and check if it exist in the set
	 * 
	 * 3 CHANGE BACK THE STRING char holder = curr[i]; curr[i] = holder;
	 * 
	 * 4 ONCE AN ITERATION IS DONE, distance++;
	 * 
	 */	
	// https://leetcode.com/problems/word-ladder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        HashSet<String> used = new HashSet<>(); // 1
        HashSet<String> unused = new HashSet<>(); // 2
        Deque<String> q = new LinkedList<>(); // 3
        int distance = 1; // 4
        
        for(String str : wordList) unused.add(str); // 5
        used.add(beginWord);
        q.addLast(beginWord);
        
        while(q.size()!=0){
            int size = q.size(); // 6
            for(int i =0; i<size; i++){
                String curr = q.removeFirst();
                // String is immutable, convert to char array
                char[] ch = curr.toCharArray(); // 7
                
                for(int j = 0; j<curr.length(); j++){
                    
                    char temp = ch[j];
                    for(char c = 'a'; c<='z'; c++){ // 8
                        ch[j] = c; // 7
                        String created = String.valueOf(ch);
                        if(created.equals(endWord)) { // 9
                            // System.out.println(created);
                            // System.out.println(created.equals(endWord));
                            return distance+1;
                        }
                        if(used.contains(created)) continue; // 10
                        //if unused
                        if(unused.contains(created)){ // 11
                            // System.out.println("in here "+ch);
                            used.add(created); // 9
                            unused.remove(created);
                            q.addLast(created);
                        }
                    }
                    ch[j] = temp; // 12
                }
            }
            distance++;
        }
        
        return 0;
    }
	
	// https://www.hackerearth.com/submission/48715922/
	// i think it doesn't work
	// https://www.hackerearth.com/problem/algorithm/strategic-warehouse-placements/description/
	void wareHousePlacement(){	
		Scanner s = new Scanner(System.in);
        // System.out.println("enter N and M");
        int n = s.nextInt();
        int m = s.nextInt();

        HashMap<Integer, ArrayList<Integer>> mapW = new HashMap<>();

        for(int i =0; i<m; i++){
            // System.out.println("enter node pairs");
            int a = s.nextInt(); int b = s.nextInt();
            ArrayList<Integer> curr = map.getOrDefault(a, new ArrayList<Integer>());
            curr.add(b);
            map.put(a, curr);
            curr = map.getOrDefault(b, new ArrayList<>());
            curr.add(a);
            map.put(b, curr);
        }
        

        PriorityQueue<Integer> pq = 
        new PriorityQueue<>((x, y)->map.get(y).size() - map.get(x).size());

        for(HashMap.Entry<Integer, ArrayList<Integer>> e :map.entrySet()){
            pq.add(e.getKey());
        }

        HashSet<Integer> visited = new HashSet<>(); int counter = 0;
        while(pq.size()!=0){
            int node = pq.remove();
            if(visited.contains(node)) continue;
            visited.add(node);
            List<Integer> list = map.get(node);
            for(int i =0; i<list.size(); i++){
                visited.add(list.get(i));
            }
            counter++;
        }

        System.out.println(counter);
	}


	// https://leetcode.com/problems/network-delay-time/
    // bfs with loops
    // visited or distance lesser?
    class Node{
        int vertex, dist;
        
        Node(int v, int d){
            this.vertex =v; 
            this.dist = d;
        }
    }
    
    
    public int networkDelayTimeBFS(int[][] times, int N, int K) {
        int n = N+1;
        int[] distance = new int[n];
        int[] visited = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        int[][] g = new int[n][n];
        for(int i = 0; i<n; i++) Arrays.fill(g[i], -1);
        
        for(int i =0; i<times.length; i++){
            g[times[i][0]][times[i][1]] = times[i][2];
        }
        Deque<Node> q = new LinkedList<>();
        int max =0;
        q.addLast(new Node(K, 0));
        distance[K] = 0;
        visited[K] = 1;
        
        while(q.size()!=0){
            int size = q.size();
            for(int i =0; i<size; i++){
                Node curr = q.removeFirst();
                // System.out.println(curr.vertex + " "+ curr.dist);
                for(int j = 0; j<n; j++){
                    if(g[curr.vertex][j]!=-1 //&& visited[j] == 0
                      && distance[j]>curr.dist+g[curr.vertex][j]){
                        visited[j] = 1;
                        distance[j] = curr.dist+g[curr.vertex][j];
                        q.addLast(new Node(j, distance[j])); 
                    }
                }
            }
        }
        
        for(int i = 1; i<n; i++){
            if(distance[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, distance[i]);
        }
        return max;
    }

	// https://leetcode.com/problems/possible-bipartition/
	
	// https://www.techiedelight.com/print-k-colorable-configurations-graph-vertex-coloring-graph


	///////////////////////////////// DIJKSTRA

	/** 
	 * BFS VS DIJKSTRA
	 * 1 QUEUE VS PQUEUE
	 * 2 VISITED VS DISTANCE
	 * 3 Q SIZE AND AN EXTRA LOOP WITH GENERIC COUNTER
	 * IN DIJKSTRA, WE CHECK FOR SHORTER DIST AND IF EDGE EXISTS
	 * (PRUNING)
	 * 
	*/ 

	/** 
	 * 
	 * ///////////
	 * https://www.quora.com/In-Dijkstra-would-using-a-visited-array
	 * -to-avoid-entering-closed-nodes-again-lead-to-better-performance
	 * 
	 * IN DIJKSTRA WE DON'T USE SET TO MAINTAIN VISITED. WHY?
	 * BECAUSE THE VISITED NODE IS THE SHORTEST AND ALL OTHER NODES
	 * WILL BE HAVING GREATER VALUE.
	 * 
	 * Re-entering won’t change the solution precisely for the reason that 
	 * if you are re-entering the node, the value with which it was 
	 * inserted is larger than current (already computed in the first visit) 
	 * value of dist[u]. Hence additional visited[u] is not needed.
	 * 
	 * 
	 * https://cs.stackexchange.com/questions/10047/
	 * is-dijkstras-algorithm-just-bfs-with-a-priority-queue 
	 */ ///////////
	
	/**
	 *  * DIJKSTRA 
	 * STEPS:
	 * 1 USE DISTANCE ARRAY
	 * INITIALISE TO INF
	 * 2 DISTANCE[SRC] = 0;
	 * 3 REMOVE FROM Q
	 * IF(!g.containsKey(src)) continue;
	 * 
	 * 5 if(distance[i] > distance[curr.node] + graph[curr.node][i])
	 * UPDATE AND ADD TO Q
	 * 
	 */ 
	// graph src, dest, edge wt : [[2,1,1],[2,3,1],[3,4,1]]
	class Shortest {
		int node, time;
		Shortest(int n, int t) {
			this.node = n;
			this.time = t;
		}
	}
	int dijkstra(int[][] graph, int head, int n) {
		int[][] g = new int[n][n];
		for(int[] i :g) Arrays.fill(i, Integer.MAX_VALUE);

		for(int[] i: graph) g[i[0]][i[1]] = i[2];

		int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE); // 1
        distance[head] = 0;

        PriorityQueue<Shortest> pq = new PriorityQueue<>((x,y)->x.time - y.time);
		pq.add(new Shortest(head, 0)); // 2

		while(pq.size()!=0){
			Shortest curr = pq.remove();

			for(int i =0; i<n; i++){
				// 3
				if(distance[i] > distance[curr.node] + graph[curr.node][i]){
					distance[i] = distance[curr.node] + graph[curr.node][i];
					pq.add(new Shortest(i, distance[i]));
				}
			}

		}
		int maxTime = 0;
        for(int i=1; i<n; i++){
			// if unvisited
            if(distance[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, distance[i]);
        }
        return maxTime;
	}


	/** 
	 * WHY NOT BFS? BECAUSE BFS CAN'T GUARANTEE SHORTEST TIME AS IT
	 * DOESN'T MAINTAIN DISTANCES, ONLY VISITED ARRAY.
	 * SO SHORTER PATH MIGHT BE FOUND BUT NOT UPATED AS
	 * NODE HAS ALREADY BEEN VISITED
	 * 
	 * MAJOR PINTS OF FAILURE:
	 * 1 0 WT EDGES, SO g[curr.node][i]!=0 FAILS
	 * 
	 * 2 (x,y)->distance[x] - distance[y] FAILS AS ONCE ADDED TO PQ, IF DISTANCE IS UPDATED,
	 * THE NODES AREN'T REARRANGED
	 * int[] visited = new int[]{200, 1, 30};
	 * PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->distance[x] - distance[y]);
	 * pq.add(0);
	 * pq.add(1);
	 * pq.add(2);
	 * System.out.println(pq); // 1
	 * visited[1] = 1000;
	 * System.out.println(pq); // 1
	 * 
	 *
	 */
	// use dijkstra as single source shortest path, not bfs
	// https://leetcode.com/problems/network-delay-time/
	class Network {
		int node, time;
		Network(int n, int t) {
			this.node = n;
			this.time = t;
		}
	}

    public int networkDelayTime(int[][] times, int N, int K) {
        int n = N+1;
        int[][] g = new int[n][n];
        
        for(int[] i : g) Arrays.fill(i, -1); // 1
        
        for(int[] i : times) g[i[0]][i[1]] = i[2];
        
		int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE); // 2
        distance[K] = 0;

		// time is needed for pq min heap property
        PriorityQueue<Network> pq = new PriorityQueue<>((x,y)->x.time - y.time);
		pq.add(new Network(K, 0));
		
        
        while(pq.size()!=0){
			Network curr = pq.remove();
			// no visited business, update neighbours if egde present and dist greater
            for(int i = 1; i<n; i++){
				if(curr.node!=i 
				&& g[curr.node][i]!=-1 // 0 wt edges
				&& distance[i] > distance[curr.node] + g[curr.node][i]){
					distance[i] = distance[curr.node] + g[curr.node][i];
                    pq.add(new Network(i, distance[i]));
                }
            }
         }
        
        int maxTime = 0;
        for(int i=1; i<n; i++){
            if(distance[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, distance[i]);
        }
        return maxTime;
    }


	// https://leetcode.com/problems/time-needed-to-inform-all-employees
    class Emp{
        int node; int time;
        Emp(int n, int t){
            this.node = n; 
            this.time = t;
        }
    }
    // dijkstra distance 
    public int numOfMinutesDijk(int n, int headID, int[] manager, int[] informTime) {
        HashMap<Integer, List<Integer>>g = new HashMap<>();
        
        // create graph
        for(int i =0; i<n; i++){
            if(manager[i] == -1) continue;
            List<Integer> list = g.getOrDefault(manager[i], new ArrayList<>());
            list.add(i);
            g.put(manager[i], list);
        }
        
        //distance
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[headID] = informTime[headID];
        int max = distance[headID];
        
        PriorityQueue<Emp> pq = new PriorityQueue<>((x,y)->x.time - y.time);
        pq.add(new Emp(headID, distance[headID]));
        while(pq.size()!=0){
            Emp curr = pq.remove();
            
            if(!g.containsKey(curr.node)) continue;
            
            List<Integer> list = g.get(curr.node);
            for(int i :list){
                if(distance[i] > distance[curr.node] + informTime[i]){
                    distance[i] = distance[curr.node] + informTime[i];
                    max = Math.max(max, distance[i]);
                    pq.add(new Emp(i, distance[i]));
                }
            }
        }
        return max;
	}
	

	/** 
	 * VARIATION : MODIFIED DIJKSTRA WITH PRUNING
	 * DISTANCE ARRAY MIGHT BE UPDATED BUT STOPS MIGHT
	 * NOT BE AVAILABLE, SO CORRECT DIST IS IN THE CUSTOM
	 * OBJECT IN PQ
	 * BASICALLY WE CHECK FOR ALL POSSIBILITIES, SO DEQUE
	 * 
	 * 
	 * POINTS :
	 * 1 USE A CUSTOM CLASS TO STORE THE NODE, DIST AND STOPS 
	 * 2 DON'T USE VISITED SET, ONLY DISTANCE ARRAY WILL DO
	 * 3 CALCULATE STOPS USING CUSTOM CLASS
	 * 
	 * UPDATE DISTANCE ARRAY AND ADD THE CUSTOM CLASS WITH UPDATED STOPS 
	 * BACK TO Q.
	 * 
	 * imp------------------->
	 * 4 IMP HERE INCORRECT DISTANCES MIGHT BE STORED IN DISTANCE ARRAY
	 * SO USE CURR.DIST
	 * distance[i] > curr.dist + g[curr.node][i]
	 * 
	 * 
	 * n = 4, src 0, dest 3, stops 1
	 * [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
	 * 
	 * https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/
	 * 834942/Faster-than-99-modified-Dijkstra-used-Deque-instead-of-PQueue
	 * 
	 * 
	 * BASICALLY A BRUTE FORCE, AS WE CHECK ALL POSSIBILITIES
	 * WITH PRUNING, ONLUY UPDATE IF DISTANCE IS LESSER AND stops <= K
	 * 
	*/
	// https://leetcode.com/problems/cheapest-flights-within-k-stops/
	class Flight{
		int node, dist, stops;
		Flight(int n, int d, int k){ 
			this.node = n; 
			this.dist = d; 
			this.stops = k;
		}
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
		int[] distance = new int[n];
		int[][] g = new int[n][n];
		
		for(int[] i: g) Arrays.fill(i, -1);
		for(int i =0; i < flights.length; i++){
			g[flights[i][0]][flights[i][1]] = flights[i][2];//1
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE); 
        distance[src] = 0;//2
		
		Deque<Flight> q = new LinkedList<>();//3
		q.addLast(new Flight(src, 0, 0)); distance[src] = 0;//4
		
		while(q.size()!=0){
			Flight curr = q.removeFirst();
            
			for(int i =0; i < n; i++){
				if(g[curr.node][i] != -1 && curr.stops <= k 
				&& distance[i] > curr.dist + g[curr.node][i]){

					distance[i] = curr.dist + g[curr.node][i];
					q.addLast(new Flight(i, distance[i], curr.stops+1));//5
				}
			}
		}
		return distance[dest]==Integer.MAX_VALUE?-1:distance[dest];//6
	}

	//////////////////////////// PRIM
	/**
	 * ALWAYS CUSTOM CLASS FOR PQ SORTING
	 * 
	 * POINTS : 
	 * 1 4 THINGS : CUSTOM CLASS, DIST ARRAY, PARENT ARRAY, HASHSET, PQ
	 * 2 DIST[START] = 0, PARENT[START] = -1 
	 * 3 USE PQ AND SORT X.TIME - Y.TIME
	 * 5 UPDATE ITS ADJACENT, DIST[J] >
	 * GRAPH[I][J], SET PARENT SIMULTANEOUSLY 
	 * 
	 * DIJKSTRA VS PRIM
	 * 1 WE DON'T USE VISITED SET IN DIJKSTRA
	 * 2 WE UPDATE ONLY EDGE WEIGHTS IN PRIM
	 * (distance[i] > distance[curr.node] + g[curr.node][i])
	 * vs
	 * (!set.contains(j) && distance[j] > graph[curr.node][j])
	 * 
	 * CLASS IS USED SO PQ CAN SORT
	 */
	class Prim{
		int node; int time;
		Prim(int n , int t){
			this.node = n;
			this.time = t;
		}
	}
	// no src node is needed as MST 
	void prim(int[][] graph, int n) {
		int[][] g = new int[n][n];
		for(int[] i: g) Arrays.fill(i, -1);
		
		for(int[] i : graph) g[i[0]][i[1]] = i[2];

		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;

		PriorityQueue<Prim> pq = new PriorityQueue<>((x,y)-> x.time - y.time);
		HashSet<Integer> set = new HashSet<>();
		int[] parent = new int[n];
		parent[0] = -1;

		while (pq.size() != 0) {
			Prim curr = pq.remove();
			set.add(curr.node);
			System.out.println("index " + curr);
			for (int j = 0; j < n; j++) {
				if (!set.contains(j) && distance[j] > graph[curr.node][j]) {
					distance[j] = graph[curr.node][j];
					parent[j] = curr.node;
				}
			}
		}

		Utility.print1DMatrix(distance);
		Utility.print1DMatrix(parent);

	}

	// AMAZON
	// https://leetcode.com/discuss/interview-question/797541/
	// amazon-online-assessment-2-sde-1-new-graduate-2021-coding-2-questions-with-solutions
	void primServer(int[][] graph) {
		int n = 0;
		// counting no of edges
		HashSet<Integer> visited = new HashSet<>();
		for (int i = 0; i < graph.length; i++) {
			if (!visited.contains(graph[i][0])) {
				visited.add(graph[i][0]);
				n++;
			}
			if (!visited.contains(graph[i][1])) {
				visited.add(graph[i][1]);
				n++;
			}
		}
		visited.clear();
		// creating adj matrix
		int[][] g = new int[n][n];
		for(int[] i: g) Arrays.fill(i, -1);

		for (int i = 0; i < graph.length; i++) {
			g[graph[i][0]][graph[i][1]] = graph[i][2];
			g[graph[i][1]][graph[i][0]] = graph[i][2];
		}
		// Utility.printMatrix(g);

		
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;

		PriorityQueue<Prim> pq = new PriorityQueue<>((x, y)->x.time - y.time);
		pq.add(new Prim(0,0));

		int[] parent = new int[n];
		parent[0] = -1;

		while (pq.size() != 0) {
			// System.out.println("curr "+curr);
			Prim curr = pq.remove();
			visited.add(curr.node);
			for (int j = 0; j < n; j++) {
				if (!visited.contains(j) 
				&& g[curr.node][j] != -1 && curr.node!= j
				&& distance[j] > g[curr.node][j]) {
					distance[j] = g[curr.node][j];
					pq.add(new Prim(j, distance[j]));
					parent[j] = curr.node;
				}
			}	
		}
		Utility.print1DMatrix(distance);
		Utility.print1DMatrix(parent);
	}


	/////////////////////////////////// BACKTRACKING


	// https://leetcode.com/problems/all-paths-from-source-to-target/
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

		List<List<Integer>> res = new ArrayList<>();
		List<Integer> list = new ArrayList<>();

		list.add(0);
		dfs(res, list, graph, 0);
		return res;
	}

	void dfs(List<List<Integer>> res, List<Integer> list, int[][] graph, int curr) {
		if (curr == graph.length - 1) {
			// temp.add(curr);
			res.add(new ArrayList<>(list));
			return;
		}
		for (int j : graph[curr]) {
			list.add(j);
			dfs(res, list, graph, j);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 * basically same as backtracking.. a vertex is continually assigned colors from
	 * 1 till n, and we check if it's safe, then we recur, else we go back to
	 * assigning it 0. 1 isSafe is tricky, run a for loop for all adjacent vertices
	 * check if the color is same as the color assigned to the vertex if there
	 * exists an edge.
	 * 
	 * 2 a global var foundMinColor is used to break out of the recursive calls
	 * 
	 * ABOVE BACKTRACKING IS ON ADJACENCY LIST, HERE ON A MATRIX.
	 * 
	 */
	boolean foundMinColor = false;

	void mColoring(int[][] arr) {
		int[] color = new int[arr.length];
		mcolorUtil(arr, 0, color);
	}

	void mcolorUtil(int[][] arr, int vertex, int[] color) {
		if (vertex == arr.length) {
			Utility.print1DMatrix(color);
			this.foundMinColor = true;
			int max = 0;
			for (int i = 0; i < color.length; i++) {
				max = Math.max(max, color[i]);
			}
			System.out.println("no of colors required is " + (max - 1));
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
		for (int i = 0; i < arr.length; i++) {
			if (arr[vertex][i] == 1 && color[vertex] == color[i])
				return false;
		}
		return true;
	}


	///////// WORD SEARCH
    /**
     * POINTS : 
     * 1 start a dfs whenever the ch[i][j] matches the starting char of string
     * 2 USE THE WORD, DON'T USE CHAR ARRAY
     * 3 NO NEED TO MAINTAIN VISITED ARRAY, USE BACKTRACKING
     * 4 RETURN BOOLEAN DFS, MAINTAINING A GLOBAL VARIABLE CHECKS
     * FOR ALL POSSIBLE STARTS AND DOESN'T RETURN TRUE TILL ALL POSSIBILITIES
     * ARE EXHAUSTED, WHICH RESULTS IN TLE
     * 5 
     * 
     */
    // backtracking
    // mark as ' '
    // pass the char, not word and index both
    // return boolean not void 
    // https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(board, i, j, word, 0)) return true;
                }
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int row, int col, String word, int index){
        // proceed till index == word.length() 
        if(index == word.length()) return true;
        
        if(isSafeBoard(board, row, col, word.charAt(index))){
            char temp = word.charAt(index);
            board[row][col] = ' '; // 2
            boolean found = dfs(board, row+1, col, word, index+1) // 3
            || dfs(board, row-1, col, word, index+1)
            || dfs(board, row, col+1, word, index+1)
            || dfs(board, row, col-1, word, index+1);
            if(found) return true; // 4
            board[row][col] = temp; // 5
        }
        return false;
    }
    
    boolean isSafeBoard(char[][] board, int row, int col, char ch){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == ch) return true;
        return false;
	}


	int[] res;
	//UNION FIND, ALL AOTHER APPROACHES FAIL
	// https://leetcode.com/problems/redundant-connection/
	public int[] findRedundantConnection(int[][] edges) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				if (!set.contains(edges[i][j]))
					set.add(edges[i][j]);
			}
		}
		int n = set.size();
		System.out.println("size n " + n);
		int[][] g = new int[n + 1][n + 1];

		for (int i = 0; i < n; i++) {
			g[edges[i][0]][edges[i][1]] = 1;
			g[edges[i][1]][edges[i][0]] = 1;
		}
		Utility.printMatrix(g);
		// System.out.println(g[2][3]);
		set.clear();
		set.add(edges[0][0]);
		Deque<Integer> q = new LinkedList<>();
		q.add(edges[0][0]);
		bfs(q, g, set);
		return res;
	}

	void bfs(Deque<Integer> q, int[][] g, HashSet<Integer> set) {

		while (q.size() != 0) {
			int curr = q.removeFirst();
			System.out.println("curr " + curr);
			System.out.println(set);
			for (int i = curr + 1; i < g.length; i++) {
				System.out.println("iterate " + i);
				if (curr != i && g[curr][i] != 0 && !set.contains(i)) {
					q.addLast(i);
					set.add(i);
					System.out.println(q);
				} else if (curr != i && g[curr][i] != 0 && set.contains(i)) {
					res = new int[] { curr, i };
					Utility.print1DMatrix(res);
					return;
				}
			}
		}
	}


	// https://www.techiedelight.com/print-k-colorable-configurations-graph-vertex-coloring-graph/
	// https://leetcode.com/problems/path-with-maximum-probability/
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		Graph g = new Graph(8);
		g.addEdge(5, 2);
		g.addEdge(5, 6);
		g.addEdge(6, 0);
		g.addEdge(6, 4);
		g.addEdge(4, 0);
		g.addEdge(0, 2);
		g.addEdge(0, 1);
		g.addEdge(4, 1);
		g.addEdge(1, 3);
		g.addEdge(2, 7);
		g.addEdge(3, 1);
		g.addEdge(3, 5);
		g.addEdge(1, 0);
		g.addEdge(2, 1);
		g.addEdge(2, 0);

		int[][] dfsgraph = new int[][]{
			{0,1,0,1},
			{0,0,0,0},
			{0,0,0,1},
			{0,0,0,0}
		};
		// g.dfsAdjMatrix(dfsgraph);
		// System.out.println("Following is a Topological " + "sort of the given
		// graph");
		// g.topologicalSort();
		// g.detectLoopUndirected();

		int[][] eventualSafeGraph = {{1,2,3,4},{1,2},{3,4},{0,4},{}};
		// g.eventualSafeNodes(eventualSafeGraph);

		int[][] graph = { { 0, 1, 1, 1 }, { 0, 0, 0, 1 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } };

		// g.topoSort(g);

		// int n = 7, headID = 6; int[] manager = {1,2,3,4,5,6,-1}, informTime =
		// {0,6,5,4,3,2,1};
		int n = 6, headID = 2;
		int[] manager = { 2, 2, -1, 2, 2, 2 }, informTime = { 0, 0, 1, 0, 0, 0 };
		// g.numOfMinutes(n, headID, manager, informTime);

		int numCourses = 3;// 4;
		int[][] prerequisites = { { 2, 0 }, { 2, 1 } };
		// {{1,0},{2,0},{3,1},{3,2}};

		// g.canFinishMatrix(numCourses, prerequisites);
		// g.findOrder1(numCourses, prerequisites);
		// g.findOrder(numCourses, prerequisites);

		int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } };
		// g.bfs(edges);
		// g.findRedundantConnection(edges);

		int[][] oranges = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		// g.orangesRotting(oranges);

		// int[][] times = {{2,1,1},{2,3,1},{3,4,1}}; int N = 4, K = 2;

		int[][] times = {{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},
		{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},
		{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},
		{4,2,51},{3,1,36},{2,3,59}};
		int N = 5, K= 5;
		// g.networkDelayTime(times, N, K);

		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		// wordList.add("b"); wordList.add("c");
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		// g.ladderLength(beginWord, endWord, wordList);

		int[][] dijk = // {{1,2,5}, {1,4,9}, {1,5,2}, {2,3,2}, {3,4,3}, {4,5,2}, {5,6,3}};
				// {{0,5,0,9,2,0},{5,0,2,0,0,0},{0,2,0,3,0,0},{9,0,3,0,0,2},{2,0,0,0,0,3},{0,0,0,2,3,0}};
				{ { 0, 1, 4, 0, 0, 0 }, { 1, 0, 4, 2, 7, 0 }, { 4, 4, 3, 5, 0, 0 }, { 0, 2, 3, 0, 4, 6 },
						{ 0, 7, 5, 4, 0, 7 }, { 0, 0, 0, 6, 7, 0 } };
		int vertices = 6;
		// g.dijkstra(dijk, 1, vertices);
		// g.dfsAdjMatrix(dijk, 0);

		// g.dijkstra(dijk, 0);

		int[][] graphPrim = { { 0, 4, 6, 0, 0, 0 }, { 4, 0, 6, 3, 4, 0 }, { 6, 6, 0, 1, 0, 0 }, { 0, 3, 1, 0, 2, 3 },
				{ 0, 4, 0, 0, 2, 7 }, { 0, 0, 0, 3, 7, 0 } };
		// g.prim(graphPrim, 0);

		int[][] servers = { { 0, 1, 1 }, { 1, 2, 4 }, { 1, 3, 6 }, { 3, 4, 5 }, { 1, 4, 1 } };
		g.primServer(servers);
	}

}
