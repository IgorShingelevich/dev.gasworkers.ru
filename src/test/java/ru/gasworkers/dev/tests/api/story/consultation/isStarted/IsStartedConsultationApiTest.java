package ru.gasworkers.dev.tests.api.story.consultation.isStarted;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.consultation.isStarted.IsStartedApi;
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.HousesResponseDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Утверждение о том, что консультация уже началась")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.PAYMENT)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class IsStartedConsultationApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final IsStartedApi isStartedApi = new IsStartedApi();
    /*
    https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-users-client-objects
    https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-client-objects--object--equipments
    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-create
    https://api.dev.gasworkers.ru/docs#konsultacii-GETapi-v1-consultation-masters-online
    */

    @Disabled
    @ParameterizedTest(name = "{0}")
    @EnumSource(IsStartedPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(IsStartedPositiveCase testCase, @WithClient User client) {
        String token = loginApi.getTokenPhone(client);
        Integer objectId = step("Add object", () -> {
            return clientHousesApi.addHouse(ClientHousesBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(HousesResponseDto.class).getData().getId();
        });
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create order", () -> {
                    JsonObject responseObject = JsonParser.parseString(
                            createOrdersApi.createOrder(testCase.getCreateOrdersDto(), token)
                                    .statusCode(200)
                                    .extract().asString()
                    ).getAsJsonObject();
                    Integer currentOrderId = responseObject.getAsJsonObject("data").get("order_id").getAsInt();
                    System.out.println("CurrentOrderId = " + currentOrderId);
                    return currentOrderId;
                }
        );
        List<Integer> masterIdList = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId), token)
                    .statusCode(200)
                    .extract().asString();
            JsonObject responseObject = JsonParser.parseString(responseString).getAsJsonObject();
            JsonArray dataArray = responseObject.getAsJsonArray("data");
            List<Integer> currentMasterIds = new ArrayList<>();
            for (JsonElement element : dataArray) {
                JsonObject masterObject = element.getAsJsonObject();
                int masterId = masterObject.get("id").getAsInt();
                currentMasterIds.add(masterId);
            }
            return currentMasterIds;
        });
        step("Is Started", () -> {
            IsStartedResponseDto actualResponse = isStartedApi.isStarted(testCase.getIsStartedDto(orderId), token)
                    .statusCode(200)
                    .extract().as(IsStartedResponseDto.class);
            IsStartedResponseDto expectedResponse = IsStartedResponseDto.successStatusFalseResponse();
            assertResponse(actualResponse, expectedResponse);
        });
    }
}