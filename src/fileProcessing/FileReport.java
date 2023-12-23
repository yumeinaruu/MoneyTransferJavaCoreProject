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
    File file = new File("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\ReportFile.txt");

    public void makeFileReport(/*String filename,*/ String cardInfoFrom, String cardInfoOn, int moneyAmount, int status) throws IOException {
        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(LocalDateTime.now() + " | transferred from " + cardInfoFrom
                    + " | transferred to " + cardInfoOn + " | " + moneyAmount
            + " | " + status);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
