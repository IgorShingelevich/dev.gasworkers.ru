package ru.gasworkers.dev.tests.api.user.client.house.getHouse;

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
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.HousesResponseDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_HOUSE)
@Story("Получение списка оборудования клиента")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.HOUSE)
@Tag(AllureTag.API)
public class GetClientHousesApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(GetHousePositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(GetHousePositiveCase testCase, @WithUser User client) {
        String token = loginApi.getTokenPhone(client);
        Integer objectId = step("Add object", () -> {
            return clientHousesApi.addHouse(ClientHousesBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(HousesResponseDto.class).getData().getId();
        });
        System.out.println("objectId = " + objectId);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        step("Get Client Objects", () -> {
            GetClientHousesResponseDto actualResponse = getClientHousesApi.getHouse(token)
                    .statusCode(200)
                    .extract().as(GetClientHousesResponseDto.class);
            GetClientHousesResponseDto.DataDto firstData = actualResponse.getData()[0];
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
