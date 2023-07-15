package ru.gasworkers.dev.tests.api.consultation.masters.onlineMasters;

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
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersResponseDto;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Выбор мастера онлайн")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CONSULTATION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.MASTER)
@Tag(AllureTag.API)
public class OnlineMastersApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();


    @ParameterizedTest(name = "{0}")
    @EnumSource(OnlineMastersPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(OnlineMastersPositiveCase testCase, @WithClient(houses = {@WithHouse}) User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create order", () -> {
            return createOrdersApi.createOrder(testCase.getCreateOrdersDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrderResponseDto.class).getData().getOrderId();
        });
        step("Get online masters list", () -> {
            OnlineMastersResponseDto responseDto = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId), token)
                    .statusCode(200)
                    .extract()
                    .as(OnlineMastersResponseDto.class);
            assertNotNull(responseDto.getData().get(0).getId());
        });
    }
}