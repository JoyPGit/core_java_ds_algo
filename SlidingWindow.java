import java.util.*;

public class SlidingWindow {

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /** 
     * 
     * BY DEFAULT, RUN 2 LOOPS, ONE FOR THE FIRST WINDOW AND OTHER TILL N;
     * 
     * SHRINK TILL IT'S LESSER BUT ADD IN EACH ITERATION
     * (check subarray-product-less-than-k)
     * 
     * 3 techniques
     * 
     * 1 IMP FOR LOOP AND THEN SHRINK USING WHILE LOOP 
        minSubArrayLenWithSumGreaterThanOrEqualK
        
     * 2 USING DEQUE, LINE 152, INCLUDE AND EXCLUDE, CHECK OUTGOING WITH INCOMING
        findFirstNegative

     * 3 USING DEQUE, LINE 84, WHILE ADDING POP TILL LARGER 
        maxSlidingWindowStack
    */

    /**mostly we use deque, sometimes hashmap 
     * deque properties;
     * getLast, getFirst, removeLast, removeFirst, add
    */

    // MICROSOFT
    // https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
    ArrayList<Integer> distinctElsinWindow(int[] arr, int k){
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
         * remove outgoing el, decrement count; add incoming el; if
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

    /**
     * max els in every window, use deque, 
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


    
    // https://leetcode.com/problems/subarray-product-less-than-k/
    /**  
        https://leetcode.com/problems/subarray-product-less-than-k/discuss/
        741191/JAVA-Simple-Solution%3A-Sliding-Window
        nums = [10, 5, 2, 6], k = 100; Output: 8

     * SIMILAR SHRINKING TECHNIQUE
     * 1 BOUNDARY CONDITION IS IMP if(k<=1) return 0;
     * 2 COUNT IS UPDATED THROUGHOUT
     * 3 WHEN PROD>K, IT'S BROUGHT UNDER K
     * 4 (i - start+1) ADDS ALL THE ELS AND 1 EXTRA FOR THE SUBARRAY 
     *   FROM start+1 TILL i AS A WHOLE

    */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1) return 0;
        int n = nums.length;
        
        int prod = 1; int count = 0; int start =0;
        for(int i =0; i<n; i++){
            prod*=nums[i];
            while(prod>=k){
                prod/=nums[start];
                start++;
            }
            count+=i-start+1;
        }
        return count;
    }


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



    /** tricky thing here is to check for the list size and add the incoming el
     * and then return 0 if list size is empty.
     * 1 add to res after the first for loop
     * 2 if list is empty and arr[i-k] == getfirst(); add 0
     * 3 
     */
    ArrayList<Integer> findFirstNegative(int[] arr, int k){
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
            // System.out.println(arr[i-k]);
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
    // use XOR

    // similar https://leetcode.com/problems/minimum-window-substring/
    // https://www.geeksforgeeks.org/smallest-subarray-k-distinct-numbers/
    void smallestSubArrayWithKDistinct(int[] arr, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 0; int left = 0; int min = Integer.MAX_VALUE;

        for(int i =0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            } else map.put(arr[i], 1);

            while(counter>=k){
                left = getMin(map);
                // max = i
                min = Math.min(min, i-left+1);
                map.remove(arr[left++]);
            }
        }
    }

    int getMin(HashMap<Integer, Integer>map){
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            min = Math.min(min, value);
        }   
        // System.out.println("min "+ min); 
        return min;
    }

    int getMax(HashMap<Integer, Integer>map){
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            max = Math.max(max, value);
        }   
        // System.out.println("max "+ max); 
        return max;
    }
    
    // https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/

    
    // CELEBRITY PROBLEM

    // Returns id of celebrity. Else -1
    /** if a knows b, then a isn't a celeb, and if a doesn't know b, then
     * b isn't a celeb. eliminate the non celeb; a++; b--;
     * move 2 pointers, and finally check for a
     */
	int findCelebrity(int[][] matrix) 
	{   
        int n = matrix.length;
		int a = 0; int b = n - 1; 
		
		while (a < b) { 
			if (knows(matrix, a, b)) a++; 
			else b--; 
		} 

		// Check if a is a celebrity or not
		for (int i = 0; i < n; i++) { 
			// If any person doesn't know 'a' or 'a' doesn't 
			// know any person, return -1 
			if (i != a && (knows(matrix, a, i) || 
						!knows(matrix, i, a))) 
				return -1; 
		} 
		return a; 
    } 
    
    // Returns true if a knows b, else false 
	boolean knows(int[][] matrix, int a, int b) { 
		return (matrix[a][b] == 1) ? true : false; 
	} 


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
        
        int[][] celebMatrix = 
        { {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 0},
        {0, 0, 1, 0} };
        slide.findCelebrity(celebMatrix);
    }
    
}