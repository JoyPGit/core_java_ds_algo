import java.util.*;

public class SearchSort {
    
    SearchSort(){}

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

    // https://leetcode.com/problems/sort-list/submissions/
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head; ListNode slow = head;
        ListNode temp = null;
        while(fast!=null && fast.next != null){
            temp = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        
        temp.next = null;
        ListNode a = sortList(head);
        ListNode b = sortList(slow);
        return merge(a, b);
    }
    
    ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(a!=null && b!=null){
            if(a.val<b.val) {
                dummy.next = a; a = a.next;
            }else{
                dummy.next = b; b = b.next;
            }
            dummy = dummy.next;
        }
        
        if(a!=null) dummy.next = a;
        if(b!=null) dummy.next = b;
        
        return p.next;
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
            swap(arr, index, min);
            heapify(arr, min);
        }
    }

    void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    void mergeSort(int[] arr){

    }

    void mergeSortLinkedList(){}

    void quickSort(int[] arr){

    }

    void quickSelect(int[] arr){
        
    }

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
    public static void main(String[] args){
        SearchSort coronaSort = new SearchSort();
        int[] arr = {10,3,5,6,8,1,4,6,7,9,21,43};
        coronaSort.heapSort(arr);
        for(int i =0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}