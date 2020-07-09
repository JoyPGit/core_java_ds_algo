import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

class stringPractice {

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

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

    String reverseWordOrderInAString(String word) {
        String[] words = word.split(" ");
        String output = "";
        for (int i = words.length - 1; i >= 0; i--) {
            output += words[i] + " "; // 3 reverse ordering by traversing from end to start
        }
        return output;
    }

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

    // void findWordInGridUtil(char[][] grid, char[] ch, int index, int rowIndex, int colIndex) {
    //     if (rowIndex >= grid.length && colIndex >= grid[0].length || rowIndex < 0 && colIndex < 0)
    //         return;
    //     int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    //     int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };
    //     if (index == ch.length - 1) {
    //         System.out.println("found");
    //         return;
    //     }
    //     for (int k = 0; k < row.length; k++) {
    //         if ((rowIndex + row[k]) < grid.length && (colIndex + col[k]) < grid[0].length && (rowIndex + row[k]) > -1
    //                 && (colIndex + col[k]) > -1 && (index + 1) < ch.length) {
    //             if (ch[index + 1] == grid[rowIndex + row[k]][colIndex + col[k]]) {
    //                 // System.out.print(ch[index+1]+", ");
    //                 findWordInGridUtil(grid, ch, index + 1, rowIndex + row[k], colIndex + col[k]);
    //             }
    //         }
    //     }
    // }
    
    void findWordInGridUtil(char[][] grid, char[] ch, int index, int rowIndex, int colIndex) {
        if (rowIndex < grid.length && colIndex < grid[0].length && rowIndex >= 0 && colIndex >= 0
        && ch[index] ==grid[rowIndex][colIndex]) {
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


    boolean isAnagram(String str1, String str2) {
        HashMap<Character, Integer> list = new HashMap<>();

        for(char c: str1.toCharArray()){
            if(list.containsKey(c)){
                list.put(c, list.get(c)+1);
            } else list.put(c,1);
        }

        System.out.println(list);

        for(char c:str2.toCharArray()){
            if(list.containsKey(c)){
                list.put(c, list.get(c)-1);
                // System.out.println(list.get(c));// returns the value
                if(list.get(c) == 0) list.remove(c);
            }
            else return false;
        }

        // Iterator it = list.entrySet().iterator();
        // while(it.hasNext()){
        //     HashMap.Entry pair = (HashMap.Entry)it.next();
        //     System.out.println(pair.getValue());
        // }
        System.out.println("final "+list);
        return list.isEmpty();
    }


    String lexicographicSubConcat(String s) {
        int n = s.length();

        // Creating an array to store substrings
        int sub_count = n * (n + 1) / 2;
        String[] arr = new String[sub_count];

        // finding all substrings of string
        int index = 0;
        for (int i = 0; i < n; i++)
            for (int len = 1; len <= n - i; len++) {
                System.out.println(s.substring(i, i + len));
                arr[index++] = s.substring(i, i + len);
            }
        // Sort all substrings in lexicographic order
        Arrays.sort(arr);

        // Concatenating all substrings
        String res = "";
        for (int i = 0; i < sub_count; i++)
            res += arr[i];

        return res;
    }

    public String removeKdigits(String num, int k) {
        String str = num;
        char[] ch = str.toCharArray();
        
        while(k!=0){
            int maxIndex = ch[0]>ch[1]?0:1;
            move(ch, maxIndex, ch.length-1);
            k--;
        }

        String ans = String.valueOf(ch);
        String ans1 = ans.substring(0,ans.indexOf(" "));
        // System.out.println(ans1);
        // System.out.println(String.valueOf(ch));

        System.out.println(ans.indexOf(" "));
        return ans1;
        // return String.valueOf(ch);
    }

    void move(char[] ch, int start, int end){
        for(int i = start; i<end; i++){
            ch[i] = ch[i+1];
        }
        ch[end] = ' ';
    }

    // ababcbacadefegdehijhklij
    //https://leetcode.com/problems/partition-labels/
    // public ArrayList<Integer> partitionLabels(String S) {
    public int partitionLabels(String S) {
        // ArrayList<Integer> result = new ArrayList<Integer>();
        // ArrayList<Character> char1 = new ArrayList<Character>();
        int[] alphabet = new int[26]; int[] count = new int[S.length()];
        count[0] = 1;
        alphabet[S.charAt(0)-'a']= 0;

        for(int i =1; i<S.length(); i++){
            System.out.println("in here i "+i);
            System.out.println("alp "+(S.charAt(i)-'a')+ " "+ (S.charAt(i)));
            if(alphabet[S.charAt(i)-'a']!=0){
                fill(count, count[S.charAt(i)-'a'], alphabet[S.charAt(i)-'a'], i);
                //tricky to link count with alphabet
                alphabet[S.charAt(i)-'a'] = i;
                print1DMatrix(count);
                System.out.println();
                print1DMatrix(alphabet);
            }else {
                System.out.println("in here");
                alphabet[S.charAt(i)-'a'] = i;
                count[i] = count[i-1]+1;
                System.out.println();
                print1DMatrix(count);
                System.out.println();
                print1DMatrix(alphabet);
            }
        }

        int max  = 0;
        System.out.println();
        for(int i =0; i<count.length; i++){
            System.out.print(count[i]+", ");
            max = Math.max(max, count[i]);
        }
        System.out.println();

        for(int i =0; i<alphabet.length; i++){
            System.out.print(alphabet[i]+", ");
        }
        
        return max;
    }

    void fill(int[] arr, int value, int start, int end){
        System.out.println("value "+value);
        for(int i =start; i<=end; i++){
            arr[i] = value;
        }
    }

    void keyPadPrint(String str){
        if(str == "1"){}
        if(str == "2"){}
        if(str == "3"){}
        if(str == "4"){}
        if(str == "5"){}
        if(str == "6"){}
        if(str == "7"){}
        if(str == "8"){}
        if(str == "9"){}
        // keyPadPrintUtil(str);
    }

    // https://www.youtube.com/watch?v=qBbZ3tS0McI
    // void generateParentheses(int n){
    //     int open = n/2;
    //     char[] arr = new char[n];

    //     generateParenthesesHelper(arr, 0, open);
    // }

    // void generateParenthesesHelper(char[] arr, int index, int count){
    //     if(index == arr.length){
    //         System.out.println(arr.toString());
    //         return;
    //     }
    //     if(count == 0){
    //         arr[index] = ')'; 
    //         generateParenthesesHelper(arr, index+1, count );
    //     }else {
    //         arr[index] = '(';
    //         generateParenthesesHelper(arr, index+1, count-1);
    //         // arr[index] = 
    //     }

    // }
    public static void main(String[] args) {
        stringPractice string = new stringPractice();
        // System.out.println(string.reverse("word of"));
        // System.out.println(string.reverseWordOrderInAString("I love Java Programming"));

        String x ="123";
        // System.out.println(Integer.parseInt(x)+1);//4
        String str = "GeeksForGeeks12";
        // char[] in = str.toCharArray();
        // System.out.println(stringPractice.to_upper(in));

        // System.out.println((char)('a'+2));
        String str1 = "1??";
        // string.generateBinPattern(str1);

        // string.genBinPerm01s(4);
        // string.recPalindrome("abba");

        char[][] grid = {{'A','C','P','R','C'},
        {'X','S','O','P','C'},
        {'V','O','V','N','I'},
        {'W','G','F','M','N'},//'F','O','R','G','E','E','K','S'}, 
        // {'G','E','E','K','S','Q','U','I','Z','G','E','E','K'}, 
        {'Q','A','T','I','T'}};//,'P','R','A','C','T','I','C','E'}}; 

        String word = "MICROSOFT";
        // string.findWordInGrid(grid, word);

        // System.out.println(string.isAnagram("str1", "str1"));

        String s = "abc";  
        // System.out.println(string.lexicographicSubConcat(s)); 
        // string.generateParentheses(3);

        String num = "1432219"; int k = 3;
        // string.removeKdigits(num, k);

        string.partitionLabels("ababcbacadefegdehijhklij");
        
    }
}