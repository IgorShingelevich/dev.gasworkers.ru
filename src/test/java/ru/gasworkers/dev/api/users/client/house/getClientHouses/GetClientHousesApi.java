package ru.gasworkers.dev.api.users.client.house.getClientHouses;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class GetClientHousesApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#obieekty-GETapi-v1-users-client-objects
    @Step("API: Get house")
    public ValidatableResponse getHouse(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/client-objects")
                .then().spec(baseResponseSpec);
    }
}
