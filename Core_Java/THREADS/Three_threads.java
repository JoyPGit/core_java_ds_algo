/** 
 * Volatile keyword is used to modify the value of a variable by different threads. 
 * It is also used to make classes thread safe. It means that multiple threads can 
 * use a method and instance of the classes at the same time without any problem.
 * 
 * 
 * a common variable accessed by the three threads
 * 
 * 
 * 
 * 
 * ----------------------------------------------------------
 * Should i use synchronized in each thread class
 * or use a single synchronized method and make threads access it?
 * 
 * * Producer-Consumer uses 2 synchronized methods
 * * for printing even odd, also used 2
 * 
 * but for synchronized to work a critical resource is required, either
 * the synchronized block beomes the critical resource or a common 
 * no primitive data structure is chosen.
 * 
 * */
package Core_Java.THREADS;

import java.util.*;

public class Three_threads implements Runnable {
    // volatile int commonNumber;
    int remainder;
    static int number = 1;
    static Object lock = new Object();

    Three_threads(int r) {
        this.remainder = r;
    }

    /** 
     * synchronized run or a synchronized block or a common resource is called
     * 
     * basic structure :
     * while(n%3 != 0){ // some condition which is false
     *      try{
     *          wait();
     *      }
     *      catch(){
     *      }
     * }
     * 
     * print;
     * n++;
     * notifyAll();
    */
    // synchronized 
    public void run(){
        while(number<10){
            synchronized (lock){
                while(number%3 != remainder){
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        //TODO: handle exception
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+" - "+number);
                number++;
                lock.notifyAll();
            }
        }
    }

    
    public static void main(String[] args) {
        
        System.out.println("in here");
        Thread t1 = new Thread(new Three_threads(1), "Thread 1");
        Thread t2 = new Thread(new Three_threads(2), "Thread 2");
        Thread t3 = new Thread(new Three_threads(0), "Thread 3");

        t1.start();
        t2.start();
        t3.start();

    }
}



