import java.util.*;

public class Memoization {
    /** 
     * recursion with memo :
     * 
     * knapsack
     * min edit distance
     * longest common substring
     * https://leetcode.com/problems/longest-common-subsequence/
     * https://leetcode.com/problems/longest-palindromic-substring/
     * https://leetcode.com/problems/longest-palindromic-subsequence/
     * https://leetcode.com/problems/shortest-common-supersequence
     * 
     * 
     * 
     */

    // https://leetcode.com/problems/min-cost-climbing-stairs
    public int minCostClimbingStairs(int[] cost) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = cost.length;
        return Math.min( helperStair(cost, 0, map), helperStair(cost, 1, map));
    }
                        
    int helperStair(int[] cost, int index, HashMap<Integer, Integer> map){
        if(index == cost.length) {
            map.put(index, 0);
            return 0;
        }
        if(index > cost.length) {
            map.put(index, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }
        
        if(map.containsKey(index)) return map.get(index);
        int val = cost[index] + Math.min(helperStair(cost, index+1, map), helperStair(cost, index+2, map));
        map.put(index, val);
        return val;
    }

    /** 
     * start from 0; include-exclude principle
     * include val[n] + helper(W-wt[n], n+1)
     * exclude helper(W, n+1)
     * 
    */
    // https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1#
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
         // your code here 
         int[][] memo = new int[1001][n+1];
         return helper(W, wt, val, 0, memo);
    } 
    
    static int helper(int W, int[] wt, int [] val, int n, int[][] memo){
        if(n == wt.length || W == 0) return 0;
        if(wt[n]>W) return memo[W][n] = helper(W, wt, val, n+1, memo);
        
        if(memo[W][n] != 0) return memo[W][n];
        
        return memo[W][n] = 
            Math.max(val[n] + helper(W-wt[n], wt, val, n+1, memo), 
                helper(W, wt, val, n+1, memo));
    }

    /**
     * trick is when m reaches end, then return word2.length() - n. why? "ho" and "".
     * so to match "ho" and "", 2 operations are reqd.
     * same pattern, use return dp[m][n]
     * 
    */
    // https://leetcode.com/problems/edit-distance/
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        
        return helperEdit(word1, word2, 0, 0, dp);
    }
    
    int helperEdit(String word1, String word2, int m, int n, int[][] dp){
        if(m == word1.length()) return dp[m][n] = word2.length() - n;
        if(n == word2.length()) return dp[m][n] = word1.length() - m;
        
        if(dp[m][n] != 0) return dp[m][n];
        
        if(word1.charAt(m) == word2.charAt(n)) 
            return dp[m][n] = helper(word1, word2, m+1, n+1, dp);
        else{
            return dp[m][n] = 1 + Math.min(helperEdit(word1, word2, m+1, n+1, dp),
                   Math.min(helperEdit(word1, word2, m+1, n, dp), 
                            (helperEdit(word1, word2, m, n+1, dp))));
        }
    }

    /** 
     * use return dp[m][n] = helper();
     * if match then 1+ helper()
     * else Math.max(helper(m+1, n), helper(m, n+1))
    */
    // https://leetcode.com/problems/longest-common-subsequence/
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length()-1, n = text2.length()-1;
        int[][] dp = new int[m+1][n+1];
        
        return helper(text1, text2, 0, 0, dp);
    }
    
    int helper(String text1, String text2, int m, int n, int[][] dp){
        if(m == text1.length() || n == text2.length()) return 0;

        if(dp[m][n] != 0) return dp[m][n];
        
        if(text1.charAt(m) == text2.charAt(n)) {
            return dp[m][n] = 1 + helper(text1, text2, m+1, n+1, dp);
        }
        else {
            return dp[m][n] =
            Math.max(helper(text1, text2, m, n+1, dp), helper(text1, text2, m+1, n, dp));
        }
    }

    /** 
     * trick is to reverse and find lcs of both strings(original and reverse)
    */
    // https://www.youtube.com/watch?v=wuOOOATz_IA
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    public int longestPalindromeSubseq(String s) {
        StringBuilder s1 = new StringBuilder(s);
        String t = new String(s1.reverse());
        
        int[][] memo = new int[s.length()][t.length()];
        for(int[] i: memo) Arrays.fill(i, -1);
        return lcs(s, t, 0, 0, memo);
    }
    
    
    int lcs(String s, String t, int sptr, int tptr, int[][] memo){
        if(tptr == t.length() || sptr == s.length()) return 0;
        
        if(memo[sptr][tptr]!=-1) return memo[sptr][tptr];
        if(s.charAt(sptr) == t.charAt(tptr)) 
            return memo[sptr][tptr] = 1+lcs(s, t, sptr+1, tptr+1, memo);
        
        return memo[sptr][tptr] = 
            Math.max(lcs(s, t, sptr+1, tptr, memo), lcs(s, t, sptr, tptr+1, memo));
    }

    /** 
     * find lcs and then m+n - 2*lcs
    */
    // https://leetcode.com/problems/delete-operation-for-two-strings/
    public int minDistanceTwoStrings(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        return m+n - 2*helper(word1, word2, 0, 0, dp);
    }
    
    int helperTwoStrings(String word1, String word2, int m, int n, int[][] dp){
        if(m == word1.length() || n == word2.length()) return 0;

        if(dp[m][n] !=0) return dp[m][n];
        
        if(word1.charAt(m) == word2.charAt(n)) {
            return dp[m][n] = 1 + helperTwoStrings(word1, word2, m+1, n+1, dp);
        }
        else{
            return dp[m][n] = 
            Math.max(helperTwoStrings(word1, word2, m+1, n, dp), helperTwoStrings(word1, word2, m, n+1, dp));
        }
    }
    
    /** 
     * TLE
    */
    // https://leetcode.com/problems/uncrossed-lines/
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length+1];
        return lcs(A, B, 0, 0, dp);
    }
    
    int lcs(int[] a, int[] b,int m, int n, int[][] dp){
        if(m == a.length || n == b.length) return 0;
        
        if(dp[m][n]!=0) return dp[m][n];
        
        if(a[m] == b[n]) return dp[m][n] = 1 + lcs(a, b, m+1, n+1, dp);
        else{
            return dp[m][n] = Math.max(lcs(a, b, m+1, n, dp), lcs(a, b, m, n+1, dp));
        }
    }

    /** 
     * ab, b
     * the basic idea is helper(sptr, tptr) will call helper(sptr+1, tptr) if there is no match, 
     * else helper(sptr+1, tptr+1) will be called.
     * if match then both indexes will be incremented.
     * 
     * trick here : sum = helper(sptr+1, tptr)
     * if match sum+= helper(sptr+1, tptr+1)
     * 
     * if(a[i]==b[j]) return fun(a,b,i+1,j+1) + fun(a,b,i+1,j); 
     * take ab and ab. dp[0][0] = helper(0,0);
     * 
    */
    // https://leetcode.com/problems/distinct-subsequences
    int ans = 0;
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for(int[] i : dp) Arrays.fill(i, -1);
        
        return helperDistinct(s, t, 0, 0, dp);    
        // return ans;
        // return dp[s.length()-1][t.length()-1];
        // return dp[0][0];
    }
    
    int helperDistinct(String s, String t, int sptr, int tptr, int[][] dp){
        if(tptr == t.length()) {
            ans++; 
            return 1;
        }
        
        if(sptr == s.length()) return 0;
        
        if(dp[sptr][tptr] != -1) return dp[sptr][tptr];
        
        int sum = helperDistinct(s, t, sptr+1, tptr, dp);
        
        if(s.charAt(sptr) == t.charAt(tptr)){
            sum+=helperDistinct(s, t, sptr+1, tptr+1, dp);
        }
        return dp[sptr][tptr] = sum;
        
    }
    
    /** 
     * used hashmap, as sum can be -ve and storing using -ve index is impossible.
     * return 1 only if sum == target and all els have been used
    */
    // HASHMAP MEMOIZATION
    // https://leetcode.com/problems/target-sum/
    Map<String, Integer> map = new HashMap<>();
    
    public int findTargetSumWays(int[] nums, int S) {

        return helper(nums, S, 0, 0);
        // return ans;
    }
    
    int helper(int[] arr, int target, int sum, int index){
        if(index == arr.length && sum == target) return 1;
        if(index >= arr.length) return 0;
        
        // use delimiter
        String key = ""+sum+"=>"+index;
        if(map.containsKey(key)) return map.get(key);
        
        map.put(key,   
            helper(arr, target, sum+arr[index], index+1)+
            helper(arr, target, sum-arr[index], index+1)
        );
        
        return map.get(key);
    }

    // https://leetcode.com/problems/reaching-points
    HashMap<String, Boolean> map1 = new HashMap<>();
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        
        if(sx>tx || sy>ty) return false;
        if(sx == tx && sy == ty ) return true;     
        String key = sx+"->"+sy;
        if(map.containsKey(key)) return map1.get(key);
        boolean val = reachingPoints(sx+sy, sy, tx, ty) || reachingPoints(sx, sx+sy, tx, ty);
        map1.put(key, val);
        return val;
    }
    
    // subsets
    public static void main(String[] args) {
        
    }
}
    
