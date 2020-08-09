import java.util.*;

public class Utility {
    // Map iterator
    void hashiterator(HashMap<Integer, Integer> map){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key "+key+" value " +value);
        }
    }

    // coverting min heap to max heap
    void maxheap(){
        PriorityQueue<Integer> pq =new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        pq.add(10);
        pq.add(5);
        // pq.remove()
        System.out.println(pq.peek());
    }

    void sortString(String original){
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        // String sorted = chars.toString();
        // doesn't work
        // Returns a string representation of the object
        System.out.println(sorted);
    }
    
    void intTodouble(int n){
        /* valueOf() method of Double class converts the passed
        * value to the double value.
        */
	    Double dnum = Double.valueOf(n);
    }

    void doubleToint(double n){
        int value = (int) n;   
    }


    /** for k smallest or largest, when we use heap
     * techniques :
     * 1 sort the array and return kth index nums[n-k]
     * 
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/
     * 367136/Very-Simple-PriorityQueue
     * 2 use a heap of size k and poll when size exceeds k
     * 
     * 3 use a hashmap and add arraylist as values
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/
     * 695451/java-solution-using-PriorityQueue-(Simple-solution-) 
     * 
     * HashMap<Integer,ArrayList<int[]>> map= new HashMap();
     * if(map.containsKey(d)){
            ArrayList<int[]> l = map.get(d);
            l.add(arr);
            map.put(d,l);
        }
        else{
            ArrayList<int[]> l = new ArrayList();
            l.add(arr);
            map.put(d,l);
        }
     */

    // USING XOR
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/
    
    public static void main(String[] args) {
        Utility utility = new Utility();
        utility.sortString("original");
    }

    
}