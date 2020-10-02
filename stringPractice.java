import java.util.*;

class StringPractice {

    String sortString(String s) {
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        // System.out.println(ch);
        return new String(ch);
    }

    /**
     * imp methods : startsWith, contains, split, replace, StringBuilder
     * s.length() braces needed 
     * split removes trailing spaces
     * str1.compareTo(str2) - lexicographically smaller word
     * 
     * 
     * LOWERCASE TO UPPERCASE in[i] = (char) (in[i] - 'a' + 'A');
     * 
     * IMP ALSO LEARN ch - '0' gives int
     * 
     * BIDIRECTIONAL SEARCH, DUPLICATES
     * 
     * CHECKING FOR DIGITS (Character.isDigit(in[i])) 
     * (Character.isSpace(in[i]))
     * (Character.isLowercase(in[i])) 
     * (Character.isLetter(in[i]))
     * 
     * FOR STRING TO CHAR[] : S.toCharArray() 
     * 
     * FOR CHAR[] TO STRING : 
     * String s = String.valueOf(ch)
     * (or) new String(ch)
     * (or) String s = ""; for(char c:ch)s+=c;
     * 
     * FOR KEEPING COUNT PREFER A CHAR ARRAY OVER HASHMAP
     * AS ARRAYS CAN BE COMPARED USING ARRAYS.EQUALS
     * KEEP A BASE ARRAY AND A CURR ARRAY AND COMPARE BOTH
     * 
     * * WHENEVER SUSBTRING COMES, SLIDING WINDOW
     * * FOR SUBSEQUENCE HASHMAP CAN BE USED
     * 
     * boolean isSubsequence(String a, String b){
        int i = 0; int j = 0;
        
        while(i<a.length() && j<b.length()){
            if(a.charAt(i) == b.charAt(j)){
                i++; j++;
            }else i++;
        }
        
        return j==b.length();
     * }
     * 
     * 
     * 
     * QUESTIONS: 1 ALL SUBSTRINGS(2 loops) 
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     * discuss/92007/Sliding-Window-algorithm-template-to-solve-all-
     * the-Leetcode-substring-search-problem.
     * 
     * 2 WORDS IN A STRING USE SPLIT 
     * 3 ALL PALINDROME QUES (DP, use SUSBTRING TECHNIQUE FROM lexicographicSubConcat) 
     * 4 DUPLICATES USE WHILE LOOP AND BOUNDARY (j<n)
     * 5 PATTERN : REPEATED SUBSTRING PATTERN(MATHS), GEN BINARY PATTERN(RECURSION), 
     *   CAMELCASE, WILDCARD, REGEX, LONGEST HAPPY, 
     * 
     * 6 PARTITION LABELS (HASHING) 
     * 7 MIN WINDOW CONTAINING K DISTINCT (HASHING, WINDOW SHRINKING) 
     * 8 SEARCH WORD IN GRID (DFS) 
     * 9 VERSION COMPARE 
     * 10 GROUP ANAGRAM
     * 11 FILE SYSTEM
     * 12 TIME COMPARE, NEXT CLOSEST
     * 13 NO OF CONSECUTIVE 1s, MOD(10^9+7)
     * 14 GCD
     * 15 DP - DECODE, PATTERN
     * 16 ALL ANAGRAMS(SLIDING WINDOW)
     * 17 RANK TEAMS (CUSTOM COMPARATOR)
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     */

    /** 
     * POINTS :
     * 1 RUN 2 WHILE LOOPS
     * 2 CONTINUE TILL charAt(i) == charAt(j), AT THE END KEEP COUNT
     * 3 IT DOESN'T TAKE O(n^2) AS i = j IS ASSIGNED AT THE END, 
     * WHICH SAVES FROM TRAVERSING THE SAME ELEMENTS TWICE
     * 
    */
    // https://leetcode.com/problems/consecutive-characters/
    public int maxPower(String s) {
        int max = 1; int i = 0;
        while(i<s.length()){
            int j = i;
            while(j<s.length() && s.charAt(j) == s.charAt(i)) j++;
            max = Math.max(max, j-i); 
            i = j+1;
        }
        return max;
    }
     
    // https://leetcode.com/problems/length-of-last-word
    public int lengthOfLastWord(String s) {
        // if(s.length() == 0) return 0; // handled using words.length
        String[] words = s.split(" ");
        // System.out.println(words.length);
        if(words.length == 0) return 0;
        // for(String strs : words) System.out.println(strs+"; ");
        return (words[words.length-1]).length();
    }

    // GO TILL n-i-1
    // https://leetcode.com/problems/reverse-string/
    public void reverseString(char[] s) {
        int n = s.length;
        
        for(int i =0; i<n/2; i++) swap(s, i, n-i-1);
    }
    
    void swap(char[] ch, int a, int b){
        if(a==b) return;
        char temp = ch[a];
        ch[a] = ch[b];
        ch[b] = temp;
    }

    void reverseRecursive(String word) {
    }

    // https://practice.geeksforgeeks.org/problems/reverse-words-in-a-given-string/0
    String reverseWordOrderInAString(String word) {
        String[] words = word.split(" ");
        String output = "";
        for (int i = words.length - 1; i >= 0; i--) {
            output += words[i] + " "; // 3 reverse ordering by traversing from end to start
        }
        return output;
    }
    

    /** POINTS : 
     * 1 BREAK USING SPLIT
     * 2 ADD TO A Q AND REVERSE ADD TO RESULT
     * 3 IMP TO COMPARE WITH SPACE OR BLANK USE str.isBlank()
    */
    // https://leetcode.com/problems/reverse-words-in-a-string
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        String res = "";
                
        Deque<String> q = new LinkedList<>();
        
        for(String str : words) if(!str.isBlank()) q.addLast(str);
        
        if(q.size()==0) return res;
        while(q.size()!=0) res = res + q.removeLast() + " ";
        
        return res.substring(0, res.length()-1);
    }

    // SPLIT AND CHECK FOR ISBLANK
    // https://leetcode.com/problems/reverse-words-in-a-string-iii/
    public String reverseWordsIII(String s) {
        String res = "";
        String[] words = s.split(" ");
        for(String str : words){
            if(!str.isBlank()) res+=reverse(str.toCharArray());
            res+=" ";
        }
        return res.substring(0, res.length()-1);
    }
    
    String reverse(char[] s) {
        int n = s.length;
        for(int i =0; i<n/2; i++) swap(s, i, n-i-1);
        return String.valueOf(s);
    }

    /** POINTS:
     * 1 HOW TO CONVERT TO UPPER AND LOWER CASE(-'a'+'A')
     * 2 USE SPLIT TO CONVERT TO ARRAY AND THE SORT
     * STRING ARRAUY VS STRING
     * 3 A LOT OF CODE HAS GONE INTO HANDLING BULLSHIT TESTCASE,
     * ESPECIALLY THE textNew
     */
    // https://leetcode.com/problems/rearrange-words-in-a-sentence
    public String arrangeWords(String text) {
        char c = (char)(text.charAt(0)-'A'+'a');
        String textNew = ""+c+text.substring(1, text.length());
        
        String[] words = textNew.split(" ");
        Arrays.sort(words, (x,y)->x.length() - y.length());
        
        char first = (char)((words[0]).charAt(0)-'a'+'A');
        String res = ""+first;
        res+=(words[0]).substring(1, words[0].length());
        // res[0] = first;
        // System.out.println("first "+first);
        
        for(int i =1; i<words.length; i++){
            res= res+" "+ words[i];
        }
        return res;
    }


    // incomplete
    String sumOf2LargeNos(String str1, String str2) {
        String output = "";
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        int length = str1.length() > str2.length() ? str1.length() : str2.length();

        for (int i = length; i >= 0; i--) {

        }
        return output;
    }

    // Converts a string to uppercase
    static String to_upper(char[] in) {
        for (int i = 0; i < in.length; i++) {
            if ('a' <= in[i] & in[i] <= 'z') {
                in[i] = (char) (in[i] - 'a' + 'A');
            } else if (Character.isDigit(in[i]))
                System.out.println("number found");
            // else if((in[i]).getClass().getName()) System.out.println("number found");
            // System.out.println( (in[i]).getName() );
        }
        return String.valueOf(in);
    }

    // https://leetcode.com/problems/excel-sheet-column-title/
    public String convertToTitle(int n) {
        System.out.println('A');
        System.out.println('a');
        return "a";
    }

    // https://leetcode.com/problems/detect-capital/
    
    // using include-exclude concept of recursion
    /**
     * using include-exclude concept of recursion if(i == length) sysout f(arr[i],
     * i+1) arr[i] = 0 f(arr, i+1);
     * 
     */
    void generateBinPattern(String str) {
        char[] ch = str.toCharArray();
        int n = ch.length;

        int index = str.indexOf('?');
        // System.out.println("index of ? "+index);
        generateBinPatternUtil(ch, n, index);
    }

    void generateBinPatternUtil(char[] ch, int k, int index) {
        if (index == k) {
            // System.out.println(index+ " equals k");
            System.out.println("ch array " + String.valueOf(ch));
        } else {
            if (ch[index] == '?') {
                ch[index] = '0';
                // curIndex = index + 1;
                // System.out.println(index);
                // System.out.println("ch array if 0 " + String.valueOf(ch));
                generateBinPatternUtil(ch, k, index + 1);

                ch[index] = '1';
                // System.out.println("ch array if 1 " + String.valueOf(ch));
                generateBinPatternUtil(ch, k, index + 1);
                ch[index] = '?';
            } else {
                // System.out.println("ch array else " + String.valueOf(ch));
                generateBinPatternUtil(ch, k, index + 1);
            }
        }
    }

    void genBinPerm01s(int length) {
        String str = "1";
        for (int i = 1; i < length; i++) {
            str += '?';
        }
        char[] ch = str.toCharArray();
        int oneCount = 1;
        int zeroCount = 0;
        genBinPerm01sUtil(ch, 1, oneCount, zeroCount);
    }

    void genBinPerm01sUtil(char[] ch, int index, int oneCount, int zeroCount) {
        if (index == ch.length)
            System.out.println(String.valueOf(ch));
        else {
            if (zeroCount <= oneCount - 1) {
                ch[index] = '0';
                genBinPerm01sUtil(ch, index + 1, oneCount, zeroCount + 1);
                ch[index] = '1';
                genBinPerm01sUtil(ch, index + 1, oneCount + 1, zeroCount);
                ch[index] = '?';
            } else {
                ch[index] = '1';
                genBinPerm01sUtil(ch, index + 1, oneCount + 1, zeroCount);
                ch[index] = '?';
            }
        }
    }

    
    /** 
     * POINTS:
     * 1 SIMILAR TO GCD, MATHEMATICAL CALCULATOIN OF LENGTH
     * 2 START FROM N/2, IF N%L == 0
     * 3 REPEATEDLY ADD THE SUBSTRING(0,l) m TIMES
     * AND CHECK WITH STRING
     * 
     * 
    */
    // https://leetcode.com/problems/repeated-substring-pattern
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();         
        if(n==0) return false;
        
        for(int l = n/2; l>0; l--){
            if(n%l==0){
                int m = n/l;
                String curr = s.substring(0,l);
                String holder ="";
                for(int i =0; i<m; i++) holder+=curr;
                if(holder.equals(s)) return true;
            }
        }
        return false;
    }

    /** POINTS:
     * TRICK : if (!(str1+str2).equals(str2+str1))  return "";
     * IF COMMON STRING (GCD) EXISTS, THEN THE STRINGS WILL BE EQUAL 
     * WHEN ADDED IN ANY ORDER, ABABAB & ABAB -> ABABABABAB;
     * BUT IF NOT, E.G. ABCDEF AND ABC, WHEN ADDED -> ABCDEFABC, ABCABCDEF : NOT SAME
     * 2 FIND GCD
     * 3 RETURN OFFSET
     */
    // https://leetcode.com/problems/greatest-common-divisor-of-strings/
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1))  return "";
        int offset = gcd(str1.length(), str2.length());
        return str1.substring(0, offset);
    }
    
    int gcd(int a, int b){
        if(a%b == 0) return b;
        return gcd(b, a%b);
    }


    // https://leetcode.com/problems/sort-characters-by-frequency
    class SortFreq{
        char c; int count;
        SortFreq(char c, int v){this.c = c; this.count = v;}
    }
    
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        char[] ch = s.toCharArray();
        
        for(int i =0; i<ch.length; i++){
            int curr = map.getOrDefault(ch[i], 0);
            map.put(ch[i], curr+1);
        }
        
        PriorityQueue<SortFreq> pq = new PriorityQueue<>((x,y)-> y.count-x.count);
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            pq.add(new SortFreq(entry.getKey(), entry.getValue()));
        }
        
        // System.out.println(pq);
        String result  = "";
        
        while(pq.size()!=0){
            SortFreq curr = pq.remove();
            int i = curr.count;
            while(i!=0){
                result+=curr.c; i--;
            }
        }
        // System.out.println(result);
        return result;
    }


    /**
     * POINTS : 
     * 1 start a dfs whenever the ch[i][j] matches the starting char of string
     * 2 USE THE WORD, DON'T USE CHAR ARRAY
     * 3 NO NEED TO MAINTAIN VISITED ARRAY, USE BACKTRACKING
     * 4 RETURN BOOLEAN DFS, MAINTAINING A GLOBAL VARIABLE CHECKS
     * FOR ALL POSSIBLE STARTS AND DOESN'T RETURN TRUE TILL ALL POSSIBILITIES
     * ARE EXHAUSTED, WHICH RESULTS IN TLE
     * 5 
     * 
     */
    // https://leetcode.com/problems/word-search/submissions/
    public boolean exist(char[][] board, String word) {
        // char[] ch = word.toCharArray();
        // boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i =0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int row, int col, String word, int index){
        if(isSafe(board, row, col, word, index)){
            if(index == word.length()-1 && word.charAt(index) == board[row][col]) {
                // System.out.println("in here"); 
                return true;
            }
            char temp = board[row][col];
            board[row][col] = ' '; // 4 not using visited array
            boolean found =  dfs(board, row-1, col, word, index+1) 
            || dfs(board, row+1, col, word, index+1) 
            || dfs(board, row, col-1, word, index+1)
            || dfs(board, row, col+1, word, index+1);
            
            board[row][col] = temp; // 3 backtracking
            return found;
        }
        return false;
    }
    
    
    boolean isSafe(char[][] board, int row, int col, String word, int index){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == word.charAt(index)) return true;
        return false;
    }


    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs(char[] X, char[] Y, int m, int n) {
        System.out.println("m " + m + " n " + n);
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1]) {
            System.out.println("X " + X[m - 1] + " Y " + Y[n - 1]);
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            System.out.println("in else");
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }


    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        for(char c : t.toCharArray()) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c)-1); 
                if(map.get(c) == 0) map.remove(c);
            }
            else return false;
        }
        
        return map.size()==0;
    }

    /**
     * ch.toString converts ch to an object and not string add individual chars OR
     * USE NEW STRING(CH)
     */
    boolean isAnagramStringCompare(String str1, String str2) {
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);
        String s1 = "";
        String s2 = "";
        // for(char c:ch1) s1+=c;
        // for(char c:ch2) s2+=c;
        // System.out.println(s1.compareTo(s2));
        // return s1.compareTo(s2)==0?true:false;
        return new String(ch1).compareTo(new String(ch2)) == 0 ? true : false;
    }

    // https://leetcode.com/problems/group-anagrams/
    /**
     * ["eat","tea","tan","ate","nat","bat"] {aet=[eat, tea, ate], abt=[bat],
     * ant=[tan, nat]}
     * 
     * POINTS: 1 SORT EACH STRING, CHECK IF PRESENT IN MAP 2 IF PRESENT ADD TO LIST,
     * ELSE CREATE NEW ENTRY 3 BUT WHILE ADDING : map.put(keyStr, new
     * ArrayList<>());
     * 
     * MAP TO KEYSTR(THE SORTED CHAR ARRAY), CHECK EX OF BAT ABOVE
     * 
     * 4 NEW ARRAYLIST<>(MAP.VALUES())
     * 
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /** 
     * POINTS : 
     * 1 HOW TO CHECK FOR SUBSEQEUNCE 
     * if(charAt(i)==charAt(j)) i++; j++; 
     * else i++;
     * return j == b.length() if it reaches end or not
     * 
     * 2 A HASHMAP IS MAINTAINED FOR DUPLICATE SUBSEQUENCES,
     * IF FOUND AND TRUE COUNT++;
     * 
     * 3 IF NOT CHECK AND IF TRUE COUNT++;
     * 
     */
    // https://leetcode.com/problems/number-of-matching-subsequences
    public int numMatchingSubseq(String S, String[] words) {
        int n = S.length(); int count=0;
        if(n == 0) return count;
        
        HashMap<String, Boolean> map = new HashMap<>();
        
        for(int i =0; i<words.length; i++){
            if(map.containsKey(words[i])){
                if(map.get(words[i])) count++;
            }else{
                boolean val = isSubsequence(S, words[i]);
                if(val) count++;
                map.put(words[i], val);
            }
        }
        return count;
    }
    
    boolean isSubsequence(String a, String b){
        int i = 0; int j = 0;
        
        while(i<a.length() && j<b.length()){
            if(a.charAt(i) == b.charAt(j)){
                i++; j++;
            }else i++;
        }
        
        return j==b.length();
    }

    // https://www.youtube.com/watch?v=qBbZ3tS0McI
    // void generateParentheses(int n){
    // int open = n/2;
    // char[] arr = new char[n];

    // generateParenthesesHelper(arr, 0, open);
    // }

    // void generateParenthesesHelper(char[] arr, int index, int count){
    // if(index == arr.length){
    // System.out.println(arr.toString());
    // return;
    // }
    // if(count == 0){
    // arr[index] = ')';
    // generateParenthesesHelper(arr, index+1, count );
    // }else {
    // arr[index] = '(';
    // generateParenthesesHelper(arr, index+1, count-1);
    // // arr[index] =
    // }

    // }

    void stringtoWords(String number) {
        int n = number.length();
        String a, b, c, d;
        // remove all preceding zeroes
        if (n == 4) {
            a = number.substring(0, 1);
            b = number.substring(1, 2);
            c = number.substring(2, 3);
            d = number.substring(3, 4);
            System.out.println("a " + a);
            printDigit(a);
            if (!a.equals("0"))
                System.out.print(printDigit(a) + " thousand ");
            if (!b.equals("0"))
                System.out.print(printDigit(b) + " hundred ");
            if (!c.equals("0"))
                System.out.print(printTensDigit(c));
            if (!d.equals("0"))
                System.out.print(printDigit(d));
        } else if (n == 3) {
            a = number.substring(0, 1);
            b = number.substring(1, 2);
            c = number.substring(2, 3);
            if (!a.equals("0"))
                System.out.print(printDigit(a) + " hundred ");
            if (!b.equals("0"))
                System.out.print(printTensDigit(b));
            if (!c.equals("0"))
                System.out.print(printDigit(c));
        } else if (n == 2) {
            a = number.substring(0, 1);
            b = number.substring(1, 2);
            if (!a.equals("0")) {
                if (a.equals("1")) {
                    System.out.println(printTeen(number));
                    return;
                } else
                    System.out.print(printTensDigit(a));
            }
            if (!b.equals("0"))
                System.out.print(printDigit(b));
        } else if (n == 1) {
            a = number.substring(0, 1);
            if (!a.equals("0"))
                System.out.print(printDigit(a));
            else if (a.equals("0"))
                System.out.print("zero");
        }
        System.out.println();
    }

    String printDigit(String s) {
        if (s.equals("1"))
            return "one ";
        else if (s.equals("2"))
            return "two ";
        else if (s.equals("3"))
            return "three ";
        else if (s.equals("4"))
            return "four";
        else if (s.equals("5"))
            return "five";
        else if (s.equals("6"))
            return "six";
        else if (s.equals("7"))
            return "seven";
        else if (s.equals("8"))
            return "eight";
        else if (s.equals("9"))
            return "nine";
        else
            return "";
    }

    String printTensDigit(String s) {
        if (s.equals("1"))
            return " ten ";
        else if (s.equals("2"))
            return " twenty ";
        else if (s.equals("3"))
            return " thirty ";
        else if (s.equals("4"))
            return " forty ";
        else if (s.equals("5"))
            return " fifty ";
        else if (s.equals("6"))
            return " sixty ";
        else if (s.equals("7"))
            return " seventy ";
        else if (s.equals("8"))
            return " eighty ";
        else if (s.equals("9"))
            return " ninety ";
        else
            return "";
    }

    String printTeen(String num) {
        if (num.equals("10"))
            return "ten";
        else if (num.equals("11"))
            return "eleven";
        else if (num.equals("12"))
            return "twelve";
        else if (num.equals("13"))
            return "thirteen";
        else if (num.equals("14"))
            return "fourteen";
        else if (num.equals("15"))
            return "fifteen";
        else if (num.equals("16"))
            return "sixteen";
        else if (num.equals("17"))
            return "seventeen";
        else if (num.equals("18"))
            return "eighteen";
        else
            return "nineteen";
    }

    /**
     * https://leetcode.com/problems/split-a-string-in-balanced-strings/
     *
     * Given a balanced string s split it in the maximum amount of balanced strings.
     * which have equal quantity of 'L' and 'R' characters.
     */

    // "RLLLLRRRLR"
    public int balancedStringSplit(String s) {
        Deque<Character> list = new LinkedList<>();
        list.addLast(s.charAt(0));

        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (!list.isEmpty() && list.getLast() == s.charAt(i)) {
                list.addLast(s.charAt(i));
                System.out.println(list + " line add");
            } else if (list.size() != 0 && list.getLast() != s.charAt(i)) {
                list.removeLast();
            } else if (list.size() == 0)
                list.addLast(s.charAt(i));
            System.out.println(list);
            if (list.size() == 0)
                count++;
        }
        System.out.println("count " + count);
        return count;
    }

    // RABIN KARP algo pattern searching

    // MIN WINDOW
    // works without duplicates,
    // leetcode prob statement seems inconsistent
    // https://leetcode.com/problems/minimum-window-substring/
    // https://leetcode.com/problems/minimum-window-substring/discuss/26808/
    // Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
    
    class minWindowString {
        int start;
        int end;

        minWindowString(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    // public String minWindow(String s, String t) {
    //     if (s.length() < t.length())
    //         return "";
    //     if (s.length() == t.length()) {
    //         System.out.println("in here");
    //         // System.out.println(s.equals(t)==false);
    //         if (!s.equals(t))
    //             return "";
    //     }

    //     String result = "";
    //     HashMap<Character, Integer> map = new HashMap<>();
    //     char[] ch = s.toCharArray();

    //     for (int i = 0; i < s.length(); i++) {
    //         if (t.contains(s.charAt(i) + "")) {
    //             map.put(ch[i], i);

    //             if (map.size() == t.length()) {
    //                 int min = getMin(map);
    //                 int max = getMax(map);
    //                 // substring is inclusive of lower index only and not higher
    //                 String str = s.substring(min, max + 1);

    //                 if (result.length() == 0) {
    //                     result = str;
    //                     System.out.println("str first " + str);
    //                 } else
    //                     result = result.length() < str.length() ? result : str;
    //             }

    //         }
    //     }
    //     System.out.println("Result " + result);
    //     return result;
    // }

    // int getMin(HashMap<Character, Integer> map) {
    //     int min = Integer.MAX_VALUE;
    //     for (Map.Entry<Character, Integer> entry : map.entrySet()) {
    //         Integer value = entry.getValue();
    //         min = Math.min(min, value);
    //     }
    //     // System.out.println("min "+ min);
    //     return min;
    // }

    // int getMax(HashMap<Character, Integer> map) {
    //     int max = Integer.MIN_VALUE;
    //     for (Map.Entry<Character, Integer> entry : map.entrySet()) {
    //         Integer value = entry.getValue();
    //         max = Math.max(max, value);
    //     }
    //     // System.out.println("max "+ max);
    //     return max;
    // }

     /** POINTS : 
     * MOST IMP ANAGRAM SO WINDOW SIZE WILL REMAIN SAME
     * 1 CREATE AN ARRAY TO SERVE AS A PRIMARY MAP(OR DICTIONARY)
     * WHICH WILL STORE THE COMMON ELEMENTS
     * 2 CREATE A NEW ARRAY FOR EACH SUBSEQUENT STRING AND STORE THE FREQ
     * 3 UPDATE THE PRIMARY ARAY TO STORE COMMON OF BOTH PRIMARY ABND CURR
     * 
     * SIMILAR TO INTERSECTION OF 2 ARRAYS
     */
    // https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        int n = t.length();
        String res = "";
        if(n>s.length()) return res;
        
        int[] base = new int[26];
        for(int i =0; i<n; i++) base[t.charAt(i)-'A']++;
        utilCustom.Utility.print1DMatrix(base);
        
        int[] curr = new int[26];
        for(int i =0; i<n; i++) {
            if(t.contains(""+s.charAt(i))) curr[s.charAt(i)-'A']++;
        }
        utilCustom.Utility.print1DMatrix(curr);

        if(Arrays.equals(base, curr)) res = findBest(res, s.substring(0, n));
        
        for(int i = n; i<s.length(); i++){
            int prev = i-n;
            if(curr[s.charAt(prev)-'A']!=0) curr[s.charAt(prev) - 'A']--;
            if(t.contains(""+s.charAt(i))) curr[s.charAt(i) - 'A']++;
            
            if(Arrays.equals(base, curr)) 
                res = findBest(res, s.substring(prev+1, i+1));
        }
        return res;
    }
    
    String findBest(String a, String b){
        System.out.println("b "+b);
        if(a != "" && a.length()<=b.length()) return a;
        return b;
    }

    // https://leetcode.com/problems/word-subsets/

    /** 
     * POINTS : 
     * 1 FOR EACH CHAR, IF NOT PRESENT IN MAP, FIND LENGTH
     * 2 IF PRESENT, UPDATE LEFT
     * 3 PUT IN MAP
     * 
     * handle aab, pwwke, dvdf
     * 
    */
    // https://leetcode.com/problems/longest-substring-without-repeating-characters
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n == 0) return n;
        HashMap<Character, Integer> map = new HashMap<>();
        int len = 0; 
        int left = 0;
        
        for(int i =0; i<n; i++){
            if(map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i))+1);
            }
            len = Math.max(len, i-left+1);   
            map.put(s.charAt(i), i);
        }
        return len;
    }

    /**
     * IMP TECHNIQUE TO GENERATE SUBSTRINGS 1 i = 0 till n-1 2 j = 0 till n
     * substring takes start and end ptr, includes start but excludes end ex.
     * ("abc").subsring(2,3) = "c" index 2 = 2 till index 3. Out of bounds error
     * doesn't occur
     */

    String lexicographicSubConcat(String s) {
        int n = s.length();

        // Creating an array to store substrings
        int sub_count = n * (n + 1) / 2;
        String[] arr = new String[sub_count];

        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        String str = "";
        for (char c : ch)
            str += c;
        System.out.println("sorted : " + str);
        // finding all substrings of string
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                System.out.println(str.substring(i, j));
                arr[index++] = str.substring(i, j);
            }
        }
        // Sort all substrings in lexicographic order
        // Arrays.sort(arr);

        // Concatenating all substrings
        String res = "";
        for (int i = 0; i < sub_count; i++)
            res += arr[i];

        return res;
    }

    // PALINDROME

    void recPalindrome(String str) {
        char[] ch = str.toCharArray();
        recPalindromeUtil(ch, 0, str.length() - 1);
    }

    void recPalindromeUtil(char[] ch, int start, int end) {
        if (start > end)
            return;
        if (ch[start] != ch[end]) {
            System.out.println("no match");
            return;
        }
        recPalindromeUtil(ch, start + 1, end - 1);
    }

    // https://leetcode.com/problems/palindromic-substrings/
    // ALL PALINDROMIC SUBSTRINGS

    /**
     * UPPER TRIANGULAR MATRIX
     * 
     * points : 1 dp size [n][n] 2 l here denotes length of substring; goes till n;
     * starts from 1 3 i+l-1 denotes the ending index; goes till n-1
     * 
     * aaa; l= 3; i=0; j = 2; l= 2; i=0; j = 1; i=1; j = 3;
     * 
     * 4 if l==1; dp[i][j] = 1; each char is a palindrome 5 if l==2 check only if
     * s(i) == s(j); no need to check internal substrings
     * 
     * 6 l>=3 check s(i) == s(j) and also internal substrings(dp[i+1][j-1]) 7
     * increment count whenever dp[i][j] =1;
     *
     */
    int countPalindromicSubstrings(String s) {
        int n = s.length();
        int count = s.length();

        int[][] dp = new int[n][n];

        for (int l = 1; l <= n; l++) {
            for (int i = 0; l + i - 1 < n; i++) {
                int j = l + i - 1;
                if (l == 1)
                    dp[i][j] = 1;
                else if (l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1;
                    count++;
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                    count++;
                }
            }
        }
        utilCustom.Utility.printMatrix(dp);
        System.out.println("all palindromic substrings' count : " + count);
        return count;
    }

    /**
     * longest palindrome 1 create a upper triangular dp and keep a flag for max
     * length 2 using Manacher's concept of EXPANDING AROUND MIDDLE
     * https://www.youtube.com/watch?v=y2BD4MJqV20&t=768s
     */

    /**
     * technique 1 using upper triangular matrix
     * 
     * points : 1 l IN FOR LOOP DENOTES LENGTH OF WORD IN EVERY PALINDROME QUES
     * 
     * 2 similar to all palindromic substrings' count 1 if the whole string is a
     * palindrome and if it's allowed l<=n; else l<n l is imp; denotes substring
     * length; starts from 1
     * 
     * 3 maxlen is initially 1 as every char is a palindrome; it keeps track of
     * longest length 4 start index is updated everytime the chars match 5 substring
     * start, start+maxlen
     */
    String longestPalindromicSubstring(String s) {
        int n = s.length();
        int start = 0;
        int maxlen = 1;

        if (n == 0)
            return "";
        if (n == 1)
            return s;
        int[][] dp = new int[n][n];

        for (int l = 1; l <= n; l++) {
            for (int i = 0; l + i - 1 < n; i++) {
                int j = l + i - 1;
                if (l == 1)
                    dp[i][j] = 1;
                else if (l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1;
                    maxlen = 2;
                    start = i;
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                    maxlen = Math.max(maxlen, l);
                    start = i;
                }
            }
        }
        System.out.println("longest palindromic substring : " + s.substring(start, start + maxlen));
        return s.substring(start, start + maxlen);
    }
    
    // https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/
    // all palindromes , string 'abaab'

    /** 
     * BASICALLY MIN IS STORED FOR ALL OCCURENCES OF EACH WORD
     * FIRST WORD IS USED TO CREATE A BASE
     * POINTS : 
     * 
     * 1 CREATE AN ARRAY TO SERVE AS A PRIMARY MAP(OR DICTIONARY)
     * WHICH WILL STORE THE COMMON ELEMENTS
     * 2 CREATE A NEW ARRAY FOR EACH SUBSEQUENT STRING AND STORE THE FREQ
     * 3 UPDATE THE PRIMARY ARAY TO STORE COMMON OF BOTH PRIMARY ABND CURR
     * 
     * SIMILAR TO INTERSECTION O F 2 ARRAYS
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


    // SPLIT AND THEN USE STARTSWITH
    // https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        int n = words.length;
        
        for(int i =0; i<n; i++){
            if(words[i].startsWith(searchWord)) return i+1;
        }
        return -1;
    }


    // COMPARE WITH ABOVE QUES COMMON CHARS
    // https://leetcode.com/problems/longest-common-prefix/
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if(n==0) return "";
        
        Arrays.sort(strs);
        String first = strs[0]; String last = strs[n-1];
        int c =0;
        for(int i =0; i<first.length(); i++){
            if(first.charAt(i) == last.charAt(i)) c++;
            else break;
        }
        return first.substring(0, c);
    }


    /** POINTS:
     * 1 THE FIRST STRING IS COMPARED WITH SECOND TO CHECK IF
     * THE FIRST STRING IS PREFIX OR BOTH ARE SAME
     * 2 2 LOOPS ARE USED
     * 3 PRE HOLDS THE COMMON SUBSTRING,
     * INITIALLY PRE = STRINGS[0]
     * NOW WE COMPARE WITH NEXT STRINGS IN THE ARRAY
     * IF DISSIMILAR WE TRUNCATE PREV
     * WHEN SIMILAR PREFIX IS FOUND, WE INCREMENT i
     * 
     * ["flight","flow","flower"]
     * ("flow").startsWith("flight") -> false
     * ("flo").startsWith("flight") -> false
     * ("fl").startsWith("flight") -> true
     * 
     * now prev = "fl"
     * 
     */
    public String longestCommonPrefixStartsWith(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String prev = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].startsWith(prev) == false){
                prev = prev.substring(0,prev.length()-1);
                System.out.println(prev);
            }
            i++;
        }
        return prev;
    }

      /** 
     * if j==0; check for match
     * if j>0; check for no match j= lps[j-1]
     * if match j++; lps[i] = j
     * https://www.geeksforgeeks.org/longest-prefix-also-suffix/
    */
    // https://leetcode.com/problems/longest-happy-prefix
    public String longestPrefix(String s) {
        int n = s.length(); int j = 0;
        int[] lps = new int[n];

        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = lps[j-1];
            if (s.charAt(i) == s.charAt(j)) lps[i] = ++j;
        }
        return s.substring(0, lps[n-1]);
    }
    

    // INCOMPLETE
    /** ISSUES :
     * COUNTING FREQ TEMPLATE
     * 1 USE WHILE LOOP, ASSIGNING i = j no doubt makes i point 
     * to the index of next dissimilar char, but the for
     * loop increments it further by 1
     * SO FOR DUPLICATES WHILE LOOP IS BETTER
     * 
     * 2 BOUNDARY CONDN(j<n)
     * 3 COUNT j-i
     *   // https://leetcode.com/problems/string-compression/discuss/92559/
    // similar to remove duplicates from array
     */
    // https://leetcode.com/problems/string-compression/
    public int compress(char[] chars) {
        int n = chars.length;
        String res ="";
        int count = 0;
        
        int i=0;
        while(i<n){
            int j = i+1;
            while(j<n && chars[j] == chars[i]) j++;
            res+=chars[i]+""+(j-i);
            count+=(j-i);
            i=j;
        }
        System.out.println("res "+res);
        return count;
    }
    // https://leetcode.com/problems/summary-ranges/
    // discuss/63219/Accepted-JAVA-solution-easy-to-understand

    // https://www.geeksforgeeks.org/find-number-distinct-
    // palindromic-sub-strings-given-string/
    // https://leetcode.com/problems/longest-palindromic-subsequence/

    // aug leetcode valid palindrome
    // https://leetcode.com/problems/distinct-subsequences-ii/

    // https://www.geeksforgeeks.org/find-excel-column-name-given-number/


    class partition {
        int index;
        int group;

        partition(int i, int g) {
            this.index = i;
            this.group = g;
        }
    }

    /* 
     * 9 Aug
     * Use hashmap and hint: contiguous so shrink
     * 
     * POINTS:
     * 1 USE A HASHMAP TP STORE OCCURENCES OF ALL CHARS
     * 2 IF ALREADY FOUND ITERATE OVER MAP AND CHANGE BUCKET 
     * OF ALL CHARS TO CURRENT BUCKET, WHICH IS THE BUCKET OF
     * THIS CHAR IN THE MAP (shrinkGroup)
     * 
     * 3 KEEP TRACK OF CURRBUCKET AND PREVIOUS BUCKET
     * IF THESE DIFFER ADD THE DIFF OF INDEX AND CURRSTART
     * 
     * 4 ADD A BOUNDARY CONDN AT LAST res.add(i - currStart + 1);
     * 
     */
    public List<Integer> partitionLabels(String S) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = S.toCharArray();
        int group = 0;

        for (int i = 0; i < ch.length; i++) {
            if (map.containsKey(ch[i])) {
                group = map.get(ch[i]);
                shrinkGroup(map, ch, ch[i], i - 1, group);
            } else
                map.put(ch[i], ++group);
        }

        List<Integer> res = new ArrayList<>();

        int currBucket = map.get(S.charAt(0));
        int currStart = 0;
        for (int i = 1; i < ch.length; i++) {

            int bucket = map.get(ch[i]);
            if(bucket == currBucket)// do nothing
            if (bucket != currBucket) {
                res.add(i - currStart);
                currStart = i;
                currBucket = bucket;
            }

            if (i == ch.length - 1) {
                res.add(i - currStart + 1);
            }
        }
        System.out.println(res);
        // return (List<Integer>) res;
        return res;
    }

    void shrinkGroup(HashMap<Character, Integer> map, char[] ch, char c, int index, int currGroup) {
        while (ch[index] != c) {
            map.put(ch[index], currGroup);
            index--;
        }
    }

    void shrinkMap(HashMap<Character, partition> map, String s, int index, int group) {
        int i = index - 1;
        int curr = map.get(s.charAt(index)).group;
        while (s.charAt(i) != s.charAt(index)) {
            map.put(s.charAt(i), new partition(map.get(s.charAt(i)).index, curr));
            i--;
        }
    }

    // void hashiterator1(HashMap<Character, partition> map) {
    // for (Map.Entry<Character, partition> entry : map.entrySet()) {
    // Character key = entry.getKey();
    // Integer value = entry.getValue().group;
    // System.out.println("key "+key+" value " +value);
    // }
    // }

    // "0.1.2", "0.01.2"
    /**
     * mapping between integer and char
     * 
     * 1 create an int for every substring b/w dots and compare 2 two while loops 3
     * p1, p2 are incremented once when dot is encountered, p1++ takes it till '.' 4
     * char value of '0' is 0 5 for chars use single quotes and double for strings
     * 
     * can be done using Integer.parseInt too one base case for str = "" when no dot
     * is present then add a check
     */
    // https://leetcode.com/problems/compare-version-numbers/
    public int compareVersion(String version1, String version2) {
        int p1 = 0, p2 = 0;
        while (p1 < version1.length() || p2 < version2.length()) {
            int num1 = 0, num2 = 0;

            String str1 = "";
            String str2 = "";
            while (p1 < version1.length() && version1.charAt(p1) != '.') {
                // System.out.println("char val of 0 "+'0');
                // System.out.print("char val of '1'-'0' ");
                // System.out.println('1'-'0');
                // num1 = num1*10 + (version1.charAt(p1++) - '0');
                str1 += version1.charAt(p1++);
                System.out.println("str1 " + str1);
            }
            while (p2 < version2.length() && version2.charAt(p2) != '.') {
                // num2 = num2*10 + (version2.charAt(p2++) - '0');
                str2 += version2.charAt(p2++);
                System.out.println("str2 " + str2);
            }

            num1 = Integer.parseInt(str1);
            num2 = Integer.parseInt(str2);
            if (num1 != num2)
                return num1 > num2 ? 1 : -1;
            p1++;
            p2++;
        }
        return 0;
    }
    // https://leetcode.com/problems/next-closest-time/

    // https://leetcode.com/problems/largest-time-for-given-digits/
    
     
    // https://leetcode.com/problems/minimum-time-difference/
    // https://leetcode.com/problems/reformat-date/
    // https://leetcode.com/problems/integer-to-english-words/

    /** POINTS:
     * 1 EXISTING -> 01 COUNT = 1
     * IF NEXT COMES 1, THEN SUBSTRINGS WITH ALL CHARACTERS 1 ARE
     * 1, 1, 11 (IDUPLCATES INCLUDED)
     * 
     * 2 WE CALCULATE ON THE BASIS OF CONSECUTIVE ONES
     * count++
     * res = (res+count)%(1000000007)
     * 
     * first 1 count = 1, res =1
     * second 1 count =2 res = 3(res+=count);
     * 
     * 3 USE 1000000007 FOR MOD(10^9 +7) , 10^9 IS XOR NOT POWER
     * 4 10^9 : 9 zeroes +7 
    */
    // https://leetcode.com/problems/number-of-substrings-with-only-1s
    public int numSub(String s) {
        int count = 0; int res = 0;
        
        for(int i =0; i<s.length(); i++){
            if(s.charAt(i) == '0') count = 0;
            else {
                count++;
                res = (res + count)%(1000000007);
            }
        }
        return res;
    }
    // SIMILAR : MICROSOFT https://leetcode.com/discuss/interview-question/364618/

  

    // https://leetcode.com/problems/multiply-strings/


    // https://leetcode.com/problems/remove-k-digits/
    /**
     * NEW NO IS SMALLEST POSSIBLE
     * 
     * add the digits to stack, when the top(GETLAST) is smaller than current, pop
     * till you find smaller or k==0
     * 
     * if k!=0 pop till k==0
     * 
     * ensure leading zeroes are removed.
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> s = new LinkedList<>();
        s.addLast(num.charAt(0));

        for (int i = 1; i < num.length(); i++) {
            while (s.size() != 0 && num.charAt(i) < s.getLast() && k > 0) {
                s.removeLast();
                k--;
            }

            s.addLast(num.charAt(i));
        }

        while (k > 0 && s.size() != 0) {
            s.removeLast();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty())
            sb.append(s.removeFirst());
        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.length() != 0 ? sb.toString() : "0";
    }

    // https://www.techiedelight.com/inplace-remove-all-occurrences-ab-c-string/
    

    /** 
     * Steps :
     * 1 create a hashmap
     * 2 backtracking template (similar to all subsets, all combinations etc)
     * USE RES IN PLACE OF CURR LIST AND DON'T FORGET TO REMOVE
     * 
     * f(index){
     *  if(index == length) res.add() // base condition
            for( c: array){
                temp += c;        //add
                f(index+1);
                temp = temp.substring(temp.length()-1);  //remove
            }
     * }
    */
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});

        List<String> res = new ArrayList<>();
        String temp = "";
        btk(map, 0, digits, res, temp);
        System.out.println("final res "+res);
        return res;
    }

    void btk(HashMap<Character, char[]> map, int index, String digits, List<String> res, String temp){
        if(index==digits.length()) {
            System.out.println("res "+res);
            res.add(temp);
            return;
        }
        char[] curr = map.get(digits.charAt(index));
        for(char c : curr){
            temp+=c;
            btk(map, index+1, digits, res, temp);
            temp = temp.substring(0, temp.length()-1);
        }
    }

    // NEEDS TO BE OPTIMIZED
    // https://leetcode.com/problems/decode-string/
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
                    while(count-->1)i++;
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

        // System.out.println("final "+res);
        return res;
    }

    //checks for larger numbers length>=2 
    int check(String s, Deque<Integer> num, int i){
        String number = ""; int c = 0;
        int index = i;
        while(s.charAt(index)!='[') {
            number+=s.charAt(index); index++; c++;
        }
        // System.out.println("number "+number);
        // System.out.println("count "+c);
        num.addLast(Integer.parseInt(number));
        return c;
    }
    
    //adds the letter n times, n= number.peek() 
    void process(Deque<Character> letter, Deque<Integer> number){
        String res = "";
        while(letter.size()!=0 && letter.peekLast()!='['){
            res = letter.removeLast()+res;
        }
        if(letter.size()!=0) letter.removeLast();//removes '['
        int n = 0;
        if(number.size()!=0) n= number.removeLast();
        // System.out.println("num "+n);
        for(int i =0; i<n; i++){
            for(char s : res.toCharArray()){
                letter.addLast(s);
            }
        }
        // System.out.println("letter "+letter);
    }


    /** 
     * POINTS : 
     * 1 BASIC PREPROCESSING, STORE COUNT IN MAP
     * 2 ADD ENTRIES TO Q
     * 3 NOW REMOVE FROM Q AND ADD TO TEMP
     * 4 LIST.REMOVE(0) USE INDEX REMOVE IN ALL CASES
     * 
     * 5 K= 2 IS FOR SINGLE DISTANCE APART, CAN BE GENERALIZED
     * 
     * 6 IF(temp size >=k) ADD the first char back to q
     * for clarity check this 
     * https://leetcode.com/problems/reorganize-string/discuss/128907/
     * Java-solution-99-similar-to-358
     * 
     * q -> current -> res-> temp -> checksize() -> pushback(freq>0)
     * ex. aaab
     * q       current .   res       temp .      
     * a3,b1 
     * b1 .     a          a          a2
     * --       b          ab         a2,b0
     * size == 2 remove from temp
     *                                b0 
     * a2
     * --       a          aba        b0a1
     * size == 2 remove b, not added as freq = 0
     * 
     * SO NOW Q IS EMPTY AND ALL CHARS HAVEN'T BEEN USED
     * res.length() == s.length()? res : ""
     * 
     * qatsf
     * q remove, add, add to temp, size chk, freq chk
     */
    // https://leetcode.com/problems/reorganize-string/

    // https://leetcode.com/problems/reorganize-string/discuss/
    // 847881/k-distance-apart-generalisation-here-(k2)-AIN'T-MUCH-BUT-IT'S-HONEST-WORK.
    class Reorg{
        char c; int freq;
        Reorg(char ch, int f){
            this.c = ch;
            this.freq = f;
        }
    }
    public String reorganizeString(String s) {
        char[] ch = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i =0; i<ch.length; i++)
            map.put(ch[i], map.getOrDefault(ch[i], 0)+1);
        
        PriorityQueue<Reorg> q = new PriorityQueue<>((x,y)-> y.freq-x.freq);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) 
            q.add(new Reorg(entry.getKey(), entry.getValue()));
        
        int k = 2;
        String res ="";
        ArrayList<Reorg> temp = new ArrayList<>();
        
        while(q.size()!=0){
            Reorg curr = q.remove();
            res+=curr.c;
            // System.out.println(res);
            temp.add(new Reorg(curr.c, curr.freq-1));    
            
            if(temp.size()<k) continue;
            Reorg pushBack = temp.remove(0);//remove in all cases
            // System.out.println("pushBack "+pushBack.c+" "+pushBack.freq);
            if(pushBack.freq>0) q.add(pushBack);
            
        }
        
        return res.length() == s.length()? res : "";
    }

    /** 
     * POINTS :
     * 1 dp size [m+1][n+1]
     * 2 assign first row and col -> i to match with null string
     * 3 start from i=1, j=1 and word.charAt(i-1) && word.charAt(j-1)
     * 
    */
    // https://leetcode.com/problems/edit-distance/submissions/
    public int minEditDistance(String word1, String word2) {
        int m = word2.length();
        int n = word1.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i =0; i<=m; i++) dp[i][0] = i;
        for(int i =0; i<=n; i++) dp[0][i] = i;
        
        for(int i =1; i<=m; i++){
            for(int j =1; j<=n; j++){
                if(word1.charAt(j-1) == word2.charAt(i-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))+1;
            }
        }
        return dp[m][n];
    }


    // LCS
    // https://leetcode.com/problems/delete-operation-for-two-strings/

  

    // https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram
    public int minSteps(String s, String t) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
    
        for(int i =0; i<s.length(); i++){
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i),0)+1);
        }

        int c =0;
        for(int i =0; i<t.length(); i++){
            map2.put(s.charAt(i), map2.getOrDefault(t.charAt(i),0)+1);
            if(map1.containsKey(t.charAt(i))) map1.put(t.charAt(i), map1.get(t.charAt(i))-1);
            // else c++
        }
        
        // System.out.println(map1);
        // System.out.println(map2);
        int count = 0;
        for(Map.Entry<Character, Integer> entry : map1.entrySet()){
            System.out.println(entry.getValue());
            if(entry.getValue()!=null) count+=entry.getValue();
        }
        
        return count;
        // return c;
    }

    
   
    // IMP SORTING TO REDUCE COMPARISONS

    // IMP FILESYSTEM
    // https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
    /** POINTS : 
     * 1 SORT THE ARRAY
     * 2 USE STARTSWITH; SIMILAR TO MERGE INTERVALS
     * 3 prev = folder[0]; curr.startsWith(prev) && curr.charAt(prev.length()) == '/'
     * 
     * if curr starts with prev and has '/', iit must be a subfolder, don't add to res
    */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        if(folder.length == 0) return res;
        if(folder.length == 1) {res.add(folder[0]); return res;}
        
        res.add(folder[0]);
        
        String prev = folder[0];
        
        for(int i =1; i<folder.length; i++){
            String curr = folder[i];
            
            if(curr.startsWith(prev) && curr.charAt(prev.length()) == '/') continue;
            else {
                res.add(curr); prev = curr;
            }
        }
        return res;
    }

    /**  
     * 
     * POINTS : 
     * how to compare if sorted, how to compare if unsorted
     * 
     * 1 IF SORTED, THEN COMPARE FROM i=0; j = i+1
     * 2 ELSE start from i=0; j = 0
     * 
    
     how to remove duplicates if sorted
     */

    public List<String> stringMatching(String[] words) {
        HashSet<String> set = new HashSet<>();
        
        // Arrays.sort(words, (x,y)-> y.length()-x.length());
        
        for(int i =0; i<words.length; i++){
            for(int j =0; j<words.length; j++){
                if(i!=j && words[i].contains(words[j])){
                    if(!set.contains(words[j])) set.add(words[j]);
                }
            }
        }
        return new ArrayList<String>(set);
    }

    /** 
     * POINTS : 
     * 1 IF NULL THEN 1 WAY; dp[0] = 1;
     * 2 IF LENGTH 1 CHECK IF 0, THEN 0 AS A STARTS FROM 1
     * 3 dp[1] depends on s.charAt(0)
     * 4 singles keeps track of a single substring from i-1 till i
     * doubles same for i-2 till i
     * if these are valid add to dp[i]
     * 5 why +=? if singles &/or doubles are invalid, then dp[i]
     * can take value previous index
     * 
     * ex. 09 singles = 0, doubles = 1
     * 
    */
    // https://leetcode.com/problems/decode-ways
    public int numDecodings(String s) {
        int n = s.length();
        if(n==0) return 0;        
    
        int[] dp = new int[n+1];
        
        dp[0] = 1;
        dp[1] = s.charAt(0) =='0'? 0:1;
        
        for(int i =2; i<=n; i++){
            int singles = Integer.valueOf(s.substring(i-1,i));
            int doubles = Integer.valueOf(s.substring(i-2,i));
            
            if(singles>=1) dp[i] += dp[i-1];
            if(doubles>=10 && doubles<=26) dp[i] += dp[i-2];
        }
        return dp[n];
    }

    /**
     * IMP : x.str.compareTo(y.str);
     * 
     * POINTS :
     * 1 PUT FREQIN HASHMAP
     * 2 CREATE A CUSTOM CLASS TO STORE STRING AND CORR FREQ
     * 3 USE A PQUEUE TO SSORT STRINGS
     * ((x,y)->{
            if(x.freq == y.freq) return x.str.compareTo(y.str);
            return y.freq - x.freq;
        })
     * 
    */

    // https://leetcode.com/problems/top-k-frequent-words
    class Frequency{
        String str; int freq;
        Frequency(String s, int f){
            this.str = s;
            this.freq = f;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i =0; i<words.length; i++){
            map.put(words[i], map.getOrDefault(words[i], 0)+1);
        }
        
        PriorityQueue<Frequency> pq = new PriorityQueue<>((x,y)->{
            if(x.freq == y.freq) return x.str.compareTo(y.str);
            return y.freq - x.freq;
        });
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            pq.add(new Frequency(entry.getKey(), entry.getValue()));
        }
        
        for(int i =0; i<k; i++){
            res.add(pq.remove().str);
        }
        return res;
    }

    // https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/

    // TAKE CARE TO ADD THE CURR ELEMENT TO THE NEW ARRAY IN ELSE 
    // https://leetcode.com/problems/rank-teams-by-votes/
    public String rankTeams(String[] votes) {
        HashMap<Character, int[]> map = new HashMap<>();
        
        int places = votes[0].length();
        
        for(String s : votes){
            for(int i =0; i<s.length(); i++){
                if(map.containsKey(s.charAt(i))) map.get(s.charAt(i))[i]++;
                else {
                    int[] curr = new int[places];
                    curr[i]++;
                    map.put(s.charAt(i), curr);
                }
            }
        }
        
        PriorityQueue<Character> q = new PriorityQueue<>((x,y)->{
            int[] a = map.get(x); int[] b = map.get(y);
            for(int i =0; i<a.length; i++){
                if(a[i]!=b[i]) return b[i]-a[i]; //max heap
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

    /** basically we keep a char array(base) to keep count of pattern
    and use a new array(curr) while sliding over the string. 
    Whenever the arrays match, we store the start index of the current window.

    Fow sliding window, fill the first window and then subsequent windows.

    if(Arrays.equals(base, curr)) res.add( i-pattern.length() + 1); 

    POINTS : 
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
     * 
     * TO MAP INDEX OF SLIDING WINDOW TO CHAR ARRAY USE 
     * curr[s.charAt(prev) - 'a']
    */	
    // https://leetcode.com/problems/find-all-anagrams-in-a-string
    public List<Integer> findAnagrams(String s, String t) {
        char[] base = new char[26];
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0)
            return res;
        int n = t.length();
        if (n > s.length())//1
            return res;

        for (char c : t.toCharArray()) base[c - 'a']++; //2

        char[] curr = new char[26];

        for (int i = 0; i < n; i++) curr[s.charAt(i) - 'a']++;
        if (Arrays.equals(base, curr)) res.add(0);

        for (int i = n; i < s.length(); i++) {
            int prev = i - n;
            curr[s.charAt(prev) - 'a']--;
            curr[s.charAt(i) - 'a']++;

            if (Arrays.equals(base, curr)) res.add(prev + 1);
        }
        return res;
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
     */
    // https://leetcode.com/problems/word-subsets/
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] base = new int[26];

        for (String s : B) {
            int[] temp = getFreq(s);
            for (int i = 0; i < 26; i++) {
                base[i] = Math.max(base[i], temp[i]);
            }
        }

        List<String> result = new ArrayList<>();
        for (String str : A) {
            boolean flag = true;
            int[] curr = getFreq(str);

            for (int i = 0; i < 26; i++) {
                if (base[i] > curr[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) result.add(str);
        }
        return result;
    }

    public int[] getFreq(String s) {
        int[] result = new int[26];
        for (char c : s.toCharArray()) {
            result[c - 'a']++;
        }
        return result;
    }

    /** POINTS :
     * 1 KEEP LEFT AND RIGHT SUMS
     * 2 RUN ADD FUNCTION WITH A FLAG
     * 3 IF FLAG IS TRUE COUNT ZEROES
     * 4 GO TILL n-1, AS PARTITION CAN'T BE OF 0 LENGTH
     * 
     * int left = add(s, 0, i, true); int right = add(s, i+1,n-1, false);
     * 
     */
    // https://leetcode.com/problems/maximum-score-after-splitting-a-string/
    public int maxScore(String s) {
        int n = s.length();
        if(n==0) return 0;
        int sum = 0;
        
        for(int i =0; i<n-1; i++){
            int left = add(s, 0, i, true); int right = add(s, i+1,n-1, false);
            sum = Math.max(sum, left+right);
        }
        
        return sum;
    }
    
    int add(String str, int start, int end, boolean left){
        char value = '1';
        if(left) value = '0';
        int currSum = 0;
        for(int i =start; i<=end; i++){
            if(str.charAt(i) == value) currSum+=1;
        }
        return currSum;
    }


    /**
     * IT STRUCK ME THAT THE FIRST CHARACTER IS THE ONE CAUSING ALL PROBLEMS.
     * SO DECIDED TO DO AN ADDITIONAL CHECK FOR word.charAt(1) AS WELL.
     * 
     * 1 THE IDEA IS TO KEEP 3 FLAGS, first, allUpper AND allLower.
     * 2 ONCE DONE WITH FIRST 2 CHARS, FOR NEXT CHARS, 
     * IF UPPERCASE CHAR COMES AND allLower is true, THEN RETURN FALSE;
     * IF LOWERCASE AND allUpper is true, RETURN FALSE;
     * 3 FOR FIRST 2 CHARS, IF UPPERCASE CHAR COMES AND FIRST IS FALSE RETURN FALSE 
     * AS 'aA' will be false.
     * 
     * https://leetcode.com/problems/detect-capital/discuss/
     * 860902/Java-3-Flags-simple-solution
    */
public boolean detectCapitalUse(String word) {
        boolean first = false;
        boolean allUpper = false;
        boolean allLower = false;
        
        if(word.length() == 1) return true;
        
        if(Character.isUpperCase(word.charAt(0))) first = true;
        else {first = false; allLower = true;}
        
        if(Character.isUpperCase(word.charAt(1))){
            if(!first) return false;
            allUpper = true;
        }
        else allLower = true;
        
        for(int i =2; i<word.length(); i++){
            if(Character.isUpperCase(word.charAt(i)) ){
                if(allLower) return false;
            }else {
                if(allUpper) return false;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/add-binary/
    // public String addBinary(String a, String b) {
    //     char[] ch = a.toCharArray();
        
    // }

    // https://leetcode.com/problems/word-ladder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        int distance = 1;
        
        Deque<String> q = new LinkedList<>();
        q.addLast(beginWord);
        while(q.size()!=0){
            int size = q.size();            
            // all words in the same go, helps maintain smallest dist
            for(int k =0; k<size; k++){//1
                char[] curr = (q.removeFirst()).toCharArray();
                
                for(int i =0; i<curr.length; i++){
                    char holder = curr[i];//2
                    
                    for(char c ='a'; c<='z'; c++){
                        if(c==holder) continue;//3
                        curr[i] = c;
                        String after = String.valueOf(curr);
                        if(after.equals(endWord)) return distance+1;
                        if(set.contains(after)) {
                            // System.out.print(after+", ");
                            q.addLast(after); set.remove(after);//4
                        }
                    }
                    curr[i] = holder;//5
                }   
            }
            distance++;//6
        }
        return 0;
    }
    

    // https://leetcode.com/problems/add-binary/
    // https://leetcode.com/problems/longest-duplicate-substring/
    // https://leetcode.com/problems/next-closest-time/
    // https://www.codertrain.co/next-closest-time


    // https://leetcode.com/problems/multiply-strings/
    // https://leetcode.com/problems/boats-to-save-people/
    // https://leetcode.com/problems/shifting-letters/
    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
    // https://leetcode.com/problems/camelcase-matching/
    // https://leetcode.com/problems/implement-trie-prefix-tree/
    // https://leetcode.com/problems/string-to-integer-atoi/discuss/4922/Java-Simple-clean-and-fast!

    public static void main(String[] args) {
        StringPractice string = new StringPractice();
        // System.out.println(string.reverse("word of"));
        // System.out.println(string.reverseWordOrderInAString("I love Java
        // Programming"));
        // string.convertToTitle(3);

        String x = "123";

        // System.out.println(Integer.parseInt(x)+1);//4
        String str = "GeeksForGeeks12";
        // char[] in = str.toCharArray();
        // System.out.println(stringPractice.to_upper(in));

        // System.out.println((char)('a'+2));
        String str1 = "1??";
        // string.generateBinPattern(str1);

        // string.genBinPerm01s(4);
        // string.recPalindrome("abba");

        char[][] grid = { { 'A', 'C', 'P', 'R', 'C' }, { 'X', 'S', 'O', 'P', 'C' }, { 'V', 'O', 'V', 'N', 'I' },
                { 'W', 'G', 'F', 'M', 'N' }, // 'F','O','R','G','E','E','K','S'},
                // {'G','E','E','K','S','Q','U','I','Z','G','E','E','K'},
                { 'Q', 'A', 'T', 'I', 'T' } };// ,'P','R','A','C','T','I','C','E'}};

        String word = "MICROSOFT";
        // string.findWordInGrid(grid, word);

        // System.out.println(string.isAnagram("str1", "str1"));
        // System.out.println(string.isAnagramStringCompare("str1", "str2"));

        String s = "othello";
        // System.out.println(s.substring(2,3));
        // System.out.println(string.lexicographicSubConcat(s));

        // string.generateParentheses(3);

        String num = "1432219";
        int k = 3;
        // string.removeKdigits(num, k);

        // string.partitionLabels("ababcbacadefegdehijhklij");
        String number = "1440";
        // string.stringtoWords(number);

        String S = // "aa";
                "BANC";//"ADOBECODEBANC";
        String T = // "aa";
                "ABC";
        string.minWindow(S, T);

        String palin = "ac";// "hellolle";
        // string.countAllPalindromes(palin);

        // string.countPalindromicSubstrings(palin);
        // string.longestPalindromicSubstring(palin);

        String partition = "ababcbacadefegdehijhklij";
        // string.partitionLabels(partition);

        // System.out.println(string.compareVersion("0.1.2", "0.01.2"));

        String balanceLR = "RLLLLRRRLR";
        // string.balancedStringSplit(balanceLR);

        String keyPad = "23";
        // string.letterCombinations(keyPad);

        char[] toCompress = new char[]{'a','a','b','b','c','c','c'};
        // string.compress(toCompress);

    }
}