import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ConfigFileEditor {
    public static void main(String[] args) {
        String configFilePath = "config.properties";
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(configFilePath)) {
            properties.load(inputStream);
            System.out.println("Configuration file loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found. A new file will be created.");
        } catch (IOException e) {
            System.err.println("Error reading the configuration file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Configuration Editor ---");
            System.out.println("1. View All Properties");
            System.out.println("2. Get a Property Value");
            System.out.println("3. Update a Property");
            System.out.println("4. Save Changes");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> viewAllProperties(properties);
                case 2 -> getPropertyValue(properties, scanner);
                case 3 -> updateProperty(properties, scanner);
                case 4 -> savePropertiesToFile(properties, configFilePath);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to display all properties
    private static void viewAllProperties(Properties properties) {
        if (properties.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            properties.forEach((key, value) -> System.out.println(key + " = " + value));
        }
    }
    private static void getPropertyValue(Properties properties, Scanner scanner) {
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();
        String value = properties.getProperty(key);

        if (value != null) {
            System.out.println("Value for '" + key + "' = " + value);
        } else {
            System.out.println("Key '" + key + "' not found.");
        }
    }
    private static void updateProperty(Properties properties, Scanner scanner) {
        System.out.print("Enter the key to update: ");
        String key = scanner.nextLine();
        System.out.print("Enter the new value: ");
        String value = scanner.nextLine();

        properties.setProperty(key, value);
        System.out.println("Property updated: " + key + " = " + value);
    }
    private static void savePropertiesToFile(Properties properties, String filePath) {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, "Updated Configuration");
            System.out.println("Properties saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving properties to file: " + e.getMessage());
        }
    }
}
