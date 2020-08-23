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
     *  dfs(){
     *   res.add;
     *   for(){
     *    add(i)                   //ADD
     *    dfs(i)
     *    remove(list.size()-1)    //REMOVE
     *   }
     *  }
     * -------------------------------------
     * 
     * 1 List<ArrayList<Integer>> res = new ArrayList<>();
     *  List<Integer> list = new ArrayList<>();
     *  dfs(res, list, nums, 0);
     *  return res;
     * 
     
     * 
     * 2 ALWAYS PASS A NEW ARRAYLIST AS AN ARG AFTER INITIALIZATION (list here)
     * 
     * 3 add new ArrayList<>(list) to res
     * 
     * 4 subsets without duplicates, use hashmap
     *   (or) nums[i] == nums[i-1] && i>start
     * 5 for permutations, use list.size() == length
     * 6 for reusing the same element; just loop with i instead of i+1, 
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
    /** what's the diff w.r.t combinations?
     * 1 only add when size == nums.length, 
     * 2 if present, continue
     * IMP IF NO HASHMAP; LIST.CONTAINS WORKS
     * use hashmap
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

    // Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
    
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] nums) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack4(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }
    
    private void backtrack4(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tempList, 
    int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true; 
                tempList.add(nums[i]);
                backtrack4(list, tempList, nums, used);
                used[i] = false; 
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    
    // https://leetcode.com/problems/combinations/
    // for all combinations of size k; add a check for list.size
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
        Arrays.sort(nums);
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
     */
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        backtrack7(list, new ArrayList<>(), s, 0);
        return list;
    }
    
    public void backtrack7(ArrayList<ArrayList<String>> list, ArrayList<String> tempList, String s, int start){
       if(start == s.length())
          list.add(new ArrayList<>(tempList));
       else{
          for(int i = start; i < s.length(); i++){
             if(isPalindrome(s, start, i)){
                tempList.add(s.substring(start, i + 1));
                backtrack7(list, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
             }
          }
       }
    }
    
    public boolean isPalindrome(String s, int low, int high){
       while(low < high)
          if(s.charAt(low++) != s.charAt(high--)) return false;
       return true;
    }    

    // https://leetcode.com/problems/letter-case-permutation/
    // https://leetcode.com/problems/next-permutation/
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public static void main(String[] args) {
        PermutationsCombinationsAndSubsets pcs = new PermutationsCombinationsAndSubsets();

    }
}