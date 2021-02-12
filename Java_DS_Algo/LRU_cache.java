import java.time.LocalDateTime;
import java.util.*;

class LRUCache {
    class Page{
        int key, value, index;
        LocalDateTime last_used;
    }
    HashMap<Integer, Page> map;
    ArrayList<Page> list;
    int size;

    //try by storing index
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.size = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            list.add(map.get(key), 0);
            list.remove(map.get(key).index);
            map.put(key, list.get(0));
            return list.get(0).value;
        }
        else return -1;
    }

    // how to update entry in map
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(map.size() == this.size){
                map.remove()
                list.remove(list.size()-1);
                list.addLast(value);
                map.put(key, list.size()-1);
            }
            list.addLast(value);
            map.put(key, list.size()-1);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */