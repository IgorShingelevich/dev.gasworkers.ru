package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class RegularStartRegistrationApi1 {

    private Response regularStartRegistration(String userType, String email, String phone, boolean isPhoneSend) {
        // InputValidatorApi.validateRegularStartRegistration(userType, email, phone, isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + email + " " + phone, () -> {
        });

        String requestBody = "{\"type\":\"" + userType + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone + "\",\"phone_send\":" + isPhoneSend + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "ru-RU,ru;q=0.9")
                .body(requestBody)
                .when()
                .post("/auth/register")
                .then()
                .extract()
                .response();

        if (response.statusCode() != 200) {
            System.out.println("Start register != 200 Request: " + requestBody);
            System.out.println("Start register != 200 Response: " + response.asString());
        }
        if (response.statusCode() == 200) {
            System.out.println("Start register Request: " + requestBody);

            String responseString = response.asString();
            String decodedMessage = decodeUnicode(getValueFromJSONString(responseString, "message"));
            String decodedResponse = responseString.replace(getValueFromJSONString(responseString, "message"), decodedMessage);
            System.out.println("Start register Response: " + decodedResponse);
        }

        System.out.println("Start register for role: " + userType + " " + email + " " + phone);
        System.out.println();

        return response;
    }

    private static String getValueFromJSONString(String jsonString, String key) {
        try {
            JSONObject json = new JSONObject(jsonString);
            return json.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String decodeUnicode(String unicode) {
        StringBuilder sb = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int value = Integer.parseInt(hex[i], 16);
            sb.append((char)value);
        }
        return sb.toString();
    }

}
