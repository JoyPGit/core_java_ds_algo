package Java_DS_Algo;

class TrieNode{
    boolean isWord;
    TrieNode[] holder = new TrieNode[26];//IMP
    TrieNode(){}
}
public class Trie {
    // https://leetcode.com/problems/implement-trie-prefix-tree/

    /** 
     * SIMILAR TO LINKED LIST, CREATE A NODE CLASS INSIDE THE LIST CLASS
     * 
     * TrieNode[] holder = new TrieNode[26];//IMP
     * 
     * POINTS : 
     * 1 NODE IS DEFINED BY 2 THINGS isEnd AND ARRAY OF NODES
     * 2 FOR SEARCHING AND INSERTING ALWAYS USE A POINTER P TO TRAVERSE
     * 3 GO TO THE INDEX AND CHECK IF NULL
     * 4. TRAVERSAL IS SIMILAR TO LINKEDLIST
     *  p = p.holder[index]; p = p.next
     * 
     * 5 IN SEARCH RETURN p.isEnd, AND IF NULL IN B/W RETURN FALSE;
     * 
     * */
    TrieNode root = new TrieNode();

    void insert(String word){
        TrieNode p = root;
        for(int i =0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(p.holder[index] == null) p.holder[index] = new TrieNode();
            p = p.holder[index];
        }
        p.isWord = true;
    }

    boolean search(String word){
        TrieNode p = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(p.holder[index] == null) return false;
            p = p.holder[index];
        }
        return p.isWord;
    }

    boolean prefix(String prefix){
        TrieNode p = root;
        for(char c : prefix.toCharArray()){
            int index = c-'a';
            if(p.holder[index] == null) return false;
            p = p.holder[index];
        }
        return true;
    }
    // https://leetcode.com/problems/stream-of-characters/ 
    // https://leetcode.com/problems/implement-magic-dictionary/
    // https://leetcode.com/problems/replace-words/
    // https://leetcode.com/problems/design-add-and-search-words-data-structure/

}