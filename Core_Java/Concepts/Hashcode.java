//package Core_Java;

import java.util.*;
/** 
 * https://www.youtube.com/watch?v=ghswNpRv2t0
 * w/o implementing equals, .equals or == returns false for same vals as it checks address.
 *  
 * The Object class defines both the equals() and hashCode() methods – which means that these two
 * methods are implicitly defined in every Java class.

 equals() Contract
    Java SE defines a contract that our implementation of the equals() method must fulfill. Most of the criteria are common sense. The equals() method must be:

    reflexive: an object must equal itself
    symmetric: x.equals(y) must return the same result as y.equals(x)
    transitive: if x.equals(y) and y.equals(z) then also x.equals(z)
    consistent: the value of equals() should change only if a property that is contained in equals() changes (no randomness allowed)

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Voucher))
            return false;
        Voucher other = (Voucher) o;
        boolean valueEquals = (this.value == null && other.value == null)
          || (this.value != null && this.value.equals(other.value));
        boolean storeEquals = (this.store == null && other.store == null)
          || (this.store != null && this.store.equals(other.store));
        return valueEquals && storeEquals;
    }

    1 o == this, check address
    2 check instance
    3 cast and then check all the class variables
 * 
 * objects that are equal to each other must return the same hashCode, so 
 * if we override equals(), we must also override hashCode().
 * 
 * hashcode helps in identifying objects in heap where other objs also reside
 * entry into hasmap of the object checks hashcode and if hashcode values are same, 
 * then equals is called.
 * 
 * == compares addresses,

 * One common way is to let our IDE generate the equals() and hashCode() methods.
 * 
*/

public class Hashcode{
    public static void main(String[] args) {
        Employee e1 = new Employee(1);
        Employee e2 = new Employee(1);
        Employee e3 = e1;
        HashMap<Employee, String> map = new HashMap<>();
        System.out.println(e1 == e3); // true same obj
        map.put(e1, "one"); // makes a call to hashCode method of Employee
        map.put(e2, "one");

        System.out.print("Are e1 and e2 same? ");
        System.out.println(e1 == e2); // false, diff mem locations
        System.out.println("hashcode :");
        System.out.println("Aren't they really the same? : "+e1.equals(e2));
        System.out.println();
        System.out.println(e1.hashCode());
        System.out.println(e2.hashCode());
        System.out.println("map size "+map.size());


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
        // it works correctly here as Integer class implements equals and hashcode
        System.out.println(i3 == i2);
        System.out.println("hashcode :");
        System.out.println(i2.hashCode());
        System.out.println(i3.hashCode());
        System.out.println("Integer map size : "+mapInteger.size());

        /** 
         * if hashcode is same, then equals method is used
         * equals and hashcode if present, help in identifying if objects with same params
         * are duplicate or not
         * 
         * https://www.youtube.com/watch?v=7V3589CReug
         * 
         * 
         * if a class doesn't implement equals and hashcode same instances won't be marked duplicate
         * equals tests for identity, so unequal
         * to test for equality use Employee1.equals(Emp2); like in string
         * equal instances always result in  the same hashcode
         * equal hashcodes don't mean equal instances
         * 
         * All three criteria in the contract of hashCode() mention in some ways the equals() method:
         * 
         * internal consistency: the value of hashCode() may only change if a property 
         * that is in equals() changes
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
    //    if (object == null || getClass() != object.getClass()) return false;
    //    if (!super.equals(object)) return false;
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