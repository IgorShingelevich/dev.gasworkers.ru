package ru.gasworkers.dev.tests.api.user.client.house.getEquipments;

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
import ru.gasworkers.dev.api.users.client.house.equipment.getEquipments.GetEquipmentsApi;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.tests.api.BaseApiTest;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_HOUSE)
@Story("Получение списка оборудования по объекту")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.HOUSE)
@Tag(AllureTag.API)
public class GetEquipmentsApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final GetEquipmentsApi getEquipmentsApi = new GetEquipmentsApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(GetEquipmentPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(GetEquipmentPositiveCase testCase, @WithClient User client) {
      /*  String token = loginApi.getToken(client);
        Integer objectId = step("Add object", () -> {
            return clientHousesApi.addHouse(ClientHousesBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(HousesResponseDto.class).getDataDto().getId();
        });
        Integer equipmentId = addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                .statusCode(200)
                .extract().as(AddEquipmentResponseDto.class).getDataDto().getId();
        GetEquipmentsResponseDto expectedResponse = GetEquipmentsResponseDto.successResponse();
        GetEquipmentsResponseDto actualResponse = getEquipmentsApi.getEquipments(testCase.getGetEquipmentsDto(), objectId, equipmentId, token)
                .statusCode(200)
                .extract().as(GetEquipmentsResponseDto.class);
        assertResponse(expectedResponse, actualResponse);*/
    }
}
