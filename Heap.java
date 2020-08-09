import java.util.*;

public class Heap {

    int[] heapArray;
    int lastIndex;
    private int lastIndexDelete = -1;
    private int largestSingle;

    Heap(int capacity) {
        heapArray = new int[capacity];
        this.lastIndex = capacity - 1;
    }

    void addToheap(int i) {// insert new key at end
        this.heapArray[lastIndex] = i;
        // System.out.println("1");
        heapify(lastIndex);
        printHeap();
        System.out.println("-------------");
    }

    void heapify(int childIndex) {
        // for(int)
        if (this.heapArray[lastIndex] > this.heapArray[((lastIndex + 1) / 2) - 1]) {
            System.out.println("size " + lastIndex + " " + this.heapArray[lastIndex]);
            System.out
                    .println("size/2 " + (((lastIndex + 1) / 2) - 1) + " " + this.heapArray[((lastIndex + 1) / 2) - 1]);
            int temp = this.heapArray[lastIndex];
            this.heapArray[lastIndex] = this.heapArray[((lastIndex + 1) / 2) - 1];
            this.heapArray[((lastIndex + 1) / 2) - 1] = temp;
        }

        if (childIndex / 2 == 0) {
            return;
        } else
            heapify(childIndex / 2);
    }

    void printHeap() {
        for (int i = 0; i < heapArray.length; i++) {
            System.out.println(this.heapArray[i]);
        }
    }

    void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == arr.length - 1 && j == arr[0].length - 1) {
                    System.out.println(arr[i] + ";");
                    System.out.println();
                } else
                    System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i] + ";");
                System.out.println();
            } else
                System.out.print(arr[i] + ", ");
        }
    }

    void maxHeapify(int nodeIndex) {
        // if(heapArray[nodeIndex] != null){
        if (nodeIndex < heapArray.length) {
            int leftChild = -1;
            int rightChild = -1;
            // for(int i = (heapArray.length)/2-1; i >0; i--){
            // System.out.println("heapArray.length/2-1 "+ (heapArray.length/2-1));
            System.out.println("heapArray[nodeIndex] " + heapArray[nodeIndex]);
            if (nodeIndex * 2 + 1 < heapArray.length) {
                leftChild = nodeIndex * 2 + 1;
            }
            if (nodeIndex * 2 + 1 < heapArray.length) {
                rightChild = nodeIndex * 2 + 2;
            }

            int largest;
            if (leftChild > 0 && rightChild > 0) {
                System.out.println("leftchild " + leftChild + "  " + heapArray[leftChild]);
                System.out.println("rightchild " + rightChild + "  " + heapArray[rightChild]);

                if (heapArray[nodeIndex] < heapArray[leftChild]) {
                    largest = leftChild;
                } else
                    largest = nodeIndex;
                if (heapArray[largest] < heapArray[rightChild]) {
                    largest = rightChild;
                }
                System.out.println("heapArray[largest] " + heapArray[largest]);
                if (largest != nodeIndex) {
                    System.out.println("largest " + largest);
                    System.out.println("nodeIndex " + nodeIndex);
                    int temp = heapArray[nodeIndex];
                    heapArray[nodeIndex] = heapArray[largest];
                    heapArray[largest] = temp;

                    System.out.println("swapped " + heapArray[nodeIndex] + "   " + heapArray[largest]);

                    maxHeapify(largest);

                } else {
                    System.out.println("no swap");
                }
            }

            // }
        } else
            return;

    }

    /**
     * one big mistake while using recursion was that the last indexes were also
     * used to calculate children which then went out of bounds. As the left and
     * right children were initialised to 0, the indexes were getting wrongly set.
     * So use -1 as initial value
     * 
     * also the variables need to be initialized outside of current scope.
     */

    void deleteFromHeap() {
        for (int i = 0; i < heapArray.length; i++) {
            if (heapArray[i] == 0) {

                System.out.println("the empty index is " + i);
                System.out.println("the last value is " + heapArray[i]);
                lastIndexDelete = i;

            }
        }

        if (lastIndexDelete == -1) {
            lastIndexDelete = heapArray.length - 1;
        }

        System.out.println("lastIndexDelete " + lastIndexDelete + " value is " + heapArray[lastIndexDelete]);
        heapArray[0] = heapArray[lastIndexDelete];
        heapArray[lastIndexDelete] = 0;

        printHeap();
        heapifySinglePath(0);
    }

    void heapifySinglePath(int index) {
        /** Always the heapify FLOATS DOWN */
        System.out.println("index " + index);
        largestSingle = index;
        if (index < heapArray.length) {

            if (((2 * index) + 1) < heapArray.length) {
                System.out.println("2*index+1 " + (2 * index) + 1);
                if (heapArray[largestSingle] < heapArray[(2 * index) + 1]) {
                    largestSingle = (2 * index) + 1;
                }
            }

            if (((2 * index) + 2) < heapArray.length) {
                System.out.println("2*index+2 " + (2 * index) + 2);
                if (heapArray[(2 * index) + 2] > heapArray[largestSingle]) {
                    largestSingle = 2 * index + 2;
                }
            }

            if (largestSingle != index) {
                int temp = heapArray[index];
                heapArray[index] = heapArray[largestSingle];
                heapArray[largestSingle] = temp;
                System.out.println("swapped " + heapArray[index] + "  " + heapArray[largestSingle]);
                heapifySinglePath(largestSingle);
            }

        }
    }

    /*
     * * provides O(log(n)) time for the enqueuing and dequeuing methods ({@code
     * offer}, {@code poll}, {@code remove()} and {@code add}); linear time for the
     * {@code remove(Object)} and {@code contains(Object)} methods; and constant
     * time for the retrieval methods ({@code peek} ->first el, {@code element}, and
     * {@code size}).
     * 
     * removeEq, removeAt and contains capacity grow automatically listiterator,
     * hasNext size()
     */
    // k-sorted array
    /**
     * 1 add comparator
     * 
     */
    int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Comparator.reverseOrder());

        heap.add(nums[0]);
        for (int i = 1; i < k; i++) {
            heap.add(nums[i]);
        }

        res[index++] = heap.peek();

        for (int i = k; i < n; i++) {
            heap.add(nums[i]);
            heap.remove(nums[i - k]);
            res[index++] = heap.peek();
        }

        print1DMatrix(res);
        return res;
    }

    // https://www.techiedelight.com/convert-min-heap-to-max-heap-linear-time/
    public static void main(String[] args) {
        Heap newHeap = new Heap(17);

        // newHeap.addToheap(3);
        // newHeap.addToheap(5);
        // newHeap.addToheap(7);
        // newHeap.addToheap(9);
        newHeap.heapArray[0] = 2;
        newHeap.heapArray[1] = 4;
        newHeap.heapArray[2] = 3;
        newHeap.heapArray[3] = 6;
        newHeap.heapArray[4] = 79;
        newHeap.heapArray[5] = 7;
        newHeap.heapArray[6] = 17;
        newHeap.heapArray[7] = 57;
        newHeap.heapArray[8] = 73;
        newHeap.heapArray[9] = 97;
        newHeap.heapArray[10] = 47;
        newHeap.heapArray[11] = 27;
        newHeap.heapArray[12] = 75;
        newHeap.heapArray[13] = 72;
        newHeap.heapArray[14] = 74;
        newHeap.heapArray[15] = 77;
        newHeap.heapArray[16] = 53;

        // newHeap.printHeap();

        for (int i = (newHeap.heapArray.length) / 2 - 1; i >= 0; i--) {
            // newHeap.maxHeapify(i);
        }
        // newHeap.maxHeapify(7);

        // newHeap.printHeap();

        // newHeap.deleteFromHeap();

        // newHeap.printHeap();

        int[] nums = 
        { 1, 3, 1, 2, 0, 5 };
        // {7, 2, 4};
        // {1,-1};
        // {1,3,-1,-3,5,3,6,7};
        int k = 3;// 2
        newHeap.maxSlidingWindow(nums, k);
    }
}

/**
 * errors : insert '}' to complete class body
 */