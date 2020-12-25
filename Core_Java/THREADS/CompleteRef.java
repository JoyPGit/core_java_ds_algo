import java.util.*;

public class CompleteRef {
    
    public static void main(String[] args) {
        new NewThread();
    }
}

// threads need not always run when accessing critical or shared resources
class NewThread extends Thread{
    int num;

    NewThread(){
        super("thread1");
        start();
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(++num);
        }
    }
}