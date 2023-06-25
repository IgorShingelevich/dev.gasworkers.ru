package ru.gasworkers.dev.api.consultation.masters;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.masters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;

import static io.restassured.RestAssured.given;

public class OnlineMastersApi extends BaseApi {
    /*
    https://dev.gasworkers.ru/api/consultation/masters/online?order_id=6441&main_filter=rating
    Query Parameters
order_id: 6441
main_filter: rating
    * */
    @Step("API: Get online masters")
    public ValidatableResponse getOnlineMasters(OnlineMastersRequestDto inputDto) {
        String token = RegularRegistrationApi.getLoginToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("order_id", inputDto.getOrderId())
                .queryParam("main_filter", inputDto.getSearch())
                .when()
                .get("/consultation/masters/online")
                .then().spec(baseResponseSpec);
    }


}
