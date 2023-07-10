package ru.gasworkers.dev.api.users.selfEmployed.complete;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.users.selfEmployed.complete.dto.CompleteRequestDto;

import static io.restassured.RestAssured.given;

public class CompleteSelfEmployedRoleApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#endpoints-POSTapi-v1-self-employed-complete
    @Step("API: Регистрация самозанятого [роль: завершение регистрации]")
    public ValidatableResponse completeSelfEmployedRole(CompleteRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/self-employed/complete")
                .then().spec(baseResponseSpec);
    }
}
