package com.kafka.udemy.kafkabeginner;

import lombok.SneakyThrows;

import java.util.Deque;
import java.util.LinkedList;

public class ProdConWoThird {
    /**
     * instead of a third party class, each of producer and consumer will have the sync methods
     * and use a shared queue instead of shared third party class.
     * https://www.java67.com/2012/12/producer-consumer-problem-with-wait-and-notify-example.html
     * 
     * */
    Deque<Integer> q;
    ProdConWoThird() {
        this.q = new LinkedList<>();
    }

    class Producer implements Runnable{
        Deque<Integer> sharedQ;
        Thread t;

        Producer(Deque<Integer> q){
            this.sharedQ = q;
            this.t = new Thread(this);
            this.t.start();
        }

        @SneakyThrows
        @Override
        public void run(){
            while(true) {
                synchronized (sharedQ) {
                    while (sharedQ.size() == 5)
                        sharedQ.wait(); // wait obj to call on whom?
                    for (int i = 0; i < 5; i++) {
                        this.sharedQ.add(i);
                        System.out.println("added " + i);
                    }
                    Thread.sleep(1000);
                    sharedQ.notifyAll();
                }
            }
        }
    }


    class Consumer implements Runnable{

        Deque<Integer> sharedQ;
        Thread t;

        Consumer(Deque<Integer> q){
            this.sharedQ = q;
            this.t = new Thread(this);
            this.t.start();
        }

        @SneakyThrows
        @Override
        public void run() {
            while(true) {
                synchronized (sharedQ) {
                    while (sharedQ.size() == 0)
                        sharedQ.wait();
                    for (int i = 0; i < 5; i++) {
                        System.out.println("removed " + sharedQ.removeFirst());
                    }
                    Thread.sleep(1000);
                    sharedQ.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProdConWoThird prodConWoThird = new ProdConWoThird();
        prodConWoThird.new Producer(prodConWoThird.q);
        prodConWoThird.new Consumer(prodConWoThird.q);
    }
}
