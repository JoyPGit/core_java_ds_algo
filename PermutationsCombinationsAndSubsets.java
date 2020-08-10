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

    

    //28 apr

    public ArrayList<ArrayList<Integer>> subsets28apr(int[] nums) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		subsetsHelper(list, new ArrayList<>(), nums, 0);
		return list; 
	}

    private void subsetsHelper(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> resultList, 
    int[] nums, int start) {
		// if(resultList.size() == 0) System.out.println(" new ");
		list.add(new ArrayList<>(resultList)); //this constructor copies the empty arraylist line 22
		/** empty is copied for the first time and added
		 * resultlist refers to the line 22 list always
		 * so a new list is copied from the existing resultlist and added, but 
		 * all operations of addition are done in the line 22 arraylist
		*/ 
		// list.add((resultList));
		// System.out.println("list size " + list.size());
		// System.out.println("resultlist size " + resultList.size());
		// System.out.println("resultlist contents " + resultList);
		// printList(resultList);
		for (int i = start; i < nums.length; i++) {
			// add element
			resultList.add(nums[i]);
            // System.out.println("resultlist contents after addition " + 
            // resultList + " index "+ start);
		
			/**this is for subsets equalling a sum 
             * 
                int sum = 0;
                for(int j =0; j< resultList.size(); j++){
                    sum += resultList.get(j);
                    if(sum ==4 || sum == 5) System.out.println("the set is "+resultList);
                }
             * 
            */

			/**this is for k length subsets */

			if(resultList.size() == 2) System.out.println("size 2 subsets "+ resultList);
			// Explore
			subsetsHelper(list, resultList, nums, i + 1);
			// printList
			// printList(resultList);
			// remove
			resultList.remove(resultList.size() - 1);
			// System.out.println("resultlist contents after removal " + resultList);
		}
	}

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

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack1(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private void backtrack1(ArrayList<ArrayList<Integer>> list , ArrayList<Integer> tempList, 
    int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack1(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    // Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
    
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private void backtrack2(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack2(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    } 

    // Permutations : https://leetcode.com/problems/permutations/
    
    public ArrayList<ArrayList<Integer>> permute(int[] nums) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
       // Arrays.sort(nums); // not necessary
       backtrack3(list, new ArrayList<>(), nums);
       return list;
    }
    
    private void backtrack3(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tempList, int [] nums){
       if(tempList.size() == nums.length){
          list.add(new ArrayList<>(tempList));
       } else{
          for(int i = 0; i < nums.length; i++){ 
             if(tempList.contains(nums[i])) continue; // element already exists, skip
             tempList.add(nums[i]);
             backtrack3(list, tempList, nums);
             tempList.remove(tempList.size() - 1);
          }
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
    // Combination Sum : https://leetcode.com/problems/combination-sum/
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack5(list, new ArrayList<>(), nums, target, 0);
        return list;
    }
    
    private void backtrack5(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tempList, 
    int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{ 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack5(list, tempList, nums, remain - nums[i], i); 
                // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    // Combination Sum II (can't reuse same element) : 
    // https://leetcode.com/problems/combination-sum-ii/
    
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack6(list, new ArrayList<>(), nums, target, 0);
        return list;
        
    }
    
    private void backtrack6(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tempList, 
    int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack6(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1); 
            }
        }
    } 
    // Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
    
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

    public static void main(String[] args) {
        PermutationsCombinationsAndSubsets pandc = new PermutationsCombinationsAndSubsets();

    }
}