package Java_DS_Algo;

// import utilCustom.*;
// import 

import java.util.*;

class Node{
    int val;
    Node left; Node right;
    Node(int v){
        this.val = v;
        this.left = null;
        this.right = null;
    }
    Node(int v, Node l, Node r){
        this.val = v;
        this.left = l;
        this.right = r;
    }
} 
class Array {

    /** 
     * sliding window max, duplicates, 3sum,
     * summary, wiggle, equalLeftAndRightSum (paytm), 
     * single el in sorted array, 
     * product except self, rainwater
     * 
     * 
     * 
     * int, float and other primitive data types ar passed by value
     * object by reference, like array, arraylist, pQueue etc.
     * the object name points to the same entity
     * 
     * Arrays class in Java doesn’t have reverse method. 
     * We can use Collections.reverse() to reverse an array also.
     * 
     * USE ARRAYS.EQUALS
     * 
     * IMP HOW TO CONVERT HASHMAP TO ARRAY  
     * Object[] res = map.values().toArray();
     * 
     * REVERSE SORT AN ARRAY
     * Arrays.sort(res, Collections.reverseOrder());
     * 
     * TO SORT FROM A SPECIFIC INDEX USE ARGS Arrays.sort(arr, i, j);
     * 
     * TO CHECK IF ARRAY CONATINS A VALUE
     * Arrays.asList(yourArray).contains(yourValue)
     * 
     * REMOVE DUPLICATES CHECK 3 SUM
     * 
    
     // https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/

    */
    /**TECHNIQUES
     * 1 REMOVE DUPLICATES  USE WHILE LOOP AND BOUNDARY (j<n)
     * 2 SORTED -> BIDIRECTIONAL SEARCH 
     * 3 UNSORTED -> HASHMAP
     * 4 TWO TRAVERSALS
     * 5 WIGGLE SORT
     * 6 USING STACK/DEQUE
     * 7 USING XOR
     * 8 CIRCULAR ARRAY 
     */

    int binarrySearch(int[] arr, int key){
        int n = arr.length; int res = -1; 
        int lo = 0; int hi = n-1;
        while(lo<=hi){
            int mid = lo+ (hi-lo)/2;
            if(arr[mid] == key) res = mid;
            else if(arr[mid]>key) hi= mid-1;
            else lo = mid+1;
        }
        return res;
    }
   

    // 2nd SECOND TEMPLATE OF BS, 
    /** 
     * 3 STEP BINARY
     * POINTS :
     * 1 low<high (NO <=)
     * 2 NO CHECK FOR MID == TARGET
     * 3 IF MID<HIGH, ARRAY IS SORTED, SO MOVE LEFT
     * 4 IF MID == HIGH, HIGH--
     * 5 RETURN nums[low]
     */
    // [4,5,1,2,3]
    // WORKS FOR DUPLICATES TOO (high--)
    // move towards smaller el, if(mid>hi) lo = mid+1
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        int low = 0; int high = nums.length-1;
        
        while(low<high){ // 1
            int mid = low + (high - low)/2;
            // 2 
            if(nums[mid] < nums[high]) high = mid;
            else if(nums[mid] == nums[high]) high--;
            else low = mid+1;
            
        }
        return nums[low]; // 3
    }


    // some cases which have been avoided
    // j = i+1 so count is 1 for [0], j-i+1 fails
    // nums[i] == 0 continue
    // only update if (j!=i+1) 
    // n == 0 condn check not needed as j starts from i
    // https://leetcode.com/problems/max-consecutive-ones/
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int max = 0;

        int j = 0;
        for(int i =0; i<n; i++){
            if(nums[i] == 0) continue;
            j = i;
            while(j<n && nums[j] == 1) j++;
            max = Math.max(max, j-i);
            // update i, as j is at 0
            i = j;
        }
        Utility.print1DMatrix(nums);
        return max;
    }

    // [1,2,34,3,4,5,7,23,12]
    // [1,2,1,1]
    // https://leetcode.com/problems/three-consecutive-odds
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            if (arr[i] % 2 != 0) {
                if (i + 1 < n && arr[i + 1] % 2 != 0) {
                    if (i + 2 < n && arr[i + 2] % 2 != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // https://leetcode.com/problems/intersection-of-two-arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i : nums1) set.add(i);
        for(int i : nums2){
            if(set.contains(i)) {
                list.add(i);
                set.remove(i);
            }
        }
        
        int[] res = new int[list.size()]; int index = 0;
        for (int i : list) {
            res[index++] = i;
        }
        return res;
    }

    /**
     * 3 TECHNIQUES:
     * 1 SORT AND FIND
     * 2 USE COUNT ARRAY
     * 3 MARK INDEX
     */
    // https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
    void findMissingAndRepeated(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            if (arr[i] == -1) {
                System.out.println("the repeated number is " + (i + 1));
                // break;
                System.out.println(arr[i]);

            } else
                arr[arr[i] - 1] = -1;
        }
    }


    // PIVOT
    // https://leetcode.com/problems/find-pivot-index/
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int left = 0; int sum =0;
        
        for(int i=0; i<n; i++){
            sum+=nums[i];
        }
        
        for(int i =0; i<n; i++){
            if(sum-left-nums[i] == left) return i;
            left += nums[i];
        }
        return -1;
    }


    /** 
     *  Given an array, find all the subarrays (contiguous) of even length which 
     * have equal left and right sums. Ex:- [2,4,6,6,4,2,10], 
     * answer is 4, i.e. [2,4,6,6,4,2],[4,6,6,4,2,10],[4,6,6,4],[6,6]
     * 
     * find sum from i to j.
     * Check if any sum from i till k (k<j) = sum/2;
     * THIS MEANS THE OTHER HALF(k+1 to j)) MUST HAVE SUM = SUM/2.
     * 
    /** similar to dp ques, use l and i+l-1 =j; palindrome type */
    // https://www.geeksforgeeks.org/paytm-interview-experience-set-9/
    int equalLeftAndRightSum(int[] arr){
        int count = 0;
        int n = arr.length;
        for(int l=2; l<n; l++){
            for(int i = 0; l+i-1<n; i++){
                int j = i+l-1;
                // checking for length 2, if both els match
                if(l==2 && arr[i] == arr[j]) count++;
                // even length
                else if(l%2==0){
                    int sum = sum(arr,i,j);
                    for(int k = i;k<j; k++){
                        if(sum(arr,i, k) == sum/2){
                            System.out.println("i "+i+" k "+k);
                            // System.out.println("in here "+sum(arr, i, k));
                            count++;
                        }
                    }
                }                
            }
        }
        System.out.println("count of equal left and right sums' partition "+count);
        return count;
    }

    int sum(int[] arr, int start, int end){
        int sum =0;
        for(int i =start; i<=end; i++){
            sum+=arr[i];
        }
        return sum;
    }

    
    /** POINTS :
     * 1 USE WHILE LOOP, AS FOR DUPLICATES
     * 2 j = i+1, TO FIND CONSECUTIVES, USE THIS TRICK
     * nums[j]-nums[i] == j-i
     * 3 USUAL BOUNDARY CONDNS j<n
     * 4 DON'T FORGET i = j
    */
    // https://leetcode.com/problems/summary-ranges/
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        if(n==0) return new ArrayList<>();
        List<String> res = new ArrayList<>(); String curr = "";
        
        int i =0;
        while(i<n){
            int j = i+1;
            while(j<n && nums[j]-nums[i] == j-i) j++; //only j++ will do
            if(j==i+1) curr = nums[i]+"";  //single el
            else curr = nums[i]+"->"+nums[j-1];
            res.add(curr);
            i = j;
        }
        return res;
    }

    /////////////////////////////// DUPLICATES
    
    /** 
     * mark index nums[abs(nums[i])]
     * 
     * 1 nums[i] can be negative but 
     * nums[nums[i]], if negative determines duplicate
     * 2 return nums[i]
     * 
     * nums[Math.abs(nums[i])]
     * 
    */
    // [1,2,2], [1,3,4,2,2]
    // https://leetcode.com/problems/find-the-duplicate-number/
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for(int i =0; i<n; i++){
            if(nums[Math.abs(nums[i])]<0) return Math.abs(nums[i]);
            else if(nums[Math.abs(nums[i])]>0) nums[Math.abs(nums[i])]*=-1;
        }
        return 0;
    }

    /**
     * MARKING VALUES OF VISITED INDICES AS -VE
     */
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i =0; i<n; i++){
           if(nums[Math.abs(nums[i])-1]>0) nums[Math.abs(nums[i])-1]*=(-1);
           else if(nums[Math.abs(nums[i])-1]<0) list.add(Math.abs(nums[i])); 
        }
        
        return list;
    }

    /**
     * go till n-1, 
     * if ADJACENTS ARE SAME i++;
     * the i++ of for loop will incremwnt once again so i will jump
     * by 2 places, 0->2
     * for last single el return nums[n-1]
     * 
     * [1,1,3,3,4,4,8]
     */
    // https://leetcode.com/problems/single-element-in-a-sorted-array
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        for(int i =0; i<n-1; i++){
            if(nums[i] == nums[i+1]) i++;
            else return nums[i];
        }
        return nums[n-1];
    }

    /**
     *  We want the first element of the middle pair,
     * which should be at an even index if the left part is sorted.
     * https://leetcode.com/problems/single-element-in-a-sorted-array
     * /discuss/100754/Java-Binary-Search-short-(7l)-O(log(n))-w-explanations
     * 
     * IMP : IF NO SINGLE EL, LAST INDEX OF PAIR WILL BE ODD.
     * 
     * 1,1,2 -> mid 1,  mid is odd, arr[mid] == arr[mid+1]
     * 0,1,1,2,2  ->  2,  mid is even, arr[mid] != arr[mid+1]
     * 0,0,1,1,2  ->  2,  mid is odd, arr[mid] == arr[mid+1]
     * 
     * if mid is even and arr[mid] != arr[mid+1], the pair
     * end at even index, so the disturbance must be on the left side
     * 
     * not equal here signifies we have the last index of the pair
     * and it is at even posn, but for no disturbance, last must
     * be at an odd posn. So hi = mid;
     * 
     * If equal, no disturbance, move right by 2 as this is a pair.
     * 
     * return nums[lo]
     */ 
    // https://leetcode.com/problems/single-element-in-a-sorted-array
    public int singleNonDuplicateBinary(int[] nums) {
        int n = nums.length;
        int lo = 0; int hi = n-1;
        
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(mid%2!=0) mid--; // 
            
            // el on left
            if(nums[mid] != nums[mid+1]){ // 
                hi = mid;
            }else lo = mid+2; // 
        }
        return nums[lo]; // 
    }
    
    
    /**
     * It is somewhat similar to QUICKSORT partition, 
     * the way we do partition(i++, swap(a[i], a[j]). 
     * j holds the index of the first occurrence of the previous element. 
     * (1,1,2,2,3) : j is 0,
     * i =1; arr[j] == arr[i]; continue; //j=0, i=0; 1==1
     * 
     * i =2; arr[j] != arr[i]; j++, arr[j] = arr[i]; //j=0, i=2; 1 != 2
     * so j is incremented and index 1's value is replaced with index 2's value. 
     * //arr = [1,2,2,2,3]; j=1; j now holds first occurrence of 2
     * 
     * i = 3; arr[j] == arr[i]; continue; //j=1, i =3; 2==2
     * i = 4; arr[j] != arr[i]
     * j++, change val //arr= [1,2,3,2,3] j now holds first occurrence of 3.
     * 
     * 
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array/ 
     * discuss/11769/5-lines-Java-solution
    */

    // * J WILL HOLD THE INDEX OF LAST NON REPEATING EL
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int j=0;
        for (int i=0; i<n; i++){
            if (nums[i]!=nums[j]){
                j++; 
                nums[j]=nums[i];
            }
        }
        System.out.println("j is at "+j);
        Utility.print1DMatrix(nums);
        return ++j;
    }

    // recursive, return when index == n
    public int removeDuplicates2(int[] nums) {
        return removeHelper(nums, 0, 0);
    }
    
    int removeHelper(int[] arr, int index, int j){
        if(index == arr.length) return j+1;
        
        if(arr[index]!=arr[j]){
            arr[++j] = arr[index];
        }
        return removeHelper(arr, index+1, j);
    }

    /** 
     * POINTS:
     * 1 USE CONCEPT OF MERGING INTO A NEW ARRAY
     * 2 START FROM MAX ELS AS FOR FINDING MIN EL ONE EXTRA PASS IS NEEDED [-5, -3, -2, -1]
     * 3 
    */
    // https://leetcode.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        int left = 0; int right = n-1; int index = n-1;
        
        while(left<=right){
            if(nums[left]*nums[left] < nums[right]*nums[right]){
                res[index--] = nums[right]*nums[right];
                right--;
            }
            else {
                res[index--] = nums[left]*nums[left];
                left++;
            }
        }
        return res;
    }
    
    ////////////////////////// 2 SUM, 3 SUM

    /**  
     * IF REPEATED AND NOT SORTED, USE HASHMAP
    */
    // https://leetcode.com/problems/two-sum/
    public int[] twoSumUnsorted(int[] nums, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] res = new int[2];
        int n = nums.length;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<nums.length; i++){
            if(map.containsKey(target - nums[i])) {
                res[0] = i; res[1] = map.get(target - nums[i]);
                break;
            }     
            else map.put(nums[i], i);
        }
        
        return res;
    }
    
    /**  
     * IF SORTED, USE BIDIRECTIONAL SEARCH
    */
    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSumSorted(int[] nums, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] res = new int[2];
        int n = nums.length;
        
        int low = 0; int high = n-1;
        
        while(low<high){
            if(nums[low]+nums[high] == target){
                res[0] = low+1; res[1] = high+1; break;
            }
            else if(nums[low]+nums[high]>target) high--;
            else if(nums[low]+nums[high]<target) low++;
            
        }
        
        return res;
    }


    /** IMP 
     * POINTS : 
     * 1 USE BIDIRECTIONAL SEARCH (WORKS ON SORTED)
     * 2 low = i+1; high = n
     * DUPLICATE HANDLING : 
     * 3 if (i > 0 && nums[i] == nums[i - 1]) continue;
     * 4 while (low < high && nums[low] == nums[low+1]) low++;
     * 
     * 5 AND AT THE END low++; high--;
     * TO CONTINUE THE LOOP AND NOT STOP;
     * WE COULD HAVE BROKEN IF WE HAD TO FIND A SINGLE SET
     *  
     * 
    */
    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if(n<3) return new ArrayList<>();
        Arrays.sort(nums);
        
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i =0; i<n; i++){
            // 1
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i+1; int high = n-1;
            while(low<high){
                int sum = nums[i]+nums[low]+nums[high];
                if(sum == 0){
                    // List<Integer> curr = new ArrayList<>();
                    // curr.add(nums[i]); curr.add(nums[low]); 
                    // curr.add(nums[high]);
                    // res.add(curr);
                    
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    // 2
                    while (low < high && nums[low] == nums[low+1]) low++;
                    while (low < high && nums[high] == nums[high-1]) high--;
                    low++; high--;
                    // break;
                }
                else if(sum>0) high--;
                else low++;
            }
        }
        return res;
    }

    // 2 condns, 1 diff is less, 
    // 2 sum > target
    // sort to use 2 pointer technique
    // diff in case of -ve nos
    // [1,1,1,0], -100
    /** 
     * sort, use 2 ptr
     * compare iwth current diff, if lesser update
    */
    // https://leetcode.com/problems/3sum-closest/
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length; 
        int diff = Integer.MAX_VALUE; int res = 0; // 1
        if (n<3) return 0;
        
        Arrays.sort(nums); // 2
        
        for(int i = 0; i<n-2; i++){ // 3
            int start = nums[i];
            int left = i+1; int right = n-1;
            
            while(left<right){ // 4
                int sum = start + nums[left] + nums[right];
                if(sum<target) left++;
                else right--;
                
                if(Math.abs(sum - target)<diff) { // 5
                    diff = Math.abs(sum - target);
                    res = sum;
                }
            }
        }
        return res;
    }

    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
    
    void mergekSortedArrays(int[] arr1, int[] arr2, int[] arr3) {

    }

    ///////////////////// SORT 0s1s2s

    // same as dutch national below, if 1, increment, else swap
    void sort01(int[] nums){
        int n = nums.length;
        int lo = 0; int i =0;
        while(i<n){
            if(nums[i] == 0) Utility.swap(nums, lo++, i++);
            else i++;
        }
    }


    /** 
     * DUTCH NATIONAL FLAG
     * it's a bit tricky, concept of quicksort partition
     * points:
     * 1 use while not for
     * 2 2 pointers needed 
     * 3 when 0 is seen increment both
     * 4 when 2 only decrement h; hence while is used
     *  as for will increment i in all cases
     * 
     * if 1 is seen we increment i
     * so if we see 2, then swap and check the same i, so same i
     */
    // lo holds index of first 1
    // [2,0,1]
    // https://leetcode.com/problems/sort-colors/
    public void sortColors(int[] nums) {
        int n = nums.length;
        int lo = 0; int hi = n-1;
        int i = 0 ;
        while(i<=hi){ // 1
            if(nums[i] == 0) Utility.swap(nums, lo++, i++); // 2
            else if(nums[i] == 2) Utility.swap(nums, hi--, i);
            else i++;
        }
    }


    void insertionSort(int[] arr) {
        // int i =0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j >= 1 && arr[j] < arr[j - 1]) {
                Utility.swap(arr, j, j - 1);
                j--;
            }
        }
    }

    // https://leetcode.com/problems/wiggle-sort-ii/
    /** technique is to sort and then fill with elements 
     * at even index from mid and odd index from end
     * even is smaller so mid is chosen
     * 
     * IMP: TO FIND MIDDLE ALWAYS USE (N-1)/2; NOT N/2
     * IF LENGTH=6 MID = 2, NOT 3
    */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] res = new int[n];
        int mid = (n-1)/2; int right = n-1; int count = 0;
        
        while(mid>=0 || right>(n-1)/2) { // 1
            if(count%2 == 0){  //2
                res[count]=nums[mid--];
            }
            if(count%2 != 0){
                res[count]=nums[right--];
            }
            count++;
        }
        
        for(int i =0; i<n; i++){
            nums[i] = res[i];
        }
    }
    

    void sortOddAscEvenDesc(int[] arr) {
        // arr[] = {1, 2, 3, 5, 4, 7, 10}, Output : {7, 5, 3, 1, 2, 4, 10}
        /** first use partition to separate even and odd */
        int oddNumbers = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0)
                oddNumbers++;

        }

    }


    /** SIMILARITIES WITH QUICKSORT
     * 1 BOUNDARY CONDN (low<=high) 
     * 2 USE MID
     * 3 f(low, mid-1); f(mid+1, high); NOT 0 AND n, USE LOW AND HIGH
     * 
    */
    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree
    public Node sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }
    
    Node helper(int[] arr,int low, int high){
        if(low>high) return null;
        if(low == high) return new Node(arr[low]);
        int mid = low + (high-low)/2;
        Node root = new Node(arr[mid]);
        root.left = helper(arr, low, mid-1);
        root.right = helper(arr, mid+1, high);
        return root;
    }

    /** 
     * POINTS : 
     * 1 MARK ALL -VE AND >n AS n+1
     * 2 MARK nums[Math.abs(nums[k]) - 1] AS -ve
     * 3 ITERATE AGAIN AND FIND FIRST +ve
     * 4 IF NONE, RETURN n+1
     * 
     * mark -ve nos and nos>n as n+1 and nums[i] - 1 as -ve
     * 
    */
    // [1,1], [-1,-2], [2,1], 
    // [-5] this is why we mark -ve as n+1
    // https://leetcode.com/problems/first-missing-positive
    public int firstMissingPositive(int[] nums) {
        // Math.abs(arr[index]) -1 -> -ve
        // traverse and find first +ve index-> +1
        int n = nums.length;
        if(n==0) return 1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        
        for(int k = 0; k<n; k++){ // start from 0 not j
            if(Math.abs(nums[k])-1 >= 0 && Math.abs(nums[k])-1<n 
               && nums[Math.abs(nums[k]) - 1] >0 ) // -ve should remain -ve
                nums[Math.abs(nums[k]) - 1]*=(-1);
        }
        
        // for(int p : nums) System.out.println(p);
        for(int i =0; i<n; i++){
            if(nums[i] > 0) return i+1;
        }
        return n+1;
    }

    // BOYER-MOORE ALGO, MAJORITY EL
    // https://leetcode.com/problems/majority-element-ii/
    // discuss/63520/Boyer-Moore-Majority-Vote-algorithm-and-my-elaboration

    // https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int res = nums[0]; int count = 1;
        
        for(int i=1; i<n; i++){
            if(count == 0){
                count++;
                res =nums[i];
            }
            else if(nums[i] == res) count++;
            else if(nums[i] != res) count--;
        }
        return res;
    }

    // KADANE' ALGO
    // int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
    /** 
     * point : max is found first and then
     * sum check is done. Doing the opposite 
     * doesn't work for negative numbers, as max
     * can never be negative as sum is set zero
     * beforehand and 0>-ve.
     */
    int maxSubArrayContiguous(int[] arr) {
        if(arr.length == 1) return arr[0];
        int max = Integer.MIN_VALUE; int sum = 0;
        
        for(int i =0; i<arr.length; i++){
            sum+= arr[i];
            max = Math.max(max, sum);
            if(sum<0) sum = 0;
        }
        return max;
    }

    /**
     * if 7,2,4 we can ignore the second element 
     * if k>=2 check boundary cases 7,2,4 
     * the first el of q is always added to res
     * 
     * 4 steps:
     * remove outgoing el, i-k
     * remove all smaller els
     * add el to q 
     * add first of q to res
     */
    int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;

        Deque<Integer> list = new LinkedList<Integer>();
        list.add(nums[0]);
        for(int i =0; i<k; i++){

            while(list.size()!=0 && list.getLast()<nums[i]){
                list.removeLast();
            }

            list.add(nums[i]);
        }
        res[index++] = list.getFirst();

        for(int i =k; i<n; i++){
            // remove (i-k)th el
            if(list.getFirst()==nums[i-k]){
                list.removeFirst();                
            }
            
            while(list.size()!=0 && list.getLast()<nums[i]){
                list.removeLast();
            }
            
            list.add(nums[i]);
            res[index++] = list.getFirst();
        }
        
        Utility.print1DMatrix(res);
        return res;
    }


    /** 
     * for every incoming remove the larger ones
     * if(k>0) remove still 
    */
    // https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if(n<k) return num;
        if(n==k) return "0"; // 10, 2
        Deque<Character> q = new LinkedList<>();
        q.addLast(num.charAt(0));
        
        for(int i =1; i<n; i++){
            char curr = num.charAt(i);
            // if(q.getLast()<curr){
            //     q.addLast(curr);
            //     continue;
            // }

            // 3 condns, 
            while (k>0 && q.size()!=0 && q.getLast()>curr) {
                q.removeLast(); k--;
                // System.out.println(q);
            }
            q.addLast(curr); // 2
        }
        
        // remaining 
        while(k>0 && q.size()!=0) { // 3 
            q.removeLast(); // 10
            k--;
        }
        // if(q.size() == 1) return ""+q.getFirst(); // 10, 1
        
        // remove leading zeroes
        while(q.size()!=0){
            if(q.getFirst() == '0') q.removeFirst();
            else break;
        }
        
        String res = "";
        while(q.size()!=0) res+=q.removeFirst();
        return res.length()==0?"0":res;
    }


    // https://stackoverflow.com/questions/6780632/returning-an-empty-array
    // https://practice.geeksforgeeks.org/problems/max-sum-path-in-two-arrays/1
    
    // TWO TRAVERSAL TECHNIQUE
    // https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums) {
        /**traversing from both ends
         do some calculations, will get the idea
        1st pass from l to r gives last element's output
        2nd pass gives first element's output
        
        middle elements are calculated as product of
        the 2 arrays correspnding indexes
        */
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n]; int[] holder = new int[n];
        
        int prod = nums[0]; left[0] = nums[0]; left[1] = nums[0];
        for(int i = 2; i<n; i++){
            left[i] = prod*nums[i-1]; 
            prod*=nums[i-1];
        }
        Utility.print1DMatrix(left);

        prod = nums[n-1]; right[n-1] = nums[n-1]; right[n-2] = nums[n-1];
        for(int i = n-3; i>=0; i--){
            right[i] = prod*nums[i+1]; 
            prod*=nums[i+1];
        }
        Utility. print1DMatrix(right);
        
        holder[0] = right[0]; holder[n-1] = left[n-1];
        for(int i = 1; i<n-1; i++){
            holder[i] = left[i]*right[i]; 
        }
        Utility.print1DMatrix(holder);
        return holder;
    }

    /** 
     * TWO TRAVERSALS NEEDED (LEFT AND RIGHT)
     * 
     * use 2 arrays
     * 1 store max till now in q
     * 2 IF CURR EL IS SMALLER, STORE DIFF IN ARRAY
     * 3 IF CURR EL IS LARGER THAN PEEK, PUSH
     * 4 DO SAME FOR RIGHT ARRAY
     * 5 FOR RES TAKE MIN OF LEFT AND RIGHT INDEX
     * 
     */
    // https://leetcode.com/problems/trapping-rain-water
    public int trap(int[] height) {
        int n = height.length;
        if(n==0) return 0;
        int[] leftMaxDiff = new int[n];
        int[] rightMaxDiff = new int[n];
        
        Deque<Integer> q = new LinkedList<>();
        q.addLast(height[0]); // 1
        
        for(int i =1; i<n-1; i++){
            // if curr is greater push; diff[index] = 0 by default
            if(q.getLast()<height[i]) q.addLast(height[i]);
            else leftMaxDiff[i] = q.getLast()-height[i];
        }
        
        q.clear();
        q.addLast(height[n-1]); // 1
        for(int i =n-1; i>0; i--){
            // if greater push and diff = 0;
            if(q.getLast()<height[i]) q.addLast(height[i]);
            else rightMaxDiff[i] = q.getLast()-height[i];
        }
        
        int res = 0;
        for(int i =1; i<n-1; i++){
            res+=Math.min(leftMaxDiff[i], rightMaxDiff[i]);
        }
        return res;
    }


    // Hashing, Prefix Sum
    // https://leetcode.com/problems/subarray-sum-equals-k
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        if(n==0) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1); // tricky, don't forget

        int sum = 0; int count = 0;
        
        for(int i : nums){
            sum+=i;
            count+=map.getOrDefault(sum-k,0);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;
    }

    // https://www.geeksforgeeks.org/sum-of-all-subarrays/

    // https://leetcode.com/problems/next-permutation/

    // CIRCULAR ARRAY 
    // https://leetcode.com/problems/next-greater-element-ii/

    
    public static void main(String[] args) {
        Array test = new Array();

        int[] arr2 = new int[] { 9, 7, 1, 8, 5, 6 };
        int[] duplicates = {1,1,2,2,2,3};
        // test.findMountain(arr2);
        // test.removeDuplicates(duplicates);
        int[] arr = new int[] { 1, 2, 5, 4, 3 };
        // test.findMissingAndRepeated(arr);

        // int[] arr = new int[]{1,0,1,0,0};
        // test.zeroOneSort(arr);
        // test.quickSortImplementation(arr, 0, arr.length-1);
        // System.out.println("-------------");
        // test.showArray(arr);

        // test.swap(arr, 0, 1);
        // test.showArray(arr2);

        // test.insertionSort(arr2);
        // System.out.println("---");
        // test.showArray(arr2);
        // test.findKthMax(arr2, 3);
        // int[] zeroArr = { 1, 9, 8, 4, 2, 7, 0, 0 };

        int[] zeroOneArr = { 0, 1, 1, 1, 1, 1 };
        // test.sort01(zeroOneArr);
        // Utility.print1DMatrix(zeroOneArr);
        // test.moveAllZeroesToEnd(zeroArr);
        // test.quickSortArray24Apr(zeroArr, 0, zeroArr.length-1);
        // test.print1DMatrix(zeroArr);
        // System.out.println();
        // test.showArray(zeroArr);

        // test.heapSort25Apr(zeroArr);
        // test.showArray(zeroArr);

        // int[] zeroOneArr = {0,0,0,0,0};
        // int[] zeroOneArr = {0,0,1,1,1};
        // int[] zeroOneArr = {1,1,1,1,1};
        // System.out.println( test.findFirstOne(zeroOneArr, 0, zeroOneArr.length-1));
        // test.whileCheck(zeroArr);

        String s = "abcd";
        // System.out.println(s.toCharArray()[0]);

        int[] pivot =
                // {1,7,3,6,5,6};
                { 1, 2, 3 };
        // test.pivotIndex(pivot);

        int[] asteroids = { -2, -1, 1, 2 };
        // test.asteroidCollision(asteroids);

        int[] maxIndex = { 34, 8, 10, 3, 2, 80, 30, 33, 1 };
        // test.maxIndex(maxIndex);

        int smallestMissing[] =
                // {0, -10, 1, 3, -20};
                { 1, 2, 3, 4, 5 };
        test.firstMissingPositive(smallestMissing);
        int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
        // test.largestSumContigousSubarray(a);

        int[] nums = // {7, 2, 4};
        {9,10,9,-7,-4,-8,2,-6};

                // { 1, -1 };
        // {1,3,-1,-3,5,3,6,7};
        int k = 5;//1;// 3;//2
        // test.maxSlidingWindow(nums, k);

        int[] selfArr = {1,2,3,4};
        // test.productExceptSelf(selfArr);

        int[] dutch = {2,1,2,1,0,0,0,1,0,2};
        // test.dutchNational(dutch);

        int[] dup = {1,3,4,5,6,2,3,4,5,6,8,8,9,11,1,11};
        // test.findDuplicates(dup);

        int[] equalLeftAndRight = {2,4,6,6,4,2,10};
        // test.equalLeftAndRightSum(equalLeftAndRight);

    }

}