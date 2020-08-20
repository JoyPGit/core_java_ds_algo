import java.util.*;

//how hashmaps work https://www.youtube.com/watch?v=c3RVW3KGIIE

/** hash stores key value pairs by adding an extra hash value for the key which is used to compute the index
 * key->hash->value
 * the value returned with get is the value of the key
 * 
 * HASHSET FOR SINGLE VALUE., add method , HASMAP FOR KEY VALUE PAIR put method
 * */


 /** important things
  * 1. diff b/w hashmap and hashset
  * 2. how to iterate over a hashmap line 58 similar to tree  

    https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/

        for (CustomClassForSwap element : tree.queueSwapLeafNew) {
            System.out.println(element.node.key);
        }

   3 https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
   hashmap iterator     
   */

   /** 
    * TECHNIQUES
    1 USE A CUSTOM CLASS FOR STORING VALUE PER KEY
    2 USE FOR AND THEN WHILE FOR SHRINKING
    */
public class HashPractice{

    void findSubArrayWithSumZero(int[] arr){
        int sum = 0;

        for(int i =0; i<arr.length; i++){
            sum+= arr[i];

        }
    }

    boolean CheckSubsetArray(int[] arr1, int[] arr2){
        HashSet<Integer> hset= new HashSet<Integer>();
        // int[] longer = arr1.length>arr2.length?arr1:arr2;

        for(int i =0; i<arr1.length; i++){
            hset.add(arr1[i]);
        }
        System.out.println("hset "+hset);
        for (int i=0; i<arr2.length; i++){
            if(!hset.contains(arr2[i])){
                return false;
            }
        }
        return true;
    }


    int minSubsets(int[] arr){
        HashMap<Integer, Integer> minsubmap = new HashMap<Integer, Integer>();

        for(int i =0; i<arr.length; i++){
            minsubmap.put(arr[i],minsubmap.get(arr[i]) == null?1:minsubmap.get(arr[i])+1);
        }


        Iterator hmIterator = minsubmap.entrySet().iterator(); 

        int maxFreq = 0;
        while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            int frequency = (int)mapElement.getValue();
            if(frequency > maxFreq) maxFreq = frequency; 
            System.out.println(mapElement.getKey() + " : " + frequency); 
        } 

        // for (HashMap.Entry mapElement : minsubmap.entrySet()) { 
        //     String key = (String)mapElement.getKey();   
        //     int value = (int)mapElement.getValue(); 
  
        //     System.out.println(key + " : " + value); 
        // } 
        minsubmap.forEach((k, v) -> System.out.println(k + " : frquency ->" + (v)));

        return maxFreq;
    }


    int SmallestElementRepeatedKTimes(int[] arr, int times){
        HashMap<Integer, Integer> repeatkmap = new HashMap<Integer, Integer>();

        for(int i =0; i<arr.length; i++){
            repeatkmap.put(arr[i], repeatkmap.get(arr[i])==null?1:repeatkmap.get(arr[i])+1);
        }

        Iterator hmIterator = repeatkmap.entrySet().iterator(); 

        int maxFreq = 0; int smallest =100000000;
        while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            int frequency = (int)mapElement.getValue();
            int key = (int)mapElement.getKey();
            if(frequency == times && key <smallest ) {
                maxFreq = frequency; 
                smallest = key;
            }
            System.out.println(mapElement.getKey() + " : " + frequency); 
        } 
        return smallest;
        // int[] smallest = {10000000}; 
        // repeatkmap.forEach((k,v)->{
        //     System.out.println("v "+v);
        //     if(times == v && smallest[0] < k){
        //        smallest[0] = k;
        //        System.out.println("samller "+k);
        //     }
        // });
        // System.out.println("smallest "+smallest[0]);
        // return smallest[0];
    }

    //IMP QUESTIONS
    
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
    
    
    // https://www.geeksforgeeks.org/smallest-subarray-k-distinct-numbers/
    /** it is not sliding window prob, rather a counter is kept when map size 
     * equals k.
     * USES SHRINKING TECHNIQUE THOUGH, FOR LOOP THEN SHRINK WITH WHILE
     * 
     * the technique is to hold the rightmost index of every el
     * and if map size>k, remove the min el and find length with next min
     * 
     * how to shrink?
     * once the map size is >k, 
     * while loop
     * start with left
     * remove left, then left = get new min
     * compare len
     * 
     * 
     * holding left ptr and updating it by checking if the el matches left
     * helps in removing the min element
    // arr[] = { 1, 1, 2, 2, 3, 3, 4, 5} ,    k = 3  o/p = [5 7]
    // arr[] = { 1, 2, 2, 3} , k = 2 o/p = [0,1]

     */
    void smallestSubArrayKDistinct(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int minLen = Integer.MAX_VALUE; int left = 0;

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], i);
                System.out.println(map);
            } else {
                map.put(arr[i], i);
            }
            if(map.size()==k) System.out.println("start " + left + " end " + i);
            // left starts with 0, then when shrinking starts, 
            // it is updated to next min index
            while(map.size() > k) {
                map.remove(arr[left]);
                left = getMin(map);
                int end = i;
                if((end- left+1) < minLen){
                    minLen = end- left+1;
                    System.out.println("start " + left + " end " + i);
                }
            }
        }
        System.out.println("min length : "+minLen+" start : "+left);
    }

    // int shrink(){}
    int getMin(HashMap<Integer, Integer>map){
        int min =Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            min = Math.min(min, value);
        }
        return min;
    }

    // int getMax(HashMap<Integer, Integer>map){
    //     int max =Integer.MIN_VALUE;
    //     for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //         Integer value = entry.getValue();
    //         max = Math.max(max, value);
    //     }
    //     return max;
    // }

    // https://www.geeksforgeeks.org/design-a-data-structure-that-supports-
    // insert-delete-search-and-getrandom-in-constant-time/

    // https://www.geeksforgeeks.org/find-four-elements-a-b-c-and-d-in-an-array-such-that-ab-cd/

    // https://www.geeksforgeeks.org/smallest-element-repeated-exactly-k-times-not-limited-small-range/

    //IMP
    // https://www.geeksforgeeks.org/print-triplets-sorted-array-form-ap/
    // https://www.geeksforgeeks.org/count-number-triplets-product-equal-given-number/
        
   
    //RABIN KARP ALGO

    // https://www.geeksforgeeks.org/find-pair-with-greatest-product-in-array/
    // int pairGreatestProduct(int[] arr){
        
    // }

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
    
    //https://www.geeksforgeeks.org/longest-arithmetic-progression-dp-35/

    class APHolder{
        int start, end, count;
        
        APHolder(int a, int b, int c){
            this.start = a;
            this.end = b;
            this.count = c;
        }
    }
    
    

    // IMP check this with largest subarray with equal no of 0s and 1s
    // https://www.geeksforgeeks.org/substring-equal-number-0-1-2/
    // https://www.geeksforgeeks.org/length-of-the-longest-substring-with-equal-1s-and-0s/
    // Java Code for finding the length of
    // the longest balanced substring
    int longestSubarrayEqual0sAnd1s() {
        String str = "101001000";

        // Create a map to store differences
        // between counts of 1s and 0s.
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        // Initially difference is 0;
        map.put(0, -1);
        int res = 0;
        int count_0 = 0, count_1 = 0;
        for (int i = 0; i < str.length(); i++) {
            // Keep track of count of 0s and 1s
            if (str.charAt(i) == '0')
                count_0++;
            else
                count_1++;

            // If difference between current counts
            // already exists, then substring between
            // previous and current index has same
            // no. of 0s and 1s. Update result if this
            // substring is more than current result.

            if (map.containsKey(count_1 - count_0))
                res = Math.max(res, (i - map.get(count_1 - count_0)));

            // If the current difference is seen first time.
            else
                map.put(count_1 - count_0, i);

        }

        System.out.println("Length of longest balanced sub string = " + res);
        return res;
    }

    // class ItineraryHolder{
    //     String to;
    //     int rank;
    //     ItineraryHolder(String s, int r){
    //         this.to = s;
    //         this.rank = r;
    //     }
    // }

    // public List<String> findItinerary(List<List<String>> tickets) {
    //     ArrayList<String> result = new ArrayList<>();
    //     HashMap<String, ItineraryHolder> map = new HashMap<>();

    //     for(int i =0; i<tickets.size(); i++){
    //         // if(map.containsKey(tickets.get(i)))
    //         System.out.println(tickets.get(i));
    //     }
    //     return result;
    // }

    //didn't work, don't know why
    public int longestArithSeqLength(int[] arr) {
        int maxLen = 0;
        HashMap<Integer, APHolder> map = new HashMap<>();

        for(int i =0; i<arr.length-1; i++){
            for(int j = i+1; j<arr.length; j++){
                int diff = arr[i] - arr[j];
                if(map.containsKey(diff)){
                    APHolder curr = map.get(diff);
                    if(curr.end == arr[i]){
                        map.put(diff, new APHolder(curr.start, arr[j], curr.count+1));
                    } else map.put(diff, new APHolder(arr[i], arr[j], 2));
                }
                else map.put(diff, new APHolder(arr[i], arr[j], 2));
            }
        }
        
        for (Map.Entry<Integer, APHolder> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue().count;
            maxLen = Math.max(maxLen, value);
            System.out.println("diff "+key+" value " +value);
        }
        System.out.println("maxLen is "+maxLen);
        return maxLen;
    }

    //this works
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

    // https://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/
    // sort 'a' accdng to 'b'
    // similar to sort by frequency

    /** elements are NOT stored in hashmap in the order they 
     * are inserted, sort the uncommon elements and add
     * 
     * add elements of a to map, 
     * add els of b in result list 'count' number of times, remove it from map
     * so that it's not added at last when adding uncommon els
     * add remaining from map
    */
    // AMAZON
    void relativeSorting(int[] a, int[] b){
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i =0; i<a.length; i++){
            if(map.containsKey(a[i])){
                map.put(a[i], map.get(a[i])+1);
            } else 
            map.put(a[i], 1);
        }

        for(int i =0; i<b.length; i++){
            int count = map.get(b[i]);
            for(int j =0; j<count; j++){
                res.add(b[i]);
            }
            map.remove(b[i]);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            res.add(key);
        }

        System.out.println(res);
    }

    
    //https://www.geeksforgeeks.org/find-pair-with-greatest-product-in-array/
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

        int[] arr3 = {2,2,3,4,5,6,6,6,6,7,7,8,8,8};
        // System.out.println(h.minSubsets(arr3));


        // System.out.println("smallest k times is "+h.SmallestElementRepeatedKTimes(arr3, 2));
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
        h.relativeSorting(A1, A2);
        // h.relativeSorting11Aug(A1, A2);

    }
}