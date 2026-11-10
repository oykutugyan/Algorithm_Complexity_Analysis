import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dataset {
    /**
     * Reads the file, extracts Flow Duration column
     * @param path File path
     * @return List of Flow Duration values
     */
    public static int[] readDatasetFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); // Reading the file
            if (lines.size() == 0) {
                return new int[0];
            }
            lines.remove(0);
            lines.removeIf(line -> line.split(",").length != 3);

            int[] flowDurationValues = new int[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String[] line = lines.get(i).split(",");
                flowDurationValues[i] = Integer.parseInt(line[2].trim());
            }
            return flowDurationValues;
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }
    public static int[] subsetDatasetFile(int[] dataset, int sizeOfSubset) {
        int subsetSize = Math.min(sizeOfSubset, dataset.length);
        int[] subset = new int[subsetSize];
        System.arraycopy(dataset, 0, subset, 0, subsetSize);
        return subset;
    }
}
