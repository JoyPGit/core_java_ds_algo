package Core_Java.THREADS;

public class Four_Threads implements Runnable {
    static int commonNumber = 1;
    static int limit = 12;
    int remainder;
    static Object lock = new Object();

    Four_Threads(int r) {
        this.remainder = r;
    }

    // synchronized
    public void run() {
        while (commonNumber < limit) {
            synchronized (lock) {
                while (commonNumber % 4 != remainder) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
                if (commonNumber > 12) {
                    // Thread.currentThread().stop();
                    // commonNumber = limit;
                    // continue;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+" : "+commonNumber);
                commonNumber++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Four_Threads(1), "thread-1");
        Thread t2 = new Thread(new Four_Threads(2), "thread-2");
        Thread t3 = new Thread(new Four_Threads(3), "thread-3");
        Thread t4 = new Thread(new Four_Threads(0), "thread-4");
        t1.start(); t2.start(); t3.start(); t4.start();
    }
}

// java.lang.IllegalMonitorStateException
// also see how synchronized is used; synchronized block
// vs synchronized method?
