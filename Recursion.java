import java.util.*;

public class Recursion {
    Recursion() {
    }

    // https://www.techiedelight.com/recursion-practice-problems-with-solutions/
    void printListOflists(ArrayList<ArrayList<Integer>> list) {
        // System.out.println(list.size());//k
        for (int i = 0; i < list.size(); i++) {
            Object j;
            Iterator it = list.get(i).listIterator();
            while (it.hasNext()) {
                j = it.next();
                System.out.println(j);
            }
            System.out.println();
        }

    }

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                System.out.print(arr[i]);
            else
                System.out.print(arr[i] + ", ");
        }
        System.out.println();
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

    boolean subsetSum(int[] arr, int sum, int index) {
        if (index >= arr.length)
            return false;
        if (sum == 0)
            return true;
        return subsetSum(arr, sum - arr[index], index + 1) || subsetSum(arr, sum, index + 1);
    }


    void divideInKSubsetsArray(int[] arr, int k) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % k != 0)
            return;
        int[] arr1 = new int[arr.length];
        int[] arr2 = new int[arr.length];
        int[] arr3 = new int[arr.length];

        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum / k, 0, 0, 0, 0);
    }

    boolean proceedArray = true;

    void divideInKSubsetsHelperArray(int[] arr, int k, int[] arr1, int[] arr2, int[] arr3, int sum, int index,
            int index1, int index2, int index3) {
        if (sum == 0) {
            System.out.println("found");
            this.proceedArray = false;
            print1DMatrix(arr1);
            print1DMatrix(arr2);
            print1DMatrix(arr3);
            return;
        }

        if (index == arr.length)
            return;
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum, index + 1, index1 + 1, index2 + 1, index3 + 1);
        arr1[index1] = arr[index];
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum - arr[index], index + 1, index1 + 1, index2, index3);
        arr2[index2] = arr[index];
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum - arr[index], index + 1, index1, index2 + 1, index3);
        arr3[index3] = arr[index];
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum - arr[index], index + 1, index1, index2, index3 + 1);
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
            print1DMatrix(subset);
            return;
        }
        subset[index] = 0;
        allSubsetHelper(arr, subset, index + 1);
        subset[index] = arr[index];
        allSubsetHelper(arr, subset, index + 1);
    }


    /**
     * https://www.geeksforgeeks.org/all-unique-combinations-whose-sum-equals-to-k/
     * use BACKTRACKING FORMAT 
     * 
     * 1 dfs(){ res.add; for(){ add(i) //ADD dfs(i+1)
     * remove(list.size()-1) //REMOVE } }
     * 
     * 2 ARRAYS.SORT(NUMS) HELPS GET RID OF DUPLCATES ELSE (1,7) & (7,1) ARE COUNTED
     * SEPARATELY, IF ARRAY IS SORTED, 1 WILL ALWAYS BE BEFORE 7 
     * 
     * 3 ADDITION AND REMOVAL INSIDE FOR LOOP, 
     * 4 START WITH i = start AND USE i NOT start 
     * 5 FOR NO DUPLICATES -> !res.contains(list)
     */

    int uniqueCombinationsSumK(int[] arr, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // Arrays.sort(arr);
        int start = 0;
        int sum = 0;
        dfs(res, list, arr, k, start, sum);
        System.out.println(res);
        return res.size();
    }

    void dfs(List<List<Integer>> res, List<Integer> list, int[] arr, int k, int start, int sum) {
        if (sum > k)
            return;

        if (k == sum && !res.contains(list))
            res.add(new ArrayList<>(list));
        else {
            for (int i = start; i < arr.length; i++) {
                list.add(arr[i]);
                dfs(res, list, arr, k, i + 1, sum + arr[i]);
                list.remove(list.size() - 1);
            }
        }
    }


    // https://www.geeksforgeeks.org/
    // partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
    /*
     * return the min diff {1, 6, 11, 5} ; diff = 1 (12-11)
     * 
     * Using the same technique of backtracking as in combinations 
     * for dividing in 2, set target of sum/2 add a check for target == 0
     */
    void partitionIn2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i : nums) sum += i;
        int target = sum / 2;
        partitionIn2Util(nums, target, 0, res, list);
    }

    
    void partitionIn2Util(int[] nums, int target, int start, List<List<Integer>> res, List<Integer> list) {
        if (target < 0)
            return;
        else if (target == 0)
            System.out.println(list);
        else {
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                partitionIn2Util(nums, target - nums[i], i + 1, res, list);
                list.remove(list.size() - 1);
            }
        }
    }

    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
    // discuss/795846/Java-Easy-to-understand-DFS-or-Faster-than-98
    public boolean canPartitionKSubsets(int[] nums, int k) {

        int t = 0;
        for (int i = 0; i < nums.length; i++) t += nums[i];
        if (t % k != 0) return false;
        int target = t / k;
        boolean[] visited = new boolean[nums.length];

        return partition(0, visited, nums, k, target, 0);
    }

    public boolean partition(int index, boolean[] visited, int[] nums, int k, int target, int sum) {
        if (k == 0) {
            return true;
        }
        if (target == sum) {
            return partition(0, visited, nums, k - 1, target, 0);
        }

        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && sum + nums[i] <= target) {
                visited[i] = true;
                if (partition(i + 1, visited, nums, k, target, sum + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
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

    // https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
    // https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
    // https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/

    // https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
    // https://leetcode.com/problems/different-ways-to-add-parentheses/
    // discuss/66328/A-recursive-Java-solution-(284-ms)

    // https://www.geeksforgeeks.org/recursively-break-number-3-parts-get-maximum-sum/
    // https://leetcode.com/problems/all-possible-full-binary-trees/
    // discuss/216853/Java%3A-Easy-with-Examples

    public static void main(String[] args) throws Exception {
        Recursion recur = new Recursion();

        int[] stones =
                // {0,1,2,3};
                // {0,1,2,7};
                // {0,1,3,5,6,8,12,17};
                // {0,1,2,3,4,8,9,11};
                { 0, 1, 2, 3, 4, 5, 6, 12 };
        // recur.canCross(stones);

        // recur.printWithoutLoop(16);

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
        recur.uniqueCombinationsSumK(uniqueComb, 8);

        int[] partition = { 1, 2, 3, 1 };
        // { 5, 6, 1, 11 };
        // System.out.println(recur.partition(partition));
        System.out.println("------------------");
        recur.partitionIn2(uniqueComb);
    }
}