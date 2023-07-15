package ru.gasworkers.dev.tests.api.user.client.house.equipment;

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
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_HOUSE)
@Story("Добавление оборудования")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.HOUSE)
@Tag(AllureTag.API)
public class AddEquipmentsApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddEquipmentPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(AddEquipmentPositiveCase testCase, @WithUser User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            AddEquipmentResponseDto expectedResponse = AddEquipmentResponseDto.successResponse();

            AddEquipmentResponseDto actualResponse = addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AddEquipmentNegativeCase.class)
    @DisplayName("Negative case:")
    void negativeTestCase(AddEquipmentNegativeCase testCase, @WithUser User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            AddEquipmentResponseDto expectedResponse = testCase.getExpectedResponse();
            AddEquipmentResponseDto actualResponse = addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(422)
                    .extract().as(AddEquipmentResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }
}
