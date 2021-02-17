import java.util.*;
// Thrown to indicate that a thread has attempted to wait on an object's 
// monitor or to notify other threads waiting on an object's monitor without owning the specified monitor.

/** 
 * lock.wait
 * lock.notify
 * 
 * so obj if used as monitor then for wait we need obj.wait else it throws
 * IllegalState Exception which means wait is callled before lock was acquired.
*/
// synchronized and monitor
class Producer_Consumer{
    private Object obj = new Object();
    List<Integer> q = new ArrayList<>(5);

    // synchronized methods vs blocks
    // try synchronized at diff places
    void produce(Thread t){
        while(true){
            // monitor; only one thread can access 
            synchronized (this){ // synchronized(this.obj)
                try {
                    while(q.size()!=0) wait();  // obj.wait()

                    System.out.println("in producer");
                    for(int i =0; i<5; i++){
                        q.add((int)(Math.random()*i));
                        System.out.println("added ");
                    }
                    notifyAll();
    
                    Thread.sleep(1000);
                } catch (Exception e) {
                    //TODO: handle exception
                    e.printStackTrace();
                }
            }
            
        }
    }

    void consume(Thread t){
        while(true){
            synchronized(this){ // synchronized(this.obj)
                try {
                    while(q.size()!=5) wait(); // obj.wait()
                    System.out.println("in consumer");
                    for(int i =0; i<5; i++){
                        System.out.println("removed "+ q.remove(0));
                    }
                    notifyAll();
    
                    Thread.sleep(1000);
                    
                } catch (Exception e) {
                    //TODO: handle exception
                    e.printStackTrace();
                }
            }
            
        }
    }

    
    // BlockingQueue<> b = new BlockingQueue<E>();
    public static void main(String[] args) {
        Producer_Consumer pc = new Producer_Consumer();
        Producer p =new Producer(pc);
        Consumer c = new Consumer(pc);
    }
}

class Producer implements Runnable{
    Producer_Consumer p;
    Thread t;
    Producer(Producer_Consumer p){
        this.p = p;
        t = new Thread(this, "prod");
        t.start();
    }
    
    public void run() {
        p.produce(t);
    }
}


class Consumer implements Runnable{
    Producer_Consumer p;
    Thread t;
    Consumer(Producer_Consumer p){
        this.p = p;
        t = new Thread(this, "prod");
        t.start();
    }

    public void run(){
        p.consume(t);
    }
}