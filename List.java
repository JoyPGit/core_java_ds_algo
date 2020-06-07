import java.util.*;

public class List {
    
    
    // void printAll(){
    //     for
    // }
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Integer last = list.getLast();
        System.out.println(last);
        
    }
}

