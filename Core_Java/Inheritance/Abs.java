import java.util.*;

abstract public class Abs {
    static int absVar = 11;
    int absVar2 = 12;
    abstract void showabsVar();
    void showVar(){
        System.err.println("abs class method");
    }

    public static void main(String[] args) {
        Abs a = new Con();
        a.showVar();
        Con b = new Con();
        System.out.println(b.absVar);
        System.out.println(b.absVar2);
        
        System.out.println(b.infVar);
        System.out.println(Inf.infVar);
    }
}

interface Inf{
    static int infVar = 13;
    void showInfVar();
}

// The type Concrete must implement the inherited abstract method 
class Con extends Abs implements Inf{
    void showabsVar(){
        System.out.println("concrete implements abs class method");
    }

    // Cannot reduce the visibility of the inherited method from Inf
    public void showInfVar(){
        
    }
}