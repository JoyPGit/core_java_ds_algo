import java.time.LocalDateTime;
import java.util.*;

class LRUCache {
    class Page{
        int key, val;
        Page(int k, int v){
            this.key = k;
            this.val = v;
        }
    }
    
    int size, limit;
    LinkedList<Page> list;
    HashMap<Integer, Page> map;
    
    public LRUCache(int capacity) {
        this.limit = capacity;
        this.list = new LinkedList<>();
        this.map = new HashMap<>();
    }
    
    // get counts as a ref, move to front; map entry need not change
    public int get(int key) {
        System.out.print("size "+size+", "+key+", ");
        System.out.println(map);
        if(map.containsKey(key)){
            this.list.remove(map.get(key));
            this.list.addFirst(map.get(key));
            return map.get(key).val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        System.out.println("size "+size);
        if(size == limit){
            Page curr = list.remove(list.size()-1);
            map.remove(curr.key);
            size--;
            // return;
        }
        Page p = new Page(key, value);
        list.addFirst(p);
        map.put(key, p);
        size++;
        System.out.println("put , "+map);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */