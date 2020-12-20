package Core_Java.Inheritance;

public class Dog_subclass extends Animal_superClass
//extends Canine_subclass 
{
    int age;
    Dog_subclass() {
        super(1);
        this.age = 10;
        // TODO Auto-generated constructor stub
    }

    // @Override
    public void beFriendly() {
        // TODO Auto-generated method stub
        System.out.println("be friendly");
    }

    // @Override
    public void play() {
        // TODO Auto-generated method stub
        System.out.println("be playful");

    }
    
    public void eat(String str){
        // super.eat();
        System.out.println("dog eating "+str);
    }

    public static void main(String[] args) {
        Dog_subclass d = new Dog_subclass();
        d.eat("meat");
    }
    
}
