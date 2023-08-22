package ru.gasworkers.dev.api.administration.getUserWithAdmin;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class GetUserWithAdminApi extends BaseApi {
    // to do add doc link
    @Step("API: Get user with admin")
    public ValidatableResponse getUserWithAdmin(String token, Integer userId) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/admin/users/" + userId)
                .then().spec(baseResponseSpec);
    }
}
