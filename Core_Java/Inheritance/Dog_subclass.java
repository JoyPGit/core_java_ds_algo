package Core_Java.Inheritance;

public class Dog_subclass extends Animal_superClass implements Interface_pet {
//extends Canine_subclass {
    int age;
    Dog_subclass() {
        super(1);
        this.age = 10;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void beFriendly() {
        // TODO Auto-generated method stub
        System.out.println("be friendly");
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub
        System.out.println("be playful");

    }
    
    // @Override
    public void eat(String str){
        // super.eat();
        System.out.println("dog eating "+str);
    }

    // @Override
    public void roam(String str){
        // super.eat();
        System.out.println("dog roaming "+str);
    }

    public static void main(String[] args) {
        Dog_subclass d = new Dog_subclass();
        System.out.println("static var in interface : "+d.petType);
        d.eat("meat");
    }
    
}


class Animal_superClass {
    int superVar; 
    Animal_superClass(int a){
        this.superVar = a;
    }

    public void eat(){
        System.out.println("eating in animal super");
    }

    public void roam(){
        System.out.println("roaming super");
    }
}

interface Interface_pet {
    static String petType = "DOGGO";
    abstract void beFriendly();
    abstract void play();
}