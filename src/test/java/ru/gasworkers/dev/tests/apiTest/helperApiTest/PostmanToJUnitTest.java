package ru.gasworkers.dev.tests.apiTest.helperApiTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class PostmanToJUnitTest {

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.API_COLLECTION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.API_COLLECTION)})
    @DisplayName("Постман коллекция")
    public void testPostmanCollection() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/api/apiGW.json"));
            JSONObject postmanObject = (JSONObject) obj;
            JSONArray postmanCollection = (JSONArray) postmanObject.get("item");

            for (Object item : postmanCollection) {
                JSONObject requestItem = (JSONObject) item;
                JSONObject request = (JSONObject) requestItem.get("request");

                if (request != null) {
                    String method = (String) request.get("method");
                    String url = (String) request.get("url");
                    JSONObject headersJson = (JSONObject) request.get("headers");
                    JSONObject body = (JSONObject) request.get("body");

                    // Convert JSONObject headers to Map<String, String>
                    Map<String, String> headers = new HashMap<>();
                    if (headersJson != null) {
                        for (Object key : headersJson.keySet()) {
                            headers.put((String) key, (String) headersJson.get(key));
                        }
                    }

                    RequestSpecification requestSpecification = given().contentType(ContentType.JSON).headers(headers);
                    if (body != null) {
                        requestSpecification.body(body.toJSONString());
                    }

                    Response response = null;
                    switch (method) {
                        case "GET":
                            response = requestSpecification.get(url);
                            break;
                        case "POST":
                            response = requestSpecification.post(url);
                            break;
                        case "PUT":
                            response = requestSpecification.put(url);
                            break;
                        case "DELETE":
                            response = requestSpecification.delete(url);
                            break;
                        case "PATCH":
                            response = requestSpecification.patch(url);
                            break;
                    }

                    // Add your RestAssured assertions here
                }
            }
        } catch (Exception e) {
            fail("An error occurred while executing the test: " + e.getMessage());
        }
    }
}