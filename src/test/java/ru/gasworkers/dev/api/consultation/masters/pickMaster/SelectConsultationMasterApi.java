package ru.gasworkers.dev.api.consultation.masters.pickMaster;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;

import static io.restassured.RestAssured.given;

public class SelectConsultationMasterApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-masters--master-
    @Step("API: Pick master")
    public ValidatableResponse selectMaster(PickMasterRequestDto inputDto, Integer masterId, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/consultation/masters/" + masterId)
                .then().spec(baseResponseSpec);

    }
}
