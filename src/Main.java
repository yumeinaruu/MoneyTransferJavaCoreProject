import FileProcessing.ParseInputFiles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Type 1 to parse Input Catalog info\nType 2 to read info from Report File");
            int choice = scanner.nextInt();
            if(choice == 1){
                ParseInputFiles inputFiles = new ParseInputFiles();
                inputFiles.ParseFile();
                //inputFiles.moveToArchive();
            } else if(choice == 2){
                System.out.println("Under maintenance");
            } else {
                System.out.println("You should type only 1 or 2");
            }
            System.out.println("If you want to stop program type 1");
            if(scanner.nextInt() == 1){
                System.exit(0);
            }
        }
    }
}
