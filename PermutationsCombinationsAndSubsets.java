import java.util.*;

public class PermutationsCombinationsAndSubsets {

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
     * POINTS : 
     * -------------------------------------
     * use BACKTRACKING FORMAT
     * Arrays.sort(NUMS)
     *  dfs(){
     *   res.add;
     *   for(){
     *    add(i)                   //ADD
     *    dfs(i+1)
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
     *  dfs(res, list, nums, 0);
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
     * dfs(){
     *  add;
     *  for(){
     *   add
     *   dfs
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
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, nums, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, int [] nums, int start){
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }


    // Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
    /** can use a HashMap to keep track
     * or
     * if(i > start && nums[i] == nums[i-1]) continue;
     */
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(res, list, nums, 0);
        return res;
    }
    
    private void backtrack2(List<List<Integer>> res, List<Integer> list, int [] nums, int start){
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            list.add(nums[i]);
            backtrack2(res, list, nums, i + 1);
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
        dfs(res, list, nums);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, int [] nums){
        if(list.size() == nums.length) res.add(new ArrayList<>(list));
        for(int i = 0; i < nums.length; i++){
            if(list.contains(nums[i])) continue; //no duplicates, if present don't add
            list.add(nums[i]);
            dfs(res, list, nums);
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

    
    // https://leetcode.com/problems/combinations/
    // if all combinations of size k are reqd; add a check for list.size
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i] = i+1;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsComb(res, list, nums, k, 0);
        return res;
    }
    
    private void dfsComb(List<List<Integer>> res, List<Integer> list, int [] nums, int k, int start){
        if(list.size() == k) res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            dfsComb(res, list, nums, k, i+1);
            list.remove(list.size() - 1);
        } 
    }

    // Combination Sum : https://leetcode.com/problems/combination-sum/
    // can reuse the same element, start with i again in dfs
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // Arrays.sort(nums); not reqd
        dfs(res, list, nums, target, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, 
    int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) res.add(new ArrayList<>(list));
        else{ 
            for(int i = start; i < nums.length; i++){
                list.add(nums[i]);
                dfs(res, list, nums, remain - nums[i], i); 
                // not i + 1 because we can reuse same elements
                list.remove(list.size() - 1);
            }
        }
    }


    // Combination Sum II (can't reuse same element) : 
    // https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfsComb2(res, list, nums, target, 0);
        return res;
        
    }
    
    private void dfsComb2(List<List<Integer>> res, List<Integer> list, 
    int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) res.add(new ArrayList<>(list));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                list.add(nums[i]);
                dfsComb2(res, list, nums, remain - nums[i], i + 1);
                list.remove(list.size() - 1); 
            }
        }
    } 


    // Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
    /**
     * in dp palindrome partition we have to find the no of splits
     * to be made to make the string a collection of palindromes,
     * BUT HERE WE HAVE TO RETURN A LIST OF ALL PALINDROMIC 
     * SUBSTRINGS AFTER SPLITTING
     * 
     * start changes every time
     */
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
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public static void main(String[] args) {
        PermutationsCombinationsAndSubsets pcs = new PermutationsCombinationsAndSubsets();

        int[] nums={1,1,2,3};
        // pcs.permuteUnique(nums);

        String palindromePart = "babac";
        pcs.palindromePartition(palindromePart);

    }
}