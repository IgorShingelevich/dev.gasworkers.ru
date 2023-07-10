package ru.gasworkers.dev.api.auth.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#avtorizaciiaregistraciia-GETapi-v1-auth-user
    @Step("API: Получение информации о пользователе")
    public ValidatableResponse getUser(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/user")
                .then().spec(baseResponseSpec);
    }
}
