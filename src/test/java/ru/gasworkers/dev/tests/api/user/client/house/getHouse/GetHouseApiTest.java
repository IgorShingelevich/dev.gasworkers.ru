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
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.dto.HouseResponseDto;
import ru.gasworkers.dev.api.users.client.house.getHouse.GetHouseApi;
import ru.gasworkers.dev.api.users.client.house.getHouse.dto.GetHouseResponseDto;
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
public class GetHouseApiTest extends BaseApiTest {
    private final HouseApi houseApi = new HouseApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final GetHouseApi getHouseApi = new GetHouseApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(GetHousePositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(GetHousePositiveCase testCase, @WithUser User client) {
        String token = loginApi.getTokenPhone(client);
        Integer objectId = step("Add object", () -> {
            return houseApi.addHouse(HouseBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(HouseResponseDto.class).getData().getId();
        });
        System.out.println("objectId = " + objectId);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        step("Get Client Objects", () -> {
            GetHouseResponseDto actualResponse = getHouseApi.getHouse(token)
                    .statusCode(200)
                    .extract().as(GetHouseResponseDto.class);
            GetHouseResponseDto.DataDto firstData = actualResponse.getData()[0];
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
