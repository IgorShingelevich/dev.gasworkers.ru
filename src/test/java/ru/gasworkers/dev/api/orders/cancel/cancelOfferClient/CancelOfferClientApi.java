package ru.gasworkers.dev.api.orders.cancel.cancelOfferClient;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class CancelOfferClientApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-cancel-offer-client
    @Step("API: Отменить клиентом предложение")
    public ValidatableResponse cancelOfferClient(CancelOfferRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/cancel-offer-client")
                .then()
                .spec(baseResponseSpec);
    }
}
