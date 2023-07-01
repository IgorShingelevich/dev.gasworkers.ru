//package ru.gasworkers.dev.tests.api.consultation.masters.pickMaster;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Owner;
//import io.qameta.allure.Story;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EnumSource;
//import ru.gasworkers.dev.allure.AllureEpic;
//import ru.gasworkers.dev.allure.AllureFeature;
//import ru.gasworkers.dev.allure.AllureTag;
//import ru.gasworkers.dev.api.consultation.isStarted.IsStartedApi;
//import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedResponseDto;
//import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
//import ru.gasworkers.dev.api.consultation.masters.pickMaster.PickMasterApi;
//import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
//import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
//import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
//import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
//import ru.gasworkers.dev.api.users.client.object.addObject.AddHouseObjectApi;
//import ru.gasworkers.dev.extension.user.User;
//import ru.gasworkers.dev.extension.user.WithUser;
//import ru.gasworkers.dev.tests.api.BaseApiTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static io.qameta.allure.Allure.step;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Owner("Igor Shingelevich")
//@Epic(AllureEpic.CONSULTATION)
//@Feature(AllureFeature.CONSULTATION_NOW)
//@Story("Выбор мастера онлайн")
//@Tag(AllureTag.REGRESSION)
//@Tag(AllureTag.CONSULTATION)
//@Tag(AllureTag.CLIENT)
//@Tag(AllureTag.MASTER)
//@Tag(AllureTag.API)
//public class PickMasterApiTest extends BaseApiTest {
//    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();
//    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
//    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
//    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
//    private final IsStartedApi isStartedApi = new IsStartedApi();
//    private final PickMasterApi pickMasterApi = new PickMasterApi();
//
//    @ParameterizedTest(name = "{0}")
//    @EnumSource(PickMasterPositiveCase.class)
//    @Tag(AllureTag.POSITIVE)
//    @DisplayName("Success case:")
//    void positiveTestCase(PickMasterPositiveCase testCase, @WithUser User client) {
//        Integer objectId = step("Add object", () -> {
//            JsonObject actualResponseObject = JsonParser.parseString(
//                    addObjectApi.addObject(AddHouseObjectBuilder.addDefaultHouseObjectRequest()) //todo add to test case
//                            .statusCode(200)
//                            .extract().asString()
//            ).getAsJsonObject();
//            Integer currentObjectId = actualResponseObject.getAsJsonObject("data").get("id").getAsInt();
//            return currentObjectId;
//        });
//        step("Add equipment", () -> {
//            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId)
//                    .statusCode(200)
//                    .extract().as(AddEquipmentResponseDto.class);
//        });
//        Integer orderId = step("Create order", () -> {
//                    JsonObject actualResponseObject = JsonParser.parseString(
//                            createOrdersApi.createOrders(testCase.getCreateOrdersDto())
//                                    .statusCode(200)
//                                    .extract().asString()
//                    ).getAsJsonObject();
//                    Integer currentOrderId = actualResponseObject.getAsJsonObject("data").get("order_id").getAsInt();
//                    return currentOrderId;
//                }
//        );
//        List<Integer> mastersIdList = step("Get online masters", () -> {
//            String responseString = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId))
//                    .statusCode(200)
//                    .extract().asString();
//            JsonObject actualResponseObject = JsonParser.parseString(responseString).getAsJsonObject();
//            JsonArray dataArray = actualResponseObject.getAsJsonArray("data");
//            List<Integer> currentMasterIds = new ArrayList<>();
//
//            for (JsonElement element : dataArray) {
//                JsonObject masterObject = element.getAsJsonObject();
//                int masterId = masterObject.get("id").getAsInt();
//                currentMasterIds.add(masterId);
//            }
//            System.out.println("List of Master IDs: " + currentMasterIds);
//            return currentMasterIds;
//        });
//        step("Is Started", () -> {
//            isStartedApi.isStarted(testCase.getIsStartedDto(orderId))
//                    .statusCode(200)
//                    .extract().as(IsStartedResponseDto.class);
//        });
//        Integer timetableId = step("Pick master", () -> {
//            JsonObject actualResponseObject = JsonParser.parseString(
//                    pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), mastersIdList.get(0))
//                            .statusCode(200)
//                            .extract().asString()
//            ).getAsJsonObject();
//            System.out.println("Actual response: " + actualResponseObject);
//
//            Boolean fromMaintenance = actualResponseObject.getAsJsonObject("data").get("from_maintenance").getAsBoolean();
//            System.out.println("From maintenance: " + fromMaintenance);
//
//            Boolean isInsuranceCase = actualResponseObject.getAsJsonObject("data").get("is_insurance_case").getAsBoolean();
//            System.out.println("Is insurance case: " + isInsuranceCase);
//
//            Boolean repairOrderId = actualResponseObject.getAsJsonObject("data").get("repair_order_id").isJsonNull();
//            System.out.println("Repair order id: " + repairOrderId);
//
//            Boolean maintenanceOrderId = actualResponseObject.getAsJsonObject("data").get("maintenance_order_id").isJsonNull();
//            System.out.println("Maintenance order id: " + maintenanceOrderId);
//
//            Integer currentTimetableId = actualResponseObject.getAsJsonObject("data").get("timetable_id").getAsInt();
//            System.out.println("Timetable ID: " + currentTimetableId);
//
//            assertEquals(false, fromMaintenance);
//            assertEquals(false, isInsuranceCase);
//            assertNull(repairOrderId);
//            assertNull(maintenanceOrderId);
//            assertNotNull(currentTimetableId);
//
//            return currentTimetableId;
//        });
//
//    }
//}
