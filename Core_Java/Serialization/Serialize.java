
import java.io.*;

public class Serialize {
    public static void main(String[] args) throws Exception {
        
    
    /** 
     * https://java2blog.com/java-serialization-interview-questions-and-answers/
     * 
     * Serialization and De-serialization :
     * serialization is the conversion of a Java object into a static stream (sequence) of bytes 
     * which can then be saved to a database or transferred over a network.
     * 
     * The serialization process is instance-independent, i.e. objects can be 
     * serialized on one platform and deserialized on another. 
     * 
     * Classes that are eligible for serialization need to implement a special marker interface Serializable.
     * Serializable is a marker interface (no methods, no body)
     * 
     * When a class implements the java.io.Serializable interface, all its sub-classes are 
     * serializable as well. On the contrary, when an object has a reference to another object, 
     * these objects must implement the Serializable interface separately, 
     * or else a NotSerializableException will be thrown:
     * 
     * If a serializable class doesn't declare a serialVersionUID, 
     * the JVM will generate one automatically at run-time.
     * 
     * For handling file always use a try_catch block
     * and add throws Exception
     * 
     * 
     * Can you Serialize static variables?
     * As you know static variable are at class level not at object level and 
     * you serialize a object so you can’t serialize static variables.
     * 
     * 
     * 
     * What if superclass is Serializable?  Does that mean child class is automatically Serializable?
     * Answer : Yes
     * 
     * Question 9: What if superclass is Serializable but you don’t want subclass to be Serializable?
     * Answer : If you don’t want subclass to serializable then you need to implement 
     * writeObject() and readObject() method and need to throw NotSerializableException from this methods.
     * 
     * Question 10 :What is externalizable interface?
     * Answer: As name suggests it is externalizing your serialization. If you want to customize your 
     * serialization mechanism then you can use it. It uses custom written mechanism to 
     * perform marshalling and unmarshalling of objects.
     * Externalizable interface extends Serializable interface. 
     * 
     * If you implement this interface then you need to override following methods :
     * readExternal, writeExternal
     * 
     * imp : Serializable is a Marker interface no implementation of any method. Externalizable is not.
     * 
     * 
     * Spring boot will do the following step when receives a http request.
     * 
     * 1.Read json http requestbody First
     * 2.Then deserialized it to @RequestBody object.
     * 
     * // Json Data Mapper
     * ObjectMapper mapper = new ObjectMapper();
     * mapper.writeValueAsString(request)
     * 
    */
    ObjSave save1 = new ObjSave(3);

        try {
            File f = new File("serialize_Obj.class");
            // for writing data to a file
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // writes specifed obj to output stream
            oos.writeObject(save1);
            oos.flush();
            oos.close();
        
            // same file reference
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ios = new ObjectInputStream(fis);
            ObjSave deserializedObj = (ObjSave)(ios.readObject());
            ios.close();

            System.out.println("deserialized obj int i "+deserializedObj.i);
            System.out.println("deserialized obj secret "+deserializedObj.secret);
            // as transient was used, default value is returned
            System.out.println(save1.equals(deserializedObj));
            // after implementing equals, both will be equal
    
        } catch (Exception e) {
            
            //TODO: handle exception
            e.printStackTrace();

            throw new Exception();
        }
    }
}

// java.io.NotSerializableException: ObjSave
// java.io.eofexception
class ObjSave implements Serializable{
    
    // The serialization process at runtime associates an id with each Serializable class 
    // which is known as SerialVersionUID

    private static final long serialVersionUID = 1L;
    int i;
    // transient won't be serialized with its value, it will hold feault value
    transient int secret;
    ObjSave(int i){
        this.i = i;
    }
}