import java.io.*;

public class LineSeparator {
    public static void main(String[] args) {
        // Input and output file paths
        String inputFilePath = "largeFile.txt";
        String oddLinesFilePath = "oddLines.txt";
        String evenLinesFilePath = "evenLines.txt";

        // Initialize file readers and writers
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter oddWriter = new BufferedWriter(new FileWriter(oddLinesFilePath));
                BufferedWriter evenWriter = new BufferedWriter(new FileWriter(evenLinesFilePath))
        ) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    evenWriter.write(line);
                    evenWriter.newLine();
                } else {
                    oddWriter.write(line);
                    oddWriter.newLine();
                }
                lineNumber++;
            }

            System.out.println("Lines have been separated successfully.");
            System.out.println("Odd lines written to: " + oddLinesFilePath);
            System.out.println("Even lines written to: " + evenLinesFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
