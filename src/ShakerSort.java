public class ShakerSort {
    /**
     * Sorts the given array using shakerSort
     * @param arrayA Array to be sorted
     */
    public static void shakerSort(int[] arrayA) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i <= arrayA.length - 2; i++) {
                if (arrayA[i] > arrayA[i + 1]) {
                    swap(arrayA, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = arrayA.length - 2; i>= 0 ; i--) {
                if (arrayA[i] > arrayA[i + 1]) {
                    swap(arrayA, i, i + 1);
                    swapped = true;
                }
            }
        }
    }

    /**
     * Swaps the values
     * @param array Array to make switch operation
     * @param a Index of the first element
     * @param b Index of the second element
     */
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}