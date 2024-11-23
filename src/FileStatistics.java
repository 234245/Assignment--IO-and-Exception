import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileStatistics {
    public static void main(String[] args) {
        //  text file
        String filePath = "input.txt";

        // Initialize counters
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        // Try to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++; // Increment line count
                charCount += line.length(); // Count characters in the current line
                wordCount += line.split("//s+").length; // Split line into words and count
            }

            // Display the results
            System.out.println("File: " + filePath);
            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters: " + charCount);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
