package ru.gasworkers.dev.api.registration.authorisation;

import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.authorisation.dto.LoginRequestDTO;

import static io.restassured.RestAssured.given;

public class LoginApi extends BaseApi {

    public ValidatableResponse login(LoginRequestDTO requestDTO) {
        return given().spec(baseRequestSpec)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then().spec(baseResponseSpec);
    }
}
