package Java_DS_Algo;
import java.util.*;
// import utilCustom.Utility;
class Matrix {
    /** 
     * 
     * PACIFIC ATLANTIC, OBSTACLES, WALLS AND GATES, 
     * LONGEST PATH, WORD SEARCH
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
     *   for(int i =0; i<n; i++){
     *      for(int j = 0; j<m; j++){
     *          if(visited[i][j] == 1) continue;
     *          dfsUtil(graph, i, j, visited);
     *      }
     *   }
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
     * 3 MARKING AS VISITED SAME IN BOTH
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
    
    ////////////////////////////////////// COUNTING TYPE

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


    /////////////////////////////////////// BOUNDARY DFS
    
    // https://leetcode.com/problems/number-of-closed-islands
    public int closedIsland(int[][] grid) {
        int count = 0;
        
        // boundary marking
        for(int i =0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(i ==0 || i == grid.length-1 || j == 0 || j == grid[0].length-1){
                    dfs(grid, i, j);
                }
            }
        }
    
        // counting islands
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


    /**
     * CAPTURE ALL Os SURROUNDED BY Xs.
     * 
     * RUN A DFS FROM BOUNDARY AND MARK THE Os AS Ys
     * THEN RUN A SIMPLE FOR LOOP AND MARK ALL 0s as Xs.
     * THEN REVERT ALL Ys TO Os.
     */
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
        
        // revert back
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


    ////////////////////////////////////////// RETURN VALUE

    /**  
     * POINTS : 
     * 1 MARK THE VISITED
     * 2 
     * 
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
            
            int val = 1;     // 1
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


    /////////////////////////////////// WITH DP
    /** 
     * POINTS :
     * 1 DP IN MATRIX
     * 2 NO VISITED, CHECK ID DP!=0
     * 3 PASS PREV AS MIN TO START THE PROCESS
     * 4 PASS CURR MATRIX VAL
     * 5 TAKE MAX+1, DON'T SUM UP
     * 
     * DP CAN ONLY BE APPLIED IF VALUES ARE INCREASING
     * IF SAME THE INF LOOP AS (matrix[row][col] > prev) fails
     * 
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
                    + dfsInc(matrix, row, col-1, dp, matrix[row][col])))) +1; // imp
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

    
    // GOLD MINE


    // https://www.techiedelight.com/probability-alive-after-taking-n-steps-island/
    

    // [[-19,57],[-40,-5]]     
    // https://leetcode.com/problems/minimum-falling-path-sum/
    public int minFallingPathSumDP(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dp = new int[m][n];
  
        int min = Integer.MAX_VALUE; 
        for(int i =0; i<n; i++){
            min = Math.min(min, dfsFalling(A, 0, i, dp, Integer.MIN_VALUE));
        }
        return min;
    }
    
    int dfsFalling(int[][] arr, int row, int col, int[][] dp, int prev){
        if(isSafe(arr, row, col, prev)){
            // if(row == arr.length-1) return arr[row][col];
            if(dp[row][col]!=0) return dp[row][col];
            
            int curr = arr[row][col];
            int lowerLeft = 0; int lowerRight = 0; int lower = 0;
            int min = Integer.MAX_VALUE;
            if(col == 0) {
                lower = dfsFalling(arr, row+1, col, dp, curr);
                lowerRight = dfsFalling(arr, row+1, col+1, dp, curr);
                min = Math.min(lower, lowerRight);
            }
            else if(col == arr[0].length-1){
                lower = dfsFalling(arr, row+1, col, dp, curr);
                lowerLeft = dfsFalling(arr, row+1, col-1, dp, curr);
                min = Math.min(lower, lowerLeft);
            }
            else {
                lower = dfsFalling(arr, row+1, col, dp, curr);
                lowerLeft = dfsFalling(arr, row+1, col-1, dp, curr);
                lowerRight = dfsFalling(arr, row+1, col+1, dp, curr);
                min = Math.min(lowerLeft, Math.min(lowerRight, lower));
            }
            
            if(min == Integer.MAX_VALUE) min = 0;
            // don't find min here, always 0
            dp[row][col] = min + arr[row][col];
            // System.out.println(dp[row][col]);
            return dp[row][col];
        }
        return Integer.MAX_VALUE;
    }
    
    boolean isSafe(int[][] arr, int row, int col, int prev){
        if(row>=0 && row<arr.length
          && col>=0 && col<arr[0].length
          // && arr[row][col]>=prev
          ) return true;
        return false;
    }


    ///////////////////////////////////// DIST FROM GATES

    // WALLS AND GATES
    /**
     * we are starting from each zero and keeping a counter, if the grid[i][j] >
     * counter, we update it
     */
    void wallsAndGatesKevin(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    helperK(matrix, i, j, 0);
                }
            }
        }
        // utilCustom.Utility.printMatrix(matrix);
        // Utility.printMatrix(matrix);
    }

    void helperK(int[][] grid, int row, int col, int count) {
        if (row > -1 && row < grid.length 
        && col > -1 && col < grid[0].length 
        && grid[row][col] != -1 && count >= 0 // &&
        && grid[row][col] >= count) {
            grid[row][col] = count;
            helperK(grid, row, col + 1, count + 1);
            helperK(grid, row, col - 1, count + 1);
            helperK(grid, row + 1, col, count + 1);
            helperK(grid, row - 1, col, count + 1);
        }
    }

    /**
     * WALLS AND GATES
     * WALL -1, GATE 0
     * FIND SHORTEST DIST TO 0 FROM INDEX WHILE AVOIDING WALLS
     * 
     * APPROACHES : 
     * 1 USE DFS FROM inf INDEXES //fails. Why?
     * 2 USE DFS FROM 0
     * HOW TO AVOID INF LOOP?
     *  MAINTAIN VISITED?
     *  USE PREV?
     * 
     * 3 USE BFS
     *  
     */

    /** 
     * DFS VS BFS 
     * UPDATE THE CURR VALUE IN MATRIX AFTRE ISSAFE CHECK
     * 
    */

    /**  
     * STARTING FROM INF DOESN'T WORK. 
     * ONCE MARKED VISITED, THE INDEX CAN'T BE UPDATED OR ACCESSED AGAIN.
     * SO A GATE CAN BE ACCESSED AT MAX ONCE.
     */ 

    /**
     * DP CAN'T BE APPLIED HERE AS WE RUN INTO A COMPARISON OF 
     * INF WITH INF WHICH RUNS INTO AN INF LOOP
     * DP WORKS WITH GOLD MINE AS INDEX VALUES ARE INCREASING, 
     * IF WE USE SAME ADJACENT VALUES IT WILL RUN INTO inf LOOP
     * 
     *   
     * STARTING FROM 0
     * 1 INITIAL PREV IS Integer.MIN_VALUE
     * 2 IS SAFE curr>prev
     * 3 UPDATE ONLY IF NOT ZERO AND GREATER
     * if(matrix[r][c]!=0 && matrix[r][c] > prev) matrix[r][c] = prev;
     * 
     *
     */ 
    void wallsAndGates1(int[][] matrix){
        int m = matrix.length; int n = matrix[0].length;

        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(matrix[i][j] == 0) 
                    dfsWall(matrix, i, j, Integer.MIN_VALUE);
            }
        }
        System.out.println("walls 1 from 0 :");
        // Utility.printMatrix(matrix);
    } 


    void dfsWall(int[][] matrix, int r, int c, int prev){
        if(isSafeWall(matrix, r, c, prev)){
            // same as marking visited after isSafe in dfs
            // assign if not 0, shorter dist as check's been done in isSafe
            if(matrix[r][c]!=0) matrix[r][c] = prev;

            for(int k =0; k<rows.length; k++){
                int curr = matrix[r][c];
                dfsWall(matrix, r + rows[k], c + cols[k], curr+1);
            }
        }
    }

    // -1 is handled automatically as -1 can never be > 0
    boolean isSafeWall(int[][] matrix, int row, int col, int prev){
        if(row>=0 && row<matrix.length
        && col>=0 && col<matrix[0].length
        && matrix[row][col] > prev) return true;
        return false;
    }

    // https://leetcode.com/problems/shortest-distance-from-all-buildings/
    
    
    //////////////////////////////// UNIQUE PATHS
    /**
     * the trick is to convert a recursive relation to a dp relation 
     * dfs(r,c) = dfs(r+1,c)+dfs(r,c+1);
     * 
     * but the relation here for dp is built in bottom up manner
     */
    // https://leetcode.com/problems/unique-paths/
    int uniquePaths(int[][] arr) {
        int m = arr.length; int n = arr[0].length;
        int[][] dp = new int[m][n];
        
        for(int i = 0; i<m; i++){
            for(int j =0; j<n; j++){
                // top row and left col
                if(i==0 || j==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }


    /** 
     * SAME AS ABOVE UNIQUE PATHS
     * 1 ONLY DIFF IS WHEN FILLING FIRST ROW AND COL
     * 2 AND WHEN 1 IS SEEN MARK dp[i][j] = 0
     * 3 CHECK dp[0][0] FOR OBSTACLES TOO
     * 
    */
    // [[1]]
    // https://leetcode.com/problems/unique-paths-ii/submissions/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // boundary
        if(m==0 || n==0) return 0;
        int[][] dp = new int[m][n];
        
        // 
        dp[0][0] = obstacleGrid[0][0] == 1?0:1;
        
        //row
        for(int i =1; i<n; i++){
            if(obstacleGrid[0][i] == 1) dp[0][i] = 0;
            else dp[0][i] = dp[0][i-1];
        }

        // col
        for(int i =1; i<m; i++){
            if(obstacleGrid[i][0] == 1) dp[i][0] = 0;
            else dp[i][0] = dp[i-1][0];
        }
        
        for(int i = 1; i<m; i++){
            for(int j =1; j<n; j++){
                if(obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }


    //////////////////////////////////////// BFS
    // q size and global counter
    /** 
     * DIFFERENCE B/W BFS IN A MATRIX AND A GRAPH :
     * 
     * 1 THE ISSAFE METHOD CHANGES
     * IN MATRIX ONLY NODES IN 4 DIRECTIONS, IN GRAPH ALL ADJACENT EDGES
     * 
     * 2 IN BOTH, NODES ARE ADDED TO Q ONLY IF DIST IS GREATER
     * (USING PREV, TO AVOID INF LOOPS) OR IS UNVISITED
     *
    */

    // NORMAL BFS
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

    int BFSMatrix(int[][] graph) {
        int m = graph.length; int n = graph[0].length;
        int[][] visited = new int[m][n];

        // starting from 0
        BFSNode node = new BFSNode(0, 0, graph[0][0]);

        Deque<BFSNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(node);
        int distance = 0;

        int[] row = {0,-1,1,0};
        int[] col = {-1,0,0,1}; 
        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            for(int i =0; i<size; i++){
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
            distance++;
        }
        return distance;
    }

    boolean isSafeBFS(int[][] graph, int row, int col, int[][] visited){
        if(row>=0 && row<graph.length
        && col>=0 && col<graph[0].length
        // only checking if unvisited
        && visited[row][col]!=0) return true;
        return false;
    }

    // BFS (q size and global counter)
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
            int size= q.size();
           
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
        // Utility.printMatrix(matrix);
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
	 * 1 VISITED ARRAY
	 * 2 AFTER ISSAFE(VALID INDEXES AND UNVISITED), 
	 * MARK VISITED AND ADD TO Q.
	 * 
	 * WHY DOES THIS WORK? ONLY UPDATING MAX?
	 * WHAT OIF THERE IS A SHORTER PATH?
	 * 
	 * IN BFS THERE CAN NEVER BE A CONFLICT OF SHORTER PATH.
	 * AS ALL NODES ARE ADDED TO Q IN SORTED ORDER.
	 * SO NODES WHICH ARE CLOSER WILL UPDATE THEIR NEIGHBOURS
	 * IN DFS THERE CAN BE CONFLICT, BUT HERE ALL EQUIDISTANT
	 * NOES ARE UPDATED BEFORE ANY NODE WITH GREATER DISTANCE.
	 * 
	 * we add alls 2s, so all 2s will update neighbouring 1s at 
	 * the same time. so there won't be a case where a 1 farther
	 * is updated first.
     * 
     * imp : mark visited index and use size
	 * 
	 */
	// https://leetcode.com/problems/rotting-oranges/
	// NORMAL BFS
	class Node{
        int row, col;
        Node(int r, int c){
            this.row = r; this.col = c;
        }
    }
    public int orangesRotting(int[][] grid) {
        Deque<Node> q = new LinkedList<>();
        int m = grid.length; int n = grid[0].length;
        int counter = -1;
        
        int[] rows = new int[]{0, -1, 1, 0};
        int[] cols = new int[]{-1, 0, 0, 1};
        
        for(int i =0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 2) q.addLast(new Node(i, j));
            }
        }
        
        while(q.size()!=0){
            // use size
            int size = q.size();
            for(int i =0; i<size; i++){
                Node curr = q.removeFirst();
                int row = curr.row; int col = curr.col;
                for(int k =0; k<rows.length; k++){
                    if(isSafe(grid, row+rows[k], col + cols[k])) {
                        // mark
                        grid[row+rows[k]][col + cols[k]] = 2;
                        q.addLast(new Node(row+rows[k], col+cols[k]));
                    }
                }
            }
            counter++;
        }
        for(int i =0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 1) return -1;
            }
        }
        return counter<0?0:counter;
    }
    
    boolean isSafe(int[][] grid, int row, int col){
        if(row>= 0 && row<grid.length
          && col>=0 && col<grid[0].length
          && grid[row][col] == 1) return true;
        return false;
    }
    
    // https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleHard/317.html
    /** 
     * 1 RUN BFS FROM ALL BUILDINGS.
     * 2 STORE DIST IN A NEW 2D MATRIX (DISTANCE)
     * 3 ALSO STORE IF THAT INDEX IS REACHABLE FROM THE BUILDINGS (REACH)
     * 4 TRAVERSE THE DISTANCE MATRIX AND IF REACH == NO OF BUILDINGS
     * STORE THE INDEX OF MIN DIST
    */

    // all paths from a to b 
    // https://www.geeksforgeeks.org/print-paths-given-source-destination-using-bfs/

    // https://leetcode.com/problems/shortest-distance-from-all-buildings/


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

    
    /*
     * POINTS :
     * 1 USE 4 VARS rS, rE, cS, cE
     * 2 BASE CONDITION while(rS <= rE && cS<=cE)
     * 
     * 3 MOVE FROM CS TILL CE, NEXT START FROM RS, SO RS++
     * the row or col from where the next print will start is 
     * changed.
     * 
     * 4 ENSURE rS<rE AND cS<cE, ELSE CONTINUE
     * 
     */
    // https://leetcode.com/problems/spiral-matrix/
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length; int n = matrix[0].length;
        int rS = 0; int cS = 0; int rE = m-1; int cE = n-1;
        List<Integer> res = new ArrayList<>();
        
        while(rS <= rE && cS<=cE){
            for(int i = cS; i<=cE; i++){
                res.add(matrix[rS][i]);    
            }
            rS++;

            for(int i = rS; i<=rE; i++){
                res.add(matrix[i][cE]);    
            }
            cE--;

            if (rS > rE) continue;
            for(int i = cE; i>=cS; i--){
                res.add(matrix[rE][i]);    
            }
            rE--;

            if (cS > cE) continue;
            for(int i = rE; i>=rS; i--){
                res.add(matrix[i][cS]);    
            }
            cS++;
        }
        return res;
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

    /**
     * 90 DEGREE ROTATION
     * 1 FIRST TRANSPOSE (i<j && i!=j)
     * 2 THEN FLIP THE LEFT AND RIGHT BOUNDARIES TILL MIDDLE
     * i from 0 till m-1
     * j<n/2
     * swap([i][j] and [i][n-1-j])
     */
    // https://leetcode.com/problems/rotate-image/
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // tranpose
        for(int i =0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i!=j && i<j){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;    
                } 
            }
        }
        // flip left and right till middle
        for(int i =0; i<m; i++){
            for(int j =0; j<n/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }



    /** 
     * POINTS :
     * 1 USE A DP OF SIZE m+1, n+1 AS IT'S 
     * DIFFICULT TO HANDLE CASES OF SINGLE ROW CONTAINING 1
     * 2 if(matrix[i-1][j-1] == '1')
     * 3 min of all three + 1;
     * 4 USE MAX VAR
     *
    */
    // https://leetcode.com/problems/maximal-square/
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if(m==0) return 0;
        
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];//
        
        int result = 0;
        for(int i = 1; i<=m; i++){ //1
            for(int j = 1; j<=n; j++){ //2
                if(matrix[i-1][j-1] == '1'){//3
                    dp[i][j] = 
                        Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;//
                    result = Math.max(result, dp[i][j]); //4 
                }
            }
        }
        System.out.println(result);
        return result*result;
    }

    
    //////////////////////////////////////////
    // https://practice.geeksforgeeks.org/problems/row-with-max-1s0023/1
    int rowWithMaximumOnes(int[][] matrix){
        int m = matrix.length;
        int min = -1; int first = Integer.MAX_VALUE;
        for(int i = 0; i<m; i++){
            int currFirst = findFirstOne(matrix, i);
            if(currFirst!=-1 && currFirst<first){
                first = currFirst;
                min = i;
            }
        }
        System.out.println("ones "+min);
        return min;
    }

    int findFirstOne(int[][] arr, int row){
        int n = arr[0].length;
        int lo = 0; int hi = n-1;
        int index = -1;
        while(lo<=hi){
            int mid = lo+ (hi-lo)/2;
            if(arr[row][mid] == 1){
                index = mid;
                hi = mid-1; // hi = mid causes TLE
            }
            else {
                lo = mid+1;
            }
        }
        return index;
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

    // TRY YSING BINARY SEARCH
    // duplicates too
    // [[1,1]], 2
    // [[1,1],[2,2]], 3
    // https://leetcode.com/problems/search-a-2d-matrix/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0) return false;
                
        int n = matrix[0].length;
        if(m==1 && n==0) return false;
        if(m==1 && n==1) return matrix[0][0] == target;
        
        if(m==1) {
            int i = 0; 
            while(i<n){
                if(matrix[0][i] == target) return true;
                i++;
            } 
            return false;
        }
        if(n==1) {
            int i = 0; 
            while(i<m){
                if(matrix[i][0] == target) return true;
                i++;
            } 
            return false;
        }
        int i = m-1;  int j = n-1;
        boolean res = false;
        while(i>=0 && matrix[i][j]>=target){
            i--;
        }
        if(i<m-1)i++;
        while(j>=0 && matrix[i][j]>= target){
            if(matrix[i][j] == target) res= true;
            j--;
            if(j<0) return res;
        }
        return res;
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

        int[][] onesArray = 
                            {{0,0,0,0,1},
                             {0,0,0,0,0},
                             {0,0,0,0,0},
                             {0,0,0,0,0}};
                            // {{0,0,0,1,1},
                            //  {0,1,1,1,1},
                            //  {0,0,1,1,1},
                            //  {1,1,1,1,1},
                            //  {0,0,0,0,0}};

        // System.out.println("row index of max1s " + matrix.rowWithMax1s(onesArray));
        // matrix.rowWithMaximumOnes(onesArray);

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

        int pInf = Integer.MAX_VALUE;
        int[][] wallsAndGates =  { { pInf, -1, 0, 0 }, 
                                { pInf, pInf, pInf, -1 }, 
                                { pInf, -1, pInf, -1 }, 
                                { 0, -1, pInf, pInf } };
        // matrix.wallsAndGatesKevin(wallsAndGates);
        // matrix.wallsAndGates1(wallsAndGates);
        // matrix.wallsAndGatesBFS(wallsAndGates);

        // matrix.antiDiaPrint(twoDimArr);

        int[][] matRowColSorted = 
        { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };
        int num = 100;

        // System.out.println(matrix.searchRowColSortedMatrix(matRowColSorted, num));

        // matrix.dfs(twoDimArr);

        int[][] path = {{0,1,1,1},{0,0,0,1},{1,1,0,0}, {0,0,0,0}};
        // matrix.allPathsFromAtoB(path, 2,  3);

        char[][] maximalRectangle = {{'1','0','1','0','0'},
                     {'1','0','1','1','1'},{'1','1','1','1','1'},
                     {'1','0','0','1','0'}};

        // matrix.maximalRectangle(maximalRectangle);

            char[] ch = new char[2];
            System.out.println("val is "+ch[0]);
            

    }

}