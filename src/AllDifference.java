import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.sort;
public class AllDifference {
    public static List<int[]> AllPairs(int[] arr) {
        int minDiff = Integer.MAX_VALUE;

        // Проход 1: находим minDiff
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int diff = Math.abs(arr[i] - arr[j]);
                if (diff < minDiff) {
                    minDiff = diff;
                }
            }
        }

        // Проход 2: собираем все пары с разностью == minDiff
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (Math.abs(arr[i] - arr[j]) == minDiff) {
                    result.add(new int[]{arr[i], arr[j]});
                }
            }
        }
        return result;
    }

    // Усложнение через сортировку: все пары с минимальной разностью
    public static List<int[]> AllPairsSort(int[] arr) {
        sort(arr);
        int minDiff = Integer.MAX_VALUE;

        // Проход 1: находим minDiff
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i+1] - arr[i];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        // Проход 2: собираем все пары с разностью == minDiff
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i+1] - arr[i] == minDiff) {
                result.add(new int[]{arr[i], arr[i+1]});
            }
        }
        return result;
    }

}
