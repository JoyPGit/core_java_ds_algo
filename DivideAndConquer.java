import java.util.*;

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


    /** one optimization can be to keep heap size limited to k */
    // https://leetcode.com/problems/k-closest-points-to-origin/
    class Holder{
        int index; int dist;
        Holder(int i, int d){
            this.index = i;
            this.dist =d;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        
        PriorityQueue<Holder> heap = 
            new PriorityQueue<Holder>((x, y)-> x.dist - y.dist);
        
        for(int i=0; i<n; i++){
            int a = points[i][0]; int b = points[i][1];
            int c = a*a + b*b;
            heap.add(new Holder(i, c));
        }
        
        int[][] res = new int[K][2];
        
        for(int i =0; i<K; i++){
            Holder curr = heap.remove();
            res[i][0] = points[curr.index][0];
            res[i][1] = points[curr.index][1];
        }
        
        return res;
    }

    
    
    // https://leetcode.com/problems/maximum-subarray/
    // https://leetcode.com/problems/search-a-2d-matrix-ii/
    // https://leetcode.com/problems/different-ways-to-add-parentheses/
    // https://leetcode.com/problems/count-of-range-sum/
    // https://leetcode.com/problems/the-skyline-problem/
    // https://leetcode.com/problems/merge-k-sorted-lists/


    public static void main(String[] args) {
        DivideAndConquer soluDivideAndConquer = new DivideAndConquer();
        int[] arr = new int[]{1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10};
        soluDivideAndConquer.findCountSortedInRange(arr);
    }
}