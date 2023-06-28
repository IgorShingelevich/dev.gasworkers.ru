package ru.gasworkers.dev.tests.api.user.client.object.getEquipments;

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
import ru.gasworkers.dev.api.users.client.object.addObject.AddHouseObjectApi;
import ru.gasworkers.dev.api.users.client.object.getEquipments.GetEquipmentsApi;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_OBJECT)
@Story("Получение списка оборудования по объекту")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT_OBJECT)
@Tag(AllureTag.API)
public class GetEquipmentsApiTest {
    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final GetEquipmentsApi getEquipmentsApi = new GetEquipmentsApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(GetEquipmentPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(GetEquipmentPositiveCase testCase) {
        /*Integer objectId = addObjectApi.addObject(AddHouseObjectBuilder.addDefaultHouseObjectRequest())
                .statusCode(200)
                .extract().as(AddHouseObjectResponseDTO.class).getData().getId();
        Integer equipmentId = addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId)
                .statusCode(200)
                .extract().as(AddEquipmentResponseDto.class).getData().getId();
        GetEquipmentsResponseDto expectedResponse = GetEquipmentsResponseDto.successResponse();
        GetEquipmentsResponseDto actualResponse = getEquipmentsApi.getEquipments(objectId)
                .statusCode(200)
                .extract().as(GetEquipmentsResponseDto.class);
        assertResponse(expectedResponse, actualResponse);*/
    }
}
