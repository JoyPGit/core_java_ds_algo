import java.io.*;

import javax.imageio.IIOException;

//package Core_Java.Questions;

public class Questions {

    // ques 5
    static int a = 1111;
    static{
        a = a-- - --a;
        System.out.println("static block, this is what runs w/o instantiation");
        System.out.println("a-- - --a => (a--) - (--a)");
        System.out.println("(a--) => 1110; then (--a) acts on 1110 => 1109");
        System.out.println("so finally 1111 - 1109 = 2; is assigned to a");
    }
    {
        a = a++ + ++a;
    }

    public static void main(String[] args) throws Exception {
        // https://www.youtube.com/watch?v=FB6RsHx7S6A

        // ques 1 : the line below prints or not?    
        //\u000d System.out.println("comment prints");
        
        /**
         * Java parses character escape codes in source code, not just strings.
         * This allows you to use Unicode identifiers without a Unicode encoding.
         * Therefore, the \u000d in the comment is parsed as a newline, ending the comment 
         * and beginning an instance initializer.
        */

        // ques 2
        String s = "one"+1+2+"two"+3;
        System.out.println("String concat : "+s);

        int z = 10 + + 11 - - 12 + + 13 - - 14 + + 15;
        // int i2 = 10++ 11 --12;
        System.out.println("+ + operators "+z);


        // ques 3, equals and hashcode concept
        // == checks reference
        // to check equality use .equals
        // hashcode is for using with hashmap
        String one = "Random";
        String two = "RAndom";

        System.out.println(one == two);
        System.out.println(one.equals(two));
        System.out.println(("a").equals("A")); // false

        System.out.println("without instantiation, will it work?");
        System.out.println("yes"+ Fruit.type);
        // non-static variable type cannot be referenced from a static context
        System.out.println("only when static variables are used, it can work without instantiation");
        System.out.println("everything inside an interface is public static and final");


        // ques 4 static in nested class; final -> constant
        final class Constants{
            public final static String name = "Pi";
            
            Thread t = new Thread(new Runnable(){
        
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println(Constants.name);
                }
            });
            // t.start();

        }


        // q5 continuation
        System.out.println("value of a : "+a);


        // interface -> functional, marker(when no method)
         
        // interface can be used as reference to class implementing it, polymorphism
        IFruit if1 = new Fruit();
        // as type is static
        System.out.println("type is : "+if1.type);

        // functional interface, lambda implementation, m1 defined here
        // as lambdas are anonymous, these are defined this way
        Boy b = ()->{System.out.println("m1 of Boy interface implemented");};
        b.m1();

        // another func interface example with Runnable
        // Emp e1 = new Emp();
        // Thread t1 = new Thread(e1);
        // t1.start();

        
        // same code with lambda
        Runnable r1 = ()->{
            for(int i =0; i<5; i++){
                System.out.println("Inside child but in main");
            }
        };
        Thread t2 = new Thread(r1);
        t2.start();

        for(int i =0; i<5; i++){
            System.out.println("Main thread");
        }

        // native methods are methods which are implemented internally, may or may not be written 
        // in Java, like libuv used C++

        // transient is for variables, using transient keyword stops the value of variable 
        // from being stored during serialization; default or null is stored in place


        // volatile is applicable only for variables
        // to overcome data inconsistency
        // creating and maintaining a copy for each thread; hence deprecated

        // throw, throws
        


        // final, finalize, finally

        // variable -> static, final
        // methods -> statci abstract, final

        /**
         * Some research. What is an Integer? And why cannot we use int in an ArrayList?
         * An Integer is a reference type (a class). An int is a value. And: The
         * ArrayList requires, in its implementation, a reference type. So int is not
         * allowed.
         * 
         * Quote: The Integer class wraps a value of the primitive type int in an
         * object. An object of type Integer contains a single field whose type is int
         * (Java Documentation).
        */

    }
}

interface IFruit{
    // Illegal modifier for the interface field IFruit.name; only public, static & final are permitted
    public String type = "Apple";
    // protected String name = "Inter";


}

class Fruit implements IFruit{
    // String type = "Mango";
    
}

// functional interface, single method
// it can have defualt and staic methods
// this interface @FunctionalInterface
// https://www.youtube.com/watch?v=qML6jcQu3jE&t=5509s
@FunctionalInterface
interface Boy{
    public void m1();
}

// interface can only exytend another intyerface, it can't impelement
// Functional Interface can have only one abstract method, so 
// if FunctionalInterface annotation is used, then Boy2 can't have any other method
// as it needs to implement Boy's method

// @FunctionalInterface
interface Boy2 extends Boy{
    public void m1();
    public int m2();
}

// how to implement a lambda in class
class Student implements Boy{
    public void m1(){
         System.out.println("inside m1");
    }
}

class CollegeStudent implements Boy2{
    public void m1(){
         System.out.println("inside m1");
    }

    public int m2(){
        return 1+2;
    }
}

// https://www.youtube.com/watch?v=kpK2e343v48&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=9

// it is difficult to define lambda methods as they have no return type, no access modifers and no name
// how to call lambda exp?

// we can't define method insiide interface;
// we define in class or during instantion in main
// lambda with Runnable
class Emp implements Runnable{

    public void run(){
        for(int i =0; i<5; i++){
            System.out.println("child thread with lambda");
        }
    }
}