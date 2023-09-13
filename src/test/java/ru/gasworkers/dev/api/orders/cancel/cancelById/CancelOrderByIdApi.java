package ru.gasworkers.dev.api.orders.cancel.cancelById;

import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class CancelOrderByIdApi extends BaseApi {
    /*Request URL:
https://dev.gasworkers.ru/api/orders/9960/cancel
Request Method:
PUT*/
    public ValidatableResponse cancelOrderById(CancelOrderByIdRequestDto inputDto, Integer orderId, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .put("/orders/" + orderId + "/cancel")
                .then()
                .spec(baseResponseSpec);
    }
}
