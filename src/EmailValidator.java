import java.io.*;
import java.util.regex.*;

public class EmailValidator {
    public static void main(String[] args) {
        String inputFilePath = "emails.txt";
        String outputFilePath = "valid_emails.txt";
        validateEmails(inputFilePath, outputFilePath);
    }
    private static void validateEmails(String inputFilePath, String outputFilePath) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            String email;
            int validCount = 0;

            while ((email = reader.readLine()) != null) {
                email = email.trim();
                Matcher matcher = pattern.matcher(email);

                if (matcher.matches()) {
                    writer.write(email);
                    writer.newLine();
                    validCount++;
                }
            }
            System.out.println("Validation complete. " + validCount + " valid email(s) saved to " + outputFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during file processing: " + e.getMessage());
        }
    }
}
