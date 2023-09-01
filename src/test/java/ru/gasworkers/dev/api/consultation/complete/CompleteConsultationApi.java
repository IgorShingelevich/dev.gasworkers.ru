package ru.gasworkers.dev.api.consultation.complete;

import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class CompleteConsultationApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-complete
    public ValidatableResponse complete(CompleteConsultationRequest inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(inputDto)
                .post("/consultation/complete")
                .then().spec(baseResponseSpec);
    }
}
