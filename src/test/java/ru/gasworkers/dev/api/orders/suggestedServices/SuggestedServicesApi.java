package ru.gasworkers.dev.api.orders.suggestedServices;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesRequestDto;

import static io.restassured.RestAssured.given;

public class SuggestedServicesApi extends BaseApi {
    //client  https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-suggest-services
    @Step("API: Suggest services")
    public ValidatableResponse suggestServices(SuggestServicesRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/suggest-services")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Suggest services")
    public ValidatableResponse suggestServices(Integer orderId, String token) {
        return suggestServices(SuggestServicesRequestDto.builder()
                .orderId(orderId)
                .build(), token);
    }
}
