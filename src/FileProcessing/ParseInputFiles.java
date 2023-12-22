package FileProcessing;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ParseInputFiles {
    File folder = new File("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\input");
    File[] listOfFiles = folder.listFiles();

    public void ParseFile() {
        try (FileWriter fileWriter = new FileWriter("CardInfoFile.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    FileReader fileReader = new FileReader(file);
                    int character;
                    while ((character = fileReader.read()) != -1) {
                        stringBuilder.append((char) character);
                    }
                    System.out.println(stringBuilder);
                    Pattern pattern = Pattern.compile("\\d{5}-\\d{5}\\|\\d{5}-\\d{5}/\\d+");
                    Matcher matcher = pattern.matcher(stringBuilder);
                    while (matcher.find()) {
                        fileWriter.write(matcher.group());
                    }
                    fileReader.close();
                    stringBuilder.delete(0, stringBuilder.length());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void moveToArchive() {
        Path result = null;
        Path source = Paths.get("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\input");
        Path destination = Paths.get("C:\\Users\\Yumeinaruu\\IdeaProjects\\MoneyTransferProject\\src\\archive");
        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(source)) {
            for (Path path : directoryStream) {
                Path d2 = destination.resolve(path.getFileName());
                Files.move(path, d2, REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
