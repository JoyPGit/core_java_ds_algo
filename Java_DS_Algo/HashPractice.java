package Java_DS_Algo;
import java.util.*;


//how hashmaps work https://www.youtube.com/watch?v=c3RVW3KGIIE

/** 
 * ** min window
 * 
 * AP HASH, ITINERARY, WORD SUBSETS
 * 
 * SMALLEST SUBSEQUECE, MNIN WINDOW SUBSTRING
 * 
 * 0s and 1s, prefix sum
 * rank teams, word subsets
 * 
 * LONGEST SUBARRAY OF 0S AND 1S
 * LONGEST AP
 * RANK TEAMS
 * WORD SUBSETS
 * PATH SUM 3
 * NO OF SUBARRAYS HAVING SUM K (PREFIX SUM)
 * FIND ITINERARY
 * WORD SUBSETS
 * 
 * SLIDING WINDOW -> WHENEVER K DISTINCT
 * SMALLEST
*/

/** hash stores key value pairs by adding an extra hash value for 
 * the key which is used to compute the index
 * key->hash->value
 * the value returned with get is the value of the key
 * 
 * HASHSET FOR SINGLE VALUE., add method , HASMAP FOR KEY VALUE PAIR 
 * put method
 * */


 /** important things
 * 1. diff b/w hashmap and hashset
 * 2. how to iterate over a hashmap line 58 similar to tree
 * IMP HOW TO CONVERT HASMAP TO ARRAY  
 * Object[] res = map.values().toArray();
 * Arrays.sort(res, Collections.reverseOrder());

    https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/

        for (CustomClassForSwap element : tree.queueSwapLeafNew) {
            System.out.println(element.node.key);
        }

   3 https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
   hashmap iterator     
   */

   /** 
    * TECHNIQUES 
    * 1 USE A CUSTOM CLASS FOR STORING VALUE PER KEY
    * 2 USE FOR AND THEN WHILE FOR SHRINKING
    * 3 WHENEVER CONTIGUOUS SUBARRAY WITH SUM = K IS REQD, USE HASHMAP
    *
    */
class HashPractice{

    int smallestElementRepeatedKTimes(int[] arr, int k){
        int n = arr.length; int min = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i =0; i<n; i++) map.put(arr[i], map.getOrDefault(arr[i], 0)+1);

        for(HashMap.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == k) min = Math.min(min, entry.getKey());
        }
        return min;
    }


    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[2];
        // Arrays.sort(nums);
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<n; i++){
            if(map.containsKey(target - nums[i])) {
                res[0] = i; res[1] = map.get(target - nums[i]);
                break;
            }     
            else map.put(nums[i], i);
        }
        return res;
    }


    /** POINTS : 
     * 1 CREATE AN ARRAY TO SERVE AS A PRIMARY MAP(OR DICTIONARY)
     * WHICH WILL STORE THE COMMON ELEMENTS
     * 2 CREATE A NEW ARRAY FOR EACH SUBSEQUENT STRING AND STORE THE FREQ
     * 3 UPDATE THE PRIMARY ARAY TO STORE COMMON OF BOTH PRIMARY ABND CURR
     * 
     * SIMILAR TO INTERSECTION OF 2 ARRAYS
     */
    // https://leetcode.com/problems/find-common-characters/
    public List<String> commonChars(String[] A) {
        int[] primary = new int[26];
        
        for(char c : (A[0]).toCharArray()) primary[c-'a']++;
        
        for(int i =1; i<A.length; i++){
            int[] curr = new int[26];
            for(char c : (A[i]).toCharArray()){
                curr[c-'a']++;
            }
            update(primary, curr);
        }
        
        List<String> res = new ArrayList<>();
        for(int i =0; i<primary.length; i++){
            if(primary[i]!=0) {
                int freq = primary[i];
                for(int j =0; j<freq; j++) res.add(""+(char)(i+'a'));
            }
        }
        return res;
    }

    void update(int[] a, int[] b){
        for(int i =0; i<a.length; i++) a[i] = Math.min(a[i], b[i]);
    }


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

    /** POINTS :
     * 1 MAINTAIN A HASHMAP
     * 2 WHEN ITERATING OVER SECOND MAP, IF PRESENT, ADD TO RESULT
     * 3 MOST IMP REDUCE THE COUNT, AND IF FREQ = 0, REMOVE FROM MAP
     * 
     * AN ELEMENT OF ARR2 IF NOT PRESENT IN MAP, THAT MEANS, FIRST ARRAY 
     * DOESN'T HAVE THAT MANY OCCURENCES OF THAT EL.
     * 
     * 2,3,3 {2 =1, 3 =2}
     * 1,2,2,3 
     * 2 prresent, remove {2=0}
     * for second 2, no entry hence not added to result
     * 
     *   
     */
    // https://leetcode.com/problems/intersection-of-two-arrays-ii
    public int[] intersect(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> res= new ArrayList<>();
        
        for(int i : arr1) map.put(i, map.getOrDefault(i, 0)+1);
        
        for(int i :arr2){
            if(map.containsKey(i)){
                res.add(i); 
                map.put(i, map.get(i)-1);
                if(map.get(i)==0) map.remove(i);
            }
        }
        
        int[] result = new int[res.size()];
        for(int i =0; i<res.size(); i++) result[i] = res.get(i);
        return result;
    }

    
    // update last in each iteration
    // https://www.geeksforgeeks.org/maximum-difference-first-last-indexes-element-array/
    class FirstLast{
        int first; int second;

        FirstLast(int f, int s){
            this.first = f;
            this.second = s;
        }
    }

    int MaxDiffFirstAndLast(int[] arr){
        int maxDiff = 0;
        HashMap<Integer, FirstLast> list = new HashMap<>();
        for(int i =0; i<arr.length; i++){
            if(list.containsKey(arr[i])){
                int first = list.get(arr[i]).first;
                list.put(arr[i], new FirstLast(first, i));
            }else list.put(arr[i], new FirstLast(i, i));
        }

        for (Map.Entry<Integer, FirstLast> entry : list.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue().second - entry.getValue().first;
            maxDiff = Math.max(maxDiff, value);
            System.out.println("key "+key+" value " +value);
        }
        System.out.println("maxDiff is "+maxDiff);
        return maxDiff;
    }

    // https://www.geeksforgeeks.org/print-maximum-shortest-distance/
    // https://www.geeksforgeeks.org/common-elements-in-all-rows-of-a-given-matrix/
    // https://www.geeksforgeeks.org/find-pairs-given-sum-elements-pair-different-rows/
    
    
    // https://leetcode.com/problems/subarrays-with-k-different-integers/solution/
    // https://www.geeksforgeeks.org/find-four-elements-a-b-c-and-d-in-an-array-such-that-ab-cd/

    // https://www.geeksforgeeks.org/smallest-element-repeated-exactly-k-times-not-limited-small-range/
   
    // https://www.geeksforgeeks.org/find-pair-with-greatest-product-in-array/
    // int pairGreatestProduct(int[] arr){
        
    // }
    

    ///////////////////////// PREFIX-SUM
    /** 
     * 
     * ALWAYS THINK CUMULATIVE SUM, IF THERE EXISTS SUM-K
     * TARGET = 8, SUM = 18 AND 10 EXISTS, SO THERE IS A SUBARRAY WHOSE
     * SUM IS 10 AND ANOTHER WHOSE SUM IS 18, 
     * SO SELECT FROM AFTER 10 TILL 18 TO GET 8
     * 
     * POINTS : 
     * MOST IMP WE STORE CUMULATIVE SUM AS IT HELPS US SELECT CONTIGUOUS SUBARRAYS
     * 1 STORE SUM IN MAP ALONG WITH FREQ
     * 2 NO NEED TO CHECK FOR INDIVIDUAL ELS
     * 3 NO NEED TO KEEP TRACK OF 0, THE FREQ COUNT OF 0 WILL DO THE TRICK
     * 4 MAKE AN ENTRY IF DOES NOT EXIST
     * 
     * [1,-1,2,1] ; k = 1
     * put sum not sum-k
     * el----sum----sum-k-------count---------map{0=1}
     * 1 -----1-------0----------1(0)--------{0=1, 1=1}
     * -1-----0------(-1)--------1+1(0)------{0=2, 1=1}
     * 2 -----3-------2----------2-----------{0,2, 1=1, 3=1}
     * 1 -----4-------3----------2+1(3)------{0,2, 1=2}
     * 
     * count = 3 [1],[-1,2],[1]
     * 
     * sum + x - k = sum => x=k
     * Existence of (sum-k) means from a previos index till this index
     * there is k sum subarray. 
     * 
     * STEPS :
     * 1 map.put(0,1)
     * 2 sum+=i;
     * 3 count += map.getOrDefault(sum-target)
     * 4 add current sum
     */
    // https://leetcode.com/problems/subarray-sum-equals-k
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        if(n==0) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1); // tricky, don't forget

        int sum = 0; int count = 0;
        
        for(int i : nums){
            sum+=i;
            count+=map.getOrDefault(sum-k,0);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;
    }


    /**  
     * LONGEST SUBARRAY OF 0S AND 1S
     * 
     * SIMILAR TO PREFIX SUM
     * 1 MARK ALL 0S AS -1
     * 2 STORE CUMULATIVE SUM AND INDEXES
     * 3 IF SUM EXISTS, UPDATE LEN
     * ELSE MAKE AN ENTRY
     * 
     * len =  i - map.get(sum) // map stores index
     * 
    */ 
    // https://leetcode.com/problems/contiguous-array
    public int findMaxLength0s1s(int[] nums) {
        int n = nums.length; 
        int sum = 0; int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i =0; i <n; i++){
            if(nums[i] == 0) nums[i] =-1;
        }
        
        // cumulative sum ,index
        map.put(0,-1);
        
        for(int i = 0; i<n; i++){
            sum+=nums[i];
            if(map.containsKey(sum)) len = Math.max(len, i- map.get(sum));
            else map.put(sum, i);
        }
        
        return len;
    }

    /** POINTS :
     * 1 STORE REMAINDER INSTEAD OF SUM
     * 2 NO NEED TO CHECK IF INDIVIDUAL EL IS DIV BY K
     * COUNT+=MAP.GET(REM) DOES THE TRICK
     * 3 IF REM<0 REM+=K TO MAKE IT GREATER THAN 0
     * 
     * imp : rem<0 rem+=k
     */
    // https://leetcode.com/problems/subarray-sums-divisible-by-k/
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        if(n==0) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int sum = 0; int rem = 0; int count = 0; 
        map.put(rem,1);
        
        for(int i : nums){
            sum+=i; rem = sum%k; // 1
            if(rem<0) rem+=k; // 2
            if(map.containsKey(rem)) count+=map.get(rem);
            // System.out.println("i "+i+" "+count);
            map.put(rem, map.getOrDefault(rem, 0)+1);
        }
        return count;
    }
    
    // https://www.geeksforgeeks.org/longest-subarray-sum-divisible-k/
    int longestSubArrayDivByK(int[] arr, int k){
        int max =0; int sum =0;
        HashMap<Integer, Integer> list = new HashMap<>();

        for(int i =0; i<arr.length; i++){
            sum+= arr[i];
            list.put(sum%k, 0);
        }

        return max;
    }

    // SMALLEST LEXICOGRAPHICALLY
     /** 
     * 
     * POINTS : 
     * 1 FOR ANY INCOMING CHAR, IF IT EXISTS IN RESULT STRING, CONTINUE
     * Why? because the char is alrady at its lowest lexicographic position.
     * 
     * 2 IF LAST CHAR IS LEXICOGRAPHICALLY GREATER AND IT'S FREQ IS >0, 
     * IT MEANS THAT CHAR CAN BE USED LATER, SO IT'S SAFE TO REMOVE IT.
     * REMEMBER TO REMOVE IT FROM VISITED SET AS WELL
     * 
     * STEPS :
     * 1 CHECK IF INCOMING CHAR IS IN RESULT STRING
     * 2 CHECK IS THE INCOMING CHAR IS GREATER THAN THE LAST CHAR IN RESULT
     * AND IF IT THE LAST CHAR HAS ANY OCCURENCES LEFT (FREQ >= 1)
     * 3 AFTER TRIMMING USING WHILE LOOP ADD THE CHAR AT END
     * 
     * */
    /**
     * imp how to handle bb? -> if(res.contains) continue;
     * decrement freq of incoming char as it cn't be used further
     * if exists continue
     * check for freq >= 1
     * add after removal of larger with freq>=1
     * 
     * FREQ-- FOR ALL, IF EXISTS CONTINUE; REST SAME 
     */ 
    // https://leetcode.com/problems/smallest-subsequence-of-distinct-characters
    public String smallestSubsequence(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        String res = "";
        
        for(char c : text.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        for(char c : text.toCharArray()){
            map.put(c, map.get(c)-1);

            if(res.contains(""+c)) continue; // 1
            while(res.length()>0 // 2
                  // last char must be graeter and has future occurences
                  && res.charAt(res.length()-1) > c // 3
                  // no need to keep an index as substrng removes the last char
                  && map.get(res.charAt(res.length()-1)) > 0 ){ // 4 
                    res = res.substring(0, res.length()-1);
            }
            res+=c; // 5
        }
        return res;
    }


    public int longestAPHash(int[] A) {
        HashMap<Integer, Integer> hash[] = new HashMap[A.length];
        for(int i = 0; i < A.length; i++)
            hash[i] = new HashMap<Integer, Integer>();
        int max = 0;
        for(int i = 1; i < A.length; i++)
            for(int j = 0; j <i; j++){
                int diff = A[i] - A[j];
                int counttillnow = hash[j].getOrDefault(diff, 0);
                System.out.println("diff "+diff+ " countTillNow "+counttillnow);

                //trick to ensure hash[i] get doesn't return null; add condition in and
                if(hash[i].containsKey(diff) && hash[i].get(diff) > counttillnow) continue;
                else {
                    hash[i].put(diff, counttillnow + 1);
                    max = Math.max(max, counttillnow + 1);
                }
            }
        
        System.out.print("max AP using Hash "); System.out.println(max+1);
        return max + 1;
    }

    
    // https://www.geeksforgeeks.org/count-number-triplets-product-equal-given-number/
    // https://www.geeksforgeeks.org/find-pair-with-greatest-product-in-array/

    // https://leetcode.com/problems/top-k-frequent-words/
    /** if same freq check for lexicographically smaller x.compareTo(y) */
    class KFreq {
        int freq;
        String str;

        KFreq(int f, String s) {
            this.freq = f;
            this.str = s;
        }
    }

    public ArrayList<String> topKFrequent(String[] words, int k) {
        int n = words.length;
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<KFreq> heap = new PriorityQueue<KFreq>((x, y) -> {
            if (x.freq == y.freq)
                return x.str.compareTo(y.str);
            // y.str.charAt(0) - x.str.charAt(0);
            return y.freq - x.freq;
        });

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String s = e.getKey();
            int freq = e.getValue();
            heap.add(new KFreq(freq, s));
        }
        int counter = 0;

        while (heap.size() != 0 && counter<k) {
            System.out.println((heap.remove().str));
            counter++;
        }

        return res;
    }

    /** 
     * POINTS : 
     * WE CREATE INDIVUDUAL SCORECARD FOR EACH PLAYER, FOR THIS WE MAP
     * CHARACTER TO AN INT ARRAY
     * THE SCORE CARD IS OF SIZE  = THE NO OF PLAYERS
     * 
     * IMP : 
     * ABC -> 3 PLAYERS, SO SCORECARD FOR EACH PLAYER WILL HAVE 3 PLACES
     * AND WE WILL INCREMENT THE RANKS BY 1, USING INDEX OF CHAR IN 
     * CURRENT STRING.
     * 
     * A [ , , ]
     * B []
     * C []
     * 
     * ABC - > A[1,0,0]; B[0,1,0]; C[0,0,1]
     * ACB - > A[2,0,0]; B[0,1,1]; C[0,1,1]
     * ABC - > A[3,0,0]; B[0,2,1]; C[0,1,2]
     * ACB - > A[4,0,0]; B[0,2,2]; C[0,2,2]
     * BCA - > A[4,0,1]; B[1,2,2]; C[0,3,2]
     * 
     * 
     * SORT BY RANKS, IF RANKS ARE SAME LEXICOGRAPHICALLY SMALLEST 
     * 
     * WHY DIDN'T WE ADD ALL POINTS USING (n-i) BECAUSE HERE RANK MATTERS, 
     * EVEN IF E SCORES MORE THAN B WITH 2 3rd places, 
     * E IS PREFERRED BECAUSE OF BETTER RANK (2)
     * 
     * 
     * 1 CREATE AN ARRAY OF SIZE votes[0], THIS IS MAPPED TO CHAR
     * 2 INCREMENT RANK map.get(s.charAt(i))[i]++
     * 3 COMPARATOR IN Q IS IMP
     * 4 ADD TO Q AND REMOVE TO RESULT
     * 
     * the tricky thing is for each team we create a scoreboard of size
     * n, (n = no of positions, not the length of array).
     * 
     * MAP IS UED TO CREATE THE SCOREBOARD OF SIZE N (WORD SIZE)
    */
    // https://leetcode.com/problems/rank-teams-by-votes/
    public String rankTeams(String[] votes) {
        HashMap<Character, int[]> map = new HashMap<>();
        
        int n = votes[0].length();
        
        for(String s : votes){
            for(int i =0; i<s.length(); i++){
                int[] curr = map.getOrDefault(s.charAt(i), new int[n]);
                curr[i]++; // 1
                map.put(s.charAt(i), curr);
            }
        }
        
        PriorityQueue<Character> q = new PriorityQueue<>((x,y)->{
            int[] a = map.get(x); int[] b = map.get(y);
            for(int i =0; i<a.length; i++){
                if(a[i]!=b[i]) return b[i]-a[i]; // 2 max heap
            }
            return x-y;
        });
        
        
        for(Map.Entry<Character, int[]> entry : map.entrySet()){
            q.add(entry.getKey());
        }
        
        String result = "";
        while(q.size()!=0) result+=(q.remove());
            
        return result;
    }

    
    /** 
     * 
     * A = ["amazon","apple","facebook","google","leetcode"], 
     * B = ["lo","eo"]
     * Output: ["google","leetcode"]
     *
     * IDEA IS TO STORE ALL FREQS OF ALL ELS OF B AND THEN COMPARE WITH
     * FREQ OF EACH WORD OF A.
     * IF FREQ OF WORD OF A IS LESSER, MEANS IF WORD DOESN'T
     * HAVE ENOUGH CHARS, IT CAN'T BE ADDED.
     * 
     * leetcode has 3 e and 1 o.
     * if we don't maintain max of all freqs of B and simply add to existing,
     * o will have freq of 2 in maxFreq.
     * THIS ENSURES THAT EACH PATTERN'S CHAR FREQ IS NOT PILED UP, ELSE WE WILL
     * COMPARE TWO o with leetcode, but the patterns have at max one o (lo, eo).
     *  
     * POINTS : 
     * 1 CREATE 3 ARRAYS
     * ONE BASE TO KEEP TRACK OF THE MAX FREQS OF ALL PATTERNS OF B
     * ONE TEMP IS RETURNED WHICH CONTAINS FREQ OF CURR EL OF B
     * AND ANOTHER TEMP TO KEEP TRACK OF FREQ OF WORD OF A
     * 
     * 2 A NEW ARRAY CURR IS RETURNED FOR EACH WORD IN A
     * 3 IF FREQ OF BASE  > CURR, THEN BREAK AND DON'T ADD.
     * WORD DOESN'T HAVE ENOUGH CHARS, IT CAN'T BE ADDED.
     * 
     * 4 FLAG IS USED TO KEEP TRACK. 
     * 
     * // [leetcode"]  ["lo","eo"] 
     * 
     * IMP : 
     * create an array for each word in both A and B
     * for B, we keep max of each array in base
     * and if max freq of any el is lesser than A's curr, don't add.
     * 
     * TRICK is to merge all separate arrays of B into one using max
     * and then compare.
    */
    // https://leetcode.com/problems/word-subsets/
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] base = new int[26]; // 1

        for (String s : B) {
            int[] temp = getFreqArr(s);
            for (int i = 0; i < 26; i++) {
                base[i] = Math.max(base[i], temp[i]); // 2
            }
        }

        List<String> result = new ArrayList<>();
        for (String str : A) {
            boolean flag = true;
            int[] curr = getFreqArr(str);

            for (int i = 0; i < 26; i++) {
                if (base[i] > curr[i]) { // 3
                    flag = false; 
                    break;
                }
            }
            if (flag) result.add(str);
        }
        return result;
    }

    public int[] getFreqArr(String s) {
        int[] result = new int[26];
        for (char c : s.toCharArray()) {
            result[c - 'a']++;
        }
        return result;
    }

    // https://leetcode.com/problems/longest-consecutive-sequence/
    // discuss/41290/Simple-Java-Solution-Using-HashMap


    // https://www.youtube.com/watch?v=ofMqFAFVcKY
    // https://leetcode.com/problems/path-sum-iii/submissions/
    int count = 0;
    int target = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    
    public int pathSum(TreeNode root, int sum) {
        target = sum;
        map.put(0,1);
        helper(root, 0);
        return count;
    }
    
    void helper(TreeNode root, int sum){
        if(root == null) return;
        sum+=root.val;
        count+=map.getOrDefault(sum-target, 0);
        map.put(sum, map.getOrDefault(sum, 0)+1);
        helper(root.left, sum);
        helper(root.right, sum);
        map.put(sum, map.getOrDefault(sum, 0)-1);
        // this is for leaf nodes, removing left leaf for right path
    }
    // https://leetcode.com/problems/path-sum-iii/discuss/
    // 895204/Java-DFS-2-ms-faster-than-100.00-39-MB-less-than-12.94


    // https://www.geeksforgeeks.org/find-pair-with-greatest-product-in-array/

    // https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

    // https://leetcode.com/discuss/interview-question/844979/amazon-online-assessment-question-2
    /** USE DFS SIMILAR TO FRIEND CIRCLE LEETCODE */

    // https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
    // https://leetcode.com/problems/longest-consecutive-sequence/
    
    public static void main(String[] args) {
        HashPractice h = new HashPractice();
        HashMap<Integer, String> map = new HashMap<>();

        // map.put("12",1);
        // map.put("12",13);
        // map.put("123",1);
        // map.put("124",15);
        // map.put("12",14);
        // map.put("12",5);

        // System.out.println(map.get("12"));

        String arr[] = {"swagat","adarsh","vasil","shivani","hanish","manendra"};

        // for(int i=0; i<arr.length; i++){
        //     map.put(i,arr[i]);
        // }

        // System.out.println(map.get(0));
        for(int i =0; i<arr.length; i++){
            // System.out.println(map.get(i));//the values of the map
        }

        //instance 
        // HashMap<Integer, Integer> h = new Hashmap<Integer, Integer>();


        int[] arr1 = {3,4,5,6};
        int[] arr2 = {1,2};
        // boolean val = h.CheckSubsetArray(arr1, arr2);
        // if(val){
            // System.out.println("subset true");
        // }

        int[] arr3 = {2,2,3,4,5,6,6,6,7,7,8,8,8};
        // System.out.println(h.minSubsets(arr3));
        // h.smallestElementRepeatedKTimes(arr3, 3);

        int[] maxDiffArr = {2, 1, 3, 4, 2, 1, 5, 1, 7};
        // h.MaxDiffFirstAndLast(maxDiffArr);

        int subarrKdis[] = { 1, 1, 2, 2, 3, 3, 4, 5};
        // { 1, 2, 2, 3};
        int k = 3;
        // 2;
        // h.smallestSubArrayKDistinct(subarrKdis, k);

        int[] apSeq = {24,13,1,100,0,94,3,0,3};
        //{3,6,9,10};
            // {44,46,22,68,45,66,43,9,37,30,50,67,32,47,
            // 11,15,4,6,20,64,54,61,63,23,3,12,51,16,57,14,
            // 55,17,18,25,19,28,56,29,39,52,8,1,21,70};
        
        // {44,46,22,68,45,66,43,9,37,30,50,67,32,47,44,11,15,4,11,
        //  6,20,64,54,54,61,63,23,43,3,12,51,61,16,57,14,12,55,17,18,
        //  25,19,28,45,56,29,39,52,8,1,21,17,21,23,70,51,61,21,52,25,28};

        // h.longestArithSeqLength(apSeq);
        // h.longestAPHash(apSeq);

        // List<List<String>> list = ["MUC,LHR"]["JFK,MUC"]["SFO,SJC"]["LHR,SFO"];

        int A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
        int A2[] = {2, 1, 8, 3};
        // ans = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}    
        // h.relativeSortArray(A1, A2);

    }
}