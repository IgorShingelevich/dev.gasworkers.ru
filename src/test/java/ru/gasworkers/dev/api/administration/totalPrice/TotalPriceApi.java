package ru.gasworkers.dev.api.administration.totalPrice;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class TotalPriceApi extends BaseApi {

//    https://api.dev.gasworkers.ru/docs#administrirovanie-GETapi-v1-admin-orders--order_id--total-price

    @Step("API: Получение информации о ценах по заказу")
    public ValidatableResponse getTotalPrice(Integer orderId, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/admin/orders/" + orderId + "/total-price")
                .then().spec(baseResponseSpec);
    }

}
