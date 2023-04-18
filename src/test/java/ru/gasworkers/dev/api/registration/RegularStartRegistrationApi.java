package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import ru.gasworkers.dev.api.helperApi.InputValidatorApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegularStartRegistrationApi {

    /**

     * This process does not register the user in the literal sense, but only creates a "registration attempt".
     *
     * @param userType  Type of user being registered (client/master/service) (required).
     * @param email     E-mail of the user being registered (optional, but mandatory if phone is not provided).
     * @param phone     Phone number of the user being registered (optional, but mandatory if email is not provided).
     * @param isPhoneSend Indicates whether the phone number should be sent or not (optional, default: false).
     */
   /* public void regularStartRegistration(String userType, String email, String phone, Boolean isPhoneSend) {
        InputValidatorApi.validateRegularStartRegistration(userType, email, phone, isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + email + " " + phone, () -> {

            String requestBody = "{"
                    + "\"type\": \"" + userType + "\","
                    + "\"email\": \"" + email + "\","
                    + "\"phone\": \"" + phone + "\","
                    + "\"phone_send\": " + isPhoneSend
                    + "}";

            Response response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/register")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("Успешная регистрация"))
                    .extract()
                    .response();
            if (response.statusCode() != 200){
                System.out.println("Start register != 200 Request: " + requestBody);
                System.out.println("Start register != 200 Response: " + response.asString());
            }
            if (response.statusCode() == 200){
                System.out.println("Start register Request: " + requestBody);
                System.out.println("Start register Response: " + response.asString());
            }

            System.out.println("Start register for role: " + userType + " " + email + " " + phone);
            System.out.println();
        });
    }*/



    public Response regularStartRegistration(String userType, String email, String phone, Boolean isPhoneSend) {
        InputValidatorApi.validateRegularStartRegistration(userType, email, phone, isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + email + " " + phone, () -> {
        });

        String requestBody = "{\"type\":\"" + userType + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone + "\",\"phone_send\":" + isPhoneSend + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
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
