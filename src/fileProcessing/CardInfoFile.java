package fileProcessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CardInfoFile {
    private File file = new File("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\CardInfoFile.txt");
    public void cardInfoFileUpdate() throws IOException {
        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(ParseCardInfoFile.getCardInfo().keySet() + "/" + ParseCardInfoFile.getCardInfo().get(ParseCardInfoFile.getCardInfo().keySet()) + "\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
