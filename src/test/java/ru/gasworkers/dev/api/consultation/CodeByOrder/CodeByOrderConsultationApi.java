package ru.gasworkers.dev.api.consultation.CodeByOrder;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class CodeByOrderConsultationApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-code-by-order
    @Step("API: Получение кода конференции для agora.io в случае если этот метод вызовет мастер то будут сгенерированы коды.")
    public ValidatableResponse codeByOrder(CodeByOrderConsultationRequest inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(inputDto)
                .post("/consultation/code-by-order")
                .then().spec(baseResponseSpec);
    }
}
