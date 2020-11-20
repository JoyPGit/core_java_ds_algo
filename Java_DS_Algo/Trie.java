package Java_DS_Algo;

class TrieNode{
    boolean isWord;
    TrieNode[] holder = new TrieNode[26];//IMP
    TrieNode(){}
}
public class Trie {

    /** 
     * SIMILAR TO LINKED LIST, CREATE A NODE CLASS INSIDE THE LIST CLASS
     * 
     * TrieNode[] holder = new TrieNode[26];//IMP
     * 
     * POINTS : 
     * 1 NODE IS DEFINED BY 2 THINGS isEnd AND ARRAY OF NODES
     * 2 FOR SEARCHING AND INSERTING ALWAYS USE A POINTER p TO TRAVERSE
     * 3 GO TO THE INDEX AND CHECK IF NULL
     * 4 TRAVERSAL IS SIMILAR TO LINKEDLIST
     *  p = p.holder[index]; p = p.next
     * 
     * 5 IN SEARCH RETURN p.isEnd, AND IF NULL IN B/W RETURN FALSE;
     * 
     * no char is used, only index specified by ch-'a'
     * 
     * */
    // https://leetcode.com/problems/implement-trie-prefix-tree/
    class TrieNode{
        TrieNode[] letters;
        boolean isEnd;    
        
        TrieNode(){ // 
            this.letters = new TrieNode[26];
            // letters[ch-'a'] = ;
            this.isEnd = false;
        }
    }
    
    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = root;
        for(int i =0; i<word.length(); i++){
            if(p.letters[word.charAt(i)-'a'] == null){ // 
                p.letters[word.charAt(i)-'a'] = new TrieNode();
            }
            p = p.letters[word.charAt(i)-'a'];
        }
        p.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        for(int i =0; i<word.length(); i++){
            if(p.letters[word.charAt(i)-'a'] == null)return false;
            p = p.letters[word.charAt(i)-'a'];
        }
        return p.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for(int i =0; i<prefix.length(); i++){
            if(p.letters[prefix.charAt(i)-'a'] == null)return false;
            p = p.letters[prefix.charAt(i)-'a'];
        }
        return true;
    }
    // https://leetcode.com/problems/stream-of-characters/ 
    // https://leetcode.com/problems/implement-magic-dictionary/
    // https://leetcode.com/problems/replace-words/
    // https://leetcode.com/problems/design-add-and-search-words-data-structure/

}