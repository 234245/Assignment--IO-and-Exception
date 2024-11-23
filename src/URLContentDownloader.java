import java.io.*;
import java.net.*;
import java.util.*;

public class URLContentDownloader {
    public static void main(String[] args) {
        String urlsFilePath = "urls.txt";
        String outputDirectory = "downloads";
        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        downloadURLContent(urlsFilePath, outputDirectory);
    }
    private static void downloadURLContent(String urlsFilePath, String outputDirectory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(urlsFilePath))) {
            String url;
            int fileIndex = 1;

            while ((url = reader.readLine()) != null) {
                url = url.trim();
                if (!url.isEmpty()) {
                    System.out.println("Downloading content from: " + url);
                    try {
                        String content = downloadContent(url);
                        String outputFilePath = outputDirectory + File.separator + "file" + fileIndex + ".txt";
                        saveToFile(outputFilePath, content);
                        System.out.println("Content saved to: " + outputFilePath);
                        fileIndex++;
                    } catch (IOException e) {
                        System.err.println("Failed to download content from " + url + ": " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: URLs file not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading URLs file: " + e.getMessage());
        }
    }

    private static String downloadContent(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();

        // Open connection to the URL
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }

        return content.toString();
    }

    private static void saveToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
