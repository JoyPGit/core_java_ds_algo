import java.util.*;

class Matrix {

    int[][] twoDimArr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };// new int[2][2];

    // twoDimArr = {{1,2},{3,4}};

    void showMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

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


    void longestPathFromAnyIndex(int[][] arr, int rowStart, int colStart) {
        int rowLength = arr.length;
        int columnLength = arr[0].length;
        int dp[][] = new int[rowLength][columnLength];
        int max = -9;
        int i;
        int j;
        for (i = 0; i < dp.length; i++) {
            for (j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        for (i = 0; i < dp.length; i++) {
            for (j = 0; j < dp[0].length; j++) {
                this.longestPathHelper(arr, dp, i, j);
                max = Math.max(max, dp[i][j]);
            }
        }

        // for (i=0; i<dp.length; i++){
        // for(int l =0; l<dp[0].length; l++){
        // if(dp[k][l]>max) max = dp[k][l];
        // }
        // }

        System.out.println("max is " + max);

    }

    int longestPathHelper(int[][] arr, int[][] dp, int row, int col) {
        // System.out.println(col);

        if (row < 0 || row > arr.length - 1 || col < 0 || col > arr[0].length - 1) {
            return 0;
        }
        if (dp[row][col] == -1) {

            int left = 0;
            int right = 0;
            int up = 0;
            int down = 0;
            // if(col-1>=0 && col-1<=arr[0].length-1){
            if (isSafeLongestPath(arr, row, col - 1, arr[row][col])) {
                // if(arr[row][col]<arr[row][col-1]){
                System.out.println("in left");
                left = longestPathHelper(arr, dp, row, col - 1);
                // }
            }
            // if(col+1>=0 && col+1<=arr[0].length-1){
            if (isSafeLongestPath(arr, row, col + 1, arr[row][col])) {
                // if(arr[row][col]<arr[row][col+1]){
                System.out.println("in right");
                right = longestPathHelper(arr, dp, row, col + 1);
                // }
            }

            // if(row+1>=0 && row+1<=arr.length-1){
            if (isSafeLongestPath(arr, row + 1, col, arr[row][col])) {
                // if(arr[row][col]<arr[row+1][col]){
                System.out.println("in down");
                down = longestPathHelper(arr, dp, row + 1, col);
                // }
            }

            // if(row-1>=0 && row-1<=arr.length-1){
            if (isSafeLongestPath(arr, row - 1, col, arr[row][col])) {
                // if(arr[row][col]<arr[row-1][col]){
                System.out.println("in up");
                up = longestPathHelper(arr, dp, row - 1, col);
                // }
            }

            /**
             * Mistakes missing the base condition of arr[row][col]>prev adding excessive
             * checks
             * 
             */

            /**
             * the tricky thing is the col-1 or col+1 might run out of bounds, use isSafe
             * check. Also teh base condition needs to be checked, i.e. the value of the
             * left or roght must be greater than the current element, Instead of using an
             * additional check pass it as a param.
             * 
             * if(rowIndex<0 || rowIndex>arr.length-1 || colIndex<0 || colIndex
             * >arr[0].length-1){ if(arr[rowIndex][colIndex]<prev) return false; }
             * 
             * see teh condition here ois to check if the index is within limits if we check
             * if it's outside or not then it will be difficult to check if it is valid or
             * not so the fucntion signature changes
             * 
             * AND THE CONDITION CHANGES FROM OR TO AND
             * ---------------------------------------> if(rowIndex>=0 &&
             * rowIndex<=arr.length-1 && colIndex>=0 && colIndex <= arr[0].length-1){
             * if(arr[rowIndex][colIndex]>prev) return true; }
             */
            System.out.println("left " + left);
            dp[row][col] = Math.max(left, Math.max(right, Math.max(up, down))) + 1;
            System.out.println("dp[i][j] " + dp[row][col]);
            return dp[row][col];

        }
        return dp[row][col];
    }

    boolean isSafeLongestPath(int[][] arr, int rowIndex, int colIndex, int prev) {
        // if(rowIndex<0 || rowIndex>arr.length-1 || colIndex<0 || colIndex
        // >arr[0].length-1){
        // if(arr[rowIndex][colIndex]<prev) return false;
        // }
        if (rowIndex >= 0 && rowIndex <= arr.length - 1 && colIndex >= 0 && colIndex <= arr[0].length - 1) {
            if (arr[rowIndex][colIndex] > prev)
                return true;
        }
        return false;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] mem = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = LIPhelper(matrix, mem, i, j);
                result = Math.max(result, t);
            }
        }

        return result;
    }

    private int LIPhelper(int[][] matrix, int[][] mem, int i, int j) {
        if (mem[i][j] > 0) {
            return mem[i][j];
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
                mem[i][j] = Math.max(mem[i][j], LIPhelper(matrix, mem, x, y));
            }
        }

        return ++mem[i][j];
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

    // void printSpiral28Apr(int[][] arr){
    // int rowEnd = arr.length-1; int rowStart = 0;
    // int colEnd = arr[0].length-1; int colStart = 0;

    // int i;

    // while(rowEnd>=0){

    // for(i =rowStart; i<=rowEnd; i++){
    // System.out.print(arr[i][colStart]+", ");
    // }
    // colStart++;
    // System.out.println();

    // for(i =colStart; i<=colEnd; i++){
    // System.out.print(arr[rowEnd][i]+", ");
    // }
    // rowEnd--;
    // System.out.println();

    // for(i =rowEnd; i>=rowStart; i--){
    // System.out.print(arr[i][colEnd]+", ");
    // }
    // colEnd--;
    // System.out.println();

    // for(i = colEnd; i>=colStart; i--){
    // System.out.print(arr[rowStart][i]+", ");
    // }

    // rowStart++;
    // System.out.println();

    // }

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

    public int orangesRotting(int[][] grid) {

        int R = grid.length;
        int C = grid[0].length;
        int[][] tracker = new int[R][C];
        int i, j;

        for (i = 0; i < R; i++) {
            for (j = 0; j < C; j++) {
                orangesRottingHelper(grid, tracker, i, j, 0);
            }
        }

        for (i = 0; i < R; i++) {
            for (j = 0; j < C; j++) {
                if (grid[i][j] == 1)
                    return -1;
            }
        }

        int max = 0;
        for (i = 0; i < R; i++) {
            for (j = 0; j < C; j++) {
                System.out.print(tracker[i][j] + ", ");
                if (tracker[i][j] > max)
                    max = tracker[i][j];
            }
            System.out.println();
        }
        // System.out.println(tracker[R-1][C-2]);
        // System.out.println(tracker[R-1][C-1]);
        return max;

    }

    void orangesRottingHelper(int[][] arr, int[][] tracker, int rowIndex, int colIndex, int time) {

        if (isSafeOranges(arr, rowIndex, colIndex) == 1) {
            arr[rowIndex][colIndex] = 2;
            if (tracker[rowIndex][colIndex] == 0) {
                tracker[rowIndex][colIndex] = time;
            }

            if (tracker[rowIndex][colIndex] > time) {
                tracker[rowIndex][colIndex] = time;
            }

            time = tracker[rowIndex][colIndex] + 1;
            orangesRottingHelper(arr, tracker, rowIndex + 1, colIndex, time);
            orangesRottingHelper(arr, tracker, rowIndex - 1, colIndex, time);
            orangesRottingHelper(arr, tracker, rowIndex, colIndex + 1, time);
            orangesRottingHelper(arr, tracker, rowIndex, colIndex - 1, time);
        }

        if (isSafeOranges(arr, rowIndex, colIndex) == 2) {
            arr[rowIndex][colIndex] = 3;
            tracker[rowIndex][colIndex] = 0;
            orangesRottingHelper(arr, tracker, rowIndex + 1, colIndex, 1);
            orangesRottingHelper(arr, tracker, rowIndex - 1, colIndex, 1);
            orangesRottingHelper(arr, tracker, rowIndex, colIndex + 1, 1);
            orangesRottingHelper(arr, tracker, rowIndex, colIndex - 1, 1);
        }
    }

    int isSafeOranges(int[][] arr, int rowIndex, int colIndex) {
        if (rowIndex < arr.length && rowIndex >= 0 && colIndex >= 0 && colIndex < arr[0].length
                && arr[rowIndex][colIndex] == 2) {
            return 2;
        }
        if (rowIndex < arr.length && rowIndex >= 0 && colIndex >= 0 && colIndex < arr[0].length
                && arr[rowIndex][colIndex] == 1) {
            return 1;
        }
        if (rowIndex < arr.length && rowIndex >= 0 && colIndex >= 0 && colIndex < arr[0].length
                && arr[rowIndex][colIndex] == 1) {
            return 3;
        } else
            return -1;
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

    void orangesRotting1May(int[][] arr) {

        Queue<holder> hold = new LinkedList<Matrix.holder>();

        holder[][] finalTimeArray = new holder[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0)
                    (finalTimeArray[i][j]) = new holder(i, j, arr[i][j], -1, false);
                else
                    (finalTimeArray[i][j]) = new holder(i, j, arr[i][j], 0, false);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if ((finalTimeArray[i][j]).value == 2) {
                    hold.add(new holder((finalTimeArray[i][j]).row, (finalTimeArray[i][j]).col,
                            (finalTimeArray[i][j]).value, (finalTimeArray[i][j]).time, true));
                }
            }
        }

        // System.out.println(hold.size());

        while (!hold.isEmpty()) {
            holder current = hold.poll();
            // holder current = hold.remove();
            // System.out.print("index row:"+current.row+", index col"+current.col+",
            // visited "+ current.visited+", ");
            orangesRotting1MayHelper(arr, hold, current, finalTimeArray);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.err.print((finalTimeArray[i][j]).time + ", ");
            }
            System.out.println();
        }
    }

    void orangesRotting1MayHelper(int[][] arr, Queue hold, holder current, holder[][] finalTimeArray) {
        if (isSafeOranges1May(finalTimeArray, current.row + 1, current.col)) {// into visited method
            if (isVisitedOranges1May(finalTimeArray, current.row + 1, current.col)) {
                if ((finalTimeArray[current.row + 1][current.col]).time > current.time + 1) {
                    // (finalTimeArray[current.row+1][current.col]).time =current.time+1;
                    finalTimeArray[current.row + 1][current.col] = new holder(current.row + 1, current.col,
                            (finalTimeArray[current.row + 1][current.col]).value, current.time + 1, true);
                    // hold.add(new holder(current.row+1, current.col, 2, current.time+1, true));
                }
            } else {
                (finalTimeArray[current.row + 1][current.col]).setVisited();
                finalTimeArray[current.row + 1][current.col].time = current.time + 1;
                // System.out.println("visited "
                // +(finalTimeArray[current.row+1][current.col]).visited);
                hold.add(new holder(current.row + 1, current.col, 2, current.time + 1, true));
            }

        }

        if (isSafeOranges1May(finalTimeArray, current.row, current.col + 1)) {
            if (isVisitedOranges1May(finalTimeArray, current.row, current.col + 1)) {
                if ((finalTimeArray[current.row][current.col + 1]).time > current.time + 1) {

                    finalTimeArray[current.row][current.col + 1] = new holder(current.row, current.col + 1,
                            (finalTimeArray[current.row + 1][current.col]).value, current.time + 1, true);
                    // hold.add(new holder(current.row, current.col+1, 2, current.time+1, true));
                }
            } else {
                (finalTimeArray[current.row][current.col + 1]).setVisited();
                finalTimeArray[current.row][current.col + 1].time = current.time + 1;
                hold.add(new holder(current.row, current.col + 1, 2, current.time + 1, true));
            }
            // finalTimeArray[current.row][current.col+1] =
            // new holder(current.row, current.col+1,
            // (finalTimeArray[current.row][current.col+1]).value,
            // current.time+1, true);

        }

        if (isSafeOranges1May(finalTimeArray, current.row - 1, current.col)) {
            if (isVisitedOranges1May(finalTimeArray, current.row - 1, current.col)) {
                if (current.time > current.time + 1) {
                    current.time = current.time + 1;
                    finalTimeArray[current.row - 1][current.col] = new holder(current.row - 1, current.col,
                            (finalTimeArray[current.row - 1][current.col]).value, current.time + 1, true);
                    // hold.add(new holder(current.row-1, current.col, 2, current.time+1, true));
                }
            } else {
                (finalTimeArray[current.row - 1][current.col]).setVisited();
                hold.add(new holder(current.row - 1, current.col, 2, current.time + 1, true));
            }
            // finalTimeArray[current.row-1][current.col] =
            // new holder(current.row-1, current.col,
            // (finalTimeArray[current.row-1][current.col]).value,
            // current.time+1, true);

        }

        if (isSafeOranges1May(finalTimeArray, current.row, current.col - 1)) {
            if (isVisitedOranges1May(finalTimeArray, current.row, current.col - 1)) {
                if (current.time > current.time + 1) {
                    current.time = current.time + 1;
                    finalTimeArray[current.row][current.col - 1] = new holder(current.row, current.col - 1,
                            (finalTimeArray[current.row][current.col - 1]).value, current.time + 1, true);
                    // hold.add(new holder(current.row, current.col-1, 2, current.time+1, true));
                } else {
                    (finalTimeArray[current.row][current.col - 1]).setVisited();
                    hold.add(new holder(current.row, current.col - 1, 2, current.time + 1, true));
                }
                // finalTimeArray[current.row][current.col-1] =
                // new holder(current.row, current.col-1,
                // (finalTimeArray[current.row][current.col-1]).value,
                // current.time+1, true);

            }
        }

    }

    boolean isSafeOranges1May(holder[][] arr, int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < arr.length && colIndex >= 0 && colIndex < arr[0].length
                && (arr[rowIndex][colIndex]).value != -1) {
            return true;
        }
        return false;
    }

    boolean isVisitedOranges1May(holder[][] arr, int rowIndex, int colIndex) {
        if ((arr[rowIndex][colIndex]).visited == true)
            return true;
        return false;
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
    
    int countPath;


    // public int maximalRectangle(char[][] matrix) {
    //     int n = matrix.length; int m = matrix[0].length;
    //     int[][] dp = new int[n][m];
    //     int max = 0;
        
    //     for(int i =0; i<n; i++){
    //         for(int j =0; j<m ;j++){
    //             if(matrix[i][j] == '0') dp[i][j] = 0;
    //             else dp[i][j] =1;
    //         }
    //     }
        
    //     for(int i =1; i<n; i++){
    //         for(int j =1; j<m ;j++){
    //             if(matrix[i-1][j] !='0' && matrix[i][j]!='0'  
    //             && matrix[i][j-1]!='0' && matrix[i-1][j-1]!='0'){
    //                 dp[i][j] = Math.max(dp[i-1][j-1], 
    //                 Math.max(dp[i-1][j], dp[i][j-1]+1))+1;
    //             }
    //         }
    //     }
        
    //     utilCustom.Utility.printMatrix(dp);
    //     for(int i =1; i<n; i++){
    //         for(int j =1; j<m ;j++){
    //             max =Math.max(max, dp[i][j]);
    //         }
    //     }
    //     return max;
    // }
    
   //all paths from a to b 
   //https://www.geeksforgeeks.org/print-paths-given-source-destination-using-bfs/

    // https://leetcode.com/problems/island-perimeter/
    
    // https://leetcode.com/problems/pacific-atlantic-water-flow/
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