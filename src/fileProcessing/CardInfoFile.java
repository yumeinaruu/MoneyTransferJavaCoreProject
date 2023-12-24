package fileProcessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CardInfoFile {
    private File file = new File("src/CardInfoFile.txt");

    public void cardInfoFileUpdate(HashMap<String, Integer> hashMap) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(hashMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
