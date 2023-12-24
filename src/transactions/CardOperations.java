package transactions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import fileProcessing.CardInfoFile;
import fileProcessing.FileReport;
import fileProcessing.ParseCardInfoFile;
import fileProcessing.ParseInputFiles;

public class CardOperations {
    //HashMap<String, Integer> cardInfoFrom = ParseInputFiles.getCardInfoFromTr();
    //HashMap<String, Integer> cardInfoOn = ParseInputFiles.getCardInfoOnTr();
    private HashMap<String, Integer> cardInfo = ParseCardInfoFile.getCardInfo();
    private FileReport fileReport = new FileReport();
    private CardInfoFile cardInfoFile = new CardInfoFile();

    public void transferMoney(String cardNumberFrom, String cardNumberOn, int moneyAmount) throws IOException {
        if (moneyAmount > cardInfo.get(cardNumberFrom)) {
            System.out.println("Not enough money to make a transaction");
        } else if (moneyAmount <= 0) {
            System.out.println("Cannot transfer a non-sufficient amount of money");
        } else if (Objects.equals(cardNumberFrom, cardInfo.keySet().toString())) {

        } else {
            cardInfo.replace(cardNumberFrom, cardInfo.get(cardNumberFrom) - moneyAmount);
            cardInfo.replace(cardNumberOn, cardInfo.get(cardNumberOn) + moneyAmount);
            cardInfoFile.cardInfoFileUpdate(cardInfo);
            fileReport.makeFileReport(cardNumberFrom, cardNumberOn, moneyAmount, 1);//TODO: сделать ошибки
            //и тип выполение работы вместо статуса
        }
    }
}
