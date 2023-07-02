package ru.gasworkers.dev.tests.api.consultation.masters.onlineMasters;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.StringReader;

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
    private final HouseApi houseApi = new HouseApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();


    @ParameterizedTest(name = "{0}")
    @EnumSource(OnlineMastersPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(OnlineMastersPositiveCase testCase, @WithUser User client) {
        String token = loginApi.getToken(client);
        Integer objectId = step("Add object", () -> {
            return houseApi.addHouse(HouseBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(AddHouseObjectResponseDTO.class).getData().getId();
        });

        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });

        Integer orderId = step("Create order", () -> {
            JsonObject responseObject = JsonParser.parseReader(
                    new JsonReader(new StringReader(
                            createOrdersApi.createOrders(testCase.getCreateOrdersDto(), token)
                                    .statusCode(200)
                                    .extract().asString()
                    ))
            ).getAsJsonObject();
            return responseObject.getAsJsonObject("data").get("order_id").getAsInt();
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