package ru.gasworkers.dev.api.consultation.masters.onlineMasters;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;

import static io.restassured.RestAssured.given;

public class OnlineMastersApi extends BaseApi {

    //https://api.dev.gasworkers.ru/docs#konsultacii-GETapi-v1-consultation-masters-online
    @Step("API: Get online masters")
    public ValidatableResponse getOnlineMasters(OnlineMastersRequestDto inputDto) {
        String token = RegularRegistrationApi.getUserToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("order_id", inputDto.getOrderId())
                .queryParam("main_filter", inputDto.getSearch())
                .when()
                .get("/consultation/masters/online")
                .then().spec(baseResponseSpec);
    }
}
