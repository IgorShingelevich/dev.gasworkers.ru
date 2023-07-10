package ru.gasworkers.dev.api.consultation.cancel;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.cancel.dto.ConsultationCancelRequestDto;

import static io.restassured.RestAssured.given;

public class ConsultationCancelApi extends BaseApi {
    //     https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-cancel
    @Step("API: Отмена консультации")
    public ValidatableResponse cancelConsultation(ConsultationCancelRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/consultation/cancel")
                .then().spec(baseResponseSpec);
    }
}
