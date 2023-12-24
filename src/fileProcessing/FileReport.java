package fileProcessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class FileReport {
    private LocalDateTime date;
    private String filename;
    private String cardInfoFrom;
    private String cardInfoOn;
    private int moneyAmount;
    private int status;
    File file = new File("src/ReportFile.txt");

    public void makeFileReport(String filename, String cardInfoFrom, String cardInfoOn, int moneyAmount, String status) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(LocalDateTime.now() + "|" + filename + " | transferred from " + cardInfoFrom
                    + " | transferred to " + cardInfoOn + " | " + moneyAmount
                    + " | " + status + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
