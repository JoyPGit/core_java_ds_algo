import java.util.*;
import utilCustom.*;

public class Recursion {
    Recursion() {
    }
    /** 
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


    // https://leetcode.com/problems/target-sum/
    int count = 0;
    public int findTargetSumWays(int[] nums, int target) {
        // return f(nums, 1, target+nums[0]) + f(nums, 1, target-nums[0]);
        f(nums, 0, target);
        return count;
    }
    
    void f(int[] nums, int index, int sum){
        if(index==nums.length) {
            if(sum ==0) count++; 
            return;
        }
        
        f(nums, index+1, sum+nums[index]);
        f(nums, index+1, sum-nums[index]);
    }

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


    // https://www.youtube.com/watch?v=wvaYrOp94k0
    // TLE
    // NO BACKTRACKING NEEDED
    // https://leetcode.com/problems/palindrome-partitioning-ii
    int min = Integer.MAX_VALUE;
    public int minCut(String s) {
        if(s.length() == 1) return 0;
        helper(s);
        return min;
    }
    
     // like dfs in matrix
    int helper(String str){
        if(utilCustom.Utility.isPalindrome(str)) return 0;
                
        for(int i = 0; i<str.length(); i++){
            String palin = str.substring(0, i+1);
            if(utilCustom.Utility.isPalindrome(palin)){
                min = Math.min(min, 1 + helper(str.substring(i+1)) );
            }
        }
        return 0;
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

            min = Math.min(min, Math.max(utilCustom.Utility.sumSubarray(boards, 0, k),
            painterHelper(boards, painters-1, k+1)));
        }
        return min;
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

    // write recursive similar to rod cutting
    // int minChange2(int[] arr,int sum){
    //     minCoinsHelper2(arr, sum, 0, 0);
    //     return minCoins;
    // }

    // int minCoinsHelper2(int[] arr, int sum, int index){
    //     for(int i =index; i<arr.length; i++){
    //         min = Math.min()
    //     }
    // }


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
     * https://stackoverflow.com/questions/37424284/unreported-exception-
     * java-lang-exception-must-be-caught-or-declared-to-be-throw
     */
    boolean found = false;

    void startToDestination(int start, int end) throws Exception {
        try {
            if (start > end || start <= 0)
                return;
            if (start == end) {
                System.out.println("done");
                found = true;
                return;
            }
            if (!found) {
                System.out.println("current " + start);
                startToDestination(start * 2, end);
                startToDestination(start + 2, end);
            }
        } catch (Exception e) {
            // handle exception
            System.out.println(e);
            e.printStackTrace();
            throw new Exception(e);
        }

    }

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

    void allSubsets(int[] arr) {
        int[] subset = new int[arr.length];
        allSubsetHelper(arr, subset, 0);
    }

    /**
     * always remember to print when index == arr.length & assign subset[index] = 0;
     */
    void allSubsetHelper(int[] arr, int[] subset, int index) {
        if (index == arr.length) {
            utilCustom.Utility.print1DMatrix(subset);
            return;
        }
        subset[index] = 0;
        allSubsetHelper(arr, subset, index + 1);
        subset[index] = arr[index];
        allSubsetHelper(arr, subset, index + 1);
    }


    

    // https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesisWithoutBT(int n) {
        List<String> list = new ArrayList<String>();
        int open = 0, close = 0;
        String curr = ""; 
        parenthesisHelper(list, curr, open, close, n);
        return list;
    }
    
    public void parenthesisHelper(List<String> list, String curr, int open, int close, int n){
        
        if(curr.length() == n*2){
            list.add(curr);
            return;
        }
        
        if(open < n) parenthesisHelper(list, curr+"(", open+1, close, n);
        if(close < open) parenthesisHelper(list, curr+")", open, close+1, n);
    }

    /** 
     * For primitive arguments (int, long, etc.), the pass by value is 
     * the actual value of the primitive (for example, 3).
     * 
     * For objects, the pass by value is the value of the reference to the object.
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
        patternHelper(s, s.indexOf('?'), s.length());
    }

    void patternHelper(String s, int index, int n){
        if(index == n) {
            System.out.println("final "+s);
            return;
        }
        if(s.charAt(index) == 63){ // ? -> 63
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

    /**
     * POINTS : 
     */
    // https://leetcode.com/problems/target-sum/
    // https://leetcode.com/problems/path-sum-iii/discuss/780231/java-DFS-.-easy-to-understand-!!!


    // https://leetcode.com/problems/jump-game-iii/
    boolean foundZero = false;
    public boolean canReach(int[] arr, int start) {
        HashSet<Integer> set = new HashSet<>();
        int n = arr.length;
        if(n==0) return false;
        
        recur(arr, start, set);
        return foundZero;
    }
    
    void recur(int[] arr, int index, HashSet<Integer> set){
        if(index<0 || index >= arr.length || set.contains(index)) return;
        int val = arr[index];
        set.add(index);
        if(val ==0) {foundZero = true; return;}
        recur(arr, index+val, set);
        recur(arr, index-val, set);
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
    */
    // https://www.youtube.com/watch?v=KAoRNDx-S8M
    // https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings
    HashSet<String> splitSet;
    public int maxUniqueSplit(String s) {
        int n = s.length();
        if(n==0) return 0;
        splitSet = new HashSet<>();
        return splitHelper(s);
    }

    int splitHelper(String str){
        int countSplit = 0;
        
        for(int i = 1; i<=str.length(); i++){
            String sub = str.substring(0, i);
            if(!splitSet.contains(sub)){
                splitSet.add(sub);
                // System.out.println(splitSet);
                countSplit = Math.max(countSplit, 
                                1 + splitHelper (str.substring(i, str.length() ) ));
                splitSet.remove(sub); //if commented, fails for "wwwzfvedwfvhsww"
            }
        }
        return countSplit;
    }

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

    
    public static void main(String[] args) throws Exception {
        Recursion recur = new Recursion();

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
        System.out.println("min time div "+recur.paintersPartition(boards, painters));

        int set[] = { 3, 34, 4, 12, 5, 2 }, sum = 30;
        // {3, 34, 4, 12, 5, 2}, sum = 9;
        // System.out.println(recur.subsetSum(set, sum, 0));

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