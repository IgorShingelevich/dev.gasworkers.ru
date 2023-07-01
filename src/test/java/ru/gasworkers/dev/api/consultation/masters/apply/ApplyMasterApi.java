//package ru.gasworkers.dev.api.consultation.masters.apply;
//
//import io.qameta.allure.Step;
//import io.restassured.response.ValidatableResponse;
//import ru.gasworkers.dev.api.BaseApi;
//import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
//import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;
//
//import static io.restassured.RestAssured.given;
//
//public class ApplyMasterApi extends BaseApi {
//    //https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-masters--master--apply
//    @Step("API: Apply master")
//    public ValidatableResponse applyMaster(ApplyMasterRequestDto inputDto, Integer masterId) {
//        String token = RegularRegistrationApi.getUserToken();
//        return given().spec(baseRequestSpec)
//                .header("Authorization", "Bearer " + token)
//                .body(inputDto)
//                .when()
//                .post("/consultation/masters/" + masterId + "/apply")
//                .then().spec(baseResponseSpec);
//    }
//}
