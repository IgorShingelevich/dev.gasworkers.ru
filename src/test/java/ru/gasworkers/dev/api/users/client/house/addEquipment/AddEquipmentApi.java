package ru.gasworkers.dev.api.users.client.house.addEquipment;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.users.BaseUserApi;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentRequestDto;

import static io.restassured.RestAssured.given;

public class AddEquipmentApi extends BaseUserApi {
    //https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-client-objects--object--equipments
    @Step("API: Add equipment")
    public ValidatableResponse addEquipment(AddEquipmentRequestDto inputDto, Integer houseId, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/client-objects/" + houseId + "/equipments")
                .then().spec(baseResponseSpec);
    }
}
