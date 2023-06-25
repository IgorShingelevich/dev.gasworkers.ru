package ru.gasworkers.dev.api.orders.create;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;

import static io.restassured.RestAssured.given;

public class CreateOrdersApi extends BaseApi {
    @Step("API: Create order")
    public ValidatableResponse createOrders(CreateOrdersRequestDto inputDto) {
        String token = RegularRegistrationApi.getLoginToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/create")
                .then().spec(baseResponseSpec);
    }
}
