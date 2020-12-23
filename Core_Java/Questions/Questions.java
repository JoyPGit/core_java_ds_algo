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


        // Polymorphism
        /**
         * Polymorphism is the ability of an object to take on many forms. 
         * The most common use of polymorphism in OOP occurs when a parent class reference is used 
         * to refer to a child class object.
         */

         
        // interface can be used as reference to class implementing it, polymorphism
        IFruit if1 = new Fruit();
        // as type is static
        System.out.println("type is : "+if1.type);

        // functional interface, lambda implementation, m1 defined here
        // as lambdas are anonymous, these are defined this way
        Boy b = ()->{System.out.println("m1 of Boy interface implemented");};
        b.m1();

        // another func interface x with Runnable
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

        /** 
         * Serialization and De-serialization :
         * serialization is the conversion of a Java object into a static stream (sequence) of bytes 
         * which can then be saved to a database or transferred over a network.
         * 
         * The serialization process is instance-independent, i.e. objects can be serialized on one platform and deserialized on another. 
         * 
         * Classes that are eligible for serialization need to implement a special marker interface Serializable.
         * Serializable is a marker interface (no methods, no body)
         * 
         * When a class implements the java.io.Serializable interface, all its sub-classes are 
         * serializable as well. On the contrary, when an object has a reference to another object, 
         * these objects must implement the Serializable interface separately, 
         * or else a NotSerializableException will be thrown:
         * 
         * If a serializable class doesn't declare a serialVersionUID, 
         * the JVM will generate one automatically at run-time.
        */
        ObjSave save1 = new ObjSave(3);

        try {
            File f = new File("serialize_Obj.txt");
            // for writing data to a file
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // writes specifed obj to output stream
            oos.writeObject(save1);
            oos.flush();
            oos.close();
        
            // same file reference
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ios = new ObjectInputStream(fis);
            ObjSave deserializedObj = (ObjSave)(ios.readObject());
            ios.close();

            System.out.println("deserialized obj int i "+deserializedObj.i);
            System.out.println(save1.equals(deserializedObj));
            // after implementing equals, both will be equal
    
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            throw new Exception();
        }
        


        // volatile is applicable only for variables
        // to overcome data inconsistency
        // creating and maintaining a copy for each thread; hence deprecated

        // throw, throws
        


        // final, finalize, finally



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

// java.io.NotSerializableException: ObjSave
// java.io.eofexception
class ObjSave implements Serializable{
    // private static final long serialVersionUID = 1L;
    int i;
    ObjSave(int i){
        this.i = i;
    }
}