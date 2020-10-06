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


    // https://leetcode.com/problems/diagonal-traverse-ii/

    /** 
     * POINTS
     * 1 a HOLDS INDEX OF FIRST 1
     * 2 b HOLDS INDEX OF 2
     * 3 CHECK QUICKSORT (A DIFF SOLN TO CLRS IF POSSIBLE)
     * 4 IF 0, SWAP, i++, a++;
     *   IF 1, i++
     *   IF 2, SWAP, b--;
     * */
    // https://leetcode.com/problems/sort-colors
    public void sortColors(int[] nums) {
        int n = nums.length;
        int a = 0, i = 0, b = nums.length-1;
        while(i<=b){
            if(nums[i] == 0) {
                utilCustom.Utility.swap(nums, i, a);
                i++;
                a++;
            }
            else if(nums[i] == 1) i++;
            else if(nums[i] == 2) {
                utilCustom.Utility.swap(nums, i, b);
                b--;
            }
        }
        // System.out.println(a);
    }

    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/
    
    public static void main(String[] args) {
        Sorting sorter = new Sorting();
        int[] arr = {4,5,6,7,1,2,8};
        sorter.insertionSort(arr);
        
    }
}
