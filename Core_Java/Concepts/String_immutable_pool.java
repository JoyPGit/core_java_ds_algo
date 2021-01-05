import java.util.*;

/*
 * For Strings, String Pool is used as String is immutable.
 * https://www.youtube.com/watch?v=to9DPVsdByE
 * 
 * String refVar stores address of String, 
 * also using new String("ab") doesn't use Heap, but String Pool
 * FLyWeight Pattern, same object with diff refVars can be reused
 */

/** 
 * String Pool is a storage area in Java heap.
 * 
 * String allocation, like all object allocation, proves to be a costly affair both in terms 
 * of time and memory. The JVM performs some steps while initializing string literals to 
 * increase performance and decrease memory overhead. To decrease the number of String objects 
 * created in the JVM, the String class keeps a pool of strings.
*/

/** 
 * how many strings are getting created in the below statement;
 *  String str = new String("Cat");
 * 
 * In the above statement, either 1 or 2 string will be created. 
 * If there is already a string literal “Cat” in the pool, then only one string “str” 
 * will be created in the pool. 
 * If there is no string literal “Cat” in the pool, then it will be first created in the pool 
 * and then in the heap space, so a total of 2 string objects will be created.
 */

/** 
 * https://www.baeldung.com/java-string-pool
 * 
 * Thanks to the immutability of Strings in Java, the JVM can optimize the amount of 
 * memory allocated for them by storing only one copy of each literal String in the pool. 
 * This process is called interning.
 * 
 * When we create a String variable and assign a value to it, the JVM searches the pool for a
 * String of equal value.
 * 
 * If found, the Java compiler will simply return a reference to its memory address, 
 * without allocating additional memory.
 * 
 * If not found, it'll be added to the pool (interned) and its reference will be returned.
 * 
 * 
 * When we create a String via the new operator, the Java compiler will create a 
 * new object and store it in the heap space reserved for the JVM.
 * 
 * When we create a String object using the new() operator, it always creates a new object in heap memory. 
 * On the other hand, if we create an object using String literal syntax e.g. “Baeldung”, 
 * it may return an existing object from the String pool, if it already exists.
 * 
 * https://medium.com/javarevisited/what-does-string-pool-mean-in-java-414c725fbd59#
 * Using the ‘new’ keyword :
 * String name2 = new String(“newJava”);
 * The above statement creates a string object in heap memory and checks whether it is present 
 * in the string pool or not. If the “newJava” is not present in the string pool then 
 * it will place this string in the string pool else it will skip it.
 * In this case, two objects are created. One in heap memory and the other in the String pool.
 * 
 * 

*/
class String_immutable_pool{
    // static String s1 = "ABC";
    // static String s2 = new String("ABC");
    // static String s3 = "ABC";
    // static String s4 = new String("ABC");
    
    public static void main(String[] args) {

        String s1 = "ABC";
        String s2 = new String("ABC");
        String s3 = "ABC";
        String s4 = new String("ABC");
        s4.intern();
        String s5 = new String("ABC").intern();
        /** 
         * if string is declared using new, it refers to heap
         * if created using literal, stored in pool
         * 
         * so s1 != s2 as diff memory locations
         * s1 = s3
         * 
         * after interning, a copy of String in heap is created in String pool.
         * then also s1 != s4 as interning is done afterwards.
         * 
         * if interning is done at the timne of creation, then s1 == s5
        */
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s1 == s5);
        System.out.println(s2 == s4);

        String str1 = "test";
        String str2 = new String("test");  // "new String" guarantees a different object

        System.out.println("str");
        System.out.println(str1 == str2);  // should print "false"
        
        // You can add a string to the pool by calling String.intern()
        str2 = str2.intern();
        System.out.println(str1 == str2);  // should print "true"

        String str3 = "three";
        String str4 = str3;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(str1, 1);
        map.put(str2, 2);
        map.put(str3, 3);
        str3.toUpperCase();
        System.out.println(str4);
        System.out.println(map.get(str3));


        String string1 = "Java";
        String string2 = string1.intern();

        System.out.println(string1 == string2);

        /** 
         * In the above example at line number 6 “Java” was not present in the string pool and 
         * hence string object is created in the string pool and the reference of this object is 
         * returned to string1 variable. 
         * 
         * When string1.intern() is called it looked into the string pool and there was already a 
         * string present and hence the reference of that string object is returned to string2. 
         * Hence the output of the program is true.
        */
    }
}
