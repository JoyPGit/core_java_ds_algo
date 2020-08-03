import java.util.*;

public class SlidingWindow {

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /**mostly we use deque or hashmap or both 
     * deque properties;
     * getLast, getFirst, removeLast, removeFirst, add
    */

    // https://leetcode.com/problems/minimum-size-subarray-sum/
    /** run 2 loops, continue till sum>s, then
     * try to decrement from the start, till sum>=s; increment left; track min
     */
    public int minSubArrayLenWithSumGreaterThanOrEqualK(int s, int[] nums) {
        int n = nums.length; int sum =0; 
        int min =Integer.MAX_VALUE; int left =0;
        
        
        for(int i =0; i<n; i++){
            sum+=nums[i];
            while(sum >= s){
                min = Math.min(i-left+1, min);
                sum = sum - nums[left++];
            }
        }
        return min == Integer.MAX_VALUE?0:min;
    }
    /** the above works for non negative integers, this one for both */
    // https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/


    // MICROSOFT
    // https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
    List<Integer> distinctElsinWindow(int[] arr, int k){
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i =0; i<k; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], 1);
                count++;
            }else {
                map.put(arr[i], map.get(arr[i])+1);
            }
        }
        res.add(count);

        /**
         * remove outgoing el, decrement count; add incomiiing el; if
         * count>1, don't increment counter; else c+1
         * 
         * map contains mapping of element, by default it's null; check for that too
         * 
         * if outgoing = incoming, do nothing
         * else 
         * check if outgoing has count 1, then decrement count
         * check incoming, if it has count == null increment count; update map
         *  if count!=null; update map
         */
        for(int i =k; i<arr.length; i++){
            
            if(arr[i-k] == arr[i]){
                res.add(count);
            }else{
                int old = map.get(arr[i-k]);
                if(old==1) count--;
                map.put(arr[i-k], old-1);
                System.out.println(map);

                if(map.get(arr[i])==null){
                    map.put(arr[i], 1);
                    count++;
                }else if(map.get(arr[i])!=null){
                    map.put(arr[i], map.get(arr[i])+1);
                }
                res.add(count);
            } 
        }
        System.out.println(res);
        return res;
    }

    /**max els in every window, use deque, 
     * run 2 loops, pop till el is larger
     * 
     * same technique is used often
    */

    public int[] maxSlidingWindowStack(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int [n-k+1]; int index= 0;

        Deque<Integer> list = new LinkedList<>();

        for(int i =0; i<k; i++){
            while(list.size()!=0 && list.getLast()<nums[i]){
                list.removeLast();
            }

            list.add(nums[i]);
            // System.out.println(list);
        }
        res[index++] = list.getFirst();
    
        System.out.println("first k");
        print1DMatrix(res);

        for(int i =k; i<n; i++){
            if(list.getFirst()==nums[i-k]){
                list.removeFirst();                
            }
            
            while(list.size()!=0 && list.getLast()<nums[i]){
                System.out.println("in here");
                list.removeLast();
            }
            
            list.add(nums[i]);
            System.out.println(list);
            res[index++] = list.getFirst();
        }
        print1DMatrix(res);
        return res;
    }


    /** tricky thing here is to check for the list size and add the incoming el
     * and then return 0 if list size is empty.
     * 1 add to res after the first for loop
     * 2 if list is empty anf arr[i-k] == getfirst(); add 0
     * 3 
     */
    List<Integer> findFirstNegative(int[] arr, int k){
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> list = new LinkedList<>();

        for(int i =0; i<k; i++){
            if(arr[i]<0) {
                list.add(arr[i]);
            }
        }
        System.out.println(list);
        if(list.size()==0) res.add(0);
        else res.add(list.getFirst());

        for(int i =k; i<arr.length; i++){
            System.out.println(arr[i-k]);
            if(list.size()!=0 && list.getFirst() == arr[i-k]){
                list.removeFirst(); 
            }
            if(arr[i]<0){
                list.add(arr[i]);
            }

            if(list.size()==0) res.add(0);
            else res.add(list.getFirst());

            System.out.println("k "+list);
        }

        System.out.println(res);
        return res;
    }

    // https://www.geeksforgeeks.org/
    // count-of-subarrays-of-size-k-with-elements-having-even-frequencies/?ref=rp

    // https://www.geeksforgeeks.org/count-of-subarrays-of-size-k-with-
    // elements-having-even-frequencies/?ref=rp

    public static void main(String[] args) {
        SlidingWindow slide = new SlidingWindow();
        int sum = 11; int[] nums = {1,2,3,4,5};    
        // slide.minSubArrayLenWithSumGreaterThanOrEqualK(sum, nums);

        int[] arr = {1, 2, 4, 4};
        // {1, 2, 1, 3, 4, 2, 3};
        int k = 2;//4;
        // slide.distinctElsinWindow(arr, k);

        // int negArr[] = {12, -1, -7, 8, -15, 30, 16, 28} , negk = 3;
        int negArr[] = {-8, 2, 3, -6, 10}, negk = 2;
        slide.findFirstNegative(negArr, negk);
    }
}