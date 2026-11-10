public class RadixSort {
    /**
     * Sorts the given array using radixSort
     * @param arrayA Array to be sorted
     * @param d Number of digits
     * @return The sorted array
     */
    public static int[] radixSort(int[] arrayA, int d) {
        for (int pos = 0; pos < d; pos++) {
            arrayA = countingSort(arrayA, pos);
        }
        return arrayA;
    }

    /**
     * Helper function
     * @param arrayA Array to be sorted
     * @param pos Digit position
     * @return The output array
     */
    public static int[] countingSort(int[] arrayA, int pos) {
        int[] count = new int[10];
        int size = arrayA.length;
        int[] output = new int[size];

        for (int i = 0; i < size; i++) {
            int digit = getDigit(arrayA[i], pos);
            count[digit]++;
        }
        for (int j = 1; j < 10; j++) {
            count[j] += count[j - 1];
        }
        for (int k = size - 1; k >= 0; k--) {
            int digit = getDigit(arrayA[k], pos);
            output[count[digit] - 1] = arrayA[k];
            count[digit]--;
        }
        System.arraycopy(output, 0, arrayA, 0, size);
        return arrayA;
    }

    /**
     * Returns the digit at a specific position
     * @param number The number The position of the digit
     * @param position
     * @return The wanted digit at the given position
     */
    public static int getDigit(int number, int position) {
        if (position <= 0) {
            return 0;
        }
        return (number / (int) Math.pow(10, position - 1)) % 10;
    }

}