package ru.gasworkers.dev.api.users.client.clientsOrdersRequests;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class ClientOrdersRequestsApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#zakazy-GETapi-v1-clients-orders-requests
    @Step("API: Клиент Получение информации о заказе")
    public ValidatableResponse getInfoOrder(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/clients/orders/requests")
                .then().spec(baseResponseSpec);
    }
}
