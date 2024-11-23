import java.io.*;

public class FileMerger {
    public static void main(String[] args) {
        // Input and output file
        String firstFilePath = "file1.txt";
        String secondFilePath = "file2.txt";
        String mergedFilePath = "merged.txt";

        mergeFiles(firstFilePath, secondFilePath, mergedFilePath);
    }
    // merge two files
    private static void mergeFiles(String firstFilePath, String secondFilePath, String mergedFilePath) {
        try (
                BufferedReader reader1 = new BufferedReader(new FileReader(firstFilePath));
                BufferedReader reader2 = new BufferedReader(new FileReader(secondFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFilePath))
        ) {
            System.out.println("Merging files...");

            // Write contents of the first file to the merged file
            copyFileContents(reader1, writer);

            // Write contents of the second file to the merged file
            copyFileContents(reader2, writer);

            System.out.println("Files merged successfully into " + mergedFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: One of the input files was not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during file processing: " + e.getMessage());
        }
    }
    private static void copyFileContents(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
    }
}
