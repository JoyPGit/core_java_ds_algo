public class lambda {
    /** 
     * Functional Interface
     * Lambda expression provides implementation of functional interface. 
     * An interface which has only one abstract method is called functional interface. 
     * Java provides an anotation @FunctionalInterface, which is used to declare an interface as 
     * functional interface.
     * 
    */

    // without lambda
    interface Drawable{  
        public void draw();  
    }  
    public static void main(String[] args) {  
        int width=10;  
    
        //without lambda, Drawable implementation using anonymous class  
        Drawable d1 =new Drawable(){  
            public void draw(){System.out.println("Drawing "+width);}  
        };  
        d.draw();  

        // with lambda
        Drawable d2 = ()->{System.out.println("Drawing with lambda "+width);};
        d2.draw();

    }  

    
}
