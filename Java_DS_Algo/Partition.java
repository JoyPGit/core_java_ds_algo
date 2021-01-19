package Java_DS_Algo;
import java.util.*;


public class Partition{
    
    /** 
     * QUES :
     * 1 CAPACITY TO SHIP
     * 2 SPLIT ARRAY LARGEST SUM
     * 3 MAX SUM AFTER PARTITIONING
     * 4 
     * 
     * if no of partitions is <=k, we are getting bigger chunks.
     * then we need to decrease the capacity
    */

    // go till N; if sum>N break; start++
    // https://leetcode.com/problems/consecutive-numbers-sum/
    public int consecutiveNumbersSum(int N) {
        int start = 1; int end = N;//(N+1)/2;
        int count = 0;
        
        while(start<=end){
            int sum = 0;
            
            for(int i = start; i<=N; i++){
                sum+=i;
                // System.out.println("sum "+sum);
                if(sum == N) {
                    count++;
                    break;
                }
                if(sum>N) break;
            }
            start++;
        }
        return count;
    }

    
    /** 
     * POINTS :
     * 1 IF CAPACITY = SUM OF ALL WTS, IT CAN BE TRANSFERRED IN A DAY
     * 2 CAPACITY CAN'T BE LESSER THAN MAX WT
     * 3 START FROM MIN AND GO TILL MAX
     * 4 CHECK IF ANY WT MATCHES THE ISSAFE CONDN
     * 
     * ISSAFE CONDN : 
     * ADD UP THE WEIGHTS TILL IT IS LESSER THAN CURRENT CAPACITY
     * ELSE WE WILL LOAD THIS WT FROM THE NEXT DAY. 
     * ALSO WE WILL TRY TO ADD AS MUCH WT AS POSSIBLE
     * (LESS THAN CURRENT CAPACITY, OF COURSE) THAT'S WHY SUM+=WEIGHT
     * 
     * normal version w/o binary search
     * for(int i = minW, i<=maxW; i++){
     *    if(isSafe(i, weights, D)) return i;
     * }
     * 
     * BINARY SEARCH:
     * START FROM MID, IF MID IS VALID, WE LOOK FOR A LESSER CAPACITY
     * ELSE WE MOVE TOWARDS HIGHER CAPACITY
     * 
    */
    // https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int sum  = 0; int max = 0;
        for(int i : weights) {
            max = Math.max(max, i);
            sum+=i;
        }

        // capacity starts from max
        int lo = max; int hi = sum; int res = 0;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(isSafe(weights, mid, D)) {
                res = mid;
                hi = mid-1;
            }
            else lo = mid+1; 
        }
        return res;
    }
    
    
    boolean isSafe(int[] weights, int capacity, int maxDays){
        int sum = 0; int days = 1;
        for(int i : weights){
            sum+=i;
            if(sum>capacity) {
                days++;
                sum = i;
                
                // System.out.println("sum "+sum +" cap "+capacity+" days " +days);
            }
        }
        if(days>maxDays) return false;
        return true;
    }

    /** 
     * SIMILAR TO ABOVE : WHENEVER QUESTION ASKS FOR CONTIGUOUS PARTITION 
     * THINK OF BINARY SEARCH
     * 
     * idea is if we can find max of all partitions less than mid and 
     * no of partitions <= m, then we check if an even lesser max is possible.
     * so hi = mid-1;
     * 
     * 1 MIN VALUE = MAX AND MAX VALUE = SUM OF ALL ELS
     * 2 BIN SEARCH FROM MIN TILL MAX
     * 3 IF SUM EXCEEDS MID, TAKE A NEW PARTITION
     * 4 RETURN TRUE ONLY IF NO OF PARTITIONS IS <= m
     * 
     * imp : count = 1
     * 
     * largest contiguous sum with m partitions
    */
    // https://leetcode.com/problems/split-array-largest-sum/
    public int splitArray(int[] nums, int m) {
        int sum = 0, max = -1;
        
        for(int i : nums){
            sum+=i; max = Math.max(max, i);
        }
        
        int lo = max, hi = sum, res = 0;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            
            int x = findPartitions(nums, mid, m);

            // if larger no of partitions, smaller pieces, so we need to lessen the size
            if(x>m){
                lo = mid+1;
            }
            else {
                res = mid;
                hi = mid-1;
            }
        }
        return res;
    }
    
    int findPartitions(int[] nums, int mid, int m){
        int sum = 0, count = 1;  // imp count = 1
        for(int i :nums){
            sum+=i;
            if(sum>mid){
                count++;
                sum = i;
            }
        }
        return count;
    }

    /** 
     * the above ques is for finding largest contiguous sum with m partitions
     * this one below is for minimizing the max sum after partitioning into k subarrays
     * both are same
     * 
     * https://www.geeksforgeeks.org/split-the-given-array-into-k-sub-arrays-such-that-maximum-
     * sum-of-all-sub-arrays-is-minimum/
    */


    /// TRICKY
    /** 
     * POINTS :
     * 1 FILL DP ARRAY FOR FIRST K ELS, THEN FOR OTHER ELS
     * 2 FOR FIRST K, WE FIND MAX AND STORE FOR ALL
     * i+1 as we start from i=0, so i+1 is the size
     * 
     * 3 FOR NEXT ELS
     * 
     * max at index i =
     * 
     * dp[i-1] + arr[i]*1 or
     * dp[i-2] + arr[i]*2 or
     * dp[i-3] + arr[i]*3 ...
     * 
     * arr[i-size+1] -> max 
     * dp[i-size] + max*size
     * 
     * imp : whichever is max of the last k els is selected as max
    */
    // https://www.youtube.com/watch?v=RNGA4gHvC7E
    // https://leetcode.com/problems/partition-array-for-maximum-sum/
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length; int[] dp = new int[n];
        int max = 0;
        
        for(int i =0; i<k; i++){
            max = Math.max(max, arr[i]);
            dp[i] = max*(i+1);
        }
        
        for(int i =k; i<n; i++){
            int subMax = arr[i];
            for(int size = 1; size<=k; size++){
                // whichever is max of the last k els is selected as max
                // arr
                subMax = Math.max(subMax, arr[i-size+1]);
                // dp, dp[i] = dp[i-2]+max*2
                dp[i] = Math.max(dp[i], dp[i-size]+ subMax*size);
            }
        }
        for(int i : dp) System.out.print(i+", ");
        return dp[n-1];
    }


    ////// SUBSETS
    
    // https://leetcode.com/problems/partition-equal-subset-sum/
    // size, first row
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        for(int i : nums) sum+=i;
        if(sum%2!=0) return false;
        int m = sum/2;
        boolean[][] dp = new boolean[n+1][m+1];
        
        for(int i =0; i<=n; i++) dp[i][0] = true;
        
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if(j<nums[i-1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
            }
        }
        return dp[n][m];
    }


    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length; int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if (sum%k != 0) return false;

        int individual = sum/k;
        boolean[] visited = new boolean[n];

        return partition(0, visited, nums, k, individual, 0);
    }
    
    boolean partition(int index, boolean[] visited, int[] nums, int k, int individual, int currSum){
        if (k == 0) return true;
        // if one partition reaches target sum, recur for other k-1 partitions
        if (currSum == individual) return partition(0, visited, nums, k - 1, individual, 0);

        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && currSum + nums[i] <= individual) {
                visited[i] = true;
                // start from next index
                if (partition(i + 1, visited, nums, k, individual, currSum + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    // above with dp?

    // ALL PALINDROMIC SUBSTRINGS TILL END?
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        helper(res, curr, s, 0);
        return res;
    }
    
    void helper(List<List<String>> res, List<String> curr, String str, int index){
        if(index == str.length()) {
            // System.out.println("curr "+curr);
            res.add(new ArrayList(curr));
            return;
        }
        for(int i = index+1; i<=str.length(); i++){
            if(isPalindrome(str.substring(index, i))){
                // System.out.println(str.substring(index, i));
                curr.add(str.substring(index, i));
                helper(res, curr, str, i);
                curr.remove(curr.size()-1);
            }
        }
    }
    
    boolean isPalindrome(String str){
        
        int lo = 0; int hi = str.length()-1;
        while(lo<=hi){
            if(str.charAt(lo) != str.charAt(hi)) return false;
            lo++; hi--;
        }
        return true;
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

    
    // https://leetcode.com/problems/partition-array-for-maximum-sum (dp based)

    // https://leetcode.com/problems/partition-labels/

    
    // https://www.geeksforgeeks.org/split-array-into-maximum-subarrays-such-that-
    // every-distinct-element-lies-in-a-single-subarray/?ref=rp


    // https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/


    // https://stackoverflow.com/questions/63329608/
    // how-can-i-divide-an-array-into-k-sub-arrays-such-that-the-sum-of-the-number-of-d#new-answer

    // https://stackoverflow.com/questions/41302115/
    // optimize-divide-an-array-into-continuous-subsequences-of-length-no-greater-than


    public static void main(String[] args) {
        Partition part = new Partition();

        // Scanner, parse, close 
		Scanner input = new Scanner(System.in);
        System.out.println("enter array size");
        int n  = Integer.parseInt(input.nextLine());
        int[] arr = new int[n]; 
        int index = 0;

        for(int i = 0; i<n; i++){
            System.out.println("enter next entry");
            arr[index++] = Integer.parseInt(input.nextLine());
        }
        
        System.out.println("enter k limit");
        int k = Integer.parseInt(input.nextLine());
        input.close();

        part.minSplit(arr, k);
        
        // int[] arr = new int[]{1,2,3,4};
        // part.minSplit(arr, 3);
    }
}
