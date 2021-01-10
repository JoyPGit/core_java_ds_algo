import java.util.*;

/** 
 * thread always requires a class to run, so a param needs to be passed
*/
// can't reduce visibility
public class ThreadCreation implements Runnable{
    int i;
    Thread t;
    // ThreadPrac(int i, Thread t){
        ThreadCreation(int i){
        this.i = i;
        // need to pass object to run
        this.t =  new Thread(this);
        t.start();
    }

    public void run(){
        System.out.println("in here");
        System.out.println(t.getName()+" "+this.i);
        System.out.println(t.currentThread().getName()+" "+this.i);
    }

    public static void main(String[] args) {
        Thread b = new Thread(new ThreadCreation(2), "thread-b");
        b.start();
        Thread c = new Thread();

        // need to pass object to run
        ThreadCreation tp1 = new ThreadCreation(1);
        // ThreadCreation tp1 = new ThreadCreation(1, a);
        // ThreadCreation tp2 = new ThreadCreation(2, b);
        // ThreadCreation tp3 = new ThreadCreation(3, c);
    }
}


/** 
 * 3 cases :
 * thread is instantiated inside class
 * thread is instantiated outside and start is called outside
 * Runnable r = ()->{
 * }
 * r.run();
*/
