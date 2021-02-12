//// package Core_Java.Threads;
//
//import java.io.*;
//import java.util.*;
//import java.util.concurrent.*;
//
//class CompleteRef_copy{
//    public static void main(String[] args) {
//        Printer pass = new Printer();
//        new Servant(1, pass);
//        new Servant(2, pass);
//        new Servant(3, pass);
//    }
//}
//
//class Printer{
//    int globalVar = 0;
//    Printer(){
//
//    }
//    synchronized printUtilty(int number){
//        while(globalVar%number!=0){
//            wait();
//        }
//        notifyAll();
//        globalVar++;
//    }
//}
//
//class Servant implements Runnable{
//    int divisor;
//    Thread t;
//    Printer p;
//    Servant(int d, Printer passed){ //
//        this.divisor = d;
//        p = passed;
//        t = new Thread(this); //
//        t.start();
//    }
//    run(){
//        p.printUtilty(divisor);
//    }
//}
