import java.util.*;

public class ProdConComplRef {
    public static void main(String[] args) {
        Q queue = new Q();
        new Producer(queue);
        new Consumer(queue);
    }
}

class Q {
    int n;
    boolean value;

    Q() {
        this.value = true;
    }

    synchronized int get() throws InterruptedException {
        while (!value) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("exception caught " + ex);
                throw new InterruptedException();
            }
        }

        System.out.println("Got " + n);
        // as first put should run
        value = false;
        notify();
        return n;
    }

    synchronized int put() throws InterruptedException {
        while (!value) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("exception caught " + ex);
                throw new InterruptedException();
            }
        }

        System.out.println("Put " + n++);
        // as first put should run
        value = true;
        notify();
        return n;
    }

}

class Producer implements Runnable {
    Q prod;
    Thread t;

    Producer(Q q) {
        this.prod = q;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            while(true) this.prod.put();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class Consumer implements Runnable {
    Q con;
    Thread t;

    Consumer(Q q) {
        this.con = q;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            while(true) this.con.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}