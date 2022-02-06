import java.util.*;


public class PrintNum2{
    List<Integer> list;

    PrintNum2(){
        this.list = new ArrayList<>();
    }

    public void produce(){
        synchronized(this){
            while(this.list.size()!=5){
                this.list.add(2);
                System.out.println("Added ");
            }
        }
    }


    public void consume(){
        synchronized(this){
            while(this.list.size()!=0){
                System.out.println(this.list.remove());
            }
        }
    }

    public static void main(String[] args){
        Producer p = new Producer(this.list);
        Consumer c = new Consumer(this.list);
    }

    class Producer implements Runnable{
        Thread t;
        PrintNum2 base;

        Producer(List<Integer> list){
            this.t = new Thread(this, "prod");
            this.prodList = list;
            t.start();
        }

        public void run(){
            this.base.produce();
        }

    }


    class Consumer implements Runnable{
        Thread t;
        PrintNum2 base;

        Consumer(List<Integer> list){
            this.t = new Thread(this, "cons");
            this.consList = list;
            t.start();
        }

        public void run(){
            this.base.consume();
        }
    }
}

/***
a shared block : 2 synchronized blocks
prod calls sync bloc, con calls sync bloc


 */