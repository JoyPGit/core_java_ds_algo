package Java_DS_Algo;

import java.util.*;

import utilCustom.Utility;;

public class DP {

    /**
     * 
     * RELN -> PAINTER, STAIRS, COIN 1D
     * 
     * subset -> knapsack, coin change no of ways,
     * subset, coin -> 1st col TRUE, 1
     * 1d coin(always for min no), rod cutting, train ticket
     * book allocation
     * match : 
     * substr maxLen, subseq, +1, +2 
     * else : 
     * substr 0,      subseq Math.max 
     * 
     *           substr       subseq
     * longest.  i,j+1, 0    i,j+1, max
     * palin.    1,0         i,j+2, max 
     *           match,no     match,no
     * 
     * 
     * job scheduling
     * stairs, uniqe paths, house robber
     * knapsack, subset, coin change(no of ways),
     * wiggle, longest path
     * palindrome subseq, 
     * partition,
     * for(k = i; k < j)
     *    min = (dp[i][k] + dp[k+1][j])
     * 
     * painter, mcm
     * word dict
     * 
     * 
     * STAIRS - INCLUDE EXCLUDE
     * DEL AND EARN, HOUSE ROBBER, 
     * PAINTER -> BOOK ALLOCATION -> DP INDEX -> BINARY SEARCH
     * PALINDROME -> USE l, i+l-1<n
     * ELSE FOR ALL OTHERS USE size [m+1][n+1]
     * 
     * MIN CUTS, PALINDROME, ROD CUT
     * DFS MATRIX GOLDMINE
     * RECURSION INCLUDE EXCLUDE MEMO
     * TRAIN TICKET
     * DEL AND EARN
     * 
    */
     /**
     * POINTS 
     * 1 either include the element or not 
     * LCS exc = f(arr, i+1, prev) inc = f(arr, i+1, arr[i]) return max
     * 
     * 2 knapsack similar include or exclude 
     * 
     * 3 coin change diff is that the supply of coins is
     * infinite so return f( S, m - 1, n ) + f( S, m, n-S[m-1] );
     * 
     * 3 similar subset sum 
     * dp[index] = arr[index];
     * subsetSumHelper(arr, dp, sum - arr[index], index+1); the element is added and sum is reduced
     * dp[index] = 0;
     * subsetSumHelper(arr, dp, sum , index+1); //elem is ignored
     */

    Utility utility = new Utility();

    public int factorial(int n){
        if(n==0 || n==1) return 1;
        return n*factorial(n-1);
    }

    public int factorialDP(int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i =2; i<=n; i++){
            dp[i] = i*dp[i-1];
        }
        return dp[n];
    }

    public int factorialMemoize(int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        if(dp[n]!=0) return dp[n];

        dp[n] = n * factorialDP(n-1);
        return dp[n];
    }


    /////////////////////////// HOUSE ROBBER
    /**
     * 1 add boundary condition check for n= 0 and 1 
     * 2 also dp[1] = max of arr[0] and arr[1] 2 
     * 3 dp[i] = max(arr[i]+ dp[i-2], dp[i-1])
     */
    int houseRobber(int[] arr) {
        int n = arr.length;

        if (n == 0) return 0;
        if (n == 1) return arr[0];
        if (n == 2) return arr[0] > arr[1] ? arr[0] : arr[1];

        int[] dp = new int[n];

        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            dp[i] = Math.max(arr[i] + dp[i-2], dp[i-1]);
        }
        return dp[dp.length - 1];
    }

    /** 
     * POINTS :
     * 1 TRY TO CONVERT TO HOUSE ROBBER
     * 2 CREATE A NEW ARRAY OF SIZE = max el of arr
     * 3 TAKE CUMULATIVE SUM
    */
    // [3,1]
    // https://leetcode.com/problems/delete-and-earn/
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        // if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int n = nums[nums.length-1]+1;
        System.out.println(n);
        int[] base = new int[n];
        int[] dp = new int[n];
        
        for(int i =0; i<nums.length; i++) base[nums[i]] += nums[i];
        
        dp[0] = base[0];
        dp[1] = Math.max(dp[0], base[1]);
        
        for(int i = 2; i<n; i++){
            dp[i] = Math.max(dp[i-2] + base[i], dp[i-1]);
        }
        
        return dp[n-1];
    }

    //////////// MAX PROD SUBARRAY

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

    ///////////////////////// COIN CHANGE 1D

    int rodCutting1dDP(int[] arr){
        int[] dp = new int[arr.length+1];

        dp[0] = 0;

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

    /** 
     * FILL DP WITH AMT+1, dp[0][0] = 0
     * 
     * POINTS :
     * 1 dp[0] = 0, NOT SELECTING ANY CON CAN GGIVE A VALUE OF 0,
     * IT'S NOT IMPOSSIBLE, SO DON'T RETURN -1
     * 2 2 FOR LOOPS, SIMILAR TO PARTITION,
     * COMPARING ALL COIN VALUES(j) FOR AN amount(i)
    */
    // https://leetcode.com/problems/coin-change/solution/
    public int coinChange1D(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // IMP
    // https://leetcode.com/problems/minimum-cost-for-tickets/
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        for (int day : days) set.add(day);

        int lastDay = days[days.length-1], dp[] = new int[lastDay+1];
        for (int i = 1; i <= lastDay; i++) {
            if (!set.contains(i)) dp[i] = dp[i-1];
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

    /**
     * POINTS :
     * 1 SIMILAR TO COIN CHANGE
     * 2 CREATE A nums ARRAY TO STIORE SQUARES, fill with (i+1)*(i+1)
     * 3 USE  SAME RELATION
     * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-nums[i-1]] +1);
     * +1 for current el,
    */
    // https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
        int sq = (int)Math.sqrt(n);
        int[][] dp = new int[sq+1][n+1];
        
        int[] nums = new int[n+1];
        for(int i =0; i<=n; i++){
            nums[i] = (i+1)*(i+1);
        }
        
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        
        for(int i =1; i<=sq; i++){
            for(int j =1; j<=n; j++){
                if(nums[i-1]>j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-nums[i-1]] +1);
            }
        }
        return dp[sq][n];
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
    

    /////////////////////////// STAIR, UNIQUE PATH

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

    /** 
     * 
     * can start from either 0 or 1st index, hence recurrence
     * 1 USED RECURSION AND THEN APLIED MEMOIZATION
     * f(i) = min(f(i+1), f(i+2))
     * 
     * 2 MEMOIZATION
     * if(dp[index]!=0) return dp[index]
     * return dp[index] = min
     * 
     * 3 NEVER FORGET BASE CONDITON
     * if(index >= cost.length) return 0;
     * if(dp[index]!=0) return dp[index];
     * 
     * 
     */
    // https://leetcode.com/problems/min-cost-climbing-stairs
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n]; 
        return Math.min(stairsHelper(cost, 0, dp), stairsHelper(cost, 1, dp)); // 1
    }
    
    int stairsHelper(int[] cost, int index, int[] dp){
        if(index >= cost.length) return 0; // 2
        if(dp[index]!=0) return dp[index]; // 3
        int min = Integer.MAX_VALUE-100;
        // 4 i=0 same index, causes overflow, i determines jump length
        for(int i = 1; i<3; i++){         
            // 5 cost of current
            min = Math.min(min, cost[index] + stairsHelper(cost, index+i, dp)); 
        }
        return dp[index] = min; // 6
    }


    ////////////// JUMP GAME

    // check partition
    // https://leetcode.com/problems/jump-game-ii/
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        return jumpHelper(nums, 0, dp);
    }
    
    int jumpHelper(int[] nums, int index, int[] dp){
        if(index>=nums.length-1) return 0; // base condn
        if(dp[index]!=0)return dp[index];
        
        int min = Integer.MAX_VALUE-10000;
        for(int i = 1; i<=nums[index]; i++){ // i value
            min = Math.min(min, 1 + jumpHelper(nums, index+i, dp)); // cost
        }
        return dp[index] = min;
    }

    /**
     * https://www.youtube.com/watch?v=cETfFsSTGJI
     * points
     * 1 assign infinity to dp index 1 till end 
     * 2 for i =1 loop j from j=0 till i
     * 3 if arr[j]+j >=i checking if we can reach index i from index j
     * if yes and dp[i] > dp[j]+1, we update
     */
    int jumpDP(int[] arr) {
        // int[] result = new int[arr.length];
        int n = arr.length;
        int []dp = new int[n];
        dp[0] = 0;
        for(int i=1; i < arr.length ; i++){
            dp[i] = Integer.MAX_VALUE-1;
        }
        
        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                // if can cross i, check jumps(dp[j] + 1)
                if(arr[j] + j >= i && dp[i] > dp[j] + 1){
                    // result[i] = j;
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return dp[n-1];
    }

    /////////////////////////// PARTITION
    // LIKE CLIMBING STAIRS

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

        if(painters == 1) return Utility.sumSubarray(boards, index, boards.length-1);

        int min = Integer.MAX_VALUE;
        for(int k = 0; k<boards.length; k++){

            min = Math.min(min, 
            Math.max
            (Utility.sumSubarray(boards, 0, k), painterHelper(boards, painters-1, k+1)));
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
                    min = Math.min(min, Math.max(dp[i - 1][p], sum(arr, p, j - 1)));  
                } 
                dp[i][j] = min; 
            } 
        } 
        Utility.printMatrix(dp);
        return dp[k][n]; 
    } 

    int sum(int arr[], int from, int to) { 
        int total = 0; 
        for (int i = from; i <= to; i++) 
            total += arr[i]; 
        return total; 
    }

    /** 
     * k transactions (stock buy sell)
     * POINTS : 
     * 1 LIKE PAINTER
     * 2 TWO OPTIONS, EITHER I DO OR DON'T DO ANY TRANSACTION
     * 3 IF I DO, SELECT MAX FROM p TILL j-1 i.e.
     * THE STOCK COULD HAVE BEEN BOUGHT ON DAY 0 AND SOLD ON p, 
     * AND WE BUY AGAIN ON p, 
     * SO PROFIT = prices[j] - prices[p] + profit from previous transactions
     * 
     * previous transactions = dp[i-1][p]
     * i-1 because transaction is reduced by one as this transaction 
     * is included in k
     * 
     * 2, [3,0,5,10]
     * bought at 0, sold at 5, bought at 5 sold at 10 
     * profit = 10
     * 
     * size [k+1][n], 0 transactions
     * reduce transaction
     * 
     */ 
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n<2) return 0;
        
        int[][] dp = new int[k+1][n];
        
        // k+1 as 0 transactions is an option
        for(int i =1; i<=k; i++){
            for(int j=1; j<n; j++){
                int max = 0;
                // p from 0 till j-1
                for(int p = 0; p<j; p++){
                    max = Math.max(max, prices[j] - prices[p] + dp[i-1][p]);
                }
                // no transaction case
                dp[i][j] = Math.max(max, dp[i][j-1]);
            }
        }
        return dp[k][n-1];
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
        if(Utility.isPalindrome(str)) return 0;
                
        for(int i = 0; i<str.length(); i++){
            String palin = str.substring(0, i+1);
            if(Utility.isPalindrome(palin)){
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
        if(Utility.isPalindrome(s)) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for(int l =1; l<=n; l++){
            for(int i=0; i+l-1<n; i++){
                int j = i+l-1;
                if(l==1) {
                    dp[i][j] = 0; continue;
                }
                if(Utility.isPalindrome(s.substring(i,j+1))) dp[i][j] = 0;
                else{
                    int min = Integer.MAX_VALUE;
                    for(int k = i; k<j; k++){
                        min = Math.min(min, dp[i][k] + dp[k+1][j]);
                    }
                    dp[i][j] = min+1;    
                    System.out.println("dp["+i+"]["+j+"] "+dp[i][j]);
                }
            }
        }
        Utility.printMatrix(dp);
        return dp[0][n-1];
    }


    // similar 
    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

    // https://leetcode.com/problems/burst-balloons/

    /** 
     * POINTS : 
     * 1 USE l<=n
     * 2 SUBSTRING WORKS WITH 2 ARGS, FOR PRINTING SINGLE CHAR, USE i, i+1
     * 3 k goes from i till j; dp[i][k] && dp[k+1][j] 
     * 
     * "applepeneapple", ["apple,pen"]
     * using s.substring(i,k+1) &&  s.substring(k+1,j+1) at 5 fails
     * 
    */
    // https://leetcode.com/problems/word-break/
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        if(n == 0) return false;
        
        for(int i =0; i<wordDict.size(); i++) wordDict.add(wordDict.get(i));
        if(wordDict.contains(s)) return true;
        
        boolean[][] dp = new boolean[n][n];
        
        for(int l=1; l<=n; l++){//2
            for(int i =0; l+i-1<n; i++){
                int j = i+l-1;
                if(wordDict.contains(s.substring(i,j+1))) {//4
                    dp[i][j] =true;
                }else{
                    // k<j
                    for(int k = i;k<j; k++){
                        // 5
                        if(dp[i][k] == true && dp[k+1][j] == true) dp[i][j] = true;
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    // https://leetcode.com/problems/
    // number-of-ways-to-form-a-target-string-given-a-dictionary/

    ///////////////////////// MCM 
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
     * 
     * imp 
     * dp[i][k] + dp[k][j] + arr[i]*arr[k]*arr[j]
    */
    public int matrixMultiplication(int arr[]){
        int n = arr.length;
        int dp[][] = new int[n][n];

        for(int l=2; l<n; l++){
            for(int i=0; l+i<n; i++){
                int j = i + l;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + arr[i]*arr[k]*arr[j]);
                }
            }
        }
        Utility.printMatrix(dp);
        return dp[0][arr.length-1];
    }

    //////////////////// UNIQUE PATHS
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


    //////////////////////////// LIS

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
        int n = arr.length;
        int[] dp = new int[n];
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
        for (int i = 0; i<n; i++) {
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
        Utility.print1DMatrix(dp);
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


    /** 
     * 1 use LIS
     * 2 Collections.sort(arraylist)
     * 
    */
    // https://leetcode.com/problems/russian-doll-envelopes/
    class Russian{
        int width;
        int height;
        Russian(int w, int h){
            this.width = w;
            this.height = h;
        }
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        
        List<Russian> list = new ArrayList<>();
        
        
        for(int i =0; i<n; i++){
            list.add(new Russian(envelopes[i][0], envelopes[i][1]));
        }
            
        Collections.sort(list,((x,y)->{ // 1
            if(x.width == y.width) return x.height - y.height;
            return x.width - y.width;
        }));

        int[] dp = new int[n];
        dp[0] = 1;
        for(int i =1; i<n; i++){
            for(int j= 0;j<i; j++){
                if(list.get(j).width < list.get(i).width
                   && list.get(j).height < list.get(i).height
                   && dp[i]<dp[j]) dp[i] = dp[j];
            }
            dp[i]++;
        }
        
        int max =0;
        for(int i : dp) max = Math.max(max, i);
        return max;
    }


    /** 
     * similar to ap sequence, if diff>0 look for -ve and if
     * diff<0 look for +ve
     * compare and update if greater
     * */
    // https://leetcode.com/problems/wiggle-subsequence/
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
                if(nums[i]>nums[j]){
                    dp[i].pos = Math.max(dp[j].neg+1, dp[i].pos);
                }
                else if(nums[i]<nums[j]){
                    dp[i].neg = Math.max(dp[j].pos+1, dp[i].neg);
                }
            }        
        }
        
        return Math.max(dp[n-1].pos, dp[n-1].neg);
    }
    // https://leetcode.com/explore/featured/card/
    // october-leetcoding-challenge/560/week-2-october-8th-october-14th/3494/


    ///////////////////IMP JOB SCHEDULING
    /**
     * Also the masseuse problem, NOT GREEDY BUT DP
     * IMP : SORT ON THE BASIS OF END TIME AND START FROM
     * j= i-1 till 0
     * 
     * WEIGHTED JOB SCHEDULING
     * 1 SORT ON THE BASIS OF END TIMES
     * 2 ASSIGN MAX OF (jobs[i].profit, dp[i-1]);
     * 3 CHECK FOR j = i-1 till 0;
     * 4 FINAL CONDN dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
     * 
     * finding profit, not longest, so take Max of profits
     * 
    */
    // can be done in LIS fashion also, TLE though
    // https://leetcode.com/problems/maximum-profit-in-job-scheduling/ 
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
            dp[i] = Math.max(jobs[i].profit, dp[i-1]); // 1
            for(int j = i-1; j >= 0; j--){
                if(jobs[j].end <= jobs[i].start){  // 2
                    // if no overlap
                    dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]); // 3
                    break;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int val : dp) max = Math.max(val, max);
        return max;
    }
    

    ////////////////////////////////// KNAPSACK TYPE

    int knapsack(int[] val, int[] wt, int remainingWeight, int index){
        if(remainingWeight <0) return Integer.MIN_VALUE;
        if(index<=0) return 0; //removing equal to causes problems
        // if(remainingWeight == 0 || index == val.length) return ;
        int incl = val[index] + knapsack(val, wt, remainingWeight-wt[index], index);
        int excl = knapsack(val, wt, remainingWeight, index-1);

        return Math.max(incl, excl);
    }

    // REMEMBER BOUNDARY CONDITIONS
    
    // WITH MEMOIZATION
    int knapsackMemo(int[] val, int[] wt, int remainingWeight, int currVal, int index, int[][] dp){
        if(remainingWeight<0 || index>=val.length) return 0; // 1
        if(remainingWeight == 0) return dp[index][remainingWeight] = currVal;
        
        // if(dp[index][remainingWeight]!=0) return dp[index][remainingWeight];
        Utility.printMatrix(dp);

        dp[index][remainingWeight] =  // 2
        Math.max(
        knapsackMemo(val, wt, remainingWeight - wt[index], currVal + val[index], index, dp),
        knapsackMemo(val, wt, remainingWeight, currVal, index+1, dp));
        // boundary conditions are remainingWeight - wt[index] and index+1
        return dp[index][remainingWeight]; // 3
    }

    /** 
     * 
     * dp[n+1][W+1]
     * FIRST ROW AND COL ARE 0;
     * NO WEIGHT SO VAL = 0;
     * NO VAL SO WT = 0;
     * 
     * LIKE SUBSET SUM, BUT THE VALUE OF THE EL (v[i-1]) IS ALSO ADDED
     * 
    */
    public int knapSackDP(int[] v, int[] w, int W)
	{
		int n = v.length;
		int[][] dp = new int[n+1][W+1];

		for (int i = 1; i <= v.length; i++){
			for (int j = 1; j <= W; j++){
				if (w[i-1] > j) dp[i][j] = dp[i-1][j];
				else {
					// max of excluding or including the ith item
                    int excl = dp[i-1][j];
                    // same row T[i], if no repitition, T[i-1]
					int incl = dp[i][j-w[i-1]] + v[i-1]; 
					dp[i][j] = Math.max(excl, incl);
				}
			}
        }
		Utility.printMatrix(dp);
		return dp[v.length][W];
    }


    /** 
     * can't use painter partition technique as if index =2, then 
     * func should recur for length -2, not price[index].
     * that way price calculated will be for more than actual length.
     * 
    */
    int rodCutting(int[] price, int size){ 
        //size is for fixing the for loop iteration number
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


    int rodCuttingDP(int[] arr){
        int n = arr.length;
        int[] dp = new int[n+1];

        dp[0] = 0;
        for(int i=1; i<=arr.length; i++ ){
            int max = Integer.MIN_VALUE;
            for(int j = 0; j<i; j++){
                max = Math.max(dp[i-j-1]+arr[j], max);
            }
            dp[i] = max;
        }
        return dp[n-1];
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
        Utility.printMatrix(dp);
        return dp[value.length][limit];
    }

     /** similar to rod cutting, we have inifinite supply of coins and 
     * we have to find no of ways we can make change
     * in rod cutting too, we could take any piece as many times as we wanted
     */

    /**
     * the no of ways to make up a sum if infinite supply of coins 
     * of each denomination is given
     * */
    int coinChange(int[] arr, int sum, int index){
        System.out.println("sum "+sum);
        if(sum==0) return 1;
        if(sum<0) return 0;
        if(index == arr.length ) return 0;
        // if(index==0) return 1;
        return coinChange(arr, sum-arr[index], index) + coinChange(arr, sum, index+1);
    }


    /**
     * https://www.techiedelight.com/coin-change-problem-find-total-
     * number-ways-get-denomination-coins/ 
     * 
     * this counts permutations as well.
     * if we use for loop as in rod cutting
     */
    public static int count(int[] S, int N){
		// if total is 0, return 1
		if (N == 0) return 1;
		if (N < 0) return 0;

		// initialize total number of ways to 0
		int res = 0;

		// do for each coin
		for (int i = 0; i < S.length; i++){
			// recur to see if total can be reached by including
			// current coin S[i]
			res += count(S, N - S[i]);
		}
		return res;
    }
    

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
    // coin change to achieve the sum with min no of coins
    // https://leetcode.com/problems/coin-change/submissions/
    public int coinChangeDP(int[] coins, int amount) {
        int n = coins.length;
        if(n == 0) return -1;

        int[][] dp = new int[n+1][amount+1];
        
        for(int i =0; i<=amount; i++) dp[0][i] = Integer.MAX_VALUE-100;
        dp[0][0] = 0;
        
        for(int i =1; i<=n; i++){
            for(int j =1; j<=amount; j++){
                if(coins[i-1] > j) dp[i][j] = dp[i-1][j];
                else{
                    // 1 + dp[][]
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i-1]]+1);
                }
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE-100?-1:dp[n][amount];
    }

    // min no of coins
    // https://leetcode.com/problems/coin-change/solution/
    public int coinChange1DArr(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    // FIRST COL -> 1, NOT SELECTING ANY, same as subset
    // a way to make 0 can also be by not selecting any

    // COINS CAN BE REUSED
    // [2,1,5] -> indexes [2][1]
    //somplae just add up incl and exclude
    // https://leetcode.com/problems/coin-change-2
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
                if(j<coins[i-1]) dp[i][j] = dp[i-1][j]; // excl
                // excl + incl
                else dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]; 
            }
        }
        Utility.printMatrix(dp);
        return dp[n][total];
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
        Utility.printMatrix(dp);
        return dp[limit-1][n];
    }

    // TRYING TO USE MEMOIZATION
    // SEE CHEAPEST FLIGHTS WITH DP (ULTIMATE)
    // INCLUDE EXCLUDE
    // https://leetcode.com/problems/target-sum/
    int sumCount = 0;
    public int findTargetSumWays(int[] nums, int target) {
        // return f(nums, 1, target+nums[0]) + f(nums, 1, target-nums[0]);
        f(nums, 0, target);
        return sumCount;
    }
    
    void f(int[] nums, int index, int sum){
        // need to use all els
        if(index==nums.length && sum ==0){
            sumCount++; 
            return;
        }
        
        f(nums, index+1, sum+nums[index]);
        f(nums, index+1, sum-nums[index]);
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
        Utility.printMatrix(dp);
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

     /**
     * USING INCLUDE EXCLUDE TECHNIQUE AS IN KNAPSACK
     * 
     * dp approach
     * create 2d array
     */
    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    void subsetSum(int[] arr, int sum, int index){
        subsetSumHelper(arr, sum, index);
    }

    void subsetSumHelper(int[] arr, int sum, int index){
        if(sum == 0) {
            System.out.println("found");
            return;
        }
        if(index>=arr.length) return;
        subsetSumHelper(arr, sum - arr[index], index+1);
        subsetSumHelper(arr, sum , index+1);
    }

    /////////////////////////////// SUBSETS
    // FIRST COL TRUE
    // 0 ELS-> 0 SUM TRUE
    // 0 SUM -> ALL ELS TRUE
    // [n+1][target+1]
    
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


    // FIRST COL IS ALL TRUE, 
    // 0 ELS-> 0 SUM TRUE
    // 0 SUM -> ALL ELS TRUE
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
        Utility.printMatrixBool(dp);
        return dp[n][target];
    }


    /**
     * We can use Dynamic Programming 
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
        

   
    //IMP
    //2 subset problems for practice

    // https://www.youtube.com/watch?v=7BynUy5ml0I
    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/


    // https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/


   /** 
    * SIMILAR TO PARTTION, HERE WE CHECK FOR A SUM INTEAD OF SUM/2
   */
    boolean subsetSumDP(int[] set, int sum){
        // dp[0][0] =true;
        int n = set.length;
        boolean[][] dp = new boolean[n+1][sum+1];

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
        Utility.printMatrixBool(dp);
        System.out.println("the subset exists : "+dp[n-1][sum]);
        return dp[n-1][sum];
    }

    
    // https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
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
     * use 2 arrays dp and count
     * if (i >= set[j-1]) {
     *  subset[i,j] = subset[i,j] || subset[i-set[j-1]][j-1];
     * 
     *  if (subset[i,j])
     *   count[i,j] = Math.Max(count[i, j-1], count[i- set[j-1], j-1] + 1);
     *  }
     * 
     * 1 2 ARRAYS DP AND COUNT
     * 2 UPDATE CONDITIONIS TRICKY, UPDATE COUNT ONLY IF dp[i][j] IS TRUE
     * 3 ELSE SIMILAR, TAKE FROM ABOVE IF SMALLER
     * 
     */
    // https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
    public int maxSizeSubsetDP(int[] nums, int target) {
        int n = nums.length;
        if(n==0 || n==1) return 0;
        
        boolean[][] dp = new boolean[n+1][target+1];
        int[][] count = new int[n+1][target+1];
        
        for(int i =0; i<=n; i++) dp[i][0] = true;
        
        for(int i =1; i<=n; i++){
            for(int j = 1; j<=target; j++){
                if(nums[i-1]>j) {
                    dp[i][j] = dp[i-1][j];
                    count[i][j] = count[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                    // update count only if dp[i][j]is true
                    if(dp[i][j]){
                        count[i][j] = Math.max(count[i-1][j], count[i-1][j-nums[i-1]])+1;
                    }
                }
            }
        }
        return count[n][target];
    }

    ////////////////////////////// MINI-MAX

    /** how to think about this?
     * first use recursion. if i select start, then player 2 has to select 
     * either start+1 or end. 
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

    /** 
     * 1 dp[i][i] = piles[i]
     * 2 i+1,j-1; i, j-2; i+2,j
     * 3 Math.max( (piles[i] + min(a,b)), (piles[j] + min(b,c)))
     * 
    */
    // https://leetcode.com/problems/stone-game/
    boolean twoPlayerStoneGameDP(int[] piles){
        int n = piles.length; int i =0; int sum = 0; int half = 0;
        
        int[][] dp = new int[n][n];
        
        for(i=0; i<n; i++) {
            dp[i][i] = piles[i];
            sum+=piles[i];
        }
        
        half = sum/2; //
        
        for(int l=1; l<=n;l++){
            for( i = 0; i+l-1<n;i++){
                int j = i+l-1;
                if(l==1) {
                    dp[i][i] = piles[i];
                    continue;
                }
                // i and j are adjacent
                int a = i+1<=j-1?dp[i+1][j-1]:0;
                int b = i+2<=j?dp[i+2][j]:0;
                int c = i<=j-2?dp[i][j-2]:0;
                
                dp[i][j] = Math.max(piles[i] + Math.min(a,b), 
                               piles[j]+ Math.min(a,c));
            }
        }
        
        Utility.printMatrix(dp);
        if(dp[0][n-1]>=half) return true; //
        return false;
    }

    //////////////////////////////// MATRIX DP

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


    // 7 July
    // https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
    

    // [[-19,57],[-40,-5]]     
    // https://leetcode.com/problems/minimum-falling-path-sum/
    public int minFallingPathSumDP(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dp = new int[m][n];
  
        int min = Integer.MAX_VALUE; 
        for(int i =0; i<n; i++){
            min = Math.min(min, dfs(A, 0, i, dp, Integer.MIN_VALUE));
        }
        return min;
    }
    
    int dfs(int[][] arr, int row, int col, int[][] dp, int prev){
        if(isSafe(arr, row, col, prev)){
            // if(row == arr.length-1) return arr[row][col];
            if(dp[row][col]!=0) return dp[row][col];
            
            int curr = arr[row][col];
            int lowerLeft = 0; int lowerRight = 0; int lower = 0;
            int min = Integer.MAX_VALUE;
            if(col == 0) {
                lower = dfs(arr, row+1, col, dp, curr);
                lowerRight = dfs(arr, row+1, col+1, dp, curr);
                min = Math.min(lower, lowerRight);
            }
            else if(col == arr[0].length-1){
                lower = dfs(arr, row+1, col, dp, curr);
                lowerLeft = dfs(arr, row+1, col-1, dp, curr);
                min = Math.min(lower, lowerLeft);
            }
            else {
                lower = dfs(arr, row+1, col, dp, curr);
                lowerLeft = dfs(arr, row+1, col-1, dp, curr);
                lowerRight = dfs(arr, row+1, col+1, dp, curr);
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


    ////////////////////////// STRING DP
    // if similar, refer diagonally upper el
    /** 
     * size m+1, no filling row col
     * POINTS :
     * 1 dp size [m+1][n+1]
     * 2 assign first row and col -> i to match with null string
     * 3 start from i=1, j=1 and word.charAt(i-1) && word.charAt(j-1)
     * 
    */
    // https://leetcode.com/problems/edit-distance/submissions/
    public int minEditDistance(String word1, String word2) {
        int m = word2.length();
        int n = word1.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i =0; i<=m; i++) dp[i][0] = i;
        for(int i =0; i<=n; i++) dp[0][i] = i;
        
        for(int i =1; i<=m; i++){
            for(int j =1; j<=n; j++){
                if(word1.charAt(j-1) == word2.charAt(i-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))+1;
            }
        }
        return dp[m][n];
    }
    
    /** 
     * 
     * POINTS :
     * 1 RUN LOOPS FOR BOTH STRINGS
     * 2 FIRST ROW COL -> 0
    */
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
                // diagonal
                if(textArr[i-1] == patternArr[j-1] || patternArr[j-1] == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                // '*' any of left or down
                else if(patternArr[j-1] == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else dp[i][j] = false;
            }
        }
        Utility.printMatrixBool(dp);
        return dp[n1][n2];
    }

    
    // https://www.youtube.com/watch?v=BysNXJHzCEs&t=4s
    int longestCommonSubstring(String str1, String str2){
        int m = str1.length(); int n = str2.length();
        int[][] dp = new int[m][n];

        int maxLen = 0;

        for(int i =0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(i==0 || j==0) dp[i][j] = 0;
                else if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i][j] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        return maxLen;
    }

     
    /** 
     * DIFF : SUBSTRING VS SUBSEQUENCE,
     * 
     * match : substr dp[i][j]+1, maxLen; subseq dp[i][j]+1
     * else : dp[][] = 0, Math.max()
     * 
     *           substr       subseq
     * longest.  i,j+1, 0    i,j+1, max
     * palin.    1,0         i,j+2, max 
     *           match,n     match,no
     * 
     */

     

    /** 
     * 
     * if curr chars match check inner string
     * if (X[m - 1] == Y[n - 1]) {
     *   return 1 + lcs(X, Y, m - 1, n - 1);
     * } else {
     *   return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
     * } 
     *
     * first row anc ol are extra, startr from i=0; j=0 and 
     * compare i-1 and j-1
    */
    int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length(); int n = str2.length();
        
        int[][] dp = new int [m+1][n+1];
        
        for(int i =0; i<=m; i++){
            for(int j =0; j<=n; j++){
                if(i ==0 || j==0 ) continue;
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                } 
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
    

    // https://www.youtube.com/watch?v=hbTaCmQGqLg
    /** 
     * longest repeating subsequence
     * SAME AS LCSUBSEQUENCE, USE TWO(SAME) STRINGS AND FIND LCS,
     * BUT THE CHARS AT THE SAMEINDEX SHOUDLNT BE SAME
     * 
     * 
    */
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
        
        Utility.printMatrix(dp);
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

    /////////////////////////////////// PALINDROME

    // SAME STRING, SO UPPPER TRIANGULAR

     /**
     * technique 1 using upper triangular matrix
     * keep track of maxLen and start
     * THE INNER ELS NEED TO MATCH FOR A SUBSTRING
     * 
     * 
     * POINTS :
     * 1 l RUNS FROM 1 TILL N
     * 2 CHECK FOR l=2 AS dp[i + 1][j - 1] CAN'T HANDLE LENGTH 2
     * 
     * 3 FOR ALL OTHER LENGTHS, IF CHARS AT i AND j MATCH
     * THEN CHECK IF CHARS AT i+1 and j-1 MATCH.
     * 
     * 4 dp[i][j] = 1 MARKING 1 DENOTES A PALINDROMIC SUBSTRING
     * 5 MAXLEN IS USED TO KEEP TRACK OF LENGTH
     * 
     * //
     * 4 -> dp[i+1][j-1] == 1, len 1,2,..., maxlen, start
     * 
     */
    // babcd
    // https://leetcode.com/problems/longest-palindromic-substring/
    String longestPalindromicSubstring(String s) {
        int n = s.length();
        int start = 0;
        int maxlen = 1;

        if (n == 0) return "";
        if (n == 1) return s;
        int[][] dp = new int[n][n];

        for (int l=1; l<=n; l++) {
            for (int i=0; i+l-1 < n; i++) {
                int j = i+l-1;
                if (l == 1) dp[i][j] = 1;
                // length 2 
                else if(l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1;
                    maxlen = 2;
                    start = i;
                }
                // all other lengths
                // substring is contiguous, so inner els need to match
                // i+1 == j-1
                else if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                    maxlen = Math.max(maxlen, l);
                    // updating start as this is the longest palindrome so far
                    start = i;
                }
            }
        }
        Utility.printMatrix(dp);
        System.out.println("longest palindromic substring : " + s.substring(start, start + maxlen));
        return s.substring(start, start + maxlen);
    }

    /** 
     * count only if dp[i+1][j-1] == 1
     * 
     * POINTS : 
     * 1 SAME AS PALINDROMIC SUBSTRING BELOW
     * 2 CHECK FOR l=1, l=2 and OTHER LENGTHS
     * 3 
     * 
    */
    // https://leetcode.com/problems/palindromic-substrings/
    int countPalindromicSubstrings(String s) {
        int n = s.length();
        int count = s.length();

        int[][] dp = new int[n][n];

        for (int l = 1; l <= n; l++) {
            for (int i = 0; l + i - 1 < n; i++) {
                int j = l + i - 1;
                if (l == 1) dp[i][j] = 1;
                if (l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1;
                    count++;
                } 
                // also check if inner indexes form a substring
                else if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                    count++;
                }
            }
        }
        Utility.printMatrix(dp);
        System.out.println("all palindromic substrings' count : " + count);
        return count;
    }

     /** 
     * DIFF : PALINDROMIC SUBSTRING VS SUBSEQUENCE,
     * 
     * match : substr maxLen, subseq dp[i][j]+2
     * else : dp[][] = 0, Math.max()
     * 
     * in palin substr, 
     * 1 inner els must match too dp[i+1][j-1] == 1
     * 2 dp[i][j] =1, no dp[][]+1
     * 
     * 
     *           substr       subseq
     * longest.  i,j+1, 0    i,j+1, max
     * palin.    1,0         i,j+2, max 
     *           match,n     match,no
     */

    /** diagonally up, comparing around middle
     * aba (0,2)-> 'a' matches 'a' then dp[0][2] = dp[1][1] + 2;
     * 
     * 1 here if match dp[i+1][j-1]+2
     * 2 else max(dp[i+1][j], dp[i][j-1])
     * 3 FOR FILLING 1 DON'T RUN LOOP, USE l==1 CHECK AND CONTINUE  
     *
     * dp[i][j] = dp[i+1][j-1] +2, 
    */
    // "bbbab", 4
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        
        int[][] dp = new int[n][n];
        for(int i =0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i == j) dp[i][j] =1;
            }
        }
        
        for(int l =2; l<=n; l++){ // 
            for(int i= 0; i+l-1<n; i++){
                if(l==1) {
                    dp[i][i] = 1; continue;
                }
                int j = i+l-1;
                if(s.charAt(i) == s.charAt(j)) {
                    // we don't check if inners are equal
                    dp[i][j] = dp[i+1][j-1] +2; // 
                }
                else dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
            }
        }
        Utility.printMatrix(dp);
        System.out.println("longest palin subseq is "+dp[0][n-1]);
        return dp[0][n-1];
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
            if(Utility.isPalindrome(palin)) {
                // System.out.println(palin);
                curr.add(palin);
                helper(res, curr, str.substring(i+1));// pass the string
                curr.remove(curr.size()-1);
            }
        }
    }


    
    /** 
     * POINTS :
     * 1 USE A DP OF SIZE m+1, n+1
     * IT'S DIFFICULT TO HANDLE CASES OF SINGLE ROW CONTAINING 1
     * 2 if(matrix[i-1][j-1] == '1')
     * 3 RESULT IS MAX OF ALL VALUES, not dp[m][n]
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
        Utility.printMatrix(dp);
        System.out.println(result);
        return result*result;
    }

    // MIN CUTS, PALINDROME, ROD CUT
    // DFS MATRIX GOLDMINE
    // RECURSION INCLUDE EXCLUDE MEMO
    // TRAIN TICKET
    // DEL AND EARN
        
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

        int[] coins = {1,2,3}; int coinSum =4;
        // System.out.println("coin change ways "+ dp.coinChange(coins, 4, 0));
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

        int number = 12;
        dp.numSquares(number);

        int[][] blockSum = {{1,2,3},{4,5,6},{7,8,9}};
        // dp.matrixBlockSum(blockSum, 1);

        int subSetDP[] = {1,5,5,11};//{3, 34, 16, 12, 5, 2}; 
        int subsetSum = 16;
        dp.subsetSum(subSetDP, sum, 0);
        dp.subsetSumDP(subSetDP, subsetSum);
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
        // dp.minFallingPathSumDP(fallingSum);

        int[] apSeq = //{3,6,9,10};
        {44,46,22,68,45,66,43,9,37,30,50,67,32,47,44,11,15,4,11,6,
        20,64,54,54,61,63,23,43,3,12,51,61,16,57,14,12,55,17,18,25,
        19,28,45,56,29,39,52,8,1,21,17,21,23,70,51,61,21,52,25,28};
        
        // dp.longestAP(apSeq);

        String palinCut = "abcbm";
        // dp.minCutDP(palinCut);

        String str1 = "abac", str2 = "cab";
        // dp.shortestCommonSupersequence(str1, str2);


        int matrixMul[] = {1,2,3,4};
        //{4,2,3,5,3};
        // dp.matrixMultiplication(matrixMul);

        
        // int[] boards = { 10, 20, 60, 50, 30, 40 }; int painters = 4; 
        // dp.paintersPartition(boards, boards.length, painters);
        int[] boards = {10, 20, 30, 40}; int painters = 2;
        // System.out.println("min time div "+dp.paintersPartitionDP(boards, painters));


        String s= "leetcode"; List<String> wordDict = new ArrayList<>();
        wordDict.add("leet"); wordDict.add("code");
        // wordDict.add("i");  wordDict.add("a"); wordDict.add("am"); 
        // wordDict.add("ace");
        dp.wordBreak(s, wordDict);


        String palin = "abacba";
        // dp.longestPalindromeSubseq(palin);
        // dp.longestPalindromicSubstring(palin);

        char[][] square =
        {{'1','0','1','0','0'},
        {'1','0','1','1','1'},
        {'1','1','1','1','1'},
        {'1','0','0','1','0'}};
        // dp.maximalSquare(square);
    }
}