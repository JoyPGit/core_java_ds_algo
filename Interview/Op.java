import java.util.*;

public class Op {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(7);
        list.add(21);
        list.add(22);

        List<Integer> list2 = new ArrayList<>();
        // list2.add(2); list2.add(4); list2.add(6); list2.add(9); list2.add(10);

        List<Integer> res = new ArrayList<>();

        int index1 = 0, index2 = 0;

        if (list2.size() == 0) System.out.println("result list " +list);
        else if (list.size() == 0) System.out.println("result list " +list2);
        else{
            while (index1 != list.size() - 1 && index2 != list2.size() - 1) {
                if (list.get(index1) < list2.get(index2))
                    res.add(list.get(index1++));
                else
                    res.add(list2.get(index2++));
            }
    
            if (index1 != list.size() - 1) {
                for (int i = index1; i < list.size(); i++)
                    res.add(list.get(i));
            }
    
            if (index2 != list.size() - 1) {
                for (int i = index2; i < list2.size(); i++)
                    res.add(list2.get(i));
            }
            System.out.println("result list " + res);
            
        }
        
    }
}
