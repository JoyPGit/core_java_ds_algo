import java.util.*;

public class Searching {
    
    Searching(){}

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
     * FOR BOUNDARY CONDS, MID-1>=0 && MID+1<=n-1)
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
        System.out.println("index "+result+", floor of "+key+ "is "+nums[result]);
        return result;
    }


    /** 
     * POINTS :
     * 1 SIMILAR TO FINDING FLOOR OF AN EL
     * 2 THE INDEX WHERE THE EL WILL BE INSERTED IS THE LAST INDEX OF THE 
     * JUST SMALLER ELEMENT + 1
     * 
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



    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    /** POINTS :
     * 1 USE TWO BINARY SEACH FUNCS, TO FIND FIRST AND LAST
     * 2 THE FIRST USES HIGH = MID-1 AS ONCE WE FIND A MATCHING INDEX, 
     *   WE NEED TO GO LEFT TO FIND IF SMALLER EXISTS
     * 3 THE LAST USED LOW = MID+1
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length; 
        if(n ==1 && nums[0]!= target) return new int[]{-1, -1};
        int i = binSearchFirst(nums, target);
        int j = binSearchLast(nums, target);
      
        return new int[]{i,j};
    }
    
    int binSearchFirst(int[] arr, int key){
        int result = -1;
        int low = 0; int high = arr.length-1;
        while(low <= high){
            int mid = low + (high-low)/2;

            if(arr[mid] == key) {
                result = mid; 
                high = mid-1;
            }
            if(arr[mid]>key) high = mid-1;
            if(arr[mid]<key) low = mid+1;
            
        }
        
        return result;
    }
    
    int binSearchLast(int[] arr, int key){
        int result = -1;
        int low = 0; int high = arr.length-1;
        while(low <= high){
            int mid = low + (high-low)/2;

            if(arr[mid] == key) {
                result = mid; 
                low = mid+1;
            }
            if(arr[mid]>key) high = mid-1;
            if(arr[mid]<key) low =  mid+1;
            
        }
        
        return result;
    }

    // min el is always in the unsorted array side, so move towards the unsorted array
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    // discuss/48590/Easy-Java-solution-for-both-the-problems-
    // (Find-Minimum-in-Rotated-Sorted-Array-I-and-II).
    
    // SECOND TEMPLATE OF BS, 
    // WORKS FOR DUPLICATES TOO (high--)
    /** 
     * 5 STEP BINARY
     * POINTS :
     * 1 low<high (NO <=)
     * 2 NO CHECK FOR MID == TARGET
     * 3 IF MID<HIGH, ARRAY IS SORTED, SO MOVE LEFT
     * 4 IF MID == HIGH, HIGH--
     * 5 RETURN nums[low]
     * */
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        int low = 0; int high = nums.length-1;
        
        while(low<high){ //1
            int mid = low + (high - low)/2;
            //2 
            if(nums[mid] < nums[high]) high = mid;//3
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
    // https://leetcode.com/problems/first-bad-version/submissions/
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

    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    // [1,1,2,3,3,4,4,8,8]
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int j = 0; int i = 0;
        while(i<n-1){//out of bounds error
            while(nums[j] == nums[i]) j++;
            j--;// get back to curr el as j goes to next el
            if(j == i) return nums[i];
            // System.out.println("j "+j+" i "+i);
            i = ++j;//move i and j both to next el
        }
        //if here, the last el must be the single el which is returned
        return nums[n-1];
    }


    // FIND FIRST 1 IN MATRIX ROW
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


    // https://leetcode.com/problems/first-missing-positive/
    // inaccurate soln but works for tougher ques
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length; 
        int i = 0; int j =0;
        int min = 1; boolean found = false;
        
        while(nums[i]<=0 && i<n) i++;
        // min = nums[i]<0?1:nums[i];
        
        if(i==n-1) return min;
        if(i==n) return min;
        // else min = nums[i];
        System.out.println(i);
        for(j =i+1; j<n; j++){
            if((nums[j] - nums[j-1])!=1) {
                min = nums[j-1]+1;
                found =true;
                System.out.println("min "+min);
            }
        }
        System.out.println(j);
        if(j== n && !found) return nums[n-1]+1;
        return min;
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
        utilCustom.Utility.print1DMatrix(res);
        return res;
    }

    static void swap(int[] arr,int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

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
                hi = mid - 1;
            }
            if (arr[mid] == 0) {
                lo = mid + 1;
            }
        }
        return result;
    }


    // https://leetcode.com/tag/binary-search/
    // https://leetcode.com/problems/h-index-ii/
    
    // GOOGLE
    // https://leetcode.com/discuss/interview-question/153179/google-onsite-min-swaps-to-make-sorted
    // https://leetcode.com/problems/rank-teams-by-votes/
    // https://leetcode.com/problems/sort-the-matrix-diagonally/
    // https://leetcode.com/problems/sort-list/
    // https://leetcode.com/problems/pancake-sorting/
    // https://leetcode.com/problems/maximum-number-of-coins-you-can-get/
    // https://leetcode.com/problems/car-fleet/
    // https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

    // https://leetcode.com/problems/single-element-in-a-sorted-array/

    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/
    // https://www.geeksforgeeks.org/
    // queries-to-find-kth-greatest-character-in-a-range-l-r-from-a-string-with-updates/
    // ?ref=leftbar-rightbar
    public static void main(String[] args){
        Searching coronaSearch = new Searching();
        int[] arr = {1,3,3,5,8,11,14};//,4,6,7,9,21,43};

        // coronaSearch.findFloor(arr, 5);
        int[] scores = {100, 90, 90, 80, 75, 60};
        int[] alice = {50, 65, 77, 90, 102};
        climbingLeaderboard(scores, alice);
        // coronaSort.heapSort(arr);
        // for(int i =0; i<arr.length; i++){
        //     System.out.println(arr[i]);
        // }
    }
}