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

    // https://leetcode.com/problems/evaluate-reverse-polish-notation/submissions/

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
    // https://leetcode.com/problems/decode-string/
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
        stack.decodeString(decode);
    }

}