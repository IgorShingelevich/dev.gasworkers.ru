package utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationCodeExtractor {

    public static void main(String[] args) throws IOException {
        // Read the sms.json file and parse it into a Java object
        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> smsMap = mapper.readValue(new File("src/test/java/resources/sms.json"), new TypeReference<Map<String, Object>>() {});
        Map<String, Object> smsMap = mapper.readValue(new File("src/test/java/resources/sms.json"), new TypeReference<Map<String, Object>>() {});

        // Get the list of SMS messages
        Map<String, Object> values = (Map<String, Object>) smsMap.get("values");

        // Iterate through the SMS messages and extract the validation code from each message
        String mostRecentCode = "";
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Map<String, Object> message = (Map<String, Object>) entry.getValue();
            String text = (String) message.get("text");
            // Check if the message text contains the validation code
            if (text.contains("Код для подписания договора ТО: ")) {
                // Extract the code from the message text
                String code = text.substring(text.indexOf(":") + 2);
                // Update the most recent code if necessary
                if (code.compareTo(mostRecentCode) > 0) {
                    mostRecentCode = code;
                }
            }
        }

        // Print the most recent validation code to the console
        System.out.println(mostRecentCode);
    }
}
