
/** 
 * How to instantate and run class defined inside a class
 * if InnerClass inner = new InnerClass("inner");
 * this gives static error : non-static variable this cannot be referenced from a static context
 * 
 * 1 InnerClass is defined inside; hence it is a class variable for ParentClass,
 * so it can't be instantiated before instantiating ParentClass.
 * 
 * 2 method accessInner creates a new instance of InnerClass,
 * but it is attached to an instance of ParentClass
 * 
 * 3 OuterClass is dfined outside, so not class variable
 * 
 * 4 any outer class can be instantiated and run inside any other class's main
 * 
 */


public class ParentClass {

    class InnerClass{
        String name;

        InnerClass(String s){
            this.name = s;
            System.out.println(this.name);
        }
    }

    void accessInner(){
        InnerClass calledInside = new InnerClass("instantiated inside");
    }

    public static void main(String[] args) {
        // InnerClass creatingInner = new InnerClass("creating inner");

        ParentClass newParent = new ParentClass();
        newParent.new InnerClass("inner class is instantiated and run this way.");

        newParent.accessInner();
        
        OuterClass outer = new OuterClass("outer runs inside Parent's main.");
    }
}

class OuterClass{
    String name;

    OuterClass(String s){
        this.name = s;
        System.out.println(this.name);
    }
}
