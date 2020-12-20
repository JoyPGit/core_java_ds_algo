package Core_Java.Inheritance;

public class Canine_subclass extends Animal_superClass{

    Canine_subclass(int a) {
        super(a);
        // TODO Auto-generated constructor stub
    }

    public void eat(){

    }

    protected void callallCanines(){
        System.out.println("all canines");
    }
    
}
