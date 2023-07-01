//package ru.gasworkers.dev.api.orders.selectObject;
//
//import io.qameta.allure.Step;
//import io.restassured.response.ValidatableResponse;
//import ru.gasworkers.dev.api.BaseApi;
//import ru.gasworkers.dev.api.orders.selectObject.dto.SelectObjectRequestDto;
//import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;
//
//import static io.restassured.RestAssured.given;
//
//public class SelectObjectApi extends BaseApi {
//    //    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-object
//    @Step("API: Select object")
//    public ValidatableResponse selectObject(SelectObjectRequestDto inputDto) {
//        String token = RegularRegistrationApi.getUserToken();
//        return given().spec(baseRequestSpec)
//                .header("Authorization", "Bearer " + token)
//                .body(inputDto)
//                .when()
//                .post("/orders/select-object")
//                .then().spec(baseResponseSpec);
//    }
//}
