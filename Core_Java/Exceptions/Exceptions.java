import java.util.*;
import java.io.*;

/** 
 * JVM Exceptions − These are exceptions/errors that are exclusively or logically thrown by the JVM. 
 * Examples: NullPointerException, ArrayIndexOutOfBoundsException, ClassCastException.
 * 
 * Programmatic Exceptions − These exceptions are thrown explicitly by the application or the 
 * API programmers. Examples: IllegalArgumentException, IllegalStateException.
*/
class Exceptions{
    /* For example, if you use FileReader class in your program to read data from a file, 
     * if the file specified in its constructor doesn't exist, then a FileNotFoundException occurs, 
     * and the compiler prompts the programmer to handle the exception.
     */
    static void readFile() {
        try {
            FileInputStream f = new FileInputStream("a");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("in here");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        readFile();
    }
}


class InsufficientFundsException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private double amount;

    public InsufficientFundsException(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

class Fund {
    void withdraw() throws InsufficientFundsException{
        try {
            
        } catch (Exception e) {
            //TODO: handle exception
            throw InsufficientFundsException();
        }
    }

    void funcThrow() throws ArrayIndexOutOfBoundsException{
        try {
            throw new ArrayIndexOutOfBoundsException();
        } catch (Exception e) {
            //TODO: handle exception

        }
    }
}

