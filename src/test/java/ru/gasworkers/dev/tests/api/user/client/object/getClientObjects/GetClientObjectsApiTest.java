package ru.gasworkers.dev.tests.api.user.client.object.getClientObjects;

import com.codeborne.pdftest.assertj.Assertions;
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
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.api.users.client.house.getHouse.GetHouseApi;
import ru.gasworkers.dev.api.users.client.house.getHouse.dto.GetClientObjectResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_OBJECT)
@Story("Получение списка оборудования клиента")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT_OBJECT)
@Tag(AllureTag.API)
public class GetClientObjectsApiTest extends BaseApiTest {
    private final HouseApi houseApi = new HouseApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final GetHouseApi getClientObjectsApi = new GetHouseApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(GetClientObjectsPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(GetClientObjectsPositiveCase testCase, @WithUser User client) {
        String token = loginApi.getToken(client);
        Integer objectId = step("Add object", () -> {
            return houseApi.addHouse(HouseBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(AddHouseObjectResponseDTO.class).getData().getId();
        });
        System.out.println("objectId = " + objectId);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        step("Get Client Objects", () -> {
            GetClientObjectResponseDto actualResponse = getClientObjectsApi.getClientObjects(token)
                    .statusCode(200)
                    .extract().as(GetClientObjectResponseDto.class);
            GetClientObjectResponseDto.DataDto firstData = actualResponse.getData()[0];
            Integer equipmentId = firstData.getEquipments()[0].getId();
            Integer actualObjectId = firstData.getId();
            System.out.println("equipmentId = " + equipmentId);
            System.out.println("actualObjectId = " + actualObjectId);
            Assertions.assertThat(equipmentId)
                    .isNotNull()
                    .as("equipmentId should not be null");
            Assertions.assertThat(actualObjectId)
                    .isNotNull()
                    .as("actualObjectId should not be null");
        });


    }
}
