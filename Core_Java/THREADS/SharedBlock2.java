package Threads;

public class SharedBlock2 {
    /** 
     * here a shared class method is accessed by threads of Runner class
    */
    /** 
     * thread only runs the portion of code specified inside run
     * so it doesn't matter is thread is initialized outside or inside the class
    */
    public static void main(String[] args) {
        Printer p = new Printer();
        (new Thread(new Runner(p, 1), "thread_1")).start();
        (new Thread(new Runner(p, 2), "thread_2")).start();
        (new Thread(new Runner(p, 0), "thread_3")).start();
    }
}

class Printer{
    int number;

    Printer(){
        this.number =1;
    }
    synchronized void print(int remainder){
        while(number<9){
            while(number%3!=remainder){
                try {
                    wait();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(number);
            number++;
            notifyAll();
        }
    }
}

/** 
 * here a shared class method is accessed by threads of Runner class
*/
class Runner implements Runnable{
    Printer p;  
    int threadVar;
    Thread t;

    public Runner(Printer p, int n){
        this.p = p;
        this.threadVar = n;
    }

    public void run(){
        p.print(this.threadVar);
    }

}
