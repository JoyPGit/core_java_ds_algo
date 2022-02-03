package Java_DS_Algo;
import java.util.*;
// import utilCustom.Utility;
public class Bin_Search {
    
    /**
     * find min : compare with hi, return lo
     * search in matrix
     * capacity to ship
     * floor, ceil, peak,
     * leaderboard
     * high = mid -1;
     * only in 2nd template high = mid
     * longest-increasing-subsequence
     *
     */
    Bin_Search(){}

    int binarySearch(int[] arr, int key){
        int low = 0; int high = arr.length-1;
        while(low <= high){
            int mid = low+ (high - low)/2;
            if(arr[mid] == key) return mid;
            if(arr[mid] < key) low = mid+1;
            else high = mid-1;
        }
        return -1;
    }

    /** 
     * QUES : 
     * 1 DESC ORDER SORTED ARRAY 
     * (OPPOSITE CONDN START = MID+1 IF ARR[MID]>KEY)
     * 
     * 2 ARRAY WHOSE ORDER IS UNKNOWN (ASC OR DESC) 
     * (CHECK SIZE, IF 1 RETURN)
     * (ELSE CHECK ORDER USING ANY 2 INDEXES)
     * 
     * 3 FIND FLOOR IN SORTED ARRAY
     * (IF NEXT GREATER ELEMENT IS NOT PRESENT, COMPLEXITY INCREASES)
     * 
     * SIMILAR : SEARCH INSERT POSITION
     * 
     * 4 FIND FIRST AND LAST OCCURENCES OF AN EL
     * (USE ACCEPTING BINARY SEARCH HAVING RESULT FLAG)
     * 
     * 5 COUNT OF AN EL IN A SORTED ARRAY
     * (SUBTRACT LAST AND FIRST, SEE QUES 4)
     * 
     * 6 FIND MIN IN ROTATED SORTED ARRAY
     * (5 STEP BINARY SEARCH)
     * 
     * 7 SEARCH FOR AN EL IN ROTATED SORTED ARRAY
     * (FIRST USE QUES 6, FIND MIN INDEX, THEN RUN 2 SEARCHES
     * BS(0, INDEX-1) & BS(INDEX, N-1))
     * 
     * 8 SEARCH IN A NEARLY SORTED ARRAY
     * (MAX DIFF i-1, i or i-1,
     * USE CHECKS, COMPARE WITH ALL THREE VALUES,
     * END = MID-2 OR MID+2
     * FOR BOUNDARY CONDNS, MID-1>=0 && MID+1<=n-1)
     * 
     * 
     * 9 FIND PEAK IN A BITONIC ARRAY
     * 
     * 10 SEARCH AN EL IN A BITONIC ARRAY
     * (USE ASCENDING BS FOR FIRST HALF(O, PEAK) AND DESC FOR SECOND)
     * 
     * 11 FIND IN INFINITE SORTED ARRAY
     * https://www.youtube.com/watch?v=FzvK5uuaki8&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=13
     * 11 FIRST MISSING
     * 
     * 12 FIND DUPLICATE
     * 
     * 13 FRST REPEATING EL
     * 14 2 REPEATNG ELS
     * 15 FIND 2 ELS WITH SUM == K(HASHMAP OR 2 PTR, IF SORTED)
     * 3 SUM
     * 
     * 16 2 ELS WHOSE SUM IS CLOSEST TO ZERO
     * 
     * 17 EL WITH MIN IDF W.R.T KEY IN AN ARRAY, FIND FLOOR AND CEIL OF KEY AND RETURN MIN
     * 
     * 18 PIVOT (SUM SAME BOTH SIDES)
     * 
     */


    // ACCEPTING BINARY SEARCH 
    /** 
     * POINTS : 
     * 1 THIS IS AN ACCEPTING SEARCH AS COMPARED TO THE BINARY SEARCH
     * IN SUCH THAT IT STORES A RESULT AND THEN IMPROVES UPON IT.
     * 2 UPDATE RESULT AND RETURN AT END.
     * 
     * */
    int binarySearch(int[] arr, int start, int end, int key){
        int result = -1;
        int low = start; int high = end;
        while(low<=high){
            int mid = low + (high-low)/2;

            if(arr[mid] == key) result = mid; 
            if(arr[mid]>key) high = mid-1;
            if(arr[mid]<key) low =  mid+1;
        }
        
        return result;
    }

    /**  FIRST TEMPLATE OF ACCEPTING BS, FIND FLOOR, no arr[mid] == target
     * 1 if(nums[mid] < key){
     *      result = mid;
     *      lo = mid+1;
     *   }
     * 
     * 2 IN BINSEARCH FOR FIRST INDEX, AS WE MOVE TO THE LOWER SIDE,
     * SO HIGH = MID-1, WHEN nums[mid] == key
     * 
    */


    /** 
     * POINTS :
     * 1 THE FLOOR WILL BE SMALLER THAN KEY, SO LOW CONDN ALSO
     * UPDATES RESULT
     * 2 FLOOR CAN NEVER BE EQUAL HENCE nums[mid] == key
     * ISN'T USED
     * 
    */
    int findFloor(int[] nums, int key){
        int n = nums.length;
        int lo = 0; int hi = n-1; 
        int result = 0;

        while(lo<=hi){
            int mid = lo + (hi - lo)/2;

            if(nums[mid] < key){
                result = mid;
                lo = mid+1;
            }
            else hi = mid-1;
        }
        System.out.println("index "+result+", floor of "+key+ " is "+nums[result]);
        return result;
    }


    /** 
     * POINTS :
     * 1 SIMILAR TO FINDING FLOOR OF AN EL
     * 2 THE INDEX WHERE THE EL WILL BE INSERTED IS THE LAST INDEX OF THE 
     * JUST SMALLER ELEMENT + 1
     * 
     * IMP
    */
    // https://leetcode.com/problems/search-insert-position
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int lo = 0; int hi = n-1;
        int result = -1;
        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            
            if(nums[mid]<target) {
                result = mid;
                lo = mid+1;
            }else hi = mid-1;
        }
        return result+1;
    }



    /** POINTS :
     * 1 USE TWO BINARY SEACH FUNCS, TO FIND FIRST AND LAST
     * 2 THE FIRST USES HIGH = MID-1 AS ONCE WE FIND A MATCHING INDEX, 
     *   WE NEED TO GO LEFT TO FIND IF SMALLER EXISTS
     * 3 THE LAST USED LOW = MID+1
     * 
     * nums[mid] == target also moves low and high

     * same as normal bin search, keep track of res (accepting bin search)
     */
    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public int[] searchRange(int[] nums, int target) {
        int a = findFirst(nums, target);
        int b = findLast(nums, target);
        
        return new int[]{a, b};
    }
    
    int findFirst(int[] arr, int target){
        int n = arr.length, lo = 0, hi = n-1, res = -1;
        
        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] == target) {
                res = mid;
                hi = mid-1;
            }
            else if(arr[mid]<target) lo= mid+1;
            else hi = mid-1;
        }
        return res;
    }
    
    int findLast(int[] arr, int target){
        int n = arr.length, lo = 0, hi = n-1, res = -1;
        
        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] == target){
                res = mid;
                lo = mid+1;
            }
            else if(arr[mid]<target) lo= mid+1;
            else hi = mid-1;
        }
        return res;
    }


    // min el is always in the unsorted array side, so move towards the unsorted array
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    // discuss/48590/Easy-Java-solution-for-both-the-problems-
    // (Find-Minimum-in-Rotated-Sorted-Array-I-and-II).
    
   
    
    /** 
     * 5 STEP BINARY
     * POINTS :
     * 1 low<high (NO <=)
     * 2 NO CHECK FOR MID == TARGET
     * 3 IF MID<HIGH, ARRAY IS SORTED, SO MOVE LEFT
     * 4 IF MID == HIGH, HIGH--
     * 5 RETURN nums[low]
     * 
     * only ques where high = mid; else high = mid-1
     * */
    
    // 2nd SECOND TEMPLATE OF BS, 
    // WORKS FOR DUPLICATES TOO (high--)
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        int low = 0; int high = nums.length-1;
        
        while(low<high){ //1
            int mid = low + (high - low)/2;
            //2 
            if(nums[mid] < nums[high]) high = mid;//3
            // duplicates
            else if(nums[mid] == nums[high]) high--;
            else low = mid+1;
            
        }
        return nums[low];//4
    }

    /**
     * 1 findMin FROM ABOVE
     * 2 FIND INDEX OF MIN EL
     * 3 USE 2 BIN SEARCHES BS(0, INDEX-1) & BS(INDEX, n-1)
     * 
     */
    // https://leetcode.com/problems/search-in-rotated-sorted-array
    public int search(int[] nums, int target) {
        int n = nums.length; int res= -1;
        
        int index = findMin(nums);

        res = binSearch(nums, 0, index-1, target); 
        if(res == -1) res = binSearch(nums, index, n-1, target);
        
        return res;
    }
    
    int binSearch(int[] nums, int start, int end, int target){
        int lo = start; int hi = end;
        while(lo <= hi){
            int mid = lo + (hi -lo)/2;
            if(nums[mid] == target) return mid;
            
            if(nums[mid]<target) lo = mid+1;
            else hi = mid-1;
        }
        return -1;
    }

    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
    public int findMinWithDuplicates(int[] nums) {
        int n =nums.length;
        int lo = 0; int hi = n-1;
        while(lo<hi){
            int mid = lo +(hi - lo)/2;
            if(nums[mid]<nums[hi]) hi = mid;
            else if(nums[mid] == nums[hi]) hi--;
            else lo = mid + 1;
        }
        return nums[lo];
    }


    /** 3RD TEMPLATE
     * POINTS : 
     * 1 DO A NORMAL BINARY SEARCH FOR MID B/W 0 AND n-1
     * 2 if (mid == 0) CHECK arr[0] and arr[1]
     * 3 if (mid == n-1) CHECK arr[n-1] and arr[n-2]
     * 
     * [4,5,1,2,3]; mid will go from 1 till 3
     * but once it reaches 3, we check boundary condns
     * the array can have multiple peaks, here 3 is a peak
    */
    // https://leetcode.com/problems/find-peak-element/
    public int findPeakElement(int[] arr) {
        int n = arr.length;
        int lo = 0; int hi = n-1;
        if(n==1) return 0;
        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            
            if(mid>0 && mid<n-1){
                if(arr[mid]>arr[mid-1] && arr[mid]>arr[mid+1]) return mid;
                else if(arr[mid]<arr[mid+1]) lo = mid+1; // 5, 10, 20 -> move right
                else hi = mid-1;
            }
            // boundary cases
            else{
                if(mid == 0){
                    if(arr[mid]>arr[mid+1]) return mid;
                    else return mid+1;    
                } 
                
                if(mid == n-1){
                    if(arr[mid]>arr[mid-1]) return mid;
                    else return mid-1;
                }
            }
        }
        return 0;
    }

    /**
     * 1 USING ACCEPTING BINARY SEARCH
     * 2 IF MID IS BAD, STORE THEN GO LEFT
     * 3 ELSE GO RIGHT
     * 4 WHEN TO STORE, CHECK WHICH TYPE IS ATLEAST ACCEPTABLE,
     * IF BAD, STORE BAD.
     * 
     * STORING HELPS US AVOID UNNECESSARY CHECKS TO SEE IF THIS IS THE FIRST 
     * BAD BY CHECKING LEFT.
     */
    // https://leetcode.com/problems/first-bad-version/
    boolean isBadVersion(int x){return true;}//dummy func check ques
    public int firstBadVersion(int n) {
        int result = -1;
        
        int low = 1; int high = n;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isBadVersion(mid)){
                result =  mid; 
                high = mid-1;
            } else low = mid+1;
        }
        return result;
    }


    // BOYER-MOORE ALGO, MAJORITY EL
    // https://leetcode.com/problems/majority-element/
    // https://leetcode.com/problems/majority-element-ii/
    // discuss/63520/Boyer-Moore-Majority-Vote-algorithm-and-my-elaboration
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int res = nums[0]; int count = 1;
        
        for(int i=1; i<n; i++){
            if(count == 0){
                count++;
                res =nums[i];
            }
            else if(nums[i] == res) count++;
            else if(nums[i] != res) count--;
        }
        return res;
    }

    /**
     *  We want the first element of the middle pair,
     * which should be at an even index if the left part is sorted.
     * https://leetcode.com/problems/single-element-in-a-sorted-array
     * /discuss/100754/Java-Binary-Search-short-(7l)-O(log(n))-w-explanations
     * 
     * IMP : IF NO SINGLE EL, LAST INDEX OF PAIR WILL BE ODD.
     * 
     * 1,1,2 -> mid 1,  mid is odd, arr[mid] == arr[mid+1]
     * 0,1,1,2,2  ->  2,  mid is even, arr[mid] != arr[mid+1]
     * 0,0,1,1,2  ->  2,  mid is odd, arr[mid] == arr[mid+1]
     * 
     * if mid is even and arr[mid] != arr[mid+1], the pair
     * end at even index, so the disturbance must be on the left side
     * 
     * not equal here signifies we have the last index of the pair
     * and it is at even posn, but for no disturbance, last must
     * be at an odd posn. So hi = mid;
     * 
     * If equal, no disturbance, move right by 2 as this is a pair.
     * 
     * return nums[lo]
     */ 
    // https://leetcode.com/problems/single-element-in-a-sorted-array
    public int singleNonDuplicateBinary(int[] nums) {
        int n = nums.length;
        int lo = 0; int hi = n-1;
        
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(mid%2!=0) mid--; // 
            
            // el on left
            if(nums[mid] != nums[mid+1]){ // 
                hi = mid;
            }else lo = mid+2; // 
        }
        return nums[lo]; // 
    }


    /** 
     * POINTS : 
     * 1 MARK ALL -VE AND >n AS n+1
     * 2 MARK nums[Math.abs(nums[k]) - 1] AS -ve
     * 3 ITERATE AGAIN AND FIND FIRST +ve
     * 4 IF NONE, RETURN n+1
     * 
     * mark -ve nos and nos>n as n+1 and nums[i] - 1 as -ve
     * 
    */
    // [1,1], [-1,-2], [2,1], 
    // [-5] this is why we mark -ve as n+1
    // https://leetcode.com/problems/first-missing-positive
    public int firstMissingPositive(int[] nums) {
        // Math.abs(arr[index]) -1 -> -ve
        // traverse and find first +ve index-> +1
        int n = nums.length;
        if(n==0) return 1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        
        for(int k = 0; k<n; k++){ // start from 0 not j
            if(Math.abs(nums[k])-1 >= 0 && Math.abs(nums[k])-1<n 
               && nums[Math.abs(nums[k]) - 1] >0 ) // -ve should remain -ve
                nums[Math.abs(nums[k]) - 1]*=(-1);
        }
        
        // for(int p : nums) System.out.println(p);
        for(int i =0; i<n; i++){
            if(nums[i] > 0) return i+1;
        }
        return n+1;
    }

    // PIVOT
    // https://leetcode.com/problems/find-pivot-index/
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int left = 0; int sum =0;
        
        for(int i=0; i<n; i++){
            sum+=nums[i];
        }
        
        for(int i =0; i<n; i++){
            if(sum-left-nums[i] == left) return i;
            left += nums[i];
        }
        return -1;
    }

    /** 
     * the indexes are added in a sorted amnner, no need to sort list
     * do bin search and keep track of min difference
    */
    // https://leetcode.com/problems/shortest-distance-to-target-color/
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = colors.length;
        
        for(int i=0; i<n; i++){
            List<Integer> curr = map.getOrDefault(colors[i], new ArrayList<>());
            curr.add(i);
            map.put(colors[i], curr);
        }
        
        List<Integer> res = new ArrayList<>();
        
        for(int[] i : queries){
            if(!map.containsKey(i[1])) {
                res.add(-1);
                continue;
            }
            List<Integer> list = map.get(i[1]);
            // Collections.sort(list);
            //if(list.contains(i[0])) res.add(0);
            if(i[0]<list.get(0)) res.add(list.get(0) - i[0]);
            else if(i[0]>list.get(list.size()-1)) res.add(i[0] - list.get(list.size()-1));
            else{
                // bin search to find min diff
                res.add(binSearch(i[0], list));
            }
        }
        return res;
    }
    
    int binSearch(int key, List<Integer> list){
        int min = Integer.MAX_VALUE, lo = 0, hi = list.size()-1;
        while(lo<=hi){
            int mid = lo +(hi - lo)/2;
            if(key == list.get(mid)) return 0;
            // else if(Math.abs(key - list.get(mid)) > min) break;
            else if(key<list.get(mid)){
                min = Math.min(min, Math.abs(key - list.get(mid)));
                hi = mid-1;
            }
            else{
                min = Math.min(min, Math.abs(key - list.get(mid)));
                lo = mid+1;
            }
        }
        return min;
    }

    /**
     * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
     * 
     * POINTS :
     * 1 THE ARRAY HAS DUPLICATES, REMOVE THOSE USING TEMPLATE
     * 2 THEN DO NBINARY SEARCH TO FIND CEIL FOR EACH SCORE OF ALICE
     * 3 IF CEIL INDEX == -1, IT'S THE LARGEST, RANK = 1
     * ELSE RANK = INDEX+2
     * 
     * WHY FIND CEIL IF THE EL IS PRESENT?
     * BECAUSE WHEN THE EL IS NOT PRESENT, THE FIRST TEMPLATE BS
     * WILL HAVE TO BE MODIFIED. 
     * 
     */

    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int n = alice.length;
        int[] res= new int[n];

        int j = 0;
        // remove duplicates
        for(int i=0; i<scores.length; i++){
            if(scores[j] != scores[i]){
                j++;
                swap(scores, i, j);
            }
        }

        // find ceil for each alice[i] from 0 to j
        for(int i =0; i<n; i++){
            int index = findCeiling(scores, 0, j, alice[i]);
            // ceil doesn't exist so only case will be the el is max
            if(index == -1) res[i] = 1;
            else res[i] = index + 2;
        }
        // Utility.print1DMatrix(res);
        return res;
    }

    static void swap(int[] arr,int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // larger key accept
    static int findCeiling(int[] arr, int start, int end, int key){
        int lo = start; int hi = end;
        int result = -1;
        while(lo<=hi){
            int mid = lo + (hi -lo)/2;

            if(arr[mid] > key){
                result = mid;
                lo = mid+1;
            }
            else hi = mid-1;
        }
        return result;
    }

    
    // FIND FIRST 1 IN MATRIX ROW
    /**
     * POINTS : 
     * 
     * 1 Maintain two flags, minIndex and minRow. 
     * 2 Find first occurrence of 1. IF index<minIndex update minIndex and minRow.
     * 
     * 2 Edge case : If single row with all zeroes, 
     * check if minIndex !=Integer.MAX_VALUE.
     */
    // https://practice.geeksforgeeks.org/problems/row-with-max-1s0023/1
    int rowWithMax1s(int arr[][], int n, int m) {
        // code here
        int minRow = 0;
        int minIndex = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int index = findFirst(arr[i]);
            // System.out.println("index "+index+" row "+i);
            if (index != -1 && index < minIndex) {
                minIndex = index;
                minRow = i;
            }
        }
        return minIndex != Integer.MAX_VALUE ? minRow : -1;

    }

    int findFirst(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == 1) {
                result = mid;
                hi = mid-1;
            }
            if (arr[mid] == 0) {
                lo = mid + 1;
            }
        }
        return result;
    }


    // https://leetcode.com/problems/search-a-2d-matrix-ii/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;
        if (m == 1 && n == 1 && matrix[0][0] != target)
            return false;

        int i = 0;
        int j = n - 1;
        // System.out.println("i "+i+" j "+j);

        while (i >= 0 && i < m && j >= 0 && j < n) {
            // System.out.println("i "+i+" j "+j);
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                j--; // use else if
            else if (matrix[i][j] < target)
                i++;
        }
        return false;
    }

    /** 
     * SEE THE DIFF W.R.T ABOVE, 
     * 
     * idea is simple, add els of first row
     * remove and add next el of curr col;
     * if x == m-1 continue;
     * run k-1 times
    */
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix
    class Node1{
        int x, y, val;
        Node1(int x, int y, int v){
            this.x = x; this.y =y; this.val = v;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length; int n = matrix[0].length;
        
        PriorityQueue<Node1> pq = new PriorityQueue<>((x,y)->x.val - y.val);
        
        // add first row
        for(int i =0; i<m; i++){
            pq.add(new Node1(0 , i, matrix[0][i]));
        }
        
        for(int i =0; i<k-1; i++){
            Node1 curr = pq.remove();
            // System.out.println(curr.val);
            if(curr.x == m-1) continue;
            // next el in same col
            pq.add(new Node1(curr.x+1, curr.y, matrix[curr.x+1][curr.y] ));
        }
        return pq.remove().val;
    }

    /** 
     * POINTS :
     * 1 IF CAPACITY = SUM OF ALL WTS, IT CAN BE TRANSFERRED IN A DAY
     * 2 CAPACITY CAN'T BE LESSER THAN MAX WT
     * 3 START FROM MIN AND GO TILL MAX
     * 4 CHECK IF ANY WT MATCHES THE ISSAFE CONDN
     * 
     * ISSAFE CONDN : 
     * ADD UP THE WEIGHTS TILL IT IS LESSER THAN CURRENT CAPACITY
     * ELSE WE WILL LOAD THIS WT FROM THE NEXT DAY. 
     * ALSO WE WILL TRY TO ADD AS MUCH WT AS POSSIBLE
     * (LESS THAN CURRENT CAPACITY, OF COURSE) THAT'S WHY SUM+=WEIGHT
     * 
     * normal version w/o binary search
     * for(int i = minW, i<=maxW; i++){
     *    if(isSafe(i, weights, D)) return i;
     * }
     * 
     * BINARY SEARCH:
     * START FROM MID, IF MID IS VALID, WE LOOK FOR A LESSER CAPACITY
     * ELSE WE MOVE TOWARDS HIGHER CAPACITY
     * 
    */
    // https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int sum  = 0; int max = 0;
        for(int i : weights) {
            max = Math.max(max, i);
            sum+=i;
        }

        // capacity starts from max
        int lo = max; int hi = sum; int res = 0;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(isSafe(weights, mid, D)) {
                res = mid;
                hi = mid-1;
            }
            else lo = mid+1; 
        }
        return res;
    }
    
    
    boolean isSafe(int[] weights, int capacity, int maxDays){
        int sum = 0; int days = 1;
        for(int i : weights){
            sum+=i;
            if(sum>capacity) {
                days++;
                sum = i;
                
                // System.out.println("sum "+sum +" cap "+capacity+" days " +days);
            }
        }
        if(days>maxDays) return false;
        return true;
    }


    /** 
     * SIMILAR TO ABOVE : WHENEVER QUESTION ASKS FOR CONTIGUOUS PARTITION 
     * THINK OF BINARY SEARCH
     * 
     * idea is if we can find max of all partitions less than mid and 
     * no of partitions <= m, then we check if an even lesser max is possible.
     * so hi = mid-1;
     * 
     * 1 MIN VALUE = MAX AND MAX VALUE = SUM OF ALL ELS
     * 2 BIN SEARCH FROM MIN TILL MAX
     * 3 IF SUM EXCEEDS MID, TAKE A NEW PARTITION
     * 4 RETURN TRUE ONLY IF NO OF PARTITIONS IS <= m
     * 
     * imp : count = 1
    */
    // https://leetcode.com/problems/split-array-largest-sum/
    public int splitArray(int[] nums, int m) {
        int sum = 0, max = -1;
        
        for(int i : nums){
            sum+=i; max = Math.max(max, i);
        }
        
        int lo = max, hi = sum, res = 0;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            
            int x = findPartitions(nums, mid, m);
            // if larger pieces, then lesser partitions, so we need to lessen the size, hence
            // hi = mid-1;
            if(x <= m){
                res = mid;
                hi = mid-1;
            }
            else if(x>m){
                lo = mid+1;
            }
        }
        return res;
    }
    
    int findPartitions(int[] nums, int mid, int m){
        int sum = 0, count = 1;  // imp count = 1
        for(int i :nums){
            sum+=i;
            if(sum>mid){
                count++;
                sum = i;
            }
        }
        return count;
    }


    /** 
     * case 1: citations[mid] == len-mid, then it means there are citations[mid] papers 
     * that have at least citations[mid] citations.
     * case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that 
     * have more than citations[mid] citations, so we should continue searching in the left half
     * case 3: citations[mid] < len-mid, we should continue searching in the right side.
     * After iteration, it is guaranteed that right+1 is the one we need to find 
     * (i.e. len-(right+1) papars have at least len-(righ+1) citations)
    */
    // https://leetcode.com/problems/h-index-ii
    public int hIndex(int[] citations) {
        int n = citations.length;
        int lo = 0; int hi = n-1;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(citations[mid]==n-mid) return citations[mid];
            else if(citations[mid]> n-mid){
                // System.out.println("mid "+mid+" "+citations[mid]);
                hi = mid-1;
            }
            else lo = mid+1;
        }
        return n - lo;
    }


    /** 
     * 1 use quicksort partition
     * 2 if x > position, hi = x-1 and vice-versa
    */
    // https://leetcode.com/problems/kth-largest-element-in-an-array
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length, lo = 0, hi = n-1;
        while(lo<=hi){
            int x = partition(nums, lo, hi);
            // System.out.println(x);
            if(x == n-k) return nums[x]; // index
            else if(x<n-k) lo = x+1;
            else hi = x-1;
        }
        return lo;
    }
    
    
    int partition(int[] arr, int lo, int hi){
        int j = lo, pivot = arr[hi];
        for(int i = lo; i<hi; i++){
            if(arr[i]<pivot) swap(arr, i, j++);
        }
        swap(arr, hi, j);
        return j;
    }


    /** 
     * steps:
     * 1 check which array has lesser length
     * 2 use cut1 and cut2, no of elements to the left of cut1 and cut2 should be half of the total no of els
     * 3 6 vars, lo and hi for bin search, rest for calculation
     * 4 l1<r2 and l2<r1
     * 5 if l1>r2 we need to move cut1 to the left and if l2>r1 move to the right.
     * remember always els to the left will be equal in number to els on the right
     * 
     * bin search on arr1, take max of left half and min of right half
     * 
     * REMEMBER : C1 IS THE MID ANALOGOUS TO BIN SEARCH
     * HI = M, C1 = LO +()/2; C2 = (M+N)/2-C1
     * L1 = C1-1, L2 = C2-1..
     * MOVE ON FIRST ARRAY, L1>R2? HI = C1 -1
     * IF ODD MIN(R1, R2); ELSE (MAX() + MIN()) /2
     * 
    */
    // https://www.youtube.com/watch?v=yD7wV8SyPrc
    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m>n) return findMedianSortedArrays(nums2, nums1);

        int lo = 0, hi = m;
        
        while(lo<=hi){
            int c1 = lo +(hi-lo)/2;
            int c2 = (m+n)/2 - c1;
            
            int l1 = c1 == 0?Integer.MIN_VALUE:nums1[c1-1];
            int l2 = c2 == 0?Integer.MIN_VALUE:nums2[c2-1];
            int r1 = c1 == m?Integer.MAX_VALUE:nums1[c1];
            int r2 = c2 == n?Integer.MAX_VALUE:nums2[c2];
            
            // binary search
            if(l1>r2) hi = c1-1;
            else if(l2>r1) lo = c1+1;
            else{
                if((m+n)%2 == 0) return (Math.max(l1, l2) + (Math.min(r1, r2)))/2.0;
                else return Math.min(r1, r2);
            }
        }
        return 0;
    }

    /**
     * koko eating bananas https://leetcode.com/problems/koko-eating-bananas/submissions/
     * the min speed can be sum/h, as sum/h * h = sum
     * the max speed can be the max el in the pile, as only one pile can be eaten in an hour
     * 
     * if speed is such that hours needed are less, we need to dec the speed,
     * so hi = mid-1
     *
     * hours <- inverse -> speed 
     */
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length, lo = 0, hi = 0, max = 0, sum = 0, res = 0;
        
        for(int i : piles){
            max = Math.max(max, i);
            sum+=i;
        }
        
        lo =1; //sum/h; 
        hi = max;
        
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            
            int val = findCount(piles, mid);
            // System.out.println(val+", "+lo+", "+mid+", "+hi);
            if(val<=h){
                res = mid; // res = val
                hi = mid-1;
            }
            else lo = mid+1;
        }
        return res;
    }
    
    int findCount(int[] piles, int mid){
        int count = 0;
        // Math.ceil
        for(int i : piles){
            if(i%mid == 0) count+=i/mid;
            else{
                count+=i/mid+1;
            }
        }
        
        return count;
    }


    // https://leetcode.com/problems/russian-doll-envelopes/

    // https://leetcode.com/tag/binary-search/
    // https://leetcode.com/problems/h-index-ii/
    
    // GOOGLE
    // https://leetcode.com/discuss/interview-question/153179/google-onsite-min-swaps-to-make-sorted
    // https://leetcode.com/problems/pancake-sorting/
    // https://leetcode.com/problems/maximum-number-of-coins-you-can-get/
    // https://leetcode.com/problems/car-fleet/
    // https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

    
    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/
    // https://www.geeksforgeeks.org/
    // queries-to-find-kth-greatest-character-in-a-range-l-r-from-a-string-with-updates/
    // ?ref=leftbar-rightbar
    public static void main(String[] args){
        Bin_Search coronaSearch = new Bin_Search();
        int[] arr = {1,3,3,5,8,11,14};//,4,6,7,9,21,43};

        // coronaSearch.findFloor(arr, 5);

        int[][] matrix = new int[][]{
            {0,0,1,1,1},
            {0,1,1,1,1},
            {0,0,0,0,0},
            {1,1,1,1,1}
        };

        System.out.println("row with max 1s " +
        coronaSearch.rowWithMax1s(matrix, matrix.length, matrix[0].length));
        int[] scores = {100, 90, 90, 80, 75, 60};
        int[] alice = {50, 65, 77, 90, 102};
        // climbingLeaderboard(scores, alice);
        // coronaSort.heapSort(arr);
        // for(int i =0; i<arr.length; i++){
        //     System.out.println(arr[i]);
        // }
    }
}


