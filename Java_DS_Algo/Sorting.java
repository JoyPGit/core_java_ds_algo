package Java_DS_Algo;
import java.util.*;

public class Sorting {
    Sorting(){}

    void insertionSort(int[] nums){
        int n = nums.length;
        for(int i = 1; i<n; i++){
            int j = i; int temp = nums[i];
            while(j>0 && (temp < nums[j-1])) {
                nums[j] = nums[j-1]; j--;
            }
            nums[j] = temp;
        }
        for(int i :nums) System.out.println(i);
        // Utility.print1DMatrix(nums);
    }


    void heapSort (int[] arr){
        int i =0;
        for(i=arr.length/2;i>=0;i--){
            heapify(arr, i);
        }
    }

    void heapify(int[] arr, int index){
        int min= index;
        if(index*2+1<arr.length && arr[index]>arr[index*2+1]) min = index*2+1;
        if(index*2+2<arr.length && arr[min]>arr[index*2+2]) min = index*2+2;

        if(min!=index) {
            // Utility.swap(arr, index, min);
            heapify(arr, min);
        }
    }

    //////////////////////////////////////////////
     void heapSort25Apr(int[] arr) {
        int n = arr.length;
        int mid = arr.length / 2 - 1;
        for (int i = mid; i >= 0; i--) {
            heapify25Apr(arr, arr.length, i);
        }

        // Utility.print1DMatrix(arr);
        System.out.println();

        for (int i = n - 1; i > n - 2 - 1; i--) {
            // Utility.swap(arr, i, 0);
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
            // Utility.swap(arr, index, max);
            heapify25Apr(arr, n, max);
        }
    }



    void quickSelect(int[] arr){
        
    }


    void merge(int[] arr, int l, int m, int r) {
        // all work is done on the arr passed as param
        int n1 = m - l + 1; // middle is always on the lower side
        int n2 = r - m;

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            arr1[i] = arr[l + i];
        }
        for (int i = 0; i < n2; i++) {
            arr2[i] = arr[m + i]; // m+i+1
        }
        
        int[] arr3 = new int[n1 + n2];
        int t1 = 0, t2 = 0;
      
        int k = l;
        
        while (t1 < n1 && t2 < n2) {
            if (arr1[t1] < arr2[t2]) {
                arr3[t1] = arr1[t1];
                t1++;
            } else {
                arr3[t2] = arr2[t2];
                t2++;
            }
            k++;
        }

        while (t1 < k) {
            arr[k] = arr1[t1];
            t1++; k++;
        }
        while (t2 < k) {
            arr[k] = arr2[t2];
            t2++; k++;
        }
    }

    void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int middle = (l + r) / 2;
            mergeSort(arr, l, middle);
            mergeSort(arr, middle + 1, r);

            merge(arr, l, middle, r);
        }
    }

    // in place merge sort
    // merge sort linked list

    /** 
     * POINTS : (IMP INDEX VAR)
     * 1 ADD a(TO BE SORTED) TO A MAP
     * 2 ITERATE OVER B AND ADD THE ELS TO A 'FREQ' NO OF TIMES
     * 3 KEEP AN INDEX VAR TO KEEP TRACK OF WHERE TO ADD IN A
     * 4 FOR REMAINING ELS IN MAP, ADD ALL ELS 'FREQ' NO OF TIMES
     * TO A LIST
     * 5 SORT LIST AND ADD TO A USING 'INDEX' VAR
     */
    // https://leetcode.com/problems/relative-sort-array/
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int x : arr1) map.put(x, map.getOrDefault(x,0)+1);

        int index = 0;
        for(int i=0; i<arr2.length; i++){
            int freq = map.get(arr2[i]); 
            for(int j=0; j<freq; j++) arr1[index++] = arr2[i];
            map.remove(arr2[i]);
        }
        
        // now add remaining to list to be sorted
        List<Integer> remaining = new ArrayList<>();
        for(Map.Entry <Integer, Integer> entry : map.entrySet()){
            for(int i=0; i<entry.getValue(); i++) remaining.add(entry.getKey());
        }
        
        Collections.sort(remaining);
        for(int i =0; i<remaining.size(); i++) arr1[index++] = remaining.get(i);
        
        return arr1;
    }

    /**
     * QUICKSORT
     * sort(){
     *  mid = partition()
     *  sort(lo, mid-1); sort(mid+1, hi);
     * }
     * 
     * 1 USE LAST EL AS PIVOT
     * 2 ALWAYS USE lo and hi;
     * 
     * pivot is returned after partition
     * 
     */
    // https://leetcode.com/problems/sort-an-array
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        if(n==0 || n==1) return nums;
        int index = (int)Math.random()*n;
        swap(nums, index, n-1); // 1
        quickSort(nums, 0, n-1);
        return nums;
    }
    
    // like tree from inorder
    // root, then both halves
    void quickSort(int[] nums, int lo, int hi){
        // lo == hi; single el so must be at correct posn
        // no need to find pivot
        if(lo>=hi) return; 
        int pivot = partition(nums, lo, hi);
        // System.out.println("pivot "+pivot);
        quickSort(nums, lo, pivot-1);
        quickSort(nums, pivot+1, hi);
    }
    
    int partition(int[] arr, int lo , int hi){
        // if(lo == hi) return lo;
        int i = lo; int j = lo; int compare = arr[hi];
        while(i<=hi){
            if(arr[i] < compare) swap(arr, i++, j++);
            else i++;
        }
        swap(arr, j, hi);
        
        return j;
    }
    
    void swap(int[] arr, int a ,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /** MERGE SORT ON LINKED LIST 
     * similar structure as quickSort
     * 3 STEPS : 
     * sort(){
     * middle; next null
     * sort(); sort();
     * merge
    */
    // https://leetcode.com/problems/sort-list/submissions/
    // public ListNode sortList(ListNode head) {
    //     if (head == null || head.next == null) return head;
    //     ListNode fast = head; ListNode slow = head;
    //     ListNode temp = null;
    //     while(fast!=null && fast.next != null){
    //         temp = slow;
    //         fast = fast.next.next;
    //         slow = slow.next;
    //     }
        
    //     temp.next = null;
    //     ListNode a = sortList(head);
    //     ListNode b = sortList(slow);
    //     return merge(a, b);
    // }
    
    // ListNode merge(ListNode a, ListNode b){
    //     ListNode dummy = new ListNode(0);
    //     ListNode p = dummy;
    //     while(a!=null && b!=null){
    //         if(a.val<b.val) {
    //             dummy.next = a; a = a.next;
    //         }else{
    //             dummy.next = b; b = b.next;
    //         }
    //         dummy = dummy.next;
    //     }
        
    //     if(a!=null) dummy.next = a;
    //     if(b!=null) dummy.next = b;
        
    //     return p.next;
    // }

  

    /**
     * POINTS :
     * 1 START FROM MID AND RIGHT AND ADD FROM BOTH ALTERNATELY
     * 2 DO ODD EVEN CHECK AS ADDING SIMULTANEOULSY FAILS FOR
     * ODD LENGTH ARRAY
     * MID = (n-1)/2  ; lo + (hi-lo)/2;
     * 
     */
    // [1,1,2,1,2,2,1]
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] res = new int[n];
        int mid = (n-1)/2; int right = n-1;
        
        for(int i =0; i<n; i++){
            if(i%2 == 0) res[i] = nums[mid--];
            else res[i] = nums[right--];
        }
        
        for(int i =0; i<n; i++){
            nums[i] = res[i];
        }
    }


    // https://leetcode.com/problems/diagonal-traverse-ii/

    ///////////////////// SORT 0s1s2s

    // same as dutch national below, if 1, increment, else swap
    void sort01(int[] nums){
        int n = nums.length;
        int lo = 0; int i =0;
        while(i<n){
            // if(nums[i] == 0) //Utility.swap(nums, lo++, i++);
            // else i++;
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
    // [2,0,1]
    // https://leetcode.com/problems/sort-colors/
    public void sortColors(int[] nums) {
        int n = nums.length;
        int lo = 0; int hi = n-1;
        int i = 0 ;
        while(i<=hi){ // 1
            // if(nums[i] == 0) //Utility.swap(nums, lo++, i++); // 2
            // else if(nums[i] == 2) //Utility.swap(nums, hi--, i); // 3
            // else i++;
        }
    }

    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/

    
    
    public static void main(String[] args) {
        Sorting sorter = new Sorting();
        int[] arr = {4,5,6,7,1,2,8};
        sorter.insertionSort(arr);        
    }
}
