package ru.gasworkers.dev.api.users.client.object.addObject;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.users.BaseUserApi;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;

import static io.restassured.RestAssured.given;

public class AddHouseObjectApi extends BaseUserApi {
    //https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-users-client-objects
    @Step("API: Add object")
    public ValidatableResponse addObject(AddHouseObjectRequestDTO inputDto) {
        String token = RegularRegistrationApi.getUserToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/users/client-objects")
                .then().spec(baseResponseSpec);
    }
}
