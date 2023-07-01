package ru.gasworkers.dev.api.users.client.house;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.gasworkers.dev.api.users.BaseUserApi;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectRequestDTO;

import static io.restassured.RestAssured.given;

public class HouseApi extends BaseUserApi {

    //https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-users-client-objects
    @Step("API: Add object")
    public ValidatableResponse addHouse(AddHouseObjectRequestDTO inputDto, String token) {
        RequestSpecification requestSpec = given().spec(baseRequestSpec);
        if (token != null)
            requestSpec.header("Authorization", "Bearer " + token);
        return requestSpec
                .body(inputDto)
                .when()
                .post("/users/client-objects")
                .then().spec(baseResponseSpec);
    }

}
