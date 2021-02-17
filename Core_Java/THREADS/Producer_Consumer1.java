import java.util.*;

/** 
 * 1 implement runnable
 * 2 wait, notify
 * 3 monitor
 * 4 exception IO
 * 5 import java.io.*
 * */ 
class Producer_Consumer1{
    Deque<Integer> q = new LinkedList<>();

    public void add(){
        synchronized(this){
            try {
                while(q.size() != 0) wait();
                for(int i=0; i<5; i++) q.addLast((int)(Math.random()*2)); 
                System.out.println(" added to q "+ q);   
                notifyAll();
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        }
    }

    public void remove(){
        synchronized(this){
            try {
                while(q.size() == 0) wait();
                for(int i=0; i<5; i++) System.out.println("removed" + q.removeFirst());    
                notifyAll();
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        }
    }

    public static void main(String[] args) {
        Producer_Consumer1 pc1 = new Producer_Consumer1();
        new Producer(pc1);
        new Consumer(pc1);
    }
}

class Producer implements Runnable{
    Producer_Consumer1 pc;
    Thread t;
    Producer(Producer_Consumer1 p){
        this.pc = p;
        t = new Thread(this);
        t.start();
    }

    public void run(){
        this.pc.add();
    }

}

class Consumer implements Runnable{
    Producer_Consumer1 pc;
    Thread t;
    Consumer(Producer_Consumer1 p){
        this.pc = p;
        t = new Thread(this);
        t.start();
    }

    public void run(){
        this.pc.remove();
    }
}