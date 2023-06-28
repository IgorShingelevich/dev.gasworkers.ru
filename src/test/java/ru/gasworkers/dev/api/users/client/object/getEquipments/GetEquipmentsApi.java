package ru.gasworkers.dev.api.users.client.object.getEquipments;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.users.client.object.getEquipments.dto.GetEquipmentsRequestDto;

import static io.restassured.RestAssured.given;

public class GetEquipmentsApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#obieekty-GETapi-v1-client-objects--object--equipments--id-
    @Step("API: Get equipments")
    public ValidatableResponse getEquipments(GetEquipmentsRequestDto inputDto, Integer objectId, Integer equipmentId) {
        String token = RegularRegistrationApi.getLoginToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(inputDto)
                .get("/client-objects/" + objectId + "/equipments/" + equipmentId)
                .then().spec(baseResponseSpec);
    }
}
