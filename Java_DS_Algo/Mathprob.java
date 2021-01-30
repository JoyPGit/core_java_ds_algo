import java.util.*;

// https://leetcode.com/problems/distribute-candies-to-people
/** candies>=count helps to stop 
 * assign remaining candies to the next person
 * as we are traversing the array multiple times, 
 * use mod circular array concept
 */
public class Mathprob {

    int gcd(int a, int b){
        if(a%b == 0) return b;
        return gcd(b, a%b);
    }

    // num%10 and num/=10
    // https://leetcode.com/problems/self-dividing-numbers
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        int num = left;
        
        while(num<=right){
            if(isValid(num)) res.add(num);
            num++;
        }
        return res;
    }
    
    boolean isValid(int num){
        int x = num, y = 0;
        while(x!=0){
            y = x%10;
            if((y == 0) || (num%y) != 0) return false;
            // System.out.println("x "+x+" y "+y);
            x/=10;
        }
        return true;
    }

    // https://practice.geeksforgeeks.org/problems/power-of-2-1587115620/1
    // Function to check if given number is power of two
    public static boolean isPowerofTwo(long n){
        long x = 2;
        if(n == 1 || n == 2) return true;
        while(x<n){
            x*=2;
            if(n==x) return true;
        }
        return false;
    }

    // https://leetcode.com/problems/count-primes/
    public int countPrimes(int n) {
        int count =0;
        for(int i =0; i<n; i++){
            // to handle 0 and 1
            if(i == 0 || i == 1) continue;
            if(isPrime(i)) {
                // System.out.println(" num "+i);
                count++;
            }
        }
        return count;
    }
    
    boolean isPrime(int num){
        // not j<=num/2
        for(int j =2; j*j<=num; j++){
            if(num%j == 0) return false;
        }
        return true;
    }


    // SIEVE OF ERATOSTHENES
    /** 
     * 1 FIND A PRIME, if(!isPrimeArr[i]) continue;
     * 2 MARK ALL ITS MULTIPLES FALSE IN ARRAY
     * 3 RUN FOR i*i<=n
    */
    // https://leetcode.com/problems/count-primes/
    // no need to check for isPrime, use array
    public int countPrimes1(int n) {
        int count = 0;
        if(n==0 || n==1 || n==2) return 0;
        boolean isPrimeArr[] = new boolean[n+1];
        Arrays.fill(isPrimeArr, true);

        for(int i = 2; i*i<=n; i++){ // 
            // if already marked non-prime, continue
            if(!isPrimeArr[i]) continue;
            // mark multiples
            markPrime(i, n, isPrimeArr);
        }
        for(int i = 2; i<n; i++){ //
            if(isPrimeArr[i]) count++;
        }
        return count;
    }
    

    // find multiples
    void markPrime(int div, int n, boolean[] arr){
        for(int i = 2; i*div<=n; i++) { // mnultiples
            if(arr[i*div]==false) continue; 
            arr[i*div] = false;
        }
    }

    boolean isPerfectSquare(int x){
        if(x == 1) return true;
        int lo = 2; int hi =  x/2;
        while(lo<=hi){
            int mid = lo+(hi - lo)/2;
            if(mid*mid == x) {
                System.out.println(x+" is a perfect sq");
                return true;
            }
            else if(mid*mid>x) hi = mid-1;
            else lo = mid+1;
        }
        System.out.println(x+" is not a perfect sq");
        return false;
    }

    /**use map for memoization of fibonacci series
     * add to heap
     * greedily subtract the max from k,
     * if top is greater than k remove
     * else subtract from k
     * ALWAYS ADD CHECK FOR HEAP!=0
     * 
     * 
     * had to add 45 as 10^7 was limit(saw in soln)
     * 
     * while(true) breaks with return count statement
     */
    // https://leetcode.com/problems/find-the-minimum-number-of-
    // fibonacci-numbers-whose-sum-is-k/
    public int findMinFibonacciNumbers(int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((x,y)->y-x);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2, 1); map.put(1, 1);

        // fib(45, map);
        heap.addAll(map.values());
        System.out.println(heap);        
        // https://stackoverflow.com/questions/5695017/priorityqueue-not-sorting-on-add

        int count =0; int curr =k;
        System.out.println(heap);
        while(heap.peek()>k) heap.remove();
        System.out.println(heap);
        while(true){
            if(curr - heap.peek()==0){
                return count+1;
            }
            else if(heap.size()!=0 && curr - heap.peek()>0){
                curr-=heap.remove();
                count++;
            }
            if(curr==0) return count;
            else if(heap.peek()>curr) heap.remove();
        }
    }

    
    // https://leetcode.com/problems/robot-bounded-in-circle/
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        String dir = "up";
        
        for(char c : instructions.toCharArray()){
            if(c == 'G') {
                if(dir.equals("up")) y++;
                else if(dir.equals("right")) x++;
                else if(dir.equals("left")) x--;
                else if(dir.equals("down")) y--;
            }
            if(c == 'L') {
                if(dir.equals("up")) dir = "left";
                else if(dir.equals("down")) dir = "right";
                else if(dir.equals("right")) dir = "up";
                else if(dir.equals("left")) dir = "down";
            }
            if(c == 'R') {
                if(dir.equals("up")) dir = "right";
                else if(dir.equals("down")) dir = "left";
                else if(dir.equals("right")) dir = "down";
                else if(dir.equals("left")) dir = "up";
            }
            System.out.println("x "+x+" y "+y+" "+dir);
        }
        
        if(x==0 && y == 0) return true;
        // any other dirn will revert back
        if (dir.equals("up")) return false;
        return false;
    }

    // https://leetcode.com/problems/strobogrammatic-number/
    public boolean isStrobogrammatic(String num) {
        int lo = 0, hi = num.length()-1;
        // if(num.length() == 1) return true;
        
        while(lo<=hi){
            if((num.charAt(lo) == '0' && num.charAt(hi) =='0')
              || (num.charAt(lo) == '1' && num.charAt(hi) =='1')
              || (num.charAt(lo) == '6' && num.charAt(hi) =='9')
              || (num.charAt(lo) == '8' && num.charAt(hi) =='8')
              || (num.charAt(lo) == '9' && num.charAt(hi) =='6')
              ) {
                hi--; lo++;
            }
            else return false;
        }
        return true;
    }

    // For ex, low = 4, high = 8 is same as low = 5 and high = 7 as the odds are only [5,7].
    // https://leetcode.com/problems/count-odd-numbers-in-an-interval-range
    public int countOdds(int low, int high) {
        if(low%2 == 0) low++;
        if(high%2 == 0) high--;
        return (high-low)/2+1;
    }
    

    // https://leetcode.com/problems/ugly-number
    public boolean isUgly(int num) {
        if(num == 0) return false;
        while(num!=1){
            if(num%2==0) num/=2;
            else if(num%3==0) num/=3;
            else if(num%5==0) num/=5;
            else return false;
        }    
        return true;
    }

    /** 
     * start from i till n
     * maintain count
    */
    // https://leetcode.com/problems/the-kth-factor-of-n
    public int kthFactor(int n, int k) {
        if(k == 1) return 1;
        int count = 1;
        for(int i = 2; i<=n; i++){
            if(n%i == 0) count++;
            if(count == k) return i;
        }
        return count<k?-1:1;
    }

    /**
     * https://www.youtube.com/watch?v=UzVQ9zRVsWg
     * if i is a factor, then n/i is also a factor.
     * so start from 1 till sqrt(n),
     * then start from sqrt(n) till 1, but inthis loop, add n/i
     */
    // https://leetcode.com/problems/the-kth-factor-of-n
    public int kthFactor1(int n, int k) {
        for(int i =1; i*i<n; i++){
            if(n%i == 0) {
                k--;
                if(k==0) return i;
            }
        }
        
        for(int i = (int)Math.sqrt(n); i>=1; i--){
            if(n%i == 0){
                k--;
                if(k == 0) return n/i;
            }
        }
        return -1;
    }


    // after sorting, the smallest -ve numbers will be at 0 and 1, their product can be +ve, so 
    // those two are multiplied with largest num nums[n-1];
    // https://leetcode.com/problems/maximum-product-of-three-numbers
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int a = nums[n-1]*nums[n-2]*nums[n-3];
        int b = nums[0]*nums[1]*nums[n-1];
        return Math.max(a, b);
    }


    //////////// MAX PROD SUBARRAY
    // kadane
    // https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
    // maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i]; 
    // [-2,-1]
    // https://leetcode.com/problems/maximum-subarray    
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) return nums[0];
        int sum = 0; int max = Integer.MIN_VALUE;
        for(int i : nums){
            sum+=i;
            
            max = Math.max(max, sum);
            if(sum<0) sum = 0;
            
            
        }
        return max;
    }

    // https://leetcode.com/explore/interview/card/
    // top-interview-questions-hard/121/dynamic-programming/860/
    /** 
     * max prod in contiguous subarray 
     * we basically hold 4 variables and an ans var
     * prev is assigned to curr,
     * max is max of prev*arr[i], arr[i];
     * same for min
    */
    // https://www.youtube.com/watch?v=vtJvbRlHqTA
    int maxProdSubarray(int[] arr){
        if(arr.length == 0) return -1;

        int curr_min = arr[0];
        int curr_max = arr[0];
        int prev_min = arr[0];
        int prev_max = arr[0];
        int max = arr[0];

        for(int i =1; i<arr.length; i++){
            curr_max = Math.max(prev_max*arr[i], Math.max(prev_min*arr[i], arr[i]));
            curr_min = Math.min(prev_max*arr[i], Math.min(prev_min*arr[i], arr[i]));
            // update ans
            max = Math.max(max, curr_max);
            // reassign prev
            prev_max = curr_max; prev_min = curr_min;
        }
        System.out.println("max prod subarray "+max);
        return max;
    }


    /** 
     * POINTS :
     * 1 BINARY CARRY = SUM/2;
     * 2 SUM IS CALCULATED FOR THE CURRENT 2 DIGITS
     * SUM = (CARRY + DIGITOF + DIGIT OF B)%2
     * 
    */
    // https://leetcode.com/problems/add-binary/
    public String addBinary(String a, String b) {
        
        int i = a.length()-1, j = b.length()-1;
        int carry =0;
        
        StringBuilder res = new StringBuilder(); 

        while(i>=0 || j>=0){
            int sum = carry;
            
            if(i>=0) sum+= a.charAt(i) - '0';
            if(j>=0) sum+= b.charAt(i) - '0';
            
            res.append(sum%2);
            carry = sum/2;
        }
        
        if(carry!=0) res.append(carry);
        return res.reverse().toString();
        // String class in Java does not have reverse() method
    }

    /** 
     * SIMILAR AS ABOVE
     * SUM = CARRY
     * SUM+= A'S DIGIT
     * SUM+= A'S DIGIT
     * APPEND SUM%10
     * CARRY = SUM/10
    */
    // public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //     ListNode p = l1;
    //     ListNode q = l2;
        
    //     int carry = 0; String res = "";
    //     while(p!=null || q!=null){
    //         int sum = carry;
    //         if(p!=null) sum+=p.val;
    //         if(q!=null) sum+=q.val;
    //         // System.out.println(res);
    //         res += (sum%10);
    //         carry = sum/10;
    //         if(p!=null) p = p.next; 
    //         if(q!=null) q = q.next;
    //     }
    //     if(carry!=0) res += carry;
    //     ListNode r = new ListNode(res.charAt(0)-'0');
        
    //     ListNode ans = r;
    //     for(int i =1; i<res.length(); i++){
    //         // System.out.println(res.charAt(i));
            
    //         r.next = new ListNode(res.charAt(i)-'0');
    //         r = r.next;
    //     }
    //     return ans;
    // }

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int count = 1; int i =0;
        while(candies>=count){
            res[i%num_people] += count;       
            i++;
            candies-=count++;
        }
        if(candies!=0) res[i%num_people] += candies;
        
        return res;
    }

    /** 
     * POINTS :
     * 1 MARK -VE, ONLY IF +VE
     * 2 ITERATE AGAIN AND ADD INDEX+1 WHERE nums[index] > 0
     * 
     * imp : nums[Math.abs(i)-1]
    */
    // https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for(int i : nums){
            if(nums[Math.abs(i)-1]>0) nums[Math.abs(i)-1]*=-1; // 1
        }
        
        for(int i=0; i<n; i++){
            if(nums[i]>0) res.add(i+1); // 2
        }
        return res;
    }

    // similar to stairs
    // https://www.geeksforgeeks.org/count-ofdifferent-ways-express-n-sum-1-3-4/
    int countWays(int n) { 
        int dp[] = new int[n + 1]; 
  
        // base cases 
        dp[0] = dp[1] = dp[2] = 1; 
        dp[3] = 2; 
  
        // iterate for all values from 4 to n 
        for (int i = 4; i <= n; i++) 
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4]; 
  
        return dp[n]; 
    } 
    

    // imp : add to set, remove from set
    // https://leetcode.com/problems/intersection-of-two-arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i : nums1) set.add(i);
        for(int i : nums2){
            if(set.contains(i)) {
                list.add(i);
                set.remove(i);
            }
        }
        
        int[] res = new int[list.size()]; int index = 0;
        for (int i : list) {
            res[index++] = i;
        }
        return res;
    }


    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n<2) return 0;
        int min = prices[0]; int profit = 0;
        for(int i =1; i<n; i++){
            if(prices[i]<min) min = prices[i];
            else profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }

    // here we can buy and sell on the same day, so just check if next
    // price is greater; 
    // [1,2,3,4,5] buy on 1, sell on 2; buy on2, sell on 3
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int profit = 0;        
        int index = 0;
        
        for(int i =0; i<n-1; i++){
            if(prices[i]<prices[i+1]) profit += prices[i+1] - prices[i];
        }
        
        return profit;
    }

    // mismatch b/w my ans and gfg's
    // reset min and max when min is encountered
    // if max is found, update diff
    // https://practice.geeksforgeeks.org/problems/maximum-difference-1587115620/1#
    int findMaxDiff(int arr[], int n){
	    int min = Integer.MAX_VALUE; int max = Integer.MIN_VALUE;
	    int diff = 0;
	    
	    for(int i = 0; i<n; i++){
	        if(min > arr[i]){
	            min = arr[i]; max = arr[i];
	        }
	        if(arr[i]>max){
	            max = arr[i];    
	            diff = Math.max(diff, max-min);
	        }
	 
        }
        System.out.println("max diff is "+ diff);
	    return diff;
    }


    /** 
     * if mod == 0 add one to numBottles
    */
    // https://leetcode.com/problems/water-bottles/
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = 0; int empty = 0;
        while(numBottles>0){
            count++; empty++;
            if(empty%numExchange == 0) numBottles++;
            numBottles--;
        }
        return count;
    }

    // https://www.geeksforgeeks.org/sort-an-almost-sorted-array-where-only-two-elements-are-swapped/
    void sortByOneSwap(int arr[]){
        for(int i =1; i<arr.length; i++){
            if(arr[i]<arr[i-1]){
                System.out.println("i-1"+arr[i-1]);

                for(int k = i; k<arr.length-1; k++){
                    if(arr[k]>arr[k+1]) {
                        
                        System.out.println("k+1 "+arr[k+1]);
                        int temp = arr[i-1];
                        arr[i-1] = arr[k+1];
                        arr[k+1] = temp;
                        // swap(arr, i, k+1);
                    }
                }
            }
        }
        for(int i : arr) System.out.print(i+", ");
    }
    
    // https://www.youtube.com/watch?v=UcTKk2y_3s4
    // https://leetcode.com/problems/excel-sheet-column-title/
    public String convertToTitle(int n) {
        String result = "";
        while(n>0){
            int a = (n-1)%26;
            char c= (char)('A'+a);
            result = c+result;
            n = (n-1)/26;
        }
        return result;
    }

    // https://leetcode.com/problems/lemonade-change/
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        
        int c5 = 0; int c10 =0; 
        for(int i :bills){
            if(i==5) c5++;
            else if(i == 10) {
                c10++;
                if(c5>=1) c5--; 
                else return false;
            }
            else {
                if(c5>=1 && c10>=1){
                    c5--; c10--;
                } else if(c5>=3) c5-=3;
                else return false;
            }
            // System.out.println("c5 "+c5 + " c10 "+c10);
        }
        return true;
    }

    // https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        float a = 0; float b = 0; 
        float c= tomatoSlices, d = cheeseSlices;
        // 4*a+2*(cheeseSlices - a) = tomatoSlices;
        a = (c/2 - d); b = d - a;
        
        ArrayList<Integer> res= new ArrayList<>();
        if(a!=(int)a || b!=(int)b || (a*b)<0) return res;
        res.add((int)a); res.add((int)b);
        return (List<Integer>) res;
    }

    // https://leetcode.com/problems/water-and-jug-problem/

    // Find power(a, n) iteratively without extra space 
    // https://practice.geeksforgeeks.org/problems/abset-25327/1
    long power(int a, long b){
        //complete the function here
        if(a==0 || b==0) return a;
        if(b==1) return a;
        int base = a; int mul = a;
        int k =1;
        while(k<b){
            base*=mul;
            k++;
        }
        System.out.println("base "+base);
        return Long.valueOf((base)%(10^9+7));
    }

    // For a >= b >= c, a,b,c can form a triangle if a < b + c.
    // https://leetcode.com/problems/largest-perimeter-triangle/
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        if(n<3) return 0;
        
        for(int i=n-1; i>1; i--){
            if (A[i] < A[i - 1] + A[i - 2]) return (A[i] + A[i - 1] + A[i - 2]);
        }
        return 0;
    }

    // https://leetcode.com/problems/path-crossing
    public boolean isPathCrossing(String path) {
        HashSet<String> set = new HashSet<>();
        set.add("00");
        int n = path.length(); 
        int x =0; int y =0; 
        
        for(int i =0; i<n; i++){
            String point = "";
            if(path.charAt(i) == 'E') x = x+1;
            if(path.charAt(i) == 'W') x = x-1;
            if(path.charAt(i) == 'N') y = y+1;
            if(path.charAt(i) == 'S') y = y-1;
            point = x+""+y;
            // System.out.println("point " + point);
            // System.out.println("x " + x +" y "+y);
            if(set.contains(point)) return true;
            set.add(point);
        }
        return false;
    }

    /** 
     * Arrays.sort() doesn't accept custom comparator, use Collections.sort
     * compare on the basis of a+b and b+a
     * also check if first char is 0, thrn return 0
    */
    // 3, 300
    // https://leetcode.com/problems/largest-number/
    int compare(int a, int b){
        String str1 = ""+a+b;
        String str2 = ""+b+a;

        // return Long.parseLong(str1) > Long.parseLong(str2)?-1:1;
        return str1.compareTo(str2)>0?-1:1;
    }
    
    public String largestNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i : nums) list.add(i);
        
        Collections.sort(list, (x,y)->{
            return compare(x,y);
        });
        
        // for(int i : list) System.out.print(i+", ");
        
        String res = "";
        for(int i : list) res+=i;
        if(res.charAt(0) == '0'){
            if(Long.parseLong(res) == 0) return "0";
        }
        return res;
    }

    /**
     * [1,7,9,9,8,3] 
     * index = 0
     * sort from index 2 till end
     * swap 7 with just larger to right
     * 
     * Why flip? 
     * Because we are searching for the first smaller el
     * that means all els till now are in descending order.
     * 
     * find first smaller, flip from smaller+1, 
     * swap smaller with first larger
    */
    // https://leetcode.com/problems/next-permutation
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n<2) return;
        
        int index = -1; int i = n-1;
        while(i>0){
            if(nums[i]>nums[i-1]) {
                index = i;
                break;
            }
            i--;
        }
        
        // all desc, flip from index till end
        if(index == -1) flip(nums, index, n-1);
        else{
            flip (nums, index, n-1);
            for(i = index+1; i<n; i++){
                if(nums[i]>nums[index-1]) {
                    swap(nums, index, i);
                    break;
                }   
            }
        }
    }
    
    void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    void flip(int[] arr, int start, int end){
        while(start <= end){
            swap(arr, start++, end--);
        }
    }

    // see above; 3 step process
    // find index; flip from index till end
    // swap index-1 with first greater
    // 230241, 12443322
    // https://leetcode.com/problems/next-greater-element-iii/
    public int nextGreaterElement(int N) {
        if( N == 1999999999 || N == 2147483647) return -1;
        String str = ""+N;
        int n = str.length();
        if(n == 1) return -1;
        int[] res = new int[n];
        
        for(int i =0; i<n; i++) res[i] = str.charAt(i)-'0';
        
        int index = 0;
        
        for(int i = n-1; i>0; i--){
            if(res[i]>res[i-1]){
                // index is el just before the smallest 
                index = i;
                break;
            }  
            if(i == 1) return -1;
        }
        
        // Arrays.sort(res, index, n);
        // as array is in descending order till this index, flipping will be faster        
        flip(res, index, n-1);
        for(int i = index; i<n; i++){
            if(res[i]>res[index-1]) {
                swap(res, index-1, i);
                break;
            }
        }
        
        String s = "";
        for(int i : res) s+=i;
        return Integer.parseInt(s);
    }
    
    // imp : hi = m * n - 1;
    // mid = (lo + hi) / 2; midVal = matrix[mid/n][mid%n];
    // https://leetcode.com/problems/search-a-2d-matrix/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        int lo = 0; 
        int hi = m * n - 1;
        int mid = 0;
        
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            int midVal = matrix[mid/n][mid%n];
            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                lo = mid + 1;
            } else hi = mid - 1;
        }
        return false;
    }

    /** 
     * 1 scan from both sides consecutively
     * 2 from left to right, if ratings[i]>ratings[i-1] ratings[i] = ratings[i-1]+1
     * 3 from right to left, select max as lower rating can cause conflict
     * 
    */
    // https://leetcode.com/problems/candy
    public int candy(int[] ratings) {
        int n = ratings.length;
        int sum = 0;
        int[] res = new int[n];
        
        Arrays.fill(res, 1);
        
        for(int i =1; i<n; i++){
            if(ratings[i]>ratings[i-1]){
                res[i] = res[i-1]+1;
            }
        }
        
        for(int i =n-2; i>=0; i--){
            
            if(ratings[i]>ratings[i+1]){
                res[i] = Math.max(res[i+1]+1, res[i]);
            }
        }
        
        for(int i : res) sum+=i;
        return sum;
    }

    /* 
     * r^2 - x^2 must be a perfect sq
     * result = 4 for (r,0); (-r, 0); (0, r); (0-r);
     * 
     * for r = 5
     * result - 4 for (5,0)...
     * 3-> (3,4); (-3, 4); (3,-4); (-3, -4)
     * same for 4;
     * 
    */
    // trick to check perfect sq : take sqrt and then square to get back
    // https://www.geeksforgeeks.org/circle-lattice-points/
    int latticePoints(int radius){
        int result = 4;
        int rSquare = radius*radius;
        int ySquare = 0;
        for(int i =0; i < radius; i++){
            ySquare = rSquare - (i*i);
            int y = (int)Math.sqrt(ySquare);

            if(y*y == ySquare) result +=4;
        }
        return result;
    }


    // https://leetcode.com/problems/elimination-game
    public int lastRemaining(int n) {
        int start  = 0; boolean ltor = true;
        List<Integer> list = new ArrayList<>();
        for(int i =0; i<n; i++) list.add(i,i+1);
        
        while(list.size()>1){
            if(start >= list.size()) {
                Collections.reverse(list);
                start = 0;//
                continue;
            }
            if(start == list.size()-1) {// not size-1
                list.remove(start);
                // System.out.println(list);
                Collections.reverse(list);
                start = 0;//
                continue;
            } 
            // System.out.println(list+" "+start);
            list.remove(start);// removal reduces list size
            start++;
        }
        return list.get(0);
    }

    // go till N; if sum>N break; start++
    // https://leetcode.com/problems/consecutive-numbers-sum/
    public int consecutiveNumbersSum(int N) {
        int start = 1; int end = N;//(N+1)/2;
        int count = 0;
        
        while(start<=end){
            int sum = 0;
            
            for(int i = start; i<=N; i++){
                sum+=i;
                // System.out.println("sum "+sum);
                if(sum == N) {
                    count++;
                    break;
                }
                if(sum>N) break;
            }
            start++;
        }
        return count;
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

    // why -2? we are taking into acount the oundary of the upper cell
    // -2 one for each cell
    // https://leetcode.com/problems/island-perimeter/
    public int islandPerimeter(int[][] grid) {
        int m = grid.length; int n = grid[0].length;
        int perimeter = 0;
        
        for(int i =0; i<m; i++){
            for(int j=0; j<n; j++){
                
                if(grid[i][j] == 1) {
                    perimeter += 4;
                    
                    if(i>0 && grid[i-1][j] == 1) perimeter -= 2;
                    if(j>0 && grid[i][j-1] == 1) perimeter -= 2;
                }
            }
        }
        return perimeter;
    }
    
    /** 
     * 1 IF PEEK IS LARGER, REMOVE
     * 2 ADD CURRENT AFTER REMOVAL
     * 3 CHECK IF COUNT<K
     * 4 REMOVE PRECEDING ZEROES
     * 
     * check if first char is 0, keep removing till 0 is there
     * add remaining to res and return
     * 
    */
    // https://leetcode.com/problems/remove-k-digits/
    // leading zeroes
    // "10", 1
    public String removeKdigits(String str, int k) {
        Deque<Character> q = new LinkedList<>();
        
        for(int i =0; i<str.length(); i++){
            while(q.size()!=0 && q.getLast()>str.charAt(i) && k-->0) q.removeLast();
            q.addLast(str.charAt(i));
        }
        
        // System.out.println(q.size());
        // what if k!=0?
        while(k-->0) q.removeLast();
        
        String res = "";
        while(q.size()!=0 && q.getFirst() == '0'){
            q.removeFirst();
        }
        if(q.size() == 0) return "0";
        
        while(q.size()!=0) res+=q.removeFirst();
        return res;
    }


    /** 
     * POINTS :
     * 1 USE DP TABLE
     * 2 res = m+n + (sum of all els apart from first row and col)
     * 3 dp[i][j] = dp[i-1][j] + dp[i][j-1];
    */
    // https://math.stackexchange.com/questions/3396050/magnificent-necklace-combinatorics-problem#:~:text=
    // You%20have%20to%20make%20a,be%20formed%20with%20such%20condition.
    int numberOfNecklaces(int n, int lm, int hm){
        int m = hm-lm+1;
        int[][] dp = new int[n][m];
        int res = m+n;

        Arrays.fill(dp[0], 1);
        for(int i =0; i<n; i++){
            dp[i][0] = 1;
        }

        for(int i =1; i<n; i++){
            for(int j = 1; j<m; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                res+=dp[i][j];
            }
        }
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                System.out.print(dp[i][j]+", ");
            }
            System.out.println();
        }
        return res;
    }

    // use hashmap
    // https://practice.geeksforgeeks.org/problems/find-all-pairs-whose-sum-is-x5808/1
    public pair[] allPairs( long a[], long b[], long n, long m, long k) {
        // Your code goes here 
        int count = 0;
        List<Long> list = new ArrayList<>();
        HashMap<Long, Integer> map = new HashMap<>();
        
        for(long l : a){
            map.put(l ,map.getOrDefault(l ,0)+1);
        }
        
        for(long l : b){
            if(map.containsKey(k-l)){
                int x = map.get(k-l);
                for(int i=0; i<x; i++){
                    list.add(k-l); list.add(l); 
                }
            }
        }
        
        int index = 0;
        pair[] p = new pair[list.size()/2];
        for(int j =0; j<list.size(); j++){
            p[index++] = new pair(list.get(j), list.get(++j));    
        }
        return p;
    }
        
    // i tried creating an array and storing freqs
    // then find odd and even sum, why?
    // because moving from even indexes doesn't cost money
    // https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position
    public int minCostToMoveChips(int[] position) {
        int n = position.length, costOdd = 0, costEven = 0;
        for(int i: position){
            if(i%2 == 0) costEven++;
            else costOdd++;
        }
        
        return Math.min(costEven, costOdd);
    }


    // find h, m and diff. then check if>180
    // https://leetcode.com/problems/angle-between-hands-of-a-clock
    public double angleClock(int hour, int minutes) {
        boolean opp = false;
        if((hour<6 && minutes>30 ) || (hour>6 && minutes<30)) opp = true;
        double h = hour*30; 
        double m = minutes*6;
        
        h+= minutes*(0.5); 
        
        double diff = Math.abs(h-m);
        if(diff>180) return 360 -diff;
        return diff;
    }


    class Candy{
        int number, priority, maxDays, minDays;
        Candy(int a, int b, int c){
            this.number = a;
            this.priority = b;
            this.maxDays = c;
        }
    }
    // https://leetcode.com/discuss/interview-question/936510/
    // fleetx-online-assessment-help-needed-to-identify-the-problem-favorite-candy
    String[] candy(int[][] arr, int K, int[][] queries){
        String[] res =  new String[queries.length];
        Arrays.sort(arr, (x,y)->y[1] - x[1]);

        Candy[] cumulative = new Candy[arr.length];
        int index = 0, sum = 0; 
        for(int[] i : arr){
            cumulative[index++] = new Candy(index+1, i[1], sum+i[0]);
            sum+=i[0];
        }
        for(int i =0; i<cumulative.length; i++){
            cumulative[i].minDays = cumulative[i].maxDays/K;
        }

        index = 0;
        int index1 = 0;
        for(int[] i : queries){
            // find index of candy
            for(Candy[] c : cumulative){
                if(c.number == i[0]){
                    if(c.minDays<i[1] && i[1]<c.maxDays) res[index1] = "YES";
                    else res[index1] = "NO";
                }
            }
            index1++;
        }

        return res;
    }

    // https://leetcode.com/problems/maximum-swap/
    // https://leetcode.com/problems/h-index/discuss/70810/A-Clean-O(N)-Solution-in-Java
    // https://leetcode.com/problems/power-of-four
    
    public static void main(String[] args) {
        Mathprob math = new Mathprob();
        // math.power(99, 9);
        // math.isPerfectSquare(2);
        // math.isPerfectSquare(81);
        // math.numberOfNecklaces(3, 6, 9);
        int[] diff = new int[]{
            87, 78, 16, 94, 36, 87, 93, 50, 22, 63, 28, 91, 60, 64, 27, 41, 27, 73, 37, 12, 69, 
            68, 30, 83, 31, 63, 24, 68, 36, 30, 3, 23, 59, 70, 68, 94, 57, 12, 43, 30, 74, 22, 
            20, 85, 38, 99, 25, 16, 71, 14, 27, 92, 81, 57, 74, 63, 71, 97, 82, 6, 26, 85, 28,
            37, 6, 47, 30, 14, 58, 25, 96, 83, 46, 15, 68, 35, 65, 44, 51, 88, 9, 77, 79, 89};
        // Correct output : 79, 96
        // math.findMaxDiff(diff, diff.length);

        int[] arr2Swap = new int[]{10, 20, 60, 40, 50, 30} ;
        // math.sortByOneSwap(arr2Swap);

        long A[] = {1, 2, 4, 5, 7},
        B[] = {5, 6, 3, 4, 8} ,
        X = 9; 
        math.allPairs(A, B, 5, 5, X);

        int[] arr23 = {1,2};
        int[] arr34 = {3,4};
        System.out.println(Arrays.equals(arr23, arr34));
    }

}