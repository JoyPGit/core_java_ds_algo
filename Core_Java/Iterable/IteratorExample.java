import java.util.*;

public class IteratorExample{
    public static void main(String[] args) {

        // Make a collection
        List<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
    
        // Get the iterator
        Iterator<String> it = cars.iterator();
    
        // Print the first item
        System.out.println(it.next());

        while(it.hasNext()){
            System.out.println(it.next());
        }

        for(String str : cars) System.out.println(str);
      }
}