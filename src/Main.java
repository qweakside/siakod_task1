import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        int[] arr = Difference.Array_lol();

        int[] resultPair = Difference.Pair(arr.clone());
        System.out.println("Перебор — минимальная пара: " + resultPair[0] + " " + resultPair[1]);

        int[] resultSort = Difference.PairSort(arr.clone());
        System.out.println("Сортировка — минимальная пара: " + resultSort[0] + " " + resultSort[1]);

        List<int[]> allBrute = AllDifference.AllPairs(arr.clone());
        System.out.println("Все пары (перебор):");
        for (int[] pair : allBrute) {
            System.out.println(pair[0] + " " + pair[1]);
        }

        List<int[]> allSorted = AllDifference.AllPairsSort(arr.clone());
        System.out.println("Все пары (сортировка):");
        for (int[] pair : allSorted) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}