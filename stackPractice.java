import java.util.*;


public class StackPractice {
    
    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(i==arr.length-1) {
                System.out.println(arr[i]+";");
                System.out.println();
            } 
            else System.out.print(arr[i] + ", ");
        }
    }
    /** POINTS
     * 1 FOR REMOVING DUPLICATES USE STACK
     * 2 NEXT GREATER EL (DAILY TEMP - STORE INDEX)
     * 3 LARGEST RECT IN HISTOGRAM
     * 4 TREE TRAVERSAL ITERATIVE
     * 5 EVALUATE POLISH(REVERSE) NOTATION 
     * 6 REMOVE K DIGITS
     * 7 ASTEROID COLLISION
     * 8 FIND MIN, MIDDLE IN O(1)
     * 9 PARENTHESES - VALID, BALANCE, REVERSE
     * 10 REVERSE
     * 11 SOMETIMES SHRINKING TECHNIQUE IS USED, AS IN 
     * SLIDING WINDOW, FOR AND WHILE (to shrink).
     * 12 ALSO CUSTOM CLASS CAN BE USED (SEE DAILY TEMP)
     */

    /** points:
     * 1 first remove then add
     * 2 tricky case is when list size is 0 and list.getLast() throws error
     * use or(||); (list.size()==0 || list.getLast()!=S.charAt(i))
     * 3 no need to get excited when abbcc comes 
     * REMEMBER till last index the list has been fixed and all duplicates have been 
     * removed by the while loop, so when c comes bb will have been rmeoved
     */

    String removeDuplicates(String S) {
        Deque<Character> list = new LinkedList<>();
        list.addLast(S.charAt(0));
        
        for(int i=1; i<S.length(); i++){
            // if(list.size()==0 || list.getLast()!=S.charAt(i)) list.addLast(S.charAt(i));
            // else if (list.size()!=0 && list.getLast()==S.charAt(i)) list.removeLast();

            if(list.size()!=0 && list.getLast()==S.charAt(i)) list.removeLast();
            else list.addLast(S.charAt(i));
        }
        
        System.out.println(list);
        String res = "";
        while(list.size()!=0){
            res+=list.removeFirst();
        }
        System.out.println(res);
        
        return res;
            
    }

    // https://leetcode.com/problems/next-greater-element-i/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length; int n2 = nums2.length;
        if(n1==0) return new int[]{};
        
        int[] res = new int[n1];
        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> q = new LinkedList<>();
        
        int curr;
        q.addLast(nums2[0]);
        for(int i =1; i<n2; i++){
            while(q.size()!=0 && q.getLast()<nums2[i]){
                curr = q.removeLast();
                map.put(curr, nums2[i]);
            }
            q.addLast(nums2[i]);
        }
        
        while(q.size()!=0) map.put(q.removeLast(), -1);
        
        for(int i =0; i<nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }
        
        return res;
    }
    // https://leetcode.com/problems/next-greater-element-ii/

    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    // https://leetcode.com/problems/remove-k-digits/
    void stockSpan(int[] arr){
        Deque<Integer> stock = new ArrayDeque<Integer>();
        int[] span = new int[arr.length];

        span[0]=1;
        stock.add(0);
        for(int i =1; i<arr.length; i++){
            while(!stock.isEmpty() && arr[stock.peek()]<arr[i]) stock.pop();

            if(stock.isEmpty()) span[i] = i+1;
            else {
                System.out.println("peek "+stock.peek());
                span[i] = i - stock.peek();
            }
            print1DMatrix(span);
            stock.push(i);
        }
        print1DMatrix(span);
    }

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
                list.removeLast();
            }
            
            list.add(nums[i]);
            System.out.println(list);
            res[index++] = list.getFirst();
        }
        print1DMatrix(res);
        return res;
    }

    /**
     * AMAZON
     * https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
     *  Input: D        Output: 21
        Input: I        Output: 12
        Input: DD       Output: 321
        Input: II       Output: 123
        Input: DIDI     Output: 21435
        Input: IIDDD    Output: 126543
        Input: DDIDDIID Output: 321654798 
     */

    void printPattern (String s){
        char[] charArray = s.toCharArray();
        System.out.print(charArray);
        Deque<Integer> intStack = new LinkedList<>();
        Deque<Integer> intStackPopHolder = new LinkedList<>();

        int counter = 0;

        boolean first = true;
        for(char c:charArray){

            System.out.print(c+" ");
            System.out.println(intStack);
            if(c == 'I'){
                counter++;
                if(first){
                    intStack.push(1);
                    intStack.push(2);
                    first = false;
                    counter++;
                } else{
                    intStack.push(counter);
                }
                

            } else if(c == 'D'){
                counter++;
                System.out.print("counter "+counter);
                if(first){
                    intStack.push(2);
                    intStack.push(1);
                    first = false;
                    counter++;
                } else{
                    while(!intStack.isEmpty() && intStack.peekFirst() != (counter-1)){
                        intStackPopHolder.push(intStack.pop());
                    }
                    int holeder = intStack.pop();
                    intStack.push(counter);
                    intStack.push(holeder);
                    while(!intStackPopHolder.isEmpty()){
                        intStack.push(intStackPopHolder.pop());
                    }
                    System.out.println(" line 140 " +intStack);
                }
            }
        }

        while(!intStack.isEmpty()){
            System.out.print(intStack.pop());
        }
    }

    // https://leetcode.com/problems/evaluate-reverse-polish-notation/

    void longestCorrectBracketSubsequence(String s){
        char[] charArray = s.toCharArray();
        int counter = 0; int maxCounter = 0; int max = 0;
        for(char c:charArray){
            if(c=='('){
                counter++;
            } else{
                maxCounter = counter;
                counter--;
            }
            if(counter <0){
                maxCounter =0;
            }
            if(max >maxCounter) max = maxCounter;
        }
        System.out.println("max is "+max);
    }

    void findMin(int[] nums){
        Deque<Integer> list = new LinkedList<>();
        Deque<Integer> minList = new LinkedList<>();
        list.addLast(nums[0]); minList.addLast(nums[0]);
        for(int i =1; i<nums.length; i++){
            if(nums[i]<minList.getLast()){
                minList.addLast(nums[i]);
            }else {
                minList.addLast(minList.getLast());
            }
            list.addLast(nums[i]);
        }
        System.out.println("list is "+list);
        System.out.println("minList is "+minList);
    }

    /** 
     * POINTS :
     * 1 BRUTE FORCE SOLUTION
     * 2 PASSES 87 TEST CASES
     * 3 
     * O(n) stack solution exists, check if possible
     * */
    // https://leetcode.com/problems/sum-of-subarray-minimums/
    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        if(n==0) return 0;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        int sum = 0;
        
        for(int l =1; l<=n; l++){
            int min = Integer.MAX_VALUE;
            for(int i = 0; i+l-1<n; i++){
                int j = i+l-1;
                System.out.println("i "+i+" j "+j);
                sum+=findMin(A, i, j);
            }
            // sum+=min;
            System.out.println("sum "+sum);

        }
        utilCustom.Utility.printListOfLists(res);
        for(int i =0; i<res.size(); i++){
            Collections.sort(res.get(i));
            if((res.get(i)).size()==0) continue;
            sum+=(res.get(i)).get(0);
        }
        return sum;
    }

    int findMin(int[] arr, int start, int end){
        int min = Integer.MAX_VALUE;
        for(int i = start; i<=end; i++){
            min = Math.min(min, arr[i]);
        }
        return min;
    }
    
    // MINOR MODS NEEDED
    // https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        int[] res; boolean same= false; boolean greater = false;
        Deque<Integer> list = new LinkedList<>();
        list.add(asteroids[0]);
        for(int i =1; i<n; i++){
            int curr = asteroids[i];
            same = false; greater = false;
            // if((list.getLast()>0 && curr>0)
            // || (list.getLast()<0 && curr<0)
            // || (list.getLast()<0 && curr>0)) list.add((curr));
            // else{
                System.out.println("in else");

                while(list.size()!=0 && list.getLast()>0 && curr<0){
                    int popped = list.removeLast();
                    // System.out.println("new last "+list.getLast());
                    System.out.println("popped "+popped);
                    if(Math.abs(popped)<Math.abs(curr)){
                        System.out.println("line 89");
                        // list.add(curr);
                    }
                    else if(Math.abs(popped) == Math.abs(curr)){
                        System.out.println("line 93");
                        same = true;
                        break;
                    }
                    else if(Math.abs(popped) > Math.abs(curr)){
                        System.out.println("line 98");
                        list.add(popped);
                        greater = true;
                        break;
                    }
                }
                if(!same && !greater)list.add(curr);
            // }
            System.out.println("for i "+i+" "+list);    
        }
        System.out.println(list);
        res = new int[list.size()];
        int i =0;
        while(list.size()!=0){
            res[i] = list.removeFirst();
            i++;
        }
        print1DMatrix(res);
        return res;
    }

    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/submissions/
    public int minAddToMakeValidParentheses(String S) {
        Deque<Character> list = new LinkedList<>();
        
        char[] ch = S.toCharArray();
        
        for(int i =0; i<ch.length; i++){
            if(list.size()==0) list.add(ch[i]);
            else{
                if(list.getLast() == '('){
                    if(ch[i] == ')') list.removeLast();
                    else if(ch[i] == '(') list.addLast(ch[i]);
                }
                else list.addLast(ch[i]);                
            }
        }
        
        return list.size();
    }
    // https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
    
    // https://leetcode.com/problems/next-greater-element-i/


    // https://leetcode.com/problems/simplify-path/
    // https://leetcode.com/problems/remove-k-digits/
    // https://leetcode.com/problems/mini-parser/
    // https://leetcode.com/problems/decode-string/
    // https://leetcode.com/problems/next-greater-element-ii/

    // https://leetcode.com/problems/daily-temperatures/

    /** POINTS:
     * 1 CUSTOM CLASS TO HOLD index and value
     * 2 when popping store difference of current index and popped index
     */
    class Temperature{
        int val; int index;
        Temperature(int v, int i){
            this.val = v;
            this.index = i;
        }
    }
    public int[] dailyTemperaturesCustom(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Temperature> list = new LinkedList<>();
        list.add(new Temperature(nums[0], 0));
        
        for(int i =1; i<n; i++){
            while(list.size()!=0 && list.getLast().val<nums[i]){
                Temperature x = list.removeLast(); 
                res[x.index] = i- x.index;
            }
            list.add(new Temperature(nums[i], i));
        }
        return res;
    }

    //TRICK IS TO STORE INDEXES DIRECTLY
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }


    // https://leetcode.com/problems/exclusive-time-of-functions/
    // https://leetcode.com/problems/sum-of-subarray-minimums/
    // LOOK FOR DIFFERENT SOLN, WAY TOO MUCH OPTIMIZED
    String reverseParentheses(String s) {
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(char c: s.toCharArray()){
            if(c =='('){
                stack.addFirst(sb.toString());
                sb = new StringBuilder();
            } else if(c ==')'){
                sb = sb.reverse();
                sb.insert(0,stack.pop());
            } else {
                sb.append(c);
            } 
            // System.out.println(stack);
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }

    // https://leetcode.com/problems/longest-valid-parentheses/
    /**  
     * DEL ER(PEEK) : DEL ERP
     * EMPTY, LEFT, ELSE REMOVE, ELSE, PEEK 
     * left= 0; max = 0;
     * if ( add index
     * if ) -> if empty left = j+1;
     *      -> else pop -> empty : max, j-left+1     
     *                  -> not : max j-getLast
    */                  
    public int longestValidParentheses(String s) {
        Deque<Integer> q = new LinkedList<Integer>();
        int max=0;
        int left = 0;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') q.addLast(j);            
            else {
                if (q.isEmpty()) left=j+1;
                else{
                    q.removeLast();
                    if(q.isEmpty()) max=Math.max(max,j-left+1);
                    else max = Math.max(max,j-q.getLast());
                }
            }
        }
        return max;
    }

    // https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
    // discuss/?currentPage=1&orderBy=most_votes&query=

    // https://leetcode.com/problems/reorganize-string/
    public String decodeString(String s) {
        int n = s.length();
        if(n==1) return s;
        
        Deque<Integer> num = new LinkedList<>();
        Deque<Character> letter = new LinkedList<>();
        
        String res= "";
        
        for(int i =0; i<n; i++){
            if(Character.isDigit(s.charAt(i))){
                int count = check(s, num, i);
                if(count>1){
                    while(count-->0)i++;
                }
                // num.addLast(Integer.parseInt(String.valueOf(s.charAt(i))));
            }
            else if(s.charAt(i) == '['){
                letter.addLast(s.charAt(i));
            }
            else if(s.charAt(i) == ']'){
                process(letter, num);
            }
            else if(Character.isLetter(s.charAt(i))){
                letter.addLast(s.charAt(i));
            }
        }

        // for(int i =0; i<=letter.size(); i++){
        while(letter.size()!=0){
            res+=letter.removeFirst();
        }

        System.out.println("final "+res);
        return res;
    }

    int check(String s, Deque<Integer> num, int i){
        String number = ""; int c = 0;
        int index = i;
        while(s.charAt(index)!='[') {
            number+=s.charAt(index); index++; c++;
        }
        System.out.println("number "+number);
        System.out.println("count "+c);
        num.addLast(Integer.parseInt(number));
        return c;
    }
    void process(Deque<Character> letter, Deque<Integer> number){
        String res = "";
        while(letter.size()!=0 && letter.peekLast()!='['){
            res = letter.removeLast()+ res;
        }
        if(letter.size()!=0)letter.removeLast();
        int n = number.removeLast();
        System.out.println("num "+n);
        for(int i =0; i<n; i++){
            for(char s : res.toCharArray()){
                letter.addLast(s);
            }
        }
        System.out.println("letter "+letter);
    }

    /** 
     * POINTS :1
     * 1 REVERSE DOESN'T TAKE ANY PARAM, INSERT TAKES ONE
     * 2 INSERT AT BOTTOM TAKES AN INTEGER AND ADDS AT BOTTOM, 
     * SAME PARAM IS PASSED TILL STACK IS EMPTY. 
     * 3 REVERSE RETURNS WHEN STACK IS EMPTY
     * 
     * r(){
     *   x = pop(); r(); i(x);
     * }
     * 
     * i(x){
     *   y = pop(); i(x); add(y); 
     * }
     * 
    */
    void reverseStackUsingRecursion(Deque<Integer> stack){
        if(stack.isEmpty()) return;
        int top = stack.removeLast();
        reverseStackUsingRecursion(stack);
        insertAtBottom(stack, top);
    }

    void insertAtBottom(Deque<Integer> stack, int num){
        if(stack.isEmpty()) {
            stack.addLast(num); return;
        }
        int top = stack.removeLast();
        insertAtBottom(stack, num);
        stack.addLast(top);
    }

    // https://leetcode.com/problems/sum-of-subarray-minimums/
    // https://leetcode.com/problems/maximal-rectangle/

    public static void main(String[] args) {
        StackPractice stack = new StackPractice();

        String dup = "ibdyigbklifapflhpafjstizipojdrsykvskxtzadkdblmikwlofjiujlnpr";
        // stack.removeDuplicates(dup);

        int[] price = 
        // { 10, 4, 5, 90, 120, 80 }; 
        {100, 80,60,70,60,75,85};
        // stack.stockSpan(price);

        int[] nums = // {7, 2, 4};
        {9,10,9,-7,-4,-8,2,-6};

                // { 1, -1 };
        // {1,3,-1,-3,5,3,6,7};
        int k = 5;//1;// 3;//2
        // stack.maxSlidingWindowStack(nums, k);


        int[] stackMin = {2,3,1,4,5,2};
        // stack.findMin(stackMin);

        int[] asteroids = {1,-2,-2,-2};
        // {-2,-2,1,1,1,-2};
        // {10, 2, -5};
        // {5,-5};
        // {-2, -1, 1, 2};
        // stack.asteroidCollision(asteroids);

        String bracketReverse = "(ed(et(oc))el)";//co octe leetcode 
        // stack.reverseParentheses(bracketReverse);

        String decode = "100[leetcode]";// "3[a2[c]]";//"3[a]2[bc]";
        // stack.decodeString(decode);

        Deque<Integer> stackToReverse = new LinkedList<>();
        for(int i =0; i<5; i++) stackToReverse.addLast(i);
        System.out.println(stackToReverse);
        stack.reverseStackUsingRecursion(stackToReverse);
        System.out.println(stackToReverse);
    }

}