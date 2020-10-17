import java.util.*;

/**
 * QUES 
 * 1 DFS (FRIEND CIRCLE, ALL PATHS FROM SRC TO TARGET) 
 * 2 BFS (ROTTING ORANGES, WORD LADDER, CHEAPEST FLIGHTS, TIME TO  INFORM EMPLOYEES)
 * 
 * 3 DIJKSTRA (N/W DELAY)
 * 4 DAG -> TOPO SORT (COURSE SCHEDULE 1,2, CHECK LOOP USING BACTRACKING(CURRPARENTS)) 
 * 5 GRAPH COLORING 
 * 6 PRIM 
 * 7 BACKTRACKING (WORD LADDER, COURSE SCHEDULE 2)
 * 
 * GRAPH REPRESENTATION: 
 * 1 HASHSET AND HASHMAP<INTEGER, ARRAYLIST> (ADJ LIST) 
 * 2 HASHSET AND INT[][] G (ADJ MATRIX)
 * 
 * 2 FOR BFS FINDING TIME OR DIST, USE ADJ MATRIX, AS VISITED ARRAY CAN ALSO
 * STORE THE DIST OR TIME, USE DIJKSTRA IF POSSIBLE.
 * 
 * 3 FOR TOPO SORT PREFER ADJLIST OVER MATRIX AS ENTRY EXISTS ONLY IF NODE HAS
 * OUTGOING EDGES(DAG) AND NO NEED TO ADD AND REMOVE.
 * 
 * 4 FOR FINDING LOOP IN GRAPH :
 * 
 * Cycle in undirected graphs : An undirected graph has a cycle if and only
 * if a depth-first search (DFS) finds an already visited node
 * 
 * Cycle in directed graphs : In addition to visited vertices we need to keep track 
 * of vertices currently in recursion stack of function for DFS traversal. 
 * If we reach a vertex that is already in the recursion stack, (currParents)
 * then there is a cycle in the graph.
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

	// Adjacency List as ArrayList of ArrayList's
	private ArrayList<ArrayList<Integer>> adj;
	private HashMap<Integer, ArrayList> map;

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
	 * 2 FOR LOOP
	 * 3 SET.ADD AT THE START OF HELPER
	 * 4 2 CHECKS 
	 * IF(!SET.CONTAINS) DFSHELPER
	 * IF(SET.CONTAINS) CONTINUE;
	 * 
	*/
	void dfs() {
		// boolean[] visited = new boolean[g.V];
		HashSet<Integer> set = new HashSet<>();
		HashMap<Integer, ArrayList> g = new HashMap<>();

		for (int i = 0; i < V; i++) {
			if(!set.contains(i)) dfsHelper(i, set, g);
		}
	}

	void dfsHelper(int curr, HashSet<Integer> set, HashMap<Integer, ArrayList> g ) {
		System.out.println("dfs " + curr);
		set.add(curr);
		if (!map.containsKey(curr)) return;
		ArrayList<Integer> list = g.get(curr);
		for (int i : list) {
			if (set.contains(i)) continue;
			dfsHelper(i, set, g);
		}
	}

	void dfsAdjMatrix(int[][] graph, int src) {
		int n = graph.length;
		HashSet<Integer> set = new HashSet<>();

		set.add(src);
		for (int i = src; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j != i && !set.contains(j) && graph[i][j] != 0)
					set.add(j);
			}
		}
		System.out.println(set);
	}
	
	/** 
	 * POINTS : 
	 * 1 ADD A NODE TO SET
	 * 2 RUN DFS FOR ALL ITS ADJACENT NODES
	 * 3 INCREMENT COEUNT
	 * */
	// https://leetcode.com/problems/friend-circles
    public int findCircleNum(int[][] M) {
        int count = 0;
        int n = M.length;
        HashSet<Integer> set = new HashSet<>();
        
        for(int i =0; i<n; i++){
            if(set.contains(i)) continue;
            set.add(i);
            for(int j = 0; j<n; j++){
                if(M[i][j]!=0 && !set.contains(j)){
                    dfs(M, j, set);
                }
            }
            count++;
        }
        return count;
    }
    
    void dfs(int[][] M, int node, HashSet<Integer> set){
        set.add(node);
        for(int i=0; i<M.length; i++){
            if(M[node][i]!=0 && !set.contains(i)) dfs(M, i, set);
        }
	}
	
	/** 
	 * POINTS :
	 * 1 TWO PARTS, 
	 * FIND THE SET OF NODES THAT CAN VISIT THE TERMINAL EDGES,
	 * FIND THE NODES THAT HAVE SELF LOOP
	 * 
	 * REMOVE ALL THOSE WHICH HAVE LOOP FROM TERMINAL SET AND RETURN RES
	 * 
	 * 2 HOW TO KNOW IF A NODE CAN REACH TERMINAL NODES? 
	 * I USED A GLOBAL PARENT VAR WHICH IS ASSIGNED THE NODE NO AT THE 
	 * START OF DFS AND ADDED TO TERMINAL IFDFS REACHES TERMINAL.
	 * 
	 * 3 WE DON'T STOP AFTER REACHING TERMINAL AS WE NEED TO FIND IF
	 * LOOP EXISTS
	 * 
	 * 
	 * 4 WHY VISITED IS NOT USED? PARENT IS GLOBAL SO VISITED
	 * NODES CAN'T BE TRACKED IF THEY CAN REACH TERMINAL.
	 * 
	 */

	// DOESN'T WORK, BECASUSE OF SELF LOOPS, ELSE WORKS WITH LOOPS TOO
	// https://leetcode.com/problems/find-eventual-safe-states/
    int parent = -1;
    HashSet<Integer> currParents = new HashSet<>();
    HashSet<Integer> loopNodes = new HashSet<>();
    HashSet<Integer> terminal = new HashSet<>();
    public List<Integer> eventualSafeNodes(int[][] graph) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i = 0; i<graph.length; i++){
            List<Integer> curr = new ArrayList<>();
            for(int j =0; j<graph[i].length; j++){
                curr.add(graph[i][j]);
            }
            if(curr.size() == 0) terminal.add(i);
            else map.put(i, curr);
        }
        
        for(int i =0; i<graph.length; i++){
            if(!map.containsKey(i)) continue;
            parent = i;//2
            dfs(i, map);
            currParents.clear();
        }
        
        System.out.println(loopNodes);
        System.out.println(terminal);
		List<Integer> res = new ArrayList<Integer>(terminal);
		for(Integer node : terminal){
			// Exception in thread "main" java.util.ConcurrentModificationException
			// if(loopNodes.contains(node)) terminal.remove(node);
			if(loopNodes.contains(node)) res.remove(node);//3
		}
		System.out.println(res);
        return res;
    }
    
    // check if terminal is reacheable
    // check for loop
    
    void dfs(int node, HashMap<Integer, List<Integer>> map){
        if(terminal.contains(node)) terminal.add(parent);//
        if(!map.containsKey(node)) return;//
        currParents.add(node);
        List<Integer> curr = map.get(node);
        for(int i =0; i<curr.size(); i++){
            if(currParents.contains(curr.get(i))) {
                loopNodes.add(curr.get(i));
                return;
            }
            dfs(curr.get(i), map);
		}
		currParents.remove(node);
	}
	
	/** 
	 * FIND COMPONENTS AND THEN CHECK MST PORPERTY
	 * edges = n-1
	 * HERE EACH EDGE IS COUNTED TWICE, DIRECTED (0->1, 1->0)
	 * SO (edges/2).
	 * 
	 * return (edges/2)>=n-1?components-1:-1;
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
            if(visited.contains(i)) continue;
            dfs(i, map, visited);
            components++;
        }
       
        return (edges/2)>=n-1?components-1:-1;
    }
    
    void dfs(int node, HashMap<Integer, List<Integer>> map, HashSet<Integer> visited){
        if(visited.contains(node)) return;
        visited.add(node);
        if(!map.containsKey(node)) return;
        List<Integer> curr = map.get(node);
        for(int i =0; i<curr.size(); i++){
            if(visited.contains(curr.get(i))) continue;
            dfs(curr.get(i), map, visited);
        }
    }

	/**
	 * BFS 
	 * USING Q 1 CREATED MAP FROM A MATRIX 2 CREATE A VISITED ARRAY OF SIZE n+1,
	 * 0 MIGHT NOT BE PRESENT, SO VISITED[n+1] WILL THROW AN ERROR
	 * 
	 * 3 ADD STARTING VERTEX, AND RUN LOOP FOR Q EMPTY 4 ALWAYS CHECK IF MAP
	 * CONTAINS KEY
	 */

	public void bfs(int[][] edges) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		int n = edges.length;
		boolean[] visited = new boolean[n + 1];
		Deque<Integer> q = new LinkedList<>();
		List<Integer> res = new ArrayList<>();

		for (int[] i : edges) {
			map.putIfAbsent(i[0], new ArrayList<>());
			map.get(i[0]).add(i[1]);
		}

		res.add(edges[0][0]);
		q.addLast(edges[0][0]);
		while (q.size() != 0) {
			int x = q.removeFirst();
			if (!map.containsKey(x))
				continue;
			ArrayList<Integer> curr = map.get(x);
			for (int i : curr) {
				if (!visited[i]) {
					visited[i] = true;
					q.add(i);
					res.add(i);
				}
			}
		}
		System.out.println("bfs traversal " + res);
	}

	///////////////////////////////////////////////////////

	/**
	 * TOPOLOGICAL SORT
	 * POINTS : 
	 * 1 RUN A FOR LOOP, IF NOT VISITED CALL TPS 
	 * 2 IN TPS ADD TO SET(VISITED) 
	 * 3 CHECK IF VERTEX HAS LIST 
	 * 4 IF YES, THEN ITERATE 
	 * 5 IF SET CONTAINS VERTEX, CONTINUE 
	 * 6 ELSE CALL TPS WITH i 
	 * 7 ADD TO Q(STACK)
	 * 
	 * VISITED CHECK IS DONE TWICE, ONCE IN MAIN FN, ONCE IN TPS
	 * 
	 * TO CHECK FOR LOOP IN TPS, MAINTAIN A CURRPARENT SET, ADD AT THE START OF
	 * EVERY CALL, REMOVE AT THE END;
	 * REFER COURSE-SCHEDULE-ii
	 */
	int[] tpsStart(int N) {
		// iterate over the graph in argument to create hashmap graph
		HashMap<Integer, ArrayList> g = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		Deque<Integer> q = new LinkedList<>();
		int n = N;
		int[] res = new int[n];

		for (int i = 0; i < n; i++) {
			if (!set.contains(i)) tps(i, g, set, q);
		}

		int s = q.size();
		for (int i = 0; i < s; i++) res[i] = q.removeLast();

		return res;
	}

	void tps(int curr, HashMap<Integer, ArrayList> map, HashSet<Integer> set, Deque<Integer> q) {
		set.add(curr);
		if (map.containsKey(curr)) {
			ArrayList<Integer> list = map.get(curr);
			for (int i : list) {
				if (set.contains(i))
					continue;
				tps(i, map, set, q);
			}
		}
		q.addLast(curr);
	}

	/**
	 * LOOP
	 * 1 backtracking
	 * 2 2 HASHSETS
	 * 
	 * 
	 * /** it's almost impossible to detect loop using ADJ MATRIX AND WITHOUT USING
	 * KAHN'S ALGO (KEEP INDEGREE COUNT). 
	 * FOR LOOP USE BACKTRACKING APPROACH FOR DAG 	 
	 * */

	// LOOP IN UNDIRECTED (DFS)
	boolean loop = false;

	boolean detectLoopUndirected(){
		HashSet<Integer> visited = new HashSet<>();
		HashMap<Integer, ArrayList> map = new HashMap<>(); 
		for (int i = 0; i < this.V; i++) {
			if (loop) return loop;
			if (!visited.contains(i)) detectLoopUHelper(i, visited, map);
		}
		return loop;
	} 

	void detectLoopUHelper(int curr, HashSet<Integer> visited, HashMap<Integer, ArrayList> map){
		visited.add(curr);
		if (!map.containsKey(curr)) return;
		ArrayList<Integer> list = map.get(curr);
		for(int i : list){
			if(visited.contains(i)) {
				loop = true; break;}
			detectLoopUHelper(curr, visited, map);
		}
	}

	/**  DETECT LOOP IN DAG (BACTRACKING)
	* 1 CREATE GRAPH 2 USE 2 HASHSETS ONE FOR VISITED, ONE FOR
	* CURR PARENTS 3 ADD TO VISITED AT START, ADD TO Q AT END 
	* 
	* 4 2 CHECKS : 
	* ONE FOR VISITED(SET.CONTAINS) 
	* ONE FOR CURRPARENTS (LOOP) 
	* 6 CURRPARENTS.ADD(), CURRPARENTS.REMOVE()
	* 
	* TO DETECT LOOP WE KEEP OF TRACK OF CURRENT PARENTS IN EVERY ITERATION
	* AND REMOVE AT THE END OF THE ITERATION
	* 
	* 2 HASHSETS ARE USED. ONE IS TO DETECT THE VERTEX IS VISITED OR NOT
	* CURRPARENTS HOLDS THE CURRENTLY VISITED NODES IN THIS ITERATION,
	* IT IS CLEARED AT THE END.
	* VISITED SPEEDS UP AS WE DON'T VISIT THE VISITED NODES AGAIN.
	* */
	boolean detectLoopDirected() {
		// int[] visited = new int[this.V];
		HashMap<Integer, ArrayList> map = new HashMap<>();
		HashSet<Integer> visited = new HashSet<>();
		HashSet<Integer> currParents = new HashSet<>();

		for (int i = 0; i < this.V; i++) {
			if (loop) return loop;
			if (!visited.contains(i)) detectLoopUtil(i, visited, currParents, map);
		}
		System.out.println("loop in list " + loop);
		return loop;
	}

	void detectLoopUtil(int curr, HashSet<Integer> visited, HashSet<Integer> currParents, HashMap<Integer, ArrayList> map) {
		visited.add(curr);
		if(!map.containsKey(curr)) return;
		ArrayList<Integer> list = map.get(curr);
		currParents.add(curr);
		for(int i : list){
			if (currParents.contains(i)) {
				this.loop = true;
				System.out.println("loop detected");
				break;
			}
			detectLoopUtil(i, visited, currParents, map);
		}
		// visited[vertex] = 0;
		currParents.remove(curr);
	}


	int[] res;
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
		utilCustom.Utility.printMatrix(g);
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
					utilCustom.Utility.print1DMatrix(res);
					return;
				}
			}
		}
	}

	// https://leetcode.com/problems/course-schedule/
	/**
	 * FOR TOPO SORT USE ADJ LIST AS 
	 * 1 IT HELPS WHEN ANY NODE DOESN'T HAVE ANY
	 * OUTGOING EDGE, THE MAP.CONTAINS CHECK HELPS REMOVE IT QUICKER AND NOT ITERATE
	 * AND ADD UNNECESSARILY. 
	 * 2 ALSO IT IS FASTER AS IN ADJMATRIX WE ITERATE OVER EACH LOOP
	 * 
	 * set and currParenst both are needed, set is not necessary bit speeds up a lot
	 * currParents is like backtracking, add at the start and remove at the end.
	 * 
	 * STEPS : 
	 * 1 USE CURRPARENTS, DON'T ADD IN MAIN, ALWAYS ADD TO currParents IN DFS
	 * 2 ADD, LOOP CHECK, DFS AND REMOVE
	 * ALDR
	 */
	boolean loopC1 = false;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> currParents = new HashSet<>();
        
        for(int i =0; i<prerequisites.length; i++){
            ArrayList<Integer> curr = 
                map.getOrDefault(prerequisites[i][1], new ArrayList<Integer>());
            
            curr.add(prerequisites[i][0]);
            map.put(prerequisites[i][1], curr);
        }
        
        for(int i = 0; i<numCourses; i++){
            // if(loop) return false; 
            if(!set.contains(i)) dfs(i, map, set, currParents);//1
        }
        return !loopC1;
    }
    
	void dfs(int curr, HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> set, 
	HashSet<Integer> currParents){
        set.add(curr);
        if(!map.containsKey(curr)) return;
        currParents.add(curr);
        ArrayList<Integer> list = map.get(curr);
        for(int i : list) {
            //return is required here
            if(currParents.contains(i)) {loopC1 = true; return;}
            dfs(i, map, set, currParents);
            
        }
        currParents.remove(curr);
    }


	// https://stackoverflow.com/questions/38578995/how-to-cast-object-to-int-java
	// USING ADJ MATRIX doesn't work for all cases

	// https://leetcode.com/problems/course-schedule-ii/
	/**
	 * BASIC DAG WITH LOOP TEMPLATE 
	 * 1 CREATE GRAPH 
	 * 2 USE 2 HASHSETS ONE FOR VISITED, ONE FOR CURR PARENTS 
	 * 3 DON'T ADD IN THE MAIN FUNC, ADD INSIDE DFS
	 * 4 ADD TO SET AT START, BACKTRACK, ADD TO Q AT END 
	 * 5 WHILE BACKTRACKING, ADD AND REMOVE
	 * 
	 */

	boolean isLoop = false;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
	    HashSet<Integer> currParents = new HashSet<>();
		Deque<Integer> q = new LinkedList<>();

        //3, [[1,0]] chk this case
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
        
        if (isLoop) return new int[] {};
        
        int i = 0;
		int[] resCourse1 = new int[q.size()];
        while(q.size()!=0) resCourse1[i++] = q.removeLast();
		// utilCustom.Utility.print1DMatrix(resCourse1);
		return resCourse1;
	}

	void tps(int curr, HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> set, 
	Deque<Integer> q, HashSet<Integer> currParents) {
		set.add(curr);//1
        //smacps
		if (map.containsKey(curr)) {//2
            
			currParents.add(curr);//3 add
			ArrayList<Integer> list = map.get(curr);
			for (int i : list) {
				if (currParents.contains(i)) {//4
					isLoop = true;
					return;
				}
                
				if (set.contains(i)) continue;//5
				tps(i, map, set, q, currParents);//6
			}
			currParents.remove(curr);//7 remove
		}

        q.addLast(curr);
	}

	
	// https://leetcode.com/problems/redundant-connection-ii/ BFS CAN BE USED?
	// https://leetcode.com/problems/critical-connections-in-a-network/
	// https://leetcode.com/problems/minimum-height-trees/

	
	// https://leetcode.com/problems/time-needed-to-inform-all-employees/
	// AMAZON, USED BFS

	public int numOfMinutesBFS(int n, int headID, int[] manager, int[] informTime) {
        int[] visited = new int[n];
        // initialize all to max value
        Arrays.fill(visited, Integer.MAX_VALUE);
        
        //set headID to it's informTime
        visited[headID] = informTime[headID];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        // create graph
        for(int i=0; i<manager.length; i++) {
            ArrayList<Integer> curr = map.getOrDefault(manager[i], new ArrayList<>());
            curr.add(i);
            map.put(manager[i], curr);
        }
        
        Deque<Integer> q= new LinkedList<>();
        q.addLast(headID);
        // BFS
        while(q.size()!=0){
            int node = q.removeFirst();
            if(visited[node]!=Integer.MAX_VALUE && map.containsKey(node)){
                ArrayList<Integer> list = map.get(node);
                for(int i =0; i<list.size(); i++){
                    // dijkstra
                    if(visited[list.get(i)] > visited[node]+informTime[list.get(i)]){
                        visited[list.get(i)] = visited[node]+informTime[list.get(i)];
                        q.addLast(list.get(i));
                    }
                }
            }
        }
        
        int max = 0;
        for(int j =0; j<visited.length; j++){
            // if visited[j] == Integer.mAX_VALUE return -1;
            max = Math.max(max, visited[j]);
        }
        return max;
	}

	/**
	 * TECHNIQUE : 1 CREATE A GRAPH USING HASHMAP 2 DFS 3 FOR TIME THERE ARE 2 WAYS,
	 * 3.1 ADD TIME BEFORE ENTERING CHILD, IF THE CHILD DOESN'T HAVE ANY FURTHER
	 * CHILDREN IT JUST RETURNS THE TIME. IT WORKS BECAUSE ONLY NODES HAVING CHIDREN
	 * ARE IN MAP, SO IF NODE IS IN MAP, THEN IT MUST HAVE CHILD NODE(S), HENCE
	 * ADDING INFORM TIME BEFORE HAND IS OK.
	 * 
	 * 3.2 USE F(ROOT) = INFORMTIME[ROOT] + F(CHILD); SET FLAG TO ZERO IN EACH
	 * ITERATION AND RETURN MAX MAX(MINUTES, INFORMTIME[ROOT]+DFS(CHILD))
	 * 
	 * 4 USE DP TO STORE VALUE IN ANOTHER MAP (TIMEHOLDER)
	 * 
	 */
	int minutes = Integer.MIN_VALUE;
	HashMap<Integer, Integer> timeHolder = new HashMap<>();

	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

		for (int i = 0; i < manager.length; i++) {
			if (manager[i] == -1)
				continue;
			else if (map.containsKey(manager[i])) {
				ArrayList<Integer> c = map.get(manager[i]);
				c.add(i);
				map.put(manager[i], c);
			} else {
				ArrayList<Integer> c = new ArrayList<>();
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
	 * works but added informtime beforehand void dfsNumMinutes(HashMap<Integer,
	 * ArrayList<Integer>> map, int start, int[] informTime, int time){
	 * if(!map.containsKey(start)) { minutes = Math.max(minutes, time); return; }
	 * time+=informTime[start]; // System.out.println(time); for(int i :
	 * map.get(start)){ dfsNumMinutes(map, i, informTime, time);
	 * 
	 * } }
	 */

	int dfsNumMinutes(HashMap<Integer, ArrayList<Integer>> map, int start, int[] informTime, int time) {
		int minutes = 0;
		if (!map.containsKey(start))
			return 0;
		// System.out.println(time);
		for (int i : map.get(start)) {
			if (timeHolder.containsKey(i))
				return timeHolder.get(i);
			minutes = Math.max(minutes, informTime[start] + dfsNumMinutes(map, i, informTime, time));
			timeHolder.put(i, minutes);
		}
		return minutes;
	}

	
	// https://leetcode.com/problems/path-with-maximum-probability/

	// DFS EXAMPLE

	// https://leetcode.com/problems/all-paths-from-source-to-target/
	/**
	 * 1 the indexes are the vertices, int j : graph[curr] curr is int and references
	 * to the particular row index
	 * 
	 * 2 use a helper 
	 * 
	 * 3 MOST IMP : ALWAYS USE List<List<Integer>> res = new
	 * ArrayList<>(); List<Integer> temp = new ArrayList<>();
	 * 
	 * AND PASS THIS temp IN dfs; dfs (res, temp, graph, 0);
	 * 
	 * 4 while adding the result clone it into a new ArrayList
	 * 
	 * 
	 */
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

	// BFS
	// https://leetcode.com/problems/rotting-oranges/
	/**
	 * POINTS : 1 USE A CUSTOM CLASS 2 USE A QUEUE AND ADD ALL 2s 3 THE TRICKY THING
	 * IS TO KEEP TRACK OF VISITED INDEXES, CAN BE HANDLED VIA IS SAFE CHECKER AND
	 * MARKING THE GRID INDEX AS 2.
	 * 
	 * WE ADD ONLY 2, WE CHECK FOR SURROUNDING INDEXES, IF SAFE MARK THEM AS 2 AND
	 * INCREMENT TIME;
	 * 
	 */
	class Orange {
		int row, col, val, time;

		Orange(int r, int c, int v, int t) {
			this.row = r;
			this.col = c;
			this.val = v;
			this.time = t;
		}
	}

	public int orangesRotting(int[][] grid) {
		Deque<Orange> q = new LinkedList<>();
		int maxTime = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2)
					q.add(new Orange(i, j, 2, 0));
			}
		}

		int[] rows = { -1, 0, 0, 1 };
		int[] cols = { 0, -1, 1, 0 };
		while (q.size() != 0) {
			Orange curr = q.removeFirst();

			for (int i = 0; i < rows.length; i++) {
				if (isSafeOrange(grid, curr.row + rows[i], curr.col + cols[i])) {
					grid[curr.row + rows[i]][curr.col + cols[i]] = 2;
					q.addLast(new Orange(curr.row + rows[i], curr.col + cols[i], 2, curr.time + 1));
					maxTime = curr.time + 1;
				}
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1)
					return -1;
			}
		}
		return maxTime;
	}

	boolean isSafeOrange(int[][] grid, int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1)
			return true;
		return false;
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
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        int distance = 1;
        
        Deque<String> q = new LinkedList<>();
        q.addLast(beginWord);
        while(q.size()!=0){
            int size = q.size();            
            // all words in the same go, helps maintain smallest dist
            for(int k =0; k<size; k++){//1
                char[] curr = (q.removeFirst()).toCharArray();
                
                for(int i =0; i<curr.length; i++){
                    char holder = curr[i];//2
                    
                    for(char c ='a'; c<='z'; c++){
                        if(c==holder) continue;//3
                        curr[i] = c;
                        String after = String.valueOf(curr);
                        if(after.equals(endWord)) return distance+1;
                        if(set.contains(after)) {
                            // System.out.print(after+", ");
                            q.addLast(after); set.remove(after);//4
                        }
                    }
                    curr[i] = holder;//5
                }   
            }
            distance++;//6
        }
        return 0;
	}
	
	

	/** 
	 * POINTS :
	 * 1 MODIFIED BFS
	 * 2 RUN FOR ALL INDICES OF THE GRAPH
	 * 3 VISITED CHECK
	 * 
	 * VISITED ARRAY HELPS AVOID VIVITED COMPONENTS, EVEN THOUGH THERE MIGHT BE 
	 * DISCONNECTED COMPS, VISITED ARRAY HELPS AVOID VISITING THE CONNECTED
	 * COMPONENTS AGAIN 
	 * 
	 * 4 THE QUEUE IS INITIALISED FOR EACH ELEMENT AS THERE CAN BE DISCONNECTED 
	 * COMPONENTS TOO
	 * 
	 * 5 CHECKING FOR ALL NEIGHBORS
	 * 
	 * 6 ADD ONLY IF NOT VISITED
	 * 
	 * 7 FINAL CHECK FOR ALREADY VISITED ONES
	*/
	// https://leetcode.com/problems/is-graph-bipartite/
	public boolean isBipartite(int[][] graph) {
        // BFS
        // 0(not visited), 1(black), 2(white)
        int[] visited = new int[graph.length];
        
		for (int i = 0; i < graph.length; i++) {//2
			//3 VISITED CHECK AND FOR THIS TYPE OF FIRST ARRAY [[], [1,2]]
            if (graph[i].length != 0 && visited[i] == 0) {
                visited[i] = 1;
                Deque<Integer> q = new LinkedList<>();//4
                q.addLast(i);//5
                while(!q.isEmpty()) {
                    int current = q.removeFirst();
                    for (int c : graph[current]) {
						if (visited[c]!=0 && visited[c] == visited[current]) return false;//6
						
                        if (visited[c] == 0) {//7
                            visited[c] = (visited[current] == 1) ? 2 : 1;
                            q.addLast(c);//
                        } 
                        
                    }
                }                        
            }
        }
        
        return true;
	}

	// https://leetcode.com/problems/possible-bipartition/
	

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
			utilCustom.Utility.print1DMatrix(color);
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

	// https://www.techiedelight.com/print-k-colorable-configurations-graph-vertex-coloring-graph

	/**
	 * DIJKSTRA'S ALGO (SHORTEST PATH FROM SOURCE TO VERTEX) 1 MAINTAIN A PQUEUE FOR
	 * MAPPING 2 REMOVE MIN FROM Q, ADD TO VISITED HASHMAP WITH DISTANCE 3 CHECK FOR
	 * ADJACENT VERTICES FO POPPED EL(BFS), UPDATE DISTANCES 4 ON UPDATING DIST, ADD
	 * TO ANOTHER MAP HOLDING PARENT CHILD RELN 5
	 */
	class Shortest {
		int val, dist;

		Shortest(int v, int d) {
			this.val = v;
			this.dist = d;
		}
	}

	    /** 
	 * DIJKSTRA
	 * 
	 * IDEA : 
	 * WE ADD THE HEAD TO A PQ AND SIMULTANEOUSLY TO A SET,
	 * THEN REMOVE THE SMALLEST FROM PQ, ADD TO SET AND UPDATE ITS NEIGHBOURS
	 * IF SET CONTAINS A NEIGHBOUR, IT HAS BEEN RELAXED ALREADY, CONTINUE
	 * IF DIST[I] > EDGE + DIST[CURR], UPDATE AND ADD TO PQ
	 * 
	 * 
	 * REQUISITES : CUSTOM CLASS, ADJ MATRIX, PQUEUE, SET, DISTANCE[] //5
	 *  1 class Network{
     *    int node, time;
     *    Network(int n, int t){
     *       this.node = n;
     *       this.time = t;
     *    }
     *  } 
	 * 2 USE ADJ MATRIX AS IT CAN STORE EDGE WT, WHICH IS DIFFICULT WITH ADJ LIST
	 * 3 USE A PQ, TO FETCH THE LEAST DIST NODE
	 * 4 USE A SET TO MAINTAIN VISITED NODES
	 * 5 USE DIST[] TO FETCH DIST AT THE END
	 * 
	 * 1 SAME AS BFS, ALWAYS USE A CUSTOM CLASS
	 * 2 FILL THE GRAPH WITH Integer.MAX_VALUE (AS THERE MIGHT BE 0 WT EDGES)
	 * 
	 * 3 USE A PQ
	 * PriorityQueue<Network> pq = new PriorityQueue<>((x,y)->x.time - y.time);
	 * 
	 * 4 ADD TO SET ONLY WHEN REMOVING FROM PQ
	 * 
	 * 5 if(!set.contains(curr.node) && distance[i] > distance[curr.node] + g[curr.node][i])
	 * ENSURE NEIGHBOR HASN'T BEEN RELAXED (NOT IN VISITED SET)
	 * 
	 * 6 WHILE CHECKING FOR MAX DIST, CHECK FOR DIST = 7000
	 *
	 * https://cs.stackexchange.com/questions/10047/
	 * is-dijkstras-algorithm-just-bfs-with-a-priority-queue 
	 * 
	 * DIJKSTRA IS A BIT DIFFERENT, IT USE SET.SIZE()==N-1
	 * PQ WORKS JUST FINE
	*/
	// graph src, dest, edge wt : [[2,1,1],[2,3,1],[3,4,1]]
	int dijkstra(int[][] graph, int head, int n) {
		// any value greater than max edge wt(6000), but not Integer.MAX_VALUE
		int[][] g = new int[n][n];
		for(int[] i :g) Arrays.fill(i, Integer.MAX_VALUE-10000);

		for(int[] i: graph){
			g[i[0]][i[1]] = i[2];
		}

		int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE-10000);
        distance[head] = 0;

        PriorityQueue<Shortest> pq = new PriorityQueue<>((x,y)->x.dist - y.dist);

		HashSet<Integer> visited = new HashSet<>();

		pq.add(new Shortest(head, 0));
		distance[head] = 0;

		while(pq.size()!=0){
			Shortest curr = pq.remove();
			visited.add(curr.val);

			for(int i =0; i<n; i++){
				if(!visited.contains(i) 
				&& distance[i]> curr.dist + graph[curr.val][i]){
					distance[i] = curr.dist + graph[curr.val][i];
					pq.add(new Shortest(i, distance[i]));
				}
			}

		}
		int maxTime = 0;
        for(int i=1; i<distance.length; i++){
            if(distance[i] == Integer.MAX_VALUE-10000) return -1;
            maxTime = Math.max(maxTime, distance[i]);
        }
        return maxTime;
	}


	// https://leetcode.com/problems/network-delay-time/

	/** 
	 * MAJOR PINTS OF FAILURE:
	 * 1 0 WT EDGES, SO g[curr.node][i]!=0 FAILS
	 * 
	 * 2 (x,y)->distance[x] - distance[y] FAILS AS ONCE ADDED TO PQ, IF DISTANCE IS UPDATED,
	 * THE NODES AREN'T REARRANGED
	 * int[] visited = new int[]{200, 1, 30};
	 *  PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->distance[x] - distance[y]);
	 * pq.add(0);
	 * pq.add(1);
	 * pq.add(2);
	 * System.out.println(pq);
	 * visited[1] = 1000;
	 * System.out.println(pq);
	 * 
	 * 3 THE DISTANCE NEEDS TO BE INITIALISED TO 7000, INTEGER.MAX_VALUE
	 * OVERFLOWS, Integer.MAX_VALUE + distance[curr.node] GOES TO -ve
	 * SO INITIALIZE TO MORE THAN 6000 (read ques condition).
	 * 
	 * 
	 * 
	 */
	// use dijkstra as single source shortest path, not bfs
	
	// https://leetcode.com/problems/network-delay-time/
    class Network{
        int node, time;
        Network(int n, int t){
            this.node = n;
            this.time = t;
        }
    }
    public int networkDelayTime(int[][] times, int N, int k) {
        int n = N+1;
        int[][] g = new int[n][n];
        
        for(int[] i : g) Arrays.fill(i, Integer.MAX_VALUE-10000);
        
        for(int[] i : times) g[i[0]][i[1]] = i[2];
        
		int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE-10000);
        distance[k] = 0;

        PriorityQueue<Network> pq = new PriorityQueue<>((x,y)->x.time - y.time);
		pq.add(new Network(k, 0));
		
        HashSet<Integer> set = new HashSet<>();
        
        
        while(pq.size()!=0){
            Network curr = pq.remove();
            set.add(curr.node);
            // System.out.println(curr.node+" "+distance[curr.node]);
            for(int i = 1; i<n; i++){
                if(!set.contains(i)
                   // && curr.node!=i 
                   // && g[curr.node][i]!=0 // 0 wt edges
                   && distance[i] > curr.time + g[curr.node][i]){
                    pq.add(new Network(i, curr.time + g[curr.node][i]));
					distance[i] = curr.time + g[curr.node][i];
					// distance[curr.node] can be used in place of curr.time
                    // System.out.println("curr "+curr.node+" i "+i +" "+distance[i]);
                }
            }
         }
        
        int maxTime = 0;
        for(int i=1; i<distance.length; i++){
            if(distance[i] == Integer.MAX_VALUE-10000) return -1;
            maxTime = Math.max(maxTime, distance[i]);
        }
        return maxTime;
    }


	/**
	 * POINTS : 
	 * 1 USE 3 STORAGE : DIST ARRAY, PARENT ARRAY, HASHSET 
	 * 2 DIST[START] =0, PARENT[START] = -1 
	 * 3 BASE CONDN HASHSET.SIZE!=N 
	 * 4 REMOVE THE LOWEST FROM DIST ARRAY WHICH IS NOT IN HASHSET 
	 * 5 UPDATE ITS ADJACENT, DIST[J] >
	 * GRAPH[I][J], SET PARENT SIMULTANEOUSLY 
	 * 6 S, I, M, A, R,
	 * 
	 * GETMININDEX IS IMP, ONLY ITERATE FOR NODES NOT PRESENT IN SET
	 */
	void prim(int[][] graph, int n) {
		int[][] g = new int[n][n];
		for(int[] i: g) Arrays.fill(i, Integer.MAX_VALUE-10000);
		
		for(int[] i : graph) g[i[0]][i[1]] = i[2];

		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		HashSet<Integer> set = new HashSet<>();
		int[] parent = new int[n];
		parent[0] = -1;

		while (set.size() != n) {
			int curr = getNextMinIndex(set, dist);
			set.add(curr);
			System.out.println("index " + curr);
			for (int j = 0; j < n; j++) {
				if (!set.contains(j) && dist[j] > graph[curr][j]) {
					dist[j] = graph[curr][j];
					parent[j] = curr;
				}
			}
		}

		utilCustom.Utility.print1DMatrix(dist);
		utilCustom.Utility.print1DMatrix(parent);

	}

	int getNextMinIndex(HashSet<Integer> set, int[] dist) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < dist.length; i++) {
			if (!set.contains(i)) {
				if (min > dist[i]) {
					min = dist[i];
					index = i;
				}
			}
		}
		return index;
	}

	// AMAZON
	// https://leetcode.com/discuss/interview-question/797541/
	// amazon-online-assessment-2-sde-1-new-graduate-2021-coding-2-questions-with-solutions
	void primServer(int[][] graph) {
		int n = 0;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < graph.length; i++) {
			if (!set.contains(graph[i][0])) {
				set.add(graph[i][0]);
				n++;
			}
			if (!set.contains(graph[i][1])) {
				set.add(graph[i][1]);
				n++;
			}
		}
		set.clear();
		// creating adj matrix
		int[][] g = new int[n][n];
		for (int i = 0; i < graph.length; i++) {
			g[graph[i][0]][graph[i][1]] = graph[i][2];
			g[graph[i][1]][graph[i][0]] = graph[i][2];
		}
		// utilCustom.Utility.printMatrix(g);

		int[] parent = new int[n];
		parent[0] = -1;
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;

		while (set.size() != n) {
			int curr = getNextMinIndex(set, dist);
			// System.out.println("curr "+curr);
			set.add(curr);
			for (int j = 0; j < n; j++) {
				if (!set.contains(j) && g[curr][j] != 0 && curr != j) {
					if (dist[j] > g[curr][j]) {
						dist[j] = g[curr][j];
						parent[j] = curr;
					}
				}
			}
		}
		utilCustom.Utility.print1DMatrix(dist);
		utilCustom.Utility.print1DMatrix(parent);
	}

	/** 
	 * POINTS :
	 * 1 USE A CUSTIOM CLASS TO STORE THE NODE, DIST AND STOPS 
	 * 2 
	*/
	// https://leetcode.com/problems/cheapest-flights-within-k-stops/
	class Flight{
		int val, dist, stops;
		Flight(int v, int d, int k){ 
			this.val = v; 
			this.dist = d; 
			this.stops = k;
		}
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
		int[] visited = new int[n];
		int[][] g = new int[n][n];
		
		for(int i =0; i < flights.length; i++){
			g[flights[i][0]][flights[i][1]] = flights[i][2];//1
		}
		
		Arrays.fill(visited, Integer.MAX_VALUE); visited[src] = 0;//2
		
		Deque<Flight> q = new LinkedList<>();//3
		q.addLast(new Flight(src, 0, 0)); visited[src] = 0;//4
		
		while(q.size()!=0){
			Flight c = q.removeFirst();
			for(int i =0; i < n; i++){
				if(g[c.val][i] != 0 && c.stops <= k && visited[i] > c.dist + g[c.val][i]){
					visited[i] = c.dist+g[c.val][i];
					q.addLast(new Flight(i, visited[i], c.stops+1));//5
				}
			}
		}
		return visited[dest]==Integer.MAX_VALUE?-1:visited[dest];//6
	}


	// https://www.techiedelight.com/print-k-colorable-configurations-graph-vertex-coloring-graph/
	// https://leetcode.com/problems/path-with-maximum-probability/
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		Graph g = new Graph(8);
		// g.addEdge(5, 2);
		// g.addEdge(5, 6);
		// g.addEdge(6, 0);
		// g.addEdge(6, 4);
		// // g.addEdge(4, 0);
		// g.addEdge(0, 2);
		// g.addEdge(0, 1);
		// g.addEdge(4, 1);
		// g.addEdge(1, 3);
		// g.addEdge(2, 7);
		// g.addEdge(3, 1);
		// g.addEdge(3, 5);
		g.addEdge(1, 0);
		g.addEdge(2, 1);
		g.addEdge(2, 0);

		// g.dfs(g);
		// System.out.println("Following is a Topological " + "sort of the given
		// graph");
		// g.topologicalSort();
		// g.detectLoopUndirected();

		int[][] eventualSafeGraph = {{1,2,3,4},{1,2},{3,4},{0,4},{}};
		g.eventualSafeNodes(eventualSafeGraph);

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
		// g.primServer(servers);
	}

}
