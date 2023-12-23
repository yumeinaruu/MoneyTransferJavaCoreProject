package fileProcessing;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCardInfoFile {
    private static final File cardInfoFile = new File("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\CardInfoFile.txt");
    private static HashMap<String, Integer> cardInfo = new HashMap<>();

    public static void setCardInfo(HashMap<String, Integer> cardInfo) {
        ParseCardInfoFile.cardInfo = cardInfo;
    }

    public static HashMap<String, Integer> getCardInfo() {
        return cardInfo;
    }

    public static void parseCardInfoFile() throws FileNotFoundException {
        try (FileReader fileReader = new FileReader(cardInfoFile)) {
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            Pattern pattern = Pattern.compile("/\\d+\r\n");
            Matcher matcher = pattern.matcher(stringBuilder);
            StringBuilder cardBalanceValue = new StringBuilder();
            while (matcher.find()) {
                cardBalanceValue.append(matcher.group());
            }
            cardBalanceValue.deleteCharAt(0);
            cardBalanceValue.delete(cardBalanceValue.length() - 2, cardBalanceValue.length());
            pattern = Pattern.compile("\\d{5}-\\d{5}");
            matcher = pattern.matcher(stringBuilder);
            StringBuilder cardNumber = new StringBuilder();
            while (matcher.find()) {
                cardNumber.append(matcher.group());
            }
            cardInfo.put(cardNumber.toString(), Integer.parseInt(cardBalanceValue.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
