public class DP {
    void printMatrix(int[][] arr){
        for(int i =0; i<arr.length; i++){
            for(int j =0; j<arr[0].length; j++){
                System.out.print(arr[i][j]+", " );
            }
            System.out.println();
        }
    }


    /** points:
     * only right and down movements, else visited matrix would have been needed
     * global var count used
     */
    int count =0;
    int countPaths(int[][] arr){
        int n = arr.length, m = arr[0].length;
        // dfs(arr,0,0);
        // System.out.println(this.count);
        // return this.count;
        // return dfsInt(arr, 0, 0, 0);
        
        int[][] countHolder = new int[arr.length][arr[0].length];
        dfsIntDP(arr, 0, 0, countHolder);
        // printMatrix(countHolder);

        countPathsDP(arr, countHolder);
        printMatrix(countHolder);
        return countHolder[n-1][m-1];


    }

    void dfs(int[][] arr, int row, int col){
        if(row>-1&& row<arr.length
        && col>-1 && col<arr[0].length){
            if(row == arr.length-1 && col == arr[0].length-1){
                this.count++;
                System.out.println("in here "+count);
                return;
            }
            dfs(arr,row+1, col);
            // dfs(arr,row-1, col, count);
            dfs(arr,row, col+1);
            // dfs(arr,row, col-1, count);
        }
    }

    int dfsInt(int[][] arr, int row, int col, int count){
        if(row>-1&& row<arr.length
        && col>-1 && col<arr[0].length){
            if(row == arr.length-1 && col == arr[0].length-1){
                count++;
                System.out.println("in here "+count);
                return count;
            }
            return dfsInt(arr,row+1, col, count) + dfsInt(arr,row, col+1, count);
        }
        return 0;
    }

    int dfsIntDP(int[][] arr, int row, int col, int[][] dp){
        if(row>-1&& row<arr.length
        && col>-1 && col<arr[0].length){
            if(row == arr.length-1 && col == arr[0].length-1) return 0;
            if(dp[row][col]!=0) return dp[row][col];
            dp[row][col] = dfsIntDP(arr,row+1, col, dp) + dfsIntDP(arr,row, col+1, dp)+1;
        }
        return 0;
    }

    /** the trick is to convert a recursive relation to  a dp relation
     * dfs(r,c) = dfs(r+1,c)+dfs(r,c+1);
     * 
     * but the relation here for dp is built in bottom up manner
    */
    void countPathsDP(int[][] arr, int[][] dp){
        for(int i =0; i<arr.length; i++){
            dp[i][0] = 1;
        }
        for(int i =0; i<arr.length; i++){
            dp[0][i] = 1;
        }

        for(int i =1; i<dp.length; i++){
            for(int j =1; j<dp.length; j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
    }

    int LIS(int[] arr){
        int i = arr.length-1;
        // return LIShelper(arr, 0, -9999999);
        // return LIShelperReverse(arr, 0);
        return LISDP(arr);
    }

    // int LIShelper(int[] arr, int index){
    //     if(index==1) {
    //         if(arr[index-1] < arr[index]) return 2; 
    //         return 1;
    //     }
    //     if(arr[index-1]<arr[index]) return LIShelper(arr, index-1)+1;
    //     // return LIShelper(arr, index-1);
    //     return LIShelper(arr, index-1);
    // }

    int LIShelper(int[] arr, int index, int prev){
        if(index==arr.length) {
            return 0;
        }

        int inc = 0, exc =0;
        if(prev<arr[index]) inc = LIShelper(arr, index+1, arr[index])+1;
        // return LIShelper(arr, index-1);
        exc = LIShelper(arr, index+1, prev);
        return Math.max(inc, exc);
    }

    int LIShelperReverse(int[] arr, int index){
        if(index==arr.length-2) {
            if(arr[index] < arr[index+1]) return 2; 
            return 1;
        }
        if(arr[index]<arr[index+1]) return LIShelperReverse(arr, index+1)+1;
        return LIShelperReverse(arr, index+1);
    }

    int LISDP(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for(int i = 1; i<arr.length; i++){
            for(int j =0; j<i; j++){
                if(arr[j]<arr[i] && dp[i]<dp[j]){
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }
        
        int max =-1;
        for(int i =0; i<dp.length; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    int russianDoll(int[][] arr){
        int n = arr.length;
        if(n==0) return 0;
        if(n==1) return arr[0][0];
        int[] dp = new int[arr.length];

        russianDollhelper(arr, dp);
        int max = -1;
        for(int i =0; i<dp.length; i++){
            max= Math.max(max, dp[i]);
        }
        return max;
    }

    class russianDoll{
        int h; int w;
        russianDoll(int height, int width){
            this.h = height;
            this.w = width;
        }
    }

    void russianDollhelper(int[][] arr, int[] dp){
        russianDoll[] holder = new russianDoll[arr.length];
        int i=0, j=0;
        for(i =0; i<holder.length; i++){
            holder[i] = new russianDoll(arr[i][0], arr[i][1]);
        }
        sortClass(holder);
        for(i =0; i<holder.length;i++){
            System.out.println(holder[i].h+", "+holder[i].w);
        }
        dp[0]=1;
        for(i =1; i<holder.length; i++){
            for(j =0; j<i ; j++){
                if((holder[j]).h<holder[i].h && (holder[j]).w<holder[i].w && dp[i]<dp[j]){
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }
    }

    void sortClass(russianDoll[] arr){
        for(int i =1; i<arr.length; i++){
            for(int j =0; j<i; j++){
                if(arr[i].h<arr[j].h && arr[i].w<arr[j].w){
                    russianDoll temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**1 add boundary condition check for n= 0and 1 
     * 2 also dp[1] = max pdf arr[0] and arr[1]
     * 2 after that just dp[i] = max(arr[i]+ dp[i-2], dp[i-1])
    */
    int houseRobber(int[] arr){
        int n = arr.length;
        if(n==0) return 0;
        if(n==1) return arr[0];
        
        if(n==2) return arr[0]>arr[1]?arr[0]:arr[1];
        int[] dp =  new int[n];

        dp[0] = arr[0]; dp[1] = arr[0]>arr[1]?arr[0]:arr[1];

        for(int i =2; i<arr.length; i++){
            dp[i] = (arr[i]+dp[i-2]>dp[i-1])?(arr[i]+dp[i-2]):dp[i-1];
        }
        return dp[dp.length-1];
    }
    /** POINTS
     * 1 either include the elment or not
     * LCS exc = f(Arr, i+1, prev) 
     * inc = f(arr, i+1, arr[i])
     * return max
     * 
     * 2 knapsack similar include or exclude
     * 3 coin change diff is supply is infinite 
     * so return f( S, m - 1, n ) + f( S, m, n-S[m-1] );
     */

    public static void main(String[] args) {
        DP dp = new DP();
        int[][] arr = { { 8, 2, 1 }, { 3, 9, 7 }, { 2, 1, 8 } };
        // dp.countPaths(arr);
        // dp.countPaths(arr);
        // int[] LISArr = {3,10,2,1,20};
        // int[] LISArr = {50, 3, 10, 7, 40, 80};
        int[] LISArr = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
        // System.out.println( dp.LIS(LISArr));

        int[][] russianDollArr = {{5,4},{6,4},{6,7},{2,3}};
        // System.out.println(dp.russianDoll(russianDollArr));
        int[] house = {2,7,9,3,1};//{1,2,3,1};
        System.out.println(dp.houseRobber(house));
    }
}