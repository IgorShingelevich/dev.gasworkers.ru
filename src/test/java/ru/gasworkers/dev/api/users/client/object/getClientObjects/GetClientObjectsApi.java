package ru.gasworkers.dev.api.users.client.object.getClientObjects;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;

import static io.restassured.RestAssured.given;

public class GetClientObjectsApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#obieekty-GETapi-v1-users-client-objects
    @Step("API: Get client objects")
    public ValidatableResponse getClientObjects() {
        String token = RegularRegistrationApi.getUserToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/client-objects")
                .then().spec(baseResponseSpec);
    }
}
