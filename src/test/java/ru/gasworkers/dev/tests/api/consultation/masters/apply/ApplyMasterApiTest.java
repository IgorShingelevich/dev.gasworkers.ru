package ru.gasworkers.dev.tests.api.consultation.masters.apply;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Подтверждение выбора мастера онлайн")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CONSULTATION_NOW)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.MASTER)
@Tag(AllureTag.API)
public class ApplyMasterApiTest extends BaseApiTest {
    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final PickMasterApi pickMasterApi = new PickMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(ApplyMasterPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(ApplyMasterPositiveCase testCase, @WithUser User client) {
        int objectId = step("Add object", () -> {
            JsonObject responseObject = JsonParser.parseString(
                    addObjectApi.addObject(AddHouseObjectBuilder.addDefaultHouseObjectRequest()) //todo add to test case
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            int currentObjectId = responseObject.getAsJsonObject("data").get("id").getAsInt();
            return currentObjectId;
        });
        Integer addedEquipmentId = step("Add equipment", () -> {
            JsonObject responseObject = JsonParser.parseReader(
                    new JsonReader(new StringReader(
                            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId)
                                    .statusCode(200)
                                    .extract().asString()
                    ))
            ).getAsJsonObject();
            Integer equipmentId = responseObject.get("id").getAsInt();
            return equipmentId;
        });
        Integer orderId = step("Create order", () -> {
            JsonObject responseObject = JsonParser.parseReader(
                    new JsonReader(new StringReader(
                            createOrdersApi.createOrders(testCase.getCreateOrdersDto())
                                    .statusCode(200)
                                    .extract().asString()
                    ))
            ).getAsJsonObject();
            return responseObject.getAsJsonObject("data").get("order_id").getAsInt();
        });
        List<Integer> masterIdList = step("Get online masters", () -> {
            JsonArray responseObject = JsonParser.parseString(
                    onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId))
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonArray();
            List<Integer> currentMasterIds = new ArrayList<>();
            for (JsonElement element : responseObject) {
                currentMasterIds.add(element.getAsJsonObject().get("id").getAsInt());
            }
            return currentMasterIds;
        });
        step("Pick master", () -> {
            pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0))
                    .statusCode(200);
        });
        step("Apply master", () -> {
            applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId), masterIdList.get(0))
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
    }


}
