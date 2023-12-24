import fileProcessing.ParseInputFiles;
import fileProcessing.ReportFileDisplay;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportFileDisplay reportFileDisplay = new ReportFileDisplay();

        while (true) {
            System.out.println("""
                    Type 1 to parse Input Catalog info
                    Type 2 to read info from Report File
                    Type 3 to read info from Report File within time limits""");
            int choice = scanner.nextInt();
            if (choice == 1) {
                ParseInputFiles inputFiles = new ParseInputFiles();
                inputFiles.ParseFile();
                inputFiles.moveToArchive();
            } else if (choice == 2) {
                reportFileDisplay.showReport();
            } else if (choice == 3) {
                System.out.println("Enter the first date:");
                LocalDateTime localDateTime1 = LocalDateTime.parse(scanner.next());
                System.out.println("Enter the second date:");
                LocalDateTime localDateTime2 = LocalDateTime.parse(scanner.next());
                reportFileDisplay.showLimitedReport(localDateTime1, localDateTime2);
            } else {
                System.out.println("You should type only 1, 2 or 3");
            }
            System.out.println("If you want to stop program type 1");
            if (scanner.nextInt() == 1) {
                System.exit(0);
            }
        }
    }
}
