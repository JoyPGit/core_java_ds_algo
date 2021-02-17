import java.io.*;

// https://stackoverflow.com/questions/10034890/synchronized-block-and-monitor-objects/10034955
public class Thread_monitor implements Runnable{
    
    Object obj;
    Thread t;
    Thread_monitor(){
        obj = new Object();
        t = new Thread();
        System.out.println("constructor");
    }

    public void run(){
        if(Thread.currentThread().getName().equals("test1")) {
            test1();
        }
        else test2();
    }


    void test1(){
        synchronized(obj){
            for(int i = 0; i<3; i++) System.out.println("test1 "+i);
        }
    }

    void test2(){
        synchronized(obj){
            for(int i = 0; i<3; i++) System.out.println("test2 "+i);
        }
    }

    public static void main(String[] args) {
        // same instance of tm will pass same intance of obj
        Thread_monitor tm = new Thread_monitor();
        Thread t1 = new Thread(tm, "test1");
        Thread t2 = new Thread(tm, "test2");
        // t1.start();
        // t2.start();

        // String S= "AB";
        // int l1 =0, l2 = 0, r1= 0, r2 =0, index = 0;

        // int[] arr = new int[26];
        // for(int i =0; i<S.length(); i++){
        //     while((char)(i-'A') != S.charAt(i)) i++;
        //     i++;
        // }

        // for(int i =0; i<26; i++) System.out.println(i-'A');

        try {
            throw new Exception("LINE 50");
        } catch (IOException e) {
            //TODO: handle exception
        }
        catch(Exception E1){
            System.exit(1);
            System.out.println("caught ");
        }
    }
}
