package ru.gasworkers.dev.api.authorisation;

import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class LoginApi extends BaseApi {

    public ValidatableResponse authorization(LoginRequestDTO requestDTO) {
        return given().spec(baseRequestSpec)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then().spec(baseResponseSpec);
    }
}
