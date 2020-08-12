import java.util.*;

class nodeSort{
    int key;
    nodeSort next;

    nodeSort(int data){
        this.key = data;
        this.next = null;
    }
}


class linkedListSort{
    nodeSort head;

    linkedListSort(){
        
    }
}

public class Sorting {
    
    Sorting(){}

    void heapSort (int[] arr){
        int i =0;
        for(i=arr.length/2;i>=0;i--){
            heapify(arr, i);
        }
    }

    void heapify(int[] arr, int index){
        int min= index;
        if(index*2+1<arr.length && arr[index]>arr[index*2+1]) min = index*2+1;
        if(index*2+2<arr.length && arr[min]>arr[index*2+2]) min = index*2+2;

        if(min!=index) {
            swap(arr, index, min);
            heapify(arr, min);
        }
    }

    void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    void mergeSort(int[] arr){

    }

    void mergeSortLinkedList(nodeSort node){
        nodeSort head = node;
        nodeSort mid = findMidLinkedList(head);

        // mergeSortLinkedListHelper(head, mid.next);
    }

    nodeSort findMidLinkedList(nodeSort head){
        nodeSort fast = head;
        nodeSort slow = head;

        while(fast.next!=null){
            fast = fast.next;
            if(fast.next!=null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;    
    }

    // void mergeSortLinkedListHelper(nodeSort node){
    //     while(node1.next!=null ){
    //         if(node1.key<node2.key){
    //             nodeSort holder = node1.next;
    //             node1.next 
    //         }
    //     }
    // }

    void quickSort(int[] arr){

    }

    void quickSelect(int[] arr){
        
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] res = new int[n];
        int mid = (n-1)/2; int right = n-1; int count = 0;
        
        while(mid>=0 || right>(n-1)/2) {
            if(count%2 == 0){
                res[count]=nums[mid--];
            }
            if(count%2 != 0){
                res[count]=nums[right--];
            }
            count++;
        }
        
        for(int i =0; i<n; i++){
            nums[i] = res[i];
        }
    }

    public static void main(String[] args){
        Sorting coronaSort = new Sorting();
        int[] arr = {10,3,5,6,8,1,4,6,7,9,21,43};
        coronaSort.heapSort(arr);
        for(int i =0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}