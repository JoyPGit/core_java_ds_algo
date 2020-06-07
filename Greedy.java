import java.util.*;

class Greedy{

    void MiceHole(int[] mice, int[] holes){
        Arrays.sort(mice);
        Arrays.sort(holes);

        int difference = -1;
        /**trick is you can use 2 conditions in the middle statement of the for loop */
        for(int i =0; i< mice.length; i++){
            if(Math.abs(mice[i]-holes[i]) > difference) difference = Math.abs(mice[i]-holes[i]); 
        }

        System.out.println("the max time is  "+ difference);
    }

    void minSumFrom2Arrays(int[] arr1, int[] arr2){
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int length = Math.max(arr1.length, arr2.length);

        for(int i =length; i>0; i--){
            int sum = 0;
            int j =i;
            // if(j>0){
            //     sum = arr1[]
            // }
        }
    }

    // void policeCatchThief(int[] arr){
    //     for(int i =)
    // }
    
    /**float vs double
     * https://study.com/academy/lesson/java-floating-point-numbers.html
     * float q = 506.12789f;
        Here the variable q saves the value 506.1247 as a float.

        float r = -101.23;
     */
    class Holder{
        String arrOrDep;
        float time;

        Holder(String type, float time){
            this.arrOrDep = type;
            this.time = time;
        }
    }
    void trainPlatform(double[] arrival, double[] departure){
        Arrays.sort(arrival);
        Arrays.sort(departure);

        Holder newArray = new Holder[(arrival.length*2)];
        double arrivalStart = 0; double departureStart = 0; 

        Holder newholder;
        /**
         * error: unclosed character literal
         * Strings in Java need to be enclosed in double quotes. Use "hello"
         */

        /**use arraylist 
         * https://stackoverflow.com/questions/18578864/double-array-initialization-in-java/47036616 
         * */
        while(arrivalStart<arrival.length){
            if(arrival[arrivalStart]<departure[departureStart]){
                newholder = new Holder("arrival",arrival[arrivalStart]);
                newArray.push(new Holder("arrival",arrival[arrivalStart]));
                arrivalStart++;
            } else {
                newArray.push(new Holder("departure",departure[departureStart]));
                departureStart++;
            }
        }

        while(departureStart<departure.length){
            newArray.push(new Holder("departure",departure[departureStart]));
            departureStart++;
        }

        // Arrays.sort(newArray);

        int platformCount = 0;
        for(int i =0; i<newArray.length; i++){
            if((newArray[i]).arrOrDep == "arrival") platformCount++;
            else platformCount--;
        }

        System.out.println("the platforms required are "+ platformCount);
    }

    public static void main(String[] args) {
        Greedy solGreedy = new Greedy();
        int[] mice = new int[]{-10, -79, -79, 67, 93, -85, -28, -94 };
        int[] holes = new int[]{-2, 9, 69, 25, -31, 23, 50, 78 };

        // solGreedy.MiceHole(mice, holes);

        double arrivalTimes = new double[]{9,9.40,9.50,11,15,18};
        double departureTimes = new double[]{9.10,12,11.20,11.30,19,20};

        solGreedy.trainPlatform(arrivalTimes, departureTimes);
    }
}