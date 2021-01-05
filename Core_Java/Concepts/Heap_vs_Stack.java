
// https://www.youtube.com/watch?v=UcPuWY0wn3w

public class Heap_vs_Stack{

    static void print(){
        System.out.println("in stack");
    }

    void printHeap(){
        System.out.println("in heap");
    }

    public static void main(String[] args) {
        print(); // in stack
        System.out.println("in main java prac");
        Heap_vs_Stack p = new Heap_vs_Stack();
        p.printHeap();
    }
}
/** 
 * normally classes are stored in stack. static methods and calls inside mai
 * 
 * main is put in a frame in stack, frame is a memory area
 * any local variable in main is stored inside main's frame in stack
 * 
 * static methods inside main have their own frames
 * 
 * Car myCar = new Car();
 * myCar resides in main and holds address of the instance of Car.
 * this address points to heap where the instance of the Car is stored
 * 
 * once the main is over, its frame is popped from stack and 
 * GARBAGE COLLECTOR CHECKS IF THE OBJECTS HAVE REF VARS ATTACHED.
 * 
 * For Strings, String Pool is used as String is immutable.
 * https://www.youtube.com/watch?v=to9DPVsdByE
 * 
 * String refVar stores address of String, 
 * also using new String("ab") doesn't use Heap, but String Pool
 * FLyWeight Pattern, same object with diff refVars can be reused
 * 
*/