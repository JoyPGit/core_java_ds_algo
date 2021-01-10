public class SharedBlock1 {
    public static void main(String[] args) {
        // Printer p = new Printer(n)
        new Thread(new PrintUsingSyncBlock(1), "thread_1").start();
        new Thread(new PrintUsingSyncBlock(2), "thread_2").start();
        new Thread(new PrintUsingSyncBlock(0), "thread_3").start();
    }
}

/* java.lang.IllegalMonitorStateException
 * 
 * this comes up when wait is used in place of wait.notify();
*/

// class Printer{
//     int number;

//     Printer(int n){
//         this.number = n;
//     }

//     void print(){
//         System.out.println(this.number);
//     }
// }

// use abstract class or use extends 
// here also we need a common var, so static var is used
// wi/o using static in this class, maybe an abtract class
// or a parent class can be used.

/**
 * instead of using a separate class main can be used to run PrinyUsingSyncBlock also
 */

class PrintUsingSyncBlock implements Runnable{
    int threadVar;
    static int number = 1;
    static Object obj = new Object();
    PrintUsingSyncBlock( int t){
        this.threadVar = t;
    }

    public void run(){
        while(number<10){
            synchronized (obj){
                while(number%3!=threadVar){
                    try {
                        obj.wait();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(number);
                number++;
                obj.notifyAll();
            }
                
        }
    }

//     public static void main(String[] args) {
//         new Thread(new PrintUsingSyncBlock(1), "thread_1").start();
//         new Thread(new PrintUsingSyncBlock(2), "thread_2").start();
//         new Thread(new PrintUsingSyncBlock(0), "thread_3").start();
//     }
}