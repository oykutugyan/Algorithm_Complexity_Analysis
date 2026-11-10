public class CombSort {
    /**
     * Sorts the given array using combSort
     * @param arrayA Array to be sorted
     */
    public static void combSort(int[] arrayA) {
        int gap = arrayA.length;
        double shrink = 1.3;
        boolean sorted = false;
        while (!sorted) {
            gap = max(1, (int) Math.floor(gap/shrink));
            sorted = (gap == 1);
            for (int i = 0; i < arrayA.length - gap; i++) {
                if (arrayA[i] > arrayA[i + gap]) {
                    swap(arrayA, i, i + gap);
                    sorted = false;
                }
            }
        }
    }

    /**
     * Finds the max
     * @param a The first integer
     * @param b The second integer
     * @return Returns the max integer
     */
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
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