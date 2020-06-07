import java.util.*;

/**
 * class Node{ int key; Node next;
 * 
 * Node(int key){ this.key = key; this.next = null; } }
 * 
 * @param <K>
 * @param <V>
 */
class HashNode<K, V> {
    K key;
    V value;

    HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

}

class Map<K, V> {
    // private ArrayList<HashNode<K, V>> bucketArray;
    private LinkedList<HashNode<K, V>> bucketList;
    private int numBuckets;
    private int size;

    Map(){
        // this.bucketArray = new ArrayList<>();
        this.bucketList = new LinkedList<>();
        this.numBuckets = 10;//this is the default size for finding modulo value
        this.size = 0;

        for(int i =0; i < numBuckets; i++){
            bucketList.add(null);
        }
    }

    int getBucketIndex(K key){ 
        int hashCode = key.hashCode(); 
        int index = hashCode % numBuckets; 
        return index; 
    } 


    void add(K key, V value){
        int bucketIndex = getBucketIndex(key);

        // HashNode<K, V> tracker = bucketArray.get(bucketIndex);
        HashNode<K, V> tracker = bucketList.get(bucketIndex);

        //checking if the key exists
        while(tracker!=null){
            if(tracker.key.equals(key)){
                tracker.value = value;
            }
            // tracker = tracker.next;
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////

class Hash{


}