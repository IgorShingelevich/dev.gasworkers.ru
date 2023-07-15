package ru.gasworkers.dev.tests.api.user.client.house.selectHouse;

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
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseResponseDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithHouse;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CLIENT)
@Feature(AllureFeature.CLIENT_HOUSE)
@Story("Добавление оборудования")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.HOUSE)
@Tag(AllureTag.API)
public class SelectClientHousesApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(SelectHousePositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(SelectHousePositiveCase testCase, @WithUser(houses = {@WithHouse}) User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create orders", () -> {
            return createOrdersApi.createOrders(testCase.getCreateOrdersDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class).getData().getOrderId();
        });

        GetClientHousesResponseDto actualResponse = getClientHousesApi.getHouse(token)
                .statusCode(200)
                .extract().as(GetClientHousesResponseDto.class);
        GetClientHousesResponseDto.DataDto firstData = actualResponse.getData()[0];
        Integer firstEquipmentId = firstData.getEquipments()[0].getId();
        List<Integer> equipmentList = new ArrayList<>();
        for (GetClientHousesResponseDto.Equipment equipment : firstData.getEquipments()) {
            equipmentList.add(equipment.getId());
        }
        Integer actualObjectId = firstData.getId();
        System.out.println("equipmentList = " + equipmentList);
        System.out.println("firstEquipmentId = " + firstEquipmentId);
        System.out.println("actualObjectId = " + actualObjectId);

        SelectHouseResponseDto expectedResponse = selectHouseApi.selectObject(testCase.getSelectObjectDto(actualObjectId, orderId, equipmentList), token)
                .statusCode(200)
                .extract().as(SelectHouseResponseDto.class);

    }
}
