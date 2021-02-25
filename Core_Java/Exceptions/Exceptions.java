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
/** 
 * 
 * Checked and unchecked exceptions in java
 * 
 * What are checked exceptions?
 * Checked exceptions are checked at compile-time. It means if a method is throwing a checked exception 
 * then it should handle the exception using try-catch block or it should declare the exception using 
 * throws keyword, otherwise the program will give a compilation error.
 * 
 * How to resolve the error? There are two ways to avoid this error. 
 * 
 * https://beginnersbook.com/2013/04/java-checked-unchecked-exceptions-with-examples/
 * 
 * Method 1: Declare the exception using throws keyword.
 * Method 2: Handle them using try-catch blocks.
 * 
 * 
 * Unchecked Exceptions
 * NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException, 
 * IllegalArgumentException, NumberFormatException
*/

