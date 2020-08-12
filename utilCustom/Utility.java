package utilCustom;
import java.util.*;

public class Utility {
    // Map iterator
    void hashiterator(HashMap<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key " + key + " value " + value);
        }
    }

    // MAP.PUT(C, MAP.GETORDEFAULT(C,0)+1);
    // for(char c: tasks) map.put(c, map.getOrDefault(c, 0)+1);

    // coverting min heap to max heap
    void maxheap() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        pq.add(10);
        pq.add(5);
        pq.add(20);
        // pq.remove()
        System.out.println(pq.peek());
    }

    void sortString(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        // String sorted = chars.toString();
        // doesn't work
        // Returns a string representation of the object
        System.out.println(sorted);
    }

    void intTodouble(int n) {
        /*
         * valueOf() method of Double class converts the passed value to the double
         * value.
         */
        Double dnum = Double.valueOf(n);
    }

    void doubleToint(double n) {
        int value = (int) n;
    }

    /**
     * Java char to int using Integer.parseInt() method Here we are using
     * Integer.parseInt(String) method to convert the given char to int. Since this
     * method accepts string argument we are converting char to String using
     * String.valueOf() method and then passing the converted value to the method.
     */

    void charToint() {
        char ch = '9';

        /*
         * Since parseInt() method of Integer class accepts String argument only, we
         * must need to convert the char to String first using the String.valueOf()
         * method and then we pass the String to the parseInt() method to convert the
         * char to int
         */
        int num = Integer.parseInt(String.valueOf(ch));
        System.out.println(num);
    }

    void stringToint(String s) {
        char[] ch = s.toCharArray();
        int num = Integer.parseInt(new String(ch));
        System.out.println(num);
    }

    /**
     * for k smallest or largest, when we use heap techniques : 1 sort the array and
     * return kth index nums[n-k]
     * 
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/
     * 367136/Very-Simple-PriorityQueue 2 use a heap of size k and poll when size
     * exceeds k
     * 
     * 3 use a hashmap and add arraylist as values
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/
     * 695451/java-solution-using-PriorityQueue-(Simple-solution-)
     * 
     * HashMap<Integer,ArrayList<int[]>> map= new HashMap(); if(map.containsKey(d)){
     * ArrayList<int[]> l = map.get(d); l.add(arr); map.put(d,l); } else{
     * ArrayList<int[]> l = new ArrayList(); l.add(arr); map.put(d,l); }
     */

    // USING XOR
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/

    // for hashmap use getOrDefault

    // add hashmap sort
    // map.putIfAbsent

    /**
     * use a char map instead if chars are there 
     * int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c); 
    */

    public static void main(String[] args) {
        Utility utility = new Utility();
        // utility.sortString("original");

        // utility.charToint();

        utility.maxheap();
    }

    // public void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     int T = sc.nextInt();
    //     while (T > 0) {
    //         int n1 = sc.nextInt();
    //         Add_LinkedList list1 = new Add_LinkedList();
    //         for (int i = 1; i <= n1; i++) {
    //             int a = sc.nextInt();
    //             list1.push(a);
    //         }
    //         int n2 = sc.nextInt();
    //         Add_LinkedList list2 = new Add_LinkedList();
    //         for (int i = 0; i < n2; i++) {
    //             int b = sc.nextInt();
    //             list2.push(b);
    //         }
    //         Add_LinkedList list3 = new Add_LinkedList();
    //         // Add g = new Add();
    //         // Node rs = g.addTwoLists(list1.head, list2.head);
    //         // list3.printList(rs);
    //         System.out.println();
    //         T--;
    //     }
    // }

}