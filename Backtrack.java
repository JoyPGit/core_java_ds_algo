import java.util.*;

public class Backtrack {

    class Node {
        int key;
        Node left;
        Node right;
    
        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    void printListOfLists(ArrayList<ArrayList<Integer>> lists) {
        for (ArrayList<Integer> subset : lists) {
            System.out.println(subset);
        }
    }

    void printList(ArrayList<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**  
     * 
     * IMP CHECK WHEN TO ADD AND REMOVE INSIDE FOR AND WHEN OUTSIDE
     * 
     * POINTS : 
     * -------------------------------------
     * use BACKTRACKING FORMAT
     * Arrays.sort(NUMS)
     *  btk(){
     *   res.add;
     *   for(){
     *    add(i)                   //ADD
     *    btk(i+1)
     *    remove(list.size()-1)    //REMOVE
     *   }
     *  }
     * 
     * ADDITION AND REMOVAL INSIDE FOR LOOP, 
     * IF NO REUSE, START WITH i = start AND USE i NOT start
     * ELSE START WITH i
     * -----------------------------------------
     * WHENEVER UNIQUE,
     * ARRAYS.SORT(NUMS) && !RES.CONTAINS(LIST) 
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
     * 
     * 
     * 
     * 
     * 
     * 
     * LIST ACCEPTS NEW ARRAYLIST, BUT NOT NESTED
     *  List<List<Integer>> res = new ArrayList<ArrayList<>>(); is incorrect
     *  List<List<Integer>> res = new ArrayList<>(); also is incorrect
     *  ArrayList<ArrayList<Integer>> res = new ArrayList<>(); is incorrect
     * THIS WORKS THOUGH
     * ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    */

    //28 apr

    public ArrayList<ArrayList<Integer>> hasPathSum(Node root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

        hasPathSumHelper(root, sum, new ArrayList<Integer>(), paths);

		// printListOfLists(paths);	
		printListOfLists(paths);
		
        return paths;
    }

	void hasPathSumHelper(Node node, int sum, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> paths) {
        if (node == null) {
            return;
        }

        if (node.key == sum && node.left == null && node.right == null) {
            paths.add(current);
        }

        current.add(node.key);
        hasPathSumHelper(node.left, sum - node.key, new ArrayList<Integer>(current), paths);
        hasPathSumHelper(node.right, sum - node.key, new ArrayList<Integer>(current), paths);
    }

    
    // Subsets : https://leetcode.com/problems/subsets/
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
     * 
     * IF SUBSETS OF SIZE K ARE NEEDED, ADD CHECK
     * IF(SIZE == K) RES.ADD(LIST)
     * IF(I = ARR.LENGTH-1) RES.ADD(LIST)
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums, 0);
        utilCustom.Utility.printListOfLists(res);
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


    // Subsets II (contains duplicates) : 
    /** can use a HashMap to keep track
     * or
     * if(i > start && nums[i] == nums[i-1]) continue;
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
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            list.add(nums[i]);
            btk2(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    } 

    
    // Permutations : https://leetcode.com/problems/permutations/
    // Given a collection of distinct integers, return all possible permutations
    /** 
     * what's the diff w.r.t combinations?
     * 1 Arrays.sort(nums) is not used
     * 
     * 2 only add when size == nums.length, 
     * 3 start from 0th index always, AND THE CHECK IS NOT ON INDEX
     *   RATHER ON THE PRESENCE OF THE EL, AS WE ITERATE FROM START
     *   EVERY TIME
     * 
     * 4 IMP IF NO HASHMAP; LIST.CONTAINS WORKS
     *   use hashmap
     *   (or) nums[i] == nums[i-1] && i>start
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> list, int [] nums){
        if(list.size() == nums.length) res.add(new ArrayList<>(list));
        for(int i = 0; i < nums.length; i++){
            if(list.contains(nums[i])) continue; //no duplicates, if present don't add
            list.add(nums[i]);
            backtrack(res, list, nums);
            list.remove(list.size() - 1);
        }
    } 

    // Permutations II (contains duplicates) : return all possible unique permutations.
    // https://leetcode.com/problems/permutations-ii/
    /**  
     * 1 if(used[i] || (i>0 && nums[i] == nums[i-1]) && !used[i - 1]) continue;
     * https://ibb.co/Sw0fgk5
     * 
     * IN SHORT, IF PRECEDING VAL IS SAME AND IS UNUSED, WE CAN'T PROCEED
     * AS THE PRECEDING VAL NEEDS TO BE SELECTED FIRST
     * 
     * The difficulty is to handle the duplicates.
     * With input as [1a, 1b, 2a] (duplicate 1),
     * If we don't handle the duplicates, the result will be: [1a, 1b, 2a], [1b, 1a, 2a]..,
     * so we must make sure 1a goes before 1b to avoid duplicates
     * 2 By using nums[i-1]==nums[i] && !used[i-1], 
     * we can make sure that 1b is not chosen before 1a
     * 
     * 3 similar to subsets, extra operation of setting used[i] as true and false in the end
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack4(res, list, nums, used);
        System.out.println(res);
        return res;
    }
    
    private void backtrack4(List<List<Integer>> res, List<Integer> list, 
    int [] nums, boolean [] used){
        // System.out.println(list);
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || (i>0 && nums[i] == nums[i-1]) && !used[i - 1]) continue;
                // set, add
                used[i] = true; 
                list.add(nums[i]);

                backtrack4(res, list, nums, used);

                // unset, remove
                used[i] = false; 
                list.remove(list.size() - 1);
            }
        }
    }

    // permute with duplicates
    // https://www.youtube.com/watch?v=nYFd7VHKyWQ
    
    // https://leetcode.com/problems/combinations/
    //this ques is quiite silly start with combination sum 2
    // if all combinations of size k are reqd; add a check for list.size
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

   
    // Combination Sum : https://leetcode.com/problems/combination-sum/
    // can reuse the same element, start with i again in btk()
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // Arrays.sort(nums); not reqd
        backtrack(res, list, nums, target, 0);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> list, 
    int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) res.add(new ArrayList<>(list));
        else{ 
            for(int i = start; i < nums.length; i++){
                list.add(nums[i]);
                backtrack(res, list, nums, remain - nums[i], i); 
                // not i + 1 because we can reuse same elements
                list.remove(list.size() - 1);
            }
        }
    }


     /** POINTS : 
     * 1 FOR REMOVING DUPLICATES, SORT THE ARRAY AND USE CHECK 
     * if(i > start && nums[i] == nums[i-1]) continue;
     * 
     * 2 i = start
     * 
    */
    // Combination Sum II (can't reuse same element) : 
    // https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(res, list, nums, target, 0);
        return res;
        
    }
    
    private void backtrack2(List<List<Integer>> res, List<Integer> list, 
    int [] nums, int target, int start){
        if(target < 0) return;
        if(target == 0) res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            list.add(nums[i]);
            backtrack2(res, list, nums, target - nums[i], i + 1);
            list.remove(list.size() - 1); 
        }
    } 


    // Palindrome Partitioning : 
    /**
     * in dp palindrome partition we have to find the no of splits
     * to be made, to make the string a collection of palindromes,
     * BUT HERE WE HAVE TO RETURN A LIST OF ALL PALINDROMIC SUBSTRINGS 
     * AFTER SPLITTING
     * 
     * start changes every time
     */
    // https://leetcode.com/problems/palindrome-partitioning
    public List<List<String>> palindromePartition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        backtrack7(res, list, s, 0);
        System.out.println(res);
        return res;
    }
    
    public void backtrack7(List<List<String>> res, List<String> list, String s, int start){
       if(start == s.length())
          res.add(new ArrayList<>(list));
       else{
          for(int i = start; i < s.length(); i++){
             if(isPalindrome(s, start, i)){
                list.add(s.substring(start, i + 1));
                backtrack7(res, list, s, i + 1);
                list.remove(list.size() - 1);
             }
          }
       }
    }
    
    public boolean isPalindrome(String s, int low, int high){
       while(low < high)
          if(s.charAt(low++) != s.charAt(high--)) return false;
       return true;
    }    

    // https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/
    // https://leetcode.com/problems/letter-case-permutation/
    // https://leetcode.com/problems/next-permutation/

    public int numMatchingSubseq(String S, String[] words) {
        HashSet<String> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        int count = 0;
        char[] ch = S.toCharArray();
        btk(set, list, ch, 0);
        // System.out.println(set);
        for(String s: words){
            if(set.contains(s))count++;
        }
        System.out.println("count is "+ count);
        return count;
    }
    
    void btk(HashSet<String> set, List<Character> list, char[] ch, int index){
        set.add(stringConvert(new ArrayList<>(list)));
        
        for(int i = index; i<ch.length; i++){
            list.add(ch[i]);
            btk(set, list, ch, i+1);
            list.remove(list.size()-1);
        }
    }
    
    String stringConvert(List<Character> list){
        String s = "";
        for(int i =0; i<list.size(); i++) s+=list.get(i);
        // System.out.println(s);
        return s;
    }

    /** 
     * POINTS :
     * 1 BRUTE FORCE SOLUTION
     * 2 PASSES 87 TEST CASES
     * 3 
     * O(n) stack solution exists, check if possible
     * */
    // https://leetcode.com/problems/sum-of-subarray-minimums/
    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        if(n==0) return 0;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        int sum = 0;
        
        for(int l =1; l<=n; l++){
            int min = Integer.MAX_VALUE;
            for(int i = 0; i+l-1<n; i++){
                int j = i+l-1;
                System.out.println("i "+i+" j "+j);
                sum+=findMin(A, i, j);
            }
            // sum+=min;
            System.out.println("sum "+sum);

        }
        utilCustom.Utility.printListOfLists(res);
        for(int i =0; i<res.size(); i++){
            Collections.sort(res.get(i));
            if((res.get(i)).size()==0) continue;
            sum+=(res.get(i)).get(0);
        }
        return sum;
    }

    int findMin(int[] arr, int start, int end){
        int min = Integer.MAX_VALUE;
        for(int i = start; i<=end; i++){
            min = Math.min(min, arr[i]);
        }
        return min;
    }
    
    /**
     * POINTS :
     * 1 row IS PASSED AS A PARAMTER
     * 2 col IS ITERATED OVER IN EACH FUNCTION CALL
     * 3 IF VALID PROCEED TO NEXT ROW, ELSE NEXT COL
     * 4 NO NEED TO CHCK FOR LOWER RIGHT DIA AS NO QUEENS HAVE YET BEEN PLACED
     */
    void nQueen4jul(int n) {
        int[][] board = new int[n][n];
        helper(board, 0);
    }

    void helper(int[][] board, int row) {
        if (row == board.length) {
            System.out.println("found");
            utilCustom.Utility.printMatrix(board);
            return;
        } else {
            for (int i = 0; i < board.length; i++) {
                board[row][i] = 1;
                if (isValid(board, row, i)) helper(board, row + 1);
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

    
    ///////////////////////////////////////////////

    /**
     * POINTS :
     * 1 run 2 loops, row+1 style doesn't work here
     * 2 return after for loop
     * 3 isSafe first and then recursive call
     * 4 imp row offset and col offset
     * board[3*(row/3) + i/3][3*(col/3) + i%3] == c
     * 
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
                    //for loop over 
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
     */
    // https://leetcode.com/problems/word-search/submissions/
    public boolean exist(char[][] board, String word) {
        // char[] ch = word.toCharArray();
        // boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i =0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int row, int col, String word, int index){
        if(isSafe(board, row, col, word, index)){
            if(index == word.length()-1 && word.charAt(index) == board[row][col]) {
                // System.out.println("in here"); 
                return true;
            }
            char temp = board[row][col];
            board[row][col] = ' '; // 4 not using visited array
            boolean found =  dfs(board, row-1, col, word, index+1) 
            || dfs(board, row+1, col, word, index+1) 
            || dfs(board, row, col-1, word, index+1)
            || dfs(board, row, col+1, word, index+1);
            
            board[row][col] = temp; // 3 backtracking
            return found;
        }
        return false;
    }
    
    
    boolean isSafe(char[][] board, int row, int col, String word, int index){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == word.charAt(index)) return true;
        return false;
    }

    // https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/
    // 551/week-3-august-15th-august-21st/3428/


    // PAINTERS' PARTITION PROBLEM RECURSIVE SOLN
    /**  
     * 1 6 params, index, visited[], nums[], target, currrSum, partitions
    */
    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length; int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if (sum%k != 0) return false;
        
        int target = sum/k;
        boolean[] visited = new boolean[n];

        return partition(0, visited, nums, k, target, 0);
    }
    
    boolean partition(int index, boolean[] visited, int[] nums, int k, int target, int currSum){
        if (k == 0) return true;
        // if one partition reaches target sum, recur for other k-1 partitions
        if (target == currSum) return partition(0, visited, nums, k - 1, target, 0);

        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && currSum + nums[i] <= target) {
                visited[i] = true;
                // start from next index
                if (partition(i + 1, visited, nums, k, target, currSum + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
    

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
            // System.out.println(index+ " equals k");
            System.out.println("ch array " + String.valueOf(ch));
        } else {
            if (ch[index] == '?') {
                ch[index] = '0';
                // System.out.println("ch array if 0 " + String.valueOf(ch));
                generateBinPatternUtil(ch, k, index + 1);

                ch[index] = '1';
                // System.out.println("ch array if 1 " + String.valueOf(ch));
                generateBinPatternUtil(ch, k, index + 1);
                ch[index] = '?';
            } else {
                // System.out.println("ch array else " + String.valueOf(ch));
                generateBinPatternUtil(ch, k, index + 1);
            }
        }
    }


    // generate 0s and 1s, free walk
    // https://leetcode.com/problems/generate-parentheses/
    List<String> res = new ArrayList<>();
    String curr = "(";
    int open = 1, close = 0;
    public List<String> generateParenthesis(int n) {
        
        helper(n);
        System.out.println("res "+res);
        return res;
        
    }
    void helper(int n){
        if(open==n && close == n) {
            res.add(curr);
            System.out.println(curr);
            return;
        }

        if(open<0 || close>open) return;
        
        System.out.println("curr "+curr);
        if(open<n){
            curr+="(";
            open++;
            helper(n);
            curr = curr.substring(0, curr.length()-1);
            open--;
        }
        curr+=")";
        close++;
        helper(n);
        curr = curr.substring(0, curr.length()-1);
        close--;
    }

    // https://www.youtube.com/watch?v=KAoRNDx-S8M
    // https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings
    HashSet<String> splitSet;
    public int maxUniqueSplit(String s) {
        int n = s.length();
        if(n==0) return 0;
        splitSet = new HashSet<>();
        int res = splitHelper(s);
        return res;
    }

    int splitHelper(String str){
        int countSplit = 0;
        
        for(int i = 1; i<=str.length(); i++){
            String sub = str.substring(0, i);
            if(!splitSet.contains(sub)){
                splitSet.add(sub);
                countSplit = Math.max(countSplit, 
                                1 + splitHelper (str.substring(i, str.length() ) ));
                splitSet.remove(sub);
            }
        }
        return countSplit;
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

    ////////////////////////////////////////////////////////////////////////////////////
    // 6 june
    // multiple jumps allowed
    boolean solveNJumpsRatMaze(int[][] maze) {
        int n = maze.length;
        int[][] sol = new int[n][n];
        
        System.out.println("in n rat multiple jumps");
        if (!solveNRatMazeUtil(maze, sol, 0, 0))
            return false;
        utilCustom.Utility.printMatrix(sol);
        System.out.println("sol found");
        return true;
    }

    boolean solveNRatMazeUtil(int[][] maze, int[][] sol, int row, int col) {
        if (isNSafeRatMaze(maze, row, col)) {
            sol[row][col] = 1;
            System.out.println("row " + row + ", col " + col + 
            " maze index value " + maze[row][col]);

            if (row == maze.length - 1 && col == maze[0].length - 1)
                return true;

            for (int i = 1; i <= maze[row][col]; i++) {
                if (solveNRatMazeUtil(maze, sol, row + i, col))
                    return true;
                if (solveNRatMazeUtil(maze, sol, row, col + i))
                    return true;
            }

            sol[row][col] = 0;// backtrack

            return false;
        }
        return false;
    }

    boolean isNSafeRatMaze(int[][] grid, int rowIndex, int colIndex) {
        System.out.println("rowIndex " + rowIndex + ", colIndex " + colIndex);
        return (rowIndex >= 0 && colIndex >= 0 
        && rowIndex < grid.length && colIndex < grid[0].length
        && grid[rowIndex][colIndex] != 0);
    }

    

    // rat in maze, n queen
    // https://www.techiedelight.com/find-total-number-unique-paths-maze-source-destination/
        
    // https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1
    // https://www.geeksforgeeks.org/print-subsequences-string/
    // https://www.techiedelight.com/find-total-number-unique-paths-maze-source-destination/
    // https://www.techiedelight.com/find-minimum-number-possible-k-swaps/
    // https://www.techiedelight.com/find-minimum-cuts-needed-palindromic-partition-string/

    // https://www.techiedelight.com/find-all-nodes-at-given-distance-from-leaf-nodes-in-a-binary-tree/

    // https://leetcode.com/problems/knight-probability-in-chessboard/discuss/
    // 113954/Evolve-from-recursive-to-dpbeats-94
    public static void main(String[] args) {
        Backtrack pcs = new Backtrack();


        int[] nums={1,1,2,3};
        // pcs.permuteUnique(nums);

        String palindromePart = "babac";
        // pcs.palindromePartition(palindromePart);

        String S = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        // pcs.numMatchingSubseq(S, words);

        int[] subArrayMin = {3,1,2,4};
        // pcs.sumSubarrayMins(subArrayMin);

        // pcs.subsets(subArrayMin);

        pcs.generateParenthesis(3);

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
        pcs.solveSudoku(sudoku);

    }
}