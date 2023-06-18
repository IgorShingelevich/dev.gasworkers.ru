package ru.gasworkers.dev.tests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.tests.BaseTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class BaseApiTest extends BaseTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected void assertResponse(Object expectedResponse, Object actualResponse) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    protected void assertResponsePartial(Object expectedResponse, Object actualResponse, List<String> excludedFields) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONObject expectedJsonObject = new JSONObject(expectedJson);
        JSONObject actualJsonObject = new JSONObject(actualJson);

        for (String field : excludedFields) {
            removeField(expectedJsonObject, field.split("\\."));
            removeField(actualJsonObject, field.split("\\."));
        }

        JSONAssert.assertEquals(expectedJsonObject, actualJsonObject, false);
    }

    private void removeField(JSONObject jsonObject, String[] fieldComponents) {
        if (fieldComponents.length == 1) {
            jsonObject.remove(fieldComponents[0]);
        } else {
            String currentField = fieldComponents[0];
            String[] remainingFields = Arrays.copyOfRange(fieldComponents, 1, fieldComponents.length);

            if (jsonObject.has(currentField) && jsonObject.get(currentField) instanceof JSONObject) {
                removeField(jsonObject.getJSONObject(currentField), remainingFields);
            }
        }
    }


    /*protected void assertResponsePartial(Object expectedResponse, Object actualResponse, List<String> excludedFields) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONObject expectedJsonObject = new JSONObject(expectedJson);
        JSONObject actualJsonObject = new JSONObject(actualJson);

        for (String field : excludedFields) {
            removeField(expectedJsonObject, field);
            removeField(actualJsonObject, field);
        }

        JSONAssert.assertEquals(expectedJsonObject, actualJsonObject, false);
    }

    private void removeField(JSONObject jsonObject, String field) {
        String[] fieldComponents = field.split("\\.");

        if (fieldComponents.length == 1) {
            jsonObject.remove(field);
        } else {
            String currentField = fieldComponents[0];
            String remainingFields = field.substring(currentField.length() + 1);

            if (jsonObject.has(currentField) && jsonObject.get(currentField) instanceof JSONObject) {
                removeField(jsonObject.getJSONObject(currentField), remainingFields);
            }
        }
    }*/




}
