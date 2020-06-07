import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

public class SubsetsOfSetJava {

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		subsetsHelper(list, new ArrayList<>(), nums, 0);
		return list; 
	}

	private void subsetsHelper(List<List<Integer>> list, List<Integer> resultList, int[] nums, int start) {
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
			// System.out.println("resultlist contents after addition " + resultList + " index "+ start);
		
			/**this is for subsets equalling a sum */

			// int sum = 0;
			// for(int j =0; j< resultList.size(); j++){
			// 	sum += resultList.get(j);
			// 	if(sum ==4 || sum == 5) System.out.println("the set is "+resultList);
			// }

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

	void printList(List<Integer> list) {
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	//28 apr

	public ArrayList<ArrayList<Integer>> hasPathSum(Node root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

        hasPathSumHelper(root, sum, new ArrayList<Integer>(), paths);

		// printListOfLists(paths);	
		printArrayListofArrayLists(paths);
		
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
	
    void printListOfLists(List<List<Integer>> subsets){
        System.out.println("in print");
        for (List<Integer> subset: subsets) {
            // System.out.println("in print, size " + subset.size());
            System.out.println(subset);
        }
	}
	
	void printArrayListofArrayLists(ArrayList<ArrayList<Integer>> lists){
		for(int i=0;i<lists.size();i++) {
			for(int j=0;j<lists.get(i).size();j++){
				System.out.println(lists.get(i).get(j));
			}
		}
	}



	public static void main(String[] args) {

		SubsetsOfSetJava soa = new SubsetsOfSetJava();
		int[] nums = { 1, 2, 3 };
		List<List<Integer>> subsets = soa.subsets(nums);
		// soa.printListOfLists(subsets);

		// for (List<Integer> subset: subsets) {
		// 	System.out.println(subset);
		// }
		// soa.hasPathSum(root, 9)
	}

}