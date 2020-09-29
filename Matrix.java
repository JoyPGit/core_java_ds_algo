import java.util.*;

class Matrix {

    // DFS TEMPLATE
    void dfs(int[][] arr){
        int n = arr.length; int m = arr[0].length;
        int[][] visited = new int[n][m];

        for(int i =0; i<n; i++){
            for(int j = 0; j<m; j++){
                dfsUtil(arr, i, j, visited);
            }
        }
    }

    void dfsUtil(int[][] arr, int row, int col, int[][] visited){
        if(isSafedfs(arr, row, col)) {
            if(visited[row][col]==0){
                visited[row][col] = 1;
                System.out.println(arr[row][col]);
                dfsUtil(arr, row+1, col, visited);
                dfsUtil(arr, row, col+1, visited);
                dfsUtil(arr, row-1, col, visited);
                dfsUtil(arr, row, col-1, visited);
            }
        }
    }

    boolean isSafedfs(int[][] arr,int row, int col){
        if(row>=0 && row<arr.length
        && col>=0 && col<arr[0].length) return true;
        return false;
    }


    int[][] twoDimArr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };// new int[2][2];

    // twoDimArr = {{1,2},{3,4}};

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

    void maxPathSum(int[][] arr) {
        int rowMax = arr.length;
        int colMax = arr[0].length;

        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {

            }
        }
    }

    int maximumPathSum(int i, int j, int[][] matrix) {
        int down = 0;
        int right = 0;
        if (isSafe(i, j + 1, matrix)) {
            right = maximumPathSum(i, j + 1, matrix);
        }
        if (isSafe(i + 1, j, matrix)) {
            down = maximumPathSum(i + 1, j, matrix);
        }
        System.out.println(max(right, down) + matrix[i][j] + " i " + i + " j " + j);
        return max(right, down) + matrix[i][j];
    }

    boolean isSafe(int i, int j, int[][] matrix) {
        if (i <= matrix.length - 1 && j <= matrix[0].length - 1 && i >= 0 && j >= 0)
            return true;
        return false;
    }

    int max(int a, int b) {
        return a > b ? a : b;
    }

    ////////////////////////////// WITH DP

    int[][] fill2DArray(int[][] arr, int numRows, int numCols) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = -1;
            }
        }
        return arr;
    }

    int maxPathSumWithDP(int[][] arr, int i, int j, int[][] dp) {
        if (dp[i][j] == -1) {
            int right = 0;
            int down = 0;
            int ans = 0;
            if (isSafe(i, j + 1, arr)) {
                right = maxPathSumWithDP(arr, i, j + 1, dp);
            }
            if (isSafe(i + 1, j, arr)) {
                down = maxPathSumWithDP(arr, i + 1, j, dp);
            }
            ans = max(right, down) + arr[i][j];
            return dp[i][j] = ans;
        } else
            return dp[i][j];

    }

    int findMaxIn2DArray(int[][] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.println(arr[i][j]);
                if (arr[i][j] > max)
                    max = arr[i][j];
            }
        }
        return max;
    }

    void lengthOfLongestConsecutiveFromGivenChar() {

    }


    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length==0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                if(dp[i][j] !=0) continue;//1
                dfs(i, j, matrix, dp, Integer.MIN_VALUE);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
    
    int dfs(int row, int col, int[][] matrix, int[][] dp, int prev){
        if(isSafe(row, col, matrix, prev)){
            if(dp[row][col]!=0) return dp[row][col];
            
            int left = dfs(row, col-1, matrix, dp, matrix[row][col]);//2
            int right = dfs(row, col+1, matrix, dp, matrix[row][col]);
            int up = dfs(row-1, col, matrix, dp, matrix[row][col]);
            int down = dfs(row+1, col, matrix, dp, matrix[row][col]);
            dp[row][col] = Math.max(left, Math.max(right, Math.max(up, down)))+1;
            return dp[row][col];
        }
        return 0;
    }
    
    boolean isSafe(int r, int c, int[][] matrix, int prev){
        if(r>=0 && r<matrix.length
          && c>=0 && c<matrix[0].length
          && matrix[r][c]>prev) return true;//3
        return false;
    }

    int numberOfIslands(int[][] arr) {
        int i = 0;
        int j = 0;
        int count = 0;
        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    dfsIslandHelper(arr, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void dfsIslandHelper(int[][] arr, int rowIndex, int colIndex) {
        int[] row = { 0, 0, -1, 1, -1, -1, 1, 1 };
        int[] col = { -1, 1, 0, 0, -1, 1, -1, 1 };
        if (rowIndex >= 0 && rowIndex < arr.length && colIndex >= 0 && colIndex < arr[0].length) {
            if (arr[rowIndex][colIndex] == 1) {
                arr[rowIndex][colIndex] = 2;
                for (int k = 0; k < row.length; k++) {
                    dfsIslandHelper(arr, rowIndex + row[k], colIndex + col[k]);
                }
            }
        }
    }

    // 19 APR
    /* package whatever //do not write package name here */

    // code

    int maxCount = 0;
    int count = 0;

    int findMaxOnesRegion(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    findMaxOnesRegionHelper(arr, i, j);
                    maxCount = maxCount < count ? count : maxCount;
                }
                count = 0;
            }
        }

        return maxCount;
    }

    void findMaxOnesRegionHelper(int[][] arr, int rowIndex, int colIndex) {
        if (isSafe(arr, rowIndex, colIndex)) {
            count++;
            arr[rowIndex][colIndex] = 2;
            int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
            int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };
            for (int k = 0; k < row.length; k++) {
                findMaxOnesRegionHelper(arr, rowIndex + row[k], colIndex + col[k]);
            }
        }
    }

    boolean isSafe(int[][] arr, int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < arr.length && colIndex >= 0 && colIndex < arr[0].length
                && arr[rowIndex][colIndex] == 1)
            return true;
        return false;
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

    /** all 0s surrounded by 1s will be marked 1 */
    void floodFill(int[][] arr) {

        int[][] visited = new int[arr.length][arr[0].length];

        int i, j;
        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < (arr[0].length); j++) {
                visited[i][j] = 0;
            }
        }

        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr[0].length; j++) {
                floodFillUtil(arr, visited, i, j);
            }
        }
    }

    void floodFillUtil(int[][] arr, int[][] visited, int rowIndex, int colIndex) {
        if (isSafe29Apr(arr, rowIndex, colIndex)) {
            if (visited[rowIndex][colIndex] != 1) {
                arr[rowIndex][colIndex] = 1;
                visited[rowIndex][colIndex] = 1;
                floodFillUtil(arr, visited, rowIndex + 1, colIndex);
                floodFillUtil(arr, visited, rowIndex + 1, colIndex);
                floodFillUtil(arr, visited, rowIndex, colIndex + 1);
                floodFillUtil(arr, visited, rowIndex, colIndex - 1);
            }
        }
    }

    boolean isSafe29Apr(int[][] arr, int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < arr.length && colIndex >= 0 && colIndex < arr[0].length
                && arr[rowIndex][colIndex] == 0) {
            return true;
        } else
            return false;
    }
   

    void diagonalPrintMatrix(int[][] matrix) {
        int R = matrix.length - 1; // 2
        int C = matrix[0].length - 1; // 2
        int sum = 0;
        int i = 0, j = 0;
        while (sum <= R * C) {
            findAndPrint(matrix, sum);
            sum++;
            System.out.println();
        }
        // while(i+j==sum && sum<=R*C){
        // System.out.println(matrix[i][j]+", ");
        // i++; j++;
        // }
        // for(i =0 ;i<=R; i++){
        // for (j =0; j<=C; j++){
        // if(sum==i+j && sum<=R*C){System.out.print(matrix[i][j]+", ");}
        // sum++;
        // }
        // System.out.println();
        // }
    }

    // https://leetcode.com/problems/diagonal-traverse-ii/
    void findAndPrint(int[][] matrix, int sum) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i + j == sum)
                    System.out.print(matrix[i][j] + ", ");
            }
        }
    }

    class holder {
        int row;
        int col;
        int value;
        int time;
        boolean visited = false;

        holder(int rowIndex, int colIndex, int value, int time, boolean visited) {
            this.row = rowIndex;
            this.col = colIndex;
            this.value = value;
            this.time = time;
            this.visited = visited;
        }

        boolean getVisited() {
            return this.visited;
        }

        void setVisited() {
            this.visited = true;
        }
    }


    class BFSNode {
        int rowIndex;
        int colIndex;
        int value;

        BFSNode(int rowIndex, int colIndex, int value) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.value = value;
        }

    }

    void BFSMatrix(int[][] arr) {

        int i, j;
        int[][] visited = new int[arr.length][arr[0].length];

        BFSNode node = new BFSNode(0, 0, arr[0][0]);

        Queue<BFSNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(node);
        visited[0][0] = 1;

        while (!bfsQueue.isEmpty()) {
            BFSNode x = bfsQueue.remove();
            System.out.print(x.value + ", ");
            // visited[x.rowIndex][x.colIndex] = 1;
            BFSMatrixHelper(arr, visited, x, bfsQueue);
        }
    }

    void BFSMatrixHelper(int[][] arr, int[][] visited, BFSNode node, Queue bfsQueue) {
        int row = node.rowIndex;
        int col = node.colIndex;

        if (isSafeBFS(row + 1, col, arr)) {
            if (visited[row + 1][col] != 1) {
                visited[row + 1][col] = 1;
                // System.out.println("el "+arr[row+1][col]);
                bfsQueue.add(new BFSNode(row + 1, col, arr[row + 1][col]));
            }
        }

        if (isSafeBFS(row - 1, col, arr)) {
            if (visited[row - 1][col] != 1) {
                visited[row - 1][col] = 1;
                // System.out.println("el "+arr[row-1][col]);
                bfsQueue.add(new BFSNode(row - 1, col, arr[row - 1][col]));
            }
        }

        if (isSafeBFS(row, col + 1, arr)) {
            if (visited[row][col + 1] != 1) {
                visited[row][col + 1] = 1;
                // System.out.println("el "+arr[row][col+1]);
                bfsQueue.add(new BFSNode(row, col + 1, arr[row][col + 1]));
            }
        }

        if (isSafeBFS(row, col - 1, arr)) {
            if (visited[row][col - 1] != 1) {
                visited[row][col - 1] = 1;
                // System.out.println("el "+arr[row][col-1]);
                bfsQueue.add(new BFSNode(row, col - 1, arr[row][col - 1]));
            }
        }
    }

    boolean isSafeBFS(int row, int col, int[][] arr) {
        if (row >= 0 && row < arr.length && col >= 0 && col < arr[0].length) {
            return true;
        }
        return false;
    }

    /**
     * need to take one of two approaches either a global var and update it after
     * every dfs or make function return value
     */
    int countPathLength = 0;

    void maxPathLength(int[][] arr) {
        int[][] holder = new int[arr.length][arr[0].length];
        int max7jun = -1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                // holder[i][j] = maxPathLengthHelper(arr, i, j, holder);
                // System.out.print(holder[i][j]+", ");
                max7jun = Math.max(max7jun, maxPathLengthHelper(arr, i, j, holder, -9999));
                // 3 technique for max no extra for loop

                // this.countPathLength = 0;
            }
        }

        System.out.println("max length path is " + max7jun);
    }

    /**
     * this can't be solved without dp if we dont use a visited matrix else we eill
     * be recurring for adjacent psns indefinitely dp is used to check if value
     * exists or not.
     */
    int maxPathLengthHelper(int[][] arr, int i, int j, int[][] holder, int curr) {
        /** should i put the check now or wait for the next func call with (i+1,j)? */

        // 1 check is put first
        // 2 pass -999... as the first arg when calling helper, hten pass the arr value
        // for
        // left , right, up and down
        if (isSafePathLength(i, j, arr, curr)) {
            int value = arr[i][j];
            // this line caused all problems
            if (holder[i][j] != 0) {
                return holder[i][j];
            }
            int down = maxPathLengthHelper(arr, i + 1, j, holder, value);
            int up = maxPathLengthHelper(arr, i - 1, j, holder, value);
            int left = maxPathLengthHelper(arr, i, j - 1, holder, value);
            int right = maxPathLengthHelper(arr, i, j + 1, holder, value);
            // }
            // if(isSafePathLength(i+1, j, arr, value)){
            // // this.countPathLength++;
            // // down =1;

            // }
            // if(isSafePathLength(i-1, j, arr, value)){
            // // up=1;
            // }
            // if(isSafePathLength(i, j+1, arr, value)){
            // // right =1;
            // }
            // if(isSafePathLength(i, j-1, arr, value)){
            // // left =1;
            // }
            // System.out.println("left "+left+" right "+right+" up "+up+" down "+down);
            holder[i][j] = Math.max(down, Math.max(up, Math.max(left, right))) + 1;
            System.out.println("holder[" + i + "][" + j + "] " + holder[i][j]);
            return holder[i][j];
        } else
            return 0;
    }

    boolean isSafePathLength(int row, int col, int[][] arr, int curr) {
        if (row >= 0 && row < arr.length && col >= 0 && col < arr[0].length && arr[row][col] - curr >= 1) {
            return true;
        }
        return false;
    }

    public int sumGold = 0;
    public int currSumGold = 0;

    public int getMaximumGold(int[][] grid) {

        int[][] visited = new int[grid.length][grid[0].length];
        int i, j;
        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                getMaxHelper(grid, i, j, 0, visited);
                if (currSumGold > sumGold)
                    sumGold = currSumGold;
                currSumGold = 0;
                initializeToZero(visited);
            }
        }
        return sumGold;
    }

    void getMaxHelper(int[][] grid, int rowIndex, int colIndex, int sum, int[][] visited) {
        if (rowIndex >= 0 && colIndex >= 0 && rowIndex < grid.length && colIndex < grid[0].length
                && grid[rowIndex][colIndex] != 0 && visited[rowIndex][colIndex] == 0) {

            System.out.println("sum " + sum);
            System.out.println("currSumGold " + currSumGold);
            System.out.println("value " + grid[rowIndex][colIndex]);
            visited[rowIndex][colIndex] = 1;
            int[] row = { -1, 0, 0, 1 };
            int[] col = { 0, -1, 1, 0 };

            currSumGold = sum;
            for (int k = 0; k < row.length; k++) {
                getMaxHelper(grid, rowIndex + row[k], colIndex + col[k], sum + grid[rowIndex][colIndex], visited);
            }
        }
    }

    void initializeToZero(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = 0;
            }
        }
    }

    //////////////////////////// 7 jun dp applied
    /**
     * 1 pass -9999 2 pass current value for subsequent 3 math.max for max
     */
    void goldMine(int[][] grid) {
        int max = -1;
        int[][] dp = new int[grid.length][grid[0].length];
        int j, i;
        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                max = Math.max(goldMineHelper(grid, i, j, dp, -9999), max);
            }
        }
        System.out.println("max gold is " + max);
    }

    int goldMineHelper(int[][] grid, int rowIndex, int colIndex, int[][] dp, int prev) {

        if (rowIndex >= 0 && rowIndex < grid.length && colIndex >= 0 && colIndex < grid[0].length
                && prev < grid[rowIndex][colIndex] && grid[rowIndex][colIndex] != 0) {
            if (dp[rowIndex][colIndex] != 0)
                return dp[rowIndex][colIndex];

            int curr = grid[rowIndex][colIndex];

            int left = goldMineHelper(grid, rowIndex, colIndex - 1, dp, curr);
            int right = goldMineHelper(grid, rowIndex, colIndex + 1, dp, curr);
            int up = goldMineHelper(grid, rowIndex - 1, colIndex, dp, curr);
            int down = goldMineHelper(grid, rowIndex + 1, colIndex, dp, curr);

            dp[rowIndex][colIndex] = Math.max(left, Math.max(right, Math.max(up, down))) + curr;
            System.out.println("dp[" + rowIndex + "][" + colIndex + "] " + dp[rowIndex][colIndex]);
            return dp[rowIndex][colIndex];
        } else
            return 0;
    }

    // https://www.includehelp.com/icp/gold-mine-problem.aspx
    int GoldMine7Jun(int[][] arr, int n, int m) {
        // DP table
        int[][] DP = new int[n][m];

        for (int i = 0; i < n; i++)
            DP[i][0] = arr[i][0];

        // for every column
        for (int j = 0; j < n; j++) {
            // check which option is better accordingly
            for (int i = 0; i < m; i++) {
                // choosing max of possible moves
                DP[i][j] = arr[i][j];
                int val = DP[i][j - 1];
                if (isSafe(i, j - 1, DP)) {
                    if (val < DP[i - 1][j - 1])
                        val = DP[i - 1][j - 1];
                }
                if (isSafe(i, j + 1, DP)) {
                    if (val < DP[i - 1][j - 1])
                        val = DP[i - 1][j - 1];
                }
                if (i - 1 >= 0) {
                    if (val < DP[i - 1][j - 1])
                        val = DP[i - 1][j - 1];
                }
                if (i + 1 < n) {
                    if (val < DP[i + 1][j - 1])
                        val = DP[i + 1][j - 1];
                }
                DP[i][j] += val;
            }
        }
        // find the maximum of the last column
        int gold = DP[0][m - 1];
        for (int i = 1; i < n; i++) {
            if (DP[i][m - 1] > gold)
                gold = DP[i][m - 1];
        }

        return gold;
    }

    /** the -1s are inaccessible; reduce 99 to lowest dist from 0 */

    void wallsAndGates(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int[][] visited = new int[grid.length][grid[0].length];

        int i, j;

        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == -1) {
                    dp[i][j] = grid[i][j];
                }
            }
        }

        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 99) {
                    dp[i][j] = wallsAndGatesHelper(grid, i, j, dp, visited);
                    initializeToZero(visited);
                }
            }
        }

        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                System.out.print(dp[i][j] + ", ");
            }
            System.out.println();
        }
    }

    /** this is like keeping a trck of the cells traversed */
    /**
     * finally found a technique for dfs, use visited array and add a check for that
     * visited!=1, and assign to dp outside the helper
     */

    int wallsAndGatesHelper(int[][] grid, int row, int col, int[][] dp, int[][] visited) {
        if (row > -1 && row < grid.length && col > -1 && col < grid[0].length && grid[row][col] != -1
                && visited[row][col] != 1) {
            if (dp[row][col] != 0)
                return dp[row][col];
            if (grid[row][col] == 0)
                return 0;
            System.out.println("row " + row + " ,col " + col);
            visited[row][col] = 1;

            // if(grid[row][col]==-1) return 99;
            int left = wallsAndGatesHelper(grid, row, col - 1, dp, visited);
            int right = wallsAndGatesHelper(grid, row, col + 1, dp, visited);
            int up = wallsAndGatesHelper(grid, row - 1, col, dp, visited);
            int down = wallsAndGatesHelper(grid, row + 1, col, dp, visited);

            System.out.println("left " + left + ", right " + right + ", up " + up + ", down " + down);
            // dp[row][col] = Math.min(left, Math.min(right, Math.min(up, down)))+1;
            // System.out.println("dp[" +row+"]["+col+"] "+ dp[row][col]);
            System.out.println("visited[" + row + "][" + col + "] " + visited[row][col]);
            int val = Math.min(left, Math.min(right, Math.min(up, down))) + 1;
            System.out.println("val " + val);
            return val;

        } else
            return 90;
    }

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
        if (row > -1 && row < grid.length && col > -1 && col < grid[0].length && grid[row][col] != -1 && count >= 0 // &&
                                                                                                                    // count<99
                && grid[row][col] >= count) {
            grid[row][col] = count;
            wallsAndGatesHelperKevin(grid, row, col + 1, dp, count + 1);
            wallsAndGatesHelperKevin(grid, row, col - 1, dp, count + 1);
            wallsAndGatesHelperKevin(grid, row + 1, col, dp, count + 1);
            wallsAndGatesHelperKevin(grid, row - 1, col, dp, count + 1);
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

    /**
     * THE BASIC TEMPLATE IS CHANGED A BIT,
     * WE DON'T RECUR FOR ALL POINTS IN THE GRAPH ,RATHER ONLY FOR THE
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
    // https://leetcode.com/problems/pacific-atlantic-water-flow/
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res= new ArrayList<>();
        
        int m = matrix.length;
        if(m==0) return res;
        
        int n = matrix[0].length;
        
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
    
    void dfs(int[][] matrix, int row, int col, int[][] visited, int prev){
        if(isSafe(matrix, row, col, visited, prev)){
            visited[row][col] = 1;
            dfs(matrix, row+1, col, visited, matrix[row][col]);
            dfs(matrix, row-1, col, visited, matrix[row][col]);
            dfs(matrix, row, col+1, visited, matrix[row][col]);
            dfs(matrix, row, col-1, visited, matrix[row][col]);
        }
    }
    
    boolean isSafe(int[][] matrix, int r, int c, int[][] visited, int prev){
        if(r>=0 && r<matrix.length
          && c>=0 && c<matrix[0].length
          && matrix[r][c]>=prev 
          && visited[r][c]!=1) return true;
        return false;
    }
    
    //all paths from a to b 
    //https://www.geeksforgeeks.org/print-paths-given-source-destination-using-bfs/

    // https://leetcode.com/problems/number-of-islands
    public int numIslands(char[][] grid) {
        int count = 0;
        
        for(int i =0; i<grid.length; i++){
            for(int j =0; j<grid[0].length; j++){
                if(grid[i][j] == '1') {dfs(grid, i, j); count++;}
            }
        }
        return count;
    }
    
    void dfs(char[][] grid, int row, int col){
        if(row>=0 && row<grid.length
          && col>=0 && col<grid[0].length
          && grid[row][col] == '1'){
            grid[row][col] = '0';
            dfs(grid, row+1, col);
            dfs(grid, row-1, col);
            dfs(grid, row, col+1);
            dfs(grid, row, col-1);
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

    // https://leetcode.com/problems/island-perimeter/
    int countPerimeter = 0; 
    public int islandPerimeter(int[][] grid) {
        int r = grid.length; int c = grid[0].length;
        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j] == 1) dfsPerimeter(grid, i, j);
            }
        }
        return countPerimeter;
    }
    
    void dfsPerimeter(int[][] grid, int row, int col){
        if(row >= 0 && row<grid.length
          && col>=0 && col<grid[0].length
          && grid[row][col] == 1){
            if(row+1 == grid.length || grid[row+1][col] == 0) countPerimeter++;
            if(row-1 == -1 || grid[row-1][col] == 0) countPerimeter++;
            if(col+1 == grid[0].length || grid[row][col+1] == 0) countPerimeter++;
            if(col-1 == -1 || grid[row][col-1] == 0) countPerimeter++;
        }
    }

    // https://leetcode.com/problems/max-area-of-island/
    int countArea = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for(int i =0; i<grid.length; i++){
            for(int j =0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    dfsArea(grid, i, j);
                    max = Math.max(count, max);
                    countArea = 0;
                }
            }
        }
        return max;
    }
    
    void dfsArea(int[][] grid, int r, int c){
        if(r>=0 && r<grid.length
          && c>=0 && c<grid[0].length
          && grid[r][c] == 1){
            countArea++;
            grid[r][c] = 0;
            dfs(grid, r+1, c);
            dfs(grid, r-1, c);
            dfs(grid, r, c+1);
            dfs(grid, r, c-1);
        }
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