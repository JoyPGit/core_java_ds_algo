package Java_DS_Algo;
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

    // https://leetcode.com/problems/water-bottles/
    public int numWaterBottles(int numBottles, int numExchange) {
        int i = 0; 
        while(i <= numBottles){
            if(i%numExchange==0) {
                numBottles++;
            }
            i++;
        }

        // for(i =1; i<=numBottles; i++){
        //     if(i%numExchange==0) numBottles++;
        // }
        // System.out.println(numBottles);
        return numBottles;
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
     * [1,7,9,9,8,3] 
     * index = 1
     * sort from index 3 till end
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
                index = i-1;
                break;
            }
            i--;
        }
        
        // all desc, flip to smallest
        if(index == -1) flip(nums, index+1, n-1);
        else{
            flip (nums, index+1, n-1);
            for(i = index+1; i<n; i++){
                if(nums[i]>nums[index]) {
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
    
    // https://leetcode.com/problems/maximum-swap/
    // https://leetcode.com/problems/h-index/discuss/70810/A-Clean-O(N)-Solution-in-Java
    // https://leetcode.com/problems/power-of-four
    
    public static void main(String[] args) {
        Mathprob math = new Mathprob();
        // math.power(99, 9);
        // math.isPerfectSquare(2);
        math.isPerfectSquare(81);
    }

}