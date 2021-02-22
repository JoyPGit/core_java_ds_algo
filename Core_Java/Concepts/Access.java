import java.util.*;

public class Access extends FunkyClass{
    static{
        System.out.println("------------>");
        System.out.println("static block runs without instantiation also");
        System.out.println("in static block");
        statFunc(5);
    }
    static void statFunc(int n){
        System.out.println("in static method");
    }

    static int statFunc(){
        System.out.println("overloaded");
        return 1;
    }

    @java.lang.Override
    void methFunk2() {
        System.out.println("abstarct method overridden");
    }

    Access(){
        System.out.println("in constructor");
        methFunk2();
    }

    final void statFuncFinal(){
        System.out.println("in final");
    }

    public int add(int a, int b){
        return a+b;
    }

    public float add(float a, float b){
        return a+b;
    }

    public int add(int a, int b, int c){
        return a+b+c;
    }

    public static void main(String[] args) {
        Access child = new Derived();
//        child.statFunc();

        Access a = new Access();
        System.out.println(a.add(1,2));
        System.out.println(a.add(1,2,3));
        Funky f = ()->{ System.out.println("in functional interf method");};
        f.methFunk();
    }

    /** 
     * Abstraction, encapsulation
     * polymorphism Validation
     * Method overloading, overriding
     * Final static 
     * Static override
     * Final 
     * 
     * OVERRRIDDEN METHODS CAN'T REDUCE THE VISIBILITY, IF PARENT CLASS HAS PROTECTED, SUBCLASS 
     * CAN'T MAKE IT PRIVATE
     * 
     * static methods can be overloaded, but not overridden
     * static same class, final 
     * how to call static methods Classname.method System.
     *
     * static methods can't be overridden
     * final class can't be extended
     * System - final class
     * public static final PrintStream out = null;
     * public class PrintStream extends FilterOutputStream
     *      implements Appendable, Closeable
     * {}
     *
     * static
     * method
     * variable : static variable can be used by all subclasses, it can be used before
     * instantiation also, it can be changed unlike final var
     * A static final variable that is not initialized during declaration
     * can only be initialized in static block.
     * class : only nested or inner class, the top level class can't be marked static
     *
     * 
     * final
     * method -> no ol, no or; subclasses can call this method of super class
     * variable kind of like a constant
     * class -> no extending
     * there is no 'final' block
     *
     * mostly final is used to prevent overriding and inheritance
     *
     * to make a class immutable : mark it final and make its contructor private
     *
     * Integer is final class, static class can only be nested
     *
     * abtract methods in interface don't need abstract keyword
     *
     * interfaces earlier could only have abstract and public methods
     * but in java 8 they can have static and default methods
     * static in interface
     * 
     * System.out.println()
     * 
     * System is an immutable class(final, privtae consructor);
     * out is reference of PrintStream class -> FilterOutputStream -> OutputStream
    */
}


class Derived extends Access{
    // static methods can't be overridden
//     @Override
//    static void statFunc(){
//        System.out.println("in child, overridden method");
//    }
    Derived(){
//        Integer.parseInt("");
    }
}

interface Funky {
    public void methFunk();
}

abstract class FunkyClass{
    abstract void methFunk2();
}


