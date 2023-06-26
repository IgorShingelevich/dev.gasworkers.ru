package ru.gasworkers.dev.tests.api.consultation.masters.pickMaster;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import ru.gasworkers.dev.api.consultation.masters.pickMaster.PickMasterApi;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.AddHouseObjectApi;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Выбор мастера онлайн")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CONSULTATION_NOW)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.MASTER)
@Tag(AllureTag.API)
public class PickMasterApiTest extends BaseApiTest {
    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final PickMasterApi pickMasterApi = new PickMasterApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(PickMasterPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(PickMasterPositiveCase testCase, @WithUser User client) {
        int addedObjectId = step("Add object", () -> {
            JsonObject responseObject = JsonParser.parseString(
                    addObjectApi.addObject(AddHouseObjectBuilder.addHouseObjectRequest())
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            int objectId = responseObject.getAsJsonObject("data").get("id").getAsInt();
            return objectId;
        });
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), addedObjectId)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        int orderId = step("Create order", () -> {
                    JsonObject responseObject = JsonParser.parseString(
                            createOrdersApi.createOrders(testCase.getCreateOrdersDto())
                                    .statusCode(200)
                                    .extract().asString()
                    ).getAsJsonObject();
                    int orderId1 = responseObject.getAsJsonObject("data").get("order_id").getAsInt();
                    return orderId1;
                }
        );
        List<Integer> mastersId = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId))
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
            System.out.println("List of Master IDs: " + currentMasterIds);
            return currentMasterIds;
        });
        step("Pick master", () -> {
            pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), mastersId.get(0))
                    .statusCode(200);
        });


    }


}
