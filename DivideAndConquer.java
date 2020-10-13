import java.util.*;

class DivideAndConquer{
    
    void printList(ArrayList<Integer> list){
        for(int i : list){
            System.out.println(list.get(i));
        }
    }
    void printListArray(ArrayList<int[]> list){
        for(int i =0; i<list.size(); i++){
            System.out.print("["+list.get(i)[0]+","+list.get(i)[1]+"], ");
        }
    }

    int binarySearch(int[] arr, int low, int high, int key) {
        if (low >= 0 && high < arr.length) {
            int mid = (low + high) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                binarySearch(arr, mid + 1, high, key);
            if (arr[mid] > key)
                binarySearch(arr, low, mid, key);
        }
        return -1;
    }
    
    public boolean isPalindrome(String s, int low, int high){
        while(low < high)
           if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    /**TECHNIQUES
     * 1 DIVIDE AND CONQUER MOSTLY USES HEAPS
     * 2 OR ELSE SOME SORT OF SORTING, BE IT ARRAY OR HEAP
     */
    // https://leetcode.com/tag/divide-and-conquer/
    
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/

    void findCountSortedInRange(int[] arr){
        int length = arr.length;
        breakAndFind(arr, 0, length/2-1);
        breakAndFind(arr, length/2,length-1);
    } 

    void breakAndFind(int[] arr, int start, int end){
        if(start>=0 && start<=end ){
            if(arr[start]==arr[end]){
                
                System.out.println("count of element "+ arr[start] +" "+ (end-start+1));
            } else {
                int length = end-start+1;
                breakAndFind(arr, start, start + (length/2)-1);
                breakAndFind(arr, start + (length/2), end);
            }
        }
    }

    
    // https://leetcode.com/problems/maximum-subarray/
    // https://leetcode.com/problems/search-a-2d-matrix-ii/
    // https://leetcode.com/problems/count-of-range-sum/
    // https://leetcode.com/problems/the-skyline-problem/
    // https://leetcode.com/problems/merge-k-sorted-lists/
    // https://leetcode.com/problems/count-of-smaller-numbers-after-self/

    

    // https://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/

    public static void main(String[] args) {
        DivideAndConquer soluDivideAndConquer = new DivideAndConquer();
        int[] arr = new int[]{1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10};
        // soluDivideAndConquer.findCountSortedInRange(arr);
        
    }
}