
public class Memoization {
    /** 
     * recursion with memo :
     * 
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

    // https://leetcode.com/problems/longest-palindromic-substring/


    public static void main(String[] args) {
        
    }
}
    
