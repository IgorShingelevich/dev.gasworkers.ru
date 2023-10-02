package ru.gasworkers.dev.api.orders.makeOffer;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class MakeOfferApi extends BaseApi {
    //    https://dev.gasworkers.ru/api/orders/make-offer
    @Step("API: Make offer")
    public ValidatableResponse makeOffer(MakeOfferRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/make-offer")
                .then().spec(baseResponseSpec);
    }

}
