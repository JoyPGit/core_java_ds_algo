import java.util.*;

// import org.graalvm.compiler.nodes.calc.LeftShiftNode;

class Array {

    void showArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    void findMountain(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1] && a[i] > a[i + 1]) {

            }
        }
    }

    void reArrange(int[] arr) {
        // int[] arr = new int[]{1,2,3,4,5,6,7,8};
        int mid = (arr.length / 2) - 1;
        int track = 2;

        for (int i = 1; i < arr.length / 2; i++) {
            while (arr[track] < arr[i]) {
                track++;
            }
            if (arr[i] % 2 == 0 && i + 1 > arr.length - 1) {
                swap(arr, i, track);
            }
        }
    }

    void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

    void mergekSortedArrays(int[] arr1, int[] arr2, int[] arr3) {
        int holder1, holder2, holder3 = 0;

        // max function needed for max of k elements
        // int smallest = max(arr1[holder1], arr2[holder2], arr3[holder3]);

        // can use a priority queue or a min heap

    }

    void findMissingAndRepeated(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            if (arr[i] == -1) {
                System.out.println("the repeated number is " + (i + 1));
                // break;
                System.out.println(arr[i]);

            } else
                arr[arr[i] - 1] = -1;
        }
    }

    void zeroOneSort(int[] arr) {
        int j = -1;
        int pivot = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j++;
                swap(arr, i, j);
            }
            // System.out.println("j "+ arr[j]);
        }

        swap(arr, j + 1, pivot);
    }

    void quickSortImplementation(int[] arr, int start, int end) {
        if (start < end) {

            int pivot = partition(arr, start, end);

            quickSortImplementation(arr, start, pivot - 1);
            quickSortImplementation(arr, pivot + 1, end);
        }
    }

    int partition(int[] arr, int start, int end) {
        int pivot = end;

        int j = start - 1;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[pivot]) {
                j++;
                swap(arr, j, i);
            }
        }
        j++;
        swap(arr, j, pivot);
        return pivot;
    }

    void sort0s1s2s(int[] arr) {
        int zeroTracker = 0;
        int oneTracker = 0;
        int twoTracker = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                zeroTracker = i;
            if (arr[i] == 1)
                zeroTracker = i;
            if (arr[i] == 2)
                zeroTracker = i;
        }
    }

    void insertionSort(int[] arr) {
        // int i =0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j >= 1 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    void findKthMax(int[] arr, int k) {
        int size = arr.length;
        buildHeap(arr, size);
        for (int i = 0; i < k; i++) {
            System.out.println(deleteTop(arr, size--));
        }
    }

    void buildHeap(int[] arr, int size) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
        System.out.println(" start" + arr[0]);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    void heapify(int[] arr, int i) {
        int max = i;
        if (2 * i + 1 < arr.length) {
            if (arr[max] > arr[2 * i + 1])
                max = 2 * i + 1;
        }
        if (2 * i + 2 < arr.length) {
            if (arr[max] > arr[2 * i + 2])
                max = 2 * i + 2;
        }
        if (max != i) {
            swapHeap(arr, i, max);
            heapify(arr, max);
        }
    }

    int deleteTop(int[] arr, int size) {
        int value = arr[0];
        arr[0] = arr[size - 1];
        heapify(arr, 0);
        return value;
    }

    void swapHeap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    void trappingRainWater(int[] arr) {
        int leftBoundary = 0;
        int rightBoundary = 0;
        int[] newArray = new int[arr.length];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1])
                leftBoundary = arr[i];
        }
        for (int i = arr.length - 2; i > 0; i--) {
            if (arr[i] > arr[i - 1])
                rightBoundary = arr[i];
        }

        for (int i = 1; i < arr.length; i++) {
            newArray[i] = leftBoundary - arr[i];
            if (arr[i] > leftBoundary)
                leftBoundary = arr[i];
        }
    }

    void moveAllZeroesToEnd(int[] arr) {
        int zeroCounter = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // zeroCounter++;
                swap(arr, i, ++zeroCounter);
            }
        }

        showArray(arr);
    }

    void sortOddAscEvenDesc(int[] arr) {
        // arr[] = {1, 2, 3, 5, 4, 7, 10}, Output : {7, 5, 3, 1, 2, 4, 10}
        /**first use partition to separate even and odd */
        int oddNumbers = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0)
                oddNumbers++;

        }

    }

    void sortByFrequency(int[] arr){
        int[] indexArr = new int[arr.length];
        quickSortArray24Apr(arr, 0, arr.length-1);

        indexCountMapper(arr, indexArr);
        



    }

    void indexCountMapper(int[] arr, int[] indexArr){
        int counter = 0; int prevValue=arr[0];
        for(int i =1; i<arr.length; i++){
            if(prevValue == arr[i]){
                prevValue = arr[i];
                counter++;
            } else{
                indexArr[i-1] = ++counter;
                prevValue = arr[i];
                counter = 0;
            }
        }
    }


    void quickSortArray24Apr(int[] arr, int low, int high) {
        if(low<high){
            int pivot = partition24Apr(arr, low, high);
            System.out.println("pivot "+pivot);
            // if(pivot!=-1){
                quickSortArray24Apr(arr, low, pivot - 1);
                quickSortArray24Apr(arr, pivot + 1, high);
            // }
            /**when it comes to single element of array, then the element violates
             * the condition of start<end and hence recursion stops
             */
        }
    }

    int partition24Apr(int[] arr, int start, int end) {
        // if (start < end) {
            System.out.print("low index "+start + ", value "+arr[start]+" ,");
            System.out.println("high index "+end+ ", value "+arr[end]);

            int i = start-1;
            int j = 0;
            int key = arr[end];

            for (j = start; j < end; j++) {
                if (arr[j] < key) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, end);//8,9 maintain position 
            return i + 1;
        // }
        // if(start == end) return start;
        // return -1;
    }

    void heapSort25Apr(int[] arr){
        int n = arr.length;
        int mid = arr.length/2-1;
        for(int i=mid; i>=0; i--){
            heapify25Apr(arr, arr.length, i);
        }

        showArray(arr);
        System.out.println();

        for(int i=n-1; i>n-2-1; i--){
            swap(arr, i, 0);
            // int temp =arr[0];
            // arr[0] = arr[i];
            // arr[i] = temp;

            heapify25Apr(arr, i, 0);
        }
    }

    void heapify25Apr(int[] arr, int n, int index){
        int max = index; int left; int right;

        if((2*index+1)<n){
            left = 2*index+1; 
            if(arr[max]>arr[left]) max = left;
        }
        if((2*index+2)<n){
            right = 2*index +2;
            if(arr[max]>arr[right]) max = right;
        } 

        if(max!=index) {
            swap(arr, index, max);
            heapify25Apr(arr, n, max);
        }
    }
    
    int findFirstOne(int[] arr, int start, int end) {
        if (end>=start) {
            int mid = start + (end - start) / 2;

            if(arr[mid]==1){
                if((mid-1)>=0 && arr[mid-1]==1) return findFirstOne(arr, start,mid-1);
                return mid;
            } 
            if(arr[mid]==0){
                return findFirstOne(arr, mid+1, end);
            }

            else {
                return findFirstOne(arr, start, mid-1);
            }
        }

        else return -1;
        
    }

    // return mid;
    
    void whileCheck(int[] arr){
        int i =0;
        while(i<2){
            System.out.println(i);
            i++;
        }
        zeroSetter(i); 
    }

    void zeroSetter(int i){
        i=0;
    }

    // boolean dead = false;
    // int count = 0;
    // void goDeeper(){
    //     if(dead == true) return;
    //     System.out.println(count++);//goes till 8612
    //     goDeeper();
    // }


    public static void main(String[] args) {
        // code

        Array test = new Array();
        int[] arr = new int[] { 1, 2, 5, 4, 3 };
        int[] arr2 = new int[] { 9, 7, 1, 3, 5, 6 };
        // test.findMissingAndRepeated(arr);

        // int[] arr = new int[]{1,0,1,0,0};
        // test.zeroOneSort(arr);
        // test.quickSortImplementation(arr, 0, arr.length-1);
        // System.out.println("-------------");
        // test.showArray(arr);

        // test.swap(arr, 0, 1);
        // test.showArray(arr2);

        // test.insertionSort(arr2);
        // System.out.println("---");
        // test.showArray(arr2);
        // test.findKthMax(arr2, 3);
        int[] zeroArr = { 1, 9, 8, 4, 2, 7};
        // test.moveAllZeroesToEnd(zeroArr);
        // test.quickSortArray24Apr(zeroArr, 0, zeroArr.length-1);
        // System.out.println();
        // test.showArray(zeroArr);

        // test.heapSort25Apr(zeroArr);
        // test.showArray(zeroArr);

        // int[] zeroOneArr = {0,0,0,0,0};
        // int[] zeroOneArr = {0,0,1,1,1};
        int[] zeroOneArr = {0,1,1,1,1,1};
        // int[] zeroOneArr = {1,1,1,1,1};
        // System.out.println( test.findFirstOne(zeroOneArr, 0, zeroOneArr.length-1));
        // test.whileCheck(zeroArr);

        String s ="abcd";
        // System.out.println(s.toCharArray()[0]);

    }

}