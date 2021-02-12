package Threads;// package Core_Java.Threads;

import java.util.*;

// https://www.baeldung.com/java-even-odd-numbers-with-2-threads

/*
 * Inter-Thread Communication
 * Inter-thread communication allows synchronized threads to communicate with each 
 * other using a set of methods.
 * The methods used are wait, notify, and notifyAll, which are all inherited from the Object class.
*/

/** 
 * passing Obj param to new Thread and t.start outside
 * or using Thread as instance var and t.start in constructir
 * in both cases class needs to implement runnable as 
 * thread only runs code inside run()
 * 
*/


public class Prod_Cons {
    // create obj of both
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Producer p = new Producer(list);
        Consumer c = new Consumer(list);
        Thread t1 = new Thread(p,"Odd"); // thread name
        Thread t2 = new Thread(c,"Even");
        t1.start();
        t2.start();
    }
}

class Producer implements Runnable{
    List<Integer> list;

    Producer(List<Integer> list){
        this.list = list;
    }

    // needs public 
    public void run(){
        try {
            System.out.println("thread 1");
            // synchronized block
            synchronized(list){
                while(true){
                    if(list.size()>0){
                        list.wait();
                    }else {
                        produce(list);
                    }
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }

    public void produce(List<Integer> list) throws InterruptedException{
        int rand = (int)Math.random();
        
        for(int i =0; i<=5; i++){
            System.out.println(i*rand*10);
            Thread.sleep(1000);
            list.add(i);
            System.out.println("added el "+list.get(i));
        }
        list.notifyAll();
    }
}

class Consumer implements Runnable{
    List<Integer> list;

    Consumer(List<Integer> list){
        this.list = list;
    }

    // needs public 
    public void run(){
        synchronized(list){
            try {
                System.out.println("thread 2");
                while(true){
                    if(list.size() == 0){
                        list.wait();
                    }
                    else {
                        consume(list);
                    }
                }
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public void consume(List<Integer> list) throws InterruptedException {
        while(list.size()!=0){
            Thread.sleep(1000);
            System.out.println("removed el "+list.remove(0));
        }
        list.notifyAll();
    }
}

/** 
 * 1 InterruptedException
 * 
 * 2 run method and synchronized method
 * 
 * wait vs sleep?
 * wait on shared obj, sleep on thread
 * 
 * notifyAll
 * 
 * .run()
 * */


