package Java_DS_Algo;
import java.util.*;

public class StackPractice {
    
    /** 
     * k-digits, sliding window max
     * LARGEST RECT, VALID PARENTHESIS
     * REVERSE USING RECURSION, STOCK SPAN
     * DECODE STRING, SUM OF SUBARRAY MINS
     * 
     * TEMPLATES :
     * 1 ALWAYS STORE INDEX
     * BASIC TEMPLATE
     * for(int i =0; i<k; i++){
            while(q.size()!=0 && nums[i] > nums[q.getLast()]){
                q.removeLast();
            }
            q.addLast(i);
        }
     * 
     * 
     * */
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

    /** 
     * https://www.youtube.com/watch?v=vXPL6UavUeA
     * STEPS:
     * 1 ADD OPERAND TO RES
     * 2 ADD OPERATOR TO STACK IF LOWER OF SIMILAR PRECEDENCE
     * 3 IF HIGHER, POP TILL LOWER IS FOUND AND ADD TO RES
     * 4 OPEN BRACKET HAS LOWEST PRIORITY
     * 5 IF CLOSED BRACKET, POP EVEVYTHING TILL OPEN BRACKET AND ADD TO RES
     * 
     * 
     * A+B/C*D+E-F; ABC/DE+*+F-
    */
    String infixToPostfix(String str){
        int n = str.length();
        String res = "";
        Deque<Character> q = new LinkedList<>();

        for(int i =0; i<n; i++){
            if(Character.isLetter(str.charAt(i))  // 
            // str.charAt(i) != '+' || str.charAt(i) != '-'
            // || str.charAt(i) != '*' || str.charAt(i) != '/'
            ) res+=str.charAt(i);
            else{
                if(str.charAt(i) == ')'){
                    while(q.size()!=0 && str.charAt(i)!='(') res+=q.removeLast();
                    if(q.size()!=0 && q.getLast() == '(')q.removeLast();
                    continue;
                }
                while(q.size()!=0 &&  // 
                str.charAt(i) != '(' &&
                compareOperators(q.getLast(), str.charAt(i)) >= 0) {
                    if(q.getLast() == '(') {
                        q.removeLast();
                        continue;
                    }
                    res+=q.removeLast();
                }

                q.addLast(str.charAt(i));
            }
            System.out.println("res "+res);
        }
        System.out.println("infix to postfix "+ res);
        return res;
    }

    // if greater return true, needs to be popped
    int compareOperators(char a, char b){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('(', 1); 
        map.put('+', 2); map.put('-', 2);
        map.put('*', 3); map.put('/', 3);
    
        return map.get(a) - map.get(b);
    }

    // Microsoft
    /**
     * USE STACK TO COMPARE THE INCOMING EL
     * IF SAME COMPARE COST
     * ELSE ADD TO STACK
     */
    class Cost{
        char ch; int cost;
        Cost(char c, int i){
            this.ch = c; this.cost = i;
        }
    }
    public int removeDuplicateCharCost(String S, int[] C){
        int cost = 0;

        Deque<Cost> q = new LinkedList<>();
        q.addLast(new Cost(S.charAt(0), C[0]));

        for(int i = 1; i<S.length(); i++){
            // if same cost will increase
            if(S.charAt(i) == q.getLast().ch) {
                // if cost at peek is lesser than curr, pop
                if(q.getLast().cost < C[i]){
                    cost+= q.removeLast().cost;
                    q.addLast(new Cost(S.charAt(i), C[i]));
                }
                // else don't add
                else cost+=C[i];
            }
            else q.addLast(new Cost(S.charAt(i), C[i]));
        }
        return cost;
    }


    /**  
     * POINTS :
     * 1 THIS IS DIFF FROM NEXT GREATER EL, WE NEED A MAPPING BETWEEN
     * FIRST ARR AND SECOND ARR, SO ADD ALL (EL, GREATER EL) PAIRINGS 
     * TO A MAP. 
     * 
     * 2 FINALLY TRAVERSE THE ARR1 AND FETCH GREATER AND ADD TO RES
    */
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


    /** 
     * POINTS :
     * 1 RES ARRAY IS OF SIZE n-k+1
     * 2 DON'T FORGET TO REMOVE THE OUTGOING EL
     * 3 TWO ITERATIONS ONE till k ELS
     * AND THEN FROM k TILL n
     * 4 HERE STORED INDEXES, CAN STORE ELS ALSO
     * 
    */
    // https://leetcode.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0, n = nums.length, index = 0;
        int[] res = new int[n-k+1];
        
        Deque<Integer> q = new LinkedList<>();
        
        for(int i =0; i<k; i++){
            while(q.size()!=0 && q.getLast()<nums[i]) q.removeLast();
            q.addLast(nums[i]);
        }
        
        res[index++] = q.getFirst();
        
        for(int i =k; i<n; i++){
            // remove outgoing index el
            if(q.getFirst() == nums[i-k]) q.removeFirst();
            while(q.size()!=0 && q.getLast()<nums[i]) q.removeLast();
            q.addLast(nums[i]);
            res[index++] = q.getFirst();
        }
        return res;
    }

    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    // https://www.techiedelight.com/inplace-remove-all-occurrences-ab-c-string/

    // char to int and vice cersa
    // n==1 and k==1
    // q size
    // preceding zeroes
    // 423, 14682, 865, 1423
    // if curr is smaller smaller then peeek but larger than below peek?
    // 100, 1
    /** 
     * 1 IF PEEK IS LARGER, REMOVE
     * 2 IF LARGER ADD
     * 3 IF K!=0 STILL REMOVE
     * 4 REMOVE PRECEDING ZEROES
     * 
    */
    // https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if(n<k) return num;
        if(n==k) return "0"; // 10, 2
        Deque<Character> q = new LinkedList<>();
        q.addLast(num.charAt(0));
        
        for(int i =1; i<n; i++){
            char curr = num.charAt(i);
            // if(q.getLast()<curr){
            //     q.addLast(curr);
            //     continue;
            // }
            while (k>0 && q.size()!=0 && q.getLast()>curr) {
                q.removeLast(); k--;
                // System.out.println(q);
            }
            q.addLast(curr);
        }
        
        // remaining
        while(k>0 && q.size()!=0) {
            q.removeLast(); // 10
            k--;
        }
        // if(q.size() == 1) return ""+q.getFirst(); // 10, 1
        // remove leading zeroes
        while(q.size()!=0){
            if(q.getFirst() == '0') q.removeFirst();
            else break;
        }
        
        String res = "";
        while(q.size()!=0) res+=q.removeFirst();
        return res.length()==0?"0":res;
    }

    
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
            stock.push(i);
        }
    }

    /**
     * 
     * ALWAYS USE INDEX
     * THE BASIC PREMISE IS THAT A BAR CAN EXPAND ONLY TILL ITS
     * SMALLER ELS. SO WE FIND SMALLER ELS' INDEX.
     * 
     * IMP : heights[q.getLast()], 
     * 
     * while(q.size()!=0 && heights[i] < heights[q.getLast()]) 
     *      smallerRightIndex[q.removeLast()] = i;
     * q.addLast(i);
     *  
     * POINTS :
     * 1 FIND THE NEXT SMALLER EL TO THE RIGHT AND LEFT
     * 2 FOR ELS AT THE ENDS OR LARGEST ONES OR FOR WHOM 
     * SMALLER ELS DON'T EXIST, PREFILL THE ARRAY WITH n AND -1.
     *
     * 3 AREA IS (RIGHT - LEFT) * HEIGHT
     *  
     * imp : fill left with -1 and right with n, store indexes
    */
    // https://www.youtube.com/watch?v=0do2734xhnU
    // {2,1,5,6,2,3};
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0) return 0;
        if(n == 1) return heights[0];
        
        // for end els fill n and -1
        int[] smallerRightIndex = new int[n];
        Arrays.fill(smallerRightIndex, n); //
        int[] smallerLeftIndex = new int[n];
        Arrays.fill(smallerLeftIndex, -1);
        
        // finding next smaller on the right
        Deque<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            while(q.size()!=0 && heights[i] < heights[q.getLast()]) {
                smallerRightIndex[q.removeLast()] = i;
            }
            q.addLast(i);
        }
        
        // finding next smaller on the left
        q.clear();
        for(int i=n-1; i>=0; i--){
            while(q.size()!=0 && heights[i] < heights[q.getLast()]) {
                smallerLeftIndex[q.removeLast()] = i;
            }
            q.addLast(i);
        }
        
        int maxArea = 0;
        for(int i =0; i<n; i++){
            int width = smallerRightIndex[i] - smallerLeftIndex[i] - 1;
            int area = heights[i]*width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    /** 
     * POINTS : 
     * 1 SIMILAR TO LARGEST RECT IN HISTOGRAM
     * 2 JUST ADD EACH ROW TO A HOLDER ARRAY SUCCESSIVELY
     * 
     * 3 IF matrix[i][j] == '0', holder[j] = 0 AS BASE OF A TOWER CAN'T BE 0.
     * 4 
    */
    // https://leetcode.com/problems/maximal-rectangle/submissions/
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length; 
        if(m == 0) return 0;
        int n = matrix[0].length;
        int[] holder = new int[n];
        int max = 0;
        
        
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == '0') holder[j] = 0;
                else{
                    holder[j] += 1;
                }
            }
            max = Math.max(max, largestRectangleArea(holder));
        }
        return max;
    }



    // https://leetcode.com/problems/evaluate-reverse-polish-notation/
    public int evalRPN(String[] tokens) {;
        int n = tokens.length;                                         
        if(n == 0) return 0;
            
        Deque<Integer> q = new LinkedList<>(); 
        for(int i =0; i<n; i++){
            if((!(tokens[i]).equals("*") && !(tokens[i]).equals("+")
            && !(tokens[i]).equals("-") && !(tokens[i]).equals("/"))){
                q.addLast(Integer.parseInt(tokens[i]));
            }else{
                int num2 = q.removeLast();
                int num1 = q.removeLast();
                int num3 = 0;
                if((tokens[i]).equals("+")) num3 = num1+num2;
                if((tokens[i]).equals("-")) num3 = num1-num2;
                if((tokens[i]).equals("*")) num3 = num1*num2;
                if((tokens[i]).equals("/")) num3 = num1/num2;
                q.addLast(num3);
            }
        }
        
        return q.removeFirst();
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
        // Utility.printListOfLists(res);
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
        // Utility.print1DMatrix(res);
        return res;
    }

   

    // https://leetcode.com/problems/simplify-path/
    // https://leetcode.com/problems/remove-k-digits/
    // https://leetcode.com/problems/mini-parser/
    // https://leetcode.com/problems/decode-string/
    // https://leetcode.com/problems/next-greater-element-ii/



    /** POINTS:
     * 1 CUSTOM CLASS TO HOLD index and value
     * 2 when popping store difference of current index and popped index
     */
    // https://leetcode.com/problems/daily-temperatures/
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



    // https://leetcode.com/problems/exclusive-time-of-functions/
    // https://leetcode.com/problems/sum-of-subarray-minimums/



    /////////////////////////////////////////////////////
    ///////////// PARENTHESIS
    // LOOK FOR DIFFERENT SOLN, WAY TOO MUCH OPTIMIZED
    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
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

    /**  
     * ADD DEL REM
     * OPEN ADD
     * Closed D
     * E EMPTY
     * L LEFT UPDATE(j+1)
     * R REMOVE
     * E EMPTY, USE LEFT 
     * M UPDATE MAX
     * 
     * left= 0; max = 0; 
     * if ( add index                               ADD
     * if ) -> if empty left = j+1;                 E,L
     *      -> else pop -> empty : max, j-left+1    DEL, E, L 
     *                  -> not : max j-getLast      
     * 
     * 1 we only store index of opening brackets
     * 2 if closed comes and queue is empty, no point in keeping track
     * of this index,hence left = i+1
     * 3 if queue is empty, use left
     * 4 else use top el after removal.
     * do a trial run with "(()()"
     * 
    */       
    // "(()()"           
    // https://leetcode.com/problems/longest-valid-parentheses/
    public int longestValidParentheses(String s) {
        Deque<Integer> q = new LinkedList<Integer>();
        int max=0;
        int left = 0;
        for(int i=0;i<s.length();i++){
            System.out.println(q);
            // stack stores index of only opening brackets
            if(s.charAt(i)=='(') q.addLast(i);            
            else {
                // first char is ')', so left can't start from here
                // possibly from next index
                if (q.isEmpty()) left=i+1;
                else{
                    q.removeLast();
                    // if empty, use left
                    if(q.isEmpty()) max = Math.max(max,i-left+1);
                    // if not empty, peek will be last opening bracket
                    else max = Math.max(max,i-q.getLast());
                }
            }
        }
        System.out.println("longest valid parenthesis length "+ max);
        return max;
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
    

    void longestCorrectBracketSubsequence(String s){
        int counter = 0; int maxCounter = 0; int max = 0;

        for(char c:s.toCharArray()){
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

    // https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/

    // https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
    // discuss/?currentPage=1&orderBy=most_votes&query=

    /** 
     * POINTS :
     * 1 ADD TO STACK TILL ']'
     * 2 POP TILL '[' IS FOUND
     * 3 FIND PRECEDING NUMBER, use Character.isDigit() as number can be 100
     * 4 StringBuilder has reverse and append.
     * 5 
    */
    // https://leetcode.com/problems/decode-string/
    public String decodeString(String s) {
        Deque<Character> q= new LinkedList<>();
        
        String res =""; 
        
        for(int i =0; i<s.length(); i++){
            if(s.charAt(i) != ']') q.addLast(s.charAt(i));
            else{
                String temp = "";
                String curr = "";
                while(q.getLast()!='[') curr += q.removeLast();
                q.removeLast();
                int n = findNumber(q);

                for(int j =0; j<n; j++){
                    temp += curr;
                }
                
                for(int k =temp.length()-1; k>=0; k--){
                    q.addLast(temp.charAt(k));
                }
            }
        }
        
        while(q.size()!=0) res+=q.removeFirst();
        return res;
    }
    
    int findNumber(Deque<Character> q){
        StringBuilder num = new StringBuilder();
        while(q.size()!=0 && Character.isDigit(q.getLast())) num.append(q.removeLast());
        String res = new String(num.reverse());
        return Integer.parseInt(res);
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

    // https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/


    // https://leetcode.com/problems/sum-of-subarray-minimums/
    // https://leetcode.com/problems/maximal-rectangle/

    // https://leetcode.com/problems/implement-stack-using-queues/

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


    public static void main(String[] args) {
        StackPractice stack = new StackPractice();

        String dup = "ibdyigbklifapflhpafjstizipojdrsykvskxtzadkdblmikwlofjiujlnpr";
        // stack.removeDuplicates(dup);

        String expression = "(A+B/C*(D+E)-F)";
        stack.infixToPostfix(expression);

        int[] price = 
        // { 10, 4, 5, 90, 120, 80 }; 
        {100, 80,60,70,60,75,85};
        // stack.stockSpan(price);

        int[] heights = new int[]{2,1,5,6,2,3};
        // stack.largestRectangleArea(heights);

        int[] nums = // {7, 2, 4};
        {9,10,9,-7,-4,-8,2,-6};

                // { 1, -1 };
        // {1,3,-1,-3,5,3,6,7};
        int k = 5;//1;// 3;//2
        // stack.maxSlidingWindow(nums, k);


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

        String brackets = ")(())())";
        // stack.longestValidParentheses(brackets);
;
        String decode = "100[leetcode]";// "3[a2[c]]";//"3[a]2[bc]";
        // stack.decodeString(decode);

        Deque<Integer> stackToReverse = new LinkedList<>();
        // for(int i =0; i<5; i++) stackToReverse.addLast(i);
        // System.out.println(stackToReverse);
        // stack.reverseStackUsingRecursion(stackToReverse);
        // System.out.println(stackToReverse);

        String S = "aaaabbcc"; int[] C = new int[]{1,2,1,3,1,2,1,2}; // 3
        // stack.removeDuplicateCharCost(S, C);
    }

}