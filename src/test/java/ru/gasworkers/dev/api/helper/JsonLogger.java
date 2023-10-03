package ru.gasworkers.dev.api.helper;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonLogger {

    private static final String FOLDER_PATH = "json_logs";
    private static final Map<String, Integer> METHOD_CALL_COUNTS = new HashMap<>();

    static {
        try {
            Files.createDirectories(Paths.get(FOLDER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String methodName, String jsonResponse) {
        int callCount = METHOD_CALL_COUNTS.getOrDefault(methodName, 0) + 1;
        METHOD_CALL_COUNTS.put(methodName, callCount);

        String fileName = String.format("%s/%02d_%s.json", FOLDER_PATH, callCount, methodName);
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

