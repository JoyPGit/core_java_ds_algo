//package Core_Java;

import java.util.*;

public class Hashcode{
    public static void main(String[] args) {
        Employee e1 = new Employee(1);
        Employee e2 = new Employee(1);
        Employee  e3 = e1;
        HashMap<Employee, String> map = new HashMap<>();
        System.out.println(e1 == e3);
        map.put(e1, "one");
        map.put(e2, "one");

        System.out.println(e1 == e2);
        System.out.println("hashcode :");
        System.out.println("is it :"+e1.equals(e2));
        System.out.println();
        System.out.println(e1.hashCode());
        System.out.println(e2.hashCode());
        System.out.println(map.size());


        // // ques 3, equals and hashcode concept
        // String one = "Random";
        // String two = "RAndom";

        // System.out.println(one == two);
        // System.out.println(one.equals(two));

        Integer i2 = new Integer(2);
        Integer i3 = new Integer(2);
        HashMap<Integer, String> mapInteger = new HashMap<>();
        mapInteger.put(i2, "two");
        mapInteger.put(i3, "two");
        System.out.println(i3 == i2);
        System.out.println("hashcode :");
        System.out.println(i2.hashCode());
        System.out.println(i3.hashCode());
        System.out.println("Integer map size : "+mapInteger.size());

        /** 
         * if hashcode is same, then equals method is used
         * equals and hashcode if present help in identofying if objects with same params
         * are duplicate or not
         * 
         * https://www.youtube.com/watch?v=7V3589CReug
         
         * 
         * if class doesn't imeplement equals and hashcode same instances won't be marked diplca ite
         * equals ctest for identity, so unequal
         * to test for equality use Employee1.equals(Emp2); lik ein string
         * equal instances always result in  the same hashcode
         * equal hashcodes don't mean equal instances
         * 
         * All three criteria in the contract of hashCode() mention in some ways the equals() method:
         * 
         * internal consistency: the value of hashCode() may only change if a property that is in equals() changes
         * equals consistency: objects that are equal to each other must return the same hashCode
         * collisions: unequal objects may have the same hashCode
         * 
        */
    }
}

class Employee{
    int id;

    Employee(int i){
        this.id = i;
    }
    public int getId() {
        return id;
    }

   public boolean equals(Object object) {
       System.out.println("in equals");
       if (this == object) return true;
       if (object == null || getClass() != object.getClass()) return false;
       if (!super.equals(object)) return false;
       Employee employee = (Employee) object;
       return id == employee.id;
   }

   public int hashCode() {
       System.out.println("in hashcode");
       return Objects.hash(super.hashCode(), id);
   }

    public void setId(int id) {
        this.id = id;
    }
}