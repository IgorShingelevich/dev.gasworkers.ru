package ru.gasworkers.dev.api.consultation.resume;

import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class ResumeConsultationApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-resume
    public ValidatableResponse resume(ResumeConsultationRequest inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(inputDto)
                .post("/consultation/resume")
                .then().spec(baseResponseSpec);
    }
}
