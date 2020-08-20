import java.util.*;

class Array {

    /** 
    * IMP: TO FIND MIDDLE ALWAYS USE (N-1)/2; NOT N/2
    * IF LENGTH=6 MID = 2, NOT 3
    */
    /**TECHNIQUES
     * 1 TWO TRAVERSALS
     * 2 WIGGLE SORT
     * 3 USING STACK/DEQUE
     * 4 USING XOR
     * 5 CIRCULAR ARRAY 
     */
    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i] + ";");
                System.out.println();
            } else
                System.out.print(arr[i] + ", ");
        }
    }

    void showArray(int[] arr) {
        print1DMatrix(arr);
    }

    void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == arr.length - 1 && j == arr[0].length - 1) {
                    System.out.println(arr[i] + ";");
                    System.out.println();
                } else
                    System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void findMountain(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1] && a[i] > a[i + 1]) {

            }
        }
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
     * THE TRICK IS TO INCEREMNT i IN THE INNER LOOP SO THAT 
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

    void reArrange(int[] arr) {
        // int[] arr = new int[]{1,2,3,4,5,6,7,8};
        int mid = (arr.length / 2) - 1;
        int track = 2;

        for (int i = 1; i < arr.length / 2; i++) {
            while (arr[track] < arr[i]) {
                track++;
            }
            if (arr[i] % 2 == 0 && i + 1 > arr.length - 1) {
                swap(arr, i, track);
            }
        }
    }

    void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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
                swap(arr, i, j);
            }
            // System.out.println("j "+ arr[j]);
        }

        swap(arr, j + 1, pivot);
    }

    void quickSortImplementation(int[] arr, int start, int end) {
        if (start < end) {

            int pivot = partition(arr, start, end);

            quickSortImplementation(arr, start, pivot - 1);
            quickSortImplementation(arr, pivot + 1, end);
        }
    }

    int partition(int[] arr, int start, int end) {
        int pivot = end;

        int j = start - 1;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[pivot]) {
                j++;
                swap(arr, j, i);
            }
        }
        j++;
        swap(arr, j, pivot);
        return pivot;
    }

    public void sort0s1s(int[] nums) {
        int index = -1;
        for(int i =0; i<nums.length; i++){
            if(nums[i] != 0){
                index++;
                swap(nums, index, i);
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
            if(nums[i] == 0 ) swap(nums, l++, i++);
            else if(nums[i] == 2) swap(nums, h--, i);    
            else i++;
        }
        print1DMatrix(nums);
    }

    void insertionSort(int[] arr) {
        // int i =0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j >= 1 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
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

    void sortByFrequency(int[] arr) {
        int[] indexArr = new int[arr.length];
        quickSortArray24Apr(arr, 0, arr.length - 1);

        indexCountMapper(arr, indexArr);

    }

    void indexCountMapper(int[] arr, int[] indexArr) {
        int counter = 0;
        int prevValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (prevValue == arr[i]) {
                prevValue = arr[i];
                counter++;
            } else {
                indexArr[i - 1] = ++counter;
                prevValue = arr[i];
                counter = 0;
            }
        }
    }

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
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);// 8,9 maintain position
        return i + 1;
        // }
        // if(start == end) return start;
        // return -1;
    }

    void heapSort25Apr(int[] arr) {
        int n = arr.length;
        int mid = arr.length / 2 - 1;
        for (int i = mid; i >= 0; i--) {
            heapify25Apr(arr, arr.length, i);
        }

        showArray(arr);
        System.out.println();

        for (int i = n - 1; i > n - 2 - 1; i--) {
            swap(arr, i, 0);
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
            swap(arr, index, max);
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

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length == 0)
            return new int[0];
        if (asteroids.length == 1)
            return asteroids;

        int leftptr = 0;
        int rightptr = -1;

        for (int i = 0; i < asteroids.length - 1; i++) {
            if ((asteroids[i] * asteroids[i + 1]) < 0) {
                System.out.println(asteroids[i] * asteroids[i + 1]);
                leftptr = i;
                rightptr = i + 1;
                break;
            }
        }

        System.out.println("left " + leftptr + " right " + rightptr);

        while (leftptr >= 0 && rightptr < asteroids.length) {
            if (Math.abs(asteroids[leftptr]) > Math.abs(asteroids[rightptr])) {
                asteroids[rightptr++] = 0;
            } else if (Math.abs(asteroids[leftptr]) == Math.abs(asteroids[rightptr])) {
                asteroids[leftptr--] = 0;
                asteroids[rightptr++] = 0;
            } else {
                asteroids[leftptr--] = 0;
            }
        }

        int count = 0;
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != 0)
                count++;
        }

        int[] arr = new int[count];
        int index = 0;
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != 0)
                arr[index++] = asteroids[i];
        }
        showArray(arr);
        return arr;
    }

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

        print1DMatrix(res);
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
        print1DMatrix(left);

        prod = nums[n-1]; right[n-1] = nums[n-1]; right[n-2] = nums[n-1];
        for(int i = n-3; i>=0; i--){
            right[i] = prod*nums[i+1]; 
            prod*=nums[i+1];
        }
        print1DMatrix(right);
        
        holder[0] = right[0]; holder[n-1] = left[n-1];
        for(int i = 1; i<n-1; i++){
            holder[i] = left[i]*right[i]; 
        }
        print1DMatrix(holder);
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

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public ArrayList<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i =0; i<n-1; i++){
            while((i<n-1) && (nums[i]^nums[i+1]) == 0){
                list.add(nums[i]);
                i++;//to get rid of similar elements
            } 
            // val = val^nums[i];
        }
        
        System.out.println(3^3);
        // System.out.println(4^3);
        System.out.println("list is "+list);
        return list;
    }

    // https://leetcode.com/problems/first-missing-positive/
    // inaccurate soln but works for tougher ques
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length; 
        int i = 0; int j =0;
        int min = 1; boolean found = false;
        
        while(nums[i]<=0 && i<n) i++;
        // min = nums[i]<0?1:nums[i];
        
        if(i==n-1) return min;
        if(i==n) return min;
        // else min = nums[i];
        System.out.println(i);
        for(j =i+1; j<n; j++){
            if((nums[j] - nums[j-1])!=1) {
                min = nums[j-1]+1;
                found =true;
                System.out.println("min "+min);
            }
        }
        System.out.println(j);
        if(j== n && !found) return nums[n-1]+1;
        return min;
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
    // https://leetcode.com/problems/subarray-sum-equals-k/

    // CIRCULAR ARRAY 
    // https://leetcode.com/problems/next-greater-element-ii/
    public static void main(String[] args) {
        // code

        Array test = new Array();
        int[] arr = new int[] { 1, 2, 5, 4, 3 };
        int[] arr2 = new int[] { 9, 7, 1, 3, 5, 6 };
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
        int[] zeroArr = { 1, 9, 8, 4, 2, 7 };
        // test.moveAllZeroesToEnd(zeroArr);
        // test.quickSortArray24Apr(zeroArr, 0, zeroArr.length-1);
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
        test.findDuplicates(dup);
    }

}