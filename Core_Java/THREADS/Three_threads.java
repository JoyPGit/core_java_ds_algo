// package Core_Java.Threads;

import java.util.*;

/** 
 * here 2 approaches 
 * one is synchronized block iun runner class when shared class method is not synchronized
 * second is synchronized method in shared and each thread uses 
*/
/** 
 * A SYNCHRONIZED BLOCK INSIDE RUN;
 * LOCK ON OBJECT 
 * 
 * Volatile keyword is used to modify the value of a variable by different threads. 
 * It is also used to make classes thread safe. It means that multiple threads can 
 * use a method and instance of the classes at the same time without any problem.
 * 
 * */


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
        
        System.out.println("Two implementations, one has 'run' as shared; other has a shared class");
        Thread t1 = new Thread(new Three_threads(1), "Thread 1");
        Thread t2 = new Thread(new Three_threads(2), "Thread 2");
        Thread t3 = new Thread(new Three_threads(0), "Thread 3");

        // t1.start();
        // t2.start();
        // t3.start();

        SharedPrinter sharedp = new SharedPrinter();
        new NumberPrinter(1, sharedp, "t_1");
        new NumberPrinter(2, sharedp, "t_2");
        new NumberPrinter(0, sharedp, "t_3");

    }
}


class SharedPrinter{
    int commonNumber;
    boolean isGreater;
    SharedPrinter(){
        this.isGreater = false;
        this.commonNumber = 1;
    }

    synchronized void print(int remainder) throws InterruptedException{
        while(commonNumber<10 && !isGreater){
            while(commonNumber%3!=remainder){
                try {
                    if(commonNumber >= 9) return;
                    wait();
                } catch (InterruptedException e) {
                    //TODO: handle exception
                    System.out.println("Thread was interrupted, Failed to complete operation");
                    e.printStackTrace();
                }
            }

            // how to stop a thread?
            // Thread.currentThread().stop() is deprecated

            // https://stackoverflow.com/questions/10961714/how-to-properly-stop-the-thread-in-java

            
            System.out.println(Thread.currentThread().getName()+" -> "+ commonNumber);
            commonNumber++;
            notifyAll();
        }
    }
}

class NumberPrinter implements Runnable{
    Thread t;
    int remainder;
    SharedPrinter p;
    
    NumberPrinter (int r, SharedPrinter p, String name){
        this.p = p;
        this.remainder = r;
        this.t = new Thread(this, name);
        this.t.start();
    }

    public void run(){
        try {
            p.print(this.remainder);
        } catch (InterruptedException e) {
            //TODO: handle exception
            System.out.println("thraed interrupted");
        }
    }
}



