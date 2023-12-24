package fileProcessing;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import transactions.CardOperations;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ParseInputFiles {
    private File folder = new File("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\input");
    private File[] listOfFiles = folder.listFiles();
    // private static HashMap<String, Integer> cardInfoFromTr = new HashMap<>();
    // private static HashMap<String, Integer> cardInfoOnTr = new HashMap<>();

    public void ParseFile() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    FileReader fileReader = new FileReader(file);
                    int character;
                    while ((character = fileReader.read()) != -1) {
                        stringBuilder.append((char) character);
                    }
                    System.out.print(stringBuilder);
                    Pattern pattern = Pattern.compile("\\d{5}-\\d{5}\\|");
                    Matcher matcher = pattern.matcher(stringBuilder);
                    StringBuilder cardNumberFrom = new StringBuilder();
                    while (matcher.find()) {
                        cardNumberFrom.append(matcher.group());
                    }
                    cardNumberFrom.deleteCharAt(cardNumberFrom.length() - 1);
                    StringBuilder cardNumberOn = new StringBuilder();
                    pattern = Pattern.compile("\\|\\d{5}-\\d{5}");
                    matcher = pattern.matcher(stringBuilder);
                    while (matcher.find()) {
                        cardNumberOn.append(matcher.group());
                    }
                    cardNumberOn.deleteCharAt(0);
                    pattern = Pattern.compile("/-?\\d+\r\n");
                    matcher = pattern.matcher(stringBuilder);
                    StringBuilder cardTransferValue = new StringBuilder();
                    while (matcher.find()) {
                        cardTransferValue.append(matcher.group());
                    }
                    try {
                        cardTransferValue.deleteCharAt(0);
                        cardTransferValue.delete(cardTransferValue.length() - 2, cardTransferValue.length());
                    } catch (Exception e){

                    }
                    //cardInfoFromTr.put(cardNumberFrom.toString(), Integer.parseInt(cardTransferValue.toString()));
                    //cardInfoOnTr.put(cardNumberOn.toString(), Integer.parseInt(cardTransferValue.toString()));
                    ParseCardInfoFile.parseCardInfoFile();
                    CardOperations cardOperations = new CardOperations();
                    cardOperations.transferMoney(cardNumberFrom.toString(), cardNumberOn.toString(), Integer.parseInt(cardTransferValue.toString()));
                    fileReader.close();
                    stringBuilder.delete(0, stringBuilder.length());
                    cardTransferValue.delete(0, stringBuilder.length());
                    cardNumberFrom.delete(0, stringBuilder.length());
                    cardNumberOn.delete(0, stringBuilder.length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveToArchive() {
        Path result = null;
        Path source = Paths.get("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\input");
        Path destination = Paths.get("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\archive");
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(source)) {
            for (Path path : directoryStream) {
                Path d2 = destination.resolve(path.getFileName());
                Files.move(path, d2, REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

   /* public static HashMap<String, Integer> getCardInfoOnTr() {
        return cardInfoOnTr;
    }

    public static HashMap<String, Integer> getCardInfoFromTr() {
        return cardInfoFromTr;
    }*/
}
