import java.util.*;

public class DP {
    void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /**
     * points: only right and down movements, else visited matrix would have been
     * needed global var count used
     */
    int count = 0;

    int countPaths(int[][] arr) {
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
        return countHolder[n - 1][m - 1];

    }

    void dfs(int[][] arr, int row, int col) {
        if (row > -1 && row < arr.length && col > -1 && col < arr[0].length) {
            if (row == arr.length - 1 && col == arr[0].length - 1) {
                this.count++;
                System.out.println("in here " + count);
                return;
            }
            dfs(arr, row + 1, col);
            // dfs(arr,row-1, col, count);
            dfs(arr, row, col + 1);
            // dfs(arr,row, col-1, count);
        }
    }

    int dfsInt(int[][] arr, int row, int col, int count) {
        if (row > -1 && row < arr.length && col > -1 && col < arr[0].length) {
            if (row == arr.length - 1 && col == arr[0].length - 1) {
                count++;
                System.out.println("in here " + count);
                return count;
            }
            return dfsInt(arr, row + 1, col, count) + dfsInt(arr, row, col + 1, count);
        }
        return 0;
    }

    int dfsIntDP(int[][] arr, int row, int col, int[][] dp) {
        if (row > -1 && row < arr.length && col > -1 && col < arr[0].length) {
            if (row == arr.length - 1 && col == arr[0].length - 1)
                return 0;
            if (dp[row][col] != 0)
                return dp[row][col];
            dp[row][col] = dfsIntDP(arr, row + 1, col, dp) + dfsIntDP(arr, row, col + 1, dp) + 1;
        }
        return 0;
    }

    /**
     * the trick is to convert a recursive relation to a dp relation dfs(r,c) =
     * dfs(r+1,c)+dfs(r,c+1);
     * 
     * but the relation here for dp is built in bottom up manner
     */
    void countPathsDP(int[][] arr, int[][] dp) {
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
    }

    int LIS(int[] arr) {
        int i = arr.length - 1;
        // return LIShelper(arr, 0, -9999999);
        // return LIShelperReverse(arr, 0);
        return LISDP(arr);
    }

    // int LIShelper(int[] arr, int index){
    // if(index==1) {
    // if(arr[index-1] < arr[index]) return 2;
    // return 1;
    // }
    // if(arr[index-1]<arr[index]) return LIShelper(arr, index-1)+1;
    // // return LIShelper(arr, index-1);
    // return LIShelper(arr, index-1);
    // }

    int LIShelper(int[] arr, int index, int prev) {
        if (index == arr.length) {
            return 0;
        }

        int inc = 0, exc = 0;
        if (prev < arr[index])
            inc = LIShelper(arr, index + 1, arr[index]) + 1;
        // return LIShelper(arr, index-1);
        exc = LIShelper(arr, index + 1, prev);
        return Math.max(inc, exc);
    }

    int LIShelperReverse(int[] arr, int index) {
        if (index == arr.length - 2) {
            if (arr[index] < arr[index + 1])
                return 2;
            return 1;
        }
        if (arr[index] < arr[index + 1])
            return LIShelperReverse(arr, index + 1) + 1;
        return LIShelperReverse(arr, index + 1);
    }

    /**
     * 1 dp[0]= 1 dont forget 2 i = 1 3 j = 0, j<i 4 dp[i] = dp[j] 5 dp[i]++ 6
     * return max of dp
     */
    int LISDP(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j]) {
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }

        int max = -1;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    int russianDoll(int[][] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0][0];
        int[] dp = new int[arr.length];

        russianDollhelper(arr, dp);
        int max = -1;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    class russianDoll {
        int h;
        int w;

        russianDoll(int height, int width) {
            this.h = height;
            this.w = width;
        }
    }

    void russianDollhelper(int[][] arr, int[] dp) {
        russianDoll[] holder = new russianDoll[arr.length];
        int i = 0, j = 0;
        for (i = 0; i < holder.length; i++) {
            holder[i] = new russianDoll(arr[i][0], arr[i][1]);
        }
        sortClass(holder);
        for (i = 0; i < holder.length; i++) {
            System.out.println(holder[i].h + ", " + holder[i].w);
        }
        dp[0] = 1;
        for (i = 1; i < holder.length; i++) {
            for (j = 0; j < i; j++) {
                if ((holder[j]).h < holder[i].h && (holder[j]).w < holder[i].w && dp[i] < dp[j]) {
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }
    }

    void sortClass(russianDoll[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i].h < arr[j].h && arr[i].w < arr[j].w) {
                    russianDoll temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 1 add boundary condition check for n= 0and 1 2 also dp[1] = max pdf arr[0]
     * and arr[1] 2 after that just dp[i] = max(arr[i]+ dp[i-2], dp[i-1])
     */
    int houseRobber(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];

        if (n == 2)
            return arr[0] > arr[1] ? arr[0] : arr[1];
        int[] dp = new int[n];

        dp[0] = arr[0];
        dp[1] = arr[0] > arr[1] ? arr[0] : arr[1];

        for (int i = 2; i < arr.length; i++) {
            dp[i] = (arr[i] + dp[i - 2] > dp[i - 1]) ? (arr[i] + dp[i - 2]) : dp[i - 1];
        }
        return dp[dp.length - 1];
    }

    public int deleteAndEarn(int[] arr) {
        int n = arr.length;
        int i = 0, j = 0;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];

        ArrayList<Integer> list = new ArrayList<>();

        int sum = 0;
        for (i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1])
                sum += arr[i];
            else {
                list.add(sum);
                sum = arr[i];
            }
        }

        sortList(list, list.size() - 1, 0);

        int[] dp = new int[list.size()];

        for (i = 1; i < arr.length; i++) {
            for (j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j]) {
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }

        int max = -1;
        for (i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    void sortList(ArrayList<Integer> list, int end, int start) {
        if (end > start) {
            int low = start - 1;
            int pivot = end;
            int j;
            for (j = start; j < pivot; j++) {
                if (list.get(j) < list.get(pivot)) {
                    low++;
                    int temp = list.remove(low);
                    list.add(low, list.get(j));
                    list.add(j, temp);
                }
                if (j == 1)
                    System.out.println("line 283" + list);
            }
            low++;
            int temp = list.get(low);
            list.add(low, list.get(j));
            list.add(j, temp);

            sortList(list, low - 1, start);
            sortList(list, end, low + 1);
        }
    }

    /** also the masseuse problem */
    int highwayBillBoard(int[] board, int[] revenue, int dist) {
        int n = board.length;

        if (n == 0)
            return 0;
        if (n == 1)
            return revenue[0];

        int[] dp = new int[n];
        dp[0] = revenue[0];
        for (int i = 1; i < revenue.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] > dp[i] && board[i] - board[j] > dist) {
                    dp[i] = dp[j];
                }
            }
            dp[i] += revenue[i];
        }

        int max = -1;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println("max billboard revenue " + max);
        return max;
    }

    int uniquePathCount = 0;

    int noOfUniquePaths(int[][] arr) {
        int count = 0;
        int[] path = new int[7];
        noOfUniquePathsHelper(arr, 0, 0, path, 0);
        return uniquePathCount;
    }

    void noOfUniquePathsHelper(int[][] arr, int row, int col, int[] path, int pathIndex) {
        if (row >= arr.length || col >= arr[0].length)
            return;
        // if(pathIndex>=path.length) return;
        path[pathIndex] = arr[row][col];
        if (row == arr.length - 1 && col == arr[0].length - 1) {
            uniquePathCount++;
            print1DMatrix(path);
            System.out.println();
            return;
        }
        int path_index = pathIndex++;
        noOfUniquePathsHelper(arr, row + 1, col, path, path_index);
        noOfUniquePathsHelper(arr, row, col + 1, path, path_index);
    }

    int minJumpCount = Integer.MAX_VALUE;// 1

    int minJumps(int[] arr) {
        int[] dp = new int[arr.length];
        minJumpHelper(arr, dp, 0, 0);
        return dp[dp.length-1];
        // return minJumpCount;
    }

    void minJumpHelper(int[] arr, int[] dp, int index, int count) {
        // int jump = minJumpCount;
        // System.out.println("line 359 "+index);
        if (index >= arr.length){
            return;// 2
        }
        if(dp[index]!=0) {
            // minJumpCount = dp[index];
            // return;
            // if()
        }
        if (index == arr.length - 1) {// 3
            // if(jump<minJumpCount) minJumpCount = jump;
            minJumpCount = Math.min(minJumpCount, count);
            dp[index] = minJumpCount;
            System.out.println("final count " + count);
            return;
        }
        for (int i = 1; i <= arr[index]; i++) {// 4 start from i=1
            minJumpHelper(arr, dp, index + i, count + 1);
        }

    }

    int rodCutting(int[] price, int size){ //size is for fixing the for loop iteration number
        if(size <=0) return 0;

        int max = Integer.MIN_VALUE;

        for(int i =0; i<size; i++){ 
            max = Math.max(max, price[i]+rodCutting(price, size- i-1)); //either select or not
        }
        return max;
    }

    //trying a unifying pattern for rod cutting, coin change and knapsack
    int rodCuttingIncludeExclude(int[] price, int[] length, int L, int index){ //size is for fixing the for loop iteration number
        if(index<0) return 0;
        if(L<=0) return Integer.MIN_VALUE;
        int incl = price[index]+rodCuttingIncludeExclude(price, length, L-length[index], index); //either select or not
        int excl = rodCuttingIncludeExclude(price, length, L, index-1);
        return Math.max(incl,excl);
    }

    int rodCuttingDP(int[] arr){
        int[] dp = new int[arr.length+1];

        dp[0] = 0;

        //the code is similar to LIS i=1, j=0, 
        //after for lopp dp[i] value 
        for(int i=1; i<=arr.length; i++ ){
            int max = Integer.MIN_VALUE;
            for(int j = 0; j<i; j++){
                max = Math.max(dp[i-j-1]+arr[j], max);//same as recursive function, just change the arrays
            }
            
            dp[i] = max;
        }
        return dp[dp.length-1];
    }


     /** similar to rod cutting, we have inifinite supply of coins and 
     * we have to find no of ways we can make change
     * in rod cutting too, we could take any piece as many times we wanted
     */
    int coinChange(int[] arr, int sum, int index){
        System.out.println("sum "+sum);
        if(sum==0) return 1;
        if(sum<0) return 0;
        if(index <0) return 0;
        return coinChange(arr, sum-arr[index], index) + coinChange(arr, sum, index-1);
    }

    //https://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/
    /** if we use for loop as in rod cutting
     * public static int count(int[] S, int N)
	{
		// if total is 0, return 1
		if (N == 0) {
			return 1;
		}

		// return 0 if total becomes negative
		if (N < 0) {
			return 0;
		}

		// initialize total number of ways to 0
		int res = 0;

		// do for each coin
		for (int i = 0; i < S.length; i++)
		{
			// recur to see if total can be reached by including
			// current coin S[i]
			res += count(S, N - S[i]);
		}

		// return total number of ways
		return res;
    }
    
    this counts permutations as well
     */

    int knapsack(int[] val, int[] wt, int weightLimit, int index){
        if(weightLimit <0) return Integer.MIN_VALUE;
        if(index<0) return 0; //removing equal to causes problems
        int incl = val[index]+knapsack(val, wt, weightLimit-wt[index], index-1);
        int excl = knapsack(val, wt, weightLimit, index-1);

        return Math.max(incl, excl);
    }
    

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] holder = new int[nums.length-k+1]; int holderIndex = 0;
        int left_index = 0;
        int right_index = k-1;
        
        int max_index = 0; int second_index;
        for(int i =1; i<k; i++){
            if(nums[i]>nums[max_index]) {
                second_index = max_index;
                max_index =i;
            }
        }

        if(max_index!=0) {
            second_index = 0;
        } else {
            second_index = 1;
        }    
        
        // for(int i =1; i<k; i++){
        //     if(nums[i]>nums[second_index] && second_index!=max_index) {
        //         second_index =i;
        //     }
        // }
        
        System.out.println("max "+max_index);
        System.out.println("second "+ second_index);
        
        holder[holderIndex++] = nums[max_index];
        for(int i = k; i<nums.length; i++){
            right_index++; left_index++;
            if(max_index<left_index){
                if(nums[second_index]<nums[right_index]){
                    max_index = right_index;
                }
                max_index= second_index;
            }
            if(nums[max_index]<nums[right_index]){
                second_index  = max_index;
                max_index = right_index;
            }
            
            holder[holderIndex++] = nums[max_index];
        }
        
        return holder;
    }

   

    //21 June
    void allSubsets(int[] arr, int[] subset, int index){
        if(index == arr.length) {
            print1DMatrix(subset);
            System.out.println();
            return;
        }
        subset[index] = 0;
        allSubsets(arr, subset, index+1);
        subset[index] = arr[index];
        allSubsets(arr, subset, index+1);
    }


    void matrixBlockSum(int[][] arr, int k){
        int R = arr.length; int C = arr[0].length;
        int[][] dp = new int[R][C];
        // Arrays.fill(dp, 0);
        for(int i =0; i<R; i++){
            for(int j =0; j<C; j++){
                // if(dp[i][j]!=0){
                    matrixBlockSumHelper(arr, dp, i, j, k);
                    System.out.println(dp[i][j]);
                // }
            }
        }
        printMatrix(dp);
    }
    
    void matrixBlockSumHelper(int[][] arr, int[][] dp, int row, int col, int k){
        int r1 = (row +k)>=arr.length?arr.length:(row+k);
        int r2 = (row-k)<=0?0:(row-k);

        int c1 = (col +k)>=arr[0].length?arr[0].length:(col+k);
        int c2 = (col-k)<=0?0:(col-k);
        int sum = 0;
        for(int i =r2; i<r1; i++){
            for(int j= c2; j<c1; j++){
                sum+=arr[i][j];
            }
        }
        System.out.println("sum "+sum);
        dp[row][col] = sum;
    }

    //https://leetcode.com/problems/target-sum/

    // int targetSum(int[] arr){

    //     targetSumHelper
    // }

    /**this problem and the one below it are complementary
     * one is to find if a subset exists with given sum
     * second is to print all subsets with the sum
     * 
     * recursive approach, add or ignore
     * 
     * dp approach
     * create 2d array
     */
    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    void subsetSum(int[] arr, int sum, int index){
        int[] dp = new int[arr.length];
        subsetSumHelper(arr, dp, sum, index);
    }

    void subsetSumHelper(int[] arr, int[] dp, int sum, int index){
        if(sum == 0) {
            print1DMatrix(dp);
            System.out.println("found");
            return;
        }
        if(index>=arr.length) return;
        dp[index] = arr[index];
        subsetSumHelper(arr, dp, sum - arr[index], index+1);
        dp[index] = 0;
        subsetSumHelper(arr, dp, sum , index+1);
    }


    // https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/


    /**
     * POINTS 1 either include the elment or not LCS exc = f(arr, i+1, prev) inc =
     * f(arr, i+1, arr[i]) return max
     * 
     * 2 knapsack similar include or exclude 3 coin change diff is supply is
     * infinite so return f( S, m - 1, n ) + f( S, m, n-S[m-1] );
     * 
     * 3 similar subset sum 
     * dp[index] = arr[index];
     * subsetSumHelper(arr, dp, sum - arr[index], index+1); the element is added and sum is reduced
     * dp[index] = 0;
     * subsetSumHelper(arr, dp, sum , index+1); //elem is ignored
     */


    public static void main(String[] args) {
        DP dp = new DP();
        int[][] arr = { { 8, 2, 1 }, { 3, 9, 7 }, { 2, 1, 8 } };
        // dp.countPaths(arr);
        // dp.countPaths(arr);
        // int[] LISArr = {3,10,2,1,20};
        // int[] LISArr = {50, 3, 10, 7, 40, 80};
        int[] LISArr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        // System.out.println( dp.LIS(LISArr));

        int[][] russianDollArr = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
        // System.out.println(dp.russianDoll(russianDollArr));
        int[] house = { 2, 7, 9, 3, 1 };// {1,2,3,1};
        // System.out.println(dp.houseRobber(house));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(7);
        list.add(9);
        list.add(2);
        // dp.sortList(list, list.size()-1, 0);
        // System.out.println(list);

        // int board[] = {6, 7, 12, 13, 14};
        // int revenue[] = {5, 6, 5, 3, 1};
        // System.out.println(dp.highwayBillBoard(board, revenue, 5));

        int[][] uniquePathArr = { { 1, 2, 3 }, { 4, 5, 6 } };
        // System.out.println(dp.noOfUniquePaths(uniquePathArr));

        int[] jumpArr = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
        // System.out.println("min no of jumps " + dp.minJumps(jumpArr));

        int[] rodValArr = {1, 5, 8, 9, 10, 17, 17, 20}; 
        int[] rodLengthArr = {1,2, 3, 4, 5, 6, 7, 8};  
        // System.out.println(dp.rodCutting(rodArr, rodArr.length));

        // System.out.println("the max value of rod cutting is "+dp.rodCuttingDP(rodArr));
        System.out.println(dp.rodCuttingIncludeExclude(rodValArr, rodLengthArr, rodLengthArr[rodLengthArr.length-1], rodValArr.length-1));

        int[] coins = {1,2,3};
        // System.out.println("coin change ways "+ dp.coinChange(coins, 4, coins.length-1));

        int val[] = new int[] { 60, 100, 120 }; 
        int wt[] = new int[] { 10, 20, 30 }; 
        int W = 50; 
        int n = val.length; 
        // System.out.println("max knapsack value "+dp.knapsack(val, wt, W, n-1)); 

        int[] subsetArr = {1,2,3};
        // dp.allSubsets(subsetArr, new int[subsetArr.length], 0);

        int[][] blockSum = {{1,2,3},{4,5,6},{7,8,9}};
        // dp.matrixBlockSum(blockSum, 1);

        int set[] = {3, 34, 4, 12, 5, 2}; int sum = 9;
        // dp.subsetSum(set, sum, 0);

        int[] nums = {1,3,-1,-3,5,3,6,7}; int k = 3;
        // dp.print1DMatrix(dp.maxSlidingWindow(nums, k));

    }
}