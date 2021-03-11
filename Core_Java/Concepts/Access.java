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

    @Override
    public void methFunk2() {
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
     * https://stackoverflow.com/questions/1743715/behaviour-of-final-static-method
     * 
     * //Snippet 1 - Compiles fine
        public class A {
            static void ts() {
            }
        }

        class B extends A {
            static void ts() {
            }
        }
     *   
     * shadowing
     * Static methods cannot be overridden but they can be hidden. The ts() method of B is 
     * not overriding(not subject to polymorphism) the ts() of A but it will hide it. 
     * If you call ts() in B (NOT A.ts() or B.ts() ... just ts()), the one of B will be called and not A. 
     * Since this is not subjected to polymorphism, the call ts() in A will never be redirected to 
     * the one in B.
     * 
     * The keyword final will disable the method from being hidden. So they cannot be hidden and an 
     * attempt to do so will result in a compiler error.
     * 
     * static means that a field or method belongs to the class, as opposed to individual instances 
     * of the class.
     * final actually means different things when applied to methods versus fields (or local variables):
     * final variables and fields cannot be reassigned. This is fairly similar to C++'s const.
     * final methods cannot be overridden, which only applies to methods on instances. When used in this 
     * sense, final is not similar to C++'s const.
     * Because you cannot override static methods on classes, the combined modifiers static final are 
     * usually redundant, which is why IntelliJ advises you to remove one of the modifiers.
     * 
     * it makes no sense to have a static final method static methods can't be overridden, 
     * but can be hidden. Making them final prevents even hiding. 
     * 
     * https://stackoverflow.com/questions/3406703/whats-the-meaning-of-system-out-println-in-java
     * 
     * //////////////////////////////////////////////////////////////////
     * abtract methods in interface don't need abstract keyword
     *
     * interfaces earlier could only have abstract and public methods
     * but in java 8 they can have static and default methods
     * static in interface
     * 
     * System.out.println()
     * 
     * System is an immutable class(final, private consructor);
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
        super.statFuncFinal();
    }
}

interface Funky {
    public void methFunk();
}

abstract class FunkyClass{
    abstract void methFunk2();
}


