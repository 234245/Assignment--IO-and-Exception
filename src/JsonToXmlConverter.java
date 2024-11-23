import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
public class JsonToXmlConverter {
    public static void main(String[] args) {
        String jsonFilePath = "data.json";
        String xmlFilePath = "data.xml";
        convertJsonToXml(jsonFilePath, xmlFilePath);
    }
    private static void convertJsonToXml(String jsonFilePath, String xmlFilePath) {
        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        try (
                BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFilePath))
        ) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            JsonNode jsonNode = jsonMapper.readTree(jsonContent.toString());
            String xmlContent = xmlMapper.writeValueAsString(jsonNode);
            writer.write(xmlContent);
            System.out.println("JSON data successfully converted to XML and written to " + xmlFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: JSON file not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during file processing: " + e.getMessage());
        }
    }
}