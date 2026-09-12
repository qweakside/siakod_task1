import java.util.Scanner;
import static java.util.Arrays.sort;
public class Difference {
    public static int[] PairSort(){
        int []minPair = new int[2];
        int[]arr = Array();
            sort(arr);
        int minDiff=Integer.MAX_VALUE;
        for(int i =0;i<arr.length-2;i++){
            if (minDiff>Math.abs(arr[i]-arr[i+1])){
                minDiff = Math.abs(arr[i] - arr[i+1]);
                minPair[0] = arr[i];
                minPair[1] = arr[i+1];
            }
        }
        return minPair;
    }
    public static int[] Pair(){
        int []arr=Array();
        int []minPair = new int[2];
        int minDiff=Integer.MAX_VALUE;
        for (int i =0;i< arr.length;i++){
            for (int j=i+1;j< arr.length;j++){
                if (minDiff>Math.abs(arr[i]-arr[j])) {
                    minDiff = Math.abs(arr[i] - arr[j]);
                    minPair[0] = arr[i];
                    minPair[1] = arr[j];
                }
            }
        }
        return minPair;
    }
    private static int[] Array(){
        Scanner scan = new Scanner(System.in);
        System.out.println("введите размер массива");
        int size = scan.nextInt();
        int[] array = new int[size];
        System.out.println("вводите элементы");
        for (int i = 0; i< size; i++){
            int n = scan.nextInt();
            array[i]=n;
        }
        return array;
    }
}
