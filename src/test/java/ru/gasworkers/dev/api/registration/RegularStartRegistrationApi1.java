package ru.gasworkers.dev.api.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.gasworkers.dev.model.apiModel.UserType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegularStartRegistrationApi1 {

    private final ObjectMapper objectJacksonMapper = new ObjectMapper();



    public void regularStartRegistration(String userType, String email, String phone, boolean isPhoneSend) throws JsonProcessingException {
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("type", userType);
        requestBodyMap.put("email", email);
        requestBodyMap.put("phone", phone);
        requestBodyMap.put("phone_send", isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + requestBodyMap.get("email") + " " + requestBodyMap.get("phone"), () -> {});

        String requestBody = objectJacksonMapper.writeValueAsString(requestBodyMap);

        given()
                .log().ifValidationFails()
                .log().body()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .body(requestBody)
                .when()
                .post("/auth/register")
                .then()
                .log().ifValidationFails()
                .log().body()
                .statusCode(200);


        System.out.println("Start register for role: " + requestBodyMap.get("type") + " " + requestBodyMap.get("email") + " " + requestBodyMap.get("phone"));
        System.out.println();


    }

    public void regularStartRegistration(Map<String, Object> requestBodyMap) throws JsonProcessingException {

        Allure.step("Начало - Регистрация  Api: " + requestBodyMap.get("email") + " " + requestBodyMap.get("phone"), () -> {});

        String requestBody = objectJacksonMapper.writeValueAsString(requestBodyMap);

        given()
                .log().ifValidationFails()
                .log().body()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .body(requestBody)
                .when()
                .post("/auth/register")
                .then()
                .log().ifValidationFails()
                .log().body()
                .statusCode(200);


        System.out.println("Start register for role: " + requestBodyMap.get("type") + " " + requestBodyMap.get("email") + " " + requestBodyMap.get("phone"));
        System.out.println();


    }


}
