package ru.gasworkers.dev.api.consultation.cancelRequest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.cancelRequest.dto.ConsultationCancelRequestRequestDto;

import static io.restassured.RestAssured.given;

public class ConsultationCancelRequestApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-cancel-request
    @Step("API: Отмена заявки на консультацию")
    public ValidatableResponse cancelRequest(ConsultationCancelRequestRequestDto inputDto, Integer orderId, String token) {
        inputDto.setOrderId(orderId);
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/consultation/cancel-request")
                .then().spec(baseResponseSpec);
    }
}
