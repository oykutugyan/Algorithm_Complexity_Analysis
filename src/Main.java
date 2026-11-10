import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws IOException {
        String path = "TrafficFlowDataset.csv";
        int[] datasetOfFlowDurations = Dataset.readDatasetFile(path);
        // X axis data
        int[] subsetSizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        String[] algorithms = {"Comb Sort", "Insertion Sort", "Shaker Sort", "Shell Sort", "Radix Sort"};
        double[][] randomData = new double[10][5];
        double[][] sortedData = new double[10][5];
        double[][] reversedData = new double[10][5];

        for (int i = 0; i < 10; i++) {
            int size = subsetSizes[i];
            int[] subset = Dataset.subsetDatasetFile(datasetOfFlowDurations, size);

            // Experiments on the Given Random Data
            System.out.println("\n--- Experiments on the Given Random Data (Size: " + size + ") ---");
            for (int j = 0; j < 5; j++) {
                randomData[i][j] = measureAverageTime(Arrays.copyOf(subset, subset.length), algorithms[j]);
            }

            // Experiments on the Sorted Data
            Arrays.sort(subset);
            System.out.println("\n--- Experiments on the Sorted Data (Size: " + size + ") ---");
            for (int j = 0; j < 5; j++) {
                sortedData[i][j] = measureAverageTime(Arrays.copyOf(subset, subset.length), algorithms[j]);
            }

            // Experiments on the Reversely Sorted Data
            int[] reverselySortedInput = new int[size];
            int index = 0;
            for (int k = subset.length - 1; k >= 0; k--) {
                reverselySortedInput[index++] = subset[k];
            }
            System.out.println("\n--- Experiments on the Reversely Sorted Data (Size: " + size + ") ---");
            for (int j = 0; j < 5; j++) {
                reversedData[i][j] = measureAverageTime(Arrays.copyOf(reverselySortedInput, reverselySortedInput.length), algorithms[j]);
            }
        }

        // Save the chart as .png and show it
        showAndSaveChart("Random Data Sorting Chart", subsetSizes, randomData, algorithms);
        showAndSaveChart("Sorted Data Sorting Chart", subsetSizes, sortedData, algorithms);
        showAndSaveChart("Reversed Data Sorting Chart", subsetSizes, reversedData, algorithms);

        double[][] combSortData = new double[10][3];
        double[][] insertionSortData = new double[10][3];
        double[][] shakerSortData = new double[10][3];
        double[][] shellSortData = new double[10][3];
        double[][] radixSortData = new double[10][3];
        for (int i = 0; i < 10; i++) {
            combSortData[i][0] = randomData[i][0];
            combSortData[i][1] = sortedData[i][0];
            combSortData[i][2] = reversedData[i][0];

            insertionSortData[i][0] = randomData[i][1];
            insertionSortData[i][1] = sortedData[i][1];
            insertionSortData[i][2] = reversedData[i][1];

            shakerSortData[i][0] = randomData[i][2];
            shakerSortData[i][1] = sortedData[i][2];
            shakerSortData[i][2] = reversedData[i][2];

            shellSortData[i][0] = randomData[i][3];
            shellSortData[i][1] = sortedData[i][3];
            shellSortData[i][2] = reversedData[i][3];

            radixSortData[i][0] = randomData[i][4];
            radixSortData[i][1] = sortedData[i][4];
            radixSortData[i][2] = reversedData[i][4];

        }


// Now, plot charts for each sorting algorithm
        showAndSaveChart("Comb Sort", subsetSizes, combSortData, new String[]{"Random", "Sorted", "Reversed"});
        showAndSaveChart("Insertion Sort", subsetSizes, insertionSortData, new String[]{"Random", "Sorted", "Reversed"});
        showAndSaveChart("Shaker Sort", subsetSizes, shakerSortData, new String[]{"Random", "Sorted", "Reversed"});
        showAndSaveChart("Shell Sort", subsetSizes, shellSortData, new String[]{"Random", "Sorted", "Reversed"});
        showAndSaveChart("Radix Sort", subsetSizes, radixSortData, new String[]{"Random", "Sorted", "Reversed"});
    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, String[] seriesNames) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        for (int j = 0; j < seriesNames.length; j++) {
            double[] yValues = new double[yAxis.length];
            for (int i = 0; i < yAxis.length; i++) {
                yValues[i] = yAxis[i][j];
            }
            chart.addSeries(seriesNames[j], doubleX, yValues);
        }

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper<>(chart).displayChart();
    }

    public static double measureAverageTime(int[] input, String sortAlg) {
        long durationSum = 0;
        int[] inputCopy = Arrays.copyOf(input, input.length);
        for (int run = 1; run < 11; run++) {
            long startTime = System.nanoTime();
            if (sortAlg.equals("Comb Sort")) {
                CombSort.combSort(inputCopy);
            } else if (sortAlg.equals("Insertion Sort")) {
                InsertionSort.insertionSort(inputCopy);
            } else if (sortAlg.equals("Shaker Sort")) {
                ShakerSort.shakerSort(inputCopy);
            } else if (sortAlg.equals("Shell Sort")) {
                ShellSort.shellSort(inputCopy);
            } else {
                int max = Arrays.stream(inputCopy).max().orElse(0);
                int digits = (max == 0) ? 1 : (int) Math.log10(max) + 1;
                RadixSort.radixSort(inputCopy, digits);
            }
            long endTime = System.nanoTime();
            durationSum += endTime - startTime;
        }
        double mean = durationSum / 1000000.0;
        System.out.println("Mean: " + mean + " milliseconds for " + sortAlg);
        return mean;
    }
}