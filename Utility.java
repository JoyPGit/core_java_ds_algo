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


    
}