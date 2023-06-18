package ru.gasworkers.dev.api.users.client.object;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.users.BaseUserApi;
import ru.gasworkers.dev.api.users.client.object.dto.AddObjectRequestDTO;

import static io.restassured.RestAssured.given;

public class AddObjectApi extends BaseUserApi {
    @Step("API: Add object")
    public ValidatableResponse addObject(AddObjectRequestDTO inputDto) {
        String token = RegularRegistrationApi.getLoginToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/users/client-objects")
                .then().spec(baseResponseSpec);
    }
}
