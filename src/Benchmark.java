import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {

    private static int[] generateRandomArray(int size, int bound, long seed) {
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(bound);
        }
        return arr;
    }

    // среднее время по нескольким прогонам, с warm-up
    private interface Task { void run(int[] arr); }

    private static double measure(Task task, int[] baseArr, int repeats) {
        // warm-up
        for (int w = 0; w < 3; w++) task.run(baseArr.clone());

        long total = 0;
        for (int r = 0; r < repeats; r++) {
            int[] copy = baseArr.clone();
            long start = System.nanoTime();
            task.run(copy);
            long end = System.nanoTime();
            total += (end - start);
        }
        return (total / (double) repeats) / 1_000_000.0; // мс
    }

    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 500, 1000, 2000, 5000, 8000, 10000, 15000, 20000};
        int repeats = 5;

        // Сценарий 1: широкий диапазон значений (мало повторов)
        FileWriter fw1 = new FileWriter("/home/claude/bench/results_random.csv");
        fw1.write("size,Pair_ms,PairSort_ms,AllPairs_ms,AllPairsSort_ms\n");

        // Сценарий 2: узкий диапазон значений (много повторов, minDiff=0 почти всегда)
        FileWriter fw2 = new FileWriter("/home/claude/bench/results_repeats.csv");
        fw2.write("size,Pair_ms,PairSort_ms,AllPairs_ms,AllPairsSort_ms,k_pairs\n");

        for (int size : sizes) {
            // --- широкий диапазон ---
            int[] arrRandom = generateRandomArray(size, 1_000_000, 42);

            double tPair = measure(Difference::Pair, arrRandom, repeats);
            double tPairSort = measure(Difference::PairSort, arrRandom, repeats);
            double tAllPairs = measure(AllDifference::AllPairs, arrRandom, repeats);
            double tAllPairsSort = measure(AllDifference::AllPairsSort, arrRandom, repeats);

            fw1.write(size + "," + tPair + "," + tPairSort + "," + tAllPairs + "," + tAllPairsSort + "\n");
            System.out.println("[random] size=" + size + " Pair=" + tPair + "ms PairSort=" + tPairSort
                    + "ms AllPairs=" + tAllPairs + "ms AllPairsSort=" + tAllPairsSort + "ms");

            // --- узкий диапазон (много повторов) ---
            int[] arrRepeats = generateRandomArray(size, 10, 42); // значения 0..9

            double tPair2 = measure(Difference::Pair, arrRepeats, repeats);
            double tPairSort2 = measure(Difference::PairSort, arrRepeats, repeats);
            double tAllPairs2 = measure(AllDifference::AllPairs, arrRepeats, repeats);
            double tAllPairsSort2 = measure(AllDifference::AllPairsSort, arrRepeats, repeats);

            List<int[]> pairsFound = AllDifference.AllPairsSort(arrRepeats.clone());
            int k = pairsFound.size();

            fw2.write(size + "," + tPair2 + "," + tPairSort2 + "," + tAllPairs2 + "," + tAllPairsSort2 + "," + k + "\n");
            System.out.println("[repeats] size=" + size + " Pair=" + tPair2 + "ms PairSort=" + tPairSort2
                    + "ms AllPairs=" + tAllPairs2 + "ms AllPairsSort=" + tAllPairsSort2 + "ms k=" + k);
        }

        fw1.close();
        fw2.close();
        System.out.println("Готово, результаты в results_random.csv и results_repeats.csv");
    }
}