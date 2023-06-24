package ru.gasworkers.dev.api.users.client.equipment;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.users.BaseUserApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;

import static io.restassured.RestAssured.given;

public class AddEquipmentApi extends BaseUserApi {
    @Step("API: Add equipment")
    public ValidatableResponse addEquipment(AddEquipmentRequestDto inputDto, Integer houseId) {
        String token = RegularRegistrationApi.getLoginToken();
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/client-objects/" + houseId + "/equipments")
                .then().spec(baseResponseSpec);
    }
}