class DivideAndConquer{
    
    /**TECHNIQUES
     * 1 DIVIDE AND CONQUER MOSTLY USES HEAPS
     * 2 OR ELSE SOME SORT OF SORTING, BE IT ARRAY OR HEAP
     */
    // https://leetcode.com/tag/divide-and-conquer/
    
    // https://leetcode.com/problems/different-ways-to-add-parentheses/
    // discuss/66328/A-recursive-Java-solution-(284-ms)

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


    public static void main(String[] args) {
        DivideAndConquer soluDivideAndConquer = new DivideAndConquer();
        int[] arr = new int[]{1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10};
        soluDivideAndConquer.findCountSortedInRange(arr);
    }
}