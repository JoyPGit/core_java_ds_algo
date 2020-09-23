import java.util.*;

public class SearchSort {
    
    SearchSort(){}

    /** 
     * POINTS : 
     * 1 THIS IS AN ACCEPTABLE SEARCH AS COMPARED TO THE RECURSIVE BINARY SEARCH
     * IN SUCH THAT IT STORES A RESULT AND THEN IMPROVES UPON IT.
     * 2 UPDATE RESULT AND RETURN AT END.
     * 
     * */
    int binarySearch(int[] arr, int start, int end, int key){
        int result = -1;
        int low = start; int high = end;
        while(high>=low){
            int mid = low + (high-low)/2;

            if(arr[mid] == key) result = mid; 
            if(arr[mid]>key) high = mid-1;
            if(arr[mid]<key) low =  mid+1;
        }
        
        return result;
    }

    /**
     * 1 USING ACCEPTABLE BINARY SEARCH
     * 2 IF MID IS BAD, STORE THEN GO LEFT
     * 3 ELSE GO RIGHT
     * 4 WHEN TO STORE, CHECK WHICH TYPE IS ATLEAST ACCEPTABLE,
     * IF BAD, STORE BAD.
     * 
     * STORING HELPS US AVOID UNNECESSARY CHECKS TO SEE IF THIS IS THE FIRST 
     * BAD BY CHECKING LEFT.
     */
    // https://leetcode.com/problems/first-bad-version/submissions/
    boolean isBadVersion(int x){return true;}//dummy func check ques
    public int firstBadVersion(int n) {
        int result = -1;
        
        int low = 1; int high = n;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isBadVersion(mid)){
                result =  mid; 
                high = mid-1;
            } else low = mid+1;
        }
        return result;
    }

    // https://leetcode.com/problems/search-insert-position/
    /** USING ACCEPTABLE BIN SEARCH
     * 1 IF EQUAL OR GREATER, IT'S ACCEPTABLE
     * 2 ELSE 
     */
    public int searchInsert(int[] nums, int target) {
        int result = -1; int n = nums.length;
        int low = 0; int high = n-1;
        if(n==0) return 0;
        if(target>nums[n-1]) return n;
        
        while(low <= high){
            int mid = low + (high-low)/2;
            
            // if(nums[mid] == target) result = mid;
            if(nums[mid] >= target){
                result = mid;
                high = mid-1;
            }
            if(nums[mid] < target) low = mid+1;
            
        }
        return result;
    }


    void insertionSort(int[] nums){
        int n = nums.length;
        for(int i = 1; i<n; i++){
            int j = i; int temp = nums[i];
            while(j>0 && (temp < nums[j-1])) {
                nums[j] = nums[j-1]; j--;
            }
            nums[j] = temp;
        }
        utilCustom.Utility.print1DMatrix(nums);
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
            utilCustom.Utility.swap(arr, index, min);
            heapify(arr, min);
        }
    }

    void quickSelect(int[] arr){
        
    }
    /** POINTS : (IMP INDEX VAR)
     * 1 ADD a(TO BE SORTED) TO A MAP
     * 2 ITERATE OVER B AND ADD THE ELS TO A 'FREQ' NO OF TIMES
     * 3 KEEP AN INDEX VAR TO KEEP TRACK OF WHERE TO ADD IN A
     * 4 FOR REMAININIG ELS IN MAP, ADD ALL ELS 'FREQ' NO OF TIMES
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
     *  x= partition()
     *  sort(x-1); sort(x+1);
     * }
     * 
     * 1 USE LAST EL AS PIVOT
     * 2 ALWAYS START i FROM LOW-1;
     * 
     */
    // https://leetcode.com/problems/sort-an-array/submissions/
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        if(n==0 || n==1) return nums;
        sort(nums, 0, n-1);//1 restructure
        // Arrays.sort(nums);
        return nums;
    }
    
    void sort(int[] arr, int low, int high){
        if(low < high){ // 2 
            int x = partition(arr, low, high);
            sort(arr, low, x-1); // 3 x-1, x+1
            sort(arr, x+1, high);
        }
    }
    
    int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low-1; // 4 most imp
        for(int j = low; j<high; j++){
            if(arr[j]<=pivot) utilCustom.Utility.swap(arr, ++i, j); //5 
        }
        utilCustom.Utility.swap(arr, ++i, high);
        return i;
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


    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    /** POINTS :
     * 1 USE TWO BINARY SEACH FUNCS, TO FIND FIRST AND LAST
     * 2 THE FIRST USES HIGH = MID-1 AS ONCE WE FIND A MATCHING INDEX, 
     *   WE NEED TO GO LEFT TO FIND IF SMALLER EXISTS
     * 3 THE LAST USED LOW = MID+1
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length; 
        if(n ==1 && nums[0]!= target) return new int[]{-1, -1};
        int i = binSearch(nums, 0, n-1, target);
        int j = binSearchLast(nums, 0, n-1, target);
      
        return new int[]{i,j};
    }
    
    int binSearch(int[] arr, int start, int end, int key){
        int result = -1;
        int low = start; int high = end;
        while(high>=low){
            int mid = low + (high-low)/2;
            if(arr[mid]>key) high = mid-1;
            if(arr[mid]<key) low =  mid+1;
            if(arr[mid] == key) {
                result = mid; 
                high = mid-1;
            }
        }
        
        return result;
    }
    
    int binSearchLast(int[] arr, int start, int end, int key){
        int result = -1;
        int low = start; int high = end;
        while(high>=low){
            int mid = low + (high-low)/2;
            if(arr[mid]>key) high = mid-1;
            if(arr[mid]<key) low =  mid+1;
            if(arr[mid] == key) {
                result = mid; 
                low = mid+1;
            }
        }
        
        return result;
    }

    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    // https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
    // https://leetcode.com/problems/diagonal-traverse-ii/
    // https://leetcode.com/problems/sort-colors/
    // https://leetcode.com/problems/rank-teams-by-votes/
    // https://leetcode.com/problems/sort-the-matrix-diagonally/
    // https://leetcode.com/problems/sort-list/
    // https://leetcode.com/problems/pancake-sorting/
    // https://leetcode.com/problems/maximum-number-of-coins-you-can-get/
    // https://leetcode.com/problems/rank-teams-by-votes/
    // https://leetcode.com/problems/car-fleet/
    // https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/
    // https://www.geeksforgeeks.org/
    // queries-to-find-kth-greatest-character-in-a-range-l-r-from-a-string-with-updates/
    // ?ref=leftbar-rightbar
    public static void main(String[] args){
        SearchSort coronaSort = new SearchSort();
        int[] arr = {10,3,5,8,6,1};//,4,6,7,9,21,43};
        coronaSort.insertionSort(arr);
        // coronaSort.heapSort(arr);
        // for(int i =0; i<arr.length; i++){
        //     System.out.println(arr[i]);
        // }
    }
}