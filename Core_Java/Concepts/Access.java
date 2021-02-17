public class Access {
    static{
        System.out.println("------------>");
        System.out.println("static block runs without instantiation also");
        System.out.println("in static block");
        statFunc(5);
    }
    static void statFunc(int n){
        System.out.println("in static method");
    }
    Access(){
        System.out.println("in constructor");
    }

    public static void main(String[] args) {
        
    }
}
