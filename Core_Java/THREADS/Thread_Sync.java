package Threads;

class Thread_Sync implements Runnable{
    private Object obj;

    Thread_Sync(){
        this.obj = new Object();
    }
    public void run(){
        if(Thread.currentThread().getName().equalsIgnoreCase("test1")){
            test1();
        }else{
            test2();
        }
    }

    public void test1(){
        synchronized (obj){
            for(int i =0; i<5; i++) System.out.println("test1");
        }
    }
    public void test2(){
        synchronized (obj){
            for(int i =0; i<5; i++) System.out.println("test2");
        }
    }

    public static void main(String[] args) {
        Thread_Sync t = new Thread_Sync();
//        Thread t1 = new Thread(new Thread_Sync(), "test1");
        Thread t1 = new Thread(t, "test1");
        t1.start();
//        Thread t2 = new Thread(new Thread_Sync(), "test2" );
        Thread t2 = new Thread(t, "tes2");
        t2.start();
    }
}