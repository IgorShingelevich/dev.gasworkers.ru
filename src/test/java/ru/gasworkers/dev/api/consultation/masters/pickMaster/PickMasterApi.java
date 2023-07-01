package ru.gasworkers.dev.api.consultation.masters.pickMaster;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;

import static io.restassured.RestAssured.given;

public class PickMasterApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-masters--master-
    @Step("API: Pick master")
    public ValidatableResponse pickMaster(PickMasterRequestDto inputDto, Integer masterId) {
        String token = RegularRegistrationApi.getUserToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/consultation/masters/" + masterId)
                .then().spec(baseResponseSpec);

    }
}
