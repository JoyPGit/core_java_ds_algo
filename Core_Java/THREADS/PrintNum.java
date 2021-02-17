public class PrintNum {
    int base;
    PrintNum(){
        base = 0;
    }

    void printNumbers(int rem){
        while(base<10){
            synchronized(this){
                try {
                    while(base%2!=rem) wait();
                    System.out.println(base+", "+Thread.currentThread().getName());
                    base++;
                    notifyAll();
                    // Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            
           
        }
    }
    public static void main(String[] args) {
        PrintNum pNum = new PrintNum();
        new Worker(0, pNum);
        new Worker(1, pNum);
        new Worker(2, pNum);
    }
}

class Worker implements Runnable{
    PrintNum pn;
    int remainder;
    Thread t;
    Worker(int n, PrintNum p){
        this.pn = p;
        this.remainder = n;
        t = new Thread(this);
        t.start();
    }

    public void run(){
        pn.printNumbers(remainder);
    }
}


/** 
 * https://stackoverflow.com/questions/11900982/two-synchronized-blocks-execution-in-java
 * 
 * 1 if two diff methods have synchronized keyword, both will run sequentially. why?
 * because the lock is on the object
 * 
 * 2 using this as monitor vs object obj= new object as monitor?
 * obj.wait vs wait
 * 
 * 3 basic structure:
 * synchronized(this){
 *    while (wait);
 *    num++; notifyAll();
 * }
 * 
 * but thread can throw index out of bounds exception, so wrap a try catch block around
 * 
 * the method will run once and a single thread will execute.
 * to run multiple times, use while loop
 * 
 * 
*/