import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {

    public static Integer[] getData(String filename, int dataSize) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            List<Integer> values = new ArrayList<>();

            reader.readLine();

            String line;
            int count = 0;
            while ((line = reader.readLine()) != null && count < dataSize) {
                // Virgülle ayrılmış verileri parçala
                String[] tokens = line.split(",");
                // İlk sütundaki değeri al ve Integer'a çevirip listeye ekle
                int value = Integer.parseInt(tokens[6]);
                values.add(value);
                count++;
            }
            // Listeyi Integer dizisine dönüştür
            Integer[] array = new Integer[values.size()];
            array = values.toArray(array);

            return array;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
