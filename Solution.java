import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//         int newLength = nums1.length + nums2.length;
//         int newArray = new int[newLength];
        
//         int length = nums1.length>num2.length?nums1.length:nums2.length;
        
//         for(int i =0; i< newLength; i++){
//             if(nums1[i]<nums2[i]){
//                 newArray[i] = nums1[i];
//             } else newArray[i] = nums2[i] 
//         }
        
        
        int median = (findMiddle(nums1)+findMiddle(nums2))/2;
        System.out.println("the median is "+median);
        return median;
    }
    
    int findMiddle(int[] arr){
        int middle=0;
            if(arr.length%2==0){
                int half = (arr.length)/2;
                middle = (arr[half-1]+arr[half])/2;
                System.out.println("middle1 "+(arr[half-1]+arr[half])/2);
            } else if(arr.length%2==1){
                int halfOdd = (arr.length+1)/2;
                middle = arr[halfOdd];
            }
        System.out.println("middle "+middle);
        return middle;
        }


        //PATTERN MATCHING
        ArrayList <Integer> indexList = new ArrayList<Integer>();   
        public void isMatch(String s, String p) {
        }

        public void findIndexes( String s, String p){
            // int[] index = new int[s.length()];
           

            for (int i =0; i< p.length(); i++){
                System.out.println(p.charAt(i));
                String star = "*";
                if(p.charAt(i) != star.charAt(0)){
                    for(int j =0; j<s.length(); j++){
                        if(p.charAt(i)==s.charAt(j)){
                            indexList.add(j);
                        }
                    }
                }
            }
        }

        public void match(String s, String p){
            String star = "*";
            for(int i =0; i< p.length(); i++){
                if(p.charAt(i) != star.charAt(0)){
                    for(int j =0; j<indexList.size(); j++){
                        if(p.charAt(i)==s.charAt(j)){
                            indexList.add(j);
                        }
                    }
                } 
            }
        }
        


        public void findPaths(int[][] arr, int i, int j){
            int count=0;
            int m = 3;//arr.length;
            int n = 3;//arr[0].length;
            if(i+1<m && j+1<n){
                System.out.println("i "+i+"; j "+j);
                if(arr[i][j]==arr[m-1][n-1]){
                    System.out.println("last index "+arr[m-1][n-1]);
                    count++;
                }
                
                if(arr[i][j+1]!=arr[m-1][n-1]){
                    findPaths(arr, i,j+1);
                    System.out.println("j+1");
                }
                
                if(arr[i+1][j]!=arr[m-1][n-1]){
                    findPaths(arr, i,j+1);
                    System.out.println("i+1");
                }

                System.out.println("count "+ count);
            }
            
		}

        
    public static void main(String[] args){
        Solution answer = new Solution();
        int[] arr1 ={1,2}; int[] arr2 ={3,4};
        // answer.findMedianSortedArrays(arr1, arr2);

        String s1 = "baaabab";
        String s2 = "*****ba***ab";
        
        // answer.isMatch(s1, s2);
        int[][] arr3 = {{1,2,3},{4,5,6},{7,8,9}};
        // int[][] twoDimArr = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};//new int[2][2];
        answer.findPaths(arr3, 0, 0);
    }
}