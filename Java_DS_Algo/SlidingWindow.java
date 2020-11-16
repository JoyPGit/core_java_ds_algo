package Java_DS_Algo;
import java.util.*;
// import Java_DS_Algo.utilCustom.*;
public class SlidingWindow {


    /** 
     * counting distinct can be done using counter or map size
     * 
     * count distinct
     * longest ones
     * smallest with sum>=k, smallest substr with k els
     * length of longest substr, 
     * no of substrings, numSubarrayProductLessThanK
     * all substrings containing k els (count+=)
     * 
     * celebrity, anagrams, swaps
     * 
     * min window, 
     * 
     * 
     * basic idea is to reduce the freq of outgoing and 
     * increase freq of incoming
     * 
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

    /** 
     * POINTS :
     * 1 USE MAP SIZE TO FIND DISTINCT ELS
     * 2 DO IN TWO STEPS, ONCE FOR FIRST WINDOW, THEN FOR SUBSEQUENT WINDOWS
     * 3 DECREMENT FOR OUTGOING EL, IF FREQ == 0, REMOVE
     * 4 ADD THE INCOMING EL
     * 5 ADD MAP SIZE TO RES
     * 
    */
    // MICROSOFT
    // https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
    ArrayList<Integer> countDistinct(int A[], int n, int k){
        ArrayList<Integer> res = new ArrayList<>();
        if(n==0) return res;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<k; i++){
            map.put(A[i], map.getOrDefault(A[i], 0)+1);
            // if(map.get(A[i]) == 1) count++;
        }
        res.add(map.size());
        
        for(int i = k; i<n; i++){
            int out = A[i-k];
            // decrement outgoing's freq
            map.put(out, map.get(out)-1);
            // remove if 0 freq
            if(map.get(out) == 0) map.remove(out);
            // new el
            map.put(A[i], map.getOrDefault(A[i], 0)+1);
            res.add(map.size());
        }
        System.out.println("disinct els in every window of size "+k+
        " is "+res+" found using map size");
        return res;
    }

    /**  
     * count distinct can be solved wither by counting all els
     * or keeping track of map size
     * */

    /**
     * max els in every window, use deque, 
     * 1 run 2 loops, 
     * 2 pop till el is larger
     * 3 add first el to res
     * maintain a global index
    */
    // https://leetcode.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int [n-k+1]; int index= 0;

        Deque<Integer> list = new LinkedList<>();

        for(int i =0; i<k; i++){
            while(list.size()!=0 && list.getLast()<nums[i]){
                list.removeLast();
            }
            list.add(nums[i]);
        }
        res[index++] = list.getFirst();

        for(int i = k; i<n; i++){
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
        // Java_DS_Algo.utilCustom.Utility.print1DMatrix(res);
        return res;
    }

    

    /** tricky thing here is to check for the list size and add the incoming el
     * and then return 0 if list size is empty.
     * 1 add to res after the first for loop
     * 2 if list is empty and arr[i-k] == getfirst(); add 0
     * 3 COMPARING THE INCOMING WITH Q's FIRST
     */
    // ONLY DISTINCT NEGATIVE NO IN EVERY WINDOW OF SIZE K
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


    /////////////// SHRINKING, USE LEFT AND UPDATE AFTER WHILE

    /** 
     * 1 SHRINK ONLY WHEN ZEROES>K
     * 2 UPDATE ONLY AFTER SHRINKING, THIS HANDLES THE ELSE CONDITION
     * OF UPDATING ONLY WHEN ZEROES<K
     * 3  i-left+1
     * 
     * we may change up to K values from 0 to 1.
    */
    // https://leetcode.com/problems/max-consecutive-ones-iii
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        if(n == 0) return n;
        int zeroes = 0;
        int max = 0;
        int left = 0;
        
        for(int i =0; i<n; i++){
            if(A[i]==0) zeroes++;
            
            // int j = 0;
            while(zeroes>K){
                if(A[left]==0) zeroes--;
                left++;
            }
            max = Math.max(max, i-left+1);
        }
        return max;
    }
    
    ///// TRICKY USE HASHMAP AND COMPARE USING t.length()
    /** 
     *  IT'S A BIT TRICKY, if t contains aa, we need to find aa, 
     * only a won't do.
     * 
     * POINTS :
     * 
     * 1 SO USE HASHMAP (why? because hashset can't store freq
     * and we need freq to remove els from the map)
     * 2 COUNT IS USED TO KEEP TRACK OF MAP SIZE 
     * (i.e. when all chars of t have been included)
     * 3 IF LEFT IS PRESENT IN MAP, REDUCE COUNT; AS LEFT HAS MOVED OVER A VALID CHAR
     * 
     */
    // https://leetcode.com/problems/minimum-window-substring
    public String minWindow(String s, String t) {
        if(s == null || s.length() < t.length() || s.length() == 0) return "";
        HashMap<Character, Integer>map = new HashMap<>();
        
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        int start =0; int left = 0; int count = 0; int len = Integer.MAX_VALUE;
        
        // add only valid chars(chars in t) 
        for(int i =0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get( s.charAt(i) ) -1);
                if(map.get(s.charAt(i)) >= 0) count++;
            }
            // to accoiunt for duplicates in t
            // System.out.println(map);
            while(count == t.length()) {
                // if smaller len found, update len and starting char
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

    /////////////////// SHRINK; MAP SIZE = SLIDING WINDOW SIZE

    /** 
     * POINTS :
     * 1 USE A SLIDING WINDOW, A LEFT POINTER
     * 2 ALWAYS USE INDEXES, NOT (int i : nums) FOR SUM
     * 3 ALWAYS UPDATE LENGTH INSIDE WHILE LOOP
     * 4 ADD A CHECK IN THE RETURN STATEMENT
     * 
     * find the minimal length of subarray which has sum ≥ s. 
    */
    // https://leetcode.com/problems/minimum-size-subarray-sum
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int left = 0; int sum = 0;
        int length = Integer.MAX_VALUE;
        
        for(int i=0; i<n; i++){
            sum += nums[i];
            while(sum>=s){
                length = Math.min(length, i-left+1);
                // System.out.println(sum);
                // System.out.println("len "+length);
                sum-=nums[left];
                left++;
            }
        }
        return length==Integer.MAX_VALUE?0:length;
    }


    /** 
     * HOLD THE FREQ OF INTEGER IN MAP, NOT INDEX, 
     * INDEX CAN BE KEPT TRACK OF USING LEFT FLAG
     * 
     * how to shrink?
     * once the map size is >= k, 
     * while loop
     * compare length
     * remove left, 
     * 
     * repeated eles can cause the length to grow
     * arr[] = { 1, 1, 2, 2, 3, 3, 4, 5} ,    k = 3  o/p = [5 7]
     * 
     * for exactly k els :  while(map.size() == k)
     * for atleast k :  while(map.size()>=k)
     */
    // https://www.geeksforgeeks.org/smallest-subarray-k-distinct-numbers/
    int smallestSubArrayKDistinct(int[] arr, int k){
        int n = arr.length; 
        int left = 0; int length = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1); 
            // keep shrinking
            while(map.size()>=k){
                // update length
                length = Math.min(length, i- left+1);
                if(map.get(arr[left]) == 1) map.remove(arr[left]);
                else map.put(arr[left], map.get(arr[left]) - 1);
                left++;
            }
        }
        System.out.println("smallest subarray with "+k+" distinct els has length "+length);
        return length;
    }


    /** 
     * 
     * SLIDING WINDOW
     * 1,1,2,3,1,4
     * always store freq, not index, use left for index
     * 
     * 1 USING (map.size()>=k) EQUAL TO DOESN'T WORK AS IT 
     * CONSIDERS LENGTHS > k.
     * 2 UPDATE LENGTH AFTER THE EXTRA ELS ARE TRIMMED, i.e. LEFT
     * REACHES CORRECT POSITION
     * 3 
     * 
     * 
     * https://leetcode.com/discuss/interview-question/332807/
     * uber-phone-screen-longest-substring-with-at-most-k-distinct-characters
     * 
     * remove till size==k and then update length
    */
    // https://www.geeksforgeeks.org/longest-subarray-not-k-distinct-elements/
    int longestSubArraytWithkEls(int[] arr, int k){
        int n = arr.length;
        int left = 0; int length = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<n; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            while(map.size()>k){
                if(map.get(arr[left]) == 1) map.remove(arr[left]);
                else map.put(arr[left], map.get(arr[left])-1);
                System.out.println("left "+arr[left]);
                left++;
                System.out.println(map);
            }
            length = Math.min(length, i-left+1);

            // length is placed here, so it checks for cases
            // when the map size is <= k
            System.out.println("length "+length);
        }
        System.out.println("longest subarray with "+k+" els has length "+length);
        return length;
    }

    /** 
     * POINTS : 
     * 1 SIMILAR TO ABOVE, LONGEST SUBARRAY
     * 2 KEEP REMOVING FROM LEFT TILL ALL OCCURENCES OF CURR CHAR ARE REMOVED
     * 3 ADD CHAR TO SET
     * 4 UPDATE LENGTH ONCE DUPLICATES ARE REMOVED
     * 
     * "dvdf"
     * 
     * hashset, remove then update length
    */
    // https://leetcode.com/problems/longest-substring-without-repeating-characters
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        int left = 0; int len = -1;
        HashSet<Character> set = new HashSet<>();
        
        for(int i =0; i<s.length(); i++){
            while(set.contains(s.charAt(i))){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(i));
            len = Math.max(len, i-left+1);
        }
        return len;
    }

    // similar
    // https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/


    
    // https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/


    ///////////////// COUNT += 

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

    /**  
     * https://leetcode.com/problems/subarray-product-less-than-k/discuss/
     * 741191/JAVA-Simple-Solution%3A-Sliding-Window
     * 
     * nums = [10, 5, 2, 6], k = 100; Output: 8
     * 
     * SIMILAR SHRINKING TECHNIQUE
     * 1 BOUNDARY CONDITION IS IMP if(k<=1) return 0;
     * 2 COUNT IS UPDATED THROUGHOUT
     * 3 WHEN PROD>K, IT'S BROUGHT UNDER K
     * 4 (i - start+1) ADDS ALL THE ELS AND 1 EXTRA FOR THE SUBARRAY 
     *   FROM start+1 TILL i AS A WHOLE
     * 
     * remove product>k in while and then update
     * 
    */
    // https://leetcode.com/problems/subarray-product-less-than-k/
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


    // https://www.geeksforgeeks.org/
    // count-of-subarrays-of-size-k-with-elements-having-even-frequencies/?ref=rp

    
    // CELEBRITY PROBLEM

    // Returns id of celebrity. Else -1
    /** 
     * if a knows b, then a isn't a celeb, and if a doesn't know b, then
     * b isn't a celeb. eliminate the non celeb; a++; b--;
     * move 2 pointers, and finally check for a
     */
	int findCelebrity(int[][] matrix) 
	{   
        int n = matrix.length;
		int a = 0; int b = n - 1; 
        
        //checking single row, a holds possible celeb no
		while (a < b) { 
			if (knows(matrix, a, b)) a++; 
			else b--; 
		} 

		// Check if a is a celebrity or not
		for (int i = 0; i < n; i++) { 
            // If 'a' knows any person or
            // any person doesn't know 'a' return -1
			if (i != a && (knows(matrix, a, i) || !knows(matrix, i, a))) 
				return -1; 
		} 
		return a; 
    } 
    
    // Returns true if a knows b, else false 
	boolean knows(int[][] matrix, int a, int b) { 
		return (matrix[a][b] == 1) ? true : false; 
	} 


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
        if (s.length() == 0) return res;

        int n = t.length();
        if (n > s.length()) return res;

        for (char c : t.toCharArray()) base[c - 'a']++;

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

    /** 
     * POINTS :
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
        int windowSize = 0;
        for(int i =0; i<n; i++) if(arr[i]==1) windowSize++; // no of 1s

        //first window
        int zero = 0; 
        for(int i = 0; i<windowSize; i++){
            if(arr[i]==0) zero++;
        }
        int min = zero;
        System.out.println("min "+min);


        // subsequent windows, start from windowSize and outgoing el = j-windowSize
        for(int j = windowSize; j<n; j++){
            if(arr[j-windowSize] == 0) zero--;
            if(arr[j] == 0) zero++;
            min = Math.min(min, zero);
        }
        System.out.println("min no of swaps " + min);
        return min;
    }


    // https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/


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
        // slide.smallestSubsequence(smallestSub);

        int[] arr = {1, 2, 4, 4};
        // {1, 2, 1, 3, 4, 2, 3};
        int k = 2;//4;
        // slide.distinctElsinWindow(arr, k);

        int N = 7, K = 4, A[] = {1,2,1,3,4,2,3};
        slide.countDistinct(A, A.length, K);

        int[] longestK = new int[]
        {6, 5, 1, 2, 3, 2, 1, 4, 5};
        // {1,1,2,3,1,4};
        // slide.longestSubArraytWithkEls(longestK, 3);

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