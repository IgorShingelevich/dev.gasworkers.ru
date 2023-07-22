package ru.gasworkers.dev.tests.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.tests.api.story.repair.pay.CommonFieldsRepairDto;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class BaseApiTest extends BaseTest {

    protected final LoginApi loginApi = new LoginApi();
    private final ObjectMapper objectMapper = new ObjectMapper();
    protected final CommonFieldsRepairDto commonFields = new CommonFieldsRepairDto();


    public static void compareJsonDifferences(Object response1, Object response2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String json1 = objectMapper.writeValueAsString(response1);
            String json2 = objectMapper.writeValueAsString(response2);

            JsonNode expectedNode = objectMapper.readTree(json1);
            JsonNode actualNode = objectMapper.readTree(json2);

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(actualNode).usingRecursiveComparison().isEqualTo(expectedNode);
            softly.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void assertResponse(Object expectedResponse, Object actualResponse) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    protected void assertResponse2(Object expectedResponse, Object actualResponse) throws IOException {
        Assertions.assertThat(actualResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedResponse);
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

    protected void assertResponsePartialExcludeFields(Object expectedResponse, Object actualResponse, List<String> excludedFields) throws IOException {
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields(excludedFields.toArray(new String[0]))
                .build();

        Assertions.assertThat(actualResponse)
                .usingRecursiveComparison(configuration)
                .isEqualTo(expectedResponse);
    }

    protected void assertResponsePartialNoATExcludeFields(Object expectedResponse, Object actualResponse, List<String> excludedFields) throws IOException {
        RecursiveComparisonConfiguration.Builder builder = RecursiveComparisonConfiguration.builder();

        // Condition 1: Ignore fields ending with "at"
        builder = builder.withIgnoredFieldsMatchingRegexes(".*(At|Ended|Started)");

        // Condition 2: No excluded fields list
        if (excludedFields.isEmpty()) {
            builder = builder.withStrictTypeChecking(true);
        } else {
            builder = builder.withIgnoredFields(excludedFields.toArray(new String[0]));
        }

        RecursiveComparisonConfiguration configuration = builder.build();

        Assertions.assertThat(actualResponse)
                .usingRecursiveComparison(configuration)
                .isEqualTo(expectedResponse);
    }

    protected void assertResponsePartialNoAt(Object expectedResponse, Object actualResponse) {
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFieldsMatchingRegexes(".*(At|Ended|Started)")
                .build();

        Assertions.assertThat(actualResponse)
                .usingRecursiveComparison(configuration)
                .isEqualTo(expectedResponse);
    }

}







