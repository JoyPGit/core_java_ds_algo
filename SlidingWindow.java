import java.util.*;

public class SlidingWindow {


    /** 
     * IMP FOR SUBSTRING USE SLIDING WINDOW, FOR SUBSEQUENCE,
     * HASHMAP MIGHT BE USED.
     * 
     * IMP QUES MIN-WINDOW-SUBSTRING SEE SHRINKING PART
     * BY DEFAULT, RUN 2 LOOPS, ONE FOR THE FIRST WINDOW AND OTHER TILL N;
     * 
     * SHRINK TILL IT'S LESSER BUT ADD IN EACH ITERATION
     * (check subarray-product-less-than-k)
     * 
     * some techniques
     * 
     * 1 IMP FOR LOOP AND THEN SHRINK USING WHILE LOOP 
     * minSubArrayLenWithSumGreaterThanOrEqualK
     *   
     * 2 USING DEQUE, LINE 152, INCLUDE AND EXCLUDE, CHECK OUTGOING WITH INCOMING 
     * findFirstNegative
     * 
     * 3 USING DEQUE, LINE 84, WHILE ADDING POP TILL LARGER maxSlidingWindowStack
     *    
     * 4 MIN SWAPS QUESTIONS TOO, KEEP TRACK OF THE NO NOT TO BE SWAPPED
     * 
     * 5 TO MAP INDEX OF SLIDING WINDOW TO CHAR ARRAY USE 
     * curr[s.charAt(prev) - 'a']
     * 
     * 
     * basically we keep a char array(base) to keep count of pattern
     * and use a new array(curr) while sliding over the string. 
     * Whenever the arrays match, we store the start index of the current window.
     *
     * if(Arrays.equals(base, curr)) res.add( i-pattern.length() + 1); 
     * 
     * 
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
        utilCustom.Utility.print1DMatrix(res);

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
        utilCustom.Utility.print1DMatrix(res);
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


    /** 
     * POINTS :
     * 1 USE MAP.SIZE, NOT INDIVIDUAL COUNTS
     * no need oF while(leftMap.get('a') + leftMap.get('b') + leftMap.get('c') >= 3)
     * 
     * 2 IF FREQ == 0, REMOVE FROM MAP
     * 3 left++
     * 4 count+=left similar to above
     * 
    */
    // https://leetcode.com/problems/number-of-substrings-containing-all-three-characters
    public int numberOfSubstrings(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0; int count = 0;
        // HashSet<String> set = new HashSet<>();
        
        for(int i =0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
            while(map.size() == 3){
                // count++; 
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if(map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
                left++;
            }
            count+=left;
        }
            
        return count;
    }

    // https://leetcode.com/problems/minimum-size-subarray-sum/
    /** 
     * POINTS : 
     * 1 run 2 loops
     * 2 WHILE sum>=s; track min, LEFT++ 
     * 3 RETURN CONDITION IS IMP, IF length == 0 OR SUM NEVER REACHES s, 
     * MAX_VALUE IS RETURNED
     */
    public int minSubArrayLenWithSumGreaterThanOrEqualK(int s, int[] nums) {
        int n = nums.length; int sum =0; 
        int min =Integer.MAX_VALUE; int left =0;
        
        
        for(int i =0; i<n; i++){
            sum+=nums[i];
            while(sum >= s){
                min = Math.min(i-left+1, min);
                sum = sum - nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE?0:min;
    }


    /** the above works for non negative integers, this one for both */
    // https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
    // https://leetcode.com/problems/subarray-sum-equals-k/submissions/


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
    /////////////////////////// NEXT 3 QUES ARE VERY IMP ////////////////

    /** POINTS : 
     * 1 left HOLDS THE INDEX OF A NON REPEATING CHAR
     * left = Math.max(left, map.get(s.charAt(i))+1);
     * handle aab, pwwke, dvdf
     * 
     * left IS INCREMENTED TO THE NEXT INDEX OF THE CHAR FOUND IN MAP
     * 
     */
    // https://leetcode.com/problems/longest-substring-without-repeating-characters
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n == 0) return n;
        HashMap<Character, Integer> map = new HashMap<>();
        int len = 0; 
        int left = 0;
        
        for(int i =0; i<n; i++){
            if(map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i))+1);
            }
            len = Math.max(len, i-left+1);   
            map.put(s.charAt(i), i);
        }
        return len;
    }


    // "cdadabcc"
    // https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
    public String smallestSubsequence(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int len = text.length();

        PriorityQueue<String> pq = new PriorityQueue<>();

        for (int i = 0; i < text.length(); i++)
            set.add(text.charAt(i));

        for (int i = 0; i < text.length(); i++) {
            map.put(text.charAt(i), map.getOrDefault(text.charAt(i), 0) + 1);
            while (map.size() == set.size()) {
                map.put(text.charAt(left), map.get(text.charAt(left)) - 1);
                if (map.get(text.charAt(left)) == 0)
                    map.remove(text.charAt(left));
                if (i - left + 1 <= len) {
                    len = i - left + 1;
                    pq.add(text.substring(left, i + 1));
                }
                left++;
            }
        }
        System.out.println(pq);
        return "pq listed";
    }

    /** 
     * IMP QUES */
    // https://leetcode.com/problems/minimum-window-substring
    public String minWindow(String s, String t) {
        if(s == null || s.length() < t.length() || s.length() == 0) return "";
        HashMap<Character, Integer>map = new HashMap<>();
        
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        int start =0; int left = 0; int count = 0; int len = Integer.MAX_VALUE;
        
        for(int i =0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get( s.charAt(i) ) -1);
                if(map.get(s.charAt(i)) >= 0) count++;
            }
            
            // System.out.println(map);
            while(count == t.length()) {
                if(i-left+1 < len){
                    start = left;
                    len = i-left+1;
                }
                // now increment the freq which was reduced
                if(map.containsKey(s.charAt(left))){
                    map.put(s.charAt(left), map.get(s.charAt(left))+1);
                    if(map.get(s.charAt(left))>0) count--;
                }
                left++;
                // System.out.print("in here ");
                // System.out.println(map);
            }
            // System.out.println("left "+left);
        }
        if(len>s.length()) return ""; 

        return s.substring(start, start+len);
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


    /** POINTS :
     * 1 WHENEVER A QUES OF MIN SWAPS AND THE DATA SET IS CONTIGOUS
     * USE SLIDING WINDOW
     * 2 HERE WE FIND THE NO OF 1s, THIS DETERMINES THE SIZE OF THE WINDOW
     * 3 NOW COUNT NO OF 0s, THIS IS THE MIN NO OF SWAPS
     * 4 KEEP TRACK OF MIN NO OF 0s
     * 5 IF OUTGOING INDEX IS ZERO zero--; IF INCOMING IS ZERO zero++
     * 6 IMP BOUNDARY CONDNS : j = count+1 till n-count
     */
    // https://leetcode.com/discuss/interview-question/414660/
    // MICROSOFT
    // https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
    // https://www.youtube.com/watch?v=VXi_-2CmitM
    // https://leetcode.com/discuss/interview-question/344778/
    // find-the-minimum-number-of-swaps-required-such-that-all-
    // the-0s-and-all-the-1s-are-together
    int minSwaps(int[] arr){
        int n = arr.length;
        int count = 0;
        for(int i =0; i<n; i++) if(arr[i]==1) count++;

        int zero = 0; int min = Integer.MAX_VALUE;
        for(int i = 0; i<count; i++){
            if(arr[i]==0) zero++;
        }
        min = Math.min(min, zero);
        System.out.println("min "+min);

        for(int j = 1; j<n-count; j++){
            if(arr[j-1]==0) zero--;
            if(arr[j+count]==0) zero++;
            min = Math.min(min, zero);
        }
        System.out.println("min no of swaps " + min);
        return min;
    }
    // https://leetcode.com/problems/max-consecutive-ones-iii/

    /** 
    POINTS : 
     * MOST IMP ANAGRAM SO WINDOW SIZE WILL REMAIN SAME
     * ARRAYS.EQUALS
     * 
     * 1 USE A CHAR ARRAY NOT A HASHMAP, IT'S EASIER TO COMPARE WITH ARRAYS.EQUALS
     * 2 STORE PATTERN'S COUNT IN A CHAR ARRAY(NAMED 'BASE') OF SIZE 26
     * 3 NOW SLIDING WINDOW CONCEPT COMES. IT IS DONE IN 2 STEPS : 
     * FIRST WINDOW AND THEN ALL OTHER WINDOWS,

     * TRAVERSE FROM i TILL n (PATTERN LENGTH) AND STORE IN A NEW ARRAY--> FIRST WINDOW
     * AND THEN SLIDE RIGHT BOUNDARY TILL END(STRING LENGTH) --> OTHER WINDOWS

     * 4 COMPARE IF ARRAYS ARE EQUAL 
     * WE KEEP THE BASE ARRAY AS A REFERENCE AND THE CURR ARRAY HOLDS 
     * THE STATE OF THE CURRENT SLIDING WINDOW

     * 5 IF ARRAYS ARE EQUAL STORE START INDEX OF THIS WINDOW
     * (i-window length) window length = pattern length;
    */	
    // https://leetcode.com/problems/find-all-anagrams-in-a-string
    public List<Integer> findAnagrams(String s, String t) {
        char[] base = new char[26];
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0)
            return res;
        int n = t.length();
        if (n > s.length())
            return res;

        for (char c : t.toCharArray())
            base[c - 'a']++;

        char[] ch = s.toCharArray();
        char[] curr = new char[26];
        for (int i = 0; i < n; i++)
            curr[ch[i] - 'a']++;
        if (Arrays.equals(base, curr))
            res.add(0);

        for (int i = n; i < s.length(); i++) {
            int prev = i - n;
            curr[s.charAt(prev) - 'a']--;
            curr[s.charAt(i) - 'a']++;

            if (Arrays.equals(base, curr))
                res.add(prev + 1);
        }
        return res;
    }

    /** 
     * POINTS : 
     * 1 IF S1 CONTAINS ANY CHAR OF S2, TAKE SUBSTRING FROM 
     * i TILL  i+n, SORT IT AND COMAPRE WITH S1'S CHAR ARRAY
     * 
     * SAME AS ANAGRAM, SORT AND COMPARE
     * TAKE CARE OF INDEXES, loop till s2.length()-n+1
    */
    // https://leetcode.com/problems/permutation-in-string/
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        if(s1.length() > s2.length()) return false;
        char[] ch = s1.toCharArray();
        Arrays.sort(ch);
        
        for(int i =0; i<s2.length()-n+1; i++){
            if(s1.contains(""+s2.charAt(i))){//1
                char[] curr = (s2.substring(i, i+n)).toCharArray();//2
                // System.out.println(String.valueOf(curr));
                Arrays.sort(curr);
                if(Arrays.equals(ch, curr)) return true;
                // if(n >= 3)i += n-2;//3
            }        
        }
        return false;
    }
    

    // https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
    // discuss/516977/JavaC%2B%2BPython-Easy-and-Concise
    // https://leetcode.com/problems/permutation-in-string/

    // https://leetcode.com/problems/find-the-town-judge/

    // Count Number of Nice Subarrays
    // Replace the Substring for Balanced String
    // Max Consecutive Ones III
    // Binary Subarrays With Sum
    // Subarrays with K Different Integers
    // Fruit Into Baskets
    // Shortest Subarray with Sum at Least K
    // Minimum Size Subarray Sum
    public static void main(String[] args) {
        SlidingWindow slide = new SlidingWindow();
        int sum = 11; int[] nums = {1,2,3,4,5};    
        // slide.minSubArrayLenWithSumGreaterThanOrEqualK(sum, nums);

        String smallestSub = "cdadabcc";
        slide.smallestSubsequence(smallestSub);

        int[] arr = {1, 2, 4, 4};
        // {1, 2, 1, 3, 4, 2, 3};
        int k = 2;//4;
        // slide.distinctElsinWindow(arr, k);

        // int negArr[] = {12, -1, -7, 8, -15, 30, 16, 28} , negk = 3;
        int negArr[] = {-8, 2, 3, -6, 10}, negk = 2;
        // slide.findFirstNegative(negArr, negk);
        
        int[][] celebMatrix = 
        { {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 0},
        {0, 0, 1, 0} };
        // slide.findCelebrity(celebMatrix);

        int[] minSwap1s = {1,0,1,0,1,0,0,1,1,1};
        // int[] minSwap1s = {0,0,0,1,0};
        // slide.minSwaps(minSwap1s);
    }
    
}