import java.io.*;
import java.util.Scanner;

public class WordReplacer {
    public static void main(String[] args) {
        String filePath = "sample.txt"; // text file

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the word to be replaced: ");
        String wordToReplace = scanner.nextLine();

        System.out.print("Enter the new word: ");
        String newWord = scanner.nextLine();

        replaceWordInFile(filePath, wordToReplace, newWord);

        scanner.close();
    }
    private static void replaceWordInFile(String filePath, String wordToReplace, String newWord) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder();

        // Read file content
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line.replace(wordToReplace, newWord)).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent.toString());
            System.out.println("File updated successfully. Word \"" + wordToReplace + "\" replaced with \"" + newWord + "\".");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
