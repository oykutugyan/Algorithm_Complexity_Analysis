public class ShellSort {
    /**
     * Sorts the given array using shellSort
     * @param arrayA Array to be sorted
     */
    public static void shellSort(int[] arrayA) {
        int n = arrayA.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arrayA[i];
                int j = i;
                while (j >= gap && arrayA[j - gap] > temp) {
                    arrayA[j] = arrayA[j - gap];
                    j = j - gap;
                }
                arrayA[j] = temp;
            }
            gap = gap / 2;
        }
    }
}