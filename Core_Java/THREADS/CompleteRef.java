// package Core_Java.Threads;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/** 
 * threads need not always run when accessing critical or shared resources
 * 
 * thread needs an object ref to run. so this is passed if thread is instantiated inside the class, 
 * else an instance is passed during thread creation if outside
 * 
 * 2 ways using implements Runnable:
 * 
 * 1 create a new object, pass this to a new Thread(); t.start()
 * 2 create a new thread inside constructor; pass (this) and start the thread;
 * 
 * when extending
 * no need to use run(); but start the thread inside constructor;
 * 
 * using lambda: call refVar.run(); as rub is the only method of Runnable fucntional interface
 * 
 * 
 * 
*/
public class CompleteRef {
    
    public static void main(String[] args) {
        // new NewThread1();

        /** 
         * NewThread2 runnableThread = new NewThread2();
         * Thread outside = new Thread(runnableThread);
         * outside.start();
         * 
         * Runnable r = ()->{
         *     try {
         *         for(int i = 6; i<11; i++){
         *             System.out.println("using lambda "+ i);
         *             Thread.sleep(1000);
         *         }
         *     } catch (Exception e) {
         *       //TODO: handle exception
         *     }
         *  
         * };
         * r.run();
         */
         
        // 2 threads named one and two 
        // new NewThread2("one");
        // new NewThread2("two");

        /** 
         * synchronized is used on same shared object, here same instance of Shared is passed
         * to sync_one and sync_two;
         * 
         * synchronized is used on shared's method
         * Threads inside Synch will call sychronized method of shared
         * start threads inside class and call shared inside run().
        */
        // Shared shared = new Shared();
        // Synch sync_one = new Synch("sync_one", shared);
        // Synch sync_two = new Synch("sync_two", shared);
        try {
            // join just waits for a thread to die, se same technique of starting thread inside constructor
            // sync_one.t.join();
            // sync_two.t.join(); 
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        // Food f = new Food();
        // Producer p = new Producer(f);
        // Consumer c = new Consumer(f);

        // Printer printer = new Printer();
        // new NumberPrinter(1, printer, "thread_one");
        // new NumberPrinter(2, printer, "thread_two");
        // new NumberPrinter(0, printer, "thread_three");


        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> f1;
        Future<Integer> f2;
        Future<Integer> f3;

        System.out.println("starting executor");

        f1 = es.submit((Callable<Integer>) new Task1(1, 2));
        f2 = es.submit((Callable<Integer>) new Task2(1, 2));
        f3 = es.submit((Callable<Integer>) new Task3(2, 3));

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        System.out.println("all tasks completed, in main");
        es.shutdown();
        System.out.println("executor service shut down");
    }
}


// extending Thread
// thread can be started inside constructor
class NewThread1 extends Thread{
    int num;

    NewThread1(){
        super("thread1");
        start();
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(++num);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
    }
}


// implements Runnable
class NewThread2 implements Runnable{
    int num;
    Thread t;

    NewThread2(String s){
        // need to pass object to run
        this.t = new Thread(this, s);
        this.t.start();
    }

    public void run() {
        try {
            for(int i =0; i<5; i++){
                System.out.println(t.getName()+" " + ++num);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        System.out.println(t.getName()+" exiting");
    }
}

class Shared{

    // not using instance var as I want to pass the value from thread
    // so passing arg to sync method
    Shared(){}
    // synchronized on shared resource
    // if synchronized is not used, as soon as Thread1 goes to sleep , thread2 starts printing
    /**
     * non synchronized :
     * [sync_one
     * [sync_two
     * ]
     * ]
     * 
     * synchronized :
     * [sync_one
     * ]
     * [sync_two
     * ]
     * 
     * synchronized And sleep causes everything in sync
     * synchronized and wait causes non sync
     * 
     * sleep() is a method which is used to hold the process for few seconds or the time 
     * you wanted but in case of wait() method thread goes in waiting state and 
     * it won’t come back automatically until we call the notify() or notifyAll().
     * 
     * The major difference is that wait() releases the lock or monitor while sleep() doesn’t 
     * release any lock or monitor while waiting. 
     * Wait is used for inter-thread communication while sleep is used to introduce pause 
     * on execution, generally.
     * 
    */
    synchronized void print(String msg){
        System.out.println('['+ msg);

        try {
            wait(1000);
            // Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        System.err.println(']');
    }
}
// Synchronized method is used to lock an object for any shared resource.
// so it must be used on a common class's method not on run
class Synch implements Runnable{
    String name;
    Thread t;
    Shared shared;

    Synch(String s, Shared sharedObjInstance){
        this.name = s;
        shared = sharedObjInstance;
        this.t = new Thread(this); // 1 which class to pass
        // this.t = new Thread(shared);
        t.start();
    }

    public void run(){
        try {
            shared.print(this.name);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}

class Food {
    List<Integer> list;
    boolean isEmpty;

    Food(){
        this.isEmpty = true;
        this.list = new ArrayList<>();
    }

    synchronized void get(){
        while(true){
            try {
                while(isEmpty){
                    wait();
                    // not using notify() causes deadlock as both are in waiting mode
                }    
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            while(list.size()!=0){
                System.out.println("in get "+list.remove(0));
            }
            isEmpty = true;
            notify();
        }
        
    }

    synchronized void put(){
        while(true){
            try {
                while(!isEmpty){
                    wait();
                }    
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            for(int i =0; i<5; i++) {
                list.add((int)(Math.random()*10*i));
                System.out.println("in put "+list.get(list.size()-1));
            }
    
            isEmpty = false;
            notify();

        }
    }
}

class Producer implements Runnable{
    Thread t;
    Food food;

    Producer(Food f){
        this.t = new Thread(this);
        t.start();
        this.food = f;
    }

    public void run(){
        while(true) this.food.put();
    }
}

class Consumer implements Runnable{
    Thread t;
    Food food;

    Consumer(Food f){
        this.t = new Thread(this);
        t.start();
        this.food = f;
    }

    public void run(){
        while(true) this.food.get();
    }
}

// using semaphore

// using blockingQueue


class Printer{
    int commonNumber;
    
    Printer(){
        this.commonNumber = 1;
    }

    synchronized void print(int remainder){
        while(commonNumber<10){
            while(commonNumber%3!=remainder){
                try {
                    wait();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }

            // to control the case when threads enter when commonNUmber is <9 and end up
            // printing 10, 11, use condition in the run block, like passing an arg 
            // which is checked in shared method
            System.out.println(Thread.currentThread().getName()+" -> "+ commonNumber);
            commonNumber++;
            notifyAll();
        }
        
    }
}

class NumberPrinter implements Runnable{
    Thread t;
    int remainder;
    Printer p;
    
    NumberPrinter (int r, Printer p, String name){
        this.p = p;
        this.remainder = r;
        this.t = new Thread(this, name);
        this.t.start();
    }

    public void run(){
        p.print(this.remainder);
    }
}

// .get calls call
// arg passed in constructor
// 


// Tasks
class Task1 implements Callable<Integer>{
    int num1, num2;
    Task1(int a, int b){
        this.num1 =a; this.num2 = b;
    }

    public Integer call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("multiplication");
        return num1+num2;
    }
}

// Callable is a raw type. References to generic type Callable<V> should be parameterized
// Callable<Integer>
class Task2 implements Callable{
    int num1, num2;
    Task2(int a, int b){
        this.num1 =a; this.num2 = b;
    }

    public Integer call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("multiplication");
        return num1-num2;
    }
}

class Task3 implements Callable<Integer>{
    int num1, num2;
    Task3(int a, int b){
        this.num1 =a; this.num2 = b;
    }

    public Integer call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("multiplication");
        return num1*num2;
    }
}