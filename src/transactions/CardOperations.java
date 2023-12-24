package transactions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import exception.NotEnoughMoneyException;
import exception.UnsufficientAmountException;
import fileProcessing.CardInfoFile;
import fileProcessing.FileReport;
import fileProcessing.ParseCardInfoFile;

public class CardOperations {
    //HashMap<String, Integer> cardInfoFrom = ParseInputFiles.getCardInfoFromTr();
    //HashMap<String, Integer> cardInfoOn = ParseInputFiles.getCardInfoOnTr();
    private HashMap<String, Integer> cardInfo = ParseCardInfoFile.getCardInfo();
    private FileReport fileReport = new FileReport();
    private CardInfoFile cardInfoFile = new CardInfoFile();

    public void transferMoney(String filename, String cardNumberFrom, String cardNumberOn, int moneyAmount) throws IOException {
        try {
            if (moneyAmount > cardInfo.get(cardNumberFrom)) {
                fileReport.makeFileReport(filename, cardNumberFrom, cardNumberOn, moneyAmount, "Transfer Failed. Not enough money.");
                throw new NotEnoughMoneyException();
            } else if (moneyAmount <= 0) {
                fileReport.makeFileReport(filename,cardNumberFrom, cardNumberOn, moneyAmount, "Transfer Failed. Non-sufficient " +
                        "amount of money");
                throw new UnsufficientAmountException();
            } else if (Objects.equals(cardNumberFrom, cardInfo.keySet().toString())) {
            } else {
                try {
                    cardInfo.replace(cardNumberFrom, cardInfo.get(cardNumberFrom) - moneyAmount);
                    cardInfo.replace(cardNumberOn, cardInfo.get(cardNumberOn) + moneyAmount);
                    cardInfoFile.cardInfoFileUpdate(cardInfo);
                } catch (Exception e) {
                    System.out.println("Card number mismatch");
                    fileReport.makeFileReport(filename, cardNumberFrom, cardNumberOn, moneyAmount, "Transfer failed. Invalid card number");
                    return;
                }
                fileReport.makeFileReport(filename, cardNumberFrom, cardNumberOn, moneyAmount, "Transfer completed successfully");
            }
        } catch (NotEnoughMoneyException | UnsufficientAmountException e) {
            System.out.println(e);
        }
    }
}
