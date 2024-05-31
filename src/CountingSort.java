public class CountingSort {

    public static void sort(Integer[] array, int max) {
        int[] count = new int[max + 1];

        for (int value : array) {
            count[value]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        Integer[] sortedArray = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }
}
