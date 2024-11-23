import java.io.*;
import java.util.Scanner;

public class BinaryFileHandler {
    public static void main(String[] args) {
        String sourceFilePath = "source.bin";
        String destinationFilePath = "destination.bin";
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Binary File Handler ---");
            System.out.println("1. Copy File");
            System.out.println("2. Append Data to File");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> copyFile(sourceFilePath, destinationFilePath);
                case 2 -> appendDataToFile(destinationFilePath, scanner);
                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    // Method to copy the contents of the source file to the destination file
    private static void copyFile(String sourceFilePath, String destinationFilePath) {
        try (
                FileInputStream inputStream = new FileInputStream(sourceFilePath);
                FileOutputStream outputStream = new FileOutputStream(destinationFilePath)
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied successfully from " + sourceFilePath + " to " + destinationFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error while copying file: " + e.getMessage());
        }
    }
    private static void appendDataToFile(String destinationFilePath, Scanner scanner) {
        System.out.print("Enter the data to append (as a string): ");
        String dataToAppend = scanner.nextLine();

        try (FileOutputStream outputStream = new FileOutputStream(destinationFilePath, true)) {
            outputStream.write(dataToAppend.getBytes());
            System.out.println("Data appended successfully to " + destinationFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error while appending data: " + e.getMessage());
        }
    }
}
