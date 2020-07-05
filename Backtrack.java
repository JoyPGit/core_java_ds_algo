import java.net.Inet4Address;
import java.util.*;

class Backtrack {

    void printMatrix(int[][] arr){
        for(int i =0; i<arr.length; i++){
            for(int j =0; j< arr[0].length; j++){
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println(" ");
        }
    }

    void solveNQueenProblem(int[][] arr){
        int rowMax = arr.length;
        int colMax = arr[0].length;
        int row = 0; int col = 0;
        
        arr[row][col] = 1;
        while (row < rowMax ){
            while (col < colMax ) {
                if (arr[row][col] == 1) {
                    markRow(arr, row+1, col, rowMax);
                    markCol(arr, row, col+1, colMax);
                    markDia(arr, row+1, col+1, rowMax, colMax);
                }
                if (arr[row][col] == 0){
                    arr[row][col] = 1;
                    markRow(arr, row+1, col, rowMax);
                    markCol(arr, row, col+1, colMax);
                    markDia(arr, row+1, col+1, rowMax, colMax);
                }

                if (arr[row][col] == -1 ){
                    //
                }
                
                col++;

                System.out.println("done once");
                System.out.println("row "+ row +" "+"col " +col);
            }
            row++; col = 0;
            System.out.println("outside row "+ row +" "+"col " +col); 
        }    
    }

    void markRow(int[][] arr, int row, int column, int rowMaximum){
        while(row<rowMaximum){
            if(arr[row][column]!=1){
                arr[row][column] = -1;
            }
            row++;
        }
        if(row == rowMaximum-1) arr[row][column] = -1;
        System.out.println("done markrow");
        printMatrix(arr);
    }

    void markCol(int[][] arr, int row, int column, int colMaximum){
        while(column<colMaximum){
            if(arr[row][column]!=1){
                arr[row][column] = -1;
            }
            System.out.println(arr[row][column]);
            column++;
        }
        // if(column == colMaximum-1) arr[row][column] = -1;
        System.out.println("done markcol");
        printMatrix(arr);
    }

    void markDia(int[][] arr, int row, int column, int rowMaximum, int colMaximum){
        while(row<(rowMaximum-1) && column < (colMaximum-1)){
            if(arr[row][column]!=1)arr[row][column] = -1;
            row++; column++;
        }
        arr[row][column] = -1;
        System.out.println("done markdia");
        printMatrix(arr);
    }


    ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        // ArrayList
        subsetsHelper(lists, new ArrayList<>(), nums, 0);
        printListOfLists(lists);
		return lists;
	}
 
    // /https://stackoverflow.com/questions/12757841/are-arrays-passed-by-value-or-passed-by-reference-in-java
	void subsetsHelper(ArrayList<ArrayList<Integer>> list , ArrayList<Integer> resultList, int [] nums, int start){
        // System.out.println("resultList initiated "+resultList);
        // if(resultList instanceof ArrayList)System.out.println("resultList type is ArrayList and size is " 
        // +resultList.size());
        /**initially it's size is 0, so even though we pass a new arraylist, this resultlist 
         * is holding nothing */

         /**Some research. What is an Integer? And why cannot we use int in an ArrayList? 
          * An Integer is a reference type (a class). An int is a value.
            And: The ArrayList requires, in its implementation, a reference type. So 
            int is not allowed.

            Quote: The Integer class wraps a value of the primitive type int in an object. 
            An object of type Integer contains a single field whose type is int (Java Documentation). */

        int count =1;
        list.add(new ArrayList<>(resultList));
		for(int i = start; i < nums.length; i++){
           // add element
            resultList.add(nums[i]);
           // Explore
			subsetsHelper(list, resultList, nums, i + 1);
           // remove the last
            resultList.remove(resultList.size() - 1);
		}
    }
    
    void printListOfLists(ArrayList<ArrayList<Integer>> lists){
        for (ArrayList<Integer> subset: lists) {
            // System.out.println("in print, size " + subset.size());
            System.out.println(subset);
        }
    }


    void nQueenProblem(int[][] arr, int r){
    
        if(r==arr.length){
            for(int i =0; i<arr.length; i++){
                for(int j =0; j<arr[0].length; j++){
                    System.out.print(arr[i][j]+", ");
                }
                System.out.println();
            }
        }

        for(int i =0; i<arr.length; i++){
            if(isSafe(arr,r,i)){
                arr[r][i]=1;
                nQueenProblem(arr, r+1);
            }
        }
    }

    boolean isSafe(int[][] arr, int row, int colIndex){

        for(int i =0; i<arr.length; i++){
            if(arr[i][colIndex]==1){
                return false;
            }
        }

        // for(int i =0; i<arr.length; i++){
        //     if(arr[row][i]==1){
        //         return false;
        //     }
        // }

        for(int i =0; i<arr.length; i++){
            if(row+i<arr.length && (colIndex+i)<arr[0].length){
                if(arr[row+i][colIndex+i]==1){
                    return false;
                }
            }
            if(row-i>=0 && (colIndex-i)>=0){
                if(arr[row-i][colIndex-i]==1){
                    return false;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (row + i < arr.length && colIndex - i >= 0) {
                if (arr[row + i][colIndex - i] == 1) {
                    return false;
                }
            }
            if(row-i>=0 && (colIndex+i)<arr[0].length){
                if(arr[row-i][colIndex+i]==1){
                    return false;
                }
            }
        }

        return true;
    }


    void nQueen4jul(int n) {
        int[][] board = new int[n][n];
        
        helper(board, 0);
        // ArrayList<ArrayList<String>> queenBoard = new ArrayList<>();
        
        // for(int i =0; i<n; i++){
        //     // List[i] = new ArrayList<>();
        //     for(int j =0; j<n; j++){
        //         if(board[i][j] == 1) queenBoard.get(i).add("Q");
        //         else queenBoard.get(i).add(".");
        //     }
        // }
        // return queenBoard;
    }
    
    void helper(int[][] board, int row){
        if(row == board.length) {
            System.out.println("found");
            printMatrix(board);
            return;
        }else{
            for(int i = 0; i<board.length ; i++){
                board[row][i] = 1;
                // System.out.println("in here");
                // printMatrix(board);
                if(isValid(board, row, i)){
                    // printMatrix(board);
                    helper(board, row+1);                
                } 
                board[row][i] = 0;
            }
        }
        
    }
    
    boolean isValid(int[][] board, int row, int col){
        for(int i =0; i<board.length; i++){
            if(i==col) continue;
            if(board[row][i] == 1) return false;
        }
        for(int i =0; i<board.length; i++){
            if(i == row) continue;
            if(board[i][col] == 1) return false;
        }
        int i =0; int j =0;
        // while(row<board.length && row>=0
        //      && col<board.length && col>=0){
        //     if(board[row+1][col-1]==1) return false;
        //     row++; col--;
        // }
        
        // while(row<board.length && row>=0
        //      && col<board.length && col>=0){
        //     if(board[row+1][col+1]==1) return false;
        //     row++; col++;
        // }
        // while(row<board.length && row>=0
        //      && col<board.length && col>=0){
        //     if(board[row-1][col-1]==1) return false;
        //     row--; col--;
        // }
        // while(row<board.length && row>=0
        //      && col<board.length && col>=0){
        //     if(board[row-1][col+1]==1) return false;
        //     row--; col++;
        // }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if(i == row && j == col) continue;
            if (board[i][j] == 1) return false;     
        }
           
  
        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--) {
            if(i == row && j == col) continue;
            if (board[i][j] == 1) return false; 
        }

        for (i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if(i == row && j == col) continue;
            if (board[i][j] == 1) return false; 
        }
        
        return true;
    }
    
    //6 june
    boolean solveRatMaze(int[][] maze){
        int n = maze.length;
        int[][] sol = new int[n][n];

        if(!solveRatMazeUtil(maze, sol, 0 ,0)) return false;
        printMatrix(sol);
        System.out.println("sol found");
        return true;
    }

    boolean solveRatMazeUtil(int[][] maze, int[][] sol, int row, int col){
        if(isSafeRatMaze(maze, row, col)){
            sol[row][col] =1;

            if(row == maze.length-1 && col == maze[0].length-1) return true;
            if(solveRatMazeUtil(maze, sol, row+1, col)) return true;
            if(solveRatMazeUtil(maze, sol, row, col+1)) return true;

            printMatrix(sol);

            System.out.println("row "+row+", col "+ col);

            sol[row][col] =0;
            
            printMatrix(sol);
            return false;
        }
        return false;
    }

    boolean isSafeRatMaze(int[][] grid, int rowIndex, int colIndex){
        return (rowIndex>=0 && colIndex>=0
        && rowIndex<grid.length && colIndex< grid[0].length
        && grid[rowIndex][colIndex]==1);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////
    //multiple jumps allowed
    boolean solveNRatMaze(int[][] maze){
        int n = maze.length;
        int[][] sol = new int[n][n];

        System.out.println("in n rat multiple jumps");
        if(!solveNRatMazeUtil(maze, sol, 0 ,0)) return false;
        printMatrix(sol);
        System.out.println("sol found");
        return true;
    }

    boolean solveNRatMazeUtil(int[][] maze, int[][] sol, int row, int col){
        if(isNSafeRatMaze(maze, row, col)){
            sol[row][col] =1;
            System.out.println("row "+row+", col "+ col +" maze index value "+maze[row][col]);
            if(row == maze.length-1 && col == maze[0].length-1) return true;

            for(int i =1; i<=maze[row][col]; i++){
                if(solveNRatMazeUtil(maze, sol, row+i, col)) return true;
                if(solveNRatMazeUtil(maze, sol, row, col+i)) return true;
            }

            sol[row][col] =0;
        
            return false;
        }
        return false;
    }

    boolean isNSafeRatMaze(int[][] grid, int rowIndex, int colIndex){
        System.out.println("rowIndex "+rowIndex+", colIndex "+ colIndex);
        return (rowIndex>=0 && colIndex>=0
        && rowIndex<grid.length && colIndex< grid[0].length
        && grid[rowIndex][colIndex]!=0);
    }


    //sudoku
    boolean sudoku(int[][] matrix){
        int i, j;
        for(i =0; i<matrix.length; i++){
            // for(j=0; j<matrix[0].length; j++ ){
                if(sudokuHelper(matrix, i, 0)) return true;
            // }
        }
        
        printMatrix(matrix);
        return false;
    }

    boolean sudokuHelper(int[][] matrix, int row, int col){
        int i,j;
        // System.out.println("row "+row+", col "+col);
        if(row == matrix.length && col == matrix[0].length) return true;
        
        //for assigning value
        for (i = 1; i <= 9; i++) {

            if (isSafeSudoku(matrix, row, col)) {
                System.out.println("is safe");
                if(matrix[row][col]==0) {
                    matrix[row][col] = i;

                    // for (j = 1; j <= matrix[0].length; j++) {
                    j = col; j++;
                    System.out.println("j "+ j);
                    sudokuHelper(matrix, row, j);
                    // }
                    // sudokuHelper(matrix, row + 1, col);
                    matrix[row][col] = 0;
                } 
                else {
                    j = col; j++;
                    System.out.println("j line 302 "+ j);
                    sudokuHelper(matrix, row, j);
                }   

            }
            
        }
        return false;
    }

    boolean isSafeSudoku(int[][] matrix, int row, int col){
        int i,j; 
        if(row<0 || row > matrix.length-1 
        || col <0 || col > matrix[0].length-1) return false;
        
        // System.out.println("rowIndex "+row+", colIndex "+ col);        

        int curr = matrix[row][col];

        for(i=0; i<matrix.length; i++){
            if(i!=row){
                if(matrix[i][col] == curr) return false;
                // System.out.println("line 318");
            }
        }
        for(i=0; i<matrix.length; i++){
            if(i!=col){
                if(matrix[row][i] == curr) return false;
                // System.out.println("line 324");
            }
        }

        // int rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;

        // if(row ==0 || row == 1|| row ==2) rowStart = 0; rowEnd = 2;
        // if(row ==3 || row == 4|| row ==5) rowStart = 3; rowEnd = 5;
        // if(row ==6 || row == 7|| row ==8) rowStart = 6; rowEnd = 8;
        // if(col ==0 || row == 1|| row ==3) colStart = 0; colEnd = 2;
        // if(row ==3 || row == 4|| row ==5) colStart = 3; colEnd = 5;
        // if(row ==6 || row == 7|| row ==8) colStart = 6; colEnd = 8;

        // for(i = rowStart; i<=rowEnd; i++){
        //     for(j = colStart; j<=colEnd; j++){
        //         if(i== row && j ==col) break;
        //         if(matrix[i][j] == curr) return false;
        //     }
        // }
        // System.out.println("line 341");
        return true;
    }

    boolean sudoku3x3(int[][] matrix){
        int i, j;
        for(i =0; i<matrix.length; i++){
            for(j=0; j<matrix[0].length; j++ ){
                if(matrix[i][j]==0){
                    if(sudokuHelper3x3(matrix, i, j)) return true;
                }
            }
        }
        
        printMatrix(matrix);
        return true;
    }

    boolean sudokuHelper3x3(int[][] matrix, int row, int col){
        int i,j;
        // System.out.println("row "+row+", col "+col);
        if(row == matrix.length && col == matrix[0].length) {
            printMatrix(matrix);
            return true;
        }
        
        //for assigning value
        for (i = 1; i <= 3; i++) {

            if (isSafeSudoku3x3(matrix, row, col)) {
                // System.out.println("is safe");
                System.out.println("rowIndex "+row+", colIndex "+ col);
                    matrix[row][col] = i;

                    // for (j = 1; j <= matrix[0].length; j++) {
                    j = col; j++;
                    System.out.println("j "+ j);
                    if(sudokuHelper3x3(matrix, row, j)) return true;
                    // }
                    // sudokuHelper(matrix, row + 1, col);
                    matrix[row][col] = 0;
            }
            
        }
        return false;
    }

    boolean isSafeSudoku3x3(int[][] matrix, int row, int col){
        int i,j; 
        if(row<0 || row > matrix.length-1 
        || col <0 || col > matrix[0].length-1) return false;
        
        // System.out.println("rowIndex "+row+", colIndex "+ col);        

        // int curr = matrix[row][col];

        // for(i=0; i<matrix.length; i++){
        //     if(i!=row){
        //         if(matrix[i][col] == curr) return false;
        //         // System.out.println("line 318");
        //     }
        // }
        // for(i=0; i<matrix.length; i++){
        //     if(i!=col){
        //         if(matrix[row][i] == curr) return false;
        //         // System.out.println("line 324");
        //     }
        // }

        // int rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;

        // if(row ==0 || row == 1|| row ==2) rowStart = 0; rowEnd = 2;
        // if(row ==3 || row == 4|| row ==5) rowStart = 3; rowEnd = 5;
        // if(row ==6 || row == 7|| row ==8) rowStart = 6; rowEnd = 8;
        // if(col ==0 || row == 1|| row ==3) colStart = 0; colEnd = 2;
        // if(row ==3 || row == 4|| row ==5) colStart = 3; colEnd = 5;
        // if(row ==6 || row == 7|| row ==8) colStart = 6; colEnd = 8;

        // for(i = rowStart; i<=rowEnd; i++){
        //     for(j = colStart; j<=colEnd; j++){
        //         if(i== row && j ==col) break;
        //         if(matrix[i][j] == curr) return false;
        //     }
        // }
        // System.out.println("line 341");
        return true;
    }
    
    public static void main(String[] args) {
        Backtrack solbacktrack = new Backtrack();
        int[][] problemArr = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        // solbacktrack.solveNQueenProblem(problemArr);
        // solbacktrack.printMatrix(problemArr);

        // solbacktrack.nQueenProblem(problemArr, 0);
        solbacktrack.nQueen4jul(8);
        // int[] nums= {1,2,3};
        // ArrayList<ArrayList<Integer>> subSetsList = solbacktrack.subsets(nums);
        int maze[][] = { { 1, 1, 0, 0 }, { 0, 2, 0, 1 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 } }; 
        // solbacktrack.solveRatMaze(maze);
        // solbacktrack.solveNRatMaze(maze);

        int[][] grid = { {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
        {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
        {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
        {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
        {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
        {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
        {0, 0, 5, 2, 0, 6, 3, 0, 0} };

        // solbacktrack.sudoku(grid);

        int[][] grid3x3 = { {3, 0, 6}, 
        {5, 2, 0}, 
        {0, 8, 0}};
        // solbacktrack.sudoku3x3(grid3x3);
    }
}