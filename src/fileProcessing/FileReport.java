package fileProcessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileReport {
    File file = new File("src/ReportFile.txt");

    public void makeFileReport(String filename, String cardInfoFrom, String cardInfoOn, int moneyAmount, String status) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(LocalDateTime.now() + "|" + filename + " | transferred from " + cardInfoFrom
                    + " | transferred to " + cardInfoOn + " | " + moneyAmount
                    + " | " + status + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
