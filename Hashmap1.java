import java.util.*;

//how hashmaps work https://www.youtube.com/watch?v=c3RVW3KGIIE

/** hash stores key value pairs by adding an extra hash value for the key which is used to compute the index
 * key->hash->value
 * the value returned with get is the value of the key
 * 
 * HASHSET FOR SINGLE VALUE., add method , HASMAP FOR KEY VALUE PAIR put method
 * */


 /** important things
  * 1. diff b/w hashmap and hashset
  * 2. how to iterate over a hashmap line 58 similar to tree  

    https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/

        for (CustomClassForSwap element : tree.queueSwapLeafNew) {
            System.out.println(element.node.key);
        }
   */
public class Hashmap1{

    void findSubArrayWithSumZero(int[] arr){
        int sum = 0;

        for(int i =0; i<arr.length; i++){
            sum+= arr[i];

        }
    }

    boolean CheckSubsetArray(int[] arr1, int[] arr2){
        HashSet<Integer> hset= new HashSet<Integer>();
        // int[] longer = arr1.length>arr2.length?arr1:arr2;

        for(int i =0; i<arr1.length; i++){
            hset.add(arr1[i]);
        }
        System.out.println("hset "+hset);
        for (int i=0; i<arr2.length; i++){
            if(!hset.contains(arr2[i])){
                return false;
            }
        }
        return true;
    }

    int minSubsets(int[] arr){
        HashMap<Integer, Integer> minsubmap = new HashMap<Integer, Integer>();

        for(int i =0; i<arr.length; i++){
            minsubmap.put(arr[i],minsubmap.get(arr[i]) == null?1:minsubmap.get(arr[i])+1);
        }


        Iterator hmIterator = minsubmap.entrySet().iterator(); 

        int maxFreq = 0;
        while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            int frequency = (int)mapElement.getValue();
            if(frequency > maxFreq) maxFreq = frequency; 
            System.out.println(mapElement.getKey() + " : " + frequency); 
        } 

        // for (HashMap.Entry mapElement : minsubmap.entrySet()) { 
        //     String key = (String)mapElement.getKey();   
        //     int value = (int)mapElement.getValue(); 
  
        //     System.out.println(key + " : " + value); 
        // } 
        minsubmap.forEach((k, v) -> System.out.println(k + " : frquency ->" + (v)));

        return maxFreq;
    }

    int SmallestElementRepeatedKTimes(int[] arr, int times){
        HashMap<Integer, Integer> repeatkmap = new HashMap<Integer, Integer>();

        for(int i =0; i<arr.length; i++){
            repeatkmap.put(arr[i], repeatkmap.get(arr[i])==null?1:repeatkmap.get(arr[i])+1);
        }

        Iterator hmIterator = repeatkmap.entrySet().iterator(); 

        int maxFreq = 0; int smallest =100000000;
        while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            int frequency = (int)mapElement.getValue();
            int key = (int)mapElement.getKey();
            if(frequency == times && key <smallest ) {
                maxFreq = frequency; 
                smallest = key;
            }
            System.out.println(mapElement.getKey() + " : " + frequency); 
        } 
        return smallest;
        // int[] smallest = {10000000}; 
        // repeatkmap.forEach((k,v)->{
        //     System.out.println("v "+v);
        //     if(times == v && smallest[0] < k){
        //        smallest[0] = k;
        //        System.out.println("samller "+k);
        //     }
        // });
        // System.out.println("smallest "+smallest[0]);
        // return smallest[0];
    }

    void MaxDiffFirstAndLast(int[] arr){
        
        // for(int i =0; i<arr.length)
    }


    public static void main(String[] args) {
        Hashmap1 h = new Hashmap1();
        HashMap<Integer, String> map = new HashMap<>();

        // map.put("12",1);
        // map.put("12",13);
        // map.put("123",1);
        // map.put("124",15);
        // map.put("12",14);
        // map.put("12",5);

        // System.out.println(map.get("12"));

        String arr[] = {"swagat","adarsh","vasil","shivani","hanish","manendra"};

        for(int i=0; i<arr.length; i++){
            map.put(i,arr[i]);
        }

        System.out.println(map.get(0));
        for(int i =0; i<arr.length; i++){
            // System.out.println(map.get(i));//the values of the map
        }

        //instance 
        // HashMap<Integer, Integer> h = new Hashmap<Integer, Integer>();


        int[] arr1 = {3,4,5,6};
        int[] arr2 = {1,2};
        boolean val = h.CheckSubsetArray(arr1, arr2);
        if(val){
            System.out.println("subset true");
        }

        int[] arr3 = {2,2,3,4,5,6,6,6,6,7,7,8,8,8};
        System.out.println(h.minSubsets(arr3));


        System.out.println("smallest k times is "+h.SmallestElementRepeatedKTimes(arr3, 2));

        
    }
}