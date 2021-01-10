public class SharedMethod {
    
    /** 
     * even if each thread has its own run, they need to run sequentially
     * to print, so they need a shared object which will govern their running.
     * this is kind of similar to having one shared resource and all threads accessing it
     * 
     * So it's better to have a single class's three threads
     * If three classes want to access the critical section then 
     * either the class must have synchronized method or the calls to the shared 
     * class must be inside a sync block (COMPLETE REF)
     * 
     * 
     * synchronized takes a common shared object
     * 
    */

    /** 
     * 3 techniques to print
     * either a common resource is accessed simultaneously
     * or each class has its thread which runs simultanoeusly
     * 
     * there are variations in synchronized 
     * synchronized block 
     * synchronized method
     * 
     * synchronized will always be used with shared/critical resource, 
     * 
     * if each thread uses its own then each will have its own synchronized block
    */
    
    public static void main(String[] args) {
        Printer p1 = new Printer();
        new Runner(p1, 1);
        new Runner(p1, 2);
        new Runner(p1, 0);
    }
}


class Printer{
    int number;

    Printer(){
        this.number = 1;
    }

    synchronized void print(int threadVar){
        while(this.number<10){
            while(this.number%3!=threadVar){
                try {
                    wait();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(number);
            this.number++;
            notifyAll();
        }
    }
}
/** 
 * i want all to print 1,2,3..sequentially
 * so instead of three classes, used a single class and 
 * passed three instances to three threads
*/
class Runner implements Runnable{
    Printer p;
    Thread t;
    int var;

    Runner(Printer p, int v){
        this.p = p;
        this.t = new Thread(this);
        t.start();
        this.var = v;
    }
    public void run(){
        p.print(this.var);
    }
}