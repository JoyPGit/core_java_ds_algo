import java.util.*;

class Matrix {

    /** 
     * 
     * PACIFIC ATLANTIC, OBSTACLES, WALLS AND GATES, WORD SEARCH
     * SPIRAL PRINT
     * PATH WITH OBSTACLES (USE DP, NO DFS)
     * 
     * TEMPLATES : 
     * 1 BACKTRACKING
     * 2 DP, VISITED
     * 3 AREA OF ISLAND
     * 4 UNIQUE PATHS
     * 
     * 
     * // TO ITERATE IN 4 DIRECTIONS
     * 1 CHECK ONLY IN ISSAFE
     * 2 MARK VISITED AFTER IS SAFE IN HELPER
     * 
     * int[] rows = new int[]{-1,0,0,1};
     * int[] cols = new int[]{0,-1,1,0};
     * 
     * void dfs(int[][] graph){
     *  int m = graph.length; int n = graph[0].length;
     *  int[][] visited = new int[m][n];
     * 
     *  for(int i =0; i<n; i++){
     *      for(int j = 0; j<m; j++){
     *          if(visited[i][j] == 1) continue;
     *          dfsUtil(graph, i, j, visited);
     *      }
     *  }
     * }
     *    
     * void dfsUtil(int[][] arr, int row, int col, int[][] visited){
     *  if(isSafedfs(arr, row, col, visited)) {
     *      visited[row][col] = 1;
     *  
     *      System.out.println(arr[row][col]);
     *      for(int k =0; k<rows.length; k++){
     *          int newX = row + rows[k];
     *          int newY = col + cols[k];
     *          // if(visited[newX][newY] == 1) continue; // dont check here; out of bounds
     *          dfsUtil(arr, newX, newY, visited);
     *      }
     *  }
     * } 
     * 
     * boolean isSafedfs(int[][] arr,int row, int col, int[][] visited){
     *    if(row>=0 && row<arr.length
     *    && col>=0 && col<arr[0].length
     *    && visited[row][col] == 0) return true;
     *    return false;
     * }
     * 
     * ///////
     * ONE MAJOR ISSUE IS INFINITE LOOP BY VISITING LEFT AND THEN RIGHT
     * USE -> VISITED ARRAY, OR PASS PREV, OR DP
     * 
     * FRIEND CIRCLE, NO OF ISLANDS
     * BASIC COUNT TEMPLATE 
     * for(int i =0; i<grid.length; i++){
     *    for(int j =0; j<grid[0].length; j++){
     *        if(grid[i][j] == '1') {
     *            dfs(grid, i, j); 
     *            count++;
     *        }
     *    }
     * }
     * return count;
     * 
     * DFS WITH RETURN TEMPLATE
     * 
     * boolean dfs(char[][] board, int row, int col, String word, int index){
     *   // proceed till index == word.length() 
     *   if(index == word.length()) return true;
     * 
     *   if(isSafeBoard(board, row, col, word.charAt(index))){
     *      char temp = word.charAt(index);
     *      board[row][col] = ' '; // 2
     *      boolean found = dfs(board, row+1, col, word, index+1) // 3
     *      ||dfs(board, row-1, col, word, index+1)
     *      ||dfs(board, row, col+1, word, index+1)
     *      ||dfs(board, row, col-1, word, index+1);
     *      if(found) return true; // 4
     *      board[row][col] = temp; // 5
     *   }
     *  return false;
     * }
     * 
     * DP TO AVOID LOOP W/O VISITED :
     * DON'T ADD DP CONDN IN ISSAFE
     * int dfsInc(int[][] matrix, int row, int col, int[][] dp, int prev){
     *   if(isSafeInc(matrix, row, col, dp, prev)){
     *      // System.out.println("row "+row+" col " +col);
     *      // pass matrix[row][col]
     *      // take max
     *      if(dp[row][col]!=0) return dp[row][col];
     * 
     *      else{
     *        dp[row][col] = 
     *        Math.max(dfsInc(matrix, row+1, col, dp, matrix[row][col]),
     *        Math.max(dfsInc(matrix, row-1, col, dp, matrix[row][col]),
     *        Math.max(dfsInc(matrix, row, col+1, dp, matrix[row][col]),
     *        + dfsInc(matrix, row, col-1, dp, matrix[row][col])))) +1; //imp
     *       return dp[row][col];
     *      }
     *   }
     *   return 0;
     * }
     * 
     * boolean isSafeInc(int[][] matrix, int row, int col, int[][] dp, int prev){
     *    if(row>=0 && row<matrix.length
     *    && col>=0 && col<matrix[0].length
     *    && matrix[row][col] > prev) return true;
     *  
     *  return false;
     * }
     * 
     * 
     * 
     * AVOIDING INF LOOP USING VISITED ARR PACIFIC ATLANTIC
     * void dfs(int[][] matrix, int row, int col, int[][] arr, int prev){
     *    if(row>=0 && row<matrix.length
     *    && col>=0 && col<matrix[0].length
     *    && matrix[row][col] >= prev){
     *       // don't add in isSafe as in dp  
     *       if(arr[row][col] == 1) return;
     *      
     *       arr[row][col] = 1;
     *      
     *       dfs(matrix, row+1, col, arr, matrix[row][col]);
     *       dfs(matrix, row-1, col, arr, matrix[row][col]);
     *       dfs(matrix, row, col+1, arr, matrix[row][col]);
     *       dfs(matrix, row, col-1, arr, matrix[row][col]);
     *    }
     * }
     * 
    */

    // DFS TEMPLATE
    /**  
     * POINTS :
     * 1 VISITED CHECK IN FOR LOOP BEFORE DFS START
     * 2 VISITED CHECK IN ISSAFE
     * 3 MARK VISITED IN HELPER
    */
    // TO ITERATE IN 4 DIRECTIONS
    int[] rows = new int[]{-1,0,0,1};
    int[] cols = new int[]{0,-1,1,0};

    void dfs(int[][] graph){
        int m = graph.length; int n = graph[0].length;
        int[][] visited = new int[m][n];

        for(int i =0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(visited[i][j] == 1) continue; // visited check
                dfsUtil(graph, i, j, visited);
            }
        }
    }
   
    void dfsUtil(int[][] arr, int row, int col, int[][] visited){
        if(isSafeDFS(arr, row, col, visited)) {
            visited[row][col] = 1; // 2 mark visited

            System.out.println(arr[row][col]);
            for(int k =0; k<rows.length; k++){
                int newX = row + rows[k];
                int newY = col + cols[k];
                // if(visited[newX][newY] == 1) continue; // dont check here; out of bounds
                dfsUtil(arr, newX, newY, visited);
            }
        }
    }

    boolean isSafeDFS(int[][] arr,int row, int col, int[][] visited){
        if(row>=0 && row<arr.length
        && col>=0 && col<arr[0].length
        && visited[row][col] == 0) return true; // visited check
        return false;
    }

    ///////////////////// FLOOD FILL, MARKING AS VISITED IN THE SAME GRAPH

     /** 
     * DFS MATRIX VS GRAPH
     * 1 VISITED CHECKS ARE IN 2 PLACES, IN FOR LOOP OF DFS AND IN ISSAFE
     * IN GRAPH WE CHECK IN FOR LOOP OF HELPER AS NO ISSAFE METHOD EXISTS
     * 
     * 2 MARK VISITED IN HELPER 
     * MATRIX -> 1 AND 2
     * GRAPH -> 1 AND 4
     * 
     * 3 MARKING ASVISITED SAME IN BOTH
     */
    
    /** 
	 * POINTS : 
	 * 1 RUN 2 CHECKS IN FOR LOOP; BEFORE STARTING DFS AND IN ISSAFE
	 * 2 MARK VISITED IN HELPER AFTER ISSAFE
	 * 3 INCREMENT COUNT
     * 
     * CAN SPEED UP, IF POSSIBLE USING //3 USED IN GRAPH
	 * */
	// DFS TEMPLATE
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
        if(isSafeFriend(visited, start)){
            visited[start] = 1;                             // 3
            for(int i = 0; i<g.length; i++){
                // if(visited[i] != Integer.MAX_VALUE) continue; // 4
                if(i!=start && g[start][i] == 1) dfs(i, g, visited);
            }
        } 
    }
    
    boolean isSafeFriend(int[] visited, int index){
        if(visited[index] == Integer.MAX_VALUE) return true; // 2
        return false;
    }


    // https://leetcode.com/problems/number-of-islands
    public int numIslands(char[][] grid) {
        int count = 0;
        
        for(int i =0; i<grid.length; i++){
            for(int j =0; j<grid[0].length; j++){
                if(grid[i][j] == '1') {
                    dfs(grid, i, j); 
                    count++;
                }
            }
        }
        return count;
    }
    
    void dfs(char[][] grid, int row, int col){
        if(row>=0 && row<grid.length
          && col>=0 && col<grid[0].length
          && grid[row][col] == '1'){
            grid[row][col] = '0';

            for(int k = 0; k<rows.length; k++){
                int newX = row + rows[k];
                int newY = col + cols[k];
                dfs(grid, newX, newY);
            }
        }
    }


    // https://leetcode.com/problems/number-of-closed-islands
    public int closedIsland(int[][] grid) {
        int count = 0;
        
        for(int i =0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(i ==0 || i == grid.length-1 || j == 0 || j == grid[0].length-1){
                    dfs(grid, i, j);
                }
            }
        }
    
        for(int i =0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==0){
                    dfs(grid, i, j); 
                    count++;
                }
            }
        }
        return count;
    }
    
    void dfs(int[][] grid, int row, int col){
        if(row>=0 && row<grid.length
          && col>=0 && col<grid[0].length
          && grid[row][col] == 0){
            grid[row][col] = 1;
            dfs(grid, row+1, col);
            dfs(grid, row-1, col);
            dfs(grid, row, col+1);
            dfs(grid, row, col-1);
        }
    }


    // https://leetcode.com/problems/surrounded-regions
    public void solve(char[][] board) {

        for(int i =0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(i ==0 || i == board.length-1 || j == 0 || j == board[0].length-1){
                    dfsSurrounded(board, i, j);
                }
            }
        }
    
        for(int i =0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j]=='O') board[i][j] = 'X';
            }
        }
        
        for(int i =0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == 'Y') board[i][j] = 'O';
            }
        }
    }
    
    void dfsSurrounded(char[][] board, int row, int col){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == 'O'){
            board[row][col] = 'Y';
            dfs(board, row+1, col);
            dfs(board, row-1, col);
            dfs(board, row, col+1);
            dfs(board, row, col-1);
        }
    }
    

     /**
     * THE BASIC TEMPLATE IS CHANGED A BIT,
     * WE DON'T RECUR FOR ALL POINTS IN THE GRAPH, RATHER ONLY FOR THE
     * BOUNDARY POINTS.
     * 
     * ONLY IN THE isSafe method a diff condn "matrix[r][c]>=prev"
     * IS USED. THIS CHECKS IF WATER CAN FLOW FROM THE BOUNDARY 
     * TO ANY POINT.
     * 
     * WATER WILL FLOW FROM 5->2 (IF 2 IS BOUNDARY); 
     * IF WE START FROM 2 THEN "prev<=matrix[r][c]" to reach 5.
     * 
     * POINTS : 
     * 1 USED DFS, BUT HERE REVERSE IS DONE
     * WE CHECK IF WATER CAN FLOW FROM THE BOUNDARY(OCEAN) INTO THE MATRIX
     * 2 CHECK FOR PACIFIC OCEAN ONCE, CREATE A VISITED ARRAY(named PACIFIC) AND MARK 1
     * 3 CHECK FOR ATLANTIC AGAIN
     * 4 THE POINTS FROM WHERE PACIFIC AND ATLANTIC BOTH HAVE 1 IS
     * ADDED TO RES.
     * 
     * */
    // water flows from inside to boundaries, matrix[row][col] >= prev
    // https://leetcode.com/problems/pacific-atlantic-water-flow/
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res= new ArrayList<>();
        
        int m = matrix.length;
        if(m == 0) return res;
        
        int n = matrix[0].length;
        
        // visited
        int pacific[][] = new int[m][n];
        int atlantic[][] = new int[m][n];
        
        
        for(int i=0;i<n;i++)
        {
            dfs(matrix,0,i,pacific,Integer.MIN_VALUE); //top
            dfs(matrix,m-1,i,atlantic,Integer.MIN_VALUE); //bottom
        }
        
        for(int i=0;i<m;i++)
        {
            dfs(matrix,i,0,pacific,Integer.MIN_VALUE); //left
            dfs(matrix,i,n-1,atlantic,Integer.MIN_VALUE);
        }
        
        for(int i =0; i<m; i++){
            for(int j = 0; j<n; j++){
               if(pacific[i][j]==1 && atlantic[i][j]==1){
                    List<Integer> curr = new ArrayList<>();
                    curr.add(i);curr.add(j);
                    res.add(curr);
                }
            }
        }
        return res;
    }
    
    void dfs(int[][] matrix, int row, int col, int[][] arr, int prev){
        if(row>=0 && row<matrix.length
          && col>=0 && col<matrix[0].length
          && matrix[row][col] >= prev){
            // don't add in isSafe as in dp  
            if(arr[row][col] == 1) return;
            
            arr[row][col] = 1;
            
            dfs(matrix, row+1, col, arr, matrix[row][col]);
            dfs(matrix, row-1, col, arr, matrix[row][col]);
            dfs(matrix, row, col+1, arr, matrix[row][col]);
            dfs(matrix, row, col-1, arr, matrix[row][col]);
        }
    }


    /////////////// RETURN VALUE

    /**  
     * TRY TO RETURN THE DFS VALUE
     * SAME AS IN LONGEST PATH
     * ALSO MARK THE VISITED, SO NO EXTRA VISITED
    */
    // https://leetcode.com/problems/max-area-of-island
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for(int i =0; i<grid.length; i++){
            for(int j =0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    max = Math.max(max, dfsArea(grid, i, j));
                }
            }
        }
        return max;
    }
    
    int[] rowsArea = {-1,0,0,1};
    int[] colsArea = {0,-1,1,0};
    
    int dfsArea(int[][] grid, int r, int c){
        if(isSafeArea(grid, r, c)){
            // so we don't visit again
            grid[r][c] = 0;
            
            int val = 1;       // 1
            for(int k=0;k<rows.length; k++){
                val+=dfsArea(grid, r + rowsArea[k], c + colsArea[k]); // 2
            }
            
            return val;
        }
        return 0;
    }
    boolean isSafeArea(int[][] grid, int r, int c){
        if(r>=0 && r<grid.length
          && c>=0 && c<grid[0].length
          && grid[r][c] == 1) return true;
        return false;
    }


    /////////////////////////////////////////// WITH DP
    /** 
     * POINTS :
     * 1 DP IN MATRIX
     * 2 NO VISITED, CHECK ID DP!=0
     * 3 PASS PREV AS MIN TO START THE PROCESS
     * 4 PASS CURR MATRIX VAL
     * 5 TAKE MAX+1, DON'T SUM UP
    */
    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        
        int max = 0;
        for(int i =0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(dp[i][j] == 0){
                    max = Math.max(max, dfsInc(matrix, i, j, dp, Integer.MIN_VALUE));
                }
            }
        }
        return max;
    }
    
    int dfsInc(int[][] matrix, int row, int col, int[][] dp, int prev){
        if(isSafeInc(matrix, row, col, dp, prev)){
            // System.out.println("row "+row+" col " +col);
            // pass matrix[row][col]
            // take max
            if(dp[row][col]!=0) return dp[row][col];
            else{
                dp[row][col] = 
                    Math.max(dfsInc(matrix, row+1, col, dp, matrix[row][col]),
                    Math.max(dfsInc(matrix, row-1, col, dp, matrix[row][col]),
                    Math.max(dfsInc(matrix, row, col+1, dp, matrix[row][col]),
                    + dfsInc(matrix, row, col-1, dp, matrix[row][col]))))+1; // imp
                return dp[row][col];
            }
        }
        return 0;
    }
    
    boolean isSafeInc(int[][] matrix, int row, int col, int[][] dp, int prev){
        if(row>=0 && row<matrix.length
          && col>=0 && col<matrix[0].length
          && matrix[row][col] > prev) return true;
        
        return false;
    }

    
    /**
     * 1 pass Integer.MAX_VALUE AS prev, TO START FIRST DFS 
     * 2 pass current value for subsequent 
     * 3 DON'T ADD DP CONDITION IN IN ISSAFE
     */
    void goldMine(int[][] grid) {
        int max = -1;
        int m = grid.length; int n =grid[0].length; 
        int[][] dp = new int[m][n];
        
        int j, i;
        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                max = Math.max(max,
                    goldMineHelper(grid, i, j, dp, Integer.MIN_VALUE)); // 1 min_value
            }
        }
        System.out.println("max gold is " + max);
    }

    int goldMineHelper(int[][] grid, int row, int col, int[][] dp, int prev) {

        if (isSafeGold(grid, row, col, prev)) { 
            if (dp[row][col] != 0) return dp[row][col]; // 2

            int curr = grid[row][col]; // 3

            int left = goldMineHelper(grid, row, col - 1, dp, curr);
            int right = goldMineHelper(grid, row, col + 1, dp, curr);
            int up = goldMineHelper(grid, row - 1, col, dp, curr);
            int down = goldMineHelper(grid, row + 1, col, dp, curr);

            dp[row][col] = Math.max(left, Math.max(right, Math.max(up, down))) + curr; // 4

            System.out.println("dp[" + row + "][" + col + "] " + dp[row][col]);
            return dp[row][col]; // 5
        } 
        return 0;
    }

    boolean isSafeGold(int[][] grid, int row, int col, int prev){
        if(row>=0 && row<grid.length
        && col>=0 && col<grid[0].length
        && grid[row][col] > prev) return true; // 5 >prev
        return false;
    }
    // https://www.techiedelight.com/probability-alive-after-taking-n-steps-island/
    
    /** the -1s are inaccessible; reduce 99 to lowest dist from 0 */

    /**
     * we are starting from each zero and keeping a counter, if the grid[i][j] >
     * counter, we update it
     */
    void wallsAndGatesKevin(int[][] matrix) {
        int i, j;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    wallsAndGatesHelperKevin(matrix, i, j, dp, 0);
                }
            }
        }

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println();
        }

    }

    /**
     * here the tricky thing is to choose the initial value of count while making
     * the first call to helper we assign value to matrix if the count is lesser
     * than it's current value, but we use grid[row][col]>=count, equal to is used
     * to bypass the base condition
     */

    // try using BFS
    void wallsAndGatesHelperKevin(int[][] grid, int row, int col, int[][] dp, int count) {
        if (row > -1 && row < grid.length 
        && col > -1 && col < grid[0].length 
        && grid[row][col] != -1 && count >= 0 // &&
        && grid[row][col] >= count) {
            grid[row][col] = count;
            wallsAndGatesHelperKevin(grid, row, col + 1, dp, count + 1);
            wallsAndGatesHelperKevin(grid, row, col - 1, dp, count + 1);
            wallsAndGatesHelperKevin(grid, row + 1, col, dp, count + 1);
            wallsAndGatesHelperKevin(grid, row - 1, col, dp, count + 1);
        }
    }


    ////////////////////////////////  PATHS WITH OBSTACLES
    int countPath;
    // https://leetcode.com/problems/unique-paths-ii/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if(m==0 || n==0) return 1;
        if(m==1 && n==1) {
            if(obstacleGrid[m-1][n-1] == 0) return 1;
            if(obstacleGrid[m-1][n-1] == 1) return 0;
        }
        if(obstacleGrid[0][0] == 1) return 0; //1
        int[][] dp = new int[m][n];
        dp[0][0] = 1; //2
        
        for(int i = 1; i<m; i++){
            if(obstacleGrid[i][0] == 1) dp[i][0] =0; //3
            else dp[i][0] = dp[i-1][0]; //4
        }
        
        for(int i =1; i<n; i++){
            if(obstacleGrid[0][i] == 1) dp[0][i] =0;
            else dp[0][i] = dp[0][i-1];
        }
        
        for(int i =1; i<m; i++){
            for(int j=1; j<n; j++){
                if(obstacleGrid[i][j] == 1) dp[i][j] = 0; //5
                else {
                    // if(i==0 || j==0) dp[i][j] =1;
                    // else 
                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        // System.out.println(dp[1][2]);
        return dp[m-1][n-1];
    }
    // https://leetcode.com/problems/unique-paths-iii/



    ///////////////////////////////////////
    /** 
     * DIFFERENCE B/W BFS IN A MATRIX AND A GRAPH :
     * 
     * 1 IN MATRIX, WE JUST CHECK FOR THE SURROUNDING INDEXES,
     * AND IF VALID ADD TO Q,
     * 
     * IN GRAPH REPRESENTATION USING ADJ-MATRIX, WE ITERATE OVER ALL
     * VERTICES HOPING TO FIND A VALID EDGE AND ADD TO Q.
     * 
     * 2 IN MATRIX A GRAPH NEED NOT BE CREATED SEPARATELY
    */

    // BFS, USING VISITED -> MODIFIED BFS DOESN'T USE VISITED, RATHER DISTANCE
    class BFSNode {
        int node;
        int x;
        int y;

        BFSNode(int n, int rowIndex, int colIndex) {
            this.node = n;
            this.x = rowIndex;
            this.y = colIndex;
        }
    }

    void BFSMatrix(int[][] graph) {
        int m = graph.length; int n = graph[0].length;
        int[][] visited = new int[m][n];

        // starting from 0
        BFSNode node = new BFSNode(0, 0, graph[0][0]);

        Deque<BFSNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(node);

        int[] row = {0,-1,1,0};
        int[] col = {-1,0,0,1}; 

        while (!bfsQueue.isEmpty()) {
            BFSNode curr = bfsQueue.removeFirst();
            visited[curr.x][curr.y] = 1;

            System.out.print(curr.node + ", ");
            for(int k=0; k<row.length; k++){
                int newX = curr.x + row[k];
                int newY = curr.y + col[k];
                if(isSafeBFS(graph, newX, newY, visited)) 
                    bfsQueue.addLast(new BFSNode(graph[newX][newY], newX, newY));
            }
            
        }
    }

    boolean isSafeBFS(int[][] graph, int row, int col, int[][] visited){
        if(row>=0 && row<graph.length
        && col>=0 && col<graph[0].length
        && visited[row][col]!=0) return true;
        return false;
    }


    // all paths from a to b 
    // https://www.geeksforgeeks.org/print-paths-given-source-destination-using-bfs/


    ///////////////////////////////// ROTATION
    void rotateMatrix(int m, int n, int[][] arr) {

        int row = 0;
        int column = 0;
        int prev, curr;

        int rowMax = arr.length;
        int colMax = arr[0].length;

        prev = arr[row + 1][column];

        for (int i = 0; i < colMax - 1; i++) {
            arr[row][i + 1] = arr[row][i];
        }
        // if(row==0 && i==0) arr[row][i] = arr[row+1][i];
        // row++;

        for (int i = row; i < rowMax - 1; i++) {
            // curr = arr[i][n-1];
            // arr[i][n-1]= prev;
            // prev = curr;
            // arr[row+1][colMax-1]=
        }
        m--;

        for (int i = n - 1; i >= column; i--) {
            curr = arr[m - 1][i];
        }
    }

    void printInSnakeFormat(int[][] arr) {
        int row = 0;
        int column = 0;
        boolean ltor = true;
        int rowMax = arr.length;
        int colMax = arr[0].length;
        int track = 0;
        System.out.println("rowmax " + rowMax);
        System.out.println("colmax " + colMax);
        /**
         * 2 things placement of the if condition before the other if using continue
         */
        while (row < rowMax) {
            if (ltor) {
                if (column == colMax - 1) {
                    System.out.println(arr[row][column]);
                    row++;
                    ltor = false;
                    continue;
                }
                if (column < colMax)
                    System.out.println(arr[row][column]);
                column++;

            } else {
                if (column == 0) {
                    System.out.println(arr[row][column]);
                    row++;
                    ltor = true;
                    continue;
                }
                if (row >= 0 && column >= 0)
                    System.out.println(arr[row][column]);
                column--;
            }

        }
    }

    void printSpiral(int[][] arr) {
        int currentCol = 0;
        int currentRow = 0;
        int colEnd = arr[0].length;
        int rowEnd = arr.length;
        int rowStartCounter = 0;
        int colStartCounter = 0;
        int rowEndCounter = rowEnd;
        int colEndCounter = colEnd;

        // while(rowCounter< rowEnd && colCounter< colEnd){
        // rowCounter++;colCounter++;
        // }

        /**
         * 4 counters are held, currentcol, currentrow and colstartcounter and
         * rowstartcounter
         */
        while (rowStartCounter < rowEnd / 2) {
            currentRow = rowStartCounter;
            currentCol = colStartCounter;
            while (currentCol < colEndCounter - 1) {
                System.out.println(arr[currentRow][currentCol]);
                // if(currentCol<colEnd-1)
                currentCol++;
            } // 4 is printed in second while
            while (currentRow < rowEndCounter - 1) {
                System.out.println(arr[currentRow][currentCol]);
                if (currentRow < rowEnd - 1)
                    currentRow++;
            }
            while (currentCol > colStartCounter) {
                System.out.println(arr[currentRow][currentCol]);
                if (currentCol >= 1)
                    currentCol--;
            }
            while (currentRow > rowStartCounter) {
                System.out.println(arr[currentRow][currentCol]);
                if (currentRow >= 1)
                    currentRow--;
            }
            rowStartCounter++;
            colStartCounter++;
            colEndCounter--;
            rowEndCounter--;
        }
    }

    void rotateByKElements(int[][] arr, int k) {
        for (int i = 0; i < k; i++) {
            rotateByOne(arr);
        }
    }

    void rotateByOne(int[][] arr) {
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = arr.length;
        int colEnd = arr[0].length;
        int currentCol = 0;
        int currentRow = 0;

        while (rowStart < arr.length && colStart < arr[0].length) {
            currentCol = colStart;
            currentRow = rowStart;
            while (currentCol < colEnd - 1) {
                swap(arr, currentRow, currentCol + 1, currentRow, currentCol);
                currentCol++;
                System.out.println("1 while done");
                int temp = arr[currentRow][currentCol];
            }
            while (currentRow < rowEnd - 1) {
                // swap(arr, currentRow, currentCol, currentRow+1, currentCol);
                // arr[currentRow+1][currentCol];
                currentRow++;
                System.out.println("2 while done");
            }
            while (currentCol > colStart) {
                swap(arr, currentRow, currentCol, currentRow, currentCol - 1);
                currentCol--;
                System.out.println("3 while done");
            }
            while (currentRow > rowStart) {
                swap(arr, currentRow, currentCol, currentRow - 1, currentCol);
                currentRow--;
                System.out.println("4  while done");
            }
            rowStart++;
            colStart++;
            rowEnd--;
            colEnd--;
        }
    }

    int swap(int[][] arr, int row1, int col1, int row2, int col2) {
        int temp = arr[row1][col1];
        arr[row2][col2] = temp;
        System.out.println("swapped " + arr[row1][col1] + " " + arr[row2][col2]);
        return arr[row2][col2];
    }

    
    // Java program to print a given matrix in spiral form

    // int i, k = 0, l = 0;
    /*
     * k - starting row index m - ending row index l - starting column index n -
     * ending column index i - iterator
     */
    // }

    void printSpiral29Apr(int[][] arr) {
        int rowEnd = arr.length - 1;
        int colEnd = arr[0].length - 1;

        int i, rowStart = 0, colStart = 0;
        /*
         * k - starting row index m - ending row index l - starting column index n -
         * ending column index i - iterator
         */

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Print the first row from the remaining rows
            for (i = colStart; i <= colEnd; ++i) {
                System.out.print(arr[rowStart][i] + " ");
            }
            rowStart++;
            /**
             * for the row the column counters are used, and the rowstart is constant whihc
             * is changed after the loop
             */

            // Print the last column from the remaining columns
            for (i = rowStart; i <= rowEnd; ++i) {
                System.out.print(arr[i][colEnd] + " ");
            }
            colEnd--;

            // Print the last row from the remaining rows */
            if (rowStart <= rowEnd) {
                for (i = colEnd; i >= colStart; --i) {
                    System.out.print(arr[rowEnd][i] + " ");
                }
                rowEnd--;
            }

            // Print the first column from the remaining columns */
            if (colStart <= colEnd) {
                for (i = rowEnd; i >= rowStart; --i) {
                    System.out.print(arr[i][colStart] + " ");
                }
                colStart++;
            }
        }
    }

    void antiDiaPrint(int[][] arr) {
        int sum = 0;
        while (sum <= (arr.length - 1 + arr[0].length - 1)) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (i + j == sum)
                        System.out.print(arr[i][j] + ", ");
                }
            }
            System.out.println();
            sum++;
        }
    }

    // https://leetcode.com/problems/diagonal-traverse-ii/

    // https://leetcode.com/problems/sort-the-matrix-diagonally
    /*
     * the most imp thing is that the diff b/w indices remains 
     * 1 same across a dia, so store diff in map
     * 2 USE PQUEUE INSEAD OF LIST AND THEN SORTING IT LATER
    */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                PriorityQueue<Integer> curr = 
                    map.getOrDefault(i-j, new PriorityQueue<>());
                curr.add(mat[i][j]);
                map.put(i-j, curr);
            }
        }
        
        for(int i =0; i<m; i++){
            for(int j=0; j<n; j++){
                mat[i][j] = (map.get(i-j)).remove();
            }
        }
        return mat;    
    }


    //////////////////////////////////////////

    // Java program to find the row
    // with maximum number of 1s

    static int R = 5, C = 5;

    // Function to find the index of first index
    // of 1 in a boolean row-wise sorted array arr[]
    int first(int arr[], int low, int high) {
        if (high >= low) {
            // Get the middle index
            int mid = low + (high - low) / 2;

            // Check if the element at middle index is first 1 or the mid index has reached
            // 0
            if ((mid == 0 || (arr[mid - 1] == 0)) && arr[mid] == 1)
                return mid;

            // If the element is 0, recur for right side
            else if (arr[mid] == 0)
                return first(arr, (mid + 1), high);

            // If element is not first 1, recur for left side
            else
                return first(arr, low, (mid - 1));
        }
        return -1;
    }

    // Function that returns index of row
    // with maximum number of 1s.
    int rowWithMax1s(int mat[][]) {
        // Initialize max values
        int max_row_index = 0, max = -1;

        // Traverse for each row and count number of
        // 1s by finding the index of first 1
        int i, index;
        for (i = 0; i < R; i++) {
            index = first(mat[i], 0, C - 1);
            System.out.println("mid " + index);
            if (index != -1 && C - index > max) {
                max = C - index;
                max_row_index = i;
            }
        }

        return max_row_index;
    }


    boolean searchRowColSortedMatrix(int[][] arr, int num) {
        int row = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1][0] > num && arr[i][0] < num)
                row = i;
        }
        if (row == -1) {
            if (arr[arr.length - 1][0] < num)
                row = arr.length - 1;
            else if (row == -1)
                return false;
        }
        System.out.println(row);
        return binsearch(arr, row, num, 0, arr[0].length - 1);
    }

    boolean binsearch(int[][] arr, int row, int num, int start, int end) {
        if (start > end)
            return false;
        int mid = -1;
        if (start <= end) {
            mid = (start + end) / 2;
        }
        if (arr[row][mid] == num)
            return true;
        else if (arr[row][mid] > num)
            return binsearch(arr, row, num, start, mid - 1);
        else
            return binsearch(arr, row, num, mid + 1, end);
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
            ||dfs(board, row-1, col, word, index+1)
            ||dfs(board, row, col+1, word, index+1)
            ||dfs(board, row, col-1, word, index+1);
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

    // https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
    // discuss/547633/Python-SUPER-EASY-Idea%3A-just-walk-the-maze-based-on-the-rule
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        int[][] twoDimArr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, 
        { 13, 14, 15, 16 } };// new int[2][2];
        int[][] arr = { { 8, 2, 1 }, { 3, 9, 7 }, { 2, 1, 8 } };
        // matrix.printInSnakeFormat(twoDimArr);

        // matrix.rotateByKElements(twoDimArr, 1);
        // matrix.printSpiral(twoDimArr);

        // System.out.println(matrix.maximumPathSum(0, 0, arr));
        // int dp[][] = new int[3][3];
        // matrix.maxPathSumWithDP(arr, 0, 0, matrix.fill2DArray(dp, 3, 3));
        // System.out.println(matrix.findMaxIn2DArray(dp));

        int[][] arr1 = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
        // matrix.longestPathFromAnyIndex(arr1, 0, 0);
        // System.out.println(matrix.longestIncreasingPath(arr1));

        // int[][] islandMatrix = { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1,
        // 1 }, { 0, 0, 0, 0, 0 },
        // { 1, 0, 1, 0, 1 } };
        // System.out.println("the no of islands in the islandMatrix are "+
        // matrix.numberOfIslands(islandMatrix));
        // System.out.println("the max 1s are "+matrix.findMaxOnesRegion(islandMatrix));

        int[][] arr28Apr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int[][] arr29Apr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        // matrix.printSpiral29Apr(arr29Apr);
        // int[][] onesArray =
        // {{0,0,0,1,1},{0,1,1,1,1},{0,0,1,1,1},{1,1,1,1,1},{0,0,0,0,0}};
        // System.out.println("row index of max1s " + matrix.rowWithMax1s(onesArray));

        // int[][] floodFillArray = {{1,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        // matrix.floodFill(floodFillArray);
        // matrix.showMatrix(floodFillArray);

        int[][] orangesArray = { { 2, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } };
        int v[][] = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };

        // int[][] pathArray = {{9,9,4},{6,6,8},{2,1,1}};
        int[][] pathArray = { { 7, 7, 5 }, { 2, 4, 6 }, { 8, 2, 0 } };
        // matrix.orangesRotting(orangesArray);

        // matrix.diagonalPrintMatrix(orangesArray);

        // matrix.orangesRotting1May(orangesArray);
        // matrix.BFSMatrix(arr28Apr);
        // matrix.maxPathLength(arr1);

        int[][] goldArray = { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } };
        // matrix.goldMine(goldArray);

        // System.out.println("max gold is "+matrix.GoldMine7Jun(goldArray, 3, 3));

        int[][] wallsAndGates = { { 99, -1, 0, 99 }, { 99, 99, 99, -1 }, { 99, -1, 99, -1 }, { 0, -1, 99, 99 } };
        // matrix.wallsAndGates(wallsAndGates);
        // matrix.wallsAndGatesKevin(wallsAndGates);
        // matrix.antiDiaPrint(twoDimArr);

        int[][] matRowColSorted = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };
        int num = 100;

        // System.out.println(matrix.searchRowColSortedMatrix(matRowColSorted, num));

        // matrix.dfs(twoDimArr);

        int[][] path = {{0,1,1,1},{0,0,0,1},{1,1,0,0}, {0,0,0,0}};
        // matrix.allPathsFromAtoB(path, 2,  3);

        char[][] maximalRectangle = {{'1','0','1','0','0'},
                     {'1','0','1','1','1'},{'1','1','1','1','1'},
                     {'1','0','0','1','0'}};

        // matrix.maximalRectangle(maximalRectangle);



    }

}