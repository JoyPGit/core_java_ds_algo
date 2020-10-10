import java.util.*;

class Backtrack1 {


    /**
     * BACKTRACKING technique is to add and after processing, 
     * remove the element same when using ARRAYLIST
     */

    // somewhere around March
    // all subsets using list of lists
    ArrayList<ArrayList<Integer>> subsetsList(int[] nums) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        // ArrayList
        subsetsHelper(lists, new ArrayList<>(), nums, 0);
        // printListOfLists(lists);
        System.out.println(lists);
        return lists;
    }

    // /https://stackoverflow.com/questions/12757841/are-arrays-passed-by-
    // value-or-passed-by-reference-in-java
    void subsetsHelper(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> resultList, 
    int[] nums, int start) {
        // System.out.println("resultList initiated "+resultList);
        // if(resultList instanceof ArrayList)System.out.println("resultList type
        // is ArrayList and size is " +resultList.size());
        /**
         * initially it's size is 0, so even though we pass a new arraylist, this
         * resultlist is holding nothing
         */

        // this constructor copies the empty list initialized above
        /**
         * empty list is copied for the first time and added resultlist refers to the
         * line 22 list always so a new list is copied from the existing resultlist and
         * added, but all operations of addition are done in the line 22 arraylist
         */

        /**
         * Some research. What is an Integer? And why cannot we use int in an ArrayList?
         * An Integer is a reference type (a class). An int is a value. And: The
         * ArrayList requires, in its implementation, a reference type. So int is not
         * allowed.
         * 
         * Quote: The Integer class wraps a value of the primitive type int in an
         * object. An object of type Integer contains a single field whose type is int
         * (Java Documentation).
         */

        /**
         * this is for subsets equalling a sum
         * 
         * int sum = 0; for(int j =0; j< resultList.size(); j++){ sum +=
         * resultList.get(j); if(sum == 4 || sum == 5) System.out.println("the set is
         * "+resultList); }
         */

        list.add(new ArrayList<>(resultList));
        for (int i = start; i < nums.length; i++) {
            // add element
            resultList.add(nums[i]);
            // Explore
            // System.out.println(resultList);
            /** this is for k length subsets */
            // if(resultList.size() == 2) System.out.println("size 2 subsets "+ resultList);

            subsetsHelper(list, resultList, nums, i + 1);
            // remove the last
            resultList.remove(resultList.size() - 1);
        }
    }

    // using Iterator, else directly sysout(list) works
    // void printListOfLists(List<List<Integer>> subsets) { //List works too
    void printListOfLists(ArrayList<ArrayList<Integer>> lists) {
        for (ArrayList<Integer> subset : lists) {
            System.out.println(subset);
        }
    }

    void printList(ArrayList<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    /**
     * POINTS :
     * 1 run 2 loops, row+1 style doesn't work here
     * 2 return after for loop
     * 3 isSafe first and then recursive call
     * 4 imp row offset and col offset
     * board[3*(row/3) + i/3][3*(col/3) + i%3] == c
     * 
     */
    // https://leetcode.com/problems/sudoku-solver
    public void solveSudoku(char[][] board) {
        helper(board);
    }
    
    boolean helper(char[][] board){
        for(int i = 0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                
                if(board[i][j] == '.'){
                    for(char c = '1'; c<='9'; c++){
                        if(isSafe(board, i, j, c)) {
                            board[i][j] = c;
                            if(helper(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    //for loop over 
                    return false;
                }
            }
        }
        //for loop over, return true
        return true;
    }
    
    boolean isSafe(char[][] board, int row, int col, char c){
        // row check
        for(int i=0; i<board.length; i++){
            if(i == col) continue;
            if(board[row][i] == c) return false;
        }
        
        // col check
        for(int i=0; i<board[0].length; i++){
            if(i == row) continue;
            if(board[i][col] == c) return false;
        }
        
        // sub grid check
        /** 
         * logic : 
         * row = 7, col = 1
         * row/3 = 2, col/3 = 0
         * 3*(row/3) = 6, 3*(col/3) = 0
         * subgrid is identified
         * 
         * i from 0 till 8
         * i  i/3  i%3
         * 0   0    0
         * 1   0    1
         * 2   0    2
         * 3   1    0
         * 4   1    1
         * 5   1    2
         * .
         * .
         * .
         * 
         * this way the co-ordinates traversed are 
         * (6,0), (6,1), (6,2),
         * (7,0), (7,2) ...
         * 
         * so i/3 helps to keep row constant while i%3 keeps incrementing col by 1
         */
        for(int i =0; i<board.length; i++){
            // System.out.println("row "+row+" col "+col);
            // System.out.println(3*(row/3) + i/3);
            // System.out.println(3*(col/3) + i%3);
            if(board[3*(row/3) + i/3][3*(col/3) + i%3] == c) return false;
        }
        return true;
    }
    

    // 28 apr haspathsum

    //////////////////////////////////////////
    void nQueen4jul(int n) {
        int[][] board = new int[n][n];
        helper(board, 0);
    }

    void helper(int[][] board, int row) {
        if (row == board.length) {
            System.out.println("found");
            printMatrix(board);
            return;
        } else {
            for (int i = 0; i < board.length; i++) {
                board[row][i] = 1;
                if (isValid(board, row, i)) {
                    helper(board, row + 1);
                }
                board[row][i] = 0;
            }
        }

    }

    boolean isValid(int[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            if (i == col)
                continue;
            if (board[row][i] == 1)
                return false;
        }
        for (int i = 0; i < board.length; i++) {
            if (i == row)
                continue;
            if (board[i][col] == 1)
                return false;
        }
        int i = 0;
        int j = 0;

        // checking upper '\' direction 
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (i == row && j == col)
                continue;
            if (board[i][j] == 1)
                return false;
        }

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--) {
            if (i == row && j == col)
                continue;
            if (board[i][j] == 1)
                return false;
        }

        // checking '/' direction
        for (i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (i == row && j == col)
                continue;
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }
    // https://leetcode.com/problems/queens-that-can-attack-the-king/

    // 6 june
    boolean solveRatMaze(int[][] maze) {
        int n = maze.length;
        int[][] sol = new int[n][n];

        if (!solveRatMazeUtil(maze, sol, 0, 0))
            return false;
        printMatrix(sol);
        System.out.println("sol found");
        return true;
    }

    boolean solveRatMazeUtil(int[][] maze, int[][] sol, int row, int col) {
        if (isSafeRatMaze(maze, row, col)) {
            sol[row][col] = 1;

            if (row == maze.length - 1 && col == maze[0].length - 1)
                return true;
            if (solveRatMazeUtil(maze, sol, row + 1, col))
                return true;
            if (solveRatMazeUtil(maze, sol, row, col + 1))
                return true;

            printMatrix(sol);

            System.out.println("row " + row + ", col " + col);

            sol[row][col] = 0;

            printMatrix(sol);
            return false;
        }
        return false;
    }

    boolean isSafeRatMaze(int[][] grid, int rowIndex, int colIndex) {
        return (rowIndex >= 0 && colIndex >= 0 
        && rowIndex < grid.length 
        && colIndex < grid[0].length
        && grid[rowIndex][colIndex] == 1);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // multiple jumps allowed
    boolean solveNJumpsRatMaze(int[][] maze) {
        int n = maze.length;
        int[][] sol = new int[n][n];
        
        System.out.println("in n rat multiple jumps");
        if (!solveNRatMazeUtil(maze, sol, 0, 0))
            return false;
        utilCustom.Utility.printMatrix(sol);
        System.out.println("sol found");
        return true;
    }

    boolean solveNRatMazeUtil(int[][] maze, int[][] sol, int row, int col) {
        if (isNSafeRatMaze(maze, row, col)) {
            sol[row][col] = 1;
            System.out.println("row " + row + ", col " + col + 
            " maze index value " + maze[row][col]);

            if (row == maze.length - 1 && col == maze[0].length - 1)
                return true;

            for (int i = 1; i <= maze[row][col]; i++) {
                if (solveNRatMazeUtil(maze, sol, row + i, col))
                    return true;
                if (solveNRatMazeUtil(maze, sol, row, col + i))
                    return true;
            }

            sol[row][col] = 0;// backtrack

            return false;
        }
        return false;
    }

    boolean isNSafeRatMaze(int[][] grid, int rowIndex, int colIndex) {
        System.out.println("rowIndex " + rowIndex + ", colIndex " + colIndex);
        return (rowIndex >= 0 && colIndex >= 0 
        && rowIndex < grid.length && colIndex < grid[0].length
        && grid[rowIndex][colIndex] != 0);
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
     */
    boolean foundMinColor = false;

    void mColoring(int[][] arr) {
        int[] color = new int[arr.length];
        mcolorUtil(arr, 0, color);
    }

    void mcolorUtil(int[][] arr, int vertex, int[] color) {
        if (vertex == arr.length) {
            print1DMatrix(color);
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


    // https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
    int knightMoveCount = 1;
    void knightsTour(int[][] board, int startRow, int startCol){
        int n = board.length;
        int[][] visited = new int[n][n];
        dfsKnight(board, visited, startRow, startCol);
        System.out.println("knight's tour matrix : ");
    }

    void dfsKnight(int[][] board, int [][] visited, int r, int c){
        if(isSafeKnight(board, visited, r, c)){
            visited[r][c] = knightMoveCount++;
            if(r== board.length-1 && c== board[0].length-1 && knightMoveCount == 64) return;
            dfsKnight(board, visited, r+2, c+1);
            dfsKnight(board, visited, r+2, c-1);
            dfsKnight(board, visited, r-2, c+1);
            dfsKnight(board, visited, r-2, c-1);
            dfsKnight(board, visited, r+1, c-2);
            dfsKnight(board, visited, r-1, c-2);
            dfsKnight(board, visited, r+1, c+2);
            dfsKnight(board, visited, r-1, c+2);
        }
    }

    boolean isSafeKnight(int[][] board, int [][] visited, int r, int c){
        if(r>=0 && r<board.length
        && c>=0 && c<board[0].length
        && visited[r][c]==0) return true;
        return false;
    }

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
    // https://leetcode.com/problems/word-search/submissions/
    public boolean exist(char[][] board, String word) {
        // char[] ch = word.toCharArray();
        // boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i =0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int row, int col, String word, int index){
        if(isSafe(board, row, col, word, index)){
            if(index == word.length()-1 && word.charAt(index) == board[row][col]) {
                // System.out.println("in here"); 
                return true;
            }
            char temp = board[row][col];
            board[row][col] = ' '; // 4 not using visited array
            boolean found =  dfs(board, row-1, col, word, index+1) 
            || dfs(board, row+1, col, word, index+1) 
            || dfs(board, row, col-1, word, index+1)
            || dfs(board, row, col+1, word, index+1);
            
            board[row][col] = temp; // 3 backtracking
            return found;
        }
        return false;
    }
    
    
    boolean isSafe(char[][] board, int row, int col, String word, int index){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == word.charAt(index)) return true;
        return false;
    }
    

    // https://leetcode.com/problems/queens-that-can-attack-the-king/
    // https://leetcode.com/problems/knight-probability-in-chessboard/discuss/113954/Evolve-from-recursive-to-dpbeats-94
    public static void main(String[] args) {
        Backtrack1 solbacktrack = new Backtrack1();
        int[][] problemArr = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        // solbacktrack.solveNQueenProblem(problemArr);
        // solbacktrack.printMatrix(problemArr);

        // solbacktrack.nQueenProblem(problemArr, 0);
        // solbacktrack.nQueen4jul(8);

        int maze[][] = { { 1, 1, 0, 0 }, { 0, 2, 0, 1 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 } };
        // solbacktrack.solveRatMaze(maze);
        // solbacktrack.solveNJumpsRatMaze(maze);

        int[][] grid = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        // solbacktrack.sudoku(grid);

        int[][] grid3x3 = { { 3, 0, 6 }, { 5, 2, 0 }, { 0, 8, 0 } };
        // solbacktrack.sudoku3x3(grid3x3);

        int[][] graph = { { 0, 1, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 1 }, { 1, 0, 1, 0 }, };

        // solbacktrack.mColoring(graph);

        int[] subset = { 1, 2, 3 };
        // solbacktrack.subsetsList(subset);

        int[][] chessBoard = new int[8][8];
        // solbacktrack.knightsTour(chessBoard, 4, 6);

        char[][] sudoku = 
        {{'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}};
        solbacktrack.solveSudoku(sudoku);
    }

}