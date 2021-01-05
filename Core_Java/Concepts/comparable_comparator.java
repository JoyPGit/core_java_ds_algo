
import java.util.*;


public class comparable_comparator {
    // Comparable vs Comparator
    /** 
     * Comparable is an interface and to compare objects of a class a compareTo
     * method needs to be implemented
     * 
     * But if the class is beyond access, comparator is used.
     * Comparator can be used to sort on a diff parameter.
    */
    // https://www.youtube.com/watch?v=oAp4GYprVHM

    public static void main(String[] args) {
        Laptop l1 = new Laptop("Dell", 8, 1200);
        Laptop l2 = new Laptop("HP", 4, 800);
        Laptop l3 = new Laptop("Lenovo", 16, 2400);

        List<Laptop> list = new ArrayList<>();
        list.add(l1); list.add(l2); list.add(l3);

        // using comparator interface
        Collections.sort(list,new Comparator() {
 
            @Override
            public int compare(Laptop o1, Laptop o2) {
                // TODO Auto-generated method stub
                if(o1.brand.compareTo(o2.brand)==0){
                    return o1.price - o2.price;
                }
                else {
                    return o1.brand.compareTo(o2.brand);
                }
            }

            @Override
            public int compare(Object o1, Object o2) {
                // TODO Auto-generated method stub
                if(o1.brand.compareTo(o2.brand)==0){
                    return o1.price - o2.price;
                }
                else {
                    return o1.brand.compareTo(o2.brand);
                }
            }

        });

        Collections.sort(list,(x, y)-> (x.brand).compareTo(y.brand));
        System.out.println();
        System.out.println("sorting with comparator");
        for(Laptop l : list) System.out.println("brand "+l.brand+ " ram "+l.ram+", ");


        // using comparable's compareTo
        Collections.sort(list);
        for(Laptop l : list) System.out.print("brand "+l.brand+ " ram "+l.ram+", ");
        
    }

}

// imp Comparable takes an item type
// or else The method compareTo(Laptop) of type Laptop must override or implement a supertype method

class Laptop implements Comparable<Laptop>{
    int ram;
    int price;
    String brand;

    Laptop(String s, int r, int p){
        this.brand = s;
        this.ram = r;
        this.price = p;
    }

    @Override
    public int compareTo(Laptop l) {
        // TODO Auto-generated method stub
        if(this.ram > l.ram) return 1;
        else if(this.ram<l.ram)return -1;
        else return 0;
    }

    
    
}
