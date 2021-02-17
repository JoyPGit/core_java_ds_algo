import java.util.*;

/** 
 * get a thread pool,
 * pass classes to submit and use Future to refer
 * call get on refvar
 * 
 * exec service, thread pool, es.submit, f.get
*/
// Future is an interface which hold ref to Callable Object
public class Future_Executor {
    ExecutorService es = Executors.newFixedThreadPool(3);
    Future<Integer> f1, f2, f3;

    Future_Executor(Callable c1, Callable c2, Callable c3){
        f1 = es.submit(c1);
        f2 = es.submit(c2);
        f3 = es.submit(c3);
        runner();
    }

    void runner(){
        f1.get();
        f2.get();
        f3.get();
    }

    public static void main(String[] args) {
        Add a = new Add(2, 1);
        Subtract s = new Subtract(2, 1);
        Multiply m = new Multiply(2, 3);
        new Future_Executor(a, s, m);
    }

}

class Add implements Callable{
    int num1, num2;
    Add(int a, int b){
        this.num1 = a; this.num2 = b;
    }

    int add(){
        return this.num1 + this.num2;
    }

    public Object call() {
        return this.num1 + this.num2;
    }
}

class Subtract implements Callable{
    int num1, num2;
    Subtract(int a, int b){
        this.num1 = a; this.num2 = b;
    }

    int subtract(){
        return this.num1 - this.num2;
    }
    public Object call() {
        return this.num1 - this.num2;
    }
}


class Multiply implements Callable{
    int num1, num2;
    Multiply(int a, int b){
        this.num1 = a; this.num2 = b;
    }

    int multiply(){
        return this.num1 * this.num2;
    }
    public Object call() {
        return this.num1 * this.num2;
    }
}