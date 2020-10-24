import java.util.*;
import utilCustom.*;

public class DP {

    /** 
     * PAINTER -> BOOK ALLOCATION -> DP INDEX -> BINARY SEARCH
     * PALINDROME -> USE l, i+l-1<n
     * ELSE FOR ALL OTHERS USE size [m+1][n+1]
    */

    public int factorial(int n){
        if(n==0 || n==1) return 1;
        int[] dp = new int[n+1];
        if(dp[n]==0){
            dp[n] = n*factorial(n-1);
            return dp[n];
        }
        else return dp[n];
    }

    public int factorialDP(int n){
        // if(n==0 || n==1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i =2; i<=n; i++){
            // if(dp[n]==0){
                dp[i] = i*dp[i-1];
                // return dp[n];
            // }
        }
        return dp[n];
    }

    /**
     * points: only right and down movements, else visited matrix would have
     * needed global var count to be used
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

        countPathsHelper(arr, countHolder);
        utilCustom.Utility.printMatrix(countHolder);
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

    // https://leetcode.com/problems/unique-paths-ii/

    /**
     * the trick is to convert a recursive relation to a dp relation dfs(r,c) =
     * dfs(r+1,c)+dfs(r,c+1);
     * 
     * but the relation here for dp is built in bottom up manner
     */
    void countPathsHelper(int[][] arr, int[][] dp) {
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

    int LIShelper(int[] arr, int index, int prev) {
        if (index == arr.length) {
            return 0;
        }

        int inc = 0, exc = 0;
        if (prev < arr[index]) inc = LIShelper(arr, index + 1, arr[index]) + 1;
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
     * 1 dp[0]= 1 
     * 2 i = 1 
     * 3 j = 0, j<i 
     * 4 dp[i] = dp[j] 5 dp[i]++ 6
     * return max of dp
     */
    int longestIncreasingSubsequenceDP(int[] arr) {
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


    int highwayBillBoard(int[] board, int[] revenue, int dist) {
        int n = board.length;

        if (n == 0) return 0;
        if (n == 1) return revenue[0];

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


    int maxSumIncreasingSubsequence(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        
        for(int i =0; i<n; i++){
            dp[i] = nums[i];
        }

        /** the dp[i] is incremented by the addition of nums[j],
         * and for the next iteration of j, we check if the nums[i] + nums[j]>dp[i];
         * if yes, then nums[j] is greater than nums[j-1] and dp[i] is incremented
         * 
         * nums[i]+nums[j]>=dp[i] the equal to here is needed in case 3+100 = 1+2+100
         */
        for(int i =1 ; i<n; i++){
            for(int j =0; j<i; j++){
                if(nums[j]<nums[i] && nums[i]+nums[j]>=dp[i]){
                    dp[i] += nums[j];
                }
            }
        }

        int max  = 0;
        for(int i=0; i<n; i++){
            max = Math.max(max, dp[i]);
        }
        utilCustom.Utility.print1DMatrix(dp);
        return max;
    }
    
    // https://leetcode.com/problems/maximum-length-of-pair-chain/submissions/
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        int[] dp = new int[n];
        
        Arrays.sort(pairs,(x,y)->x[0]-y[0]);
        dp[0] =1;
        
        for(int i =1; i<n; i++){
            for(int j =0; j<i; j++){
                if(pairs[i][0]>pairs[j][1] && dp[i]<dp[j]){
                    dp[i] = dp[j];
                }    
            }
            dp[i]++;
        }
        return dp[n-1];
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
     * Also the masseuse problem
     * 
     * WEIGHTED JOB SCHEDULING
     * https://leetcode.com/problems/maximum-profit-in-job-scheduling/ 
     * 1 SORT ON THE BASIS OF END TIMES
     * 2 ASSIGN MAX OF (jobs[i].profit, dp[i-1]);
     * 3 CHECK FOR j = i-1 till 0;
     * 4 FINAL CONDN dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
     * 
    */
    class Job {
        int start, end, profit;
        
        public Job(int s, int e, int p) {
            this.start = s;
            this.end = e;
            this.profit = p;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = (new Job(startTime[i], endTime[i], profit[i]));
        }
        
        int dp[] = new int[jobs.length];
        Arrays.sort(jobs, (a,b)->(a.end - b.end));
        
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++){
            dp[i] = Math.max(jobs[i].profit, dp[i-1]);
            for(int j = i-1; j >= 0; j--){
                if(jobs[j].end <= jobs[i].start){ 
                    // if no overlap
                    dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
                    break;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int val : dp) max = Math.max(val, max);
        return max;
    }
        

    // https://leetcode.com/explore/interview/card/
    // top-interview-questions-hard/121/dynamic-programming/860/
    /** 
     * max prod in contiguous subarray 
     * we basically hold 4 variables and an ans var
     * prev is assigned to curr,
     * max is max of prev*arr[i], arr[i];
     * same for min
    */
    // https://www.youtube.com/watch?v=vtJvbRlHqTA
    int maxProdSubarray(int[] arr){
        if(arr.length == 0) return -1;

        int curr_min = arr[0];
        int curr_max = arr[0];
        int prev_min = arr[0];
        int prev_max = arr[0];
        int ans = arr[0];

        for(int i =1; i<arr.length; i++){
            curr_max = Math.max(prev_max*arr[i], Math.max(prev_min*arr[i], arr[i]));
            curr_min = Math.min(prev_max*arr[i],Math.min(prev_min*arr[i], arr[i]));
            
            ans = Math.max(ans, curr_max);
            prev_max = curr_max; prev_min = curr_min;
        }
        System.out.println("max prod subarray "+ans);
        return ans;
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

    // https://leetcode.com/explore/featured/card/
    // october-leetcoding-challenge/560/week-2-october-8th-october-14th/3494/
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


    int deleteAndEarn9jul(int[] arr){
        if(arr.length==0) return 0;
        if(arr.length ==1) return arr[0];
       
        int maxEl = 0;
        for(int i=0; i<arr.length; i++){
            maxEl = Math.max(maxEl, arr[i]);
        }

        int[] dp = new int[maxEl+1];

        for(int i =0; i<arr.length; i++){
            dp[arr[i]]+=arr[i];
        }

        utilCustom.Utility.print1DMatrix(dp);
        dp[2] = Math.max(dp[1], dp[2]);
        for(int i =3; i<dp.length; i++){
            dp[i] = Math.max(dp[i]+dp[i-2], dp[i-1]);
        }

        // System.out.println(dp[dp.length-1]);
        // dp[dp.length-2] = Math.max(dp[dp.length-2],dp[dp.length-1]);
        // for(int i =dp.length-3; i>0; i--){
        //     dp[i] = Math.max(dp[i+1], dp[i]+ dp[i+2]);
        // }

        System.out.println("max is "+dp[1]);
        return dp[1];
    }


    // https://leetcode.com/problems/decode-ways/
    public int numDecodings(String s) {
        int n = s.length();
        if(n==0) return 0;        
    
        int[] dp = new int[n+1];
        
        dp[0] = 1;
        dp[1] = s.charAt(0) =='0'? 0:1;
        
        for(int i =2; i<=n; i++){
            int singles = Integer.valueOf(s.substring(i-1,i));
            int doubles = Integer.valueOf(s.substring(i-2,i));
            
            if(singles>=1) dp[i] += dp[i-1];
            if(doubles>=10 && doubles<=26) dp[i] += dp[i-2];
        }
        return dp[n];
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
            utilCustom.Utility.print1DMatrix(path);
            System.out.println();
            return;
        }
        int path_index = pathIndex++;
        noOfUniquePathsHelper(arr, row + 1, col, path, path_index);
        noOfUniquePathsHelper(arr, row, col + 1, path, path_index);
    }

    /** points:
     * 1 initialize first row and col
     * 2 can be done in 2 ways either top down or bottom up
     */
    int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];
        
        dp[0][0] = 1;
        
        for(int i =0; i<m; i++) dp[i][0] = 1;
        for(int j =0; j<n; j++) dp[0][j] = 1;
        
        for(int i = 1; i<m; i++){
            for(int j =1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        System.out.println(dp[m-1][n-1]);
        return dp[m-1][n-1];
    }

    int minJumpCount = Integer.MAX_VALUE;// 1

    //this is the recursive approach
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

    /**
     * points
     * 1 assign infinity to dp index 1 till end 
     * 2 for i =1 loop j from j=0 till i
     * 3 if arr[j]+j >=i checking if we can reach index i from index j
     */
    int jumpDP(int[] arr) {
        // int[] result = new int[arr.length];
        int []dp = new int[arr.length];
        dp[0] = 0;
        for(int i=1; i < arr.length ; i++){
            dp[i] = Integer.MAX_VALUE-1;
        }
        
        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                if(arr[j] + j >= i && dp[i] > dp[j] + 1){
                    // result[i] = j;
                    dp[i] = dp[j] + 1;
                }
            }
        }
        
        return dp[dp.length-1];
    }

    int countStairs = 0;
    //jumps of 1,2
    int stairs(int n){
        if(n<0) return 0;
        if(n==0) {
            countStairs++;
            return 0;
        }
        return stairs(n-1) + stairs(n-2) + stairs(n-3);
    }

    //steps are only of 1,2 or 3
    int staircase(int n){
        int[] dp = new int[n+1];
        
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        for(int i=3; i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        return dp[n];
    }

    // https://leetcode.com/problems/min-cost-climbing-stairs

    // https://web.stanford.edu/class/archive/cs/cs161/cs161.1168/lecture12.pdf
    /**  
     * MIN NO OF CUTS CAN BE SIMILAR TO PALNDROME PARTITION
     * f(str){
     *    if(isPalindrome(str))
     *    int min = MAX;
     *    for(int i = 0; i<str.length; i++){
     *       if(isPalindrome(str.substring(0, i+1))){
     *          min = Math.min(min, 1+ f(str.substring(i+1)));
     *       }
     *    }        
     * }
    */

    int rodCutting(int[] price, int size){ //size is for fixing the for loop iteration number
        if(size <=0) return 0;

        int max = Integer.MIN_VALUE;

        for(int i =0; i<size; i++){ 
            max = Math.max(max, price[i]+rodCutting(price, size- i-1)); //either select or not
        }
        return max;
    }


    //trying a unifying pattern for rod cutting, coin change and knapsack
    int rodCuttingIncludeExclude(int[] price, int[] length, int L, int index){ 
        //size is for fixing the for loop iteration number
        if(index<0) return 0;
        if(L<0) return Integer.MIN_VALUE;
        if(L==0) return 0;
        //either select or not
        int incl = price[index]+rodCuttingIncludeExclude(price, length, L-length[index], index); 
        int excl = rodCuttingIncludeExclude(price, length, L, index-1);
        return Math.max(incl,excl);
    }

    int rodCuttingDP(int[] arr){
        int[] dp = new int[arr.length+1];

        dp[0] = 0;

        //the code is similar to LIS i=1, j=0, 
        //after for loop dp[i] value 
        for(int i=1; i<=arr.length; i++ ){
            int max = Integer.MIN_VALUE;
            for(int j = 0; j<i; j++){
                max = Math.max(dp[i-j-1]+arr[j], max);
                //same as recursive function, just change the arrays
            }
            
            dp[i] = max;
        }
        return dp[dp.length-1];
    }

    int rodCutting2dDP(int[] value, int[] length, int limit){
        int[][] dp = new int[value.length+1][limit+1];
        
        for(int i =1; i<=value.length; i++){
            for(int j = 1; j<=limit; j++){
                // if(i==0|| j==0) dp[i][j] = 0;
                /**the base condition needs to be taken care of
                 * the first row and the first column need to be correctly filled
                 */
                if(length[i-1]>j) dp[i][j] = dp[i-1][j];
                else {
                    // System.out.println("i "+i+", j "+j);
                    dp[i][j] = Math.max(value[i-1]+dp[i][j-length[i-1]], dp[i-1][j]);
                    /**
                     * as we can select the same element multiple times, dp[i] is used
                     * row doesn't denote indexes, it simply denotes if the element 
                     * is selected or not
                     * hence we have 0th row(1st el not selected)
                     */
                }
            }
        }
        utilCustom.Utility.printMatrix(dp);
        return dp[value.length][limit];
    }

     /** similar to rod cutting, we have inifinite supply of coins and 
     * we have to find no of ways we can make change
     * in rod cutting too, we could take any piece as many times as we wanted
     */

    /***the no of ways to make up a sum if infinite supply of coins of each denomination 
     * is given*/
    int coinChange(int[] arr, int sum, int index){
        System.out.println("sum "+sum);
        if(sum==0) return 1;
        if(sum<0) return 0;
        if(index <=0 && sum>=1) return 0;
        // if(index==0) return 1;
        return coinChange(arr, sum-arr[index-1], index) + coinChange(arr, sum, index-1);
    }


    /**
     * https://www.techiedelight.com/coin-change-problem-find-total-
     * number-ways-get-denomination-coins/ 
     * if we use for loop as in rod cutting
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

    /** 
     * POINTS :
     * 1 INITIALIZE FIRST ROW TO INTEGER.MAX_VALUE
     * 2 SET dp[0][0] = 0;
     * 3 IF SET EL IS GREATER THAN CURRENT AMOUNT, TAKE FROM ABOVE
     * 4 ELSE TAKE MINIMUM OF (dp[i-1][j], dp[i][j-coins[i-1]]+1)
     * 
     * dp[i][j-coins[i-1]]+1
     * 
     * HERE AS NO OF COINS IS UNLIMITED, WE CAN MOVE IN THE SAME ROW
     * TO REUSE THE COIN, SO dp[i]
     * AND +1 IS BECAUSE WE HAVE USED THE CURR COIN (coins[i-1]),
     * so sum is reduced by coins[i-1].
     * 
     * 5 DO A FINAL CHECK BEFORE RETURNING, AS THERE CAN BE CASES 
     * WHEN NO POSSIBLE CONFIG IS FOUND
     * 
    */
    /**coin change to achieve the sum with min no of coins */
    // https://leetcode.com/problems/coin-change/submissions/
    public int coinChangeDP(int[] coins, int amount) {
        int n = coins.length;
        if(n==0) return -1;

        int[][] dp = new int[n+1][amount+1];
        
        for(int i =0; i<=amount; i++) dp[0][i] = Integer.MAX_VALUE-100;
        dp[0][0] = 0;
        
        for(int i =1; i<=n; i++){
            for(int j =1; j<=amount; j++){
                if(coins[i-1] > j) dp[i][j] = dp[i-1][j];
                else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i-1]]+1);//
                }
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE-100?-1:dp[n][amount];
    }

    public int coinChangeSingleArrayDP(int[] coins, int amount) {
        int[] ans = new int[amount + 1];
        ans[0] = 0;
        for(int i = 1; i <= amount; i++){
            int min = Integer.MAX_VALUE-1;
            for(int coin: coins){
                if(coin<=i) min = Math.min(ans[i-coin]+1, min);
            }
            ans[i] = min;
        }
        return ans[amount] == Integer.MAX_VALUE-1? -1:ans[amount];
    }

    // FIRST COL -> 1
    // COINS CAN BE REUSED

    int coinChangeNumberOfWays(int[] coins, int total){
        int n = coins.length; 
        if(n==0) return 0;
        int[][] dp = new int[n+1][total+1];
        
        // Initializing first column of array to 1
        // because a sum of 0 can be made
        // in one possible way: {0}       
        for(int i=0; i <= n; i++) dp[i][0]=1;

        for(int i=1; i<=n; i++){
            for(int j = 1; j<=total; j++){
                if(j<coins[i-1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
            }
        }
        utilCustom.Utility.printMatrix(dp);
        return dp[n][total];

    }

    int knapsack(int[] val, int[] wt, int remainingWeight, int index){
        if(remainingWeight <0) return Integer.MIN_VALUE;
        if(index<=0) return 0; //removing equal to causes problems
        // if(remainingWeight == 0 || index == val.length) return ;
        int incl = val[index] + knapsack(val, wt, remainingWeight-wt[index], index);
        int excl = knapsack(val, wt, remainingWeight, index-1);

        return Math.max(incl, excl);
    }

    // REMEMBER BOUNDARY CONDITIONS
    //
    
    // WITH MEMOIZATION
    int knapsackMemo(int[] val, int[] wt, int remainingWeight, int currVal, int index, int[][] dp){
        if(remainingWeight<0 || index>=val.length) return 0;
        // System.out.println("index "+index+" currVal "+ currVal);
        if(remainingWeight == 0) return dp[index][remainingWeight] = currVal;
        
        // if(dp[index][remainingWeight]!=0) return dp[index][remainingWeight];
        utilCustom.Utility.printMatrix(dp);
        return 
        dp[index][remainingWeight] = 
        Math.max(
        knapsackMemo(val, wt, remainingWeight - wt[index], currVal + val[index], index, dp),
        knapsackMemo(val, wt, remainingWeight, currVal, index+1, dp));
        // boundary conditions are remainingWeight - wt[index] and index+1

    }

    // USE MAX OF (T[i-1][j], T[i][j-w[i-1]] + v[i-1])
    // above and same row but with reduced weight
    public int knapSackDP(int[] v, int[] w, int W)
	{
		// T[i][j] stores the maximum value of knapsack having weight less
		// than equal to j with only first i items considered.
		int[][] T = new int[v.length + 1][W + 1];

		// do for ith item
		for (int i = 1; i <= v.length; i++)
		{
			// consider all weights from 0 to maximum capacity W
			for (int j = 1; j <= W; j++)
			{
				// don't include ith element if capacity is less than w[i-1]
				if (w[i-1] > j) T[i][j] = T[i-1][j];
				else {
					// find maximum value we get by excluding or including the ith item
                    int excl = T[i-1][j];
                    // same row T[i], if no repitition, T[i-1]
					int incl = T[i][j-w[i-1]] + v[i-1]; 
					// System.out.println("for i = "+i+", j = "+j +"; excl "+excl+ ", incl "+incl);
					T[i][j] = Math.max(excl, incl);
				}
			}
		}

		// return maximum value
		utilCustom.Utility.printMatrix(T);	
		return T[v.length][W];
    }

    // IMP CHECK WHY dp[0][0] = 1 was required
    // https://leetcode.com/problems/perfect-squares/
    int perfectSquares(int n){
        double n1 = n;
        double limit1 = Math.sqrt(n1);
        int limit = (int) limit1;
        System.out.println(limit);

        int[] nums = new int[limit];

        for(int i =0; i<nums.length; i++){
            nums[i] = (i+1)*(i+1);
        }

        int[][] dp = new int[limit][n+1];
        for(int i =0; i<=n; i++){
            dp[0][i] = i;
        }

        dp[0][0] =1;
       
        for(int i =1; i<limit; i++){
            for(int j =1; j<=n; j++){
                
                //take care here
                if (nums[i]>j) dp[i][j] = dp[i-1][j];
                else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-nums[i]]+1);
                }
            }
        }
        utilCustom.Utility.printMatrix(dp);
        return dp[limit-1][n];
    }


    //https://leetcode.com/problems/minimum-cost-for-tickets/
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        for (int day : days) set.add(day);

        int lastDay = days[days.length-1], dp[] = new int[lastDay+1];
        for (int i = 1; i <= lastDay; i++) {
            if (!set.contains(i)) {
                dp[i] = dp[i-1];
            }
            else {
                dp[i] = dp[i-1]+costs[0];

                int j = (i >= 7) ? i-7 : 0;
                dp[i] = Math.min(dp[i], dp[j] + costs[1]);

                j = (i >= 30) ? i-30 : 0;
                dp[i] = Math.min(dp[i], dp[j] + costs[2]);
            }
        }

    return dp[lastDay];
    }
    

    //21 June
    void allSubsets(int[] arr, int[] subset, int index){
        if(index == arr.length) {
            utilCustom.Utility.print1DMatrix(subset);
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
        utilCustom.Utility.printMatrix(dp);
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

    // INCLUDE EXCLUDE
    // https://leetcode.com/problems/target-sum/
    int sumCount = 0;
    public int findTargetSumWays(int[] nums, int target) {
        // return f(nums, 1, target+nums[0]) + f(nums, 1, target-nums[0]);
        f(nums, 0, target);
        return sumCount;
    }
    
    void f(int[] nums, int index, int sum){
        if(index==nums.length) {
            if(sum ==0) sumCount++; 
            return;
        }
        
        f(nums, index+1, sum+nums[index]);
        f(nums, index+1, sum-nums[index]);
    }

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
            utilCustom.Utility.print1DMatrix(dp);
            System.out.println("found");
            return;
        }
        if(index>=arr.length) return;
        dp[index] = arr[index];
        subsetSumHelper(arr, dp, sum - arr[index], index+1);
        dp[index] = 0;
        subsetSumHelper(arr, dp, sum , index+1);
    }
    //IMP
    //2 subset problems for practice
    
    // https://www.geeksforgeeks.org/partition-a-set-into-two-subsets
    //-such-that-the-difference-of-subset-sums-is-minimum/
    
    // https://www.youtube.com/watch?v=7BynUy5ml0I
    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/



    // https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/


    /**
     * POINTS 
     * 1 either include the element or not 
     * LCS exc = f(arr, i+1, prev) inc = f(arr, i+1, arr[i]) return max
     * 
     * 2 knapsack similar include or exclude 
     * 3 coin change diff is that the supply of coins is
     * infinite so return f( S, m - 1, n ) + f( S, m, n-S[m-1] );
     * 
     * 3 similar subset sum 
     * dp[index] = arr[index];
     * subsetSumHelper(arr, dp, sum - arr[index], index+1); the element is added and sum is reduced
     * dp[index] = 0;
     * subsetSumHelper(arr, dp, sum , index+1); //elem is ignored
     */

    boolean subsetSumDP(int[] set, int sum){
        // dp[0][0] =true;
        int n = set.length;
        boolean[][] dp = new boolean[n][sum+1];

        for(int i=1; i<n; i++){
            if(i == set[0]) dp[0][i] = true;
        }
        for(int i = 1; i<set.length; i++){
            for(int j =0; j<=sum; j++){
                // if(j==0) dp[i][j] = true;
                // else 
                if(set[i]>j) dp[i][j] = dp[i-1][j];//tricky : false or from above
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-set[i]];
            }
        }
        utilCustom.Utility.printMatrixBool(dp);
        System.out.println("the subset exists : "+dp[n-1][sum]);
        return dp[n-1][sum];
    }


    int maxSizeSubset(int[] set, int sum){
        int maxLength = 0;
        maxSizeSubsethelper(set, sum, 0, 0, maxLength);
        return maxLength;
    }

    int maxSizeSubsethelper(int[] set, int sum, int index, int length, int maxLength){
        if(index >= set.length) return 0;
        if(sum == 0){
            maxLength = Math.max(maxLength, length);
            System.out.println("max in here " +maxLength);
            return 0;
        }
        return Math.max(maxSizeSubsethelper(set, sum-set[index], index+1, length+1, maxLength),
        maxSizeSubsethelper(set, sum, index+1, length, maxLength));
    }

    /** 
     * for every recursion involving selecting or not, remember
     * 1 the base condition will provide the output
     * 2 the index needs to be incremented and if selected subtract from some target
     * 3 add a unifying statement at the end
     */
    boolean canPartition(int[] set){
        int sum = 0;
        for(int i=0; i<set.length; i++){
            sum+=set[i];
        }
        if(sum%2!=0) return false;
        int target = sum/2;
        return canPartitionHelper(set, target ,0);
    }

    boolean canPartitionHelper(int[] set, int target, int index){
        if(target == 0) return true;
        if(index>=set.length) return false;
        System.out.println("target "+target+" current set el "+set[index]);
        return canPartitionHelper(set, target-set[index], index+1) || 
        canPartitionHelper(set, target, index+1);
    }

    boolean canPartitionDP(int[] set){
        int sum = 0;
        for(int i=0; i<set.length; i++){
            sum+=set[i];
        }
        if(sum%2!=0) return false;
        int target = sum/2;
        boolean[][] dp = new boolean[set.length+1][target+1];

        dp[0][0] = true;
        for(int i=1; i<=set.length; i++){
            for(int j =0; j<=target;j++){
                if(set[i-1]>j) dp[i][j] = dp[i-1][j];
                //ALWAYS USE ELSE
                else dp[i][j] = dp[i-1][j]|| dp[i-1][j-set[i-1]];
            }
        }
        utilCustom.Utility.printMatrixBool(dp);
        return dp[set.length][target];
    }


    // FIRST COL IS ALL TRUE LIKE LCS
    // https://leetcode.com/problems/partition-equal-subset-sum/
    public boolean canPartition2(int[] set) {
        int sum = 0;
        int n = set.length;
        for(int i=0; i<n; i++){
            sum+=set[i];
        }
        if(sum%2!=0) return false;
        int target = sum/2;
        boolean[][] dp = new boolean[n+1][target+1];//2
        // dp[0][0] =true;
        
        for(int i = 0; i<=n; i++) dp[i][0] = true;
        
        for(int i= 1; i<=n; i++){
            for(int j = 1; j<=target;j++){
                if(set[i-1]>j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-set[i-1]];
            }
        }
        utilCustom.Utility.printMatrixBool(dp);
        return dp[n][target];
    }


    /**We can use Dynamic Programming 
     * (similar to the way we find if a set can be partitioned into two equal sum subsets). 
     * Then we find the max possible sum, which will be our first partition.
     * Second partition will be the difference of the total sum and firstSum.
     * Answer will be the difference of the first and second partitions.   
     * PARTITION INTO SUBSETS WITH MIN DIFF
    */
    // https://practice.geeksforgeeks.org/problems/minimum-sum-partition3317/1
    public int minDifference(int set[]) { 
    
        int sum = 0;
        int n = set.length;
        for(int i=0; i<n; i++) sum+=set[i];
        //finding half of total sum, because min difference can be at max 0, 
        // if one subset reaches half, the other part must be teh other half

        int target = sum/2;
        boolean[][] dp = new boolean[n+1][target+1];//2
        
        for(int i = 0; i<=n; i++) dp[i][0] = true;
        
        for(int i= 1; i<=n; i++){
            for(int j = 1; j<=target;j++){
                if(set[i-1]>j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-set[i-1]];
            }
        }
        
        // we now find the max sum possible starting from target
        int firstPart = 0;
        for(int j = target; j>=0; j--){
            if(dp[n][j] == true) {
                firstPart = j; break;
            }
        }
        
        int secondPart = sum - firstPart;
        return Math.abs(firstPart - secondPart);
    } 
        

    /** how to think about this?
     * first use recursion. if i select start, then player 2 has to select either
     * start+1 or end. 
     * if he selects start+1, then
     * i have the min from start+2 till end. 
     * or if he goes for end, i have the min from start+1 till end.
     * same goes if i select end.
     * 
     * so relation is arr[start] + min{(start+1, end-1), (start+2, end)}
     *  arr[end] + min{(start, end-2), start+1,end-1)}
     * 
     * THE (START+1, END-1) DENOTES I SELECT START AND PLAYER 2 SELECTS END.
     * 
     * why not this relation arr[start] + min{(start+1, end-1) + arr[end], (start+2, end)}
     * 
     * ARR[END] IS NOT ADDED AS IT GOES TO PLAYER 2
     */
    int twoPlayerStoneGame(int[] arr, int start, int end){
        // if(end<start) return 0;
        if(end - start == 1) return Math.max(arr[start], arr[end]);

        return Math.max(
        arr[start] + 
        Math.min( twoPlayerStoneGame(arr, start+2, end),
        twoPlayerStoneGame(arr, start+1, end-1))
        ,
        arr[end] + 
        Math.min( 
            // arr[end-1] + 
            twoPlayerStoneGame(arr, start, end-2),
        // arr[start] + 
        twoPlayerStoneGame(arr, start+1, end-1))
        );
    }

    boolean twoPlayerStoneGameDP(int[] piles){
        int n = piles.length; int i =0; int sum = 0; int half = 0;
        
        int[][] dp = new int[n][n];
        
        for(i=0; i<n; i++) {
            dp[i][i] = piles[i];
            sum+=piles[i];
        }
        
        half = sum/2;
        
        for(int l=2; l<=n;l++){
            for( i = 0; i+l-1<n;i++){
                int j = i+l-1;
                
                int a = i+1<=j-1?dp[i+1][j-1]:0;
                int b = i+2<=j?dp[i+2][j]:0;
                int c = i<=j-2?dp[i][j-2]:0;
                
                dp[i][j] = Math.max(piles[i] + Math.min(a,b), 
                               piles[j]+ Math.min(a,c));
            }
        }
        
        utilCustom.Utility.printMatrix(dp);
        if(dp[0][n-1]>=half) return true;
        return false;
    }

    /** IMPORTANT here the difference between the element shuld be 1,
     * so pass arr[i][j] - 1 as prev, not infinity
    */
    int longestPathMatrix(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;

        int[][] dp = new int[n][m];

        for(int i =0; i<n ; i++){
            for(int j=0; j<m ; j++){
                max = Math.max(longestPathMatrixHelper(matrix, i, j, dp, matrix[i][j]-1), max);
            }
        }
        return max;
    }

    int longestPathMatrixHelper(int[][] matrix, int row, int col, int[][] dp, int prev){
        if(row>=0 && row<matrix.length
        && col>=0 && col<matrix[0].length
        && (matrix[row][col]-prev)==1){
            if(dp[row][col]!=0) return dp[row][col];
            int left = longestPathMatrixHelper(matrix, row, col-1, dp, matrix[row][col]);
            int right = longestPathMatrixHelper(matrix, row, col+1, dp, matrix[row][col]);
            int up = longestPathMatrixHelper(matrix, row-1, col, dp, matrix[row][col]);
            int down = longestPathMatrixHelper(matrix, row+1, col-1, dp, matrix[row][col]);

            dp[row][col] = Math.max(left, Math.max(right, Math.max(up, down)))+1;
            System.out.println("in here "+dp[row][col]);
            return dp[row][col];
        }
        return 0;
    }

    /** remember to replace all consectuive * with a single *
     * even though the resultant matrix is upper triangular,
     * the whole matrix is filled.
     * 
     */

    

    //https://www.geeksforgeeks.org/ways-to-arrange-balls-such-that-adjacent-
    //balls-are-of-different-types/
    // int arrangeBalls(int p , int q, int r){
    //     int n  = p+q+r;
    //     int[] arr = new int[n];
    //     int count =0;

    //     if(p!=0){
    //         arr[]
    //     }
    //     return arr[n-1];
    // }

    // void arrangeBallsHelper(int p, int q, int r, int[] arr, int count, int index){
    //     if(p!=0){
    //         arr[index-1] !=  
    //     }
    // }

    // 7 July
    // https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
    
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];
        
        for(int i =0; i<m; i++){
            dp[n-1][i] = A[n-1][i];
        }
        
        for(int i = n-2; i>=0; i--){
            for(int j = m-1; j>=0; j--){
                
                int lowerLeft = ((j-1>=0))?dp[i+1][j-1]: Integer.MAX_VALUE; 
                int down = dp[i+1][j];
                int lowerRight = (j+1)<m?dp[i+1][j+1]:Integer.MAX_VALUE;
                dp[i][j] = Math.min(lowerLeft, 
                                    Math.min(down, lowerRight)) + A[i][j];
            }
        }
        int min = Integer.MAX_VALUE; 
        for(int i =0; i<n; i++){
            min = Math.min(min, dp[0][i]);
        }
        utilCustom.Utility.printMatrix(dp);
        return min;
    }

    

    // public int maxSumDivThree(int[] nums) {
    //     int n = nums.length;   
        
    //     int[] length = new int[n];

    //     for(int i =1; i<n; i++){
    //         int sum =0;
    //         for(int j =0; j<i; j++){
    //             if(((sum+=nums[j])%3)==0){
                    
    //             }
    //         }
    //     }
    // }

    

    // https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
    // discuss/766002/Java-DP-without-recursion

    // https://leetcode.com/problems/wiggle-subsequence/
    /** 
     * similar to ap sequence, if diff>0 look for -ve and if
     * diff<0 look for +ve
     * compare and update if greater
     * */
    class Wiggle{
        int pos; int neg;
        Wiggle(int p , int n){
            this.pos = p; this.neg = n;
        }
    }
    
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        if(n==1) return 1;
        Wiggle[] dp = new Wiggle[n];
        for(int i =0; i<n; i++){
            dp[i] = new Wiggle(1,1);
        }
        
        for(int i =1; i<n; i++){
            for(int j =0; j<=i; j++){
                if(nums[i]-nums[j]>0){
                    dp[i].pos = Math.max(dp[j].neg+1, dp[i].pos);
                }
                else if(nums[i]-nums[j]<0){
                    dp[i].neg = Math.max(dp[j].pos+1, dp[i].neg);
                }
            }        
        }
        
        return Math.max(dp[n-1].pos, dp[n-1].neg);
    }


    ////////////////////////STRING DP
    // https://leetcode.com/problems/decode-ways/discuss/
    // 30358/Java-clean-DP-solution-with-explanation

    boolean wildcardMatch(String text, String pattern){
      
        char[] textArr = text.toCharArray();
        int n1 = textArr.length;
        char[] patternArr =  pattern.toCharArray();
        int n2 = patternArr.length;

        boolean[][] dp = new boolean[n1+1][n2+1];

        dp[0][0] =true;

        for(int i = 1; i<=n1; i++){
            for(int j=1; j<=n2; j++ ){
                if(textArr[i-1] == patternArr[j-1] || patternArr[j-1] == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(patternArr[j-1] == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else dp[i][j] = false;
            }
        }
        utilCustom.Utility.printMatrixBool(dp);
        return dp[n1][n2];
    }

    /** 
     * the difference b/w longest common subsequence and substring is 
     * in subsequence we take max of [i-1][j] or [i][j-1], but in 
     * substring we update only when chars match, else value = 0
     * */
    int longestCommonSubsequence(String text1, String text2) {
        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();
        
        int n1 = ch1.length; int n2 = ch2.length;
        
        int[][] dp = new int [n1+1][n2+1];
        
        dp[0][0] =0;
        
        for(int i =1; i<=n1; i++){
            for(int j =1; j<=n2; j++){
                if(ch1[i-1] == ch2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                } 
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);    
                }
            }
        }
        return dp[n1][n2];
    }
    
    // https://www.youtube.com/watch?v=hbTaCmQGqLg
    // longest repeating subsequence
    /** 
     * POINTS : 
     * 1 SAME DIMENSIONS AS LCS
     * 2 START FROM i,j = 1
     * 3 AFTER DP MATRIX FILLED, START BUILDING FR0M DP
     * 
     * 4 if(dp[i][j] == dp[i-1][j]){
     *       res.append(str1.charAt(i-1));
     *       i--;
     *   }
     * 
     * THE VALUE MATCHES, SO APPEND THE CHAR; IF i-1 USE str1 FOR j USE str2
     * GO IN THAT ORDER, DON'T ADD dp[i][j] == dp[i-1][j-1] FIRST
     * 
     * 5 AFTER THS GO TILL i>0 AND j>0
     * 6 REVERSE
    */
    // https://leetcode.com/problems/shortest-common-supersequence
    public String shortestCommonSupersequence(String str1, String str2) {
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        
        for(int i = 1; i<=str1.length(); i++){
            for(int j = 1; j<=str2.length(); j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1; // 1
                }
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        utilCustom.Utility.printMatrix(dp);
        int i = str1.length(); int j = str2.length();
        StringBuilder res = new StringBuilder();
        
        while(i>0 && j>0){
            
            if(dp[i][j] == dp[i-1][j]){
                res.append(str1.charAt(i-1));
                i--;
            }
            else if(dp[i][j] == dp[i][j-1]){
                res.append(str2.charAt(j-1));
                j--;
            }
            else {
                res.append(str1.charAt(i-1));
                i--; j--;
            }
        }
        System.out.println("i "+i+" j "+j);
        System.out.println(res);
        while(i > 0) {
            res.append(str1.charAt(i));i--;
        }
        while(j > 0) {
            res.append(str2.charAt(j-1));j--;
        }

        System.out.println(res);
        return res.reverse().toString();
    }

    // https://leetcode.com/problems/distinct-subsequences/

    /** PALINDROME QUES */    
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    // https://leetcode.com/problems/palindromic-substrings/
    int countPalindromicSubstrings(String s) {
        int n = s.length();
        int count = s.length();

        int[][] dp = new int[n][n];

        for (int l = 1; l <= n; l++) {
            for (int i = 0; l + i - 1 < n; i++) {
                int j = l + i - 1;
                if (l == 1)
                    dp[i][j] = 1;
                else if (l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1;
                    count++;
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                    count++;
                }
            }
        }
        utilCustom.Utility.printMatrix(dp);
        System.out.println("all palindromic substrings' count : " + count);
        return count;
    }

    // https://leetcode.com/problems/palindrome-partitioning
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        helper(res, curr, s);
        return res;
    }
    
    
    void helper(List<List<String>> res, List<String> curr, String str){
        if(str.length() == 0){
            // System.out.println(curr);
            res.add(new ArrayList<>(curr));
            return;
        } 
        
        for(int i = 0; i<str.length(); i++){
            String palin = str.substring(0, i+1);
            if(utilCustom.Utility.isPalindrome(palin)) {
                // System.out.println(palin);
                curr.add(palin);
                helper(res, curr, str.substring(i+1));// pass the string
                curr.remove(curr.size()-1);
            }
        }
    }

    /** 
     * POINTS:
     * 1 NO INDEX IS PASSED AS SUBSTRING IS PASSED FOR NEXT CALL
     * 2 WE FIND PALINDROMIC SUBSTRING AND THEN PASS THE OTHER SUSBTRING TO NEXT CALL
     * 3 NO GLOBAL VAR, INITIALISE INSIDE AND RETURN
    */
    // int min = Integer.MAX_VALUE;
    // https://leetcode.com/problems/palindrome-partitioning-ii
    public int minCut(String s) {
        if(s.length() == 1) return 0;
        return helper(s);
    }
    
     // like dfs in matrix
    int helper(String str){
        int min = Integer.MAX_VALUE;
        if(utilCustom.Utility.isPalindrome(str)) return 0;
                
        for(int i = 0; i<str.length(); i++){
            String palin = str.substring(0, i+1);
            if(utilCustom.Utility.isPalindrome(palin)){
                // System.out.println(palin);
                min = Math.min(min, 1 + helper(str.substring(i+1)) );
            }
        }
        return min;// base case
    }

    /** 
     * https://www.youtube.com/watch?v=lDYIvtBVmgo
     * POINTS :
     * 1 SAME AS MATRIX MULTIPLICATION
     * 2 FOR PALINDROME ALWAYS USE SAME SIZED ARRAY, NOT n+1
     * 3 FOR (l=1) USE 0 AS INDIVIDUAL LETTER IS A PALINDROME 
     * 
     * 4 l = 1 to n; i = 0 to i+l-1<n; j = i+l-1
     * 
     * 5 for(k = i; k < j)
     *    min = (dp[i][k] + dp[k+1][j])
     * 
     * 6 dp[i][j] = min+1;
     * 
    */
    // https://leetcode.com/problems/palindrome-partitioning-ii/
    public int minCutDP(String s) {
        if(utilCustom.Utility.isPalindrome(s)) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for(int l =1; l<=n; l++){
            for(int i=0; i+l-1<n; i++){
                int j = i+l-1;
                if(l==1) {
                    dp[i][j] = 0; continue;
                }
                if(utilCustom.Utility.isPalindrome(s.substring(i,j+1))) dp[i][j] = 0;
                else{
                    int min = Integer.MAX_VALUE;
                    for(int k = i; k<j; k++){
                        min = Math.min(min, dp[i][k]+dp[k+1][j]);
                    }
                    dp[i][j] = min+1;    
                    System.out.println("dp["+i+"]["+j+"] "+dp[i][j]);
                }
            }
        }
        utilCustom.Utility.printMatrix(dp);
        return dp[0][n-1];
    }

    /** 
     * MATRIX CHAIN MULTIPLICATION
     * BURSTING BALLOONS
     * PAINTER'S PARTITION
     * PALINDROMIC PARTITIONING
     * */

    /**
     * https://www.youtube.com/watch?v=kMK148J9qEE 
     * MATRIX MULTIPLICATION , UPPER TRIANGULAR
     * POINTS : 
     * 1 i+l<n not i+l-1
     * as here we consider l=2 to include 3 elements; one more
     * 2 k =i+1 till j 
     * 3 dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + arr[i]*arr[k]*arr[j]);
     * 
    */


    public int matrixMultiplication(int arr[]){
        int n = arr.length;
        int dp[][] = new int[n][n];

        for(int l=2; l <n; l++){
            for(int i=0; l+i< n; i++){
                int j = i + l;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i+1; k <j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + arr[i]*arr[k]*arr[j]);
                }
            }
        }
        utilCustom.Utility.printMatrix(dp);
        return dp[0][arr.length-1];
    }

    /** 
     * 1 THE APPROX RELATON IS 
     * f(painters, 0) = sum(0, k)+ f(painters-1); // for loop
     * 2 THE PARTITIONS NEED TO BE CONTIGUOUS, SO SUM ALWAYS STARTS FROM 0
     * 3 BASE CONDITIONS : IF END OD BOARD IS REACHED, RETURN THAT INDEX
     * 4 IF 1 PAINTER, THEN SUM AND NO FURTHER PARTITION
     * 
     * 5 SAME AS K SUM PARTITIONS, WE REDUCE PAINTERS BY 1 FOR NEXT CALL
     * 
     * 6 MIN(MAX()) : MAX HELPS TO GET THE LARGEST VALUES
     * AND THEN MIN PROVIDES THE MINIMUM OF THESE MAXIMUMS
     * 
     * 
    */
    int paintersPartition(int[] boards, int painters){
        return painterHelper(boards, painters, 0);
    }

    int painterHelper(int[] boards, int painters, int index){
        if(index==boards.length-1) return boards[boards.length-1];

        if(painters == 1) return utilCustom.Utility.sumSubarray(boards, index, boards.length-1);

        int min = Integer.MAX_VALUE;
        for(int k = 0; k<boards.length; k++){

            min = Math.min(min, 
            Math.max
            (utilCustom.Utility.sumSubarray(boards, 0, k), painterHelper(boards, painters-1, k+1)));
        }
        return min;
    }

    // PAINTER'S PARTITION
    // { 10, 20, 60, 50, 30, 40 }; int painters = 4; 
    int paintersPartitionDP(int arr[], int k) { 
        int n = arr.length;
        int dp[][] = new int[k+1][n+1]; 
    
        // 1 painter 
        for (int i = 1; i <= n; i++) dp[1][i] = sum(arr, 0, i - 1); 
    
        // 1 board 
        for (int i = 1; i <= k; i++) dp[i][1] = arr[0]; 
    
        // 2 to k partitions 
        for (int i = 2; i <= k; i++) { 
            // 2 to n boards 
            for (int j = 2; j <= n; j++) { 
                int min = Integer.MAX_VALUE;    
    
                // i-1 th separator before position arr[p=1..j] 
                for (int p = 1; p <= j; p++) {
                    min = Math.min(min, Math.max(sum(arr, p, j - 1), dp[i - 1][p]));  
                } 
                dp[i][j] = min; 
            } 
        } 
        utilCustom.Utility.printMatrix(dp);
        return dp[k][n]; 
    } 

    int sum(int arr[], int from, int to) { 
        int total = 0; 
        for (int i = from; i <= to; i++) 
            total += arr[i]; 
        return total; 
    } 

    // https://leetcode.com/problems/burst-balloons/

    /** 
     * POINTS : 
     * 1 USED A HASHSET TO ADD WORDS IN DICT
     * 2 ALWAYS USE l<=n
     * 3 FOR l=1, use continue IF MATCHES.
     * 4 SUBSTRING WORKS WITH 2 ARGS, FOR PRINTING SINGLE CHAR, USE i, i+1
     * 5 k goes from i till j; dp[i][k] && dp[k+1][j] 
    */
    // https://leetcode.com/problems/word-break/
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        if(n == 0) return false;
        HashSet<String> set = new HashSet<>();
        
        for(int i =0; i<wordDict.size(); i++) set.add(wordDict.get(i));
        if(set.contains(s)) return true;
        
        boolean[][] dp = new boolean[n][n];
        
        for(int l=1; l<=n; l++){//2
            for(int i =0; l+i-1<n; i++){
                if(l==1 && set.contains(s.substring(i,i+1))){
                     dp[i][i] = true; continue; //3painter
                }
                int j = i+l-1;
                if(set.contains(s.substring(i,j+1))) {//4
                    System.out.println("in here "+s.substring(i, j+1));
                    dp[i][j] =true; continue;// continue
                }
                for(int k = i;k<j; k++){//5
                    if(dp[i][k] == true && dp[k+1][j] == true) dp[i][j] = true;
                }
            }
        }
        utilCustom.Utility.printMatrixBool(dp);
        return dp[0][n-1];
    }

    /** 
     * POINTS :
     * 1 USE A D P OF SIZE m+1, n+1
     * IT'S DIFFICULT TO HANDLE CASES OF INSGKLE ROW CONTAINING 1
     * 2 if(matrix[i-1][j-1] == '1')
     * 3 RESULT IS MAX OF ALL VALUES
     * 4 RETURN RESULT*RESULT
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
        utilCustom.Utility.printMatrix(dp);
        System.out.println(result);
        return result*result;
    }
        
    // SIMILAR MCM
    // https://leetcode.com/problems/burst-balloons/
    // https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/

    // https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
    // https://www.techiedelight.com/find-n-digit-binary-strings-without-consecutive-1s/
    // https://www.techiedelight.com/probability-alive-after-taking-n-steps-island/
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    // https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
    
    // https://leetcode.com/problems/target-sum/

    // https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
    // https://leetcode.com/problems/minimum-height-trees/
    // https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/ //done

    // https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/

    // https://leetcode.com/problems/count-square-submatrices-with-all-ones/
    // https://leetcode.com/problems/maximum-length-of-repeated-subarray/

    // https://www.geeksforgeeks.org/probability-knight-remain-chessboard/
    public static void main(String[] args) {
        DP dp = new DP();

        // System.out.println(dp.factorialDP(4));
        // System.out.println(dp.factorial(4));

        // dp.perfectSquares(12);

        int[][] arr = { { 8, 2, 1 }, { 3, 9, 7 }, { 2, 1, 8 } };
        // dp.countPathsMatrixDP(arr);
        // dp.countPaths(arr);
        // int[] LISArr = {3,10,2,1,20};
        // int[] LISArr = {50, 3, 10, 7, 40, 80};
        int[] LISArr = //{ 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        // {10, 22, 9, 33, 21, 50, 41, 60, 80};
        {10,9,2,5,3,7,101,18};
        // System.out.println( dp.LIS(LISArr));
        // System.out.println(dp.LISPrint9jul(LISArr));

        int[][] russianDollArr = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
        // System.out.println(dp.russianDoll(russianDollArr));

        int[] startTime = {1,2,3,3};
        int[] endTime = {3,4,5,6};
        int[] profit = {50,10,40,70};
        // dp.jobScheduling(startTime, endTime, profit);

        int[] maxProd = {6, -3, -10, 0, 2};
        // dp.maxProdSubarray(maxProd);

        int[] house = { 2, 7, 9, 3, 1 };// {1,2,3,1};
        // System.out.println(dp.houseRobber(house));

        int maxArr[] = //{1, 101, 2, 3, 100, 4, 5}; 
        { 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11 };
        // System.out.println("Sum of maximum sum "+ 
        //                     "increasing subsequence is "+ 
        //                       dp.maxSumIncreasingSubsequence(maxArr)); 

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(7);
        list.add(9);
        list.add(2);
        // dp.sortList(list, list.size()-1, 0);
        // System.out.println(list);

        // int[] numsDeleteAndEarn = //{1,1,1,2,4,5,5,5,6} ;//{3,3,3,4,2,2};//
        // {8,10,4,9,1,3,5,9,4,10};
        // {1,6,3,3,8,4,8,10,1,3};
        // {12,32,93,17,100,72,40,71,37,92,58,34,29,78,11,84,77,90,92,35,12,
        // 5,27,92,91,23,65,91,85,14,42,28,80,85,38,71,62,82,66,3,33,33,55,60,
        // 48,78,63,11,20,51,78,42,37,21,100,13,60,57,91,53,49,15,45,19,51,2,
        // 96,22,32,2,46,62,58,11,29,6,74,38,70,97,4,22,76,19,1,90,63,55,64,
        // 44,90,51,36,16,65,95,64,59,53,93};
        // {4,10,10,8,1,4,10,9,7,6};
        // dp.deleteAndEarnDP(numsDeleteAndEarn);
        // dp.deleteAndEarn4jul(numsDeleteAndEarn);
        // dp.deleteAndEarn9jul(numsDeleteAndEarn);

        // int board[] = {6, 7, 12, 13, 14};
        // int revenue[] = {5, 6, 5, 3, 1};
        // System.out.println(dp.highwayBillBoard(board, revenue, 5));

        int[][] uniquePathArr = { { 1, 2, 3 }, { 4, 5, 6 } };
        // System.out.println(dp.noOfUniquePaths(uniquePathArr));

        int[] jumpArr = 
        // { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
        { 1, 3, 6, 1, 0, 9 }; 
        // System.out.println("min no of jumps " + dp.minJumps(jumpArr));
        // System.out.println("min no of jumps " + dp.jumpDP(jumpArr));

        int[] steps = {1,2};
        int stairs = 3;
        // System.out.println("no of ways to climb stairs : "+dp.staircase(stairs));
        // dp.stairs(4);
        // System.out.println(dp.countStairs);

        int[] rodValArr = {1, 5, 8, 9, 10, 17, 17, 20}; 
        int[] rodLengthArr = {1, 2, 3, 4, 5, 6, 7, 8};  
        // System.out.println("rod cutting 2d dp "+dp.rodCutting2dDP(rodValArr, rodLengthArr, 8));
        // System.out.println(dp.rodCutting(rodValArr, rodValArr.length));

        // System.out.println("the max value of rod cutting is "+dp.rodCuttingDP(rodArr));
        // System.out.println(dp.rodCuttingIncludeExclude(rodValArr, rodLengthArr, 
        // rodLengthArr[rodLengthArr.length-1], rodValArr.length-1));

        // int[] coins = {1,2,3}; int coinSum =4;
        // System.out.println("coin change ways "+ dp.coinChange(coins, 4, coins.length));
        // dp.coinChangeDP(coins, coinSum);
        int[] coinsMin = {1,2,5};
        // {5,6,9};//
        // {25, 10, 5}; 
        int sum  = 11;
        // 30;
        // System.out.println("the min of of coins is "+dp.coinChangeDP(coinsMin, sum));

        int[] coinsNumber = {1,2, 3}; int total = 5;
        // dp.coinChangeNumberOfWays(coinsNumber, total);

        int val[] = {1,1};//{10, 40, 50, 70}; 
        int wt[]  = {2,1};//{1, 3, 4, 5}; 
        int W = 3; 
        int n = val.length; 
        // ans 110 unbounded knapsack
        // System.out.println("max knapsack value "+dp.knapsack(val, wt, W, n-1)); 
        // System.out.println("Knapsack value with memoization is " +
        // dp.knapsackMemo(val, wt, W, 0, 0 , new int[val.length+1][W+1]));
        
        // System.out.println("Knapsack value is " + dp.knapSackDP(val, wt, W));


        // int[] days = {1,4,6,7,8,20};
        // int[] days = {1,2,3,4,5,6,7,8,20};
        // int[] costs = {2, 7, 15};
        // System.out.println(" min train ticket cost : "+dp.trainticketDP(days, costs));
        // dp.mincostTickets(days, costs);


        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        // dp.mincostTickets(days, costs);

        int[] subsetArr = {1,2,3};
        // dp.allSubsets(subsetArr, new int[subsetArr.length], 0);

        int[][] blockSum = {{1,2,3},{4,5,6},{7,8,9}};
        // dp.matrixBlockSum(blockSum, 1);

        int subSetDP[] = {1,5,5,11};//{3, 34, 16, 12, 5, 2}; 
        int subsetSum = 36;
        // dp.subsetSum(set, sum, 0);
        // dp.subsetSumDP(subSetDP, subsetSum);
        // System.out.println("can be partitioned :"+ dp.canPartition(subSetDP));
        // dp.canPartition2(subSetDP);

        int[] setPartition = //{1, 2, 3, 4};
        {28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,
        43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,
        90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,
        87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79};
        // System.out.println("can the set be partitioned : "+dp.canPartition(setPartition));
        // System.out.println("can the set be partitioned : "+dp.canPartitionDP(setPartition));


        // int[] stone= {1, 5, 3, 7, 10};
        int[] stone= {8, 15, 3, 7};
        // System.out.println("max stone value by first player is "+
        //dp.twoPlayerStoneGame(stone, 0, stone.length-1));
        // System.out.println("did the first player win : "+dp.twoPlayerStoneGameDP(stone));

        // System.out.println(dp.wildcardMatch("xbylmz", "x?y*z"));

        int set[] = {2, 3, 5, 7, 10, 15};
        int sumSet  = 20;
        // System.out.println("max length subset is "+dp.maxSizeSubset(set, sumSet));

        int mat[][] = {{1, 2, 9}, {5, 3, 8}, {4, 6, 7}};
        // System.out.println("longest path in matrix is "+dp.longestPathMatrix(mat));

        int[][] fallingSum = {{1,2,3},{4,5,6},{7,8,9}};
        // dp.minFallingPathSum(fallingSum);

        int[] apSeq = //{3,6,9,10};
        {44,46,22,68,45,66,43,9,37,30,50,67,32,47,44,11,15,4,11,6,
        20,64,54,54,61,63,23,43,3,12,51,61,16,57,14,12,55,17,18,25,
        19,28,45,56,29,39,52,8,1,21,17,21,23,70,51,61,21,52,25,28};
        
        // dp.longestAP(apSeq);

        String palinCut = "abcbm";
        // dp.minCutDP(palinCut);

        String str1 = "abac", str2 = "cab";
        dp.shortestCommonSupersequence(str1, str2);


        int matrixMul[] = {1,2,3,4};
        //{4,2,3,5,3};
        // dp.matrixMultiplication(matrixMul);

        
        // int[] boards = { 10, 20, 60, 50, 30, 40 }; int painters = 4; 
        // dp.paintersPartition(boards, boards.length, painters);
        int[] boards = {10, 20, 30, 40}; int painters = 2;
        // System.out.println("min time div "+dp.paintersPartitionDP(boards, painters));


        String s= "iam"; List<String> wordDict = new ArrayList<>();
        // wordDict.add("i");  wordDict.add("a"); wordDict.add("am"); wordDict.add("ace");
        // dp.wordBreak(s, wordDict);

        char[][] square =
        {{'1','0','1','0','0'},
        {'1','0','1','1','1'},
        {'1','1','1','1','1'},
        {'1','0','0','1','0'}};
        // dp.maximalSquare(square);
    }
}