package Java_DS_Algo;
import java.util.*;
// import utilCustom.Utility;
class StringPractice {

    // https://www.baeldung.com/java-string-immutable
    
    String sortString(String s) {
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }

    // https://www.geeksforgeeks.org/string-data-structure/
    /**
     * string patterns
     * no of substrings containing all 3 chars,
     * longest repeating
     * longest palindrome
     * longest happy prefix, matching subsequences
     * 
     * max length of concatenated string, phone no combinations
     * 
     * QUES : 
     * 
     * shortest to char, VERSION COMPARE, IS SUBSEQUENCE, TIME, SUBFOLDER, KDIGITS,
     * DECODE STRING, NO OF DECODINGS
     * WORD SEARCH, WORD LADDER, REORGANIZE, NUMBER OF DECODINGS,
     * LARGEST TIME
     * detect capital
     * 
     * 
     * 
     * imp methods : startsWith, contains, split, replace, isBlank
     * StringBuilder
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
     * int j = 0;
     * 
     * for(int i =0; i<a.length(); i++){
     *      if(a.charAt(i) == b.charAt(j)) i++; j++;
     *      // if end reached, substring
     *      if(j == b.length()) return true;
     * }
     *  
     * return j==b.length();
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


    String reverse(String str){
        StringBuilder res = new StringBuilder(str); 
        res.append("1");//    
        res.deleteCharAt(res.length()-1);
        System.out.println(res);
        res.insert(res.length(), 2);
        System.out.println(res);
        return res.reverse().toString();
    }


    boolean palindrome(String str){
        int lo = 0; int hi = str.length()-1;
        while(lo<=hi){
            if(str.charAt(lo) != str.charAt(hi)) return false;
            lo++; hi--;
        }
        return true;
    }

    // take care of i++ in loop, assign j-1
    // https://leetcode.com/problems/consecutive-characters/
    public int maxPower(String s) {
        int n = s.length();
        int j = 0; int len = 1;
        
        for(int i =0; i<n; i++){
            j = i;
            while(j<n && s.charAt(i) == s.charAt(j)) j++;
            len = Math.max(len, j-i);
            i = j-1;
        }
        return len;
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

    // USE lo, hi
    // https://leetcode.com/problems/reverse-string/
    public void reverseString(char[] s) {
        int n = s.length;
        int lo = 0; int hi = n-1;
        while(lo<=hi){
            swap(s, lo++, hi--);
        }
    }
    
    void swap(char[] ch, int a, int b){
        if(a==b) return;
        char temp = ch[a];
        ch[a] = ch[b];
        ch[b] = temp;
    }

    // recursive
    public void reverseRecursive(char[] s) {
        int n = s.length;
        helper(s, 0, n-1);
    }
    
    
    void helper(char[] arr, int lo, int hi){
        if(lo>hi) return;
        swap(arr, lo, hi);
        helper(arr, ++lo, --hi);
    }
    
    

    // https://leetcode.com/problems/shuffle-string
    public String restoreString(String s, int[] indices) {
        int n = indices.length;
        char[] res = new char[n];
        for(int i =0; i<n; i++){
            res[indices[i]] = s.charAt(i);
        }
        return new String(res);
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


    // https://leetcode.com/problems/reverse-vowels-of-a-string
    public String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length;
        if(n == 0) return s;
        
        HashSet<Character> vowel = new HashSet<>();
        vowel.add('a'); vowel.add('e'); vowel.add('i');
        vowel.add('o'); vowel.add('u');
        vowel.add('A'); vowel.add('E'); vowel.add('I');
        vowel.add('O'); vowel.add('U');
        
        if(n==1 && !vowel.contains(s.charAt(0))) return s;
        int lo = 0; int hi = n-1;
        
        while(lo<=hi){
            while(!vowel.contains(s.charAt(lo))) {
                lo++;
                if(lo>=n) break;
            }
            while(!vowel.contains(s.charAt(hi))) {
                hi--;
                if(hi<0) break;
            }
            // System.out.println("lo "+lo+" "+s.charAt(lo));
            // System.out.println("hi "+hi+" "+s.charAt(hi));
            if(lo>hi) break;
            swap(ch, lo++, hi--);
        }
    
        return new String(ch);
    }

    /** POINTS:
     * 1 HOW TO CONVERT TO UPPER AND LOWER CASE(-'a'+'A')
     * 2 USE SPLIT TO CONVERT TO ARRAY AND THE SORT
     * STRING ARRAY VS STRING
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


    // https://leetcode.com/problems/shifting-letters/
    public String shiftingLetters(String str, int[] shifts) {
        int n = shifts.length;
        if (n == 0) return str;
        
        char[] ch = str.toCharArray();
        int shift = 0;
        for(int i =0; i<n; i++){
            shift = shifts[i]%26;
            for(int j = 0; j<=i; j++){
                ch[j] = (char)((ch[j]-'a'+shift)%26+'a'); 
                // System.out.println(ch[j]);
            }
            
        }
        return new String(ch);
    }

    
    // https://leetcode.com/problems/first-unique-character-in-a-string/
    public int firstUniqChar(String s) {
        char[] ch = new char[26];
        for(int i =0; i<s.length(); i++){
            ch[s.charAt(i)-'a']++;
        }
        
        for(int i = 0; i<s.length(); i++){
            if(ch[s.charAt(i)-'a'] == 1) return i;
        }
        return -1;
    }


    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    public String removeDuplicates(String str) {
        StringBuilder res = new StringBuilder();
        res.append(str.charAt(0));
        
        for(int i = 1; i<str.length(); i++){
            if(res.length()!=0 && res.charAt(res.length()-1) == str.charAt(i)) 
                res.deleteCharAt(res.length()-1);
            else res.append(str.charAt(i));
        }
        return res.toString();
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


    // STRINGBUILDER - > 4 METHODS append, delete, deleteCharAt, reverse, toString
    // ddd, dee, deee
    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    public String removeDuplicates(String s, int k) {
        StringBuilder res = new StringBuilder();
        
        res.append(s.charAt(0));
        
        for(int i =1; i<s.length(); i++){
            res.append(s.charAt(i)); //imp
            int counter = 0;

            while(res.length() >= k
                  && counter<k
                  && res.charAt(res.length()-counter-1) == s.charAt(i)) counter++;

            if(res.length()>=k && counter == k) {
                res.delete(res.length()-k, res.length());
            }
            
        }
        return res.toString();
    }

    /** 
     * min dist b/w two words
     * POINTS :
     * 1 RUN A LOOP
     * 2 IF word[i] MATCHES EITHER WORD1 OR WORD2, UPDATE MINDIST
     * 3 
     * 
    */
    // https://leetcode.com/discuss/interview-question/124615/
    // given-a-list-of-words-find-the-absolute-minimum-distance-between-two-words
    int distance(String s,String w1,String w2){
        int index1 = 0; int index2 = 0;
        int minDist = 0;
        String[] words = s.split(" ");
        for(int i =0; i<words.length; i++){
            if(((w1.compareTo(words[i]) == 0) || (w2.compareTo(words[i])==0) )){
                if(w1.compareTo(words[i])==0) index1 = i;
                else if(w2.compareTo(words[i])==0) index2 = i;
                
                minDist = Math.min(minDist, Math.abs(index1 - index2+1));
            }
            
        }
        System.out.println(minDist);
        return minDist;
    }

    
    /** 
     * POINTS : 
     * 1 RUN TWO LOOPS, LEFT TO RIGHT AND VICE-VERSA
     * 2 USE STACK TO FIND DIST.
     * 3 FILL WITH N-1 AND arr[i] = 0 for C.
     * 
    */
    // "aaba", "b"; fill with n-1 and ch[C] = 0
    // https://leetcode.com/problems/shortest-distance-to-a-character/
    class Node{
        char ch; int index;
        Node(char c, int i){
            this.ch = c;
            this.index = i;
        }
    }
    
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        Deque<Node> q = new LinkedList<>();
        int[] ch = new int[n];
        Arrays.fill(ch, n-1);
        for(int i =0; i<n; i++){
            if(S.charAt(i) == C){
                while(q.size()!=0){
                    Node curr = q.removeLast();
                    ch[curr.index] = i-curr.index;
                }
                ch[i] = 0;
            }
            else{
                q.addLast(new Node(S.charAt(i), i));
            }
        }
        
        for(int i = n-1; i>=0; i--){
            if(S.charAt(i) == C){
                while(q.size()!=0){
                    Node curr = q.removeLast();
                    ch[curr.index] = Math.min(ch[curr.index], Math.abs(i-curr.index));
                }
                ch[i] = 0;
            }
            else{
                q.addLast(new Node(S.charAt(i), i));
            }
        }
        return ch;
        
    }

    // OPTIMIZED
    /** 
     * 1 START FILLING TO THE RIGHT OF FIRST OCCURENCE OF C WHILE GOING FROM L TO R
     * 2 THEN FILL TO THE LEFT
     * 3 START WITH A VALUE OF pos SUCH THAT DIST IS MAX
     * 
     * pos = -n
     * ch[i] = i-pos;
     * 
     * 4 FINALLY TAKE MIN OF BOTH DISTANCES HIE FILLING FROM RIGHT
    */
    // "aaba", "b"; fill with n-1 and ch[C] = 0
    // https://leetcode.com/problems/shortest-distance-to-a-character/
    public int[] shortestToChar2(String S, char C) {
        int n = S.length();
        int[] ch = new int[n];
        int pos = -n;
        for(int i =0; i<n; i++){
            if(S.charAt(i) == C){
                pos = i;
            }
            ch[i] = i - pos;
        }
        
        for(int i = n-1; i>=0; i--){
            if(S.charAt(i) == C){
                pos = i;
            }
            ch[i] = Math.min(Math.abs(i - pos), ch[i]);
        }
        return ch;
        
    }

    
    // https://leetcode.com/discuss/interview-question/851939/ancestor-problem
    class Name{
        String first;
        String roman;
        int num;
        Name(String f, String r, int n){
            this.first = f;
            this.roman = r;
            this.num = n;
        }
    }
    List<String> sortRoyal(List<String> names){
        PriorityQueue<Name> pq = new PriorityQueue<>((x,y)->{
            if((x.first).compareTo(y.first)==0) return x.num - y.num;
            return (x.first).compareTo(y.first); 
        });

        for(String str : names){
            int index = str.indexOf(" ");
            String first = str.substring(0, index);
            String second = str.substring(index+1);
            int num = romanToInt(second);

            pq.add(new Name(first, second, num));
        }

        List<String> res = new ArrayList<>();
        while(pq.size()!=0){
            Name curr = pq.remove(); 
            res.add(curr.first+" "+curr.roman);
        }
        return res;
    }

    public int romanToInt(String s) {
        int num = 0;
        for(int i =0; i<s.length(); i++){
            if(s.charAt(i) == 'I') num+=1;
            if(s.charAt(i) == 'V') {
                if(i>=1 && s.charAt(i-1) == 'I') num+=3;
                else num+=5;
            }
            if(s.charAt(i) == 'X') {
                if(i>=1 && s.charAt(i-1) == 'I') num+=8;
                else num+=10;
            }
            if(s.charAt(i) == 'L') {
                if(i>=1 && s.charAt(i-1) == 'X') num+=30;
                else num+=50;
            }
            if(s.charAt(i) == 'C') {
                if(i>=1 && s.charAt(i-1) == 'X') num+=80;
                else num+=100;
            }
            if(s.charAt(i) == 'D') {
                if(i>=1 && s.charAt(i-1) == 'C') num+=300;
                else num+=500;
            }
            if(s.charAt(i) == 'M') {
                if(i>=1 && s.charAt(i-1) == 'C') num+=800;
                else num+=1000;
            }
        }
        return num;
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

    
    /** 
     * POINTS :
     * 1 THE SAME CHAR ARRAY IS PASSED
     * w/o -> ch[index] = '?'
     * ch array if 0 10?
     * ch array if 0 100
     * ch array 100
     * ch array if 1 101
     * ch array 101
     * ch array if 1 111
     * ch array else 111
     * ch array 111
     * 
     * with -> ch[index] = '?'
     * ch array if 0 10?
     * ch array if 0 100
     * ch array 100
     * ch array if 1 101
     * ch array 101
     * ch array if 1 11?
     * ch array if 0 110
     * ch array 110
     * ch array if 1 111
     * ch array 111
     * 
     * ONCE THE CHAR ARRAY IS SET TO 101, 
     * THE CALL f(11?, 2) changes to f(111, 2);
     * THE LAST INDEX HAS BEEN SET 1 AND THE 
     * IF CHECK (ch[index] == '?') for f(111, 2) FAILS.
     * 
     * 
     * 
    */
    // using include-exclude concept of recursion
    /**
     * using include-exclude concept of recursion 
     * if(i == length) sysout f(arr[i], i+1) arr[i] = 0 f(arr, i+1);
     * 
     */
    // https://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/
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


    // https://www.geeksforgeeks.org/generate-binary-permutations-1s-0s-every-point-permutations/
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
    // https://www.youtube.com/watch?v=qBbZ3tS0McI

    
    /*
     * backtracking
     * helper(){
     *  for(){
     *      helper();
     *  }
     * }
     */ 
    // ["a", "abc", "d", "de", "def"]
    // https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
    int max = 0;
    public int maxLength(List<String> arr) {
        dfs(0, arr, "");
        return max;
    }
    
    void dfs(int index, List<String> list, String str){
        max = Math.max(max, str.length());
        if(index == list.size()) {
            return;
        }
        
        for(int i = index; i<list.size(); i++){
            if(hasDuplicate(str+list.get(i))) continue;
            str+=list.get(i);
            // System.out.println("before "+str);
            dfs(i+1,list, str);
            str = str.substring(0, str.length() - list.get(i).length());
            // System.out.println("after "+str);
        }
    }
    
    boolean hasDuplicate(String str){
        int[] ch = new int[26];
        for(char c : str.toCharArray()){
            if(ch[c-'a']!=0) return true;
            ch[c-'a']++;
        }
        return false;
    }

    /** 
     * POINTS:
     * 1 SIMILAR TO GCD, MATHEMATICAL CALCULATION OF LENGTH
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

    /** 
     * POINTS:
     * 1 SWAP EACH CHAR WITH ALL ITS SUCCEEDING INDEXES
     * 2 START FROM index = 0 
     * 3 FOR SUBSEQUENT ITERATION index+1
     * 4 
    */
    // all permutations
    // https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
    // List<List<String>> 
    void allPermutations(String str){
        char[] ch = str.toCharArray();
        permHelper(ch, 0);
    }

    void permHelper(char[] ch, int index){
        if(index == ch.length) System.out.println(new String(ch));
        // swap with itself, so is starts from index
        for(int i =index; i<ch.length; i++){
            swap(ch, index, i);  
            permHelper(ch, index+1);
            // swapping back to prreserve the original order of string chars
            swap(ch, index, i);
        }
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
     * 
     * 4 RETURN BOOLEAN DFS, MAINTAINING A GLOBAL VARIABLE HELPS CHECK
     * FOR ALL POSSIBLE STARTS AND DOESN'T RETURN TRUE TILL ALL POSSIBILITIES
     * ARE EXHAUSTED, WHICH RESULTS IN TLE
     * 5 
     * 
     */
    // backtracking
    // mark as ' '
    // pass the char
    // https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(board, i, j, word, 0)) return true;
                }
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int row, int col, String word, int index){
        // proceed till index == word.length() 
        if(index == word.length()) return true;
        
        if(isSafe(board, row, col, word.charAt(index))){
            char temp = word.charAt(index);
            board[row][col] = ' '; // 2
            boolean found = dfs(board, row+1, col, word, index+1) // 3
            || dfs(board, row-1, col, word, index+1)
            || dfs(board, row, col+1, word, index+1)
            || dfs(board, row, col-1, word, index+1);
            if(found) return true; // 4
            board[row][col] = temp; // 5
        }
        return false;
    }
    
    boolean isSafe(char[][] board, int row, int col, char ch){
        if(row>=0 && row<board.length
          && col>=0 && col<board[0].length
          && board[row][col] == ch) return true;
        return false;
    }

    // RABIN KARP
    /** 
     * Use Sliding Window, match the hash value of substrings
     * if match is found, sort and compare the arrays
    */
    // https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
    char[] ch2;
    int search(String pat, String txt) {
        // code here
        int sum = 0, base = 0, left = 0, count= 0;
        ch2 = pat.toCharArray();
        Arrays.sort(ch2);
        // for(char c: ch2) System.out.print(c+", ");
        // System.out.println();
        for(char c : pat.toCharArray()){
            base+=Integer.parseInt(""+(c-'a'));
        }
        
        for(int i = 0; i<pat.length(); i++){
            sum+=Integer.parseInt(""+(txt.charAt(i)-'a'));
        }
        
        if(sum == base && compare(txt.substring(0, pat.length()))) count++;
        
        for(int i = pat.length(); i<txt.length(); i++){
            sum+=Integer.parseInt(""+(txt.charAt(i)-'a'));
            sum-=Integer.parseInt(""+(txt.charAt(left++)-'a'));
            if(sum == base && compare(txt.substring(left, i+1))) count++;
        }
        return count;
    }
    
    
    boolean compare(String str1){
        char[] ch1 = str1.toCharArray();
        
        Arrays.sort(ch1);
        // for(char c: ch1) {
        //     System.out.print(c+", ");
        // }
        
        if(Arrays.equals(ch1, ch2)) return true;
        return false;
    }

    /**
     * https://www.youtube.com/watch?v=KRQSOygJvuU
     * netaseta, 
     *  
     * POINTS :
     * 1 RUN 2 LOOPS 
     * 2 CHECK IF dp[i][j] > j-i
     * 3 UPPER HALF 
     * 
     * imp : fill first row and check for overlap dp[i][j] > j-i
     * 
     * DP
    */
    int longestRepeatingNonOverlappingSubstring(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        int max = 0;
        // first row filled with 0 or 1
        for(int i =1; i<n; i++){
            if(str.charAt(0) == str.charAt(i)) dp[0][i] = 1;
        }

        for(int i =1; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(str.charAt(i) == str.charAt(j)){
                    if(i == 0) dp[i][j] = 0;
                    else dp[i][j] = 1 + dp[i-1][j-1];

                    // overlap
                    if(dp[i][j]> j-i) dp[i][j] = 0;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        for(int i =0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.print(dp[i][j]+", ");
            }
            System.out.println();
        }
        System.out.println("longest length repeated non-overlapping susbstring is "+max);
        return max;
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

        return new String(ch1).compareTo(new String(ch2)) == 0 ? true : false;
    }

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
    // https://leetcode.com/problems/group-anagrams/
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
     * HERE WE ADD ALL SEEN SUBSEQUENCES, THEY BE VALID OR NOT.
     * SO WE DON'T HAVE TO CHECK VALIDITY AGAIN
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
        int j = 0;
        
        for(int i =0; i<a.length(); i++){
            if(a.charAt(i) == b.charAt(j)) i++; j++;
            // if end reached, substring
            if(j == b.length()) return true;
        }
        // match j with b.length()
        return j==b.length();
    }


    /** 
     * POINTS :
     * 1 DIFF FROM ABOVE AS FooBar WITH FB IS true BUT
     * FooBarTest WITH FB IS false
     * WEE NEED TO CONTINUE CHECKING FOR AN UPPERCASE BECAUSE
     * HAD T NOT BEEN THERE, FooBarest WILL RETURN true.
     * 
     * 2 return j == pattern.length();
    */
    // https://leetcode.com/problems/camelcase-matching/
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for(String q : queries){
            if(isMatch(q, pattern)) res.add(true);
            else res.add(false);
        }
        return res;
    }
    
    boolean isMatch(String query, String pattern){
        int j = 0;
        for(int i =0; i<query.length(); i++){
            // out of bounds, add check
            if(j<pattern.length() && query.charAt(i) == pattern.charAt(j)) j++;
            // [FooBarTest"] -> FoBa", fails for T so check added
            else if(Character.isUpperCase(query.charAt(i))) return false;
            // if(j== pattern.length()) return true; // j out of bounds 
        }
        return j == pattern.length();
    }


    void stringtoWords(String number) {}

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
        /** 
     * POINTS : 
     * 1 FOR ANY INCOMING CHAR, IF IT EXISTS IN VISITED SET, CONTINUE
     * BECAUSE THE CHAR IS ALRFEADY AT ITS BEST POSN
     * 
     * 2 IF LAST CHAR IS LEXICOGRAPHICALLY GREATER AND IT'S FREQ IS >0, 
     * IT MEANS THAT CHAR CAN BE USED LATER, SO IT'S SAFE TO REMOVE IT.
     * REMEMBER TO REMOVE IT FROM VISITED SET AS WELL
     * 
     * 3 USE STRING BUILDER, deleteCharAt CAN BE USED AND .toString()
     * NEEDS TO BE USED
     * 
     * 4
     * */
    // https://leetcode.com/problems/smallest-subsequence-of-distinct-characters
    public String smallestSubsequence(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        StringBuilder res = new StringBuilder();
        
        for(char c : text.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        for(char c : text.toCharArray()){
            map.put(c, map.get(c)-1);
            
            if(set.contains(c)) continue; 

            while(res.length()>0 && res.charAt(res.length()-1) > c 
                && map.get(res.charAt(res.length()-1)) > 0 ){
                    char last = res.charAt(res.length()-1); 
                    set.remove(last);//out of bounds error
                
                    // map.put(c, map.get(last)+1);//don't add
                    res.deleteCharAt(res.length()-1);
            }
            // if(res.length()==0 || res.charAt(res.length()-1) != c){
            // if(set.contains(c)) continue; 
            res.append(c);
            set.add(c);
            // } 
            // System.out.println(res+ " "+map);
        }
        return res.toString();
    }

    // SAME AS ABOVE BUT WIHOUT STRINGBUILDER AND HASHSET
    /**  
     * BASICALLY 3 CONDNS
     * 1 IF PRESENT IN RES, CONTINUE
     * 2 KEEP REMOVING TILL LENGTH ==0 OR IF LAST CHAR IS 
     * GREATER AND HAS FREQ>=1 REMOVE
     * 3 IF LAST CHAR IS SMALLER, ADD
    */
    // https://leetcode.com/problems/smallest-subsequence-of-distinct-characters
    public String smallestSubsequenceString(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        String res = "";
        
        for(char c : text.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        for(char c : text.toCharArray()){
            map.put(c, map.get(c)-1);

            if(res.contains(""+c)) continue; 
            while(res.length()>0 
                && res.charAt(res.length()-1) > c 
                && map.get(res.charAt(res.length()-1)) > 0 ){
                    res = res.substring(0, res.length()-1);
            }
            res+=c;
        }
        return res;
    }


    // MIN WINDOW
    // works without duplicates,
    // leetcode prob statement seems inconsistent
    // https://leetcode.com/problems/minimum-window-substring/
    // https://leetcode.com/problems/minimum-window-substring/discuss/26808/
    // Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
    

     /** POINTS : 
     * MOST IMP ANAGRAM SO WINDOW SIZE WILL REMAIN SAME
     * 1 CREATE AN ARRAY TO SERVE AS A PRIMARY MAP(OR DICTIONARY)
     * WHICH WILL STORE THE COMMON ELEMENTS
     * 2 CREATE A NEW ARRAY FOR EACH SUBSEQUENT STRING AND STORE THE FREQ
     * 3 UPDATE THE PRIMARY ARAY TO STORE COMMON OF BOTH PRIMARY ABND CURR
     * 
     * SIMILAR TO INTERSECTION OF 2 ARRAYS
     */
    // s = "ADOBECODEBANC", t = "ABC"
    // https://leetcode.com/problems/minimum-window-substring
    public String minWindow(String s, String t) {
        if(s == null || s.length() < t.length() || s.length() == 0) return "";
        HashMap<Character, Integer>map = new HashMap<>();
        
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        
        int start = 0; int left = 0; int count = 0; int len = Integer.MAX_VALUE;
        
        for(int i =0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get( s.charAt(i) ) -1);
                if(map.get(s.charAt(i)) >= 0) count++;
            }
            
            while(count == t.length()) {
                // if smaller len found, update len and starting char
                if(i-left+1 < len){
                    start = left;
                    len = i-left+1;
                }
                // now increment the freq which was reduced
                if(map.containsKey(s.charAt(left))){
                    map.put(s.charAt(left), map.get(s.charAt(left))+1);
                    if(map.get(s.charAt(left))>0) count--;
                }
                left++;
            }
        }
        if(len>s.length()) return ""; 

        return s.substring(start, start+len);
    }

    // https://leetcode.com/problems/word-subsets/

    /** 
     * POINTS : 
     * 1 FOR EACH CHAR, IF PRESENT IN SET, REMOVE CHAR AT LEFT TILL 
     * CURRENT CHAR IS REMOVED.
     * 2 PUT IN MAP
     * 3 UPDATE LENGTH
     * 
     * handle aab, pwwke, dvdf
     * 
    */
    // https://leetcode.com/problems/longest-substring-without-repeating-characters
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0; int len = 0;
        for(int i =0; i<s.length(); i++){
            if(set.contains(s.charAt(i))) {
                while(set.contains(s.charAt(i)) && left!=i) set.remove(s.charAt(left++));
            }
            set.add(s.charAt(i));
            len = Math.max(len, i- left+1);
        }
        return len;
    }

    /**
     * IMP TECHNIQUE TO GENERATE SUBSTRINGS 
     * 1 i = 0 till n-1 2 j = 0 till n
     * substring takes start and end ptr, includes start but excludes end ex.
     * ("abc").subsring(2,3) = "c" index 2 = 2 till index 
     * 3. Out of bounds error
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


    /** 
     * Min Adj Swaps to Make Palindrome :
     * 1 START FROM LEFT AND GO TILL n/2; FOR EVEN AND ODD BOTH
     * 2 COMPARE I AND RIGHT, IF NOT SAME MOVE p TILL SAME CHAR IS FOUND
     * 3 IF i== p, MIDDLE CHAR, HENCE MOVE TO MID
     * 4 USE A FLAG WHEN MOVE TO MID OCCURS
     * 5 
     * 
    */
    // https://www.youtube.com/watch?v=zXpYs8j5oI8
    // abaeacabdcccd 21, asflkj -1, 
    // https://leetcode.com/discuss/interview-question/351783/
    int swapCount = -1;
    int minSwaps(String str){
        if(!canBePalindrome(str)) {
            System.out.println("can't be made a palindrome");
            return swapCount;
        }
        int n= str.length();
        swapCount = 0;
        char ch[] = str.toCharArray();
        int right = n-1;
        boolean midChar = false;
        for(int i =0; i<n/2; i++){
            int p = right;
            if(ch[i] != ch[p]){
                System.out.println("i "+i+" right "+right);
                while(ch[p] != ch[i] && p>=i) {
                    // middle char, single occurence
                    p--;
                    if(i == p){
                        System.out.println("in here with "+ch[p]);
                        midChar = true;
                        move(ch, i, n/2);
                        continue;
                    }
                }
                if(!midChar)move(ch, p, right);
            }
            if(midChar){
                // moving to same i in case of middle char
                i--;
            }else{
                right--;
            }
            midChar = false;

        }
        System.out.println("no of adj swaps needed "+swapCount);
        return swapCount;
    }

    void move(char[] ch, int start, int end){
        char temp = ch[start];
        for(int i =start; i<end; i++){
            ch[i] = ch[i+1];
            swapCount++;
        }
        ch[end] = temp;
        System.out.println("start "+start+" end "+end + " "+ new String(ch));
    }

    boolean canBePalindrome(String str){
        int odd = 0;
        int[] ch = new int[26];
        for(char c: str.toCharArray()) ch[c-'a']++;
        for (int i : ch) if (i % 2 != 0) odd++;
        return odd <= 1;
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
        // Utility.printMatrix(dp);
        System.out.println("all palindromic substrings' count : " + count);
        return count;
    }


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
     * 3 UPDATE THE PRIMARY ARAY TO STORE COMMON OF BOTH PRIMARY AND CURR
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


    // https://leetcode.com/problems/longest-word-in-dictionary/
    public String longestWord(String[] words) {
        Arrays.sort(words, (x, y)->{
            if(x.length() == y.length()) return x.compareTo(y);
            return x.length() - y.length();   
        });
        
        String res = "";
        for(String str : words){
            if(isValid(res, str)) res+=str;
            else return res;
        }
        return res;
    }
    
    boolean isValid(String a, String b){
        if(b.length() - a.length() != 1) return false;
        if(!b.startsWith(a)) return false;
        return true;
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
    /** 
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     * 
     * SORT AND COMPARE ONLY FIRST AND LAST WORDS CHAR BY CHAR
     * FIND COUNT
    */
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
     * 
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
    // https://leetcode.com/problems/partition-labels/
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


    /** 
     * POINTS :
     * 1 NESTED LOOPS
     * 2 USE OR BECAUSE IF ONE STRING RUNS OUT, COMPARISON SHOULD
     *  BE MADE WITH 0
     * 3 SUBTRING GOES ONE AHEAD, e1 GOES TILL DOT, 
     * SO version1.substring(s1, e1)
     * 4 INCREMENT e1 AND s1
     *  
     * imp : if s1 == e1 substring will fail, add a check
    */
    // to handle "1.01.01", "1.001"
    // https://leetcode.com/problems/compare-version-numbers/
    public int compareVersion(String version1, String version2) {
        int s1 = 0; int s2 = 0;
        int e1 = 0; int e2 = 0;
        int num1 = 0; int num2 = 0;
        // diff lengths, so or
        while(s1<version1.length() || s2<version2.length()){
            while(e1<version1.length() && version1.charAt(e1)!='.') e1++;
            if(s1 == e1) num1 = 0;
            else num1 = Integer.parseInt(version1.substring(s1, e1));
            e1++; s1 = e1;
            
            while(e2<version2.length() && version2.charAt(e2)!='.') e2++;
            if(s2 == e2) num2 = 0;
            else num2 = Integer.parseInt(version2.substring(s2, e2));
            e2++; s2 = e2;
            
            // System.out.println("num1 "+num1+" num2 "+num2);
            if(num1 != num2) return num1-num2>0?1:-1;
        }
        return 0;
    }
  
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


    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
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
     * 
     * imp : 2 indexes, one for given and one for curr
     * 2 Arrays.asList();
     * 3 str.charAt(i) - '0'
    */


    // https://leetcode.com/problems/letter-combinations-of-a-phone-number
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        HashMap<Integer, List<Character>> map = new HashMap<>();
        
        map.put(2, Arrays.asList('a', 'b', 'c')); // 1
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));
        map.put(5, Arrays.asList('j', 'k', 'l'));
        map.put(6, Arrays.asList('m', 'n', 'o'));
        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        map.put(8, Arrays.asList('t', 'u', 'v'));
        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));
        
        
        helper(res, "", digits, map, 0);
        return res;
    }
    
    // imp parameters 2 indexes, one for digits, one for list
    void helper(List<String> res, String curr, String digits, HashMap<Integer, List<Character>> map, int index){ 
        if(index == digits.length()) {
            res.add(new String(curr));
            return;
        }
        
        List<Character> list = map.get(digits.charAt(index) - '0'); // 2
        
        for(int i =0; i<list.size(); i++){
            curr+=list.get(i);
            helper(res, curr, digits, map, index+1);
            curr = curr.substring(0,curr.length()-1);
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
    
    //adds the letter n times, n = number.peek() 
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

    

    // https://leetcode.com/discuss/interview-question/558379/
    
    /** 
     * POINTS :
     * 1 dp size [m+1][n+1]
     * 2 assign first row and col -> i to match with null string
     * 3 start from i=1, j=1 and word.charAt(i-1) && word.charAt(j-1)
     * 
    */
    // https://leetcode.com/problems/edit-distance
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
    /** 
     * SIMILAR TO MERGE INTERVALS
     * CHECK curr.charAt(prev.length()) == '/'
     * IF '/' IS FOUND A SUBFOLDER SO CAN IGNORE
     * 
     * POINTS : 
     * 1 SORT THE ARRAY
     * 2 USE STARTSWITH; SIMILAR TO MERGE INTERVALS
     * 3 prev = folder[0]; 
     * curr.startsWith(prev) && curr.charAt(prev.length()) == '/'
     * 
     * 4 if curr starts with prev and has '/', 
     * it must be a subfolder, don't add to res
     * 
     * 
    */
    // https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
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
     * 2 IF LENGTH IS 1 CHECK IF 0, THEN 0 AS A STARTS FROM 1
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
     * 1 PUT FREQ IN HASHMAP
     * 2 CREATE A CUSTOM CLASS TO STORE STRING AND CORR FREQ
     * 3 USE A PQUEUE TO SSORT STRINGS
     * ((x,y)->{
     *     if(x.freq == y.freq) return x.str.compareTo(y.str);
     *     return y.freq - x.freq;
     * })
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

    

    /** basically we keep a char array(base) to keep count of pattern
    and use a new array(curr) while sliding over the string. 
    Whenever the arrays match, we store the start index of the current window.

    Fow sliding window, fill the first window and then subsequent windows.

    if(Arrays.equals(base, curr)) res.add( i-pattern.length() + 1); 

    POINTS : 
     * 1 USE A CHAR ARRAY NOT A HASHMAP, IT'S EASIER TO COMPARE WITH ARRAYS.EQUALS
     * 2 STORE PATTERN'S COUNT IN A CHAR ARRAY(NAMED 'BASE') OF SIZE 26
     * 
     * 3 NOW SLIDING WINDOW CONCEPT COMES. IT IS DONE IN 3 STEPS : 
     * FIRST WINDOW AND THEN ALL OTHER WINDOWS,
     * TRAVERSE FROM i TILL n (PATTERN LENGTH) AND STORE IN A NEW ARRAY--> FIRST WINDOW
     * AND THEN SLIDE RIGHT BOUNDARY TILL END(STRING LENGTH) --> OTHER WINDOWS
     * * IN BETWEEN, IF THE CONDN IS MET, (SET.SIZE == n), TRY TO SHRINK THE WINDOW

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
        if (s.length() == 0) return res;
        int n = t.length();
        
        if (n > s.length()) return res; // 1 

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
     * 1 THE IDEA IS TO KEEP 1 FLAG lower.
     * 2 ONCE DONE WITH FIRST 2 CHARS, FOR NEXT CHARS, 
     * IF UPPERCASE CHAR COMES AND lower is true, THEN RETURN FALSE;
     * IF LOWERCASE AND lower is false, RETURN FALSE;
     * 3 FOR FIRST 2 CHARS, check all 4 combinations 
     * lower, upper(lu), ll, ul, uu
     * lu -> false
     * ll -> lower(true)
     * uu -> lower(false)
     * ul -> lower(true)
     * 
     * https://leetcode.com/problems/detect-capital/discuss/
     * 860902/Java-3-Flags-simple-solution
    */
     // "FlaG", "USa", "fffF", "mL"
    // https://leetcode.com/problems/detect-capital/
    public boolean detectCapitalUse(String word) {
        boolean lower = true;
        
        // check for length 1
        if(word.length() ==1) return true;
        
        if(Character.isLowerCase(word.charAt(0))){ 
           if(Character.isLowerCase(word.charAt(1))){
            // only lower allowed
            lower = true;
           }else return false;
        }
        else if(Character.isUpperCase(word.charAt(0))) {
             if(Character.isLowerCase(word.charAt(1))) lower = true;
             else if (Character.isUpperCase(word.charAt(1)))
            // only upper allowed
            lower = false;
        }
        
        for(int i =2; i<word.length(); i++){
            if(Character.isUpperCase(word.charAt(i)) && lower){
                return false;
            }
            else if(Character.isLowerCase(word.charAt(i)) && !lower){
                return false;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/add-binary/
    // public String addBinary(String a, String b) {
    //     char[] ch = a.toCharArray();
        
    // }

    /**
     * String is immutable
     * 
     * after word is created 
     * check if in used, continue; 
     * check if in unused(valid), add to q
     * unused is required, as wordList is too slow for checking
     */
    // https://leetcode.com/problems/word-ladder/submissions/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        HashSet<String> used = new HashSet<>(); // 1
        HashSet<String> unused = new HashSet<>(); // 2
        Deque<String> q = new LinkedList<>(); // 3
        int distance = 1; // 4
        
        for(String str : wordList) unused.add(str); // 5
        used.add(beginWord);
        q.addLast(beginWord);
        
        while(q.size()!=0){
            int size = q.size(); // 6
            for(int i =0; i<size; i++){
                String curr = q.removeFirst();
                // string is immutable, convert to char array
                char[] ch = curr.toCharArray(); // 7
                
                for(int j = 0; j<curr.length(); j++){
                    
                    char temp = ch[j];
                    for(char c = 'a'; c<='z'; c++){ // 8
                        ch[j] = c; // 7
                        String created = String.valueOf(ch);
                        if(created.equals(endWord)) { // 9
                            // System.out.println(created);
                            // System.out.println(created.equals(endWord));
                            return distance+1;
                        }
                        if(used.contains(created)) continue; // 10
                        //if unused
                        if(unused.contains(created)){ // 11
                            // System.out.println("in here "+ch);
                            used.add(created); // 9
                            unused.remove(created);
                            q.addLast(created);
                        }
                    }
                    ch[j] = temp; // 12
                }
            }
            distance++;
        }
        
        return 0;
    }
    
    // https://www.youtube.com/watch?v=KAoRNDx-S8M
    // https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings
    HashSet<String> splitSet;
    public int maxUniqueSplit(String s) {
        int n = s.length();
        if(n==0) return 0;
        splitSet = new HashSet<>();
        return splitHelper(s);
    }

    int splitHelper(String str){
        int countSplit = 0;
        
        for(int i = 1; i<=str.length(); i++){
            String sub = str.substring(0, i);
            if(!splitSet.contains(sub)){
                splitSet.add(sub);
                // System.out.println(splitSet);
                countSplit = Math.max(countSplit, 
                                1 + splitHelper (str.substring(i, str.length() ) ));
                splitSet.remove(sub); //if commented, fails for "wwwzfvedwfvhsww"
            }
        }
        return countSplit;
    }

    // TIME BASED QUES
    // https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
    /** 
     * 1 CONVERT TO MINUTES
     * 2 SORT, FIND MIN
     * 3 FIND MIN DIFF B/W ADJACENTS 
     * 4 CHECK THE CORNER CASE AT THE END
     * int corner = times[0] + (1440 - times[times.length-1]);
     * 
    */
    // find min minutes difference between any two time-points in the list
    // https://leetcode.com/problems/minimum-time-difference
    public int findMinDifference(List<String> timePoints) {
        // String time1 = timePoints.get(0);
        // String time2 = timePoints.get(1);
        
        int[] times = new int[timePoints.size()];
        for(int i =0; i<timePoints.size(); i++){
            times[i] = convertToMinutes(timePoints.get(i));
        }
        Arrays.sort(times);
        int min = Integer.MAX_VALUE;
        
        for(int i = 1; i<times.length; i++){
            int diff = times[i]-times[i-1];
            // if(diff>720) diff = 1440-diff;
            // min = Math.min(min, diff);
            min = Math.min(min, diff);
        }
        int corner = times[0] + (1440 - times[times.length-1]);
        return Math.min(min, corner);
    }
    
    int convertToMinutes(String time){
        int minutes= Integer.parseInt(time.substring(0,2))*60;
        minutes+= Integer.parseInt(time.substring(3));
        return minutes;
    }

    
    // https://leetcode.com/problems/largest-time-for-given-digits
    public String largestTimeFromDigits(int[] arr) {
        // start from 1439 (23:59 is the largest time)
        // keep decrementing while checking validity
        
        HashMap<Integer, Integer> base = new HashMap<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr) base.put(i, base.getOrDefault(i, 0)+1);
        
        int res = 1439;
        
        while(res>=0000){
            map = (HashMap)base.clone();
            if(isValid(res, map)) return timeFormat(res);
            res--;
        }
        return ""; // if no valid time
    }
    
    boolean isValid(int time, HashMap<Integer, Integer> map){
        // convert to HH:MM format
        int[] num = new int[]{time/60/10, time/60%10, time%60/10, time%60%10};
        for(int i : num){
            if(!map.containsKey(i) || map.get(i) <= 0) return false;
            map.put(i, map.getOrDefault(i,0)-1);
        }
        map.clear();
        return true;
    }
    
    
    String timeFormat(int time){
        String res = "";
        res+=time/60/10; res+= time/60%10; res+=":";
        res+=time%60/10; res+= time%60%10;
        return res;
    }

    
    // https://leetcode.com/problems/add-binary/
    // https://leetcode.com/problems/longest-duplicate-substring/
    // https://leetcode.com/problems/next-closest-time/
    // https://www.codertrain.co/next-closest-time


    // https://stackoverflow.com/questions/59549234/
    // how-to-determine-the-smallest-common-divisor-of-a-string
    // #:~:text=If%20t%20divides%20s%2C%20then,of%20that%20length%
    // 20divides%20t.&text=This%20will%20tell%20you%20all,are%20also%20
    // prefixes%20of%20t.

    // https://leetcode.com/problems/multiply-strings/
    // https://leetcode.com/problems/boats-to-save-people/
    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
    // https://leetcode.com/problems/camelcase-matching/
    // https://leetcode.com/problems/implement-trie-prefix-tree/
    // https://leetcode.com/problems/string-to-integer-atoi/discuss/4922/Java-Simple-clean-and-fast!

    // https://www.geeksforgeeks.org/minimum-cost-to-partition-the-given-binary-string/
    // https://stackoverflow.com/questions/32907406/
    // is-there-an-efficient-algorithm-for-integer-partitioning-with-restricted-number
    public static void main(String[] args) {
        StringPractice string = new StringPractice();
        // System.out.println(string.reverse("word of"));
        // System.out.println(string.reverseWordOrderInAString("I love Java
        // Programming"));
        // string.convertToTitle(3);
        String perm = "ABC";
        string.allPermutations(perm);

        // string.reverse("abc");
        String x = "123";

        // System.out.println(Integer.parseInt(x)+1);//4
        String str = "GeeksForGeeks12";
        // char[] in = str.toCharArray();
        // System.out.println(stringPractice.to_upper(in));

        // System.out.println((char)('a'+2));
        String str1 = "1???";
        // string.generateBinPattern(str1);

        // string.genBinPerm01s(4);
        // string.recPalindrome("abba");

        char[][] grid = {{ 'A', 'C', 'P', 'R', 'C' }, 
                        { 'X', 'S', 'O', 'P', 'C' }, 
                        { 'V', 'O', 'V', 'N', 'I' },
                        { 'W', 'G', 'F', 'M', 'N' }, 
                        { 'Q', 'A', 'T', 'I', 'T' } };

        String word = "MICROSOFT";
        // string.findWordInGrid(grid, word);

        // System.out.println(string.isAnagram("str1", "str1"));
        // System.out.println(string.isAnagramStringCompare("str1", "str2"));

        String s = "othello";
        String s1  = "aba";
        // System.out.println(s+" is palindrome "+string.palindrome(s));
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
        // string.minWindow(S, T);

        String palin = "ac";// "hellolle";
        // string.countAllPalindromes(palin);

        // string.countPalindromicSubstrings(palin);
        // string.longestPalindromicSubstring(palin);

        String partition = "ababcbacadefegdehijhklij";
        // string.partitionLabels(partition);

        // System.out.println(string.compareVersion("0.1.2", "0.01.2"));

        String reorgStr = "aabbc";
        // string.reorganizeString1(reorgStr);
        // string.reorganizeString2(reorgStr);
        String balanceLR = "RLLLLRRRLR";
        // string.balancedStringSplit(balanceLR);

        String keyPad = "23";
        // string.letterCombinations(keyPad);

        char[] toCompress = new char[]{'a','a','b','b','c','c','c'};
        // string.compress(toCompress);

        char a = 'a'; char b = 'b';
        // System.out.println(a-b);

        String strSwap = "abaeacabdcccd";//"abaeacdcccdab";//"asflkj";//"mamadee";//"mademaed";//
        // string.minSwaps(strSwap);

    }
}