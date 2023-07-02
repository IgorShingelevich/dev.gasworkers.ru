package ru.gasworkers.dev.tests.api.user.client.object.equipment;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_OBJECT)
@Story("Добавление оборудования")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT_OBJECT)
@Tag(AllureTag.API)
public class AddEquipmentApiTest extends BaseApiTest {
    private final HouseApi houseApi = new HouseApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddEquipmentPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(AddEquipmentPositiveCase testCase, @WithUser User client) {
        String token = loginApi.getToken(client);
        int addedObjectId = step("Add object", () -> {
            JsonObject responseObject = JsonParser.parseString(
                    houseApi.addHouse(HouseBuilder.addDefaultHouseRequestDto(), token)
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();

            int objectId = responseObject.getAsJsonObject("data").get("id").getAsInt();
            return objectId;
        });
        step("Add equipment", () -> {
            AddEquipmentResponseDto expectedResponse = AddEquipmentResponseDto.successResponse();

            AddEquipmentResponseDto actualResponse = addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), addedObjectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddEquipmentNegativeCase.class)
    @DisplayName("Negative case:")
    void negativeTestCase(AddEquipmentNegativeCase testCase, @WithUser User client) {
        String token = loginApi.getToken(client);
        int addedObjectId = step("Add object", () -> {
            JsonObject responseObject = JsonParser.parseString(
                    houseApi.addHouse(HouseBuilder.addDefaultHouseRequestDto(), token)
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();

            int objectId = responseObject.getAsJsonObject("data").get("id").getAsInt();
            return objectId;
        });
        step("Add equipment", () -> {
            AddEquipmentResponseDto expectedResponse = testCase.getExpectedResponse();
            AddEquipmentResponseDto actualResponse = addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), addedObjectId, token)
                    .statusCode(422)
                    .extract().as(AddEquipmentResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }
}
