package ru.gasworkers.dev.api.orders.selectPayment;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentRequestDto;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;

import static io.restassured.RestAssured.given;

public class SelectPaymentApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-payment
    @Step("API: Select payment")
    public ValidatableResponse selectPayment(SelectPaymentRequestDto inputDto) {
        String token = RegularRegistrationApi.getLoginToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/select-payment")
                .then().spec(baseResponseSpec);
    }

}
