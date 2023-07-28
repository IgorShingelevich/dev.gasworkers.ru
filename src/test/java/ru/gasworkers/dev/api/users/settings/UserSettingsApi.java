package ru.gasworkers.dev.api.users.settings;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class UserSettingsApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#polzovateli-POSTapi-v1-users-settings--section-
    @Step("API: User settings common")
    public ValidatableResponse common(UserSettingsCommonRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when().post("/users/settings/common")
                .then().spec(baseResponseSpec);
    }
}
