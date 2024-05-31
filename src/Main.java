import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        int[] dataSizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        int numIterations = 10;
        int numIterations2 = 1000;


        
        // Experiments on the Given Random Data
        int[] insertionSortTimes = new int[dataSizes.length];
        int[] mergeSortTimes = new int[dataSizes.length];
        int[] countingSortTimes = new int[dataSizes.length];

        for (int j = 0; j < dataSizes.length; j++) {
            int dataSize = dataSizes[j];
            long totalInsertionDuration = 0;
            long totalMergeDuration = 0;
            long totalCountingDuration = 0;

            for (int i = 0; i < numIterations; i++) {
                // Veri kümesini al
                Integer[] originalArray = FileOperation.getData(filePath, dataSize);

                // Insertion Sort süresini hesapla
                Integer[] insertionArray = Arrays.copyOf(originalArray, originalArray.length);
                long insertionStartTime = System.currentTimeMillis();
                InsertionSort.sort(insertionArray);
                long insertionEndTime = System.currentTimeMillis();
                totalInsertionDuration += insertionEndTime - insertionStartTime;

                // Merge Sort süresini hesapla
                Integer[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
                long mergeStartTime = System.currentTimeMillis();
                MergeSort.sort(mergeArray);
                long mergeEndTime = System.currentTimeMillis();
                totalMergeDuration += mergeEndTime - mergeStartTime;

                // Counting Sort süresini hesapla
                Integer[] countingArray = Arrays.copyOf(originalArray, originalArray.length);
                int max = Arrays.stream(countingArray).mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
                long countingStartTime = System.currentTimeMillis();
                CountingSort.sort(countingArray, max);
                long countingEndTime = System.currentTimeMillis();
                totalCountingDuration += countingEndTime - countingStartTime;
            }

            // Ortalama süreleri hesapla
            long averageInsertionDuration = totalInsertionDuration / numIterations;
            long averageMergeDuration = totalMergeDuration / numIterations;
            long averageCountingDuration = totalCountingDuration / numIterations;

            // Ortalama süreleri hesapla ve dizilere ekle
            insertionSortTimes[j] = (int)totalInsertionDuration / (int) numIterations;
            mergeSortTimes[j] = (int)totalMergeDuration / (int) numIterations;
            countingSortTimes[j] =(int) totalCountingDuration / (int) numIterations;

            // Sonuçları ekrana yazdır
            System.out.println("Data size: " + dataSize);
            System.out.println("Random Data Insertion Sort time: " + averageInsertionDuration + " ms");
            System.out.println("Random Data Merge Sort time: " + averageMergeDuration + " ms");
            System.out.println("Random Data Counting Sort time: " + averageCountingDuration + " ms");
            System.out.println();
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).title("Tests on Random Data")
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        chart.addSeries("Insertion Sort",dataSizes,insertionSortTimes);
        chart.addSeries("Merge Sort",dataSizes,mergeSortTimes);
        chart.addSeries("Counting Sort",dataSizes,countingSortTimes);

        BitmapEncoder.saveBitmap(chart, "RandomDataSorts" + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart).displayChart();





        // Experiments on the Sorted Data
        int[] insertionSortTimes2 = new int[dataSizes.length];
        int[] mergeSortTimes2 = new int[dataSizes.length];
        int[] countingSortTimes2 = new int[dataSizes.length];
        for (int j = 0; j < dataSizes.length; j++) {
            int dataSize = dataSizes[j];
            long totalInsertionDuration = 0;
            long totalMergeDuration = 0;
            long totalCountingDuration = 0;

            for (int i = 0; i < numIterations; i++) {
                // Veri kümesini al ve sırala
                Integer[] originalArray = FileOperation.getData(filePath, dataSize);
                Arrays.sort(originalArray);

                // Insertion Sort süresini hesapla
                Integer[] insertionArray = Arrays.copyOf(originalArray, originalArray.length);
                long insertionStartTime = System.currentTimeMillis();
                InsertionSort.sort(insertionArray);
                long insertionEndTime = System.currentTimeMillis();
                totalInsertionDuration += insertionEndTime - insertionStartTime;

                // Merge Sort süresini hesapla
                Integer[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
                long mergeStartTime = System.currentTimeMillis();
                MergeSort.sort(mergeArray);
                long mergeEndTime = System.currentTimeMillis();
                totalMergeDuration += mergeEndTime - mergeStartTime;

                // Counting Sort süresini hesapla
                Integer[] countingArray = Arrays.copyOf(originalArray, originalArray.length);
                int max = Arrays.stream(countingArray).mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
                long countingStartTime = System.currentTimeMillis();
                CountingSort.sort(countingArray, max);
                long countingEndTime = System.currentTimeMillis();
                totalCountingDuration += countingEndTime - countingStartTime;
            }

            // Ortalama süreleri hesapla
            long averageInsertionDuration = totalInsertionDuration / numIterations;
            long averageMergeDuration = totalMergeDuration / numIterations;
            long averageCountingDuration = totalCountingDuration / numIterations;

            insertionSortTimes2[j] = (int)totalInsertionDuration / (int) numIterations;
            mergeSortTimes2[j] = (int)totalMergeDuration / (int) numIterations;
            countingSortTimes2[j] =(int) totalCountingDuration / (int) numIterations;

            // Sonuçları ekrana yazdır
            System.out.println("Data size: " + dataSize);
            System.out.println("Sorted Data Insertion Sort time: " + averageInsertionDuration + " ms");
            System.out.println("Sorted Data Merge Sort time: " + averageMergeDuration + " ms");
            System.out.println("Sorted Data Counting Sort time: " + averageCountingDuration + " ms");
            System.out.println();
        }

        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Tests on Sorted Random Data")
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        chart2.addSeries("Insertion Sort",dataSizes,insertionSortTimes2);
        chart2.addSeries("Merge Sort",dataSizes,mergeSortTimes2);
        chart2.addSeries("Counting Sort",dataSizes,countingSortTimes2);

        BitmapEncoder.saveBitmap(chart2, "SortedRandomDataSorts" + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart2).displayChart();





        // Experiments on the Reversely Sorted Data

        int[] insertionSortTimes3 = new int[dataSizes.length];
        int[] mergeSortTimes3 = new int[dataSizes.length];
        int[] countingSortTimes3 = new int[dataSizes.length];

        for (int j = 0; j < dataSizes.length; j++) {
            int dataSize = dataSizes[j];
            long totalInsertionDuration = 0;
            long totalMergeDuration = 0;
            long totalCountingDuration = 0;

            for (int i = 0; i < numIterations; i++) {
                // Veri kümesini al ve sırala
                Integer[] originalArray = FileOperation.getData(filePath, dataSize);
                Arrays.sort(originalArray);
                Collections.reverse(Arrays.asList(originalArray)); // Veri kümesini tersine çevir

                // Insertion Sort süresini hesapla
                Integer[] insertionArray = Arrays.copyOf(originalArray, originalArray.length);
                long insertionStartTime = System.currentTimeMillis();
                InsertionSort.sort(insertionArray);
                long insertionEndTime = System.currentTimeMillis();
                totalInsertionDuration += insertionEndTime - insertionStartTime;

                // Merge Sort süresini hesapla
                Integer[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
                long mergeStartTime = System.currentTimeMillis();
                MergeSort.sort(mergeArray);
                long mergeEndTime = System.currentTimeMillis();
                totalMergeDuration += mergeEndTime - mergeStartTime;

                // Counting Sort süresini hesapla
                Integer[] countingArray = Arrays.copyOf(originalArray, originalArray.length);
                int max = Arrays.stream(countingArray).mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
                long countingStartTime = System.currentTimeMillis();
                CountingSort.sort(countingArray, max);
                long countingEndTime = System.currentTimeMillis();
                totalCountingDuration += countingEndTime - countingStartTime;
            }

            // Ortalama süreleri hesapla
            long averageInsertionDuration = totalInsertionDuration / numIterations;
            long averageMergeDuration = totalMergeDuration / numIterations;
            long averageCountingDuration = totalCountingDuration / numIterations;

            insertionSortTimes3[j] = (int)totalInsertionDuration / (int) numIterations;
            mergeSortTimes3[j] = (int)totalMergeDuration / (int) numIterations;
            countingSortTimes3[j] =(int) totalCountingDuration / (int) numIterations;

            // Sonuçları ekrana yazdır
            System.out.println("Data size: " + dataSize);
            System.out.println("Reversely Sorted Data Insertion Sort time: " + averageInsertionDuration + " ms");
            System.out.println("Reversely Sorted Data Merge Sort time: " + averageMergeDuration + " ms");
            System.out.println("Reversely Sorted Data Counting Sort time: " + averageCountingDuration + " ms");
            System.out.println();
        }

        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Tests on Reversed Sorted Random Data")
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        chart3.addSeries("Insertion Sort",dataSizes,insertionSortTimes3);
        chart3.addSeries("Merge Sort",dataSizes,mergeSortTimes3);
        chart3.addSeries("Counting Sort",dataSizes,countingSortTimes3);

        BitmapEncoder.saveBitmap(chart3, "ReverselySortedRandomDataSorts" + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart3).displayChart();







        // search algoritmaları
        int[] linearSearch = new int[dataSizes.length];
        int[] sortedLinearSearch = new int[dataSizes.length];
        int[] binarySearch = new int[dataSizes.length];
        for (int j = 0; j < dataSizes.length; j++) {
            int dataSize = dataSizes[j];
            long totalLinearUnsortedDuration = 0;
            long totalLinearSortedDuration = 0;
            long totalBinarySortedDuration = 0;

            for (int i = 0; i < numIterations2; i++) {
                // Veri kümesini al
                Integer[] originalArray = FileOperation.getData(filePath, dataSize);
                Random rand = new Random();
                int linearTarget = originalArray[rand.nextInt(originalArray.length)]; // Rastgele hedef sayı

                // Linear Search süresini hesapla unsorted
                Integer[] linearArray = Arrays.copyOf(originalArray, originalArray.length);
                long linearStartTime = System.nanoTime();
                int linearIndex = LinearSearch.search(linearArray, linearTarget);
                long linearEndTime = System.nanoTime();
                totalLinearUnsortedDuration += linearEndTime - linearStartTime;

                // Linear Search süresini hesapla sorted
                Integer[] sortedArray = Arrays.copyOf(originalArray, originalArray.length);
                Arrays.sort(sortedArray); // Sıralı diziyi oluştur
                long linearSortedStartTime = System.nanoTime();
                int linearSortedIndex = LinearSearch.search(sortedArray, linearTarget);
                long linearSortedEndTime = System.nanoTime();
                totalLinearSortedDuration += linearSortedEndTime - linearSortedStartTime;

                // Binary Search süresini hesapla sorted
                long binaryStartTime = System.nanoTime();
                int binaryIndex = BinarySearch.search(sortedArray, linearTarget);
                long binaryEndTime = System.nanoTime();
                totalBinarySortedDuration += binaryEndTime - binaryStartTime;
            }

            // Ortalama süreleri hesapla
            long averageLinearUnsortedDuration = totalLinearUnsortedDuration / numIterations2;
            long averageLinearSortedDuration = totalLinearSortedDuration / numIterations2;
            long averageBinarySortedDuration = totalBinarySortedDuration / numIterations2;

            linearSearch[j] = (int)totalLinearUnsortedDuration / (int) numIterations;
            sortedLinearSearch[j] = (int)totalLinearSortedDuration / (int) numIterations;
            binarySearch[j] =(int) totalBinarySortedDuration / (int) numIterations;

            // Sonuçları ekrana yazdır
            System.out.println("Data size: " + dataSize);
            System.out.println("Linear Search (random data) time: " + averageLinearUnsortedDuration + " ns");
            System.out.println("Linear Search (sorted data) time: " + averageLinearSortedDuration + " ns");
            System.out.println("Binary Search (sorted data) time: " + averageBinarySortedDuration + " ns");
            System.out.println();
        }

        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Tests on Random Data")
                .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();

        chart4.addSeries("Unsorted Linear Search",dataSizes,linearSearch);
        chart4.addSeries("Sorted Linear Search",dataSizes,sortedLinearSearch);
        chart4.addSeries("Binary Search",dataSizes,binarySearch);

        BitmapEncoder.saveBitmap(chart4, "Search" + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart4).displayChart();


        

    }
}
