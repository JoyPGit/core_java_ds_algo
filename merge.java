import java.util.*;

public class merge {
    int[] a1 = new int[] { 1, 5, 6, 8, 9 };
    int[] a2 = new int[] { 2, 3, 7, 10 };
    int[] newArr;

    // void mergeArr(int[] arr1, int[]arr2){
    // // int length = arr1.length< arr2.length?arr2.length:arr1.length;

    // newArr = new int[arr1.length+arr2.length];
    // int a11 =0; int a22 =0;
    // for(int i =0;i<9;i++){
    // System.out.println("a11 "+a11);System.out.println(" a22 "+a22);
    // if(a1[a11]<a2[a22]){
    // newArr[i]=a1[a11];
    // a11++;
    // } else {
    // newArr[i] = a2[a22];
    // a22++;
    // }
    // System.out.println("newArr "+newArr[i]);
    // }
    // }

    void merge(int[] arr, int l, int m, int r) {
        // all work is done on the arr passed as param
        int n1 = m - l + 1; // middle is always on the lower side
        int n2 = r - m;

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            arr1[i] = arr[l + i];
        }
        for (int i = 0; i < n2; i++) {
            arr2[i] = arr[m + i]; // m+i+1
        }
        
        int[] arr3 = new int[n1 + n2];
        int t1 = 0, t2 = 0;
        // for(int i = 0; i< k; i++){
        // if(arr1[t1]<arr2[t2]){
        // arr3[i] = arr1[t1];
        // t1++;
        // } else {
        // arr3[i] = arr2[t2];
        // t2++;
        // }
        // }
        int k = l;
        
        while (t1 < n1 && t2 < n2) {
            if (arr1[t1] < arr2[t2]) {
                arr3[t1] = arr1[t1];
                t1++;
            } else {
                arr3[t2] = arr2[t2];
                t2++;
            }
            k++;
        }

        while (t1 < k) {
            arr[k] = arr1[t1];
            t1++;
            k++;
        }
        while (t2 < k) {
            arr[k] = arr2[t2];
            t2++;
            k++;
        }
    }

    void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int middle = (l + r) / 2;
            mergeSort(arr, l, middle);
            mergeSort(arr, middle + 1, r);

            merge(arr, l, middle, r);
        }
    }

    void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(" " + arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        merge mergeObject = new merge();
        // newClass.mergeArr(newClass.a1,newClass.a2);
        // for(int i =0; i<newClass.newArr.length;i++){
        // System.out.println(newClass.newArr[i]);
        // }

        int arr[] = new int[] { 3, 5, 6, 7, 8, 9, 1, 22 };
        mergeObject.printArray(arr);

        mergeObject.mergeSort(arr, 0, arr.length - 1);
        mergeObject.printArray(arr);

    }
}