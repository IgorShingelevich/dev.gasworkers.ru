package ru.gasworkers.dev.api.helper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class JsonLogger {

    private static final String FOLDER_PATH = "json_logs";
    private static final Map<String, Integer> METHOD_CALL_COUNTS = new HashMap<>();
    private static final JsonFactory jsonFactory = new JsonFactory();
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");

    static {
        try {
            Files.createDirectories(Paths.get(FOLDER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String methodName, String jsonResponse) {
        String currentTime = LocalTime.now().format(timeFormatter);
        String fileName = String.format("%s/%s_%s.json", FOLDER_PATH, currentTime, methodName);
        File file = new File(fileName);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            formatJson(jsonResponse, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void formatJson(String jsonResponse, OutputStream outputStream) throws IOException {
        String decodedJson = StringEscapeUtils.unescapeJava(jsonResponse);
        try (InputStream inputStream = new ByteArrayInputStream(decodedJson.getBytes(StandardCharsets.UTF_8));
             JsonParser parser = jsonFactory.createParser(inputStream);
             JsonGenerator generator = jsonFactory.createGenerator(outputStream).useDefaultPrettyPrinter()) {

            JsonToken token;
            while ((token = parser.nextToken()) != null) {
                generator.copyCurrentEvent(parser);
            }
        }
    }
}
