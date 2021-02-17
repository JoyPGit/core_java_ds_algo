import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Prod_Con_BlockQ{
    /** 
     * blocking queue can be found under java.util.concurrent package
     * it helps avoid the complexities of wait and notify.
     * 
     * */    
    public BlockingQueue<Integer> bq;
    Prod_Con_BlockQ(){
        this.bq = new LinkedBlockingQueue<>();
    }

    public static void main(String[] args) {
        Prod_Con_BlockQ pc = new Prod_Con_BlockQ();
        new Producer(pc);
        new Consumer(pc);
    }
}

class Producer implements Runnable{
    // common class 
    Prod_Con_BlockQ q;
    Thread t;
    Producer(Prod_Con_BlockQ queue){
        this.q = queue;
        t = new Thread(this, "test1");
        t.start();
    }

    public void run(){
        try {
            System.out.println("in here");
            for(int i =0; i<5; i++){
                q.bq.add(i);

                System.out.println("added");
                Thread.sleep(1000);

            }

        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}

// if not implemented Rujnable., then thread arg mismatch error
class Consumer implements Runnable{
    Prod_Con_BlockQ q;
    Thread t;
    Consumer(Prod_Con_BlockQ queue){
        this.q = queue;
        this.t = new Thread(this, "test2");
        t.start();
    }

    public void run(){
        try {
            for(int i=0; i<5; i++) {
                q.bq.take();
                System.out.println("removed");
                Thread.sleep(1000);

            }

        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}   