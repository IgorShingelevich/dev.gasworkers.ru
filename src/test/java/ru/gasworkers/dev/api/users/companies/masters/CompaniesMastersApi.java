package ru.gasworkers.dev.api.users.companies.masters;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersByIdRequestDto;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersRequestProfileDto;

import static io.restassured.RestAssured.given;

public class CompaniesMastersApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#servisnye-kompanii-GETapi-v1-companies-masters
//    https://api.dev.gasworkers.ru/docs#servisnye-kompanii-POSTapi-v1-companies-masters
    @Step("API: Get accepted masters")
    public ValidatableResponse getAcceptedMasters(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("accepted", true)
                .when()
                .get("/companies/masters")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Add master")
    public ValidatableResponse addMaster(CompaniesMastersRequestProfileDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/companies/masters")
                .then().spec(baseResponseSpec);
    }


    //    https://api.dev.gasworkers.ru/docs#servisnye-kompanii-POSTapi-v1-companies-masters-cancel
    @Step("API: Cancel master")
    public ValidatableResponse cancelMaster(CompaniesMastersByIdRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/companies/masters/cancel")
                .then().spec(baseResponseSpec);
    }

    //    https://api.dev.gasworkers.ru/docs#servisnye-kompanii-POSTapi-v1-companies-masters-accept
    @Step("API: Accept master")
    public ValidatableResponse acceptMaster(CompaniesMastersByIdRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/companies/masters/accept")
                .then().spec(baseResponseSpec);
    }

    // refresh master GET https://api.dev.gasworkers.ru/docs#servisnye-kompanii-GETapi-v1-companies-masters--id-
    @Step("API: get Refresh master")
    public ValidatableResponse getRefreshMaster(CompaniesMastersRequestProfileDto inputDto, Integer id, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .get("/companies/masters/" + id)
                .then().spec(baseResponseSpec);
    }

    //   refresh master POST https://api.dev.gasworkers.ru/docs#servisnye-kompanii-POSTapi-v1-companies-masters--id-
    @Step("API: post Refresh master")
    public ValidatableResponse postRefreshMaster(CompaniesMastersRequestProfileDto inputDto, Integer id, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/companies/masters/" + id)
                .then().spec(baseResponseSpec);
    }
}
