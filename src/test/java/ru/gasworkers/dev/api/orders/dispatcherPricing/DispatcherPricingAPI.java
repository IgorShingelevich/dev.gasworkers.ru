package ru.gasworkers.dev.api.orders.dispatcherPricing;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class DispatcherPricingAPI extends BaseApi {
    //https://dev.gasworkers.ru/api/orders/dispatcher-pricing/10578
    @Step("API: Dispatcher pricing")
    public ValidatableResponse dispatcherPricing(DispatcherPricingRequestDto inputDto, Integer orderId, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/dispatcher-pricing/" + orderId)
                .then().spec(baseResponseSpec);
    }

}
