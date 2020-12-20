package Core_Java.THREADS;

// https://www.baeldung.com/java-even-odd-numbers-with-2-threads

public class EvenOdd implements Runnable {
    // critical resource(s) being shared
    private int max;
    private Printer print;
    private boolean isEvenNumber;

    // standard constructor
    EvenOdd(Printer p, int n, boolean val){
        this.print = p;
        this.max = n;
        this.isEvenNumber = val;
    }
    
    @Override
    public void run() {
        int number = isEvenNumber ? 2 : 1;
        while (number <= max) {
            if (isEvenNumber) {
                print.printEven(number);
            } else {
                print.printOdd(number);
            }
            number += 2;
        }
    }   
    
    public static void main(String[] args) {
        Printer print = new Printer();
        Thread t1 = new Thread(new EvenOdd(print, 10, false), "Odd"); // thread name
        Thread t2 = new Thread(new EvenOdd(print, 10, true), "Even");
        t1.start();
        t2.start();
    }
}

// In Java, we can mark a method or block as synchronized, 
// which means that only one thread will be able to enter that 
// method or block at a given point of time.
// here printEven is synchronized

class Printer {
    private volatile boolean isOdd;

    // common parameter in both sync methods

    synchronized void printEven(int number) {
        while (!isOdd) {
            try {
                System.out.println("first ?");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);
        isOdd = false;
        notify();
    }

    synchronized void printOdd(int number) {
        System.out.println("isOdd "+isOdd);
        while (isOdd) {
            try {
                System.out.println("before wait");
                wait();
                System.out.println("after wait");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);
        isOdd = true;
        notify();
        /** 
         * The awakened thread will not be able to proceed until the current thread 
         * relinquishes the lock on this object. Hence thread enters while loop.
         * wait makes it relinquish the lock.
        */
    }
}

/** 
 * thread.start? how without start method?
 * 
 * Volatile keyword is used to modify the value of a variable by different threads. 
 * It is also used to make classes thread safe. It means that multiple threads 
 * can use a method and instance of the classes at the same time without any problem.
 * 
*/

