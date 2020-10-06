import java.util.*;

class Array {

    /** 
     * IMP: TO FIND MIDDLE ALWAYS USE (N-1)/2; NOT N/2
     * IF LENGTH=6 MID = 2, NOT 3
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
   

    // { 9, 7, 1, 8, 5, 6 };
    void findMountain(int[] arr) {
        int n = arr.length;
        if(n==0) return;
        for (int i = 0; i < n; i++) {
            if(i==0 && arr[i] > arr[i+1]) {System.out.println("mountain found "+arr[i]);continue;}
            if(i==n-1 && arr[i] > arr[i-1]) {System.out.println("mountain found "+arr[i]);continue;}
            
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                System.out.println("mountain found "+arr[i]);
            }
        }
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

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && nums[j] == 1 && nums[i] == 1) j++;
            max = Math.max(j - i, max);
            i = j;
        }
        return max;
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

    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    // [1,1,2,3,3,4,4,8,8]
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int j = 0; int i = 0;
        while(i<n-1){//out of bounds error
            while(nums[j] == nums[i]) j++;
            j--;// get back to curr el as j goes to next el
            if(j == i) return nums[i];
            // System.out.println("j "+j+" i "+i);
            i = ++j;//move i and j both to next el
        }
        //if here, the last el must be the single el which is returned
        return nums[n-1];
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

    // * J WILL HOLD THE INDEX F LAST NON REPEATING EL
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
        utilCustom.Utility.print1DMatrix(nums);
        return ++j;
    }

    // https://leetcode.com/discuss/interview-question/558379/
    
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
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i+1; int high = n-1;
            while(low<high){
                int sum = nums[i]+nums[low]+nums[high];
                if(sum == 0){
                    // List<Integer> curr = new ArrayList<>();
                    // curr.add(nums[i]); curr.add(nums[low]); curr.add(nums[high]);
                    // res.add(curr);
                    
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    
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

    // https://leetcode.com/problems/three-consecutive-odds/
    /** 
     * FOR QUES LIKE THESE WHERE WE HAVE TO CHECK FOR SOME
     * SERIES SATRTING FROM AN INDEX
     * USE 2 LOOPS. HERE AS WE HAVE TO LOOK FOR 2 
     * WE CAN USE NESTED IF TWICE
     * 
     * else for values >3
        for(int i =0; i<n-1; i++){
            if(arr[i]%2!=0){
                int j =0;
                while(j<2){
                    if((++i)==n || arr[i]%2==0) break;
                    j++;
                }
                if(j==2) return true;                
            }
        }
     * 
     * 
     * THE TRICK IS TO INCREMENT i IN THE INNER LOOP SO THAT 
     * WE DON'T LOOP AGAIN OVER SAME ELEMENT.
     * 
     * 
     * optimizations: run till n-1
     * replace nested if with &&
     * if (arr[i]%2 != 0) && (++i)!= n && arr[i]%2 != 0) &&
                (++i) != n && arr[i]% 2 != 0)
                        return true;
     * 
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 != 0) {
                if ((++i) != n && arr[i] % 2 != 0) {
                    if ((++i) != n && arr[i] % 2 != 0)
                        return true;
                }
            }
        }
        return false;
    }


   

    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
    
    void mergekSortedArrays(int[] arr1, int[] arr2, int[] arr3) {
        int holder1, holder2, holder3 = 0;

        // max function needed for max of k elements
        // int smallest = max(arr1[holder1], arr2[holder2], arr3[holder3]);

        // can use a priority queue or a min heap

    }

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

    void zeroOneSort(int[] arr) {
        int j = -1;
        int pivot = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j++;
                utilCustom.Utility.swap(arr, i, j);
            }
            // System.out.println("j "+ arr[j]);
        }

        utilCustom.Utility.swap(arr, j + 1, pivot);
    }

    public void sort0s1s(int[] nums) {
        int index = -1;
        for(int i =0; i<nums.length; i++){
            if(nums[i] != 0){
                index++;
                utilCustom.Utility.swap(nums, index, i);
            }
        }
    }
    

    /** it's a bit tricky, concept of quicksort partition
     * points:
     * 1 use while not for
     * 2 2 pointers needed 
     * 3 when 0 is seen increment both
     * 4 when 2 only decrement h; hence while is used
     *  as for will increment i in all cases
     */
    public void dutchNational(int[] nums) {
        int n = nums.length;
        int l = 0, h = n - 1, i = 0;
        while( i <= h ) {
            if(nums[i] == 0 ) utilCustom.Utility.swap(nums, l++, i++);
            else if(nums[i] == 2) utilCustom.Utility.swap(nums, h--, i);    
            else i++;
        }
        utilCustom.Utility.print1DMatrix(nums);
    }

    void insertionSort(int[] arr) {
        // int i =0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j >= 1 && arr[j] < arr[j - 1]) {
                utilCustom.Utility.swap(arr, j, j - 1);
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
        
        while(mid>=0 || right>(n-1)/2) {
            if(count%2 == 0){
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

    void findKthMax(int[] arr, int k) {
        int size = arr.length;
        buildHeap(arr, size);
        for (int i = 0; i < k; i++) {
            System.out.println(deleteTop(arr, size--));
        }
    }

    void buildHeap(int[] arr, int size) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
        System.out.println(" start" + arr[0]);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    void heapify(int[] arr, int i) {
        int max = i;
        if (2 * i + 1 < arr.length) {
            if (arr[max] > arr[2 * i + 1])
                max = 2 * i + 1;
        }
        if (2 * i + 2 < arr.length) {
            if (arr[max] > arr[2 * i + 2])
                max = 2 * i + 2;
        }
        if (max != i) {
            swapHeap(arr, i, max);
            heapify(arr, max);
        }
    }

    int deleteTop(int[] arr, int size) {
        int value = arr[0];
        arr[0] = arr[size - 1];
        heapify(arr, 0);
        return value;
    }

    void swapHeap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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

    //USE HASHMAP AND PRIORITYQUEUE
    void sortByFrequency(int[] arr) {}


    void quickSortArray24Apr(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition24Apr(arr, low, high);
            System.out.println("pivot " + pivot);
            // if(pivot!=-1){
            quickSortArray24Apr(arr, low, pivot - 1);
            quickSortArray24Apr(arr, pivot + 1, high);
            // }
            /**
             * when it comes to single element of array, then the element violates the
             * condition of start<end and hence recursion stops
             */
        }
    }

    int partition24Apr(int[] arr, int start, int end) {
        // if (start < end) {
        System.out.print("low index " + start + ", value " + arr[start] + " ,");
        System.out.println("high index " + end + ", value " + arr[end]);

        int i = start - 1;
        int j = 0;
        int key = arr[end];

        for (j = start; j < end; j++) {
            if (arr[j] < key) {
                i++;
                utilCustom.Utility.swap(arr, i, j);
            }
        }
        utilCustom.Utility.swap(arr, i + 1, end);// 8,9 maintain position
        return i + 1;
        // }
        // if(start == end) return start;
        // return -1;
    }

    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree
    /** SIMILARITIES WITH QUICKSORT
     * 1 BOUNDARY CONDN (low<=high) 
     * 2 USE MID
     * 3 f(low, mid-1); f(mid+1, high); NOT 0 AND n, USE LOW AND HIGH
     * 
    */
    // public TreeNode sortedArrayToBST(int[] nums) {
    //     return helper(nums, 0, nums.length-1);
    // }
    
    // TreeNode helper(int[] arr,int low, int high){
    //     if(low>high) return null;
    //     int mid = low + (high-low)/2;
    //     TreeNode root = new TreeNode(arr[mid]);
    //     root.left = helper(arr, low, mid-1);
    //     root.right = helper(arr, mid+1, high);
    //     return root;
    // }

    //////////////////////////////////////////////
    void heapSort25Apr(int[] arr) {
        int n = arr.length;
        int mid = arr.length / 2 - 1;
        for (int i = mid; i >= 0; i--) {
            heapify25Apr(arr, arr.length, i);
        }

        utilCustom.Utility.print1DMatrix(arr);
        System.out.println();

        for (int i = n - 1; i > n - 2 - 1; i--) {
            utilCustom.Utility.swap(arr, i, 0);
            // int temp =arr[0];
            // arr[0] = arr[i];
            // arr[i] = temp;

            heapify25Apr(arr, i, 0);
        }
    }

    void heapify25Apr(int[] arr, int n, int index) {
        int max = index;
        int left;
        int right;

        if ((2 * index + 1) < n) {
            left = 2 * index + 1;
            if (arr[max] > arr[left])
                max = left;
        }
        if ((2 * index + 2) < n) {
            right = 2 * index + 2;
            if (arr[max] > arr[right])
                max = right;
        }

        if (max != index) {
            utilCustom.Utility.swap(arr, index, max);
            heapify25Apr(arr, n, max);
        }
    }

    int findFirstOne(int[] arr, int start, int end) {
        if (end >= start) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == 1) {
                if ((mid - 1) >= 0 && arr[mid - 1] == 1)
                    return findFirstOne(arr, start, mid - 1);
                return mid;
            }
            if (arr[mid] == 0) {
                return findFirstOne(arr, mid + 1, end);
            }

            else {
                return findFirstOne(arr, start, mid - 1);
            }
        }

        else
            return -1;

    }

    // return mid;

    void whileCheck(int[] arr) {
        int i = 0;
        while (i < 2) {
            System.out.println(i);
            i++;
        }
        zeroSetter(i);
    }

    void zeroSetter(int i) {
        i = 0;
    }

    // boolean dead = false;
    // int count = 0;
    // void goDeeper(){
    // if(dead == true) return;
    // System.out.println(count++);//goes till 8612
    // goDeeper();
    // }

    // public int pivotIndex(int[] nums) {
    // int left =0; int leftSum =nums[left];
    // int right =nums.length-1; int rightSum=nums[right];

    // boolean found = false;
    // while(!found){
    // if(left>=right || right-left==1) return -1;
    // if(leftSum > rightSum) rightSum += nums[--right];
    // else if(leftSum<rightSum) leftSum += nums[++left];
    // else {
    // found = true;
    // System.out.println(rightSum);
    // }
    // }
    // System.out.println("left "+left);
    // System.out.println("right "+right);
    // return left+1;
    // }


    int maxIndex(int[] arr) {
        int maxDiff = 0;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && (i - j) > maxDiff) {
                    maxDiff = i - j;
                }
            }
        }
        System.out.println(maxDiff);
        return maxDiff;
    }

    int smallestPositiveMissing(int[] arr) {
        int min = 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == min)
                min++;
        }
        System.out.println("smallest +ve missing ");
        System.out.println(min);
        return min;
    }

    // KADANE' ALGO
    // int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
    /** point : max is found first and then
     * sum check is done. Doing the opposite 
     * doesn't work for negative numbers, as max
     * can never be negative as sumis set zero
     * beforehand and 0>-ve.
     */
    int maxSubArrayContiguous(int[] arr) {
        if(arr.length ==1) return arr[0];
        int max = Integer.MIN_VALUE; int sum = 0;
        
        for(int i =0; i<arr.length; i++){
            sum+= arr[i];
            max = Math.max(max, sum);
            if(sum<0) sum = 0;
        }
        
        return max;
    }

    /**
     * if 7,2,4 we canignore the second element if k>=2 check boundary cases 7,2,4 k
     * = 1 1,-1 k =1 INCORECT SOLUTION, USE HEAP CHECK Heap.java
     */
    int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;

        Deque<Integer> list = new LinkedList<Integer>();
        list.add(nums[0]);
        for (int i = 1; i < k; i++) {
            if (nums[i] > list.getLast())
                list.add(nums[i]);
            else
                list.add(list.getLast());
        }
        res[index++] = list.getLast();
        for (int i = k; i < n; i++) {
            if (list.size() != 0) {
                if (list.getFirst() == list.getLast()) {
                    /**
                     * if the first element is max till now then just add the incoming element
                     * because, had the last element been greater it would have been the last
                     * element in the list
                     */
                    list.add(nums[i]);
                    // res[index++] = list.getLast();
                } else {
                    if (nums[i] > list.getLast())
                        list.add(nums[i]);
                    else
                        list.add(list.getLast());
                }
            } else {
                list.add(list.getLast());
            }
            list.removeFirst();
            res[index++] = list.getLast();

        }

        utilCustom.Utility.print1DMatrix(res);
        return res;
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
        utilCustom.Utility.print1DMatrix(left);

        prod = nums[n-1]; right[n-1] = nums[n-1]; right[n-2] = nums[n-1];
        for(int i = n-3; i>=0; i--){
            right[i] = prod*nums[i+1]; 
            prod*=nums[i+1];
        }
        utilCustom.Utility. print1DMatrix(right);
        
        holder[0] = right[0]; holder[n-1] = left[n-1];
        for(int i = 1; i<n-1; i++){
            holder[i] = left[i]*right[i]; 
        }
        utilCustom.Utility.print1DMatrix(holder);
        return holder;
    }

    //https://leetcode.com/problems/trapping-rain-water/
    /** technique is similar to product of elements except self
     * TWO TRAVERSALS NEEDED;
     * use 3 arrays
     * 1 store previous max for each el
     * 2 store next max for each el
     * 3 third array stores the min of both the max of both arrays
     * 4 use thge same array to store diff of 3rd array els and original array
     * 5 use <= as for same height no need to update max
     * 6 the ends are always zero
     */
    public int trapRainwater(int[] height) {
        int n = height.length;
        if(n==0 || n==1) return 0;
        int prev_max = height[0]; int next_max = height[n-1];
        int[] prev = new int[n];
        int[] next = new int[n];
        int[] holder = new int[n];
        
        prev[0] = 0;
        for(int i =1; i<n; i++){
            if(height[i]<=prev_max) prev[i] = prev_max;
            else {
                prev_max = height[i];
                prev[i] = 0;
            }
        }
        
        next[n-1] = 0;
        for(int i =n-2; i>=0; i--){
            if(height[i]<=next_max) next[i] = next_max;
            else {
                next_max = height[i];
                next[i] = 0;
            }
        }
        
        for(int i =1; i<n-1; i++){
            holder[i] = Math.min(prev[i], next[i]);
        }
        
        for(int i =1; i<n-1; i++){
            holder[i] = (holder[i]-height[i])>0?(holder[i]-height[i]):0;
        }
        
       int sum =0;
        for(int i =1; i<n-1; i++){
            sum+=holder[i];
        }
          
        return sum;
    }

    // https://leetcode.com/problems/first-missing-positive/
    // inaccurate soln but works for tougher ques
    public int firstMissingPositive(int[] nums) {
        int holder = 1;
        Arrays.sort(nums);
        for(int i : nums) if(i==holder) holder++;
        return holder;
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
    // https://www.geeksforgeeks.org/paytm-interview-experience-set-9/
    // Given an array, find all the subarrays (contiguous) of even length which 
    // have equal left and right sums. Ex:- [2,4,6,6,4,2,10], 
    // answer is 4, i.e. [2,4,6,6,4,2],[4,6,6,4,2,10],[4,6,6,4],[6,6]
    /** similar to dp ques, use l and i+l-1 =j; palindrome type */
    int equalLeftAndRightSum(int[] arr){
        int count = 0;
        int n = arr.length;
        for(int l=2; l<n; l++){
            for(int i = 0; l+i-1<n; i++){
                int j = i+l-1;
                if(l==2 && arr[i] == arr[j]) count++;
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

    // https://leetcode.com/problems/next-permutation/
    // https://leetcode.com/problems/subarray-sum-equals-k/

    // CIRCULAR ARRAY 
    // https://leetcode.com/problems/next-greater-element-ii/

    // https://leetcode.com/problems/subarray-product-less-than-k/
    

    // https://www.geeksforgeeks.org/paytm-interview-experience-set-9/
    // Given an array, find all the subarrays (contiguous) of even length which 
    // have equal left and right sums. Ex:- [2,4,6,6,4,2,10], 
    // answer is 4, i.e. [2,4,6,6,4,2],[4,6,6,4,2,10],[4,6,6,4],[6,6]

    
    public static void main(String[] args) {
        // code

        int[] arr2 = new int[] { 9, 7, 1, 8, 5, 6 };
        Array test = new Array();
        int[] duplicates = {1,1,2,2,2,3};
        // test.findMountain(arr2);
        test.removeDuplicates(duplicates);
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
        int[] zeroArr = { 1, 9, 8, 4, 2, 7, 0, 0 };
        // test.moveAllZeroesToEnd(zeroArr);
        // test.quickSortArray24Apr(zeroArr, 0, zeroArr.length-1);
        // test.print1DMatrix(zeroArr);
        // System.out.println();
        // test.showArray(zeroArr);

        // test.heapSort25Apr(zeroArr);
        // test.showArray(zeroArr);

        // int[] zeroOneArr = {0,0,0,0,0};
        // int[] zeroOneArr = {0,0,1,1,1};
        int[] zeroOneArr = { 0, 1, 1, 1, 1, 1 };
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
        // test.smallestPositiveMissing(smallestMissing);
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