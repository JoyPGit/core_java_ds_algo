package utilCustom;

import java.util.*;

public class Utility {
    // HASHMAP
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

    // HASHSET
    // https://stackoverflow.com/questions/38578995/how-to-cast-object-to-int-java
    HashSet<Integer> set = new HashSet<>();
    int[] resCourse = Arrays.stream(set.toArray()).mapToInt(o -> (int)o).toArray();

    // coverting min heap to max heap
    void maxheap() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        pq.add(10);
        pq.add(5);
        pq.add(20);
        // pq.remove()
        System.out.println(pq.peek());
    }

    /**
     * Stringbuilder 
     * StringBuilder sb = new StringBuilder(); 
     * while(!s.isEmpty()) sb.append(s.removeFirst()); 
     * while(sb.length()>1 && sb.charAt(0)=='0') sb.deleteCharAt(0); 
     * return sb.length()!=0?sb.toString():"0";
     * 
     * NO NEED TO USE SUBSTRING, S.deleteCharAt
     */
    void sortString(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        // String sorted = chars.toString();
        // doesn't work
        // Returns a string representation of the object
        System.out.println(sorted);
    }

    // check for integer if(a!=(int)a) return res;

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
     * ARRAY
     * 
     * no need to create a new array int[] x = {start,end}; return new int[]{start,
     * end};
     *
     */
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

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i] + ";");
                System.out.println();
            } else
                System.out.print(arr[i] + ", ");
        }
    }

    public static void printMatrix(int[][] arr) {
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

    void printMatrixBool(boolean[][] arr) {
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

    /**
     * for k smallest or largest, when we use heap techniques : 1 sort the array and
     * return kth index nums[n-k]
     * 
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/
     * 367136/Very-Simple-PriorityQueue
     * 
     * 1 use a custom class to hold index and dist 2 add class to pQueue 3 iterate
     * for k times and add elemnst to res array
     * 
     * for(int i =0; i<K; i++){ Holder curr = heap.remove(); res[i][0] =
     * points[curr.index][0]; res[i][1] = points[curr.index][1]; }
     * 
     * index holds the index of the closest el so add values by mapping directly
     * mapping to index of original array
     */

    // USING XOR
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/

    // for hashmap use getOrDefault
    // https://stackoverflow.com/questions/26230225/hashmap-getting-first-key-value
    // add hashmap sort
    // map.putIfAbsent

    /**
     * use a char map instead if chars are there int[] c = new int[26]; for(char t :
     * tasks){ c[t - 'A']++; } Arrays.sort(c);
     */

    /**
     * ARRAYLIST
     * 
     * List<Integer> res = (List<Integer>) new ArrayList<Integer>(); add a cast
     * (List<Integer>), it works
     * 
     * ARRAYLIST SORT for(int i = 0; i<n; i++){ list.add(new People(people[i][0],
     * people[i][1])); } Collections.sort(list, (x, y)-> y.h-x.h);
     *
     * https://leetcode.com/problems/merge-intervals/
     * 
     * ArrayList<int[]> result = new ArrayList<>(); int[] prevInterval =
     * intervals[0]; result.add(prevInterval); printListArray(result); return
     * result.toArray(new int[result.size()][]);
     * 
     * 
     * void printListArray(ArrayList<int[]> list){ for(int[]i : list)
     * System.out.print("["+i[0]+","+i[1]+"], "); }
     * 
     * 
     * 2D ARRAY TO MULTILEVEL ARRAYLIST int[][] nums = {{4,10,15,24,26},
     * {0,9,12,20}, {5,18,22,30}}; ArrayList<ArrayList<Integer>> numsKList = new
     * ArrayList<ArrayList<Integer>>(); for(int i =0; i<nums.length; i++){
     * ArrayList<Integer> newRow = new ArrayList<>(); for(int j : nums[i]){
     * newRow.add(j); } numsKList.add(newRow); } System.out.println(numsKList);
     */

    // HEAP
    // https://stackoverflow.com/questions/5695017/priorityqueue-not-sorting-on-add
    // heap.addAll(map.values());

    // https://leetcode.com/problems/merge-k-sorted-lists/

    /**
     * PQUEUE WITH 2 COMPARATORS
     * 
     * PriorityQueue<KFreq> heap = new PriorityQueue<KFreq>((x, y) -> { if (x.freq
     * == y.freq) return x.str.compareTo(y.str); // y.str.charAt(0) -
     * x.str.charAt(0); return y.freq - x.freq; });
     * 
     * IF 2 STRS HAVE SAME FREQ, SMALLER IS INSERTED FIRST A BEFORE AA
     */

    public static void main(String[] args) {
        Utility utility = new Utility();
        // utility.sortString("original");

        // utility.charToint();

        utility.maxheap();
    }

    // public void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // int T = sc.nextInt();
    // while (T > 0) {
    // int n1 = sc.nextInt();
    // Add_LinkedList list1 = new Add_LinkedList();
    // for (int i = 1; i <= n1; i++) {
    // int a = sc.nextInt();
    // list1.push(a);
    // }
    // int n2 = sc.nextInt();
    // Add_LinkedList list2 = new Add_LinkedList();
    // for (int i = 0; i < n2; i++) {
    // int b = sc.nextInt();
    // list2.push(b);
    // }
    // Add_LinkedList list3 = new Add_LinkedList();
    // // Add g = new Add();
    // // Node rs = g.addTwoLists(list1.head, list2.head);
    // // list3.printList(rs);
    // System.out.println();
    // T--;
    // }
    // }

}