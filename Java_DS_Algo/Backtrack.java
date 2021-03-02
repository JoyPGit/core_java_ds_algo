package Java_DS_Algo;

import java.util.*;

public class Backtrack {

    // 6 june
    // multiple jumps allowed
    // https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
    boolean solveNJumpsRatMaze(int[][] maze) {
        int n = maze.length;
        int[][] visited = new int[n][n];
        
        System.out.println("in n rat multiple jumps");
        if (!solveNRatMazeUtil(maze, visited, 0, 0)) return false;

        Utility.printMatrix(visited);
        System.out.println("sol found");
        return true;
    }

    boolean solveNRatMazeUtil(int[][] maze, int[][] visited, int row, int col) {
        if (isNSafeRatMaze(maze, row, col)) {
            if(row == maze.length-1 && col == maze[0].length-1) return true;

            // same as graph
            visited[row][col] = 1;

            for (int i = 1; i <= maze[row][col]; i++) {
                if (solveNRatMazeUtil(maze, visited, row + i, col)) return true;
                if (solveNRatMazeUtil(maze, visited, row, col + i)) return true;
            }

            visited[row][col] = 0;// backtrack
            return false;
        }
        return false;
    }

    boolean isNSafeRatMaze(int[][] grid, int rowIndex, int colIndex) {
        if(rowIndex >= 0 && rowIndex < grid.length 
        && colIndex >= 0 && colIndex < grid[0].length
        && grid[rowIndex][colIndex] != 0) return true;
        return false;
    }

    
    /**  
     * sorting is necessary to remove [7,1]
     * [1,7],[2,6],[2,1,5],[7,1]]
     * 
     * for combinations and subsets, always use Arrays.sort()
     * for permutations, use visited and start from 0
     * for sum always use exit condition(sum>target)
     * 
     * for no duplicates, use nums[i] == num[i-1] (sort already used)
     * for reusing same el, recur from i
     * 
     * for no duplicate sets, use global hashset or !res.contains()
     * 
     * 
     * subsets-ii, all subsets w/o dups
     * (sort and check if same, continue)
     * 
     * comb-ii, 
     * no dup sets (res.contains)
     * no dup in the same set (sort and run check)
     * 
     * perm-i
     * 
     * 
     * subset -> add at first, remember empty []
     * 
     * ***********************************************
     * DFS WITH RETURN TEMPLATE
     * 
     * boolean dfs(char[][] board, int row, int col, String word, int index){
     *   // proceed till index == word.length() 
     *   if(index == word.length()) return true;
     * 
     *   if(isSafeBoard(board, row, col, word.charAt(index))){
     *      char temp = word.charAt(index);
     *      board[row][col] = ' '; // 2
     *      boolean found = dfs(board, row+1, col, word, index+1) // 3
     *      ||dfs(board, row-1, col, word, index+1)
     *      ||dfs(board, row, col+1, word, index+1)
     *      ||dfs(board, row, col-1, word, index+1);
     *      if(found) return true; // 4
     *      board[row][col] = temp; // 5
     *   }
     *  return false;
     * }
     * 
     * WHEN THE CONDITION IS MET, USING RETURN DOESN'T REMOVE THE LAST EL
     * FROM THE LIST. SO WHILE FINDING PATH SUM IN TREE, DON'T USE
     * BUT IT CAN BE USED IN SUBSET(PARTITION IN 2)
     * BECAUSE IF WE FIND A SET OF SUM/2, THEN WE CAN RETURN.
     * BUT IN TREE PATH SUM, WE NEED TO REMOVE THE LEFT SUBTREE
     * BEFORE PROCESSING THE RIGHT SUBTREE 
     * 
     * FOR FINDING ALL POSSIBLE PARTITIONS (OR SOMETHING LIKE THAT)
     * WE USE BACKTRACKING, FOR FINDING MIN CUTS OR PARTITIONS
     * WE NEED RECUSRION, NO ADDITION OR REMOVAL
     * 
     * SEE PALINDROME PARTITION 1 AND 2
     * 
     * WHENEVER WE NEED TO GO BACK ON OUR SELECTION ,WE NEED BACKTRACKING
     * WHEN WE USE GLOBAL VARIABLE LIKE AN OBJECT LIKE CHAR ARRAY
     * WHEN NORMAL INCLUSION-EXCLUSION WILL OD, RECUSRION IS ENOUGH, i.e.
     * WE DON'T NEED TO GO BACK TO INCLUDE IF WE INITIALLY EXCLDUDED
     * 
     * IMP CHECK WHEN TO ADD AND REMOVE INSIDE FOR AND WHEN OUTSIDE
     * 
     * * N QUEEN -> K EQUAL SUM SUBSETS 
     * 
     * POINTS : 
     * -------------------------------------
     * use BACKTRACKING FORMAT
     * Arrays.sort(NUMS)
     *  btk(){
     *   res.add;
     *   for(){
     *    add(i)                   // ADD
     *    btk(i+1)                 // imp index+1
     *    remove(list.size()-1)    // REMOVE
     *   }
     *  }
     * 
     * ADDITION AND REMOVAL INSIDE FOR LOOP, 
     * IF NO REUSE, START WITH i = start AND USE i NOT start
     * ELSE START WITH i
     * -----------------------------------------
     * WHENEVER UNIQUE,
     * !RES.CONTAINS(LIST) 
     * -----------------------------------------
     * Arrays.sort(NUMS) HELPS GET RID OF DUPLCATES ELSE (1,7) & (7,1) ARE 
     *  COUNTED SEPARATELY, IF ARRAY IS SORTED, 1 WILL ALWAYS BE BEFORE 7
     *  NOT USED IN PERMUTATION
     * -----------------------------------------------------
     * 
     * if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
     * -----------------------------------------------------
     * 1 List<ArrayList<Integer>> res = new ArrayList<>();
     *  List<Integer> list = new ArrayList<>();
     *  btk(res, list, nums, 0);
     *  return res;
     * 
     * 
     * 2 ALWAYS PASS A NEW ARRAYLIST AS AN ARG AFTER INITIALIZATION 
     * 
     * 3 add new ArrayList<>(list) to res
     * 
     * 4 subsets without duplicates, use hashmap
     *   (or) nums[i] == nums[i-1] && i>start
     * 5 for permutations, use list.size() == length
     * 
     * 6 for reusing the same element; just loop with i instead of i+1, 
     *   IN PERMUTATIONS, ALWAYS START WITH i
     * 
     * 7 while checking for target value, add 2 checks for sum<0 and sum == 0
     * 
    */

    // all substrings and all contiguous subsets can be found by using 2 for loops

    void printAllSubstrings(String inputString) {
        StringBuilder subString;
         
        for (int i = 0; i < inputString.length(); i++){

            subString = new StringBuilder().append(inputString.charAt(i));
            System.out.println(subString);
             
            for (int j = i+1; j < inputString.length(); j++){
                subString.append(inputString.charAt(j));
                System.out.println(subString);
            }
        }
    }

    //28 apr

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

    /** 
     * POINTS:
     * 1 SWAP EACH CHAR WITH ALL ITS SUCCEEDING INDEXES
     * 2 START FROM index = 0 
     * 3 FOR SUBSEQUENT ITERATION index+1
     * 4 
    */
    // all permutations OF A STRING
    // https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
    // List<List<String>> 
    void allPermutations(String str){
        char[] ch = str.toCharArray();
        permHelper(ch, 0);
    }

    void permHelper(char[] ch, int index){
        if(index == ch.length) System.out.println(new String(ch));
        // swap with itself, so i starts from index
        for(int i = index; i<ch.length; i++){
            swap(ch, index, i);  
            permHelper(ch, index+1);
            // swapping back to preserve the original order of string chars
            swap(ch, index, i);
        }
    }

    void swap(char[] ch, int a, int b){
        char temp = ch[a];
        ch[a] = ch[b];
        ch[b] = temp;
    }




    
    /** 
     * use BACKTRACKING FORMAT
     * btk(){
     *  add;
     *  for(){
     *   add
     *   btk
     *   remove   
     *  }
     * }
     * 
     * 
     * empty is copied and added
     * list refers to the original list always, 
     * whenever we add, we pass the current state of list to the consructor
     * which copies the state and adds the new cloned ArrayList
     * all operations are done on the original list, 
     * we add different states of it to res
     */

    /**
     * 
     * SUBSETS VS PERMUTATIONS VS COMBINATIONS:
     * 1 res.add(); add only when size == length; size == length
     * 2 start from index, start from 0; start from index
     * 3 for no dups, use nums[i] == nums[i-1]; Arrays .sort;  for perms use visited set
     * 4 for reuse, recur from same index, but add exit condition
     * 
     * perm start from 0 , so visited
     * comb, start from index, so sort and nums[i] == nums[i-1]
     * 
     * IF SUBSETS OF SIZE K ARE NEEDED, ADD CHECK
     * IF(SIZE == K) RES.ADD(LIST)
     * IF(I = ARR.LENGTH-1) RES.ADD(LIST)
     * 
     * 
     * in subsets and combinations it's better to sort as [1,2,5] and [5,2,1] are same.
     * so sorting helps avoid duplication of subsets.
    */
    // https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums, 0);
        Arrays.sort(nums);

        Utility.printListOfLists(res);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> list, int [] nums, int start){
        res.add(new ArrayList<>(list));

        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            backtrack(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }


    // Subsets II  : 
    /** can use a HashMap to keep track
     * or
     * if(i > start && nums[i] == nums[i-1]) continue;
     * 
     * no duplicate subsets, use global hashset or res.contains
     * sort must
     */
    // https://leetcode.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        btk2(res, list, nums, 0);
        return res;
    }
    
    private void btk2(List<List<Integer>> res, List<Integer> list, int [] nums, int start){
        if(!res.contains(list)) res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            // if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            list.add(nums[i]);
            btk2(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    } 

    
    // Given a collection of distinct integers, return all possible permutations
    /** 
     * what's the diff w.r.t combinations and subsets?
     * 1 Arrays.sort(nums) is not used
     * 
     * 2 only add when size == nums.length, 
     * 
     * 3 start from 0th index always, AND CHECK IF VISITED
     * 
     * 4 IMP USE visited
     *  IF NO HASHMAP; LIST.CONTAINS WORKS
     *  use hashmap
     *  FOR SUBSETS nums[i] == nums[i-1] && i>start
     * 
     * 5 IN SUBSETS, NO VISITED SET IS MAINTAINED
     * 
     * no sort, start from 0, add when size == length, use visited setl visited[i] == 0 continue
     * in perm no duplicate indexes are allowed, so for unique perms, use a global hashset
     */
    // https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, list, nums, visited);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> list, int [] nums, int[] visited){
        if(list.size() == nums.length) res.add(new ArrayList<>(list));
        for(int i = 0; i < nums.length; i++){

            // if(list.contains(nums[i])) continue; //no duplicates, if present don't add
            if(visited[i] != 0) continue;
            visited[i] = 1;

            list.add(nums[i]);
            backtrack(res, list, nums, visited);
            list.remove(list.size() - 1);

            visited[i] = 0;
        }
    } 
 
    /////////////// UNIQUE -> USE HASHSET
    /** 
     * instead of res.contains, use a HashSet (like graph all paths)
    */
    // Permutations II (contains duplicates) : easier than below
    // https://leetcode.com/problems/permutations-ii/
    HashSet<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        
        int[] visited = new int[nums.length];
        
        helper(res, curr, nums, visited);
        return new ArrayList<>(set);
        // return res;
    }
    
    void helper(List<List<Integer>> res, List<Integer> curr, int[] nums, int[] visited){
        if(curr.size() == nums.length){
        // !res.contains(curr)) {
            // res.add(new ArrayList<>(curr));
            set.add(new ArrayList<>(curr));
        }
        
        for(int i = 0; i<nums.length; i++){
            
            if(visited[i] == 0){
                visited[i] = 1;
                curr.add(nums[i]);
                
                helper(res, curr, nums, visited);

                visited[i] = 0;
                curr.remove(curr.size()-1);
            }            
        }
    }

    
    // permute with duplicates
    // https://www.youtube.com/watch?v=nYFd7VHKyWQ
    

    /** 
     * combinations of size k are reqd; add a check for list.size
     * compare this ques and permute-i
     * 
     * combinations are similar to subsets, 
     * add when size = k, in subsets add at start(empty is also reqd)
     * 
     */ 
    // https://leetcode.com/problems/combinations/
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i] = i+1;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack5(res, list, nums, k, 0);
        return res;
    }
    
    private void backtrack5(List<List<Integer>> res, List<Integer> list, int [] nums, int k, int start){
        if(list.size() == k) res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            backtrack5(res, list, nums, k, i+1);
            list.remove(list.size() - 1);
        } 
    }

    

    /** 
     * for sum always add an exit condition
     * how to reuse? recur from i 
     * 
    */
    // can reuse the same element, start with i again in btk()
    // select only higher indexes
    // https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        helper(res, curr, candidates, 0, 0, target);
        return res;
    }
    
    
    void helper(List<List<Integer>> res, List<Integer> curr, int[] arr, int index, int sum, int target){
        if(sum>target) return;
        // System.out.println(sum);
        
        if(sum == target){
            // System.out.println(curr);
            res.add(new ArrayList<>(curr));
            // return;
        }
        for(int i =index; i<arr.length; i++){
            sum+=arr[i];
            curr.add(arr[i]);
            helper(res, curr, arr, i, sum, target);
            sum-=arr[i];
            curr.remove(curr.size()-1);
        }
    }

    /**
     * https://www.geeksforgeeks.org/all-unique-combinations-whose-sum-equals-to-k/
     * use BACKTRACKING FORMAT 
     * 
     * 1 dfs(){ 
     *  res.add; 
     *  for(){ 
     *    add(i) //ADD 
     *    dfs(i+1)
     *    remove(list.size()-1) //REMOVE 
     *   }
     * }
     * 
     * 2 ARRAYS.SORT(NUMS) HELPS GET RID OF DUPLCATES ELSE (1,7) & (7,1) ARE COUNTED
     * SEPARATELY, IF ARRAY IS SORTED, 1 WILL ALWAYS BE BEFORE 7 
     * 
     * 3 ADDITION AND REMOVAL INSIDE FOR LOOP, 
     * 4 START WITH i = start AND USE i NOT start 
     * 5 FOR NO DUPLICATES -> !res.contains(list)
     */

    /** POINTS : 
     * 1 FOR REMOVING DUPLICATES, SORT THE ARRAY AND USE CHECK 
     * if(i > start && nums[i] == nums[i-1]) continue;
     * 
     * 2 i = start
     * same as subsets ii
     *
     * there is a diff b/w reuse and duplicate 
     * reuse is when same el is reused, duplicate is when els are repeated (diff index)
     * reuse : recur from i 
     * no dups : sort and nums[i] == nums[i-1] 
     * sum : exit condn
     * unique combination : sort
     * 
    */
    // Combination Sum II (can't reuse same element) : 
    // https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(res, list, nums, target, 0, 0);
        return res;
        
    }
    
    private void backtrack2(List<List<Integer>> res, List<Integer> list, 
    int [] nums, int target, int sum, int start){
        if(target < 0) return;
        
        if(target == 0) res.add(new ArrayList<>(list));

        for(int i = start; i < nums.length; i++){
            
            list.add(nums[i]);
            sum+=nums[i];
            backtrack2(res, list, nums, target, sum, i + 1);
            list.remove(list.size() - 1); 
            sum-=nums[i];
        }
    } 


    /////////////////// PARTITION

    // Palindrome Partitioning : 
    /**
     * in dp palindrome partition we have to find the no of splits
     * to be made, to make the string a collection of palindromes,
     * BUT HERE WE HAVE TO RETURN A LIST OF ALL PALINDROMIC SUBSTRINGS 
     * AFTER SPLITTING
     * 
     * 
     * pass index and start from i = index+1
     * imp : i<= str.length()
     */
    // https://leetcode.com/problems/palindrome-partitioning
    public List<List<String>> palinPartition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        helper(res, curr, s, 0);
        return res;
    }
    
    
    void helper(List<List<String>> res, List<String> curr, String str, int index){
        if(index == str.length()){
            res.add(new ArrayList<>(curr));
        }
        
        // <=str.length() substr property one index ahead
        for(int i = index+1; i<=str.length(); i++){
            if(isPalindrome(str.substring(index, i))){
                curr.add(str.substring(index, i)); 
                helper(res, curr, str, i);
                curr.remove(curr.size()-1);
            }
        }
    }
    
    boolean isPalindrome(String str){
        int i = 0; int j = str.length()-1;
        while(i<=j){
            if(str.charAt(i) != str.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }


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


      
    /*
     * return the min diff {1, 6, 11, 5} ; diff = 1 (12-11)
     * 
     * Using the same technique of backtracking as in combinations 
     * for dividing in 2, set target of sum/2 add a check for target == 0
     */
    List<List<Integer>> partitionIn2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i : nums) sum += i;
        int target = sum / 2;
        partitionIn2Util(nums, target, 0, res, list);
        System.out.println(res);
        return res;
    }


    void partitionIn2Util(int[] nums, int target, int start, List<List<Integer>> res, 
    List<Integer> list) {
        if (target < 0) return;
        if (target == 0) {
            // System.out.println(list);
            res.add(new ArrayList<>(list)); return;
        }
        else {
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                partitionIn2Util(nums, target - nums[i], i + 1, res, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**  
     * N QUEEN SIMILAR -> K SUBSET
     * IN QUEEN WE INCREMENT ROWS, HERE WE DECRMENT THE NO OF SUBSETS
     * 
     * 1 6 params, index, visited[], nums[], target, currrSum, partitions
    */
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

    
    /**
     * POINTS :
     * 1 row IS PASSED AS A PARAMETER
     * 2 col IS ITERATED OVER IN EACH FUNCTION CALL
     * 3 IF VALID PROCEED TO NEXT ROW, ELSE NEXT COL
     * 4 NO NEED TO CHCK FOR LOWER RIGHT DIA AS NO QUEENS HAVE YET BEEN PLACED
     */
    void nQueen4jul(int n) {
        int[][] board = new int[n][n];
        helper4jul(board, 0);
    }

    void helper4jul(int[][] board, int row) {
        if (row == board.length) {
            System.out.println("found");
            Utility.printMatrix(board);
            return;
        } else {
            for (int i = 0; i < board.length; i++) {
                board[row][i] = 1;
                if (isValid(board, row, i)) helper4jul(board, row + 1);
                board[row][i] = 0;
            }
        }
    }

    boolean isValid(int[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            if (i == col) continue;
            if (board[row][i] == 1) return false;
        }
        for (int i = 0; i < board.length; i++) {
            if (i == row) continue;
            if (board[i][col] == 1) return false;
        }
        int i = 0;
        int j = 0;

        // board[i][j] == 1 implies queen placed
        // upper LEFT DIA
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (i == row && j == col) continue;
            if (board[i][j] == 1) return false;
        }

        // lower LEFT DIA
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--) {
            if (i == row && j == col) continue;
            if (board[i][j] == 1) return false;
        }

        // checking UPPER RIGHT DIA
        for (i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (i == row && j == col) continue;
            if (board[i][j] == 1) return false;
        }

        // NO LOWER RIGHT DIA CHECK AS NO QUEENS YET

        return true;
    }


    /**
     * POINTS :
     * 1 run 2 loops, row+1 style doesn't work here
     * 2 return after for loop
     * 3 isSafe first and then recursive call
     * 4 imp row offset and col offset
     * board[3*(row/3) + i/3][3*(col/3) + i%3] == c
     * 
     * f(){
     *  if('.') {
     *     c = '1'-'9'
     *     f();
     *  } else c = '.'
     * }
     */
    // https://leetcode.com/problems/sudoku-solver
    public void solveSudoku(char[][] board) {
        helper(board);
    }
    
    boolean helper(char[][] board){
        for(int i = 0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                
                if(board[i][j] == '.'){
                    for(char c = '1'; c<='9'; c++){
                        if(isSafe(board, i, j, c)) {
                            board[i][j] = c;
                            if(helper(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    // for loop over 
                    return false;
                }
            }
        }
        //for loop over, return true
        return true;
    }
    
    boolean isSafe(char[][] board, int row, int col, char c){
        // row check
        for(int i=0; i<board.length; i++){
            if(i == col) continue;
            if(board[row][i] == c) return false;
        }
        
        // col check
        for(int i=0; i<board[0].length; i++){
            if(i == row) continue;
            if(board[i][col] == c) return false;
        }
        
        // sub grid check
        /** 
         * logic : 
         * row = 7, col = 1
         * row/3 = 2, col/3 = 0
         * 3*(row/3) = 6, 3*(col/3) = 0
         * subgrid is identified
         * 
         * i from 0 till 8
         * i  i/3  i%3
         * 0   0    0
         * 1   0    1
         * 2   0    2
         * 3   1    0
         * 4   1    1
         * 5   1    2
         * .
         * .
         * .
         * 
         * this way the co-ordinates traversed are 
         * (6,0), (6,1), (6,2),
         * (7,0), (7,2) ...
         * 
         * so i/3 helps to keep row constant while i%3 keeps incrementing col by 1
         */
        for(int i =0; i<board.length; i++){
            // System.out.println("row "+row+" col "+col);
            // System.out.println(3*(row/3) + i/3);
            // System.out.println(3*(col/3) + i%3);
            if(board[3*(row/3) + i/3][3*(col/3) + i%3] == c) return false;
        }
        return true;
    }
    

    /////////////////////// WORD LADDER, SEARCH
    /** 
	 * POINTS : 
	 * 1 HERE BFS IS USED, BUT FOR ALL STRINGS IN THE QUEUE AT A TIME, 
	 * THE NEXT STRING IS FOUND AND STORED. SO MIN DIST CAN BE FOUND
	 * WHENEVER THE END WORD COMES AS WE INCREMENTING BY UNIT DIST FOR ALL 
	 * TRANSFORMATIONS.
	 * 
	 * 2 for(char c ='a'; c<='z'; c++) curr[i] == c
	 * create a new string and check if it exist in the set
	 * 
	 * 3 CHANGE BACK THE STRING char holder = curr[i]; curr[i] = holder;
	 * 
	 * 4 ONCE AN ITERATION IS DONE, distance++;
	 * 
	 */	
	// https://leetcode.com/problems/word-ladder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        int distance = 1;
        
        Deque<String> q = new LinkedList<>();
        q.addLast(beginWord);
        while(q.size()!=0){
            int size = q.size();            
            // all words in the same go, helps maintain smallest dist
            for(int k =0; k<size; k++){//1
                char[] curr = (q.removeFirst()).toCharArray();
                
                for(int i =0; i<curr.length; i++){
                    char holder = curr[i]; // 2
                    
                    for(char c ='a'; c<='z'; c++){
                        if(c == holder) continue; // 3
                        curr[i] = c;
                        String after = String.valueOf(curr);
                        if(after.equals(endWord)) return distance+1;
                        if(set.contains(after)) {
                            // System.out.print(after+", ");
                            q.addLast(after); set.remove(after); // 4
                        }
                    }
                    curr[i] = holder; // 5
                }   
            }
            distance++; // 6
        }
        return 0;
	}

    /**
     * POINTS : 
     * 1 start a dfs whenever the ch[i][j] matches the starting char of string
     * 2 USE THE WORD, DON'T USE CHAR ARRAY
     * 3 NO NEED TO MAINTAIN VISITED ARRAY, USE BACKTRACKING
     * 4 RETURN BOOLEAN DFS, MAINTAINING A GLOBAL VARIABLE CHECKS
     * FOR ALL POSSIBLE STARTS AND DOESN'T RETURN TRUE TILL ALL POSSIBILITIES
     * ARE EXHAUSTED, WHICH RESULTS IN TLE
     * 5 
     * 
     * why returning void fails? because if we don't return false, 
     * only return is when index == str.length; no break out on out of bounds..
     * hence TLE
     * 
     */
    // https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(board, i, j, word, 0)) return true;
                }
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int row, int col, String word, int index){
        if(index == word.length()) return true;
        
        if(isSafeBoard(board, row, col, word.charAt(index))){
            char temp = word.charAt(index);
            // to avoid inf loop
            board[row][col] = '.'; // 2

            boolean found = dfs(board, row+1, col, word, index+1) // 3
            || dfs(board, row-1, col, word, index+1)
            || dfs(board, row, col+1, word, index+1)
            || dfs(board, row, col-1, word, index+1);

            if(found) return true; // 4

            board[row][col] = temp; // 5
            return false;
        }
        return false;
    }
    
    boolean isSafeBoard(char[][] board, int row, int col, char ch){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == ch) return true;
        return false;
    }
    
    // dfs, 
    // https://leetcode.com/problems/all-paths-from-source-to-target/
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

		List<List<Integer>> res = new ArrayList<>();
		List<Integer> list = new ArrayList<>();

		list.add(0);
		dfs(res, list, graph, 0);
		return res;
	}

	void dfs(List<List<Integer>> res, List<Integer> list, int[][] graph, int curr) {
		if (curr == graph.length - 1) {
			// temp.add(curr);
			res.add(new ArrayList<>(list));
			return;
		}
		for (int j : graph[curr]) {
			list.add(j);
			dfs(res, list, graph, j);
			list.remove(list.size() - 1);
		}
    }
    

    // https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/
    // 551/week-3-august-15th-august-21st/3428/
    

    /** 
     * Steps :
     * 1 create a hashmap
     * 2 backtracking template (similar to all subsets, all combinations etc)
     * USE RES IN PLACE OF CURR LIST AND DON'T FORGET TO REMOVE
     * 
     * f(index){
     *  if(index == length) res.add() // base condition
            for( c: array){
                temp += c;        //add
                f(index+1);
                temp = temp.substring(temp.length()-1);  //remove
            }
     * }
    */
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});

        List<String> res = new ArrayList<>();
        String temp = "";
        btk(map, 0, digits, res, temp);
        System.out.println("final res "+res);
        return res;
    }

    void btk(HashMap<Character, char[]> map, int index, String digits, List<String> res, String temp){
        if(index==digits.length()) {
            System.out.println("res "+res);
            res.add(temp);
            return;
        }
        char[] curr = map.get(digits.charAt(index));
        for(char c : curr){
            temp+=c;
            btk(map, index+1, digits, res, temp);
            temp = temp.substring(0, temp.length()-1);
        }
    }


    ////////////////////// PATTERN ()

    // https://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/
    void generateBinPattern(String str) {
        char[] ch = str.toCharArray();
        int n = ch.length;

        int index = str.indexOf('?');
        // System.out.println("index of ? "+index);
        generateBinPatternUtil(ch, n, index);
    }

    void generateBinPatternUtil(char[] ch, int k, int index) {
        if (index == k) {
            System.out.println("ch array " + String.valueOf(ch));
        } else {
            if (ch[index] == '?') {
                ch[index] = '0';
                generateBinPatternUtil(ch, k, index + 1);

                ch[index] = '1';
                generateBinPatternUtil(ch, k, index + 1);
                ch[index] = '?';// backtrack
            } else {
                generateBinPatternUtil(ch, k, index + 1);
            }
        }
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

    /** 
     * IF A GLOBAL VAR IS USED, THEN REMOVAL IS NEEDED, 
     * curr = curr.substring(0, curr.length()-1);
     * 
     * IF PASSED AS PARAMETER, REMOVAL ISN'T REQUIRED.
     * 
     * keep on adding till no of open or closed reaches half
     * add to res pnly open = closed =half
    */
    // generate 0s and 1s, free walk
    // https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helperP(res, "", 0, 0, n);
        return res;
    }
    
    void helperP(List<String> res, String curr, int open, int closed, int n){
        if(open ==n && closed == n) {
            res.add(curr);
            return;
        }

        if(open<n) {
            curr+='(';
            helperP(res, curr, open+1, closed, n);
            curr =curr.substring(0, curr.length()-1);
        }
        
        if(open>closed && closed<n) {
            curr+=')';
            helperP(res, curr, open, closed+1, n);
            curr =curr.substring(0, curr.length()-1);
        }
        
    }

    ////
    public List<String> generateParenthesisWithoutBT(int n) {
        List<String> list = new ArrayList<String>();
        int open = 0, close = 0;
        String curr = ""; 
        helper(list, curr, open, close, n);
        return list;
    }
    
    public void helper(List<String> list, String curr, int open, int close, int n){
        
        if(curr.length() == n*2){
            list.add(curr);
            return;
        }
        
        if(open < n) helper(list, curr+"(", open+1, close, n);
        if(close < open) helper(list, curr+")", open, close+1, n);
    }


    /**
     * basically same as backtracking.. a vertex is continually assigned colors from
     * 1 till n, and we check if it's safe, then we recur, else we go back to
     * assigning it 0. 1 isSafe is tricky, run a for loop for all adjacent vertices
     * check if the color is same as the color assigned to the vertex if there
     * exists an edge.
     * 
     * 2 a global var foundMinColor is used to break out of the recursive calls
     * 
     */
    boolean foundMinColor = false;

    void mColoring(int[][] arr) {
        int[] color = new int[arr.length];
        mcolorUtil(arr, 0, color);
    }

    void mcolorUtil(int[][] arr, int vertex, int[] color) {
        if (vertex == arr.length) {
            this.foundMinColor = true;
            int max = 0;
            for (int i = 0; i < color.length; i++) {
                max = Math.max(max, color[i]);
            }
            System.out.println("no of colors required is " + (max - 1));
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (!foundMinColor) {
                color[vertex] = i;

                if (isSafemColor(arr, vertex, color)) {
                    mcolorUtil(arr, vertex + 1, color);
                }

                color[vertex] = 0;
            }
        }
    }

    boolean isSafemColor(int[][] arr, int vertex, int[] color) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[vertex][i] == 1 && color[vertex] == color[i])
                return false;
        }
        return true;
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
    // https://www.youtube.com/watch?v=ofMqFAFVcKY
    // https://leetcode.com/problems/path-sum-iii/submissions/
    int count = 0;
    int target = 0;
    // public int pathSumDFS(TreeNode root, int sum) {
    //     target = sum;
    //     dfsExclude(root);
    //     return count;
    // }
    
    
    // void dfsExclude(TreeNode root){        
    //     if(root == null) return;
    //     dfsInclude(root, 0);// not root.val
    //     dfsExclude(root.left);
    //     dfsExclude(root.right);
    // }
    
    // void dfsInclude(TreeNode root, int currSum){
    //     if(root == null) return;
    //     currSum+=root.val;
    //     if(currSum == target) count++;
    //     dfsInclude(root.left, currSum);
    //     dfsInclude(root.right, currSum);
    // }

    // HashMap<Integer, Integer> map = new HashMap<>();
    
    // public int pathSum(TreeNode root, int sum) {
    //     target = sum;
    //     map.put(0,1);
    //     helper(root, 0);
    //     return count;
    // }
    
    // void helper(TreeNode root, int sum){
    //     if(root == null) return;
    //     sum+=root.val;
    //     count+=map.getOrDefault(sum-target, 0);
    //     map.put(sum, map.getOrDefault(sum, 0)+1);
    //     helper(root.left, sum);
    //     helper(root.right, sum);
    //     // this is for leaf nodes, removing left leaf for right path
    //     map.put(sum, map.getOrDefault(sum, 0)-1);
        
    // }

    // https://www.techiedelight.com/find-total-number-unique-paths-maze-source-destination/
        
    // https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1
    // https://www.geeksforgeeks.org/print-subsequences-string/
    // https://www.techiedelight.com/find-minimum-number-possible-k-swaps/

    // https://www.techiedelight.com/find-all-nodes-at-given-distance-from-leaf-nodes-in-a-binary-tree/

    // https://leetcode.com/problems/knight-probability-in-chessboard/discuss/
    // 113954/Evolve-from-recursive-to-dpbeats-94


    // need to do dfs for every node, stuck when node doesn't have entry. 
    // Use for loop as in dfs of graph
    // https://leetcode.com/problems/reconstruct-itinerary/
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> map = new HashMap<>();
        int count = tickets.size()+1;
        List<String> res = new ArrayList<>();
        // map creation
        for(int i =0; i<tickets.size(); i++){
            
            List<String> curr = 
                map.getOrDefault((tickets.get(i)).get(0), new ArrayList<String>());
            curr.add((tickets.get(i)).get(1));
            map.put((tickets.get(i)).get(0), curr);
        }
        
        // map sortiing lexicographically
        for(HashMap.Entry<String, List<String>> entry : map.entrySet()){
            List<String> curr = entry.getValue();
            Collections.sort(curr);
            map.put(entry.getKey(), curr);
        }
        // HashSet<String> visited
        // can remove the vivited airports from the list
        String start = "JFK"; res.add(start);
        dfs(start, map, res);
        
        return res;
    }
    
    void dfs(String start, HashMap<String, List<String>> map, List<String> res){
        if(!map.containsKey(start)) return;
        List<String> currList = map.get(start);
        while(currList.size()!=0){
            start = currList.remove(0);
            res.add(start);
            dfs(start, map, res);
        }
        map.remove(start);
    }
    


    List<Integer> permutation(int n, int k){
        int[] arr = new int[n];
        for(int i =0; i<n; i++) arr[i] = i+1;

        
    }

    List<Integer> res = new ArrayList<>();
    HashSet<Integer> set1 = new HashSet<>();
    List<Integer> curr = new ArrayList<>();

    int helper(int n, int k){
        if(k == 0) return list.get();
        while(true){
            int a = 0;
            for(int i = 1; i<n; i++){
                a = k;
                k-= factorial(n-1);
                if(k == 0) {
                    for(int j =1; j<n; j++){
                        if(!set.contains(j))  curr.add(j);
                    }
                    Collections.reverse(list);
                    res.add(i);
                }
                if(k<0) break;
                res.add(i-1);
                helper(n-1, k-a);
            }
        }   
    }
    
    // 123 3 123; 132; 1
    // 213; 231; (1-2)-1
    // 312

    // 12345; 84 72 12 
    // 1,2,3,4,5,6,7  

    public static void main(String[] args) {
        Backtrack pcs = new Backtrack();


        int[] nums={1,1,2,3};
        // pcs.partitionIn2(nums);

        // pcs.permuteUnique(nums);

        String palindromePart = "babac";
        // pcs.palindromePartition(palindromePart);

        String S = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        // pcs.numMatchingSubseq(S, words);

        int[] subArrayMin = {3,1,2,4};
        // pcs.sumSubarrayMins(subArrayMin);

        String uniqueSplit = "wwwzfvedwfvhsww";
        // pcs.maxUniqueSplit(uniqueSplit);
        // pcs.subsets(subArrayMin);

        // pcs.generateParenthesis(3);
        String pattern = "1??0?101";
        // pcs.generateBinPattern(pattern);
        // pcs.generatePattern(pattern);

        // pcs.minCut("ababababababababab");

        char[][] sudoku = 
        {{'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}};
        // pcs.solveSudoku(sudoku);

        int[] candidates = new int[]{2,3,5,7};
        int target = 7;
        pcs.combinationSum5(candidates, target);
    }
}