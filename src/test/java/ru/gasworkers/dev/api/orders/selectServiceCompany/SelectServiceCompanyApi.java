package ru.gasworkers.dev.api.orders.selectServiceCompany;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyRequestDto;

import static io.restassured.RestAssured.given;

public class SelectServiceCompanyApi extends BaseApi {
    // client https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-service-company
    @Step("API: Select service company")
    public ValidatableResponse selectServiceCompany(SelectServiceCompanyRequestDto inputDto, String tokenClient) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + tokenClient)
                .body(inputDto)
                .when()
                .post("/orders/select-service-company")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Select service company")
    public ValidatableResponse selectServiceCompany(Integer orderId, Integer serviceId, String tokenClient) {
        return selectServiceCompany(SelectServiceCompanyRequestDto.builder()
                .orderId(orderId)
                .serviceId(serviceId)
                .build(), tokenClient);
    }

}
