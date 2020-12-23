
import java.util.*;
import java.io.*;

// Polymorphism
/**
 * 
 * https://java2blog.com/polymorphism-java-example/
 * 
 * Polymorphism is the ability of an object to take on many forms. 
 * The most common use of polymorphism in OOP occurs when a parent class reference is used 
 * to refer to a child class object.
 * 
 * 
 * There are two types of polymorphism in java.
 * 
 * Compile time polymorphism.
 * Run time polymorphism.
 * 
 * Compile time Polymorphism
 * Compile time Polymorphism is nothing but method overloading in java. 
 * You can define various methods with same name but different method arguments
 * 
 * 
 * Runtime Polymorphism is nothing but method overriding in java.
 * If subclass has same method as base class then it is known as method overriding.
 * 
 * 
 * JVM decides at runtime which method it needs to call depending on the object assignment. 
 * That’s why this is known as Run time polymorphism.
 * 
 */


// https://java2blog.com/java-interview-questions-for-5-years-experience/
public class Polymorphism extends SuperClass {

    void m1(){
		System.out.println("In m1 Polymorphism subclass");
	}
	void m2(){
		System.out.println("In m2 B Polymorphism subclass");
    }
    
    public static void main(String[] args) {
        // polymorphism
        SuperClass a = new Polymorphism();
        // if m1 throws exception, use in a try_catch block else compile time error
        // a.m1();

        try {
            // throws error as there is no m2 method in SuperClass
    		// a.m2();

            // runtime polymorphism; JVM decides and calls m2 of child class
            a.m1();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

}


class SuperClass {
    void m1() throws Exception {
		System.out.println("In m1 SuperClass");
	}
}
