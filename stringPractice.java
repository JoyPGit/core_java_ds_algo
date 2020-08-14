import java.util.*;

class StringPractice {

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == arr.length - 1 && j == arr[0].length - 1) {
                    System.out.println(arr[i][j] + ";");
                    System.out.println();
                } else
                    System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void hashiterator(HashMap<Integer, Integer> list){
        for (Map.Entry<Integer, Integer> entry : list.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key "+key+" value " +value);
        }
    }

    String sortString(String s){
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        // System.out.println(ch);
        return new String(ch);
    }

    /**
     * s.length() braces needed
     * 
     * QUESTIONS: 1 ALL SUBSTRINGS(2 loops) 
     * 2 ALL PALINDROME QUES (use SUSBTRING TECHNIQUE FROM lexicographicSubConcat) 
     * 3 PARTITION LABELS (HASHING) 
     * 4 MIN WINDOW CONTAINING K DISTINCT (HASHING, WINDOW SHRINKING) 
     * 5 SEARCH WORD IN GRID (DFS) 
     * 6 INCLUDE OR EXCLUDE TYPE : GEN BINARY PATTERN (RECURSION)
     * 7 VERSION COMPARE
     * 8 GROUP ANAGRAM
     */

    String reverse(String word) {
        char[] ch = word.toCharArray(); // 1 tocharArray
        int n = ch.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = ch[n - i - 1];
            ch[n - i - 1] = ch[i];
            ch[i] = temp;
        }
        return new String(ch); // 2 new String to convert char array to string
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

    // using backtracking concept
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
            int curIndex = index;
            if (ch[index] == '?') {
                ch[index] = '0';
                // curIndex = index + 1;
                // System.out.println(index);
                // System.out.println(curIndex);
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

    void findWordInGrid(char[][] grid, String str) {
        char[] ch = str.toCharArray();
        // int index = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // if (grid[i][j] == ch[index]) {
                findWordInGridUtil(grid, ch, 0, i, j);
                // }
            }
        }
    }

    // void findWordInGridUtil(char[][] grid, char[] ch, int index, int rowIndex,
    // int colIndex) {
    // if (rowIndex >= grid.length && colIndex >= grid[0].length || rowIndex < 0 &&
    // colIndex < 0)
    // return;
    // int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    // int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };
    // if (index == ch.length - 1) {
    // System.out.println("found");
    // return;
    // }
    // for (int k = 0; k < row.length; k++) {
    // if ((rowIndex + row[k]) < grid.length && (colIndex + col[k]) < grid[0].length
    // && (rowIndex + row[k]) > -1
    // && (colIndex + col[k]) > -1 && (index + 1) < ch.length) {
    // if (ch[index + 1] == grid[rowIndex + row[k]][colIndex + col[k]]) {
    // // System.out.print(ch[index+1]+", ");
    // findWordInGridUtil(grid, ch, index + 1, rowIndex + row[k], colIndex +
    // col[k]);
    // }
    // }
    // }
    // }

    void findWordInGridUtil(char[][] grid, char[] ch, int index, int rowIndex, int colIndex) {
        if (rowIndex < grid.length && colIndex < grid[0].length && rowIndex >= 0 && colIndex >= 0
                && ch[index] == grid[rowIndex][colIndex]) {
            int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
            int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };
            if (index == ch.length - 1) {
                System.out.println("found");
                return;
            }
            for (int k = 0; k < row.length; k++) {
                findWordInGridUtil(grid, ch, index + 1, rowIndex + row[k], colIndex + col[k]);
            }
        }
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

    boolean isAnagram(String str1, String str2) {
        HashMap<Character, Integer> list = new HashMap<>();

        for (char c : str1.toCharArray()) {
            if (list.containsKey(c)) {
                list.put(c, list.get(c) + 1);
            } else
                list.put(c, 1);
        }

        System.out.println(list);

        for (char c : str2.toCharArray()) {
            if (list.containsKey(c)) {
                list.put(c, list.get(c) - 1);
                // System.out.println(list.get(c));// returns the value
                if (list.get(c) == 0)
                    list.remove(c);
            } else
                return false;
        }

        System.out.println("final " + list);
        return list.isEmpty();
    }

    //works in leetcode
    // public List<List<String>> groupAnagrams(String[] strs) {
    //     if (strs == null || strs.length == 0) return new ArrayList<>();
    //     Map<String, List<String>> map = new HashMap<>();
    //     for (String s : strs) {
    //         String keyStr = sortString(s);
            
    //         //optimization adding in both cases
    //         if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
    //         map.get(keyStr).add(s);
    //     }
    //     return new ArrayList<>(map.values());
    // }

    public String removeKdigits(String num, int k) {
        String str = num;
        char[] ch = str.toCharArray();

        while (k != 0) {
            int maxIndex = ch[0] > ch[1] ? 0 : 1;
            move(ch, maxIndex, ch.length - 1);
            k--;
        }

        String ans = String.valueOf(ch);
        String ans1 = ans.substring(0, ans.indexOf(" "));
        // System.out.println(ans1);
        // System.out.println(String.valueOf(ch));

        System.out.println(ans.indexOf(" "));
        return ans1;
        // return String.valueOf(ch);
    }

    void move(char[] ch, int start, int end) {
        for (int i = start; i < end; i++) {
            ch[i] = ch[i + 1];
        }
        ch[end] = ' ';
    }

    // unsolved solve using hash and shrinking technique
    // ababcbacadefegdehijhklij
    // https://leetcode.com/problems/partition-labels/
    // public ArrayList<

    void keyPadPrint(String str) {
        if (str == "1") {
        }
        if (str == "2") {
        }
        if (str == "3") {
        }
        if (str == "4") {
        }
        if (str == "5") {
        }
        if (str == "6") {
        }
        if (str == "7") {
        }
        if (str == "8") {
        }
        if (str == "9") {
        }
        // keyPadPrintUtil(str);
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

    // https://leetcode.com/problems/split-a-string-in-balanced-strings/
    // "RLLLLRRRLR"
    public int balancedStringSplit(String s) {
        Deque<Character> list = new LinkedList<>();
        list.addLast(s.charAt(0));
        
        int count =0;
        for(int i =1; i<s.length(); i++){
            if(!list.isEmpty() && list.getLast()==s.charAt(i)){
                list.addLast(s.charAt(i));
                System.out.println(list +" line add");
            }
            else if(list.size()!=0 && list.getLast()!=s.charAt(i)){
                list.removeLast();
            }
            else if(list.size()==0) list.addLast(s.charAt(i));
            System.out.println(list);
            if(list.size()==0)count++;
        }
        System.out.println("count "+count);
        return count;
    }

    // RABIN KARP algo pattern searching

    // MIN WINDOW
    // works without duplicates,
    // leetcode prob statement seems inconsistent
    // https://leetcode.com/problems/minimum-window-substring/
    class minWindowString {
        int start;
        int end;

        minWindowString(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        if (s.length() == t.length()) {
            System.out.println("in here");
            // System.out.println(s.equals(t)==false);
            if (!s.equals(t))
                return "";
        }

        String result = "";
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (t.contains(s.charAt(i) + "")) {
                map.put(ch[i], i);

                if (map.size() == t.length()) {
                    int min = getMin(map);
                    int max = getMax(map);
                    // substring is inclusive of lower index only and not higher
                    String str = s.substring(min, max + 1);

                    if (result.length() == 0) {
                        result = str;
                        System.out.println("str first " + str);
                    } else
                        result = result.length() < str.length() ? result : str;
                }

            }
        }
        System.out.println("Result " + result);
        return result;
    }

    int getMin(HashMap<Character, Integer> map) {
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            min = Math.min(min, value);
        }
        // System.out.println("min "+ min);
        return min;
    }

    int getMax(HashMap<Character, Integer> map) {
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            max = Math.max(max, value);
        }
        // System.out.println("max "+ max);
        return max;
    }

    /**
     * IMP TECHNIQUE TO GENERATE SUBSTRINGS 1 i = 0 till n-1 2 j = 0 till n
     * substring takes start and end ptr, includes start but excludes end ex.
     * ("abc").subsring(2,3) = "c" index 2 = 2 till index 3 Out of bounds error
     * doesn't occur
     */

    String lexicographicSubConcat(String s) {
        int n = s.length();

        // Creating an array to store substrings
        int sub_count = n * (n + 1) / 2;
        String[] arr = new String[sub_count];

        // finding all substrings of string
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int len = i + 1; len <= n; len++) {
                System.out.println(s.substring(i, len));
                arr[index++] = s.substring(i, len);
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
     * points : 1 dp size [n][n] 2 l here denotes length of substring; goes till n;
     * starts from 1 3 i+l-1 denotes the ending index; goes till n-1
     * 
     * aaa; l= 3; i=0; j = 2; l= 2; i=0; j = 1; i=1; j = 3;
     * 
     * 4 if l==1; dp[i][j] = 1; each char is a palindrome 5 if l==2 check only if
     * s(i) == s(j); no need to check internal substrings 6 l>=3 check s(i) == s(j)
     * and also internal substrings(dp[i+1][j-1]) 7 increment count whenever
     * dp[i][j] =1;
     * 
     * UPPER TRIANGULAR MATRIX
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
        printMatrix(dp);
        System.out.println("all palindromic substrings' count : " + count);
        return count;
    }

    /**
     * longest palindrome 1 create a upper triangular dp and keep a flag for max
     * length 2 using Manacher's concept of EXPANDING AROUND MIDDLE
     * https://www.youtube.com/watch?v=y2BD4MJqV20&t=768s
     */

    // technique 1 using upper triangular matrix
    /**
     * points : similar to all palindromic substrings' count 1 if the whole string
     * is a palindrome and if it's allowed l<=n; else l<n l is imp; denotes
     * substring length; starts from 1
     * 
     * 2 maxlen is 1 as every char is a palindrome; it keeps track of longest length
     * 3 start index is updated everytime 4 substring start, start+maxlen
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
        System.out.println("longest palindromic substring : " + 
                                s.substring(start, start + maxlen));
        return s.substring(start, start + maxlen);
    }

    // https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/
    // all palindromes , string 'abaab'

    // doesn't work now
    int palinCount = 0;

    int countAllPalindromes(String s) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            palinCount++;
            expand(ch, i, i + 1);
            expand(ch, i, i);
        }
        System.out.println(palinCount);
        return palinCount;
    }

    void expand(char[] ch, int start, int second) {
        if (start < 0 || second >= ch.length)
            return;
        if (start == 0 || second == ch.length - 1) {
            palinCount = (ch[start] == ch[second]) ? ++palinCount : palinCount;
            System.out.println((ch.toString()).substring(start, second + 1));
            System.out.println("count " + palinCount);
            return;
        }
        if (ch[start] == ch[second])
            expand(ch, --start, ++second);
        else
            return;
    }

    // https://www.geeksforgeeks.org/longest-prefix-also-suffix/
    // https://www.geeksforgeeks.org/find-number-distinct-
    // palindromic-sub-strings-given-string/
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    // aug leetcode valid palindrome
    // https://leetcode.com/problems/distinct-subsequences-ii/
    
    // https://www.geeksforgeeks.org/find-excel-column-name-given-number/
    
    
    // https://leetcode.com/problems/partition-labels/

    class partition{
        int index; int group;
        partition(int i, int g){
        this.index = i;
        this.group = g;
        }
    }


    // 9 Aug 
    //IMCOMPLETE
    int[] partitionLabels(String s){
        HashMap<Character, partition> map = new HashMap<>();
        int[] ans;
        int group = 1;
        map.put(s.charAt(0), new partition(0, group++));
        for(int i =1; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                shrinkMap(map, s, i, group);
            }
            else map.put(s.charAt(i), new partition(i, group++));
        }

        ans= new int[group];
        //iterate over map and count
        // System.out.println(map);
        hashiterator1(map);

        return ans;
    }

    void shrinkMap(HashMap<Character, partition> map, String s,
    int index, int group){
        int i = index-1; int curr = map.get(s.charAt(index)).group;
        while(s.charAt(i)!= s.charAt(index)){
            map.put(s.charAt(i), 
            new partition(map.get(s.charAt(i)).index, curr));
            i--;
        }
    }

    void hashiterator1(HashMap<Character, partition> map) {
        for (Map.Entry<Character, partition> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue().group;
            System.out.println("key "+key+" value " +value);
        }
    }

    // https://www.techiedelight.com/inplace-remove-all-occurrences-ab-c-string/
    // https://leetcode.com/problems/compare-version-numbers/
    // "0.1.2", "0.01.2"
    /**  
     * mapping between integer and char
     * 
     * 1 create an int for every substring b/w dots and compare
     * 2 two while loops 
     * 3 p1, p2 are incremented once when dot is encountered, p1++ takes it till '.'
     * 4 char value of '0' is 0
     * 5 for chars use single quotes and double for strings
     * 
     * can be done using Integer.parseInt too
     * one base case for str = "" when no dot is present
     * then add a check
     */

     public int compareVersion(String version1, String version2) {
        int p1=0,p2=0;
        while(p1<version1.length() || p2<version2.length()){
            int num1=0,num2=0;

            String str1 = ""; String str2 =""; 
            while(p1<version1.length() && version1.charAt(p1)!='.'){
                // System.out.println("char val of 0 "+'0');
                // System.out.print("char val of '1'-'0' ");
                // System.out.println('1'-'0');
                // num1 = num1*10 + (version1.charAt(p1++) - '0'); 
                str1+=version1.charAt(p1++);
                System.out.println("str1 "+str1);
            }
            while(p2<version2.length() && version2.charAt(p2)!='.') {
                // num2 = num2*10 + (version2.charAt(p2++) - '0');
                str2+=version2.charAt(p2++);
                System.out.println("str2 "+str2);
            }

            num1 = Integer.parseInt(str1); 
            num2 = Integer.parseInt(str2);
            if(num1 != num2) return num1>num2 ? 1:-1;
            p1++;
            p2++;
        }
        return 0;
    }
    // https://leetcode.com/problems/reformat-date/
    // https://leetcode.com/problems/integer-to-english-words/

    // https://leetcode.com/problems/next-closest-time/
    // https://www.codertrain.co/next-closest-time

    // IMP FILESYSTEM
    // https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
    public static void main(String[] args) {
        StringPractice string = new StringPractice();
        // System.out.println(string.reverse("word of"));
        // System.out.println(string.reverseWordOrderInAString("I love Java
        // Programming"));

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

        char[][] grid = { { 'A', 'C', 'P', 'R', 'C' }, { 'X', 'S', 'O', 'P', 'C' }, 
                { 'V', 'O', 'V', 'N', 'I' },
                { 'W', 'G', 'F', 'M', 'N' }, // 'F','O','R','G','E','E','K','S'},
                // {'G','E','E','K','S','Q','U','I','Z','G','E','E','K'},
                { 'Q', 'A', 'T', 'I', 'T' } };// ,'P','R','A','C','T','I','C','E'}};

        String word = "MICROSOFT";
        // string.findWordInGrid(grid, word);

        // System.out.println(string.isAnagram("str1", "str1"));

        String s = "hello";
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
                "ADOBECODEBANC";
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

        String balanceLR ="RLLLLRRRLR";
        string.balancedStringSplit(balanceLR); 

    }
}