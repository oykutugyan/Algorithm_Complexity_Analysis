public class InsertionSort {
    /**
     * Sorts the given array using insertionSort
     * @param arrayA Array to be sorted
     */
    public static void insertionSort(int[] arrayA) {
        for (int j = 1; j < arrayA.length; j++) {
            int key = arrayA[j];
            int i = j - 1;
            while (i >= 0 && arrayA[i] > key) {
                arrayA[i + 1] = arrayA[i];
                i = i - 1;
            }
            arrayA[i + 1] = key;
        }
    }
}