public class Anonymous_class {
    /**
     * Java Anonymous inner class can be created by two ways:
     * Class (may be abstract or concrete). 
     * Interface
     * 
     * for implementing anonymous class, make sure to provide implementation during instantiation
     * hence lambda helps ()->{};
     */

    abstract class Person {
        abstract void eat();
    }

    interface Eatable {
        void eat();
    }

    public static void main(String args[]) {
        // using classs
        Person p = new Person() {
            void eat() {
                System.out.println("nice fruits");
            }
        };
        p.eat();

        // using interface
        Eatable e = new Eatable() {
            public void eat() {
                System.out.println("nice fruits");
            }
        };
        e.eat();

        // using lambda
        Eatable el = ()->{System.out.println("nice fruits");};
        el.eat();
    }
}
