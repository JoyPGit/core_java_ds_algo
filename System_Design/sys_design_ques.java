package System_Design;

import java.util.*;

public class sys_design_ques {
    // CTCI SYSTEM DESIGN
    // tic-tac-toe game
    // chess board
    // trie
    // minesweeper

    // https://leetcode.com/discuss/
    // interview-question/system-design/344524/design-a-jobtask-scheduler

    // https://leetcode.com/problems/online-stock-span/
    // https://leetcode.com/problems/implement-trie-prefix-tree/
    // https://leetcode.com/problems/lru-cache/
    // https://leetcode.com/discuss/interview-question/874074/
    // Food-Delivery-App-or-Low-Level-Design-or-Interview-Question

    // https://stackoverflow.com/questions/26094969/design-a-generic-job-scheduler

    // https://netflixtechblog.com/
    // https://netflixtechblog.com/byte-down-making-netflixs-data-infrastructure-cost-effective-fee7b3235032
    // http://highscalability.squarespace.com/blog/2017/12/11/netflix-what-happens-when-you-press-play.html

    // https://github.com/mission-peace/interview/blob/master/src/com/
    // interview/linklist/SortedLLToBalancedBST.java

    // https://www.geeksforgeeks.org/design-a-data-structure-that-supports-
    // insert-delete-search-and-getrandom-in-constant-time/

    /** 
     * LRU
     * IF NOT PRESENT, ADD AT FRONT
     * IF PRESENT, BRING TO FRONT
     * 
     * also update head and currLast in put
     * 1 a page class, like node of linked list
     * 2 a linked list with head and last;
     * 3 a counter to check 
     * if size < capacity, add a new page at end
     * else remove last and add new at front
    */
    class LRUCache {

        class Page{
            Page prev;
            Page next;
            int value;
            Page(int val){
                this.prev = null;
                this.next = null;
                this.value = val;
            }
        }

        Page head = null; 
        Page currLast = null;
        int counter = 0;

        LinkedList<Page> list;
        int cacheSize = 0;
        HashMap<Integer, Page> map;//
        public LRUCache(int capacity) {
            this.list = new LinkedList<>();
            this.cacheSize = capacity;
            this.map = new HashMap<>(capacity);
        }
        
        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            else{
                head.prev = map.get(key);
                head.prev.next = head;
                head = head.prev;
                return map.get(key).value;
            }
        }
        
        public void put(int key, int value) {
            if(counter<cacheSize) {//capacity
                currLast.next = new Page(value);
                currLast.next.prev = currLast;
                currLast = currLast.next;
                map.put(key, map.getOrDefault(key, currLast.next));
                this.counter++;
            }
            else{
                currLast = currLast.prev;
                currLast.next = new Page(value);
                currLast.next.prev = currLast;
                currLast = currLast.next; 
            }
        }
    }

    // https://leetcode.com/problems/design-circular-deque/

    // https://stackoverflow.com/questions/35196745/
    // check-t-generic-type-has-property-s-generic-in-c-sharp

     /**
         * Some research. What is an Integer? And why cannot we use int in an ArrayList?
         * An Integer is a reference type (a class). An int is a value. And: The
         * ArrayList requires, in its implementation, a reference type. So int is not
         * allowed.
         * 
         * Quote: The Integer class wraps a value of the primitive type int in an
         * object. An object of type Integer contains a single field whose type is int
         * (Java Documentation).
         */
}