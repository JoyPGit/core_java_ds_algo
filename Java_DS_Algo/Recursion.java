package Java_DS_Algo;
import java.util.*;
// import utilCustom.Utility;
public class Recursion {
    Recursion() {
    }
    
    /** 
     * linked list reverse, tree serialize and deserialize
     * all patterns, checks, visited, backtracking
     * (target sum, jump 3, all subsets)
     * 
     * include-exclude
     * knapsack
     * parenthesis, pattern -> global var vs arg
     * partition, unique split
     * flatten
     * path sum
     * 
     * BASIC IDEA IS TO EITHER SELECT AN INDEX OR NOT.
     * IF SELECT f(sum-nums[i], index+1)
     * IF REJECT f(sum, index+1)
     * 
     * index IS INCREMENTED,
     * 
     * BOUNDARY CONDITION (index == arr.length)
     * 
     * ROD CUTTING -> CONINCHANGE
     * N QUEEN -> PAINTER 
    */


    // https://leetcode.com/problems/reverse-linked-list/
    ListNode h = null;
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        helper(head);
        head.next = null;
        return h;
    }
    
    void helper(ListNode head){
        if(head == null) return;
        ListNode a = head, b = head.next;
        if(b == null) {
            h = a;
            return;
        }
        helper(b);
        b.next = a;
    }


    void printReverseRecursive(String str){
        int n = str.length()-1;
        printHelper(str, n);
    } 

    void printHelper(String str, int index){
        System.out.print(str.charAt(index)+", ");
        if(index == 0) return;
        printHelper( str, index-1);
    }

    ////////////////// DECISION AT INDEX, AND CHECKS FOR THAT 

    // take decision at index and use checks for that
    // here we need to use all els so both index == nums.length 
    // and currSum = sum must match
    // https://leetcode.com/problems/target-sum/
    // global var to local arg?
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        helper(nums, 0, 0, S);
        return count;
    }
    
    void helper(int[] nums, int currSum, int index, int target){
        if(index == nums.length && currSum == target) count++;
        if(index>=nums.length) return;
        helper(nums, currSum+nums[index], index+1, target);
        helper(nums, currSum-nums[index], index+1, target);
    }

    //////////////////////// GENERATE PATTERN

    // https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        parenthesisHelper(list, "(", 1, 0, n);
        return list;
    }
    
    public void parenthesisHelper(List<String> list, String curr, int open, int close, int n){
        
        if(curr.length() == n*2) list.add(curr);
        if(open < n) parenthesisHelper(list, curr+"(", open+1, close, n);
        if(close < open) parenthesisHelper(list, curr+")", open, close+1, n);
    }

    /** 
     * For primitive arguments (int, long, etc.), the pass by value is 
     * the actual value of the primitive (for example, 3).
     * 
     * For objects, the pass by value is the value of the reference to the obj.
     * 
     * 
     * 1 AS NEW INSTANCE OF STRINGBUILDER IS PASSED
     * s.charAt(index) == 63 RETURNS ASCII VALUE, ? -> 63
     * 
     * 2 EVERYTIME A NEW INSTANCE IS PASSED, SO NO NEED TO 
     * REVERT BACK THE CHAR
     *
    */
    void generatePattern(String s){
        patternHelper(s, 0, s.length());
    }

    void patternHelper(String s, int index, int n){
        if(index == n) {
            System.out.println("final "+s);
            return;
        }
        if(s.charAt(index) == 63){ // ? -> 63
            // creating a new StringBuilder from s
            // for easy removal and insertion
            StringBuilder str1 = new StringBuilder(s);
            str1.deleteCharAt(index);
            str1.insert(index, "0");
            // System.out.println("replace 0 "+str1);
            patternHelper(str1.toString(), index+1, n);

            StringBuilder str2 = new StringBuilder(s);
            str2.deleteCharAt(index);
            str2.insert(index, "1");
            patternHelper(str2.toString(), index+1, n);
        }else {
            // System.out.println(s.charAt(index));
            patternHelper(s, index+1, n);
        }
    }


    /** 
     * 
     * char array is not primitive so reference is passed, but 
     * StringBuilder is primitive 
     * 
     * FOR CHAR ARRAY, BACKTRACKING IS NEEDED
     * AS THE REFERENCE IS ALWAYS TO THE SAME ARRAY OBJECT
    */
    // "10?1?";
    void generate01(String str){
        helper01(str.toCharArray(), 0);
    }

    void helper01(char[] ch, int index){
        if(index == ch.length) {
            System.out.println("binary "+String.valueOf(ch));
            return;
        }
        if(ch[index] == '?'){
            // System.out.println(String.valueOf(ch));
            ch[index] = '0';
            helper01(ch, index+1);
            ch[index] = '1';
            helper01(ch, index+1);
            ch[index] = '?';
        }
        // not using else, causes ? to be printed
        else helper01(ch, index+1);
    }
    
    // head vs tail recursion
    // https://leetcode.com/problems/excel-sheet-column-title/
    String res = "";
    public String convertToTitle(int n) {
        helper(n);
        return res;
    }
    
    void helper(int n){
        if(n<=0) return;
        if(n>0 && n<=26) {
            res+=""+(char)(n-1+'A');
            return; 
        }
        helper((n-1)/26);
        res+=""+(char)(((n-1)%26)+'A');
        
    }

    /** 
     * Points:
     * 1 basic recursion f(index) -> f(index+1)
     * 2 add chars to current string and when end is reached add it to list
     * 3 we use include-exclude technique,
     * for excluding current char, just increment index
     * for including : if a digit comes, we have nothing to do
     * 4 for letter, if lower change to upper c-'a' +'A' and vice versa, 
     * then add to current string and increment index
     * 
    */
	// https://leetcode.com/problems/letter-case-permutation
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        
        helper(S, 0, res, "");
        return res;
    }
    
    void helper(String str, int index, List<String> res, String curr){
        if(curr.length() == str.length()) {
            res.add(new String(curr));
            return;
        }
        if(index>=str.length()) return;
        
        char c = str.charAt(index);
        
        // exclude
        helper(str, index+1, res, curr+c);
        
        // include
        // for digit, nothing to do
        
        if(Character.isLowerCase(c)) {
            curr+=(char)(c-'a'+'A');
        }
        else if(Character.isUpperCase(c)) {
            curr+=(char)(c-'A'+'a');
        }
		
		helper(str, index+1, res, curr);

    }

    /////////////////////////// JUMPS
    // https://java2blog.com/check-if-possible-to-reach-end-given-array-by-jumping/

    // maintain a visited arr so as to avoid overflow
    // https://leetcode.com/problems/jump-game-iii/
    boolean ans3 = false;
    boolean[] visited;
    public boolean canReach(int[] arr, int start) {
        visited = new boolean[arr.length];
        helper(arr, start);    
        return ans3;
    }
    
    void helper(int[] arr, int index){
        if(index <0 || index>=arr.length) return;
        if(arr[index] == 0) {
            ans3 = true; return;
        }
        if(visited[index]) return;
        visited[index] =true;
        helper(arr, index+arr[index]);
        helper(arr, index-arr[index]);
    }

    // https://leetcode.com/problems/jump-game-iv/


    // https://www.hackerearth.com/problem/algorithm/free-walk-0f675f40-0d59a400
    int maxDistance = 0;
    int solveDist(String S){
        // Write your code here
        recur(S, 0, 0);
        System.out.println("max dist is "+maxDistance);
        return maxDistance;
    }

    void recur(String str, int i, int sum){
        maxDistance = Math.max(maxDistance, Math.abs(sum));

        if(i == str.length()) return;

        // System.out.println("count "+count);
        if(str.charAt(i) == 'A') {
            sum+=-1;
            recur(str, i+1, sum);
        }
        else if(str.charAt(i) == 'C') {
            sum+=1;
            recur(str, i+1, sum);
        }
        else{
            recur(str, i+1, sum+1);
            recur(str, i+1, sum-1);
        }
    }

    ///////
    boolean ans = false;

    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();

        for (int i = 1; i < stones.length; i++) {
            h.put(stones[i], i);
        }

        System.out.println(h.entrySet());

        canCrossHelper(1, 1, h, stones);
        System.out.println("can the frog jump :" + this.ans);
        return this.ans;
    }

    void canCrossHelper(int k, int value, HashMap<Integer, Integer> h, int[] arr) {
        // if(index == 5) System.out.println("barrier");
        System.out.println("k " + k);
        System.out.println("value " + value);
        if (value == arr[arr.length - 1]) {
            this.ans = true;
            System.out.println("reached end");
            return;
        }

        if (k != 1) {
            if (h.containsKey(value + k - 1)) {
                System.out.println(k - 1 + " jumps " + (value + k - 1));
                canCrossHelper(k - 1, value + k - 1, h, arr);
            }

            if (h.containsKey(value + k)) {
                System.out.println(k + " jumps " + (value + k));
                canCrossHelper(k, value + k, h, arr);
            }

            if (h.containsKey(value + k + 1)) {
                System.out.println(k + 1 + " jumps " + (value + k + 1));
                canCrossHelper(k + 1, value + k + 1, h, arr);
            }

        } else {
            if (h.containsKey(value + k)) {
                System.out.println(k + " jumps " + (value + k));
                canCrossHelper(k, value + k, h, arr);
            }

            if (h.containsKey(value + k + 1)) {
                System.out.println(k + 1 + " jumps " + (value + k + 1));
                canCrossHelper(k + 1, value + k + 1, h, arr);
            }
        }

    }

    /////////////////////// INCLUDE-EXCLUDE
    // rod, knapsack, coins, combination sum
    /** 
     * THE SAME PIECE CAN BE USED MULTIPLE TIMES, HENCE  
     * index IS NOT DECREMENTED WHEN SELECTED
     * */
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

     /** similar to rod cutting, we have inifinite supply of coins and 
     * we have to find no of ways we can make change
     * in rod cutting too, we could take any piece as many times as we wanted
     */

    /** the no of ways to make up a sum if infinite supply of coins 
     * of each denomination is given
     * 
     * 2 VARS, SUM AND INDEX
     * IN INFINITE SUPPLY, SE SAME INDEX
     */
    int coinChange(int[] arr, int sum, int index){
        System.out.println("sum "+sum);
        if(sum==0) return 1;
        if(sum<0) return 0;
        if(index <=0 && sum>=1) return 0;
        // if(index==0) return 1;
        return coinChange(arr, sum-arr[index-1], index) + coinChange(arr, sum, index-1);
    }


    /** 
     * 1 USING HELPER
     * 2 INCLUSION EXCLUSION
     * 3 THE THIRD CONDITION IS FOR THE CASE WHEN WE HAVE REACHED 0,
     * BUT WE KEEP ON EXCLUDING TILL WE REACH THE END OF ARRAY
     * 
     * 
     */
    int coinchange1(int sum, int[] arr){
        return helper1(sum-arr[0], arr, 0) + helper1(sum, arr, 1);
    }

    int helper1(int sum, int[] arr, int index){
        if(sum == 0) return 1;
        if(sum < 0) return 0;
        // if(sum - arr[index]>0) 
        if(index>=arr.length && sum>=1) return 0;
        return helper1(sum, arr, index) + helper1(sum, arr, index);
    }

    // MIN TYPE -> PALIN CUT, CON CHANGE, MATRIX MUL 
    // MIN NO OF COINS TO MAKE CHANGE
    int minCoins = Integer.MAX_VALUE;
    int minChange(int[] arr,int sum){
        minCoinsHelper(arr, sum, 0, 0);
        return minCoins;
    }

    void minCoinsHelper(int[] arr, int sum, int index, int count){
        if(sum == 0) {
            minCoins = Math.min(count, minCoins);
            return;
        }
        if(sum<0) return;
        if(index>= arr.length && sum>=1) return;
        minCoinsHelper(arr, sum-arr[index], index, count+1);
        minCoinsHelper(arr, sum, index+1, count);
    }



    int knapsack(int[] val, int[] wt, int weightLimit, int index){
        if(weightLimit <0) return Integer.MIN_VALUE;
        if(index<0) return 0; //removing equal to causes problems
        int incl = val[index]+knapsack(val, wt, weightLimit-wt[index], index-1);
        int excl = knapsack(val, wt, weightLimit, index-1);

        return Math.max(incl, excl);
    }

    boolean subsetSum(int[] arr, int sum, int index) {
        if (index >= arr.length)
            return false;
        if (sum == 0)
            return true;
        return subsetSum(arr, sum - arr[index], index + 1) || subsetSum(arr, sum, index + 1);
    }

    /**
     * // INCLUDE -EXCLUDE PRINCIPLE
     * sum, sum+arr[index]
    */
    // https://leetcode.com/problems/combination-sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        
        helper(res, curr, candidates, 0, 0, target);
        return res;
    }
    
    void helper(List<List<Integer>> res, List<Integer> curr, int[] arr, int index, int sum, int target){
        if(sum>target  || index >= arr.length) return;
        if(sum == target){
            if(!res.contains(curr)) res.add(new ArrayList<>(curr));
        }
        
        helper(res, curr, arr, index+1, sum, target);
        curr.add(arr[index]);
        helper(res, curr, arr, index, sum+arr[index], target);
        curr.remove(curr.size()-1);
    }


    // https://www.youtube.com/watch?v=wvaYrOp94k0
    // TLE
    // NO BACKTRACKING NEEDED

    ////////////////////////  PARTITION

    // https://leetcode.com/problems/palindrome-partitioning-ii
    int min = Integer.MAX_VALUE;
    public int minCut(String s) {
        if(s.length() == 1) return 0;
        helper(s);
        return min;
    }
    
     // like dfs in matrix
    int helper(String str){
        if(isPalindrome(str)) return 0;
                
        for(int i = 0; i<str.length(); i++){
            String palin = str.substring(0, i+1);
            if(isPalindrome(palin)){
                min = Math.min(min, 1 + helper(str.substring(i+1)) );
            }
        }
        return 0;
    }

    boolean isPalindrome(String str){
        int lo = 0; int hi = str.length()-1;
        while(lo<=hi){
            if(str.charAt(lo) != str.charAt(hi)) return false;
            else {    
                lo++; hi--;
            }
        }
        return true;
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

        // if(painters == 1) return Utility.sumSubarray(boards, index, boards.length-1);

        int min = Integer.MAX_VALUE;
        for(int k = 0; k<boards.length; k++){

            // min = Math.min(min, Math.max(Utility.sumSubarray(boards, 0, k),
            // painterHelper(boards, painters-1, k+1)));
        }
        return min;
    }

    // int sumSubarray(int[] arr, int)

    /** 
     * POINTS :
     * 1 NO NEED FOR GLOBAL VAR, RETURN THE FUNCTION
     * 2 int f(){
     *      int res;
     *      for(){
     *          res = Math.max(res, 1+f())
     *      }    
     *      return res;
     *  }
     * 
     * start from index+1 and return if index == str.length()
    */
    // https://www.youtube.com/watch?v=KAoRNDx-S8M
    // https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/submissions/
    HashSet<String> splitSet;
    public int maxUniqueSplit(String s) {
        int n = s.length();
        if(n == 0) return 0;
        splitSet = new HashSet<>();
        int res = splitHelper(s, 0);
        return res;
    }

    int splitHelper(String str, int index){
        int countSplit = 0;
        if(index == str.length()) return 0;
        for(int i = index+1; i<=str.length(); i++){
            String sub = str.substring(index, i);
            // same as palindrome, just check if set contains
            if(!splitSet.contains(sub)){
                splitSet.add(sub);
                countSplit = Math.max(countSplit, 1 + splitHelper(str, i) );
                splitSet.remove(sub);
            }
        }
        return countSplit;
    }

    ////////////////////// BACKTRACKING

    public boolean canPartition(int[] nums) {
        int sum = 0; 
        for(int i : nums)sum+=i;
        if(sum%2!=0) return false;
        return helper(nums, 0, sum/2);
    }
    
    boolean helper(int[] arr, int index, int target){
        if(target<0 || index == arr.length) return false;
        if(target == 0) return true;
        return helper(arr, index+1, target-arr[index]) || helper(arr, index+1, target);
    }
    
    // https://leetcode.com/problems/subsets/
    /** 
     * VERY SIMPLE APPROACH USING INCLUSION-EXCLUSION
     * FOR INCLUSION ADD THE EL, FOR EXCLUSION REPLACE WITH 0
    */
    void allSubsets(int[] arr) {
        int[] subset = new int[arr.length];
        allSubsetHelper(arr, subset, 0);
    }

    void allSubsetHelper(int[] arr, int[] subset, int index) {
        if (index == arr.length) {
            // Utility.print1DMatrix(subset);
            return;
        }
        subset[index] = 0;
        allSubsetHelper(arr, subset, index + 1);
        subset[index] = arr[index];
        allSubsetHelper(arr, subset, index + 1);
    }

    /** 
     * using backtracking  
     * HINT : TO PRINT EMPTY SUBSET,
     * PRINT AT THE START, NO if(index == nums.length)
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        helper(res, curr, nums, 0);
        return res;
    }
    
    void helper(List<List<Integer>> res, List<Integer> curr, int[] nums, int index){
        res.add(new ArrayList<>(curr));
        for(int i = index; i<nums.length; i++){ //0, index?
            curr.add(nums[i]); // 
            helper(res, curr, nums, i+1);
            curr.remove(curr.size()-1);
        }
    }

    /** 
     * GENERATE ALL SUBSETS
     * CHECK FOR DUPLICATE
     * KEEP TRACK OF MAX
    */
    // ["a", "abc", "d", "de", "def"]
    // https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
    int max = 0;
    public int maxLength(List<String> arr) {
        dfs(0, arr, "");
        return max;
    }
    
    void dfs(int index, List<String> list, String str){
        max = Math.max(max, str.length());
        if(index == list.size()) {
            return;
        }
        
        for(int i = index; i<list.size(); i++){
            if(hasDuplicate(str+list.get(i))) continue;
            str+=list.get(i);
            // System.out.println("before "+str);
            dfs(i+1,list, str);
            str = str.substring(0, str.length() - list.get(i).length());
            // System.out.println("after "+str);
        }
    }
    
    boolean hasDuplicate(String str){
        int[] ch = new int[26];
        for(char c : str.toCharArray()){
            if(ch[c-'a']!=0) return true;
            ch[c-'a']++;
        }
        return false;
    }

    // dfs using backtracking
    // need to remove to get paths with same ancestors
    int n;
    // https://leetcode.com/problems/all-paths-from-source-to-target/
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // create graph, no dedge wt, so adj list
        n = graph.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<n; i++){
            if(graph[i].length==0) continue;
            List<Integer> curr = map.getOrDefault(i, new ArrayList<>());
            for(int j =0; j<graph[i].length; j++) curr.add(graph[i][j]);
            map.put(i, curr);
        }
        // System.out.println(map);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        curr.add(0);
        
        // source is zero
        dfs(res, curr, map, 0);
        return res;
    }
    
    
    void dfs(List<List<Integer>> res, List<Integer> curr, HashMap<Integer, List<Integer>> map, int start){
        if(start == n-1) res.add(new ArrayList<>(curr));

        if(!map.containsKey(start)) return;
        
        List<Integer> list = map.get(start);
        for(int i : list){
            curr.add(i);
            dfs(res, curr, map, i);
            curr.remove(curr.size()-1);
        }
    }


    ///////////////////

    int maxDiv3 = 0;

    public int maxSumDivThree(int[] nums) {
        maxSumDivThreeHelper(nums, 1, nums[0]);
        maxSumDivThreeHelper(nums, 1, 0);

        return this.maxDiv3;

    }

    void maxSumDivThreeHelper(int[] nums, int index, int sum) {
        if (sum % 3 == 0) {
            System.out.println("in here , sum " + sum);
            this.maxDiv3 = Math.max(this.maxDiv3, sum);
            System.out.println("max " + this.maxDiv3);
        }
        if (index >= nums.length)
            return;

        maxSumDivThreeHelper(nums, index + 1, sum + nums[index]);
        maxSumDivThreeHelper(nums, index + 1, sum);
    }

    // https://www.geeksforgeeks.org/count-possible-groups-size-2-3-sum-multiple-3/
    // void max2or3Group(int[] arr){
    // max2or3GroupHelper(arr, 0, 0);
    // // max2or3GroupHelper(arr, 1);
    // }

    // void max2or3GroupHelper(int[] arr, int sum, int )


    //////////////////////////////
    // longest common subsequence
    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs(char[] X, char[] Y, int m, int n) {
        System.out.println("m " + m + " n " + n);
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1]) {
            System.out.println("X " + X[m - 1] + " Y " + Y[n - 1]);
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            System.out.println("in else");
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }


    //////////////////// TREE <-> LINKED LIST
    // https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
     class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
    /*the most imp thing was how to return 12 once we get to 11.
    so when no child is present, we return the last node.
    p will traverse as usual, so return q which holds p.prev
     * 
     * POINTS :
     * 1 TAKE CARE TO SET NEXT AND PREV BOTH
     * 2 SET CHILD TO NULL
    */
    // https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
    class Solution {
        public Node flatten(Node head) {
            helper(head);
            return head;
        }
        
        Node helper(Node head){
            Node p = head; Node q = null;
            while(p!=null){
                // if child exists, make the new adjustments
                if(p.child!=null){
                    Node r = p.next; // new next
                    p.next = p.child; // -> next
                    p.child.prev = p; // <- prev
                    Node s = helper(p.child);
                    // if(s==null) continue;
                    p.child = null;//imp
                    s.next = r; // -> next
                    if(r!=null) r.prev = s; // <- prev
                }
                q = p;
                p = p.next;
            }
            return q;
        }
    }

    class TreeNode{
        int val; TreeNode left ; TreeNode right;
        TreeNode(int v, TreeNode l, TreeNode r){
            this.val = v;
            this.left = l;
            this.right = r;
        }
    }
    // postorder, fix left, traverse till ned and add right
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        if(root.left !=null) {
            TreeNode y = root.right;
            root.right = root.left;
            TreeNode x = root.left;
            root.left = null;
            while(x.right!=null) x= x.right;
            x.right = y;
        }
    }

    ///////////////////////////


    /** 
     * POINTS :
     * 1 2 DFS ARE USED, ONE EXCLUSIVE ONE INLUSIVE
     * 2 FOR EACH EXCLUSIVE, CALL THE INCLUSIVE
     * SIMILAR TO INCLUDE EXCLUDE IN RECURSION
     * 
     * 3 IT'S JUST LIKE PREORDER, ONLY WE ADD ANOTHER CALL
     * IN PLACE OF PRINTING ROOT
    */
    //problem with currSum + root.val for next call is that it 
    // runs with root null and checks for both right and left children
    // and hence the answer is counted twice
    // also fails for 0
    // https://leetcode.com/problems/path-sum-iii/submissions/
    int countSumTree = 0;
    int target = 0;
    public int pathSum(TreeNode root, int sum) {
        target = sum;
        dfsExclude(root);
        return countSumTree;
    }
    
    
    void dfsExclude(TreeNode root){        
        if(root == null) return;
        dfsInclude(root, 0);// not root.val
        dfsExclude(root.left);
        dfsExclude(root.right);
    }
    
    void dfsInclude(TreeNode root, int currSum){
        if(root == null) return;
        currSum+=root.val;
        if(currSum == target) countSumTree++;
        dfsInclude(root.left, currSum);
        dfsInclude(root.right, currSum);
    }


    ///////////////////////////////

    void printWithoutLoop(int num) {
        printWithoutLoopSubtract(num);
        printWithoutLoopAdd(num);
    }

    void printWithoutLoopSubtract(int num) {
        if (num > 0) {
            System.out.print(num + ", ");
            printWithoutLoopSubtract(num - 5);
        }
        return;
    }

    void printWithoutLoopAdd(int num) {
        if (num != 16) {
            System.out.print(num + ", ");
            printWithoutLoopAdd(num + 5);
        }
        System.out.print(num + ", ");
        return;
    }


    // kth permutation apollo
    // median of unsorted

    // https://leetcode.com/problems/generate-parentheses/discuss/
    // 10442/Java-recursion-solution-with-comments-hope-it-helps!

   
    // https://www.geeksforgeeks.org/sort-a-stack-using-recursion/

    // https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
    // https://leetcode.com/problems/different-ways-to-add-parentheses/
    // discuss/66328/A-recursive-Java-solution-(284-ms)

    // https://www.geeksforgeeks.org/recursively-break-number-3-parts-get-maximum-sum/
    // https://leetcode.com/problems/all-possible-full-binary-trees/
    // discuss/216853/Java%3A-Easy-with-Examples

    // valid parenthesis combination, linked list flatten

    /** 
     * TO FOCUS ON HOW TO RETURN A VALUE INSTEAD OF USING A GLOBAL VAR
     * 1 HEIGHT 
     * function h(root){
     *      if(root == null) return 0;
     *      int left = h(left);
     *      int right = h(right);
     *      return max(left, right)+1;
     * }
     * 
     * 2 ALL ANCESTORS (SIMILAR TO HEIGHT)
     *  if(allAncestorsHelper(root.left, res, target)
     *   ||allAncestorsHelper(root.right, res, target)){
     *     res.add(root.val);
     *     return true;
     *   }
     * 
     * // USING SAME STYLE AS HEIGHT
     *  boolean left = allAncestorsHelper(root.left, res, target);
     *  boolean right = allAncestorsHelper(root.right, res, target);
     *  if(left || right) res.add(root.val);
     *  return (left || right);
     * 
     * 3 DELETE BST
     * 
     * 4 REVERSE LIST
     * 
     * 
     * FOR REMOVAL OF GLOBAL VAR SEE ANCESTORS AS TEMPLATE
     * 
     * boolean allAncestorsHelper(TreeNode root, List<Integer> res, int target){
        if(root == null) return false;
        if(root.val == target) return true;
        // if(allAncestorsHelper(root.left, res, target)
        // ||allAncestorsHelper(root.right, res, target)){
        //     res.add(root.val);
        //     return true;
        // }
        // return false;
        
        boolean left = allAncestorsHelper(root.left, res, target);
        boolean right = allAncestorsHelper(root.right, res, target);
        if(left || right) res.add(root.val);
        return (left || right);
    }
     * public boolean isValidBST(TreeNode root) {
     *    if(root == null) return true;
     *    return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
     *    // return isValid;
    }
    
     * boolean helper(TreeNode root, long max, long min){
     *  // if(!isValid) return;
     *  if(root == null) return true;
     * 
     *  if(root.val<=min || root.val>=max) return false;
     *  
     *  boolean left = helper(root.left, root.val, min);
     *  boolean right = helper(root.right, max, root.val);
     *  
     *  return (left == true && right == true);
     * }
     * 
     */
    
    public static void main(String[] args) throws Exception {
        Recursion recur = new Recursion();

        recur.printReverseRecursive("string");
        String binary = "???";
        recur.generatePattern(binary);
        recur.generate01(binary);

        String dist = "AC??C?C?????CCAC??CC";
        // recur.solveDist(dist);
        // int[] stones ={
                // {0,1,2,3};
                // {0,1,2,7};
                // {0,1,3,5,6,8,12,17};
                // {0,1,2,3,4,8,9,11};
                // { 0, 1, 2, 3, 4, 5, 6, 12 };
        // recur.canCross(stones);

        // recur.printWithoutLoop(16);

        int[] boards = {10, 20, 30, 40}; int painters = 2;
        // System.out.println("min time div "+recur.paintersPartition(boards, painters));

        int[] set = new int[]{478, 757, 314, 471, 729, 100, 459, 618};
        // { 3, 34, 4, 12, 5, 2 }, 
        int sum = 30;

        
        // {3, 34, 4, 12, 5, 2}, sum = 9;
        System.out.println(recur.subsetSum(set, sum, 0));

        int[] kSubsetArr = { 3, 3, 4, 1, 5, 2, 6 };
        int k = 3;
        // recur.divideInKSubsets(kSubsetArr, k);
        // recur.divideInKSubsetsArray(kSubsetArr, k);

        int start = 2;
        int end = 92;
        // recur.startToDestination(start, end);

        int[] nums = { 3, 6, 5, 1, 8 };
        // System.out.println("max sum div by 3 "+recur.maxSumDivThree(nums));

        int[] subsets = { 1, 2 };
        // recur.allSubsets(subsets);

        int[] uniqueComb = { 10, 1, 2, 7, 6, 1, 5 };
        // { 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 5 };
        // recur.uniqueCombinationsSumK(uniqueComb, 8);

        int[] partition = { 1, 2, 3, 1 };
        // { 5, 6, 1, 11 };
        // System.out.println(recur.partition(partition));
        // System.out.println("------------------");
        // recur.partitionIn2(uniqueComb);

    }
}